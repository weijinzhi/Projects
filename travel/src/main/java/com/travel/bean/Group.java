package com.travel.bean;

import java.util.Date;

public class Group {
    private Long id;

    private Long userid;

    private String groupname;

    private Date createtime;

    private Integer createtype;

    private Integer auth;
    
    private Integer state;
    
    private User user;
    
    private CommentShow commentShow;
    
    private MessageWithBLOBs bloBs;
    
    public static final int AUTH_OWNER = 1;
	public static final int AUTH_MENBER = 2;
	
	public Group(){
		
	}
	
	public Group(long id, long userid, String groupname, Date createtime,
			Integer createtype, Integer auth) {
		super();
		this.id = id;
		this.userid = userid;
		this.groupname = groupname;
		this.createtime = createtime;
		this.createtype = createtype;
		this.auth = auth;
	}

	public Group(long userid, String groupname, int auth) {
		this.userid = userid;
		this.groupname = groupname;
		this.auth = auth;
	}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserid() {
        return userid;
    }

    public void setUserid(Long userid) {
        this.userid = userid;
    }

    public String getGroupname() {
        return groupname;
    }

    public void setGroupname(String groupname) {
        this.groupname = groupname == null ? null : groupname.trim();
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public Integer getCreatetype() {
        return createtype;
    }

    public void setCreatetype(Integer createtype) {
        this.createtype = createtype;
    }

    public Integer getAuth() {
        return auth;
    }

    public void setAuth(Integer auth) {
        this.auth = auth;
    }

	@Override
	public String toString() {
		return "Group [id=" + id + ", userid=" + userid + ", groupname="
				+ groupname + ", createtime=" + createtime + ", createtype="
				+ createtype + ", auth=" + auth + "]";
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public CommentShow getCommentShow() {
		return commentShow;
	}

	public void setCommentShow(CommentShow commentShow) {
		this.commentShow = commentShow;
	}

	public MessageWithBLOBs getBloBs() {
		return bloBs;
	}

	public void setBloBs(MessageWithBLOBs bloBs) {
		this.bloBs = bloBs;
	}

	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}
}