package com.trigo.scrapeandnoti.schedule;

import com.trigo.scrapeandnoti.noti.NotiService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.core.task.TaskExecutor;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Component;

import java.util.concurrent.ThreadPoolExecutor;

/*
@Component
@RequiredArgsConstructor
 */
public class TaskScheduler {

    /*
    private final NotiService notiService;
    private TaskExecutor taskExecutor;
    @Bean(name = "taskExecutor")
    public ThreadPoolTaskExecutor executor(){
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(5);
        executor.setQueueCapacity(20);
        executor.setMaxPoolSize(10);
        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
        return executor;
    }

    public TaskScheduler(TaskExecutor taskExecutor) {
        this.taskExecutor = taskExecutor;
    }


    private class JandiNotiTask implements Runnable{

        @Override
        public void run() {
            notiService.JANDIConnect(null);
        }
    }
    */
}
