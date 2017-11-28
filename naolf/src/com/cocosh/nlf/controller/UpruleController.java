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
import com.cocosh.framework.util.StringUtil;
import com.cocosh.nlf.model.Ticket;
import com.cocosh.nlf.model.Uprule;
import com.cocosh.nlf.service.TicketService;
import com.cocosh.nlf.service.UpruleService;

@Controller
@RequestMapping("manage/uprule")
public class UpruleController extends BaseController {
	@Autowired
	private UpruleService service;
	@Autowired
	private TicketService ticketService;

	@RequiresPermissions("manage:uprule:list")
	@RequestMapping("list")
	public String list(BaseConditionVO vo, Model model) {
		model.addAttribute("uprule", service.queryone());
		model.addAttribute("list1", ticketService.queryByType(0));
		model.addAttribute("list2", ticketService.queryByType(1));
		return "nlf/ticket/uprule";
	}
	
	@RequiresPermissions("manage:uprule:add")
	@RequestMapping(value="add",method=RequestMethod.GET)
	public String add(Model model) {
		return "uprule/add";
	}

	@RequiresPermissions("manage:uprule:add")
	@RequestMapping("add")
	@ResponseBody
	public AjaxResult add(Uprule po) {
		if (service.add(po)) {
			return new AjaxResult("0");
		}
		return new AjaxResult("1");
	}

	@RequiresPermissions("manage:uprule:update")
	@RequestMapping("update/{id}")
	public String update(@PathVariable String id,Model model) {
		model.addAttribute("uprule",service.queryById(id));
		return "nlf/ticket/uprule";
	}

	@RequiresPermissions("manage:uprule:list")
	@RequestMapping("update")
	@ResponseBody
	public AjaxResult update(Uprule po) {
		if(service.validUprule(po)){
			if(StringUtil.isEmpty(po.getId())){
				if (service.add(po)) {
					return new AjaxResult("0");
				} else {
					return new AjaxResult("1");
				}
			}else{
				if (service.update(po)) {
					return new AjaxResult("0");
				} else {
					return new AjaxResult("1");
				}
			}
		}else{
			return new AjaxResult("2");
		}
	}

	@RequiresPermissions("manage:uprule:del")
	@RequestMapping("del/{id}")
	@ResponseBody
	public AjaxResult del(@PathVariable String id) {
		if (service.del(id)) {
			return new AjaxResult("0");
		}
		return new AjaxResult("1");
	}
}
