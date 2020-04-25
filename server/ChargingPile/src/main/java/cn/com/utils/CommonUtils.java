package cn.com.utils;

import org.apache.commons.beanutils.BeanUtils;

import java.util.Map;
import java.util.UUID;

public class CommonUtils {
	/**
	 * 该方法是返回一个UUID的32位大写字符串
	 * 
	 * @return 返回UUID32为大写字符串
	 */
	public static String uuid() {
		return UUID.randomUUID().toString().replace("-", "").toUpperCase();
	}

	/**
	 * 该方法致力于将map转换成为指定类型的javaBean对象
	 * 
	 * @param map   map集合，其中保存的是属性和值
	 * @param clazz
	 * @param <T>
	 * @return
	 */
	public static <T> T toBean(Map map, Class<T> clazz) {
		try {
			/**
			 * 创建指定类型的javaBean对象
			 */
			T bean = clazz.newInstance();
			/**
			 * 把参数封装到javaBean中
			 */
			BeanUtils.populate(bean, map);
			/**
			 * 返回javaBean对象
			 */
			return bean;

		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	
	
	

}
