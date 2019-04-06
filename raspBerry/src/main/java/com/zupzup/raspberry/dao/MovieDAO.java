package com.zupzup.raspberry.dao;

import com.zupzup.raspberry.domain.MovieDomain;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;

@Component
public class MovieDAO {

    @Autowired
    public MongoTemplate mongoTemplate;

    public boolean isNotExist(MovieDomain movie){
        Criteria criteria = new Criteria().where("movieId").is(movie.getMovieId());
        if(mongoTemplate.find(new Query(criteria),MovieDomain.class,"movies").isEmpty()) {
            return true;
        }
        return false;
    }

    public void addMovie(MovieDomain movie){
        mongoTemplate.insert(movie);
    }
}
