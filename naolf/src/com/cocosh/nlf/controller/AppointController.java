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
import com.cocosh.nlf.model.Appoint;
import com.cocosh.nlf.service.AppointService;
import com.cocosh.sys.service.DepartService;

@Controller
@RequestMapping("manage/appoint")
public class AppointController extends BaseController {
	@Autowired
	private AppointService service;
	@Autowired 
	private DepartService departService;

	@RequiresPermissions("manage:appoint:list")
	@RequestMapping("list")
	public String list(BaseConditionVO vo, Model model) {
		Page<Appoint> page=service.queryPage(vo);
		model.addAttribute("page", page);
		model.addAttribute("vo", vo);
		return "nlf/appoint/list";
	}
	
	@RequiresPermissions("manage:appoint:add")
	@RequestMapping(value="add",method=RequestMethod.GET)
	public String add(Model model) {
		return "nlf/appoint/add";
	}

	@RequiresPermissions("manage:appoint:add")
	@RequestMapping("add")
	@ResponseBody
	public AjaxResult add(Appoint po) {
		if (service.add(po)) {
			return new AjaxResult("0");
		}
		return new AjaxResult("1");
	}

	@RequiresPermissions("manage:appoint:update")
	@RequestMapping("update/{id}")
	public String update(@PathVariable String id,Model model) {
		model.addAttribute("appoint",service.queryById(id));
		return "nlf/appoint/update";
	}

	@RequiresPermissions("manage:appoint:update")
	@RequestMapping("update")
	@ResponseBody
	public AjaxResult update(Appoint po) {
		if (service.update(po)) {
			return new AjaxResult("0");
		} else {
			return new AjaxResult("1");
		}
	}
	/*批量结业*/
	@RequiresPermissions(value={"manage:course:del","manage:course:cdel"},logical=Logical.OR)
	@RequestMapping("status/{flg}/{id}/{course_type}")
	@ResponseBody
	public AjaxResult statusUpdate(@PathVariable Integer flg,@PathVariable String id,@PathVariable Integer course_type) {
		if (service.statusUpdate(flg,id,course_type)) {
			return new AjaxResult("0");
		}
		return new AjaxResult("1");
	}
}
