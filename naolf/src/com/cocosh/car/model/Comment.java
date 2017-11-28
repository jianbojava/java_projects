package com.cocosh.car.model;

import com.cocosh.framework.base.BaseEntity;

public class Comment extends BaseEntity {
	private String mem_id;// 评论人
	private String car_id;// 车辆ID
	private String station_id;// 充电站ID
	private String content;// 评论内容
	private String images;// 图片
	private Integer star;// 星级
	private Integer enabled;// 0正常 1屏蔽

	/***** 非数据库字段 *******/
	private String mem_name;// 评论人姓名
	private String mem_mobile;// 评论人电话
	private String mem_type;// 会员类型
	private String mem_headimg;// 会员头像
	private String car_number;// 车辆编号
	private String car_name;// 车辆名称
	private String station_name;// 电站名称

	public String getMem_id() {
		return mem_id;
	}

	public void setMem_id(String mem_id) {
		this.mem_id = mem_id;
	}

	public String getCar_id() {
		return car_id;
	}

	public void setCar_id(String car_id) {
		this.car_id = car_id;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getImages() {
		return images;
	}

	public void setImages(String images) {
		this.images = images;
	}

	public Integer getStar() {
		return star;
	}

	public void setStar(Integer star) {
		this.star = star;
	}

	public Integer getEnabled() {
		return enabled;
	}

	public void setEnabled(Integer enabled) {
		this.enabled = enabled;
	}

	public String getMem_name() {
		return mem_name;
	}

	public void setMem_name(String mem_name) {
		this.mem_name = mem_name;
	}

	public String getMem_mobile() {
		return mem_mobile;
	}

	public void setMem_mobile(String mem_mobile) {
		this.mem_mobile = mem_mobile;
	}

	public String getMem_type() {
		return mem_type;
	}

	public void setMem_type(String mem_type) {
		this.mem_type = mem_type;
	}

	public String getMem_headimg() {
		return mem_headimg;
	}

	public void setMem_headimg(String mem_headimg) {
		this.mem_headimg = mem_headimg;
	}

	public String getCar_number() {
		return car_number;
	}

	public void setCar_number(String car_number) {
		this.car_number = car_number;
	}

	public String getCar_name() {
		return car_name;
	}

	public void setCar_name(String car_name) {
		this.car_name = car_name;
	}

	public String getStation_id() {
		return station_id;
	}

	public void setStation_id(String station_id) {
		this.station_id = station_id;
	}

	public String getStation_name() {
		return station_name;
	}

	public void setStation_name(String station_name) {
		this.station_name = station_name;
	}

}