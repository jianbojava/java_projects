package com.cocosh.sys.model;

import java.util.List;

import com.cocosh.framework.base.BaseEntity;

public class Dict extends BaseEntity {
	private String code;
	private String name;
	private String content;
	private Integer grade;
	private String p_code;
	private String remark;
	private Integer enabled;

	/*** 非数据库字段 ***/
	private List<Dict> cDicts;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Integer getGrade() {
		return grade;
	}

	public void setGrade(Integer grade) {
		this.grade = grade;
	}

	public String getP_code() {
		return p_code;
	}

	public void setP_code(String p_code) {
		this.p_code = p_code;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Integer getEnabled() {
		return enabled;
	}

	public void setEnabled(Integer enabled) {
		this.enabled = enabled;
	}

	public List<Dict> getcDicts() {
		return cDicts;
	}

	public void setcDicts(List<Dict> cDicts) {
		this.cDicts = cDicts;
	}
}