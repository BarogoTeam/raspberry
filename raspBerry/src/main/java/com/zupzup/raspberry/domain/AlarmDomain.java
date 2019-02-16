package com.zupzup.raspberry.domain;

import lombok.Data;

import java.util.ArrayList;

@Data
public class AlarmDomain extends Domain {
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

    /*  getSeat API 호출변수  */
    private int screenId;
    private String screenDivisionCode;
    private int cinemaId;
    private String playDate;
    private int playSequence;


    public int getScreenId() {
        return screenId;
    }

    public void setScreenId(int screenId) {
        this.screenId = screenId;
    }

    public String getScreenDivisionCode() {
        return screenDivisionCode;
    }

    public void setScreenDivisionCode(String screenDivisionCode) {
        this.screenDivisionCode = screenDivisionCode;
    }

    public void setCinemaId(int cinemaId) {
        this.cinemaId = cinemaId;
    }

    public String getPlayDate() {
        return playDate;
    }

    public void setPlayDate(String playDate) {
        this.playDate = playDate;
    }

    public int getPlaySequence() {
        return playSequence;
    }

    public void setPlaySequence(int playSequence) {
        this.playSequence = playSequence;
    }

    public Object getCinemaId() {
        return cinemaId;
    }
}
