package com.solvd.projectSocialNetwork.model;

import java.sql.Date;
import java.util.List;

public class Users extends AbstractEntity{

	private String name;
	private String lastName;
	private String userName;
	private Date registerAt;
	private int mobile;
	private List<Posts> posts;
	private List<Users> friends;
	
	public Users() {
		super();
	}

	public Users(long id,String name, String lastName, String userName, Date registerAt, int mobile, List<Posts> posts, List<Users> friends) {
		super(id);
		this.name = name;
		this.lastName = lastName;
		this.userName = userName;
		this.registerAt = registerAt;
		this.mobile = mobile;
		this.posts=posts;
		this.setFriends(friends);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public Date getRegisterAt() {
		return registerAt;
	}

	public void setRegisterAt(Date registerAt) {
		this.registerAt = registerAt;
	}

	public int getMobile() {
		return mobile;
	}

	public void setMobile(int mobile) {
		this.mobile = mobile;
	}
	
	@Override
	public String toString() {
		return "User [name=" + name + ", lastName=" + lastName + ", username="+ userName+ "]"; 
	}

	public void addMultimedia(Posts p) {
		posts.add(p);
		
	}
	public void addFriends(Users f) {
		friends.add(f);
		
	}
	
	public List<Posts> getPosts() {
		return posts;
	}

	public void setPosts(List<Posts> post) {
		this.posts = post;
	}

	public List<Users> getFriends() {
		return friends;
	}

	public void setFriends(List<Users> friends) {
		this.friends = friends;
	}

	
}
