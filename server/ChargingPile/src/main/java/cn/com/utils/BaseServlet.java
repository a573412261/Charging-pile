package cn.com.utils;

import java.io.IOException;
import java.lang.reflect.Method;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//处理多逻辑的Servlet继承于此抽象类
public abstract class BaseServlet extends HttpServlet {

    // final防止子类改写
    public final void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        this.doPost(request, response);
    }

    public final void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    	//获取参数，得知调用方法名
        String methodName = request.getParameter("method");
        
        if (methodName == null) {
            methodName = "execute";
        }

        System.out.println("BaseServlet : " + this + " , " + methodName);

        try {
        	//反射出方法
            Method executeMethod = this.getClass().getMethod(methodName, HttpServletRequest.class, HttpServletResponse.class);
            //调用方法
            executeMethod.invoke(this, request, response);
        } catch (NoSuchMethodException e) {
            throw new RuntimeException("调用方法：[" + methodName + "]不存在");
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("服务器出错", e);
        }
    }

    /**
     * 当method=null时调用，子类可覆盖该方法
     */
    public void execute(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }
}