package cn.com.bean;

import java.util.Date;

public class Reply {
	private int rid;
	private Date time;
	private String text;
	private int like;
	private int hate;
	private User user;
	private Comment comment;
	private Date create_time;
	
	public Reply() {
		// TODO Auto-generated constructor stub
	}
	public Reply(int rid, Date time, String text, int like, int hate, User user, Comment comment, Date create_time) {
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
	public int getRid() {
		return rid;
	}
	public void setRid(int rid) {
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
	public int getLike() {
		return like;
	}
	public void setLike(int like) {
		this.like = like;
	}
	public int getHate() {
		return hate;
	}
	public void setHate(int hate) {
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
