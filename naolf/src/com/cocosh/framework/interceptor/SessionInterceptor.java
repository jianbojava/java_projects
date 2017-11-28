package com.cocosh.framework.interceptor;

import java.util.logging.Logger;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.cocosh.sys.model.User;

/**
 * session拦截器
 * 
 * @author jerry
 */
public class SessionInterceptor implements HandlerInterceptor {
	private Logger logger = Logger.getLogger(SessionInterceptor.class.getName());

	public boolean preHandle(HttpServletRequest hsr, HttpServletResponse hsr1,
			Object o) throws Exception {
		Subject currentUser = SecurityUtils.getSubject();
		Session session = currentUser.getSession();
		User user = (User) session.getAttribute("wapuser");
		if (user == null) {
			logger.info("session timeout");
			hsr.getRequestDispatcher("/wap/index").forward(hsr, hsr1);
			return false;
		}
		return true;
	}

	public void afterCompletion(HttpServletRequest arg0,
			HttpServletResponse arg1, Object arg2, Exception arg3)
			throws Exception {
	}

	public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1,
			Object arg2, ModelAndView arg3) throws Exception {
	}
}
