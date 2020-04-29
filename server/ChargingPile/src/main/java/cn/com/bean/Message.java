package cn.com.bean;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

public class Message extends Exception {
	private String message;
	private int error_code;
	
	public Message(String message) {
		super(message);
		// TODO Auto-generated constructor stub
		this.message = message;
		this.error_code=500;
	}
	
	public Message(String message,int error_code) {
		super(message);
		// TODO Auto-generated constructor stub
		this.message = message;
		this.error_code=error_code;
	}
	
	
	public String getMessage() {
		return message;
	}
	
	public int getError_code() {
		return error_code;
	}
	public void setMessageAndCode(String message,int error_code) {
		this.message = message;
		this.error_code=error_code;
		
	}
	
	
	public JSONObject getJsonMessage() {
		String string = " {\"msg\":\""+this.message+"\",\" error_code\":\""+this.error_code +"\"}     ";
		return JSON.parseObject(string);
	}
}
