package com.cocosh.nlf.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cocosh.framework.annotation.LogClass;
import com.cocosh.framework.base.BaseConditionVO;
import com.cocosh.framework.mybatis.Page;
import com.cocosh.framework.mybatis.PaginationInterceptor;
import com.cocosh.nlf.mapper.OrderDisRateSettingMapper;
import com.cocosh.nlf.model.OrderDisRateSetting;
import com.cocosh.nlf.service.OrderDisRateSettingService;

@Transactional
@Service
public class OrderDisRateSettingServiceImpl implements OrderDisRateSettingService {
	@Autowired
	private OrderDisRateSettingMapper mapper;

	
	
	@LogClass(module = "员工提成积分比例设置管理", method = "修改")
	@Override
	public boolean update(OrderDisRateSetting po) {
		return mapper.update(po) > 0;
	}

	@Override
	public Page<OrderDisRateSetting> queryPage(BaseConditionVO vo) {
		PaginationInterceptor.startPage(vo.getPageNo(),vo.getPageSize());
		mapper.queryPage(vo);
		Page<OrderDisRateSetting> page=PaginationInterceptor.endPage();
		return page;
	}

	
	
}
