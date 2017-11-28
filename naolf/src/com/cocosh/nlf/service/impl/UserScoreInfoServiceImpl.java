package com.cocosh.nlf.service.impl;


import java.math.BigDecimal;
import java.util.Date;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cocosh.car.mapper.PaymentLogMapper;
import com.cocosh.car.model.PaymentLog;
import com.cocosh.framework.annotation.LogClass;
import com.cocosh.framework.base.BaseConditionVO;
import com.cocosh.framework.mybatis.Page;
import com.cocosh.framework.mybatis.PaginationInterceptor;
import com.cocosh.framework.util.CommonUtil;
import com.cocosh.framework.util.StringUtil;
import com.cocosh.member.model.Member;
import com.cocosh.nlf.mapper.EmpDisJournalMapper;
import com.cocosh.nlf.mapper.UserScoreInfoMapper;
import com.cocosh.nlf.model.EmpDisJournal;
import com.cocosh.nlf.model.UserScoreInfo;
import com.cocosh.nlf.service.UserScoreInfoService;
import com.cocosh.sys.mapper.UserMapper;
import com.cocosh.sys.model.User;

@Transactional
@Service
public class UserScoreInfoServiceImpl implements UserScoreInfoService {
	@Autowired
	private UserScoreInfoMapper mapper;
	@Autowired
	private UserMapper userMapper;
	@Autowired
	private EmpDisJournalMapper empDisJournalMapper;
	@Autowired
	private PaymentLogMapper paylogMapper;

	/*@LogClass(module = "员工提成积分信息管理", method = "添加")
	@Override
	public boolean add(UserScoreInfo po) {
		po.setId(StringUtil.getUuid());
		return mapper.add(po) > 0;
	}*/
	
    @LogClass(module = "员工提成积分信息管理", method = "删除")
	@Override
	public boolean del(String ids) {
		return mapper.del(ids.split(",")) > 0;
	}

	@LogClass(module = "员工提成积分信息管理", method = "修改")
	@Override
	public boolean update(UserScoreInfo po) {
		return mapper.update(po) > 0;
	}

	@Override
	public Page<UserScoreInfo> queryPage(BaseConditionVO vo) {
		PaginationInterceptor.startPage(vo.getPageNo(),vo.getPageSize());
		Subject subject = SecurityUtils.getSubject();
		User user = (User) subject.getSession().getAttribute("user");  
		vo.setOperat_id(user.getId());
		mapper.queryPage(vo);
		Page<UserScoreInfo> page=PaginationInterceptor.endPage();
		return page;
	}

	@Override
	public UserScoreInfo queryByUser(String userid) {
		return mapper.queryByUser(userid);
	}
	
	public Integer getDispatchGrade(String user_number){
		return mapper.getDispatchGrade(user_number);
	}
	
	public String getDepart1grade(String depart_id){
		return mapper.getDepart1grade(depart_id);
	}
	@Override
	public Integer buyKD_score(String user_id, BigDecimal KD_SCORE) {
		UserScoreInfo userscore = mapper.queryByUser(user_id);
		User user= userMapper.queryById(user_id);
		EmpDisJournal empDisJournal = new EmpDisJournal();
		if (userscore == null) {//用户没有积分记录
			userscore = new UserScoreInfo();
			userscore.setUser_number(user.getNumber());
			userscore.setBKZ_SCORE(new BigDecimal(0));
            userscore.setKZ_SCORE(new BigDecimal(0));
            userscore.setKD_SCORE(KD_SCORE);
			userscore.setLast_update_date(new Date());
			mapper.add(userscore);
		} else {//用户有积分记录
			userscore.setKD_SCORE(userscore.getKD_SCORE().add(KD_SCORE));
			userscore.setUser_number(user.getNumber());
			mapper.update(userscore);
		}
		empDisJournal.setUser_number(user.getNumber());
		empDisJournal.setBKZ_SCORE(new BigDecimal(0));
		empDisJournal.setKZ_SCORE(new BigDecimal(0));
		empDisJournal.setKD_SCORE(KD_SCORE);
		empDisJournal.setORDER_NO(StringUtil.buildOrderSn());//充值编号
		empDisJournal.setTRANSFER_name("SRC");//积分充值
		empDisJournalMapper.add(empDisJournal);
		return 0;
	}

	@Override
	public boolean pay_success(String sn, String trade_no, Integer pay_type) {
		if (paylogMapper.queryPayValid(trade_no)==0) {//支付验证防止一直回调
			PaymentLog log=paylogMapper.queryBySn(sn);
			//修改支付记录
			log.setTrade_no(trade_no);
			log.setPay_type(pay_type);
			log.setPay_status(1);
			paylogMapper.update(log);
			//添加充值记录
			buyKD_score(log.getMem_id(), new BigDecimal(log.getAmount()*CommonUtil.SCOREPERCENT));
		}
		return true;
	}
	
}
