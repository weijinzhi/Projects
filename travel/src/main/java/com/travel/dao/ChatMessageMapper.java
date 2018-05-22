package com.travel.dao;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.travel.bean.ChatMessage;

public interface ChatMessageMapper {
	@Select("select * from t_chat_message where (touserid = #{0} and fromuserid = #{1}) or (touserid = #{1} and fromuserid = #{0} ) order by id desc limit #{2},#{3}")
	public List<ChatMessage> getSingleMessageRecord(long userid, long friendid, Integer start, Integer size);

	@Insert("insert into t_chat_message (fromuserid,touserid,content,createtime) values(#{fromuserid},#{touserid},#{content},now())")
	public int addMessage(ChatMessage chatMessage);

	@Select("select * from t_chat_message where fromuserid = #{0} and touserid = #{1} order by id desc limit 1")
	public ChatMessage getLatestMessage(long fromuserid, long touserid);
	
	@Select("select * from t_chat_message where fromuserid = #{0} and touserid = #{1} and state = 0 order by id desc")
	public List<ChatMessage> getMessages(long fromuserid, long touserid);

	@Select("select distinct fromuserid from t_chat_message where touserid = #{0} order by id desc")
	public List<Long> getRecordListFriendIds(long userid);

	@Update("update t_chat_message set state = 1 where id = #{id}")
	public int updateChatMessage(ChatMessage c);
}