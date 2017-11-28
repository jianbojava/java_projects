package com.cocosh.sys.controller;

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
import com.cocosh.sys.model.News;
import com.cocosh.sys.service.NewsService;

@Controller
@RequestMapping("manage/news")
public class NewsController extends BaseController {
	@Autowired
	private NewsService service;

	@RequiresPermissions("manage:news:list")
	@RequestMapping("list")
	public String list(BaseConditionVO vo, Model model) {
		Page<News> page=service.queryPage(vo);
		model.addAttribute("page", page);
		model.addAttribute("vo", vo);
		return "sys/news/list";
	}
	
	@RequiresPermissions("manage:news:add")
	@RequestMapping(value="add",method=RequestMethod.GET)
	public String add(Model model) {
		return "sys/news/add";
	}

	@RequiresPermissions("manage:news:add")
	@RequestMapping("add")
	@ResponseBody
	public AjaxResult add(News po) {
		if (service.add(po)) {
			return new AjaxResult("0");
		}
		return new AjaxResult("1");
	}

	@RequiresPermissions("manage:news:update")
	@RequestMapping("update/{id}")
	public String update(@PathVariable String id,Model model) {
		model.addAttribute("news",service.queryById(id));
		return "sys/news/update";
	}

	@RequiresPermissions("manage:news:update")
	@RequestMapping("update")
	@ResponseBody
	public AjaxResult update(News po) {
		if (service.update(po)) {
			return new AjaxResult("0");
		} else {
			return new AjaxResult("1");
		}
	}

	@RequiresPermissions("manage:news:del")
	@RequestMapping("del/{flg}/{id}")
	@ResponseBody
	public AjaxResult del(@PathVariable Integer flg,@PathVariable String id) {
		if (service.del(flg,id)) {
			return new AjaxResult("0");
		}
		return new AjaxResult("1");
	}
	
	@RequiresPermissions("manage:news:joinUs")
	@RequestMapping("joinUs")
	public String joinUs() {
		return "joinUs";
	}
}
