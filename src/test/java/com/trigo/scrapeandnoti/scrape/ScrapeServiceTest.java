package com.trigo.scrapeandnoti.scrape;

import com.trigo.scrapeandnoti.noti.NotiService;
import org.jsoup.Connection;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.jsoup.select.Evaluator;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.http.ResponseEntity;

import java.io.IOException;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class ScrapeServiceTest {

    @Test
    void getText() throws IOException {

        ScrapeService scrapeService = new ScrapeService();

        Document out = scrapeService.getText("http://github.com/trigonotis97");
        System.out.println(out);
        System.out.println(out.toString());
        Elements []  href_list = new Elements[]{out.select("a")};

        assertNotNull(out);

//        assertTrue(Arrays.stream(href_list).findAny().);
        ;

    }

    @Test
    void testJANDIWebhook(){
        NotiService notiService = new NotiService();

        String webhookUrl = "https://wh.jandi.com/connect-api/webhook/30712068/4d4f0cc191a3427262fd37854720a3b5";

        ResponseEntity<String> s= notiService.JANDIConnect(webhookUrl);
        assertNotNull(s);
        System.out.println(s);


    }
}
