package com.cocosh.car.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.cocosh.framework.annotation.LogClass;

import com.cocosh.framework.base.BaseConditionVO;
import com.cocosh.framework.mybatis.Page;
import com.cocosh.framework.mybatis.PaginationInterceptor;
import com.cocosh.car.mapper.ConnectorInfoMapper;
import com.cocosh.car.model.ConnectorInfo;
import com.cocosh.car.service.ConnectorInfoService;

@Transactional
@Service
public class ConnectorInfoServiceImpl implements ConnectorInfoService {
	@Autowired
	private ConnectorInfoMapper mapper;

	@LogClass(module = "充电设备接口管理", method = "添加")
	@Override
	public boolean add(ConnectorInfo po) {
		ArrayList<ConnectorInfo> list=new ArrayList<ConnectorInfo>();
		list.add(po);
		return mapper.add(list)>0;
	}
	
    @LogClass(module = "充电设备接口管理", method = "操作")
	@Override
	public boolean del(Integer flg,String ids) {
    	Map<String, Object> map = new HashMap<String, Object>();
		map.put("del_flg", flg);
		map.put("del_ids", ids.split(","));
		return mapper.del(map) > 0;
	}
    
    @LogClass(module = "充电设备接口管理", method = "删除")
	@Override
	public boolean delT(String ids) {
		return mapper.delT(ids.split(",")) > 0;
	}

	@LogClass(module = "充电设备接口管理", method = "修改")
	@Override
	public boolean update(ConnectorInfo po) {
		return mapper.update(po) > 0;
	}

	@Override
	public Page<ConnectorInfo> queryPage(BaseConditionVO vo) {
		PaginationInterceptor.startPage(vo.getPageNo(),vo.getPageSize());
		mapper.queryPage(vo);
		Page<ConnectorInfo> page=PaginationInterceptor.endPage();
		return page;
	}

	@Override
	public ConnectorInfo queryById(String id) {
		return mapper.queryById(id);
	}

	@Override
	public ConnectorInfo queryConnCount(String id) {
		return mapper.queryConnCount(id);
	}

	@Override
	public List<ConnectorInfo> queryConnByStationID(String stationID) {
		return mapper.queryConnByStationID(stationID);
	}

	@Override
	public ConnectorInfo queryByIdApp(String connectID) {
		return mapper.queryByIdApp(connectID);
	}

	@Override
	public boolean updateStatus(String connectID, Integer status) {
		ConnectorInfo connect=new ConnectorInfo();
		connect.setConnectorID(connectID);
		connect.setStatus(status);
		return mapper.update(connect)>0;
	}
	
}
