package com.solvd.projectSocialNetwork.model;

import java.sql.Date;
import java.util.List;

public class Subscriptions extends AbstractEntity{

	private String name;
	private Date suscriptionStartTime;
	private Date suscriptionEndTime;
	private Long userId;
	private List<Invoices> invoices;
	
	
	public Subscriptions() {
		
	}
	
	public Subscriptions(long id,String name, Date suscriptionStartTime, Date suscriptionEndTime, Long userId,List<Invoices> invoices) {
		super(id);
		this.name = name;
		this.suscriptionStartTime = suscriptionStartTime;
		this.suscriptionEndTime = suscriptionEndTime;
		this.userId = userId;
		this.invoices = invoices;
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Date getSuscriptionStartTime() {
		return suscriptionStartTime;
	}
	public void setSuscriptionStartTime(Date suscriptionStartTime) {
		this.suscriptionStartTime = suscriptionStartTime;
	}
	public Date getSuscriptionEndTime() {
		return suscriptionEndTime;
	}
	public void setSuscriptionEndTime(Date suscriptionEndTime) {
		this.suscriptionEndTime = suscriptionEndTime;
	}
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public List<Invoices> getInvoices() {
		return invoices;
	}
	public void setInvoices(List<Invoices> invoices) {
		this.invoices = invoices;
	}
	
	
	
}
