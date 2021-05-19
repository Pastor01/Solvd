package com.solvd.projectSocialNetwork.model;

public class Services extends AbstractEntity {

	private String name;
	private String objetive;
	
	public Services() {
		
	}
	
	public Services(long id,String name, String objetive) {
		super(id);
		this.name = name;
		this.objetive = objetive;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getObjetive() {
		return objetive;
	}
	public void setObjetive(String objetive) {
		this.objetive = objetive;
	}
	
}
