package com.cocosh.nlf.controller;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cocosh.framework.base.AjaxResult;
import com.cocosh.framework.base.BaseConditionVO;
import com.cocosh.framework.base.BaseController;
import com.cocosh.framework.mybatis.Page;
import com.cocosh.nlf.model.OrderDisRateSetting;
import com.cocosh.nlf.service.OrderDisRateSettingService;

@Controller
@RequestMapping("manage/orderdisratesetting")
public class OrderDisRateSettingController extends BaseController {
	@Autowired
	private OrderDisRateSettingService service;

	@RequiresPermissions("manage:orderdisratesetting:list")
	@RequestMapping("list")
	public String list(BaseConditionVO vo, Model model) {
		Page<OrderDisRateSetting> page=service.queryPage(vo);
		model.addAttribute("page", page);
		model.addAttribute("vo", vo);
		return "nlf/userscore/rateSetting";
	}
	
	@RequiresPermissions("manage:orderdisratesetting:update")
	@RequestMapping("update")
	@ResponseBody
	public AjaxResult update(OrderDisRateSetting rate) {
		if (service.update(rate)) {
			return new AjaxResult("0");
		} else {
			return new AjaxResult("1");
		}
		
	}

}
