package com.solvd.projectSocialNetwork.model;

import java.sql.Date;

public class Messages extends AbstractEntity{

	private String message;
	private Date createdAt;
	private long sourceId;
	private long targetId;
	private long multimediaId;
	
	public Messages() {
		
	}
	
	public Messages(long id,String message, Date createdAt, long sourceId, long targetId, long multimediaId) {
		super(id);
		this.message = message;
		this.createdAt = createdAt;
		this.sourceId = sourceId;
		this.targetId = targetId;
		this.multimediaId = multimediaId;
	}
	
	public String getMessage() {
		return message;
	}


	public void setMessage(String message) {
		this.message = message;
	}


	public Date getCreatedAt() {
		return createdAt;
	}


	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}


	public long getSourceId() {
		return sourceId;
	}


	public void setSourceId(long sourceId) {
		this.sourceId = sourceId;
	}


	public long getTargetId() {
		return targetId;
	}


	public void setTargetId(long targetId) {
		this.targetId = targetId;
	}


	public long getMultimediaId() {
		return multimediaId;
	}


	public void setMultimediaId(long multimediaId) {
		this.multimediaId = multimediaId;
	}



	
	
	
	
	
}
