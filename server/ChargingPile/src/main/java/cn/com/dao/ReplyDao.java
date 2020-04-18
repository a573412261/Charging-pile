package cn.com.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.dbutils.handlers.BeanListHandler;

import cn.com.bean.Comment;
import cn.com.bean.Message;
import cn.com.bean.Reply;
import cn.com.bean.User;
import cn.com.jdbc.TxQueryRunner;

public class ReplyDao {
	private static TxQueryRunner qr = new TxQueryRunner();
	/**
	 * 	通过uid获取回复信息
	 * @param user
	 * @return 返回回复对象列表
	 * @throws Message
	 */
	public static List<Reply> getReplyByUid(User user) throws Message{
		// TODO Auto-generated method stub
		try {
			String sql = "select time,text,comid "
					+ "from reply where uid=?";
			Object paramsObject[] = {user.getUid()};
			List<Reply> replies = qr.query(sql, paramsObject, new BeanListHandler<Reply>(Reply.class));
			if(replies.size()>0) {
				System.out.println(replies);
				return replies;
			}
			return null;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new Message("获取回复信息出错");
		}
	}
	
	/**
	 * 执行插入reply表语句	
	 * 将此reply记录插入到数据库中
	 * @throws Message 
	 */
	public static void add(String text,String comid,String uuid) throws Message {
		// TODO Auto-generated method stub
		try {
			String sql = "insert into reply(text,comid,uid,`like`,hate) "
					+ "values(?,?,?,0,0)";
			User user =UserDao.query(uuid);
			Integer uid=user.getUid();
			Object paramObject[] = {text,comid,uid}; 
			ArrayList<Object> arrayList = new ArrayList<Object>(Arrays.asList(paramObject));
			int result = qr.update(sql,arrayList.toArray());
			if(result>0) {
				System.out.println("操作数据库成功，影响行数："+result);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			throw new Message("回复评论失败");
		}

	}
}
