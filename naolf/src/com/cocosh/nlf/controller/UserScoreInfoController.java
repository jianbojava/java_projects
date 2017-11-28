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
import com.cocosh.nlf.model.UserScoreInfo;
import com.cocosh.nlf.service.UserScoreInfoService;
import com.cocosh.sys.service.DepartService;

@Controller
@RequestMapping("manage/userscoreinfo")
public class UserScoreInfoController extends BaseController {
	@Autowired
	private UserScoreInfoService service;
	@Autowired
	private DepartService deptservice;

	@RequiresPermissions("manage:userscoreinfo:list")
	@RequestMapping("list")
	public String list(BaseConditionVO vo, Model model) {
		Page<UserScoreInfo> page=service.queryPage(vo);
		model.addAttribute("page", page);
		model.addAttribute("vo", vo);
		model.addAttribute("dept", deptservice.queryByAllNew2());
		return "nlf/userscore/list";
	}
	
	@RequiresPermissions("manage:userscoreinfo:add")
	@RequestMapping(value="add",method=RequestMethod.GET)
	public String add(Model model) {
		return "userscoreinfo/add";
	}

	@RequiresPermissions("manage:userscoreinfo:list")
	@RequestMapping("userscore")
	@ResponseBody
	public AjaxResult querybyuser(BaseConditionVO vo) {
		
		UserScoreInfo userscore = service.queryByUser(vo.getUser_id());
		if (userscore!=null) {
			return new AjaxResult("0","Sucess",userscore);
		}
		return new AjaxResult("1");
	}

	/*@RequiresPermissions("manage:userscoreinfo:update")
	@RequestMapping("update/{id}")
	public String update(@PathVariable String id,Model model) {
		model.addAttribute("userscoreinfo",service.queryById(id));
		return "userscoreinfo/update";
	}*/

	@RequiresPermissions("manage:userscoreinfo:update")
	@RequestMapping("update")
	@ResponseBody
	public AjaxResult update(UserScoreInfo po) {
		if (service.update(po)) {
			return new AjaxResult("0");
		} else {
			return new AjaxResult("1");
		}
	}

	@RequiresPermissions("manage:userscoreinfo:del")
	@RequestMapping("del/{id}")
	@ResponseBody
	public AjaxResult del(@PathVariable String id) {
		if (service.del(id)) {
			return new AjaxResult("0");
		}
		return new AjaxResult("1");
	}
}
