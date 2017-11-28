package com.cocosh.sys.mapper;

import java.util.List;

import com.cocosh.sys.model.UserRole;

public interface UserRoleMapper {
	Integer add(UserRole uRole);
	Integer del(UserRole uRole);
	List<UserRole> query(UserRole uRole);
	List<String> queryAvailable(String user_id);
	String queryRolesByUser(String uid);
}
