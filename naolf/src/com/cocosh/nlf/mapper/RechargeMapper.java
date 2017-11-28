package com.cocosh.nlf.mapper;

import java.util.List;

import com.cocosh.framework.base.BaseConditionVO;
import com.cocosh.nlf.model.Recharge;

public interface RechargeMapper {
	Integer add(Recharge po);
	Integer del(String[] ids);
	Integer update(Recharge po);
	List<Recharge> queryPage(BaseConditionVO vo);
	List<Recharge> querySumPage(BaseConditionVO vo);
	Recharge queryById(String id);
	List<Recharge> queryAll();
	
	
	String generatId(String id);
}
