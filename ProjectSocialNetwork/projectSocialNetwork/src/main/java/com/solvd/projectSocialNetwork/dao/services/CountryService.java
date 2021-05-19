package com.solvd.projectSocialNetwork.dao.services;

import java.util.Optional;

import com.solvd.projectSocialNetwork.dao.ICityDAO;
import com.solvd.projectSocialNetwork.dao.ICountryDAO;
import com.solvd.projectSocialNetwork.dao.implementations.CityDAO;
import com.solvd.projectSocialNetwork.dao.implementations.CountryDAO;
import com.solvd.projectSocialNetwork.model.City;
import com.solvd.projectSocialNetwork.model.Country;

public class CountryService {
	
	private ICountryDAO countryDAO = new CountryDAO();
	private ICityDAO cityDAO = new CityDAO();
	
	public Optional<Country> getById(long id) {
		Country c = countryDAO.getById(id);
		if(c!= null) {
			c.addCities(cityDAO.getCitiesByCountryId(id));
		}
		return Optional.of(c);
	}
	
	public Optional<Country> save(Country c) {
		Country result = countryDAO.save(c);
		if(result!= null) {
			for (City city : c.getCities()) {
				cityDAO.save(city);
			}
		}
		return Optional.of(c);
	}
}
