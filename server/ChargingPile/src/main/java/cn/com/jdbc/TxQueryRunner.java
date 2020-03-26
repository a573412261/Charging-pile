package cn.com.jdbc;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;

public class TxQueryRunner extends QueryRunner{

	@Override
	public int[] batch(String sql, Object[][] params) throws SQLException {
		// TODO Auto-generated method stub
		Connection connection = jdbcUtils.getConnection();
		int [] result = super.batch(connection,sql, params);
		jdbcUtils.releaseConnection(connection);
		return result;
	}

	@Override
	public int execute(String sql, Object... params) throws SQLException {
		// TODO Auto-generated method stub
		Connection connection = jdbcUtils.getConnection();
		int result = super.execute(connection,sql, params);
		jdbcUtils.releaseConnection(connection);
		return result;
	}

	@Override
	public <T> List<T> execute(String sql, ResultSetHandler<T> rsh, Object... params) throws SQLException {
		// TODO Auto-generated method stub
		Connection connection = jdbcUtils.getConnection();
		List<T> result = super.execute(connection,sql,rsh ,params);
		jdbcUtils.releaseConnection(connection);
		return result;
	}

	@Override
	public <T> T insert(String sql, ResultSetHandler<T> rsh, Object... params) throws SQLException {
		// TODO Auto-generated method stub
		Connection connection = jdbcUtils.getConnection();
		T result = super.insert(connection,sql,rsh ,params);
		jdbcUtils.releaseConnection(connection);
		return result;
	}

	@Override
	public <T> T insert(String sql, ResultSetHandler<T> rsh) throws SQLException {
		// TODO Auto-generated method stub
		Connection connection = jdbcUtils.getConnection();
		T result = super.insert(connection,sql,rsh);
		jdbcUtils.releaseConnection(connection);
		return result;
	}

	@Override
	public <T> T insertBatch(String sql, ResultSetHandler<T> rsh, Object[][] params) throws SQLException {
		// TODO Auto-generated method stub
		Connection connection = jdbcUtils.getConnection();
		T result = super.insertBatch(connection,sql,rsh ,params);
		jdbcUtils.releaseConnection(connection);
		return result;
	}

	@Override
	public <T> T query(String sql, Object param, ResultSetHandler<T> rsh) throws SQLException {
		// TODO Auto-generated method stub
		Connection connection = jdbcUtils.getConnection();
		T result = super.query(connection,sql,param,rsh);
		jdbcUtils.releaseConnection(connection);
		return result;
	}

	@Override
	public <T> T query(String sql, Object[] params, ResultSetHandler<T> rsh) throws SQLException {
		// TODO Auto-generated method stub
		Connection connection = jdbcUtils.getConnection();
		T result = super.query(connection,sql,params,rsh);
		jdbcUtils.releaseConnection(connection);
		return result;
	}

	@Override
	public <T> T query(String sql, ResultSetHandler<T> rsh, Object... params) throws SQLException {
		// TODO Auto-generated method stub
		Connection connection = jdbcUtils.getConnection();
		T result = super.query(connection,sql,rsh,params);
		jdbcUtils.releaseConnection(connection);
		return result;
	}

	@Override
	public <T> T query(String sql, ResultSetHandler<T> rsh) throws SQLException {
		// TODO Auto-generated method stub
		Connection connection = jdbcUtils.getConnection();
		T result = super.query(connection,sql,rsh);
		jdbcUtils.releaseConnection(connection);
		return result;
	}

	@Override
	public int update(String sql, Object... params) throws SQLException {
		// TODO Auto-generated method stub
		Connection connection = jdbcUtils.getConnection();
		int result = super.update(connection,sql,params);
		jdbcUtils.releaseConnection(connection);
		return result;
	}

	@Override
	public int update(String sql, Object param) throws SQLException {
		// TODO Auto-generated method stub
		Connection connection = jdbcUtils.getConnection();
		int result = super.update(connection,sql,param);
		jdbcUtils.releaseConnection(connection);
		return result;
	}

	@Override
	public int update(String sql) throws SQLException {
		// TODO Auto-generated method stub
		Connection connection = jdbcUtils.getConnection();
		int result = super.update(connection,sql);
		jdbcUtils.releaseConnection(connection);
		return result;
	}

}
