package cn.com.jdbc;

import java.sql.Connection;
import java.sql.SQLException;


import com.mchange.v2.c3p0.ComboPooledDataSource;

public class jdbcUtils {
	
	//配置文件的默认配置！要求必须给出c3p0-config.xml
	private static ComboPooledDataSource dataSource = new ComboPooledDataSource();
	
	//它是事务专用连接！
	private static ThreadLocal<Connection> tl = new ThreadLocal<Connection>();
	
	//使用连接池返回一个连接对象
	public static Connection getConnection() throws SQLException {
		Connection con = tl.get();
		//当con不等于null，说明已经调用过beginTransaction()，表示开启了事务！
		if(con!=null) return con;
		return dataSource.getConnection();
	}
	
	//返回连接池对象
	public static ComboPooledDataSource getDataSource() {
		return dataSource;
	}
	
	public static void beginTransaction() throws SQLException {
		Connection con = tl.get();
		if(con!=null) throw new SQLException("已经开启了事务，就不要重复开启了！");
		
		con = getConnection();//给con赋值，表示事务已经开始了
		con.setAutoCommit(false);
		
		tl.set(con);//把当前线程的连接保存起来！
	}
	
	public static void commitTransaction() throws SQLException {
		Connection con = tl.get();
		if(con==null) throw new SQLException("还没有开启事务，不能提交！");
		
		con.commit();
		con.close();
		
		tl.remove();//从tl中移除连接
	}
	
	public static void rollbackTransaction() throws SQLException {
		Connection con = tl.get();
		if(con==null) throw new SQLException("还没有开启事务，不能回滚！");
		
		con.rollback();
		con.close();
		
		tl.remove();//从tl中移除连接
	}
	
	public static void releaseConnection(Connection connection) throws SQLException{
		Connection con = tl.get();
		
		//如果con ==null，说明现在没有事务，那么connection一定不是事务专用的！
		if(con == null) connection.close();
		//如果con !=null，说明有事务，那么需要判断参数连接是否与con相等，若不等，说明参数连接不是事务连接
		if(con != connection) connection.close();
	}
}
