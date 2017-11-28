package com.cocosh.api.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cocosh.car.service.OrderService;
import com.cocosh.framework.base.BaseController;
import com.cocosh.member.model.Member;
import com.cocosh.member.service.MemberService;

@Controller
@RequestMapping("api/evcard")
public class EvcardApiController extends BaseController {
	private final Logger logger = Logger.getLogger(EvcardApiController.class);
	private static final String APPKEY = "evcard";
	private static final String APPSECRET = "bc3d1f6e";
	Map<String, Object> dataMap = null;

	@Autowired
	private MemberService memberService;
	@Autowired
	private OrderService orderService;

	@RequestMapping("userInfo")
	@ResponseBody
	public Map<String, Object> userInfo(HttpServletRequest req){
		logger.info("========evcard member start========");
		dataMap = new HashMap<String, Object>();
		String AppKey = req.getHeader("AppKey");
		String TimeStamp = req.getHeader("TimeStamp");
		String sn = req.getHeader("sn");
		String token = req.getParameter("token");
		String md5 = DigestUtils.md5Hex(APPKEY + APPSECRET + TimeStamp);
		if (md5.equalsIgnoreCase(sn)) {
			logger.info("========evcard member valid========");
			Member member = memberService.queryByCredential(token);
			if (member!=null) {
				logger.info("========evcard member success========");
				dataMap.put("mobileNo", member.getMobile());
				dataMap.put("name",member.getName());
				dataMap.put("memID", member.getCard_no());
				dataMap.put("licenseCopyUrl", member.getDriver_scan());
				dataMap.put("address", member.getShip_addr());
				dataMap.put("memCardID",member.getCard_number());
				dataMap.put("message","ok");
				return dataMap;
			}
			dataMap.put("message", "用户不存在");
			return dataMap;
		}
		dataMap.put("message", "签名失效");
		return dataMap;
	}
	
	@RequestMapping("order")
	@ResponseBody
	public Map<String, Object> order(HttpServletRequest req){
		logger.info("========evcard order start========");
		dataMap = new HashMap<String, Object>();
		String AppKey = req.getHeader("AppKey");
		String TimeStamp = req.getHeader("TimeStamp");
		String sn = req.getHeader("sn");
		String md5 = DigestUtils.md5Hex(APPKEY + APPSECRET + TimeStamp);
		if (md5.equalsIgnoreCase(sn)) {
			logger.info("========evcard order valid========");
			//添加订单
			if (orderService.addEvcardOrder(req)) {
				logger.info("========evcard order success========");
				dataMap.put("message", "success");
				return dataMap;
			}
		}
		dataMap.put("message", "签名失效");
		return dataMap;
	}
	
}
