package cn.com.utils;

import java.io.IOException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.io.IOUtils;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import cn.com.bean.Comment;
import cn.com.bean.Message;
import cn.com.bean.Order;
import cn.com.bean.Reply;

/**
 * JSONObject：解析Json对象，获取对象中的值，通常是使用类中的get()方法
 * JSONArray：JSON对象数组，通常是通过迭代器取得其中的JSONObject，再利用JSONObeject的get()方法进行取值。
 * JSON：主要是实现json对象，json对象数组，javabean对象，json字符串之间的相互转化。 转换之后取值还是按各自的方法进行。
 * //json字符串-简单对象型,加\转义 private static final String JSON_OBJ_STR = "{
 * \"studentName\":\"lily\", \"studentAge\":12 }"; //json字符串-数组类型 private static
 * final String JSON_ARRAY_STR = "[{ \"studentName\":\"lily\", \"studentAge\":12
 * }, { \"studentName\":\"lucy\", \"studentAge\":15 }]"; ​ //复杂格式json字符串 private
 * static final String COMPLEX_JSON_STR = "{ \"teacherName\":\"crystall\",
 * \"teacherAge\":27, \"course\":{ \"courseName\":\"english\", \"code\":1270 },
 * \"students\":[{ \"studentName\":\"lily\", \"studentAge\":12 }, {
 * \"studentName\":\"lucy\", \"studentAge\":15 }]}"; ​ //
 * json字符串与JSONObject之间的转换
 * 
 * @Test public void JsonStrToJSONObject(){
 * 
 *       JSONObject jsonObject = JSON.parseObject(JSON_OBJ_STR);
 *       System.out.println("StudentName: " +
 *       jsonObject.getString("studentName") + "," + "StudentAge: " +
 *       jsonObject.getInteger("studentAge")); ​ }
 *       将JSONObject转换为JSON字符串,用JSON.toJSONString()方法即可将JSON字符串转化为JSON对象
 * 
 * @Test public void JSONObjectToJSONString(){ JSONObject jsonObject =
 *       JSON.parseObject(JSON_OBJ_STR); String s =
 *       JSON.toJSONString(jsonObject); System.out.println(s); }
 *       将JSON字符串数组转化为JSONArray，通过JSON的parseArray()方法 JSONArray jsonArray =
 *       JSON.parseArray(JSON_ARRAY_STR); 遍历 Iterator<Object> iterator =
 *       jsonArray.iterator(); while (iterator.hasNext()){ JSONObject jsonObject
 *       = (JSONObject) iterator.next(); System.out.println("studentName: " +
 *       jsonObject.getString("studentName") + ",StudentAge: " +
 *       jsonObject.getInteger("studentAge")); } JSONArray到json字符串-数组类型的转换
 * @Test public void JSONArrayToJSONString(){ JSONArray jsonArray =
 *       JSON.parseArray(JSON_ARRAY_STR); String s =
 *       JSON.toJSONString(jsonArray); System.out.println(s); }
 *       将复杂JSON格式字符串转换为JSONObject,也是通过JSON.parseObject()，可以取其中的部分
 * @Test public void JSONStringTOJSONObject(){ JSONObject jsonObject =
 *       JSON.parseObject(COMPLEX_JSON_STR); // 获取简单对象 String teacherName =
 *       jsonObject.getString("teacherName"); Integer teacherAge =
 *       jsonObject.getInteger("teacherAge"); System.out.println("teacherName: "
 *       + teacherName + ",teacherAge " + teacherAge); // 获取JSONObject对象
 *       JSONObject course = jsonObject.getJSONObject("course"); //
 *       获取JSONObject中的数据 String courseName = course.getString("courseName");
 *       Integer code = course.getInteger("code");
 *       System.out.println("courseName: " + courseName + " code: " + code); //
 *       获取JSONArray对象 JSONArray students = jsonObject.getJSONArray("students");
 *       // 获取JSONArray的中的数据 Iterator<Object> iterator = students.iterator();
 *       while (iterator.hasNext()){ JSONObject jsonObject1 = (JSONObject)
 *       iterator.next(); System.out.println("studentName: " +
 *       jsonObject1.getString("studentName") + ",StudentAge: " +
 *       jsonObject1.getInteger("studentAge")); } } 复杂JSONObject到json字符串的转换
 * @Test public void JSONObjectTOJSON(){ JSONObject jsonObject =
 *       JSON.parseObject(COMPLEX_JSON_STR); String s =
 *       JSON.toJSONString(jsonObject); System.out.println(s); }
 * 
 * 
 * 
 * 
 * 
 *       JavaBean转换为Json字符串,也是通过JSON的toJSONString，不管是JSONObject、JSONArray还是JavaBean转为为JSON字符串都是通过JSON的toJSONString方法
 * @Test public void JavaBeanToJsonString(){ Student lily = new Student("lily",
 *       12); String s = JSON.toJSONString(lily); System.out.println(s); }
 *       JavaBean_List到json字符串-数组类型的转换,直接调用JSON.toJSONString()方法即可
 * @Test ​ public void JavaBeanListToJSONStr(){ ​ Student student = new
 *       Student("lily", 12); ​ Student student1 = new Student("lucy", 13); ​
 *       List<Student> students = new ArrayList<Student>(); ​
 *       students.add(student); ​ students.add(student1); ​ String s =
 *       JSON.toJSONString(students); ​ System.out.println(s); ​ }
 *       复杂JavaBean_obj到json格式字符串的转换
 * @Test public void JavaBeanToComplexJSONStr(){ Teacher teacher =
 *       JSON.parseObject(COMPLEX_JSON_STR, Teacher.class); String s =
 *       JSON.toJSONString(teacher); System.out.println(s); } public class
 *       Teacher { private String teacherName; private int teacherAge; private
 *       Course course; private List<Student> students; ​ public Teacher() { } }
 * @author ASUS
 *	fastjson学习链接：https://zhuanlan.zhihu.com/p/62763428
 */
public class MultiplexUtils {

	private static final String METHOD_GET = "GET";
	private static final String METHOD_POST = "POST";

	/**
	 * 设置字符集并获取参数，将参数转化为javabean返回 条件：数据名称与表名称一致，并且都是类似"key":"value"的单一数据的集合
	 * 
	 * @param <T>
	 * @param request
	 * @param response
	 * @param clazz    javabean的类型
	 * @return 返回javabean对象
	 * @throws IOException
	 */
	public static <T> T getparams(HttpServletRequest request, HttpServletResponse response, Class<T> clazz)
			throws Message {
		// TODO Auto-generated method stub
		try {
			request.setCharacterEncoding("UTF-8");
			response.setContentType("text/html; charset=UTF-8");
			// 获取内容格式
			String contentType = request.getContentType();
			if (contentType != null && !contentType.equals(""))
				contentType = contentType.split(";")[0];
			// json格式 (post方式):json格式需要从request的输入流中解析获取
			if (METHOD_POST.equals(request.getMethod()))
				if ("application/json".equalsIgnoreCase(contentType)) {
					String paramJson = IOUtils.toString(request.getInputStream(), "UTF-8");
					Map parseObject = JSON.parseObject(paramJson, Map.class);
					return CommonUtils.toBean(parseObject, clazz);
				} else {
					System.out.println("提交的数据不是application/json格式:" + contentType);
				}
			// json格式 (get方式)
			else if (METHOD_GET.equals(request.getMethod()))
				if ("application/json".equalsIgnoreCase(contentType)) {
					Enumeration<String> set = request.getParameterNames();
					Map<String, Object> map = new HashMap<String, Object>();
					while (set.hasMoreElements()) {
						String key = (String) set.nextElement();
						Object value = request.getParameter(key);
						map.put(key, value);
					}
					return CommonUtils.toBean(map, clazz);
				} else {
					System.out.println("提交的数据不是application/json格式:" + contentType);
				}
			return null;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			throw new Message("数据发送失败");
		}

	}

	/**
	 * 设置字符集并获取参数，将参数转化为jsonobject返回 条件：jsonobject都是类似"key":"value"的单一数据的集合
	 * 
	 * @param request
	 * @param response
	 * @return 返回JSONObject对象
	 * @throws IOException
	 */
	public static JSONObject getUandM(HttpServletRequest request, HttpServletResponse response) throws Message {
		// TODO Auto-generated method stub
		try {
			request.setCharacterEncoding("UTF-8");
			response.setContentType("text/html; charset=UTF-8");
			// 获取内容格式
			String contentType = request.getContentType();
			if (contentType != null && !contentType.equals(""))
				contentType = contentType.split(";")[0];
			// json格式 (post方式):json格式需要从request的输入流中解析获取
			if (METHOD_POST.equals(request.getMethod()))
				if ("application/json".equalsIgnoreCase(contentType)) {
					String paramJson = IOUtils.toString(request.getInputStream(), "UTF-8");
					JSONObject jsonobject = JSON.parseObject(paramJson);
					return jsonobject;
				} else {
					System.out.println("提交的数据不是application/json格式:" + contentType);
				}
			// json格式 (get方式)
			else if (METHOD_GET.equals(request.getMethod()))
				if ("application/json".equalsIgnoreCase(contentType)) {
					Enumeration<String> set = request.getParameterNames();
					JSONObject jsonObject = new JSONObject();
					while (set.hasMoreElements()) {
						String key = (String) set.nextElement();
						Object value = request.getParameter(key);
						jsonObject.put(key, value);
					}
					return jsonObject;
				} else {
					System.out.println("提交的数据不是application/json格式:" + contentType);
				}
			return null;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			throw new Message("数据发送失败");
		}

	}

	/**
	 * 将java对象转化成JSONObject
	 * 
	 * @param object
	 * @return
	 */
	public static JSONObject JavaBeanToJSONObject(Object object) {
		String s = JSON.toJSONString(object);
		return JSON.parseObject(s);
	}

	/**
	 * 将javaList转化成JSONArray
	 * 
	 * @param object
	 * @return
	 */
	public static JSONArray JavaBeanToJSONArray(List list) {
		String s = JSON.toJSONString(list);
		return JSON.parseArray(s);
	}

	
	/**
	 * 	将java.util.Date转化成java.sql.Date
	 * @param utilDate
	 * @return
	 */
	public static java.sql.Date switchDate(java.util.Date utilDate) {
		long l = utilDate.getTime();
		java.sql.Date sqlDate = new java.sql.Date(l);
		return sqlDate;
	}
	
//	/**
//	 * 	将List转化成Array
//	 * @param <T>
//	 * @param list
//	 * @return 返回数组
//	 */
//	public static <T> T[] listToArray(List<T> list) {
//		Object[] array = null;
//		if(list==null||list.size()==0) {
//			return null;
//		}
//		else {
//			array = new Object[list.size()];
//			for(int i=0;i<list.size();i++) {
//				array[i] = list.get(i);
//			}
//			System.out.println("hhh");
//			System.out.println(array);
//			return (T[])array;
//		}
//	}
	
	/**
	 * 	将List<Order>转化成Array
	 * @param <T>
	 * @param list
	 * @return 返回数组
	 */
	public static Order[] orderListToArray(List<Order> list) {
		Order[] array = null;
		if(list==null||list.size()==0) {
			return null;
		}
		else {
			array = new Order[list.size()];
			for(int i=0;i<list.size();i++) {
				array[i] = list.get(i);
			}
			return array;
		}
	}
	/**
	 * 	将List<Comment>转化成Array
	 * @param <T>
	 * @param list
	 * @return 返回数组
	 */
	public static Comment[] commentListToArray(List<Comment> list) {
		Comment[] array = null;
		if(list==null||list.size()==0) {
			return null;
		}
		else {
			array = new Comment[list.size()];
			for(int i=0;i<list.size();i++) {
				array[i] = list.get(i);
			}
			return array;
		}
	}
	/**
	 * 	将List<Reply>转化成Array
	 * @param <T>
	 * @param list
	 * @return 返回数组
	 */
	public static Reply[] replyListToArray(List<Reply> list) {
		Reply[] array = null;
		if(list==null||list.size()==0) {
			return null;
		}
		else {
			array = new Reply[list.size()];
			for(int i=0;i<list.size();i++) {
				array[i] = list.get(i);
			}
			return array;
		}
	}
}
