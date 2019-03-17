package com.zupzup.raspberry;

import com.zupzup.raspberry.domain.AlarmDomain;
import com.zupzup.raspberry.domain.SequenceDomain;
import com.zupzup.raspberry.service.SeatCronService;
import com.zupzup.raspberry.service.TelegramService;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
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

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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

    // 애플리케이션 시작 후 10초 후에 첫 실행, 그 후 매 10초마다 주기적으로 실행한다.
    @Scheduled(initialDelay = 10000, fixedDelay = 10000)
    public void otherJob() throws java.text.ParseException {
        JSONParser parser = new JSONParser();
        JSONArray items = new JSONArray();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-mm-dd");
        String today = format.format(new Date());
        Date todayDate = format.parse(today);

        List<AlarmDomain> alarmList = seatCronService.findAll();
        for(AlarmDomain alarm : alarmList) {
            Date playDate = format.parse(alarm.getPlayDate());
            if (isNotOldAlarm(todayDate, playDate)) {  //TODO DB쿼리로 넘기기 (추가로 isRun상태인조건도 추가) by thesun.kim
                for (SequenceDomain sequence : alarm.getSequences()) {
                    JSONObject paramList = getJsonObject(alarm, sequence);

                    try {
                        JSONObject jobj = (JSONObject) parser.parse(getDataFromAPI(LOTTE_TICKETING_DATA_URL, "GetSeats", paramList));
                        jobj = (JSONObject) jobj.get("BookingSeats");
                        items = (JSONArray) jobj.get("Items");
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }

                    int availableSeatNum = getAvailableSeatNum(items, sequence);

                    if (availableSeatNum >= alarm.getReservationNumber()) {
                        logger.info("예약 인원수만큼 자리 생김! TO 메신저방 : " + alarm.getEmail());
                        //TODO DB에서 해당 알람 email값으로 텔레그램방에 알림전송 해야함 by thesun.kim
                        //TODO 알람 보냈으면 isRun값 false처리해줘야함 by thesun.kim
                    }
                }
            }
        }
    }

    private int getAvailableSeatNum(JSONArray items, SequenceDomain sequence) {
        ArrayList<String> seatNoList = sequence.getSeatNoList();
        int seatNum = seatNoList.size(); //알람에 등록한 좌석 개수 ( = 가능 좌석 개수 )
        for (Object item : items) {  //예약되어있는 좌석 리스트
            for (String seatNo : seatNoList) {  //알람에 등록한 좌석 리스트
                String bookedSeat = ((JSONObject) item).get("SeatNo").toString();
                if (bookedSeat.equals(seatNo)) {   //알람에 등록한 좌석이 예약되어있으면 가능 좌석 개수 차감
                    seatNum--;
                }
            }
        }
        return seatNum;
    }

    private JSONObject getJsonObject(AlarmDomain alarm, SequenceDomain sequence) {
        JSONObject paramList = new JSONObject();
        paramList.put("cinemaId", sequence.getCinemaId());
        paramList.put("screenId", sequence.getScreenId());
        paramList.put("playDate", alarm.getPlayDate());
        paramList.put("playSequence", sequence.getPlaySequence());
        return paramList;
    }

    private boolean isNotOldAlarm(Date todayDate, Date playDate) {
        return todayDate.compareTo(playDate) >= 0;
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

        return responseEntity.getBody();
    }
}