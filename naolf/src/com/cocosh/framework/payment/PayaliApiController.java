package com.cocosh.framework.payment;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.cocosh.car.model.PaymentLog;
import com.cocosh.car.service.ChargeOrderService;
import com.cocosh.car.service.OrderService;
import com.cocosh.car.service.PaymentLogService;
import com.cocosh.framework.base.BaseController;
import com.cocosh.framework.payment.alipay.util.AlipayNotify;
import com.cocosh.framework.util.StringUtil;
import com.cocosh.nlf.model.Norder;
import com.cocosh.nlf.model.Ticket;
import com.cocosh.nlf.model.Uprule;
import com.cocosh.nlf.model.UserTicket;
import com.cocosh.nlf.service.NorderService;
import com.cocosh.nlf.service.TicketService;
import com.cocosh.nlf.service.UpgradeService;
import com.cocosh.nlf.service.UpruleService;
import com.cocosh.nlf.service.UserScoreInfoService;
import com.cocosh.nlf.service.UserTicketService;
import com.cocosh.sys.model.User;

@Controller
@RequestMapping("pay/alipay")
public class PayaliApiController extends BaseController {
	@Autowired
	private OrderService service;
	@Autowired
	private ChargeOrderService chargeOrderService;
	@Autowired
	private NorderService norderService;
	@Autowired
	private PaymentLogService paylogService;
	@Autowired
	private UserScoreInfoService scoreService;
	@Autowired
	private UserTicketService uticketService;
	@Autowired
	private UpruleService upService;
	@Autowired
	private TicketService ticketService;
	@Autowired
	private UpgradeService upgradeService;
	
	/**
	 * 微信端的支付宝支付（用到）
	 * @param po
	 * @param model
	 * @return
	 */
	@RequestMapping("topay/{sn}")
	public String topay(@PathVariable String sn,Model model,HttpServletRequest req){
		Norder norder=norderService.queryBySn(sn);
		model.addAttribute("total",0.01);
		model.addAttribute("sn",sn);
		User user=getWapUser();
		Integer is_login=0;
		if(user==null){
			is_login=1;
		}
		model.addAttribute("is_login",is_login);
		return "wap/pay/wxalipay";
	}
	
	//跳转至微信端支付宝支付（用到）
	@RequestMapping("wxalipayapi")
	public String wapalipayApi() {
		System.out.println("----");
		return "wap/pay/wxalipayapi";
	}
	

	@RequestMapping("wxnotify_url")
	public String wxnotifyUrl(HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {
			PrintWriter out = response.getWriter();
			Map<String,String> params = new HashMap<String,String>();
			Map requestParams = request.getParameterMap();
			for (Iterator iter = requestParams.keySet().iterator(); iter.hasNext();) {
				String name = (String) iter.next();
				String[] values = (String[]) requestParams.get(name);
				String valueStr = "";
				for (int i = 0; i < values.length; i++) {
					valueStr = (i == values.length - 1) ? valueStr + values[i]
							: valueStr + values[i] + ",";
				}
				//乱码解决，这段代码在出现乱码时使用。如果mysign和sign不相等也可以使用这段代码转化
				valueStr = new String(valueStr.getBytes("ISO-8859-1"), "utf-8");
				params.put(name, valueStr);
			}

			//获取支付宝的通知返回参数，可参考技术文档中页面跳转同步通知参数列表(以下仅供参考)//
			//商户订单号

			String out_trade_no = new String(request.getParameter("out_trade_no").getBytes("ISO-8859-1"),"UTF-8");

			//支付宝交易号

			String trade_no = new String(request.getParameter("trade_no").getBytes("ISO-8859-1"),"UTF-8");
			//交易状态
			String trade_status = new String(request.getParameter("trade_status").getBytes("ISO-8859-1"),"UTF-8");

			//获取支付宝的通知返回参数，可参考技术文档中页面跳转同步通知参数列表(以上仅供参考)//
			
			//计算得出通知验证结果
			boolean verify_result = AlipayNotify.verifyMD5(params);
			
			if(verify_result){//验证成功
				if(trade_status.equals("TRADE_FINISHED")){
					boolean result=norderService.pay_success(out_trade_no,trade_no, 1);
					out.print(result);
					out.println("success");	//请不要修改或删除
				} else if (trade_status.equals("TRADE_SUCCESS")){
					boolean result=norderService.pay_success(out_trade_no,trade_no, 1);
					out.print(result);
					out.println("success");	//请不要修改或删除
				}
				out.println("success");	//请不要修改或删除
			}else{//验证失败
				//该页面可做页面美工编辑
				out.println("fail");
			}
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return "redirect:../../wap/shop/orderList";
	}

	@RequestMapping("wxreturn_url")
	public String wxreturnUrl(HttpServletRequest request, HttpServletResponse response) {
		try {
			PrintWriter out = response.getWriter();
			Map<String,String> params = new HashMap<String,String>();
			Map requestParams = request.getParameterMap();
			for (Iterator iter = requestParams.keySet().iterator(); iter.hasNext();) {
				String name = (String) iter.next();
				String[] values = (String[]) requestParams.get(name);
				String valueStr = "";
				for (int i = 0; i < values.length; i++) {
					valueStr = (i == values.length - 1) ? valueStr + values[i]
							: valueStr + values[i] + ",";
				}
				//乱码解决，这段代码在出现乱码时使用。如果mysign和sign不相等也可以使用这段代码转化
				valueStr = new String(valueStr.getBytes("ISO-8859-1"), "utf-8");
				params.put(name, valueStr);
			}

			//获取支付宝的通知返回参数，可参考技术文档中页面跳转同步通知参数列表(以下仅供参考)//
			//商户订单号

			String out_trade_no = new String(request.getParameter("out_trade_no").getBytes("ISO-8859-1"),"UTF-8");
			//支付宝交易号

			String trade_no = new String(request.getParameter("trade_no").getBytes("ISO-8859-1"),"UTF-8");

			//交易状态
			String trade_status = new String(request.getParameter("trade_status").getBytes("ISO-8859-1"),"UTF-8");

			//获取支付宝的通知返回参数，可参考技术文档中页面跳转同步通知参数列表(以上仅供参考)//
			
			//计算得出通知验证结果
			boolean verify_result = AlipayNotify.verifyMD5(params);
			
			if(verify_result){//验证成功
				if(trade_status.equals("TRADE_FINISHED")){
					boolean result=norderService.pay_success(out_trade_no,trade_no, 1);
					out.print(result);
					out.println("success");	//请不要修改或删除
				} else if (trade_status.equals("TRADE_SUCCESS")){
					boolean result=norderService.pay_success(out_trade_no,trade_no, 1);
					out.print(result);
					out.println("success");	//请不要修改或删除
				}
				//——请根据您的业务逻辑来编写程序（以上代码仅作参考）——
				//////////////////////////////////////////////////////////////////////////////////////////
			}else{//验证失败
				//该页面可做页面美工编辑
				out.println("fail");
			}
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return "redirect:../../wap/shop/orderList";
	}
	
	/**
	 * 微信端的支付宝支付（用到）
	 * @param po
	 * @param model
	 * @return
	 */
	@RequestMapping("topayscore/{sn}")
	public String topayscore(@PathVariable String sn,Model model,HttpServletRequest req){
		
		PaymentLog log=paylogService.queryBySn(sn);
		model.addAttribute("total",0.01);
		model.addAttribute("sn",sn);
		User user=getWapUser();
		Integer is_login=0;
		if(user==null){
			is_login=1;
		}
		model.addAttribute("is_login",is_login);
		return "wap/pay/wxscorealipay";
	}
	
	//跳转至微信端支付宝支付（用到）
	@RequestMapping("wxscorealipayapi")
	public String wxscorealipay() {
		System.out.println("----");
		return "wap/pay/wxscorealipayapi";
	}
	

	@RequestMapping("wxscorenotify_url")
	public String wxscorenotify_url(HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {
			PrintWriter out = response.getWriter();
			Map<String,String> params = new HashMap<String,String>();
			Map requestParams = request.getParameterMap();
			for (Iterator iter = requestParams.keySet().iterator(); iter.hasNext();) {
				String name = (String) iter.next();
				String[] values = (String[]) requestParams.get(name);
				String valueStr = "";
				for (int i = 0; i < values.length; i++) {
					valueStr = (i == values.length - 1) ? valueStr + values[i]
							: valueStr + values[i] + ",";
				}
				//乱码解决，这段代码在出现乱码时使用。如果mysign和sign不相等也可以使用这段代码转化
				valueStr = new String(valueStr.getBytes("ISO-8859-1"), "utf-8");
				params.put(name, valueStr);
			}

			//获取支付宝的通知返回参数，可参考技术文档中页面跳转同步通知参数列表(以下仅供参考)//
			//商户订单号

			String out_trade_no = new String(request.getParameter("out_trade_no").getBytes("ISO-8859-1"),"UTF-8");

			//支付宝交易号

			String trade_no = new String(request.getParameter("trade_no").getBytes("ISO-8859-1"),"UTF-8");
			//交易状态
			String trade_status = new String(request.getParameter("trade_status").getBytes("ISO-8859-1"),"UTF-8");

			//获取支付宝的通知返回参数，可参考技术文档中页面跳转同步通知参数列表(以上仅供参考)//
			
			//计算得出通知验证结果
			boolean verify_result = AlipayNotify.verifyMD5(params);
			
			if(verify_result){//验证成功
				if(trade_status.equals("TRADE_FINISHED")){
					boolean result=scoreService.pay_success(out_trade_no,trade_no, 1);
					out.print(result);
					out.println("success");	//请不要修改或删除
				} else if (trade_status.equals("TRADE_SUCCESS")){
					boolean result=scoreService.pay_success(out_trade_no,trade_no, 1);
					out.print(result);
					out.println("success");	//请不要修改或删除
				}
				out.println("success");	//请不要修改或删除
			}else{//验证失败
				//该页面可做页面美工编辑
				out.println("fail");
			}
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return "redirect:../../wap/my/my";
	}

	@RequestMapping("wxscorereturn_url")
	public String wxscorereturn_url(HttpServletRequest request, HttpServletResponse response) {
		try {
			PrintWriter out = response.getWriter();
			Map<String,String> params = new HashMap<String,String>();
			Map requestParams = request.getParameterMap();
			for (Iterator iter = requestParams.keySet().iterator(); iter.hasNext();) {
				String name = (String) iter.next();
				String[] values = (String[]) requestParams.get(name);
				String valueStr = "";
				for (int i = 0; i < values.length; i++) {
					valueStr = (i == values.length - 1) ? valueStr + values[i]
							: valueStr + values[i] + ",";
				}
				//乱码解决，这段代码在出现乱码时使用。如果mysign和sign不相等也可以使用这段代码转化
				valueStr = new String(valueStr.getBytes("ISO-8859-1"), "utf-8");
				params.put(name, valueStr);
			}

			//获取支付宝的通知返回参数，可参考技术文档中页面跳转同步通知参数列表(以下仅供参考)//
			//商户订单号

			String out_trade_no = new String(request.getParameter("out_trade_no").getBytes("ISO-8859-1"),"UTF-8");
			//支付宝交易号

			String trade_no = new String(request.getParameter("trade_no").getBytes("ISO-8859-1"),"UTF-8");

			//交易状态
			String trade_status = new String(request.getParameter("trade_status").getBytes("ISO-8859-1"),"UTF-8");

			//获取支付宝的通知返回参数，可参考技术文档中页面跳转同步通知参数列表(以上仅供参考)//
			
			//计算得出通知验证结果
			boolean verify_result = AlipayNotify.verifyMD5(params);
			
			if(verify_result){//验证成功
				if(trade_status.equals("TRADE_FINISHED")){
					boolean result=scoreService.pay_success(out_trade_no,trade_no, 1);
					out.print(result);
					out.println("success");	//请不要修改或删除
				} else if (trade_status.equals("TRADE_SUCCESS")){
					boolean result=scoreService.pay_success(out_trade_no,trade_no, 1);
					out.print(result);
					out.println("success");	//请不要修改或删除
				}
				//——请根据您的业务逻辑来编写程序（以上代码仅作参考）——
				//////////////////////////////////////////////////////////////////////////////////////////
			}else{//验证失败
				//该页面可做页面美工编辑
				out.println("fail");
			}
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return "redirect:../../wap/my/my";
	}
	
	/**
	 * 微信端的支付宝支付（用到）
	 * @param po
	 * @param model
	 * @return
	 */
	@RequestMapping("topayupgrade/{ut_sn}")
	public String topayupgrade(@PathVariable String ut_sn,Model model,HttpServletRequest req){
		Uprule uprule=upService.queryone();
		Ticket ticket2=ticketService.queryById(uprule.getTicket_id2());
		model.addAttribute("total",ticket2.getPrice());
		model.addAttribute("total",0.01);
		String order_sn=StringUtil.buildOrderSn();
		model.addAttribute("sn", order_sn);//新产生的订单号
		model.addAttribute("ut_sn",ut_sn);//原始卡的编号
		return "wap/pay/wxupgradealipay";
	}
	
	//跳转至微信端支付宝支付（用到）
	@RequestMapping("wxupgradealipayapi")
	public String wxupgradealipayapi() {
		return "wap/pay/wxupgradealipayapi";
	}
	
	@RequestMapping("wxupgradenotify_url")
	public String wxupgradenotify_url(HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {
			PrintWriter out = response.getWriter();
			Map<String,String> params = new HashMap<String,String>();
			Map requestParams = request.getParameterMap();
			for (Iterator iter = requestParams.keySet().iterator(); iter.hasNext();) {
				String name = (String) iter.next();
				String[] values = (String[]) requestParams.get(name);
				String valueStr = "";
				for (int i = 0; i < values.length; i++) {
					valueStr = (i == values.length - 1) ? valueStr + values[i]
							: valueStr + values[i] + ",";
				}
				//乱码解决，这段代码在出现乱码时使用。如果mysign和sign不相等也可以使用这段代码转化
				valueStr = new String(valueStr.getBytes("ISO-8859-1"), "utf-8");
				params.put(name, valueStr);
			}

			//获取支付宝的通知返回参数，可参考技术文档中页面跳转同步通知参数列表(以下仅供参考)//
			//商户订单号

			String out_trade_no = new String(request.getParameter("out_trade_no").getBytes("ISO-8859-1"),"UTF-8");

			String ut_sn = new String(request.getParameter("body").getBytes("ISO-8859-1"),"UTF-8");

			//支付宝交易号

			String trade_no = new String(request.getParameter("trade_no").getBytes("ISO-8859-1"),"UTF-8");
			//交易状态
			String trade_status = new String(request.getParameter("trade_status").getBytes("ISO-8859-1"),"UTF-8");

			//获取支付宝的通知返回参数，可参考技术文档中页面跳转同步通知参数列表(以上仅供参考)//
			
			//计算得出通知验证结果
			boolean verify_result = AlipayNotify.verifyMD5(params);
			
			if(verify_result){//验证成功
				if(trade_status.equals("TRADE_FINISHED")){
					boolean result=upgradeService.upgrade_success(ut_sn,out_trade_no,trade_no, 1);
					out.print(result);
					out.println("success");	//请不要修改或删除
				} else if (trade_status.equals("TRADE_SUCCESS")){
					boolean result=upgradeService.upgrade_success(ut_sn,out_trade_no,trade_no, 1);
					out.print(result);
					out.println("success");	//请不要修改或删除
				}
				out.println("success");	//请不要修改或删除
			}else{//验证失败
				//该页面可做页面美工编辑
				out.println("fail");
			}
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return "redirect:../../wap/my/my";
	}

	@RequestMapping("wxupgradereturn_url")
	public String wxupgradereturn_url(HttpServletRequest request, HttpServletResponse response) {
		try {
			PrintWriter out = response.getWriter();
			Map<String,String> params = new HashMap<String,String>();
			Map requestParams = request.getParameterMap();
			for (Iterator iter = requestParams.keySet().iterator(); iter.hasNext();) {
				String name = (String) iter.next();
				String[] values = (String[]) requestParams.get(name);
				String valueStr = "";
				for (int i = 0; i < values.length; i++) {
					valueStr = (i == values.length - 1) ? valueStr + values[i]
							: valueStr + values[i] + ",";
				}
				//乱码解决，这段代码在出现乱码时使用。如果mysign和sign不相等也可以使用这段代码转化
				valueStr = new String(valueStr.getBytes("ISO-8859-1"), "utf-8");
				params.put(name, valueStr);
			}

			//获取支付宝的通知返回参数，可参考技术文档中页面跳转同步通知参数列表(以下仅供参考)//
			//商户订单号

			String out_trade_no = new String(request.getParameter("out_trade_no").getBytes("ISO-8859-1"),"UTF-8");
			
			//卡券编号
			String ut_sn = new String(request.getParameter("body").getBytes("ISO-8859-1"),"UTF-8");

			//支付宝交易号
			String trade_no = new String(request.getParameter("trade_no").getBytes("ISO-8859-1"),"UTF-8");

			//交易状态
			String trade_status = new String(request.getParameter("trade_status").getBytes("ISO-8859-1"),"UTF-8");

			//获取支付宝的通知返回参数，可参考技术文档中页面跳转同步通知参数列表(以上仅供参考)//
			
			//计算得出通知验证结果
			boolean verify_result = AlipayNotify.verifyMD5(params);
			
			if(verify_result){//验证成功
				if(trade_status.equals("TRADE_FINISHED")){
					boolean result=upgradeService.upgrade_success(ut_sn,out_trade_no,trade_no, 1);
					out.print(result);
					out.println("success");	//请不要修改或删除
				} else if (trade_status.equals("TRADE_SUCCESS")){
					boolean result=upgradeService.upgrade_success(ut_sn,out_trade_no,trade_no, 1);
					out.print(result);
					out.println("success");	//请不要修改或删除
				}
				//——请根据您的业务逻辑来编写程序（以上代码仅作参考）——
				//////////////////////////////////////////////////////////////////////////////////////////
			}else{//验证失败
				//该页面可做页面美工编辑
				out.println("fail");
			}
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return "redirect:../../wap/my/my";
	}
	
	
	
	
}
