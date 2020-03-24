package cn.com.bean;

import java.util.Arrays;
import java.util.Date;

public class Comment {
	private int comid;
	private Date time;
	private int rank;
	private String text;
	private int like;
	private int hate;
	private User user;
	private Chargingpile chargingpile;
	private Date create_time;
	private Reply reply[];	//所有回复
	
	public Comment() {
		// TODO Auto-generated constructor stub
	}
	public Comment(int comid, Date time, int rank, String text, int like, int hate, User user,
			Chargingpile chargingpile, Date create_time, Reply[] reply) {
		super();
		this.comid = comid;
		this.time = time;
		this.rank = rank;
		this.text = text;
		this.like = like;
		this.hate = hate;
		this.user = user;
		this.chargingpile = chargingpile;
		this.create_time = create_time;
		this.reply = reply;
	}
	public int getComid() {
		return comid;
	}
	public void setComid(int comid) {
		this.comid = comid;
	}
	public Date getTime() {
		return time;
	}
	public void setTime(Date time) {
		this.time = time;
	}
	public int getRank() {
		return rank;
	}
	public void setRank(int rank) {
		this.rank = rank;
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
	public Chargingpile getChargingpile() {
		return chargingpile;
	}
	public void setChargingpile(Chargingpile chargingpile) {
		this.chargingpile = chargingpile;
	}
	public Date getCreate_time() {
		return create_time;
	}
	public void setCreate_time(Date create_time) {
		this.create_time = create_time;
	}
	public Reply[] getReply() {
		return reply;
	}
	public void setReply(Reply[] reply) {
		this.reply = reply;
	}
	@Override
	public String toString() {
		return "Comment [comid=" + comid + ", time=" + time + ", rank=" + rank + ", text=" + text + ", like=" + like
				+ ", hate=" + hate + ", user=" + user + ", chargingpile=" + chargingpile + ", create_time="
				+ create_time + ", reply=" + Arrays.toString(reply) + "]";
	}
	
	
}
