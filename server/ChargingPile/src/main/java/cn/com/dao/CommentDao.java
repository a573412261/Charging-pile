package cn.com.dao;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import cn.com.bean.Comment;
import cn.com.bean.Message;
import cn.com.bean.Order;
import cn.com.bean.User;
import cn.com.jdbc.TxQueryRunner;
import cn.com.utils.CommonUtils;

public class CommentDao {
	private static TxQueryRunner qr = new TxQueryRunner();
	/**
	 * 	通过uid获取评价信息
	 * @param user
	 * @return 返回评价信息列表
	 * @throws Message
	 */
	public static List<Comment> getCommentByUid(User user) throws Message{
		// TODO Auto-generated method stub
		try {
			String sql = "select time,text,comment.rank,cid "
					+ "from comment where uid=?";
			Object paramsObject[] = {user.getUid()};
			List<Comment> comments = qr.query(sql, paramsObject, new BeanListHandler<Comment>(Comment.class));
			if(comments.size()>0) {
				System.out.println(comments);
				return comments;
			}
			return null;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new Message("获取评价信息出错");
		}
	}
	
	/**
	 * 执行插入comment表语句
	 * @param comment	将此comment记录插入到数据库中
	 * @throws Message 
	 */
	public static void add(String text,int rank,String cid,String uuid) throws Message {
		// TODO Auto-generated method stub
		try {
			String sql = "insert into comment(text,rank,cid,uid,`like`,hate) "
					+ "values(?,?,?,?,0,0)";
			User user =UserDao.query(uuid);
			Integer uid=user.getUid();
			Object paramObject[] = {text,rank,cid,uid}; 
			ArrayList<Object> arrayList = new ArrayList<Object>(Arrays.asList(paramObject));
			int result = qr.update(sql,arrayList.toArray());
			if(result>0) {
				System.out.println("操作数据库成功，影响行数："+result);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			throw new Message("评论失败");
		}
		
	}
}
