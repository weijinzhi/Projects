package com.travel.bean;

import java.util.Date;

public class UserCicleFence {
    private Long id;

    private Long userid;

    private Double longitude;

    private Double latitude;

    private String fencename;

    private Float radius;

    private Date creattime;
    
    public UserCicleFence() {
		
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

    public String getFencename() {
        return fencename;
    }

    public void setFencename(String fencename) {
        this.fencename = fencename == null ? null : fencename.trim();
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
}