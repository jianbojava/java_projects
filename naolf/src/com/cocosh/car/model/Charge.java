package com.cocosh.car.model;

import com.cocosh.framework.base.BaseEntity;

public class Charge extends BaseEntity {
	private String number;
	private String name;
	private String brand_id;
	private String brand_name;
	private String dot_id;
	private String dot_name;
	private String park_id;
	private String park_name;
	private Integer comment_star;
	private String gallerys;
	private Double price;
	private Double mkt_price;
	private String support_car;
	private Integer charge_type;
	private Integer enabled;

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

	public String getBrand_id() {
		return brand_id;
	}

	public void setBrand_id(String brand_id) {
		this.brand_id = brand_id;
	}

	public String getBrand_name() {
		return brand_name;
	}

	public void setBrand_name(String brand_name) {
		this.brand_name = brand_name;
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

	public String getPark_id() {
		return park_id;
	}

	public void setPark_id(String park_id) {
		this.park_id = park_id;
	}

	public String getPark_name() {
		return park_name;
	}

	public void setPark_name(String park_name) {
		this.park_name = park_name;
	}

	public Integer getComment_star() {
		return comment_star;
	}

	public void setComment_star(Integer comment_star) {
		this.comment_star = comment_star;
	}

	public String getGallerys() {
		return gallerys;
	}

	public void setGallerys(String gallerys) {
		this.gallerys = gallerys;
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

	public String getSupport_car() {
		return support_car;
	}

	public void setSupport_car(String support_car) {
		this.support_car = support_car;
	}

	public Integer getCharge_type() {
		return charge_type;
	}

	public void setCharge_type(Integer charge_type) {
		this.charge_type = charge_type;
	}

	public Integer getEnabled() {
		return enabled;
	}

	public void setEnabled(Integer enabled) {
		this.enabled = enabled;
	}

}