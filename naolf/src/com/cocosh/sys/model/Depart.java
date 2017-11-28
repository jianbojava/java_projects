package com.cocosh.sys.model;


import java.util.ArrayList;
import java.util.List;

import com.cocosh.framework.base.BaseEntity;
/**
 * 系统部门表 sys_depart
 * @author bobo
 */

public class Depart extends BaseEntity {
 	private String name;
 	private String parent_id;
 	private Integer sort;//顺序
 	private String description;
 	private Integer enabled;//0启用，1禁用
 	private Integer grade;//等级：1最高级,2,3依次排序
 	private String province_id;
 	private String province_name;
	private String city_id;
 	private String city_name;
 	private String region_id;
 	private String region_name;
 	private String address;
 	private Integer type ;//0是分中心，1不是分中心
 	//0613后期增加
	private Double longitude;
	private Double latitude;
 	
 	private Integer performance_ind;//是否绩效部门 add by wjx 20170227
	public Integer getPerformance_ind() {
		return performance_ind;
	}

	public void setPerformance_ind(Integer performance_ind) {
		this.performance_ind = performance_ind;
	}

	/**非数据库字段---子类**/
    private List<Depart> child=new ArrayList<Depart>();
 	
 	public List<Depart> getChild() {
		return child;
	}

	public void setChild(List<Depart> child) {
		this.child = child;
	}

	public String getName() {
 		return name;
 	}
 	
 	public void setName(String name) {
 		this.name = name;
 	}
 	
 	public String getParent_id() {
 		return parent_id;
 	}
 	
 	public void setParent_id(String parent_id) {
 		this.parent_id = parent_id;
 	}
 	
 	public Integer getSort() {
 		return sort;
 	}
 	
 	public void setSort(Integer sort) {
 		this.sort = sort;
 	}
 	
 	public String getDescription() {
 		return description;
 	}
 	
 	public void setDescription(String description) {
 		this.description = description;
 	}
 	
 	public Integer getEnabled() {
 		return enabled;
 	}
 	
 	public void setEnabled(Integer enabled) {
 		this.enabled = enabled;
 	}
 	
 	public Integer getGrade() {
 		return grade;
 	}
 	
 	public void setGrade(Integer grade) {
 		this.grade = grade;
 	}
 	
 	public String getProvince_id() {
		return province_id;
	}

	public void setProvince_id(String province_id) {
		this.province_id = province_id;
	}

	public String getProvince_name() {
		return province_name;
	}

	public void setProvince_name(String province_name) {
		this.province_name = province_name;
	}

	public String getCity_id() {
		return city_id;
	}

	public void setCity_id(String city_id) {
		this.city_id = city_id;
	}

	public String getCity_name() {
		return city_name;
	}

	public void setCity_name(String city_name) {
		this.city_name = city_name;
	}

	public String getRegion_id() {
		return region_id;
	}

	public void setRegion_id(String region_id) {
		this.region_id = region_id;
	}

	public String getRegion_name() {
		return region_name;
	}

	public void setRegion_name(String region_name) {
		this.region_name = region_name;
	}
	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}
	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
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

}