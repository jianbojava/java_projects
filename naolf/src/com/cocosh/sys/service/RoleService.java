package com.cocosh.sys.service;

import java.util.List;

import com.cocosh.framework.base.BaseConditionVO;
import com.cocosh.framework.mybatis.Page;
import com.cocosh.sys.model.Role;

public interface RoleService {
	boolean add(Role role);
	boolean del(Integer flg,String ids);
	boolean update(Role role);
	Page<Role> queryPage(BaseConditionVO vo);
	List<Role> queryAll();
	Role queryById(String id);
	/**
	 * 根据user_id获取角色信息
	 */
	List<Role> queryNameByUserId(String user_id);
}
