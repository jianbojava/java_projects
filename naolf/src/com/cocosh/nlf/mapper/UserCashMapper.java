package com.cocosh.nlf.mapper;

import java.util.List;

import com.cocosh.framework.base.BaseConditionVO;
import com.cocosh.nlf.model.UserCash;

public interface UserCashMapper {
	Integer add(UserCash po);
	Integer del(String[] ids);
	Integer update(UserCash po);
	List<UserCash> queryPage(BaseConditionVO vo);
	UserCash queryById(String id);
	List<UserCash> queryWapAll(UserCash po);
	
	
	String pay(String id);
	String WithdrawalsId(String id);
}
