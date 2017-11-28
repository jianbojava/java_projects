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
import com.cocosh.car.mapper.EquipmentInfoMapper;
import com.cocosh.car.model.EquipmentInfo;
import com.cocosh.car.service.EquipmentInfoService;

@Transactional
@Service
public class EquipmentInfoServiceImpl implements EquipmentInfoService {
	@Autowired
	private EquipmentInfoMapper mapper;

	@LogClass(module = "充电设备管理", method = "添加")
	@Override
	public boolean add(EquipmentInfo po) {
		ArrayList<EquipmentInfo> list=new ArrayList<EquipmentInfo>();
		list.add(po);
		return mapper.add(list)>0;
	}
	
    @LogClass(module = "充电设备管理", method = "操作")
	@Override
	public boolean del(Integer flg,String ids) {
    	Map<String, Object> map = new HashMap<String, Object>();
		map.put("del_flg", flg);
		map.put("del_ids", ids.split(","));
		return mapper.del(map) > 0;
	}
    
    @LogClass(module = "充电设备管理", method = "删除")
	@Override
	public boolean delT(String ids) {
		return mapper.delT(ids.split(",")) > 0;
	}

	@LogClass(module = "充电设备管理", method = "修改")
	@Override
	public boolean update(EquipmentInfo po) {
		return mapper.update(po) > 0;
	}

	@Override
	public Page<EquipmentInfo> queryPage(BaseConditionVO vo) {
		PaginationInterceptor.startPage(vo.getPageNo(),vo.getPageSize());
		mapper.queryPage(vo);
		Page<EquipmentInfo> page=PaginationInterceptor.endPage();
		return page;
	}

	@Override
	public EquipmentInfo queryById(String id) {
		return mapper.queryById(id);
	}

	@Override
	public List<EquipmentInfo> queryByStationID(String stationId) {
		return mapper.queryByStationID(stationId);
	}
	
}
