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
import com.cocosh.framework.util.CommonUtil;
import com.cocosh.sys.service.UserService;
import com.cocosh.car.model.Parking;
import com.cocosh.car.service.DotService;
import com.cocosh.car.service.ParkingService;

@Controller
@RequestMapping("manage/parking")
public class ParkingController extends BaseController {
	@Autowired
	private ParkingService service;
	@Autowired
	private DotService dotService;
	@Autowired
	private UserService userService;

	@RequiresPermissions("manage:parking:list")
	@RequestMapping("list")
	public String list(BaseConditionVO vo, Model model) {
		Page<Parking> page=service.queryPage(vo);
		model.addAttribute("page", page);
		model.addAttribute("vo", vo);
		return "car/park/list";
	}
	
	@RequiresPermissions("manage:parking:add")
	@RequestMapping(value="add",method=RequestMethod.GET)
	public String add(Model model) {
		model.addAttribute("dispatchs", userService.queryByRole(CommonUtil.ROLE_DISPATCH));
		model.addAttribute("dots", dotService.queryByAll());
		return "car/park/add";
	}

	@RequiresPermissions("manage:parking:add")
	@RequestMapping("add")
	@ResponseBody
	public AjaxResult add(Parking po) {
		if (service.add(po)) {
			return new AjaxResult("0");
		}
		return new AjaxResult("1");
	}

	@RequiresPermissions("manage:parking:update")
	@RequestMapping("update/{id}")
	public String update(@PathVariable String id,Model model) {
		model.addAttribute("parking",service.queryById(id));
		model.addAttribute("dispatchs", userService.queryByRole(CommonUtil.ROLE_DISPATCH));
		model.addAttribute("dots", dotService.queryByAll());
		return "car/park/update";
	}

	@RequiresPermissions("manage:parking:update")
	@RequestMapping("update")
	@ResponseBody
	public AjaxResult update(Parking po) {
		if (service.update(po)) {
			return new AjaxResult("0");
		} else {
			return new AjaxResult("1");
		}
	}

	@RequiresPermissions("manage:parking:del")
	@RequestMapping("del/{flg}/{id}")
	@ResponseBody
	public AjaxResult del(@PathVariable Integer flg,@PathVariable String id) {
		if (service.del(flg,id)) {
			return new AjaxResult("0");
		}
		return new AjaxResult("1");
	}
}
