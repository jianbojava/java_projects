package com.cocosh.car.service;
 
import java.util.List;

import com.cocosh.framework.base.BaseConditionVO;
import com.cocosh.framework.mybatis.Page;
import com.cocosh.car.model.CouponRecord;

public interface CouponRecordService {
	boolean add(CouponRecord po);
	boolean del(String ids);
	boolean update(CouponRecord po);
	Page<CouponRecord> queryPage(BaseConditionVO vo);
	CouponRecord queryById(String id);
	
	List<CouponRecord> queryAllValid(CouponRecord po);
}
