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
import com.travel.bean.GroupMessage;
import com.travel.bean.User;
import com.travel.service.GroupMenberService;
import com.travel.service.GroupMessageService;
import com.travel.service.UserService;
import com.travel.service.XGService;

@Controller
@RequestMapping(value = "groupMessage", method = {RequestMethod.GET,RequestMethod.POST})
@ResponseBody
public class GroupMessageController {
	@Autowired
	GroupMessageService groupMessageService;
	@Autowired
	UserService userService;
	@Autowired
	XGService xgService;
	@Autowired
	GroupMenberService groupMenberService;
	
	/**
	 * 
	 * 显示所有群组聊天信息
	 */
	@RequestMapping("getGroupMessage")
	public JSONObject getGroupMessage(long groupid, int start, int size) {
		List<GroupMessage> groupMessages = groupMessageService.getGroupMessageByGroupId(groupid, start, size);
		List<JSONObject> messages = new ArrayList<>();
		for (int i = 0; i < groupMessages.size(); i++) {
			JSONObject jsb = new JSONObject();
			User user = userService.getUserById(groupMessages.get(i).getFromuserid());
			jsb.put("createtime", groupMessages.get(i).getCreatetime());
			jsb.put("content", groupMessages.get(i).getContent());
			if (user != null) {
				jsb.put("username", user.getUsername());
				jsb.put("head", user.getHead());
			}
			messages.add(jsb);
		}
		return getResult(1, "查询成功", JSON.toJSONString(messages));
	}
	
	@RequestMapping("sendMessageToGroup")
	public JSONObject pushMessageToGroupMenbers(long userid, long groupid, String message) {
		List<GroupMenber> groupMenbers = groupMenberService.getGroupMenbers(groupid);
		org.json.JSONObject resJsb = null;
		User fromUser = userService.getUserById(userid);
		groupMessageService.addGroupMessage(userid, groupid, message);// 向数据库中写入数据
		if (groupMenbers != null)
			for (int i = 0; i < groupMenbers.size(); i++) {
				final User user = userService.getUserById(groupMenbers.get(i).getUserid());
				// 用户更新位置信息小于60*10秒 ，半个小时之内
				// if ((System.currentTimeMillis() / 1000 -
				// user.getUpdateLocationTime()) < 60 * 10) {
				final JSONObject jsb = new JSONObject();
				jsb.put("username", fromUser.getUsername());
				jsb.put("message", message);
				new Thread() {
					@Override
					public void run() {
						xgService.pushMessageToSingleDevice(user.getXingetoken(), "groupChatMessage",
								jsb.toString());

					}
				}.start();
				// }
			}

		return getResult(1, "发送成功", null);
	}
	
//	/**
//	 * 
//	 * 显示新的群组聊天信息
//	 */
//	@RequestMapping("getGroupMessageRecord")
//	public JSONObject getGroupMessageRecord(long userid) {
//		List<GroupMessage> groupMessages = groupMessageService.getGroupMessageByGroupId(groupid, start, size);
//		List<JSONObject> messages = new ArrayList<>();
//		for (int i = 0; i < groupMessages.size(); i++) {
//			JSONObject jsb = new JSONObject();
//			User user = userService.getUserById(groupMessages.get(i).getFromuserid());
//			jsb.put("createtime", groupMessages.get(i).getCreatetime());
//			jsb.put("content", groupMessages.get(i).getContent());
//			if (user != null) {
//				jsb.put("username", user.getUsername());
//				jsb.put("head", user.getHead());
//			}
//			messages.add(jsb);
//		}
//		return getResult(1, "查询成功", JSON.toJSONString(messages));
//	}
	
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
