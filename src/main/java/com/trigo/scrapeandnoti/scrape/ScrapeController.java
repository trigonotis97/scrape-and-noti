package com.trigo.scrapeandnoti.scrape;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;

@Controller
@RequiredArgsConstructor
public class ScrapeController {

    private final ScrapeService scrapeService;

    @GetMapping("init")
    ModelAndView init(){
        ModelAndView mv = new ModelAndView();
        mv.setViewName  ("init.html");
        return mv;
    }
    @GetMapping("viewText")
    ModelAndView getTextFromUrl() throws IOException {
        ModelAndView mv = new ModelAndView();
         mv.setViewName  ("viewText.html");
         mv.addObject("result",scrapeService.getText());
       return mv;
    }

}
