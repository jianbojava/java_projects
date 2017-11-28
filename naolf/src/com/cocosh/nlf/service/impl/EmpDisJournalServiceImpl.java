package com.cocosh.nlf.service.impl;

import java.util.List;

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
import com.cocosh.nlf.mapper.EmpDisJournalMapper;
import com.cocosh.nlf.model.EmpDisJournal;
import com.cocosh.nlf.service.EmpDisJournalService;
import com.cocosh.sys.model.User;

@Transactional
@Service
public class EmpDisJournalServiceImpl implements EmpDisJournalService {
	@Autowired
	private EmpDisJournalMapper mapper;

	
	@Override
	public Page<EmpDisJournal> queryPage(BaseConditionVO vo) {
		PaginationInterceptor.startPage(vo.getPageNo(),vo.getPageSize());
		Subject subject = SecurityUtils.getSubject();
		User user = (User) subject.getSession().getAttribute("user");
		vo.setOperat_id(user.getId());
		mapper.queryPage(vo);
		Page<EmpDisJournal> page=PaginationInterceptor.endPage();
		return page;
	}


	@Override
	public List<EmpDisJournal> queryList(EmpDisJournal po) {
		return mapper.queryListAll(po);
	}


	@Override
	public List<EmpDisJournal> queryDetails(EmpDisJournal po) {
		return mapper.queryDetails(po);
	}
}
