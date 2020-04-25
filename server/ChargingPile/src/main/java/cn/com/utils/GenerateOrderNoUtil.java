
package cn.com.utils;
import java.text.SimpleDateFormat;
import java.util.Date;

public class GenerateOrderNoUtil {
	private static final SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssSSS");

    /**
     * 生成订单号
     *
     * @param pre 订单号前缀
     * @return
     */
    public static String gens(String pre, Long museId) {
        //生成
        String orderNo = pre + sdf.format(new Date()) + museId + (1 + (int) (Math.random() * 10000)) ;
        return orderNo;
    }

    /**
     * 生成订单号
     *
     * @param pre 订单号前缀
     * @return
     */
    public static String gen(String pre, Long museId) {
        //生成
        String orderNo = pre + ((int)((Math.random()*9+1)*10000)) + museId + (System.currentTimeMillis() / 1000);
        return orderNo;
    }

}
