package com.cocosh.member.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.cocosh.framework.annotation.LogClass;

import com.cocosh.framework.base.BaseConditionVO;
import com.cocosh.framework.mybatis.Page;
import com.cocosh.framework.mybatis.PaginationInterceptor;
import com.cocosh.framework.util.StringUtil;
import com.cocosh.member.mapper.LevelMapper;
import com.cocosh.member.model.Level;
import com.cocosh.member.service.LevelService;

@Transactional
@Service
public class LevelServiceImpl implements LevelService {
	@Autowired
	private LevelMapper mapper;

	@LogClass(module = "会员等级管理", method = "添加")
	@Override
	public boolean add(Level po) {
		po.setId(StringUtil.getUuid());
		return mapper.add(po) > 0;
	}
	
    @LogClass(module = "会员等级管理", method = "操作")
	@Override
	public boolean del(Integer flg,String ids) {
    	Map<String, Object> map = new HashMap<String, Object>();
		map.put("del_flg", flg);
		map.put("del_ids", ids.split(","));
		return mapper.del(map) > 0;
	}

	@LogClass(module = "会员等级管理", method = "修改")
	@Override
	public boolean update(Level po) {
		return mapper.update(po) > 0;
	}

	@Override
	public Page<Level> queryPage(BaseConditionVO vo) {
		PaginationInterceptor.startPage(vo.getPageNo(),vo.getPageSize());
		mapper.queryPage(vo);
		Page<Level> page=PaginationInterceptor.endPage();
		return page;
	}

	@Override
	public Level queryById(String id) {
		return mapper.queryById(id);
	}

	@Override
	public Level queryMin() {
		return mapper.queryMin();
	}
	
}
