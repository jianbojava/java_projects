package com.cocosh.member.controller;

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
import com.cocosh.member.model.Level;
import com.cocosh.member.service.LevelService;

@Controller
@RequestMapping("manage/level")
public class LevelController extends BaseController {
	@Autowired
	private LevelService service;

	@RequiresPermissions("manage:level:list")
	@RequestMapping("list")
	public String list(BaseConditionVO vo, Model model) {
		Page<Level> page=service.queryPage(vo);
		model.addAttribute("page", page);
		model.addAttribute("vo", vo);
		return "member/level/list";
	}

	@RequiresPermissions("manage:level:update")
	@RequestMapping("update/{id}")
	public String update(@PathVariable String id,Model model) {
		model.addAttribute("level",service.queryById(id));
		return "member/level/update";
	}

	@RequiresPermissions("manage:level:update")
	@RequestMapping("update")
	@ResponseBody
	public AjaxResult update(Level po) {
		if (service.update(po)) {
			return new AjaxResult("0");
		} else {
			return new AjaxResult("1");
		}
	}

	@RequiresPermissions("manage:level:add")
	@RequestMapping(value="add",method=RequestMethod.GET)
	public String add(Model model) {
		return "member/level/add";
	}

	@RequiresPermissions("manage:level:add")
	@RequestMapping("add")
	@ResponseBody
	public AjaxResult add(Level po) {
		if (service.add(po)) {
			return new AjaxResult("0");
		}
		return new AjaxResult("1");
	}

	@RequiresPermissions("manage:level:del")
	@RequestMapping("del/{flg}/{id}")
	@ResponseBody
	public AjaxResult del(@PathVariable Integer flg,@PathVariable String id) {
		if (service.del(flg,id)) {
			return new AjaxResult("0");
		}
		return new AjaxResult("1");
	}
}
