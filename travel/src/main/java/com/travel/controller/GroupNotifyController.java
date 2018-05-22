package com.travel.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.travel.bean.GroupMenber;
import com.travel.bean.GroupNotify;
import com.travel.service.GroupMenberService;
import com.travel.service.GroupNotifyService;
import com.travel.service.UserService;
import com.travel.service.XGService;

@Controller
@RequestMapping(value="groupNotify", method = {RequestMethod.GET, RequestMethod.POST})
@ResponseBody
public class GroupNotifyController {
	@Autowired
	GroupNotifyService groupNotifyService;
	@Autowired
	XGService xgService = new XGService();
	@Autowired
	GroupMenberService groupMenberService;
	@Autowired
	UserService userService;
		
	@RequestMapping("addNotify")
	public JSONObject addNotify(long groupid, String title, String content){
		if (!checkParams(title, content)) {
			return getResult(-1, "参数错误", "");
		}
		GroupNotify notify  = new GroupNotify();
		notify.setContent(content);
		notify.setGroupid(groupid);
		notify.setTitle(title);
		int res = groupNotifyService.addNotify(notify);
		final GroupNotify groupNotify = notify;
		if(res == 1){
			new Thread() {
				@Override
				public void run() {
					GroupNotify noti = groupNotifyService.getNotify(groupNotify);
					final JSONObject notiJsb = new JSONObject();
					notiJsb.put("noti", noti);
					List<GroupMenber> res = groupMenberService.getGroupMenbers(noti.getGroupid());
					if(res != null){
						for (int i = 0; i < res.size(); i++) {
							xgService.pushNotificationToSingleDeviceWithUrl(
									userService.getXingeTokenByUserId(res.get(i).getUserid()), "通过好友请求通知",
									"导游发布了通知",
									"http://www.baidu.com");
						}
					}
				}

			}.start();
		}
		return getResult(res, "发布成功", null);
	}
	
	@RequestMapping("getAllNotify")
	public JSONObject getAllNotify(long groupid){
		System.out.println(groupid);
		List<GroupNotify> notifies = groupNotifyService.getAllNotify(groupid);
		return getResult(1, "查找成功", JSON.toJSONString(notifies));
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
