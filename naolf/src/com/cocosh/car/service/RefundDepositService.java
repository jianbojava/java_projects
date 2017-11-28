package com.cocosh.car.service;
 
import com.cocosh.framework.base.BaseConditionVO;
import com.cocosh.framework.mybatis.Page;
import com.cocosh.car.model.RefundDeposit;

public interface RefundDepositService {
	boolean add(RefundDeposit po);
	boolean update(RefundDeposit po);
	Page<RefundDeposit> queryPage(BaseConditionVO vo);
}
