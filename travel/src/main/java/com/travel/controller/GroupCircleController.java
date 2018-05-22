package com.travel.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.travel.bean.GroupCircle;
import com.travel.bean.GroupMenber;
import com.travel.bean.User;
import com.travel.service.GroupCircleService;
import com.travel.service.GroupMenberService;
import com.travel.service.UserService;
import com.travel.service.XGService;

@Controller
@RequestMapping(value = "groupfence", method = { RequestMethod.GET, RequestMethod.POST })
@ResponseBody
public class GroupCircleController {
	@Autowired
	GroupCircleService groupCircleService;
	@Autowired
	GroupMenberService groupMenberService;
	@Autowired
	UserService userService;
	@Autowired
	XGService xgService;
	
	/**
	 * 创建围栏
	 * 
	 * @param userId
	 * @param groupName
	 * @return
	 */
	@RequestMapping("saveFence")
	public JSONObject saveFence(long userid, long groupid, String fencename, float radius, double longitude, double latitude) {
		if (!checkParams(Long.toString(userid), fencename , radius + "", longitude + "", latitude + "")) {
			return getResult(-1, "参数错误", "");
		}
		GroupCircle cicleFence = new GroupCircle();
		cicleFence.setFencename(fencename);
		cicleFence.setUserid(userid);
		cicleFence.setLatitude(latitude);
		cicleFence.setLongitude(longitude);
		cicleFence.setRadius(radius);
		cicleFence.setGroupid(groupid);
		int res = groupCircleService.createFence(userid, groupid, fencename, radius, longitude, latitude);
		List<GroupMenber> groupMenbers = groupMenberService.getGroupMenbers(groupid);
		org.json.JSONObject resJsb = null;
		if (res == 1) {
			if (groupMenbers != null)
				for (int i = 0; i < groupMenbers.size(); i++) {
					final User user = userService.getUserById(groupMenbers.get(i).getUserid());
					// 用户更新位置信息小于60*10秒 ，半个小时之内
					// if ((System.currentTimeMillis() / 1000 -
					// user.getUpdateLocationTime()) < 60 * 10) {
					final JSONObject jsb = new JSONObject();
					jsb.put("cicleFence", cicleFence);
					new Thread() {
						@Override
						public void run() {
							xgService.pushMessageToSingleDevice(user.getXingetoken(), "groupCircleFence",
									jsb.toString());

						}
					}.start();
					// }
				}

			return getResult(res, "创建成功", "");
		} else if(res == -1){
			return getResult(2, "该名称已经存在", "");
		}else{
			return getResult(res, "创建失败", "");
		}
	}
	
	/**
	 * 获取围栏
	 * 
	 * @param userId
	 * @param groupName
	 * @return
	 */
	@RequestMapping("getFence")
	public JSONObject getFence(long groupid) {
		if (!checkParams(Long.toString(groupid))) {
			return getResult(-1, "参数错误", "");
		}
		GroupCircle cicleFence = groupCircleService.getFenceByUserId(groupid);
		if(cicleFence != null){
			return getResult(1, "获取成功", JSON.toJSONString(cicleFence));
		}else{
			return getResult(2, "", "");
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
