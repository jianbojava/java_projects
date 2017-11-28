package com.cocosh.sys.controller;

import java.util.List;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cocosh.framework.base.AjaxResult;
import com.cocosh.framework.base.BaseController;
import com.cocosh.sys.model.Dict;
import com.cocosh.sys.service.DictService;

@Controller
@RequestMapping("manage/dict")
public class DictController extends BaseController {
	@Autowired
	private DictService service;

	@RequiresPermissions("manage:dict:list")
	@RequestMapping("list")
	public String list(Model model) {
		Subject subject=SecurityUtils.getSubject();
		List<Dict> list=service.queryAll();
		model.addAttribute("list", list);
		model.addAttribute("isPerm", subject.isPermitted("manage:dict:update")?0:1);
		return "sys/dict/list";
	}
	
	@RequiresPermissions("manage:dict:add")
	@RequestMapping(value="add",method=RequestMethod.GET)
	public String add(Model model) {
		return "dict/add";
	}

	@RequiresPermissions("manage:dict:add")
	@RequestMapping("add")
	@ResponseBody
	public AjaxResult add(Dict po) {
		if (service.add(po)) {
			return new AjaxResult("0");
		}
		return new AjaxResult("1");
	}

	@RequiresPermissions("manage:dict:update")
	@RequestMapping("update")
	@ResponseBody
	public AjaxResult update(Dict po) {
		if (service.update(po)) {
			return new AjaxResult("0");
		} else {
			return new AjaxResult("1");
		}
	}

	@RequiresPermissions("manage:dict:del")
	@RequestMapping("del/{id}")
	@ResponseBody
	public AjaxResult del(@PathVariable String id) {
		if (service.del(id)) {
			return new AjaxResult("0");
		}
		return new AjaxResult("1");
	}
	
	@RequestMapping("childs/{code}")
	@ResponseBody
	public List<Dict> childs(@PathVariable String code){
		return service.queryCAll(code);
	}
}
