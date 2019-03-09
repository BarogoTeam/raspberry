package com.zupzup.raspberry.domain;

import lombok.Data;

import java.util.ArrayList;

@Data
public class SequenceDomain extends Domain {
    private String screenId;
    private String cinemaId;
    private String screenNameKr;
    private String screenDivisionNameKr;
    private String filmNameKr;
    private String startTime;
    private String endTime;
    private Integer playSequence;
    private ArrayList<String> seatNoList;

    public String getScreenId() {
        return screenId;
    }

    public void setScreenId(String screenId) {
        this.screenId = screenId;
    }

    public String getCinemaId() {
        return cinemaId;
    }

    public void setCinemaId(String cinemaId) {
        this.cinemaId = cinemaId;
    }

    public String getScreenNameKr() {
        return screenNameKr;
    }

    public void setScreenNameKr(String screenNameKr) {
        this.screenNameKr = screenNameKr;
    }

    public String getScreenDivisionNameKr() {
        return screenDivisionNameKr;
    }

    public void setScreenDivisionNameKr(String screenDivisionNameKr) {
        this.screenDivisionNameKr = screenDivisionNameKr;
    }

    public String getFilmNameKr() {
        return filmNameKr;
    }

    public void setFilmNameKr(String filmNameKr) {
        this.filmNameKr = filmNameKr;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public Integer getPlaySequence() {
        return playSequence;
    }

    public void setPlaySequence(Integer playSequence) {
        this.playSequence = playSequence;
    }

    public ArrayList<String> getSeatNoList() {
        return seatNoList;
    }

    public void setSeatNoList(ArrayList<String> seatNoList) {
        this.seatNoList = seatNoList;
    }
}
