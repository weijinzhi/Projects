package com.travel.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.travel.bean.GroupNotify;
import com.travel.dao.GroupNotifyMapper;

@Service
public class GroupNotifyService {
	@Autowired
	GroupNotifyMapper groupNotifyMapper;

	public int addNotify(GroupNotify notify) {
		return groupNotifyMapper.addNotify(notify);
	}

	public GroupNotify getNotify(GroupNotify groupNotify) {
		return groupNotifyMapper.getNotify(groupNotify);
	}

	public List<GroupNotify> getAllNotify(long groupid) {
		return groupNotifyMapper.getAllNotify(groupid);
	}
	
}
