package com.solvd.projectSocialNetwork.model;

import java.util.List;

public class Posts extends AbstractEntity {

	private String massage;
	private String referenceLink;
	private long userId;
	private List<Multimedia> multimedia;
	
	
	public Posts() {
		super();
	}
	
	
	public Posts(long id,String massage, String referenceLink, long userId, List<Multimedia> multimedia) {
		super(id);
		this.massage = massage;
		this.referenceLink = referenceLink;
		this.userId = userId;
		this.setMultimedia(multimedia);
	}
	
	public String getMassage() {
		return massage;
	}
	public void setMassage(String massage) {
		this.massage = massage;
	}
	public String getReferenceLink() {
		return referenceLink;
	}
	public void setReferenceLink(String referenceLink) {
		this.referenceLink = referenceLink;
	}
	public long getUserId() {
		return userId;
	}
	public void setUserId(long userId) {
		this.userId = userId;
	}


	public List<Multimedia> getMultimedia() {
		return multimedia;
	}


	public void setMultimedia(List<Multimedia> multimedia) {
		this.multimedia = multimedia;
	}
	
	public void addMultimedia(Multimedia m) {
		multimedia.add(m);
	}
	
	
	
}
