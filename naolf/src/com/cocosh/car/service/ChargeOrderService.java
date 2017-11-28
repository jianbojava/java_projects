package com.cocosh.car.service;
 
import java.util.List;

import com.alibaba.fastjson.JSONObject;
import com.cocosh.framework.base.AjaxResult;
import com.cocosh.framework.base.BaseConditionVO;
import com.cocosh.framework.mybatis.Page;
import com.cocosh.member.model.Firm;
import com.cocosh.car.model.ChargeOrder;
import com.cocosh.car.model.ChargeOrderDetail;
import com.cocosh.car.model.ChargeOrderExport;

public interface ChargeOrderService {
	boolean add(ChargeOrder po);
	boolean del(String ids);
	boolean update(ChargeOrder po);
	void notifyUpdate(JSONObject dataJsonObject);
	Page<ChargeOrder> queryPage(BaseConditionVO vo);
	ChargeOrder queryById(String id);
	ChargeOrder queryValid(String mem_id);
	List<Firm> queryFirmFromOrder();
	Integer queryNoReadCount();
	boolean updateIsRead(String sn);
	List<ChargeOrderDetail> queryOrderDetail(String sn);
	boolean checkAmount(ChargeOrder po);
	List<ChargeOrderExport> queryExport(BaseConditionVO vo);
	List<ChargeOrder> queryPageByStatus(BaseConditionVO vo);
	//支付回调
	void orderNotify(String sn,String trade_no,int pay_type);
	public AjaxResult accountPay(BaseConditionVO vo);
}
