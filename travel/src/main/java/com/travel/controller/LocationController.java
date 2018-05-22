package com.travel.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.travel.bean.LocationRecord;
import com.travel.bean.User;
import com.travel.service.LocationRecordService;
import com.travel.service.UserService;

@Controller
@RequestMapping(value = "location", method = { RequestMethod.GET, RequestMethod.POST })
@ResponseBody
public class LocationController {
	@Autowired
	LocationRecordService locationRecordService;
	@Autowired
	UserService userService;

	/**
	 * 更新用户地址
	 * 
	 * @param userId
	 * @param longitude
	 * @param latitude
	 * @return
	 */
	@RequestMapping("updateLocation")
	public JSONObject updateLocation(long userid, Double longitude, Double latitude) {
		User user = new User();
		user.setId(userid);
		user.setLongitude(longitude);
		user.setLatitude(latitude);
		LocationRecord last = locationRecordService.getLastLocationRecordByUserId(userid);
		if (last == null) {
			locationRecordService.addLocationRecord(userid, latitude, longitude);
		} else if (!(last.getLatitude().equals(latitude) && last.getLongitude().equals(longitude))) {
			locationRecordService.addLocationRecord(userid, latitude, longitude);
		}
		user.setUpdatelocationtime((System.currentTimeMillis() / 1000) + "");
		int res = userService.updateLocation(user);
		return getResult(res, "", "");
	}
	
	@RequestMapping("getOnesLocationRecord")
	public JSONObject getOnesLocationRecord(@RequestParam("userid") long userid,
			@RequestParam("starttime") String starttime, @RequestParam("endtime") String endtime) {
		List<LocationRecord> res = locationRecordService.getOnesLocatonRecord(userid, starttime, endtime);
		System.out.println(res.get(0).toString());
		return getResult(1, "success", JSON.toJSONString(res));
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
