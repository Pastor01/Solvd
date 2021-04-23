package com.solvd.projectSocialNetwork.dao;

import com.solvd.projectSocialNetwork.model.Audios;


public interface IAudioDAO extends IGenericDAO<Audios>{

	public long getAudioFromMultimediaId(long id);
}
