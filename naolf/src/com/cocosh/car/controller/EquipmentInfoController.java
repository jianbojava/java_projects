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
import com.cocosh.car.model.EquipmentInfo;
import com.cocosh.car.service.EquipmentInfoService;
import com.cocosh.car.service.StationInfoService;

@Controller
@RequestMapping("manage/equipmentinfo")
public class EquipmentInfoController extends BaseController {
	@Autowired
	private EquipmentInfoService service;
	@Autowired
	private StationInfoService stationService;

	@RequiresPermissions("manage:equipmentinfo:list")
	@RequestMapping("list")
	public String list(BaseConditionVO vo, Model model) {
		Page<EquipmentInfo> page=service.queryPage(vo);
		model.addAttribute("page", page);
		model.addAttribute("vo", vo);
		return "car/equipmentinfo/list";
	}
	
	@RequiresPermissions("manage:equipmentinfo:add")
	@RequestMapping(value="add",method=RequestMethod.GET)
	public String add(Model model) {
		model.addAttribute("stations",stationService.queryAll());
		return "car/equipmentinfo/add";
	}

	@RequiresPermissions("manage:equipmentinfo:add")
	@RequestMapping("add")
	@ResponseBody
	public AjaxResult add(EquipmentInfo po) {
		if (service.add(po)) {
			return new AjaxResult("0");
		}
		return new AjaxResult("1");
	}

	@RequiresPermissions("manage:equipmentinfo:update")
	@RequestMapping("update/{id}")
	public String update(@PathVariable String id,Model model) {
		model.addAttribute("equipmentinfo",service.queryById(id));
		model.addAttribute("stations",stationService.queryAll());
		return "car/equipmentinfo/update";
	}

	@RequiresPermissions("manage:equipmentinfo:update")
	@RequestMapping("update")
	@ResponseBody
	public AjaxResult update(EquipmentInfo po) {
		if (service.update(po)) {
			return new AjaxResult("0");
		} else {
			return new AjaxResult("1");
		}
	}

	@RequiresPermissions("manage:equipmentinfo:del")
	@RequestMapping("del/{flg}/{id}")
	@ResponseBody
	public AjaxResult del(@PathVariable Integer flg,@PathVariable String id) {
		if (service.del(flg,id)) {
			return new AjaxResult("0");
		}
		return new AjaxResult("1");
	}
	
	@RequiresPermissions("manage:equipmentinfo:delT")
	@RequestMapping("delT/{id}")
	@ResponseBody
	public AjaxResult delT(@PathVariable String id) {
		if (service.delT(id)) {
			return new AjaxResult("0");
		}
		return new AjaxResult("1");
	}
}
