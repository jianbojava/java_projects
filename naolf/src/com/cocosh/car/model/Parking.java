package com.cocosh.car.model;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.alibaba.fastjson.annotation.JSONField;
import com.cocosh.framework.base.BaseEntity;

public class Parking extends BaseEntity {
	private String number;
	private String name;
	private Double longitude;
	private Double latitude;
	private String address;
	private String dot_id;
	private String dot_name;
	private String dispatch_id;
	private String dispatch_name;
	@JSONField(format = "yyyy-MM-dd")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date sign_date;
	@JSONField(format = "yyyy-MM-dd")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date end_date;
	private Double price;
	private Double mkt_price;
	private Integer enabled;

	/***** 非数据库字段 *****/
	private Double distances;// 距离

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Double getLongitude() {
		return longitude;
	}

	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}

	public Double getLatitude() {
		return latitude;
	}

	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getDot_id() {
		return dot_id;
	}

	public void setDot_id(String dot_id) {
		this.dot_id = dot_id;
	}

	public String getDot_name() {
		return dot_name;
	}

	public void setDot_name(String dot_name) {
		this.dot_name = dot_name;
	}

	public String getDispatch_id() {
		return dispatch_id;
	}

	public void setDispatch_id(String dispatch_id) {
		this.dispatch_id = dispatch_id;
	}

	public String getDispatch_name() {
		return dispatch_name;
	}

	public void setDispatch_name(String dispatch_name) {
		this.dispatch_name = dispatch_name;
	}

	public Date getSign_date() {
		return sign_date;
	}

	public void setSign_date(Date sign_date) {
		this.sign_date = sign_date;
	}

	public Date getEnd_date() {
		return end_date;
	}

	public void setEnd_date(Date end_date) {
		this.end_date = end_date;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public Double getMkt_price() {
		return mkt_price;
	}

	public void setMkt_price(Double mkt_price) {
		this.mkt_price = mkt_price;
	}

	public Integer getEnabled() {
		return enabled;
	}

	public void setEnabled(Integer enabled) {
		this.enabled = enabled;
	}

	public Double getDistances() {
		return distances;
	}

	public void setDistances(Double distances) {
		this.distances = distances;
	}
}