package com.cocosh.car.mapper;

import java.util.List;
import java.util.Map;

import com.cocosh.framework.base.BaseConditionVO;
import com.cocosh.car.model.StationInfo;

public interface StationInfoMapper {
	Integer add(List<StationInfo> list);
	Integer del(Map<String,Object> map);
	Integer delT(String[] ids);
	Integer update(StationInfo po);
	List<StationInfo> queryPage(BaseConditionVO vo);
	StationInfo queryById(String id);
	List<StationInfo> queryAll();
	List<StationInfo> query2Map(BaseConditionVO vo);
	List<StationInfo> queryPageApp(BaseConditionVO vo);
	StationInfo queryByIdApp(BaseConditionVO vo);
}
