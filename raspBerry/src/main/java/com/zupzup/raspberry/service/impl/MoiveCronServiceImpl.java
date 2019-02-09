package com.zupzup.raspberry.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zupzup.raspberry.dao.MovieDAO;
import com.zupzup.raspberry.domain.MovieDomain;
import com.zupzup.raspberry.service.MoiveCronService;

import java.util.List;

@Service
public class MoiveCronServiceImpl implements MoiveCronService{

    MovieDAO movieDao;

    @Override
    public MovieDomain findOneByMovieCode(int movieCode) {
        return movieDao.findByMovieCode(movieCode);
    }

    @Override
    public List<MovieDomain> findAll() {
        return movieDao.findAll();
    }
}
