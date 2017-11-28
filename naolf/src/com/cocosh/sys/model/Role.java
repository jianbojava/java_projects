package com.cocosh.sys.model;

import com.cocosh.framework.base.BaseEntity;

/**
 * 角色
 * 
 * @author jerry
 */
public class Role extends BaseEntity {
	private static final long serialVersionUID = 5702979154682486632L;
	private String name;// 名称
	private String description;// 描述
	private int available;// 是否可用
	private String perms;//资源集合(辅助属性)

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getAvailable() {
		return available;
	}

	public void setAvailable(int available) {
		this.available = available;
	}

	public String getPerms() {
		return perms;
	}

	public void setPerms(String perms) {
		this.perms = perms;
	}
}
