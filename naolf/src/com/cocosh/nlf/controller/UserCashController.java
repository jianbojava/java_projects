package com.cocosh.nlf.controller;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cocosh.framework.base.AjaxResult;
import com.cocosh.framework.base.BaseConditionVO;
import com.cocosh.framework.base.BaseController;
import com.cocosh.framework.mybatis.Page;
import com.cocosh.nlf.model.UserCash;
import com.cocosh.nlf.service.UserCashService;

@Controller
@RequestMapping("manage/usercash")
public class UserCashController extends BaseController {
	@Autowired
	private UserCashService service;

	@RequiresPermissions("manage:usercash:list")
	@RequestMapping("list")
	public String list(BaseConditionVO vo, Model model) {
		Page<UserCash> page=service.queryPage(vo);
		model.addAttribute("page", page);
		model.addAttribute("vo", vo);
		return "nlf/usercash/list";
	}
	
	@RequiresPermissions("manage:usercash:add")
	@RequestMapping(value="add",method=RequestMethod.GET)
	public String add(Model model) {
		return "usercash/add";
	}

	@RequiresPermissions("manage:usercash:add")
	@RequestMapping("add")
	@ResponseBody
	public AjaxResult add(UserCash po) {
		if (service.add(po)) {
			return new AjaxResult("0");
		}
		return new AjaxResult("1");
	}

	@RequiresPermissions("manage:usercash:update")
	@RequestMapping("update/{id}")
	public String update(@PathVariable String id,Model model) {
		model.addAttribute("usercash",service.queryById(id));
		return "usercash/update";
	}

	@RequiresPermissions("manage:usercash:update")
	@RequestMapping("update")
	@ResponseBody
	public AjaxResult update(UserCash po) {
		if (service.update(po)) {
			return new AjaxResult("0");
		} else {
			return new AjaxResult("1");
		}
	}
	@RequiresPermissions("manage:usercash:pay")
	@RequestMapping("pay/{id}")
	@ResponseBody
	public AjaxResult pay(@PathVariable String id) {
		String ret  = service.pay(id);
		if ("0".equalsIgnoreCase(ret)){
			return new AjaxResult("0");
		}else if("1".equalsIgnoreCase(ret)){
			return new AjaxResult("1","该用户没有积分信息");
		}else if("2".equalsIgnoreCase(ret)){
			return new AjaxResult("1","该用户的可兑换积分不够提现");
		}
		return new AjaxResult("1","系统错误");
	}

	@RequiresPermissions("manage:usercash:del")
	@RequestMapping("del/{id}")
	@ResponseBody
	public AjaxResult del(@PathVariable String id) {
		if (service.del(id)) {
			return new AjaxResult("0");
		}
		return new AjaxResult("1");
	}
}
