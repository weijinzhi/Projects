package com.travel.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.travel.bean.Admin;
import com.travel.dao.AdminMapper;

@Service
public class AdminService {
	@Autowired
	AdminMapper adminMapper;

	public Admin getUserByName(String userName) {
		System.out.println(userName);
		return adminMapper.getUserByName(userName);
	}
}
