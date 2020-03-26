package cn.com.bean;

import java.math.BigDecimal;
import java.util.Date;

public class Order {
	private Integer oid;
	private String order_code;
	private Date start_time;
	private Date end_time;
	private BigDecimal cost;
	private BigDecimal capacity;
	private Integer status;
	private User user;
	private Chargingpile chargingpile;
	private Date create_time;
	
	public Order() {
		// TODO Auto-generated constructor stub
	}
	public Order(Integer oid, String order_code, Date start_time, Date end_time, BigDecimal cost, BigDecimal capacity,
			Integer status, User user, Chargingpile chargingpile, Date create_time) {
		super();
		this.oid = oid;
		this.order_code = order_code;
		this.start_time = start_time;
		this.end_time = end_time;
		this.cost = cost;
		this.capacity = capacity;
		this.status = status;
		this.user = user;
		this.chargingpile = chargingpile;
		this.create_time = create_time;
	}
	public Integer getOid() {
		return oid;
	}
	public void setOid(Integer oid) {
		this.oid = oid;
	}
	public String getOrder_code() {
		return order_code;
	}
	public void setOrder_code(String order_code) {
		this.order_code = order_code;
	}
	public Date getStart_time() {
		return start_time;
	}
	public void setStart_time(Date start_time) {
		this.start_time = start_time;
	}
	public Date getEnd_time() {
		return end_time;
	}
	public void setEnd_time(Date end_time) {
		this.end_time = end_time;
	}
	public BigDecimal getCost() {
		return cost;
	}
	public void setCost(BigDecimal cost) {
		this.cost = cost;
	}
	public BigDecimal getCapacity() {
		return capacity;
	}
	public void setCapacity(BigDecimal capacity) {
		this.capacity = capacity;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
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
	@Override
	public String toString() {
		return "Order [oid=" + oid + ", order_code=" + order_code + ", start_time=" + start_time + ", end_time="
				+ end_time + ", cost=" + cost + ", capacity=" + capacity + ", status=" + status + ", user=" + user
				+ ", chargingpile=" + chargingpile + ", create_time=" + create_time + "]";
	}
	
	
}
