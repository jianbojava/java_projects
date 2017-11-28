package com.cocosh.car.model;

import java.util.Date;

import com.cocosh.framework.base.BaseEntity;

public class Coupon extends BaseEntity {
 	private String name;
 	private Double amount;
 	private Double full_amount;
 	private Integer expired_days;
 	private Date expired_date;
 	private String decription;
 	private Integer enabled;
 	
 	public String getName() {
 		return name;
 	}
 	
 	public void setName(String name) {
 		this.name = name;
 	}
 	
 	public Double getAmount() {
 		return amount;
 	}
 	
 	public void setAmount(Double amount) {
 		this.amount = amount;
 	}
 	
 	public Double getFull_amount() {
 		return full_amount;
 	}
 	
 	public void setFull_amount(Double full_amount) {
 		this.full_amount = full_amount;
 	}
 	
 	public Integer getExpired_days() {
 		return expired_days;
 	}
 	
 	public void setExpired_days(Integer expired_days) {
 		this.expired_days = expired_days;
 	}
 	
 	public Date getExpired_date() {
 		return expired_date;
 	}
 	
 	public void setExpired_date(Date expired_date) {
 		this.expired_date = expired_date;
 	}
 	
 	public String getDecription() {
 		return decription;
 	}
 	
 	public void setDecription(String decription) {
 		this.decription = decription;
 	}
 	
 	public Integer getEnabled() {
 		return enabled;
 	}
 	
 	public void setEnabled(Integer enabled) {
 		this.enabled = enabled;
 	}
 	
}