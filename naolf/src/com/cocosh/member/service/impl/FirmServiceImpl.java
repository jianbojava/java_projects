package com.cocosh.member.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cocosh.car.mapper.PaymentLogMapper;
import com.cocosh.car.model.PaymentLog;
import com.cocosh.framework.annotation.LogClass;

import com.cocosh.framework.base.BaseConditionVO;
import com.cocosh.framework.mybatis.Page;
import com.cocosh.framework.mybatis.PaginationInterceptor;
import com.cocosh.framework.util.StringUtil;
import com.cocosh.member.mapper.FirmMapper;
import com.cocosh.member.model.Firm;
import com.cocosh.member.service.FirmService;

@Transactional
@Service
public class FirmServiceImpl implements FirmService {
	@Autowired
	private FirmMapper mapper;
	@Autowired
	private PaymentLogMapper paymentLogMapper;

	@LogClass(module = "企业管理", method = "添加")
	@Override
	public boolean add(Firm po) {
		po.setId(StringUtil.getUuid());
		return mapper.add(po) > 0;
	}

	@LogClass(module = "企业管理", method = "删除")
	@Override
	public boolean del(Integer flg, String ids) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("del_flg", flg);
		map.put("del_ids", ids.split(","));
		return mapper.del(map) > 0;
	}

	@LogClass(module = "企业管理", method = "修改")
	@Override
	public boolean update(Firm po) {
		return mapper.update(po) > 0;
	}

	@Override
	public Page<Firm> queryPage(BaseConditionVO vo) {
		PaginationInterceptor.startPage(vo.getPageNo(), vo.getPageSize());
		mapper.queryPage(vo);
		Page<Firm> page = PaginationInterceptor.endPage();
		return page;
	}

	@Override
	public Firm queryById(String id) {
		return mapper.queryById(id);
	}

	@Override
	public List<Firm> queryAll() {
		return mapper.queryAll();
	}

	@LogClass(module="企业管理",method="充值")
	@Override
	public boolean recharge(Firm po) {
		//判断企业余额、保证金
		Firm firm=mapper.queryById(po.getId());
		//保证金
		if (po.getDeposit()>0) {
			//添加支付流水记录
			paymentLogMapper.add(new PaymentLog(null, po.getId(), po.getDeposit(), "保证金充值",null, null, 4, 1, 1,po.getRemark()));
		}
		if (po.getDeposit()<0) {
			//添加支付流水记录
			paymentLogMapper.add(new PaymentLog(null, po.getId(), Math.abs(po.getDeposit()), "保证金退返",null, null, 4, 1, 4,po.getRemark()));
		}
		//预充值企业添加流水记录
		if (firm.getPay_type()==1) {
			if (po.getLeft_amount()>0) {
				//添加支付流水记录
				paymentLogMapper.add(new PaymentLog(null, po.getId(), po.getLeft_amount(), "余额充值", null,null, 4, 1, 2,po.getRemark()));
			}
			if (po.getLeft_amount()<0) {
				//添加支付流水记录
				paymentLogMapper.add(new PaymentLog(null, po.getId(), Math.abs(po.getLeft_amount()), "余额退返",null, null, 4, 1, 5,po.getRemark()));
			}
		}
		//修改企业余额、保证金等
		po.setDeposit(firm.getDeposit()+po.getDeposit());
		po.setLeft_amount(firm.getLeft_amount()+po.getLeft_amount());
		return mapper.update(po)>0;
	}

}
