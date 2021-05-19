package com.solvd.projectSocialNetwork.parser;

import java.util.ArrayList;
import java.util.List;


import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import com.solvd.projectSocialNetwork.model.City;
import com.solvd.projectSocialNetwork.model.Country;

public class SaxParser extends DefaultHandler{
	private Country country;
	
	private List<Country> countries= new ArrayList<Country>();
	private List<City> cities= new ArrayList<City>();
	private City city;
	private StringBuilder buffer = new StringBuilder();
	
	@Override
	public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
	
		if(qName.equals("country")) {
			country= new Country();
			countries.add(country);
			country.setId(Long.parseLong(attributes.getValue("id")));
		}else if(qName.equals("city")) {
			city= new City();
			cities.add(city);
			country.addCities(cities);
			city.setId(Long.parseLong(attributes.getValue("id")));
			
		}
		

		buffer.delete(0, buffer.length());
	}

	@Override
	public void endElement(String uri, String localName, String qName) throws SAXException {
		
		if(qName.equals("name") & cities.isEmpty()) {
			country.setName(buffer.toString());
		}else if(qName.equals("name") & city != null) {
			city.setName(buffer.toString());
		}else if(qName.equals("countriesId")) {
			city.setIdCountry(Integer.parseInt(buffer.toString()));
		}
		
		
	}

	@Override
	public void characters(char[] ch, int start, int length) throws SAXException {
		buffer.append(ch, start, length);
		
		
	}

	public List<Country> getCountries() {
		return countries;
	}
	
}
