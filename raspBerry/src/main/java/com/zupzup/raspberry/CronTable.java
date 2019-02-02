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
        //TODO API호출해서 영화 데이터 받아오기 by thesun.kim

        //TODO MoiveCronServiceImpl 통해서 데이터 정재해서 저장하기 by thesun.kim
        // 실행될 로직
    }

    // 애플리케이션 시작 후 1초 후에 첫 실행, 그 후 매 1초마다 주기적으로 실행한다.
    @Scheduled(initialDelay = 1000, fixedDelay = 1000)
    public void otherJob() {
        //TODO API호출해서 좌석 데이터 받아오기 by thesun.kim

        //TODO SeatCronServiceImpl 통해서 데이터 정재해서 저장하기  by thesun.kim
        // 실행될 로직
    }
}