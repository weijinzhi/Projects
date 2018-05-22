package com.travel.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.travel.bean.ChatMessage;
import com.travel.dao.ChatMessageMapper;

@Service
public class ChatMessageService {
	@Autowired
	ChatMessageMapper chatMessageMapper;

	public List<ChatMessage> getMessageRecordByUserId(long userid,long friendid, Integer start, Integer size) {
		return chatMessageMapper.getSingleMessageRecord(userid, friendid, start, size);
	}

	public int addChatMessage(long fromuserid, long touserid, String content) {
		ChatMessage cm = new ChatMessage(fromuserid, touserid, content);
		int res = chatMessageMapper.addMessage(cm);
		return res;
	}

	public List<ChatMessage> getMessages(long fromuserid, long touserid) {
		return chatMessageMapper.getMessages(fromuserid, touserid);
	}
	
	public ChatMessage getLastestMessage(long fromuserid, long touserid) {
		return chatMessageMapper.getLatestMessage(fromuserid, touserid);
	}

	public List<Long> getRecordListFriendIds(long userid) {
		return chatMessageMapper.getRecordListFriendIds(userid);
	}

	public int updateChatMessage(ChatMessage c) {
		return chatMessageMapper.updateChatMessage(c);
	}
}
