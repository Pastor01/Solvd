package com.solvd.projectSocialNetwork.model;

import java.sql.Date;

public class UserFriends extends AbstractEntity{

	
	private int friendType;
	private int status;
	private	String notes;
	private Date createdAt;
	private long sourceId;
	private long targetId;
	
	public UserFriends() {
		super();
	}
	
	public UserFriends(long id,int friendType, int status, String notes, Date createdAt, long sourceId, long targetId) {
		super(id);
		this.friendType = friendType;
		this.status = status;
		this.notes = notes;
		this.createdAt = createdAt;
		this.sourceId = sourceId;
		this.targetId = targetId;
	}

	public int getFriendType() {
		return friendType;
	}

	public void setFriendType(int friendType) {
		this.friendType = friendType;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
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
	
	
	
}
