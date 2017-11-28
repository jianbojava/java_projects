package com.cocosh.nlf.service;
 
import com.cocosh.framework.base.BaseConditionVO;
import com.cocosh.framework.mybatis.Page;
import com.cocosh.nlf.model.DeptPerformance;

public interface DeptPerformanceService {
	Page<DeptPerformance> queryPage(BaseConditionVO vo);
	Page<DeptPerformance> queryDetailPage(BaseConditionVO vo);
}
