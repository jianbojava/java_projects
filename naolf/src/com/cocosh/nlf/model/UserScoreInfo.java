package com.cocosh.nlf.model;

import java.util.Date;

import java.io.Serializable;
import java.math.BigDecimal;

public class UserScoreInfo implements Serializable {
 	private String user_number;
 	private BigDecimal BKZ_SCORE;
 	private BigDecimal KZ_SCORE;//可转换积分
 	private BigDecimal KD_SCORE;//（可兑换积分）前端购买积分和卡券升级使用的积分
 	private String ATTRIBUTE1;
 	private String ATTRIBUTE2;
 	private String ATTRIBUTE3;
 	private Integer ATTRIBUTE4;
 	private Integer ATTRIBUTE5;
 	private Date last_update_date;
 	
 	private String user_name;
 	
 	private String depart_id;
 	private String depart_name;
 	
 	
 	public String getDepart_id() {
		return depart_id;
	}

	public void setDepart_id(String depart_id) {
		this.depart_id = depart_id;
	}

	public String getDepart_name() {
		return depart_name;
	}

	public void setDepart_name(String depart_name) {
		this.depart_name = depart_name;
	}

	public String getUser_name() {
		return user_name;
	}

	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}

	public String getUser_number() {
 		return user_number;
 	}
 	
 	public void setUser_number(String user_number) {
 		this.user_number = user_number;
 	}
 	
 	public BigDecimal getBKZ_SCORE() {
 		return BKZ_SCORE;
 	}
 	
 	public void setBKZ_SCORE(BigDecimal BKZ_SCORE) {
 		this.BKZ_SCORE = BKZ_SCORE;
 	}
 	
 	public BigDecimal getKZ_SCORE() {
 		return KZ_SCORE;
 	}
 	
 	public void setKZ_SCORE(BigDecimal KZ_SCORE) {
 		this.KZ_SCORE = KZ_SCORE;
 	}
 	
 	public BigDecimal getKD_SCORE() {
 		return KD_SCORE;
 	}
 	
 	public void setKD_SCORE(BigDecimal KD_SCORE) {
 		this.KD_SCORE = KD_SCORE;
 	}
 	
 	public String getATTRIBUTE1() {
 		return ATTRIBUTE1;
 	}
 	
 	public void setATTRIBUTE1(String ATTRIBUTE1) {
 		this.ATTRIBUTE1 = ATTRIBUTE1;
 	}
 	
 	public String getATTRIBUTE2() {
 		return ATTRIBUTE2;
 	}
 	
 	public void setATTRIBUTE2(String ATTRIBUTE2) {
 		this.ATTRIBUTE2 = ATTRIBUTE2;
 	}
 	
 	public String getATTRIBUTE3() {
 		return ATTRIBUTE3;
 	}
 	
 	public void setATTRIBUTE3(String ATTRIBUTE3) {
 		this.ATTRIBUTE3 = ATTRIBUTE3;
 	}
 	
 	public Integer getATTRIBUTE4() {
 		return ATTRIBUTE4;
 	}
 	
 	public void setATTRIBUTE4(Integer ATTRIBUTE4) {
 		this.ATTRIBUTE4 = ATTRIBUTE4;
 	}
 	
 	public Integer getATTRIBUTE5() {
 		return ATTRIBUTE5;
 	}
 	
 	public void setATTRIBUTE5(Integer ATTRIBUTE5) {
 		this.ATTRIBUTE5 = ATTRIBUTE5;
 	}
 	
 	public Date getLast_update_date() {
 		return last_update_date;
 	}
 	
 	public void setLast_update_date(Date last_update_date) {
 		this.last_update_date = last_update_date;
 	}
}