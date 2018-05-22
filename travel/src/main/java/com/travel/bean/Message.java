package com.travel.bean;

import java.util.Date;

public class Message {
    private Long id;

    private Long fromuserid;

    private Long touserid;

    private Integer type;

    private Date createtime;

    private Integer state;
    
    private Group group;
    
    // 添加好友请求的消息类型
 	public static final int TYPE_APPLYFRIEND_ACTION = 0;
    
 	/**
	 * 邀请好友入群类型
	 */
    public static final int TYPE_INVITEJOINGROUP_ACTION = 1;//邀请加入群组
    public static final int TYPE_APPLYJOINGROUP_ACTION = 2;//申请加入群组
    
    
    public static final int STATE_PASSFRIEND_ACTION = 1;//通过好友请求
	public static final int STATE_REFUSEFRIEND_ACTION = 2;//拒绝好友请求
	
	public static final int STATE_PASSGROUP_ACTION = 1;//通过群组请求
	public static final int STATE_REFUSEGROUP_ACTION = 2;//拒绝群组请求
	
	public static final int STATE_PASSGROUPINVITE_ACTION = 1;//通过群组请求
	public static final int STATE_REFUSEGROUPINVITE_ACTION = 2;//拒绝群组请求
	
	public static final String TITLE_APPLYFRIEND = "TITLE_APPLYFRIEND"; //申请好友
	public static final String TITLE_ASKJOINGROUP = "TITLE_ASKJOINGROUP";  //邀请加入群组
	public static final String TITLE_APPLYJOINGROUP = "TITLE_APPLYJOINGROUP";  //申请加入群组

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

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
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

	public Group getGroup() {
		return group;
	}

	public void setGroup(Group group) {
		this.group = group;
	}
}