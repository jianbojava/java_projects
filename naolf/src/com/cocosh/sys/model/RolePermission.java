package com.cocosh.sys.model;

/**
 * 角色-权限关联
 * 
 * @author jerry
 */
public class RolePermission {
	private String role_id;
	private String perm_id;

	public String getRoleId() {
		return role_id;
	}

	public void setRoleId(String role_id) {
		this.role_id = role_id;
	}

	public String getPermId() {
		return perm_id;
	}

	public void setPermId(String perm_id) {
		this.perm_id = perm_id;
	}
}
