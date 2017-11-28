package com.cocosh.car.mapper;

import java.util.List;

import com.cocosh.framework.base.BaseConditionVO;
import com.cocosh.car.model.RefundDeposit;

public interface RefundDepositMapper {
	Integer add(RefundDeposit po);
	Integer update(RefundDeposit po);
	List<RefundDeposit> queryPage(BaseConditionVO vo);
	List<RefundDeposit> queryBySns(String[] sns);
}
