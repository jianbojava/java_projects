package com.cocosh.nlf.model;


import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class DeptPerformance implements Serializable {
 	private String depart_id;
 	private String create_mon;
 	private BigDecimal score;
 	
 
 	private String depart_name;
 	
 	private Date create_date;
 	private String order_number;
 	
 	
 	
 	public Date getCreate_date() {
		return create_date;
	}

	public void setCreate_date(Date create_date) {
		this.create_date = create_date;
	}

	public String getOrder_number() {
		return order_number;
	}

	public void setOrder_number(String order_number) {
		this.order_number = order_number;
	}

	public String getDepart_id() {
 		return depart_id;
 	}
 	
 	public void setDepart_id(String depart_id) {
 		this.depart_id = depart_id;
 	}
 	
 	public String getCreate_mon() {
 		return create_mon;
 	}
 	
 	public void setCreate_mon(String create_mon) {
 		this.create_mon = create_mon;
 	}
 	
 	public BigDecimal getScore() {
 		return score;
 	}
 	
 	public void setScore(BigDecimal score) {
 		this.score = score;
 	}

	public String getDepart_name() {
		return depart_name;
	}

	public void setDepart_name(String depart_name) {
		this.depart_name = depart_name;
	}
 	
}