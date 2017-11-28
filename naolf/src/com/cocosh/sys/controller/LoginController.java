package com.cocosh.sys.controller;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.DisabledAccountException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.cocosh.framework.base.BaseController;
import com.cocosh.framework.util.HttpUtil;
import com.cocosh.framework.util.SecurityUtil;
import com.cocosh.sys.service.UserService;

@RequestMapping("manage")
@Controller
public class LoginController extends BaseController {
	@Autowired
	private UserService service;

	@RequestMapping(value = "login", method = RequestMethod.GET)
	public String login() {
		if (getUser() != null) {
			return "redirect:/manage/index";
		}
		return "login";
	}

	@RequestMapping(value = "login", method = RequestMethod.POST)
	public String login(HttpServletRequest req) {
		String username = req.getParameter("username");
		String password = req.getParameter("password");
		String kaptcha = req.getParameter("kaptcha");
		String kaptchaExpected = (String) req.getSession().getAttribute(
				com.google.code.kaptcha.Constants.KAPTCHA_SESSION_KEY);
		if (!kaptcha.equalsIgnoreCase(kaptchaExpected)) {
			req.setAttribute("message", "验证码错误");
			return "login";
		}
		UsernamePasswordToken token = new UsernamePasswordToken(username,
				SecurityUtil.md5(password));
		Subject currentUser = SecurityUtils.getSubject();
		try {
			currentUser.login(token);
		} catch (LockedAccountException e) {
			req.setAttribute("message", "账号被锁定");
			return "login";
		}catch (DisabledAccountException e) {
			req.setAttribute("message", "账号未审核");
			return "login";
		}catch (IncorrectCredentialsException e) {
			req.setAttribute("message", "权限不足");
			return "login";
		}catch (AuthenticationException e) {
			req.setAttribute("message", "用户名或密码错误");
			return "login";
		}
		//更新登录IP
		service.updateIp(HttpUtil.getIpAddr(req),getUser().getId());
		return "redirect:/manage/index";
	}

	@RequestMapping("index")
	public String index() {
		return "index";
	}
	
//	@RequiresPermissions("manage:main")
	@RequestMapping("main")
	public String welcome(Model model){
		return "main";
	}

	@RequestMapping("ztree")
	public String ztree() {
		return "ztree";
	}

	@RequestMapping("ueditor")
	public String ueditor() {
		return "ueditor";
	}
	
}
