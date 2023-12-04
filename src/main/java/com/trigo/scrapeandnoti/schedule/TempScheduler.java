package com.trigo.scrapeandnoti.schedule;


import com.trigo.scrapeandnoti.noti.NotiService;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TempScheduler {

    /*
    TODO : @Scheduled -> thread 를 사용한 스케줄러로 변경
     */
    private final NotiService notiService;
    //@Scheduled(cron = "0 0/1 * * * *")
    void run(){

        notiService.JANDIConnect(null);
    }
}
