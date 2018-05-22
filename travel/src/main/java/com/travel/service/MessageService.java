package com.travel.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.travel.bean.Message;
import com.travel.bean.MessageWithBLOBs;
import com.travel.dao.MessageMapper;

@Service
public class MessageService {
	@Autowired
	MessageMapper messageMapper;

	public Message getMessage(long userid, long friendid,
			int msgType) {
		Message msg = new Message();
		msg.setFromuserid(userid);
		msg.setTouserid(friendid);
		msg.setType(msgType);
		return messageMapper.queryMessageByUserIdAndFriendId(msg);
	}

	public int applyFriend(long userid, long friendid) {
		MessageWithBLOBs msg = new MessageWithBLOBs();
		msg.setTitle(Message.TITLE_APPLYFRIEND);
		msg.setType(Message.TYPE_APPLYFRIEND_ACTION);
		msg.setFromuserid(userid);
		msg.setTouserid(friendid);
		int res = messageMapper.addMessage(msg);
		return res;
	}

	public List<MessageWithBLOBs> getApplyFriendMessageByUserId(long userid) {
		MessageWithBLOBs bloBs = new MessageWithBLOBs();
		bloBs.setTouserid(userid);
		bloBs.setTitle("TITLE_APPLYFRIEND");
		
		MessageWithBLOBs bloB = new MessageWithBLOBs();
		bloB.setFromuserid(userid);
		bloB.setTitle("TITLE_APPLYFRIEND");
		List<MessageWithBLOBs> res1 = messageMapper.queryApplyFriendMessage(bloB);
		
		List<MessageWithBLOBs> res = messageMapper.queryApplyFriendMessages(bloBs);
		
		for(MessageWithBLOBs m : res1){
			res.add(m);
		}
		return res;
	}
	
	public List<MessageWithBLOBs> getMyApplyGroupMessage(long groupid) {
		MessageWithBLOBs bloBs = new MessageWithBLOBs();
		bloBs.setTouserid(groupid);
		bloBs.setTitle("TITLE_APPLYJOINGROUP");
		List<MessageWithBLOBs> res = messageMapper.getMyApplyGroupMessage(bloBs);
		return res;
	}

	public Message getMessageById(long messageid) {
		Message res = messageMapper.queryMessageByMessageId(messageid);
		return res;
	}

	public int udpateMessageState(long messageid, int state) {
		Message msg = new Message();
		msg.setId(messageid);
		msg.setState(state);
		int res = messageMapper.updateMessageState(msg);
		return res;
	}

	public MessageWithBLOBs getRecordById(long userid, Long id) {
		MessageWithBLOBs message = new MessageWithBLOBs();
		message.setFromuserid(userid);
		message.setTouserid(id);
		message.setTitle("TITLE_APPLYFRIEND");
		return messageMapper.getRecordByid(message);
	}

	public MessageWithBLOBs getRecordByIdGroup(long userid, Long id) {
		MessageWithBLOBs message = new MessageWithBLOBs();
		message.setFromuserid(userid);
		message.setTouserid(id);
		message.setTitle("TITLE_APPLYJOINGROUP");
		return messageMapper.getRecordByid(message);
	}

	public int applyGroup(long userid, long groupid) {
		MessageWithBLOBs msg = new MessageWithBLOBs();
		msg.setTitle(Message.TITLE_APPLYJOINGROUP);
		msg.setType(Message.TYPE_APPLYJOINGROUP_ACTION);
		msg.setFromuserid(userid);
		msg.setTouserid(groupid);
		int res = messageMapper.addMessage(msg);
		return res;
	}

	public List<MessageWithBLOBs> getApplyFriendNumber(long userid) {
		MessageWithBLOBs bloBs = new MessageWithBLOBs();
		bloBs.setTouserid(userid);
		bloBs.setTitle("TITLE_APPLYFRIEND");
		bloBs.setState(0);
		List<MessageWithBLOBs> res = messageMapper.getApplyFriendNumber(bloBs);
		
		MessageWithBLOBs bloB = new MessageWithBLOBs();
		bloB.setFromuserid(userid);
		bloB.setTitle("TITLE_APPLYFRIEND");
		bloB.setState(1);
		List<MessageWithBLOBs> res1 = messageMapper.getApplyFriendNumber(bloBs);
		
		MessageWithBLOBs blo = new MessageWithBLOBs();
		blo.setFromuserid(userid);
		blo.setTitle("TITLE_APPLYFRIEND");
		blo.setState(2);
		List<MessageWithBLOBs> res2 = messageMapper.getApplyFriendNumber(bloBs);
		
		for(MessageWithBLOBs m : res1){
			res.add(m);
		}
		for(MessageWithBLOBs m : res2){
			res.add(m);
		}
		return res;
	}

	public MessageWithBLOBs getOneApplyGroupMessage(long userId, long groupId) {
		MessageWithBLOBs bloB = new MessageWithBLOBs();
		bloB.setFromuserid(userId);
		bloB.setTouserid(groupId);
		bloB.setTitle("TITLE_APPLYJOINGROUP");
		return messageMapper.getRecordByid(bloB);
	}

	public List<MessageWithBLOBs> getUserApplyGroupMessage(long userid) {
		MessageWithBLOBs bloB = new MessageWithBLOBs();
		bloB.setFromuserid(userid);
		bloB.setTitle("TITLE_APPLYJOINGROUP");
		return messageMapper.getUserApplyGroupMessage(bloB);
	}

	public MessageWithBLOBs getGroupRecordById(long groupid, Long id) {
		MessageWithBLOBs bloB = new MessageWithBLOBs();
		bloB.setFromuserid(groupid);
		bloB.setTouserid(id);
		bloB.setTitle("TITLE_ASKJOINGROUP");
		return messageMapper.getRecordByid(bloB);
	}

	public int askApplyGroup(long userid, long groupid) {
		MessageWithBLOBs msg = new MessageWithBLOBs();
		msg.setTitle(Message.TITLE_ASKJOINGROUP);
		msg.setType(Message.TYPE_INVITEJOINGROUP_ACTION);
		msg.setFromuserid(groupid);
		msg.setTouserid(userid);
		int res = messageMapper.addMessage(msg);
		return res;
	}

	public List<MessageWithBLOBs> getAskApplyGroupMessage(long userid) {
		MessageWithBLOBs bloB = new MessageWithBLOBs();
		bloB.setTouserid(userid);
		bloB.setTitle("TITLE_ASKJOINGROUP");
		return messageMapper.getMyApplyGroupMessage(bloB);
	}

	public MessageWithBLOBs getRecordByIdAsk(long friendId, long userId) {
		MessageWithBLOBs message = new MessageWithBLOBs();
		message.setFromuserid(friendId);
		message.setTouserid(userId);
		message.setTitle("TITLE_ASKJOINGROUP");
		return messageMapper.getRecordByid(message);
	}
}
