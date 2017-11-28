package com.cocosh.nlf.service.impl;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
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
import com.cocosh.nlf.mapper.EmpDisJournalMapper;
import com.cocosh.nlf.mapper.LessonMapper;
import com.cocosh.nlf.mapper.RechargeMapper;
import com.cocosh.nlf.mapper.RuleMapper;
import com.cocosh.nlf.mapper.TicketMapper;
import com.cocosh.nlf.mapper.UserScoreInfoMapper;
import com.cocosh.nlf.mapper.UserTicketMapper;
import com.cocosh.nlf.model.EmpDisJournal;
import com.cocosh.nlf.model.Recharge;
import com.cocosh.nlf.model.Rule;
import com.cocosh.nlf.model.Ticket;
import com.cocosh.nlf.model.UserScoreInfo;
import com.cocosh.nlf.model.UserTicket;
import com.cocosh.nlf.service.RechargeService;
import com.cocosh.sys.mapper.UserMapper;
import com.cocosh.sys.model.User;

@Transactional
@Service
public class RechargeServiceImpl implements RechargeService {
	@Autowired
	private RechargeMapper mapper;
	@Autowired
	private UserTicketMapper uticketMapper;
	@Autowired
	private TicketMapper ticketMapper;
	@Autowired
	private LessonMapper lessonMapper;
	@Autowired
	private RuleMapper ruleMapper;
	@Autowired
	private UserTicketMapper userTicketMapper;
	@Autowired
	private UserScoreInfoMapper userScoreInfoMapper;
	@Autowired
	private UserMapper userMapper;
	@Autowired
	private EmpDisJournalMapper empDisJournalMapper;

	@SuppressWarnings("unchecked")
	@LogClass(module = "充值管理", method = "添加")
	@Override
	public boolean add(Recharge po) {
		List<Ticket> list = po.getTickets();
		UserTicket t = new UserTicket();
		Subject subject = SecurityUtils.getSubject();
		User user = (User) subject.getSession().getAttribute("user");
		//String id = StringUtil.getUuid();
		
		String id = generatId();//modified by wjx 20170307
		
		po.setId(id);
		po.setCreate_date(new Date());
		po.setOperate_id(user.getId());
		
		
		mapper.add(po);
		if (po.getRecharge_type() == 0) {
			for (Ticket tk : list) {
				for (int j = 0; j < tk.getNum(); j++) {
					Integer create_sn = uticketMapper.queryMaxCreateSn(tk
							.getRule_id());// 查询已存在的最大编号
					Rule rule = ruleMapper.queryById(tk.getRule_id());
					String ticket_sn = rule.getSn_header() + ""
							+ rule.getSn_start() + ""
							+ StringUtil.getNumber(create_sn) + ""
							+ rule.getSn_end();
					t.setId(StringUtil.getUuid());
					t.setUsed(0);
					t.setCreate_date(new Date());
					t.setCreate_sn(create_sn + 1);
					t.setSn(ticket_sn);
					t.setRule_id(tk.getRule_id());
					t.setRecharge_id(po.getId());
					t.setUser_id(po.getUser_id());
					t.setConn_id(tk.getId());
					t.setType(1);
					t.setUpgrade(0);
					userTicketMapper.add(t);
				}
			}
		} else if (po.getRecharge_type() == 1) {
			UserScoreInfo userscore = userScoreInfoMapper.queryByUser(po
					.getUser_id());
			User userInfo = userInfo = userMapper.queryById(po.getUser_id());

			EmpDisJournal empDisJournal = new EmpDisJournal();

			if (userscore == null) {
				userscore = new UserScoreInfo();
				// 獲取用戶編號
				userscore.setUser_number(userInfo.getNumber());
				userscore.setBKZ_SCORE(new BigDecimal(0));

				if (po.getKZscore() == null) {
					userscore.setKZ_SCORE(new BigDecimal(0));
				} else
					userscore.setKZ_SCORE(po.getKZscore());
				
				
				if (po.getBKZscore() == null) {
					userscore.setBKZ_SCORE(new BigDecimal(0));
				} else
					userscore.setBKZ_SCORE(po.getBKZscore());

				if (po.getKDscore() == null)
					userscore.setKD_SCORE(new BigDecimal(0));
				else
					userscore.setKD_SCORE(po.getKDscore());

				userscore.setLast_update_date(new Date());

				userScoreInfoMapper.add(userscore);
			} else {
				if (po.getKDscore() != null)
					userscore.setKD_SCORE(userscore.getKD_SCORE().add(
							po.getKDscore()));
				else
					userscore.setKD_SCORE(userscore.getKD_SCORE());

				if (po.getKZscore() != null)
					userscore.setKZ_SCORE(userscore.getKZ_SCORE().add(
							po.getKZscore()));
				else
					userscore.setKZ_SCORE(userscore.getKZ_SCORE());
				
				
				if (po.getBKZscore() != null)
					userscore.setBKZ_SCORE(userscore.getBKZ_SCORE().add(
							po.getBKZscore()));
				else
					userscore.setBKZ_SCORE(userscore.getBKZ_SCORE());

				userscore.setUser_number(userInfo.getNumber());
				userScoreInfoMapper.update(userscore);
			}

			empDisJournal.setUser_number(userInfo.getNumber());
			empDisJournal.setBKZ_SCORE(po.getBKZscore());
			empDisJournal.setKZ_SCORE(po.getKZscore());
			empDisJournal.setKD_SCORE(po.getKDscore());
			empDisJournal.setORDER_NO(id);
			
			if(po.getReason_type()==4){
				empDisJournal.setTRANSFER_name("SRCRET");
			}else
				empDisJournal.setTRANSFER_name("SRC");

			empDisJournalMapper.add(empDisJournal);

		}
		return true;
	}

	public String generatId() {
		Date now = new Date();
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
		String dateString = formatter.format(now);

		String maxid = mapper.generatId(dateString+"%");
		if (maxid == null || "".equals(maxid)) {
			return dateString + "001";
		}
		
		String journal = maxid.substring(maxid.length()-3, maxid.length());
		
		String next  = String.format("%0" + journal.length() + "d",
				Integer.parseInt(journal) + 1);
		
		return dateString+next;
	}

	
	
	@LogClass(module = "充值管理", method = "删除")
	@Override
	public boolean del(String ids) {
		return mapper.del(ids.split(",")) > 0;
	}

	@LogClass(module = "充值管理", method = "修改")
	@Override
	public boolean update(Recharge po) {
		return mapper.update(po) > 0;
	}

	@Override
	public Page<Recharge> queryPage(BaseConditionVO vo) {
		PaginationInterceptor.startPage(vo.getPageNo(), vo.getPageSize());
		Subject subject = SecurityUtils.getSubject();
		User user = (User) subject.getSession().getAttribute("user");    
		vo.setOperat_id(user.getId());
		mapper.queryPage(vo);
		Page<Recharge> page = PaginationInterceptor.endPage();
		return page;
	}

	/**
	 * 充值大概信息
	 */
	@Override
	public Page<Recharge> querySumPage(BaseConditionVO vo) {
		PaginationInterceptor.startPage(vo.getPageNo(), vo.getPageSize());
		mapper.querySumPage(vo);
		Page<Recharge> page = PaginationInterceptor.endPage();
		return page;
	}

	@Override
	public Recharge queryById(String id) {
		return mapper.queryById(id);
	}

	@Override
	public List<Recharge> queryAll() {
		return mapper.queryAll();
	}
	
	public static void main(String[] args) {
		RechargeServiceImpl imp = new RechargeServiceImpl();
		System.out.println( imp.generatId());
	}
}
