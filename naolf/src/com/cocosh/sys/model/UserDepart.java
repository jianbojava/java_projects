package com.cocosh.sys.model;

/**
 * 用户-部门数据关联
 * 
 * @author jerry
 */
public class UserDepart {
	private String user_id;
	private String depart_id;

	public String getUser_id() {
		return user_id;
	}

	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}

	public String getDepart_id() {
		return depart_id;
	}

	public void setDepart_id(String depart_id) {
		this.depart_id = depart_id;
	}
}
