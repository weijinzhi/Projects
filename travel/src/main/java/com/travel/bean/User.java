package com.travel.bean;

import java.util.Date;

public class User {
    private Long id;

    private String email;

    private String phone;

    private String password;

    private String username;

    private String sign;

    private String head;

    private Date registertime;

    private Date logintime;

    private Byte canlogin;

    private Double longitude;

    private Double latitude;

    private String xingetoken;

    private String updatelocationtime;

    private String status;
    
    private String isMember;
    
    private Integer isOnline = 0;//默认为不上线，1为在线
    
    private Comment comment;
    
    private MessageWithBLOBs bloBs;
    
    private ChatMessage chatmessage;
    
    private Friend friend;
    
    public Integer getIsOnline() {
		return isOnline;
	}

	public void setIsOnline(Integer isOnline) {
		this.isOnline = isOnline;
	}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone == null ? null : phone.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username == null ? null : username.trim();
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign == null ? null : sign.trim();
    }

    public String getHead() {
        return head;
    }

    public void setHead(String head) {
        this.head = head == null ? null : head.trim();
    }

    public Date getRegistertime() {
        return registertime;
    }

    public void setRegistertime(Date registertime) {
        this.registertime = registertime;
    }

    public Date getLogintime() {
        return logintime;
    }

    public void setLogintime(Date logintime) {
        this.logintime = logintime;
    }

    public Byte getCanlogin() {
        return canlogin;
    }

    public void setCanlogin(Byte canlogin) {
        this.canlogin = canlogin;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public String getXingetoken() {
        return xingetoken;
    }

    public void setXingetoken(String xingetoken) {
        this.xingetoken = xingetoken == null ? null : xingetoken.trim();
    }

    public String getUpdatelocationtime() {
        return updatelocationtime;
    }

    public void setUpdatelocationtime(String updatelocationtime) {
        this.updatelocationtime = updatelocationtime == null ? null : updatelocationtime.trim();
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }
    
//    @Override
//	public String toString() {
//		return "User [id=" + id + ", phone=" + phone + ", email=" + email + ", password=" + password + ", username=" + username + ", sign="
//				+ sign + ", head=" + head + ", registertime=" + registertime + ", logintime=" + logintime
//				+ ", canlogin=" + canlogin + ", longitude=" + longitude + ", latitude=" + latitude + ", xingetoken="
//				+ xingetoken + ", updatelocationtime=" + updatelocationtime + ", status=" +status + "]";
//	}

	public Comment getComment() {
		return comment;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", email=" + email + ", phone=" + phone
				+ ", password=" + password + ", username=" + username
				+ ", sign=" + sign + ", head=" + head + ", registertime="
				+ registertime + ", logintime=" + logintime + ", canlogin="
				+ canlogin + ", longitude=" + longitude + ", latitude="
				+ latitude + ", xingetoken=" + xingetoken
				+ ", updatelocationtime=" + updatelocationtime + ", status="
				+ status + ", isOnline=" + isOnline + ", comment=" + comment
				+ ", bloBs=" + bloBs + ", chatmessage=" + chatmessage
				+ ", friend=" + friend + "]";
	}

	public void setComment(Comment comment) {
		this.comment = comment;
	}

	public MessageWithBLOBs getBloBs() {
		return bloBs;
	}

	public void setBloBs(MessageWithBLOBs bloBs) {
		this.bloBs = bloBs;
	}

	public Friend getFriend() {
		return friend;
	}

	public void setFriend(Friend friend) {
		this.friend = friend;
	}

	public ChatMessage getChatmessage() {
		return chatmessage;
	}

	public void setChatmessage(ChatMessage chatmessage) {
		this.chatmessage = chatmessage;
	}

	public String getIsMember() {
		return isMember;
	}

	public void setIsMember(String isMember) {
		this.isMember = isMember;
	}
}