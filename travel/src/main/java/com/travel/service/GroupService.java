package com.travel.service;

import java.util.HashSet;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.travel.bean.Group;
import com.travel.dao.GroupMapper;

@Service
public class GroupService {
	@Autowired
	GroupMapper groupMapper;

	public int createGroup(long userid, String groupname, int auth) {
		Group group = new Group();
		group.setUserid(userid);
		group.setGroupname(groupname);
		group.setAuth(auth);
		if(groupMapper.getGroupByName(groupname) != null){
			return -1;
		}else{
			int res = groupMapper.addGroup(group);
			return res;
		}
	}

	public Group getLatestGroup(long userid) {
		return groupMapper.queryLatestGroup(userid);
	}

	public Group getGroupById(long groupid) {
		return groupMapper.queryGroupById(groupid);
	}

	public List<Group> getGroupByName(String groupname) {
		StringBuilder sb = new StringBuilder();
		sb.append("%");
		sb.append(groupname);
		sb.append("%");
		return groupMapper.getGroupsByname(sb.toString());
	}

	public List<Group> queryGroups(long userid) {
		List<Group> res = groupMapper.queryGroups(userid);
		return res;
	}

	public List<Long> getGroupNumberByUserId(long userid) {
		List<Long> res = groupMapper.queryGroupIdsByUserId(userid);
		HashSet<Long> hSet = new HashSet(res);
		res.clear();
		res.addAll(hSet);
		return res;
	}
}
