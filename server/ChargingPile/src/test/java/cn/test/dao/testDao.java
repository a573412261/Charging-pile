package cn.test.dao;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import org.junit.Test;

import cn.com.bean.Message;
import cn.com.bean.User;
import cn.com.dao.CommentDao;
import cn.com.dao.OrderDao;
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
		map.put("integral","0");
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
	
	@Test
	public void testqueryintegral() {
		String uuid="3A656F63C71C42A8BB320FDB71477209";
		try {
			UserDao.queryintegral(uuid);
		} catch (Message e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
		}
	}
	
	@Test
	public void testchargingintegral() {
		// TODO Auto-generated method stub
		String uuid="3A656F63C71C42A8BB320FDB71477209";//在本地数据库找一个uuid
		Integer intergral=6;
		try {
			UserDao.updateintegral(uuid, intergral);
		} catch (Message e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
		}
	}
	
	@Test
	public void testpay() {
		// TODO Auto-generated method stub
		String uuid="3A656F63C71C42A8BB320FDB71477209";//在本地数据库找一个uuid
		BigDecimal cost=new BigDecimal(23.3);
		try {
			UserDao.pay(uuid, cost);
		} catch (Message e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
		}
	}
	
	@Test
	public void testquerycost() {
		// TODO Auto-generated method stub
		String oid="1";
		try {
			OrderDao.querycost(oid);
		} catch (Message e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
		}
	}
	
	public void testaddcomment() {
		// TODO Auto-generated method stub
		String uuid="1D6B906B7E09415E888AE2364100DB06";
		String cid="1";
		int rank=5;
		String text="真好用";
		try {
			CommentDao.add(text, rank, cid, uuid);
		} catch (Message e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
		}
	}
}
