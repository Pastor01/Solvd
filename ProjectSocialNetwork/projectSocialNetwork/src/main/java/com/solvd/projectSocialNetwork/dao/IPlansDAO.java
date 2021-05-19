package com.solvd.projectSocialNetwork.dao;

import java.util.ArrayList;

import com.solvd.projectSocialNetwork.model.Plans;


public interface IPlansDAO extends IGenericDAO<Plans>{

	public ArrayList<Plans> getCitiesByServicesId(long id);
}
