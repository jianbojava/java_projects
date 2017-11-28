package com.cocosh.nlf.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.cocosh.framework.annotation.LogClass;

import com.cocosh.framework.base.BaseConditionVO;
import com.cocosh.framework.mybatis.Page;
import com.cocosh.framework.mybatis.PaginationInterceptor;
import com.cocosh.framework.util.StringUtil;
import com.cocosh.nlf.mapper.TicketMapper;
import com.cocosh.nlf.model.Ticket;
import com.cocosh.nlf.service.TicketService;

@Transactional
@Service
public class TicketServiceImpl implements TicketService {
	@Autowired
	private TicketMapper mapper;

	@LogClass(module = "卡券管理管理", method = "添加")
	@Override
	public boolean add(Ticket po) {
		po.setId(StringUtil.getUuid());
		return mapper.add(po) > 0;
	}
	
    @LogClass(module = "卡券管理管理", method = "删除")
	@Override
	public boolean del(String ids) {
		return mapper.del(ids.split(",")) > 0;
	}

	@LogClass(module = "卡券管理管理", method = "修改")
	@Override
	public boolean update(Ticket po) {
		return mapper.update(po) > 0;
	}

	@Override
	public Page<Ticket> queryPage(BaseConditionVO vo) {
		PaginationInterceptor.startPage(vo.getPageNo(),vo.getPageSize());
		mapper.queryPage(vo);
		Page<Ticket> page=PaginationInterceptor.endPage();
		return page;
	}

	@Override
	public Ticket queryById(String id) {
		return mapper.queryById(id);
	}

	@Override
	public List<Ticket> queryAll() {
		return mapper.queryAll();
	}

	@Override
	public List<Ticket> queryAllReset() {
		return mapper.queryAllReset();
	}

	@Override
	public Ticket checkName(String name) {
		Ticket t=new Ticket();
		t.setName(name);
		return mapper.checkName(t);
	}

	@Override
	public List<Ticket> queryByType(Integer type) {
		Ticket t=new Ticket();
		t.setType(type);
		return mapper.queryByType(t);
	}
	
}
