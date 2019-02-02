package com.zupzup.raspberry.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zupzup.raspberry.dao.MovieDAO;
import com.zupzup.raspberry.domain.MovieDomain;

import java.util.List;

public interface MoiveCronService {
    public MovieDomain findOneByMovieCode(int movieCode);
    public List<MovieDomain> findAll();
}
