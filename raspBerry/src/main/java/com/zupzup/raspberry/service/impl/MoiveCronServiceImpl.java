package com.zupzup.raspberry.service.impl;

import com.zupzup.raspberry.CronTable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zupzup.raspberry.dao.MovieDAO;
import com.zupzup.raspberry.domain.MovieDomain;
import com.zupzup.raspberry.service.MoiveCronService;

import java.util.List;

@Service
public class MoiveCronServiceImpl implements MoiveCronService{

    private static Logger logger = LoggerFactory.getLogger(CronTable.class);

    @Autowired
    MovieDAO movieDao;

    @Override
    public void updateMovie(MovieDomain movie){
        if(movieDao.isNotExist(movie)){
            movieDao.addMovie(movie);
            logger.info("updateMovie : " + movie.getName() + " 영화 추가 완료");
        }
    }
}
