package com.cocosh.car.controller;

import org.apache.shiro.authz.annotation.Logical;
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
import com.cocosh.car.model.Comment;
import com.cocosh.car.service.CarService;
import com.cocosh.car.service.CommentService;
import com.cocosh.car.service.StationInfoService;

@Controller
@RequestMapping("manage/comment")
public class CommentController extends BaseController {
	@Autowired
	private CommentService service;
	@Autowired
	private CarService carService;
	@Autowired
	private StationInfoService stationService;

	@RequiresPermissions("manage:comment:list")
	@RequestMapping("list")
	public String list(BaseConditionVO vo, Model model) {
		Page<Comment> page=service.queryPage(vo,0);
		model.addAttribute("page", page);
		model.addAttribute("vo", vo);
		model.addAttribute("type",0);
		return "car/comment/list";
	}
	
	@RequiresPermissions("manage:comment:slist")
	@RequestMapping("slist")
	public String slist(BaseConditionVO vo, Model model) {
		Page<Comment> page=service.queryPage(vo,1);
		model.addAttribute("page", page);
		model.addAttribute("vo", vo);
		model.addAttribute("type",1);
		return "car/comment/list";
	}
	
	@RequiresPermissions(value={"manage:comment:add","manage:comment:sadd"},logical=Logical.OR)
	@RequestMapping(value="add/{type}",method=RequestMethod.GET)
	public String add(@PathVariable Integer type,Model model) {
		if (type==0) {
			model.addAttribute("cars", carService.queryAll());
		}else {
			model.addAttribute("stations", stationService.queryAll());
		}
		model.addAttribute("type", type);
		return "car/comment/add";
	}

	@RequiresPermissions(value={"manage:comment:add","manage:comment:sadd"},logical=Logical.OR)
	@RequestMapping("add")
	@ResponseBody
	public AjaxResult add(Comment po) {
		if (service.add(po)) {
			return new AjaxResult("0");
		}
		return new AjaxResult("1");
	}

	@RequiresPermissions(value={"manage:comment:update","manage:comment:supdate"},logical=Logical.OR)
	@RequestMapping("update/{id}")
	public String update(@PathVariable String id,Model model) {
		model.addAttribute("comment",service.queryById(id));
		return "comment/update";
	}

	@RequiresPermissions(value={"manage:comment:update","manage:comment:supdate"},logical=Logical.OR)
	@RequestMapping("update")
	@ResponseBody
	public AjaxResult update(Comment po) {
		if (service.update(po)) {
			return new AjaxResult("0");
		} else {
			return new AjaxResult("1");
		}
	}

	@RequiresPermissions(value={"manage:comment:del","manage:comment:sdel"},logical=Logical.OR)
	@RequestMapping("del/{flg}/{id}")
	@ResponseBody
	public AjaxResult del(@PathVariable Integer flg,@PathVariable String id) {
		if (service.del(flg,id)) {
			return new AjaxResult("0");
		}
		return new AjaxResult("1");
	}
}
