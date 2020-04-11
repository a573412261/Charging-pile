package cn.com.service;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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
}
