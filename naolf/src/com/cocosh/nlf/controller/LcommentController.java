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
import com.cocosh.nlf.model.Lcomment;
import com.cocosh.nlf.service.LcommentService;
import com.cocosh.nlf.service.LessonService;

@Controller
@RequestMapping("manage/lcomment")
public class LcommentController extends BaseController {
	@Autowired
	private LcommentService service;
	@Autowired
	private LessonService lessonservice;

	@RequiresPermissions("manage:lcomment:list")
	@RequestMapping("list")
	public String list(BaseConditionVO vo, Model model) {
		Page<Lcomment> page=service.queryPage(vo);
		model.addAttribute("page", page);
		model.addAttribute("vo", vo);
		model.addAttribute("lesson", lessonservice.queryAll());
		return "nlf/lcomment/list";
	}
	
	@RequiresPermissions("manage:lcomment:add")
	@RequestMapping(value="add",method=RequestMethod.GET)
	public String add(Model model) {
		return "nlf/lcomment/add";
	}

	@RequiresPermissions("manage:lcomment:add")
	@RequestMapping("add")
	@ResponseBody
	public AjaxResult add(Lcomment po) {
		if (service.add(po)) {
			return new AjaxResult("0");
		}
		return new AjaxResult("1");
	}

	@RequiresPermissions("manage:lcomment:update")
	@RequestMapping("update/{id}")
	public String update(@PathVariable String id,Model model) {
		model.addAttribute("lcomment",service.queryById(id));
		return "nlf/lcomment/update";
	}

	@RequiresPermissions("manage:lcomment:update")
	@RequestMapping("update")
	@ResponseBody
	public AjaxResult update(Lcomment po) {
		if (service.update(po)) {
			return new AjaxResult("0");
		} else {
			return new AjaxResult("1");
		}
	}

	@RequiresPermissions("manage:lcomment:del")
	@RequestMapping("del/{id}")
	@ResponseBody
	public AjaxResult del(@PathVariable String id) {
		if (service.del(id)) {
			return new AjaxResult("0");
		}
		return new AjaxResult("1");
	}
}
