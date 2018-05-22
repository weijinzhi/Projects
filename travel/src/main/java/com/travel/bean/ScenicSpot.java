package com.travel.bean;

import java.util.Date;

public class ScenicSpot {
    private Long id;

    private String position;

    private String tourProject;

    private String introduction;

    private Double price;

    private String picture;

    private Date time;

    private Long spotSort;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position == null ? null : position.trim();
    }

    public String getTourProject() {
        return tourProject;
    }

    public void setTourProject(String tourProject) {
        this.tourProject = tourProject == null ? null : tourProject.trim();
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction == null ? null : introduction.trim();
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture == null ? null : picture.trim();
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public Long getSpotSort() {
        return spotSort;
    }

    public void setSpotSort(Long spotSort) {
        this.spotSort = spotSort;
    }
}