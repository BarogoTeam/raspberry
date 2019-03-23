package com.zupzup.raspberry.dao;

import com.zupzup.raspberry.domain.MovieDomain;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class MovieDAO {

    @Autowired
    public MongoTemplate mongoTemplate;

    public boolean isNotExist(MovieDomain movie){
        Criteria criteria = new Criteria().where("id").is(movie.getId());
        if(mongoTemplate.find(new Query(criteria),MovieDomain.class,"movies").isEmpty()) {
            return true;
        }
        return false;
    }

    public void addMovie(MovieDomain movie){
        mongoTemplate.insert(movie);
    }
}
