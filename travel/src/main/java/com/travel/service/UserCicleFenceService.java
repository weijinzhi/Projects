package com.travel.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.travel.bean.UserCicleFence;
import com.travel.dao.UserCicleFenceMapper;

@Service
public class UserCicleFenceService {
	@Autowired
	UserCicleFenceMapper cicleFenceMapper;

	public int createFence(long userid, String fencename, float radius,
			double longitude, double latitude) {
		UserCicleFence cicleFence = new UserCicleFence();
		cicleFence.setFencename(fencename);
		cicleFence.setUserid(userid);
		cicleFence.setLatitude(latitude);
		cicleFence.setLongitude(longitude);
		cicleFence.setRadius(radius);
		if(cicleFenceMapper.getFenceByName(fencename) != null){
			return -1;
		}else{
			int res = cicleFenceMapper.addFence(cicleFence);
			return res;
		}
	}

	public UserCicleFence getFenceByUserId(long userid) {
		return cicleFenceMapper.getFenceByUserId(userid);
	}
}
