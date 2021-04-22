package com.solvd.projectSocialNetwork.dao;

public interface IGenericDAO<T> {

	public T save(T s);
	public T getById(long id);
	public void remove(long id);
}
