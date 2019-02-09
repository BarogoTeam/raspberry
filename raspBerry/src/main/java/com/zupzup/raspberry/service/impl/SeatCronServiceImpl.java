package com.zupzup.raspberry.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zupzup.raspberry.dao.SeatDAO;
import com.zupzup.raspberry.domain.SeatDomain;
import com.zupzup.raspberry.service.SeatCronService;

import java.util.List;

@Service
public class SeatCronServiceImpl implements SeatCronService{

    SeatDAO seatDao;

    @Override
    public List<SeatDomain> findAll() {
        return seatDao.findAll();
    }

    @Override
    public void insertSeat(SeatDomain seatDomain) {
        seatDao.insertSeat(seatDomain);
    }
}
