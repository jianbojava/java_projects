package com.cocosh.car.model;

import java.util.Date;

import com.cocosh.framework.base.BaseEntity;

public class CouponRecord extends BaseEntity {
	private String cpn_id;
	private String mem_id;
	private Date get_date;
	private Date expired_date;
	private Integer used;
	private String order_id;
	private Integer enabled;

	/** 非数据字段 **/
	private String cpn_name;
	private Double cpn_amount;
	private Double full_amount;

	public String getCpn_id() {
		return cpn_id;
	}

	public void setCpn_id(String cpn_id) {
		this.cpn_id = cpn_id;
	}

	public String getMem_id() {
		return mem_id;
	}

	public void setMem_id(String mem_id) {
		this.mem_id = mem_id;
	}

	public Date getGet_date() {
		return get_date;
	}

	public void setGet_date(Date get_date) {
		this.get_date = get_date;
	}

	public Date getExpired_date() {
		return expired_date;
	}

	public void setExpired_date(Date expired_date) {
		this.expired_date = expired_date;
	}

	public Integer getUsed() {
		return used;
	}

	public void setUsed(Integer used) {
		this.used = used;
	}

	public String getOrder_id() {
		return order_id;
	}

	public void setOrder_id(String order_id) {
		this.order_id = order_id;
	}

	public Integer getEnabled() {
		return enabled;
	}

	public void setEnabled(Integer enabled) {
		this.enabled = enabled;
	}

	public String getCpn_name() {
		return cpn_name;
	}

	public void setCpn_name(String cpn_name) {
		this.cpn_name = cpn_name;
	}

	public Double getCpn_amount() {
		return cpn_amount;
	}

	public void setCpn_amount(Double cpn_amount) {
		this.cpn_amount = cpn_amount;
	}

	public Double getFull_amount() {
		return full_amount;
	}

	public void setFull_amount(Double full_amount) {
		this.full_amount = full_amount;
	}
}