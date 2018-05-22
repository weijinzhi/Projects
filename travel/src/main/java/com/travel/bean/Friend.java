package com.travel.bean;

public class Friend {
    private Long id;

    private Long userid;

    private Long friendid;
    
    public Friend(){
    	
    }

    public Friend(long userId, long friendId) {
		super();
		this.userid = userId;
		this.friendid = friendId;
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

    public Long getFriendid() {
        return friendid;
    }

    public void setFriendid(Long friendid) {
        this.friendid = friendid;
    }
}