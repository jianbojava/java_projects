package com.cocosh.nlf.service.impl;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
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
import com.cocosh.nlf.mapper.LessonMapper;
import com.cocosh.nlf.mapper.NorderMapper;
import com.cocosh.nlf.mapper.RuleMapper;
import com.cocosh.nlf.mapper.TicketMapper;
import com.cocosh.nlf.mapper.UpgradeMapper;
import com.cocosh.nlf.mapper.UpruleMapper;
import com.cocosh.nlf.mapper.UserScoreInfoMapper;
import com.cocosh.nlf.mapper.UserTicketMapper;
import com.cocosh.nlf.model.Lesson;
import com.cocosh.nlf.model.Norder;
import com.cocosh.nlf.model.Rule;
import com.cocosh.nlf.model.Ticket;
import com.cocosh.nlf.model.Upgrade;
import com.cocosh.nlf.model.Uprule;
import com.cocosh.nlf.model.UserScoreInfo;
import com.cocosh.nlf.model.UserTicket;
import com.cocosh.nlf.service.UpgradeService;
import com.cocosh.sys.mapper.UserMapper;
import com.cocosh.sys.model.User;

@Transactional
@Service
public class UpgradeServiceImpl implements UpgradeService {
	@Autowired
	private UpgradeMapper mapper;
	@Autowired
	private UserTicketMapper uticketMapper;
	@Autowired
	private TicketMapper ticketMapper;
	@Autowired
	private RuleMapper ruleMapper;
	@Autowired 
	private UpruleMapper upMapper;
	@Autowired
	private NorderMapper norderMapper;
	@Autowired
	private UserMapper userMapper;
	@Autowired
	private LessonMapper lessonMapper;
	@Autowired
	private OrderLogMapper logMapper;
	@Autowired
	private PaymentLogMapper paymentLogMapper;
	@Autowired
	private UserScoreInfoMapper scoreMapper;

	@LogClass(module = "卡券升级管理", method = "添加")
	@Override
	public boolean add(Upgrade po) {
		po.setId(StringUtil.getUuid());
		return mapper.add(po) > 0;
	}
	
    @LogClass(module = "卡券升级管理", method = "删除")
	@Override
	public boolean del(String ids) {
		return mapper.del(ids.split(",")) > 0;
	}

	@LogClass(module = "卡券升级管理", method = "修改")
	@Override
	public boolean update(Upgrade po) {
		return mapper.update(po) > 0;
	}

	@Override
	public Page<Upgrade> queryPage(BaseConditionVO vo) {
		PaginationInterceptor.startPage(vo.getPageNo(),vo.getPageSize());
		mapper.queryPage(vo);
		Page<Upgrade> page=PaginationInterceptor.endPage();
		return page;
	}

	@Override
	public Upgrade queryById(String id) {
		return mapper.queryById(id);
	}

	@Override
	public AjaxResult toUpgrade(Upgrade po) {
		Subject subject = SecurityUtils.getSubject();
		User user = (User) subject.getSession().getAttribute("wapuser");
		if(user==null) {//验证用户
			return new AjaxResult(CommonUtil.ERROR_CODE,"登陆超时",null);
		}
		UserTicket t1=uticketMapper.queryById(po.getUser_ticket_id1());//原始卡
		if(t1==null){//验证是否已使用
			return new AjaxResult(CommonUtil.ERROR_CODE,"原始卡不存在",null);
		}
		Uprule uprule=upMapper.queryone();
		if(uprule==null||(!uprule.getTicket_id1().equals(t1.getConn_id()))){
			return new AjaxResult(CommonUtil.ERROR_CODE,"原始卡不能升级",null);
		}
		UserTicket t2=uticketMapper.queryById(po.getUser_ticket_id2());//升级券
		if(t2==null||(!t2.getConn_id().equals(uprule.getTicket_id2()))){
			return new AjaxResult(CommonUtil.ERROR_CODE,"升级券不存在",null);
		}
		if(t1.getUpgrade()!=0||t2.getUpgrade()!=0){
			return new AjaxResult(CommonUtil.ERROR_CODE,"原始卡券不能多次升级",null);
		}
		Ticket ticket3=ticketMapper.queryById(uprule.getTicket_id3());//升级卡
        if(ticket3==null){
			return new AjaxResult(CommonUtil.ERROR_CODE,"升级卡不存在",null);
        }
		if(t1.getUsed()==0){//原始卡未使用
			if(t2.getUsed()!=0){//升级券必须未使用
				return new AjaxResult(CommonUtil.ERROR_CODE,"升级券已经被使用",null);
			}
			if(!t1.getUser_id().equals(user.getId())||(!t2.getUser_id().equals(user.getId()))){
				return new AjaxResult(CommonUtil.ERROR_CODE,"只能用自己的卡券升级",null);
			}
			//添加升级记录，修改原始卡券已使用，添加未使用新卡
			String user_ticket_id3=StringUtil.getUuid();
			po.setId(StringUtil.getUuid());
			po.setUser_ticket_id3(user_ticket_id3);
			Integer result=mapper.add(po);//添加升级记录
			if(result>0){
				t1.setUsed(1);
				t1.setUsed_date(new Date());
				t1.setUsed_id(user.getId());
				t1.setUpgrade(1);
				uticketMapper.update(t1);//修改原始卡
				t2.setUsed(1);
				t2.setUsed_date(new Date());
				t2.setUsed_id(user.getId());
				t2.setUpgrade(1);
				uticketMapper.update(t2);//修改升级券
				//给会员添加升级卡
				UserTicket t3=new UserTicket();
				Integer create_sn = uticketMapper.queryMaxCreateSn(ticket3.getRule_id());// 查询已存在的最大编号
				Rule rule = ruleMapper.queryById(ticket3.getRule_id());
				String ticket_sn = rule.getSn_header() + ""
						+ rule.getSn_start() + ""
						+ StringUtil.getNumber(create_sn) + ""
						+ rule.getSn_end();
				t3.setId(user_ticket_id3);
				t3.setUsed(0);
				t3.setCreate_date(new Date());
				t3.setCreate_sn(create_sn + 1);
				t3.setSn(ticket_sn);
				t3.setRule_id(ticket3.getRule_id());
				t3.setUser_id(user.getId());
				t3.setConn_id(ticket3.getId());
				t3.setType(1);
				t3.setUpgrade(2);//已升级的卡券
				uticketMapper.add(t3);
				return new AjaxResult(CommonUtil.SUCCESS_CODE,"升级成功",null);
			}
		}
		if(t1.getUsed()==1){//原始卡已使用
			if(!t1.getUsed_id().equals(user.getId())){
				return new AjaxResult(CommonUtil.ERROR_CODE,"只能用自己的卡券升级",null);  
			}
			if(t2.getUsed()==0){//升级券未使用
				if(StringUtil.isEmpty(t1.getNorder_id())){
				   	return new AjaxResult(CommonUtil.ERROR_CODE,"订单不存在",null);  
				}
				Norder norder1=norderMapper.queryById(t1.getNorder_id());
				if(norder1==null){
				   	return new AjaxResult(CommonUtil.ERROR_CODE,"订单不存在",null);  
				}
				//添加已支付的券订单，原订单升级，原卡券升级，无积分产生，产生新已经被使用的钻卡，
				user=userMapper.queryById(user.getId());
				Norder norder2=new Norder();
				norder2.setCreate_date(new Date());
				norder2.setConn_id(t2.getConn_id());
				norder2.setType(1);
				norder2.setBuy_type(1);
				norder2.setUser_id(user.getId());
				norder2.setId(StringUtil.getUuid());
				norder2.setRefer_number(user.getRefer_number());
				norder2.setNumber(user.getNumber());
				norder2.setDepart_id(user.getDepart_id());
				norder2.setSn(StringUtil.buildOrderSn());
				norder2.setKd_score(BigDecimal.ZERO);
				norder2.setKz_score(BigDecimal.ZERO);
				norder2.setAddtype(4);//券升级产生
				norder2.setPay_type(5);//支付方式，卡券
				norder2.setStatus(1);
				norder2.setUptype(0);
				Ticket  ticket2=ticketMapper.queryById(t2.getConn_id());
				norder2.setPrice(ticket2.getPrice());
				norder2.setName(ticket2.getName());
				norder2.setPay_amount(0.0);//券升级订单金额为0
				norder2.setTicket_sn(t2.getSn());
				try {
					norder2.setMobile(SecurityUtil.decrypt(user.getMobile()));//手机号解密
				} catch (Exception e) {
					e.printStackTrace();
				}
				//添加该订单课程的的userticket
				if(!StringUtil.isEmpty(ticket2.getLesson_ids())){
					String[] lesson_ids=ticket2.getLesson_ids().split(",");
					for(String lesson_id:lesson_ids){//购买卡券订单包含的课程，
						if(!StringUtil.isEmpty(lesson_id)){
							Lesson lesson=lessonMapper.queryById(lesson_id);
							Integer create_sn=uticketMapper.queryMaxCreateSn(lesson.getRule_id());//查询已存在的最大编号
							Rule rule=ruleMapper.queryById(lesson.getRule_id());
							String ticket_sn=rule.getSn_header()+""+rule.getSn_start()+""+StringUtil.getNumber(create_sn)+""+rule.getSn_end();
							UserTicket uticket=new UserTicket(StringUtil.getUuid(), 1, 0, user.getId(), ticket_sn, create_sn+1, lesson_id, lesson.getRule_id(), norder2.getId(), user.getId(),null,0);
							uticketMapper.add(uticket);//课程信息
						}
					}
				}
				logMapper.add(new OrderLog(StringUtil.getUuid(),norder2.getId(),user.getId(),null,"创建订单"));
				logMapper.add(new OrderLog(StringUtil.getUuid(),norder2.getId(),user.getId(),null,"订单支付成功"));
				//添加订单支付日志(支付金额为0)
				paymentLogMapper.add(new PaymentLog(user.getId(), null,0.0,"订单支付",norder2.getSn(),StringUtil.buildOrderSn(), 5, 1, 0,null));
				Integer result= norderMapper.add(norder2);
				if(result>0){//添加卡券订单成功
					//添加升级记录
					String user_ticket_id3=StringUtil.getUuid();//新的钻卡的id
					po.setId(StringUtil.getUuid());
					po.setUser_ticket_id3(user_ticket_id3);
					Integer upresult=mapper.add(po);
					if(upresult>0){
						t2.setUsed(1);
						t2.setUsed_date(new Date());
						t2.setUpgrade(1);
						t2.setUsed_id(user.getId());
						t2.setNorder_id(norder2.getId());
						uticketMapper.update(t2);//修改未使用的升级券变成已使用;
						t1.setUpgrade(1);
						uticketMapper.update(t1);//修改原始卡
						//给会员添加卡券
						UserTicket t3=new UserTicket();
						Integer create_sn = uticketMapper.queryMaxCreateSn(ticket3.getRule_id());// 查询已存在的最大编号
						Rule rule = ruleMapper.queryById(ticket3.getRule_id());
						String ticket_sn = rule.getSn_header() + ""
								+ rule.getSn_start() + ""
								+ StringUtil.getNumber(create_sn) + ""
								+ rule.getSn_end();
						t3.setId(user_ticket_id3);
						t3.setUsed(1);//该钻卡已经被使用了
						t3.setCreate_date(new Date());
						t3.setCreate_sn(create_sn + 1);
						t3.setSn(ticket_sn);
						t3.setRule_id(ticket3.getRule_id());
						t3.setUser_id(user.getId());
						t3.setConn_id(ticket3.getId());
						t3.setType(1);
						t3.setUpgrade(2);//已升级的卡券
						t3.setUsed_id(user.getId());//使用者
						t3.setUsed_date(new Date());//使用时间
						uticketMapper.add(t3);
						//修改两个订单的upnorder和uptype，uputicket
						norder1.setUpnorder(norder2.getId());
						norder1.setUputicket(t3.getSn());
						norderMapper.update(norder1);
						norder2.setUpnorder(norder1.getId());
						norder2.setUptype(1);
						norder2.setUputicket(t3.getSn());
						norderMapper.update(norder2);
						return new AjaxResult(CommonUtil.SUCCESS_CODE,"升级成功",null);
					}
				}
			}
			if(t2.getUsed()==1){//升级券已经被使用
				//订单已经存在，修改两个订单，添加升级记录，修改原始卡变成升级，添加已使用新钻卡
				if(StringUtil.isEmpty(t1.getNorder_id())||StringUtil.isEmpty(t2.getNorder_id())){
				   	return new AjaxResult(CommonUtil.ERROR_CODE,"订单不存在",null);  
				}
				Norder norder1=norderMapper.queryById(t1.getNorder_id());
				Norder norder2=norderMapper.queryById(t2.getNorder_id());
				if(norder1==null||norder2==null){
				   	return new AjaxResult(CommonUtil.ERROR_CODE,"订单不存在",null);  
				}
				//添加升级记录
				String user_ticket_id3=StringUtil.getUuid();
				po.setId(StringUtil.getUuid());
				po.setUser_ticket_id3(user_ticket_id3);
				Integer result=mapper.add(po);
				if(result>0){
					t1.setUpgrade(1);
					uticketMapper.update(t1);//修改原始卡
					t2.setUpgrade(1);
					uticketMapper.update(t2);//修改升级券
					//给会员添加卡券
					UserTicket t3=new UserTicket();
					Integer create_sn = uticketMapper.queryMaxCreateSn(ticket3.getRule_id());// 查询已存在的最大编号
					Rule rule = ruleMapper.queryById(ticket3.getRule_id());
					String ticket_sn = rule.getSn_header() + ""
							+ rule.getSn_start() + ""
							+ StringUtil.getNumber(create_sn) + ""
							+ rule.getSn_end();
					t3.setId(user_ticket_id3);
					t3.setUsed(1);//该钻卡已经被使用了
					t3.setCreate_date(new Date());
					t3.setCreate_sn(create_sn + 1);
					t3.setSn(ticket_sn);
					t3.setRule_id(ticket3.getRule_id());
					t3.setUser_id(user.getId());
					t3.setConn_id(ticket3.getId());
					t3.setType(1);
					t3.setUpgrade(2);//已升级的卡券
					t3.setUsed_id(user.getId());//使用者
					t3.setUsed_date(new Date());//使用时间
					uticketMapper.add(t3);
					//修改两个订单的upnorder和uptype，uputicket
					norder1.setUpnorder(norder2.getId());
					norder1.setUputicket(t3.getSn());
					norderMapper.update(norder1);
					norder2.setUpnorder(norder1.getId());
					norder2.setUptype(1);
					norder2.setUputicket(t3.getSn());
					norderMapper.update(norder2);
					return new AjaxResult(CommonUtil.SUCCESS_CODE,"升级成功",null);
				}
				
			}
		}
		return new AjaxResult(CommonUtil.ERROR_CODE,"升级失败",null);
	}

	@Override
	public Upgrade queryByutid123(String user_ticket_id3) {
		return mapper.queryByutid123(user_ticket_id3);
	}

	@Override
	public AjaxResult upgradebyjf(String  ut_id1) {
		Subject subject = SecurityUtils.getSubject();
		User user = (User) subject.getSession().getAttribute("wapuser");
		if(user==null) {//验证用户
			return new AjaxResult(CommonUtil.ERROR_CODE,"登陆超时",null);
		}
		//验证原始卡
		if(StringUtil.isEmpty(ut_id1)){
			return new AjaxResult(CommonUtil.ERROR_CODE,"原始卡不存在",null);
		}
		UserTicket t1=uticketMapper.queryById(ut_id1);
		if(t1==null){//验证是否已使用
			return new AjaxResult(CommonUtil.ERROR_CODE,"原始卡不存在",null);
		}
		if(t1.getUsed()!=1||t1.getUpgrade()!=0||(StringUtil.isEmpty(t1.getNorder_id()))||(!t1.getUsed_id().equals(user.getId()))){
			return new AjaxResult(CommonUtil.ERROR_CODE,"原始卡不能升级",null);
		}
		Uprule uprule=upMapper.queryone();
		if(uprule==null||(!uprule.getTicket_id1().equals(t1.getConn_id()))){
			return new AjaxResult(CommonUtil.ERROR_CODE,"原始卡不能升级",null);
		}
		//验证升级卡
		Ticket ticket3=ticketMapper.queryById(uprule.getTicket_id3());//升级卡
        if(ticket3==null){
			return new AjaxResult(CommonUtil.ERROR_CODE,"升级卡不存在",null);
        }
		//验证会员的积分是否足够
		Ticket ticket2=ticketMapper.queryById(uprule.getTicket_id2());
		if(ticket2!=null){
			//验证会员积分
			BigDecimal need_kd=new BigDecimal(ticket2.getPrice()*CommonUtil.SCOREPERCENT);
			UserScoreInfo uscore=scoreMapper.queryByUser(user.getId());
			if(uscore==null||(need_kd.compareTo(uscore.getKD_SCORE())>0)){
				return new AjaxResult(CommonUtil.ERROR_CODE,"会员积分不足",null);  
			}
			Norder norder1=norderMapper.queryById(t1.getNorder_id());
			if(norder1!=null){
				//添加新的升级券，已支付的券订单，并且支付扣除积分，原订单升级，产生新已经被使用的钻卡，
				//添加升级券
				String norder2_id=StringUtil.getUuid();
				Integer create_sn=uticketMapper.queryMaxCreateSn(ticket2.getRule_id());
				Rule rule=ruleMapper.queryById(ticket2.getRule_id());
				String ticket_sn=rule.getSn_header()+""+rule.getSn_start()+""+StringUtil.getNumber(create_sn)+""+rule.getSn_end();
				UserTicket t2=new UserTicket(StringUtil.getUuid(), 1, 1, user.getId(), ticket_sn, create_sn+1, ticket2.getId(), ticket2.getRule_id(), norder2_id, user.getId(), null, null);
				t2.setUpgrade(0);
				uticketMapper.add(t2);//卡券信息
				//添加升级券订单
				user=userMapper.queryById(user.getId());
				Norder norder2=new Norder();
				norder2.setCreate_date(new Date());
				norder2.setConn_id(ticket2.getId());
				norder2.setType(1);
				norder2.setBuy_type(1);
				norder2.setUser_id(user.getId());
				norder2.setId(norder2_id);
				norder2.setRefer_number(user.getRefer_number());
				norder2.setNumber(user.getNumber());
				norder2.setDepart_id(user.getDepart_id());
				norder2.setSn(StringUtil.buildOrderSn());
				norder2.setKd_score(need_kd);
				norder2.setKz_score(BigDecimal.ZERO);
				norder2.setAddtype(4);//券升级产生
				norder2.setPay_type(4);//支付方式，积分支付
				norder2.setStatus(0);
				norder2.setUptype(0);
				norder2.setPrice(ticket2.getPrice());
				norder2.setName(ticket2.getName());
				norder2.setPay_amount(ticket2.getPrice()*CommonUtil.ACHIEVEPERCENT);//券升级订单金额提成
				norder2.setTicket_sn(t2.getSn());
				try {
					norder2.setMobile(SecurityUtil.decrypt(user.getMobile()));//手机号解密
				} catch (Exception e) {
					e.printStackTrace();
				}
				//添加该订单课程的的userticket
				if(!StringUtil.isEmpty(ticket2.getLesson_ids())){
					String[] lesson_ids=ticket2.getLesson_ids().split(",");
					for(String lesson_id:lesson_ids){//购买卡券订单包含的课程，
						if(!StringUtil.isEmpty(lesson_id)){
							Lesson lesson=lessonMapper.queryById(lesson_id);
							Integer create_sn2=uticketMapper.queryMaxCreateSn(lesson.getRule_id());//查询已存在的最大编号
							Rule rule2=ruleMapper.queryById(lesson.getRule_id());
							String ticket_sn2=rule2.getSn_header()+""+rule2.getSn_start()+""+StringUtil.getNumber(create_sn2)+""+rule2.getSn_end();
							UserTicket uticket=new UserTicket(StringUtil.getUuid(), 1, 0, user.getId(), ticket_sn2, create_sn2+1, lesson_id, lesson.getRule_id(), norder2.getId(), user.getId(),null,0);
							uticketMapper.add(uticket);//课程信息
						}
					}
				}
				if(norderMapper.add(norder2)>0){
					logMapper.add(new OrderLog(StringUtil.getUuid(),norder2.getId(),user.getId(),null,"创建订单"));
					norder2.setStatus(1);//订单支付成功(触发器触发)
					if(norderMapper.update(norder2)>0){
						//扣除会员积分
						Map<String,Object> param = new HashMap<String,Object>();
						param.put("user_id", user.getId());
						param.put("order_no", norder2.getSn());
						param.put("kz_score", norder2.getKz_score());
						param.put("kd_score", norder2.getKd_score());
						scoreMapper.orderPayByscore(param);
						//订单支付
						logMapper.add(new OrderLog(StringUtil.getUuid(),norder2.getId(),user.getId(),null,"订单支付成功"));
						//添加订单支付日志(支付金额为0)
						paymentLogMapper.add(new PaymentLog(user.getId(), null,0.0,"订单支付",norder2.getSn(),StringUtil.buildOrderSn(), 4, 1, 0,null));
						//添加升级记录
						Upgrade po=new Upgrade();
						String user_ticket_id3=StringUtil.getUuid();//新的钻卡的id
						po.setId(StringUtil.getUuid());
						po.setUser_ticket_id1(ut_id1);
						po.setUser_ticket_id2(t2.getId());
						po.setUser_ticket_id3(user_ticket_id3);
						Integer upresult=mapper.add(po);
						if(upresult>0){
							t2.setUsed(1);
							t2.setUsed_date(new Date());
							t2.setUpgrade(1);
							t2.setUsed_id(user.getId());
							t2.setNorder_id(norder2.getId());
							uticketMapper.update(t2);//修改未使用的升级券变成已使用;
							t1.setUpgrade(1);
							uticketMapper.update(t1);//修改原始卡
							//给会员添加卡券
							UserTicket t3=new UserTicket();
							Integer create_sn3 = uticketMapper.queryMaxCreateSn(ticket3.getRule_id());// 查询已存在的最大编号
							Rule rule3 = ruleMapper.queryById(ticket3.getRule_id());
							String ticket_sn3 = rule3.getSn_header() + ""
									+ rule3.getSn_start() + ""
									+ StringUtil.getNumber(create_sn3) + ""
									+ rule3.getSn_end();
							t3.setId(user_ticket_id3);
							t3.setUsed(1);//该钻卡已经被使用了
							t3.setCreate_date(new Date());
							t3.setCreate_sn(create_sn3 + 1);
							t3.setSn(ticket_sn3);
							t3.setRule_id(ticket3.getRule_id());
							t3.setUser_id(user.getId());
							t3.setConn_id(ticket3.getId());
							t3.setType(1);
							t3.setUpgrade(2);//已升级的卡券
							t3.setUsed_id(user.getId());//使用者
							t3.setUsed_date(new Date());//使用时间
							uticketMapper.add(t3);
							//修改两个订单的upnorder和uptype，uputicket
							norder1.setUpnorder(norder2.getId());
							norder1.setUputicket(t3.getSn());
							norderMapper.update(norder1);
							norder2.setUpnorder(norder1.getId());
							norder2.setUptype(1);
							norder2.setUputicket(t3.getSn());
							norderMapper.update(norder2);
							return new AjaxResult(CommonUtil.SUCCESS_CODE,"升级成功",null);
					      }
					}
				}
			}
		}
		return new AjaxResult(CommonUtil.ERROR_CODE,"升级失败",null);
	}

	@Override
	public AjaxResult upgradeValid(String ut_id1) {
		Subject subject = SecurityUtils.getSubject();
		User user = (User) subject.getSession().getAttribute("wapuser");
		if(user==null) {//验证用户
			return new AjaxResult(CommonUtil.ERROR_CODE,"登陆超时",null);
		}
		//验证原始卡
		if(StringUtil.isEmpty(ut_id1)){
			return new AjaxResult(CommonUtil.ERROR_CODE,"原始卡不存在",null);
		}
		UserTicket t1=uticketMapper.queryById(ut_id1);
		if(t1==null){//验证是否已使用
			return new AjaxResult(CommonUtil.ERROR_CODE,"原始卡不存在",null);
		}
		if(t1.getUsed()!=1||t1.getUpgrade()!=0||(StringUtil.isEmpty(t1.getNorder_id()))||(!t1.getUsed_id().equals(user.getId()))){
			return new AjaxResult(CommonUtil.ERROR_CODE,"原始卡不能升级",null);
		}
		Uprule uprule=upMapper.queryone();
		if(uprule==null||(!uprule.getTicket_id1().equals(t1.getConn_id()))){
			return new AjaxResult(CommonUtil.ERROR_CODE,"原始卡不能升级",null);
		}
		//验证升级卡
		Ticket ticket3=ticketMapper.queryById(uprule.getTicket_id3());//升级卡
        if(ticket3==null){
			return new AjaxResult(CommonUtil.ERROR_CODE,"升级卡不存在",null);
        }
        //升级券
		Ticket ticket2=ticketMapper.queryById(uprule.getTicket_id2());
        if(ticket2==null){
			return new AjaxResult(CommonUtil.ERROR_CODE,"升级券不存在",null);
        }
        //升级卡的订单
		Norder norder1=norderMapper.queryById(t1.getNorder_id());
        if(norder1==null){
			return new AjaxResult(CommonUtil.ERROR_CODE,"原始卡订单不存在",null);
        }
        return new AjaxResult(CommonUtil.SUCCESS_CODE,t1.getSn(),null);  
	}

	@Override
	public boolean upgrade_success(String ut_sn,String sn, String trade_no, Integer pay_type) {
		if (paymentLogMapper.queryPayValid(trade_no)==0) {//是否已经支付，防止重复调用
			Uprule uprule=upMapper.queryone();
			UserTicket t1=uticketMapper.queryBySn(ut_sn);
			User user=userMapper.queryById(t1.getUsed_id());
			Ticket ticket2=ticketMapper.queryById(uprule.getTicket_id2());
			Ticket ticket3=ticketMapper.queryById(uprule.getTicket_id3());//升级卡
			Norder norder1=norderMapper.queryById(t1.getNorder_id());
			String norder2_id=StringUtil.getUuid();
			Integer create_sn=uticketMapper.queryMaxCreateSn(ticket2.getRule_id());
			Rule rule=ruleMapper.queryById(ticket2.getRule_id());
			String ticket_sn=rule.getSn_header()+""+rule.getSn_start()+""+StringUtil.getNumber(create_sn)+""+rule.getSn_end();
			UserTicket t2=new UserTicket(StringUtil.getUuid(), 1, 1, user.getId(), ticket_sn, create_sn+1, ticket2.getId(), ticket2.getRule_id(), norder2_id, user.getId(), null, null);
			t2.setUpgrade(0);
			uticketMapper.add(t2);//卡券信息
			//添加升级券订单
			user=userMapper.queryById(user.getId());
			Norder norder2=new Norder();
			norder2.setCreate_date(new Date());
			norder2.setConn_id(ticket2.getId());
			norder2.setType(1);
			norder2.setBuy_type(1);
			norder2.setUser_id(user.getId());
			norder2.setId(norder2_id);
			norder2.setRefer_number(user.getRefer_number());
			norder2.setNumber(user.getNumber());
			norder2.setDepart_id(user.getDepart_id());
			norder2.setSn(sn);
			norder2.setKd_score(BigDecimal.ZERO);
			norder2.setKz_score(BigDecimal.ZERO);
			norder2.setAddtype(4);//券升级产生
			norder2.setPay_type(pay_type);//支付方式
			norder2.setStatus(0);
			norder2.setUptype(0);
			norder2.setPrice(ticket2.getPrice());
			norder2.setName(ticket2.getName());
			norder2.setPay_amount(ticket2.getPrice()*CommonUtil.ACHIEVEPERCENT);//券升级订单金额提成
			norder2.setTicket_sn(t2.getSn());
			try {
				norder2.setMobile(SecurityUtil.decrypt(user.getMobile()));//手机号解密
			} catch (Exception e) {
				e.printStackTrace();
			}
			//添加该订单课程的的userticket
			if(!StringUtil.isEmpty(ticket2.getLesson_ids())){
				String[] lesson_ids=ticket2.getLesson_ids().split(",");
				for(String lesson_id:lesson_ids){//购买卡券订单包含的课程，
					if(!StringUtil.isEmpty(lesson_id)){
						Lesson lesson=lessonMapper.queryById(lesson_id);
						Integer create_sn2=uticketMapper.queryMaxCreateSn(lesson.getRule_id());//查询已存在的最大编号
						Rule rule2=ruleMapper.queryById(lesson.getRule_id());
						String ticket_sn2=rule2.getSn_header()+""+rule2.getSn_start()+""+StringUtil.getNumber(create_sn2)+""+rule2.getSn_end();
						UserTicket uticket=new UserTicket(StringUtil.getUuid(), 1, 0, user.getId(), ticket_sn2, create_sn2+1, lesson_id, lesson.getRule_id(), norder2.getId(), user.getId(),null,0);
						uticketMapper.add(uticket);//课程信息
					}
				}
			}
			if(norderMapper.add(norder2)>0){
				logMapper.add(new OrderLog(StringUtil.getUuid(),norder2.getId(),user.getId(),null,"创建订单"));
				norder2.setStatus(1);//订单支付成功(触发器触发)
				if(norderMapper.update(norder2)>0){
					//订单支付
					logMapper.add(new OrderLog(StringUtil.getUuid(),norder2.getId(),user.getId(),null,"订单支付成功"));
					//添加订单支付日志(支付金额为升级券的金额)
					paymentLogMapper.add(new PaymentLog(user.getId(), null,norder2.getPrice(),"订单支付",norder2.getSn(),trade_no, pay_type, 1, 0,null));
					//添加升级记录
					Upgrade po=new Upgrade();
					String user_ticket_id3=StringUtil.getUuid();//新的钻卡的id
					po.setId(StringUtil.getUuid());
					po.setUser_ticket_id1(t1.getId());
					po.setUser_ticket_id2(t2.getId());
					po.setUser_ticket_id3(user_ticket_id3);
					Integer upresult=mapper.add(po);
					if(upresult>0){
						t2.setUsed(1);
						t2.setUsed_date(new Date());
						t2.setUpgrade(1);
						t2.setUsed_id(user.getId());
						t2.setNorder_id(norder2.getId());
						uticketMapper.update(t2);//修改未使用的升级券变成已使用;
						t1.setUpgrade(1);
						uticketMapper.update(t1);//修改原始卡
						//给会员添加卡券
						UserTicket t3=new UserTicket();
						Integer create_sn3 = uticketMapper.queryMaxCreateSn(ticket3.getRule_id());// 查询已存在的最大编号
						Rule rule3 = ruleMapper.queryById(ticket3.getRule_id());
						String ticket_sn3 = rule3.getSn_header() + ""
								+ rule3.getSn_start() + ""
								+ StringUtil.getNumber(create_sn3) + ""
								+ rule3.getSn_end();
						t3.setId(user_ticket_id3);
						t3.setUsed(1);//该钻卡已经被使用了
						t3.setCreate_date(new Date());
						t3.setCreate_sn(create_sn3 + 1);
						t3.setSn(ticket_sn3);
						t3.setRule_id(ticket3.getRule_id());
						t3.setUser_id(user.getId());
						t3.setConn_id(ticket3.getId());
						t3.setType(1);
						t3.setUpgrade(2);//已升级的卡券
						t3.setUsed_id(user.getId());//使用者
						t3.setUsed_date(new Date());//使用时间
						uticketMapper.add(t3);
						//修改两个订单的upnorder和uptype，uputicket
						norder1.setUpnorder(norder2.getId());
						norder1.setUputicket(t3.getSn());
						norderMapper.update(norder1);
						norder2.setUpnorder(norder1.getId());
						norder2.setUptype(1);
						norder2.setUputicket(t3.getSn());
						norderMapper.update(norder2);
				      }
				}
		}
		}
		return true;
	}
		
	
}
