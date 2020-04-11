package cn.com.bean;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Date;

public class User {
	private Integer uid;
	private String uuid;
	private String username;
	private String imageaddress;
	private String cartype;
	private String carnumber;
	private Integer integral;
	private BigDecimal balance;
	private String password;
	private String address;
	private Chargingpile chargingpile;
	private Schedule schedule;
	private Date create_time;
	private Order order[];	//所有订单
	private Comment comment[];	//所有评价
	private Reply reply[];	//所有回复
	
	public User() {
		// TODO Auto-generated constructor stub
	}

	public User(Integer uid, String uuid, String username, String imageaddress, String cartype, String carnumber,
			Integer integral, BigDecimal balance, String password, String address, Chargingpile chargingpile,
			Schedule schedule, Date create_time, Order[] order, Comment[] comment, Reply[] reply) {
		super();
		this.uid = uid;
		this.uuid = uuid;
		this.username = username;
		this.imageaddress = imageaddress;
		this.cartype = cartype;
		this.carnumber = carnumber;
		this.integral = integral;
		this.balance = balance;
		this.password = password;
		this.address = address;
		this.chargingpile = chargingpile;
		this.schedule = schedule;
		this.create_time = create_time;
		this.order = order;
		this.comment = comment;
		this.reply = reply;
	}


	public Integer getUid() {
		return uid;
	}

	public void setUid(Integer uid) {
		this.uid = uid;
	}

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getImageaddress() {
		return imageaddress;
	}

	public void setImageaddress(String imageaddress) {
		this.imageaddress = imageaddress;
	}

	public String getCartype() {
		return cartype;
	}

	public void setCartype(String cartype) {
		this.cartype = cartype;
	}

	public String getCarnumber() {
		return carnumber;
	}

	public void setCarnumber(String carnumber) {
		this.carnumber = carnumber;
	}

	public Integer getIntegral() {
		return integral;
	}

	public void setIntegral(Integer integral) {
		this.integral = integral;
	}

	public BigDecimal getBalance() {
		return balance;
	}

	public void setBalance(BigDecimal balance) {
		this.balance = balance;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Chargingpile getChargingpile() {
		return chargingpile;
	}

	public void setChargingpile(Chargingpile chargingpile) {
		this.chargingpile = chargingpile;
	}

	public Schedule getSchedule() {
		return schedule;
	}

	public void setSchedule(Schedule schedule) {
		this.schedule = schedule;
	}

	public Date getCreate_time() {
		return create_time;
	}

	public void setCreate_time(Date create_time) {
		this.create_time = create_time;
	}

	public Order[] getOrder() {
		return order;
	}

	public void setOrder(Order[] order) {
		this.order = order;
	}

	public Comment[] getComment() {
		return comment;
	}

	public void setComment(Comment[] comment) {
		this.comment = comment;
	}

	public Reply[] getReply() {
		return reply;
	}

	public void setReply(Reply[] reply) {
		this.reply = reply;
	}

	@Override
	public String toString() {
		return "User [uid=" + uid + ", uuid=" + uuid + ", username=" + username + ", imageaddress=" + imageaddress
				+ ", cartype=" + cartype + ", carnumber=" + carnumber + ", integral=" + integral + ", balance="
				+ balance + ", password=" + password + ", address=" + address + ", chargingpile=" + chargingpile
				+ ", schedule=" + schedule + ", create_time=" + create_time + ", order=" + Arrays.toString(order)
				+ ", comment=" + Arrays.toString(comment) + ", reply=" + Arrays.toString(reply) + "]";
	}
	
}	
