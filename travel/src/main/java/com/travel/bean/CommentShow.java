package com.travel.bean;


public class CommentShow {
    private Long id;

    private float guidecomment;

    private float tourcomment;

    public CommentShow(){
    	
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

	public float getTourcomment() {
		return tourcomment;
	}

	public void setTourcomment(float tourcomment) {
		this.tourcomment = tourcomment;
	}

	public float getGuidecomment() {
		return guidecomment;
	}

	public void setGuidecomment(float guidecomment) {
		this.guidecomment = guidecomment;
	}
}