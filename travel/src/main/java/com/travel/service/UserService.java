package com.travel.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.travel.bean.User;
import com.travel.dao.UserMapper;

@Service
public class UserService {
	@Autowired
	UserMapper userMapper;

	public List<User> getAllUsers() {
		return userMapper.getAllUser();
	}
	
	public User getUserById(long id) {
		return userMapper.getUserById(id);
	}

	public User getUserByPhone(String phone) {
		return userMapper.getUserByPhone(phone);
	}
	
	public User getUserByName(String username) {
		return userMapper.getUserByName(username);
	}

	public int register(String username,String email, String phone, String password, String status) {
		User user = new User();
		user.setPhone(phone);
		user.setPassword(password);
		user.setUsername(username);
		user.setEmail(email);
		user.setStatus(status);
		if(userMapper.getUserByPhone(phone) != null){
			return -1;
		}
		else if (userMapper.getUserByName(username) != null) {
			return -2;
		}else{
			int res = userMapper.addUser(user);
			System.out.println("testAddUser = " + res);
			return res;
		}
	}

	public int updateLocation(User user) {
		if (user.getId() == null) {
			return -1;
		}
		int res = userMapper.udpateLocation(user);
		return res;
	}

	public int updateXingeToken(User user) {
		if (user.getId() == null) {
			return -1;
		}
		int res = userMapper.updateXingeToken(user);
		return res;
	}

	public List<User> getUsersByUsername(String username) {
		StringBuilder sb = new StringBuilder();
		sb.append("%");
		sb.append(username);
		sb.append("%");
		return userMapper.getUsersByUsername(sb.toString());
	}

	public String getXingeTokenByUserId(long userid) {
		return userMapper.getXingeTokenByUserId(userid);
	}

	public int updateLoginTime(long userId) {
		int res = userMapper.updateLoginTime(userId);
		return res;
	}

	public int updateHead(String head, long userId) {
		int res = userMapper.updateHead(head, userId);
		return res;
	}

	public int updateUsername(String username, long userid) {
		int res = userMapper.updateUsername(username, userid);
		return res;
	}
	
	public int updateEmail(String email, long userid) {
		int res = userMapper.updateEmail(email, userid);
		return res;
	}

	public int updateSign(String sign, long userid) {
		int res = userMapper.updateSign(sign, userid);
		return res;
	}

	public int updatePassword(String password, long userid) {
		int res = userMapper.updatePassword(password, userid);
		return res;
	}

	public User getPassword(String username, String phone) {
		User user = new User();
		user.setUsername(username);
		user.setPhone(phone);
		return userMapper.getPassword(user);
	}

}
