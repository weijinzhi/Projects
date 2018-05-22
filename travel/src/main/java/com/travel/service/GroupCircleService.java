package com.travel.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.travel.bean.GroupCircle;
import com.travel.dao.GroupCircleMapper;

@Service
public class GroupCircleService {
	@Autowired
	GroupCircleMapper groupCircleMapper;

	public int createFence(long userid, long groupid, String fencename, float radius,
			double longitude, double latitude) {
		GroupCircle cicleFence = new GroupCircle();
		cicleFence.setFencename(fencename);
		cicleFence.setUserid(userid);
		cicleFence.setLatitude(latitude);
		cicleFence.setLongitude(longitude);
		cicleFence.setRadius(radius);
		cicleFence.setGroupid(groupid);
		if(groupCircleMapper.getFenceByName(fencename) != null){
			return -1;
		}else{
			int res = groupCircleMapper.addFence(cicleFence);
			return res;
		}
	}

	public GroupCircle getFenceByUserId(long groupid) {
		return groupCircleMapper.getFenceByGroupId(groupid);
	}

}
