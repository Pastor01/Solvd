package com.solvd.projectSocialNetwork.dao;


import java.util.List;

import com.solvd.projectSocialNetwork.model.Videos;

public interface IVideosDAO extends IGenericDAO<Videos>{

	public long getVideoFromMultimediaId(long id);
}
