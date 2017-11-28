package com.cocosh.car.controller;

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
import com.cocosh.car.model.Coupon;
import com.cocosh.car.service.CouponService;

@Controller
@RequestMapping("manage/coupon")
public class CouponController extends BaseController {
	@Autowired
	private CouponService service;

	@RequiresPermissions("manage:coupon:list")
	@RequestMapping("list")
	public String list(BaseConditionVO vo, Model model) {
		Page<Coupon> page=service.queryPage(vo);
		model.addAttribute("page", page);
		model.addAttribute("vo", vo);
		return "car/coupon/list";
	}
	
	@RequiresPermissions("manage:coupon:add")
	@RequestMapping(value="add",method=RequestMethod.GET)
	public String add(Model model) {
		return "coupon/add";
	}

	@RequiresPermissions("manage:coupon:add")
	@RequestMapping("add")
	@ResponseBody
	public AjaxResult add(Coupon po) {
		if (service.add(po)) {
			return new AjaxResult("0");
		}
		return new AjaxResult("1");
	}

	@RequiresPermissions("manage:coupon:update")
	@RequestMapping("update/{id}")
	public String update(@PathVariable String id,Model model) {
		model.addAttribute("coupon",service.queryById(id));
		return "coupon/update";
	}

	@RequiresPermissions("manage:coupon:update")
	@RequestMapping("update")
	@ResponseBody
	public AjaxResult update(Coupon po) {
		if (service.update(po)) {
			return new AjaxResult("0");
		} else {
			return new AjaxResult("1");
		}
	}

	@RequiresPermissions("manage:coupon:del")
	@RequestMapping("del/{id}")
	@ResponseBody
	public AjaxResult del(@PathVariable String id) {
		if (service.del(id)) {
			return new AjaxResult("0");
		}
		return new AjaxResult("1");
	}
}
