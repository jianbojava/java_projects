package com.cocosh.api.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.cocosh.car.model.ChargeOrder;
import com.cocosh.car.model.ChargeOrderDetail;
import com.cocosh.car.model.ConnectorInfo;
import com.cocosh.car.model.StationInfo;
import com.cocosh.car.service.ChargeOrderService;
import com.cocosh.car.service.ChargeService;
import com.cocosh.car.service.ConnectorInfoService;
import com.cocosh.car.service.StationInfoService;
import com.cocosh.framework.base.AjaxResult;
import com.cocosh.framework.base.BaseConditionVO;
import com.cocosh.framework.base.BaseController;
import com.cocosh.framework.payment.alipay.config.AlipayConfig;
import com.cocosh.framework.payment.wxap.WXConfig;
import com.cocosh.framework.util.CommonUtil;
import com.cocosh.framework.util.HttpUtil;
import com.cocosh.framework.util.StringUtil;
import com.cocosh.member.model.Member;
import com.cocosh.member.service.MemberService;
import com.cocosh.sys.service.DictService;

@RequestMapping("api/charge")
@Controller
public class ChargeApiController extends BaseController {
	Map<String, Object> dataMap = null;
	@Autowired
	private StationInfoService stationService;
	@Autowired
	private ConnectorInfoService connectorService;
	@Autowired
	private MemberService memberService;
	@Autowired
	private ChargeService chargeService;
	@Autowired
	private DictService dictService;
	@Autowired
	private ChargeOrderService chargeOrderService;
	
	/**
	 * 充电站列表
	 */
	@RequestMapping("stationMap")
	@ResponseBody
	public AjaxResult stationMap(BaseConditionVO vo){
		dataMap = new HashMap<String, Object>();
		String [] securityArray = {"timeStamp"};
		String summaryKey = CommonUtil.SECRET_KEY + CommonUtil.reflectMethod(vo, securityArray);
		String summaryMD5 = DigestUtils.md5Hex(summaryKey);
		if(CommonUtil.dataValidate(vo.getSummary(), summaryMD5)){
			List<StationInfo> stations=stationService.query2Map(vo);
			String [] excludes = {"address","areaCode","busineHours","construction","countryCode","distances","electricityFee","enabled","equipmentOwnerID","matchCars","operatorID","elePrice","serPrice",
					"parkFee","parkInfo","parkNums","payment","pictures","remark","serviceFee","serviceTel","siteGuide","stationTel","stationType","supportOrder","type"};
			dataMap.put("stations",JSONArray.fromObject(stations,CommonUtil.createExcludes(excludes)));
			return new AjaxResult(CommonUtil.SUCCESS_CODE,CommonUtil.SUCCESS_MSG,dataMap);
		}
		return new AjaxResult(CommonUtil.ERROR_CODE,CommonUtil.ERROR_PARAMS,dataMap);
	}
	
	public static void testStationMap() throws Exception{
		String requestUrl =CommonUtil.SERVERDOMAIN+"/api/charge/stationMap";
		String status="50",timeStamp = StringUtil.getTimeStamp();
		String summaryKey = CommonUtil.SECRET_KEY +timeStamp;
		String summary=DigestUtils.md5Hex(summaryKey);
		String resultStr = HttpUtil.connectURL(requestUrl, "status="+status+"&timeStamp="+timeStamp+"&summary="+summary);
		System.out.println(resultStr);
	}
	
	/**
	 * 充电站列表
	 */
	@RequestMapping("stationList")
	@ResponseBody
	public AjaxResult stationList(BaseConditionVO vo){
		dataMap = new HashMap<String, Object>();
		String [] securityArray = {"timeStamp"};
		String summaryKey = CommonUtil.SECRET_KEY + CommonUtil.reflectMethod(vo, securityArray);
		String summaryMD5 = DigestUtils.md5Hex(summaryKey);
		if(CommonUtil.dataValidate(vo.getSummary(), summaryMD5)){
			List<StationInfo> stations=stationService.queryPageApp(vo);
			String [] excludes = {"areaCode","busineHours","construction","countryCode","electricityFee","enabled","equipmentOwnerID","matchCars","operatorID","serPrice",
					"parkFee","parkInfo","parkNums","payment","remark","serviceFee","serviceTel","siteGuide","stationTel","supportOrder"};
			dataMap.put("stations",JSONArray.fromObject(stations,CommonUtil.createExcludes(excludes)));
			return new AjaxResult(CommonUtil.SUCCESS_CODE,CommonUtil.SUCCESS_MSG,dataMap);
		}
		return new AjaxResult(CommonUtil.ERROR_CODE,CommonUtil.ERROR_PARAMS,dataMap);
	}
	
	public static void testStationList() throws Exception{
		String requestUrl =CommonUtil.SERVERDOMAIN+"/api/charge/stationList";
		String pageNo="2",timeStamp = StringUtil.getTimeStamp();
		String summaryKey = CommonUtil.SECRET_KEY +timeStamp;
		String summary=DigestUtils.md5Hex(summaryKey);
		String resultStr = HttpUtil.connectURL(requestUrl, "pageNo="+pageNo+"&timeStamp="+timeStamp+"&summary="+summary);
		System.out.println(resultStr);
	}
	
	/**
	 * 充电站筛选
	 */
	@RequestMapping("stationCondition")
	@ResponseBody
	public AjaxResult stationCondition(BaseConditionVO vo){
		dataMap = new HashMap<String, Object>();
		String [] securityArray = {"timeStamp"};
		String summaryKey = CommonUtil.SECRET_KEY + CommonUtil.reflectMethod(vo, securityArray);
		String summaryMD5 = DigestUtils.md5Hex(summaryKey);
		if(CommonUtil.dataValidate(vo.getSummary(), summaryMD5)){
			com.alibaba.fastjson.JSONArray dataArray=new com.alibaba.fastjson.JSONArray();
			
			JSONObject statusObject=new JSONObject();
			statusObject.put("key","status");
			statusObject.put("value","站点状态");
			com.alibaba.fastjson.JSONArray statusArray=new com.alibaba.fastjson.JSONArray();
			JSONObject statusObject1=new JSONObject();
			statusObject1.put("key",50);
			statusObject1.put("value","正常使用");
			JSONObject statusObject2=new JSONObject();
			statusObject2.put("key",1);
			statusObject2.put("value","建设中");
			JSONObject statusObject3=new JSONObject();
			statusObject3.put("key",6);
			statusObject3.put("value","维护中");
			JSONObject statusObject4=new JSONObject();
			statusObject4.put("key",5);
			statusObject4.put("value","关闭下线");
			JSONObject statusObject5=new JSONObject();
			statusObject5.put("key",0);
			statusObject5.put("value","未知");
			statusArray.add(statusObject1);
			statusArray.add(statusObject2);
			statusArray.add(statusObject3);
			statusArray.add(statusObject4);
			statusArray.add(statusObject5);
			statusObject.put("data", statusArray);
			dataArray.add(statusObject);
			
			JSONObject payTypeObject=new JSONObject();
			payTypeObject.put("key","pay_type");
			payTypeObject.put("value","收费方式");
			com.alibaba.fastjson.JSONArray payTypeArray=new com.alibaba.fastjson.JSONArray();
			JSONObject payTypeObject1=new JSONObject();
			payTypeObject1.put("key","'刷卡'");
			payTypeObject1.put("value","刷卡");
			JSONObject payTypeObject2=new JSONObject();
			payTypeObject2.put("key","'线上'");
			payTypeObject2.put("value","线上");
			JSONObject payTypeObject3=new JSONObject();
			payTypeObject3.put("key","'现金'");
			payTypeObject3.put("value","现金");
			payTypeArray.add(payTypeObject1);
			payTypeArray.add(payTypeObject2);
			payTypeArray.add(payTypeObject3);
			payTypeObject.put("data", payTypeArray);
			dataArray.add(payTypeObject);
			
			JSONObject typeObject=new JSONObject();
			typeObject.put("key","type");
			typeObject.put("value","站点类型");
			com.alibaba.fastjson.JSONArray typeArray=new com.alibaba.fastjson.JSONArray();
			JSONObject typeObject1=new JSONObject();
			typeObject1.put("key",1);
			typeObject1.put("value","公共");
			JSONObject typeObject2=new JSONObject();
			typeObject2.put("key",50);
			typeObject2.put("value","个人");
			JSONObject typeObject3=new JSONObject();
			typeObject3.put("key",100);
			typeObject3.put("value","公交（专用）");
			JSONObject typeObject4=new JSONObject();
			typeObject4.put("key",101);
			typeObject4.put("value","环卫（专用）");
			JSONObject typeObject5=new JSONObject();
			typeObject5.put("key",102);
			typeObject5.put("value","物流（专用）");
			JSONObject typeObject6=new JSONObject();
			typeObject6.put("key",103);
			typeObject6.put("value","出租车（专用）");
			JSONObject typeObject7=new JSONObject();
			typeObject7.put("key",255);
			typeObject7.put("value","其他");
			typeArray.add(typeObject1);
			typeArray.add(typeObject2);
			typeArray.add(typeObject3);
			typeArray.add(typeObject4);
			typeArray.add(typeObject5);
			typeArray.add(typeObject6);
			typeArray.add(typeObject7);
			typeObject.put("data", typeArray);
			dataArray.add(typeObject);
			
			return new AjaxResult(CommonUtil.SUCCESS_CODE,CommonUtil.SUCCESS_MSG,dataArray);
		}
		return new AjaxResult(CommonUtil.ERROR_CODE,CommonUtil.ERROR_PARAMS,dataMap);
	}
	
	public static void testStationCondition() throws Exception{
		String requestUrl =CommonUtil.SERVERDOMAIN+"/api/charge/stationCondition";
		String timeStamp = StringUtil.getTimeStamp();
		String summaryKey = CommonUtil.SECRET_KEY +timeStamp;
		String summary=DigestUtils.md5Hex(summaryKey);
		String resultStr = HttpUtil.connectURL(requestUrl, "timeStamp="+timeStamp+"&summary="+summary);
		System.out.println(resultStr);
	}
	
	/**
	 * 充电站详情
	 */
	@RequestMapping("stationDetail")
	@ResponseBody
	public AjaxResult stationDetail(BaseConditionVO vo){
		dataMap = new HashMap<String, Object>();
		String [] securityArray = {"timeStamp"};
		String summaryKey = CommonUtil.SECRET_KEY + CommonUtil.reflectMethod(vo, securityArray);
		String summaryMD5 = DigestUtils.md5Hex(summaryKey);
		if(CommonUtil.dataValidate(vo.getSummary(), summaryMD5)){
			String [] excludes = {"areaCode","construction","countryCode","electricityFee","enabled","equipmentOwnerID","matchCars","operatorID","serPrice",
					"parkInfo","parkNums","remark","serviceFee","serviceTel","supportOrder","totalConnNum"};
			dataMap.put("station",net.sf.json.JSONObject.fromObject(stationService.queryByIdApp(vo),CommonUtil.createExcludes(excludes)));
			ConnectorInfo connect=connectorService.queryConnCount(vo.getId());
			dataMap.put("totalNum",connect.getTotalQuickNum()+connect.getTotalSlowNum());
			dataMap.put("freeQuickNum",connect.getFreeQuickNum());
			dataMap.put("totalQuickNum", connect.getTotalQuickNum());
			dataMap.put("freeSlowNum", connect.getFreeSlowNum());
			dataMap.put("totalSlowNum", connect.getTotalSlowNum());
			String [] excludes_ = {"current","enabled","equipmentID","freeQuickNum","freeSlowNum","stationID","stationName","totalQuickNum","totalSlowNum","voltageLowerLimits","voltageUpperLimits","create_date","modify_date"};
			List<ConnectorInfo> connectors=connectorService.queryConnByStationID(vo.getId());
			dataMap.put("connectors",JSONArray.fromObject(connectors,CommonUtil.createExcludes(excludes_)));
			return new AjaxResult(CommonUtil.SUCCESS_CODE,CommonUtil.SUCCESS_MSG,dataMap);
		}
		return new AjaxResult(CommonUtil.ERROR_CODE,CommonUtil.ERROR_PARAMS,dataMap);
	}
	
	public static void testStationDetail() throws Exception{
		String requestUrl =CommonUtil.SERVERDOMAIN+"/api/charge/stationDetail";
		String id="3501020002",latitude="31.228194",longitude="121.440031",timeStamp = StringUtil.getTimeStamp();
		String summaryKey = CommonUtil.SECRET_KEY +timeStamp;
		String summary=DigestUtils.md5Hex(summaryKey);
		String resultStr = HttpUtil.connectURL(requestUrl, "id="+id+"&latitude="+latitude+"&longitude="+longitude+"&timeStamp="+timeStamp+"&summary="+summary);
		System.out.println(resultStr);
	}
	
	/**
	 * 充电接口详情
	 */
	@RequestMapping("connectDetail")
	@ResponseBody
	public AjaxResult connectDetail(BaseConditionVO vo){
		dataMap = new HashMap<String, Object>();
		String [] securityArray = {"timeStamp"};
		String summaryKey = CommonUtil.SECRET_KEY + CommonUtil.reflectMethod(vo, securityArray);
		String summaryMD5 = DigestUtils.md5Hex(summaryKey);
		if(CommonUtil.dataValidate(vo.getSummary(), summaryMD5)){
			ConnectorInfo connect=connectorService.queryByIdApp(vo.getId());
			String [] excludes = {"current","enabled","equipmentID","freeQuickNum","freeSlowNum","stationID","stationName","totalQuickNum","totalSlowNum","voltageLowerLimits","voltageUpperLimits","create_date","modify_date"};
			dataMap.put("connector",net.sf.json.JSONObject.fromObject(connect,CommonUtil.createExcludes(excludes)));
			String [] excludes_ = {"areaCode","construction","countryCode","enabled","equipmentOwnerID","matchCars","operatorID","serPrice","operatorName",
					"parkInfo","parkNums","remark","serviceTel","supportOrder","totalConnNum","address","busineHours","distances","elePrice","parkFee","pictures","siteGuide","stationID","stationStatus","stationTel"};
			dataMap.put("station",net.sf.json.JSONObject.fromObject(stationService.queryById(connect.getStationID()),CommonUtil.createExcludes(excludes_)));
			dataMap.put("connect_type",dictService.queryByCode("CONNECT_TYPE_DESCRIPT"));
			dataMap.put("connect_status",dictService.queryByCode("CONNECT_STATUS_DESCRIPT"));
			dataMap.put("isAllow","1");//0:正常判断逻辑【type 0禁止充电 1立即充电】   1：禁止充电
			return new AjaxResult(CommonUtil.SUCCESS_CODE,CommonUtil.SUCCESS_MSG,dataMap);
		}
		return new AjaxResult(CommonUtil.ERROR_CODE,CommonUtil.ERROR_PARAMS,dataMap);
	}
	
	public static void testConnectDetail() throws Exception{
		String requestUrl =CommonUtil.SERVERDOMAIN+"/api/charge/connectDetail";
		String id="3501010001101",timeStamp = StringUtil.getTimeStamp();
		String summaryKey = CommonUtil.SECRET_KEY +timeStamp;
		String summary=DigestUtils.md5Hex(summaryKey);
		String resultStr = HttpUtil.connectURL(requestUrl, "id="+id+"&timeStamp="+timeStamp+"&summary="+summary);
		System.out.println(resultStr);
	}
	
	/**
	 * 充电初始化，通过connectorID获取接口
	 */
	@RequestMapping("chargeInit")
	@ResponseBody
	public AjaxResult chargeInit(BaseConditionVO vo){
		dataMap = new HashMap<String, Object>();
		String [] securityArray = {"credential","timeStamp"};
		String summaryKey = CommonUtil.SECRET_KEY + CommonUtil.reflectMethod(vo, securityArray);
		String summaryMD5 = DigestUtils.md5Hex(summaryKey);
		if(CommonUtil.dataValidate(vo.getSummary(), summaryMD5)){
			ConnectorInfo connect=connectorService.queryByIdApp(vo.getId());
			String [] excludes = {"parkNo","enabled","equipmentID","freeQuickNum","freeSlowNum","stationID","stationName","totalQuickNum","totalSlowNum","create_date","modify_date","status","connectorType"};
			dataMap.put("connector",net.sf.json.JSONObject.fromObject(connect,CommonUtil.createExcludes(excludes)));
			String [] excludes_ = {"comment_star","electricityFee","payment","quickConnNum","serviceFee","stationLat","stationLng","stationType","type","areaCode","construction","countryCode","enabled","equipmentOwnerID","matchCars","operatorID","serPrice","operatorName",
					"parkInfo","parkNums","remark","serviceTel","supportOrder","totalConnNum","address","busineHours","distances","elePrice","parkFee","pictures","siteGuide","stationID","stationStatus","stationTel"};
			dataMap.put("station",net.sf.json.JSONObject.fromObject(stationService.queryById(connect.getStationID()),CommonUtil.createExcludes(excludes_)));
			return new AjaxResult(CommonUtil.SUCCESS_CODE,CommonUtil.SUCCESS_MSG,dataMap);
		}
		return new AjaxResult(CommonUtil.ERROR_CODE,CommonUtil.ERROR_PARAMS,dataMap);
	}
	
	public static void testChargeInit() throws Exception{
		String requestUrl =CommonUtil.SERVERDOMAIN+"/api/charge/chargeInit";
		String id="3501010001101",credential="v714TnBlvgVPO9FXxBW9X2TLDQ506IDWgqAalmdpxXtjteiPDiICUetam60yhlCphmQCtqu2VOugnVqcCtL1YYahnLLNsnIKXN2sR7tCVlQGbD8jOINRspgn8oeQl5juknTLentDhX45BfKduZhNbwqmweF9BXi5RaS8ErMiTKR8E9U6t7nDP4G3QS1T4toNrFUXNKeDp9FRBopTSBBUx",timeStamp = StringUtil.getTimeStamp();
		String summaryKey = CommonUtil.SECRET_KEY +timeStamp;
		String summary=DigestUtils.md5Hex(summaryKey);
		String resultStr = HttpUtil.connectURL(requestUrl, "id="+id+"&credential="+credential+"&timeStamp="+timeStamp+"&summary="+summary);
		System.out.println(resultStr);
	}
	
	/**
	 * 请求启动充电
	 */
	@RequestMapping("chargeValid")
	@ResponseBody
	public AjaxResult chargeValid(BaseConditionVO vo){
		dataMap = new HashMap<String, Object>();
		String[] securityArray = { "credential","timeStamp" };
		String summaryKey = CommonUtil.SECRET_KEY + CommonUtil.reflectMethod(vo, securityArray);
		String summaryMD5 = DigestUtils.md5Hex(summaryKey);
		if (CommonUtil.dataValidate(vo.getSummary(), summaryMD5)) {
			Member logM=memberService.queryByCredential(vo.getCredential());
			if (logM!=null) {
				ChargeOrder order= chargeOrderService.queryValid(logM.getId());
				if (order!=null) {
					if (order.getStatus()==0) {
						return new AjaxResult("2","您有未完成订单需要处理",order.getSn());
					}else if (order.getStatus()>0&&order.getPay_status()==0) {
						return new AjaxResult("3","您有未完成订单需要处理",order.getSn());
					}
				}
				return new AjaxResult(CommonUtil.SUCCESS_CODE,CommonUtil.SUCCESS_MSG,dataMap);
			}
			return new AjaxResult(CommonUtil.ERROR_CREDENTIAL,CommonUtil.CREDENTIAL_INVALID,dataMap);
		}
		return new AjaxResult(CommonUtil.ERROR_CODE, CommonUtil.ERROR_PARAMS, dataMap);
	}
	
	public static void testChargeValid() throws Exception{
		String requestUrl =CommonUtil.SERVERDOMAIN+"/api/charge/chargeValid";
		String credential="v714TnBlvgVPO9FXxBW9X2TLDQ506IDWgqAalmdpxXtjteiPDiICUetam60yhlCphmQCtqu2VOugnVqcCtL1YYahnLLNsnIKXN2sR7tCVlQGbD8jOINRspgn8oeQl5juknTLentDhX45BfKduZhNbwqmweF9BXi5RaS8ErMiTKR8E9U6t7nDP4G3QS1T4toNrFUXNKeDp9FRBopTSBBUx",timeStamp = StringUtil.getTimeStamp();
		String summaryKey = CommonUtil.SECRET_KEY +credential+timeStamp;
		String summary=DigestUtils.md5Hex(summaryKey);
		String resultStr = HttpUtil.connectURL(requestUrl, "credential="+credential+"&timeStamp="+timeStamp+"&summary="+summary);
		System.out.println(resultStr);
	}
	
	
	/**
	 * 充电订单列表    status 订单状态     传空或不传：全部  , 0：未完成  , 1：已完成
	 */
	@RequestMapping("orderList")
	@ResponseBody
	public AjaxResult orderList(BaseConditionVO vo){
		dataMap = new HashMap<String, Object>();
		String[] securityArray = { "credential","timeStamp" };
		String summaryKey = CommonUtil.SECRET_KEY + CommonUtil.reflectMethod(vo, securityArray);
		String summaryMD5 = DigestUtils.md5Hex(summaryKey);
		if (CommonUtil.dataValidate(vo.getSummary(), summaryMD5)) {
			Member logM=memberService.queryByCredential(vo.getCredential());
			if (logM!=null) {
				vo.setMem_id(logM.getId());
				List<ChargeOrder> orders=chargeOrderService.queryPageByStatus(vo);
				String [] excludes = {"create_date","modify_date","firm_id","id","is_read","notify_json","sns","remark"};
				dataMap.put("list", JSONArray.fromObject(orders,CommonUtil.createExcludes(excludes)));
				return new AjaxResult(CommonUtil.SUCCESS_CODE,CommonUtil.SUCCESS_MSG,dataMap);
			}
			return new AjaxResult(CommonUtil.ERROR_CREDENTIAL,CommonUtil.CREDENTIAL_INVALID,dataMap);
		}
		return new AjaxResult(CommonUtil.ERROR_CODE, CommonUtil.ERROR_PARAMS, dataMap);
	}
	
	/**
	 * 充电订单详情
	 */
	@RequestMapping("orderDetail")
	@ResponseBody
	public AjaxResult orderDetail(BaseConditionVO vo){
		dataMap = new HashMap<String, Object>();
		String[] securityArray = { "credential","timeStamp" };
		String summaryKey = CommonUtil.SECRET_KEY + CommonUtil.reflectMethod(vo, securityArray);
		String summaryMD5 = DigestUtils.md5Hex(summaryKey);
		if (CommonUtil.dataValidate(vo.getSummary(), summaryMD5)) {
			Member logM=memberService.queryByCredential(vo.getCredential());
			if (logM!=null) {
				ChargeOrder order=chargeOrderService.queryById(vo.getId());
				String [] excludes = {"create_date","modify_date","firm_id","id","is_read","notify_json","sns"};
				dataMap.put("order", net.sf.json.JSONObject.fromObject(order, CommonUtil.createExcludes(excludes)));
				List<ChargeOrderDetail> details=chargeOrderService.queryOrderDetail(vo.getId());
				String [] excludes_ = {"orderSn"};
				dataMap.put("details", JSONArray.fromObject(details, CommonUtil.createExcludes(excludes_)));
				ConnectorInfo connect=connectorService.queryByIdApp(order.getConnectorID());
				String [] excludes__={"enabled","create_date","current","connectorType","equipmentID","freeQuickNum","freeSlowNum","modify_date","power","stationID","status","totalQuickNum","totalSlowNum","voltageLowerLimits","voltageUpperLimits"};
				dataMap.put("connect", net.sf.json.JSONObject.fromObject(connect, CommonUtil.createExcludes(excludes__)));
				return new AjaxResult(CommonUtil.SUCCESS_CODE,CommonUtil.SUCCESS_MSG,dataMap);
			}
			return new AjaxResult(CommonUtil.ERROR_CREDENTIAL,CommonUtil.CREDENTIAL_INVALID,dataMap);
		}
		return new AjaxResult(CommonUtil.ERROR_CODE, CommonUtil.ERROR_PARAMS, dataMap);
	}
	
	
	/*******************************特来电*******************************************/
	/**
	 * 充电状态
	 */
	@RequestMapping("chargeStatus")
	@ResponseBody
	public AjaxResult chargeStatus(BaseConditionVO vo){
		dataMap = new HashMap<String, Object>();
		String[] securityArray = { "credential","timeStamp" };
		String summaryKey = CommonUtil.SECRET_KEY + CommonUtil.reflectMethod(vo, securityArray);
		String summaryMD5 = DigestUtils.md5Hex(summaryKey);
		if (CommonUtil.dataValidate(vo.getSummary(), summaryMD5)) {
			return chargeService.query_equip_charge_status(vo.getId());
		}
		return new AjaxResult(CommonUtil.ERROR_CODE, CommonUtil.ERROR_PARAMS, dataMap);
	}
	
	public static void testChargeStatus() throws Exception{
		String requestUrl =CommonUtil.SERVERDOMAIN+"/api/charge/chargeStatus";
		String id="MA1K370P7201610121434330161",timeStamp = StringUtil.getTimeStamp();
		String summaryKey = CommonUtil.SECRET_KEY +timeStamp;
		String summary=DigestUtils.md5Hex(summaryKey);
		String resultStr = HttpUtil.connectURL(requestUrl, "id="+id+"&timeStamp="+timeStamp+"&summary="+summary);
		System.out.println(resultStr);
	}
	
	/**
	 * 请求启动充电
	 */
	@RequestMapping("startCharge")
	@ResponseBody
	public AjaxResult startCharge(BaseConditionVO vo){
		dataMap = new HashMap<String, Object>();
		String[] securityArray = { "credential","timeStamp" };
		String summaryKey = CommonUtil.SECRET_KEY + CommonUtil.reflectMethod(vo, securityArray);
		String summaryMD5 = DigestUtils.md5Hex(summaryKey);
		if (CommonUtil.dataValidate(vo.getSummary(), summaryMD5)) {
			Member logM=memberService.queryByCredential(vo.getCredential());
			if (logM!=null) {
				return chargeService.query_start_charge(logM,vo.getId());
			}
			return new AjaxResult(CommonUtil.ERROR_CREDENTIAL,CommonUtil.CREDENTIAL_INVALID,dataMap);
		}
		return new AjaxResult(CommonUtil.ERROR_CODE, CommonUtil.ERROR_PARAMS, dataMap);
	}
	
	public static void testStartCharge() throws Exception{
		String requestUrl =CommonUtil.SERVERDOMAIN+"/api/charge/startCharge";
		String id="3501010001101",credential="v714TnBlvgVPO9FXxBW9X2TLDQ506IDWgqAalmdpxXtjteiPDiICUetam60yhlCphmQCtqu2VOugnVqcCtL1YYahnLLNsnIKXN2sR7tCVlQGbD8jOINRspgn8oeQl5juknTLentDhX45BfKduZhNbwqmweF9BXi5RaS8ErMiTKR8E9U6t7nDP4G3QS1T4toNrFUXNKeDp9FRBopTSBBUx",timeStamp = StringUtil.getTimeStamp();
		String summaryKey = CommonUtil.SECRET_KEY +credential+timeStamp;
		String summary=DigestUtils.md5Hex(summaryKey);
		String resultStr = HttpUtil.connectURL(requestUrl, "id="+id+"&credential="+credential+"&timeStamp="+timeStamp+"&summary="+summary);
		System.out.println(resultStr);
	}
	
	/**
	 * 停止充电
	 */
	@RequestMapping("stopCharge")
	@ResponseBody
	public AjaxResult stopCharge(BaseConditionVO vo){
		dataMap = new HashMap<String, Object>();
		String[] securityArray = { "credential","timeStamp" };
		String summaryKey = CommonUtil.SECRET_KEY + CommonUtil.reflectMethod(vo, securityArray);
		String summaryMD5 = DigestUtils.md5Hex(summaryKey);
		if (CommonUtil.dataValidate(vo.getSummary(), summaryMD5)) {
			Member logM=memberService.queryByCredential(vo.getCredential());
			if (logM!=null) {
				return chargeService.query_stop_charge(vo.getOrder_sn());
			}
			return new AjaxResult(CommonUtil.ERROR_CREDENTIAL,CommonUtil.CREDENTIAL_INVALID,dataMap);
		}
		return new AjaxResult(CommonUtil.ERROR_CODE, CommonUtil.ERROR_PARAMS, dataMap);
	}
	
	public static void testStopCharge() throws Exception{
		String requestUrl =CommonUtil.SERVERDOMAIN+"/api/charge/stopCharge";
		String order_sn="xxx",credential="v714TnBlvgVPO9FXxBW9X2TLDQ506IDWgqAalmdpxXtjteiPDiICUetam60yhlCphmQCtqu2VOugnVqcCtL1YYahnLLNsnIKXN2sR7tCVlQGbD8jOINRspgn8oeQl5juknTLentDhX45BfKduZhNbwqmweF9BXi5RaS8ErMiTKR8E9U6t7nDP4G3QS1T4toNrFUXNKeDp9FRBopTSBBUx",timeStamp = StringUtil.getTimeStamp();
		String summaryKey = CommonUtil.SECRET_KEY +credential+timeStamp;
		String summary=DigestUtils.md5Hex(summaryKey);
		String resultStr = HttpUtil.connectURL(requestUrl, "order_sn="+order_sn+"&credential="+credential+"&timeStamp="+timeStamp+"&summary="+summary);
		System.out.println(resultStr);
	}
	
	/**
	 * 支付页面信息获取  余额、支付金额等  type 0:订单支付
	 * @param args
	 * @throws Exception
	 */
	@RequestMapping("payInit")
	@ResponseBody
	public AjaxResult payInit(BaseConditionVO vo){
		dataMap=new HashMap<String, Object>();
		String [] securityArray = {"credential","timeStamp"};
		String summaryKey = CommonUtil.SECRET_KEY + CommonUtil.reflectMethod(vo, securityArray);
		String summaryMD5 = DigestUtils.md5Hex(summaryKey);
		if(CommonUtil.dataValidate(vo.getSummary(), summaryMD5)){
			Member logM=memberService.queryByCredential(vo.getCredential());
			if (logM!=null) {
				ChargeOrder order=chargeOrderService.queryById(vo.getId());
				dataMap.put("sn",order.getSn());
				dataMap.put("account",logM.getAccount());
				dataMap.put("amount", order.getTotalMoney());
				dataMap.put("name", "订单支付");
				dataMap.put("desc","订单支付");
				dataMap.put("pay_pwd",StringUtil.isEmpty(logM.getPay_pwd())?"1":"0");
				//支付宝
				dataMap.put("private_key",AlipayConfig.private_key);
				dataMap.put("partner", AlipayConfig.partner);
				dataMap.put("seller_id",AlipayConfig.partner);
				dataMap.put("url", CommonUtil.SERVERDOMAIN+"/payment/alipay/charge_notify");
				//微信
				dataMap.put("wx_private_key",WXConfig.PARTENR_KEY_APP);
				dataMap.put("wx_mch_id", WXConfig.PARTNER_APP);
				dataMap.put("wx_url", CommonUtil.SERVERDOMAIN+"/payment/wxpay/charge_notify");
				return new AjaxResult(CommonUtil.SUCCESS_CODE,CommonUtil.SUCCESS_MSG,dataMap);
			}
			return new AjaxResult(CommonUtil.ERROR_CREDENTIAL,CommonUtil.CREDENTIAL_INVALID,dataMap);
		}
		return new AjaxResult(CommonUtil.ERROR_CODE,CommonUtil.ERROR_PARAMS,dataMap);
	}
	
	/**
	 * 余额支付
	 */
	@RequestMapping("accountPay")
	@ResponseBody
	public AjaxResult pay(BaseConditionVO vo){
		dataMap=new HashMap<String, Object>();
		String [] securityArray = {"credential","timeStamp"};
		String summaryKey = CommonUtil.SECRET_KEY + CommonUtil.reflectMethod(vo, securityArray);
		String summaryMD5 = DigestUtils.md5Hex(summaryKey);
		if(CommonUtil.dataValidate(vo.getSummary(), summaryMD5)){
			return chargeOrderService.accountPay(vo);
		}
		return new AjaxResult(CommonUtil.ERROR_CODE,CommonUtil.ERROR_PARAMS,dataMap);
	}
	
	public static void main(String[] args) throws Exception {
//		testStationMap();
//		testStationList();
//		testStationCondition();
//		testStationDetail();
//		testConnectDetail();
//		testStartCharge();
//		testStopCharge();
//		testChargeInit();
		testChargeStatus();
	}
}
