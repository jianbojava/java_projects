package com.cocosh.car.model;

import com.cocosh.framework.excel.ExcelField;
import com.cocosh.framework.util.DateUtil;

/**
 * 违章导出
 * 
 * @author jerry
 */
public class IllegalExport {
	private String vin;// 车架号
	private String illegal_date;// 违章时间
	private String document_number;// 凭证编号
	private String place;// 地点
	private String illegal_content;// 违法内容
	private String illegal_code;// 违法代码
	private Integer service_amount;// 代办服务费
	private String payment;// 支付方式 0：违章预授权；1：信用卡；2：现金
	private String payment_status;// 支付状态 0：未支付；1：已支付
	private Integer illegal_amount;// 违法金额（元）
	private Integer penalty_point;// 罚分
	private String status;// 状态 0：未处理；1：处理中；2：办理完毕
	private String orderSeq;// 订单编号
	private String illegal_imageUrl;// 违章详情图片
	private String certificate_image;// 缴费凭证
	private String is_solve;// 是否解决 客服使用 0：待处理 1：已处理
	private String remark;// 处理备注

	@ExcelField(title = "车架号", sort = 1)
	public String getVin() {
		return vin;
	}

	public void setVin(String vin) {
		this.vin = vin;
	}

	@ExcelField(title = "违章时间", sort = 2)
	public String getIllegal_date() {
		return DateUtil.formatDate(
				DateUtil.stringToDate(illegal_date, "yyyy-MM-dd HH:mm:ss"),
				"yyyy-MM-dd HH:mm:ss");
	}

	public void setIllegal_date(String illegal_date) {
		this.illegal_date = illegal_date;
	}

	@ExcelField(title = "凭证编号", sort = 3)
	public String getDocument_number() {
		return document_number;
	}

	public void setDocument_number(String document_number) {
		this.document_number = document_number;
	}

	@ExcelField(title = "违章地点", sort = 4)
	public String getPlace() {
		return place;
	}

	public void setPlace(String place) {
		this.place = place;
	}

	@ExcelField(title = "违法内容", sort = 5)
	public String getIllegal_content() {
		return illegal_content;
	}

	public void setIllegal_content(String illegal_content) {
		this.illegal_content = illegal_content;
	}

	@ExcelField(title = "违法代码", sort = 6)
	public String getIllegal_code() {
		return illegal_code;
	}

	public void setIllegal_code(String illegal_code) {
		this.illegal_code = illegal_code;
	}

	@ExcelField(title = "代办服务费", sort = 7)
	public Integer getService_amount() {
		return service_amount;
	}

	public void setService_amount(Integer service_amount) {
		this.service_amount = service_amount;
	}

	@ExcelField(title = "支付方式", sort = 8)
	public String getPayment() {
		if (payment != null) {
			if (payment.equals("0")) {
				return "违章预授权";
			} else if (payment.equals("1")) {
				return "信用卡";
			} else {
				return "现金";
			}
		}
		return null;
	}

	public void setPayment(String payment) {
		this.payment = payment;
	}

	@ExcelField(title = "支付状态", sort = 9)
	public String getPayment_status() {
		if (payment_status != null) {
			if (payment_status.equals("0")) {
				return "未支付";
			}
			return "已支付";
		}
		return null;
	}

	public void setPayment_status(String payment_status) {
		this.payment_status = payment_status;
	}

	@ExcelField(title = "违法金额（元）", sort = 10)
	public Integer getIllegal_amount() {
		return illegal_amount;
	}

	public void setIllegal_amount(Integer illegal_amount) {
		this.illegal_amount = illegal_amount;
	}

	@ExcelField(title = "罚分", sort = 11)
	public Integer getPenalty_point() {
		return penalty_point;
	}

	public void setPenalty_point(Integer penalty_point) {
		this.penalty_point = penalty_point;
	}

	@ExcelField(title = "状态", sort = 12)
	public String getStatus() {
		if (status != null) {
			if (status.equals("0")) {
				return "未处理";
			} else if (status.equals("1")) {
				return "处理中";
			} else {
				return "处理完毕";
			}
		}
		return null;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@ExcelField(title = "订单编号", sort = 13)
	public String getOrderSeq() {
		return orderSeq;
	}

	public void setOrderSeq(String orderSeq) {
		this.orderSeq = orderSeq;
	}

	@ExcelField(title = "违章详情图片", sort = 14)
	public String getIllegal_imageUrl() {
		return illegal_imageUrl;
	}

	public void setIllegal_imageUrl(String illegal_imageUrl) {
		this.illegal_imageUrl = illegal_imageUrl;
	}

	@ExcelField(title = "缴费凭证", sort = 15)
	public String getCertificate_image() {
		return certificate_image;
	}

	public void setCertificate_image(String certificate_image) {
		this.certificate_image = certificate_image;
	}

	@ExcelField(title = "距马处理状态", sort = 16)
	public String getIs_solve() {
		if (is_solve.equals("0")) {
			return "待处理";
		}
		return "已处理";
	}

	public void setIs_solve(String is_solve) {
		this.is_solve = is_solve;
	}

	@ExcelField(title = "备注", sort = 17)
	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

}
