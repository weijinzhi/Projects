package com.travel.dao;

import org.apache.ibatis.annotations.Select;

import com.travel.bean.Admin;

public interface AdminMapper {
	@Select("select * from t_admin where username = #{username}")
	public Admin getUserByName(String username);
}