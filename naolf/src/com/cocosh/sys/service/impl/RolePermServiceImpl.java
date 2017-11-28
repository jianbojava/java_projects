package com.cocosh.sys.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cocosh.sys.mapper.RolePermMapper;
import com.cocosh.sys.service.RolePermService;

@Transactional
@Service
public class RolePermServiceImpl implements RolePermService {
	@Autowired
	private RolePermMapper rolePermMapper;

	@Override
	public List<String> queryAvailable(String user_id) {
		return rolePermMapper.queryAvailable(user_id);
	}

}
