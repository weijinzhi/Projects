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
import com.travel.bean.Group;
import com.travel.bean.GroupMenber;
import com.travel.bean.Message;
import com.travel.bean.MessageWithBLOBs;
import com.travel.bean.User;
import com.travel.service.CommentService;
import com.travel.service.GroupMenberService;
import com.travel.service.GroupService;
import com.travel.service.MessageService;
import com.travel.service.UserService;
import com.travel.service.XGService;

@Controller
@RequestMapping(value = "group", method = { RequestMethod.GET, RequestMethod.POST })
@ResponseBody
public class GroupController {
	@Autowired
	GroupService groupService;
	
	@Autowired
	GroupMenberService groupMenberService;
	
	@Autowired
	UserService userService;
	
	@Autowired
	CommentService commentService;
	
	@Autowired
	MessageService messageService;
	
	@Autowired
	XGService xgService = new XGService();
	
	/**
	 * 创建群组
	 * 
	 * @param userId
	 * @param groupName
	 * @return
	 */
	@RequestMapping("createGroup")
	public JSONObject createGroup(long userid, String groupname) {
		if (!checkParams(Long.toString(userid), groupname)) {
			return getResult(-1, "参数错误", "");
		}
		Group group1 = new Group();
		group1.setUserid(userid);
		group1.setGroupname(groupname);
		int res = groupService.createGroup(userid, groupname, Group.AUTH_OWNER);
		final long userId = userid;
		final JSONObject Jsb = new JSONObject();
		if (res == 1) {
			new Thread() {
				@Override
				public void run() {
					Group group = groupService.getLatestGroup(userId);
					int a = groupMenberService.joinGroup(userId, group.getId());
					Jsb.put("group", group);
					xgService.pushMessageToSingleDevice(userService.getXingeTokenByUserId(userId), "createGroup",
							Jsb.toString());
				}
			}.start();
			return getResult(1, "创建成功", "");
		} else if(res == -1){
			return getResult(2, "该群组名称已经存在", "");
		}else{
			return getResult(res, "创建失败", "");
		}
	}
	
	/**
	 * 获取群组
	 * 
	 * @param userId
	 * @return
	 */
	@RequestMapping("getGroups")
	public JSONObject getGroups(long userid) {
		if (!checkParams(Long.toString(userid))) {
			return getResult(-1, "参数错误", "");
		}
		// List<Group> res = getGroupService().getGroups(userId);
		List<Long> groupIds = groupService.getGroupNumberByUserId(userid);
		List<Group> res = new ArrayList<>();
		for (long groupid : groupIds) {
			res.add(groupService.getGroupById(groupid));
		}
		return getResult(1, "查询成功", JSON.toJSONString(res));
	}
	
	@RequestMapping("getGroup")
	public JSONObject getGroup(long userid) {
		if (!checkParams(Long.toString(userid))) {
			return getResult(-1, "参数错误", "");
		}
		// List<Group> res = getGroupService().getGroups(userId);
		List<Long> groupIds = groupMenberService.getGroupNumberByUserId(userid);
		List<Group> res = new ArrayList<>();
		for (long groupid : groupIds) {
			res.add(groupService.getGroupById(groupid));
		}
		return getResult(1, "查询成功", JSON.toJSONString(res));
	}
	
	/**
	 * 同意群组申请接口
	 * 
	 * @param userId
	 * @param messageId
	 * @return
	 */
	@RequestMapping("passApplyGroup")
	public JSONObject passApplyGroup(long userid, long messageid) {
		final Message msg = messageService.getMessageById(messageid);
		if (msg == null) {
			return getResult(-4, "消息不存在", "");
		}
		final long message_id = messageid;
		final long user_id = userid;
		if (groupMenberService.checkMember(msg.getFromuserid(), msg.getTouserid()) != null) {
			new Thread() {
				@Override
				public void run() {
					messageService.udpateMessageState(message_id, Message.STATE_PASSGROUP_ACTION);
				}
			}.start();
			return getResult(-5, "已经是在群组里了", "");
		}
		if (msg.getType() == Message.TYPE_APPLYJOINGROUP_ACTION) {
			int res = groupMenberService.addGroupMember(msg.getFromuserid(), msg.getTouserid());
			if (res == 1) {
				final long friendId = msg.getFromuserid();
				final long userId = msg.getTouserid();
				final JSONObject groupJsb = new JSONObject();
				final JSONObject messJsb = new JSONObject();
				Group group = groupService.getGroupById(userId);
				groupJsb.put("group", group);
				
				new Thread() {
					@Override
					public void run() {
						messageService.udpateMessageState(message_id, Message.STATE_PASSGROUP_ACTION);
						MessageWithBLOBs bloBs = messageService.getRecordById(friendId, userId);
						messJsb.put("message", bloBs);
						System.out.println(userService.getXingeTokenByUserId(msg.getFromuserid()));
						xgService.pushNotificationToSingleDeviceWithUrl(
								userService.getXingeTokenByUserId(msg.getFromuserid()), "通过群组请求通知",
								userService.getUserById(user_id).getUsername() + "已经成功通过了您的群组请求",
								"http://www.baidu.com");
						xgService.pushMessageToSingleDevice(userService.getXingeTokenByUserId(friendId), "passApplyGroupAsk",
								groupJsb.toString());
						xgService.pushMessageToSingleDevice(userService.getXingeTokenByUserId(friendId), "passApplyGroupAsk",
								messJsb.toString());
					}

				}.start();
				return getResult(1, "成功加入群组", "");
			} else {
				return getResult(-2, "加入群组失败", "");
			}
		} else {
			return getResult(-3, "错误请求", "");
		}
	}
	
	/**
	 * 游客同意群组邀请接口
	 * 
	 * @param userId
	 * @param messageId
	 * @return
	 */
	@RequestMapping("passAskApplyGroup")
	public JSONObject passAskApplyGroup(long userid, long messageid) {
		final Message msg = messageService.getMessageById(messageid);
		if (msg == null) {
			return getResult(-4, "消息不存在", "");
		}
		final long message_id = messageid;
		final long user_id = userid;
		if (groupMenberService.checkGroupMember(msg.getFromuserid(), msg.getTouserid()) != null) {
			new Thread() {
				@Override
				public void run() {
					messageService.udpateMessageState(message_id, Message.STATE_PASSGROUP_ACTION);
				}
			}.start();
			return getResult(-5, "已经是在群组里了", "");
		}
		if (msg.getType() == Message.TYPE_INVITEJOINGROUP_ACTION) {
			int res = groupMenberService.addAskGroupMember(msg.getFromuserid(), msg.getTouserid());
			if (res == 1) {
				final long friendId = msg.getFromuserid();
				final long userId = msg.getTouserid();
				final JSONObject groupJsb = new JSONObject();
				final JSONObject messJsb = new JSONObject();
				Group group = groupService.getGroupById(friendId);
				groupJsb.put("group", group);
				
				new Thread() {
					@Override
					public void run() {
						messageService.udpateMessageState(message_id, Message.STATE_PASSGROUPINVITE_ACTION);
						MessageWithBLOBs bloBs = messageService.getRecordByIdAsk(friendId, userId);
						messJsb.put("message", bloBs);
						xgService.pushNotificationToSingleDeviceWithUrl(
								userService.getXingeTokenByUserId(msg.getTouserid()), "邀请加入群组请求通知",
								"邀请群组请求",
								"http://www.baidu.com");
						xgService.pushMessageToSingleDevice(userService.getXingeTokenByUserId(userId), "passApplyGroupAsk",
								groupJsb.toString());
						xgService.pushMessageToSingleDevice(userService.getXingeTokenByUserId(userId), "passApplyGroupAsk",
								messJsb.toString());
					}

				}.start();
				return getResult(1, "成功加入群组", "");
			} else {
				return getResult(-2, "加入群组失败", "");
			}
		} else {
			return getResult(-3, "错误请求", "");
		}
	}
	
	/**
	 * 获取评价对象
	 * 
	 * @param userId
	 * @return
	 */
	@RequestMapping("getGroupAndUser")
	public JSONObject getGroupAndUser(long userid) {
		if (!checkParams(Long.toString(userid))) {
			return getResult(-1, "参数错误", "");
		}
		// List<Group> res = roupService().getGroups(userId);
		List<Long> groupIds = groupMenberService.getGroupNumberByUserId(userid);
		List<Group> res = new ArrayList<>();
		for (long groupid : groupIds) {
			Group group = groupService.getGroupById(groupid);
			User user = userService.getUserById(group.getUserid());
			group.setUser(user);
			res.add(group);
		}
		return getResult(1, "查询成功", JSON.toJSONString(res));
	}
	
	/**
	 * 获取单个群组信息
	 * 
	 * @param userId
	 * @return
	 */
	@RequestMapping("getGroupAndUserById")
	public JSONObject getGroupAndUserById(long groupid) {
		if (!checkParams(Long.toString(groupid))) {
			return getResult(-1, "参数错误", "");
		}
		Group group = groupService.getGroupById(groupid);
		User user = userService.getUserById(group.getUserid());
		group.setUser(user);
		return getResult(1, "查询成功", JSON.toJSONString(group));
	}
	
	/**
	 * 获取单个群组信息
	 * 
	 * @param userId
	 * @return
	 */
	@RequestMapping("getGroupsAndUserById")
	public JSONObject getGroupsAndUserById(long groupid, long userid) {
		if (!checkParams(Long.toString(groupid))) {
			return getResult(-1, "参数错误", "");
		}
		Group group = groupService.getGroupById(groupid);
		User user = userService.getUserById(userid);
		group.setUser(user);
		return getResult(1, "查询成功", JSON.toJSONString(group));
	}
	
	/**
	 * 获取群组成员信息
	 * 
	 * @param groupId
	 * @return
	 */
	@RequestMapping("getGroupMenber")
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
				if (System.currentTimeMillis() / 1000 - Long.parseLong(user.getUpdatelocationtime()) < 60 * 10) {
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
	 * 搜索群组
	 * 
	 * @param message
	 * @return
	 */
	@RequestMapping("queryGroup")
	public JSONObject queryGroup(String message, long userid) {
		if (!checkParams(message)) {
			return getResult(-1, "参数错误", "");
		}
		List<Group> groups = new ArrayList<Group>();
		groups = groupService.getGroupByName(message);
		for(Group g: groups){
			MessageWithBLOBs mess = messageService.getRecordByIdGroup(userid, g.getId());
			g.setBloBs(mess);
		}
		return getResult(1, "查询成功", JSON.toJSONString(groups));
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
