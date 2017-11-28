package com.cocosh.nlf.controller;

import org.apache.shiro.authz.annotation.Logical;
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
import com.cocosh.nlf.model.Rule;
import com.cocosh.nlf.service.RuleService;

@Controller
@RequestMapping("manage/rule")
public class RuleController extends BaseController {
	@Autowired
	private RuleService service;

	@RequiresPermissions("manage:rule:list")
	@RequestMapping("list")
	public String list(BaseConditionVO vo, Model model) {
		Page<Rule> page=service.queryPage(vo);
		model.addAttribute("page", page);
		model.addAttribute("vo", vo);
		return "nlf/rule/list";
	}
	
	@RequiresPermissions("manage:rule:add")
	@RequestMapping(value="add",method=RequestMethod.GET)
	public String add(Model model) {
		return "nlf/rule/add";
	}

	@RequiresPermissions("manage:rule:add")
	@RequestMapping("add")
	@ResponseBody
	public AjaxResult add(Rule po) {
		if(service.queryRule(po)!=null){
			return new AjaxResult("2");//编号已存在
		}
		if (service.add(po)) {
			return new AjaxResult("0");
		}
		return new AjaxResult("1");
	}

	@RequiresPermissions("manage:rule:update")
	@RequestMapping("update/{id}")
	public String update(@PathVariable String id,Model model) {
		model.addAttribute("rule",service.queryById(id));
		return "nlf/rule/update";
	}

	@RequiresPermissions("manage:rule:update")
	@RequestMapping("update")
	@ResponseBody
	public AjaxResult update(Rule po) {
		Rule rule=service.queryRule(po);
		if(rule!=null&&(!rule.getId().equals(po.getId()))){
			return new AjaxResult("2");//编号已存在
		}
		if (service.update(po)) {
			return new AjaxResult("0");
		} else {
			return new AjaxResult("1");
		}
	}



	@RequiresPermissions(value={"manage:lesson:del","manage:lesson:cdel"},logical=Logical.OR)
	@RequestMapping("del/{flg}/{id}")
	@ResponseBody
	public AjaxResult del(@PathVariable Integer flg,@PathVariable String id) {
		if (service.del(flg,id)) {
			return new AjaxResult("0");
		}
		return new AjaxResult("1");
	}
	@RequiresPermissions(value={"manage:lesson:del","manage:lesson:cdel"},logical=Logical.OR)
	@RequestMapping("status/{flg}/{id}")
	@ResponseBody
	public AjaxResult status(@PathVariable Integer flg,@PathVariable String id) {
		if (service.del(flg,id)) {
			return new AjaxResult("0");
		}
		return new AjaxResult("1");
	}
}
