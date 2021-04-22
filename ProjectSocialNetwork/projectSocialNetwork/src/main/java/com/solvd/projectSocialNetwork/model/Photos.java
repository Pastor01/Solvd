package com.solvd.projectSocialNetwork.model;

public class Photos extends Multimedia{

	private String resolution;
	private long multimediaId;

	public Photos() {
		super();
	}

	public Photos(long id,String resolution, long multimediaId) {
		super(id);
		this.resolution = resolution;
		this.multimediaId = multimediaId;
	}
	public Photos(long id, char type, String name, String link, long postId,String resolution,long multimediaId) {
		super(id, type, name, link, postId);
		this.resolution=resolution;
		this.multimediaId=multimediaId;
	}

	public String getResolution() {
		return resolution;
	}

	public void setResolution(String resolution) {
		this.resolution = resolution;
	}

	public long getMultimediaId() {
		return multimediaId;
	}

	public void setMultimediaId(long multimediaId) {
		this.multimediaId = multimediaId;
	}
	
	
}
