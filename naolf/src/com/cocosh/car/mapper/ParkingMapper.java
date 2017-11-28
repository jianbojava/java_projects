package com.cocosh.car.mapper;

import java.util.List;
import java.util.Map;

import com.cocosh.framework.base.BaseConditionVO;
import com.cocosh.car.model.Parking;

public interface ParkingMapper {
	Integer add(Parking po);
	Integer del(Map<String,Object> map);
	Integer update(Parking po);
	List<Parking> queryPage(BaseConditionVO vo);
	Parking queryById(String id);
	List<Parking> queryAll();
	List<Parking> queryByDotId(String dot_id);
	Parking queryDistanceByVo(BaseConditionVO vo);
}
