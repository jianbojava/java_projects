package com.cocosh.nlf.service.impl;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.cocosh.framework.annotation.LogClass;

import com.cocosh.framework.base.BaseConditionVO;
import com.cocosh.framework.mybatis.Page;
import com.cocosh.framework.mybatis.PaginationInterceptor;
import com.cocosh.framework.util.StringUtil;
import com.cocosh.nlf.mapper.DeptPerformanceMapper;
import com.cocosh.nlf.model.DeptPerformance;
import com.cocosh.nlf.service.DeptPerformanceService;
import com.cocosh.sys.model.User;

@Transactional
@Service
public class DeptPerformanceServiceImpl implements DeptPerformanceService {
	@Autowired
	private DeptPerformanceMapper mapper;

	
	
   

	@Override
	public Page<DeptPerformance> queryPage(BaseConditionVO vo) {
		PaginationInterceptor.startPage(vo.getPageNo(),vo.getPageSize());
		Subject subject = SecurityUtils.getSubject();
		User user = (User) subject.getSession().getAttribute("user");    
		vo.setOperat_id(user.getId());
		mapper.queryPage(vo);
		Page<DeptPerformance> page=PaginationInterceptor.endPage();
		return page;
	}
	@Override
	public Page<DeptPerformance> queryDetailPage(BaseConditionVO vo) {
		PaginationInterceptor.startPage(vo.getPageNo(),vo.getPageSize());
		mapper.queryDetailPage(vo);
		Page<DeptPerformance> page=PaginationInterceptor.endPage();
		return page;
	}

	
	
}
