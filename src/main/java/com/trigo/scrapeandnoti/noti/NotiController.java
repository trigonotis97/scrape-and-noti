package com.trigo.scrapeandnoti.noti;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequiredArgsConstructor
public class NotiController {

    private final NotiService notiService;

    //TODO : 텍스트 noti 보내기
    @GetMapping("jandi")
    public ModelAndView webhookConnenct(@RequestParam String webhookUrl){
        ResponseEntity responseEntity = notiService.JANDIConnect(null);
        ModelAndView mv = new ModelAndView();
        mv.addObject("result", responseEntity.toString());
        System.out.println(responseEntity.toString());
        return mv;
    }
}
