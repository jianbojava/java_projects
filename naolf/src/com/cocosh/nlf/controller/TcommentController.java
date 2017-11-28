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
import com.cocosh.nlf.model.Tcomment;
import com.cocosh.nlf.service.TcommentService;

@Controller
@RequestMapping("manage/tcomment")
public class TcommentController extends BaseController {
	@Autowired
	private TcommentService service;

	@RequiresPermissions("manage:tcomment:list")
	@RequestMapping("list")
	public String list(BaseConditionVO vo, Model model) {
		Page<Tcomment> page=service.queryPage(vo);
		model.addAttribute("page", page);
		model.addAttribute("vo", vo);
		return "manage/tcomment/list";
	}
	
	@RequiresPermissions("manage:tcomment:add")
	@RequestMapping(value="add",method=RequestMethod.GET)
	public String add(Model model) {
		return "tcomment/add";
	}

	@RequiresPermissions("manage:tcomment:list")
	@RequestMapping("add")
	@ResponseBody
	public AjaxResult add(Tcomment po) {
		if (service.addOrupdate(po)) {
			return new AjaxResult("0");
		}
		return new AjaxResult("1");
	}

	@RequiresPermissions("manage:tcomment:list")
	@RequestMapping("update/{appoint_id}")
	public String update(@PathVariable String appoint_id,Model model) {
		model.addAttribute("tcomment",service.queryByappoint_Id(appoint_id));
		model.addAttribute("teacher",service.queryteacher(appoint_id));
		model.addAttribute("appoint_id", appoint_id);
		return "nlf/tcomment/update";
	}

	@RequiresPermissions("manage:tcomment:update")
	@RequestMapping("update")
	@ResponseBody
	public AjaxResult update(Tcomment po) {
		if (service.update(po)) {
			return new AjaxResult("0");
		} else {
			return new AjaxResult("1");
		}
	}

	@RequiresPermissions("manage:tcomment:del")
	@RequestMapping("del/{id}")
	@ResponseBody
	public AjaxResult del(@PathVariable String id) {
		if (service.del(id)) {
			return new AjaxResult("0");
		}
		return new AjaxResult("1");
	}
}
