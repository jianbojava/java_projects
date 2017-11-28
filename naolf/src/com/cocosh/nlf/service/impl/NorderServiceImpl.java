package com.cocosh.nlf.service.impl;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cocosh.car.mapper.OrderLogMapper;
import com.cocosh.car.mapper.PaymentLogMapper;
import com.cocosh.car.model.OrderLog;
import com.cocosh.car.model.PaymentLog;
import com.cocosh.framework.annotation.LogClass;
import com.cocosh.framework.base.AjaxResult;
import com.cocosh.framework.base.BaseConditionVO;
import com.cocosh.framework.mybatis.Page;
import com.cocosh.framework.mybatis.PaginationInterceptor;
import com.cocosh.framework.util.CommonUtil;
import com.cocosh.framework.util.SecurityUtil;
import com.cocosh.framework.util.StringUtil;
import com.cocosh.member.mapper.MessageMapper;
import com.cocosh.member.model.Message;
import com.cocosh.nlf.mapper.AppointMapper;
import com.cocosh.nlf.mapper.DeptPerformanceMapper;
import com.cocosh.nlf.mapper.LcommentMapper;
import com.cocosh.nlf.mapper.LessonMapper;
import com.cocosh.nlf.mapper.NorderMapper;
import com.cocosh.nlf.mapper.OrderDisRateSettingMapper;
import com.cocosh.nlf.mapper.RuleMapper;
import com.cocosh.nlf.mapper.TicketMapper;
import com.cocosh.nlf.mapper.UpruleMapper;
import com.cocosh.nlf.mapper.UserScoreInfoMapper;
import com.cocosh.nlf.mapper.UserTicketMapper;
import com.cocosh.nlf.model.Appoint;
import com.cocosh.nlf.model.DeptPerformance;
import com.cocosh.nlf.model.Lcomment;
import com.cocosh.nlf.model.Lesson;
import com.cocosh.nlf.model.Norder;
import com.cocosh.nlf.model.Rule;
import com.cocosh.nlf.model.Ticket;
import com.cocosh.nlf.model.UserScoreInfo;
import com.cocosh.nlf.model.UserTicket;
import com.cocosh.nlf.service.NorderService;
import com.cocosh.sys.mapper.DepartMapper;
import com.cocosh.sys.mapper.UserMapper;
import com.cocosh.sys.model.Depart;
import com.cocosh.sys.model.User;

@Transactional
@Service
public class NorderServiceImpl implements NorderService {
	@Autowired
	private NorderMapper mapper;
	@Autowired
	private UserMapper userMapper;
	@Autowired
	private LessonMapper lessonMapper;
	@Autowired
	private TicketMapper ticketMapper;
	@Autowired
	private OrderLogMapper logMapper;
	@Autowired
	private UserTicketMapper uticketMapper;
	@Autowired
	private RuleMapper ruleMapper;
	@Autowired
	private AppointMapper appointMapper;
	@Autowired
	private LcommentMapper commentMapper;
	
	@Autowired
	private UserScoreInfoMapper userScoreInfoMapper;
	@Autowired
	private PaymentLogMapper paymentLogMapper;
	@Autowired
	private OrderDisRateSettingMapper rateMapper;
	@Autowired
	private DeptPerformanceMapper dpMapper;
	@Autowired
	private MessageMapper messageMapper;
	@Autowired
	private UpruleMapper upruleMapper;
	@Autowired
	private DepartMapper departMapper;
	

	@LogClass(module = "订单管理", method = "添加")
	@Override
	public boolean add(Norder po) {
		if(po.getCreate_date()==null) po.setCreate_date(new Date());
		User user=userMapper.queryById(po.getUser_id());
		po.setRefer_number(user.getRefer_number());
		po.setNumber(user.getNumber());
		po.setDepart_id(user.getDepart_id());
		po.setSn(StringUtil.buildOrderSn());//订单编号
		po.setId(StringUtil.getUuid());
		po.setKz_score(BigDecimal.ZERO);//积分
		po.setKd_score(BigDecimal.ZERO);//
		po.setAddtype(1);
		po.setPay_type(1);
		po.setStatus(0);
		po.setUptype(0);
		if(po.getType()==0){//课程订单
			Lesson lesson=lessonMapper.queryById(po.getConn_id());
			po.setPrice(lesson.getPrice());
			po.setName(lesson.getName());
			po.setPay_amount(0.0);
			try {
				po.setMobile(SecurityUtil.decrypt(user.getMobile()));//手机号解密
			} catch (Exception e) {
				e.printStackTrace();
			}
			po.setBuy_type(1);//课程订单
		}else{//卡券订单
			Ticket ticket=ticketMapper.queryById(po.getConn_id());
			po.setPrice(ticket.getPrice());
			po.setName(ticket.getName());
			po.setPay_amount(0.0);
			String norder_ticket_sn=po.getTicket_sn();
			if(StringUtil.isEmpty(norder_ticket_sn)){//购买系统的卡券
				try {
					po.setMobile(SecurityUtil.decrypt(user.getMobile()));//手机号解密
				} catch (Exception e) {
					e.printStackTrace();
				}
				po.setBuy_type(1);
			}else{//使用推荐人的卡
                UserTicket uticket=uticketMapper.queryBySn(norder_ticket_sn);
                if(uticket==null||uticket.getUsed()!=0) return false;
                po.setBuy_type(0);
                User puser=userMapper.queryByNumber(user.getRefer_number());
                if(puser!=null&&(!StringUtil.isEmpty(puser.getMobile()))){//推荐人手机号
	                try {
	    				po.setMobile(SecurityUtil.decrypt(puser.getMobile()));//手机号解密
	    			} catch (Exception e) {
	    				e.printStackTrace();
	    			}
                }
			}
		}
		Subject subject = SecurityUtils.getSubject();
		User sysUser = (User) subject.getSession().getAttribute("user");
		logMapper.add(new OrderLog(StringUtil.getUuid(),po.getId(),sysUser.getId(),null,"创建订单"));
//		logMapper.add(new OrderLog(StringUtil.getUuid(),po.getId(),sysUser.getId(),null,"订单支付成功"));
		return mapper.add(po) > 0;
	}
	
    @LogClass(module = "订单管理", method = "删除")
	@Override
	public boolean del(String ids) {
		return mapper.del(ids.split(",")) > 0;
	}
	@Override
	public AjaxResult addNorder(Norder norder) {
		if(norder.getCreate_date()==null) norder.setCreate_date(new Date());
		Integer type=norder.getType();//0,课程订单，1卡券
		String norder_id=StringUtil.getUuid();//订单id
		Subject subject = SecurityUtils.getSubject();
		User user = (User) subject.getSession().getAttribute("wapuser");
		if(user==null) return  new AjaxResult(CommonUtil.ERROR_CODE,"请先登录",null);
		if(StringUtil.isEmpty(user.getRefer_number())){//该用户没有推荐人
			String refer_number=norder.getRefer_number();
            User puser=userMapper.queryByNumber(refer_number);
            if(puser==null) return new AjaxResult(CommonUtil.ERROR_CODE,"推荐人不存在",null);
            user.setRefer_number(refer_number);
            user.setDepart_id(puser.getDepart_id());
		}
		//验证积分
	    BigDecimal total_score=new BigDecimal(0);//总积分
		if(norder.getKz_score()!=null||norder.getKd_score()!=null){
			BigDecimal user_kd=new BigDecimal(0);
			BigDecimal user_kz=new BigDecimal(0);
			UserScoreInfo scoreInfo=userScoreInfoMapper.queryByUser(user.getId());
			if(scoreInfo!=null){
				user_kd=scoreInfo.getKD_SCORE();
				user_kz=scoreInfo.getKZ_SCORE();
			}
			if(user_kd.compareTo(norder.getKd_score())<0||user_kz.compareTo(norder.getKz_score())<0){
				return new AjaxResult(CommonUtil.ERROR_CODE,"会员积分不足",null);
			}
			total_score=norder.getKd_score().add(norder.getKz_score());
		}
		norder.setId(norder_id);
	   norder.setUser_id(user.getId());
	   norder.setSn(StringUtil.buildOrderSn());//订单编号
	   norder.setNumber(user.getNumber());//推荐人编号
	   norder.setDepart_id(user.getDepart_id());//部门
	   norder.setStatus(0);
	   norder.setRefer_number(user.getRefer_number());
	   norder.setNumber(user.getNumber());
	   norder.setAddtype(0);
	   norder.setPay_amount(0.0);
	   norder.setUptype(0);
		if(type==0&&(StringUtil.isEmpty(norder.getTicket_sn()))){//课程订单
		   Lesson lesson=lessonMapper.queryById(norder.getConn_id());
		   if(lesson==null||lesson.getEnabled()==1) return new AjaxResult(CommonUtil.ERROR_CODE,"该课程不存在或者已禁用",null);
		   norder.setPrice(lesson.getPrice());
		   norder.setName(lesson.getName());
		   norder.setBuy_type(1);//课程订单
		}
		if(type==0&&(!StringUtil.isEmpty(norder.getTicket_sn()))){//券订单，从课程里边进入，只是输入了该券编号
			if(StringUtil.isEmpty(user.getRefer_number()))   return new AjaxResult(CommonUtil.ERROR_CODE,"您没有可购买的券",null);
			Lesson lesson =lessonMapper.queryById(norder.getConn_id());
			if(lesson==null||lesson.getEnabled()==1) return new AjaxResult(CommonUtil.ERROR_CODE,"该课程不存在或者已禁用",null);
			UserTicket uticket=uticketMapper.queryBySn(norder.getTicket_sn());
			if(uticket==null)  return new AjaxResult(CommonUtil.ERROR_CODE,"该券号不存在",null);
			if(uticket.getUsed()!=0) return new AjaxResult(CommonUtil.ERROR_CODE,"该券号已被使用",null);
			User puser=userMapper.queryByNumber(user.getRefer_number());
			if(puser==null||(!puser.getId().equals(uticket.getUser_id()))) return new AjaxResult(CommonUtil.ERROR_CODE,"该券不属于推荐人",null);
			Ticket  t=ticketMapper.queryById(uticket.getConn_id());
			if(t==null||t.getType()!=1||(!t.getLesson_ids().equals(norder.getConn_id()))) return new AjaxResult(CommonUtil.ERROR_CODE,"该券不能购买该课程",null);
			norder.setConn_id(uticket.getId());
			norder.setType(1);
			norder.setPrice(t.getPrice());
			norder.setName(t.getName());
			norder.setBuy_type(0);
		}
		if(type==1){//卡券订单
			if(!StringUtil.isEmpty(norder.getTicket_sn())){//卡券订单，从卡券进入，输入了卡券号
				if(StringUtil.isEmpty(user.getRefer_number()))   return new AjaxResult(CommonUtil.ERROR_CODE,"您没有可购买的券",null);
				UserTicket uticket=uticketMapper.queryBySn(norder.getTicket_sn());
				if(uticket==null)  return new AjaxResult(CommonUtil.ERROR_CODE,"该卡券号不存在",null);
				if(uticket.getUsed()!=0) return new AjaxResult(CommonUtil.ERROR_CODE,"该卡券号已被使用",null);
				User puser=userMapper.queryByNumber(user.getRefer_number());
				if(puser==null||(!puser.getId().equals(uticket.getUser_id()))) return new AjaxResult(CommonUtil.ERROR_CODE,"该券不属于推荐人",null);
				Ticket  t=ticketMapper.queryById(uticket.getConn_id());
				if(t==null) return new AjaxResult(CommonUtil.ERROR_CODE,"该卡券不存在",null);
				//卡券号和不是该卡券的号
				if(!uticket.getConn_id().equals(norder.getConn_id())) return new AjaxResult(CommonUtil.ERROR_CODE,"该券不能购买该课程",null);
				norder.setType(1);
				norder.setPrice(t.getPrice());
				norder.setName(t.getName());
				norder.setBuy_type(0);
			}else{//卡券订单，没有输入卡券号，从平台购买的卡
				Ticket  t=ticketMapper.queryById(norder.getConn_id());
				if(t==null) return new AjaxResult(CommonUtil.ERROR_CODE,"该卡券不存在",null);
				norder.setType(1);
				norder.setPrice(t.getPrice());
				norder.setName(t.getName());
				norder.setBuy_type(1);
			}
		}
		if(total_score.doubleValue()>0&&((total_score.doubleValue()/CommonUtil.SCOREPERCENT)!=norder.getPrice())){
			  return new AjaxResult(CommonUtil.ERROR_CODE,"提交失败，积分输入有误",null);
		}
		Integer result=mapper.add(norder);
		if(result>0){
			   //修改会员的部门和refer_number
			   userMapper.update(user);
			   logMapper.add(new OrderLog(StringUtil.getUuid(),norder.getId(),user.getId(),null,"创建订单"));
			   //如果会员使用的抵扣的积分>=订单总额,那么该订单被支付成功
			   double score_money=(total_score.doubleValue())/CommonUtil.SCOREPERCENT;
			   if(score_money==norder.getPrice().doubleValue()){
				   //调用去支付的方法;
				   Integer valid_result=payValid(norder.getId());
				   if(valid_result==0){//积分支付
					   pay_success(norder.getSn(), StringUtil.buildOrderSn(), 4);
					   return new AjaxResult("2","支付成功",norder);
				   }
			   }
			   return new AjaxResult(CommonUtil.SUCCESS_CODE,"提交成功",norder);
		}else{
			   return new AjaxResult(CommonUtil.ERROR_CODE,"提交失败",null);
		}
	}
	@LogClass(module = "订单管理", method = "修改")
	@Override
	public boolean update(Norder po) {
		return mapper.update(po) > 0;
	}
	
	@LogClass(module = "订单管理", method = "取消")
	@Override
	public boolean cancel(String id) {
		Integer result=0;
		Norder norder=mapper.queryById(id);
		if(norder.getStatus()==0){
			norder.setStatus(-1);
			result=mapper.update(norder);
			if(result>0){//取消成功
				Subject subject = SecurityUtils.getSubject();
				User sysUser = (User) subject.getSession().getAttribute("user");
				logMapper.add(new OrderLog(StringUtil.getUuid(),norder.getId(),sysUser.getId(),null,"取消订单"));
			}
		}
		return result>0;
	}
	
	@Override
	public boolean wapcancel(String id) {
		Integer result=0;
		Norder norder=mapper.queryById(id);
		if(norder.getStatus()==0){
			norder.setStatus(-1);
			result=mapper.update(norder);
			if(result>0){//取消成功
				logMapper.add(new OrderLog(StringUtil.getUuid(),norder.getId(),norder.getUser_id(),null,"取消订单"));
			}
		}
		return result>0;
	}

	@Override
	public Page<Norder> queryPage(BaseConditionVO vo) {
		PaginationInterceptor.startPage(vo.getPageNo(),vo.getPageSize());
		Subject subject = SecurityUtils.getSubject();
		User user = (User) subject.getSession().getAttribute("user"); 
		vo.setOperat_id(user.getId());
		mapper.queryPage(vo);
		Page<Norder> page=PaginationInterceptor.endPage();
		return page;
	}

	@Override
	public Norder queryById(String id) {
		return mapper.queryById(id);
	}

	@Override
	public List<Norder> queryByAll(Norder norder) {
		return mapper.queryByAll(norder);
	}

	@Override
	public List<Norder> queryLesson(Norder norder) {
		return mapper.queryLesson(norder);
	}

	@Override
	public List<Norder> queryTicket(Norder norder) {
		return mapper.queryTicket(norder);
	}

	@Override
	public boolean pay_success(String sn,String trade_no,Integer pay_type) {
		if (paymentLogMapper.queryPayValid(trade_no)==0) {
	        Norder norder=mapper.queryBySn(sn);
	        if(norder.getType()==0){//课程订单
	        	Lesson lesson=lessonMapper.queryById(norder.getConn_id());//课程
				Integer create_sn=uticketMapper.queryMaxCreateSn(lesson.getRule_id());//查询已存在的最大编号
				Rule rule=ruleMapper.queryById(lesson.getRule_id());
				String ticket_sn=rule.getSn_header()+""+rule.getSn_start()+""+StringUtil.getNumber(create_sn)+""+rule.getSn_end();
				UserTicket uticket=new UserTicket(StringUtil.getUuid(), 1, 0, norder.getUser_id(), ticket_sn, create_sn+1, norder.getConn_id(), lesson.getRule_id(), norder.getId(), norder.getUser_id(),null,0);
				uticketMapper.add(uticket);//课程信息
				norder.setTicket_sn(ticket_sn);//课程编号
	        }else{//卡券订单
	        	//如果是从推荐人购买的卡券
	        	if(!StringUtil.isEmpty(norder.getTicket_sn())){
	        		String ticket_sn=norder.getTicket_sn();
	        		UserTicket uticket=uticketMapper.queryBySn(ticket_sn);
	    			if(uticket!=null){//卡券变成已使用 ,
	    				uticket.setUsed(1);
	    				uticket.setNorder_id(norder.getId());
	    				uticket.setUsed_id(norder.getUser_id());
	    				uticket.setUsed_date(new Date());
	    				uticketMapper.update(uticket);
	    			}
	    			norder.setBuy_type(0);
	        	}else{//如果是从平台购买的卡券，
	        		Ticket ticket=ticketMapper.queryById(norder.getConn_id());
	        		Integer create_sn=uticketMapper.queryMaxCreateSn(ticket.getRule_id());
					Rule rule=ruleMapper.queryById(ticket.getRule_id());
					String ticket_sn=rule.getSn_header()+""+rule.getSn_start()+""+StringUtil.getNumber(create_sn)+""+rule.getSn_end();
					UserTicket uticket=new UserTicket(StringUtil.getUuid(), 1, 1, norder.getUser_id(), ticket_sn, create_sn+1, norder.getConn_id(), ticket.getRule_id(), norder.getId(), norder.getUser_id(),null,null);
					uticket.setUpgrade(0);//未升级的卡券
					uticketMapper.add(uticket);//卡券信息
					norder.setTicket_sn(ticket_sn);
					norder.setBuy_type(1);
	        	}
	        	Ticket ticket=ticketMapper.queryById(norder.getConn_id());
	        	if(ticket!=null){//userticket里添加课程信息
		        	if(!StringUtil.isEmpty(ticket.getLesson_ids())){
			        	String[] lesson_ids=ticket.getLesson_ids().split(",");
						for(String lesson_id:lesson_ids){//购买卡券订单包含的课程，
							if(!StringUtil.isEmpty(lesson_id)){
								Lesson lesson=lessonMapper.queryById(lesson_id);
								Integer create_sn=uticketMapper.queryMaxCreateSn(lesson.getRule_id());//查询已存在的最大编号
								Rule rule=ruleMapper.queryById(lesson.getRule_id());
								String ticket_sn=rule.getSn_header()+""+rule.getSn_start()+""+StringUtil.getNumber(create_sn)+""+rule.getSn_end();
								UserTicket uticket=new UserTicket(StringUtil.getUuid(), 1, 0, norder.getUser_id(), ticket_sn, create_sn+1, lesson_id, lesson.getRule_id(), norder.getId(), norder.getUser_id(),null,0);
								uticketMapper.add(uticket);//课程信息
							}
						}
		        	}
	        	}
	        }
	        //产生积分的金额
	        norder.setPay_amount(norder.getPrice()*CommonUtil.ACHIEVEPERCENT);
			norder.setStatus(1);//已支付
			norder.setPay_type(pay_type);
			
			//mapper.update(norder);
			
			// add by wjx 20170311
			// 更新用户积分
			if(mapper.update(norder)>0 
					&& ((norder.getKz_score() !=null&&norder.getKz_score().compareTo(BigDecimal.ZERO)==1)
							||(norder.getKd_score() !=null&&norder.getKd_score().compareTo(BigDecimal.ZERO)==1))){//支付完成
				Map param = new HashMap();
				param.put("user_id", norder.getUser_id());
				param.put("order_no", sn);
				param.put("kz_score", norder.getKz_score());
				param.put("kd_score", norder.getKd_score());
				
				userScoreInfoMapper.orderPayByscore(param);
			}
			
			
			
			String user_id=norder.getUser_id();
			Subject subject = SecurityUtils.getSubject();
			User sysUser = (User) subject.getSession().getAttribute("user");
			if(sysUser!=null) user_id=sysUser.getId();
			logMapper.add(new OrderLog(StringUtil.getUuid(),norder.getId(),user_id,null,"订单支付成功"));
			//添加支付记录
			Double  payoff=norder.getPrice();
			if(pay_type==4) payoff=0.0;//积分订单 支付金额为0;
			paymentLogMapper.add(new PaymentLog(norder.getUser_id(), null,payoff,"订单支付",sn,trade_no, pay_type, 1, 0,null));
			//添加消息
			if(norder.getAddtype()==0||norder.getAddtype()==1){
				Message message=new Message(norder.getUser_id(), "订单支付","您的订单"+norder.getSn()+"已于"+StringUtil.getLocalData()+"完成支付" );
				messageMapper.add(message);
			}			
		}
		return true;
	}
	
	@LogClass(module = "订单管理", method = "线下支付")
	@Override
	public Integer topay(String id) {
		Integer result=payValid(id);
		if(result<0) return result;
		Norder norder=mapper.queryById(id);
		pay_success(norder.getSn(),StringUtil.buildOrderSn(),0);//调用支付成功方法
		return 0;
	}
	
    //wap端支付之前验证订单
	@Override
	public Integer payValid(String id) {
		Norder norder=mapper.queryById(id);
		if(norder==null||norder.getStatus()!=0) return -1;//订单不存在或不可支付
		/*订单是否直售，如果是直售需要校验
		0.refre_number 不能为空
		1.推荐人是否已经设置了提成身份等级  
		2.推荐人的部门必须设置了第一级提成身份等级人
		add by wjx 20170224*/
		
		if (norder.getRefer_number() == null || "".equals(norder.getRefer_number())){
			return -5;//未配置提成身份
		}
		
		if( norder.getBuy_type() == 1 ) {
			Integer grade = userScoreInfoMapper.getDispatchGrade(norder.getRefer_number());
			if (grade == null || grade == 0 ){
				return -3;//未配置提成身份
			}
			
			User referUser = userMapper.queryByNumber(norder.getRefer_number());
			
			String grade1number = userScoreInfoMapper.getDepart1grade(referUser.getDepart_id());
			if (grade1number==null || "".equals(grade1number)){
				return -4;
			}
		}
		/* **************
		 * 对于推荐人的部门是否是绩效部门判断
		 * 1.直接推荐人所在部门
		 * 2.直接推荐人关联的推荐人所在部门
		 * */
		Integer deptperfind = userScoreInfoMapper.getDeptPerformce(norder.getRefer_number());
		if(deptperfind==null || deptperfind !=1) {
			//判断推荐人是不是等级是1，如果是1的话在查询部门是不是有效部门
			User puser=userMapper.queryByNumber(norder.getRefer_number());
			if(puser!=null&&puser.getDispacth_grade()==1){
				Depart pdepart=departMapper.queryById(puser.getDepart_id());
				if(pdepart!=null&&pdepart.getPerformance_ind()==1){
					
				}else{
					return -6;
				}
			}else{
				return -6;
			}
		}
		
		
		if(norder.getType()==1){//卡券订单添加验证；
			String ticket_sn=norder.getTicket_sn();
			if(!StringUtil.isEmpty(ticket_sn)){
				UserTicket uticket=uticketMapper.queryBySn(ticket_sn);
				if(uticket==null||uticket.getUsed()!=0){//卡券订单的券已经被使用，订单取消
					norder.setStatus(-1);
					mapper.update(norder);
					return -2;//卡券已经被使用，订单已取消；
				}
			}
		}
		//添加积分验证;
		BigDecimal zero=new BigDecimal(0);
		BigDecimal norder_kd_score=norder.getKd_score()==null?new BigDecimal(0):norder.getKd_score();
		BigDecimal norder_kz_score=norder.getKz_score()==null?new BigDecimal(0):norder.getKz_score();
		if(norder_kd_score.compareTo(zero )>=1||norder_kz_score.compareTo(zero)>=1){//使用了积分
			UserScoreInfo scoreinfo=userScoreInfoMapper.queryByUser(norder.getUser_id());
			if(scoreinfo==null) return -7;//会员积分不足
			if(scoreinfo.getKD_SCORE().compareTo(norder_kz_score)<0||scoreinfo.getKZ_SCORE().compareTo(norder_kz_score)<0){
				return -7;//会员积分不足
			}
			double score_money=((norder_kd_score.add(norder_kz_score)).doubleValue())/CommonUtil.SCOREPERCENT;
			if(score_money!=norder.getPrice()){
				return -7;//会员订单使用的积分不等于订单的总额
			}
		}
		return 0;
	}

	@Override
	public List<Norder> querywap(Norder norder) {
		norder.setStatus(10);//已付款或者已退款
		norder.setUptype(0);
		List<Norder> list=mapper.queryByAll(norder);
		if(norder.getType()==1){//卡券//过滤学习升级券(未升级)的订单
			String ticket_id=upruleMapper.queryticket_id2();
			Iterator<Norder> it = list.iterator();
			while(it.hasNext()){
			    Norder n = it.next();
			    if(n.getConn_id().equals(ticket_id)&&n.getUptype()==0){
			        it.remove();
			    }
			}
		}
		for(Norder n:list){
			n.setHave_ut(0);//没有
			List<UserTicket> utlist=uticketMapper.queryLessonByNorderId(n.getId());
			if(!StringUtil.isEmpty(n.getUputicket())&&(!StringUtil.isEmpty(n.getUpnorder()))){
				List<UserTicket> uplist=uticketMapper.queryLessonByNorderId(n.getUpnorder());
				utlist.addAll(uplist);
				n.setTicket_sn(n.getUputicket());
				Ticket ticket=ticketMapper.queryByutSn(n.getUputicket());
				if(ticket!=null) n.setName(ticket.getName());
			}
			for(UserTicket ut:utlist){
				if(n.getStatus()!=-2) n.setHave_ut(1);//不是退款的全部展示
				if(n.getStatus()==-2){
					if(ut.getStatus()>=4) n.setHave_ut(1);//是退款的如果有已结业的展示；
				}
				ut.setLastappoint(appointMapper.queryLastAppoint(ut.getId()));//查询最近一次预约
				ut.setLastclass(appointMapper.queryLastClass(ut.getId()));//查询最近一次课程
				//查询课程是否评价过
				if(ut.getStatus()==4||ut.getStatus()==5){
					List<Lcomment> comment=commentMapper.queryByUtId(ut.getId());
					if(comment.size()==0) ut.setCancomm(0);//可以评价
				}
			}
			n.setUtlist(utlist);
		}
		return list ;
	}

	@Override
	public AjaxResult returnOrder(Norder norder,Double return_percent,Integer return_point) {
		BigDecimal retscore ;
		BigDecimal retzkscore ;
		norder=mapper.queryById(norder.getId());
		if(norder==null||norder.getStatus()!=1){
			 return new AjaxResult(CommonUtil.ERROR_CODE,"订单状态错误，不能退款",null);
		}
		//修改norder订单状态
		norder.setStatus(-2);
		Integer order_result=mapper.update(norder);
		if(order_result>0){
			//添加订单日志
			Subject subject = SecurityUtils.getSubject();
			User sysUser = (User) subject.getSession().getAttribute("user");
			logMapper.add(new OrderLog(StringUtil.getUuid(),norder.getId(),sysUser.getId(),null,"订单退款成功"));
			//修改为未结业的userticket的状态为2已取消；
			uticketMapper.updateToReturn(norder.getId());
			//修改未结业的集中预约为已取消
			appointMapper.updateToReturn(norder.getId());
			//给用户退积分（待完善）
//			System.out.println("-----退款积分："+return_point);
			
			
			
			if(return_point == null){
				return_point = (int) (norder.getPay_amount().intValue()* CommonUtil.SCOREPERCENT* (return_percent/100));
			}
			retscore = new BigDecimal(return_point);
			
			retzkscore = norder.getKz_score();
			if(norder.getKz_score()!=null && norder.getKz_score().compareTo(BigDecimal.ZERO)==1 ){
				retscore =retscore.subtract(retzkscore);
			}
			
			if(retscore.compareTo(BigDecimal.ZERO)<1){
				retzkscore = retscore.add(retzkscore);
				retscore = new BigDecimal(0);
			}
			
			//订单退款，积分返还 add by wjx
			Map params = new HashMap();
			params.put("order_id", norder.getId());
			params.put("order_no", norder.getSn());
			params.put("user_number", norder.getNumber());
			params.put("return_point", retscore);
			params.put("return_kzpoint", retzkscore);
			userScoreInfoMapper.orderRet(params);
			
			 return new AjaxResult(CommonUtil.SUCCESS_CODE,"退款成功",null);
			
		}
		
		
		 return new AjaxResult(CommonUtil.ERROR_CODE,"系统繁忙，请稍后再试",null);
	}

	@Override
	public Norder queryBySn(String sn) {
		return mapper.queryBySn(sn);
	}

	/**
	 * 添加已结业订单，不会产生积分提成，可是会产生部门业绩
	 */
	@Override
	public boolean addfinish(Norder po) {
		if(po.getCreate_date()==null) po.setCreate_date(new Date());
		User user=userMapper.queryById(po.getUser_id());
		po.setRefer_number(user.getRefer_number());
		po.setNumber(user.getNumber());
		po.setDepart_id(user.getDepart_id());
		po.setSn(StringUtil.buildOrderSn());//订单编号
		po.setId(StringUtil.getUuid());
		po.setKd_score(BigDecimal.ZERO);
		po.setKz_score(BigDecimal.ZERO);
		po.setAddtype(2);//已结业历史订单
		po.setPay_type(0);
		po.setStatus(1);
		po.setUptype(0);
		if(po.getType()==0){//课程订单
			Lesson lesson=lessonMapper.queryById(po.getConn_id());
			po.setPrice(lesson.getPrice());
			po.setName(lesson.getName());
			po.setPay_amount(lesson.getPrice()*CommonUtil.ACHIEVEPERCENT);
			try {
				po.setMobile(SecurityUtil.decrypt(user.getMobile()));//手机号解密
			} catch (Exception e) {
				e.printStackTrace();
			}
			Integer create_sn=uticketMapper.queryMaxCreateSn(lesson.getRule_id());//查询已存在的最大编号
			Rule rule=ruleMapper.queryById(lesson.getRule_id());
			String ticket_sn=rule.getSn_header()+""+rule.getSn_start()+""+StringUtil.getNumber(create_sn)+""+rule.getSn_end();
			UserTicket uticket=new UserTicket(StringUtil.getUuid(), 1, 0, po.getUser_id(), ticket_sn, create_sn+1, po.getConn_id(), lesson.getRule_id(), po.getId(), po.getUser_id(),null,0);
			if(lesson.getType()==0) uticket.setStatus(6);//课程已完成
			if(lesson.getType()==1) uticket.setStatus(4);//课程已完成
			uticketMapper.add(uticket);//课程信息
			po.setTicket_sn(ticket_sn);
			po.setBuy_type(1);//课程订单
		}else{//卡券订单
			Ticket ticket=ticketMapper.queryById(po.getConn_id());
			po.setPrice(ticket.getPrice());
			po.setName(ticket.getName());
			po.setPay_amount(ticket.getPrice()*CommonUtil.ACHIEVEPERCENT);
			Integer buy_type=po.getBuy_type();//0自售，1直售
			if(buy_type!=null&&buy_type==1){//购买系统的卡券
				Integer create_sn=uticketMapper.queryMaxCreateSn(ticket.getRule_id());
				Rule rule=ruleMapper.queryById(ticket.getRule_id());
				String ticket_sn=rule.getSn_header()+""+rule.getSn_start()+""+StringUtil.getNumber(create_sn)+""+rule.getSn_end();
				UserTicket uticket=new UserTicket(StringUtil.getUuid(), 1, 1, po.getUser_id(), ticket_sn, create_sn+1, po.getConn_id(), ticket.getRule_id(), po.getId(), po.getUser_id(),null,null);
				uticket.setUpgrade(0);
				uticketMapper.add(uticket);//卡券信息
				po.setTicket_sn(ticket_sn);
				po.setBuy_type(1);
				try {
					po.setMobile(SecurityUtil.decrypt(user.getMobile()));//手机号解密
				} catch (Exception e) {
					e.printStackTrace();
				}
			}else{//如果是自售后的话;那么这张卡是推荐人的只是被用户使用了userticket的user_id是puser
				User puser=userMapper.queryByNumber(user.getRefer_number());
				Integer create_sn=uticketMapper.queryMaxCreateSn(ticket.getRule_id());
				Rule rule=ruleMapper.queryById(ticket.getRule_id());
				String ticket_sn=rule.getSn_header()+""+rule.getSn_start()+""+StringUtil.getNumber(create_sn)+""+rule.getSn_end();
				UserTicket uticket=new UserTicket(StringUtil.getUuid(), 1, 1, puser.getId(), ticket_sn, create_sn+1, po.getConn_id(), ticket.getRule_id(), po.getId(), po.getUser_id(),null,null);
				uticket.setUpgrade(0);
				uticketMapper.add(uticket);//卡券信息
				po.setTicket_sn(ticket_sn);
                po.setBuy_type(0);
                if(puser!=null&&(!StringUtil.isEmpty(puser.getMobile()))){//推荐人手机号
	                try {
	    				po.setMobile(SecurityUtil.decrypt(puser.getMobile()));//手机号解密
	    			} catch (Exception e) {
	    				e.printStackTrace();
	    			}
                }
			}
			if(!StringUtil.isEmpty(ticket.getLesson_ids())){
				String[] lesson_ids=ticket.getLesson_ids().split(",");
				for(String lesson_id:lesson_ids){//购买卡券订单包含的课程，
					if(!StringUtil.isEmpty(lesson_id)){
						Lesson lesson=lessonMapper.queryById(lesson_id);
						Integer create_sn=uticketMapper.queryMaxCreateSn(lesson.getRule_id());//查询已存在的最大编号
						Rule rule=ruleMapper.queryById(lesson.getRule_id());
						String ticket_sn=rule.getSn_header()+""+rule.getSn_start()+""+StringUtil.getNumber(create_sn)+""+rule.getSn_end();
						UserTicket uticket=new UserTicket(StringUtil.getUuid(), 1, 0, po.getUser_id(), ticket_sn, create_sn+1, lesson_id, lesson.getRule_id(), po.getId(), po.getUser_id(),null,0);
						if(lesson.getType()==0) uticket.setStatus(6);//课程已完成
						if(lesson.getType()==1) uticket.setStatus(4);//课程已完成
						uticketMapper.add(uticket);//课程信息
					}
				}
			}
		}
		Subject subject = SecurityUtils.getSubject();
		User sysUser = (User) subject.getSession().getAttribute("user");
		logMapper.add(new OrderLog(StringUtil.getUuid(),po.getId(),sysUser.getId(),null,"创建订单"));
		logMapper.add(new OrderLog(StringUtil.getUuid(),po.getId(),sysUser.getId(),null,"订单支付成功"));
		//添加订单支付日志
		paymentLogMapper.add(new PaymentLog(po.getUser_id(), null,po.getPrice(),"订单支付",po.getSn(),StringUtil.buildOrderSn(), 0, 1, 0,null));
		Integer result= mapper.add(po);
		//添加部门提成
		User puser=userMapper.queryByNumber(user.getRefer_number());
        if(puser!=null){
        	BigDecimal score=BigDecimal.ZERO;
        	if(po.getBuy_type()==1){//直售
        	 score=new BigDecimal((rateMapper.queryByRate_type(CommonUtil.ZHIS_TJBM).getRate()/100)*CommonUtil.SCOREPERCENT*po.getPay_amount());
        	}
        	if(po.getBuy_type()==0){//自售
            	score=new BigDecimal((1-(rateMapper.queryByRate_type(CommonUtil.ZIS_TJR).getRate()/100))*CommonUtil.SCOREPERCENT*po.getPay_amount());
        	}
        	DeptPerformance p=new DeptPerformance();
        	p.setDepart_id(puser.getDepart_id());
        	p.setScore(score);
        	p.setCreate_date(po.getCreate_date());
        	p.setOrder_number(po.getSn());
        	dpMapper.addDepartScore(p);
        }
        return result>0;
	}

	/**
	 * >0成功<0失败
	 * 后台添加未结业历史课程订单
	 * 未结业：产生积分和提成，有部门绩效
	 *  已结业未复训:不产生积分和提成，有部门绩效
	 */
	@Override
	public Integer addnofinish(Norder po,Integer is_end,String student_id,Integer left_review) {
		if(po.getCreate_date()==null) po.setCreate_date(new Date());
		User user=userMapper.queryById(po.getUser_id());
		Subject subject = SecurityUtils.getSubject();
		User sysUser = (User) subject.getSession().getAttribute("user");
		po.setRefer_number(user.getRefer_number());
		po.setNumber(user.getNumber());
		po.setDepart_id(user.getDepart_id());
		po.setSn(StringUtil.buildOrderSn());//订单编号
		po.setId(StringUtil.getUuid());
		po.setKd_score(BigDecimal.ZERO);
		po.setKz_score(BigDecimal.ZERO);
		po.setAddtype(3);//未结业历史订单
		po.setPay_type(0);//线下支付
		po.setStatus(0);
		po.setUptype(0);
		if(po.getType()==0){//课程订单
			po.setBuy_type(1);//课程订单
			Lesson lesson=lessonMapper.queryById(po.getConn_id());
			po.setPrice(lesson.getPrice());
			po.setName(lesson.getName());
			po.setPay_amount(lesson.getPrice()*CommonUtil.ACHIEVEPERCENT);
			try {
				po.setMobile(SecurityUtil.decrypt(user.getMobile()));//手机号解密
			} catch (Exception e) {
				e.printStackTrace();
			}
			if(is_end==0){//未结业，会产生会员的积分和部门绩效
				//添加订单，然后调用支付的方法
				Integer result=mapper.add(po);
				logMapper.add(new OrderLog(StringUtil.getUuid(),po.getId(),sysUser.getId(),null,"创建订单"));
				if(result>0){//订单添加成功，调用线下支付的方法
					result=topay(po.getId());
					if(result==0) result=1;
				}
				return result;
			}else{//已结业，不产生会员积分，可是会产生部门绩效
				Integer create_sn=uticketMapper.queryMaxCreateSn(lesson.getRule_id());//查询已存在的最大编号
				Rule rule=ruleMapper.queryById(lesson.getRule_id());
				String ticket_sn=rule.getSn_header()+""+rule.getSn_start()+""+StringUtil.getNumber(create_sn)+""+rule.getSn_end();
				UserTicket uticket=new UserTicket(StringUtil.getUuid(), 1, 0, po.getUser_id(), ticket_sn, create_sn+1, po.getConn_id(), lesson.getRule_id(), po.getId(), po.getUser_id(),null,0);
				uticket.setStatus(4);//课程已完成
				po.setTicket_sn(ticket_sn);
				po.setStatus(1);
				if(lesson.getType()==1){//集中课程
					uticketMapper.add(uticket);//课程信息
				}else{
					Integer train=lesson.getTrain();//该课程的进阶训练课时
					if(train==0||left_review==0){//该课程的复训也结束了
						uticket.setStatus(6);//课程已完成
					}else{
						uticket.setStatus(5);//在复训中
						//先添加集中的训练，再添加复训的次数(这里的话随机设置appoint的course_id="-1")
						//集中，
						Appoint appoint=new Appoint(StringUtil.getUuid(), user.getId(), student_id, po.getId(), "0", lesson.getId(), 4, "0", new Date(), 1, uticket.getId());
					   appointMapper.add(appoint);
					   if(left_review>train) left_review=train;//进阶的复训数量大于输入的复训数
					   appoint.setCourse_type(0);
					   Integer finish_review=train-left_review;
					   //添加复训数量
					   for(int i=0;i<finish_review;i++){
						   appoint.setId(StringUtil.getUuid());
						   appoint.setCourse_id("0"+i);
						   appointMapper.add(appoint);
					   }
					}
					uticketMapper.add(uticket);//
				}
				//添加订单，然后添加部门业绩
				logMapper.add(new OrderLog(StringUtil.getUuid(),po.getId(),sysUser.getId(),null,"创建订单"));
				logMapper.add(new OrderLog(StringUtil.getUuid(),po.getId(),sysUser.getId(),null,"订单支付成功"));
				//添加订单支付日志
				paymentLogMapper.add(new PaymentLog(po.getUser_id(), null,po.getPrice(),"订单支付",po.getSn(),StringUtil.buildOrderSn(), 0, 1, 0,null));
				Integer result= mapper.add(po);
				//添加部门提成
				User puser=userMapper.queryByNumber(user.getRefer_number());
		        if(puser!=null){
		        	BigDecimal score=BigDecimal.ZERO;
		        	if(po.getBuy_type()==1){//直售
		        	 score=new BigDecimal((rateMapper.queryByRate_type(CommonUtil.ZHIS_TJBM).getRate()/100)*CommonUtil.SCOREPERCENT*po.getPay_amount());
		        	}
		        	DeptPerformance p=new DeptPerformance();
		        	p.setDepart_id(puser.getDepart_id());
		        	p.setScore(score);
		        	p.setCreate_date(po.getCreate_date());
		        	p.setOrder_number(po.getSn());
		        	dpMapper.addDepartScore(p);
		        }
		        return result;		
			}
		}
		return 0;
		
	}

	/**
	 * 后台添加未结业历史卡券订单(全部未结业)
	 */
	@Override
	public Integer addnofinishticket(Norder po) {
		if(po.getCreate_date()==null) po.setCreate_date(new Date());
		User user=userMapper.queryById(po.getUser_id());
		Subject subject = SecurityUtils.getSubject();
		User sysUser = (User) subject.getSession().getAttribute("user");
		po.setRefer_number(user.getRefer_number());
		po.setNumber(user.getNumber());
		po.setDepart_id(user.getDepart_id());
		po.setSn(StringUtil.buildOrderSn());//订单编号
		po.setId(StringUtil.getUuid());
		po.setKd_score(BigDecimal.ZERO);
		po.setKz_score(BigDecimal.ZERO);
		po.setAddtype(3);//未结业历史订单
		po.setPay_type(0);//线下支付
		po.setStatus(0);
		po.setUptype(0);
		//全部未结业卡券订单
		Ticket ticket=ticketMapper.queryById(po.getConn_id());
		po.setPrice(ticket.getPrice());
		po.setName(ticket.getName());
		po.setPay_amount(0.0);
		Integer buy_type=po.getBuy_type();//0自售，1直售
		if(buy_type!=null&&buy_type==1){//购买系统的卡券(后边会调用支付方法，会产生卡券)
			try {
				po.setMobile(SecurityUtil.decrypt(user.getMobile()));//手机号解密
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else{//如果是自售后的话;那么这张卡是推荐人的只是被用户使用了userticket的user_id是puser
			User puser=userMapper.queryByNumber(user.getRefer_number());
			Integer create_sn=uticketMapper.queryMaxCreateSn(ticket.getRule_id());
			Rule rule=ruleMapper.queryById(ticket.getRule_id());
			String ticket_sn=rule.getSn_header()+""+rule.getSn_start()+""+StringUtil.getNumber(create_sn)+""+rule.getSn_end();
			UserTicket uticket=new UserTicket(StringUtil.getUuid(), 0, 1, puser.getId(), ticket_sn, create_sn+1, po.getConn_id(), ticket.getRule_id(), null, null,null,null);
			uticket.setUpgrade(0);
			uticket.setUsed_date(null);
			uticketMapper.add(uticket);//卡券信息
			po.setTicket_sn(ticket_sn);
			po.setBuy_type(0);
            if(puser!=null&&(!StringUtil.isEmpty(puser.getMobile()))){//推荐人手机号
                try {
    				po.setMobile(SecurityUtil.decrypt(puser.getMobile()));//手机号解密
    			} catch (Exception e) {
    				e.printStackTrace();
    			}
            }
		}
		//添加订单，然后调用支付的方法
		Integer result=mapper.add(po);
		logMapper.add(new OrderLog(StringUtil.getUuid(),po.getId(),sysUser.getId(),null,"创建订单"));
		if(result>0){//订单添加成功，调用线下支付的方法
			result=topay(po.getId());
			if(result==0) result=1;
		}
        return result;
	}
	
	/**
	 * 后台添加未结业历史卡券订单(部分未结业)
	 */
	@Override
	public Integer addnofinishticket_part(Norder po) {
		if(po.getCreate_date()==null) po.setCreate_date(new Date());
		User user=userMapper.queryById(po.getUser_id());
		Subject subject = SecurityUtils.getSubject();
		User sysUser = (User) subject.getSession().getAttribute("user");
		po.setRefer_number(user.getRefer_number());
		po.setNumber(user.getNumber());
		po.setDepart_id(user.getDepart_id());
		po.setSn(StringUtil.buildOrderSn());//订单编号
		po.setId(StringUtil.getUuid());
		po.setKd_score(BigDecimal.ZERO);
		po.setKz_score(BigDecimal.ZERO);
		po.setAddtype(3);//未结业历史订单
		po.setPay_type(0);//线下支付
		po.setStatus(0);
		po.setUptype(0);
		Ticket ticket=ticketMapper.queryById(po.getConn_id());
		po.setPrice(ticket.getPrice());
		po.setName(ticket.getName());
		po.setPay_amount(0.0);
		Integer buy_type=po.getBuy_type();//0自售，1直售
		if(buy_type!=null&&buy_type==1){//购买系统的卡券(后边会调用支付方法，会产生卡券)
			try {
				po.setMobile(SecurityUtil.decrypt(user.getMobile()));//手机号解密
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else{//如果是自售后的话;那么这张卡是推荐人的只是被用户使用了userticket的user_id是puser
			User puser=userMapper.queryByNumber(user.getRefer_number());
			Integer create_sn=uticketMapper.queryMaxCreateSn(ticket.getRule_id());
			Rule rule=ruleMapper.queryById(ticket.getRule_id());
			String ticket_sn=rule.getSn_header()+""+rule.getSn_start()+""+StringUtil.getNumber(create_sn)+""+rule.getSn_end();
			UserTicket uticket=new UserTicket(StringUtil.getUuid(), 0, 1, puser.getId(), ticket_sn, create_sn+1, po.getConn_id(), ticket.getRule_id(), null, null,null,null);
			uticket.setUpgrade(0);
			uticket.setUsed_date(null);
			uticketMapper.add(uticket);//卡券信息
			po.setTicket_sn(ticket_sn);
			po.setBuy_type(0);
            if(puser!=null&&(!StringUtil.isEmpty(puser.getMobile()))){//推荐人手机号
                try {
    				po.setMobile(SecurityUtil.decrypt(puser.getMobile()));//手机号解密
    			} catch (Exception e) {
    				e.printStackTrace();
    			}
            }
		}
		//添加订单，然后调用支付的方法
		Integer result=mapper.add(po);
		logMapper.add(new OrderLog(StringUtil.getUuid(),po.getId(),sysUser.getId(),null,"创建订单"));
		if(result>0){//订单添加成功，调用线下支付的方法
			//调用去支付的方法;
		   result=payValid(po.getId());
		   if(result==0){//积分支付
			    result=1;
			    Double pay_amount=po.getPrice()*CommonUtil.ACHIEVEPERCENT;
			    Double reduce_total=0.0;
			    List<UserTicket> poutlist=po.getUtlist();
			    String lesson_ids="";
			    for(UserTicket t:poutlist){
			    	lesson_ids+=t.getConn_id()+",";
			    }
			    List<Lesson> list=lessonMapper.queryByIds(lesson_ids.split(","));
			    for(Lesson l:list){
			    	reduce_total+=l.getPrice();
			    }
			    if(pay_amount<=reduce_total) {
			    	reduce_total=0.0;
			    }else{
			    	reduce_total=pay_amount-reduce_total;
			    }
			    pay_success(po.getSn(),StringUtil.buildOrderSn(),0,reduce_total);//对于已结业的课程没有不可转换积分
				//支付成功后;对于已经结业的课程要结业处理
				UserTicket quticket=new UserTicket();
				quticket.setNorder_id(po.getId());
				for(UserTicket ut:poutlist){
					quticket.setConn_id(ut.getConn_id());
					UserTicket uputicket=uticketMapper.queryByNorderIdAndConnId(quticket);
					if(uputicket!=null){
						Appoint appoint=new Appoint(StringUtil.getUuid(), uputicket.getUser_id(), po.getStudent_id(), uputicket.getNorder_id(), "0", uputicket.getConn_id(), 4, "0",new Date(), 1, uputicket.getId());
                        appointMapper.add(appoint);//添加预约结束
						uputicket.setStatus(4);
						uticketMapper.del(uputicket.getId().split(","));//先删除，然后再增加，这样就不会有积分转化
						uticketMapper.add(uputicket);//课程结业
						if(uputicket.getLesson_type()==0){//集中+进阶(先把课程结业产生积分，然后再修改状态)
							Integer train=uputicket.getLeft_review();//该课程的进阶训练课时
							Integer left_review=ut.getLeft_review();
							if(train==0||ut.getLeft_review()==0){//该课程的复训也结束了
								uputicket.setStatus(6);//课程已完成
							}else{
								uputicket.setStatus(5);//在复训中
								 if(left_review>train) left_review=train;//进阶的复训数量大于输入的复训数
								   appoint.setCourse_type(0);
								   //添加复训数量
								   Integer finish_review=train-left_review;
								   for(int i=0;i<finish_review;i++){
									   appoint.setId(StringUtil.getUuid());
									   appoint.setCourse_id("0"+i);
									   appointMapper.add(appoint);
								   }
							}
							uticketMapper.update(uputicket);
						}
					}
				}
			}
		}
        return result;
	}
	
	/*后台订单详情结业课程*/
	@Override
	public AjaxResult finishUserTicket(String ut_id,String student_id,Integer left_review) {
		UserTicket ut=uticketMapper.queryById(ut_id);
		if(ut==null||ut.getStatus()!=0){
			return new AjaxResult(CommonUtil.ERROR_CODE, "该状态不能结业");
		}
		Lesson lesson=lessonMapper.queryById(ut.getConn_id());
		if(lesson==null){
			return new AjaxResult(CommonUtil.ERROR_CODE, "该课程不存在");
		}
		//类型：0:集中训练+进阶训练；1集中训练
		//要产生可结业积分的
		//如果是课程这一步可省略
		Appoint appoint=new Appoint(StringUtil.getUuid(), ut.getUser_id(), student_id, ut.getNorder_id(), "0", lesson.getId(), 4, "0",new Date(), 1, ut.getId());
		appointMapper.add(appoint);
		//先修改userticket的状态
		ut.setStatus(4);//这样修改的话就会产生结业积分
		Integer result=uticketMapper.update(ut);
		if(result>0&&lesson.getType()==0){//集中+进阶(先把课程结业产生积分，然后再修改状态)
			Integer train=lesson.getTrain();//该课程的进阶训练课时
			if(train==0||left_review==0){//该课程的复训也结束了
				ut.setStatus(6);//课程已完成
			}else{
				ut.setStatus(5);//在复训中
				 if(left_review>train) left_review=train;//进阶的复训数量大于输入的复训数
				   appoint.setCourse_type(0);
				   //添加复训数量
				   Integer finish_review=train-left_review;
				   for(int i=0;i<finish_review;i++){
					   appoint.setId(StringUtil.getUuid());
					   appoint.setCourse_id("0"+i);
					   appointMapper.add(appoint);
				   }
			}
			result=uticketMapper.update(ut);
		}
		if(result>0)  return new AjaxResult(CommonUtil.SUCCESS_CODE, "结业成功");
		return new AjaxResult(CommonUtil.ERROR_CODE, "结业失败");
	}
	
	/*注意，此方法和上边的方法一样，只有一句话不同*/
	@Override
	public boolean pay_success(String sn,String trade_no,Integer pay_type,Double pay_amount) {
		if (paymentLogMapper.queryPayValid(trade_no)==0) {
	        Norder norder=mapper.queryBySn(sn);
	        if(norder.getType()==0){//课程订单
	        	Lesson lesson=lessonMapper.queryById(norder.getConn_id());//课程
				Integer create_sn=uticketMapper.queryMaxCreateSn(lesson.getRule_id());//查询已存在的最大编号
				Rule rule=ruleMapper.queryById(lesson.getRule_id());
				String ticket_sn=rule.getSn_header()+""+rule.getSn_start()+""+StringUtil.getNumber(create_sn)+""+rule.getSn_end();
				UserTicket uticket=new UserTicket(StringUtil.getUuid(), 1, 0, norder.getUser_id(), ticket_sn, create_sn+1, norder.getConn_id(), lesson.getRule_id(), norder.getId(), norder.getUser_id(),null,0);
				uticketMapper.add(uticket);//课程信息
				norder.setTicket_sn(ticket_sn);//课程编号
	        }else{//卡券订单
	        	//如果是从推荐人购买的卡券
	        	if(!StringUtil.isEmpty(norder.getTicket_sn())){
	        		String ticket_sn=norder.getTicket_sn();
	        		UserTicket uticket=uticketMapper.queryBySn(ticket_sn);
	    			if(uticket!=null){//卡券变成已使用 ,
	    				uticket.setUsed(1);
	    				uticket.setNorder_id(norder.getId());
	    				uticket.setUsed_id(norder.getUser_id());
	    				uticket.setUsed_date(new Date());
	    				uticketMapper.update(uticket);
	    			}
	    			norder.setBuy_type(0);
	        	}else{//如果是从平台购买的卡券，
	        		Ticket ticket=ticketMapper.queryById(norder.getConn_id());
	        		Integer create_sn=uticketMapper.queryMaxCreateSn(ticket.getRule_id());
					Rule rule=ruleMapper.queryById(ticket.getRule_id());
					String ticket_sn=rule.getSn_header()+""+rule.getSn_start()+""+StringUtil.getNumber(create_sn)+""+rule.getSn_end();
					UserTicket uticket=new UserTicket(StringUtil.getUuid(), 1, 1, norder.getUser_id(), ticket_sn, create_sn+1, norder.getConn_id(), ticket.getRule_id(), norder.getId(), norder.getUser_id(),null,null);
					uticket.setUpgrade(0);//未升级的卡券
					uticketMapper.add(uticket);//卡券信息
					norder.setTicket_sn(ticket_sn);
					norder.setBuy_type(1);
	        	}
	        	Ticket ticket=ticketMapper.queryById(norder.getConn_id());
	        	if(ticket!=null){//userticket里添加课程信息
	        		if(!StringUtil.isEmpty(ticket.getLesson_ids())){
			        	String[] lesson_ids=ticket.getLesson_ids().split(",");
						for(String lesson_id:lesson_ids){//购买卡券订单包含的课程，
							if(!StringUtil.isEmpty(lesson_id)){
								Lesson lesson=lessonMapper.queryById(lesson_id);
								Integer create_sn=uticketMapper.queryMaxCreateSn(lesson.getRule_id());//查询已存在的最大编号
								Rule rule=ruleMapper.queryById(lesson.getRule_id());
								String ticket_sn=rule.getSn_header()+""+rule.getSn_start()+""+StringUtil.getNumber(create_sn)+""+rule.getSn_end();
								UserTicket uticket=new UserTicket(StringUtil.getUuid(), 1, 0, norder.getUser_id(), ticket_sn, create_sn+1, lesson_id, lesson.getRule_id(), norder.getId(), norder.getUser_id(),null,0);
								uticketMapper.add(uticket);//课程信息
							}
						}
		        	}
	        	}
	        }
	        //产生积分的金额(只有这一处不同下边一行)
	        norder.setPay_amount(pay_amount);
			norder.setStatus(1);//已支付
			norder.setPay_type(pay_type);
			
			//mapper.update(norder);
			
			// add by wjx 20170311
			// 更新用户积分
			if(mapper.update(norder)>0 
					&& ((norder.getKz_score() !=null&&norder.getKz_score().compareTo(BigDecimal.ZERO)==1)
							||(norder.getKd_score() !=null&&norder.getKd_score().compareTo(BigDecimal.ZERO)==1))){//支付完成
				Map param = new HashMap();
				param.put("user_id", norder.getUser_id());
				param.put("order_no", sn);
				param.put("kz_score", norder.getKz_score());
				param.put("kd_score", norder.getKd_score());
				
				userScoreInfoMapper.orderPayByscore(param);
			}
			
			
			
			String user_id=norder.getUser_id();
			Subject subject = SecurityUtils.getSubject();
			User sysUser = (User) subject.getSession().getAttribute("user");
			if(sysUser!=null) user_id=sysUser.getId();
			logMapper.add(new OrderLog(StringUtil.getUuid(),norder.getId(),user_id,null,"订单支付成功"));
			//添加支付记录
			Double  payoff=norder.getPrice();
			if(pay_type==4) payoff=0.0;//积分订单 支付金额为0;
			paymentLogMapper.add(new PaymentLog(norder.getUser_id(), null,payoff,"订单支付",sn,trade_no, pay_type, 1, 0,null));
			//添加消息
			if(norder.getAddtype()==0||norder.getAddtype()==1){
				Message message=new Message(norder.getUser_id(), "订单支付","您的订单"+norder.getSn()+"已于"+StringUtil.getLocalData()+"完成支付" );
				messageMapper.add(message);
			}
		}
		return true;
	}
	
}
