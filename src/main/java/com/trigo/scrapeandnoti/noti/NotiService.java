package com.trigo.scrapeandnoti.noti;

import lombok.RequiredArgsConstructor;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestClient;

import java.text.SimpleDateFormat;
import java.util.SimpleTimeZone;

@Service
@RequiredArgsConstructor
public class NotiService {
    private final String jandiWebhook = "https://wh.jandi.com/connect-api/webhook/30712068/bd3f6c4f8ac3a18b7cef5135080da2d2";
    public ResponseEntity JANDIConnect(String message){

        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("body" , "test body");
        params.add("connectColor" , "#FAC11B");

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type","application/json");
        headers.add("Accept","application/vnd.tosslab.jandi-v2+json");

        RestClient restClient = RestClient.create();
        ResponseEntity<Void> response = null;
        JandiDto body = new JandiDto();
        body.setMessage( "듄 아이맥스 예매가능날짜 : " + message +
                "\\n알림발생 : " + new SimpleDateFormat("yyyy-MM-dd(E) HH:mm:ss").format(System.currentTimeMillis()));
        try {
            response = restClient.post()
                    .uri(jandiWebhook)
                    .body(body.toString())
                    .header("Content-Type", "application/json")
                    .header("Accept", "application/vnd.tosslab.jandi-v2+json")
                    .retrieve()
                    .toBodilessEntity();
        }
        catch (Exception e ){
            e.printStackTrace();
        }
      return response;
    }
}
