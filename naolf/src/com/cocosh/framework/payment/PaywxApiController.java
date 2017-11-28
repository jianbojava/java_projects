package com.cocosh.framework.payment;

import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cocosh.car.model.PaymentLog;
import com.cocosh.car.service.PaymentLogService;
import com.cocosh.framework.base.BaseController;
import com.cocosh.framework.payment.wxap.HttpsUtil;
import com.cocosh.framework.payment.wxap.WXConfig;
import com.cocosh.framework.payment.wxap.utils.GetWxOrderno;
import com.cocosh.framework.payment.wxap.utils.RequestHandler;
import com.cocosh.framework.payment.wxap.utils.Sha1Util;
import com.cocosh.framework.util.JsonUtil;
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
@RequestMapping("pay/wxpay")
public class PaywxApiController extends BaseController {
	@Autowired
	private NorderService norderService;
	@Autowired
	private PaymentLogService paylogService;
	@Autowired
	private UserScoreInfoService scoreService;
	@Autowired
	private UpruleService upService;
	@Autowired
	private TicketService ticketService;
	@Autowired
	private UserTicketService uticketService;
	@Autowired
	private UpgradeService upgradeService;
	/**
	 * 脑立方订单去支付
	 * @param po
	 * @param model
	 * @return
	 */
	@RequestMapping("topay")
	public String topay(Model model,HttpServletRequest req){
		String orderId=req.getParameter("order_id");
		Norder  po = norderService.queryById(orderId);
		model.addAttribute("total",po.getPrice());
		model.addAttribute("sn", po.getSn());
		model.addAttribute("order_id", orderId);
		model.addAttribute("appid", WXConfig.APPID);
		model.addAttribute("redirect_uri", WXConfig.WX_REDIRECT_URI);
		return "wap/pay/payNorder";
	}
	
	/**
	 * 脑立方订单去支付
	 * @param po
	 * @param model
	 * @return
	 */
	@RequestMapping("paywxorder")
	public String paywxorder(Model model,HttpServletRequest req){
		String orderId=req.getParameter("order_id");
		Norder  po = norderService.queryById(orderId);
		model.addAttribute("total",po.getPrice());
		model.addAttribute("sn", po.getSn());
		model.addAttribute("order_id", orderId);
		String code=req.getParameter("code");
		model.addAttribute("code",code);
		return "wap/pay/paywxorder";
	}
    //(脑立方微信支付用到)
	@RequestMapping("true_pay")
	@ResponseBody
	public Map<String, Object> topay(HttpServletRequest req,HttpServletResponse resp,Model model) throws Exception{
	  System.out.println("true_pay--------------"+req.getParameter("code"));
		Map<String, Object> dataMap=new HashMap<String,Object>();
		String code = req.getParameter("code");
		String url = "https://api.weixin.qq.com/sns/oauth2/access_token?appid="
				+ WXConfig.APPID
				+ "&secret="
				+ WXConfig.APPSECRET
				+ "&code=" + code + "&grant_type=authorization_code";
		System.out.println(code+"---------------");
		String data = HttpsUtil.getDsynSource(url);
		JSONObject rtnJson = JSONObject.fromObject(data);
		String open_id = rtnJson.getString("openid");
		//商户相关资料 
		String partner = WXConfig.PARTNER;
		String partnerkey = WXConfig.PARTENR_KEY;
		System.out.println("openid---------"+open_id);
		if (!StringUtil.isEmpty(open_id)) {
			//获取openId后调用统一支付接口https://api.mch.weixin.qq.com/pay/unifiedorder
			String sn=req.getParameter("out_trade_no");
			//商品描述根据情况修改
    		String body = "购买课程";
			int order_price=1;//支付金额
			Norder norder=norderService.queryBySn(sn);
			Double total = 0.01;
			body = "脑立方，订单号:"+sn;
			order_price=(int)(total*10*10);
    		System.out.println("sb："+sn+"------------------金额:"+order_price);
    		//商户号
    		String mch_id = partner;
    		//子商户号  非必输
            //String sub_mch_id="";
    		//设备号   非必输
    		String device_info="";
    		//随机数 
    		String nonce_str = sn;
    		//附加数据
    		String attach ="";//
    		//商户订单号
    		String out_trade_no = sn;
    		//订单生成的机器 IP
    		String spbill_create_ip = req.getRemoteAddr();
    		
    		//这里notify_url是 支付完成后微信发给该链接信息，可以判断会员是否支付成功，改变订单状态等。
    		String notify_url =WXConfig.NOTIFY_URL;
    		
    		
    		String trade_type = "JSAPI";
    		String openid = open_id;
    		//非必输
    		
    		SortedMap<String, String> packageParams = new TreeMap<String, String>();
    		packageParams.put("appid", WXConfig.APPID);  
    		packageParams.put("mch_id", mch_id);  
    		packageParams.put("nonce_str", nonce_str);  
    		packageParams.put("body", body);  
    		packageParams.put("attach", attach);  
    		packageParams.put("out_trade_no", out_trade_no);  
    		
    		
    		//这里写的金额为1 分到时修改
    		packageParams.put("total_fee", order_price+"");  
    		packageParams.put("spbill_create_ip", spbill_create_ip);  
    		packageParams.put("notify_url", notify_url);  
    		
    		packageParams.put("trade_type", trade_type);  
    		packageParams.put("openid", openid);  
    		System.out.println("packageParams=========================="+JsonUtil.obj2json(packageParams));
    		RequestHandler reqHandler = new RequestHandler(req, resp);
    		reqHandler.init(WXConfig.APPID, WXConfig.APPSECRET, partnerkey);
    		
    		String sign = reqHandler.createSign(packageParams);
    		String xml="<xml>"+
    				"<appid>"+WXConfig.APPID+"</appid>"+
    				"<mch_id>"+mch_id+"</mch_id>"+
    				"<nonce_str>"+nonce_str+"</nonce_str>"+
    				"<sign>"+sign+"</sign>"+
    				"<body><![CDATA["+body+"]]></body>"+
    				"<attach>"+attach+"</attach>"+
    				"<out_trade_no>"+out_trade_no+"</out_trade_no>"+
    				"<attach>"+attach+"</attach>"+
    	//金额，这里写的1 分到时修改
    						"<total_fee>"+order_price+"</total_fee>"+
    				"<spbill_create_ip>"+spbill_create_ip+"</spbill_create_ip>"+
    				"<notify_url>"+notify_url+"</notify_url>"+
    				"<trade_type>"+trade_type+"</trade_type>"+
    				"<openid>"+openid+"</openid>"+
    				"</xml>";
    		System.out.println(xml+"------xm");
    		String allParameters = "";
    		try {
    			allParameters =  reqHandler.genPackage(packageParams);
    		} catch (Exception e) {
    			// TODO Auto-generated catch block
    			e.printStackTrace();
    		}
    		String createOrderURL = "https://api.mch.weixin.qq.com/pay/unifiedorder";
    		Map<String, Object> dataMap2 = new HashMap<String, Object>();
    		String prepay_id="";
    		try {
    			prepay_id = new GetWxOrderno().getPayNo(createOrderURL, xml);
    			if(prepay_id.equals("")){
    				req.setAttribute("ErrorMsg", "统一支付接口获取预支付订单出错");
    				resp.sendRedirect("/payapi/wxpay/err");
    			}
    		} catch (Exception e1) {
    			// TODO Auto-generated catch block
    			e1.printStackTrace();
    		}
    		SortedMap<String, String> finalpackage = new TreeMap<String, String>();
    		String appid2 = WXConfig.APPID;
    		String timestamp = Sha1Util.getTimeStamp();
    		String nonceStr2 = nonce_str;
    		String prepay_id2 = "prepay_id="+prepay_id;
    		String packages = prepay_id2;
    		finalpackage.put("appId", appid2);  
    		finalpackage.put("timeStamp", timestamp);  
    		finalpackage.put("nonceStr", nonceStr2);  
    		finalpackage.put("package", packages);  
    		finalpackage.put("signType", "MD5");
    		String finalsign = reqHandler.createSign(finalpackage);
    		
    		dataMap.put("code", "0");
    		dataMap.put("appId", appid2);
    		dataMap.put("timeStamp", timestamp);
    		dataMap.put("nonceStr", nonceStr2);
    		dataMap.put("packageName", packages);
    		dataMap.put("signType", "MD5");
    		dataMap.put("paySign", finalsign);
    		return dataMap;
		}
		dataMap.put("code", "1");
		dataMap.put("message", "");
		return dataMap;
	}
	
	/**
	 * 脑立方支付成功回调方法(bobo)
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("wxnotify_url")
	public void wxnotify_url(HttpServletRequest request,HttpServletResponse response) throws Exception {
			SortedMap<String, String> packageParams = new TreeMap<String, String>();
//				Map<String, String> map1 = new HashMap<String, String>();  
			InputStream inputStream = request.getInputStream();  
			SAXReader reader = new SAXReader();  
			Document document = reader.read(inputStream);  
			Element root = document.getRootElement();  
			List<Element> elementList = root.elements();  
			for (Element e : elementList){
				packageParams.put(e.getName(), e.getText());
				
			}
			RequestHandler reqHandler = new RequestHandler(request, response);
			
			//获取package包
			reqHandler.init();
			reqHandler.init(WXConfig.APPID, WXConfig.APPSECRET, WXConfig.PARTENR_KEY);
		    String  mySign =reqHandler.createSign(packageParams);
		    
			
			request.setCharacterEncoding("UTF-8");  
	        response.setCharacterEncoding("UTF-8");
	        
	        String return_code = packageParams.get("return_code");
	        String sign = packageParams.get("sign");
	        
	        System.out.println("-------------return_code="+return_code+"-----mySign="+mySign+" ------sign="+sign);
	        if( "SUCCESS".equals(return_code)&&(sign.equals(mySign))){//支付成功 
	        	 String tradeNo = packageParams.get("out_trade_no");//订单支付号
	        	 String transaction_id = packageParams.get("transaction_id");//交易流水号
	        	 norderService.pay_success(tradeNo,transaction_id,2);
	        }else{
	        	System.out.println("-------"+"SUCCESS".equals(return_code)+"--------"+sign.equals(mySign));
	        }
	}
	

	/**
	 * 脑立方积分去购买
	 * @param po
	 * @param model
	 * @return
	 */
	@RequestMapping("topayscore")
	public String topayscore(Model model,HttpServletRequest req){
		return "wap/pay/topayScore";
	}
	/**
	 * 脑立方积分购买
	 * @param po
	 * @param model
	 * @return
	 */
	@RequestMapping("payscore")
	public String payscore(Model model,HttpServletRequest req){
		Double amount=Double.valueOf(req.getParameter("amount"));//输入的金额
		User wapuser=getWapUser();
		String order_sn=StringUtil.buildOrderSn();
		PaymentLog paylog=new PaymentLog(wapuser.getId(), wapuser.getId(), amount, "充值积分", order_sn, null, null, 0, 1, "充值积分");
		paylog.setSn(order_sn);
		paylog.setUser_id(wapuser.getId());
		paylogService.add(paylog);
		model.addAttribute("total",paylog.getAmount());
		model.addAttribute("sn", paylog.getSn());
		model.addAttribute("appid", WXConfig.APPID);
		model.addAttribute("redirect_uri", WXConfig.SCORE_WX_REDIRECT_URI);
		return "wap/pay/payScore";
	}
	
	/**
	 * 微信端的积分充值
	 * @param po
	 * @param model
	 * @return
	 */
	@RequestMapping("paywxscore")
	public String paywxscore(Model model,HttpServletRequest req){
		String sn=req.getParameter("sn");
		PaymentLog log=paylogService.queryBySn(sn);
		model.addAttribute("total",log.getAmount());
		model.addAttribute("sn",sn);
		String code=req.getParameter("code");
		model.addAttribute("code",code);
		model.addAttribute("appid", WXConfig.APPID);
		model.addAttribute("redirect_uri", WXConfig.SCORE_WX_REDIRECT_URI);
		return "wap/pay/paywxscore";
	}
	
    //(脑立方微信充值用到)
	@RequestMapping("true_payscore")
	@ResponseBody
	public Map<String, Object> true_payscore(HttpServletRequest req,HttpServletResponse resp,Model model) throws Exception{
	  System.out.println("true_pay--------------"+req.getParameter("code"));
		Map<String, Object> dataMap=new HashMap<String,Object>();
		String code = req.getParameter("code");
		String url = "https://api.weixin.qq.com/sns/oauth2/access_token?appid="
				+ WXConfig.APPID
				+ "&secret="
				+ WXConfig.APPSECRET
				+ "&code=" + code + "&grant_type=authorization_code";
		System.out.println(code+"---------------");
		String data = HttpsUtil.getDsynSource(url);
		JSONObject rtnJson = JSONObject.fromObject(data);
		String open_id = rtnJson.getString("openid");
		//商户相关资料 
		String partner = WXConfig.PARTNER;
		String partnerkey = WXConfig.PARTENR_KEY;
		System.out.println("openid---------"+open_id);
		if (!StringUtil.isEmpty(open_id)) {
			//获取openId后调用统一支付接口https://api.mch.weixin.qq.com/pay/unifiedorder
			String sn=req.getParameter("out_trade_no");
			//商品描述根据情况修改
    		String body = "积分充值";
			int order_price=1;//支付金额
			PaymentLog log=paylogService.queryBySn(sn);
			Double total = 0.01;
			body = "脑立方，订单号:"+sn;
			order_price=(int)(total*10*10);
    		System.out.println("sb："+sn+"------------------金额:"+order_price);
    		//商户号
    		String mch_id = partner;
    		//子商户号  非必输
            //String sub_mch_id="";
    		//设备号   非必输
    		String device_info="";
    		//随机数 
    		String nonce_str = sn;
    		//附加数据
    		String attach ="";//
    		//商户订单号
    		String out_trade_no = sn;
    		//订单生成的机器 IP
    		String spbill_create_ip = req.getRemoteAddr();
    		
    		//这里notify_url是 支付完成后微信发给该链接信息，可以判断会员是否支付成功，改变订单状态等。
    		String notify_url =WXConfig.SCORE_NOTIFY_URL;
    		
    		
    		String trade_type = "JSAPI";
    		String openid = open_id;
    		//非必输
    		
    		SortedMap<String, String> packageParams = new TreeMap<String, String>();
    		packageParams.put("appid", WXConfig.APPID);  
    		packageParams.put("mch_id", mch_id);  
    		packageParams.put("nonce_str", nonce_str);  
    		packageParams.put("body", body);  
    		packageParams.put("attach", attach);  
    		packageParams.put("out_trade_no", out_trade_no);  
    		
    		
    		//这里写的金额为1 分到时修改
    		packageParams.put("total_fee", order_price+"");  
    		packageParams.put("spbill_create_ip", spbill_create_ip);  
    		packageParams.put("notify_url", notify_url);  
    		
    		packageParams.put("trade_type", trade_type);  
    		packageParams.put("openid", openid);  
    		System.out.println("packageParams=========================="+JsonUtil.obj2json(packageParams));
    		RequestHandler reqHandler = new RequestHandler(req, resp);
    		reqHandler.init(WXConfig.APPID, WXConfig.APPSECRET, partnerkey);
    		
    		String sign = reqHandler.createSign(packageParams);
    		String xml="<xml>"+
    				"<appid>"+WXConfig.APPID+"</appid>"+
    				"<mch_id>"+mch_id+"</mch_id>"+
    				"<nonce_str>"+nonce_str+"</nonce_str>"+
    				"<sign>"+sign+"</sign>"+
    				"<body><![CDATA["+body+"]]></body>"+
    				"<attach>"+attach+"</attach>"+
    				"<out_trade_no>"+out_trade_no+"</out_trade_no>"+
    				"<attach>"+attach+"</attach>"+
    	//金额，这里写的1 分到时修改
    						"<total_fee>"+order_price+"</total_fee>"+
    				"<spbill_create_ip>"+spbill_create_ip+"</spbill_create_ip>"+
    				"<notify_url>"+notify_url+"</notify_url>"+
    				"<trade_type>"+trade_type+"</trade_type>"+
    				"<openid>"+openid+"</openid>"+
    				"</xml>";
    		System.out.println(xml+"------xm");
    		String allParameters = "";
    		try {
    			allParameters =  reqHandler.genPackage(packageParams);
    		} catch (Exception e) {
    			// TODO Auto-generated catch block
    			e.printStackTrace();
    		}
    		String createOrderURL = "https://api.mch.weixin.qq.com/pay/unifiedorder";
    		Map<String, Object> dataMap2 = new HashMap<String, Object>();
    		String prepay_id="";
    		try {
    			prepay_id = new GetWxOrderno().getPayNo(createOrderURL, xml);
    			if(prepay_id.equals("")){
    				req.setAttribute("ErrorMsg", "统一支付接口获取预支付订单出错");
    				resp.sendRedirect("/payapi/wxpay/err");
    			}
    		} catch (Exception e1) {
    			// TODO Auto-generated catch block
    			e1.printStackTrace();
    		}
    		SortedMap<String, String> finalpackage = new TreeMap<String, String>();
    		String appid2 = WXConfig.APPID;
    		String timestamp = Sha1Util.getTimeStamp();
    		String nonceStr2 = nonce_str;
    		String prepay_id2 = "prepay_id="+prepay_id;
    		String packages = prepay_id2;
    		finalpackage.put("appId", appid2);  
    		finalpackage.put("timeStamp", timestamp);  
    		finalpackage.put("nonceStr", nonceStr2);  
    		finalpackage.put("package", packages);  
    		finalpackage.put("signType", "MD5");
    		String finalsign = reqHandler.createSign(finalpackage);
    		
    		dataMap.put("code", "0");
    		dataMap.put("appId", appid2);
    		dataMap.put("timeStamp", timestamp);
    		dataMap.put("nonceStr", nonceStr2);
    		dataMap.put("packageName", packages);
    		dataMap.put("signType", "MD5");
    		dataMap.put("paySign", finalsign);
    		return dataMap;
		}
		dataMap.put("code", "1");
		dataMap.put("message", "");
		return dataMap;
	}
	
	/**
	 * 脑立方支付充值成功回调方法(bobo)
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("wxnotifyscore_url")
	public void wxnotifyscore_url(HttpServletRequest request,HttpServletResponse response) throws Exception {
			SortedMap<String, String> packageParams = new TreeMap<String, String>();
//					Map<String, String> map1 = new HashMap<String, String>();  
			InputStream inputStream = request.getInputStream();  
			SAXReader reader = new SAXReader();  
			Document document = reader.read(inputStream);  
			Element root = document.getRootElement();  
			List<Element> elementList = root.elements();  
			for (Element e : elementList){
				packageParams.put(e.getName(), e.getText());
				
			}
			RequestHandler reqHandler = new RequestHandler(request, response);
			
			//获取package包
			reqHandler.init();
			reqHandler.init(WXConfig.APPID, WXConfig.APPSECRET, WXConfig.PARTENR_KEY);
		    String  mySign =reqHandler.createSign(packageParams);
		    
			
			request.setCharacterEncoding("UTF-8");  
	        response.setCharacterEncoding("UTF-8");
	        
	        String return_code = packageParams.get("return_code");
	        String sign = packageParams.get("sign");
	        
	        System.out.println("-------------return_code="+return_code+"-----mySign="+mySign+" ------sign="+sign);
	        if( "SUCCESS".equals(return_code)&&(sign.equals(mySign))){//支付成功 
	        	 String tradeNo = packageParams.get("out_trade_no");//订单支付号
	        	 String transaction_id = packageParams.get("transaction_id");//交易流水号
	        	 scoreService.pay_success(tradeNo,transaction_id,2);
	        }else{
	        	System.out.println("-------"+"SUCCESS".equals(return_code)+"--------"+sign.equals(mySign));
	        }
	}
	
	
	
	/**
	 * 微信端的升级支付
	 * @param po
	 * @param model
	 * @return
	 */
	@RequestMapping("paywxupgrade")
	public String paywxupgrade(Model model,HttpServletRequest req){
		String ut_id=req.getParameter("ut_id");
		UserTicket ut=uticketService.queryById(ut_id);
		Uprule uprule=upService.queryone();
		Ticket ticket2=ticketService.queryById(uprule.getTicket_id2());
		model.addAttribute("total",ticket2.getPrice());
		model.addAttribute("sn", ut.getSn());
		String code=req.getParameter("code");
		model.addAttribute("code",code);
		model.addAttribute("ut_id", ut_id);
		return "wap/pay/paywxupgrade";
	}
	
    //(脑立方微信卡券升级用到)
	@RequestMapping("true_payupgrade")
	@ResponseBody
	public Map<String, Object> true_payupgrade(HttpServletRequest req,HttpServletResponse resp,Model model) throws Exception{
	  System.out.println("true_pay--------------"+req.getParameter("code")+"-----mytest"+req.getParameter("mytest"));
		Map<String, Object> dataMap=new HashMap<String,Object>();
		String code = req.getParameter("code");
		String url = "https://api.weixin.qq.com/sns/oauth2/access_token?appid="
				+ WXConfig.APPID
				+ "&secret="
				+ WXConfig.APPSECRET
				+ "&code=" + code + "&grant_type=authorization_code";
		System.out.println(code+"---------------");
		String data = HttpsUtil.getDsynSource(url);
		JSONObject rtnJson = JSONObject.fromObject(data);
		String open_id = rtnJson.getString("openid");
		//商户相关资料 
		String partner = WXConfig.PARTNER;
		String partnerkey = WXConfig.PARTENR_KEY;
		System.out.println("openid---------"+open_id);
		if (!StringUtil.isEmpty(open_id)) {
			//获取openId后调用统一支付接口https://api.mch.weixin.qq.com/pay/unifiedorder
			//得到的是卡券编号
			String sn=req.getParameter("out_trade_no");
			
			Uprule uprule=upService.queryone();
			Ticket ticket2=ticketService.queryById(uprule.getTicket_id2());
			//商品描述根据情况修改
    		String body = "卡券升级";
			int order_price=1;//支付金额
			Double total = 0.01;
			body = "脑立方，卡券号:"+sn;
			order_price=(int)(total*10*10);
    		System.out.println("sb："+sn+"------------------金额:"+order_price);
    		//商户号
    		String mch_id = partner;
    		//子商户号  非必输
            //String sub_mch_id="";
    		//设备号   非必输
    		String device_info="";
    		//随机数 
    		String nonce_str = sn;
    		//附加数据
    		String attach =sn;//
    		//商户订单号
    		String out_trade_no = StringUtil.buildOrderSn();
    		//订单生成的机器 IP
    		String spbill_create_ip = req.getRemoteAddr();
    		
    		//这里notify_url是 支付完成后微信发给该链接信息，可以判断会员是否支付成功，改变订单状态等。
    		String notify_url =WXConfig.UPGRADE_NOTIFY_URL;
    		
    		
    		String trade_type = "JSAPI";
    		String openid = open_id;
    		//非必输
    		
    		SortedMap<String, String> packageParams = new TreeMap<String, String>();
    		packageParams.put("appid", WXConfig.APPID);  
    		packageParams.put("mch_id", mch_id);  
    		packageParams.put("nonce_str", nonce_str);  
    		packageParams.put("body", body);  
    		packageParams.put("attach", attach);  
    		packageParams.put("out_trade_no", out_trade_no);  
    		
    		
    		//这里写的金额为1 分到时修改
    		packageParams.put("total_fee", order_price+"");  
    		packageParams.put("spbill_create_ip", spbill_create_ip);  
    		packageParams.put("notify_url", notify_url);  
    		
    		packageParams.put("trade_type", trade_type);  
    		packageParams.put("openid", openid);  
    		System.out.println("packageParams=========================="+JsonUtil.obj2json(packageParams));
    		RequestHandler reqHandler = new RequestHandler(req, resp);
    		reqHandler.init(WXConfig.APPID, WXConfig.APPSECRET, partnerkey);
    		
    		String sign = reqHandler.createSign(packageParams);
    		String xml="<xml>"+
    				"<appid>"+WXConfig.APPID+"</appid>"+
    				"<mch_id>"+mch_id+"</mch_id>"+
    				"<nonce_str>"+nonce_str+"</nonce_str>"+
    				"<sign>"+sign+"</sign>"+
    				"<body><![CDATA["+body+"]]></body>"+
    				"<attach>"+attach+"</attach>"+
    				"<out_trade_no>"+out_trade_no+"</out_trade_no>"+
    				"<attach>"+attach+"</attach>"+
    	//金额，这里写的1 分到时修改
    						"<total_fee>"+order_price+"</total_fee>"+
    				"<spbill_create_ip>"+spbill_create_ip+"</spbill_create_ip>"+
    				"<notify_url>"+notify_url+"</notify_url>"+
    				"<trade_type>"+trade_type+"</trade_type>"+
    				"<openid>"+openid+"</openid>"+
    				"</xml>";
    		System.out.println(xml+"------xm");
    		String allParameters = "";
    		try {
    			allParameters =  reqHandler.genPackage(packageParams);
    		} catch (Exception e) {
    			// TODO Auto-generated catch block
    			e.printStackTrace();
    		}
    		String createOrderURL = "https://api.mch.weixin.qq.com/pay/unifiedorder";
    		Map<String, Object> dataMap2 = new HashMap<String, Object>();
    		String prepay_id="";
    		try {
    			prepay_id = new GetWxOrderno().getPayNo(createOrderURL, xml);
    			if(prepay_id.equals("")){
    				req.setAttribute("ErrorMsg", "统一支付接口获取预支付订单出错");
    				resp.sendRedirect("/payapi/wxpay/err");
    			}
    		} catch (Exception e1) {
    			// TODO Auto-generated catch block
    			e1.printStackTrace();
    		}
    		SortedMap<String, String> finalpackage = new TreeMap<String, String>();
    		String appid2 = WXConfig.APPID;
    		String timestamp = Sha1Util.getTimeStamp();
    		String nonceStr2 = nonce_str;
    		String prepay_id2 = "prepay_id="+prepay_id;
    		String packages = prepay_id2;
    		finalpackage.put("appId", appid2);  
    		finalpackage.put("timeStamp", timestamp);  
    		finalpackage.put("nonceStr", nonceStr2);  
    		finalpackage.put("package", packages);  
    		finalpackage.put("signType", "MD5");
    		String finalsign = reqHandler.createSign(finalpackage);
    		
    		dataMap.put("code", "0");
    		dataMap.put("appId", appid2);
    		dataMap.put("timeStamp", timestamp);
    		dataMap.put("nonceStr", nonceStr2);
    		dataMap.put("packageName", packages);
    		dataMap.put("signType", "MD5");
    		dataMap.put("paySign", finalsign);
    		return dataMap;
		}
		dataMap.put("code", "1");
		dataMap.put("message", "");
		return dataMap;
	}
	
	/**
	 * 脑立方升级支付成功回调方法(bobo)
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("wxnotifyupgrade_url")
	public void wxnotifyupgrade_url(HttpServletRequest request,HttpServletResponse response) throws Exception {
			SortedMap<String, String> packageParams = new TreeMap<String, String>();
//					Map<String, String> map1 = new HashMap<String, String>();  
			InputStream inputStream = request.getInputStream();  
			SAXReader reader = new SAXReader();  
			Document document = reader.read(inputStream);  
			Element root = document.getRootElement();  
			List<Element> elementList = root.elements();  
			for (Element e : elementList){
				packageParams.put(e.getName(), e.getText());
				
			}
			RequestHandler reqHandler = new RequestHandler(request, response);
			
			//获取package包
			reqHandler.init();
			reqHandler.init(WXConfig.APPID, WXConfig.APPSECRET, WXConfig.PARTENR_KEY);
		    String  mySign =reqHandler.createSign(packageParams);
		    
			
			request.setCharacterEncoding("UTF-8");  
	        response.setCharacterEncoding("UTF-8");
	        
	        String return_code = packageParams.get("return_code");
	        String sign = packageParams.get("sign");
	        
	        System.out.println("-------------return_code="+return_code+"-----mySign="+mySign+" ------sign="+sign);
	        if( "SUCCESS".equals(return_code)&&(sign.equals(mySign))){//支付成功 
	        	 String tradeNo = packageParams.get("out_trade_no");//订单支付号
	        	 String transaction_id = packageParams.get("transaction_id");//交易流水号
	        	 String ut_sn=packageParams.get("attach");
	        	 System.out.println("ut_sn===================="+ut_sn);
	        	 upgradeService.upgrade_success(ut_sn,tradeNo,transaction_id,2);
	        }else{
	        	System.out.println("-------"+"SUCCESS".equals(return_code)+"--------"+sign.equals(mySign));
	        }
	}
}
