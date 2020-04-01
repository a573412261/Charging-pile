package cn.com.utils;

import java.io.IOException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.io.IOUtils;
import com.alibaba.fastjson.JSON;

import cn.com.bean.Message;


public class MultiplexUtils {
	

	private static final String METHOD_GET = "GET";
	private static final String METHOD_POST = "POST";
	/**
	 * 设置字符集并获取参数，将参数转化为javabean返回
	 * 条件：数据名称与表名称一致，并且都是类似"key":"value"的单一数据的集合
	 * @param <T>
	 * @param request
	 * @param response
	 * @param clazz javabean的类型
	 * @return 返回javabean对象
	 * @throws IOException 
	 */
	public static <T> T getparams(HttpServletRequest request,HttpServletResponse response,Class<T> clazz) throws Message {
		// TODO Auto-generated method stub
		try {
			request.setCharacterEncoding("UTF-8");
			response.setContentType("text/html; charset=UTF-8");
			// 获取内容格式
	        String contentType = request.getContentType();
	        if (contentType != null && !contentType.equals(""))
	            contentType = contentType.split(";")[0];
	        // json格式 (post方式):json格式需要从request的输入流中解析获取
	        if(METHOD_POST.equals(request.getMethod()))
				if("application/json".equalsIgnoreCase(contentType)) {
					String paramJson = IOUtils.toString(request.getInputStream(), "UTF-8");
			        Map parseObject = JSON.parseObject(paramJson, Map.class);
					return CommonUtils.toBean(parseObject,clazz);
				}else {
					System.out.println("提交的数据不是application/json格式:"+contentType);
				}
	        // json格式 (get方式)
	        else if(METHOD_GET.equals(request.getMethod()))
	        	if("application/json".equalsIgnoreCase(contentType)) {
	        		Enumeration<String> set = request.getParameterNames();
	        		Map<String, Object> map = new HashMap<String, Object>();
	        		while (set.hasMoreElements()) {
						String key = (String) set.nextElement();
						Object value = request.getParameter(key);
						map.put(key, value);
					}
	        		return CommonUtils.toBean(map,clazz);
	        	}else {
					System.out.println("提交的数据不是application/json格式:"+contentType);
				}
	        return null;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			throw new Message("数据发送失败");
		}
		
	}

	
}
