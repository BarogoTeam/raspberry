package com.zupzup.raspberry;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class CronTable {

    // 매일 5시 30분 0초에 실행한다.
    @Scheduled(cron = "0 30 5 * * *")
    public void aJob() {
        // 실행될 로직
    }

    // 매월 1일 0시 0분 0초에 실행한다.
    @Scheduled(cron = "0 0 0 1 * *")
    public void anotherJob() {
        // 실행될 로직
    }

    // 애플리케이션 시작 후 1초 후에 첫 실행, 그 후 매 1초마다 주기적으로 실행한다.
    @Scheduled(initialDelay = 1000, fixedDelay = 1000)
    public void otherJob() {
    	System.out.println("test");
        // 실행될 로직
    }
}