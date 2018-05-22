package com.travel.bean;

public class Comment {
    private Long id;

    private Long commentid;

    private Long guideid;

    private Long groupid;

    private Float guidecomment;

    private Float tourcomment;

    private String content;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCommentid() {
        return commentid;
    }

    public void setCommentid(Long commentid) {
        this.commentid = commentid;
    }

    public Long getGuideid() {
        return guideid;
    }

    public void setGuideid(Long guideid) {
        this.guideid = guideid;
    }

    public Long getGroupid() {
        return groupid;
    }

    public void setGroupid(Long groupid) {
        this.groupid = groupid;
    }

    public Float getGuidecomment() {
        return guidecomment;
    }

    public void setGuidecomment(Float guidecomment) {
        this.guidecomment = guidecomment;
    }

    public Float getTourcomment() {
        return tourcomment;
    }

    public void setTourcomment(Float tourcomment) {
        this.tourcomment = tourcomment;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }
}