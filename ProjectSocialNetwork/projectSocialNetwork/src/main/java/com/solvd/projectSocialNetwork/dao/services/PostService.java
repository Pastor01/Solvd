package com.solvd.projectSocialNetwork.dao.services;

import java.util.Optional;

import com.solvd.projectSocialNetwork.dao.IMultimediaDAO;
import com.solvd.projectSocialNetwork.dao.IPostsDAO;
import com.solvd.projectSocialNetwork.dao.implementations.MultimediaDAO;
import com.solvd.projectSocialNetwork.dao.implementations.PostsDAO;
import com.solvd.projectSocialNetwork.model.Multimedia;
import com.solvd.projectSocialNetwork.model.Posts;

public class PostService {

	private IPostsDAO postDAO= new PostsDAO();
	private IMultimediaDAO multDAO = new MultimediaDAO();
	
	
	public Optional<Posts> getById(long id){
		Posts p = postDAO.getById(id);
		if(p!= null) {
			p.setMultimedia(multDAO.getMultimediaFromPostId(id));
			
		}
		return Optional.of(p);
		
	}
	
	public Optional<Posts> save(Posts post){
		Posts result= postDAO.save(post);
		if(result!= null) {
			for(Multimedia m: post.getMultimedia()) {
				multDAO.save(m);
			}
		}
		return Optional.of(post);
	}
}
