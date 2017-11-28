package com.cocosh.car.service.impl;

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
import com.cocosh.framework.util.StringUtil;
import com.cocosh.car.mapper.ParkingMapper;
import com.cocosh.car.model.Parking;
import com.cocosh.car.service.ParkingService;

@Transactional
@Service
public class ParkingServiceImpl implements ParkingService {
	@Autowired
	private ParkingMapper mapper;

	@LogClass(module = "停车位管理", method = "添加")
	@Override
	public boolean add(Parking po) {
		po.setId(StringUtil.getUuid());
		return mapper.add(po) > 0;
	}
	
    @LogClass(module = "停车位管理", method = "删除")
	@Override
	public boolean del(Integer flg,String ids) {
    	Map<String, Object> map = new HashMap<String, Object>();
		map.put("del_flg", flg);
		map.put("del_ids", ids.split(","));
		return mapper.del(map) > 0;
	}

	@LogClass(module = "停车位管理", method = "修改")
	@Override
	public boolean update(Parking po) {
		return mapper.update(po) > 0;
	}

	@Override
	public Page<Parking> queryPage(BaseConditionVO vo) {
		PaginationInterceptor.startPage(vo.getPageNo(),vo.getPageSize());
		mapper.queryPage(vo);
		Page<Parking> page=PaginationInterceptor.endPage();
		return page;
	}

	@Override
	public Parking queryById(String id) {
		return mapper.queryById(id);
	}

	@Override
	public List<Parking> queryAll() {
		return mapper.queryAll();
	}

	@Override
	public List<Parking> queryByDotId(String dot_id) {
		return mapper.queryByDotId(dot_id);
	}

	@Override
	public Parking queryDistanceByVo(BaseConditionVO vo) {
		return mapper.queryDistanceByVo(vo);
	}
	
}
