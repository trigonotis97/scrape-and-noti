package com.trigo.scrapeandnoti.noti;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

@Service
@RequiredArgsConstructor
public class NotiService {
    public ResponseEntity JANDIConnect(String webhookUrl){

        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("body" , "test body");
        params.add("connectColor" , "#FAC11B");


        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type","application/json");
        headers.add("Accept","application/vnd.tosslab.jandi-v2+json");

        HttpEntity<MultiValueMap<String, String>> entity = new HttpEntity<>(params, headers);

        RestTemplate rt = new RestTemplate();

        ResponseEntity<String> response = rt.exchange(
                webhookUrl,
                HttpMethod.POST, //{요청할 방식}
                entity, // {요청할 때 보낼 데이터}
                String.class
        );
      return response;
    }
}
