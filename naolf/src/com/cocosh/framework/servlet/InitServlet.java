package com.cocosh.framework.servlet;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

import com.cocosh.framework.util.CommonUtil;

/**
 * 系统启动时候设置全局变量
 * 
 * @author jerry
 */
public class InitServlet extends HttpServlet {

	private static final long serialVersionUID = -5853209312626563162L;

	public InitServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * Destruction of the servlet. <br>
	 */
	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
		// Put your code here
	}

	/**
	 * Initialization of the servlet. <br>
	 * 
	 * @throws ServletException
	 *             if an error occurs
	 */
	public void init(ServletConfig config) throws ServletException {
		// 设置资源访问路径
		config.getServletContext().setAttribute("SevDomain",CommonUtil.SERVERDOMAIN);
		config.getServletContext().setAttribute("ResDomain",CommonUtil.RESDOMAIN);
	}

}
