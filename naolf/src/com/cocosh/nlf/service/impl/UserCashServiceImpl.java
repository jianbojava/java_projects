package com.cocosh.nlf.service.impl;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cocosh.framework.annotation.LogClass;
import com.cocosh.framework.base.BaseConditionVO;
import com.cocosh.framework.mybatis.Page;
import com.cocosh.framework.mybatis.PaginationInterceptor;
import com.cocosh.framework.util.StringUtil;
import com.cocosh.member.mapper.MessageMapper;
import com.cocosh.member.model.Message;
import com.cocosh.nlf.mapper.UserCashMapper;
import com.cocosh.nlf.model.UserCash;
import com.cocosh.nlf.service.UserCashService;

@Transactional
@Service
public class UserCashServiceImpl implements UserCashService {
	@Autowired
	private UserCashMapper mapper;
	@Autowired
	private MessageMapper messageMapper;

	@LogClass(module = "用户提现管理", method = "添加")
	@Override
	public boolean add(UserCash po) {
		po.setId("TX"+WithdrawalsId());
		//添加消息
		Message message=new Message(po.getUser_id(), "提现申请","您申请的提现需求已提交，请等待审核打款" );
		messageMapper.add(message);
		return mapper.add(po) > 0;
	}
	
    @LogClass(module = "用户提现管理", method = "删除")
	@Override
	public boolean del(String ids) {
		return mapper.del(ids.split(",")) > 0;
	}

	@LogClass(module = "用户提现管理", method = "修改")
	@Override
	public boolean update(UserCash po) {
		return mapper.update(po) > 0;
	}

	@Override
	public Page<UserCash> queryPage(BaseConditionVO vo) {
		PaginationInterceptor.startPage(vo.getPageNo(),vo.getPageSize());
		mapper.queryPage(vo);
		Page<UserCash> page=PaginationInterceptor.endPage();
		return page;
	}

	@Override
	public UserCash queryById(String id) {
		return mapper.queryById(id);
	}
	
	@LogClass(module = "用户提现管理", method = "打款")
	@Override
	public String pay(String id) {
		String result= mapper.pay(id) ;
		//添加消息
		if ("0".equalsIgnoreCase(result)){
			UserCash ucash=mapper.queryById(id);
			Message message=new Message(ucash.getUser_id(), "提现完成打款","您申请的提现需求已完成打款，请确认查收" );
			messageMapper.add(message);
		}
		return result;
	}

	@Override
	public List<UserCash> queryWapAll(UserCash po) {
		return mapper.queryWapAll(po);
	}

	public String WithdrawalsId() {
		Date now = new Date();
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
		String dateString = formatter.format(now);

		String maxid = mapper.WithdrawalsId("TX"+dateString+"%");
		if (maxid == null || "".equals(maxid)) {
			return dateString + "001";
		}
		
		String journal = maxid.substring(maxid.length()-3, maxid.length());
		
		String next  = String.format("%0" + journal.length() + "d",
				Integer.parseInt(journal) + 1);
		
		return dateString+next;
	}
}
