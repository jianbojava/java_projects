package com.cocosh.car.mapper;

import java.util.List;
import java.util.Map;

import com.cocosh.framework.base.BaseConditionVO;
import com.cocosh.car.model.Charge;

public interface ChargeMapper {
	Integer add(Charge po);
	Integer del(Map<String,Object> map);
	Integer update(Charge po);
	List<Charge> queryPage(BaseConditionVO vo);
	Charge queryById(String id);
}
