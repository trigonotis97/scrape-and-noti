package com.trigo.scrapeandnoti.crawling;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.resource.HttpResource;

@Controller
public class CrawlingController {

    @GetMapping("init")
    ModelAndView init(){
        ModelAndView mv = new ModelAndView();
        mv.setViewName("init.html");
        return mv;
    }
}
