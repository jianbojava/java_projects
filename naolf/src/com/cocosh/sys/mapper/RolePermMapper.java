package com.cocosh.sys.mapper;

import java.util.List;

import com.cocosh.sys.model.RolePermission;

public interface RolePermMapper {
	Integer add(List<RolePermission> rPerm);
	Integer del(RolePermission rPerm);
	List<RolePermission> query(RolePermission rPerm);
	List<String> queryAvailable(String user_id);
}
