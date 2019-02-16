package com.zupzup.raspberry;

import com.zupzup.raspberry.service.SeatCronService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.http.HttpMessageConverters;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import java.net.http.HttpHeaders;
import java.util.ArrayList;
import java.util.List;

@Component
public class CronTable {

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
    @Scheduled(initialDelay = 1000, fixedDelay = 10000)
    public void otherJob() {
        //TODO API호출해서 좌석 데이터 받아오기 by thesun.kim

        //TODO SeatCronServiceImpl 통해서 데이터 정재해서 저장하기  by thesun.kim
        // 실행될 로직
        getMovies();
        logger.info(seatCronService.findAll().toString());
        logger.info("every sec cron");
    }

    private String getMovies() {
//        BufferedReader in = null;
//
//        try {
//            URL obj = new URL("http://www.lottecinema.co.kr/LCWS/Movie/MovieData.aspx"); // 호출할 url
//            HttpURLConnection con = (HttpURLConnection)obj.openConnection();
//
//            con.setRequestMethod("GET");
//
//            in = new BufferedReader(new InputStreamReader(con.getInputStream(), "UTF-8"));
//
//            String line;
//            while((line = in.readLine()) != null) { // response를 차례대로 출력
//                System.out.println(line);
//            }
//        } catch(Exception e) {
//            e.printStackTrace();
//        } finally {
//            if(in != null) try { in.close(); } catch(Exception e) { e.printStackTrace(); }
//        }


        RestTemplate restTemplate = new RestTemplate();
        String url = "https://openapi.naver.com/v1/language/translate";

        HttpHeaders requestHeaders = new HttpHeaders();
        requestHeaders.set("Content-Type", "application/x-www-form-urlencoded");
        requestHeaders.set("X-Naver-Client-Id", "WYtueaQWm4bbvmwPEn6R");
        requestHeaders.set("X-Naver-Client-Secret", "yXj4Gj6oPL");

        MultiValueMap<String, String> postParameters = new LinkedMultiValueMap<String, String>();
        postParameters.add("source", "ko");
        postParameters.add("target", "en");
        postParameters.add("text", board.getLine1() + ". " + board.getLine2());
        HttpEntity<MultiValueMap<String, String>> requestEntity = new HttpEntity<MultiValueMap<String, String>>(postParameters, requestHeaders);

        ResponseEntity<String> responseEntity = restTemplate.exchange(url, HttpMethod.POST , requestEntity, String.class);

        String result = responseEntity.toString();
        int in_text = result.indexOf("translatedText")+17;
        int out_text = result.substring(in_text).indexOf("}") + in_text -1;
        String result_text = result.substring(in_text,out_text);

        System.out.println(board.getLine1() + " " + board.getLine2() + " ---translated_text-->" + result_text);

        return "";
    }
}