package com.cocosh.framework.interceptor;

import java.lang.reflect.Method;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cocosh.framework.annotation.LogClass;
import com.cocosh.framework.util.HttpUtil;
import com.cocosh.sys.model.Log;
import com.cocosh.sys.model.User;
import com.cocosh.sys.service.LogService;

/**
 * 日志切面
 * 
 * @author jerry
 */

@Aspect
@Component
public class LogAspect {
	@Autowired
	private HttpServletRequest request;
	@Autowired
	private LogService logService;

	@After("execution(* com.cocosh.*.service.impl.*.*(..))")
	public void writeLog(JoinPoint jp) throws Exception {
		Method[] ms = jp.getTarget().getClass().getMethods();
		String methodName = jp.getSignature().getName();
		for (Method m : ms) {
			if (m.getName().equals(methodName)) {
				if (m.isAnnotationPresent(LogClass.class)) {
					LogClass lc = m.getAnnotation(LogClass.class);
					// 获取当前用户
					Subject currentUser = SecurityUtils.getSubject();
					Session session = currentUser.getSession();
					User user = (User) session.getAttribute("user");
					Log log = new Log();
					log.setModule(lc.module());
					log.setMethod(lc.method());
					log.setUser_id(user!=null?user.getId():"");
					log.setIp(HttpUtil.getIpAddr(request));
					logService.add(log);
				}
			}
		}
	}

	@After("execution(* com.cocosh.sys.controller.*.login(..))")
	public void loginLog(JoinPoint jp) {
		Subject currentUser = SecurityUtils.getSubject();
		Session session = currentUser.getSession();
		User user = (User) session.getAttribute("user");
		if (null != user) {
			Log log = new Log();
			log.setModule("登录管理");
			log.setMethod("登录");
			log.setUser_id(user.getId());
			log.setIp(HttpUtil.getIpAddr(request));
			logService.add(log);
		}
	}

	@Before("execution(* com.cocosh.sys.controller.*.logout(..))")
	public void logoutLog(JoinPoint jp) {
		Subject currentUser = SecurityUtils.getSubject();
		Session session = currentUser.getSession();
		User user = (User) session.getAttribute("user");
		if (null != user) {
			Log log = new Log();
			log.setModule("登录管理");
			log.setMethod("退出");
			log.setUser_id(user.getId());
			log.setIp(HttpUtil.getIpAddr(request));
			logService.add(log);
		}
	}

	/**
	 * 异常信息
	 * 
	 * @param jp
	 * @param ex
	 */
	@AfterThrowing(throwing = "ex", pointcut = "execution(* com.cocosh.*.service.*.*(..))")
	public void doThrowable(JoinPoint jp, Throwable ex) {
		Subject currentUser = SecurityUtils.getSubject();
		Session session = currentUser.getSession();
		User user = (User) session.getAttribute("user");
		if (null != user) {
			Log log = new Log();
			log.setModule(jp.getTarget().getClass() + "." + jp.getSignature().getName());
			log.setMethod("系统异常");
			log.setUser_id(user.getId());
			log.setIp(HttpUtil.getIpAddr(request));
			log.setException(ex.toString());
			logService.add(log);
		}
	}
}
