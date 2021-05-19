package com.solvd.projectSocialNetwork.dao;

import java.util.ArrayList;

import com.solvd.projectSocialNetwork.model.City;

public interface ICityDAO extends IGenericDAO<City>{
	
	public ArrayList<City> getCitiesByCountryId(long id);
	
}
