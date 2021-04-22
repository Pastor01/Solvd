package com.solvd.projectSocialNetwork.model;

public class Logins extends AbstractEntity {

	private String email;
	private String password;
	private int validationNumber;
	private long userId;
	
	public Logins() {
		super();
	}
	
	public Logins(long id,String email, String password, int validationNumber, long userId) {
		super(id);
		this.email = email;
		this.password = password;
		this.validationNumber = validationNumber;
		this.userId = userId;
	}
	
	
	
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getValidationNumber() {
		return validationNumber;
	}

	public void setValidationNumber(int validationNumber) {
		this.validationNumber = validationNumber;
	}

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}


	
}
