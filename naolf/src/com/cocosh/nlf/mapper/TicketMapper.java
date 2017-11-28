package com.cocosh.nlf.mapper;

import java.util.List;

import com.cocosh.framework.base.BaseConditionVO;
import com.cocosh.nlf.model.Ticket;

public interface TicketMapper {
	Integer add(Ticket po);
	Integer del(String[] ids);
	Integer update(Ticket po);
	List<Ticket> queryPage(BaseConditionVO vo);
	Ticket queryById(String id);
	List<Ticket> queryAll();
	List<Ticket> queryAllReset();
	Ticket checkName(Ticket po);
	List<Ticket> queryByType(Ticket po);
	Ticket queryByutSn(String ut_sn);
}
