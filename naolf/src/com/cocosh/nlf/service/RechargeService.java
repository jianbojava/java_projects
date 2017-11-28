package com.cocosh.nlf.service;
 
import java.util.List;

import com.cocosh.framework.base.BaseConditionVO;
import com.cocosh.framework.mybatis.Page;
import com.cocosh.nlf.model.Recharge;

public interface RechargeService {
	boolean add(Recharge po);
	boolean del(String ids);
	boolean update(Recharge po);
	Page<Recharge> queryPage(BaseConditionVO vo);
	Page<Recharge> querySumPage(BaseConditionVO vo);//用户大概充值列表
	Recharge queryById(String id);
	List<Recharge> queryAll();
	String generatId();
}
