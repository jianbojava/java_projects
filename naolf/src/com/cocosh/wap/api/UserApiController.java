package com.cocosh.wap.api;

import java.util.HashMap;
import java.util.Map;

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
import com.cocosh.sys.model.User;
import com.cocosh.sys.service.UserService;

@Controller
@RequestMapping("wapapi/")
public class UserApiController extends BaseController {
	Map<String, Object> dataMap=null;
	@Autowired
	private UserService userService;
	
	/* 
	 * 登录页面api接口
	 * */
	@RequestMapping("login")
	@ResponseBody
	public AjaxResult login(User po,String number){
		dataMap = new HashMap<String, Object>();
		if(!StringUtil.isEmpty(po.getPassword())&&(!StringUtil.isEmpty(number))){
			String password = SecurityUtil.md5(po.getPassword()); 
			User user=userService.loginWap(number,password);
			if(user!=null){
				Subject subject = SecurityUtils.getSubject();
				subject.getSession().setAttribute("wapuser", user);
				if(user.getIs_locked()==1){
					return new AjaxResult(CommonUtil.ERROR_CODE,"用户已被禁用",dataMap);
				}
				if(user.getEnabled()!=null&&user.getEnabled()==1){
					return new AjaxResult(CommonUtil.SUCCESS_CODE,"登录成功",dataMap);
				}else{
					return new AjaxResult(CommonUtil.ERROR_CODE,"用户尚未通过审核",dataMap);
				}
			}else{
				return new AjaxResult(CommonUtil.ERROR_CODE,"账号或密码错误",dataMap);
			}
		}
		return new AjaxResult(CommonUtil.ERROR_CODE,"账号或密码不能为空",dataMap);
	}
	/* 
	 * 忘记密码验证手机号和验证码
	 * */
	@RequestMapping("forgetPwd")
	@ResponseBody
	public AjaxResult forgetPwd(User po,String code){
		dataMap = new HashMap<String, Object>();
		po.setIs_locked(0);//账号状态
		String sessionCode=readSession(po.getMobile());
		if(StringUtil.isEmpty(sessionCode)){
			return new AjaxResult(CommonUtil.ERROR_CODE,"请先获取验证码",dataMap);
		}else if(StringUtil.isEmpty(code)){
			return new AjaxResult(CommonUtil.ERROR_CODE,"请输入验证码",dataMap);
		}else{
			if(!code.equals(sessionCode)){
				return new AjaxResult(CommonUtil.ERROR_CODE,"验证码有误",dataMap);
			}else if(userService.queryByMobile(po.getMobile())!=null){
				//验证码输入成功后清空session
				writeSession(po.getMobile(),null);
				return new AjaxResult(CommonUtil.SUCCESS_CODE,"验证成功",dataMap);
			}else{
				return new AjaxResult(CommonUtil.ERROR_CODE,"手机号未注册",dataMap);
			}
		}
	}
	/* 
	 * 修改密码  合伙人和员工注册时也通过此页面设置密码
	 * */
	@RequestMapping("resetPwd")
	@ResponseBody
	public AjaxResult resetPwd(User po){
		dataMap = new HashMap<String, Object>();
		if(!StringUtil.isEmpty(po.getPassword())){
			po.setPassword(SecurityUtil.md5(po.getPassword()));
			if(userService.updatePwdByMobile(po)){
				return new AjaxResult(CommonUtil.SUCCESS_CODE,"密码修改成功",dataMap);
			}
		}
		return new AjaxResult(CommonUtil.ERROR_CODE,"密码不能为空",dataMap);
	}
	
}
