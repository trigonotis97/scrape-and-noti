package com.trigo.scrapeandnoti.scrape;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ScrapeController {

    @GetMapping("init")
    ModelAndView init(){
        ModelAndView mv = new ModelAndView();
        mv.setViewName("init.html");
        return mv;
    }

}
