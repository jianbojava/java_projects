package com.cocosh.car.service;
 
import java.util.List;

import com.cocosh.framework.base.BaseConditionVO;
import com.cocosh.framework.mybatis.Page;
import com.cocosh.car.model.EquipmentInfo;

public interface EquipmentInfoService {
	boolean add(EquipmentInfo po);
	boolean del(Integer flg,String ids);
	boolean delT(String ids);
	boolean update(EquipmentInfo po);
	Page<EquipmentInfo> queryPage(BaseConditionVO vo);
	EquipmentInfo queryById(String id);
	List<EquipmentInfo> queryByStationID(String stationId);
}
