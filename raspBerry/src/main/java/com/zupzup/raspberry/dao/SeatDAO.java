package com.zupzup.raspberry.dao;

//@Mapper
//public interface SeatDAO {
//    @Select("SELECT * FROM alarms")
//    public List<SeatDomain> findAll();
//
//    //TODO 임시 쿼리문 : 데이터 테이블 정의 해야함 by thesun.kim
//    @Insert("INSERT INTO alarms(seatInfo) VALUES(#{seatInfo})")
//    public void insertSeat(SeatDomain seatInfo);
//}

import com.zupzup.raspberry.domain.AlarmDomain;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class SeatDAO {

    @Autowired
    private MongoTemplate mongoTemplate;

    private static Logger logger = LoggerFactory.getLogger(SeatDAO.class);

    public List<AlarmDomain> selectAlarm() {
        return mongoTemplate.findAll(AlarmDomain.class, "alarms");
    }
}