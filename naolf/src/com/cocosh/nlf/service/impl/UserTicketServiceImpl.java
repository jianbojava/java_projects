package com.cocosh.nlf.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cocosh.framework.annotation.LogClass;
import com.cocosh.framework.base.BaseConditionVO;
import com.cocosh.framework.mybatis.Page;
import com.cocosh.framework.mybatis.PaginationInterceptor;
import com.cocosh.framework.util.StringUtil;
import com.cocosh.nlf.mapper.LessonMapper;
import com.cocosh.nlf.mapper.RuleMapper;
import com.cocosh.nlf.mapper.TicketMapper;
import com.cocosh.nlf.mapper.UpruleMapper;
import com.cocosh.nlf.mapper.UserTicketMapper;
import com.cocosh.nlf.model.Lesson;
import com.cocosh.nlf.model.Rule;
import com.cocosh.nlf.model.Ticket;
import com.cocosh.nlf.model.Uprule;
import com.cocosh.nlf.model.UserTicket;
import com.cocosh.nlf.service.UserTicketService;
import com.cocosh.sys.mapper.UserMapper;
import com.cocosh.sys.model.User;

@Transactional
@Service
public class UserTicketServiceImpl implements UserTicketService {
	@Autowired
	private UserTicketMapper mapper;
	@Autowired
	private LessonMapper lessonMapper;
	@Autowired
	private UserTicketMapper userTicketMapper;
	@Autowired
	private TicketMapper ticketMapper;
	@Autowired
	private RuleMapper ruleMapper;
	@Autowired
	private UserMapper userMapper;
	@Autowired
	private UpruleMapper upmapper;

	@LogClass(module = "会员卡券管理", method = "添加")
	@Override
	public boolean add(UserTicket po) {
		po.setId(StringUtil.getUuid());
		return mapper.add(po) > 0;
	}
	
	@LogClass(module = "添加课程到卡券", method = "添加")
	@Override
	public boolean addWapLesson(UserTicket po) {
		Subject subject = SecurityUtils.getSubject();
		User user = (User) subject.getSession().getAttribute("wapuser");
	    Lesson lesson = lessonMapper.queryById(po.getConn_id());
		Integer create_sn=userTicketMapper.queryMaxCreateSn(lesson.getRule_id());//查询已存在的最大编号
		Rule rule=ruleMapper.queryById(lesson.getRule_id());
		String ticket_sn=rule.getSn_header()+""+rule.getSn_start()+""+StringUtil.getNumber(create_sn)+""+rule.getSn_end();
		po.setId(StringUtil.getUuid());
		po.setUsed(1);
		po.setType(0);
		po.setUser_id(user.getId());
		po.setSn(ticket_sn);
		po.setCreate_sn(create_sn+1);
		po.setRule_id(rule.getId());
		po.setUsed_id(user.getId());
		po.setStatus(0);
		return mapper.add(po) > 0;
	}
	
	@LogClass(module = "添加卡券到卡券", method = "添加")
	@Override
	public boolean addWapTicket(UserTicket po) {
		Subject subject = SecurityUtils.getSubject();
		User user = (User) subject.getSession().getAttribute("wapuser");
	    Ticket ticket = ticketMapper.queryById(po.getConn_id());
		Integer create_sn=userTicketMapper.queryMaxCreateSn(ticket.getRule_id());//查询已存在的最大编号
		Rule rule=ruleMapper.queryById(ticket.getRule_id());
		String ticket_sn=rule.getSn_header()+""+rule.getSn_start()+""+StringUtil.getNumber(create_sn)+""+rule.getSn_end();
		po.setId(StringUtil.getUuid());
		po.setUsed(1);
		po.setType(1);
		po.setUser_id(user.getId());
		po.setSn(ticket_sn);
		po.setCreate_sn(create_sn+1);
		po.setRule_id(rule.getId());
		po.setUsed_id(user.getId());
		po.setStatus(0);
		return mapper.add(po) > 0;
	}
	
	@LogClass(module = "会员使用卡券", method = "添加")
	@Override
	public boolean updateWap(UserTicket po) {
		Subject subject = SecurityUtils.getSubject();
		User user = (User) subject.getSession().getAttribute("wapuser");
		po.setUsed(1);
		po.setType(1);
		po.setUsed_id(user.getId());
		po.setStatus(0);
		return mapper.update(po) > 0;
	}
	
    @LogClass(module = "会员卡券管理", method = "删除")
	@Override
	public boolean del(String ids) {
		return mapper.del(ids.split(",")) > 0;
	}

	@LogClass(module = "会员卡券管理", method = "修改")
	@Override
	public boolean update(UserTicket po) {
		return mapper.update(po) > 0;
	}

	@Override
	public Page<UserTicket> queryPage(BaseConditionVO vo) {
		PaginationInterceptor.startPage(vo.getPageNo(),vo.getPageSize());
		Subject subject = SecurityUtils.getSubject();
		User user = (User) subject.getSession().getAttribute("user");    
		vo.setOperat_id(user.getId());
		mapper.queryPage(vo);
		Page<UserTicket> page=PaginationInterceptor.endPage();
		return page;
	}
	@Override
	public UserTicket queryById(String id) {
		return mapper.queryById(id);
	}


	@Override
	public List<UserTicket> queryUticket(UserTicket u) {
		return mapper.queryUticket(u);
	}

	@Override
	public List<UserTicket> queryAll() {
		return mapper.queryAll();
	}

	@Override
	public List<UserTicket> queryLessonByNorderId(String norder_id) {
		UserTicket ut=new UserTicket();
		ut.setNorder_id(norder_id);
		ut.setType(0);
		return mapper.query(ut);
	}

	@Override
	public UserTicket queryTicketByNorderId(String norder_id) {
		UserTicket ut=new UserTicket();
		ut.setNorder_id(norder_id);
		ut.setType(1);
		List<UserTicket> list= mapper.query(ut);
		if(list.size()>0) return list.get(0);
		return null;
	}
	
	@Override
	public UserTicket queryBySn(String sn) {
		return mapper.queryBySn(sn);
	}

	@Override
	public List<UserTicket> queryUticketByUserId(String user_id) {
		return mapper.queryUticketByUserId(user_id);
	}

	@Override
	public UserTicket querydetails(String id) {
		UserTicket userTicket = mapper.queryById(id);
		userTicket.setUser_name(userMapper.queryById(userTicket.getUser_id()).getName());
		return userTicket;
	}

	@Override
	public List<UserTicket> queryByName(String ticket_name, String user_id) {
		Ticket t=new Ticket();
		t.setName(ticket_name);
		t=ticketMapper.checkName(t);
		if(t==null) return null;
		UserTicket ut=new UserTicket();
		ut.setConn_id(t.getId());
		ut.setUsed(0);
		ut.setUser_id(user_id);
		ut.setType(1);
		List<UserTicket> list= mapper.queryUticket(ut);
		for(UserTicket uticket:list){
			uticket.setImage(t.getImage());
		}
		return list;
	}

	@Override
	public List<UserTicket> queryupCard(String user_ticket_id, String user_id) {
		UserTicket ut=mapper.queryById(user_ticket_id);
		Uprule uprule=upmapper.queryone();
		List<UserTicket> list=new ArrayList<UserTicket>();
		if(ut!=null&&uprule!=null){
			UserTicket qut=new UserTicket();
		    qut.setUsed(0);
		    qut.setUser_id(user_id);
		    qut.setType(1);
		    qut.setConn_id(uprule.getTicket_id2());
			if(ut.getUsed()==0&&ut.getUpgrade()==0){//未使用的券升级
				Ticket t=ticketMapper.queryById(uprule.getTicket_id2());
			    list=mapper.queryUticket(qut);
			    if(t!=null){
			    	for (UserTicket uticket:list) {
						uticket.setImage(t.getImage());
					}
			    }
			}
			if(ut.getUsed()==1&&ut.getUpgrade()==0){//已使用的卡券升级
				list=mapper.queryUsedCanUpgrade(qut);
			}
		}
		return list;
	}
}
