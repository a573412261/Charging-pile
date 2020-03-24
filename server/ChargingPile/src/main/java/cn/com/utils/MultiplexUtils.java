package cn.com.utils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;


public class MultiplexUtils {
	
	/**
	 * 设置字符集并获取参数，将参数转化为javabean返回
	 * @param <T>
	 * @param request
	 * @param response
	 * @param clazz javabean的类型
	 * @return 返回javabean对象
	 * @throws IOException 
	 */
	public static <T> T getparams(HttpServletRequest request,HttpServletResponse response,Class<T> clazz) throws IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		String paramJson = IOUtils.toString(request.getInputStream(), "UTF-8");
        Map parseObject = JSON.parseObject(paramJson, Map.class);
		return CommonUtils.toBean(parseObject,clazz);
	}
}
