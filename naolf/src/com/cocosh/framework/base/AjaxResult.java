package com.cocosh.framework.base;

import java.io.Serializable;

/**
 * ajax返回状态
 * 
 * @author jerry
 */
public class AjaxResult implements Serializable {
	private static final long serialVersionUID = 1L;
	private String code;// 状态码 0:成功 1:失败
	private String msg;// 消息
	private Object data;// 数据

	public AjaxResult(String code) {
		super();
		this.code = code;
	}

	public AjaxResult(String code, String msg) {
		super();
		this.code = code;
		this.msg = msg;
	}

	public AjaxResult(String code, String msg, Object data) {
		super();
		this.code = code;
		this.msg = msg;
		this.data = data;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

}
