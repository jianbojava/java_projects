package com.cocosh.wap.api;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cocosh.framework.base.AjaxResult;
import com.cocosh.framework.base.BaseController;
import com.cocosh.framework.util.CommonUtil;
import com.cocosh.framework.util.SecurityUtil;
import com.cocosh.framework.util.StringUtil;
import com.cocosh.nlf.model.Activation;
import com.cocosh.nlf.service.ActivationService;
import com.cocosh.sys.model.User;
import com.cocosh.sys.service.DepartService;
import com.cocosh.sys.service.UserService;

@Controller
@RequestMapping("wapapi/register")
public class RegisterApiController extends BaseController {
	Map<String, Object> dataMap=null;
	@Autowired
	private UserService userService;
	@Autowired
	private ActivationService activationService;
	@Autowired
	private DepartService departService;
	
	/* 
	 * 用户注册
	 * */
	@RequestMapping("register")
	@ResponseBody
	public AjaxResult register(User po,String code){
		if(StringUtil.isEmpty(code))return new AjaxResult(CommonUtil.ERROR_CODE,"验证码不能为空",dataMap);
		dataMap = new HashMap<String, Object>();
		String sessionCode=readSession(po.getMobile());
		if(!code.equals(sessionCode))return new AjaxResult(CommonUtil.ERROR_CODE,"验证码输入错误",dataMap);
		User user =  new User();
		user.setMobile(po.getMobile());
		if(userService.queryByMobile(po.getMobile())!=null)return new AjaxResult(CommonUtil.ERROR_CODE,"手机号已被注册",dataMap);
		if(userService.addWap(po))return new AjaxResult(CommonUtil.SUCCESS_CODE,"注册成功",dataMap);
		return new AjaxResult(CommonUtil.SUCCESS_CODE,"系统异常,请稍后再试.",dataMap);
	}
	/* 
	 * 学员注册
	 * */
	@RequestMapping("studentsActivation")
	@ResponseBody
	public AjaxResult studentsActivation(User po,String code){
		dataMap = new HashMap<String, Object>();
		String sessionCode=readSession(po.getMobile());
		if((StringUtil.isEmpty(po.getMobile()))){
			return new AjaxResult(CommonUtil.ERROR_CODE,"请输入手机号",dataMap);
		}else if(StringUtil.isEmpty(sessionCode)){
			return new AjaxResult(CommonUtil.ERROR_CODE,"请先获取验证码",dataMap);
		}else if(StringUtil.isEmpty(code)){
			return new AjaxResult(CommonUtil.ERROR_CODE,"请输入验证码",dataMap);
		}else{
			po.setUser_type(2);
			po.setEnabled(0);
			if(!code.equals(sessionCode)){
				return new AjaxResult(CommonUtil.ERROR_CODE,"验证码有误",dataMap);
			}
			User user=userService.queryByMobile(po.getMobile());
			if(user!=null){
				  if(user.getEnabled()!=0){
					  return new AjaxResult(CommonUtil.ERROR_CODE,"手机号已激活",dataMap);
				  }
					return new AjaxResult(CommonUtil.SUCCESS_CODE,"验证成功",dataMap);
			}else{
				return new AjaxResult(CommonUtil.ERROR_CODE,"手机号不能被激活",dataMap);
			}
		}
	}
	/* 
	 * 学员注册设置密码
	 * */
	@RequestMapping("registerPwd")
	@ResponseBody
	public AjaxResult registerPwd(User po){
		dataMap = new HashMap<String, Object>();
		if(!StringUtil.isEmpty(po.getMobile())&&!StringUtil.isEmpty(po.getPassword())){	
			User user = userService.queryByMobile(po.getMobile());//手机号查询id
			po.setPassword(SecurityUtil.md5(po.getPassword()));
			po.setId(user.getId());//放入id
			po.setUser_type(2);//类型学员
			po.setIs_locked(0);//用户状态 0正常 1锁定
			po.setType("4");//用户类型 消费者or会员
			po.setEnabled(1);//未审核
			if(userService.update(po)){
				return new AjaxResult(CommonUtil.SUCCESS_CODE,"激活成功",dataMap);
			}
		}
		return new AjaxResult(CommonUtil.ERROR_CODE,CommonUtil.ERROR_PARAMS,dataMap);
	}
	/* 
	 * 员工/合伙人  激活码验证
	 * */
	@RequestMapping("partnerActivation")
	@ResponseBody
	public AjaxResult partnerActivation(Activation activation){
		dataMap = new HashMap<String, Object>();
		Activation a = activationService.checkNumber(activation.getNumber());
		if(a==null){
			return new AjaxResult(CommonUtil.ERROR_CODE,"激活码错误",dataMap);
		}
		if(a.getEnabled()==null||a.getEnabled()==1){
			User user = userService.queryById(a.getMember_id());
			if(user==null){
				return new AjaxResult("3","激活码已被使用",dataMap);
			}else{
				if(user.getEnabled()==0) return new AjaxResult("3","信息已提交至审核",dataMap);
				if(user.getEnabled()==2) return new AjaxResult("3","审核被拒绝",dataMap);
				return new AjaxResult("3","审核已通过",dataMap);
			}
		}else {
			return new AjaxResult(CommonUtil.SUCCESS_CODE,"激活码可用",dataMap);
		}
	}
	/* 
	 * 员工/合伙人  添加信息到数据库
	 * */
	@RequestMapping("partnerInfo")
	@ResponseBody
	public AjaxResult partnerActivationPwd(User po,String numbers,String code){
		String exp="^[1][0-9]{10}$";
		dataMap = new HashMap<String, Object>();
		if (!Pattern.matches(exp,po.getMobile())) {
			return new AjaxResult(CommonUtil.ERROR_CODE,"手机号格式不正确",dataMap);
		}
		String sessionCode=readSession(po.getMobile());
		if(!code.equals(sessionCode))return new AjaxResult(CommonUtil.ERROR_CODE,"验证码输入错误",dataMap);
		if(userService.queryByMobile(po.getMobile())!=null){
			return new AjaxResult(CommonUtil.ERROR_CODE,"手机号已被注册",dataMap);//-1  手机号已被注册
		}else{
			return userService.addWapPartner(po, numbers);
		}
	}
	
	
}
