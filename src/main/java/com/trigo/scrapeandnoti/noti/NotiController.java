package com.trigo.scrapeandnoti.noti;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

@Controller
@RequiredArgsConstructor
public class NotiController {

    NotiService notiService;

    //TODO : 텍스트 noti 보내기
    @GetMapping("jandi")
    public void webhookConnenct(@RequestParam String webhookUrl){ //TODO : post 로 변경
        if(webhookUrl == null|| webhookUrl.isEmpty()){
            webhookUrl = "https://wh.jandi.com/connect-api/webhook/30712068/4d4f0cc191a3427262fd37854720a3b5";
            notiService.JANDIConnect(webhookUrl);
        }
        else {
            System.out.println("SAN ERROR !! : 웹훅없음");
            return;
        }
        RestTemplate template = new RestTemplate();
    }
}
