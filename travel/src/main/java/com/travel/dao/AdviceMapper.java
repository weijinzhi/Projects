package com.travel.dao;

import org.apache.ibatis.annotations.Insert;

import com.travel.bean.Advice;

public interface AdviceMapper {

	@Insert("insert into t_advice (userid,title,content,createtime) values(#{userid},#{title},#{content},now())")
	int addAdvice(Advice advice);
}