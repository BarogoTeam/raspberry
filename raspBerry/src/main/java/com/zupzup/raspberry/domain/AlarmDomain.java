package com.zupzup.raspberry.domain;

import lombok.Data;

import java.util.ArrayList;

@Data
public class AlarmDomain extends Domain {
    private String movieId;
    private String movieNameKr;
    private String playDate;
    private Integer reservationNumber;
    private ArrayList<SequenceDomain> sequences;

    public String getMovieId() {
        return movieId;
    }

    public void setMovieId(String movieId) {
        this.movieId = movieId;
    }

    public String getMovieNameKr() {
        return movieNameKr;
    }

    public void setMovieNameKr(String movieNameKr) {
        this.movieNameKr = movieNameKr;
    }

    public String getPlayDate() {
        return playDate;
    }

    public void setPlayDate(String playDate) {
        this.playDate = playDate;
    }

    public Integer getReservationNumber() {
        return reservationNumber;
    }

    public void setReservationNumber(Integer reservationNumber) {
        this.reservationNumber = reservationNumber;
    }

    public ArrayList<SequenceDomain> getSequences() {
        return sequences;
    }

    public void setSequences(ArrayList<SequenceDomain> sequences) {
        this.sequences = sequences;
    }
}
