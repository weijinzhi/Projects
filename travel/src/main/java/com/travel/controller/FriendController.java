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
import com.travel.bean.Friend;
import com.travel.bean.Message;
import com.travel.bean.MessageWithBLOBs;
import com.travel.bean.User;
import com.travel.service.FriendService;
import com.travel.service.MessageService;
import com.travel.service.UserService;
import com.travel.service.XGService;

@Controller
@RequestMapping(value = "friends", method = { RequestMethod.GET, RequestMethod.POST })
@ResponseBody
public class FriendController {
	@Autowired
	UserService userService;
	@Autowired
	FriendService friendService;
	@Autowired
	MessageService messageService;
	@Autowired
	XGService xgService = new XGService();

	/**
	 * 获取好友列表
	 * 
	 * @param userId
	 * @return
	 */
	@RequestMapping("getFriends")
	public JSONObject queryUser(long userid) {
		if (!checkParams(Long.toString(userid))) {
			return getResult(-1, "参数错误", "");
		}
		List<Friend> res = friendService.getFriendsByUserId(userid);
		List<User> friends = new ArrayList<User>();
		for (Friend friend : res) {
			friends.add(userService.getUserById(friend.getFriendid()));
		}
		return getResult(1, "查询成功", JSON.toJSONString(friends));
	}
	
	/**
	 * 获取好友列表1
	 * 
	 * @param userId
	 * @return
	 */
	@RequestMapping("getFriend")
	public String queryUser1(long userid) {
		System.out.println("userid" + userid);
		List<Friend> res = friendService.getFriendsByUserId(userid);
		List<User> friends = new ArrayList<User>();
		for (Friend friend : res) {
			friends.add(userService.getUserById(friend.getFriendid()));
		}
		System.out.println(JSON.toJSONString(friends));
		return JSON.toJSONString(friends);
	}
	
	/**
	 * 同意好友申请接口
	 * 
	 * @param userId
	 * @param messageId
	 * @return
	 */
	@RequestMapping("passApplyFriend")
	public JSONObject passApplyFriend(long userid, long messageid) {
		final Message msg = messageService.getMessageById(messageid);
		if (msg == null) {
			return getResult(-4, "消息不存在", "");
		}
		final long message_id = messageid;
		final long user_id = userid;
		if (friendService.checkFriend(msg.getFromuserid(), msg.getTouserid()) != null) {
			new Thread() {
				@Override
				public void run() {
					messageService.udpateMessageState(message_id, Message.STATE_PASSFRIEND_ACTION);
				}
			}.start();
			return getResult(-5, "已经是好友了", "");
		}
		if (msg.getType() == Message.TYPE_APPLYFRIEND_ACTION) {
			int res = friendService.addFriend(msg.getFromuserid(), msg.getTouserid());
			if (res == 2) {
				final long friendId = msg.getFromuserid();
				final long userId = msg.getTouserid();
				User user = userService.getUserById(userId);  //同意方
				User user1 = userService.getUserById(friendId);  //被同意方
				final JSONObject userJsb = new JSONObject();
				final JSONObject friendJsb = new JSONObject();
				final JSONObject Jsb = new JSONObject();
				userJsb.put("user", user);
				friendJsb.put("user", user1);
				
				new Thread() {
					@Override
					public void run() {
						messageService.udpateMessageState(message_id, Message.STATE_PASSFRIEND_ACTION);
						MessageWithBLOBs bloBs = messageService.getRecordById(friendId, userId);
						Jsb.put("message", bloBs);
						xgService.pushNotificationToSingleDeviceWithUrl(
								userService.getXingeTokenByUserId(msg.getFromuserid()), "通过好友请求通知",
								userService.getUserById(user_id).getUsername() + "已经成功通过了您的好友请求",
								"http://www.baidu.com");
						xgService.pushMessageToSingleDevice(userService.getXingeTokenByUserId(friendId), "passApplyFriend",
								userJsb.toString());
						xgService.pushMessageToSingleDevice(userService.getXingeTokenByUserId(userId), "passApplyFriend",
								friendJsb.toString());
						xgService.pushMessageToSingleDevice(userService.getXingeTokenByUserId(friendId), "passApplyFriendAsk",
								Jsb.toString());
					}

				}.start();
				return getResult(1, "添加好友成功", "");
			} else {
				return getResult(-2, "添加失败", "");
			}
		} else {
			return getResult(-3, "错误请求", "");
		}
	}
	
	/**
	 * 拒绝好友申请接口
	 * 
	 * @param userId
	 * @param messageId
	 * @return
	 */
	@RequestMapping("refuseApplyFriend")
	public JSONObject refuseApplyFriend(long userid, long messageid) {
		final Message msg = messageService.getMessageById(messageid);
		if (msg == null) {
			return getResult(-4, "消息不存在", "");
		}
		final long message_id = messageid;
		if (friendService.checkFriend(msg.getFromuserid(), msg.getTouserid()) != null) {
			new Thread() {
				@Override
				public void run() {
					messageService.udpateMessageState(message_id, Message.STATE_PASSFRIEND_ACTION);
				}
			}.start();
			return getResult(-5, "已经是好友了", "");
		}
		if (msg.getType() == Message.TYPE_APPLYFRIEND_ACTION) {
			new Thread() {
				@Override
				public void run() {
					messageService.udpateMessageState(message_id, Message.STATE_REFUSEFRIEND_ACTION);
				}
			}.start();
			return getResult(1, "拒绝成功", "");
		}
		return getResult(-3, "错误请求", "");
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
