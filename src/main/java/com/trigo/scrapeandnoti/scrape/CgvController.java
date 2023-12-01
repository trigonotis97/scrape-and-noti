package com.trigo.scrapeandnoti.scrape;

import lombok.RequiredArgsConstructor;
import org.apache.commons.lang.StringEscapeUtils;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.util.UriComponentsBuilder;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.*;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequiredArgsConstructor
@RequestMapping("dune")
public class CgvController {
    /*
    TODO : 코드 분리, xml 및 json 관련 util 코드 분리
     */
    private String requestBody = """
            {
              "REQSITE": "x02PG4EcdFrHKluSEQQh4A==",
              "TheaterCd": "LMP+XuzWskJLFG41YQ7HGA==",
              "ISNormal": "3y+GIXzg3xKpOjlKjH8+Fg==",
              "MovieGroupCd": "b1DKm0QaGWBPUNyJbsSF1A==",
              "ScreenRatingCd": "nG6tVgEQPGU2GvOIdnwTjg==",
              "MovieTypeCd": "nG6tVgEQPGU2GvOIdnwTjg==",
              "Subtitle_CD": "nG6tVgEQPGU2GvOIdnwTjg==",
              "SOUNDX_YN": "nG6tVgEQPGU2GvOIdnwTjg==",
              "Third_Attr_CD": "nG6tVgEQPGU2GvOIdnwTjg==",
              "Language": "zqWM417GS6dxQ7CIf65+iA=="
            }
            """;

    @GetMapping("/date")
    ModelAndView getDuneDate() throws IOException, XPathExpressionException, ParserConfigurationException, SAXException {
        ModelAndView mv = new ModelAndView();

         /*
        Connection.Response response = Jsoup.connect("http://www.google.com")
                .method(Connection.Method.GET)
                .execute();
        System.out.println(response.toString());
        */
        List<String> result = new ArrayList<>();

        try {
            URL url = new URL("http://ticket.cgv.co.kr/CGV2011/RIA/CJ000.aspx/CJ_TICKET_SCHEDULE_TOTAL_PLAY_YMD");

            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setDoInput(true);
            conn.setDoOutput(true);
            conn.setRequestProperty("Content-Type", "application/json");
            conn.setRequestProperty("Accept", "application/json");

            byte[] input;
            try (OutputStream os = conn.getOutputStream()) {
                input = requestBody.getBytes("utf-8");
                os.write(input, 0, input.length);
            }

            conn.getOutputStream().write(input);
            String line;

            try (BufferedReader br = new BufferedReader(new InputStreamReader(
                    conn.getInputStream()))) {
                while ((line = br.readLine()) != null) {
                    System.out.println(line);
                    result.add(line);
                }
                System.out.println("출력종료");
            }

            String responseBody;
            if (!result.isEmpty()) {
                responseBody = StringEscapeUtils.unescapeJava(result.get(0));
                String resultXml = parseJsonAndGetData(responseBody, "DATA");

                // TODO : xml to Listmap 으로 변경하기
                Document doc = xmlStringToDoc(resultXml);
                List<Map<String, String>> nodes = fromNodeList(eval(doc, "//CSchedule"));
                System.out.println(nodes);
                System.out.println(resultXml);

                System.out.println("====== 듄 상영일 목록 : ");
                //String showList = nodes.stream().
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return mv;
    }

    @GetMapping("/date2")
    ModelAndView getDuneDate2() throws IOException {
        ModelAndView mv = new ModelAndView();
        String data;
        try {
            URI uri = UriComponentsBuilder
                    .fromUriString("http://ticket.cgv.co.kr/CGV2011/RIA/CJ000.aspx/CJ_TICKET_SCHEDULE_TOTAL_PLAY_YMD")
                    .encode()
                    .build()
                    .toUri();
            RestTemplate restTemplate = new RestTemplate();
            ResponseEntity<String> result = restTemplate.postForEntity(uri, requestBody, String.class);
            data = result.getBody();
            System.out.println(data);
            mv.addObject("result", data);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return mv;
    }

    public NodeList eval(final Document doc, final String pathStr)
            throws XPathExpressionException {
        final XPath xpath = XPathFactory.newInstance().newXPath();
        final XPathExpression expr = xpath.compile(pathStr); //가져올 문법 선택
        return (NodeList) expr.evaluate(doc, XPathConstants.NODESET);
    }

    public List<Map<String, String>> fromNodeList(final NodeList nodes) {
        final List<Map<String, String>> out = new ArrayList<Map<String, String>>();
        int len = (nodes != null) ? nodes.getLength() : 0;
        for (int i = 0; i < len; i++) {
            NodeList children = nodes.item(i).getChildNodes();
            Map<String, String> childMap = new HashMap<String, String>();
            for (int j = 0; j < children.getLength(); j++) {
                Node child = children.item(j);
                if (child.getNodeType() == Node.ELEMENT_NODE)
                    childMap.put(child.getNodeName(), child.getTextContent());
            }
            out.add(childMap);
        }
        return out;
    }

    Document xmlStringToDoc(String xml) throws ParserConfigurationException, IOException, SAXException {
        /*Document doc = (Document) DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(xml);
        doc.getDocumentElement().normalize();
        System.out.println("Root: " + doc.getDocumentElement().getNodeName());
        */
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder documentBuilder = factory.newDocumentBuilder();
        InputStream is = new ByteArrayInputStream(xml.getBytes());
        Document doc = documentBuilder.parse(is);
        return doc;
    }

    String parseJsonAndGetData(String json, String targetName) throws JSONException {
        //TODO : 개선) json target 정하고 가져오는법 확장하기
        JSONObject jsonObject = new JSONObject(json);
        JSONObject root = jsonObject.getJSONObject("d");
        return root.getString(targetName);
    }
}

