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
import com.travel.bean.ChatMessage;
import com.travel.bean.User;
import com.travel.service.ChatMessageService;
import com.travel.service.UserService;
import com.travel.service.XGService;

@Controller
@RequestMapping(value = "chatMessage", method = { RequestMethod.GET, RequestMethod.POST })
@ResponseBody
public class ChatMessageController {
	@Autowired
	ChatMessageService chatMessageService;
	@Autowired
	XGService xgService;
	@Autowired
	UserService userService;
	
	/**
	 * 获取消息列表
	 * @param userid
	 * @param friendid
	 * @return
	 */
	@RequestMapping("getSingleChatMessageRecord")
	public JSONObject getSingleChatMessageRecord(long userid, long friendid) {
		List<ChatMessage> res = chatMessageService.getMessageRecordByUserId(userid, friendid, 0, 30);
		return getResult(1, "查询成功", JSON.toJSONString(res));
	}
	
	/**
	 * 发送消息
	 * @param fromUserId
	 * @param toUserId
	 * @param content
	 * @return
	 */
	@RequestMapping("sendMessageSingle")
	public JSONObject sendMessageSingle(long fromuserid, long touserid, String content) {
		int res = chatMessageService.addChatMessage(fromuserid, touserid, content);
		if (res != 1) {
			return getResult(-1, "发送失败", null);
		}
		final long from_user = fromuserid;
		final long to_user = touserid;
		new Thread() {
			@Override
			public void run() {
				super.run();
				xgService.pushMessageToSingleDevice(userService.getXingeTokenByUserId(to_user),
						"singleChatMessage",
						JSON.toJSONString(chatMessageService.getLastestMessage(from_user, to_user)));
			}
		}.start();
		return getResult(1, "发送成功", null);
	}
	
	/**
	 * 更新消息
	 * @param fromUserId
	 * @param toUserId
	 * @param content
	 * @return
	 */
	@RequestMapping("updateMessage")
	public JSONObject updateMessageSingle(long id, long userid) {
		List<ChatMessage> chatMessages = chatMessageService.getMessages(id, userid);
		for(ChatMessage c : chatMessages){
			int res = chatMessageService.updateChatMessage(c);
		}
		final long userId = id;
		final long touserid = userid;
		new Thread() {
			@Override
			public void run() {
				User user = userService.getUserById(userId);
				System.out.println(user);
				final JSONObject Jsb = new JSONObject();
				Jsb.put("user", user);
				super.run();
				xgService.pushMessageToSingleDevice(userService.getXingeTokenByUserId(touserid),
						"updateMessageSingle",
						Jsb.toString());
			}
		}.start();
		return getResult(1, "发送成功", null);
	}
	
	@RequestMapping("getMessageRecord")
	public JSONObject getMessageRecord(long userid) {
		List<User> res = new ArrayList<>();
		List<Long> ids = chatMessageService.getRecordListFriendIds(userid);
		for (int i = 0; i < ids.size(); i++) {
			User user = userService.getUserById(ids.get(i));
//			List<ChatMessage> cm = chatMessageService.getMessages(ids.get(i), userid);
			ChatMessage cm = chatMessageService.getLastestMessage(ids.get(i), userid);
			if(cm.getState() == 0){
				user.setChatmessage(cm);
				res.add(user);
			}
			System.out.println(user.getChatmessage());
		}
		return getResult(1, "查询成功", JSON.toJSONString(res));
	}
	
	public static JSONObject getResult(int status, String msg, String data) {
		JSONObject jsb = new JSONObject();
		jsb.put("status", status);
		jsb.put("msg", msg);
		jsb.put("data", data);
		return jsb;
	}
}
