package com.cocosh.sys.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cocosh.sys.mapper.UserRoleMapper;
import com.cocosh.sys.service.UserRoleService;

@Transactional
@Service
public class UserRoleServiceImpl implements UserRoleService {
	@Autowired
	private UserRoleMapper userRoleMapper;

	@Override
	public List<String> queryAvailable(String user_id) {
		return userRoleMapper.queryAvailable(user_id);
	}
}
