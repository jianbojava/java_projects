package com.cocosh.sys.model;

import java.util.List;

import com.cocosh.framework.base.BaseEntity;

/**
 * 权限
 * 
 * @author jerry
 */
public class Permission extends BaseEntity {
	private static final long serialVersionUID = 4627030362646427895L;
	private String name;// 名称
	private String description;// 描述
	private String permission;// 权限字符串
	private String parent_id;// 父编号
	private int sort;// 排序值
	private int available;// 是否可用
	private List<Permission> childPers;

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

	public String getPermission() {
		return permission;
	}

	public void setPermission(String permission) {
		this.permission = permission;
	}

	public String getparent_id() {
		return parent_id;
	}

	public void setparent_id(String parent_id) {
		this.parent_id = parent_id;
	}

	public int getSort() {
		return sort;
	}

	public void setSort(int sort) {
		this.sort = sort;
	}

	public int getAvailable() {
		return available;
	}

	public void setAvailable(int available) {
		this.available = available;
	}

	public List<Permission> getChildPers() {
		return childPers;
	}

	public void setChildPers(List<Permission> childPers) {
		this.childPers = childPers;
	}

}
