package cn.com.dao;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.handlers.BeanListHandler;

import cn.com.bean.Comment;
import cn.com.bean.Message;
import cn.com.bean.Order;
import cn.com.bean.User;
import cn.com.jdbc.TxQueryRunner;

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
	
	
}
