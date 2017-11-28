package com.cocosh.nlf.service;
 
import java.util.List;

import com.cocosh.framework.base.BaseConditionVO;
import com.cocosh.framework.mybatis.Page;
import com.cocosh.nlf.model.Lesson;
import com.cocosh.nlf.model.UserTicket;

public interface UserTicketService {
	boolean add(UserTicket po);
	boolean del(String ids);
	boolean update(UserTicket po);
	Page<UserTicket> queryPage(BaseConditionVO vo);
	UserTicket queryById(String id);
	List<UserTicket> queryUticket(UserTicket u);
	List<UserTicket> queryAll();
	List<UserTicket> queryLessonByNorderId(String norder_id);//查询订单用到的课程
	UserTicket queryTicketByNorderId(String norder_id);//查询订单用到的卡券
	boolean updateWap(UserTicket po);
	boolean addWapTicket(UserTicket po);
	boolean addWapLesson(UserTicket po);
	UserTicket queryBySn(String sn);
	List<UserTicket> queryUticketByUserId(String user_id);
	UserTicket querydetails(String id);//微信端查看卡券升级详情
	List<UserTicket> queryByName(String ticket_name,String user_id);//按照卡券名称查询会员未使用的卡券
	List<UserTicket> queryupCard(String user_ticket_id,String user_id);//查询会员的可以用于升级的卡券
}
