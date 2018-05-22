package com.travel.dao;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import com.travel.bean.Group;

public interface GroupMapper {
	@Insert("insert into t_group (userid,groupname,createtime,auth) values (#{userid},#{groupname},now(),#{auth})")
	public int addGroup(Group group);

	@Select("select * from t_group where userid = #{userid}")
	public List<Group> queryGroups(long userid);
	
	@Select("select * from t_group where userid = #{userid} order by createtime desc limit 1")
	public Group queryLatestGroup(long userid);
	
	@Select("select * from t_group where id = #{groupid}")
	public Group queryGroupById(long groupid);
	
	@Select("select * from t_group where groupname = #{groupname}")
	public Group getGroupByName(String groupname);

	@Select("select * from t_group where groupname like #{groupname}")
	public List<Group> getGroupsByname(String groupname);

	@Select("select id from t_group where userid = #{userid}")
	public List<Long> queryGroupIdsByUserId(long userid);
}