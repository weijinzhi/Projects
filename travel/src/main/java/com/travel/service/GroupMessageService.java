package com.travel.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.travel.bean.GroupMessage;
import com.travel.dao.GroupMessageMapper;

@Service
public class GroupMessageService {
	@Autowired
	GroupMessageMapper groupMessageMapper;

	public List<GroupMessage> getGroupMessageByGroupId(long groupid, int start,
			int size) {
		return groupMessageMapper.getGroupMessageByGroupId(groupid, start, size);
	}

	public int addGroupMessage(long userid, long groupid, String message) {
		GroupMessage groupMessage = new GroupMessage();
		groupMessage.setFromuserid(userid);
		groupMessage.setContent(message);
		groupMessage.setGroupid(groupid);
		
		return groupMessageMapper.addGroupMessage(groupMessage);
	}
	
}
