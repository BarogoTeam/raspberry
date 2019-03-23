package com.zupzup.raspberry.dao;

import com.mongodb.client.result.UpdateResult;
import com.zupzup.raspberry.domain.AlarmDomain;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class SeatDAO {

    @Autowired
    public MongoTemplate mongoTemplate;

    public List<AlarmDomain> selectAlarm() {
        return mongoTemplate.findAll(AlarmDomain.class,"alarms");
    }

    public List<AlarmDomain> selectRunAlarm() {
        Criteria criteria = new Criteria().where("isRun").is(true);
        return mongoTemplate.find(new Query(criteria),AlarmDomain.class,"alarms");
    }

    public void setRun(String id, boolean flag){
        Criteria criteria = new Criteria().where("_id").is(id);
        UpdateResult updateResult = mongoTemplate.updateMulti(new Query(criteria), new Update().set("isRun", false), "alarms");
    }

}