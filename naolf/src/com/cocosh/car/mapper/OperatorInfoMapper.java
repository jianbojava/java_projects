package com.cocosh.car.mapper;

import java.util.List;
import java.util.Map;

import com.cocosh.framework.base.BaseConditionVO;
import com.cocosh.car.model.OperatorInfo;

public interface OperatorInfoMapper {
	Integer add(List<OperatorInfo> list);
	Integer del(Map<String,Object> map);
	Integer update(OperatorInfo po);
	List<OperatorInfo> queryPage(BaseConditionVO vo);
	OperatorInfo queryById(String id);
	List<OperatorInfo> queryAll();
}
