package com.cocosh.nlf.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class UserCash implements Serializable {
 	private String id;
 	private String user_id;
 	private BigDecimal kd_score;
 	private Integer type;// 0-银行 1-支付宝
 	private Integer status;// 0-待打款 1-已打款
 	private Date apply_date;
 	private Date pay_date;
 	
 	private String username;
 	private String userno;
 	private String type_desc;
 	private String status_desc;
 	
 	
 	
 	public String getType_desc() {
		return type_desc;
	}

	public void setType_desc(String type_desc) {
		this.type_desc = type_desc;
	}

	public String getStatus_desc() {
		return status_desc;
	}

	public void setStatus_desc(String status_desc) {
		this.status_desc = status_desc;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getUserno() {
		return userno;
	}

	public void setUserno(String userno) {
		this.userno = userno;
	}

	public String getId() {
 		return id;
 	}
 	
 	public void setId(String id) {
 		this.id = id;
 	}
 	
 	public String getUser_id() {
 		return user_id;
 	}
 	
 	public void setUser_id(String user_id) {
 		this.user_id = user_id;
 	}
 	
 	public BigDecimal getKd_score() {
 		return kd_score;
 	}
 	
 	public void setKd_score(BigDecimal kd_score) {
 		this.kd_score = kd_score;
 	}
 	
 	public Integer getType() {
 		return type;
 	}
 	
 	public void setType(Integer type) {
 		this.type = type;
 	}
 	
 	public Integer getStatus() {
 		return status;
 	}
 	
 	public void setStatus(Integer status) {
 		this.status = status;
 	}
 	
 	public Date getApply_date() {
 		return apply_date;
 	}
 	
 	public void setApply_date(Date apply_date) {
 		this.apply_date = apply_date;
 	}
 	
 	public Date getPay_date() {
 		return pay_date;
 	}
 	
 	public void setPay_date(Date pay_date) {
 		this.pay_date = pay_date;
 	}
}