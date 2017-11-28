package com.cocosh.car.model;

import java.io.Serializable;
import java.util.Date;

public class OrderLog implements Serializable{
	private String id;
	private String order_id;
	private String user_id;
	private String mem_id;
	private String message;
	private Date create_date; 
	
	public OrderLog() {
		super();
	}

	public OrderLog(String id,String order_id, String user_id, String mem_id,
			String message) {
		super();
		this.id=id;
		this.order_id = order_id;
		this.user_id = user_id;
		this.mem_id = mem_id;
		this.message = message;
	}

	/** 非数据库字段 **/
	private String user_name;
	private String mem_name;
	private String mem_mobile;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	
	public String getOrder_id() {
		return order_id;
	}

	public void setOrder_id(String order_id) {
		this.order_id = order_id;
	}

	public String getUser_id() {
		return user_id;
	}

	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}

	public String getMem_id() {
		return mem_id;
	}

	public void setMem_id(String mem_id) {
		this.mem_id = mem_id;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getUser_name() {
		return user_name;
	}

	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}

	public String getMem_name() {
		return mem_name;
	}

	public void setMem_name(String mem_name) {
		this.mem_name = mem_name;
	}

	public String getMem_mobile() {
		return mem_mobile;
	}

	public void setMem_mobile(String mem_mobile) {
		this.mem_mobile = mem_mobile;
	}

	public Date getCreate_date() {
		return create_date;
	}

	public void setCreate_date(Date create_date) {
		this.create_date = create_date;
	}

}