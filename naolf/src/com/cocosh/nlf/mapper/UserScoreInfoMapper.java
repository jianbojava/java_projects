package com.cocosh.nlf.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.cocosh.framework.base.BaseConditionVO;
import com.cocosh.nlf.model.UserScoreInfo;

public interface UserScoreInfoMapper {
	Integer add(UserScoreInfo po);
	Integer del(String[] ids);
	Integer update(UserScoreInfo po);
	List<UserScoreInfo> queryList(UserScoreInfo po);
	List<UserScoreInfo> queryPage(BaseConditionVO vo);
	UserScoreInfo queryByUser(String userid);
	
	Integer getDispatchGrade(String user_number);
	String getDepart1grade(String depart_id);
	String orderPayByscore(@Param("params")Map<String, Object> params);
	String orderRet(@Param("params")Map<String, Object> params);
	Integer getDeptPerformce(String user_number);
}
