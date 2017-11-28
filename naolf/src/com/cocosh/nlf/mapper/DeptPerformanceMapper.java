package com.cocosh.nlf.mapper;

import java.util.List;

import com.cocosh.framework.base.BaseConditionVO;
import com.cocosh.nlf.model.DeptPerformance;

public interface DeptPerformanceMapper {
	
	List<DeptPerformance> queryPage(BaseConditionVO vo);
	List<DeptPerformance> queryDetailPage(BaseConditionVO vo);
	
	Integer addDepartScore(DeptPerformance po);//给部门添加订单绩效
	
}
