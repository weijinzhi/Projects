package com.travel.service;

import org.json.JSONObject;
import org.springframework.stereotype.Service;

import com.tencent.xinge.Message;
import com.tencent.xinge.XingeApp;
import com.tencent.xinge.Style;
import com.tencent.xinge.ClickAction;

@Service
public class XGService {
	static XingeApp xinge = null;
	
	public XGService(){
		long accessId = 2100279545;
		String secret_key = "a4df5ac816a6dd100a7d51f9a5b0ec0b";
		xinge = new XingeApp(accessId, secret_key);
	}
	
	public JSONObject pushMessageToSingleDevice(String token, String title, String content) {
		if (token == null) {
			return null;
		}
		Message message = new Message();
		message.setTitle(title);
		message.setContent(content);
		message.setType(Message.TYPE_MESSAGE);
		message.setExpireTime(86400);
		System.out.println(xinge.pushSingleDevice(token, message));
		return xinge.pushSingleDevice(token, message);
	}
	
	public JSONObject pushNotificationToSingleDeviceWithUrl(String token, String title, String content, String url) {
		if (token == null) {
			return null;
		}
		Message message = null;
		message = new Message();
		message.setType(Message.TYPE_NOTIFICATION);
		message.setTitle(title);
		message.setContent(content);
		Style style = new Style(1);
		style = new Style(3, 1, 0, 1, 0);
		ClickAction action = new ClickAction();
		action.setActionType(ClickAction.TYPE_URL);
		action.setUrl(url);
		message.setStyle(style);
		message.setAction(action);
		System.out.println(xinge.pushSingleDevice(token, message));
		return xinge.pushSingleDevice(token, message);
	}
	
	public JSONObject pushNotificationToSingleDeviceWithIntent(String token, String title, String content,
			String intent) {
		if (token == null) {
			return null;
		}
		Message message = null;
		message = new Message();
		message.setType(Message.TYPE_NOTIFICATION);
		message.setTitle(title);
		message.setContent(content);
		Style style = new Style(1);
		style = new Style(3, 1, 0, 1, 0);
		ClickAction action = new ClickAction();
		action.setActionType(ClickAction.TYPE_INTENT);
		action.setIntent(intent);
		message.setStyle(style);
		message.setAction(action);
		return xinge.pushSingleDevice(token, message);
	}
}
