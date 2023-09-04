package com.trigo.scrapeandnoti.scrape;

import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.jsoup.select.Evaluator;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

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
}
