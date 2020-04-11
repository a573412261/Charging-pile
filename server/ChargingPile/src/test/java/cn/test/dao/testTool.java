package cn.test.dao;


import java.util.List;
import org.junit.Test;
import cn.com.bean.Comment;
import cn.com.bean.Message;
import cn.com.bean.Order;
import cn.com.bean.Reply;
import cn.com.bean.User;
import cn.com.dao.CommentDao;
import cn.com.dao.OrderDao;
import cn.com.dao.ReplyDao;
import cn.com.dao.UserDao;
import cn.com.utils.MultiplexUtils;

public class testTool {

	@Test
	public void test() throws Message {
		//获取user对象（无订单、评价和回复信息）
		User user = UserDao.query("F994A266A9B3421E92B8630EC5C43471");
		System.out.println(user.getUid());
		//获取user的所有订单信息
		List<Order> orders = OrderDao.getOrderByUid(user);
		System.out.println("orders.length"+orders.size());
		//获取user的所有评价信息
		List<Comment> comments = CommentDao.getCommentByUid(user);
		//获取user的所有回复信息
		List<Reply> replies = ReplyDao.getReplyByUid(user);
		Order[] orderArray = MultiplexUtils.orderListToArray(orders);
		user.setOrder(orderArray);
		System.out.println(user);
	}

}
