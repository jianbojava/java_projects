﻿package com.cocosh.sys.service.impl;

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
import com.cocosh.sys.mapper.NewsMapper;
import com.cocosh.sys.model.News;
import com.cocosh.sys.service.NewsService;

@Transactional
@Service
public class NewsServiceImpl implements NewsService {
	@Autowired
	private NewsMapper mapper;

	@LogClass(module = "新闻管理", method = "添加")
	@Override
	public boolean add(News po) {
		po.setId(StringUtil.getUuid());
		return mapper.add(po) > 0;
	}
	
    @LogClass(module = "新闻管理", method = "操作")
	@Override
	public boolean del(Integer flg,String ids) {
    	Map<String, Object> map = new HashMap<String, Object>();
		map.put("del_flg", flg);
		map.put("del_ids", ids.split(","));
		return mapper.del(map) > 0;
	}

	@LogClass(module = "新闻管理", method = "修改")
	@Override
	public boolean update(News po) {
		return mapper.update(po) > 0;
	}

	@Override
	public Page<News> queryPage(BaseConditionVO vo) {
		PaginationInterceptor.startPage(vo.getPageNo(),vo.getPageSize());
		mapper.queryPage(vo);
		Page<News> page=PaginationInterceptor.endPage();
		return page;
	}

	@Override
	public News queryById(String id) {
		return mapper.queryById(id);
	}
	
}
