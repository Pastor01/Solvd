package com.solvd.projectSocialNetwork.model;

public class Multimedia extends AbstractEntity{

	private char type;
	private String name;
	private String link;
	private long postId;
	
	public Multimedia() {
		super();
	}
	public Multimedia(long id) {
		super(id);
	}
	
	public Multimedia(long id,char type, String name, String link, long postId) {
		super(id);
		this.type = type;
		this.name = name;
		this.link = link;
		this.postId = postId;
	}
	public char getType() {
		return type;
	}
	public void setType(char type) {
		this.type = type;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getLink() {
		return link;
	}
	public void setLink(String link) {
		this.link = link;
	}
	public long getPostId() {
		return postId;
	}
	public void setPostId(long postId) {
		this.postId = postId;
	}
	
	
	
}
