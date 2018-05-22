package com.travel.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.travel.bean.Advice;
import com.travel.dao.AdviceMapper;

@Service
public class AdviceService {
	@Autowired
	AdviceMapper adviceMapper;

	public int addAdvice(long userid, String title, String content) {
		Advice advice = new Advice();
		advice.setContent(content);
		advice.setUserid(userid);
		advice.setTitle(title);
		return adviceMapper.addAdvice(advice);
	}
}
