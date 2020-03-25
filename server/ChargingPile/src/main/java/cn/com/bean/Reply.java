package cn.com.bean;

import java.util.Date;

public class Reply {
	private Integer rid;
	private Date time;
	private String text;
	private Integer like;
	private Integer hate;
	private User user;
	private Comment comment;
	private Date create_time;
	
	public Reply() {
		// TODO Auto-generated constructor stub
	}
	public Reply(Integer rid, Date time, String text, Integer like, Integer hate, User user, Comment comment, Date create_time) {
		super();
		this.rid = rid;
		this.time = time;
		this.text = text;
		this.like = like;
		this.hate = hate;
		this.user = user;
		this.comment = comment;
		this.create_time = create_time;
	}
	public Integer getRid() {
		return rid;
	}
	public void setRid(Integer rid) {
		this.rid = rid;
	}
	public Date getTime() {
		return time;
	}
	public void setTime(Date time) {
		this.time = time;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public Integer getLike() {
		return like;
	}
	public void setLike(Integer like) {
		this.like = like;
	}
	public Integer getHate() {
		return hate;
	}
	public void setHate(Integer hate) {
		this.hate = hate;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public Comment getComment() {
		return comment;
	}
	public void setComment(Comment comment) {
		this.comment = comment;
	}
	public Date getCreate_time() {
		return create_time;
	}
	public void setCreate_time(Date create_time) {
		this.create_time = create_time;
	}
	@Override
	public String toString() {
		return "Reply [rid=" + rid + ", time=" + time + ", text=" + text + ", like=" + like + ", hate=" + hate
				+ ", user=" + user + ", comment=" + comment + ", create_time=" + create_time + "]";
	}
	
}
