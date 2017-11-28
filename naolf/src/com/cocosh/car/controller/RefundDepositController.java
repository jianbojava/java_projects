package com.cocosh.car.controller;

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
import com.cocosh.car.model.RefundDeposit;
import com.cocosh.car.service.RefundDepositService;

@Controller
@RequestMapping("manage/refund")
public class RefundDepositController extends BaseController {
	@Autowired
	private RefundDepositService service;

	@RequiresPermissions("manage:refund:list")
	@RequestMapping("list")
	public String list(BaseConditionVO vo, Model model) {
		Page<RefundDeposit> page=service.queryPage(vo);
		model.addAttribute("page", page);
		model.addAttribute("vo", vo);
		return "car/refund/list";
	}

	@RequiresPermissions("manage:refund:update")
	@RequestMapping("update")
	@ResponseBody
	public AjaxResult update(RefundDeposit po) {
		if (service.update(po)) {
			return new AjaxResult("0");
		} else {
			return new AjaxResult("1");
		}
	}
	
}
