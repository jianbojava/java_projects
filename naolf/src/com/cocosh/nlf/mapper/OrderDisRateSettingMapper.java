package com.cocosh.nlf.mapper;

import java.util.List;

import com.cocosh.framework.base.BaseConditionVO;
import com.cocosh.nlf.model.OrderDisRateSetting;

public interface OrderDisRateSettingMapper {
	Integer add(OrderDisRateSetting po);
	Integer del(String[] ids);
	Integer update(OrderDisRateSetting po);
	List<OrderDisRateSetting> queryPage(BaseConditionVO vo);
	OrderDisRateSetting queryById(String id);
	OrderDisRateSetting queryByRate_type(String rate_type);
}
