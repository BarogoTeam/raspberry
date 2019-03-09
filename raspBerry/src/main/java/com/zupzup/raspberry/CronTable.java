package com.zupzup.raspberry;

import com.zupzup.raspberry.domain.AlarmDomain;
import com.zupzup.raspberry.domain.SequenceDomain;
import com.zupzup.raspberry.service.SeatCronService;
import com.zupzup.raspberry.service.TelegramService;
import org.json.simple.JSONArray;
import org.json.simple.JSONAware;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
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

import java.util.ArrayList;
import java.util.List;

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

    @Scheduled(cron = "0 0 * * * *")
    public void startTelegramBot() {
        TelegramService.telegramBotManager();
    }

    // 애플리케이션 시작 후 1초 후에 첫 실행, 그 후 매 1초마다 주기적으로 실행한다.
    @Scheduled(initialDelay = 10000, fixedDelay = 10000)
    public void otherJob() {
        //TODO API호출해서 좌석 데이터 받아오기 by thesun.kim

        //TODO SeatCronServiceImpl 통해서 데이터 정재해서 저장하기  by thesun.kim
        // 실행될 로직

        //TODO 알람데이터에 데이터 컬럼 보완필요 by thesun.kim
        logger.info(seatCronService.findAll().toString());
        List<AlarmDomain> alarmList = seatCronService.findAll();

        JSONParser parser = new JSONParser();
        JSONArray items = new JSONArray();
        String playDate;
        Integer reservationNumber;
        for(AlarmDomain alarm : alarmList) {
            playDate = alarm.getPlayDate();;
            reservationNumber = alarm.getReservationNumber();
            for(SequenceDomain sequence : alarm.getSequences()) {
                JSONObject paramList = new JSONObject();
                paramList.put("cinemaId",sequence.getCinemaId());
                paramList.put("screenId",sequence.getScreenId());
                paramList.put("playDate",playDate);
                paramList.put("playSequence",sequence.getPlaySequence());

                try {
                    JSONObject jobj = (JSONObject) parser.parse(getDataFromAPI(LOTTE_TICKETING_DATA_URL, "GetSeats", paramList));
                    jobj = (JSONObject) jobj.get("BookingSeats");
                    items = (JSONArray) jobj.get("Items");
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                ArrayList<String> seatNoList = sequence.getSeatNoList();
                int seatNum = seatNoList .size(); //알람에 등록한 좌석 개수 ( = 가능 좌석 개수 )
                logger.info("" + items.size());
                for (Object item : items) {  //예약되어있는 좌석 리스트
                    for (String seatNo : seatNoList) {  //알람에 등록한 좌석 리스트
                        logger.info(item.toString());
                        if (((JSONObject) item).get("seatNo") == seatNo) {   //알람에 등록한 좌석이 예약되어있으면 가능 좌석 개수 차감
                            seatNum--;
                        }
                    }
                }
                if (seatNum >= alarm.getReservationNumber()) {
                    logger.info("예약 인원수만큼 자리 생김!");
                }
                //TODO 이전데이터를 저장하고있어야 비교해서 차이가있는지 알수가있음 by thesun.kim
            }
        }
    }

    private String getDataFromAPI(String url, String methodNamem, JSONObject paramList) {

        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders requestHeaders = new HttpHeaders();
        requestHeaders.set("Content-Type", "application/x-www-form-urlencoded");

        paramList.put("MethodName",methodNamem);
        paramList.put("channelType","HO");
        paramList.put("osType","Chrome");
        paramList.put("osVersion","Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/67.0.3396.62 Safari/537.36");

        MultiValueMap<String, String> postParameters = new LinkedMultiValueMap<String, String>();
        postParameters.add("paramList", paramList.toJSONString());
        HttpEntity<MultiValueMap<String, String>> requestEntity = new HttpEntity<MultiValueMap<String, String>>(postParameters, requestHeaders);

        ResponseEntity<String> responseEntity = restTemplate.exchange(url, HttpMethod.POST , requestEntity, String.class);

        String result = responseEntity.getBody();
        return result;
    }
}