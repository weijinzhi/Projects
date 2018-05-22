package com.travel.bean;

import java.util.Date;

public class ChatMessage {
	private Long id;

	private Long fromuserid;

	private Long touserid;

	private Date createtime;

	private Integer state;

	private String content;

	public ChatMessage() {

	}

	public ChatMessage(long fromUserId, long toUserId, String content) {
		super();
		this.fromuserid = fromUserId;
		this.touserid = toUserId;
		this.content = content;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getFromuserid() {
		return fromuserid;
	}

	public void setFromuserid(Long fromuserid) {
		this.fromuserid = fromuserid;
	}

	public Long getTouserid() {
		return touserid;
	}

	public void setTouserid(Long touserid) {
		this.touserid = touserid;
	}

	public Date getCreatetime() {
		return createtime;
	}

	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}

	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content == null ? null : content.trim();
	}
}