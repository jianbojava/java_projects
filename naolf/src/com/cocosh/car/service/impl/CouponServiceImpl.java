package com.cocosh.car.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.cocosh.framework.annotation.LogClass;

import com.cocosh.framework.base.BaseConditionVO;
import com.cocosh.framework.mybatis.Page;
import com.cocosh.framework.mybatis.PaginationInterceptor;
import com.cocosh.framework.util.StringUtil;
import com.cocosh.car.mapper.CouponMapper;
import com.cocosh.car.model.Coupon;
import com.cocosh.car.service.CouponService;

@Transactional
@Service
public class CouponServiceImpl implements CouponService {
	@Autowired
	private CouponMapper mapper;

	@LogClass(module = "优惠券管理", method = "添加")
	@Override
	public boolean add(Coupon po) {
		po.setId(StringUtil.getUuid());
		return mapper.add(po) > 0;
	}
	
    @LogClass(module = "优惠券管理", method = "删除")
	@Override
	public boolean del(String ids) {
		return mapper.del(ids.split(",")) > 0;
	}

	@LogClass(module = "优惠券管理", method = "修改")
	@Override
	public boolean update(Coupon po) {
		return mapper.update(po) > 0;
	}

	@Override
	public Page<Coupon> queryPage(BaseConditionVO vo) {
		PaginationInterceptor.startPage(vo.getPageNo(),vo.getPageSize());
		mapper.queryPage(vo);
		Page<Coupon> page=PaginationInterceptor.endPage();
		return page;
	}

	@Override
	public Coupon queryById(String id) {
		return mapper.queryById(id);
	}
	
}
