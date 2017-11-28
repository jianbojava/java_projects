package com.cocosh.sys.model;

import com.cocosh.framework.base.BaseEntity;

/**
 * 日志
 * 
 * @author jerry
 */
public class Log extends BaseEntity {
	private static final long serialVersionUID = -2886849610924511564L;
	private String module;// 模块
	private String method;// 方法
	private String user_id;// 操作者ID
	private String ip;// 操作IP
	private String exception;// 异常信息

	/** 非数据库字段 **/
	private String username;// 操作者用户名

	public String getModule() {
		return module;
	}

	public void setModule(String module) {
		this.module = module;
	}

	public String getMethod() {
		return method;
	}

	public void setMethod(String method) {
		this.method = method;
	}

	public String getUser_id() {
		return user_id;
	}

	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getException() {
		return exception;
	}

	public void setException(String exception) {
		this.exception = exception;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
}
