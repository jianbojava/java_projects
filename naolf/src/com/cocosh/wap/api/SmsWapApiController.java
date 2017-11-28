package com.cocosh.wap.api;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cocosh.framework.base.AjaxResult;
import com.cocosh.framework.base.BaseController;
import com.cocosh.framework.util.CommonUtil;
import com.cocosh.framework.util.SecurityUtil;
import com.cocosh.framework.util.StringUtil;
import com.cocosh.nlf.model.Ticket;
import com.cocosh.nlf.model.UserTicket;
import com.cocosh.nlf.service.TicketService;
import com.cocosh.nlf.service.UserTicketService;
import com.cocosh.sys.model.User;
import com.cocosh.sys.service.DictService;
import com.cocosh.sys.service.UserService;
import com.cocosh.sys.service.VersionService;

@Controller
@RequestMapping("wapapi/sms")
public class SmsWapApiController extends BaseController{
	@Autowired
	private UserService userService;
	@Autowired
	private UserTicketService userTicketService;
	
	
	//新用户注册
	@RequestMapping("register")
	@ResponseBody
	public AjaxResult register(String mobile){
		Map<String, Object> dataMap = new HashMap<String, Object>();
		if(userService.queryByMobile(mobile)!=null)return new AjaxResult(CommonUtil.ERROR_CODE,"手机号已被注册",dataMap);
		String exp="^[1][0-9]{10}$";
		String code = StringUtil.getRandom(4);
		if (!Pattern.matches(exp,mobile)) {
			return new AjaxResult(CommonUtil.ERROR_CODE,"手机号格式不正确",dataMap);
		}
		String msg = "【脑立方】您好，您的验证码是"+code;
		try {
//			String returnString = HttpSender.batchSend(mobile, msg);//短信接口
//			System.out.println(returnString);//短信接口
			writeSession(mobile,code);
			dataMap.put("codes", code);
			System.out.println(msg+"--------------");
			return new AjaxResult(CommonUtil.SUCCESS_CODE,"验证码已发送",dataMap);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new AjaxResult(CommonUtil.ERROR_CODE,"发送失败，请稍后再试",dataMap);
	}
	@RequestMapping("version")
	@ResponseBody
	public AjaxResult version(String mobile){
		return sendmsg_have(mobile);
	}

	@RequestMapping("ticketCode")//卡券验证码
	@ResponseBody
	public AjaxResult ticketCode(String mobile,String refer_number,String sn) throws Exception{
		Map<String, Object> dataMap = new HashMap<String, Object>();
		User user = getWapUser();
		if(user==null) return new AjaxResult(CommonUtil.ERROR_CODE,"登陆超时",dataMap);
		user=userService.queryById(user.getId());
		if(StringUtil.isEmpty(mobile))return new AjaxResult(CommonUtil.ERROR_CODE,"请填写手机号",dataMap);
		if(StringUtil.isEmpty(user.getRefer_number())&&StringUtil.isEmpty(refer_number)){
			return new AjaxResult(CommonUtil.ERROR_CODE,"推荐人不可以为空",dataMap);
		}
		if(!StringUtil.isEmpty(user.getRefer_number())) refer_number=user.getRefer_number();
		User puser = userService.queryByNumber(refer_number);
		if(puser==null) return new AjaxResult(CommonUtil.ERROR_CODE,"推荐人不存在",dataMap);
		String en_mobile=SecurityUtil.encrypt(mobile);
		if(!StringUtil.isEmpty(sn)){
		   UserTicket userTicket = userTicketService.queryBySn(sn);
		   if(userTicket==null){
				return new AjaxResult(CommonUtil.ERROR_CODE,"请输入正确的卡券号",dataMap);
			}
		   if(userTicket.getUsed()!=0){
				 return new AjaxResult(CommonUtil.ERROR_CODE,"卡券已被使用",dataMap);
			 }
			if(!puser.getMobile().equals(en_mobile)){
				return new AjaxResult(CommonUtil.ERROR_CODE,"请输入推荐人的手机号",dataMap);
			}else{
				return sendmsg_have(mobile);
			}
		}else{
			if(!user.getMobile().equals(mobile))  return new AjaxResult(CommonUtil.ERROR_CODE,"请输入自己的手机号码",dataMap);
			return sendmsg_have(mobile);
		}
	}
	
	@RequestMapping("lessonCode")//课程验证码
	@ResponseBody
	public AjaxResult lessonCode(String mobile,String sn,String refer_number) throws Exception{
		Map<String, Object> dataMap = new HashMap<String, Object>();
		User user = getWapUser();
		if(user==null) return new AjaxResult(CommonUtil.ERROR_CODE,"登陆超时",dataMap);
		user=userService.queryById(user.getId());
		if(StringUtil.isEmpty(mobile))return new AjaxResult(CommonUtil.ERROR_CODE,"请填写手机号",dataMap);
		if(StringUtil.isEmpty(user.getRefer_number())&&StringUtil.isEmpty(refer_number)){
			return new AjaxResult(CommonUtil.ERROR_CODE,"推荐人不可以为空",dataMap);
		}
		if(!StringUtil.isEmpty(user.getRefer_number())) refer_number=user.getRefer_number();
		User puser = userService.queryByNumber(refer_number);
		if(puser==null) return  new AjaxResult(CommonUtil.ERROR_CODE,"推荐人不存在",dataMap);
		if(!user.getMobile().equals(mobile)){
			return new AjaxResult(CommonUtil.ERROR_CODE,"请输入自己的的手机号码",dataMap);
		}
		return sendmsg_have(mobile);
	}
	@RequestMapping("memberCode")//修改手机号旧验证码
	@ResponseBody
	public AjaxResult memberCode(String mobile,User po){
		Map<String, Object> dataMap = new HashMap<String, Object>();
		User user=getWapUser();
		if(user==null){
			return  new AjaxResult(CommonUtil.ERROR_CODE,"登陆超时",null);
		}
		user=userService.queryById(user.getId());
		if(user!=null){
			if(!user.getMobile().equals(po.getMobile())){
				return new AjaxResult(CommonUtil.ERROR_CODE,"请输入正确的旧手机号码",dataMap);
			}
			return sendmsg_have(mobile);
		}else{
			return new AjaxResult(CommonUtil.ERROR_CODE,"请输入正确的旧手机号码",dataMap);
		}
	}
	
	@RequestMapping("memberNotCode")//修改手机号新验证码
	@ResponseBody
	public AjaxResult memberNotCode(String mobile,User po){
		Map<String, Object> dataMap = new HashMap<String, Object>();
		User user = getWapUser();
		if(user==null){
			return  new AjaxResult(CommonUtil.ERROR_CODE,"登陆超时",null);
		}
		user=userService.queryById(user.getId());
		if(mobile.equals(user.getMobile())){return new AjaxResult(CommonUtil.ERROR_CODE,"不能和旧手机号相同",dataMap);}
		po = userService.queryByMobile(mobile);
		if(po==null){
			String exp="^[1][0-9]{10}$";
			String code = StringUtil.getRandom(4);
			if (!Pattern.matches(exp,mobile)) {
				return new AjaxResult(CommonUtil.ERROR_CODE,"手机号格式不正确",dataMap);
			}
			String phone = mobile;
			String msg = "【脑立方】您好，您的验证码是"+code;
			try {
//				String returnString = HttpSender.batchSend(phone, msg);//短信接口
//				System.out.println(returnString);//短信接口
				System.out.println(msg);
				writeSession(mobile,code);
				dataMap.put("codes", code);
				return new AjaxResult(CommonUtil.SUCCESS_CODE,"验证码已发送",dataMap);
				// TODO 处理返回值,参见HTTP协议文档
			} catch (Exception e) {
				// TODO 处理异常
				e.printStackTrace();
			}
		}else{
			return new AjaxResult(CommonUtil.ERROR_CODE,"手机号码已经被使用",dataMap);
		}
		return new AjaxResult(CommonUtil.ERROR_CODE,"参数有误",dataMap);
	}
	
	//已存在的发送验证码
	public  AjaxResult sendmsg_have(String mobile){
		Map<String, Object> dataMap = new HashMap<String, Object>();
		if(StringUtil.isEmpty(mobile)){
			return new AjaxResult(CommonUtil.ERROR_CODE,"请输入手机号",dataMap);
		}
		User user = userService.queryByMobile(mobile);
		if(user==null){
			return new AjaxResult(CommonUtil.ERROR_CODE,"手机号尚未注册",dataMap);
		}
		String exp="^[1][0-9]{10}$";
		String code = StringUtil.getRandom(4);
		if (!Pattern.matches(exp,mobile)) {
			return new AjaxResult(CommonUtil.ERROR_CODE,"手机号格式不正确",dataMap);
		}
		String msg = "【脑立方】您好，您的验证码是"+code;
		try {
//			String returnString = HttpSender.batchSend(mobile, msg);//短信接口
//			System.out.println(returnString);//短信接口
			writeSession(mobile,code);
			dataMap.put("codes", code);
			System.out.println(msg+"--------------");
			return new AjaxResult(CommonUtil.SUCCESS_CODE,"验证码已发送",dataMap);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new AjaxResult(CommonUtil.ERROR_CODE,"发送失败，请稍后再试",dataMap);
	}
	
}
