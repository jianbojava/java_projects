package com.cocosh.wap.api;

import java.util.List;
import java.util.Map;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cocosh.framework.base.AjaxResult;
import com.cocosh.framework.base.BaseController;
import com.cocosh.framework.util.CommonUtil;
import com.cocosh.framework.util.StringUtil;
import com.cocosh.nlf.model.Lesson;
import com.cocosh.nlf.model.Norder;
import com.cocosh.nlf.model.Ticket;
import com.cocosh.nlf.model.UserTicket;
import com.cocosh.nlf.service.LessonService;
import com.cocosh.nlf.service.NorderService;
import com.cocosh.nlf.service.TicketService;
import com.cocosh.nlf.service.UserTicketService;
import com.cocosh.sys.model.User;
import com.cocosh.sys.service.UserService;

@Controller
@RequestMapping("shopapi/")
public class ShopApiController extends BaseController {
	Map<String, Object> dataMap=null;
	@Autowired
	private UserService userService;
	@Autowired
	private NorderService norderService;
	@Autowired
	private TicketService ticketService;
	@Autowired
	private UserTicketService userTicketService;
	@Autowired
	private LessonService lessonService;

	/* 
	 * 订单购买课程提交成功
	 * */
	@RequestMapping("lessonOrder")//code,ticket_sn,type,conn_id,pay_type
	@ResponseBody
	public AjaxResult lessonOrder(Norder norder,String code){
		if(getWapUser()==null){
			return  new AjaxResult(CommonUtil.ERROR_CODE,"登陆超时",null);
		}
		String sessionCode=readSession(norder.getMobile());
		if(StringUtil.isEmpty(code)){return new AjaxResult(CommonUtil.ERROR_CODE,"请先获取验证码",dataMap);}
		if(!code.equals(sessionCode)){return new AjaxResult(CommonUtil.ERROR_CODE,"验证码不正确",dataMap);}
		writeSession(norder.getMobile(),null);////验证码输入成功后清空验证码
		return norderService.addNorder(norder);
	}
	/* 
	 * 订单购买卡券提交成功
	 * */
	@RequestMapping("ticketOrder")
	@ResponseBody
	public AjaxResult ticketOrder(Norder norder,String code){
		if(getWapUser()==null){
			return  new AjaxResult(CommonUtil.ERROR_CODE,"登陆超时",null);
		}
		String sessionCode=readSession(norder.getMobile());
		if(StringUtil.isEmpty(code)){return new AjaxResult(CommonUtil.ERROR_CODE,"请先获取验证码",dataMap);}
		if(!code.equals(sessionCode)){return new AjaxResult(CommonUtil.ERROR_CODE,"验证码不正确",dataMap);}
		return norderService.addNorder(norder);
	}
	/* 
	 * 取消订单
	 * */
	@RequestMapping("cancelOrder")
	@ResponseBody
	public AjaxResult cancelOrder(Norder norder){
		if(getWapUser()==null){
			return new AjaxResult(CommonUtil.ERROR_CODE,"登陆超时",null); 
		}
		if(norderService.wapcancel(norder.getId())){
			return new AjaxResult(CommonUtil.SUCCESS_CODE,"订单取消成功",dataMap);
		}else{
			return new AjaxResult(CommonUtil.ERROR_CODE,"操作失败",dataMap);
		}
	}
	
	/* 
	 * 支付前验证订单
	 * */
	@RequestMapping("payValid/{order_id}")
	@ResponseBody
	public AjaxResult payValid(@PathVariable String order_id){
		if(getWapUser()==null){
			return new AjaxResult(CommonUtil.ERROR_CODE,"登陆超时",null); 
		}
		Integer result=norderService.payValid(order_id);
		return new AjaxResult(result+"","操作失败",null);
	}
}
