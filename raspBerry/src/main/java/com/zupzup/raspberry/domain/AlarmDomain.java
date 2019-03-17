package com.zupzup.raspberry.domain;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;

@Data
@Document(collection = "alarms")
public class AlarmDomain extends Domain {
    @Id
    private String _id;
    private String movieId;
    private String movieNameKr;
    private String playDate;
    private Integer reservationNumber;
    private ArrayList<SequenceDomain> sequences;
    private String email;
    private boolean isRun;

    public boolean isRun() {
        return isRun;
    }

    public void setRun(boolean run) {
        isRun = run;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

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
