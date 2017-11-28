package com.cocosh.car.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.cocosh.framework.annotation.LogClass;

import com.cocosh.framework.base.BaseConditionVO;
import com.cocosh.framework.mybatis.Page;
import com.cocosh.framework.mybatis.PaginationInterceptor;
import com.cocosh.framework.util.StringUtil;
import com.cocosh.car.mapper.CouponRecordMapper;
import com.cocosh.car.model.CouponRecord;
import com.cocosh.car.service.CouponRecordService;

@Transactional
@Service
public class CouponRecordServiceImpl implements CouponRecordService {
	@Autowired
	private CouponRecordMapper mapper;

	@LogClass(module = "优惠券记录管理", method = "添加")
	@Override
	public boolean add(CouponRecord po) {
		po.setId(StringUtil.getUuid());
		return mapper.add(po) > 0;
	}
	
    @LogClass(module = "优惠券记录管理", method = "删除")
	@Override
	public boolean del(String ids) {
		return mapper.del(ids.split(",")) > 0;
	}

	@LogClass(module = "优惠券记录管理", method = "修改")
	@Override
	public boolean update(CouponRecord po) {
		return mapper.update(po) > 0;
	}

	@Override
	public Page<CouponRecord> queryPage(BaseConditionVO vo) {
		PaginationInterceptor.startPage(vo.getPageNo(),vo.getPageSize());
		mapper.queryPage(vo);
		Page<CouponRecord> page=PaginationInterceptor.endPage();
		return page;
	}

	@Override
	public CouponRecord queryById(String id) {
		return mapper.queryById(id);
	}

	@Override
	public List<CouponRecord> queryAllValid(CouponRecord po) {
		return mapper.queryAllValid(po);
	}
	
}
