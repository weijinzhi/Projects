package com.travel.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.travel.bean.Group;
import com.travel.bean.Message;
import com.travel.bean.MessageWithBLOBs;
import com.travel.bean.User;
import com.travel.service.GroupMenberService;
import com.travel.service.GroupService;
import com.travel.service.MessageService;
import com.travel.service.UserService;
import com.travel.service.XGService;

@Controller
@RequestMapping(value = "message", method = { RequestMethod.GET, RequestMethod.POST })
@ResponseBody
public class MessageController {
	@Autowired
	MessageService messageService;
	@Autowired
	UserService userService;
	@Autowired
	XGService xgService = new XGService();
	@Autowired
	GroupService groupService;
	@Autowired
	GroupMenberService menberService;
	
	/**
	 * 发送好友申请
	 * 
	 * @param userId
	 * @param friendId
	 * @return
	 */
	@RequestMapping("applyFriend")
	public JSONObject applyFriend(long userid, long friendid) {
		if (!checkParams(Long.toString(userid), Long.toString(friendid))) {
			return getResult(-1, "参数错误", "");
		}
		final long friendId = friendid;
		final long userId = userid;
		Message msg = messageService.getMessage(userid, friendid, Message.TYPE_APPLYFRIEND_ACTION);
		if (msg != null) {
			return getResult(-2, "好友申请已发送，不需重复请求", "");
		}
		int res = messageService.applyFriend(userid, friendid);
		final JSONObject Jsb = new JSONObject();
		if (res == 1) {
			new Thread() {
				@Override
				public void run() {
					MessageWithBLOBs bloBs = messageService.getRecordById(userId, friendId);
					System.out.println(bloBs);
					Jsb.put("message", bloBs);
					xgService.pushNotificationToSingleDeviceWithUrl(
							userService.getXingeTokenByUserId(friendId), "收到新的好友请求", "打开app查看",
							"http://www.baidu.com");
					xgService.pushMessageToSingleDevice(userService.getXingeTokenByUserId(friendId), "passApplyFriendAsk",
							Jsb.toString());
				}
			}.start();
			return getResult(1, "发送成功", "");
		} else {
			return getResult(-3, "发送失败", "");
		}
	}
	
	/**
	 * 申请加入群组
	 * @param status
	 * @param msg
	 * @param data
	 * @return
	 */
	@RequestMapping("applyGroup")
	public JSONObject applyGroup(long userid, long groupid){
		if (!checkParams(Long.toString(userid), Long.toString(groupid))) {
			return getResult(-1, "参数错误", "");
		}
		final long groupId = groupid;
		final long userId = userid;
		Message msg = messageService.getMessage(userid, groupid, Message.TYPE_APPLYJOINGROUP_ACTION);
		if (msg != null) {
			return getResult(-2, "群组申请已发送，不需重复请求", "");
		}
		int res = messageService.applyGroup(userid, groupid);
		final JSONObject Jsb = new JSONObject();
		if (res == 1) {
			new Thread() {
				@Override
				public void run() {
					Group group = groupService.getGroupById(groupId);
					User user = userService.getUserById(group.getUserid());
					MessageWithBLOBs bloBs = messageService.getOneApplyGroupMessage(userId,groupId);
					Jsb.put("message", bloBs);
					System.out.println(user.getXingetoken());
					xgService.pushNotificationToSingleDeviceWithUrl(
							user.getXingetoken(), "收到新的加入群组请求", "打开app查看",
							"http://www.baidu.com");
					xgService.pushMessageToSingleDevice(user.getXingetoken(), "passApplyGroupAsk",
							Jsb.toString());
				}
			}.start();
			return getResult(1, "发送成功", "");
		} else {
			return getResult(-3, "发送失败", "");
		}
	}
	
	/**
	 * 邀请加入群组
	 * @param status
	 * @param msg
	 * @param data
	 * @return
	 */
	@RequestMapping("askApplyGroup")
	public JSONObject askApplyGroup(long groupid, long userid){
		System.out.println("1111");
		System.out.println(userid + "----" + groupid);
		if (!checkParams(Long.toString(userid), Long.toString(groupid))) {
			return getResult(-1, "参数错误", "");
		}
		final long groupId = groupid;
		final long userId = userid;
		Message msg = messageService.getMessage(userid, groupid, Message.TYPE_INVITEJOINGROUP_ACTION);
		if (msg != null) {
			return getResult(-2, "群组邀请已发送，不需重复请求", "");
		}
		int res = messageService.askApplyGroup(userid, groupid);
		final JSONObject Jsb = new JSONObject();
		if (res == 1) {
			new Thread() {
				@Override
				public void run() {
					User user = userService.getUserById(userId);
					MessageWithBLOBs bloBs = messageService.getOneApplyGroupMessage(userId,groupId);
					Jsb.put("message", bloBs);
					System.out.println(user.getXingetoken());
					xgService.pushNotificationToSingleDeviceWithUrl(
							user.getXingetoken(), "收到新的邀请加入群组请求", "打开app查看",
							"http://www.baidu.com");
					xgService.pushMessageToSingleDevice(user.getXingetoken(), "passApplyGroupAsk",
							Jsb.toString());
				}
			}.start();
			return getResult(1, "发送成功", "");
		} else {
			return getResult(-3, "发送失败", "");
		}
	}
	
	/**
	 * 拒绝群组邀请接口
	 * 
	 * @param userId
	 * @param messageId
	 * @return
	 */
	@RequestMapping("refuseAskGroup")
	public JSONObject refuseAskGroup(long userid, long messageid) {
		final Message msg = messageService.getMessageById(messageid);
		if (msg == null) {
			return getResult(-4, "消息不存在", "");
		}
		final long message_id = messageid;
		if (menberService.checkMember(msg.getTouserid(), msg.getFromuserid()) != null) {
			new Thread() {
				@Override
				public void run() {
					messageService.udpateMessageState(message_id, Message.STATE_PASSGROUP_ACTION);
				}
			}.start();
			return getResult(-5, "已经是好友了", "");
		}
		if (msg.getType() == Message.TYPE_INVITEJOINGROUP_ACTION) {
			new Thread() {
				@Override
				public void run() {
					messageService.udpateMessageState(message_id, Message.STATE_REFUSEGROUP_ACTION);
				}
			}.start();
			return getResult(1, "拒绝成功", "");
		}
		return getResult(-3, "错误请求", "");
	}
	
	/**
	 * 获取好友申请信息接口
	 * 
	 * @param userId
	 * @return
	 */
	@RequestMapping("getApplyFriendMessage")
	public JSONObject getApplyFriendMessage(long userid) {
		if (!checkParams(Long.toString(userid))) {
			return getResult(-1, "参数错误", "");
		}
		try {
			List<MessageWithBLOBs> res = messageService.getApplyFriendMessageByUserId(userid);
			return getResult(1, "查询成功", JSON.toJSONString(res));
		} catch (Exception e) {
			return getResult(-2, "查询失败", "");
		}
	}
	
	/**
	 * 获取好友申请总数
	 * 
	 * @param userId
	 * @return
	 */
	@RequestMapping("getApplyFriendNumber")
	public JSONObject getApplyFriendNumber(long userid) {
		if (!checkParams(Long.toString(userid))) {
			return getResult(-1, "参数错误", "");
		}
		try {
			List<MessageWithBLOBs> res = messageService.getApplyFriendNumber(userid);
			System.out.println(res.size());
			return getResult(1, "查询成功", JSON.toJSONString(res));
		} catch (Exception e) {
			return getResult(-2, "查询失败", "");
		}
	}
	
	/**
	 * 获取我的群组申请信息接口
	 * 
	 * @param userId
	 * @return
	 */
	@RequestMapping("getMyApplyGroupMessage")
	public JSONObject getMyApplyGroupMessage(long userid) {
		if (!checkParams(Long.toString(userid))) {
			return getResult(-1, "参数错误", "");
		}
		List<Long> groupIds = groupService.getGroupNumberByUserId(userid);
		System.out.println(groupIds);
		try {
			if(groupIds.size() != 0){
				List<MessageWithBLOBs> res = messageService.getMyApplyGroupMessage(groupIds.get(0));
				for (int i = 1; i < groupIds.size(); i++) {
					List<MessageWithBLOBs> res1 = messageService.getMyApplyGroupMessage(groupIds.get(i));
					for(MessageWithBLOBs m : res1){
						res.add(m);
					}
				}
				System.out.println(res.size());
				return getResult(1, "查询成功", JSON.toJSONString(res));
			}else{
				return getResult(1, "查询成功", "");
			}
		} catch (Exception e) {
			return getResult(-2, "查询失败", "");
		}
	}
	
	/**
	 * 游客
	 */
	@RequestMapping("getUserApplyGroupMessage")
	public JSONObject getUserApplyGroupMessage(long userid) {
		if (!checkParams(Long.toString(userid))) {
			return getResult(-1, "参数错误", "");
		}
		try {
			List<MessageWithBLOBs> res = messageService.getUserApplyGroupMessage(userid);
			List<MessageWithBLOBs> res1 = messageService.getAskApplyGroupMessage(userid);
			if(res != null){
				for(MessageWithBLOBs m : res){
					Group group = groupService.getGroupById(m.getTouserid());
					User user = userService.getUserById(group.getUserid());
					group.setUser(user);
					m.setGroup(group);
				}
			}
			if(res1 != null){
				for(MessageWithBLOBs m : res1){
					Group group = groupService.getGroupById(m.getFromuserid());
					User user = userService.getUserById(group.getUserid());
					group.setUser(user);
					m.setGroup(group);
				}
			}
			res.addAll(res1);
			return getResult(1, "查询成功", JSON.toJSONString(res));
		} catch (Exception e) {
			return getResult(-2, "查询失败", "");
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
