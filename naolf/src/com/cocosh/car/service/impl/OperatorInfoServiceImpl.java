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
import com.cocosh.car.mapper.OperatorInfoMapper;
import com.cocosh.car.model.OperatorInfo;
import com.cocosh.car.service.OperatorInfoService;

@Transactional
@Service
public class OperatorInfoServiceImpl implements OperatorInfoService {
	@Autowired
	private OperatorInfoMapper mapper;

	@LogClass(module = "运营商管理", method = "添加")
	@Override
	public boolean add(OperatorInfo po) {
		List<OperatorInfo> list=new ArrayList<OperatorInfo>();
		list.add(po);
		return mapper.add(list)>0;
	}
	
    @LogClass(module = "运营商管理", method = "删除")
	@Override
	public boolean del(Integer flg,String ids) {
    	Map<String, Object> map = new HashMap<String, Object>();
		map.put("del_flg", flg);
		map.put("del_ids", ids.split(","));
		return mapper.del(map) > 0;
	}

	@LogClass(module = "运营商管理", method = "修改")
	@Override
	public boolean update(OperatorInfo po) {
		return mapper.update(po) > 0;
	}

	@Override
	public Page<OperatorInfo> queryPage(BaseConditionVO vo) {
		PaginationInterceptor.startPage(vo.getPageNo(),vo.getPageSize());
		mapper.queryPage(vo);
		Page<OperatorInfo> page=PaginationInterceptor.endPage();
		return page;
	}

	@Override
	public OperatorInfo queryById(String id) {
		return mapper.queryById(id);
	}

	@Override
	public List<OperatorInfo> queryAll() {
		return mapper.queryAll();
	}
	
}
