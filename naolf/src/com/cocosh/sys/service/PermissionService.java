package com.cocosh.sys.service;

import java.util.List;

import com.cocosh.framework.base.BaseConditionVO;
import com.cocosh.framework.mybatis.Page;
import com.cocosh.sys.model.Permission;

public interface PermissionService {
	boolean add(Permission perm);
	boolean del(String ids);
	boolean update(Permission perm);
	Page<Permission> queryPage(BaseConditionVO vo);
	Permission queryById(String id);
	List<Permission> queryRootPerm();
	List<Permission> queryAll();
	List<Permission> queryRoot();
}
