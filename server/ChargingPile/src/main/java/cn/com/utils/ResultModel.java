package cn.com.utils;
import com.alibaba.fastjson.JSONObject;

public class ResultModel extends JSONObject{
	private static final long serialVersionUID = 1L;
	
	public  ResultModel()
	{
		put("error_code", 0);
		put("msg","success");
	}
	
	public static ResultModel error()
	{
		ResultModel r=new ResultModel();
		r.put("error_code",500);
		r.put("msg","please contact administrator!");
		return r;
	}
	
	public static ResultModel error(String msg)
	{
		ResultModel r=new ResultModel();
		r.put("error_code",500);
		r.put("msg",msg);
		return r;
	}
	
	public static ResultModel error(int code,String msg)
	{
		ResultModel r=new ResultModel();
		r.put("error_code",code);
		r.put("msg",msg);
		return r;
	}
	
	public static ResultModel success()
	{
		ResultModel r=new ResultModel();
		return r;
	}
	
	public static ResultModel success(String msg)
	{
		ResultModel r=new ResultModel();
		r.put("msg",msg);
		return r;
	}
	
	public static ResultModel success(JSONObject data)
	{
		ResultModel r=new ResultModel();
		r.put("data",data);
		return r;
	}
	
	@Override
	public ResultModel put(String key, Object value){
        super.put(key,value);
        return this;
    }

}
