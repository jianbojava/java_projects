package com.cocosh.sys.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cocosh.sys.mapper.RegionsMapper;
import com.cocosh.sys.model.Regions;
import com.cocosh.sys.service.RegionsService;

@Transactional
@Service
public class RegionsServiceImpl implements RegionsService {
	@Autowired
	private RegionsMapper mapper;

	@Override
	public List<Regions> queryByPId(String id) {
		return mapper.queryByPId(id);
	}
	
}
