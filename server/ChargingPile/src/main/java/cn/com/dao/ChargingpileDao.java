package cn.com.dao;

import java.util.ArrayList;
import java.util.Arrays;

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
}	
