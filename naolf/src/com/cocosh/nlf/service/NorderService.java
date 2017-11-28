package com.cocosh.nlf.service;
 
import java.util.List;

import com.cocosh.framework.base.AjaxResult;
import com.cocosh.framework.base.BaseConditionVO;
import com.cocosh.framework.mybatis.Page;
import com.cocosh.nlf.model.Norder;

public interface NorderService {
	boolean add(Norder po);//后台添加订单
	boolean addfinish(Norder po);//后台添加已结业历史订单
	Integer addnofinish(Norder po,Integer is_end,String student_id,Integer left_review);//后台添加未结业的历史课程订单
	Integer addnofinishticket(Norder po);//后台添加未结业的历史卡券订单（全部未结业)
	Integer addnofinishticket_part(Norder po);//后台添加未结业的历史卡券订单(部分未结业)
	AjaxResult finishUserTicket(String ut_id,String student_id,Integer left_review);//后端结业课程
	boolean del(String ids);
	boolean update(Norder po);
	boolean cancel(String id);
	boolean wapcancel(String id);
	Page<Norder> queryPage(BaseConditionVO vo);
	Norder queryById(String id);
	AjaxResult addNorder(Norder norder);
	List<Norder> queryByAll(Norder norder);
	List<Norder> queryLesson(Norder norder);
	List<Norder> queryTicket(Norder norder);
	boolean pay_success(String sn,String trade_no,Integer pay_type);//支付成功后调用
	boolean pay_success(String sn,String trade_no,Integer pay_type,Double pay_amount);//后台添加部分未结业的卡券订单的支付(只有一处不同)
	Integer topay(String id);//后台先支付验证，然后线下支付
	Integer payValid(String id);//前端支付验证;
	List<Norder> querywap(Norder norder);//前端查询课程，卡券
	AjaxResult returnOrder(Norder norder,Double return_percent,Integer return_point);//退款
	Norder queryBySn(String sn);
	
}
