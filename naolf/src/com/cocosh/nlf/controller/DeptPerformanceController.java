package com.cocosh.nlf.controller;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.cocosh.framework.base.BaseConditionVO;
import com.cocosh.framework.base.BaseController;
import com.cocosh.framework.mybatis.Page;
import com.cocosh.nlf.model.DeptPerformance;
import com.cocosh.nlf.service.DeptPerformanceService;
import com.cocosh.sys.service.DepartService;

@Controller
@RequestMapping("manage/deptperformance")
public class DeptPerformanceController extends BaseController {
	@Autowired
	private DeptPerformanceService service;
	
	@Autowired
	private DepartService deptservice;

	@RequiresPermissions("manage:deptperformance:list")
	@RequestMapping("list")
	public String list(BaseConditionVO vo, Model model) {
		Page<DeptPerformance> page=service.queryPage(vo);
		model.addAttribute("page", page);
		model.addAttribute("vo", vo);
		model.addAttribute("dept", deptservice.queryByAllNew());
		
		return "nlf/deptperformance/list";
	}
	@RequiresPermissions("manage:deptperformance:list")
	@RequestMapping("detaillist")
	public String detaillist(BaseConditionVO vo, Model model) {
		Page<DeptPerformance> page=service.queryDetailPage(vo);
		model.addAttribute("page", page);
		model.addAttribute("vo", vo);
		
		return "nlf/deptperformance/detaillist";
	}
	
	

	
	
}
