package com.travel.dao;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import com.travel.bean.GroupMenber;

public interface GroupMenberMapper {
	@Insert("insert into t_groupmenber (groupid,userid) values (#{groupid},#{userid})")
	public int addGroupMenber(GroupMenber groupMenber);
	
	@Select("select * from t_groupmenber where groupid = #{groupid} and userid = #{userid}")
	public GroupMenber checkMeneber(GroupMenber groupMenber);
	
	@Select("select * from t_groupmenber where groupid = #{groupid}")
	public List<GroupMenber> queryGroupMenbers(long groupid);
	
	@Select("select groupid from t_groupmenber where userid = #{userid}")
	public List<Long> queryGroupNumberByUserId(long userid);

}