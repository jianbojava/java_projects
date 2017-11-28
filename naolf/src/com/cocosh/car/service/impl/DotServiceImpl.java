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
import com.cocosh.car.mapper.DotMapper;
import com.cocosh.car.model.Dot;
import com.cocosh.car.service.DotService;

@Transactional
@Service
public class DotServiceImpl implements DotService {
	@Autowired
	private DotMapper mapper;

	@LogClass(module = "网点管理", method = "添加")
	@Override
	public boolean add(Dot po) {
		po.setId(StringUtil.getUuid());
		return mapper.add(po) > 0;
	}
	
    @LogClass(module = "网点管理", method = "删除")
	@Override
	public boolean del(Integer flg,String ids) {
    	Map<String, Object> map = new HashMap<String, Object>();
		map.put("del_flg", flg);
		map.put("del_ids", ids.split(","));
		return mapper.del(map) > 0;
	}

	@LogClass(module = "网点管理", method = "修改")
	@Override
	public boolean update(Dot po) {
		return mapper.update(po) > 0;
	}

	@Override
	public Page<Dot> queryPage(BaseConditionVO vo) {
		PaginationInterceptor.startPage(vo.getPageNo(),vo.getPageSize());
		mapper.queryPage(vo);
		Page<Dot> page=PaginationInterceptor.endPage();
		return page;
	}

	@Override
	public Dot queryById(String id) {
		return mapper.queryById(id);
	}

	@Override
	public List<Dot> queryByAll() {
		return mapper.queryByAll();
	}

	@Override
	public List<Dot> queryPageApp(BaseConditionVO vo) {
		PaginationInterceptor.startPage(vo.getPageNo(),vo.getPageSize());
		mapper.queryPageApp(vo);
		Page<Dot> page=PaginationInterceptor.endPage();
		return page.getResult();
	}
	
}
