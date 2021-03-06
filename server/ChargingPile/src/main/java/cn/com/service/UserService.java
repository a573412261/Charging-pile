package cn.com.service;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Date;

import cn.com.bean.Chargingpile;
import cn.com.bean.Comment;
import cn.com.bean.Message;
import cn.com.bean.Order;
import cn.com.bean.Reply;
import cn.com.bean.User;
import cn.com.dao.ChargingpileDao;
import cn.com.dao.CommentDao;
import cn.com.dao.OrderDao;
import cn.com.dao.ReplyDao;
import cn.com.dao.UserDao;
import cn.com.jdbc.jdbcUtils;
import cn.com.utils.MultiplexUtils;

public class UserService {
	
	/**
	 * 将user插入到数据库的user表中
	 * @param user
	 * @throws Message 
	 */
	public String add(User user) throws Message {
		// TODO Auto-generated method stub
		return UserDao.add(user);
	}
	/**
	 * 更新user记录
	 * @param user
	 * @throws Message 
	 */
	public void update(User user) throws Message {
		// TODO Auto-generated method stub
		UserDao.update(user);
	}
	/**
	 * 通过uuid查询user记录
	 * @param uuid
	 * @throws Message
	 */
	public User queryByUuid(String uuid) throws Message{
		try {
			//开启事务
			jdbcUtils.beginTransaction();	
			//获取user对象（无订单、评价和回复信息）
			User user = UserDao.query(uuid);
			//获取user的所有订单信息
			List<Order> orders = OrderDao.getOrderByUid(user);
			//获取user的所有评价信息
			List<Comment> comments = CommentDao.getCommentByUid(user);
			//获取user的所有回复信息
			List<Reply> replies = ReplyDao.getReplyByUid(user);
			//将订单信息，评价信息，回复信息都添加到user中
			user.setOrder(MultiplexUtils.orderListToArray(orders));
			user.setComment(MultiplexUtils.commentListToArray(comments));
			user.setReply(MultiplexUtils.replyListToArray(replies));
			//提交事务
			jdbcUtils.commitTransaction();
			return user;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			try {
				//回滚事务
				jdbcUtils.rollbackTransaction();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		return null;
	}
	public BigDecimal chargingmoney(String uuid,BigDecimal money) throws Message {
		// TODO Auto-generated method stub
		UserDao.Chargingmoney(uuid, money);
		return UserDao.querymoney(uuid);
	}
	
	public void updatepassword(String uuid, String password, String newpassword) throws Message {
		// TODO Auto-generated method stub
		UserDao.updatepassword(uuid, password, newpassword);
	}
	
	public void reserve(String uuid, Integer cid, Integer sid, Chargingpile chargingpile) throws Message {
		try {
			//开启事务
			jdbcUtils.beginTransaction();		
			//给用户添加充电桩的id和时间段id
			UserDao.updatecidsid(uuid, cid, sid);	
			//修改充电桩的状态为已预约
			ChargingpileDao.updatestatus(chargingpile);
			//提交事务
			jdbcUtils.commitTransaction();		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			try {
				//回滚事务
				jdbcUtils.rollbackTransaction();	
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}
	
	public Integer deletereservedchargepileservice(String uuid, Integer changedintegral, Chargingpile chargingpile) throws Message{
		try {
			jdbcUtils.beginTransaction();
			//修改该用户下的积分值
			UserDao.updateintegral(uuid, changedintegral);
			//修改充电桩的状态为空闲状态
			ChargingpileDao.updatestatus(chargingpile);
			//修改该用户下的cid和sid为空值
			UserDao.updatecidsid(uuid, null, null);
			jdbcUtils.commitTransaction();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			try {
				jdbcUtils.rollbackTransaction();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		//返回用户当前的积分值
		return UserDao.queryintegral(uuid);
	}
	
	public String startchagingService(String uuid, Chargingpile chargingpile) throws Message {
		String orderno = null;
		try {
			jdbcUtils.beginTransaction();
			//如果充电桩为当前用户所预约的，需要将用户当前所预约的信息置空
			if(ChargingpileDao.querystatus(chargingpile.getCid()) == 2) {
				UserDao.updatecidsid(uuid, null, null);
			}
			//修改充电桩的状态为正在充电状态
			ChargingpileDao.updatestatus(chargingpile);
			//给订单表增加一条订单数据，该订单的状态为进行中
			orderno = OrderDao.add(2,uuid,chargingpile.getCid());
			jdbcUtils.commitTransaction();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			try {
				jdbcUtils.rollbackTransaction();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		//返回订单编号
		return orderno;
		
	}
	
	public Object[] stopchargingService(String order_code, Chargingpile chargingpile) throws Message{
		
		try {
			jdbcUtils.beginTransaction();
			//根据充电桩cid查询充电桩的功率
			BigDecimal power = ChargingpileDao.querypower(chargingpile.getCid());
			//根据充电桩cid查询充电桩的标准收费
			BigDecimal charge = ChargingpileDao.querycharge(chargingpile.getCid());
			//充电完成后计算出订单所需要的数据并更新
			Object back[] = OrderDao.update(order_code, power, charge, 0);
			//修改充电桩的状态为空闲状态
			ChargingpileDao.updatestatus(chargingpile);
			jdbcUtils.commitTransaction();
			return back;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			try {
				jdbcUtils.rollbackTransaction();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		return null;
		
	}
	
	public Object[] pay(String uuid, String oid) throws Message {
		try {
			//开启事务
			jdbcUtils.beginTransaction();		
			//根据oid来查询订单这次充电的费用
			BigDecimal cost =OrderDao.querycost(oid);
			//用户支付 即update balance
			UserDao.pay(uuid, cost);
			
			//根据用例图描述，根据支付费用同比例转化为积分，所以直接简单的四舍五入把费用取整。
			//后期若有更好的积分制度这里要重新写。就相当于把bigdecimal转化为integer
			Integer getintegral =cost.intValue();
			
			//更新积分
			UserDao.updateintegral(uuid, getintegral);
			//打包积分和余额
			Integer integral=UserDao.queryintegral(uuid);
			BigDecimal balance = UserDao.querymoney(uuid);
			Object[] back= {integral,balance,getintegral};
			//提交事务
			jdbcUtils.commitTransaction();	
			return back;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			try {
				//回滚事务
				jdbcUtils.rollbackTransaction();	
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		return null;
	}
	
	public Object[] comment(String text,int rank,String cid,String uuid) throws Message {
		try {
			//开启事务
			jdbcUtils.beginTransaction();		
			CommentDao.add(text,rank,cid,uuid);
			jdbcUtils.commitTransaction();	
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			try {
				//回滚事务
				jdbcUtils.rollbackTransaction();	
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		return null;
	}
	/**
	 * 通过cid查询充电桩
	 * @param cid
	 * @throws Message
	 */
	public Chargingpile queryByCid(String cid) throws Message{
		try {
			//开启事务
			jdbcUtils.beginTransaction();	
			//获取Chargingpile对象（无评价），订单消息无法查询
			Chargingpile Chargingpile = ChargingpileDao.query(cid);
			//获取Chargingpile的所有评价信息
			List<Comment> comments = CommentDao.getCommentByCid(Chargingpile);						
			//将订单信息，评价信息，回复信息都添加到user中
			Chargingpile.setComment(MultiplexUtils.commentListToArray(comments));		
			//提交事务
			jdbcUtils.commitTransaction();
			return Chargingpile;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			try {
				//回滚事务
				jdbcUtils.rollbackTransaction();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		return null;
	}
	
	/**
	 * 通过text，comid，uuid回复评论
	 * @param text，comid，uuid
	 * @throws Message
	 */	
	public Object[] reply(String text,String comid,String uuid) throws Message {
		try {
			//开启事务
			jdbcUtils.beginTransaction();		
			ReplyDao.add(text,comid,uuid);
			jdbcUtils.commitTransaction();	
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			try {
				//回滚事务
				jdbcUtils.rollbackTransaction();	
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		return null;
	}
}
