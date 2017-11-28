package com.cocosh.nlf.model;

import java.util.Date;

import java.io.Serializable;

import org.springframework.format.annotation.DateTimeFormat;

import sun.net.www.content.audio.basic;

import com.alibaba.fastjson.annotation.JSONField;
import com.cocosh.framework.base.BaseEntity;
/**
 * 发票
 * @author bobo
 *
 */
public class Receipt extends BaseEntity {
 	private String id;
 	private String title;
 	private String code;
	private String sn;
 	private String norder_id;
 	private Integer type;//发票类型(1专票，2普票)
 	private Integer isreturn;//是否退票(1是，2否)
 	private Double money;
 	private String content;
	@JSONField(format = "yyyy-MM-dd")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
 	private Date add_date;
 	
 	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public Integer getIsreturn() {
		return isreturn;
	}

	public void setIsreturn(Integer isreturn) {
		this.isreturn = isreturn;
	}

	public String getId() {
 		return id;
 	}
 	
 	public void setId(String id) {
 		this.id = id;
 	}
 	
 	public String getNorder_id() {
 		return norder_id;
 	}
 	
 	public void setNorder_id(String norder_id) {
 		this.norder_id = norder_id;
 	}
 	
 	public Integer getType() {
 		return type;
 	}
 	
 	public void setType(Integer type) {
 		this.type = type;
 	}
 	
 	public Double getMoney() {
 		return money;
 	}
 	
 	public void setMoney(Double money) {
 		this.money = money;
 	}
 	
 	public String getContent() {
 		return content;
 	}
 	
 	public void setContent(String content) {
 		this.content = content;
 	}
 	
 	public Date getAdd_date() {
 		return add_date;
 	}
 	
 	public void setAdd_date(Date add_date) {
 		this.add_date = add_date;
 	}
 	public String getSn() {
 		return sn;
 	}
 	
 	public void setSn(String sn) {
 		this.sn = sn;
 	}
}