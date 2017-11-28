package com.cocosh.nlf.model;

import java.util.Date;

import java.io.Serializable;

import org.springframework.format.annotation.DateTimeFormat;

import com.alibaba.fastjson.annotation.JSONField;
/**
 * 卡券升级表nlf_upgrade
 * @author bobo
 *
 */
public class Upgrade implements Serializable {
 	private String id;
 	private String user_ticket_id1;
 	private String user_ticket_id2;
 	private String user_ticket_id3;
 	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
 	private Date create_date;
 	//非数据库字段
 	private Integer  type;//升级方式：0：未使用卡用未使用的券升级；1已使用的卡用未使用的券升级
 	                     //2：已使用的券用已使用的券升级，3：已使用的券用积分升级，4已使用的券用金额升级
 	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	
 	public String getId() {
 		return id;
 	}
 	
 	public void setId(String id) {
 		this.id = id;
 	}
 	
 	public String getUser_ticket_id1() {
 		return user_ticket_id1;
 	}
 	
 	public void setUser_ticket_id1(String user_ticket_id1) {
 		this.user_ticket_id1 = user_ticket_id1;
 	}
 	
 	public String getUser_ticket_id2() {
 		return user_ticket_id2;
 	}
 	
 	public void setUser_ticket_id2(String user_ticket_id2) {
 		this.user_ticket_id2 = user_ticket_id2;
 	}
 	
 	public String getUser_ticket_id3() {
 		return user_ticket_id3;
 	}
 	
 	public void setUser_ticket_id3(String user_ticket_id3) {
 		this.user_ticket_id3 = user_ticket_id3;
 	}
 	
 	public Date getCreate_date() {
 		return create_date;
 	}
 	
 	public void setCreate_date(Date create_date) {
 		this.create_date = create_date;
 	}
}