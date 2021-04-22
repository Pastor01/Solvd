package com.solvd.projectSocialNetwork.model;

import java.sql.Date;
import java.util.List;

public class UserGroups extends AbstractEntity {

	private String tittle;
	private String theme;
	private Date createdAt;
	private long createdBy;
	private List<Messages> groupMessages;
	private List<Users> groupMembers;
	
	public UserGroups() {
		super();
	}
	
	
	public UserGroups(long id,String tittle, String theme, Date createdAt, long createdBy, List<Messages> groupMessages, List<Users> groupMembers) {
		super(id);
		this.tittle = tittle;
		this.theme = theme;
		this.createdAt = createdAt;
		this.createdBy = createdBy;
		this.groupMessages=groupMessages;
		this.groupMembers= groupMembers;
	}
	
	public String getTittle() {
		return tittle;
	}
	public void setTittle(String tittle) {
		this.tittle = tittle;
	}
	public String getTheme() {
		return theme;
	}
	public void setTheme(String theme) {
		this.theme = theme;
	}
	public Date getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}
	public long getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(long createdBy) {
		this.createdBy = createdBy;
	}

	
	public void addMessages(Messages m) {
		this.groupMessages.add(m);
	}

	public void addGroupMembers(Users u) {
		this.groupMembers.add(u);
	}
	
	public List<Messages> getGroupMessages() {
		return groupMessages;
	}


	public void setGroupMessages(List<Messages> groupMessages) {
		this.groupMessages = groupMessages;
	}


	public List<Users> getGroupMembers() {
		return groupMembers;
	}


	public void setGroupMembers(List<Users> groupMembers) {
		this.groupMembers = groupMembers;
	}
	
	
}
