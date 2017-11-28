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
import com.cocosh.nlf.model.Lesson;
import com.cocosh.nlf.service.LessonService;
import com.cocosh.nlf.service.RuleService;

@Controller
@RequestMapping("manage/lesson")
public class LessonController extends BaseController {
	@Autowired
	private LessonService service;
	@Autowired
	private RuleService ruleService;

	@RequiresPermissions("manage:lesson:list")
	@RequestMapping("list")
	public String list(BaseConditionVO vo, Model model) {
		Page<Lesson> page=service.queryPage(vo);
		model.addAttribute("page", page);
		model.addAttribute("vo", vo);
		return "nlf/lesson/list";
	}

	@RequiresPermissions("manage:lesson:list")
	@RequestMapping("list1")
	public String list1(BaseConditionVO vo, Model model) {
		Page<Lesson> page=service.queryPage(vo);
		model.addAttribute("page", page);
		model.addAttribute("vo", vo);
		return "nlf/lesson/list1";
	}
	
	@RequiresPermissions("manage:lesson:add")
	@RequestMapping(value="add",method=RequestMethod.GET)
	public String add(Model model) {
		model.addAttribute("rule",ruleService.queryAll());
		return "nlf/lesson/add";
	}

	@RequiresPermissions("manage:lesson:add")
	@RequestMapping("add")
	@ResponseBody
	public AjaxResult add(Lesson po) {
		if(service.checkName(po.getName())!=null) return new AjaxResult("2");
		if (service.add(po)) {
			return new AjaxResult("0");
		}
		return new AjaxResult("1");
	}

	@RequiresPermissions("manage:lesson:update")
	@RequestMapping("update/{id}")
	public String update(@PathVariable String id,Model model) {
		model.addAttribute("lesson",service.queryById(id));
		model.addAttribute("rule",ruleService.queryAll());
		return "nlf/lesson/update";
	}

	@RequiresPermissions("manage:lesson:update")
	@RequestMapping("update")
	@ResponseBody
	public AjaxResult update(Lesson po) {
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
}
