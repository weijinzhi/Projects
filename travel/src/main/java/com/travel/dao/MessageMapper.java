package com.travel.dao;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.travel.bean.Message;
import com.travel.bean.MessageWithBLOBs;

public interface MessageMapper {
	@Insert("insert into t_message(fromuserid,touserid,type,title,content,createtime) values(#{fromuserid},#{touserid},#{type},#{title},#{content},now())")
	public int addMessage(MessageWithBLOBs msg);
	
	@Select("select * from t_message where touserid = #{touserid} and title = #{title}")
	public List<MessageWithBLOBs> queryApplyFriendMessages(MessageWithBLOBs bloBs);
	
	@Select("select * from t_message where fromuserid = #{fromuserid} and title = #{title}")
	public List<MessageWithBLOBs> queryApplyFriendMessage(MessageWithBLOBs bloBs);
	
	@Select("select * from t_message where touserid = #{touserid} and title = #{title}")
	public List<MessageWithBLOBs> getMyApplyGroupMessage(MessageWithBLOBs bloBs);
	
	@Select("select * from t_message where id = #{messageid} limit 1")
	public Message queryMessageByMessageId(long messageid);
	
	@Update("update t_message set state = #{state} where id = #{id}")
	public int updateMessageState(Message message);
	
	@Select("select * from t_message where fromuserid = #{fromuserid} and touserid = #{touserid} and type = #{type} order by createtime desc limit 1")
	public Message queryMessageByUserIdAndFriendId(Message message);

	@Select("select * from t_message where touserid = #{touserid} and fromuserid = #{fromuserid} and" +
			" title = #{title}")
	public MessageWithBLOBs getRecordByid(MessageWithBLOBs message);

	@Select("select * from t_message where touserid = #{touserid} and title = #{title} and state = #{state}")
	public List<MessageWithBLOBs> getApplyFriendNumber(MessageWithBLOBs bloBs);

	@Select("select * from t_message where fromuserid = #{fromuserid} and title = #{title}")
	public List<MessageWithBLOBs> getUserApplyGroupMessage(MessageWithBLOBs bloB);
}