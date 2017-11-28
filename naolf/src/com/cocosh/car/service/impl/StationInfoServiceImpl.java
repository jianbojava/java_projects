package com.cocosh.car.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cocosh.car.mapper.StationInfoMapper;
import com.cocosh.car.model.StationInfo;
import com.cocosh.car.service.StationInfoService;
import com.cocosh.framework.annotation.LogClass;
import com.cocosh.framework.base.BaseConditionVO;
import com.cocosh.framework.mybatis.Page;
import com.cocosh.framework.mybatis.PaginationInterceptor;

@Transactional
@Service
public class StationInfoServiceImpl implements StationInfoService {
	@Autowired
	private StationInfoMapper mapper;

	@LogClass(module = "充电站管理", method = "添加")
	@Override
	public boolean add(StationInfo po) {
		List<StationInfo> list=new ArrayList<StationInfo>();
		po.setType(0);//后台添加为 智行家 的桩
		list.add(po);
		return mapper.add(list)>0;
	}
	
    @LogClass(module = "充电站管理", method = "操作")
	@Override
	public boolean del(Integer flg,String ids) {
    	Map<String, Object> map = new HashMap<String, Object>();
		map.put("del_flg", flg);
		map.put("del_ids", ids.split(","));
		return mapper.del(map) > 0;
	}
    
    @LogClass(module = "充电站管理", method = "删除")
	@Override
	public boolean delT(String ids) {
		return mapper.delT(ids.split(",")) > 0;
	}

	@LogClass(module = "充电站管理", method = "修改")
	@Override
	public boolean update(StationInfo po) {
		return mapper.update(po) > 0;
	}

	@Override
	public Page<StationInfo> queryPage(BaseConditionVO vo) {
		PaginationInterceptor.startPage(vo.getPageNo(),vo.getPageSize());
		mapper.queryPage(vo);
		Page<StationInfo> page=PaginationInterceptor.endPage();
		return page;
	}

	@Override
	public StationInfo queryById(String id) {
		return mapper.queryById(id);
	}

	@Override
	public List<StationInfo> queryAll() {
		return mapper.queryAll();
	}

	@Override
	public List<StationInfo> query2Map(BaseConditionVO vo) {
		return mapper.query2Map(vo);
	}

	@Override
	public List<StationInfo> queryPageApp(BaseConditionVO vo) {
		PaginationInterceptor.startPage(vo.getPageNo(),vo.getPageSize());
		mapper.queryPageApp(vo);
		Page<StationInfo> page=PaginationInterceptor.endPage();
		return page.getResult();
	}

	@Override
	public StationInfo queryByIdApp(BaseConditionVO vo) {
		return mapper.queryByIdApp(vo);
	}
	
}
