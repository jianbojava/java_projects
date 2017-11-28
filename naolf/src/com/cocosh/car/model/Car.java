package com.cocosh.car.model;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.alibaba.fastjson.annotation.JSONField;
import com.cocosh.framework.base.BaseEntity;

public class Car extends BaseEntity {
	private String number;
	private String name;
	private String license;
	private Integer type;// 车辆类型 纯电汽车/插电混动汽车/纯电货车
	private Integer nature;// 使用性质 运营/非运营
	private String brand_id;
	private String brand_name;
	private String model_id;
	private String model_name;
	private String engine_num;
	private String register_addr;
	private String register_num;
	@JSONField(format = "yyyy-MM-dd")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date register_date;
	private String product_area;
	@JSONField(format = "yyyy-MM")
	@DateTimeFormat(pattern = "yyyy-MM")
	private Date exfactory_date;
	private Integer total_distance;
	private Integer battery_distance;
	private Double charge_length;// 充电时长
	private Integer fuel_distance;
	private String fuel_type;
	private Integer tank_vol;
	private String body_size;
	private Integer body_weight;
	private String body_design;
	private Integer cargo_vol;
	private Integer top_speed;
	private String sup_company;
	private String sup_person;
	private String sup_mobile;
	@JSONField(format = "yyyy-MM-dd")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date sign_date;
	@JSONField(format = "yyyy-MM-dd")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date end_date;
	private Double price;
	private Double mkt_price;
	private Double deposit;// 押金
	private String gallerys;
	private Integer sort;
	private String dot_id;
	private String dot_name;
	private String park_id;// 停车位ID
	private String park_name;
	private Integer review;// 0待审 1通过 2拒绝
	private String review_error;
	private Double comment_star;// 评论星级
	private Integer enabled;// 0上线 1 下线 2维修
	private Integer del_flg;// 删除 0：正常 1：删除

	/*** 非数据库字段 ***/
	private String dispatch_name;// 调度员
	private String dispatch_mobile;// 调度员电话
	private Integer rent_status;// 租用状态 0：可租 1：不可组

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

	public String getLicense() {
		return license;
	}

	public void setLicense(String license) {
		this.license = license;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public Integer getNature() {
		return nature;
	}

	public void setNature(Integer nature) {
		this.nature = nature;
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

	public String getModel_id() {
		return model_id;
	}

	public void setModel_id(String model_id) {
		this.model_id = model_id;
	}

	public String getModel_name() {
		return model_name;
	}

	public void setModel_name(String model_name) {
		this.model_name = model_name;
	}

	public String getEngine_num() {
		return engine_num;
	}

	public void setEngine_num(String engine_num) {
		this.engine_num = engine_num;
	}

	public String getRegister_addr() {
		return register_addr;
	}

	public void setRegister_addr(String register_addr) {
		this.register_addr = register_addr;
	}

	public String getRegister_num() {
		return register_num;
	}

	public void setRegister_num(String register_num) {
		this.register_num = register_num;
	}

	public Date getRegister_date() {
		return register_date;
	}

	public void setRegister_date(Date register_date) {
		this.register_date = register_date;
	}

	public String getProduct_area() {
		return product_area;
	}

	public void setProduct_area(String product_area) {
		this.product_area = product_area;
	}

	public Date getExfactory_date() {
		return exfactory_date;
	}

	public void setExfactory_date(Date exfactory_date) {
		this.exfactory_date = exfactory_date;
	}

	public Integer getTotal_distance() {
		return total_distance;
	}

	public void setTotal_distance(Integer total_distance) {
		this.total_distance = total_distance;
	}

	public Integer getBattery_distance() {
		return battery_distance;
	}

	public void setBattery_distance(Integer battery_distance) {
		this.battery_distance = battery_distance;
	}

	public Double getCharge_length() {
		return charge_length;
	}

	public void setCharge_length(Double charge_length) {
		this.charge_length = charge_length;
	}

	public Integer getFuel_distance() {
		return fuel_distance;
	}

	public void setFuel_distance(Integer fuel_distance) {
		this.fuel_distance = fuel_distance;
	}

	public String getFuel_type() {
		return fuel_type;
	}

	public void setFuel_type(String fuel_type) {
		this.fuel_type = fuel_type;
	}

	public Integer getTank_vol() {
		return tank_vol;
	}

	public void setTank_vol(Integer tank_vol) {
		this.tank_vol = tank_vol;
	}

	public String getBody_size() {
		return body_size;
	}

	public void setBody_size(String body_size) {
		this.body_size = body_size;
	}

	public Integer getBody_weight() {
		return body_weight;
	}

	public void setBody_weight(Integer body_weight) {
		this.body_weight = body_weight;
	}

	public String getBody_design() {
		return body_design;
	}

	public void setBody_design(String body_design) {
		this.body_design = body_design;
	}

	public Integer getCargo_vol() {
		return cargo_vol;
	}

	public void setCargo_vol(Integer cargo_vol) {
		this.cargo_vol = cargo_vol;
	}

	public Integer getTop_speed() {
		return top_speed;
	}

	public void setTop_speed(Integer top_speed) {
		this.top_speed = top_speed;
	}

	public String getSup_company() {
		return sup_company;
	}

	public void setSup_company(String sup_company) {
		this.sup_company = sup_company;
	}

	public String getSup_person() {
		return sup_person;
	}

	public void setSup_person(String sup_person) {
		this.sup_person = sup_person;
	}

	public String getSup_mobile() {
		return sup_mobile;
	}

	public void setSup_mobile(String sup_mobile) {
		this.sup_mobile = sup_mobile;
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

	public Double getDeposit() {
		return deposit;
	}

	public void setDeposit(Double deposit) {
		this.deposit = deposit;
	}

	public String getGallerys() {
		return gallerys;
	}

	public void setGallerys(String gallerys) {
		this.gallerys = gallerys;
	}

	public Integer getSort() {
		return sort;
	}

	public void setSort(Integer sort) {
		this.sort = sort;
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

	public Integer getReview() {
		return review;
	}

	public void setReview(Integer review) {
		this.review = review;
	}

	public String getReview_error() {
		return review_error;
	}

	public void setReview_error(String review_error) {
		this.review_error = review_error;
	}

	public Double getComment_star() {
		return comment_star;
	}

	public void setComment_star(Double comment_star) {
		this.comment_star = comment_star;
	}

	public Integer getEnabled() {
		return enabled;
	}

	public void setEnabled(Integer enabled) {
		this.enabled = enabled;
	}

	public String getDispatch_name() {
		return dispatch_name;
	}

	public void setDispatch_name(String dispatch_name) {
		this.dispatch_name = dispatch_name;
	}

	public String getDispatch_mobile() {
		return dispatch_mobile;
	}

	public void setDispatch_mobile(String dispatch_mobile) {
		this.dispatch_mobile = dispatch_mobile;
	}

	public Integer getRent_status() {
		return rent_status;
	}

	public void setRent_status(Integer rent_status) {
		this.rent_status = rent_status;
	}

	public Integer getDel_flg() {
		return del_flg;
	}

	public void setDel_flg(Integer del_flg) {
		this.del_flg = del_flg;
	}

}