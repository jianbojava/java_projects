package com.cocosh.nlf.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cocosh.framework.annotation.LogClass;
import com.cocosh.framework.base.BaseConditionVO;
import com.cocosh.framework.mybatis.Page;
import com.cocosh.framework.mybatis.PaginationInterceptor;
import com.cocosh.framework.util.StringUtil;
import com.cocosh.nlf.mapper.TicketMapper;
import com.cocosh.nlf.mapper.UpruleMapper;
import com.cocosh.nlf.model.Ticket;
import com.cocosh.nlf.model.Uprule;
import com.cocosh.nlf.service.UpruleService;

@Transactional
@Service
public class UpruleServiceImpl implements UpruleService {
	@Autowired
	private UpruleMapper mapper;
	@Autowired
	private TicketMapper ticketMapper;

	@LogClass(module = "升级规则管理", method = "添加")
	@Override
	public boolean add(Uprule po) {
		po.setId(StringUtil.getUuid());
		return mapper.add(po) > 0;
	}
	
    @LogClass(module = "升级规则管理", method = "删除")
	@Override
	public boolean del(String ids) {
		return mapper.del(ids.split(",")) > 0;
	}

	@LogClass(module = "升级规则管理", method = "修改")
	@Override
	public boolean update(Uprule po) {
		return mapper.update(po) > 0;
	}

	@Override
	public Page<Uprule> queryPage(BaseConditionVO vo) {
		PaginationInterceptor.startPage(vo.getPageNo(),vo.getPageSize());
		mapper.queryPage(vo);
		Page<Uprule> page=PaginationInterceptor.endPage();
		return page;
	}

	@Override
	public Uprule queryById(String id) {
		return mapper.queryById(id);
	}

	@Override
	public Uprule queryone() {
		return mapper.queryone();
	}

	@Override
	public boolean validUprule(Uprule po) {
		Ticket t1=ticketMapper.queryById(po.getTicket_id1());
		Ticket t2=ticketMapper.queryById(po.getTicket_id2());
		Ticket t3=ticketMapper.queryById(po.getTicket_id3());
		String t1_lesson_ids=t1.getLesson_ids()+","+t2.getLesson_ids();
		String t3_lesson_ids=t3.getLesson_ids();
		String[] lesson_ids=t1_lesson_ids.split(",");
		
		if(lesson_ids.length==t3_lesson_ids.split(",").length){
			for(String lesson_id:lesson_ids){
				if(!t3_lesson_ids.contains(lesson_id)){
					return false;
				}
			}
			return true;
		}
		return false;
	}
	
}
