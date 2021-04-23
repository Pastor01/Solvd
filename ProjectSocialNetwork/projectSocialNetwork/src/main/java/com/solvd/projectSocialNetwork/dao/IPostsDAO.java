package com.solvd.projectSocialNetwork.dao;

import com.solvd.projectSocialNetwork.model.Posts;


public interface IPostsDAO extends IGenericDAO<Posts>{

	public long getPostFromUserId(long id);
	
}
