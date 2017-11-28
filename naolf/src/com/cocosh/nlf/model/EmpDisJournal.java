package com.cocosh.nlf.model;


import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class EmpDisJournal implements Serializable {
 	private String TRANSFER_name;
 	private String depart_name;
 	private String username;
 	private String user_number;
 	private BigDecimal BKZ_SCORE;
 	private BigDecimal KZ_SCORE;
 	private BigDecimal KD_SCORE;
 	private String ORDER_NO;
 	private Double pay_amount;//产生积分的金额
 	private Double price;//订单金额
 	private String buy_name;
	private String buy_num;
 	private String grade1_refer;
	private String grade2_refer;
 	private String grade3_refer;
 	private String grade4_refer;
 	
 	private String grade1;
	private String grade2;
 	private String grade3;
 	private String grade4;
 	
 	private Date create_date;
 	
 	
 	public Date getCreate_date() {
		return create_date;
	}

	public void setCreate_date(Date create_date) {
		this.create_date = create_date;
	}

	public String getGrade1() {
		return grade1;
	}

	public void setGrade1(String grade1) {
		this.grade1 = grade1;
	}

	public String getGrade2() {
		return grade2;
	}

	public void setGrade2(String grade2) {
		this.grade2 = grade2;
	}

	public String getGrade3() {
		return grade3;
	}

	public void setGrade3(String grade3) {
		this.grade3 = grade3;
	}

	public String getGrade4() {
		return grade4;
	}

	public void setGrade4(String grade4) {
		this.grade4 = grade4;
	}

	public String getGrade1_refer() {
 		return grade1_refer;
 	}
 	
 	public void setGrade1_refer(String grade1_refer) {
 		this.grade1_refer = grade1_refer;
 	}
 	
 	public String getTRANSFER_name() {
 		return TRANSFER_name;
 	}
 	
 	public void setTRANSFER_name(String TRANSFER_name) {
 		this.TRANSFER_name = TRANSFER_name;
 	}
 	
 	public String getDepart_name() {
 		return depart_name;
 	}
 	
 	public void setDepart_name(String depart_name) {
 		this.depart_name = depart_name;
 	}
 	
 	public String getUsername() {
 		return username;
 	}
 	
 	public void setUsername(String username) {
 		this.username = username;
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
 	
 	public String getORDER_NO() {
 		return ORDER_NO;
 	}
 	
 	public void setORDER_NO(String ORDER_NO) {
 		this.ORDER_NO = ORDER_NO;
 	}
 	
 	public Double getPay_amount() {
 		return pay_amount;
 	}
 	
 	public void setPay_amount(Double pay_amount) {
 		this.pay_amount = pay_amount;
 	}
 	
 	public String getBuy_name() {
 		return buy_name;
 	}
 	
 	public void setBuy_name(String buy_name) {
 		this.buy_name = buy_name;
 	}
 	
 	public String getBuy_num() {
 		return buy_num;
 	}
 	
 	public void setBuy_num(String buy_num) {
 		this.buy_num = buy_num;
 	}
 	
 	public String getGrade2_refer() {
 		return grade2_refer;
 	}
 	
 	public void setGrade2_refer(String grade2_refer) {
 		this.grade2_refer = grade2_refer;
 	}
 	
 	public String getGrade3_refer() {
 		return grade3_refer;
 	}
 	
 	public void setGrade3_refer(String grade3_refer) {
 		this.grade3_refer = grade3_refer;
 	}
 	
 	public String getGrade4_refer() {
 		return grade4_refer;
 	}
 	
 	public void setGrade4_refer(String grade4_refer) {
 		this.grade4_refer = grade4_refer;
 	}
 	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}
}