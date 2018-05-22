package com.travel.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.travel.service.AdviceService;

@Controller
@RequestMapping(value = "advice", method = { RequestMethod.GET, RequestMethod.POST })
@ResponseBody
public class AdviceController {
	@Autowired
	AdviceService adviceService;
	
	@RequestMapping("addAdvice")
	public JSONObject addAdvice(@RequestParam("userid") long userid,
			@RequestParam("title") String title, @RequestParam("content") String content) {
		return getResult(adviceService.addAdvice(userid, title, content), "success", null);
	}
	
	public static JSONObject getResult(int status, String msg, String data) {
		JSONObject jsb = new JSONObject();
		jsb.put("status", status);
		jsb.put("msg", msg);
		jsb.put("data", data);
		return jsb;
	}
}
