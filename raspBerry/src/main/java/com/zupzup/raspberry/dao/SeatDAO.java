package com.zupzup.raspberry.dao;

import java.util.List;

import com.zupzup.raspberry.domain.SeatDomain;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface SeatDAO {


    @Select("SELECT * FROM alarms")
    public List<SeatDomain> findAll();

    //TODO 임시 쿼리문 : 데이터 테이블 정의 해야함 by thesun.kim
    @Insert("INSERT INTO alarms(seatInfo) VALUES(#{seatInfo})")
    public void insertSeat(SeatDomain seatInfo);

}
