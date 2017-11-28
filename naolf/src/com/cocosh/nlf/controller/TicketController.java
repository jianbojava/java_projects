package com.cocosh.nlf.controller;


import java.util.ArrayList;
import java.util.List;

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
import com.cocosh.nlf.model.Lesson;
import com.cocosh.nlf.model.Ticket;
import com.cocosh.nlf.service.LessonService;
import com.cocosh.nlf.service.RuleService;
import com.cocosh.nlf.service.TicketService;
import com.cocosh.sys.service.UserService;

@Controller
@RequestMapping("manage/ticket")
public class TicketController extends BaseController {
	@Autowired
	private TicketService service;
	@Autowired
	private LessonService lessonService;
	@Autowired
	private RuleService ruleService;
	@Autowired
	private UserService userService;

	@RequiresPermissions("manage:ticket:list")
	@RequestMapping("list")
	public String list(BaseConditionVO vo, Model model) {
		Page<Ticket> page=service.queryPage(vo);
		model.addAttribute("page", page);
		model.addAttribute("vo", vo);
		return "nlf/ticket/list";
	}
	
	@RequiresPermissions("manage:ticket:add")
	@RequestMapping(value="add",method=RequestMethod.GET)
	public String add(BaseConditionVO vo,Model model) {
		model.addAttribute("lessons", lessonService.queryAll());
		model.addAttribute("rules", ruleService.queryAll());
		return "nlf/ticket/add";
	}
	@RequiresPermissions("manage:ticket:add")
	@RequestMapping(value="resetticketadd",method=RequestMethod.GET)
	public String resetticketadd(BaseConditionVO vo,Model model) {
		model.addAttribute("lessons", lessonService.queryAll());
		model.addAttribute("member",userService.queryByUsertype(2));
		model.addAttribute("ticket",service.queryAllReset());
		model.addAttribute("rules", ruleService.queryAll());
		return "nlf/resetticket/add";
	}
	@RequiresPermissions("manage:ticket:add")
	@RequestMapping(value="resetticketlist",method=RequestMethod.GET)
	public String resetticketlist(BaseConditionVO vo,Model model) {
		model.addAttribute("lessons", lessonService.queryAll());
		model.addAttribute("rules", ruleService.queryAll());
		return "nlf/resetticket/list";
	}

	@RequiresPermissions("manage:ticket:add")
	@RequestMapping("add")
	@ResponseBody
	public AjaxResult add(Ticket po) {
		if(service.checkName(po.getName())!=null) return new AjaxResult("2");
		if (service.add(po)) {
			return new AjaxResult("0");
		}
		return new AjaxResult("1");
	}

	@RequiresPermissions("manage:ticket:update")
	@RequestMapping("update/{id}")
	public String update(@PathVariable String id,Model model) {
		model.addAttribute("ticket",service.queryById(id));
		model.addAttribute("lessons", lessonService.queryAll());
		model.addAttribute("rules", ruleService.queryAll());
		return "nlf/ticket/update";
	}

	@RequiresPermissions("manage:ticket:update")
	@RequestMapping("update")
	@ResponseBody
	public AjaxResult update(Ticket po) {
		Ticket check=service.checkName(po.getName());
		if(check!=null&&(!po.getId().equals(check.getId()))) return new AjaxResult("2");
		if (service.update(po)) {
			return new AjaxResult("0");
		} else {
			return new AjaxResult("1");
		}
	}

	@RequiresPermissions("manage:ticket:del")
	@RequestMapping("del/{id}")
	@ResponseBody
	public AjaxResult del(@PathVariable String id) {
		if (service.del(id)) {
			return new AjaxResult("0");
		}
		return new AjaxResult("1");
	}
	
	@RequestMapping("queryLessonById/{id}")
	@ResponseBody
	List<Lesson> queryLessonById(@PathVariable String id){
		Ticket t=service.queryById(id);
		List<Lesson> list=null;
		if(t!=null&&(!StringUtil.isEmpty(t.getLesson_ids()))){
			list=lessonService.queryByIds(t.getLesson_ids());
		}
		return list;
	}
}
