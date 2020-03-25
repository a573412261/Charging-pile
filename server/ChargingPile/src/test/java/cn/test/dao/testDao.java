package cn.test.dao;

import java.util.HashMap;
import java.util.Map;
import org.junit.Test;

import cn.com.bean.Message;
import cn.com.bean.User;
import cn.com.dao.UserDao;
import cn.com.utils.CommonUtils;

public class testDao {
	
	@Test
	public void testAdd() {
		// TODO Auto-generated method stub
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("username", "吴桂兴");
		map.put("password", "45678");
		User user = CommonUtils.toBean(map, User.class);
		try {
			UserDao.add(user);
		} catch (Message e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
		}
	}

}
