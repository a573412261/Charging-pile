package cn.com.service;

import cn.com.bean.User;
import cn.com.dao.UserDao;

public class UserService {
	
	/**
	 * 将user插入到数据库的user表中
	 * @param user
	 */
	public void add(User user) {
		// TODO Auto-generated method stub
		UserDao.add(user);
	}
}
