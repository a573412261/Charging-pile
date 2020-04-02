package cn.com.dao;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;

import org.apache.commons.dbutils.handlers.ScalarHandler;

import cn.com.bean.Message;
import cn.com.bean.User;
import cn.com.jdbc.TxQueryRunner;
import cn.com.utils.CommonUtils;

public class UserDao {
	private static TxQueryRunner qr = new TxQueryRunner();

	/**
	 * 执行插入user表语句
	 * @param user	将此user记录插入到数据库中
	 * @throws Message 
	 */
	public static String add(User user) throws Message {
		// TODO Auto-generated method stub
		try {
			String sql = "insert into user(username,imageaddress,cartype,carnumber"
					+ ",integral,balance,password,address,cid,sid,uuid) "
					+ "values(?,?,?,?,?,?,?,?,?,?,?)";
			Object paramObject[] = getparamObject(user); 
			ArrayList<Object> arrayList = new ArrayList<Object>(Arrays.asList(paramObject));
			String uuid = CommonUtils.uuid();
			arrayList.add(uuid);
			int result = qr.update(sql,arrayList.toArray());
			if(result>0) {
				System.out.println("操作数据库成功，影响行数："+result);
				return uuid;
			}
			return null;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			throw new Message("用户注册失败");
		}
		
	}

	/**
	 * 执行修改user表语句
	 * 修改数据库中user的信息
	 * @param user	
	 * @throws Message
	 */
	public static void update(User user) throws Message {
		// TODO Auto-generated method stub
		try {
			String sql = "update user set username=?,cartype=?,"
					+ "carnumber=?,address=? where uuid=?";
			Object params[] = {user.getUsername(),user.getCartype(),user.getCarnumber(),user.getAddress(),user.getUuid()};
			int result = qr.update(sql,params);
			if(result>0) {
				System.out.println("操作数据库成功，影响行数："+result);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			throw new Message("用户信息修改失败");
		}
		
	}
	/**
	 * 执行修改user表语句
	 * 修改数据库中user的balance
	 * @String uuid BigDecimal money
	 * @throws Message
	 */
	public static void Chargingmoney(String uuid,BigDecimal money) throws Message {
		// TODO Auto-generated method stub
		try {
			String sql = "update user set balance=balance+? where uuid=?"; 
			Object[] parms= {money,uuid};
			int result = qr.update(sql,parms);
			if(result>0) {
				System.out.println("操作数据库成功，影响行数："+result);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			throw new Message("充钱失败");
		}
		
	}
	
	/**
	 * 执行查询user表语句
	 * 根据uuid查询数据库中user的balance
	 * @String uuid
	 * @throws Message
	 */
	public static BigDecimal querymoney(String uuid) throws Message {
		// TODO Auto-generated method stub
		try {
			String sql = "select balance from user where uuid=?"; 
			BigDecimal result = qr.query(sql,uuid,new ScalarHandler());
			if(result != null) {
				System.out.println(result);
				return result;
			}
			return null;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			throw new Message("查询余额失败");
		}
		
	}
	private static Object[] getparamObject(User user) {
		Object cid,sid;
		if(user.getChargingpile()!=null)
			cid = user.getChargingpile().getCid();
		else 
			cid = null;
		if(user.getSchedule()!=null)
			sid = user.getSchedule().getSid();
		else 
			sid = null;
		Object paramObject[] = {user.getUsername(),user.getImageaddress(),user.getCartype(),
				user.getCarnumber(),user.getIntegral(),user.getBalance(),user.getPassword(),
				user.getAddress(),cid,sid};
		return paramObject;
	}
}
