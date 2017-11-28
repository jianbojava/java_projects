package com.cocosh.car.service;
 
import java.util.List;

import com.cocosh.framework.base.BaseConditionVO;
import com.cocosh.framework.mybatis.Page;
import com.cocosh.car.model.ConnectorInfo;

public interface ConnectorInfoService {
	boolean add(ConnectorInfo po);
	boolean del(Integer flg,String ids);
	boolean delT(String ids);
	boolean update(ConnectorInfo po);
	boolean updateStatus(String connectID,Integer status);
	Page<ConnectorInfo> queryPage(BaseConditionVO vo);
	ConnectorInfo queryById(String id);
	ConnectorInfo queryConnCount(String id);
	List<ConnectorInfo> queryConnByStationID(String stationID);
	ConnectorInfo queryByIdApp(String connectID);
}
