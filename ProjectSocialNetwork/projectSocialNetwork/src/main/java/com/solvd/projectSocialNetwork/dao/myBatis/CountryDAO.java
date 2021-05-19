package com.solvd.projectSocialNetwork.dao.myBatis;

import com.solvd.projectSocialNetwork.dao.ICountryDAO;
import com.solvd.projectSocialNetwork.model.Country;

public class CountryDAO extends MyBatisDAO implements ICountryDAO{
	ICountryDAO dao = sqlSessionFactory.openSession(true).getMapper(ICountryDAO.class);

	@Override
	public Country save(Country c) {
		dao.save(c);
		return c;
	}

	@Override
	public Country getById(long id) {
		if(dao.getById(id)!=null)
			return dao.getById(id);
		return new Country();
	}

	@Override
	public void remove(long id) {
		dao.remove(id);
	
	}
}
