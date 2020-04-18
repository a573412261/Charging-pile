package cn.com.dao;

import org.apache.commons.dbutils.handlers.BeanHandler;

import cn.com.bean.Chargingpile;
import cn.com.bean.Message;
import cn.com.jdbc.TxQueryRunner;

public class ChargingpileDao {
	private static TxQueryRunner qr = new TxQueryRunner();
	/**
	 * 执行修改chargingpile表语句
	 * 修改数据库中chargingpile表的信息
	 * 可能要加上throws message
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
	
	//获取充电桩的信息
	public static Chargingpile query(String cid) throws Message {
		// TODO Auto-generated method stub
		try {
			String sql = "select address,charge,cid,code,"
					+ "interfacetype,latitude,longitude,name,power,chargingpile.rank,status from chargingpile where cid=?";
			Object paramObject[] = {cid};
			Chargingpile Chargingpile = qr.query(sql, paramObject,new BeanHandler<Chargingpile>(Chargingpile.class));
			if(Chargingpile != null) {
				System.out.println("操作数据库成功：");
				System.out.println(Chargingpile);
				return Chargingpile;
			}
			return null;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			throw new Message("查询充电桩信息失败");
		}
	}

}	
