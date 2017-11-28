package com.cocosh.sys.controller;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cocosh.framework.base.AjaxResult;
import com.cocosh.framework.base.BaseConditionVO;
import com.cocosh.framework.base.BaseController;
import com.cocosh.framework.mybatis.Page;
import com.cocosh.sys.model.Log;
import com.cocosh.sys.service.LogService;

@Controller
@RequestMapping("manage/log")
public class LogController extends BaseController {
	@Autowired
	private LogService logService;

	@RequiresPermissions("manage:log:list")
	@RequestMapping("list")
	public String list(BaseConditionVO vo,Model model) {
		Page<Log> page=logService.queryPage(vo);
		model.addAttribute("page", page);
		model.addAttribute("vo",vo);
		return "sys/log/list";
	}

	@RequiresPermissions("manage:log:del")
	@RequestMapping("del/{id}")
	@ResponseBody
	public AjaxResult del(@PathVariable String id) {
		if (logService.del(id)) {
			return new AjaxResult("0","");
		}
		return new AjaxResult("1", "");
	}
}
