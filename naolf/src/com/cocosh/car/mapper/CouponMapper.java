package com.cocosh.car.mapper;

import java.util.List;

import com.cocosh.framework.base.BaseConditionVO;
import com.cocosh.car.model.Coupon;

public interface CouponMapper {
	Integer add(Coupon po);
	Integer del(String[] ids);
	Integer update(Coupon po);
	List<Coupon> queryPage(BaseConditionVO vo);
	Coupon queryById(String id);
}
