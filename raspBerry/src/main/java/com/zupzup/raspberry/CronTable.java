package com.zupzup.raspberry;

import com.zupzup.raspberry.domain.SeatDomain;
import com.zupzup.raspberry.service.SeatCronService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

@Component
public class CronTable {

    final static String LOTTE_TICKETING_DATA_URL = "http://www.lottecinema.co.kr/LCWS/Ticketing/TicketingData.aspx";

    @Autowired
    SeatCronService seatCronService;

    private static Logger logger = LoggerFactory.getLogger(CronTable.class);

    // 매일 5시 30분 0초에 실행한다.
    @Scheduled(cron = "0 30 5 * * *")
    public void aJob() {
        // 실행될 로직
    }

    // 매일 0시 0분 0초에 실행한다.
    @Scheduled(cron = "0 0 0 * * *")
    public void anotherJob() {
        //TODO API호출해서 영화 데이터 받아오기 by thesun.kim

        //TODO MoiveCronServiceImpl 통해서 데이터 정재해서 저장하기 by thesun.kim
        // 실행될 로직


        logger.info("daily cron");
    }

    // 애플리케이션 시작 후 1초 후에 첫 실행, 그 후 매 1초마다 주기적으로 실행한다.
    @Scheduled(initialDelay = 10000, fixedDelay = 10000)
    public void otherJob() {
        //TODO API호출해서 좌석 데이터 받아오기 by thesun.kim

        //TODO SeatCronServiceImpl 통해서 데이터 정재해서 저장하기  by thesun.kim
        // 실행될 로직
        logger.info(seatCronService.findAll().toString());
        SeatDomain seatDomain = new SeatDomain();
        seatDomain.setCinemaId(1013);
        seatDomain.setScreenId(101305);
        seatDomain.setPlayDate("2019-02-16");
        seatDomain.setPlaySequence(4);
        getDataFromAPI(LOTTE_TICKETING_DATA_URL,"GetSeats",seatDomain);
    }

    private String getDataFromAPI(String url, String methodNamem, SeatDomain seatDomain) {

        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders requestHeaders = new HttpHeaders();
        requestHeaders.set("Content-Type", "application/x-www-form-urlencoded");

        JSONObject paramList = new JSONObject();
        paramList.put("MethodName",methodNamem);
        paramList.put("channelType","HO");
        paramList.put("osType","Chrome");
        paramList.put("osVersion","Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/67.0.3396.62 Safari/537.36");
        paramList.put("cinemaId",seatDomain.getCinemaId());
        paramList.put("screenId",seatDomain.getScreenId());
        paramList.put("playDate",seatDomain.getPlayDate());
        paramList.put("playSequence",seatDomain.getPlaySequence());

        MultiValueMap<String, String> postParameters = new LinkedMultiValueMap<String, String>();
        postParameters.add("paramList", paramList.toJSONString());
        HttpEntity<MultiValueMap<String, String>> requestEntity = new HttpEntity<MultiValueMap<String, String>>(postParameters, requestHeaders);

        ResponseEntity<String> responseEntity = restTemplate.exchange(url, HttpMethod.POST , requestEntity, String.class);

        String result = responseEntity.toString();

        return result;
    }
}