package com.cocosh.nlf.mapper;

import java.util.List;

import com.cocosh.framework.base.BaseConditionVO;
import com.cocosh.nlf.model.UserTicket;

public interface UserTicketMapper {
	Integer add(UserTicket po);
	Integer del(String[] ids);
	Integer update(UserTicket po);
	List<UserTicket> queryPage(BaseConditionVO vo);
	UserTicket queryById(String id);
	List<UserTicket> queryUticket(UserTicket Uticket);
	Integer queryMaxCreateSn(String rule_id);
	UserTicket queryBySn(String sn);
	List<UserTicket> queryAll();
	List<UserTicket> queryPageRe(BaseConditionVO vo);
	List<UserTicket> query(UserTicket po);
	List<UserTicket> queryLessonByNorderId(String norder_id);//通过订单id查询课程
	Integer updateToEnd(String[] ids);//集中课程批量结业
	Integer updateToCancel(String[] ids);//集中课程 批量变成已取消
	Integer updateToNoStudy(String[] ids);//集中课程 批量变成未训练
	Integer updateToReturn(String norder_id);
	UserTicket queryByNorderIdAndConnId(UserTicket po);
	List<UserTicket> queryUticketByUserId(String user_id);//查询会员的卡券，user_id或者used_id
	List<UserTicket> queryUsedCanUpgrade(UserTicket po);//查询已使用的金卡可以用来升级的学习券
}
