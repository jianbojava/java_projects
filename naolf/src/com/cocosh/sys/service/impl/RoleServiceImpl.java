package com.cocosh.sys.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cocosh.framework.annotation.LogClass;
import com.cocosh.framework.base.BaseConditionVO;
import com.cocosh.framework.mybatis.Page;
import com.cocosh.framework.mybatis.PaginationInterceptor;
import com.cocosh.framework.util.StringUtil;
import com.cocosh.sys.mapper.RoleMapper;
import com.cocosh.sys.mapper.RolePermMapper;
import com.cocosh.sys.model.Role;
import com.cocosh.sys.model.RolePermission;
import com.cocosh.sys.service.RoleService;

@Transactional
@Service
public class RoleServiceImpl implements RoleService {
	@Autowired
	private RoleMapper mapper;
	@Autowired
	private RolePermMapper rolePermMapper;

	@LogClass(module = "角色管理", method = "添加")
	@Override
	public boolean add(Role role) {
		//添加角色
		String role_id=StringUtil.getUuid();
		role.setId(role_id);
		mapper.add(role);
		//添加对应关联
		List<RolePermission> list=new ArrayList<RolePermission>();
		String[] perm_ids=role.getPerms().split(",");
		RolePermission rp=null;
		for (int i = 0; i < perm_ids.length; i++) {
			rp=new RolePermission();
			rp.setRoleId(role_id);
			rp.setPermId(perm_ids[i]);
			list.add(rp);
		}
		return rolePermMapper.add(list)>0;
	}

	@LogClass(module = "角色管理", method = "操作")
	@Override
	public boolean del(Integer flg,String ids) {
    	Map<String, Object> map = new HashMap<String, Object>();
		map.put("del_flg", flg);
		map.put("del_ids", ids.split(","));
		return mapper.del(map) > 0;
	}

	@LogClass(module = "角色管理", method = "修改")
	@Override
	public boolean update(Role role) {
		mapper.update(role);
		//删除role_perm关系
		RolePermission rp=new RolePermission();
		rp.setRoleId(role.getId());
		rolePermMapper.del(rp);
		//构建role_perm关系
		//添加对应关联
		List<RolePermission> list=new ArrayList<RolePermission>();
		String[] perm_ids=role.getPerms().split(",");
		for (int i = 0; i < perm_ids.length; i++) {
			rp=new RolePermission();
			rp.setRoleId(role.getId());
			rp.setPermId(perm_ids[i]);
			list.add(rp);
		}
		return rolePermMapper.add(list)>0;
	}

	@Override
	public Page<Role> queryPage(BaseConditionVO vo) {
		PaginationInterceptor.startPage(vo.getPageNo(),vo.getPageSize());
		mapper.queryPage(vo);
		return PaginationInterceptor.endPage();
	}

	@Override
	public Role queryById(String id) {
		Role role=mapper.queryById(id);
		if (role!=null) {
			//获取这个role所拥有的权限
			String perms="";
			RolePermission rp=new RolePermission();
			rp.setRoleId(id);
			List<RolePermission> rps=rolePermMapper.query(rp);
			if (rps!=null&&rps.size()>0) {
				for(RolePermission r:rps){
					perms+=r.getPermId()+",";
				}
				perms=perms.substring(0, perms.length()-1);
			}
			role.setPerms(perms);
			return role;
		}
		return null;
	}

	/* (non-Javadoc)
	 * @see com.cocosh.sys.service.RoleService#queryNameByUserId(java.lang.String)
	 */
	@Override
	public List<Role> queryNameByUserId(String user_id) {
		return mapper.queryNameByUserId(user_id);
	}

	@Override
	public List<Role> queryAll() {
		return mapper.queryAll();
	}

}
