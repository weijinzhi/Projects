package com.travel.dao;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import com.travel.bean.GroupNotify;

public interface GroupNotifyMapper {
	@Insert("insert into t_group_notify (groupid,content,title,createtime)"
			+ " values(#{groupid},#{content},#{title},now())")
	public int addNotify(GroupNotify notify);

	@Select("select * from t_group_notify where groupid = #{groupid} and content = #{content} and " +
			"title = #{title}")
	public GroupNotify getNotify(GroupNotify groupNotify);

	@Select("select * from t_group_notify where groupid = #{groupid} order by id desc")
	public List<GroupNotify> getAllNotify(long groupid);
}