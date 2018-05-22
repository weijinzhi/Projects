package com.travel.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.travel.bean.ScenicSpot;
import com.travel.dao.ScenicSpotMapper;

@Service
public class ScenicSpotService {
	@Autowired
	ScenicSpotMapper scenicSpotMapper;

	public List<ScenicSpot> getAllscenic() {
		return scenicSpotMapper.getAllscenic();
	}

}
