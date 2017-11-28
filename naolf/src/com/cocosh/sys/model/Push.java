package com.cocosh.sys.model;

import java.util.ArrayList;
import java.util.List;

import com.cocosh.framework.base.BaseEntity;

public class Push extends BaseEntity {
	private String content;// 推送内容
	private String alias;// 推送目标
	private Integer application;// 应用 0：客户端 1：商户端
	private Integer platform;// 平台 0：Android&ios 1：ios 2：Android 3：个人

	/** 非数据库字段 **/
	private List<String> users = new ArrayList<String>();// 推送用户列表

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getAlias() {
		return alias;
	}

	public void setAlias(String alias) {
		this.alias = alias;
	}

	public Integer getApplication() {
		return application;
	}

	public void setApplication(Integer application) {
		this.application = application;
	}

	public Integer getPlatform() {
		return platform;
	}

	public void setPlatform(Integer platform) {
		this.platform = platform;
	}

	public List<String> getUsers() {
		return users;
	}

	public void setUsers(List<String> users) {
		this.users = users;
	}

}
