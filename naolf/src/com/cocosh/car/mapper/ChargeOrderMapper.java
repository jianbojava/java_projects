package com.cocosh.car.mapper;

import java.util.List;

import com.cocosh.framework.base.BaseConditionVO;
import com.cocosh.member.model.Firm;
import com.cocosh.car.model.ChargeOrder;
import com.cocosh.car.model.ChargeOrderDetail;
import com.cocosh.car.model.ChargeOrderExport;

public interface ChargeOrderMapper {
	Integer add(ChargeOrder po);
	Integer update(ChargeOrder po);
	Integer updateM(ChargeOrder po);
	List<ChargeOrder> queryPage(BaseConditionVO vo);
	List<ChargeOrder> queryPageApp(BaseConditionVO vo);
	ChargeOrder queryById(String id);
	ChargeOrder queryValid(String mem_id);
	Integer addOrderDetail(ChargeOrderDetail orderDetail);
	List<ChargeOrderDetail> queryOrderDetail(String sn);
	List<Firm> queryFirmFromOrder();
	Integer updateIsRead(String sn);
	Integer queryNoReadCount();
	List<ChargeOrderExport> queryExport(BaseConditionVO vo);
	Integer delOrderDetail(String sn);
}
