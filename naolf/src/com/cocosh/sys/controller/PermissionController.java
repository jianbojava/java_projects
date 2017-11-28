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
import com.cocosh.sys.model.Permission;
import com.cocosh.sys.service.PermissionService;
@Controller
@RequestMapping("manage/permission")
public class PermissionController extends BaseController{
	@Autowired
	private PermissionService service;
	
	@RequiresPermissions("manage:permission:list")
	@RequestMapping("list")
	public String list(BaseConditionVO vo,Model model){
		Page<Permission> page=service.queryPage(vo);
		model.addAttribute("page", page);
		model.addAttribute("vo",vo);
		return "sys/perm/list";
	}
	
	@RequiresPermissions("manage:permission:update")
	@RequestMapping("update/{id}")
	public String input(@PathVariable String id,Model model){
		model.addAttribute("roots",service.queryRoot());
		Permission perm=service.queryById(id);
		model.addAttribute("perm",perm );
		return "sys/perm/update";
	}
	@RequestMapping("update")
	@ResponseBody
	public AjaxResult update(Permission perm){
		if(service.update(perm)){
			return new AjaxResult("0","");
		}else {
			return new AjaxResult("1","");
		}
	}
	
	@RequiresPermissions("manage:permission:add")
	@RequestMapping(value="add",method=RequestMethod.GET)
	public String add(Model model){
		model.addAttribute("roots",service.queryRoot());
		return "sys/perm/add";
	}
	
	@RequiresPermissions("manage:permission:add")
	@RequestMapping("add")
	@ResponseBody
	public AjaxResult add(Permission perm){
		if(service.add(perm)){
			return new AjaxResult("0","");
		}
		return new AjaxResult("1","");
	}
	
	@RequiresPermissions("manage:permission:del")
	@RequestMapping("del/{id}")
	@ResponseBody
	public AjaxResult del(@PathVariable String id){
		if (service.del(id)) {
			return new AjaxResult("0","");
		}
		return new AjaxResult("1","");
	}
}
