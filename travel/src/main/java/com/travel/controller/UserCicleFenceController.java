package com.travel.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.travel.bean.UserCicleFence;
import com.travel.service.UserCicleFenceService;

@Controller
@RequestMapping(value = "userfence", method = { RequestMethod.GET, RequestMethod.POST })
@ResponseBody
public class UserCicleFenceController {
	@Autowired
	UserCicleFenceService cicleFenceService;
	
	/**
	 * 创建围栏
	 * 
	 * @param userId
	 * @param groupName
	 * @return
	 */
	@RequestMapping("saveFence")
	public JSONObject saveFence(long userid, String fencename, float radius, double longitude, double latitude) {
		if (!checkParams(Long.toString(userid), fencename , radius + "", longitude + "", latitude + "")) {
			return getResult(-1, "参数错误", "");
		}
		UserCicleFence cicleFence = new UserCicleFence();
		cicleFence.setFencename(fencename);
		cicleFence.setUserid(userid);
		cicleFence.setLatitude(latitude);
		cicleFence.setLongitude(longitude);
		cicleFence.setRadius(radius);
		int res = cicleFenceService.createFence(userid, fencename, radius, longitude, latitude);
		if (res == 1) {
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
	public JSONObject getFence(long userid) {
		if (!checkParams(Long.toString(userid))) {
			return getResult(-1, "参数错误", "");
		}
		UserCicleFence cicleFence = cicleFenceService.getFenceByUserId(userid);
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
