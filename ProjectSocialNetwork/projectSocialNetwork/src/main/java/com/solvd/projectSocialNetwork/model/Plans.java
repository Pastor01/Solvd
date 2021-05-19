package com.solvd.projectSocialNetwork.model;

public class Plans extends AbstractEntity{

	private String name;
	private Double pricePerMonth;
	private String description;
	private long servicesId;
	
	public Plans() {
		
	}
	
	public Plans(long id,String name, Double pricePerMonth, String description, long servicesId) {
		super(id);
		this.name = name;
		this.pricePerMonth = pricePerMonth;
		this.description = description;
		this.servicesId = servicesId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Double getPricePerMonth() {
		return pricePerMonth;
	}
	public void setPricePerMonth(Double pricePerMonth) {
		this.pricePerMonth = pricePerMonth;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public long getServicesId() {
		return servicesId;
	}
	public void setServicesId(long servicesId) {
		this.servicesId = servicesId;
	}
	
}
