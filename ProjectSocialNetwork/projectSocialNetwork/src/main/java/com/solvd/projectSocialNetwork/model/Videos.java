package com.solvd.projectSocialNetwork.model;

public class Videos extends Multimedia{

	private Double duration;
	private String contests;
	private long multimediaId;
	
	
	public Videos() {
		super();
	}
	
	public Videos(long id,Double duration,String contests, long multimediaId) {
		super(id);
		this.duration = duration;
		this.contests=contests;
		this.multimediaId = multimediaId;
	}
	
	public Videos(long id, char type, String name, String link, long postId) {
		super(id, type, name, link, postId);
	}
	
	public Videos(long id, char type, String name, String link, long postId, Double duration,String contests,long multimediaId) {
		super(id, type, name, link, postId);
		this.duration=duration;
		this.contests=contests;
		this.multimediaId=multimediaId;
	}
	
	
	public Double getDuration() {
		return duration;
	}
	public void setDuration(Double duration) {
		this.duration = duration;
	}
	public String getContests() {
		return contests;
	}
	public void setContests(String contests) {
		this.contests = contests;
	}
	public long getMultimediaId() {
		return multimediaId;
	}
	public void setMultimediaId(long multimediaId) {
		this.multimediaId = multimediaId;
	}
	
	
	
}
