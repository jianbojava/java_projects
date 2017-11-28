package com.cocosh.car.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cocosh.framework.base.BaseConditionVO;
import com.cocosh.framework.mybatis.Page;
import com.cocosh.framework.mybatis.PaginationInterceptor;
import com.cocosh.framework.util.StringUtil;
import com.cocosh.member.model.Firm;
import com.cocosh.car.mapper.PaymentLogMapper;
import com.cocosh.car.model.PaymentLog;
import com.cocosh.car.model.PaymentLogExportVo;
import com.cocosh.car.service.PaymentLogService;

@Transactional
@Service
public class PaymentLogServiceImpl implements PaymentLogService {
	@Autowired
	private PaymentLogMapper mapper;

	@Override
	public boolean add(PaymentLog po) {
		po.setId(StringUtil.getUuid());
		return mapper.add(po) > 0;
	}
	
	@Override
	public boolean update(PaymentLog po) {
		return mapper.update(po) > 0;
	}

	@Override
	public Page<PaymentLog> queryPage(BaseConditionVO vo) {
		PaginationInterceptor.startPage(vo.getPageNo(),vo.getPageSize());
		mapper.queryPage(vo);
		Page<PaymentLog> page=PaginationInterceptor.endPage();
		return page;
	}

	@Override
	public List<PaymentLog> queryPageApp(BaseConditionVO vo) {
		PaginationInterceptor.startPage(vo.getPageNo(),vo.getPageSize());
		mapper.queryPageApp(vo);
		Page<PaymentLog> page=PaginationInterceptor.endPage();
		return page.getResult();
	}

	@Override
	public List<Firm> queryFirms() {
		return mapper.queryFirmFromPaymentLog();
	}

	@Override
	public List<PaymentLogExportVo> queryExport(BaseConditionVO vo) {
		return mapper.queryExport(vo);
	}

	@Override
	public PaymentLog queryBySn(String sn) {
		return mapper.queryBySn(sn);
	}

}
