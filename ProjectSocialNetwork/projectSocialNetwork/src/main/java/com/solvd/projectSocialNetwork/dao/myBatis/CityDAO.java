package com.solvd.projectSocialNetwork.dao.myBatis;

import java.util.ArrayList;

import com.solvd.projectSocialNetwork.dao.ICityDAO;
import com.solvd.projectSocialNetwork.model.City;

public class CityDAO extends MyBatisDAO implements ICityDAO{

	private ICityDAO dao = sqlSessionFactory.openSession(true).getMapper(ICityDAO.class);

	@Override
	public City save(City c) {
		dao.save(c);
		return c;
	}

	@Override
	public City getById(long id) {
		if(dao.getById(id)!=null)
			return dao.getById(id);
		return new City();
	}

	@Override
	public void remove(long id) {
		dao.remove(id);
	}

	@Override
	public ArrayList<City> getCitiesByCountryId(long id) {
		if(dao.getCitiesByCountryId(id)!=null)
			return dao.getCitiesByCountryId(id);
		return new ArrayList<City>();
	}

}
