package com.cocosh.car.model;

import java.util.Date;

import java.io.Serializable;

public class OperatorInfo implements Serializable {
	private String operatorID;// 运营商ID
	private String operatorName;// 运营商名称
	private String operatorTel1;// 运营商电话1
	private String operatorTel2;// 运营商电话2
	private String operatorRegAddress;// 运营商注册地址
	private String operatorNote;// 备注
	private Date create_date;
	private Date modify_date;
	private Integer enabled;// 0 正常 1禁用

	public String getOperatorID() {
		return operatorID;
	}

	public void setOperatorID(String operatorID) {
		this.operatorID = operatorID;
	}

	public String getOperatorName() {
		return operatorName;
	}

	public void setOperatorName(String operatorName) {
		this.operatorName = operatorName;
	}

	public String getOperatorTel1() {
		return operatorTel1;
	}

	public void setOperatorTel1(String operatorTel1) {
		this.operatorTel1 = operatorTel1;
	}

	public String getOperatorTel2() {
		return operatorTel2;
	}

	public void setOperatorTel2(String operatorTel2) {
		this.operatorTel2 = operatorTel2;
	}

	public String getOperatorRegAddress() {
		return operatorRegAddress;
	}

	public void setOperatorRegAddress(String operatorRegAddress) {
		this.operatorRegAddress = operatorRegAddress;
	}

	public String getOperatorNote() {
		return operatorNote;
	}

	public void setOperatorNote(String operatorNote) {
		this.operatorNote = operatorNote;
	}

	public Date getCreate_date() {
		return create_date;
	}

	public void setCreate_date(Date create_date) {
		this.create_date = create_date;
	}

	public Date getModify_date() {
		return modify_date;
	}

	public void setModify_date(Date modify_date) {
		this.modify_date = modify_date;
	}

	public Integer getEnabled() {
		return enabled;
	}

	public void setEnabled(Integer enabled) {
		this.enabled = enabled;
	}
}