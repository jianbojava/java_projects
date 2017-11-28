package com.cocosh.car.service;
 
import com.cocosh.framework.base.BaseConditionVO;
import com.cocosh.framework.mybatis.Page;
import com.cocosh.car.model.Coupon;

public interface CouponService {
	boolean add(Coupon po);
	boolean del(String ids);
	boolean update(Coupon po);
	Page<Coupon> queryPage(BaseConditionVO vo);
	Coupon queryById(String id);
}
