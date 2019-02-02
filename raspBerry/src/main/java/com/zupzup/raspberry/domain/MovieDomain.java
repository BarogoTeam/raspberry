package com.zupzup.raspberry.domain;

import lombok.Data;

@Data
public class MovieDomain {

    private int movieCode;
    private String movieName;

    public MovieDomain() {

    }

    public MovieDomain(int movieCode, String movieName) {
        this.movieCode = movieCode;
        this.movieName = movieName;
    }

}
