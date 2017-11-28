package com.cocosh.api.controller;

import java.util.HashMap;
import java.util.Map;

import net.sf.json.JSONArray;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cocosh.car.model.Dot;
import com.cocosh.car.model.Order;
import com.cocosh.car.model.PaymentLog;
import com.cocosh.car.service.DotService;
import com.cocosh.car.service.OrderService;
import com.cocosh.car.service.PaymentLogService;
import com.cocosh.framework.base.AjaxResult;
import com.cocosh.framework.base.BaseConditionVO;
import com.cocosh.framework.base.BaseController;
import com.cocosh.framework.payment.alipay.config.AlipayConfig;
import com.cocosh.framework.payment.wxap.WXConfig;
import com.cocosh.framework.util.CommonUtil;
import com.cocosh.framework.util.DateUtil;
import com.cocosh.framework.util.HttpUtil;
import com.cocosh.framework.util.StringUtil;
import com.cocosh.member.model.Member;
import com.cocosh.member.service.MemberService;
import com.cocosh.sys.service.DictService;

@Controller
@RequestMapping("api/order")
public class OrderApiController extends BaseController{
	Map<String, Object> dataMap=null;
	@Autowired
	private OrderService service;
	@Autowired
	private MemberService memberService;
	@Autowired
	private DotService dotService;
	@Autowired
	private DictService dictService;
	@Autowired
	private PaymentLogService logService;
	
	/**
	 * 查询
	 * @param po
	 * @param request
	 * @return
	 */
	@RequestMapping("init")
	@ResponseBody
	public AjaxResult init(BaseConditionVO vo){
		dataMap=new HashMap<String, Object>();
		String [] securityArray = {"credential","timeStamp"};
		String summaryKey = CommonUtil.SECRET_KEY + CommonUtil.reflectMethod(vo, securityArray);
		String summaryMD5 = DigestUtils.md5Hex(summaryKey);
		if(CommonUtil.dataValidate(vo.getSummary(), summaryMD5)){
			Member member=memberService.queryByCredential(vo.getCredential());
			if (member!=null) {
				dataMap.put("insurance",Double.parseDouble(dictService.queryByCode("INSURANCE_AMOUNT")));
				dataMap.put("distribution_range",Double.parseDouble(dictService.queryByCode("DISTRIBUTION_RANGE")));
				dataMap.put("distribution_amount",Double.parseDouble(dictService.queryByCode("DISTRIBUTION_AMOUNT")));
				dataMap.put("back_change_rule",dictService.queryByCode("BACK_CHANGE_RULE"));
				return new AjaxResult(CommonUtil.SUCCESS_CODE,CommonUtil.SUCCESS_MSG,dataMap);
			}
			return new AjaxResult(CommonUtil.ERROR_CREDENTIAL,CommonUtil.CREDENTIAL_INVALID,dataMap);
		}
		return new AjaxResult(CommonUtil.ERROR_CODE,CommonUtil.ERROR_PARAMS,dataMap);
	}
	
	public static void testInit() throws Exception{
		String requestUrl =CommonUtil.SERVERDOMAIN+"/api/order/init";
		String credential="aaE24wqWdwu4lPXUveel3ZjkKZJXWSr7XEE9VFf7gD2Fltd83UVmt1hmRiBsL2mX3MvbEym4DU1DGa8qABCixNuxvdAJ6bOrx3aqHvvOWC8o73x5CTLo7jjvXt9t8I7WOWNF7iRtldkF5EgwS53qIP4FwgU6FhbFXa1Djhg3A7rrZSKd50YtS4VdrUf8OmcLpr9SRhUZkedD6I8SKHLKtdHTptjpmS8RkPv20Q",timeStamp = StringUtil.getTimeStamp();
		String summaryKey = CommonUtil.SECRET_KEY +credential+timeStamp;
		String summary=DigestUtils.md5Hex(summaryKey);
		String resultStr = HttpUtil.connectURL(requestUrl, "credential="+credential+"&timeStamp="+timeStamp+"&summary="+summary);
		System.out.println(resultStr);
	}
	
	@RequestMapping("add")
	@ResponseBody
	public AjaxResult add(Order po,BaseConditionVO vo){
		dataMap = new HashMap<String, Object>();
		String [] securityArray = {"credential","timeStamp"};
		String summaryKey = CommonUtil.SECRET_KEY + CommonUtil.reflectMethod(vo, securityArray);
		String summaryMD5 = DigestUtils.md5Hex(summaryKey);
		if(CommonUtil.dataValidate(vo.getSummary(), summaryMD5)){
			return service.add(po, vo);
		}
		return new AjaxResult(CommonUtil.ERROR_CODE,CommonUtil.ERROR_PARAMS,dataMap);
	}
	
	public static void testAdd() throws Exception{
		String requestUrl =CommonUtil.SERVERDOMAIN+"/api/order/add";
		String  car_id="1b2d121ccb4d4749ae46de9b3abe89ef",rent_star_time="2016-06-08 12:00",rent_end_time="2016-06-08 22:00",amount="580",insurance="120",coupon_id="xxx001",use_point="100",
				get_amount="100",return_amount="100",get_type="1",return_type="1",get_dot_id="yyyy001",return_dot_id="zzzz001",get_address="上海宣化路300号",get_lng="121.0000",get_lat="31.0000",
				return_address="上海交通路1500号",return_lng="122.22000",return_lat="33.12000",remark="备注",credential="iE63UTJwjj9xTprTw8fTSQEI9HGw8qojm5wes6s3PTH6A8xgjhJZcrOefWtIPC0e5NwI5wdu7IV9qM7u8xUKqI69IQaTMMfxPDd5fb4e7OCRiLRFfsYxn5DvFr87FW10XWjWrXlCMb915ElwXPyYaosfZYqvPitUOr3XYfdQop7RV5SfvFpOKgohgeEp2FPo4KXZtxVtu1XoULQM6Mh96",timeStamp = StringUtil.getTimeStamp();
		String summaryKey = CommonUtil.SECRET_KEY +credential+timeStamp;
		String summary=DigestUtils.md5Hex(summaryKey);
		String resultStr = HttpUtil.connectURL(requestUrl, "car_id="+car_id+"&rent_star_time="+rent_star_time+"&rent_end_time="+rent_end_time+"&amount="+amount+"&insurance="+insurance+"&coupon_id="+coupon_id+"&use_point="+use_point+
				"&get_amount="+get_amount+"&return_amount="+return_amount+"&get_type="+get_type+"&return_type="+return_type+"&get_dot_id="+get_dot_id+"&return_dot_id="+return_dot_id+"&get_address="+get_address+"&get_lng="+get_lng+"&get_lat="+get_lat+
				"&return_address="+return_address+"&return_lng="+return_lng+"&return_lat="+return_lat+"&remark="+remark+"&credential="+credential+"&timeStamp="+timeStamp+"&summary="+summary);
		System.out.println(resultStr);
	}
	

	/**
	 * 查询我的行程
	 * @param po
	 * @param request
	 * @return
	 */
	@RequestMapping("list")
	@ResponseBody
	public AjaxResult list(BaseConditionVO vo){
		dataMap=new HashMap<String, Object>();
		String [] securityArray = {"credential","timeStamp"};
		String summaryKey = CommonUtil.SECRET_KEY + CommonUtil.reflectMethod(vo, securityArray);
		String summaryMD5 = DigestUtils.md5Hex(summaryKey);
		if(CommonUtil.dataValidate(vo.getSummary(), summaryMD5)){
			Member member=memberService.queryByCredential(vo.getCredential());
			if (member!=null) {
				vo.setMem_id(member.getId());
				String [] excludes = {"amount","ready_date","delivery_date","complete_date","rent_end_ttime","rent_star_ttime","coupon_amount","coupon_id","firm_id","get_address","get_amount","get_dot_id","get_dot_name","get_lat","get_lng","get_type",
						"insurance","mem_id","mem_name","modify_date","pay_amount","pay_type","remark","return_address","return_amount","return_dot_id","is_read","order_json","del_flg","car_id",
						"return_dot_name","return_lat","return_lng","return_type","settle_date","settle_status","settle_type","use_point","car_price","damage_gallerys","dispatch_name","dispatch_mobile","park_name","park_lng","park_lat","firm_name","damage_amount","car_number","ins_num","mem_mobile","order_remark","return_gallerys","other_amount","peccancy_amount"};
				dataMap.put("list", JSONArray.fromObject(service.queryPageByStatus(vo),CommonUtil.createExcludes(excludes)));
				return new AjaxResult(CommonUtil.SUCCESS_CODE,CommonUtil.SUCCESS_MSG,dataMap);
			}
			return new AjaxResult(CommonUtil.ERROR_CREDENTIAL,CommonUtil.CREDENTIAL_INVALID,dataMap);
		}
		return new AjaxResult(CommonUtil.ERROR_CODE,CommonUtil.ERROR_PARAMS,dataMap);
	}
	
	public static void testList() throws Exception{
		String requestUrl =CommonUtil.SERVERDOMAIN+"/api/order/list";
		Integer pageNo=1;
		String type="",credential="b0iFs5jWa9OKtT5MJnM0rgEHfnFyD03R74A4ZZpqY2hHvPuDrNgTD4np7tZUYWZFfuI5mKEye2BMPEYjgW2Elal0xRBw2N15X74Uc6jMGOnM6p1hMux5UeRDOiqcKHKXBY7UlvhDy6Yq3LSbwe0F9CGvBW87ravHSkbk2EfvgMmaMIFCG8QPaj7NY8BfpJWBEPBFjoEsIlw12s4Qs2jXgBIONDXOe1TXMarMIZ",timeStamp = StringUtil.getTimeStamp();
		String summaryKey = CommonUtil.SECRET_KEY +credential+timeStamp;
		String summary=DigestUtils.md5Hex(summaryKey);
		String resultStr = HttpUtil.connectURL(requestUrl, "pageNo="+pageNo+"&type="+type+"&credential="+credential+"&timeStamp="+timeStamp+"&summary="+summary);
		System.out.println(resultStr);
	}
	
	/**
	 * 查询
	 * @param po
	 * @param request
	 * @return
	 */
	@RequestMapping("detail")
	@ResponseBody
	public AjaxResult detail(BaseConditionVO vo){
		dataMap=new HashMap<String, Object>();
		String [] securityArray = {"credential","timeStamp"};
		String summaryKey = CommonUtil.SECRET_KEY + CommonUtil.reflectMethod(vo, securityArray);
		String summaryMD5 = DigestUtils.md5Hex(summaryKey);
		if(CommonUtil.dataValidate(vo.getSummary(), summaryMD5)){
			Member member=memberService.queryByCredential(vo.getCredential());
			if (member!=null) {
				Map<String,Object> oMap=new HashMap<String, Object>();
				Order oDetail=service.queryByIdApp(vo.getId());
				Integer status=oDetail.getStatus();
				oMap.put("id", oDetail.getId());
				oMap.put("sn", oDetail.getSn());
				oMap.put("unit_price",oDetail.getUnit_price());
				oMap.put("car_name", oDetail.getCar_name());
				oMap.put("car_price",oDetail.getCar_price());
				oMap.put("car_license",oDetail.getType()==0?oDetail.getCar_license():oDetail.getCar_id());
				oMap.put("amount", oDetail.getAmount());
				oMap.put("coupon_amount",oDetail.getCoupon_amount());
				oMap.put("use_point", oDetail.getUse_point());
				oMap.put("insurance", oDetail.getInsurance());
				oMap.put("ins_num", oDetail.getIns_num());
				oMap.put("get_amount", oDetail.getGet_amount());
				oMap.put("return_amount", oDetail.getReturn_amount());
				oMap.put("damage_amount", oDetail.getDamage_amount());
				oMap.put("peccancy_amount", oDetail.getPeccancy_amount());
				oMap.put("other_amount", oDetail.getOther_amount());
				oMap.put("order_status", oDetail.getStatus()>=3?"已完成":oDetail.getOrder_status());
				oMap.put("pay_type", oDetail.getPay_type());
				oMap.put("remark", oDetail.getRemark());
				oMap.put("rent_days", oDetail.getRent_days());
				oMap.put("pay_status",oDetail.getPay_status());
				oMap.put("type",oDetail.getType());
				
				oMap.put("dispatch_name", oDetail.getDispatch_name());
				oMap.put("dispatch_mobile",oDetail.getDispatch_mobile());
				
				oMap.put("get_type",oDetail.getGet_type());
				oMap.put("return_type", oDetail.getReturn_type());
				
				if (oDetail.getType()==0) {
					if (oDetail.getGet_type()==0) {
						Dot getDot=dotService.queryById(oDetail.getGet_dot_id());
						oMap.put("get_dot_name",getDot.getName());
						oMap.put("get_dot_lng", getDot.getLongitude());
						oMap.put("get_dot_lat", getDot.getLatitude());
					}else {
						oMap.put("get_address",oDetail.getGet_address());
					}
					if (oDetail.getReturn_type()==0) {
						Dot retDot=dotService.queryById(oDetail.getReturn_dot_id());
						oMap.put("return_dot_name",retDot.getName());
						oMap.put("return_dot_lng", retDot.getLongitude());
						oMap.put("return_dot_lat", retDot.getLatitude());
					}else {
						oMap.put("return_address",oDetail.getReturn_address());
					}
				}else {
					oMap.put("get_dot_name",oDetail.getGet_dot_id());
					oMap.put("return_dot_name",oDetail.getReturn_dot_id());
				}
				oMap.put("rent_star_time",DateUtil.formatDate(oDetail.getRent_star_time(),"yyyy-MM-dd HH:mm"));
				oMap.put("rent_end_time", oDetail.getRent_end_time()!=null?DateUtil.formatDate(oDetail.getRent_end_time(), "yyyy-MM-dd HH:mm"):null);
				oMap.put("status",status);
				if (status==0||status==-1) {
					oMap.put("date", DateUtil.formatDate(oDetail.getCreate_date(),"MM月dd日 HH:mm"));
				}else if (status==1) {
					oMap.put("date", DateUtil.formatDate(oDetail.getReady_date(),"MM月dd日 HH:mm"));
				}else if (status==2) {
					oMap.put("date", oDetail.getType()==0?DateUtil.formatDate(oDetail.getDelivery_date(),"MM月dd日 HH:mm"):DateUtil.formatDate(oDetail.getRent_star_time(),"MM月dd日 HH:mm"));
				}else {
					oMap.put("date", oDetail.getType()==0?DateUtil.formatDate(oDetail.getComplete_date(),"MM月dd日 HH:mm"):(oDetail.getRent_end_time()!=null?DateUtil.formatDate(oDetail.getRent_star_time(),"MM月dd日 HH:mm"):null));
				}
				dataMap.put("order",oMap);
				return new AjaxResult(CommonUtil.SUCCESS_CODE,CommonUtil.SUCCESS_MSG,dataMap);
			}
			return new AjaxResult(CommonUtil.ERROR_CREDENTIAL,CommonUtil.CREDENTIAL_INVALID,dataMap);
		}
		return new AjaxResult(CommonUtil.ERROR_CODE,CommonUtil.ERROR_PARAMS,dataMap);
	}
	
	public static void testDetail() throws Exception{
		String requestUrl =CommonUtil.SERVERDOMAIN+"/api/order/detail";
		String id="5543040c9cbe4ef9a0a78b452f319429",credential="aF1LW0Rj4amRUkLWoYA9MMISjENcnsdYcEHd90D7h8TJXXAHGZ4nrHD0dd8r75bNKLgGeIgF10cXvyBeLqulCxXBKZ2RFH7AZwJFgJIsBVUteNkZaAwteb7T426qg14ZFixjqFZloMfWgXBx6anaR1peRXZF4eFVv6kR5mMJgEg9ykariAqvceslYdg9HbkVp4OpPfU5QTfW",timeStamp = StringUtil.getTimeStamp();
		String summaryKey = CommonUtil.SECRET_KEY +credential+timeStamp;
		String summary=DigestUtils.md5Hex(summaryKey);
		String resultStr = HttpUtil.connectURL(requestUrl, "id="+id+"&credential="+credential+"&timeStamp="+timeStamp+"&summary="+summary);
		System.out.println(resultStr);
	}
	
	/**
	 * 提前还车
	 * @param request
	 * @param vo
	 * @return
	 */
	@RequestMapping("updEndTime")
	@ResponseBody
	public AjaxResult advanced(Order po,BaseConditionVO vo){
		dataMap=new HashMap<String, Object>();
		String [] securityArray = {"credential","timeStamp"};
		String summaryKey = CommonUtil.SECRET_KEY + CommonUtil.reflectMethod(vo, securityArray);
		String summaryMD5 = DigestUtils.md5Hex(summaryKey);
		if(CommonUtil.dataValidate(vo.getSummary(), summaryMD5)){
			return service.updateEndTime(po,vo);
		}
		return new AjaxResult(CommonUtil.ERROR_CODE,CommonUtil.ERROR_PARAMS,dataMap);
	}
	
	
	/**
	 * 查询
	 * @param po
	 * @param request
	 * @return
	 */
	@RequestMapping("cancel")
	@ResponseBody
	public AjaxResult cancel(BaseConditionVO vo){
		dataMap=new HashMap<String, Object>();
		String [] securityArray = {"credential","timeStamp"};
		String summaryKey = CommonUtil.SECRET_KEY + CommonUtil.reflectMethod(vo, securityArray);
		String summaryMD5 = DigestUtils.md5Hex(summaryKey);
		if(CommonUtil.dataValidate(vo.getSummary(), summaryMD5)){
			return service.cancelOrder(vo);
		}
		return new AjaxResult(CommonUtil.ERROR_CODE,CommonUtil.ERROR_PARAMS,dataMap);
	}
	
	/**
	 * 普通用户余额支付
	 * @return
	 */
	@RequestMapping("accountPay")
	@ResponseBody
	public AjaxResult pay(BaseConditionVO vo){
		dataMap=new HashMap<String, Object>();
		String [] securityArray = {"credential","timeStamp"};
		String summaryKey = CommonUtil.SECRET_KEY + CommonUtil.reflectMethod(vo, securityArray);
		String summaryMD5 = DigestUtils.md5Hex(summaryKey);
		if(CommonUtil.dataValidate(vo.getSummary(), summaryMD5)){
			return service.accountPay(vo);
		}
		return new AjaxResult(CommonUtil.ERROR_CODE,CommonUtil.ERROR_PARAMS,dataMap);
	}
	
	/**
	 * 支付页面信息获取  余额、支付金额等  type 0:订单支付   1：充值保证金   2：充值余额
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
				if (vo.getType().equals("0")) {
					Order order=service.queryAmount(vo.getId());
					dataMap.put("sn",order.getSn());
					dataMap.put("account",logM.getAccount());
					dataMap.put("amount", order.getAmount());
					dataMap.put("name", "订单支付");
					dataMap.put("desc","订单支付");
					dataMap.put("pay_pwd",StringUtil.isEmpty(logM.getPay_pwd())?"1":"0");
					//支付宝
					dataMap.put("private_key",AlipayConfig.private_key);
					dataMap.put("partner", AlipayConfig.partner);
					dataMap.put("seller_id",AlipayConfig.partner);
					dataMap.put("url", CommonUtil.SERVERDOMAIN+"/payment/alipay/order_notify");
					//微信
					dataMap.put("wx_private_key",WXConfig.PARTENR_KEY_APP);
					dataMap.put("wx_mch_id", WXConfig.PARTNER_APP);
					dataMap.put("wx_url", CommonUtil.SERVERDOMAIN+"/payment/wxpay/order_notify");
				}else if (vo.getType().equals("1")) {
					dataMap.put("deposit", logM.getDeposit());
				}else {
					dataMap.put("account",logM.getAccount());
				}
				return new AjaxResult(CommonUtil.SUCCESS_CODE,CommonUtil.SUCCESS_MSG,dataMap);
			}
			return new AjaxResult(CommonUtil.ERROR_CREDENTIAL,CommonUtil.CREDENTIAL_INVALID,dataMap);
		}
		return new AjaxResult(CommonUtil.ERROR_CODE,CommonUtil.ERROR_PARAMS,dataMap);
	}
	
	
	/**
	 * 创建流水  余额、支付金额等  type 0:订单支付 1：充值保证金 2：充值金额 3:订单退款（取消订单等） 4：保证金退返  5:余额退返
	 * pay_type   0:余额 1：支付宝  2：微信   3：银联   4：线下  5:apple pay
	 * pay_amount  充值金额
	 * @param args
	 * @throws Exception
	 */
	@RequestMapping("crePaymentLog")
	@ResponseBody
	public AjaxResult crePaymentLog(BaseConditionVO vo){
		dataMap=new HashMap<String, Object>();
		String [] securityArray = {"credential","timeStamp"};
		String summaryKey = CommonUtil.SECRET_KEY + CommonUtil.reflectMethod(vo, securityArray);
		String summaryMD5 = DigestUtils.md5Hex(summaryKey);
		if(CommonUtil.dataValidate(vo.getSummary(), summaryMD5)){
			Member logM=memberService.queryByCredential(vo.getCredential());
			if (logM!=null) {
				int pay_type=Integer.parseInt(vo.getPay_type());
				if (vo.getType().equals("1")) {
					//添加paymentlog
					PaymentLog pl=new PaymentLog(logM.getId(), null, Double.parseDouble(vo.getPay_amount()),"保证金充值",null, null, pay_type, 0, 1,null);
					logService.add(pl);
					
					dataMap.put("sn",pl.getSn());
					dataMap.put("amount", Double.parseDouble(vo.getPay_amount()));
					dataMap.put("name", "保证金充值");
					dataMap.put("desc","保证金充值");
					//支付宝
					dataMap.put("private_key",AlipayConfig.private_key);
					dataMap.put("partner", AlipayConfig.partner);
					dataMap.put("seller_id",AlipayConfig.partner);
					dataMap.put("url", CommonUtil.SERVERDOMAIN+"/payment/alipay/deposit_notify");
					//微信
					dataMap.put("wx_private_key",WXConfig.PARTENR_KEY_APP);
					dataMap.put("wx_mch_id", WXConfig.PARTNER_APP);
					dataMap.put("wx_url", CommonUtil.SERVERDOMAIN+"/payment/wxpay/deposit_notify");
				}else {
					//添加paymentlog
					PaymentLog pl=new PaymentLog(logM.getId(), null, Double.parseDouble(vo.getPay_amount()),"余额充值",null, null, pay_type, 0, 2,null);
					logService.add(pl);
					
					dataMap.put("sn",pl.getSn());
					dataMap.put("amount", Double.parseDouble(vo.getPay_amount()));
					dataMap.put("name", "余额充值");
					dataMap.put("desc","余额充值");
					//支付宝
					dataMap.put("private_key",AlipayConfig.private_key);
					dataMap.put("partner", AlipayConfig.partner);
					dataMap.put("seller_id",AlipayConfig.partner);
					dataMap.put("url", CommonUtil.SERVERDOMAIN+"/payment/alipay/account_notify");
					//微信
					dataMap.put("wx_private_key",WXConfig.PARTENR_KEY_APP);
					dataMap.put("wx_mch_id", WXConfig.PARTNER_APP);
					dataMap.put("wx_url", CommonUtil.SERVERDOMAIN+"/payment/wxpay/account_notify");
				}
				return new AjaxResult(CommonUtil.SUCCESS_CODE,CommonUtil.SUCCESS_MSG,dataMap);
			}
			return new AjaxResult(CommonUtil.ERROR_CREDENTIAL,CommonUtil.CREDENTIAL_INVALID,dataMap);
		}
		return new AjaxResult(CommonUtil.ERROR_CODE,CommonUtil.ERROR_PARAMS,dataMap);
	}
	
	public static void main(String[] args) throws Exception {
//		testAdd();
		testList();
//		testDetail();
//		testInit();
	}
}
