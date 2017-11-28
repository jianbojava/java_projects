package com.cocosh.sys.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.cocosh.framework.annotation.LogClass;

import com.cocosh.framework.util.StringUtil;
import com.cocosh.sys.mapper.DictMapper;
import com.cocosh.sys.model.Dict;
import com.cocosh.sys.service.DictService;

@Transactional
@Service
public class DictServiceImpl implements DictService {
	@Autowired
	private DictMapper mapper;

	@LogClass(module = "数据字典管理", method = "添加")
	@Override
	public boolean add(Dict po) {
		po.setId(StringUtil.getUuid());
		return mapper.add(po) > 0;
	}
	
    @LogClass(module = "数据字典管理", method = "删除")
	@Override
	public boolean del(String ids) {
		return mapper.del(ids.split(",")) > 0;
	}

	@LogClass(module = "数据字典管理", method = "修改")
	@Override
	public boolean update(Dict po) {
		return mapper.update(po)>0;
	}

	@Override
	public String queryByCode(String code) {
		return mapper.queryByCode(code);
	}

	@Override
	public List<Dict> queryAll() {
		return mapper.queryAll();
	}

	@Override
	public List<Dict> queryCAll(String code) {
		return mapper.queryCAll(code);
	}
	
}
