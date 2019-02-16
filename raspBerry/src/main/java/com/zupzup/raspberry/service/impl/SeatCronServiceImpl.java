package com.zupzup.raspberry.service.impl;

import com.zupzup.raspberry.CronTable;
import com.zupzup.raspberry.domain.AlarmDomain;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import com.zupzup.raspberry.dao.SeatDAO;
import com.zupzup.raspberry.domain.SeatDomain;
import com.zupzup.raspberry.service.SeatCronService;

import java.util.List;

@Service
public class SeatCronServiceImpl implements SeatCronService{

    @Autowired
    SeatDAO seatDao;

    private static Logger logger = LoggerFactory.getLogger(SeatCronServiceImpl.class);

    @Override
    public List<AlarmDomain> findAll() {
        List<AlarmDomain> result = seatDao.selectAlarm();
        return result;
    }

//    @Override
//    public void insertSeat(SeatDomain seatDomain) {
//        seatDao.insertSeat(seatDomain);
//    }
}
