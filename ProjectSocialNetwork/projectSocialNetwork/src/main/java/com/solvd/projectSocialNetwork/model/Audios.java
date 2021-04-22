package com.solvd.projectSocialNetwork.model;

public class Audios extends Multimedia {

	private Double duration;
	private long multimediaId;

	
	public Audios() {
		super();
	}
	
	
	public Audios(long id,Double duration, long multimediaId) {
		super(id);
		this.duration = duration;
		this.multimediaId = multimediaId;
	}


	public Audios(long id, char type, String name, String link, long postId) {
		super(id, type, name, link, postId);
		
	}
	
	public Audios(long id, char type, String name, String link, long postId,Double duration,long multimediaId) {
		super(id, type, name, link, postId);
		this.duration=duration;
		this.multimediaId=multimediaId;
	}
	
	
	
	public Double getDuration() {
		return duration;
	}
	public void setDuration(Double duration) {
		this.duration = duration;
	}
	public long getMultimediaId() {
		return multimediaId;
	}
	public void setMultimediaId(long multimediaId) {
		this.multimediaId = multimediaId;
	}
	
	
	
}
