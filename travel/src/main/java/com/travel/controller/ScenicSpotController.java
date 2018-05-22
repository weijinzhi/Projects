package com.travel.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.travel.bean.ScenicSpot;
import com.travel.service.ScenicSpotService;

@Controller
@RequestMapping(value = "scenicSpot", method = { RequestMethod.GET, RequestMethod.POST })
@ResponseBody
public class ScenicSpotController {
	@Autowired
	ScenicSpotService scenicSpotService;
	
	@RequestMapping("getAllScenic")
	public JSONObject getAllscenic(){
		List<ScenicSpot> spots = scenicSpotService.getAllscenic();
		return getResult(1, "查询成功", JSON.toJSONString(spots));
	}
	
	public static JSONObject getResult(int status, String msg, String data) {
		JSONObject jsb = new JSONObject();
		jsb.put("status", status);
		jsb.put("msg", msg);
		jsb.put("data", data);
		return jsb;
	}
}
