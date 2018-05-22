package com.travel.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.travel.service.GroupMessageCheckService;

@Controller
@RequestMapping(value = "group", method = { RequestMethod.GET, RequestMethod.POST })
@ResponseBody
public class GroupMessageCheckController {
	@Autowired
	GroupMessageCheckService checkService;
}
