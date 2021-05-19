package com.solvd.projectSocialNetwork.dao;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Select;

import com.solvd.projectSocialNetwork.model.City;

public interface ICityDAO extends IGenericDAO<City>{
	
	@Select("insert into cities(name,countriesId) values (#{name},#{idCountry})")
	public City save(City g) ;
	@Select("select * from cities  where id = #{id}")
	public City getById(long id);
	@Delete("delete from cities where id = #{id}")
	public void remove(long id) ;
	@Select("select * from cities  where id_country = #{id}")
	public ArrayList<City> getCitiesByCountryId(long id);
	
}
