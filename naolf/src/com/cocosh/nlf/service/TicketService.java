package com.cocosh.nlf.service;
 
import java.util.List;

import com.cocosh.framework.base.BaseConditionVO;
import com.cocosh.framework.mybatis.Page;
import com.cocosh.nlf.model.Ticket;

public interface TicketService {
	boolean add(Ticket po);
	boolean del(String ids);
	boolean update(Ticket po);
	Page<Ticket> queryPage(BaseConditionVO vo);
	Ticket queryById(String id);
	List<Ticket> queryAll();
	List<Ticket> queryAllReset();
	Ticket checkName(String name);
	List<Ticket> queryByType(Integer type);//0卡，1券
}
