package cn.com.service;

import java.math.BigDecimal;
import java.sql.SQLException;

import cn.com.bean.Message;
import cn.com.bean.User;
import cn.com.dao.UserDao;

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
}
