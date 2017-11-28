package com.cocosh.wap.api;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.ejb.ApplicationException;
import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cocosh.framework.base.AjaxResult;
import com.cocosh.framework.base.BaseController;
import com.cocosh.framework.util.CommonUtil;
import com.cocosh.framework.util.OssUtil;
import com.cocosh.framework.util.SecurityUtil;
import com.cocosh.framework.util.StringUtil;
import com.cocosh.nlf.model.Appoint;
import com.cocosh.nlf.model.Student;
import com.cocosh.nlf.model.Upgrade;
import com.cocosh.nlf.model.UserCash;
import com.cocosh.nlf.model.UserScoreInfo;
import com.cocosh.nlf.service.AppointService;
import com.cocosh.nlf.service.StudentService;
import com.cocosh.nlf.service.UpgradeService;
import com.cocosh.nlf.service.UserCashService;
import com.cocosh.nlf.service.UserScoreInfoService;
import com.cocosh.sys.model.User;
import com.cocosh.sys.service.UserService;

@Controller
@RequestMapping("myapi/")
public class MyApiController extends BaseController {
	Map<String, Object> dataMap=null;
	@Autowired
	private AppointService appointService;
	@Autowired
	private StudentService studentService;
	@Autowired
	private UserService userService;
	@Autowired
	private UpgradeService upgradeService;
	@Autowired
	private UserCashService userCashService;
	@Autowired
	private UserScoreInfoService scoreService;

	/* 
	 * 课程预约
	 * */
	@RequestMapping("orderList")
	@ResponseBody
	public AjaxResult aboutStatus(Appoint po){
		
		
		return new AjaxResult(CommonUtil.ERROR_CODE,"预约失败",dataMap);
	}
	
	/* 
	 * 添加孩子信息
	 * */
	@RequestMapping("memberAddChild")
	@ResponseBody
	public AjaxResult memberAddChild(Student student){
		Subject subject = SecurityUtils.getSubject();
		User user = (User) subject.getSession().getAttribute("wapuser");
		if(user==null){
			return new AjaxResult(CommonUtil.ERROR_CODE,"登陆超时",dataMap);
		}
		student.setUser_id(user.getId());
		if(studentService.add(student)){
			return new AjaxResult(CommonUtil.SUCCESS_CODE,"添加成功",dataMap);
		}else{
			return new AjaxResult(CommonUtil.ERROR_CODE,"添加失败",dataMap);
		}
	}
	

	/* 
	 * 更新个人信息
	 * */
	@RequestMapping("updateMember")
	@ResponseBody
	public AjaxResult updateMember(User po,String oldpwd){
		Subject subject = SecurityUtils.getSubject();
		User user = (User) subject.getSession().getAttribute("wapuser");
		if(user==null){
			return new AjaxResult(CommonUtil.ERROR_CODE,"登陆超时",dataMap);
		}
		po.setId(user.getId());
		if(!StringUtil.isEmpty(po.getUsername())){
			if(userService.checkUser(po) != null){return new AjaxResult(CommonUtil.ERROR_CODE,"该用户名已被使用",dataMap);}
		}
		if(!StringUtil.isEmpty(po.getPassword())){
			if(StringUtil.isEmpty(oldpwd))  return new AjaxResult(CommonUtil.ERROR_CODE,"原密码不可以为空",dataMap); 
			String mdoldpwd=SecurityUtil.md5(oldpwd);
			if(!mdoldpwd.equals(user.getPassword())){
				 return new AjaxResult(CommonUtil.ERROR_CODE,"原密码输入有误",dataMap); 
			}
			po.setPassword(SecurityUtil.md5(po.getPassword()));
		}
		if(userService.update(po)){
			return new AjaxResult(CommonUtil.SUCCESS_CODE,"修改成功",dataMap);
		}else{
			return new AjaxResult(CommonUtil.ERROR_CODE,"修改失败",dataMap);
		}
	}
	

	/* 
	 *旧手机号验证
	 * */
	@RequestMapping("memberUpdatePhone")
	@ResponseBody
	public AjaxResult memberUpdatePhone(User po,String code){
		Subject subject = SecurityUtils.getSubject();
		User user = (User) subject.getSession().getAttribute("wapuser");
		if(user==null){
			return new AjaxResult(CommonUtil.ERROR_CODE,"登陆超时",null);
		}
			String sessionCode=readSession(po.getMobile());
			if(code.equals(sessionCode)){
				writeSession(po.getMobile(),null);
				return new AjaxResult(CommonUtil.SUCCESS_CODE,"验证成功",dataMap);
			}else{
				return new AjaxResult(CommonUtil.ERROR_CODE,"验证码输入错误",dataMap);
			}
	}
	

	/* 
	 *新手机号验证
	 * */
	@RequestMapping("memberUpdateNewPhone")
	@ResponseBody
	public AjaxResult memberUpdateNewPhone(User po,String code){
		Subject subject = SecurityUtils.getSubject();
		User user = (User) subject.getSession().getAttribute("wapuser");
		po.setId(user.getId());
		user = userService.queryByMobile(po.getMobile());
		if(user==null){
			String sessionCode=readSession(po.getMobile());
			if(code.equals(sessionCode)){
				writeSession(po.getMobile(),null);
				return updateMember(po,null);
			}else{
				return new AjaxResult(CommonUtil.ERROR_CODE,"验证码输入错误",dataMap);
			}
		}else{
			return new AjaxResult(CommonUtil.ERROR_CODE,"该手机号已被使用",dataMap);
		}
	}
	
	/* 
	 *旧手机号验证
	 * */
	@RequestMapping("updatehead")
	@ResponseBody
	public AjaxResult updatehead(HttpServletRequest request){
		Subject subject = SecurityUtils.getSubject();
		User user = (User) subject.getSession().getAttribute("wapuser");
		if(user==null){
			return new AjaxResult(CommonUtil.ERROR_CODE,"登陆超时",null);
		}
		String filePath = OssUtil.uploadFile(request, OssUtil.BUCKET_MANAGE);
		if (filePath != null) {
			User upuser=new User();
			upuser.setId(user.getId());
			upuser.setHead_img(filePath);
			userService.update(upuser);
			return new AjaxResult("0", filePath);
		}
		return new AjaxResult("1", "");
	}

	
	/* 
	 *卡券升级
	 * */
	@RequestMapping("upgrade")
	@ResponseBody
	public AjaxResult upgrade(Upgrade po){
		if(getWapUser()==null){
			return new AjaxResult(CommonUtil.ERROR_CODE,"登陆超时",null);
		}
		return upgradeService.toUpgrade(po);
	}
	
	/* 
	 *卡券升级积分抵扣
	 * */
	@RequestMapping("upgradebyjf")
	@ResponseBody
	public AjaxResult upgradebyjf(Upgrade po){
		if(getWapUser()==null){
			return new AjaxResult(CommonUtil.ERROR_CODE,"登陆超时",null);
		}
		return upgradeService.upgradebyjf(po.getUser_ticket_id1());
	}
	
	/* 
	 *升级支付验证
	 * */
	@RequestMapping("upgradeValid/{ut_id1}")
	@ResponseBody
	public AjaxResult upgradeValid(@PathVariable String ut_id1){
		if(getWapUser()==null){
			return new AjaxResult(CommonUtil.ERROR_CODE,"登陆超时",null);
		}
		return upgradeService.upgradeValid(ut_id1);
	}
	
	
	
	/**
	 * 脑立方积分提现
	 * @param po
	 * @param model
	 * @return
	 */
	@RequestMapping("Withdrawals")
	@ResponseBody
	public Map<String, Object> Withdrawals(HttpServletRequest req,String type){
		Map<String, Object> dataMap=new HashMap<String,Object>();
		BigDecimal amount=BigDecimal.valueOf(Double.valueOf(req.getParameter("amount")));//输入的金额
		User wapuser=getWapUser();
		if(wapuser==null){
			dataMap.put("code", "-1");
			dataMap.put("msg", "登陆超时！");
			return dataMap;
		}
		BigDecimal KD_SCORE=BigDecimal.ZERO;
		UserScoreInfo scoreInfo=scoreService.queryByUser(wapuser.getId());
		if(scoreInfo!=null)KD_SCORE=scoreInfo.getKD_SCORE();
		if(KD_SCORE.compareTo(amount)<0){
			dataMap.put("code", "-1");
			dataMap.put("msg", "会员可提现积分不足！");
			return dataMap;
		}
		User user = userService.queryById(wapuser.getId());
		if(StringUtil.isEmpty(user.getBank_name())||StringUtil.isEmpty(user.getBank_no())||StringUtil.isEmpty(user.getBank_user())
				||StringUtil.isEmpty(user.getAli_no())){
			dataMap.put("code", "1");
			dataMap.put("msg", "请先完善支付信息！");
			return dataMap;
		}
		UserCash userCash = new UserCash();
		userCash.setUser_id(wapuser.getId());
		userCash.setKd_score(amount);
		userCash.setType(Integer.valueOf(type));
		userCash.setStatus(0);
		userCash.setApply_date(new Date());
		userCashService.add(userCash);
		dataMap.put("code", "0");
		dataMap.put("msg", "信息已提交至审核！");
		return dataMap;
	}
	
	
}
