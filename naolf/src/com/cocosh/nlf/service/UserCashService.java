package com.cocosh.nlf.service;
 
import java.util.List;

import com.cocosh.framework.base.BaseConditionVO;
import com.cocosh.framework.mybatis.Page;
import com.cocosh.nlf.model.UserCash;

public interface UserCashService {
	boolean add(UserCash po);
	boolean del(String ids);
	boolean update(UserCash po);
	Page<UserCash> queryPage(BaseConditionVO vo);
	UserCash queryById(String id);
	List<UserCash> queryWapAll(UserCash po);
	
	
	String pay(String id);// 0-成功 1-用户积分不存在 2-用户可兑换积分不够
}
