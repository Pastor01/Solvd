package com.solvd.projectSocialNetwork.model;

import java.sql.Date;

public class Invoices extends AbstractEntity{

	private Date emittion;
	private Date expirationDate;
	private Double extraCharge;
	private long subscriptionId;
	
	public Invoices() {
		
	}
	public Invoices(long id,Date emittion, Date expirationDate, Double extraCharge, long subscriptionId) {
		super(id);
		this.emittion = emittion;
		this.expirationDate = expirationDate;
		this.extraCharge = extraCharge;
		this.subscriptionId = subscriptionId;
	}
	public Date getEmittion() {
		return emittion;
	}
	public void setEmittion(Date emittion) {
		this.emittion = emittion;
	}
	public Date getExpirationDate() {
		return expirationDate;
	}
	public void setExpirationDate(Date expirationDate) {
		this.expirationDate = expirationDate;
	}
	public Double getExtraCharge() {
		return extraCharge;
	}
	public void setExtraCharge(Double extraCharge) {
		this.extraCharge = extraCharge;
	}
	public long getSubscriptionId() {
		return subscriptionId;
	}
	public void setSubscriptionId(long subscriptionId) {
		this.subscriptionId = subscriptionId;
	}
	
}
