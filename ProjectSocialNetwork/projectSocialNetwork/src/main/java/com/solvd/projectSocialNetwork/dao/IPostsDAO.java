package com.solvd.projectSocialNetwork.dao;

import java.util.List;

import com.solvd.projectSocialNetwork.model.Posts;


public interface IPostsDAO extends IGenericDAO<Posts>{

	public List<Posts> getPostFromUserId(long id);
	
}
