package com.cocosh.car.service.impl;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.cocosh.car.mapper.CarMapper;
import com.cocosh.car.mapper.OrderLogMapper;
import com.cocosh.car.mapper.OrderMapper;
import com.cocosh.car.mapper.PaymentLogMapper;
import com.cocosh.car.model.Car;
import com.cocosh.car.model.Order;
import com.cocosh.car.model.OrderLog;
import com.cocosh.car.model.PaymentLog;
import com.cocosh.car.service.OrderService;
import com.cocosh.framework.annotation.LogClass;
import com.cocosh.framework.base.AjaxResult;
import com.cocosh.framework.base.BaseConditionVO;
import com.cocosh.framework.mybatis.Page;
import com.cocosh.framework.mybatis.PaginationInterceptor;
import com.cocosh.framework.util.CommonUtil;
import com.cocosh.framework.util.DateUtil;
import com.cocosh.framework.util.HttpSender;
import com.cocosh.framework.util.StringUtil;
import com.cocosh.member.mapper.FirmMapper;
import com.cocosh.member.mapper.MemberMapper;
import com.cocosh.member.model.Firm;
import com.cocosh.member.model.Member;
import com.cocosh.member.service.MemberService;
import com.cocosh.sys.mapper.DictMapper;
import com.cocosh.sys.model.User;

@Transactional
@Service
public class OrderServiceImpl implements OrderService {
	@Autowired
	private OrderMapper mapper;
	@Autowired
	private MemberMapper memberMapper;
	@Autowired
	private CarMapper carMapper;
	@Autowired
	private FirmMapper firmMapper;
	@Autowired
	private OrderLogMapper logMapper;
	@Autowired
	private DictMapper dictMapper;
	@Autowired
	private MemberService memberService;
	@Autowired
	private PaymentLogMapper paymentLogMapper;

	/**
	 * code 0：成功   1：失败   -1：签名失效 2：保证金 
	 */
	@Override
	public AjaxResult add(Order po,BaseConditionVO vo) {
		Map<String, Object> dataMap=new HashMap<String, Object>();
		Member logM=vo!=null?memberMapper.queryByCredential(vo.getCredential()):memberMapper.queryById(po.getMem_id());
		Firm firm=null;
		double dist_amount=0.00;//配送费用
		if (logM!=null) {
			//验证用户是否有效
			if(logM.getEnabled()!=0){
				return new AjaxResult(CommonUtil.ERROR_CODE,"提交失败：用户不合法",dataMap);
			}
			//验证用户是否审核
			if (logM.getReview()!=1) {
				return new AjaxResult(CommonUtil.ERROR_CODE,"提交失败：用户资料未通过审核",dataMap);
			}
			//验证订单金额
			Car car=carMapper.queryById(po.getCar_id());//车辆信息
			if (po.getGet_type()==1||po.getReturn_type()==1) {
				dist_amount=Double.parseDouble(dictMapper.queryByCode("DISTRIBUTION_AMOUNT"));
			}
			double p1=car.getPrice()*DateUtil.getIntervalDays(po.getRent_star_time(), po.getRent_end_time())*100;
			double p2=(po.getGet_type()==1?dist_amount:0.00)*100;
			double p3=(po.getReturn_type()==1?dist_amount:0.00)*100;
			double p4=Double.parseDouble(dictMapper.queryByCode("INSURANCE_AMOUNT"))*po.getIns_num()*100;
			double p5=(po.getSale_amount()==null?0.00:po.getSale_amount())*100;
			double valiAmount=(p1+p2+p3+p4-p5)/100;
			if (logM.getType()==0) {//普通会员
				po.setPay_type(1);//预充值
				if (logM.getDeposit()<car.getDeposit()) {
					dataMap.put("deposit",logM.getDeposit());
					dataMap.put("carDeposit",car.getDeposit());
					dataMap.put("leftDeposit",car.getDeposit()-logM.getDeposit());
					if (vo==null) {
						return new AjaxResult(CommonUtil.ERROR_CODE,"提交失败：保证金不低于"+car.getDeposit()+"元",dataMap);
					}else{
						return new AjaxResult("2","提交失败：保证金不低于"+car.getDeposit()+"元",dataMap);
					}
				}
				if (vo==null) {
					if (logM.getAccount()<valiAmount) {
						return new AjaxResult(CommonUtil.ERROR_CODE,"提交失败：账户余额不足",dataMap);
					}
				}
			}else{//企业个人会员
				firm=firmMapper.queryById(logM.getFirm_id());
				po.setPay_type(firm.getPay_type());
				//验证企业合同时间
				if (firm.getEnabled()!=0) {
					return new AjaxResult(CommonUtil.ERROR_CODE,"提交失败：企业不合法",dataMap);
				}
				if (!(firm.getSign_date().before(new Date())&&firm.getEnd_date().after(new Date()))) {
					return new AjaxResult(CommonUtil.ERROR_CODE,"提交失败：企业协议已过期",dataMap);
				}
				if (car.getDeposit()>(firm.getDeposit()-mapper.queryTotalDeposit(logM.getFirm_id()))) {
					return new AjaxResult(CommonUtil.ERROR_CODE,"提交失败：企业保证金不足",dataMap);
				}
				if (firm.getLeft_amount()<valiAmount) {
					return new AjaxResult(CommonUtil.ERROR_CODE,firm.getPay_type()==0?"提交失败：企业信用额度不足":"提交失败：企业账户余额不足",dataMap);
				}
			}
			//验证用车时间和取车时间，不能为周六、周日(app提交)
			if (vo!=null) {
				if (DateUtil.getWeek(po.getRent_star_time())==0||DateUtil.getWeek(po.getRent_star_time())==6) {
					return new AjaxResult(CommonUtil.ERROR_CODE,"提交失败：取车时间不能为周末", dataMap);
				}
				if (DateUtil.getWeek(po.getRent_end_time())==0||DateUtil.getWeek(po.getRent_end_time())==6) {
					return new AjaxResult(CommonUtil.ERROR_CODE,"提交失败：还车时间不能为周末", dataMap);
				}
			}
			//验证订单金额
			if (valiAmount!=po.getAmount()) {
				return new AjaxResult(CommonUtil.ERROR_CODE,"提交失败：订单金额有误",dataMap);
			}
			//验证车辆是否下线、审核,协议时间等
			if (car.getReview()!=1) {
				return new AjaxResult(CommonUtil.ERROR_CODE,"提交失败：车辆未通过审核",dataMap);
			}
			if (car.getEnabled()!=0) {
				return new AjaxResult(CommonUtil.ERROR_CODE,"提交失败：车辆"+(car.getEnabled()==1?"已下线":"正在维修"),dataMap);
			}
			if (!(car.getSign_date().before(new Date())&&car.getEnd_date().after(new Date()))) {
				return new AjaxResult(CommonUtil.ERROR_CODE,"提交失败：车辆已过期",dataMap);
			}
			//验证订单时间
			Order hisO=mapper.queryValidDate(po);
			if (hisO!=null) {
				return new AjaxResult(CommonUtil.ERROR_CODE,"提交失败：时间段已被预约",dataMap);
			}
			//验证用户是否存在该时间段内订单
			po.setMem_id(logM.getId());
			Order hisM=mapper.queryValidDateM(po);
			if (hisM!=null) {
				return new AjaxResult(CommonUtil.ERROR_CODE,"提交失败：您有一个待完成订单需要处理",dataMap);
			}			
			po.setFirm_id(logM.getFirm_id());
			po.setUnit_price(car.getPrice());
			po.setCar_price(car.getPrice()*(DateUtil.getIntervalDays(po.getRent_star_time(), po.getRent_end_time())));
			//生成订单ID，订单SN,订单类型,添加订单
			po.setId(StringUtil.getUuid());
			po.setSn(StringUtil.buildOrderSn());
			po.setStatus(0);//下单状态
			if (vo==null) {
				po.setPay_amount(po.getAmount());
				po.setPay_status(1);//用户已支付
			}else {
				if (logM.getType()==0) {
					po.setPay_status(0);//普通用户未支付
				}else {
					po.setPay_amount(po.getAmount());
					po.setPay_status(1);//企业用户已支付
				}
			}
			po.setType(0);//日租订单
			mapper.add(po);
			//普通用户扣除账户余额
			if (logM.getType()==0) {
				if (vo==null) {
					logM.setAccount(logM.getAccount()-valiAmount);
					memberMapper.updateAccount(logM);
					//添加流水
					paymentLogMapper.add(new PaymentLog(logM.getId(), logM.getFirm_id(),valiAmount,"订单支付",po.getSn()+"【日租】",null, 0, 1, 0,null));
				}
			}else {
				firm.setCredit_amount(firm.getCredit_amount()+valiAmount);
				firm.setUsed_amount(firm.getUsed_amount()+valiAmount);
				firm.setLeft_amount(firm.getLeft_amount()-valiAmount);
				firmMapper.updateAccount(firm);
				//添加流水
				paymentLogMapper.add(new PaymentLog(logM.getId(), logM.getFirm_id(),valiAmount,"订单支付",po.getSn()+"【日租】",null, 0, 1, 0,null));
			}
			//添加订单日志
			addOrderLog(po,logM,2);
			//发送短信
			if (po.getPay_status()==1) {
				Car sc=mapper.queryDispByCid(po.getCar_id());
				HttpSender.send(logM.getMobile(),"您已预订"+sc.getDot_name()+" "+sc.getPark_name()+"上的 "+sc.getName()+","+sc.getLicense()+".提车时间："+DateUtil.formatDate(po.getRent_star_time(),"yyyy-MM-dd HH:mm")+","+(po.getGet_type()==0?"自取":("送车地点： "+po.getGet_address()))+"，请您携带有效证件和智行家会员卡按约定的时间前往。 客服电话 400-0918-332.调度联系电话"+sc.getDispatch_mobile()+"。祝您用车愉快！");
			}
			//返回结果
			dataMap.put("id",po.getId());
			return new AjaxResult(CommonUtil.SUCCESS_CODE,CommonUtil.SUCCESS_MSG,dataMap);
		}
		return new AjaxResult(CommonUtil.ERROR_CREDENTIAL,CommonUtil.CREDENTIAL_INVALID,dataMap);
	}
	
	@LogClass(module = "订单管理", method = "修改")
	@Override
	public AjaxResult update(Order po) {
		Order o=mapper.queryAmount(po.getId());
		//修改订单状态
		if (po.getStatus()!=null) {
			if (po.getStatus()==-1||o.getStatus()==po.getStatus()-1||(o.getType()==0&&o.getStatus()==4&&o.getStatus()==po.getStatus()-2)||(o.getType()==1&&o.getStatus()==po.getStatus()+2)||(o.getType()==1&&o.getStatus()==po.getStatus()-2)) {//状态不能向下更新，但是分时订单 对账可向下调整。
				if (o.getType()==0&&po.getStatus()==3) {//日租订单，已还车 更新完成时间
					po.setComplete_date(new Date());
				}
				mapper.update(po);
				//取消订单返回金额
				if (po.getStatus()==-1&&o.getPay_status()==1) {
					double penalty_amount=0.00;//违约金
					if (o.getRent_star_time().before(new Date())) {
						penalty_amount=o.getUnit_price();
					}
					//企业
					if (o.getFirm_id()!=null) {
						Firm firm=firmMapper.queryById(o.getFirm_id());
						firm.setCredit_amount(firm.getCredit_amount()-o.getAmount()+penalty_amount);
						firm.setUsed_amount(firm.getUsed_amount()-o.getAmount()+penalty_amount);
						firm.setLeft_amount(firm.getLeft_amount()+o.getAmount()-penalty_amount);
						firmMapper.updateAccount(firm);
						//添加取消订单流水
						paymentLogMapper.add(new PaymentLog(o.getMem_id(), o.getFirm_id(), o.getAmount()-penalty_amount,"订单退款",o.getSn()+"【日租】",null, 0, 1, 3,penalty_amount==0.00?null:"取消订单时间超过取车时间扣除一天违约金"+penalty_amount+"元"));
					}else {
						//个人
						Member m=memberMapper.queryByIdRefund(o.getMem_id());
						m.setAccount(m.getAccount()+o.getAmount()-penalty_amount);
						memberMapper.updateAccount(m);
						//添加取消订单流水
						paymentLogMapper.add(new PaymentLog(o.getMem_id(), o.getFirm_id(), o.getAmount()-penalty_amount,"订单退款",o.getSn()+"【日租】",null, 0, 1, 3,penalty_amount==0.00?null:"取消订单时间超过取车时间扣除一天违约金"+penalty_amount+"元"));
					}
				}
				//添加订单日志
				addOrderLog(po,null,o.getType());
				//返回结果
				return new AjaxResult("0","");
			}else{
				return new AjaxResult("1","当前状态为【"+o.getOrder_status()+"】,无法调整至【"+po.getOrder_status()+"】");
			}
		}else if (po.getReady_date()!=null||po.getDelivery_date()!=null||po.getRent_star_ttime()!=null||po.getRent_end_ttime()!=null||po.getDamage_gallerys()!=null||po.getReturn_gallerys()!=null||po.getOrder_remark()!=null) {
			mapper.update(po);
			//添加订单日志
			addOrderLog(po,null,null);
			//返回结果
			return new AjaxResult("0","");
		}else {
			//更新订单价格  一天就是一份保险
			int rent_days=DateUtil.getIntervalDays(po.getRent_star_time()!=null?po.getRent_star_time():o.getRent_star_time(),po.getRent_end_time()!=null?po.getRent_end_time():o.getRent_end_time());
			double car_price=o.getType()==0?(o.getUnit_price()*rent_days):o.getCar_price();
			double amount=(car_price*100+o.getGet_amount()*100+o.getReturn_amount()*100+o.getInsurance()*rent_days*100+(po.getDamage_amount()!=null?po.getDamage_amount():o.getDamage_amount())*100+(po.getPeccancy_amount()!=null?po.getPeccancy_amount():o.getPeccancy_amount())*100+(po.getOther_amount()!=null?po.getOther_amount():o.getOther_amount())*100-o.getCoupon_amount()*100-o.getUse_point()*100)/100;
			po.setIns_num(rent_days);
			po.setCar_price(car_price);
			po.setAmount(amount);
			//余额重置
			if (o.getAmount()!=amount) {
				double x=amount-o.getAmount();
				if (o.getFirm_id()!=null) {
					Firm firm=firmMapper.queryById(o.getFirm_id());
					if (x>0&&firm.getLeft_amount()<x) {
						return new AjaxResult(CommonUtil.ERROR_CODE,firm.getPay_type()==0?"修改失败：企业信用额度不足":"修改失败：企业账户余额不足");
					}
					firm.setCredit_amount(firm.getCredit_amount()+x);
					firm.setUsed_amount(firm.getUsed_amount()+x);
					firm.setLeft_amount(firm.getLeft_amount()-x);
					firmMapper.updateAccount(firm);
				}else {
					//个人
					Member m=memberMapper.queryById(o.getMem_id());
					if (x>0&&m.getAccount()<x) {
						return new AjaxResult(CommonUtil.ERROR_CODE,"修改失败：账户余额不足");
					}
					m.setAccount(m.getAccount()-x);
					memberMapper.updateAccount(m);
				}
				//修改订单
				mapper.update(po);
				//添加流水记录
				if(x>0)paymentLogMapper.add(new PaymentLog(o.getMem_id(), o.getFirm_id(),Math.abs(x),"订单支付",o.getSn()+"【日租】",null, 0, 1, 0,"订单延迟还车补"+Math.abs(x)+"元"));
				if(x<0)paymentLogMapper.add(new PaymentLog(o.getMem_id(), o.getFirm_id(),Math.abs(x),"订单退款",o.getSn()+"【日租】",null, 0, 1, 3,"订单提前还车退"+Math.abs(x)+"元"));
			}
			//添加订单日志
			addOrderLog(po,null,null);
			//返回结果
			return new AjaxResult("0","");
		}
	}

	@Override
	public Page<Order> queryPage(BaseConditionVO vo) {
		PaginationInterceptor.startPage(vo.getPageNo(),vo.getPageSize());
		mapper.queryPage(vo);
		Page<Order> page=PaginationInterceptor.endPage();
		return page;
	}

	@Override
	public Order queryById(String id) {
		return mapper.queryById(id);
	}
	
	@Override
	public List<Order> queryPageByStatus(BaseConditionVO vo) {
		PaginationInterceptor.startPage(vo.getPageNo(),vo.getPageSize());
		mapper.queryPageApp(vo);
		Page<Order> page=PaginationInterceptor.endPage();
		//app端订单状态处理，已还车之后状态 全部为已完成
		List<Order> list=page.getResult();
		for (Order o:list) {
		    if (o.getType()==1) o.setCar_license(o.getCar_id());
			if (o.getStatus()>3) {
				o.setOrder_status("已完成");
			}
		}
		return list;
	}

	@Override
	public Order queryByIdApp(String id) {
		return mapper.queryByIdApp(id);
	}

	@Override
	public Order queryValidDate(Order po) {
		return mapper.queryValidDate(po);
	}

	@Override
	public Page<Order> queryDyncPage(BaseConditionVO vo) {
		PaginationInterceptor.startPage(vo.getPageNo(),vo.getPageSize());
		mapper.queryDyncPage(vo);
		Page<Order> page=PaginationInterceptor.endPage();
		return page;
	}

	@Override
	public AjaxResult updateEndTime(Order po,BaseConditionVO vo) {
		Map<String, Object> dataMap=new HashMap<String, Object>();
		Member logM=memberMapper.queryByCredential(vo.getCredential());
		Firm firm=null;
		Integer flg=po.getType();//提前或延后
		if (logM!=null) {
			//验证用户是否有效
			if(logM.getEnabled()!=0){
				return new AjaxResult(CommonUtil.ERROR_CODE,"修改失败：用户不合法",dataMap);
			}
			//验证用户是否审核
			if (logM.getReview()!=1) {
				return new AjaxResult(CommonUtil.ERROR_CODE,"修改失败：用户资料未通过审核",dataMap);
			}
			Order o=mapper.queryAmount(po.getId());
			//判断订单状态  已取消订单不能操作
			if (o.getStatus()==-1) {
				return new AjaxResult(CommonUtil.ERROR_CODE,"修改失败：订单无效（已取消）",dataMap);
			}
			//修改还车时间重新计算车辆租金
			int rent_days=DateUtil.getIntervalDays(o.getRent_star_time(), po.getRent_end_time());
			double ins_amount_x=o.getInsurance()*(rent_days-o.getIns_num());//保险差值
			double carPrice_x=(o.getUnit_price()*rent_days*100-o.getCar_price()*100)/100;
			double x=(carPrice_x*100+ins_amount_x*100)/100;
			if (logM.getType()==0) {//普通会员
				Car car=carMapper.queryById(o.getCar_id());//车辆信息
				if (logM.getDeposit()<car.getDeposit()) {
					return new AjaxResult(CommonUtil.ERROR_CODE,"提交失败：保证金不低于"+car.getDeposit()+"元",dataMap);
				}if (x>0&&logM.getAccount()<x) {
					return new AjaxResult(CommonUtil.ERROR_CODE,"提交失败：账户余额不足",dataMap);
				}
			}else{//企业个人会员
				firm=firmMapper.queryById(logM.getFirm_id());
				//验证企业合同时间
				if (firm.getEnabled()!=0) {
					return new AjaxResult(CommonUtil.ERROR_CODE,"提交失败：企业不合法",dataMap);
				}
				if (!(firm.getSign_date().before(new Date())&&firm.getEnd_date().after(new Date()))) {
					return new AjaxResult(CommonUtil.ERROR_CODE,"提交失败：企业协议已过期",dataMap);
				}
				if (x>0&&firm.getLeft_amount()<x) {
					return new AjaxResult(CommonUtil.ERROR_CODE,"提交失败：企业剩余额度不足",dataMap);
				}
			}
			//验证还车时间，不能为周六、周日
			if (DateUtil.getWeek(po.getRent_end_time())==0||DateUtil.getWeek(po.getRent_end_time())==6) {
				return new AjaxResult(CommonUtil.ERROR_CODE,"提交失败：还车时间不能为周末", dataMap);
			}
			if (po.getRent_end_time().before(o.getRent_star_time())) {
				return new AjaxResult(CommonUtil.ERROR_CODE,"修改失败：还车时间不能早于用车时间",dataMap);
			}
			//验证订单时间
			po.setCar_id(o.getCar_id());
			po.setRent_star_time(o.getRent_star_time());
			Order hisO=mapper.queryValidDate(po);
			if (hisO!=null) {
				return new AjaxResult(CommonUtil.ERROR_CODE,"修改失败：时间段已被预约",dataMap);
			}
			//验证用户是否存在该时间段内订单
			po.setMem_id(logM.getId());
			Order hisM=mapper.queryValidDateM(po);
			if (hisM!=null) {
				return new AjaxResult(CommonUtil.ERROR_CODE,"修改失败：您有一个待完成订单需要处理",dataMap);
			}
			//修改订单 租车总额，保险份数，订单总额
			po.setCar_price(o.getCar_price()+carPrice_x);
			po.setIns_num(rent_days);
			po.setAmount(o.getAmount()+x);
			po.setType(null);//避免影响订单类型TYPE
			mapper.update(po);
			//普通用户扣除账户余额
			if (logM.getType()==0) {
				logM.setAccount(logM.getAccount()-x);
				memberMapper.updateAccount(logM);
			}else {
				firm.setCredit_amount(firm.getCredit_amount()+x);
				firm.setUsed_amount(firm.getUsed_amount()+x);
				firm.setLeft_amount(firm.getLeft_amount()-x);
				firmMapper.updateAccount(firm);
			}
			//添加订单日志
			addOrderLog(po, logM,flg);
			//添加订单流水
			if(x>0)paymentLogMapper.add(new PaymentLog(logM.getId(), logM.getFirm_id(),Math.abs(x),"订单支付",o.getSn()+"【日租】",null, 0, 1, 0,"订单推迟还车补"+Math.abs(x)+"元"));
			if(x<0)paymentLogMapper.add(new PaymentLog(logM.getId(), logM.getFirm_id(),Math.abs(x),"订单退款",o.getSn()+"【日租】",null, 0, 1, 3,"订单提前还车退"+Math.abs(x)+"元"));
			//返回结果
			return new AjaxResult(CommonUtil.SUCCESS_CODE,CommonUtil.SUCCESS_MSG,dataMap);
		}
		return new AjaxResult(CommonUtil.ERROR_CREDENTIAL,CommonUtil.CREDENTIAL_INVALID,dataMap);
	}

	public static String getUser_id() {
		Subject sub = SecurityUtils.getSubject();
		Session session = sub.getSession();
		User user = (User) session.getAttribute("user");
		return user.getId();
	}
	
	public void addOrderLog(Order o,Member logM,Integer flg){
		if (logM!=null) {
			// 0：提前还车  1：延后还车  2：创建订单 3:取消订单
			if (flg==2) {
				logMapper.add(new OrderLog(StringUtil.getUuid(),o.getId(),null,logM.getId(),"创建订单"));
			}else if (flg==0) {
				logMapper.add(new OrderLog(StringUtil.getUuid(),o.getId(),null,logM.getId(),"修改还车时间,提前至【"+DateUtil.formatDate(o.getRent_end_time(),"yyyy-MM-dd HH:mm")+"】"));
			}else if (flg==1){
				logMapper.add(new OrderLog(StringUtil.getUuid(),o.getId(),null,logM.getId(),"修改还车时间,延后至【"+DateUtil.formatDate(o.getRent_end_time(),"yyyy-MM-dd HH:mm")+"】"));
			}else if(flg==3){
				logMapper.add(new OrderLog(StringUtil.getUuid(),o.getId(),null,logM.getId(),"取消订单"));
			}
		}else {
			if (o.getStatus()!=null) {
				switch (o.getStatus()) {
				case 1:
					logMapper.add(new OrderLog(StringUtil.getUuid(), o.getId(),getUser_id(),null,"修改订单状态为【已准备】"));
					break;
				case 2:
					logMapper.add(new OrderLog(StringUtil.getUuid(), o.getId(),getUser_id(),null,"修改订单状态为【已交车】"));
					break;
				case 3:
					logMapper.add(new OrderLog(StringUtil.getUuid(), o.getId(),getUser_id(),null,flg==0?"修改订单状态为【已还车】":"修改订单状态为【取消对账】"));
					break;
				case 4:
					logMapper.add(new OrderLog(StringUtil.getUuid(), o.getId(),getUser_id(),null,"修改订单状态为【已核价】"));
					break;
				case 5:
					logMapper.add(new OrderLog(StringUtil.getUuid(), o.getId(),getUser_id(),null,"修改订单状态为【已对账】"));
					break;
				case 6:
					logMapper.add(new OrderLog(StringUtil.getUuid(), o.getId(),getUser_id(),null,"修改订单状态为【已结算】"));
					break;
				case -1:
					logMapper.add(new OrderLog(StringUtil.getUuid(), o.getId(),getUser_id(),null,"修改订单状态为【已取消】"));
					break;
				default:
					break;
				}
			}else {
				if (o.getComplete_date()!=null) {
					logMapper.add(new OrderLog(StringUtil.getUuid(), o.getId(),getUser_id(),null,"修改完成时间为【"+DateUtil.formatDate(o.getComplete_date(),"yyyy-MM-dd HH:mm")+"】"));
				}else if (o.getReady_date()!=null) {
					logMapper.add(new OrderLog(StringUtil.getUuid(), o.getId(),getUser_id(),null,"修改备车时间为【"+DateUtil.formatDate(o.getReady_date(),"yyyy-MM-dd HH:mm")+"】"));
				}else if (o.getDelivery_date()!=null) {
					logMapper.add(new OrderLog(StringUtil.getUuid(), o.getId(),getUser_id(),null,"修改交车时间为【"+DateUtil.formatDate(o.getDelivery_date(),"yyyy-MM-dd HH:mm")+"】"));
				}else if (o.getRent_star_time()!=null) {
					logMapper.add(new OrderLog(StringUtil.getUuid(), o.getId(),getUser_id(),null,"修改计划用车时间为【"+DateUtil.formatDate(o.getRent_star_time(),"yyyy-MM-dd HH:mm")+"】"));
				}else if (o.getRent_end_time()!=null) {
					logMapper.add(new OrderLog(StringUtil.getUuid(), o.getId(),getUser_id(),null,"修改计划还车时间为【"+DateUtil.formatDate(o.getRent_end_time(),"yyyy-MM-dd HH:mm")+"】"));
				}else if (o.getRent_star_ttime()!=null) {
					logMapper.add(new OrderLog(StringUtil.getUuid(), o.getId(),getUser_id(),null,"修改实际用车时间为【"+DateUtil.formatDate(o.getRent_star_ttime(),"yyyy-MM-dd HH:mm")+"】"));
				}else if (o.getRent_end_ttime()!=null) {
					logMapper.add(new OrderLog(StringUtil.getUuid(), o.getId(),getUser_id(),null,"修改实际还车时间为【"+DateUtil.formatDate(o.getRent_end_ttime(),"yyyy-MM-dd HH:mm")+"】"));
				}else if (o.getDamage_amount()!=null) {
					logMapper.add(new OrderLog(StringUtil.getUuid(), o.getId(),getUser_id(),null,"修改车损费用为【"+o.getDamage_amount()+"】"));
				}else if (o.getPeccancy_amount()!=null) {
					logMapper.add(new OrderLog(StringUtil.getUuid(), o.getId(),getUser_id(),null,"修改违章费用为【"+o.getPeccancy_amount()+"】"));
				}else if (o.getOther_amount()!=null) {
					logMapper.add(new OrderLog(StringUtil.getUuid(), o.getId(),getUser_id(),null,"修改其他费用为【"+o.getOther_amount()+"】"));
				}else if (o.getReturn_gallerys()!=null) {
					logMapper.add(new OrderLog(StringUtil.getUuid(), o.getId(),getUser_id(),null,"修改订单还车照片"));
				}else if (o.getDamage_gallerys()!=null) {
					logMapper.add(new OrderLog(StringUtil.getUuid(), o.getId(),getUser_id(),null,"修改订单车损照片"));
				}
			}
		}
	}

	@Override
	public List<Order> export(BaseConditionVO vo) {
		return mapper.queryExport(vo);
	}
	
	@Override
	public List<Order> timeExport(BaseConditionVO vo) {
		return mapper.queryTimeExport(vo);
	}

	@Override
	public boolean updateIsRead(String id) {
		return mapper.updateIsRead(id)>0;
	}

	@Override
	public Integer queryNoReadCount(Integer type) {
		return mapper.queryNoReadCount(type);
	}

	@Override
	public boolean addEvcardOrder(HttpServletRequest req) {
		// 获取requestbody中订单数据
		String orderJson = "";
		try {
			BufferedReader br = req.getReader();
			String inputLine;
			while ((inputLine = br.readLine()) != null) {
				orderJson += inputLine;
			}
			br.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		// 解析数据并保存
		JSONObject o = JSON.parseObject(orderJson);
		String sn=o.getString("orderNo");
		String mobile=o.getString("mobilePhone");
		String car_license=o.getString("vehicleNo");
		String star_date=o.getString("pickupDateTime");
		String end_date=o.getString("returnDateTime");
		String get_dot=o.getString("pickupStoreName");
		String return_dot=o.getString("returnStoreName");
		double amount=o.getDouble("amount");
		Member logM=memberMapper.queryByMobile(mobile);
		Firm firm=null;
		if(logM.getType()==1)firm=firmMapper.queryById(logM.getFirm_id());
		if (mapper.queryCountBySn(sn)==0) {
			//创建订单
			System.out.println("------------------add order-------------");
			Order order = new Order();
			order.setId(StringUtil.getUuid());
			order.setSn(sn);
			order.setMem_id(logM.getId());
			order.setCar_id(car_license);
			order.setGet_dot_id(get_dot);
			order.setRent_star_time(DateUtil.stringToDate(StringUtil.formatDateString(star_date),"yyyy-MM-dd HH:mm"));
			order.setStatus(2);//订单状态 已交车
			order.setPay_type(logM.getType()==0?1:firm.getPay_type());//支付类型
			order.setPay_status(0);//未支付
			order.setType(1);//分时订单
			order.setOrder_json(orderJson);
			mapper.add(order);
			//冻结会员卡
			memberService.freezeMember(logM);
		}else{
			//更新订单
			System.out.println("------------------update order-------------");
			Order order = new Order();
			order.setSn(sn);
			order.setReturn_dot_id(return_dot);
			order.setRent_end_time(DateUtil.stringToDate(StringUtil.formatDateString(end_date),"yyyy-MM-dd HH:mm"));
			order.setRent_end_ttime(DateUtil.stringToDate(StringUtil.formatDateString(end_date),"yyyy-MM-dd HH:mm"));
			order.setCar_price(amount);//车辆租金
			order.setAmount(amount);//订单金额
			order.setStatus(3);//订单状态 已还车
			order.setComplete_date(DateUtil.stringToDate(StringUtil.formatDateString(end_date),"yyyy-MM-dd HH:mm"));//订单完成时间
			order.setOrder_json(orderJson);
			mapper.update(order);
			//如果企业用户直接扣除企业余额
			if (logM.getType()==1) {
				if (firm.getLeft_amount()>=amount) {
					firm.setCredit_amount(firm.getCredit_amount()+amount);
					firm.setUsed_amount(firm.getUsed_amount()+amount);
					firm.setLeft_amount(firm.getLeft_amount()-amount);
					firmMapper.updateAccount(firm);
					//更新订单状态
					Order upd=new Order();
					upd.setSn(sn);
					upd.setPay_amount(amount);
					upd.setPay_status(1);
					mapper.update(upd);
					//解冻会员卡
					memberService.unfreezeMember(logM);
					//添加支付流水记录
					paymentLogMapper.add(new PaymentLog(logM.getId(), logM.getFirm_id(),amount,"订单支付",order.getSn()+"【时租】",null, 0, 1, 0,null));
				}
			}
		}
		return true;
	}

	@Override
	public List<Firm> queryFirmFromOrder(BaseConditionVO vo) {
		return mapper.queryFirmFromOrder(vo);
	}

	@Override
	public List<User> queryDispFromOrder(BaseConditionVO vo) {
		return mapper.queryDispFromOrder(vo);
	}

	@Override
	public void sendReadyMsg2Disp() {
		List<Order> orders=mapper.queryDispByOid0();
		if (!orders.isEmpty()) {
			for(Order o:orders){
				String result=HttpSender.send(o.getDispatch_mobile(),StringUtil.isEmpty(o.getMem_name())?o.getMem_mobile():o.getMem_name()+"已预订"+o.getDot_name()+" "+o.getPark_name()+"上的 "+o.getCar_name()+","+o.getCar_license()+".提车时间"+DateUtil.formatDate(o.getRent_star_time(),"yyyy-MM-dd HH:mm")+","+(o.getGet_type()==0?"自取":("送车地点是 "+o.getGet_address()))+(StringUtil.isEmpty(o.getRemark())?"":",备注"+o.getRemark())+",客户联系电话"+o.getMem_mobile());
				if (result!=null&&result.equals("0")) {
					mapper.updateIsSms0(o.getId());
				}
			}
		}
	}
	
	@Override
	public void sendReturnMsg2Disp() {
		List<Order> orders=mapper.queryDispByOid1();
		if (!orders.isEmpty()) {
			for(Order o:orders){
				String result=HttpSender.send(o.getDispatch_mobile(),StringUtil.isEmpty(o.getMem_name())?o.getMem_mobile():o.getMem_name()+"将归还"+o.getCar_name()+","+o.getCar_license()+"到 "+o.getDot_name()+" "+o.getPark_name()+".还车时间"+DateUtil.formatDate(o.getRent_end_time(),"yyyy-MM-dd HH:mm")+","+(o.getReturn_type()==0?"自还":("还车地点是 "+o.getReturn_address()))+(StringUtil.isEmpty(o.getRemark())?"":",备注"+o.getRemark())+",客户联系电话"+o.getMem_mobile());
				if (result!=null&&result.equals("0")) {
					mapper.updateIsSms1(o.getId());
				}
			}
		}
	}
	
	@Override
	public void sendExpiredOrder() {
		List<Order> orders=mapper.queryDispByOid2();
		if (!orders.isEmpty()) {
			for(Order o:orders){
				String result=HttpSender.send(o.getMem_mobile(),"您已预订"+o.getDot_name()+" "+o.getPark_name()+"上的 "+o.getCar_name()+","+o.getCar_license()+".提车时间："+DateUtil.formatDate(o.getRent_star_time(),"yyyy-MM-dd HH:mm")+","+(o.getGet_type()==0?"自取":("送车地点： "+o.getGet_address()))+"，预约时间已过. 请您携带有效证件和智行家会员卡按约定的时间前往。 客服电话 400-0918-332，调度联系电话"+o.getDispatch_mobile()+"。祝您用车愉快！");
				if (result!=null&&result.equals("0")) {
					mapper.updateIsSms2(o.getId());
				}
			}
		}
	}
	
	@Override
	public void cancelOrder() {
		//获取全部可以取消的订单（30分钟未支付）
		List<String> ids=mapper.queryCancelOrder();
		for(String  id:ids){
			//取消订单
			mapper.cancelOrder(id);
			//添加日志
			logMapper.add(new OrderLog(StringUtil.getUuid(), id,null,null,"系统自动取消（未支付）"));
		}
	}
	
	@LogClass(module = "订单管理", method = "删除")
	@Override
	public boolean del(String ids) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("del_field","del_flg");
		map.put("del_flg", "1");
		map.put("del_ids", ids.split(","));
		return mapper.del(map) > 0;
	}

	@Override
	public AjaxResult cancelOrder(BaseConditionVO vo) {
		Map<String, Object> dataMap=new HashMap<String, Object>();
		Member logM=memberMapper.queryByCredential(vo.getCredential());
		if (logM!=null) {
			Order o=mapper.queryAmount(vo.getId());
			//判断订单状态  避免重复取消
			if (o.getStatus()==-1) {
				return new AjaxResult(CommonUtil.ERROR_CODE,"取消失败：重复取消",dataMap);
			}
			//判断订单取消时间是否允许，需提前一天
			if (DateUtil.stringToDate(DateUtil.currentDate()).after(DateUtil.stringToDate(DateUtil.addDay(-1,o.getRent_star_time(),"yyyy-MM-dd")))) {
				return new AjaxResult(CommonUtil.ERROR_CODE,"取消失败：取消需提前一天",dataMap);
			}
			//修改订单状态
			Order upd=new Order();
			upd.setId(vo.getId());
			upd.setStatus(-1);
			mapper.update(upd);
			//退款  如果已支付
			if (o.getPay_status()==1) {
				//退换账户余额
				if (o.getFirm_id()!=null) {
					//企业
					Firm firm=firmMapper.queryById(o.getFirm_id());
					firm.setCredit_amount(firm.getCredit_amount()-o.getAmount());
					firm.setUsed_amount(firm.getUsed_amount()-o.getAmount());
					firm.setLeft_amount(firm.getLeft_amount()+o.getAmount());
					firmMapper.updateAccount(firm);
				}else {
					//个人
					Member m=memberMapper.queryById(o.getMem_id());
					m.setAccount(m.getAccount()+o.getAmount());
					memberMapper.updateAccount(m);
				}
				//添加订单日志
				addOrderLog(o,logM,3);
				//添加取消订单流水
				paymentLogMapper.add(new PaymentLog(o.getMem_id(), o.getFirm_id(),o.getAmount(),"订单退款",o.getSn()+"【日租】",null, 0, 1, 3,null));
				
			}
			return new AjaxResult(CommonUtil.SUCCESS_CODE,"取消成功",dataMap); 
		}
		return new AjaxResult(CommonUtil.ERROR_CREDENTIAL,CommonUtil.CREDENTIAL_INVALID,dataMap);
	}

	/**
	 * 0 ：成功   1：失败   2：账户余额不足
	 */
	@Override
	public AjaxResult accountPay(BaseConditionVO vo) {
		Map<String, Object> dataMap=new HashMap<String, Object>();
		Member logM=memberMapper.queryByCredential(vo.getCredential());
		if (logM!=null) {
			//获取支付订单
			Order order=mapper.queryAmount(vo.getId());
			if (order.getFirm_id()==null) {
				if (!vo.getPay_pwd().equals(logM.getPay_pwd())) {
					return new AjaxResult(CommonUtil.ERROR_CODE,"支付密码错误",dataMap);
				}
				if (order.getAmount()>logM.getAccount()) {
					return new AjaxResult("2","账户余额不足",dataMap);
				}
				//扣除账户余额
				logM.setAccount(logM.getAccount()-order.getAmount());
				memberMapper.updateAccount(logM);
			}else{
				Firm firm=firmMapper.queryById(order.getFirm_id());
				if (order.getAmount()>firm.getLeft_amount()) {
					return new AjaxResult(CommonUtil.ERROR_CODE,firm.getPay_type()==0?"企业信用额度不足":"企业账户余额不足",dataMap);
				}
				firm.setCredit_amount(firm.getCredit_amount()+order.getAmount());
				firm.setUsed_amount(firm.getUsed_amount()+order.getAmount());
				firm.setLeft_amount(firm.getLeft_amount()-order.getAmount());
				firmMapper.updateAccount(firm);
			}
			//修改订单状态
			Order upd=new Order();
			upd.setId(vo.getId());
			upd.setPay_amount(order.getAmount());
			upd.setPay_status(1);
			mapper.update(upd);
			//如果时租订单 解冻用户
			if(order.getType()==1)memberService.unfreezeMember(logM);
			//添加支付流水记录
			paymentLogMapper.add(new PaymentLog(order.getMem_id(), order.getFirm_id(),order.getAmount(),"订单支付",order.getSn()+(order.getType()==0?"【日租】":"【时租】"),null, 0, 1, 0,null));
			//发送订车短信
			Car sc=mapper.queryDispByCid(order.getCar_id());
			HttpSender.send(logM.getMobile(),"您已预订"+sc.getDot_name()+" "+sc.getPark_name()+"上的 "+sc.getName()+","+sc.getLicense()+".提车时间："+DateUtil.formatDate(order.getRent_star_time(),"yyyy-MM-dd HH:mm")+","+(order.getGet_type()==0?"自取":("送车地点： "+order.getGet_address()))+"，请您携带有效证件和智行家会员卡按约定的时间前往。 客服电话 400-0918-332.调度联系电话"+sc.getDispatch_mobile()+"。祝您用车愉快！");
			return new AjaxResult(CommonUtil.SUCCESS_CODE,CommonUtil.SUCCESS_MSG,dataMap);
		}
		return new AjaxResult(CommonUtil.ERROR_CREDENTIAL,CommonUtil.CREDENTIAL_INVALID,dataMap);
	}

	@Override
	public Order queryAmount(String id) {
		return mapper.queryAmount(id);
	}

	@Override
	public void orderNotify(String sn,String trade_no,int pay_type) {
		if (paymentLogMapper.queryPayValid(trade_no)==0) {
			Order order=mapper.queryBaseBySn(sn);
			//修改订单信息
			Order upd=new Order();
			upd.setSn(sn);
			upd.setPay_amount(order.getAmount());
			upd.setPay_status(1);
			mapper.update(upd);
			//如果时租订单 解冻用户
			Member logM=memberMapper.queryById(order.getMem_id());
			if(order.getType()==1){
				memberService.unfreezeMember(logM);
			}
			//添加支付流水记录
			paymentLogMapper.add(new PaymentLog(order.getMem_id(), order.getFirm_id(),order.getAmount(),"订单支付",sn+(order.getType()==0?"【日租】":"【时租】"),trade_no, pay_type, 1, 0,null));
			//发送订车短信
			Car sc=mapper.queryDispByCid(order.getCar_id());
			HttpSender.send(logM.getMobile(),"您已预订"+sc.getDot_name()+" "+sc.getPark_name()+"上的 "+sc.getName()+","+sc.getLicense()+".提车时间："+DateUtil.formatDate(order.getRent_star_time(),"yyyy-MM-dd HH:mm")+","+(order.getGet_type()==0?"自取":("送车地点： "+order.getGet_address()))+"，请您携带有效证件和智行家会员卡按约定的时间前往。 客服电话 400-0918-332.调度联系电话"+sc.getDispatch_mobile()+"。祝您用车愉快！");
		}
	}

	@Override
	public void depositNotify(String sn,String trade_no) {
		if (paymentLogMapper.queryPayValid(trade_no)==0) {
			PaymentLog log=paymentLogMapper.queryBySn(sn);
			//修改流水状态
			PaymentLog updlog=new PaymentLog();
			updlog.setSn(sn);
			updlog.setTrade_no(trade_no);
			updlog.setPay_status(1);
			paymentLogMapper.update(updlog);
			//增加保证金
			Member logM=memberMapper.queryById(log.getMem_id());
			double deposit=logM.getDeposit()+log.getAmount();
			Member updM=new Member();
			updM.setId(log.getMem_id());
			updM.setDeposit(deposit);
			memberMapper.updateDeposit(updM);
		}
	}

	@Override
	public void accountNotify(String sn,String trade_no) {
		if (paymentLogMapper.queryPayValid(trade_no)==0) {
			PaymentLog log=paymentLogMapper.queryBySn(sn);
			//修改流水状态
			PaymentLog updlog=new PaymentLog();
			updlog.setSn(sn);
			updlog.setTrade_no(trade_no);
			updlog.setPay_status(1);
			paymentLogMapper.update(updlog);
			//增加账户余额
			Member logM=memberMapper.queryById(log.getMem_id());
			double account=logM.getAccount()+log.getAmount();
			Member updM=new Member();
			updM.setId(log.getMem_id());
			updM.setAccount(account);
			memberMapper.updateAccount(updM);
		}
	}

	@LogClass(module="订单管理",method="对账")
	@Override
	public boolean checkAmount(Order po) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("del_field","status");
		map.put("del_flg", "5");
		map.put("del_ids", po.getId().split(","));
		return mapper.del(map) > 0;
	}

}
