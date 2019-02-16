package com.zupzup.raspberry.domain;

import lombok.Data;

@Data
public class SeatDomain {

    private String seatInfo;

    /*  getSeat API 호출변수  */
    private int screenId;
    private String screenDivisionCode;
    private int cinemaId;
    private String playDate;
    private int playSequence;

    public SeatDomain() {

    }

    public int getCinemaId() {
        return cinemaId;
    }

    public String getSeatInfo() {
        return seatInfo;
    }

    public void setSeatInfo(String seatInfo) {
        this.seatInfo = seatInfo;
    }

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
}
