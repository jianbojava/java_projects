package com.cocosh.nlf.model;


import java.io.Serializable;

public class OrderDisRateSetting implements Serializable {
 	private String rate_type;
 	private Double rate;
 	private String description;
 	
 	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getRate_type() {
 		return rate_type;
 	}
 	
 	public void setRate_type(String rate_type) {
 		this.rate_type = rate_type;
 	}
 	
 	public Double getRate() {
 		return rate;
 	}
 	
 	public void setRate(Double rate) {
 		this.rate = rate;
 	}
 	
}