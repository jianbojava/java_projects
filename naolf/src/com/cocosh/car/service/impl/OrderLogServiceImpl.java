package com.cocosh.car.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cocosh.framework.util.StringUtil;
import com.cocosh.car.mapper.OrderLogMapper;
import com.cocosh.car.model.OrderLog;
import com.cocosh.car.service.OrderLogService;

@Transactional
@Service
public class OrderLogServiceImpl implements OrderLogService {
	@Autowired
	private OrderLogMapper mapper;

	@Override
	public boolean add(OrderLog po) {
		po.setId(StringUtil.getUuid());
		return mapper.add(po) > 0;
	}

	@Override
	public List<OrderLog> query(String id) {
		return mapper.query(id);
	}
	
}
