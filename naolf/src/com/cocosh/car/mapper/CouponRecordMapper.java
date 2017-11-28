package com.cocosh.car.mapper;

import java.util.List;

import com.cocosh.framework.base.BaseConditionVO;
import com.cocosh.car.model.CouponRecord;

public interface CouponRecordMapper {
	Integer add(CouponRecord po);
	Integer del(String[] ids);
	Integer update(CouponRecord po);
	List<CouponRecord> queryPage(BaseConditionVO vo);
	CouponRecord queryById(String id);
	
	List<CouponRecord> queryAllValid(CouponRecord po);
}
