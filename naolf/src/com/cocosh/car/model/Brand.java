package com.cocosh.car.model;

import java.util.List;

import com.cocosh.framework.base.BaseEntity;

/**
 * 汽车品牌,对应表zxj_brand
 * 
 * @author bobo
 * 
 */
public class Brand extends BaseEntity {
	private String name;
	private String logo;
	private String brief;
	private String url;// 链接
	private Integer sort;// 排序值
	private Integer type;// 类型 0：车辆品牌 1：充电桩品牌
	private Integer enabled;// 0 有效 1无效
	// 非数据库字段
	private List<BrandType> types;// 品牌型号列表
	private String type_names;// 品牌型号字符串

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLogo() {
		return logo;
	}

	public void setLogo(String logo) {
		this.logo = logo;
	}

	public String getBrief() {
		return brief;
	}

	public void setBrief(String brief) {
		this.brief = brief;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Integer getSort() {
		return sort;
	}

	public void setSort(Integer sort) {
		this.sort = sort;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public Integer getEnabled() {
		return enabled;
	}

	public void setEnabled(Integer enabled) {
		this.enabled = enabled;
	}

	public List<BrandType> getTypes() {
		return types;
	}

	public void setTypes(List<BrandType> types) {
		this.types = types;
	}

	public String getType_names() {
		return type_names;
	}

	public void setType_names(String type_names) {
		this.type_names = type_names;
	}

}