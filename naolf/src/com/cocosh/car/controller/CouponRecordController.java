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
import com.cocosh.car.model.CouponRecord;
import com.cocosh.car.service.CouponRecordService;

@Controller
@RequestMapping("manage/couponrecord")
public class CouponRecordController extends BaseController {
	@Autowired
	private CouponRecordService service;

	@RequiresPermissions("manage:couponrecord:list")
	@RequestMapping("list")
	public String list(BaseConditionVO vo, Model model) {
		Page<CouponRecord> page=service.queryPage(vo);
		model.addAttribute("page", page);
		model.addAttribute("vo", vo);
		return "couponrecord/list";
	}
	
	@RequiresPermissions("manage:couponrecord:add")
	@RequestMapping(value="add",method=RequestMethod.GET)
	public String add(Model model) {
		return "couponrecord/add";
	}

	@RequiresPermissions("manage:couponrecord:add")
	@RequestMapping("add")
	@ResponseBody
	public AjaxResult add(CouponRecord po) {
		if (service.add(po)) {
			return new AjaxResult("0");
		}
		return new AjaxResult("1");
	}

	@RequiresPermissions("manage:couponrecord:update")
	@RequestMapping("update/{id}")
	public String update(@PathVariable String id,Model model) {
		model.addAttribute("couponrecord",service.queryById(id));
		return "couponrecord/update";
	}

	@RequiresPermissions("manage:couponrecord:update")
	@RequestMapping("update")
	@ResponseBody
	public AjaxResult update(CouponRecord po) {
		if (service.update(po)) {
			return new AjaxResult("0");
		} else {
			return new AjaxResult("1");
		}
	}

	@RequiresPermissions("manage:couponrecord:del")
	@RequestMapping("del/{id}")
	@ResponseBody
	public AjaxResult del(@PathVariable String id) {
		if (service.del(id)) {
			return new AjaxResult("0");
		}
		return new AjaxResult("1");
	}
}
