package com.cocosh.framework.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

/**
 * Url拦截器
 * 
 * @author jerry
 */
public class UrlInterceptor implements HandlerInterceptor {

	@Override
	public boolean preHandle(HttpServletRequest hsr, HttpServletResponse hsr1,
			Object o) throws Exception {
		if (!hsr.getRequestURI().contains("wap/search/cre")) {
			String ua = hsr.getHeader("user-agent");
			if (ua.contains("MicroMessenger")) {//微信打开
				return true;
			}
			hsr.getRequestDispatcher("/index/browserError").forward(hsr, hsr1);
			return false;
		}
		return true;
	}

	@Override
	public void afterCompletion(HttpServletRequest arg0,
			HttpServletResponse arg1, Object arg2, Exception arg3)
			throws Exception {

	}

	@Override
	public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1,
			Object arg2, ModelAndView arg3) throws Exception {

	}

}
