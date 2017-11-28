package com.cocosh.sys.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.cocosh.framework.annotation.LogClass;

import com.cocosh.framework.base.BaseConditionVO;
import com.cocosh.framework.mybatis.Page;
import com.cocosh.framework.mybatis.PaginationInterceptor;
import com.cocosh.framework.util.StringUtil;
import com.cocosh.sys.mapper.VersionMapper;
import com.cocosh.sys.model.Version;
import com.cocosh.sys.service.VersionService;

@Transactional
@Service
public class VersionServiceImpl implements VersionService {
	@Autowired
	private VersionMapper mapper;

	@LogClass(module = "App版本管理", method = "添加")
	@Override
	public boolean add(Version po) {
		po.setId(StringUtil.getUuid());
		if(po.getType()==1)po.setUrl(null);
		return mapper.add(po) > 0;
	}
	
    @LogClass(module = "App版本管理", method = "删除")
	@Override
	public boolean del(String ids) {
		return mapper.del(ids.split(",")) > 0;
	}

	@LogClass(module = "App版本管理", method = "修改")
	@Override
	public boolean update(Version po) {
		return mapper.update(po) > 0;
	}

	@Override
	public Page<Version> queryPage(BaseConditionVO vo) {
		PaginationInterceptor.startPage(vo.getPageNo(),vo.getPageSize());
		mapper.queryPage(vo);
		Page<Version> page=PaginationInterceptor.endPage();
		return page;
	}

	@Override
	public Version queryById(String id) {
		return mapper.queryById(id);
	}

	@Override
	public Version queryLast(String type) {
		if (StringUtil.isEmpty(type)) {
			return mapper.queryLast();
		}
		return mapper.queryLastByType(type);
	}
	
}
