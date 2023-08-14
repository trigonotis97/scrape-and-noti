package com.trigo.scrapeandnoti.scrape;

import org.jsoup.nodes.Document;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class ScrapeServiceTest {

    @Test
    void getText() throws IOException {

        Document out = Mockito.mock(ScrapeService.class).getText();
        System.out.println(out);
        System.out.println(out.toString());

        Object obj =   Mockito.mock(ScrapeService.class);
        assertNotNull(out);

    }
}
