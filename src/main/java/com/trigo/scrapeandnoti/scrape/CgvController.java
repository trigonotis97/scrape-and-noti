package com.trigo.scrapeandnoti.scrape;

import lombok.RequiredArgsConstructor;
import org.apache.commons.lang.StringEscapeUtils;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("dune")
public class CgvController {
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
    ModelAndView getDuneDate() throws IOException {
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
            String body = """
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
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setDoInput(true);
            conn.setDoOutput(true);
            conn.setRequestProperty("Content-Type", "application/json");
            conn.setRequestProperty("Accept", "application/json");

            byte[] input;
            try (OutputStream os = conn.getOutputStream()) {
                input = body.getBytes("utf-8");
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
        } catch (Exception e) {
            e.printStackTrace();
        }

        String resultXml;
        if(!result.isEmpty()){
            resultXml = StringEscapeUtils.unescapeJava(result.get(0));
            System.out.println(resultXml);
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
            mv.addObject("result",data);
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return mv;
    }
}
