package com.solvd.projectSocialNetwork.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;

@XmlAccessorType(XmlAccessType.FIELD)
public abstract class AbstractEntity {
	
	@XmlAttribute(name="id")
	public long id;
	
	public AbstractEntity() {
		this.id = 0;
	}

	public AbstractEntity(long id) {
		this.id = id;
	}
	
	public long getId() {
		return this.id;
	}
	
	public void setId(long id) {
		this.id=id;
		
	}
	
	
}
