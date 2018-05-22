package com.travel.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.travel.bean.GroupMenber;
import com.travel.bean.MessageWithBLOBs;
import com.travel.bean.User;
import com.travel.service.GroupMenberService;
import com.travel.service.MessageService;
import com.travel.service.UserService;
import com.travel.util.TextUtils;

@Controller
@RequestMapping(value = "groupMember", method = { RequestMethod.GET, RequestMethod.POST })
@ResponseBody
public class GroupMemberController {
	@Autowired
	GroupMenberService groupMenberService;
	@Autowired
	UserService userService;
	@Autowired
	MessageService messageService;
	
	/**
	 * 获取群组成员信息
	 * 
	 * @param groupId
	 * @return
	 */
	@RequestMapping("getGroupMember")
	public JSONObject getGroupMenber(long groupid) {
		if (!checkParams(Long.toString(groupid))) {
			return getResult(-1, "参数错误", "");
		}
		List<GroupMenber> res = groupMenberService.getGroupMenbers(groupid);
		List<User> onlineUsers = new ArrayList<>();
		List<User> unlineUsers = new ArrayList<>();
		if (res != null)
			for (int i = 0; i < res.size(); i++) {
				User user = userService.getUserById(res.get(i).getUserid());
				// 用户更新位置信息小于60*10秒 ，半个小时之内
				if ((System.currentTimeMillis() / 1000 - Long.parseLong(user.getUpdatelocationtime())) < 60 * 10) {
					user.setIsOnline(1);
					onlineUsers.add(user);
				} else {
					user.setIsOnline(0);
					unlineUsers.add(user);
				}
			}
		onlineUsers.addAll(unlineUsers);
		return getResult(1, "查询成功", JSON.toJSONString(onlineUsers));
	}
	
	/**
	 * 搜索群成员
	 * 
	 * @param message
	 * @return
	 */
	@RequestMapping("queryGroupMember")
	public JSONObject queryGroupMember(String message, long groupid) {
		int i = 0;
		if (!checkParams(message)) {
			return getResult(-1, "参数错误", "");
		}
		List<User> users = new ArrayList<User>();
		List<User> userList = new ArrayList<User>();
		List<GroupMenber> res = groupMenberService.getGroupMenbers(groupid);
		if (TextUtils.isMobilePhone(message)) {
			User user = userService.getUserByPhone(message);
			if(user != null){
				i = 0;
				MessageWithBLOBs mess = messageService.getGroupRecordById(groupid, user.getId());
				user.setBloBs(mess);
				for(GroupMenber g: res){
					if(user.getId() == g.getUserid()){
						user.setIsMember("1");
						i = 1;
					}
				}
				if(i == 0){
					user.setIsMember("0");
				}
				userList.add(user);
			}
			return getResult(1, "查询成功", JSON.toJSONString(users));
		} else {
			users = userService.getUsersByUsername(message);
			for(User u: users){
				i = 0;
				MessageWithBLOBs mess = messageService.getGroupRecordById(groupid, u.getId());
				u.setBloBs(mess);
				for(GroupMenber g: res){
					if(u.getId() == g.getUserid()){
						u.setIsMember("1");
						i = 1;
					}
				}if(i == 0){
					u.setIsMember("0");
				}
				userList.add(u);
			}
			return getResult(1, "查询成功", JSON.toJSONString(userList));
		}
	}
	
	/**
	 * 加入群组
	 * @param status
	 * @param msg
	 * @param data
	 * @return
	 */
	@RequestMapping("jionGroup")
	public JSONObject jionGroup(long userid, long groupid){
		if (groupMenberService.checkMember(userid, groupid) != null) {
			return getResult(2, "您已加入该群组,不必重复加入", "");
		}
		int res = groupMenberService.joinGroup(userid, groupid);
		if (res == 1) {
			return getResult(1, "成功加入群组", JSON.toJSONString(res));
		} else {
			return getResult(3, "加入失败,请稍后再试", JSON.toJSONString(res));
		}
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
