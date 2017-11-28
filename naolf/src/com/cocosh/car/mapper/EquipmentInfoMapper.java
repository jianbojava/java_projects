package com.cocosh.car.mapper;

import java.util.List;
import java.util.Map;

import com.cocosh.framework.base.BaseConditionVO;
import com.cocosh.car.model.EquipmentInfo;

public interface EquipmentInfoMapper {
	Integer add(List<EquipmentInfo> list);
	Integer del(Map<String,Object> map);
	Integer delT(String[] ids);
	Integer update(EquipmentInfo po);
	List<EquipmentInfo> queryPage(BaseConditionVO vo);
	EquipmentInfo queryById(String id);
	List<EquipmentInfo> queryByStationID(String stationId);
}
