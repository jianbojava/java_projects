package com.cocosh.sys.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cocosh.framework.annotation.LogClass;
import com.cocosh.framework.base.BaseConditionVO;
import com.cocosh.framework.mybatis.Page;
import com.cocosh.framework.mybatis.PaginationInterceptor;
import com.cocosh.framework.util.StringUtil;
import com.cocosh.sys.mapper.PermissionMapper;
import com.cocosh.sys.mapper.RolePermMapper;
import com.cocosh.sys.model.Permission;
import com.cocosh.sys.model.RolePermission;
import com.cocosh.sys.service.PermissionService;

@Transactional
@Service
public class PermissionServiceImpl implements PermissionService {
	@Autowired
	private PermissionMapper mapper;
	@Autowired
	private RolePermMapper rolePermMapper;

	@LogClass(module = "资源管理", method = "添加")
	@Override
	public boolean add(Permission perm) {
		perm.setId(StringUtil.getUuid());
		return mapper.add(perm) > 0 ? true : false;
	}

	@LogClass(module = "资源管理", method = "删除")
	@Override
	public boolean del(String ids) {
		String[] id_arr=ids.split(",");
		mapper.del(id_arr);
		//删除role_perm关系
		for (int i = 0; i < id_arr.length; i++) {
			RolePermission rp=new RolePermission();
			rp.setPermId(id_arr[i]);
			rolePermMapper.del(rp);
		}
		return true;
	}

	@LogClass(module = "资源管理", method = "修改")
	@Override
	public boolean update(Permission perm) {
		return mapper.update(perm) > 0 ? true : false;
	}

	@Override
	public Page<Permission> queryPage(BaseConditionVO vo) {
		PaginationInterceptor.startPage(vo.getPageNo(),vo.getPageSize());
		mapper.queryPage(vo);
		return PaginationInterceptor.endPage();
	}

	@Override
	public Permission queryById(String id) {
		return mapper.queryById(id);
	}

	@Override
	public List<Permission> queryRootPerm() {
		Permission perm=new Permission();
		perm.setparent_id("0");
		return null;
	}

	@Override
	public List<Permission> queryAll() {
		return mapper.queryAll();
	}

	@Override
	public List<Permission> queryRoot() {
		return mapper.queryRoot();
	}

}
