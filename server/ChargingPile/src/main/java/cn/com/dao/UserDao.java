package cn.com.dao;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;

import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import cn.com.bean.Message;
import cn.com.bean.User;
import cn.com.jdbc.TxQueryRunner;
import cn.com.utils.CommonUtils;

/**
 * ResultSetHandler接口：
 * BeanHandler（单行） --> 构造器需要一个class类型的参数，用来把一行结果转化为指定类型的JavaBean对象
 * BeanListHandler（多行） --> 构造器需要一个class类型的参数，用来把结果集转换成List对象，一堆JavaBean
 * MapHandler（单行） --> 把一行结果集转换成Map对象
 * MapListHandler（多行）把一行记录转换成一个Map，多行就是多个Map,即List<Map>
 * ScalarHandler（单行单列） --> 通常用于select count(*) from user 语句，结果集是单行单列，返回一个object
 * @author ASUS
 *
 */




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
	 *	通过uuid查询用户信息
	 * @param uuid
	 * @return 返回用户对象
	 * @throws Message
	 */
	public static User query(String uuid) throws Message {
		// TODO Auto-generated method stub
		try {
			String sql = "select uid,username,cartype,carnumber,integral,"
					+ "balance,address,cid,sid from user where uuid=?";
			Object paramObject[] = {uuid};
			User user = qr.query(sql, paramObject,new BeanHandler<User>(User.class));
			if(user != null) {
				System.out.println("操作数据库成功：");
				System.out.println(user);
				return user;
			}
			return null;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			throw new Message("查询用户信息失败");
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
	 * 执行修改user表语句
	 * 修改数据库中user的balance
	 * @String uuid BigDecimal cost
	 * @throws Message
	 */
	public static void pay(String uuid,BigDecimal cost) throws Message {
		// TODO Auto-generated method stub
		try {
			String sql = "update user set balance=balance-? where uuid=?"; 
			Object[] parms= {cost,uuid};
			int result = qr.update(sql,parms);
			if(result>0) {
				System.out.println("操作数据库成功，影响行数："+result);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			throw new Message("支付失败");
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
			BigDecimal result = qr.query(sql,uuid,new ScalarHandler<BigDecimal>());
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
	
	
	/**
	 * 执行修改user表用户密码的语句
	 * 修改数据库中user的密码
	 * @param user	
	 * @throws Message
	 */
	
	public static void updatepassword(String uuid, String password, String newpassword) throws Message {
		// TODO Auto-generated method stub
		try {			
			//查看是否存在uuid和密码相对应的用户
			String sql1 = "select count(*) from user where uuid=? and password=?";			
			Object[] params1= {uuid,password};
			Long result1= ((Long)(qr.query(sql1, params1, new ScalarHandler())));		
			//判断符合条件的用户个数
			if(result1 > 0) {
				System.out.println("原密码与uuid相符");
				//更新用户的密码
				String sql2 = "update user set password= ? where uuid= ?";
				Object[] params2= {newpassword,uuid};
				int result2 = qr.update(sql2,params2);
				if(result2>0)	{
					//执行成功输出结果
					System.out.println("操作数据库成功，影响行数："+result2);
				}
			}
			else	System.out.println("原密码与uuid不符，操作失败");
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			throw new Message("用户密码修改失败");
			
		
		}
		
	}	
	
	/**
	 * 执行修改user表的cid和sid的语句
	 * 执行修改chargingpile表的status的
	 * @param uuid
	 * @param cid
	 * @param sid
	 * @throws Message
	 */
	public static void updatecidsid(String uuid, Integer cid, Integer sid) throws Message {
		try {
			String sql = "update user set cid=?,sid=? where uuid=?";
			Object params[] = {cid, sid, uuid};
			int result = qr.update(sql,params);
			if(result>0) {
				System.out.println("操作数据库成功，影响行数："+result);
			}
		}catch(Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			throw new Message("用户cid和sid修改失败");
		}
	}
	
	/**
	 * 执行修改user表的integral的语句
	 * 根据uuid修改user表中的integral
	 * @param uuid
	 * @param changedintegral
	 * @throws Message
	 */
	public static void updateintegral(String uuid, Integer changedintegral) throws Message {
		
		try {
			String sql = "update user set integral=integral+? where uuid=?";
			Object params[] = {changedintegral, uuid};
			int result = qr.update(sql,params);
			if(result>0) {
				System.out.println("操作数据库成功，影响行数："+result);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new Message("用户积分修改失败");
		}
		
	}
	/**
	 * 执行查询user表的语句
	 * 根据uuid查询user表的cid值
	 * @param uuid
	 * @return
	 * @throws Message
	 */
	public static Integer querycid(String uuid) throws Message {
		try {
			String sql = "select cid from user where uuid=?";
			Integer result = qr.query(sql, uuid, new ScalarHandler<Integer>());
			if(result != null) {
				System.out.println(result);
				return result;
			}
			return null;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new Message("用户查询cid失败");
		}
	}
	/**
	 * 执行查询user表的语句
	 * 根据uuid查询user表的integral值
	 * @param uuid
	 * @return
	 * @throws Message
	 */
	public static Integer queryintegral(String uuid) throws Message {
		try {
			String sql = "select integral from user where uuid=?";
			Integer result = qr.query(sql, uuid, new ScalarHandler<Integer>());
			if(result != null) {
				System.out.println(result);
				if(result < 0)
					result = 0;
				return result;
			}
			return null;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new Message("用户查询integral失败");
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
