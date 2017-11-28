package com.cocosh.car.service;
 
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.cocosh.framework.base.AjaxResult;
import com.cocosh.framework.base.BaseConditionVO;
import com.cocosh.framework.mybatis.Page;
import com.cocosh.member.model.Firm;
import com.cocosh.sys.model.User;
import com.cocosh.car.model.Order;
import com.cocosh.car.model.PaymentLog;

public interface OrderService {
	AjaxResult add(Order po,BaseConditionVO vo);
	boolean del(String ids);
	AjaxResult update(Order po);
	Page<Order> queryPage(BaseConditionVO vo);
	Page<Order> queryDyncPage(BaseConditionVO vo);
	Order queryById(String id);
	Order queryByIdApp(String id);
	Order queryAmount(String id);
	List<Order> queryPageByStatus(BaseConditionVO vo);
	Order queryValidDate(Order po);
	boolean updateIsRead(String id);
	Integer queryNoReadCount(Integer type);
	boolean addEvcardOrder(HttpServletRequest req);
	//修改还车时间
	AjaxResult updateEndTime(Order po,BaseConditionVO vo);
	//订单导出
	List<Order> export(BaseConditionVO vo);
	//分时订单导出
	List<Order> timeExport(BaseConditionVO vo);
	
	List<Firm> queryFirmFromOrder(BaseConditionVO vo);
	List<User> queryDispFromOrder(BaseConditionVO vo);
	//发送备车提醒短信 
	void sendReadyMsg2Disp();
	//发送还车提醒短信 
	void sendReturnMsg2Disp();
	//发送提醒取车短信
	void sendExpiredOrder();
	//自动取消订单
	void cancelOrder();
	//取消订单
	AjaxResult cancelOrder(BaseConditionVO vo);
	//余额支付订单
	AjaxResult accountPay(BaseConditionVO vo);
	
	//订单支付完成后回调
	void orderNotify(String sn,String trade_no,int pay_type);
	//充值保证金回调
	void depositNotify(String sn,String trade_no);
	//充值余额回调
	void accountNotify(String sn,String trade_no);
	//时租订单对账
	boolean checkAmount(Order po);
}
