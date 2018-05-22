package com.travel.dao;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import com.travel.bean.GroupCircle;

public interface GroupCircleMapper {
	@Select("select * from t_group_circle_fence where fencename = #{fencename}")
	public GroupCircle getFenceByName(String fencename);

	@Insert("insert into t_group_circle_fence (userid,groupid,fencename,latitude," +
			"longitude,creattime,radius) values (#{userid},#{groupid},#{fencename}," +
			"#{latitude},#{longitude},now(),#{radius})")
	public int addFence(GroupCircle cicleFence);

	@Select("select * from t_group_circle_fence where groupid = #{groupid} order by id desc limit 1")
	public GroupCircle getFenceByGroupId(long groupid);
}