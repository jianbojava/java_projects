package com.cocosh.sys.controller;

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
import com.cocosh.sys.model.Version;
import com.cocosh.sys.service.VersionService;

@Controller
@RequestMapping("manage/version")
public class VersionController extends BaseController {
	@Autowired
	private VersionService service;

	@RequiresPermissions("manage:version:list")
	@RequestMapping("list")
	public String list(BaseConditionVO vo, Model model) {
		Page<Version> page=service.queryPage(vo);
		model.addAttribute("page", page);
		model.addAttribute("vo", vo);
		return "sys/version/list";
	}
	
	@RequiresPermissions("manage:version:add")
	@RequestMapping(value="add",method=RequestMethod.GET)
	public String add(Model model) {
		return "sys/version/add";
	}

	@RequiresPermissions("manage:version:add")
	@RequestMapping("add")
	@ResponseBody
	public AjaxResult add(Version po) {
		if (service.add(po)) {
			return new AjaxResult("0");
		}
		return new AjaxResult("1");
	}

	@RequiresPermissions("manage:version:update")
	@RequestMapping("update/{id}")
	public String update(@PathVariable String id,Model model) {
		model.addAttribute("version",service.queryById(id));
		return "sys/version/update";
	}

	@RequiresPermissions("manage:version:update")
	@RequestMapping("update")
	@ResponseBody
	public AjaxResult update(Version po) {
		if (service.update(po)) {
			return new AjaxResult("0");
		} else {
			return new AjaxResult("1");
		}
	}

	@RequiresPermissions("manage:version:del")
	@RequestMapping("del/{id}")
	@ResponseBody
	public AjaxResult del(@PathVariable String id) {
		if (service.del(id)) {
			return new AjaxResult("0");
		}
		return new AjaxResult("1");
	}
}
