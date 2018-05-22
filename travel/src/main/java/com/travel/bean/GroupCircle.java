package com.travel.bean;

import java.util.Date;

public class GroupCircle {
    private Long id;

    private Long userid;

    private Long groupid;

    private String fencename;

    private Double longitude;

    private Double latitude;

    private Float radius;

    private Date creattime;

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

    public Long getGroupid() {
        return groupid;
    }

    public void setGroupid(Long groupid) {
        this.groupid = groupid;
    }

    public String getFencename() {
        return fencename;
    }

    public void setFencename(String fencename) {
        this.fencename = fencename == null ? null : fencename.trim();
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

    public Float getRadius() {
        return radius;
    }

    public void setRadius(Float radius) {
        this.radius = radius;
    }

    public Date getCreattime() {
        return creattime;
    }

    public void setCreattime(Date creattime) {
        this.creattime = creattime;
    }

	@Override
	public String toString() {
		return "GroupCircle [id=" + id + ", userid=" + userid + ", groupid="
				+ groupid + ", fencename=" + fencename + ", longitude="
				+ longitude + ", latitude=" + latitude + ", radius=" + radius
				+ ", creattime=" + creattime + "]";
	}
    
}