package com.solvd.projectSocialNetwork.model;

import java.sql.Date;

public class GroupMessages extends AbstractEntity {

	private String message;
	private Date createdAt;
	private long groupId;
	private long userId;
	
	public GroupMessages() {
		super();
	}
	
	public GroupMessages(long id,String message, Date createdAt, long groupId, long userId) {
		super(id);
		this.message = message;
		this.createdAt = createdAt;
		this.groupId = groupId;
		this.userId = userId;
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

	public long getGroupId() {
		return groupId;
	}

	public void setGroupId(long groupId) {
		this.groupId = groupId;
	}

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}


	
	
	
}
