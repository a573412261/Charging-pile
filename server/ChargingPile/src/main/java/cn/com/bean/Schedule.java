package cn.com.bean;

import java.util.Date;

public class Schedule {
	private int sid;
	private String time;
	private Date create_time;
	
	public Schedule() {
		// TODO Auto-generated constructor stub
	}
	public Schedule(int sid, String time, Date create_time) {
		super();
		this.sid = sid;
		this.time = time;
		this.create_time = create_time;
	}
	public int getSid() {
		return sid;
	}
	public void setSid(int sid) {
		this.sid = sid;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public Date getCreate_time() {
		return create_time;
	}
	public void setCreate_time(Date create_time) {
		this.create_time = create_time;
	}
	@Override
	public String toString() {
		return "Schedule [sid=" + sid + ", time=" + time + "]";
	}
	
}
