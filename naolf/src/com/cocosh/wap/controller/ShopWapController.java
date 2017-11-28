package com.cocosh.wap.controller;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

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
import com.cocosh.nlf.model.Lesson;
import com.cocosh.nlf.model.Norder;
import com.cocosh.nlf.model.Ticket;
import com.cocosh.nlf.model.UserScoreInfo;
import com.cocosh.nlf.service.LessonService;
import com.cocosh.nlf.service.NorderService;
import com.cocosh.nlf.service.TicketService;
import com.cocosh.nlf.service.UserScoreInfoService;
import com.cocosh.sys.model.User;
import com.cocosh.sys.service.UserService;

@Controller
@RequestMapping("wap/shop/")
public class ShopWapController extends BaseController {
	/*
	 * 商城列表
	 * */
	@Autowired
	private LessonService lessonService;
	@Autowired
	private TicketService ticketService;
	@Autowired
	private NorderService norderService;
	@Autowired
	private UserService userService;
	@Autowired
	private UserScoreInfoService userScoreService;
	
	@RequestMapping("goodsList")
	public String goodsList(Model model) {
		List<Lesson> listL=lessonService.queryAll();
		List<Ticket> listT=ticketService.queryAll();
		model.addAttribute("listL", listL);
		model.addAttribute("listT", listT);
		return "wap/shop/goodsList";
	}
	
	/*
	 * 商城-课程详情
	 * */
	@RequestMapping("goodsCourseDetail")
	public String goodsCourseDetail(Model model,String id) {
		Lesson lesson=lessonService.queryById(id);
		model.addAttribute("lesson", lesson);
		return "wap/shop/goodsCourseDetail";
	}
	
	/*
	 * 商城-卡券详情
	 * */
	@RequestMapping("cardCouponDetail")
	public String cardCouponDetail(Model model,String id) {
		Ticket ticket=ticketService.queryById(id);
	    List<Lesson> list = new ArrayList<Lesson>();
	    if(!StringUtil.isEmpty(ticket.getLesson_ids())){
	    	String[] arr = ticket.getLesson_ids().split(",");
		    for (String ids : arr){
			    	Lesson lesson = lessonService.queryById(ids);
				    list.add(lesson);
			    }
			model.addAttribute("list", list);
			model.addAttribute("ticket", ticket);
			return "wap/shop/cardCouponDetail";
	    }else{
			model.addAttribute("list", list);
			model.addAttribute("ticket", ticket);
			return "wap/shop/cardCouponDetail";
	    }
	}
	
	/*
	 * 商城-卡券确认订单
	 * */
	@RequestMapping("ticketOrder")
	public String ticketOrder(String tid, Model model) {
		User user =getWapUser();
		if(user==null){return "wap/user/login";}
		user = userService.queryById(user.getId());
		Ticket ticket=ticketService.queryById(tid); 
		model.addAttribute("ticket", ticket);
		model.addAttribute("user",user);
		BigDecimal kz_score=new BigDecimal(0);
		BigDecimal kd_score=new BigDecimal(0);
		UserScoreInfo uscore=userScoreService.queryByUser(user.getId());
		if(uscore!=null){
			kz_score=uscore.getKZ_SCORE();
			kd_score=uscore.getKD_SCORE();
		}
		model.addAttribute("kz_score", kz_score);//查找会员可转积分
		model.addAttribute("kd_score", kd_score);//查找会员可兑积分
		model.addAttribute("score_percent", CommonUtil.SCOREPERCENT);//积分和金额换算比例
		return "wap/shop/ticketOrder";
	}

	/*
	 * 商城-我的进入卡券订单支付
	 * */
	@RequestMapping("ticketListOrder")
	public String ticketListOrder(String tid, Model model) {
		User user = getWapUser();
		if(user==null) return "wap/user/login";
		if(tid!=null&&tid!=""){
			Norder norder = new Norder();
			norder.setId(tid);
			norder = norderService.queryById(tid);
			if(norder.getId()=="1"){;//订单加入个人订单列表
				return "wap/user/login";
			}
			Ticket ticket=ticketService.queryById(norder.getConn_id()); 
			model.addAttribute("norder",norder);
			model.addAttribute("ticket", ticket);
			model.addAttribute("user",userService.queryById(user.getId()));
			return "wap/shop/ticketOrder";
		}
		return "wap/user/login";
	}

	/*
	 * 商城-课程确认订单
	 * */
	@RequestMapping("lessonOrder")
	public String lessonOrder(String lid,Model model) {
		User user =getWapUser();
		if(user==null){return "wap/user/login";}
		user = userService.queryById(user.getId());
		Lesson lesson=lessonService.queryById(lid); 
		model.addAttribute("lesson", lesson);
		model.addAttribute("user",user);
		BigDecimal kz_score=new BigDecimal(0);
		BigDecimal kd_score=new BigDecimal(0);
		UserScoreInfo uscore=userScoreService.queryByUser(user.getId());
		if(uscore!=null){
			kz_score=uscore.getKZ_SCORE();
			kd_score=uscore.getKD_SCORE();
		}
		model.addAttribute("kz_score", kz_score);//查找会员可转积分
		model.addAttribute("kd_score", kd_score);//查找会员可兑积分
		model.addAttribute("score_percent", CommonUtil.SCOREPERCENT);//积分和金额换算比例
		return "wap/shop/lessonOrder";
	}

	/*
	 * 商城-我的 进入课程订单支付
	 * */
	@RequestMapping("lessonListOrder")
	public String lessonListOrder(String lid,Model model) {
		User user = getWapUser();
		if(user==null) return "wap/user/login";
		if(lid!=null&&lid!=""){
		Norder norder = new Norder();
		norder.setId(lid);
		norder = norderService.queryById(lid);
		if(norder.getId()=="1"){;//订单加入个人订单列表
			return "wap/user/login";
		}
		Lesson lesson=lessonService.queryById(norder.getConn_id()); 
		model.addAttribute("norder",norder);
		model.addAttribute("lesson", lesson);
		model.addAttribute("user",userService.queryById(user.getId()));
		return "wap/shop/lessonOrder";
		}	
		return "wap/user/login";
	}
	/*
	 * 商城-全部订单列表
	 * */
	@RequestMapping("orderList")
	public String orderList(Model model) {
		User user =getWapUser();
		if(user==null) return "wap/user/login";
		Norder norder = new Norder();
		norder.setUser_id(user.getId());
		norder.setType(0);
		List<Norder> listL = norderService.queryLesson(norder);
		norder.setType(1);
		List<Norder> listT = norderService.queryTicket(norder);
		model.addAttribute("listL", listL);
		model.addAttribute("listT", listT);
		return "wap/shop/orderList";
	}
	
	/*
	 * 商城-已付款订单列表
	 * */
	@RequestMapping("orderList_pay")
	public String orderList_pay(Model model) {
		User user = getWapUser();
		if(user==null) return "wap/user/login";
		Norder norder = new Norder();
		norder.setUser_id(user.getId());
		norder.setType(0);
		norder.setStatus(1);
		List<Norder> listL = norderService.queryLesson(norder);
		norder.setType(1);
		norder.setStatus(1);
		List<Norder> listT = norderService.queryTicket(norder);
		model.addAttribute("listL", listL);
		model.addAttribute("listT", listT);
		return "wap/shop/orderList_pay";
	}
	
	/*
	 * 商城-待付款订单列表
	 * */
	@RequestMapping("orderList_noPay")
	public String orderList_noPay(Model model) {
		User user =getWapUser();
		if(user==null) return "wap/user/login";
		Norder norder = new Norder();
		norder.setUser_id(user.getId());
		norder.setType(0);
		norder.setStatus(0);
		List<Norder> listL = norderService.queryLesson(norder);
		norder.setType(1);
		norder.setStatus(0);
		List<Norder> listT = norderService.queryTicket(norder);
		model.addAttribute("listL", listL);
		model.addAttribute("listT", listT);
		return "wap/shop/orderList_noPay";
	}
	
	/*
	 * 商城-课程订单详情
	 * */
	@RequestMapping("lessonOrderDetail/{id}")
	public String lessonOrderDetail(Model model,@PathVariable String id) {
		User user = getWapUser();
		if(user==null) return "wap/user/login";
		user = userService.queryById(user.getId());
		Norder norder = norderService.queryById(id);
		Lesson lesson=lessonService.queryById(norder.getConn_id()); 
		model.addAttribute("lesson", lesson);
		model.addAttribute("norder", norder);
		model.addAttribute("user",user);
		return "wap/shop/lessonOrderDetail";
	}
	

	/*
	 * 商城-课程订单详情
	 * */
	@RequestMapping("ticketOrderDetail/{id}")
	public String ticketOrderDetail(Model model,@PathVariable String id) {
		User user = getWapUser();
		if(user==null) return "wap/user/login";
		user = userService.queryById(user.getId());
		Norder norder = norderService.queryById(id);
		Ticket ticket=ticketService.queryById(norder.getConn_id()); 
    	List<Lesson> list=lessonService.queryByIds(ticket.getLesson_ids());
		model.addAttribute("norder", norder);
		model.addAttribute("user",user);
		model.addAttribute("list", list);
		model.addAttribute("norder", norder);
		return "wap/shop/ticketOrderDetail";
	}
}
