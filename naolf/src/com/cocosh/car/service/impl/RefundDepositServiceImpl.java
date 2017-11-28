package com.cocosh.car.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cocosh.framework.annotation.LogClass;
import com.cocosh.framework.base.BaseConditionVO;
import com.cocosh.framework.mybatis.Page;
import com.cocosh.framework.mybatis.PaginationInterceptor;
import com.cocosh.framework.util.StringUtil;
import com.cocosh.member.mapper.MemberMapper;
import com.cocosh.member.mapper.MessageMapper;
import com.cocosh.member.model.Member;
import com.cocosh.member.model.Message;
import com.cocosh.car.mapper.PaymentLogMapper;
import com.cocosh.car.mapper.RefundDepositMapper;
import com.cocosh.car.model.PaymentLog;
import com.cocosh.car.model.RefundDeposit;
import com.cocosh.car.service.RefundDepositService;

@Transactional
@Service
public class RefundDepositServiceImpl implements RefundDepositService {
	@Autowired
	private RefundDepositMapper mapper;
	@Autowired
	private MemberMapper memberMapper;
	@Autowired
	private PaymentLogMapper paymentLogMapper;
	@Autowired
	private MessageMapper messageMapper;

	@Override
	public boolean add(RefundDeposit po) {
		po.setId(StringUtil.getUuid());
		return mapper.add(po) > 0;
	}
	
	@LogClass(module="保证金退款管理",method="审核")
	@Override
	public boolean update(RefundDeposit po) {
		String[] sns=po.getSn().split(",");
		//申请处理
		po.setSns(sns);
		mapper.update(po);
		//同意申请，退换保证金
		if (po.getIs_solve()==1) {
			//退还用户保证金
			List<RefundDeposit> deposits=mapper.queryBySns(sns);
			for(int i=0;i<deposits.size();i++){
				RefundDeposit r=deposits.get(i);
				//减保证金
				Member logM=memberMapper.queryById(r.getMem_id());
				double deposit=logM.getDeposit()-r.getAmount();
				Member updM=new Member();
				updM.setId(r.getMem_id());
				updM.setDeposit(deposit);
				memberMapper.updateDeposit(updM);
				//添加退返保证金流水
				paymentLogMapper.add(new PaymentLog(logM.getId(),null,r.getAmount(),"保证金退返",null,null, 4, 1, 4,null));
			}
		}else {
			//拒绝后，添加消息
			List<RefundDeposit> deposits=mapper.queryBySns(sns);
			for(int i=0;i<deposits.size();i++){
				messageMapper.add(new Message(deposits.get(i).getMem_id(),"保证金退还已拒绝",null,po.getRemark()));
			}
		}
		return true;
	}

	@Override
	public Page<RefundDeposit> queryPage(BaseConditionVO vo) {
		PaginationInterceptor.startPage(vo.getPageNo(),vo.getPageSize());
		mapper.queryPage(vo);
		Page<RefundDeposit> page=PaginationInterceptor.endPage();
		return page;
	}
	
}
