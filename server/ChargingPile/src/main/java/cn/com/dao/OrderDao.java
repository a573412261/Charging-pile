package cn.com.dao;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.List;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import cn.com.bean.Message;
import cn.com.bean.Order;
import cn.com.bean.User;
import cn.com.jdbc.TxQueryRunner;

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
