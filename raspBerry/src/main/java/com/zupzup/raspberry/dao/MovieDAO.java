package com.zupzup.raspberry.dao;

import com.zupzup.raspberry.domain.MovieDomain;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface MovieDAO {

    @Select("SELECT * FROM movies WHERE movieCode=#{movieCode}")
    public MovieDomain findByMovieCode(@Param("movieCode") int movieCode);


    @Select("SELECT * FROM movies")
    public List<MovieDomain> findAll();

}
