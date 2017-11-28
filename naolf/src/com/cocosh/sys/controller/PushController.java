package com.cocosh.sys.controller;

import java.util.List;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cocosh.framework.base.AjaxResult;
import com.cocosh.framework.base.BaseController;
import com.cocosh.sys.model.Push;
import com.cocosh.sys.service.PushService;

@Controller
@RequestMapping("manage/push")
public class PushController extends BaseController{
	@Autowired
	private PushService service;
	
	@RequiresPermissions("manage:push:list")
	@RequestMapping("list")
	public String list(Push po,Model model){
		return "sys/push/list";
	}
	
	@RequiresPermissions("manage:push:add")
	@RequestMapping("add")
	@ResponseBody
	public AjaxResult add(@RequestBody Push po) {
		if (service.add(po)) {
			return new AjaxResult("0", "");
		}
		return new AjaxResult("1", "");
	}
	
	@RequestMapping("add/{appliction}")
	@ResponseBody
	public List<String> init(@PathVariable int appliction) {
		return service.queryMobile(appliction);
	}

	@RequiresPermissions("manage:push:del")
	@RequestMapping("del/{id}")
	@ResponseBody
	public AjaxResult del(@PathVariable String id) {
		if (service.del(id)) {
			return new AjaxResult("0", "");
		}
		return new AjaxResult("1", "");
	}
}
