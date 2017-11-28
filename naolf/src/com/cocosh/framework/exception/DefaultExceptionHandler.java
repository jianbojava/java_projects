package com.cocosh.framework.exception;

import org.apache.shiro.authz.UnauthorizedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class DefaultExceptionHandler {

	@ExceptionHandler
	public String processUnauthenticatedException(Exception ex) {
		if (ex instanceof UnauthorizedException) {
			return "401";
		} else {
			ex.printStackTrace();
			return "500";
		}
	}

	/**
	 * 没有权限
	 */
//	@ExceptionHandler({ UnauthorizedException.class })
//	public String unauthorizedException() {
//		return "401";
//	}

}
