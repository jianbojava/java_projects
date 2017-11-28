package com.cocosh.wap.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cocosh.framework.base.BaseController;
import com.cocosh.framework.payment.wxap.WXConfig;
import com.cocosh.framework.wx.CommonUtil;
import com.cocosh.framework.wx.SHA1Util;
import com.cocosh.nlf.model.Activation;
import com.cocosh.nlf.service.ActivationService;
import com.cocosh.sys.model.Depart;
import com.cocosh.sys.model.User;
import com.cocosh.sys.service.DepartService;
import com.cocosh.sys.service.UserService;

@Controller
@RequestMapping("wap/")
public class IndexWapController extends BaseController {
	@Autowired
	private ActivationService activationService;
	@Autowired
	private UserService userService;
	@Autowired
	private DepartService departService;
	public static String access_token = null;
    public static String jsapi_ticket = null;
    public static String time = null;
	/*
	 * 登录页
	 * */
	@RequestMapping("index")
	public String index() {
		if(getWapUser()!=null) return "redirect:/wap/my/my";
		return "wap/user/login";
	}
	
	/*
	 * 退出登陆
	 * */
	@RequestMapping("logout")
	public String logout() {
		Subject sub = SecurityUtils.getSubject();
		sub.getSession().setAttribute("wapuser", null);
		return "wap/user/login";
	}
	
	/*
	 * 忘记密码
	 * */
	@RequestMapping("forgetPwd")

	public String forgetPwd() {
		return "wap/user/forgetPwd";
	}
	
	/*
	 * 重置密码          合伙人/会议需求修改  暂未使用
	 * */
	@RequestMapping("resetPwd")
	public String resetPwd(String mobile,Model model) {
		User user = new User();
		user.setMobile(mobile);
		model.addAttribute(user);
		return "wap/user/resetPwd";
	}
	
	/*
	 * 设置密码    新用户、学员设置密码
	 * */
	@RequestMapping("setPwd")
	public String setPwd(String mobile,Model model) {
		User user = new User();
		user.setMobile(mobile);
		model.addAttribute(user);
		return "wap/user/setPwd";
	}
	
	/*
	 * 注册-选择身份
	 * */
	@RequestMapping("chooseIdentity")
	public String chooseIdentity() {
		return "wap/user/chooseIdentity";
	}
	
	/*
	 * 新用户注册
	 * */
	@RequestMapping("register")
	public String register() {
		return "wap/user/register";
	}
	
	/*
	 * 学员注册-激活
	 * */
	@RequestMapping("studentsActivation")
	public String studentsActivation() {
		return "wap/user/studentsActivation";
	}
	
	/*
	 * 员工注册-激活
	 * */
	@RequestMapping("partnerActivation")
	public String partnerActivation() {
		return "wap/user/partnerActivation";
	}

	/*
	 * 员工注册-填写信息
	 * */
	@RequestMapping("partnerInfo")
	public String partnerActivationPwd(Model model,String number) {
		Activation activation = new Activation();
		activation.setNumber(number);
		model.addAttribute("activation",activation);
		model.addAttribute("pdepart", departService.queryJustParent());
		return "wap/user/partnerInfo";
	}
	
	/*
	 * 员工注册-审核信息
	 * */
	@RequestMapping("partnerChecking")
	public String partnerChecking(Model model,Activation activation,String mobile) { 
		if(mobile==null){
		Activation a = activationService.checkNumber(activation.getNumber());
		User user = userService.queryById(a.getMember_id());
			model.addAttribute("myuser", user);
			return "wap/user/partnerChecking";
		}else{
			User user = userService.queryByMobile(mobile);
			model.addAttribute("myuser",user);
			return "wap/user/partnerChecking";
		}
	}
	
	/**
	 * 部门级联查询
	 * 
	 * @param pid
	 * @return
	 */
	@RequestMapping("depart/{pid}")
	@ResponseBody
	public List<Depart> departs(@PathVariable String pid) {
		return departService.queryChild(pid);
	}
	
	// 根据编号查询会员
	@RequestMapping("queryByNumber/{number}")
	@ResponseBody
	public Map<String, Object> queryByNumber(@PathVariable String number) {
		User user = userService.queryByNumber(number);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("user", user);
		if(user!=null){
			Depart depart=departService.queryById(user.getDepart_id());
			if(depart!=null&&depart.getGrade()!=1){
				map.put("grade"+depart.getGrade(), depart.getId());
				String pid=depart.getParent_id();
				Integer grade=depart.getGrade();
				for(int i=grade;i>1;i--){
					depart=departService.queryById(pid);
				    map.put("grade"+depart.getGrade(), depart.getId());
				    pid=depart.getParent_id();
				}
			}
		}
		return map;

	}
	

	/*调用微信端的扫描二维码*/
	@RequestMapping("shareinit")
	@ResponseBody
	public Map<String,Object> init(HttpServletRequest req){
		Map<String, Object> map=new HashMap<String, Object>();
		 String nonce_str = CommonUtil.CreateNoncestr();
	     String timestamp = String.valueOf(System.currentTimeMillis() / 1000);
	     String str;
       if(access_token == null){//(这个是策淘的)wx607757f5e788a3d2 dd001d1b01ce4a194691b6c7d3486d1f
    	   	access_token =  CommonUtil.getAccess_token(WXConfig.APPID, WXConfig.APPSECRET);
            jsapi_ticket = CommonUtil.getJsApiTicket(access_token);
            time = getTime();
        }else{
            if(!time.substring(0, 13).equals(getTime().substring(0, 13))){ //缓存，每小时刷新一次
            	access_token = null;
        	   	access_token =  CommonUtil.getAccess_token(WXConfig.APPID, WXConfig.APPSECRET);
                jsapi_ticket = CommonUtil.getJsApiTicket(access_token);
                time = getTime();
            }
        }
//	     System.out.println("access_token:"+access_token);    
//	     System.out.println("jsapi_ticket:"+jsapi_ticket);    
	     
	     
	     String	url = req.getParameter("url");
//	     System.out.println("url:"+url);
	     //注意这里参数名必须全部小写，且必须有序
        str = "jsapi_ticket=" + jsapi_ticket +
              "&noncestr=" + nonce_str +
              "&timestamp=" + timestamp +
              "&url=" + url;
        String signature =  SHA1Util.Sha1(str);
//        System.out.println("signature:"+signature);
//        System.out.println("nonce_str:"+nonce_str);
//        System.out.println("timestamp:"+timestamp);
        map.put("noncestr", nonce_str);
        map.put("timestamp", timestamp);
        map.put("signature", signature);
        map.put("appId", WXConfig.APPID);
		return map;
	}
	
	 /**
     * 获取当前系统时间 用来判断access_token是否过期
     * @return
     */
    public static String getTime(){
        Date dt=new Date();
        SimpleDateFormat sdf =new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return sdf.format(dt);
    }
	
}
