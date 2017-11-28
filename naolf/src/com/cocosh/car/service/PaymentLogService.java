package com.cocosh.car.service;
 
import java.util.List;

import com.cocosh.framework.base.BaseConditionVO;
import com.cocosh.framework.mybatis.Page;
import com.cocosh.member.model.Firm;
import com.cocosh.car.model.PaymentLog;
import com.cocosh.car.model.PaymentLogExportVo;

public interface PaymentLogService {
	boolean add(PaymentLog po);
	boolean update(PaymentLog po);
	Page<PaymentLog> queryPage(BaseConditionVO vo);
	List<Firm> queryFirms();
	List<PaymentLog> queryPageApp(BaseConditionVO vo);
	List<PaymentLogExportVo> queryExport(BaseConditionVO vo);
	PaymentLog queryBySn(String sn);
}
