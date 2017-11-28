package com.cocosh.car.controller;

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
import com.cocosh.car.model.StationInfo;
import com.cocosh.car.service.OperatorInfoService;
import com.cocosh.car.service.StationInfoService;

@Controller
@RequestMapping("manage/stationinfo")
public class StationInfoController extends BaseController {
	@Autowired
	private StationInfoService service;
	@Autowired
	private OperatorInfoService operatorInfoService;

	@RequiresPermissions("manage:stationinfo:list")
	@RequestMapping("list")
	public String list(BaseConditionVO vo, Model model) {
		Page<StationInfo> page=service.queryPage(vo);
		model.addAttribute("page", page);
		model.addAttribute("vo", vo);
		return "car/stationinfo/list";
	}
	
	@RequiresPermissions("manage:stationinfo:add")
	@RequestMapping(value="add",method=RequestMethod.GET)
	public String add(Model model) {
		model.addAttribute("operators", operatorInfoService.queryAll());
		return "car/stationinfo/add";
	}

	@RequiresPermissions("manage:stationinfo:add")
	@RequestMapping("add")
	@ResponseBody
	public AjaxResult add(StationInfo po) {
		if (service.add(po)) {
			return new AjaxResult("0");
		}
		return new AjaxResult("1");
	}

	@RequiresPermissions("manage:stationinfo:update")
	@RequestMapping("update/{id}")
	public String update(@PathVariable String id,Model model) {
		model.addAttribute("stationinfo",service.queryById(id));
		model.addAttribute("operators",operatorInfoService.queryAll());
		return "car/stationinfo/update";
	}

	@RequiresPermissions("manage:stationinfo:update")
	@RequestMapping("update")
	@ResponseBody
	public AjaxResult update(StationInfo po) {
		if (service.update(po)) {
			return new AjaxResult("0");
		} else {
			return new AjaxResult("1");
		}
	}

	@RequiresPermissions("manage:stationinfo:del")
	@RequestMapping("del/{flg}/{id}")
	@ResponseBody
	public AjaxResult del(@PathVariable Integer flg,@PathVariable String id) {
		if (service.del(flg,id)) {
			return new AjaxResult("0");
		}
		return new AjaxResult("1");
	}
	
	@RequiresPermissions("manage:stationinfo:delT")
	@RequestMapping("delT/{id}")
	@ResponseBody
	public AjaxResult delT(@PathVariable String id) {
		if (service.delT(id)) {
			return new AjaxResult("0");
		}
		return new AjaxResult("1");
	}
	
}
