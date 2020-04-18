package cn.com.servlet;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import com.alibaba.fastjson.JSONObject;
import com.mysql.cj.ParseInfo;

import cn.com.bean.Chargingpile;
import cn.com.bean.Message;
import cn.com.bean.Schedule;
import cn.com.bean.User;
import cn.com.dao.UserDao;
import cn.com.service.UserService;
import cn.com.utils.BaseServlet;
import cn.com.utils.CommonUtils;
import cn.com.utils.MultiplexUtils;

@WebServlet("/user")
public class UserServlet extends BaseServlet {
	private UserService userService = new UserService();
	
	private final String SUCCESS = "1";		//操作成功确认号（返回给客户端）
	/**
	 * 场景：上传图片到服务器
	 * 输入：图片，可能还有其他数据
	 * 输出：确认码
	 * 数据库：xxx表中增加一条记录
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	public void insert(HttpServletRequest request, HttpServletResponse response) throws IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		/*
		 * 1.把表单数据封装到xxx对象中
		 */
		// 创建工厂
		DiskFileItemFactory factory = new DiskFileItemFactory();
		// 得到解析器
		ServletFileUpload sfu = new ServletFileUpload(factory);
		// 得到解析器去解析request对象List<FileItem>
		try {
			List<FileItem> fileItemList = sfu.parseRequest(request);
			/*
			 * 把fileItemList中的数据封装到xxx对象中 > 把所有的普通表单字段数据先封装到Map中 > 再把Map中的数据封装到xxx对象中
			 */
			Map<String, String> map = new HashMap<String, String>();
			int index = 0, x = 0;
			for (FileItem fileItem : fileItemList) {
				if (fileItem.isFormField()) {
					map.put(fileItem.getFieldName(), fileItem.getString("UTF-8"));
				} else {
					index = x;
				}
				x++;
			}
			User user = CommonUtils.toBean(map, User.class);
			/*
			 * 2.保存上传的文件 > 保存的目录 > 保存的文件名称
			 */
			// 得到保存的目录
			String savepath = this.getServletContext().getRealPath("/image");
			// 得到文件名称：给原来文件名称添加uuid前缀！避免文件名冲突
			String filename = CommonUtils.uuid() + "_" + fileItemList.get(index).getName();
			// 使用目录和文件名称创建目标文件
			File destFile = new File(savepath, filename);
			// 保存上传文件到目标文件位置
			fileItemList.get(index).write(destFile);
			/*
			 * 3.设置xxx对象的imageaddress,即把图片的路径设置给xxx的imageaddress
			 */
			//user.setImageaddress("image/" + filename);
			/*
			 * 4.使用xxxService完成保存
			 */
			//userService.add(user);
		} catch (Exception e) {
		}
	}

	/**
	 * 场景：加入新用户
	 * 输入：用户信息（均可选）
	 * 输出：
	 * 	成功：确认码，uuid
	 * 	失败：失败原因
	 * 数据库：user表中增加一条记录
	 * @param request
	 * @param response
	 * @throws IOException 
	 * @throws Exception
	 */
	public void insertuser(HttpServletRequest request, HttpServletResponse response) throws IOException{
		try {
			//获取数据对象user
			User user = MultiplexUtils.getparams(request, response, User.class);
			//将user插入数据库
			String uuid = userService.add(user);
			//操作成功，返回 {"result":"1"}
			JSONObject jsonObject = new Message(SUCCESS).getJsonMessage();
			jsonObject.put("uuid", uuid);
			response.getWriter().print(jsonObject);
		} catch (Message e) {
			// 异常打印信息 {"result":"xxx"}
			response.getWriter().print(e.getJsonMessage());
		}
	}
	
	/**
	 * 场景：修改用户信息
	 * 输入：uuid,username,cartype,carnumber,address必填
	 * 输出：
	 * 	成功：确认码
	 * 	失败：失败原因
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	public void updateuser(HttpServletRequest request, HttpServletResponse response) throws Exception{
		try {
			//获取数据对象user
			User user = MultiplexUtils.getparams(request, response, User.class);
			//处理sid和cid
//			String cidString,sidString;
//			cidString = request.getParameter("cid");
//			sidString = request.getParameter("sid");
//			if(cidString!=null) {
//				int cid = Integer.parseInt(cidString);
//				Chargingpile chargingpile = new Chargingpile();
//				chargingpile.setCid(cid);
//				user.setChargingpile(chargingpile);
//				System.out.println("获取到cid参数了！！！");
//			}
//			if(sidString!=null) {
//				int sid = Integer.parseInt(sidString);
//				Schedule schedule = new Schedule();
//				schedule.setSid(sid);
//				user.setSchedule(schedule);
//				System.out.println("获取到sid参数了！！！");
//			}
			//更新数据库中user的信息
			userService.update(user);
			//操作成功，返回 {"result":"1"}
			response.getWriter().print(new Message(SUCCESS).getJsonMessage());
		} catch (Message e) {
			// 异常打印信息 {"result":"xxx"}
			response.getWriter().print(e.getJsonMessage());
		}
	}
	
	/**
	 * 场景：用户充值
	 * 输入：uuid，充值的金额money
	 * 输出：
	 * 	成功：确认码，充值后的余额balance
	 * 	失败：失败原因
	 * 数据库：user表中增加一条记录
	 * @param request
	 * @param response
	 * @throws IOException 
	 * @throws Exception
	 */
	public void chargingmoney(HttpServletRequest request, HttpServletResponse response) throws Exception{
		try {
			//获取数据对象object对象
			JSONObject jsonobject = MultiplexUtils.getUandM(request, response);
			String uuid=jsonobject.getString("uuid");
			BigDecimal money=jsonobject.getBigDecimal("money");
			//更新数据库中user的余额并返回余额
			BigDecimal balance=userService.chargingmoney(uuid, money);
			//返回操作成功前端余额
			JSONObject jsonObject1 = new Message(SUCCESS).getJsonMessage();
			jsonObject1.put("balance", balance);
			response.getWriter().print(jsonObject1);
		} catch (Message e) {
			// 异常打印信息 {"result":"xxx"}
			response.getWriter().print(e.getJsonMessage());
		}
	}
	
	/**
	 * 场景：修改用户密码
	 * 输入：uuid、原密码、新密码必选
	 * 输出：
	 * 	成功：确认码
	 * 	失败：失败原因
	 * @param request
	 * @param response
	 * @throws Exception
	 */	
	public void updatepassworduser(HttpServletRequest request, HttpServletResponse response) throws Exception{
		try {
			//获取数据对象		
			JSONObject jsonobject = MultiplexUtils.getUandM(request, response);
			String newpassword = jsonobject.getString("newpassword");
			String password = jsonobject.getString("password");
			String uuid = jsonobject.getString("uuid");
			if(newpassword!=null && password!=null && uuid!=null) {
				//更新数据库中user的密码
				userService.updatepassword(uuid, password, newpassword);
				//操作成功，返回 {"result":"1"}
				response.getWriter().print(new Message(SUCCESS).getJsonMessage());				
			}
			else {
				//如果为空，打印信息
				System.out.println (" 没有传输newpassword /password /uuid！   ");
			}

		} catch (Message e) {
			// 异常打印信息 {"result":"xxx"}
			response.getWriter().print(e.getJsonMessage());
		}
	}
	/**
	 * 场景：用户预约某个时间段的充电桩
	 * 输入：uuid、cid、sid
	 * 输出：
	 * 	成功：确认码
	 * 	失败：失败原因
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	public void reservechargepile(HttpServletRequest request, HttpServletResponse response) throws Exception{
		try {
			//获取数据对象
			JSONObject jsonobject = MultiplexUtils.getUandM(request, response);
			String cidString = jsonobject.getString("cid");
			String sidString = jsonobject.getString("sid");
			String uuid = jsonobject.getString("uuid");
			if(cidString != null && sidString != null) {
				Integer cid = Integer.parseInt(cidString);
				Integer sid = Integer.parseInt(sidString);
				Chargingpile chargingpile = new Chargingpile();
				chargingpile.setCid(cid);
				chargingpile.setStatus(2);
				//修改数据库user表和chargingpile表的内容
				userService.reserve(uuid, cid, sid,chargingpile);
				//操作成功，返回 {"result":"1"}
				response.getWriter().print(new Message(SUCCESS).getJsonMessage());
			}
		}catch(Message e) {
			response.getWriter().print(e.getJsonMessage());
		}
	}
	/**
	 * 场景：用户删除已经预约成功的充电桩
	 * 输入：uuid、changedintegral
	 * 输出：
	 * 	成功：确认码、扣除后的积分
	 * 	失败：失败原因
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	public void deletereservedchargepile(HttpServletRequest request, HttpServletResponse response) throws Exception{
		try {
			JSONObject jsonobject = MultiplexUtils.getUandM(request, response);
			String uuid = jsonobject.getString("uuid");
			//获取要扣除的积分值
			String changedintegralString = jsonobject.getString("deductedintegral");
			//String类型的值转为Integer类型，同时转为负数
			Integer changedintegral = -(Integer.parseInt(changedintegralString));
			//查询删除预约的充电桩的cid值
			Integer cid = UserDao.querycid(uuid);
			Chargingpile chargingpile = new Chargingpile();
			chargingpile.setCid(cid);
			//修改充电桩为空闲状态
			chargingpile.setStatus(0);
			Integer integral = userService.deletereservedchargepileservice(uuid, changedintegral, chargingpile);
			response.getWriter().print(new Message(SUCCESS).getJsonMessage());
			jsonobject.clear(); 
			//返回前端积分值
			jsonobject.put("integral", integral);
			response.getWriter().print(jsonobject);
			
		}catch(Message e) {
			response.getWriter().print(e.getJsonMessage());
		}
	}
	/**
	 * 场景：通过uuid拉取用户信息
	 * 输入：uuid
	 * 输出：
	 * 	成功：返回用户信息
	 * 	失败：失败原因
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	public void getUserinfoByUuid(HttpServletRequest request, HttpServletResponse response) throws Exception{
		// TODO Auto-generated method stub
		try {
			//获取参数uuid
			JSONObject jsonobject = MultiplexUtils.getUandM(request, response);
			String uuid = jsonobject.getString("uuid");
			//获取用户对象
			User user = userService.queryByUuid(uuid);
			//返回成功信息及用户对象
			JSONObject jsonObject = new Message(SUCCESS).getJsonMessage();
			jsonObject.put("userInfo",MultiplexUtils.JavaBeanToJSONObject(user));
			response.getWriter().print(jsonObject);
		}catch(Message e) {
			response.getWriter().print(e.getJsonMessage());
		}
	}
	
	/**
	 * 场景：用户支付
	 * 输入：uuid，订单号码oid
	 * 输出：
	 * 	成功：确认码，充值后的余额balance，奖励后的积分
	 * 	失败：失败原因
	 * 数据库：user表中更新一条记录
	 * @param request
	 * @param response
	 * @throws IOException 
	 * @throws Exception
	 */
	public void pay(HttpServletRequest request, HttpServletResponse response) throws Exception{
		try {
			//获取数据对象object对象
			JSONObject jsonobject = MultiplexUtils.getUandM(request, response);
			
			//从前端获取用户、订单对象
			String uuid=jsonobject.getString("uuid");
			String oid=jsonobject.getString("oid");
			
			//进行支付
			Object[] back=userService.pay(uuid, oid);
			
			//返回前端操作成功信息及 得到的积分 支付后的余额 以及更新后的积分
			JSONObject jsonObject1 = new Message(SUCCESS).getJsonMessage();
			jsonObject1.put("integral", back[0]);
			jsonObject1.put("balance",back[1]);
			jsonObject1.put("getintegral",back[2]);
			response.getWriter().print(jsonObject1);
		} catch (Message e) {
			// 异常打印信息 {"result":"xxx"}
			response.getWriter().print(e.getJsonMessage());
		}
	}
	
	/**
	 * 场景：用户对充电桩进行评论
	 * 输入：用户标识码uuid,充电桩码cid,评论内容text,评价等级rank;
	 * 输出：
	 * 	成功：确认码
	 * 	失败：失败原因
	 * 数据库：comment表中增加一条记录
	 * @param request
	 * @param response
	 * @throws IOException 
	 * @throws Exception
	 */
	public void comment(HttpServletRequest request, HttpServletResponse response) throws Exception{
		try {
			//获取数据对象object对象
			JSONObject jsonobject = MultiplexUtils.getUandM(request, response);
			
			//从前端获取用户、充电桩对象以及评论内容与等级
			String uuid=jsonobject.getString("uuid");
			String cid=jsonobject.getString("cid");
			String text=jsonobject.getString("text");
			int rank=jsonobject.getInteger("rank");
			
			//进行评论
			userService.comment(text, rank, cid, uuid);
			
			//返回前端操作成功信息
			JSONObject jsonObject1 = new Message(SUCCESS).getJsonMessage();
			response.getWriter().print(jsonObject1);
		} catch (Message e) {
			// 异常打印信息 {"result":"xxx"}
			response.getWriter().print(e.getJsonMessage());
		}
	}
}
