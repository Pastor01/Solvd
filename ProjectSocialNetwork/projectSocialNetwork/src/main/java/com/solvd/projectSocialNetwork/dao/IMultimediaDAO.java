package com.solvd.projectSocialNetwork.dao;

import java.util.List;

import com.solvd.projectSocialNetwork.model.Multimedia;


public interface IMultimediaDAO extends IGenericDAO<Multimedia> {

	public List<Multimedia> getMultimediaFromPostId(long id);
}
