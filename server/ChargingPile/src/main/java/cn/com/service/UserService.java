package cn.com.service;

import java.math.BigDecimal;
import java.sql.SQLException;

import cn.com.bean.Chargingpile;
import cn.com.bean.Message;
import cn.com.bean.User;
import cn.com.dao.ChargingpileDao;
import cn.com.dao.UserDao;
import cn.com.jdbc.jdbcUtils;

public class UserService {
	
	/**
	 * 将user插入到数据库的user表中
	 * @param user
	 * @throws SQLException 
	 */
	public String add(User user) throws Message {
		// TODO Auto-generated method stub
		return UserDao.add(user);
	}

	public void update(User user) throws Message {
		// TODO Auto-generated method stub
		UserDao.update(user);
	}
	
	public BigDecimal chargingmoney(String uuid,BigDecimal money) throws Message {
		// TODO Auto-generated method stub
		UserDao.Chargingmoney(uuid, money);
		return UserDao.querymoney(uuid);
	}
	
	public void updatepassword(String uuid, String password, String newpassword) throws Message {
		// TODO Auto-generated method stub
		UserDao.updatepassword(uuid, password, newpassword);
	}
	
	public void reserve(String uuid, Integer cid, Integer sid, Chargingpile chargingpile) throws Message {
		try {
			//开启事务
			jdbcUtils.beginTransaction();		
			//给用户添加充电桩的id和时间段id
			UserDao.updatecidsid(uuid, cid, sid);	
			//修改充电桩的状态为已预约
			ChargingpileDao.updatestatus(chargingpile);
			//提交事务
			jdbcUtils.commitTransaction();		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			try {
				//回滚事务
				jdbcUtils.rollbackTransaction();	
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}
	
	public Integer deletereservedchargepileservice(String uuid, Integer changedintegral, Chargingpile chargingpile) throws Message{
		try {
			jdbcUtils.beginTransaction();
			//修改该用户下的积分值
			UserDao.updateintegral(uuid, changedintegral);
			//修改充电桩的状态为空闲状态
			ChargingpileDao.updatestatus(chargingpile);
			//修改该用户下的cid和sid为空值
			UserDao.updatecidsid(uuid, null, null);
			jdbcUtils.commitTransaction();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			try {
				jdbcUtils.rollbackTransaction();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		//返回用户当前的积分值
		return UserDao.queryintegral(uuid);
	}
}
