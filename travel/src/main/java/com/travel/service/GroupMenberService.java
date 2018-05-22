package com.travel.service;

import java.util.HashSet;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.travel.bean.GroupMenber;
import com.travel.dao.GroupMenberMapper;

@Service
public class GroupMenberService {
	@Autowired
	GroupMenberMapper groupMenberMapper;

	public int joinGroup(long userid, long groupid) {
		return groupMenberMapper.addGroupMenber(new GroupMenber(userid, groupid));
		
	}

	public List<Long> getGroupNumberByUserId(long userid) {
		List<Long> res = groupMenberMapper.queryGroupNumberByUserId(userid);
		HashSet<Long> hSet = new HashSet(res);
		res.clear();
		res.addAll(hSet);
		return res;
	}

	public List<GroupMenber> getGroupMenbers(long groupid) {
		List<GroupMenber> res = groupMenberMapper.queryGroupMenbers(groupid);
		return res;
	}

	public Object checkMember(long userid, long groupid) {
		GroupMenber res = groupMenberMapper.checkMeneber(new GroupMenber(userid, groupid));
		return res;
	}

	public int addGroupMember(Long fromuserid, Long touserid) {
		GroupMenber menber = new GroupMenber();
		menber.setGroupid(touserid);
		menber.setUserid(fromuserid);
		return groupMenberMapper.addGroupMenber(menber);
	}

	public Object checkGroupMember(Long fromuserid, Long touserid) {
		GroupMenber res = groupMenberMapper.checkMeneber(new GroupMenber(touserid, fromuserid));
		return res;
	}

	public int addAskGroupMember(Long fromuserid, Long touserid) {
		GroupMenber menber = new GroupMenber();
		menber.setGroupid(fromuserid);
		menber.setUserid(touserid);
		return groupMenberMapper.addGroupMenber(menber);
	}
}
