package com.solvd.projectSocialNetwork.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "city")
@XmlAccessorType(XmlAccessType.FIELD)
public class City extends AbstractEntity {

	@XmlElement(name="name")
	private String name;
	@XmlElement(name="countryId")
	private long idCountry;
	
	public City() {
		super();
	}
	public City(long id, String name, long idCountry) {
		super(id);
		this.name = name;
		this.idCountry=idCountry;
	}

	@Override
	public String toString() {
		return "City [name=" + name +", id=" +id +"]";
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public long getIdCountry() {
		return idCountry;
	}



	public void setIdCountry(long idCountry) {
		this.idCountry = idCountry;
	}
	
}
