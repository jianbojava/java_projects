package com.cocosh.nlf.service;
 
import com.cocosh.framework.base.BaseConditionVO;
import com.cocosh.framework.mybatis.Page;
import com.cocosh.nlf.model.OrderDisRateSetting;

public interface OrderDisRateSettingService {
	boolean update(OrderDisRateSetting po);
	Page<OrderDisRateSetting> queryPage(BaseConditionVO vo);
}
