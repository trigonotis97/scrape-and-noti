package com.trigo.scrapeandnoti.scrape;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class ScrapeService {
    public void process(){
        /* //TODO: move to project usage or notion page
        1. 요청할 url
        2. 추출할 데이터
        3. 에러상황정의
        4. 정상상황정의
        5. noti 발생 상황 정의 (일정숫자 이상, 이하, 특정단어포함시)
        6. 요청로그저장
         */
    }

    public Document getText() throws IOException {
        Document doc = Jsoup.connect("http://github.com/trigonotis97").get();

        // Response로부터 Document 얻어오기
        Connection.Response response = Jsoup.connect("http://www.google.com")
                .method(Connection.Method.GET)
                .execute();
        Document google3 = response.parse();
        return doc;
    }

}
