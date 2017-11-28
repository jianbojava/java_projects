package com.cocosh.car.mapper;

import java.util.List;

import com.cocosh.framework.base.BaseConditionVO;
import com.cocosh.member.model.Firm;
import com.cocosh.car.model.PaymentLog;
import com.cocosh.car.model.PaymentLogExportVo;

public interface PaymentLogMapper {
	Integer add(PaymentLog po);
	Integer update(PaymentLog po);
	List<PaymentLog> queryPage(BaseConditionVO vo);
	List<Firm> queryFirmFromPaymentLog();
	List<PaymentLog> queryPageApp(BaseConditionVO vo);
	PaymentLog queryBySn(String sn);
	List<PaymentLogExportVo> queryExport(BaseConditionVO vo);
	Integer queryPayValid(String trade_no);
}
