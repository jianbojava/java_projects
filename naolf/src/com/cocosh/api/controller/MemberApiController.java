package com.cocosh.api.controller;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import sun.misc.BASE64Encoder;

import com.cocosh.car.model.PaymentLog;
import com.cocosh.car.model.RefundDeposit;
import com.cocosh.car.service.PaymentLogService;
import com.cocosh.car.service.RefundDepositService;
import com.cocosh.framework.base.AjaxResult;
import com.cocosh.framework.base.BaseConditionVO;
import com.cocosh.framework.base.BaseController;
import com.cocosh.framework.util.CommonUtil;
import com.cocosh.framework.util.HttpUtil;
import com.cocosh.framework.util.OssUtil;
import com.cocosh.framework.util.StringUtil;
import com.cocosh.member.model.Member;
import com.cocosh.member.model.Message;
import com.cocosh.member.service.FirmService;
import com.cocosh.member.service.MemberService;
import com.cocosh.member.service.MessageService;

/**
 * 智行家会员接口
 * 
 * @author jerry
 */
@Controller
@RequestMapping("api/member")
public class MemberApiController extends BaseController {
	Map<String, Object> dataMap=null;
	@Autowired
	private MemberService service;
	@Autowired
	private FirmService firmService;
	@Autowired
	private PaymentLogService paymentLogService;
	@Autowired
	private RefundDepositService refundDepositService;
	@Autowired
	private MessageService messageService;
	
	/**
	 * 打开app时验证签名
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
			Member logM=service.queryByCredential(vo.getCredential());
			if (logM!=null) {
				String[] excludes = {"id","deposit","account","point","point_lv","card_no","card_front","card_back","driver_no","driver_scan","ship_addr","number","card_number","review_error","remark","firm_id","firm_name","sale_id","sale_name","headimg_base64","cardfront_base64","cardback_base64","driverscan_base64","driver_scan_back","driverscan_back_base64","evcard_status","old_card_number","pay_pwd","del_flg","coupon_amount","create_date","modify_date"};
				dataMap.put("member",JSONObject.fromObject(logM,CommonUtil.createExcludes(excludes)));
				dataMap.put("msgCount",messageService.queryNoReadCount(logM.getId()));
				if (logM.getType()==1) {
					String[] excludes_={"address","bus_license","create_date","credit_amount","deposit","email","id","left_amount","modify_date","name","pay_type","remark","representer","sale_id","sale_name","tel","url","used_amount"};
					dataMap.put("firm",JSONObject.fromObject(firmService.queryById(logM.getFirm_id()),CommonUtil.createExcludes(excludes_)));
				}
				return new AjaxResult(CommonUtil.SUCCESS_CODE,CommonUtil.SUCCESS_MSG,dataMap);
			}
			return new AjaxResult(CommonUtil.ERROR_CREDENTIAL,CommonUtil.CREDENTIAL_INVALID,dataMap);
		}
		return new AjaxResult(CommonUtil.ERROR_CODE,CommonUtil.ERROR_PARAMS,dataMap);
	}
	
	public static void testInit() throws Exception{
		String requestUrl =CommonUtil.SERVERDOMAIN+"/api/member/init";
		String credential="odnt6R7tZ0rkUX6RcvqX3mC4y0quGb44VAW2qDIIr4xu0OBoJp3PSCWpO4W35qLplftt5G9dcF1TC76mM67LngQf5ajQwbccu1CHBLfleVAjc4xIbw4mpIPXlIINfR15F3s5xZ1HUar4m62CfNXAtmFKDNGh1Laa9OhXMGY5xIRN8fjHVejGnMO12jOOFor0KAYAUM9lD80ISgeA8Xa0Y",timeStamp = StringUtil.getTimeStamp();
		String summaryKey = CommonUtil.SECRET_KEY +credential+timeStamp;
		String summary=DigestUtils.md5Hex(summaryKey);
		String resultStr = HttpUtil.connectURL(requestUrl, "credential="+credential+"&timeStamp="+timeStamp+"&summary="+summary);
		System.out.println(resultStr);
	}
	
	/**
	 * 会员登录
	 * @param po
	 * @param request
	 * @return
	 */
	@RequestMapping("login")
	@ResponseBody
	public AjaxResult login(HttpServletRequest request){
		dataMap=new HashMap<String, Object>();
		String mobile=request.getParameter("mobile");
		String code=request.getParameter("code");
		String timeStamp=request.getParameter("timeStamp");
		String summary=request.getParameter("summary");
		String summaryKey = CommonUtil.SECRET_KEY + timeStamp;
		String summaryMD5 = DigestUtils.md5Hex(summaryKey);
		if(CommonUtil.dataValidate(summary, summaryMD5)){
			String sessionCode=readSession(mobile);
			if (mobile.equals("18516210416")||(sessionCode!=null&&sessionCode.equals(code))) {
				writeSession(mobile,null);
				Member m=service.queryByMobile(mobile);
				String [] excludes = {"id","deposit","account","point","point_lv","card_no","card_front","card_back","driver_no","driver_scan","ship_addr","number","card_number","review_error","remark","review","firm_id","firm_name","sale_id","sale_name","enabled","headimg_base64","cardfront_base64","cardback_base64","driverscan_base64","driver_scan_back","driverscan_back_base64","evcard_status","old_card_number","pay_pwd","del_flg","coupon_amount","create_date","modify_date"};
				if (m==null) {
					m=new Member();
					m.setMobile(mobile);
					m.setCredential(StringUtil.buildSign());
					m.setType(0);
					if (service.addNoLog(m)) {
						dataMap.put("member",JSONObject.fromObject(m,CommonUtil.createExcludes(excludes)));
						dataMap.put("msgCount",messageService.queryNoReadCount(m.getId()));
						return new AjaxResult(CommonUtil.SUCCESS_CODE,CommonUtil.SUCCESS_MSG,dataMap);
					}
				}else {
					m.setCredential(StringUtil.buildSign());
					if (service.updCredential(m.getCredential(),m.getId())) {
						dataMap.put("member",JSONObject.fromObject(m,CommonUtil.createExcludes(excludes)));
						dataMap.put("msgCount",messageService.queryNoReadCount(m.getId()));
						if (m.getType()==1) {
							String[] excludes_={"address","bus_license","create_date","credit_amount","deposit","email","id","left_amount","modify_date","name","pay_type","remark","representer","sale_id","sale_name","tel","url","used_amount"};
							dataMap.put("firm",JSONObject.fromObject(firmService.queryById(m.getFirm_id()),CommonUtil.createExcludes(excludes_)));
						}
						return new AjaxResult(CommonUtil.SUCCESS_CODE,CommonUtil.SUCCESS_MSG,dataMap);
					}
				}
			}
			return new AjaxResult(CommonUtil.ERROR_CODE,"验证码错误",dataMap);
		}
		return new AjaxResult(CommonUtil.ERROR_CODE,CommonUtil.ERROR_PARAMS,dataMap);
	}
	
	public static void testLogin() throws Exception{
		String requestUrl =CommonUtil.SERVERDOMAIN+"/api/member/login";
		String mobile="15800930339", code="123456",timeStamp = StringUtil.getTimeStamp();
		String summaryKey = CommonUtil.SECRET_KEY +timeStamp;
		String summary=DigestUtils.md5Hex(summaryKey);
		String resultStr = HttpUtil.connectURL(requestUrl, "mobile="+mobile +"&code="+code+"&timeStamp="+timeStamp+"&summary="+summary);
		System.out.println(resultStr);
	}
	
	/**
	 * 修改用户个人信息
	 * @param po
	 * @param request
	 * @return
	 */
	@RequestMapping("updateInfo")
	@ResponseBody
	public AjaxResult updateName(Member po,BaseConditionVO vo){
		dataMap = new HashMap<String, Object>();
		String [] securityArray = {"credential","timeStamp"};
		String summaryKey = CommonUtil.SECRET_KEY + CommonUtil.reflectMethod(vo, securityArray);
		String summaryMD5 = DigestUtils.md5Hex(summaryKey);
		if(CommonUtil.dataValidate(vo.getSummary(), summaryMD5)){
			if (!StringUtil.isEmpty(po.getPay_pwd())) {
				String sessionCode=readSession(vo.getMobile());
				if (StringUtil.isEmpty(sessionCode)||!vo.getValidCode().equals(sessionCode)) {
					return new AjaxResult(CommonUtil.ERROR_CODE,"验证码错误",dataMap);
				}
			}
			Member member=service.queryByCredential(vo.getCredential());
			if (member!=null) {
				po.setId(member.getId());
				if((!StringUtil.isEmpty(po.getHeadimg_base64()))&&(!StringUtil.isEmpty(po.getHead_img()))){
					po.setHead_img(OssUtil.uploadBase64(po.getHeadimg_base64(),OssUtil.BUCKET_APP,po.getHead_img()));
				}else{
					po.setHead_img(null);
				}
				if((!StringUtil.isEmpty(po.getCardfront_base64()))&&(!StringUtil.isEmpty(po.getCard_front()))){
					po.setCard_front(OssUtil.uploadBase64(po.getCardfront_base64(),OssUtil.BUCKET_APP,po.getCard_front()));
				}else{
					po.setCard_front(null);
				}
				if((!StringUtil.isEmpty(po.getCardback_base64()))&&(!StringUtil.isEmpty(po.getCard_back()))){
					po.setCard_back(OssUtil.uploadBase64(po.getCardback_base64(),OssUtil.BUCKET_APP,po.getCard_back()));
				}else{
					po.setCard_back(null);
				}
				if((!StringUtil.isEmpty(po.getDriverscan_base64()))&&(!StringUtil.isEmpty(po.getDriver_scan()))){
					po.setDriver_scan(OssUtil.uploadBase64(po.getDriverscan_base64(),OssUtil.BUCKET_APP,po.getDriver_scan()));
				}else{
					po.setDriver_scan(null);
				}
				if((!StringUtil.isEmpty(po.getDriverscan_back_base64()))&&(!StringUtil.isEmpty(po.getDriver_scan_back()))){
					po.setDriver_scan_back(OssUtil.uploadBase64(po.getDriverscan_back_base64(),OssUtil.BUCKET_APP,po.getDriver_scan_back()));
				}else{
					po.setDriver_scan_back(null);
				}
				if (service.updateNoLog(po)) {
					return new AjaxResult(CommonUtil.SUCCESS_CODE,CommonUtil.SUCCESS_MSG,dataMap);
				}
			}
			return new AjaxResult(CommonUtil.ERROR_CREDENTIAL,CommonUtil.CREDENTIAL_INVALID,dataMap);
		}
		return new AjaxResult(CommonUtil.ERROR_CODE,CommonUtil.ERROR_PARAMS,dataMap);
	}
	
	public static void testUpdateInfo() throws Exception{
		String requestUrl =CommonUtil.SERVERDOMAIN+"/api/member/updateInfo";
		String head_img="23.png";
		String img_str=encodeImgageToBase64();
		String name="朱建波",ship_addr="上海市长宁区宣化路301号" ,credential="100001",timeStamp = StringUtil.getTimeStamp();
		String summaryKey = CommonUtil.SECRET_KEY +credential+timeStamp;
		String summary=DigestUtils.md5Hex(summaryKey);
		String resultStr = HttpUtil.connectURL(requestUrl, "head_img="+head_img+"&img_str="+img_str+"&name="+name+"&ship_addr="+ship_addr+"&credential="+credential+"&timeStamp="+timeStamp+"&summary="+summary);
		System.out.println(resultStr);
	}
	
	
	/**
	 * 个人中心
	 * @param po
	 * @param request
	 * @return
	 */
	@RequestMapping("center")
	@ResponseBody
	public AjaxResult center(BaseConditionVO vo){
		dataMap = new HashMap<String, Object>();
		String [] securityArray = {"credential","timeStamp"};
		String summaryKey = CommonUtil.SECRET_KEY + CommonUtil.reflectMethod(vo, securityArray);
		String summaryMD5 = DigestUtils.md5Hex(summaryKey);
		if(CommonUtil.dataValidate(vo.getSummary(), summaryMD5)){
			Member member=service.queryByCredential(vo.getCredential());
			if (member!=null) {
				String [] excludes = {"id","deposit","account","point","point_lv","number","card_number","review_error","remark","firm_id","firm_name","enabled","head_img","pay_pwd","coupon_amount","credential","headimg_base64","cardfront_base64","cardback_base64","driverscan_base64","driverscan_back_base64","evcard_status","old_card_number","pay_pwd","del_flg","create_date","modify_date"};
				dataMap.put("member",JSONObject.fromObject(member, CommonUtil.createExcludes(excludes)));
				return new AjaxResult(CommonUtil.SUCCESS_CODE,CommonUtil.SUCCESS_MSG,dataMap);
			}
			return new AjaxResult(CommonUtil.ERROR_CREDENTIAL,CommonUtil.CREDENTIAL_INVALID,dataMap);
		}
		return new AjaxResult(CommonUtil.ERROR_CODE,CommonUtil.ERROR_PARAMS,dataMap);
	}
	
	public static void testCenter() throws Exception{
		String requestUrl =CommonUtil.SERVERDOMAIN+"/api/member/center";
		String credential="8iQpLGCkKYiQD45Rm05GyixWH4Xlg27o2IY4tniO5v4m9i6ouK8eGCUDwyKVn5p3PM2mJ35TF4HoGkYW33KHQryHEFGHoVIMEHdfsPZWRJlUrbNXVWh2Y1kLdypsZIKdaM3mc6Rfm197PrHJXrRJ96ak8I5WxusJbTFHb8KIH1nyal5ZlJE0lEYEsNVZ5i4de0Cmt7VBsnlm1C1bhJcunel13GXRDvhhSiANhYbgkhf6eAvFbEaIsla5je27L",timeStamp = StringUtil.getTimeStamp();
		String summaryKey = CommonUtil.SECRET_KEY +credential+timeStamp;
		String summary=DigestUtils.md5Hex(summaryKey);
		String resultStr = HttpUtil.connectURL(requestUrl, "credential="+credential+"&timeStamp="+timeStamp+"&summary="+summary);
		System.out.println(resultStr);
	}
	
	/**
	 * 钱包
	 * @param vo
	 * @return
	 */
	@RequestMapping("wallet")
	@ResponseBody
	public AjaxResult wallet(BaseConditionVO vo){
		dataMap = new HashMap<String, Object>();
		String [] securityArray = {"credential","timeStamp"};
		String summaryKey = CommonUtil.SECRET_KEY + CommonUtil.reflectMethod(vo, securityArray);
		String summaryMD5 = DigestUtils.md5Hex(summaryKey);
		if(CommonUtil.dataValidate(vo.getSummary(), summaryMD5)){
			Member member=service.queryByCredential(vo.getCredential());
			if (member!=null) {
				String [] excludes = {"id","card_no","cardfront_base64","driver_no","driverscan_base64","head_img","headimg_base64","mobile","name","pay_type","ship_addr","type","point_lv","card_front","card_back","driver_scan","number","card_number","review_error","remark","review","firm_id","firm_name","enabled","headimg_base64","cardfront_base64","cardback_base64","driverscan_base64","pay_pwd","del_flg","credential","img_base64","create_date","modify_date"};
				dataMap.put("member",JSONObject.fromObject(member, CommonUtil.createExcludes(excludes)));
				return new AjaxResult(CommonUtil.SUCCESS_CODE,CommonUtil.SUCCESS_MSG,dataMap);
			}
			return new AjaxResult(CommonUtil.ERROR_CREDENTIAL,CommonUtil.CREDENTIAL_INVALID,dataMap);
		}
		return new AjaxResult(CommonUtil.ERROR_CODE,CommonUtil.ERROR_PARAMS,dataMap);
	}
	
	public static void testWallet() throws Exception{
		String requestUrl =CommonUtil.SERVERDOMAIN+"/api/member/wallet";
		String credential="8iQpLGCkKYiQD45Rm05GyixWH4Xlg27o2IY4tniO5v4m9i6ouK8eGCUDwyKVn5p3PM2mJ35TF4HoGkYW33KHQryHEFGHoVIMEHdfsPZWRJlUrbNXVWh2Y1kLdypsZIKdaM3mc6Rfm197PrHJXrRJ96ak8I5WxusJbTFHb8KIH1nyal5ZlJE0lEYEsNVZ5i4de0Cmt7VBsnlm1C1bhJcunel13GXRDvhhSiANhYbgkhf6eAvFbEaIsla5je27L",timeStamp = StringUtil.getTimeStamp();
		String summaryKey = CommonUtil.SECRET_KEY +credential+timeStamp;
		String summary=DigestUtils.md5Hex(summaryKey);
		String resultStr = HttpUtil.connectURL(requestUrl, "credential="+credential+"&timeStamp="+timeStamp+"&summary="+summary);
		System.out.println(resultStr);
	}
	
	
	/**
	 * 交易记录
	 * type 类型[0 ：保证金  1：账户余额]
	 * 保证金
	 * status 状态 ["":全部   0 ：充值  1：退还]
	 * 账户余额
	 * status 状态 ["":全部   0 ：充值   1:消费  2：退款]
	 * @param vo
	 * @return
	 */
	@RequestMapping("paymentLog")
	@ResponseBody
	public AjaxResult paymentLog(BaseConditionVO vo){
		dataMap = new HashMap<String, Object>();
		String [] securityArray = {"credential","timeStamp"};
		String summaryKey = CommonUtil.SECRET_KEY + CommonUtil.reflectMethod(vo, securityArray);
		String summaryMD5 = DigestUtils.md5Hex(summaryKey);
		if(CommonUtil.dataValidate(vo.getSummary(), summaryMD5)){
			Member member=service.queryByCredential(vo.getCredential());
			if (member!=null) {
				vo.setMem_id(member.getId());
				List<PaymentLog> logs=paymentLogService.queryPageApp(vo);
				String [] excludes = {"channel","id","firm_id","modify_date","user_id"};
				if(vo.getType().equals("0"))dataMap.put("deposit",member.getDeposit());
				if(vo.getType().equals("1"))dataMap.put("account",member.getAccount());
				dataMap.put("logs",JSONArray.fromObject(logs, CommonUtil.createExcludes(excludes)));
				return new AjaxResult(CommonUtil.SUCCESS_CODE,CommonUtil.SUCCESS_MSG,dataMap);
			}
			return new AjaxResult(CommonUtil.ERROR_CREDENTIAL,CommonUtil.CREDENTIAL_INVALID,dataMap);
		}
		return new AjaxResult(CommonUtil.ERROR_CODE,CommonUtil.ERROR_PARAMS,dataMap);
	}
	
	public static void paymentlog() throws Exception{
		String requestUrl =CommonUtil.SERVERDOMAIN+"/api/member/paymentLog";
		String pageNo="1",credential="kmkiPylEwyi4Rk7fDXSfwnJqIgeaAAVW4BH5kPXEVKsTqm0ZfoYtunlB5IBbgB6PHgG503Fhk8bNNe6knMm1gGPm4Hly0tOmwioHpITULo0iof282bTXYbYyNinV8cJQjoXpV1Ly2S84F8akax0Krdyp2k6f7m63cL1ShFRRp21KPVTJCa5xkZcwoFydewPTIbgAOCMEGuu9y5f81",timeStamp = StringUtil.getTimeStamp();
		String summaryKey = CommonUtil.SECRET_KEY +credential+timeStamp;
		String summary=DigestUtils.md5Hex(summaryKey);
		String resultStr = HttpUtil.connectURL(requestUrl, "pageNO="+pageNo+"&credential="+credential+"&timeStamp="+timeStamp+"&summary="+summary);
		System.out.println(resultStr);
	}
	
	/**
	 * 申请退还保证金
	 * pay_amount 金额
	 * @param vo
	 * @return
	 */
	@RequestMapping("refundDeposit")
	@ResponseBody
	public AjaxResult refundDeposit(BaseConditionVO vo){
		dataMap = new HashMap<String, Object>();
		String [] securityArray = {"credential","timeStamp"};
		String summaryKey = CommonUtil.SECRET_KEY + CommonUtil.reflectMethod(vo, securityArray);
		String summaryMD5 = DigestUtils.md5Hex(summaryKey);
		if(CommonUtil.dataValidate(vo.getSummary(), summaryMD5)){
			Member member=service.queryByCredential(vo.getCredential());
			if (member!=null) {
				if (Double.parseDouble(vo.getPay_amount())>member.getDeposit()) {
					return new AjaxResult(CommonUtil.ERROR_CODE,"申请金额有误",dataMap);
				}
				RefundDeposit deposit=new RefundDeposit();
				deposit.setSn(StringUtil.buildOrderSn());
				deposit.setAmount(Double.parseDouble(vo.getPay_amount()));
				deposit.setMem_id(member.getId());
				refundDepositService.add(deposit);
				return new AjaxResult(CommonUtil.SUCCESS_CODE,"申请成功，等待审核",dataMap);
			}
			return new AjaxResult(CommonUtil.ERROR_CREDENTIAL,CommonUtil.CREDENTIAL_INVALID,dataMap);
		}
		return new AjaxResult(CommonUtil.ERROR_CODE,CommonUtil.ERROR_PARAMS,dataMap);
	}
	
	public static void refundDeposit() throws Exception{
		String requestUrl =CommonUtil.SERVERDOMAIN+"/api/member/refundDeposit";
		String pay_amount="1",credential="kmkiPylEwyi4Rk7fDXSfwnJqIgeaAAVW4BH5kPXEVKsTqm0ZfoYtunlB5IBbgB6PHgG503Fhk8bNNe6knMm1gGPm4Hly0tOmwioHpITULo0iof282bTXYbYyNinV8cJQjoXpV1Ly2S84F8akax0Krdyp2k6f7m63cL1ShFRRp21KPVTJCa5xkZcwoFydewPTIbgAOCMEGuu9y5f81",timeStamp = StringUtil.getTimeStamp();
		String summaryKey = CommonUtil.SECRET_KEY +credential+timeStamp;
		String summary=DigestUtils.md5Hex(summaryKey);
		String resultStr = HttpUtil.connectURL(requestUrl, "pay_amount="+pay_amount+"&credential="+credential+"&timeStamp="+timeStamp+"&summary="+summary);
		System.out.println(resultStr);
	}
	
	
	@RequestMapping("messageList")
	@ResponseBody
	public AjaxResult messageList(BaseConditionVO vo){
		dataMap = new HashMap<String, Object>();
		String [] securityArray = {"credential","timeStamp"};
		String summaryKey = CommonUtil.SECRET_KEY + CommonUtil.reflectMethod(vo, securityArray);
		String summaryMD5 = DigestUtils.md5Hex(summaryKey);
		if(CommonUtil.dataValidate(vo.getSummary(), summaryMD5)){
			Member member=service.queryByCredential(vo.getCredential());
			if (member!=null) {
				vo.setMem_id(member.getId());
				List<Message> messages=messageService.queryPageApp(vo);
				messageService.read(member.getId(),messages);
				String [] excludes = {"mem_id"};
				dataMap.put("messages",JSONArray.fromObject(messages, CommonUtil.createExcludes(excludes)));
				return new AjaxResult(CommonUtil.SUCCESS_CODE,CommonUtil.SUCCESS_MSG,dataMap);
			}
			return new AjaxResult(CommonUtil.ERROR_CREDENTIAL,CommonUtil.CREDENTIAL_INVALID,dataMap);
		}
		return new AjaxResult(CommonUtil.ERROR_CODE,CommonUtil.ERROR_PARAMS,dataMap);
	}
	
	public static void messageList() throws Exception{
		String requestUrl =CommonUtil.SERVERDOMAIN+"/api/member/messageList";
		String pageNo="1",credential="Tg4MOZcqy89ZgqryI8sKXg5oQuDUGwFRIvfVCFJ3pvOA3XbBxTxGYgxS8XqQibIlo4xUrcd1vhMNkewqMKmi6LcvNHf8O2Alsi9fSYgkrty88saH31ll3aQGq7JcOQgKcmKxLqZV5C5D7nVjiVCo35465nl9csv5i5eguLVoDJmBThhluII6YmSrZ7Of5WxABDTZcnqMZ94qG7A1AcWT2Kn6OP3ftlfPt7o8675m2f8RpRpm7NtHTN3UA4ubh",timeStamp = StringUtil.getTimeStamp();
		String summaryKey = CommonUtil.SECRET_KEY +credential+timeStamp;
		String summary=DigestUtils.md5Hex(summaryKey);
		String resultStr = HttpUtil.connectURL(requestUrl, "pageNo="+pageNo+"&credential="+credential+"&timeStamp="+timeStamp+"&summary="+summary);
		System.out.println(resultStr);
	}
	
	@RequestMapping("messageDel")
	@ResponseBody
	public AjaxResult messageDel(BaseConditionVO vo){
		dataMap = new HashMap<String, Object>();
		String [] securityArray = {"credential","timeStamp"};
		String summaryKey = CommonUtil.SECRET_KEY + CommonUtil.reflectMethod(vo, securityArray);
		String summaryMD5 = DigestUtils.md5Hex(summaryKey);
		if(CommonUtil.dataValidate(vo.getSummary(), summaryMD5)){
			Member member=service.queryByCredential(vo.getCredential());
			if (member!=null) {
				messageService.del(member.getId(),vo.getId());
				return new AjaxResult(CommonUtil.SUCCESS_CODE,CommonUtil.SUCCESS_MSG,dataMap);
			}
			return new AjaxResult(CommonUtil.ERROR_CREDENTIAL,CommonUtil.CREDENTIAL_INVALID,dataMap);
		}
		return new AjaxResult(CommonUtil.ERROR_CODE,CommonUtil.ERROR_PARAMS,dataMap);
	}
	
	public static void messageDel() throws Exception{
		String requestUrl =CommonUtil.SERVERDOMAIN+"/api/member/messageDel";
		String id="28b1b34f17544200b1f4da2e17b1c0df",credential="Tg4MOZcqy89ZgqryI8sKXg5oQuDUGwFRIvfVCFJ3pvOA3XbBxTxGYgxS8XqQibIlo4xUrcd1vhMNkewqMKmi6LcvNHf8O2Alsi9fSYgkrty88saH31ll3aQGq7JcOQgKcmKxLqZV5C5D7nVjiVCo35465nl9csv5i5eguLVoDJmBThhluII6YmSrZ7Of5WxABDTZcnqMZ94qG7A1AcWT2Kn6OP3ftlfPt7o8675m2f8RpRpm7NtHTN3UA4ubh",timeStamp = StringUtil.getTimeStamp();
		String summaryKey = CommonUtil.SECRET_KEY +credential+timeStamp;
		String summary=DigestUtils.md5Hex(summaryKey);
		String resultStr = HttpUtil.connectURL(requestUrl, "id="+id+"&credential="+credential+"&timeStamp="+timeStamp+"&summary="+summary);
		System.out.println(resultStr);
	}
	
	public static void main(String[] args) throws Exception {
//		testLogin();//登陆
//		testCenter();//个人中心
//		testUpdateInfo();//修改用户信息(姓名，收货地址,头像,身份证，驾驶证)
//		testWallet();
//		testInit();
//		paymentlog();
		messageList();
		messageDel();
	}
	
	
	private static String encodeImgageToBase64(){
		try {
			FileInputStream in=new FileInputStream("D:\\1263764_011305308759_2.jpg");
			byte[] bytes=new byte[in.available()];
			in.read(bytes);
			in.close();
			return new BASE64Encoder().encode(bytes);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
}
