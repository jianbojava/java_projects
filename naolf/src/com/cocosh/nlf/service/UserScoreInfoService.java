package com.cocosh.nlf.service;
 
import java.math.BigDecimal;
import java.util.List;

import com.cocosh.framework.base.BaseConditionVO;
import com.cocosh.framework.mybatis.Page;
import com.cocosh.nlf.model.UserScoreInfo;

public interface UserScoreInfoService {
//	boolean add(UserScoreInfo po);
	boolean del(String ids);
	boolean update(UserScoreInfo po);
	Page<UserScoreInfo> queryPage(BaseConditionVO vo);
	UserScoreInfo queryByUser(String userid);
	
	Integer getDispatchGrade(String user_number);
	String getDepart1grade(String depart_id);
	
	Integer buyKD_score(String user_id,BigDecimal KD_SCORE);//前端积分购买充值bobo0317
	boolean pay_success(String sn,String trade_no,Integer pay_type);//支付成功后调用,sn代表paymnetlog的sn
}
