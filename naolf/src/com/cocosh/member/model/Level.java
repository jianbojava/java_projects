package com.cocosh.member.model;

import com.cocosh.framework.base.BaseEntity;

/**
 * 会员积分等级,对应zxj_level
 * 
 * @author bobo
 * 
 */
public class Level extends BaseEntity {
	private String name;// 名称
	private String image;// 图片
	private String remark;// 描述
	private Integer minimum;// 积分小区间
	private Integer maximum;// 积分小区间
	private Integer enabled;// 0正常 1禁用

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Integer getMinimum() {
		return minimum;
	}

	public void setMinimum(Integer minimum) {
		this.minimum = minimum;
	}

	public Integer getMaximum() {
		return maximum;
	}

	public void setMaximum(Integer maximum) {
		this.maximum = maximum;
	}

	public Integer getEnabled() {
		return enabled;
	}

	public void setEnabled(Integer enabled) {
		this.enabled = enabled;
	}

}