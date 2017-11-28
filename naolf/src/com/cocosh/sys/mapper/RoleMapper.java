package com.cocosh.sys.mapper;

import java.util.List;
import java.util.Map;

import com.cocosh.framework.base.BaseConditionVO;
import com.cocosh.sys.model.Role;

public interface RoleMapper {
	Integer add(Role role);
	Integer del(Map<String, Object> map);
	Integer update(Role role);
	List<Role> queryPage(BaseConditionVO vo);
	List<Role>queryAll();
	List<Role> queryNameByUserId(String user_id);
	Role queryById(String id);
}
