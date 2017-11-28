package com.cocosh.sys.mapper;

import java.util.List;

import com.cocosh.framework.base.BaseConditionVO;
import com.cocosh.sys.model.Permission;

public interface PermissionMapper {
	Integer add(Permission perm);
	Integer del(String[] ids);
	Integer update(Permission perm);
	List<Permission> queryPage(BaseConditionVO vo);
	List<Permission> queryRoot();
	Integer queryCount(Permission perm);
	List<Permission> queryAll();
	Permission queryById(String id);
}
