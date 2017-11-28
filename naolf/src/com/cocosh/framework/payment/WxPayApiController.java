package com.cocosh.framework.payment;

import java.io.InputStream;
import java.util.List;
import java.util.SortedMap;
import java.util.TreeMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.cocosh.car.service.ChargeOrderService;
import com.cocosh.car.service.OrderService;
import com.cocosh.framework.base.BaseController;
import com.cocosh.framework.payment.wxap.WXConfig;
import com.cocosh.framework.payment.wxap.utils.RequestHandler;

@Controller
@RequestMapping("payment/wxpay")
public class WxPayApiController extends BaseController {
	@Autowired
	private OrderService service;
	@Autowired
	private ChargeOrderService chargeOrderService;
	
	/**
	 * 微信支付app端回调(订单)
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping(value="order_notify")
	public void order_notify(HttpServletRequest request,HttpServletResponse response) throws Exception { 
		//设置package订单参数
		SortedMap<String, String> packageParams = new TreeMap<String, String>();
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
		reqHandler.init(WXConfig.APPID, WXConfig.APPSECRET, WXConfig.PARTENR_KEY_APP);
	    
	    String  mySign =reqHandler.createSign(packageParams);
	   
		request.setCharacterEncoding("UTF-8");
	    response.setCharacterEncoding("UTF-8");
	    
	    String return_code = packageParams.get("return_code");
	    String sign = packageParams.get("sign");
	    
	    if( "SUCCESS".equals(return_code)&&(sign.equals(mySign))){//支付成功 
	    	String out_trade_no = packageParams.get("out_trade_no");//订单号
	    	String transaction_id = packageParams.get("transaction_id");//交易流水号
	    	service.orderNotify(out_trade_no,transaction_id,2);
	    }
	}
	
	/**
	 * 微信支付app端回调(保证金充值)
	 * @param request
	 * @param response
	 */
	@RequestMapping(value="deposit_notify")
	public void deposit_notify(HttpServletRequest request,HttpServletResponse response) throws Exception { 
		//设置package订单参数
		SortedMap<String, String> packageParams = new TreeMap<String, String>();
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
		reqHandler.init(WXConfig.APPID, WXConfig.APPSECRET, WXConfig.PARTENR_KEY_APP);
	    
	    String  mySign =reqHandler.createSign(packageParams);
	   
		request.setCharacterEncoding("UTF-8");
	    response.setCharacterEncoding("UTF-8");
	    
	    String return_code = packageParams.get("return_code");
	    String sign = packageParams.get("sign");
	    
	    if( "SUCCESS".equals(return_code)&&(sign.equals(mySign))){//支付成功 
	    	String out_trade_no = packageParams.get("out_trade_no");//订单号
	    	String transaction_id = packageParams.get("transaction_id");//交易流水号
	    	service.depositNotify(out_trade_no,transaction_id);
	    }
	}
	
	
	/**
	 * 微信支付app端回调(余额充值)
	 * @param request
	 * @param response
	 */
	@RequestMapping(value="account_notify")
	public void account_notify(HttpServletRequest request,HttpServletResponse response) throws Exception { 
		//设置package订单参数
		SortedMap<String, String> packageParams = new TreeMap<String, String>();
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
		reqHandler.init(WXConfig.APPID, WXConfig.APPSECRET, WXConfig.PARTENR_KEY_APP);
	    
	    String  mySign =reqHandler.createSign(packageParams);
	   
		request.setCharacterEncoding("UTF-8");
	    response.setCharacterEncoding("UTF-8");
	    
	    String return_code = packageParams.get("return_code");
	    String sign = packageParams.get("sign");
	    
	    if( "SUCCESS".equals(return_code)&&(sign.equals(mySign))){//支付成功 
	    	String out_trade_no = packageParams.get("out_trade_no");//订单号
	    	String transaction_id = packageParams.get("transaction_id");//交易流水号
	    	service.accountNotify(out_trade_no,transaction_id);
	    }
	}
	
	/**
	 * 微信支付app端回调(订单)
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping(value="charge_notify")
	public void charge_notify(HttpServletRequest request,HttpServletResponse response) throws Exception { 
		//设置package订单参数
		SortedMap<String, String> packageParams = new TreeMap<String, String>();
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
		reqHandler.init(WXConfig.APPID, WXConfig.APPSECRET, WXConfig.PARTENR_KEY_APP);
	    
	    String  mySign =reqHandler.createSign(packageParams);
	   
		request.setCharacterEncoding("UTF-8");
	    response.setCharacterEncoding("UTF-8");
	    
	    String return_code = packageParams.get("return_code");
	    String sign = packageParams.get("sign");
	    
	    if( "SUCCESS".equals(return_code)&&(sign.equals(mySign))){//支付成功 
	    	String out_trade_no = packageParams.get("out_trade_no");//订单号
	    	String transaction_id = packageParams.get("transaction_id");//交易流水号
	    	chargeOrderService.orderNotify(out_trade_no,transaction_id,2);
	    }
	}

}
