package com.cocosh.sys.model;

/**
 * 用户-角色关联
 * 
 * @author jerry
 */
public class UserRole {
	private String user_id;
	private String role_id;

	public String getUserId() {
		return user_id;
	}

	public void setUserId(String user_id) {
		this.user_id = user_id;
	}

	public String getRoleId() {
		return role_id;
	}

	public void setRoleId(String role_id) {
		this.role_id = role_id;
	}
}
