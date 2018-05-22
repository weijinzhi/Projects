package com.travel.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.travel.bean.Friend;
import com.travel.dao.FriendMapper;

@Service
public class FriendService {
	@Autowired
	FriendMapper friendMapper;

	public List<Friend> getFriendsByUserId(long userid) {
		List<Friend> res = friendMapper.queryFriendsByUserId(userid);
		return res;
	}

	public Friend checkFriend(Long userid, Long friendid) {
		Friend res = friendMapper.queryFriendByUserId(new Friend(userid, friendid));
		return res;
	}

	public int addFriend(Long userid, Long friendid) {
		int res1 = friendMapper.addFriend(new Friend(userid, friendid));
		int res2 = friendMapper.addFriend(new Friend(friendid, userid));
		return res1 + res2;
	}

}
