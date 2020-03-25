package cn.com.servlet;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
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
import com.mchange.v2.c3p0.impl.NewPooledConnection;

import cn.com.bean.Message;
import cn.com.bean.User;
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
	 * @throws Exception
	 */
	public void insertuser(HttpServletRequest request, HttpServletResponse response) throws Exception{
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
	 * 输入：uuid必选,其他均可选
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
			//更新数据库中user的信息
			userService.update(user);
			//操作成功，返回 {"result":"1"}
			response.getWriter().print(new Message(SUCCESS).getJsonMessage());
		} catch (Message e) {
			// 异常打印信息 {"result":"xxx"}
			response.getWriter().print(e.getJsonMessage());
		}
	}
}
