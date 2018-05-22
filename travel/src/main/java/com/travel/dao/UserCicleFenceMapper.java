package com.travel.dao;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import com.travel.bean.UserCicleFence;

public interface UserCicleFenceMapper {
	@Select("select * from t_user_cicle_fence where fencename = #{fencename}")
	public UserCicleFence getFenceByName(String fencename);

	@Insert("insert into t_user_cicle_fence (userid,fencename,latitude," +
			"longitude,creattime,radius) values (#{userid},#{fencename}," +
			"#{latitude},#{longitude},now(),#{radius})")
	public int addFence(UserCicleFence cicleFence);

	@Select("select * from t_user_cicle_fence where userid = #{userid} order by id desc limit 1")
	public UserCicleFence getFenceByUserId(long userid);
}