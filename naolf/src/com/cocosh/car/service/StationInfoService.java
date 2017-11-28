package com.cocosh.car.service;
 
import java.util.List;

import com.cocosh.framework.base.BaseConditionVO;
import com.cocosh.framework.mybatis.Page;
import com.cocosh.car.model.StationInfo;

public interface StationInfoService {
	boolean add(StationInfo po);
	boolean del(Integer flg,String ids);
	boolean delT(String ids);
	boolean update(StationInfo po);
	Page<StationInfo> queryPage(BaseConditionVO vo);
	StationInfo queryById(String id);
	List<StationInfo> queryAll();
	List<StationInfo> query2Map(BaseConditionVO vo);
	List<StationInfo> queryPageApp(BaseConditionVO vo);
	StationInfo queryByIdApp(BaseConditionVO vo);
}
