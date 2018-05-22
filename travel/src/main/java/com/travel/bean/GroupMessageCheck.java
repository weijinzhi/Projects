package com.travel.bean;

public class GroupMessageCheck {
    private Long id;

    private Long groupmessageid;

    private Long userid;

    private Integer state;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getGroupmessageid() {
        return groupmessageid;
    }

    public void setGroupmessageid(Long groupmessageid) {
        this.groupmessageid = groupmessageid;
    }

    public Long getUserid() {
        return userid;
    }

    public void setUserid(Long userid) {
        this.userid = userid;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }
}