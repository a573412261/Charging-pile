package cn.com.bean;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

public class Message extends Exception {
	private String message;
	
	public Message(String message) {
		super(message);
		// TODO Auto-generated constructor stub
		this.message = message;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public JSONObject getJsonMessage() {
		String string = "{\"result\":\""+this.message+"\"}";
		return JSON.parseObject(string);
	}
}
