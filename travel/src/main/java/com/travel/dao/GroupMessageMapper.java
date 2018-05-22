package com.travel.dao;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import com.travel.bean.GroupMessage;

public interface GroupMessageMapper {
	@Insert("insert into t_group_message (groupid,fromuserid,content,createtime) values(#{groupid},#{fromuserid},#{content},now())")
	public int addGroupMessage(GroupMessage groupMessage);
	
	@Select("select * from t_group_message where groupid = #{0} order by createtime desc limit #{1},#{2}")
	public List<GroupMessage> getGroupMessageByGroupId(long groupid,int start,int size);
}