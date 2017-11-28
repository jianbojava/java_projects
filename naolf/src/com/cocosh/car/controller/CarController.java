package com.cocosh.car.controller;

import java.util.List;

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
import com.cocosh.car.model.Car;
import com.cocosh.car.model.Order;
import com.cocosh.car.model.Parking;
import com.cocosh.car.service.BrandService;
import com.cocosh.car.service.CarService;
import com.cocosh.car.service.DotService;
import com.cocosh.car.service.OrderService;
import com.cocosh.car.service.ParkingService;

@Controller
@RequestMapping("manage/car")
public class CarController extends BaseController {
	@Autowired
	private CarService service;
	@Autowired
	private OrderService orderService;
	@Autowired
	private DotService dotService;
	@Autowired
	private BrandService brandService;
	@Autowired
	private ParkingService parkingService;

	@RequiresPermissions("manage:car:list")
	@RequestMapping("list")
	public String list(BaseConditionVO vo, Model model) {
		Page<Car> page=service.queryPage(vo);
		model.addAttribute("page", page);
		model.addAttribute("vo", vo);
		return "car/list";
	}
	
	@RequiresPermissions("manage:car:dync")
	@RequestMapping("dync")
	public String dync(BaseConditionVO vo, Model model) {
		Page<Car> page=service.queryDyncPage(vo);
		model.addAttribute("page", page);
		model.addAttribute("vo", vo);
		return "car/dync/list";
	}
	
	@RequiresPermissions("manage:car:dyncDetail")
	@RequestMapping("dyncDetail")
	public String dyncDetail(BaseConditionVO vo, Model model) {
		Page<Order> page=orderService.queryDyncPage(vo);
		model.addAttribute("page", page);
		model.addAttribute("vo", vo);
		return "car/dync/view";
	}
	
	@RequiresPermissions("manage:car:dyncDetail")
	@RequestMapping("dyncAjax")
	@ResponseBody
	public List<Order> dyncDetail(BaseConditionVO vo) {
		return orderService.queryDyncPage(vo).getResult();
	}
	
	@RequiresPermissions("manage:car:add")
	@RequestMapping(value="add",method=RequestMethod.GET)
	public String add(Model model) {
		model.addAttribute("brands",brandService.queryAllByType("0"));
		model.addAttribute("dots", dotService.queryByAll());
		return "car/add";
	}

	@RequiresPermissions("manage:car:add")
	@RequestMapping("add")
	@ResponseBody
	public AjaxResult add(Car po) {
		if (service.add(po)) {
			return new AjaxResult("0");
		}
		return new AjaxResult("1");
	}

	@RequiresPermissions("manage:car:update")
	@RequestMapping("update/{id}")
	public String update(@PathVariable String id,Model model) {
		model.addAttribute("car",service.queryById(id));
		model.addAttribute("brands",brandService.queryAllByType("0"));
		model.addAttribute("dots", dotService.queryByAll());
		return "car/update";
	}

	@RequiresPermissions("manage:car:update")
	@RequestMapping("update")
	@ResponseBody
	public AjaxResult update(Car po) {
		if (service.update(po)) {
			return new AjaxResult("0");
		} else {
			return new AjaxResult("1");
		}
	}

	@RequiresPermissions("manage:car:del")
	@RequestMapping("del/{flg}/{id}")
	@ResponseBody
	public AjaxResult del(@PathVariable Integer flg,@PathVariable String id) {
		if (service.enabled(flg,id)) {
			return new AjaxResult("0");
		}
		return new AjaxResult("1");
	}
	
	@RequiresPermissions("manage:car:review")
	@RequestMapping("review/{flg}/{id}")
	@ResponseBody
	public AjaxResult review(@PathVariable Integer flg,@PathVariable String id) {
		if (service.review(flg,id)) {
			return new AjaxResult("0");
		}
		return new AjaxResult("1");
	}
	
	@RequiresPermissions("manage:car:fdel")
	@RequestMapping("fdel/{id}")
	@ResponseBody
	public AjaxResult fdel(@PathVariable String id) {
		if (service.del(id)) {
			return new AjaxResult("0");
		}
		return new AjaxResult("1");
	}
	
	/**
	 * 网点、车位联动
	 * @param id
	 * @return
	 */
	@RequestMapping("changeParking/{dot_id}")
	@ResponseBody
	public List<Parking> changeParking(@PathVariable String dot_id){
		return parkingService.queryByDotId(dot_id);
	}
	
	@RequestMapping("refresh")
	@ResponseBody
	public List<Car> refresh(BaseConditionVO vo){
		return service.queryAllNoPage(vo);
	}
	
}
