package com.solvd.projectSocialNetwork.dao;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Select;

import com.solvd.projectSocialNetwork.model.Country;

public interface ICountryDAO extends IGenericDAO<Country>{
	
	@Select("insert into countries(name) values (#{name})")
	public Country save(Country g) ;
	@Select("select * from countries where id = #{id}")
	public Country getById(long id);
	@Delete("delete from countries where id = #{id}")
	public void remove(long id);
	
}