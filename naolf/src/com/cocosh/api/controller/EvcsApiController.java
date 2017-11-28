package com.cocosh.api.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.cocosh.car.model.ChargeOrder;
import com.cocosh.car.service.ChargeOrderService;
import com.cocosh.car.service.ConnectorInfoService;
import com.cocosh.framework.util.AESUtil;
import com.cocosh.framework.util.CommonUtil;
import com.cocosh.framework.util.HttpUtil;

@RequestMapping("evcs/v20160701")
@Controller
public class EvcsApiController {
	private final Logger logger = Logger.getLogger(EvcsApiController.class);
	Map<String, Object> dataMap = null;
	@Autowired
	private ChargeOrderService chargeOrderService;
	@Autowired
	private ConnectorInfoService connectorService;
	
	/**
	 * 获取token
	 */
	@RequestMapping(value="query_token",method=RequestMethod.POST)
	@ResponseBody
	public JSONObject query_token(HttpServletRequest req){
		logger.info("========query_token start========");
		String dataJson=HttpUtil.getReqBody(req);
		chargeOrderService.add(new ChargeOrder("query_token:"+dataJson));
		JSONObject temp=JSONObject.parseObject(dataJson);
		JSONObject resultJson=new JSONObject();
		resultJson.put("SuccStat",0);
		resultJson.put("OperatorID",temp.getString("OperatorID"));
		resultJson.put("AccessToken","8MYhohKETDwxEtfLpbkhN1lvU4NRrQCNfLgUpqueoJmUuS0sMcPKa1W3S2tqz5Dy");
		resultJson.put("TokenAvailableTime",7200);
		resultJson.put("FailReason", 0);
		JSONObject object=new JSONObject();
		object.put("Data",AESUtil.Encrypt(resultJson.toString(),CommonUtil.OperatorSecret));
		object.put("Msg", "");
		object.put("Ret", 0);
		object.put("Sig",temp.getString("Sig"));
		logger.info("========query_token end========");	
		return object;
	}

	/**
	 * 设备状态变化推送
	 */
	@RequestMapping(value="notification_stationStatus",method=RequestMethod.POST)
	@ResponseBody
	public JSONObject notification_stationStatus(HttpServletRequest req) {
		logger.info("========notification_stationStatus start========");		
		String dataJson = HttpUtil.getReqBody(req);
		System.out.println(dataJson);
		chargeOrderService.add(new ChargeOrder("notification_stationStatus:"+dataJson));
		//更新接口状态
		JSONObject retJson=JSONObject.parseObject(dataJson);
		JSONObject dataJsonObject=JSON.parseObject(AESUtil.Decrypt(retJson.getString("Data"),CommonUtil.OperatorSecret));
		connectorService.updateStatus(dataJsonObject.getJSONObject("ConnectorStatusInfo").getString("ConnectorID"), dataJsonObject.getJSONObject("ConnectorStatusInfo").getInteger("Status"));
		//返回结果
		JSONObject resultJson=new JSONObject();
		resultJson.put("Status",0);
		JSONObject object=new JSONObject();
		object.put("Data",AESUtil.Encrypt(resultJson.toString(),CommonUtil.OperatorSecret));
		object.put("Msg", "");
		object.put("Ret", 0);
		object.put("Sig", retJson.getString("Sig"));
		logger.info("========notification_stationStatus end========");
		return object;
	}
	
	/**
	 * 推送启动充电结果
	 */
	@RequestMapping(value="notification_start_charge_result",method=RequestMethod.POST)
	@ResponseBody
	public JSONObject notification_start_charge_result(HttpServletRequest req) {
		logger.info("========notification_start_charge_result start========");		
		String dataJson =HttpUtil.getReqBody(req);
		System.out.println(dataJson);
		chargeOrderService.add(new ChargeOrder("notification_start_charge_result:"+dataJson));
		//处理推送数据
		JSONObject retJson=JSONObject.parseObject(dataJson);
		JSONObject dataJsonObject=JSON.parseObject(AESUtil.Decrypt(retJson.getString("Data"),CommonUtil.OperatorSecret));
		//返回结果
		JSONObject resultJson=new JSONObject();
		resultJson.put("SuccStat",0);
		resultJson.put("FailReason", 0);
		resultJson.put("StartChargeSeq", dataJsonObject.getString("StartChargeSeq"));
		JSONObject object=new JSONObject();
		object.put("Data",AESUtil.Encrypt(resultJson.toString(),CommonUtil.OperatorSecret));
		object.put("Msg", "");
		object.put("Ret", 0);
		object.put("Sig", retJson.getString("Sig"));
		logger.info("========notification_start_charge_result end========");		
		return object;
	}
	
	/**
	 * 推送停止充电结果
	 */
	@RequestMapping(value="notification_stop_charge_result",method=RequestMethod.POST)
	@ResponseBody
	public JSONObject notification_stop_charge_result(HttpServletRequest req) {
		logger.info("========notification_stop_charge_result start========");		
		String dataJson = HttpUtil.getReqBody(req);
		System.out.println(dataJson);
		chargeOrderService.add(new ChargeOrder("notification_stop_charge_result:"+dataJson));
		//处理推送数据
		JSONObject retJson=JSONObject.parseObject(dataJson);
		JSONObject dataJsonObject=JSON.parseObject(AESUtil.Decrypt(retJson.getString("Data"),CommonUtil.OperatorSecret));
		//返回结果
		JSONObject resultJson=new JSONObject();
		resultJson.put("SuccStat",0);
		resultJson.put("FailReason", 0);
		resultJson.put("StartChargeSeq", dataJsonObject.getString("StartChargeSeq"));
		JSONObject object=new JSONObject();
		object.put("Data",AESUtil.Encrypt(resultJson.toString(),CommonUtil.OperatorSecret));
		object.put("Msg", "");
		object.put("Ret", 0);
		object.put("Sig", retJson.getString("Sig"));
		logger.info("========notification_stop_charge_result end========");		
		return object;
	}
	
	
	/**
	 * 推送充电订单信息
	 */
	@RequestMapping(value="notification_charge_order_info",method=RequestMethod.POST)
	@ResponseBody
	public JSONObject notification_charge_order_info(HttpServletRequest req) {
		logger.info("========notification_charge_order_info start========");		
		String dataJson = HttpUtil.getReqBody(req);
		System.out.println(dataJson);
		chargeOrderService.add(new ChargeOrder("notification_charge_order_info:"+dataJson));
		//处理订单
		JSONObject retJson=JSONObject.parseObject(dataJson);
		JSONObject dataJsonObject=JSON.parseObject(AESUtil.Decrypt(retJson.getString("Data"),CommonUtil.OperatorSecret));
		chargeOrderService.notifyUpdate(dataJsonObject);
		//返回结果
		JSONObject resultJson=new JSONObject();
		resultJson.put("StartChargeSeq",dataJsonObject.getString("StartChargeSeq"));
		resultJson.put("ConnectorID", dataJsonObject.getString("ConnectorID"));
		resultJson.put("ConfirmResult",0);
		JSONObject object=new JSONObject();
		object.put("Data",AESUtil.Encrypt(resultJson.toString(),CommonUtil.OperatorSecret));
		object.put("Msg", "");
		object.put("Ret", 0);
		object.put("Sig", retJson.getString("Sig"));
		logger.info("========notification_charge_order_info end========");
		return object;
	}
	
	/**
	 * 推送充电状态
	 */
	@RequestMapping(value="notification_equip_charge_status",method=RequestMethod.POST)
	@ResponseBody
	public JSONObject notification_equip_charge_status(HttpServletRequest req) {
		logger.info("========notification_equip_charge_status start========");		
		String dataJson = HttpUtil.getReqBody(req);
		System.out.println(dataJson);
		chargeOrderService.add(new ChargeOrder("notification_equip_charge_status:"+dataJson));
		//处理推送数据
		JSONObject retJson=JSONObject.parseObject(dataJson);
		JSONObject dataJsonObject=JSON.parseObject(AESUtil.Decrypt(retJson.getString("Data"),CommonUtil.OperatorSecret));
		//返回结果
		JSONObject resultJson=new JSONObject();
		resultJson.put("StartChargeSeq",dataJsonObject.getString("StartChargeSeq"));
		resultJson.put("SuccStat",0);
		JSONObject object=new JSONObject();
		object.put("Data",AESUtil.Encrypt(resultJson.toString(),CommonUtil.OperatorSecret));
		object.put("Msg", "");
		object.put("Ret", 0);
		object.put("Sig", retJson.getString("Sig"));
		logger.info("========notification_equip_charge_status end========");
		return object;
	}
	
	/**
	 * 推送订单对账结果信息
	 */
	@RequestMapping(value="check_charge_orders",method=RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> check_charge_orders(HttpServletRequest req) {
		logger.info("========check_charge_orders start========");		
		String dataJson = HttpUtil.getReqBody(req);
		System.out.println(dataJson);
		chargeOrderService.add(new ChargeOrder("check_charge_orders:"+dataJson));
		logger.info("========check_charge_orders end========");		
		dataMap = new HashMap<String, Object>();
		dataMap.put("Status",0);
		return dataMap;
	}
}
