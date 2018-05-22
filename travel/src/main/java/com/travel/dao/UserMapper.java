package com.travel.dao;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.travel.bean.User;

public interface UserMapper {
	@Select("select * from t_user")
	public List<User> getAllUser();

	@Select("select * from t_user where id = #{id}")
	public User getUserById(long id);

	@Select("select * from t_user where phone = #{phone}")
	public User getUserByPhone(String phone);
	
	@Select("select * from t_user where username = #{username}")
	public User getUserByName(String username);

	@Select("select * from t_user where username like #{username}")
	public List<User> getUsersByUsername(String username);

	@Insert("insert into t_user (username,email,phone,password,registertime,logintime,status)"
			+ " values(#{username},#{email},#{phone},#{password},now(),now(),#{status})")
	public int addUser(User user);

	@Update("update t_user set longitude = #{longitude},latitude=#{latitude},updatelocationtime = #{updatelocationtime} where id = #{id}")
	public int udpateLocation(User user);

	@Update("update t_user set xingetoken = #{xingetoken} where id = #{id}")
	public int updateXingeToken(User user);
	
	@Update("update t_user set username = #{0} where id = #{1}")
	public int updateUsername(String username,long userid);
	
	@Update("update t_user set email = #{0} where id = #{1}")
	public int updateEmail(String email,long userid);
	
	@Update("update t_user set sign = #{0} where id = #{1}")
	public int updateSign(String sign,long userid);

	@Update("update t_user set head = #{0} where id = #{1}")
	public int updateHead(String head, long userId);
	
	@Update("update t_user set password = #{0} where id = #{1}")
	public int updatePassword(String password, long userid);

	@Select("select xingetoken from t_user where id=#{userid}")
	public String getXingeTokenByUserId(long userid);

	@Update("update t_user set loginTime = now() where id = #{userId}")
	public int updateLoginTime(long userId);

	@Select("select * from t_user where username = #{username} and phone = #{phone}")
	public User getPassword(User user);
}