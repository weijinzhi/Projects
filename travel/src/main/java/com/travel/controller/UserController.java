package com.travel.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.travel.bean.Friend;
import com.travel.bean.MessageWithBLOBs;
import com.travel.bean.User;
import com.travel.service.FriendService;
import com.travel.service.MessageService;
import com.travel.service.UserService;
import com.travel.util.TextUtils;

@Controller
@RequestMapping(value = "user", method = { RequestMethod.GET, RequestMethod.POST })
@ResponseBody
public class UserController {
	@Autowired
	UserService userService;
	@Autowired
	MessageService messageService;
	@Autowired
	FriendService friendService;

	/**
	 * 登录
	 * 
	 * @param phone
	 * @param password
	 * @return
	 */
	@RequestMapping("login")
	public JSONObject login(String username, String password, String status) {
		if (!checkParams(username, password,status)) {
			return getResult(-1, "参数错误", "");
		}
		User user = userService.getUserByName(username);
		if (user != null) {
			if (password.equals(user.getPassword())&&status.equals(user.getStatus())) {
				userService.updateLoginTime(user.getId());
				return getResult(1, "登录成功", JSON.toJSONString(user));
			} else {
				return getResult(3, "密码错误", "");
			}
		} else {
			return getResult(2, "用户不存在", "");
		}
	}

	/**
	 * 注册
	 * 
	 * @param phone
	 * @param password
	 * @return
	 */
	@RequestMapping("register")
	public JSONObject register(String username,String email,String phone, String password, String status) {
		if (!checkParams(username, email, phone, password, status)) {
			return getResult(-1, "参数错误", "");
		}
		User user = new User();
		user.setUsername(username);
		user.setEmail(email);
		user.setPhone(phone);
		user.setPassword(password);
		user.setStatus(status);
		int res = userService.register(username, email, phone, password, status);
		if (res == -1) {
			return getResult(2, "该手机号已经注册", "");
		}else if(res == -2){
			return getResult(2, "该用户名已经注册", "");
		}else {
			return getResult(1, "注册成功", "");
		}
	}
	
	/**
	 * 获取密码
	 * 
	 * @param phone
	 * @param password
	 * @return
	 */
	@RequestMapping("getPassword")
	public JSONObject getPassword(String username,String phone) {
		if (!checkParams(username, phone)) {
			return getResult(-1, "参数错误", "");
		}
		User user = userService.getPassword(username, phone);
		if (user != null) {
			return getResult(1, "查找成功", JSON.toJSONString(user));
		}else{
			return getResult(2, "查找失败", "");
		}
	}
	
	/**
	 * 更新信鸽token
	 * 
	 * @param userId
	 * @param xingeToken
	 * @return
	 */
	@RequestMapping("updateXingeToken")
	public JSONObject updateXingeToken(long userid, String xingetoken) {
		User user = new User();
		user.setId(userid);
		user.setXingetoken(xingetoken);
		int res = userService.updateXingeToken(user);
		return getResult(res, "", "");
	}
	
	/**
	 * 搜索好友
	 * 
	 * @param message
	 * @return
	 */
	@RequestMapping("queryUser")
	public JSONObject queryUser(String message, long userid) {
		if (!checkParams(message)) {
			return getResult(-1, "参数错误", "");
		}
		List<User> users = new ArrayList<User>();
		if (TextUtils.isMobilePhone(message)) {
			User user = userService.getUserByPhone(message);
			if(user != null){
				MessageWithBLOBs mess = messageService.getRecordById(userid, user.getId());
				user.setBloBs(mess);
			}
			Friend friend = friendService.checkFriend(userid, user.getId());
			user.setFriend(friend);
			users.add(user);
		} else {
			users = userService.getUsersByUsername(message);
			for(User u: users){
				MessageWithBLOBs mess = messageService.getRecordById(userid, u.getId());
				Friend friend = friendService.checkFriend(userid, u.getId());
				u.setFriend(friend);
				u.setBloBs(mess);
			}
		}
		return getResult(1, "查询成功", JSON.toJSONString(users));
	}
	
	@RequestMapping("getUserById")
	public JSONObject getUserById(long userid) {
		User user = userService.getUserById(userid);
		System.out.println(JSON.toJSONString(user));
		return getResult(1, "查询用户", JSON.toJSONString(user));
	}
	
	/**
	 * 
	 * 更新用户名
	 */
	@RequestMapping("updateUsername")
	public JSONObject updateNickname(@RequestParam("userid") long userid,
			@RequestParam("username") String username) {
		User user = userService.getUserByName(username);
		if (user != null) {
			return getResult(2, "用户名已存在", "");
		}
		int res = userService.updateUsername(username, userid);
		return getResult(res, "更新用户名", null);
	}
	
	/**
	 * 
	 * 更新邮箱
	 */
	@RequestMapping("updateEmail")
	public JSONObject updateEmail(@RequestParam("userid") long userid,
			@RequestParam("email") String email) {
		int res = userService.updateEmail(email, userid);
		return getResult(res, "更新邮箱", null);
	}
	
	/**
	 * 
	 * 更新密码
	 */
	@RequestMapping("updatePassword")
	public JSONObject updatePassword(@RequestParam("userid") long userid,
			@RequestParam("password") String password) {
		int res = userService.updatePassword(password, userid);
		return getResult(res, "更新密码", null);
	}
	
	/**
	 * 
	 * 更新签名
	 */
	@RequestMapping("updateSign")
	public JSONObject updateSign(@RequestParam("userid") long userid,
			@RequestParam("sign") String sign) {
		int res = userService.updateSign(sign, userid);
		return getResult(res, "更新签名", null);
	}
	
	public static JSONObject getResult(int status, String msg, String data) {
		JSONObject jsb = new JSONObject();
		jsb.put("status", status);
		jsb.put("msg", msg);
		jsb.put("data", data);
		return jsb;
	}

	public static Boolean checkParams(String... params) {
		for (String str : params) {
			if (str == null || "".equals(str)) {
				return false;
			}
		}
		return true;
	}
}
