package com.zupzup.raspberry.domain;

import lombok.Data;

@Data
public class SeatDomain extends Domain{

    private String seatInfo;

    public SeatDomain() {

    }

    public String getSeatInfo() {
        return seatInfo;
    }

    public void setSeatInfo(String seatInfo) {
        this.seatInfo = seatInfo;
    }

}
