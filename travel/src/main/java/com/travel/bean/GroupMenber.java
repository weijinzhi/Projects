package com.travel.bean;

public class GroupMenber {
    private Long id;

    private Long groupid;

    private Long userid;

    private String note;
    
    public GroupMenber() {

	}
    
    public GroupMenber(long userid, long groupid) {
		this.userid = userid;
		this.groupid = groupid;
	}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getGroupid() {
        return groupid;
    }

    public void setGroupid(Long groupid) {
        this.groupid = groupid;
    }

    public Long getUserid() {
        return userid;
    }

    public void setUserid(Long userid) {
        this.userid = userid;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note == null ? null : note.trim();
    }
}