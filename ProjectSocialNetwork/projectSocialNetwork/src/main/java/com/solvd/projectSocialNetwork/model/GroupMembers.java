package com.solvd.projectSocialNetwork.model;

import java.sql.Date;

public class GroupMembers extends AbstractEntity{

	private int role;
	private int status;
	private Date createdAt;
	private long groupsId;
	private long usersId;
	
	public GroupMembers() {
		super();
	}
	public GroupMembers(long id,int role, int status, Date createdAt, long groupsId, long usersId) {
		super(id);
		this.role = role;
		this.status = status;
		this.createdAt = createdAt;
		this.groupsId = groupsId;
		this.usersId = usersId;
	}
	
	
	public int getRole() {
		return role;
	}
	public void setRole(int role) {
		this.role = role;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public Date getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}
	public long getGroupsId() {
		return groupsId;
	}
	public void setGroupsId(long groupsId) {
		this.groupsId = groupsId;
	}
	public long getUsersId() {
		return usersId;
	}
	public void setUsersId(long usersId) {
		this.usersId = usersId;
	}
	
	
	
	
}
