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
import com.cocosh.nlf.model.Activation;
import com.cocosh.nlf.service.ActivationService;

@Controller
@RequestMapping("manage/activation")
public class ActivationController extends BaseController {
	@Autowired
	private ActivationService service;

	@RequiresPermissions("manage:activation:list")
	@RequestMapping("list")
	public String list(BaseConditionVO vo, Model model) {
		Page<Activation> page=service.queryPage(vo);
		model.addAttribute("page", page);
		model.addAttribute("vo", vo);
		return "nlf/activation/list";
	}
	
	@RequiresPermissions("manage:activation:add")
	@RequestMapping(value="add",method=RequestMethod.GET)
	public String add(Model model) {
		return "nlf/activation/add";
	}

	@RequiresPermissions("manage:activation:add")
	@RequestMapping("add/{num}")
	@ResponseBody
	public AjaxResult add(@PathVariable Integer num,Activation po) {
		if (service.add(num)) {
			return new AjaxResult("0");
		}
		return new AjaxResult("1");
	}

	@RequiresPermissions("manage:activation:update")
	@RequestMapping("update/{id}")
	public String update(@PathVariable String id,Model model) {
		model.addAttribute("activation",service.queryById(id));
		return "nlf/activation/update";
	}

	@RequiresPermissions("manage:activation:update")
	@RequestMapping("update")
	@ResponseBody
	public AjaxResult update(Activation po) {
		if (service.update(po)) {
			return new AjaxResult("0");
		} else {
			return new AjaxResult("1");
		}
	}

	@RequiresPermissions("manage:activation:del")
	@RequestMapping("del/{id}")
	@ResponseBody
	public AjaxResult del(@PathVariable String id) {
		if (service.del(id)) {
			return new AjaxResult("0");
		}
		return new AjaxResult("1");
	}
}
