package com.cocosh.sys.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cocosh.framework.annotation.LogClass;
import com.cocosh.framework.base.BaseConditionVO;
import com.cocosh.framework.mybatis.Page;
import com.cocosh.framework.mybatis.PaginationInterceptor;
import com.cocosh.framework.util.StringUtil;
import com.cocosh.sys.mapper.LogMapper;
import com.cocosh.sys.model.Log;
import com.cocosh.sys.service.LogService;

@Transactional
@Service
public class LogServiceImpl implements LogService {
	@Autowired
	private LogMapper logMapper;

	@Override
	public boolean add(Log log) {
		log.setId(StringUtil.getUuid());
		return logMapper.add(log) > 0 ? true : false;
	}

	@LogClass(module = "日志管理", method = "删除")
	@Override
	public boolean del(String ids) {
		return logMapper.del(ids.split(",")) > 0 ? true : false;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Page<Log> queryPage(BaseConditionVO vo) {
		PaginationInterceptor.startPage(vo.getPageNo(),vo.getPageSize());
		logMapper.queryPage(vo);
		Page<Log> page=PaginationInterceptor.endPage();
		return page;
	}
}
