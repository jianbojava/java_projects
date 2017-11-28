package com.cocosh.nlf.controller;

import java.util.List;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cocosh.framework.base.AjaxResult;
import com.cocosh.framework.base.BaseConditionVO;
import com.cocosh.framework.base.BaseController;
import com.cocosh.framework.mybatis.Page;
import com.cocosh.nlf.model.UserTicket;
import com.cocosh.nlf.service.UserTicketService;

@Controller
@RequestMapping("manage/userticket")
public class UserTicketController extends BaseController {
	@Autowired
	private UserTicketService service;
	@Autowired
	private UserTicketService userTicketService;

	@RequiresPermissions("manage:userticket:list")
	@RequestMapping("list")
	public String list(BaseConditionVO vo, Model model) {
		vo.setType("1");
		Page<UserTicket> page=userTicketService.queryPage(vo);
		model.addAttribute("page", page);
		model.addAttribute("vo", vo);
		return "nlf/userticket/list";
	}
	
	@RequiresPermissions("manage:userticket:add")
	@RequestMapping(value="add",method=RequestMethod.GET)
	public String add(Model model) {
		return "nlf/userticket/add";
	}

	@RequiresPermissions("manage:userticket:add")
	@RequestMapping("addTicket")
	@ResponseBody
	public AjaxResult addTicket(UserTicket po) {
		if (service.add(po)) {
			return new AjaxResult("0");
		}
		return new AjaxResult("1");
	}
	
	@RequiresPermissions("manage:userticket:add")
	@RequestMapping("add")
	@ResponseBody
	public AjaxResult add(UserTicket po) {
		if (service.add(po)) {
			return new AjaxResult("0");
		}
		return new AjaxResult("1");
	}

	@RequiresPermissions("manage:userticket:update")
	@RequestMapping("update/{id}")
	public String update(@PathVariable String id,Model model) {
		model.addAttribute("userticket",service.queryById(id));
		return "nlf/userticket/update";
	}

	@RequiresPermissions("manage:userticket:update")
	@RequestMapping("update")
	@ResponseBody
	public AjaxResult update(UserTicket po) {
		if (service.update(po)) {
			return new AjaxResult("0");
		} else {
			return new AjaxResult("1");
		}
	}

	@RequiresPermissions("manage:userticket:del")
	@RequestMapping("del/{id}")
	@ResponseBody
	public AjaxResult del(@PathVariable String id) {
		if (service.del(id)) {
			return new AjaxResult("0");
		}
		return new AjaxResult("1");
	}
	
	@RequestMapping("query")
	@ResponseBody
	public List<UserTicket> query(Model model,UserTicket uticket){
		return service.queryUticket(uticket);
	}
}
