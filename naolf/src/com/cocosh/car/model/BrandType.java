package com.cocosh.car.model;


import java.io.Serializable;
/**
 * 汽车品牌型号,对应zxj_brand_type
 * @author bobo
 *
 */
public class BrandType implements Serializable {
 	private String id;
 	private String name;
 	private Integer sort;
 	private String brand_id;
 	
 	public String getId() {
 		return id;
 	}
 	
 	public void setId(String id) {
 		this.id = id;
 	}
 	
 	public String getName() {
 		return name;
 	}
 	
 	public void setName(String name) {
 		this.name = name;
 	}
 	
 	public Integer getSort() {
 		return sort;
 	}
 	
 	public void setSort(Integer sort) {
 		this.sort = sort;
 	}
 	
 	public String getBrand_id() {
 		return brand_id;
 	}
 	
 	public void setBrand_id(String brand_id) {
 		this.brand_id = brand_id;
 	}
}