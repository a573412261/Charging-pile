package cn.test.dao;

import java.math.BigDecimal;
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
		map.put("balance","0");
		User user = CommonUtils.toBean(map, User.class);
		try {
			UserDao.add(user);
		} catch (Message e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
		}
	}
	@Test
	public void testchargingmoney() {
		// TODO Auto-generated method stub
		String uuid="3A656F63C71C42A8BB320FDB71477209";//在本地数据库找一个uuid
		BigDecimal money=new BigDecimal(23.23);
		try {
			UserDao.Chargingmoney(uuid, money);
		} catch (Message e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
		}
	}
	
	@Test
	public void testquerymoney() {
		String uuid="3A656F63C71C42A8BB320FDB71477209";
		try {
			UserDao.querymoney(uuid);
		} catch (Message e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
		}
	}
}
