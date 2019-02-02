package com.zupzup.raspberry.domain;

import lombok.Data;

@Data
public class SeatDomain {

    private String seatInfo;

    public SeatDomain() {

    }

    public SeatDomain(String seatInfo) {
        this.seatInfo = seatInfo;
    }

}
