package cn.com.dao;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import cn.com.bean.Message;
import cn.com.bean.Order;
import cn.com.bean.User;
import cn.com.jdbc.TxQueryRunner;
import cn.com.utils.GenerateOrderNoUtil;

public class OrderDao {
	private static TxQueryRunner qr = new TxQueryRunner();
	/**
	 * 	通过uid查询返回订单列表
	 * @param user
	 * @throws Message
	 */
	public static List<Order> getOrderByUid(User user) throws Message{
		// TODO Auto-generated method stub
		try {
			String sql = "select order_code,start_time,end_time,cost,capacity,status,cid "
					+ "from `order` where uid=?";
			Object paramsObject[] = {user.getUid()};
			List<Order> orders = qr.query(sql, paramsObject, new BeanListHandler<Order>(Order.class));
			if(orders.size()>0) {
				System.out.println(orders);
				return orders;
			}
			return null;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new Message("获取订单信息出错");
		}
	}
	
	/**
	 * 执行插入order语句
	 * 插入部分记录到order表里
	 * @param status
	 * @param uid
	 * @param cid
	 * @return
	 * @throws Message
	 */
	public static String add(Integer status, String uuid, Integer cid) throws Message{
		String sql = "insert into `order`(order_code,start_time,status,uid,cid) values(?,?,?,?,?)";
		//获取当前时间
		Date date = new Date();
		Timestamp dateString = new Timestamp(date.getTime());
		//获取订单编号
		String Orderno = GenerateOrderNoUtil.gens("CP", 53L);
		//根据uuid获取uid，用来存到订单表中
		Integer uid = UserDao.queryuid(uuid);
		Object paramsObject[] = {Orderno,dateString,status,uid,cid};
		int result = 0;
		try {
			ArrayList<Object> arrayList = new ArrayList<Object>(Arrays.asList(paramsObject));
			result = qr.update(sql,arrayList.toArray());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new Message("新增订单信息出错");
		}
		if(result>0) {
			System.out.println("操作数据库成功，影响行数："+result);
			return Orderno;
		}
		return null;
	}
	
	
	/**
	 * 执行更新order语句
	 * 新增完成充电时产生的一些记录
	 * @param order_code
	 * @param power
	 * @param charge
	 * @param status
	 * @return 
	 */
	public static Object[] update(String order_code,BigDecimal power,BigDecimal charge,Integer status) {
		String query_sql = "select start_time from `order` where order_code=?";
		String sql = "update `order` set end_time=?,cost=?,capacity=?,status=? where order_code=?";
		//获取当前时间 status=?      
		Date date = new Date();
		Timestamp end_time = new Timestamp(date.getTime());
		//Object[] back = null;
		try {
			Timestamp start_time = qr.query(query_sql, order_code, new ScalarHandler<Timestamp>());
			//计算出所花的时间
			Long t1 = end_time.getTime();
			Long t2 = start_time.getTime();
			int hours=(int) ((t1 - t2)/(1000*60*60));
	        int minutes=(int) (((t1 - t2)/1000-hours*(60*60))/60);
	        int second=(int) ((t1 - t2)/1000-hours*(60*60)-minutes*60);
	        String time_display = hours+":"+minutes+":"+second;
	        BigDecimal time_length = BigDecimal.valueOf((double)hours+(double)(minutes/60)+(double)(second/3600));
	        //通过收费标准和时长计算出充电费用
	        BigDecimal cost = charge.multiply(time_length);
	        //通过充电桩功率和时长计算出充电电量
	        BigDecimal capacity = power.multiply(time_length);
	        Object paramsObject[] = {end_time,cost, capacity, status, order_code};
	        ArrayList<Object> arrayList = new ArrayList<Object>(Arrays.asList(paramsObject));
	        //执行更新语句
	        int result = qr.update(sql,arrayList.toArray());
	        if(result>0) {
				System.out.println("操作数据库成功，影响行数："+result);
			}
	        Object back[] = {time_display, capacity, cost};
			return back;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public static BigDecimal querycost(String oid) throws Message{
		// TODO Auto-generated method stub
		try {
			String sql = "select cost from `order` where oid=?";
			BigDecimal result = qr.query(sql, oid, new ScalarHandler<BigDecimal>());
			if(result!=null) {
				System.out.println(result);
				return result;
			}
			return null;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new Message("查询失败");
		}
	}
}
