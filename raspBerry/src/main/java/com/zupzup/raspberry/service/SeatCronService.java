package com.zupzup.raspberry.service;

import com.zupzup.raspberry.domain.AlarmDomain;

import java.util.List;

public interface SeatCronService {
    public List<AlarmDomain> findAll();
    public List<AlarmDomain> findRunAlarms();
    public void setRun(String id, boolean flag);
}
