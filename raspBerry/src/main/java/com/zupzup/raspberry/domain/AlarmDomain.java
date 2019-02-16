package com.zupzup.raspberry.domain;

import lombok.Data;

import java.util.ArrayList;

@Data
public class AlarmDomain {
    private String movieId;
    private ArrayList<String> cinemaNames;
    private ArrayList<String> cinemaTypes;
    private String date;
    private ArrayList<Integer> weekDays;
    private String startTime;
    private String endTime;
    private ArrayList<String> seatNoList;
    private Integer reservationNumber;
    private Boolean isRun;
}
