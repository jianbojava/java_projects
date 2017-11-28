package com.cocosh.framework.interceptor;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

/**
 * cookie拦截器
 * 
 * @author jerry
 */
public class CookieInterceptor implements HandlerInterceptor{

	@Override
	public boolean preHandle(HttpServletRequest hsr, HttpServletResponse hsr1,
			Object o) throws Exception {
		boolean isLogin=false;
		Cookie[] cookies=hsr.getCookies();
		if(cookies != null && cookies.length >0){
			for (Cookie c : cookies) {
				if (c.getName().equals("wapuser")) {
					isLogin=true;
					break;
				}
			}
		}
		if (isLogin) {
			return true;
		}
		hsr.getRequestDispatcher("/wap/login").forward(hsr, hsr1);
		return false;
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
