package com.cocosh.car.mapper;

import java.util.List;
import java.util.Map;

import com.cocosh.framework.base.BaseConditionVO;
import com.cocosh.car.model.ConnectorInfo;

public interface ConnectorInfoMapper {
	Integer add(List<ConnectorInfo> list);
	Integer del(Map<String,Object> map);
	Integer delT(String[] ids);
	Integer update(ConnectorInfo po);
	List<ConnectorInfo> queryPage(BaseConditionVO vo);
	ConnectorInfo queryById(String id);
	ConnectorInfo queryConnCount(String id);
	List<ConnectorInfo> queryConnByStationID(String stationID);
	ConnectorInfo queryByIdApp(String connectID);
}
