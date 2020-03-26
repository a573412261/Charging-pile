package cn.com.service;

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
}
