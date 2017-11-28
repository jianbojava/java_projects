package com.cocosh.nlf.model;



import com.cocosh.framework.base.BaseEntity;

/**
 * 编号规则 nlf_rule
 * @author bobo
 *
 */
public class Rule extends BaseEntity {
 	private String name;
 	private String sn_header;
 	private String sn_start;
 	private String sn_middle;
 	private String sn_end;
 	private Integer sort;
 	private Integer enabled;
 	
 	public String getName() {
 		return name;
 	}
 	
 	public void setName(String name) {
 		this.name = name;
 	}
 	
 	public String getSn_header() {
 		return sn_header;
 	}
 	
 	public void setSn_header(String sn_header) {
 		this.sn_header = sn_header;
 	}
 	
 	public String getSn_start() {
 		return sn_start;
 	}
 	
 	public void setSn_start(String sn_start) {
 		this.sn_start = sn_start;
 	}
 	
 	public String getSn_middle() {
 		return sn_middle;
 	}
 	
 	public void setSn_middle(String sn_middle) {
 		this.sn_middle = sn_middle;
 	}
 	
 	public String getSn_end() {
 		return sn_end;
 	}
 	
 	public void setSn_end(String sn_end) {
 		this.sn_end = sn_end;
 	}
 	
 	public Integer getSort() {
 		return sort;
 	}
 	
 	public void setSort(Integer sort) {
 		this.sort = sort;
 	}
 	
 	public Integer getEnabled() {
 		return enabled;
 	}
 	
 	public void setEnabled(Integer enabled) {
 		this.enabled = enabled;
 	}
}