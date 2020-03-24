package cn.test.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;

import cn.com.bean.User;
import cn.com.dao.UserDao;
import cn.com.utils.CommonUtils;

public class testDao {
	
	@Test
	public void testAdd() {
		// TODO Auto-generated method stub
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("username", "laalla");
		map.put("password", "123456");
		User user = CommonUtils.toBean(map, User.class);
		UserDao.add(user);
	}

}
