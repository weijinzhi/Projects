package com.travel.dao;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import com.travel.bean.Friend;

public interface FriendMapper {
	@Insert("insert into t_friend (userid,friendid) values(#{userid},#{friendid})")
	public int addFriend(Friend friend);
	
	@Select("select * from t_friend where userid = #{userid} and friendid = #{friendid}")
	public Friend queryFriendByUserId(Friend friend);
	
	@Select("select * from t_friend where userid = #{userid}")
	public List<Friend> queryFriendsByUserId(long userid);
}