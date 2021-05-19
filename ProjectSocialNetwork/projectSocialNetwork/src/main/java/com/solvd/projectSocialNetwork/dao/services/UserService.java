package com.solvd.projectSocialNetwork.dao.services;

import java.util.Optional;

import com.solvd.projectSocialNetwork.dao.IPostsDAO;
import com.solvd.projectSocialNetwork.dao.IUsersDAO;
import com.solvd.projectSocialNetwork.dao.implementations.PostsDAO;
import com.solvd.projectSocialNetwork.dao.implementations.UsersDAO;
import com.solvd.projectSocialNetwork.model.Posts;
import com.solvd.projectSocialNetwork.model.Users;

public class UserService {

	private IUsersDAO userDAO= new UsersDAO();
	private IPostsDAO postDAO= new PostsDAO();
	
	public Optional<Users> getById(long id){
		Users u= userDAO.getById(id);
		if(u!=null) {
			u.setPosts(postDAO.getPostFromUserId(id));
		}
		return Optional.of(u);
	}
	
	public Optional<Users> save(Users u){
		Users result = userDAO.save(u);
		if(result!= null) {
			for(Posts p: u.getPosts()) {
				postDAO.save(p);
			}
		}
		return Optional.of(u);
		
		
	}
}
