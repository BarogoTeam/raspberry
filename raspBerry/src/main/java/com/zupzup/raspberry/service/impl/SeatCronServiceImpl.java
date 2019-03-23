package com.zupzup.raspberry.service.impl;

import com.zupzup.raspberry.dao.SeatDAO;
import com.zupzup.raspberry.domain.AlarmDomain;
import com.zupzup.raspberry.service.SeatCronService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    @Override
    public List<AlarmDomain> findRunAlarms() {
        List<AlarmDomain> result = seatDao.selectRunAlarm();
        return result;
    }

    @Override
    public void setRun(String id, boolean flag){
        seatDao.setRun(id, flag);
    }

}
