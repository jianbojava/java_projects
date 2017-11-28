package com.cocosh.nlf.controller;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.cocosh.framework.base.BaseConditionVO;
import com.cocosh.framework.base.BaseController;
import com.cocosh.framework.mybatis.Page;
import com.cocosh.nlf.model.EmpDisJournal;
import com.cocosh.nlf.service.EmpDisJournalService;

@Controller
@RequestMapping("manage/empdisjournal")
public class EmpDisJournalController extends BaseController {
	@Autowired
	private EmpDisJournalService service;

	@RequiresPermissions("manage:userscoreinfo:list")
	@RequestMapping("list")
	public String list(BaseConditionVO vo, Model model) {
		Page<EmpDisJournal> page=service.queryPage(vo);
		model.addAttribute("page", page);
		model.addAttribute("vo", vo);
		return "nlf/userscore/empDisJournal";
	}
	
	
}
