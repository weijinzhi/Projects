package com.travel.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.travel.dao.GroupMessageMapper;

@Service
public class GroupMessageCheckService {
	@Autowired
	GroupMessageMapper groupMessageMapper;
}
