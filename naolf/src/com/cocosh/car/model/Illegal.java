package com.cocosh.car.model;

import java.util.Date;

import com.cocosh.framework.base.BaseEntity;

public class Illegal extends BaseEntity {
	private Integer illegalSeq;// 主键
	private String vin;// 车架号
	private Date illegal_date;// 违章时间
	private String document_number;// 凭证编号
	private String place;// 地点
	private String illegal_content;// 违法内容
	private String illegal_code;// 违法代码
	private Integer service_amount;// 代办服务费
	private Integer payment;// 支付方式 0：违章预授权；1：信用卡；2：现金
	private Integer payment_status;// 支付状态 0：未支付；1：已支付
	private Integer illegal_amount;// 违法金额（元）
	private Integer penalty_point;// 罚分
	private Integer status;// 状态 0：未处理；1：处理中；2：办理完毕
	private String orderSeq;// 订单编号
	private String illegal_imageUrl;// 违章详情图片
	private String certificate_image;// 缴费凭证
	private Integer is_solve;// 是否解决 客服使用 0：待处理 1：已处理
	private String remark;// 处理备注
	
	/*******非数据字段*******/
	private String[] ids;//操作记录集合

	public Integer getIllegalSeq() {
		return illegalSeq;
	}

	public void setIllegalSeq(Integer illegalSeq) {
		this.illegalSeq = illegalSeq;
	}

	public String getVin() {
		return vin;
	}

	public void setVin(String vin) {
		this.vin = vin;
	}

	public Date getIllegal_date() {
		return illegal_date;
	}

	public void setIllegal_date(Date illegal_date) {
		this.illegal_date = illegal_date;
	}

	public String getDocument_number() {
		return document_number;
	}

	public void setDocument_number(String document_number) {
		this.document_number = document_number;
	}

	public String getPlace() {
		return place;
	}

	public void setPlace(String place) {
		this.place = place;
	}

	public String getIllegal_content() {
		return illegal_content;
	}

	public void setIllegal_content(String illegal_content) {
		this.illegal_content = illegal_content;
	}

	public String getIllegal_code() {
		return illegal_code;
	}

	public void setIllegal_code(String illegal_code) {
		this.illegal_code = illegal_code;
	}

	public Integer getService_amount() {
		return service_amount;
	}

	public void setService_amount(Integer service_amount) {
		this.service_amount = service_amount;
	}

	public Integer getPayment() {
		return payment;
	}

	public void setPayment(Integer payment) {
		this.payment = payment;
	}

	public Integer getPayment_status() {
		return payment_status;
	}

	public void setPayment_status(Integer payment_status) {
		this.payment_status = payment_status;
	}

	public Integer getIllegal_amount() {
		return illegal_amount;
	}

	public void setIllegal_amount(Integer illegal_amount) {
		this.illegal_amount = illegal_amount;
	}

	public Integer getPenalty_point() {
		return penalty_point;
	}

	public void setPenalty_point(Integer penalty_point) {
		this.penalty_point = penalty_point;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getOrderSeq() {
		return orderSeq;
	}

	public void setOrderSeq(String orderSeq) {
		this.orderSeq = orderSeq;
	}

	public String getIllegal_imageUrl() {
		return illegal_imageUrl;
	}

	public void setIllegal_imageUrl(String illegal_imageUrl) {
		this.illegal_imageUrl = illegal_imageUrl;
	}

	public String getCertificate_image() {
		return certificate_image;
	}

	public void setCertificate_image(String certificate_image) {
		this.certificate_image = certificate_image;
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

	public String[] getIds() {
		return ids;
	}

	public void setIds(String[] ids) {
		this.ids = ids;
	}

}