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
import com.cocosh.sys.model.Role;
import com.cocosh.sys.service.PermissionService;
import com.cocosh.sys.service.RoleService;

@Controller
@RequestMapping("manage/role")
public class RoleController extends BaseController {
	@Autowired
	private RoleService service;
	@Autowired
	private PermissionService permissionService;

	@RequiresPermissions("manage:role:list")
	@RequestMapping("list")
	public String list(BaseConditionVO vo, Model model) {
		Page<Role> page=service.queryPage(vo);
		model.addAttribute("page", page);
		model.addAttribute("vo",vo);
		return "sys/role/list";
	}

	@RequiresPermissions("manage:role:update")
	@RequestMapping("update/{id}")
	public String  input(@PathVariable String id,Model model) {
		model.addAttribute("perms", permissionService.queryAll());
		 model.addAttribute("role",service.queryById(id));
		 return "sys/role/update";
	}

	@RequiresPermissions("manage:role:update")
	@RequestMapping("update")
	@ResponseBody
	public AjaxResult update(Role role) {
		if (service.update(role)) {
			return new AjaxResult("0", "");
		} else {
			return new AjaxResult("1", "");
		}
	}

	@RequiresPermissions("manage:role:add")
	@RequestMapping(value="add",method=RequestMethod.GET)
	public String add(Model model) {
		model.addAttribute("perms", permissionService.queryAll());
		return "sys/role/add";
	}
	
	@RequiresPermissions("manage:role:add")
	@RequestMapping("add")
	@ResponseBody
	public AjaxResult add(Role role) {
		if (service.add(role)) {
			return new AjaxResult("0", "");
		}
		return new AjaxResult("1", "");
	}

	@RequiresPermissions("manage:role:del")
	@RequestMapping("del/{flg}/{id}")
	@ResponseBody
	public AjaxResult del(@PathVariable Integer flg,@PathVariable String id) {
		if (service.del(flg,id)) {
			return new AjaxResult("0");
		}
		return new AjaxResult("1");
	}

}
