package com.trigo.scrapeandnoti.scrape;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;

@Service
public class ScrapeService {
    public void process() {
        /* //TODO: move to project usage or notion page
        1. 요청할 url
        2. 추출할 데이터
        3. 에러상황정의
        4. 정상상황정의
        5. noti 발생 상황 정의 (일정숫자 이상, 이하, 특정단어포함시)
        6. 요청로그저장
         */
    }

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

    public Document getText(String url) throws IOException {
        Document doc = Jsoup.connect(url).get();

        // Response로부터 Document 얻어오기
        Connection.Response response = Jsoup.connect("http://www.google.com")
                .method(Connection.Method.GET)
                .execute();
        Document google3 = response.parse();
        return doc;
    }

    public String getDuneDate() throws IOException {
        RestClient restClient = RestClient.create();
        ResponseEntity<Void> response = null;

        response = restClient.post()
                .uri("http://ticket.cgv.co.kr/CGV2011/RIA/CJ000.aspx/CJ_TICKET_SCHEDULE_TOTAL_PLAY_YMD")
                .body(requestBody)
                .header("Content-Type", "application/json")
                .header("Accept", "application/json")
                .retrieve()
                .toBodilessEntity();
        return null;
    }

}
