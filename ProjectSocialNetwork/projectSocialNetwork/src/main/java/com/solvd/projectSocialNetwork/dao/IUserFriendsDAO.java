package com.solvd.projectSocialNetwork.dao;

import java.util.List;

import com.solvd.projectSocialNetwork.model.UserFriends;


public interface IUserFriendsDAO extends IGenericDAO<UserFriends>{

	public List<Long> getFriendFromUserId(long id);
}
