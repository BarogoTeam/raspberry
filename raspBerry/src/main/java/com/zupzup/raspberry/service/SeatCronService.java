package com.zupzup.raspberry.service;

import com.zupzup.raspberry.domain.AlarmDomain;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zupzup.raspberry.dao.SeatDAO;
import com.zupzup.raspberry.domain.SeatDomain;

import java.util.List;

public interface SeatCronService {
    public List<AlarmDomain> findAll();
//    public void insertSeat(SeatDomain seatDomain);
}
