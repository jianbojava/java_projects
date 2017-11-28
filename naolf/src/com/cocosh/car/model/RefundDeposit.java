package com.cocosh.car.model;

import com.cocosh.framework.base.BaseEntity;

public class RefundDeposit extends BaseEntity {
	private String sn;
	private String mem_id;
	private Double amount;
	private Integer is_solve;//0 待处理  1通过  2拒绝
	private String remark;

	/****** 非数据字段 ******/
	private String mem_name;
	private String mem_mobile;
	private String[] sns;//操作记录集合

	public String getSn() {
		return sn;
	}

	public void setSn(String sn) {
		this.sn = sn;
	}

	public String getMem_id() {
		return mem_id;
	}

	public void setMem_id(String mem_id) {
		this.mem_id = mem_id;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public Integer getIs_solve() {
		return is_solve;
	}

	public void setIs_solve(Integer is_solve) {
		this.is_solve = is_solve;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
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

	public String[] getSns() {
		return sns;
	}

	public void setSns(String[] sns) {
		this.sns = sns;
	}
}