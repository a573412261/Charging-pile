package cn.com.bean;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Date;

public class Chargingpile {
	private Integer cid;
	private String code;
	private String name;
	private String address;
	private BigDecimal longitude;
	private BigDecimal latitude;
	private BigDecimal power;
	private Integer status;
	private BigDecimal rank;
	private BigDecimal charge;
	private String interfacetype;
	private Date create_time;
	private Order order[];	//所有订单
	private Comment comment[];	//所有评价
	
	public Chargingpile() {
		// TODO Auto-generated constructor stub
	}
	public Chargingpile(Integer cid, String code, String name, String address, BigDecimal longitude, BigDecimal latitude,
			BigDecimal power, Integer status, BigDecimal rank, BigDecimal charge, String interfacetype, Date create_time,
			Order[] order, Comment[] comment) {
		super();
		this.cid = cid;
		this.code = code;
		this.name = name;
		this.address = address;
		this.longitude = longitude;
		this.latitude = latitude;
		this.power = power;
		this.status = status;
		this.rank = rank;
		this.charge = charge;
		this.interfacetype = interfacetype;
		this.create_time = create_time;
		this.order = order;
		this.comment = comment;
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

	public Integer getCid() {
		return cid;
	}
	public void setCid(Integer cid) {
		this.cid = cid;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public BigDecimal getLongitude() {
		return longitude;
	}
	public void setLongitude(BigDecimal longitude) {
		this.longitude = longitude;
	}
	public BigDecimal getLatitude() {
		return latitude;
	}
	public void setLatitude(BigDecimal latitude) {
		this.latitude = latitude;
	}
	public BigDecimal getPower() {
		return power;
	}
	public void setPower(BigDecimal power) {
		this.power = power;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public BigDecimal getRank() {
		return rank;
	}
	public void setRank(BigDecimal rank) {
		this.rank = rank;
	}
	public BigDecimal getCharge() {
		return charge;
	}
	public void setCharge(BigDecimal charge) {
		this.charge = charge;
	}
	public String getInterfacetype() {
		return interfacetype;
	}
	public void setInterfacetype(String interfacetype) {
		this.interfacetype = interfacetype;
	}
	public Date getCreate_time() {
		return create_time;
	}
	public void setCreate_time(Date create_time) {
		this.create_time = create_time;
	}

	@Override
	public String toString() {
		return "Chargingpile [cid=" + cid + ", code=" + code + ", name=" + name + ", address=" + address
				+ ", longitude=" + longitude + ", latitude=" + latitude + ", power=" + power + ", status=" + status
				+ ", rank=" + rank + ", charge=" + charge + ", interfacetype=" + interfacetype + ", create_time="
				+ create_time + ", order=" + Arrays.toString(order) + ", comment=" + Arrays.toString(comment) + "]";
	}
}
