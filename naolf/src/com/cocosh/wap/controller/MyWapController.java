package com.cocosh.wap.controller;

import java.math.BigDecimal;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.cocosh.framework.base.BaseController;
import com.cocosh.framework.payment.wxap.WXConfig;
import com.cocosh.framework.util.CommonUtil;
import com.cocosh.framework.util.StringUtil;
import com.cocosh.nlf.model.EmpDisJournal;
import com.cocosh.nlf.model.Student;
import com.cocosh.nlf.model.Ticket;
import com.cocosh.nlf.model.Uprule;
import com.cocosh.nlf.model.UserCash;
import com.cocosh.nlf.model.UserScoreInfo;
import com.cocosh.nlf.model.UserTicket;
import com.cocosh.nlf.service.EmpDisJournalService;
import com.cocosh.nlf.service.StudentService;
import com.cocosh.nlf.service.TicketService;
import com.cocosh.nlf.service.UpruleService;
import com.cocosh.nlf.service.UserCashService;
import com.cocosh.nlf.service.UserScoreInfoService;
import com.cocosh.nlf.service.UserTicketService;
import com.cocosh.sys.model.User;
import com.cocosh.sys.service.UserService;
import com.sun.org.glassfish.gmbal.ParameterNames;

@Controller
@RequestMapping("wap/my")
public class MyWapController extends BaseController {
	@Autowired
	private StudentService studentService;
	@Autowired
	private UserService userService;
	@Autowired
	private UserTicketService userTicketService;
	@Autowired
	private UserScoreInfoService userScoreInfoService;
	@Autowired
	private EmpDisJournalService empDisJournalService;
	@Autowired
	private UserCashService userCashService;
	@Autowired
	private TicketService ticketService;
	@Autowired
	private UpruleService upruleService;
	@Autowired
	private UserScoreInfoService userScoreService;
	/*
	 * 我的
	 * */
	@RequestMapping("my")
	public String my(Model model) {
		Subject subject = SecurityUtils.getSubject();
		User user = (User) subject.getSession().getAttribute("wapuser");
		user = userService.queryById(user.getId());
		model.addAttribute("user", user);
		return "wap/my/my";
	}
	
	/*
	 * 积分
	 * */
	@RequestMapping("point")
	public String point(Model model,EmpDisJournal empDisJournal,String record) {
		User user =getWapUser();
		if(!StringUtil.isEmpty(record)){
			UserCash  uc = new UserCash();
			uc.setUser_id(user.getId());
			List<UserCash> userCash = userCashService.queryWapAll(uc);
			model.addAttribute("user",  userService.queryById(user.getId()));
			model.addAttribute("userCash", userCash);
			model.addAttribute("score_percent", CommonUtil.SCOREPERCENT);
			return "wap/my/point";
		}
		UserScoreInfo userScoreInfo= userScoreInfoService.queryByUser(user.getId());
		empDisJournal.setUser_number(user.getNumber());
		List<EmpDisJournal> list = empDisJournalService.queryList(empDisJournal);
		model.addAttribute("userScoreInfo", userScoreInfo);
		model.addAttribute("empDisJournal", empDisJournal);
		model.addAttribute("list", list);
		return "wap/my/point";
	}
	
//	/*
//	 * 积分提现
//	 * */
//	@RequestMapping("pointWithdraw")
//	public String pointWithdraw() {
//		return "wap/my/pointWithdraw";
//	}
	
	/*
	 * 积分详情
	 * */
	@RequestMapping("pointDetail")
	public String pointDetail(Model model,EmpDisJournal po,String type){
		User user =getWapUser();
		po.setORDER_NO(po.getORDER_NO());
		po.setUser_number(user.getNumber());
		List<EmpDisJournal> list=empDisJournalService.queryDetails(po);
		if(list.size()>0) po=list.get(0);
		model.addAttribute("emp", po);
		model.addAttribute("type", type);
		return "wap/my/pointDetail";
	}
	
//	/*
//	 * 充值
//	 * */
//	@RequestMapping("recharge")
//	public String recharge() {
//		return "wap/my/recharge";
//	}
	
	/*
	 * 会员信息
	 * */
	@RequestMapping("memberInfo")
	public String memberInfo(Model model) {
		User user =getWapUser();
		user = userService.queryById(user.getId());
		user.setMobile(StringUtil.mobilehide(user.getMobile()));
		Student student = new Student();
		student.setUser_id(user.getId());
		List<Student> students = studentService.queryByAll(student);
		model.addAttribute("student", students);
		model.addAttribute("user", user);
		return "wap/my/memberInfo";
	}
	
	/*
	 * 会员信息-添加孩子
	 * */
	@RequestMapping("memberAddChild")
	public String memberAddChild() {
		return "wap/my/memberAddChild";
	}
	
	/*
	 * 会员信息-修改密码
	 * */
	@RequestMapping("memberUpdatePwd")
	public String memberUpdatePwd() {
		return "wap/my/memberUpdatePwd";
	}
	
	/*
	 * 会员信息-输入旧手机号码
	 * */
	@RequestMapping("memberUpdatePhone")
	public String memberUpdatePhone() {
		return "wap/my/memberUpdatePhone";
	}

	/*
	 * 会员信息-输入新手机号码
	 * */
	@RequestMapping("memberUpdateNewPhone")
	public String memberUpdateNewPhone() {
		return "wap/my/memberUpdateNewPhone";
	}
	
	/*
	 * 会员信息-修改地址
	 * */
	@RequestMapping("memberUpdateAddress")
	public String memberUpdateAddress(Model model) {
		User u=getWapUser();
		u=userService.queryById(u.getId());
		model.addAttribute("user", u);
		return "wap/my/memberUpdateAddress";
	}
	
	/*
	 * 我的卡券列表
	 * */
	@RequestMapping("myCardList")
	public String myCardList(Model model) {
		User user=getWapUser();
		List<UserTicket> list = userTicketService.queryUticketByUserId(user.getId());
		model.addAttribute("list", list);
		return "wap/my/myCardList";
	}
	
	/*
	 * 我的卡券详情
	 * */
	@RequestMapping("myCardDetail")
	public String myCardDetail(Model model,String ut_id) {
		UserTicket ut = userTicketService.querydetails(ut_id);
		String used_name="";
		Integer canup=0;//不能升级
		if(ut!=null){
			if(ut.getUsed()==1){
				User user = userService.queryById(ut.getUsed_id());
				used_name=user.getName();
			}
			if(ut.getUsed()==0){
				canup=1;//未使用可以升级
			}
			if(ut.getUsed()==1&&ut.getUpgrade()==0&&ut.getUsed_id().equals(getWapUser().getId())){
				canup=1;//已经被使用,未升级,使用者是本人
			}
			if(canup==1){
				Uprule uprule=upruleService.queryone();
				if(uprule==null||(!uprule.getTicket_id1().equals(ut.getConn_id()))){
					canup=0;
				}
			}
		}
		model.addAttribute("used_name", used_name);
		model.addAttribute("userTicket", ut);
		model.addAttribute("canup", canup);
		return "wap/my/myCardDetail";
	}
	
	/*
	 * 我的卡券升级
	 * */
	@RequestMapping("cardUpGrade")
	public String cardUpGrade(Model model,String ut_id) {
		model.addAttribute("ut_id", ut_id);
		UserTicket ut = userTicketService.querydetails(ut_id);
		model.addAttribute("used", ut.getUsed());
		return "wap/my/cardUpGrade";
	}
	
	/*
	 * 选择支付方式
	 * */
	@RequestMapping("choosePayMethod")
	public String choosePayMethod() {
		return "wap/my/choosePayMethod";
	}
	
	/*
	 * 选择在线支付
	 * */
	@RequestMapping("chooseOnlinePay")
	public String chooseOnlinePay(Model model,String ut_id) {
		Uprule uprule=upruleService.queryone();
		Ticket ticket2=ticketService.queryById(uprule.getTicket_id2());
		model.addAttribute("price",ticket2.getPrice());//抵扣金额
		User user=getWapUser();
		UserScoreInfo uscore=userScoreService.queryByUser(user.getId());
		BigDecimal member_kd=BigDecimal.ZERO;
		if(uscore!=null){
			member_kd=uscore.getKD_SCORE();
		}
		BigDecimal need_kd=new BigDecimal(ticket2.getPrice()*CommonUtil.SCOREPERCENT);
		Integer canpay=0;//不能支付
		if(member_kd.compareTo(need_kd)>=0){
			canpay=1;
		}
		model.addAttribute("ut_id", ut_id);
		model.addAttribute("canpay", canpay);
		model.addAttribute("appid", WXConfig.APPID);
		model.addAttribute("redirect_uri", WXConfig.UPGRADE_WX_REDIRECT_URI);
		return "wap/my/chooseOnlinePay";
	}
	
	@RequestMapping("upgradeByjf/{ut_id}")
	public String upgradeByjf(Model model,@PathVariable String ut_id){
		model.addAttribute("ut_id", ut_id);
		Uprule uprule=upruleService.queryone();
		Ticket ticket2=ticketService.queryById(uprule.getTicket_id2());
		BigDecimal need_kd=new BigDecimal(ticket2.getPrice()*CommonUtil.SCOREPERCENT);
		model.addAttribute("price",ticket2.getPrice());//抵扣金额
		model.addAttribute("score", need_kd);
		return "wap/my/upgradebyjf";
	}
	
	/*
	 * 选择学习券（升级）
	 * */
	@RequestMapping("chooseStudentCard")
	public String chooseStudentCard(Model model,String ut_id) {
		model.addAttribute("ut_id", ut_id);//卡的id
		User user=getWapUser();
		//model.addAttribute("utlist",userTicketService.queryByName(CommonUtil.USERTICKETNAME2, user.getId()));
		model.addAttribute("utlist", userTicketService.queryupCard(ut_id, user.getId()));
		return "wap/my/chooseStudentCard";
	}
	
	
	/**
	 * 脑立方积分去提现
	 * @param po
	 * @param model
	 * @return
	 */
	@RequestMapping("topayWithdrawals")
	public String topayWithdrawals(Model model,HttpServletRequest req){
		User user=getWapUser();
		BigDecimal KD_SCORE=BigDecimal.ZERO;
		UserScoreInfo userScoreInfo= userScoreInfoService.queryByUser(user.getId());
		if(userScoreInfo!=null){
			KD_SCORE=userScoreInfo.getKD_SCORE();
		}
		model.addAttribute("KD_SCORE", KD_SCORE);
		return "wap/my/topayWithdrawals";
	}
	/**
	 * 脑立方积分提现选择
	 * @param po
	 * @param model
	 * @return
	 */
	@RequestMapping("payWithdrawals")
	public String payWithdrawals(Model model,HttpServletRequest req){
		Double amount = Double.valueOf(req.getParameter("amount"));
		model.addAttribute("amount",amount);
		return "wap/my/payWithdrawals";
	}
}
