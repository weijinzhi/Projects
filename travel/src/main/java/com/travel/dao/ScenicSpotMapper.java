package com.travel.dao;

import java.util.List;

import org.apache.ibatis.annotations.Select;

import com.travel.bean.ScenicSpot;

public interface ScenicSpotMapper {
	@Select("select * from t_scenic_spot order by id asc limit 0,10")
	List<ScenicSpot> getAllscenic();
}