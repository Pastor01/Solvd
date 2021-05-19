package com.solvd.projectSocialNetwork.model;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlAccessType;

@XmlRootElement(name = "country")
@XmlAccessorType(XmlAccessType.FIELD)
public class Country extends AbstractEntity {
		
	@XmlElement(name="name")
	private String name;
	@XmlElementWrapper(name="cities")
	@XmlElement(name="city")
	private List<City> cities;
		
	public Country() {
			super();
		}
	
	public Country(long id, String name, List<City> cities) {
			super(id);
			this.name = name;
			this.cities = cities;
		}
	
	public String getName() {
			return name;
		}
	
	public void setName(String name) {
			this.name = name;
		}
	
		@Override
	public String toString() {
			return "Country [name=" + name + ", cities=" + getCities() + ", id="+getId()+"]";
	}
		
	public void addCities(List<City> cities) {
			this.cities=cities;
	
		}
	
	public List<City> getCities() {
			return cities;
		}
	
	public void setCities(List<City> cities) {
			this.cities = cities;
		}
	
	public void addCity(City c1) {
			cities.add(c1);
			
		}
		
}
