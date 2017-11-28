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
import com.cocosh.car.model.Dot;
import com.cocosh.car.service.DotService;

@Controller
@RequestMapping("manage/dot")
public class DotController extends BaseController {
	@Autowired
	private DotService service;

	@RequiresPermissions("manage:dot:list")
	@RequestMapping("list")
	public String list(BaseConditionVO vo, Model model) {
		Page<Dot> page=service.queryPage(vo);
		model.addAttribute("page", page);
		model.addAttribute("vo", vo);
		return "car/dot/list";
	}
	
	@RequiresPermissions("manage:dot:add")
	@RequestMapping(value="add",method=RequestMethod.GET)
	public String add(Model model) {
		return "car/dot/add";
	}

	@RequiresPermissions("manage:dot:add")
	@RequestMapping("add")
	@ResponseBody
	public AjaxResult add(Dot po) {
		if (service.add(po)) {
			return new AjaxResult("0");
		}
		return new AjaxResult("1");
	}

	@RequiresPermissions("manage:dot:update")
	@RequestMapping("update/{id}")
	public String update(@PathVariable String id,Model model) {
		model.addAttribute("dot",service.queryById(id));
		return "car/dot/update";
	}

	@RequiresPermissions("manage:dot:update")
	@RequestMapping("update")
	@ResponseBody
	public AjaxResult update(Dot po) {
		if (service.update(po)) {
			return new AjaxResult("0");
		} else {
			return new AjaxResult("1");
		}
	}

	@RequiresPermissions("manage:dot:del")
	@RequestMapping("del/{flg}/{id}")
	@ResponseBody
	public AjaxResult del(@PathVariable Integer flg,@PathVariable String id) {
		if (service.del(flg,id)) {
			return new AjaxResult("0");
		}
		return new AjaxResult("1");
	}
}
