package cn.com.dao;

import java.math.BigDecimal;

import org.apache.commons.dbutils.handlers.ScalarHandler;

import cn.com.bean.Chargingpile;
import cn.com.bean.Message;
import cn.com.jdbc.TxQueryRunner;

public class ChargingpileDao {
	private static TxQueryRunner qr = new TxQueryRunner();
	/**
	 * 执行修改chargingpile表语句
	 * 修改数据库中chargingpile表的信息
	 * @param chargeingpile
	 */
	public static void updatestatus(Chargingpile chargingpile) throws Message{
		try {
			String sql = "update chargingpile set status=? where cid=?";
			Object params[] = {chargingpile.getStatus(),chargingpile.getCid()};
			int result = qr.update(sql,params);
			if(result>0) {
				System.out.println("操作数据库成功，影响行数：" + result);
			}
		}catch(Exception e) {
			e.printStackTrace();
			throw new Message("充电桩的状态修改失败");
		}
	}
	/**
	 * 执行查询chargingpile表语句
	 * 查询充电桩状态
	 * @param cid
	 * @return
	 * @throws Message
	 */
	public static Integer querystatus(Integer cid) throws Message{
		try {
			String sql = "select status from chargingpile where cid=? ";
			
			Integer result = qr.query(sql, cid, new ScalarHandler<Integer>());
			if(result!=null) {
				return result;
			}
			return null;
		}catch(Exception e) {
			e.printStackTrace();
			throw new Message("充电桩的状态查询失败");
		}
	}
	/**
	 * 执行查询chargingpile表语句
	 * 查询充电桩的功率
	 * @param cid
	 * @return
	 * @throws Message
	 */
	public static BigDecimal querypower(Integer cid) throws Message {
		try {
			String sql = "select power from chargingpile where cid=?"; 
			BigDecimal result = qr.query(sql,cid,new ScalarHandler<BigDecimal>());
			if(result != null) {
				System.out.println(result);
				return result;
			}
			return null;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			throw new Message("查询充电桩功率失败");
		} 
	}
	/**
	 * 执行查询chargingpile表语句
	 * 查询充电桩的收费标准
	 * @param cid
	 * @return
	 * @throws Message
	 */
	public static BigDecimal querycharge(Integer cid) throws Message {
		try {
			String sql = "select charge from chargingpile where cid=?"; 
			BigDecimal result = qr.query(sql,cid,new ScalarHandler<BigDecimal>());
			if(result != null) {
				System.out.println(result);
				return result;
			}
			return null;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			throw new Message("查询标准收费失败");
		} 
	}
}	
