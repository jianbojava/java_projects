package com.cocosh.car.controller;

import java.util.List;

import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cocosh.framework.base.AjaxResult;
import com.cocosh.framework.base.BaseConditionVO;
import com.cocosh.framework.base.BaseController;
import com.cocosh.framework.mybatis.Page;
import com.cocosh.car.model.Brand;
import com.cocosh.car.model.BrandType;
import com.cocosh.car.service.BrandService;

@Controller
@RequestMapping("manage/brand")
public class BrandController extends BaseController {
	@Autowired
	private BrandService service;

	/**
	 * 车辆品牌
	 * @param vo
	 * @param model
	 * @return
	 */
	@RequiresPermissions("manage:brand:list")
	@RequestMapping("list")
	public String list(BaseConditionVO vo, Model model) {
		vo.setType("0");//车辆品牌
		Page<Brand> page=service.queryPage(vo);
		model.addAttribute("page", page);
		model.addAttribute("vo", vo);
		return "car/brand/list";
	}
	
	/**
	 * 充电桩品牌
	 * @param vo
	 * @param model
	 * @return
	 */
	@RequiresPermissions("manage:brand:clist")
	@RequestMapping("clist")
	public String clist(BaseConditionVO vo, Model model) {
		vo.setType("1");//电桩品牌
		Page<Brand> page=service.queryPage(vo);
		model.addAttribute("page", page);
		model.addAttribute("vo", vo);
		return "car/brand/clist";
	}

	@RequiresPermissions(value={"manage:brand:update","manage:brand:cupdate"},logical=Logical.OR)
	@RequestMapping("update/{id}/{type}")
	public String update(@PathVariable String id,@PathVariable String type,Model model) {
		model.addAttribute("brand",service.queryById(id));
		model.addAttribute("type", type);
		return "car/brand/update";
	}

	@RequiresPermissions(value={"manage:brand:update","manage:brand:cupdate"},logical=Logical.OR)
	@RequestMapping("update")
	@ResponseBody
	public AjaxResult update(@RequestBody Brand po) {
		if (service.update(po)) {
			return new AjaxResult("0");
		} else {
			return new AjaxResult("1");
		}
	}

	@RequiresPermissions(value={"manage:brand:add","manage:brand:cadd"},logical=Logical.OR)
	@RequestMapping(value="add/{type}",method=RequestMethod.GET)
	public String add(@PathVariable String type,Model model) {
		model.addAttribute("type", type);
		return "car/brand/add";
	}

	@RequiresPermissions(value={"manage:brand:add","manage:brand:cadd"},logical=Logical.OR)
	@RequestMapping("add")
	@ResponseBody
	public AjaxResult add(@RequestBody Brand po) {
		if (service.add(po)) {
			return new AjaxResult("0");
		}
		return new AjaxResult("1");
	}

	@RequiresPermissions(value={"manage:brand:del","manage:brand:cdel"},logical=Logical.OR)
	@RequestMapping("del/{flg}/{id}")
	@ResponseBody
	public AjaxResult del(@PathVariable Integer flg,@PathVariable String id) {
		if (service.del(flg,id)) {
			return new AjaxResult("0");
		}
		return new AjaxResult("1");
	}
	
	/**
	 * 品牌、车型联动
	 * @param id
	 * @return
	 */
	@RequestMapping("changeTypes/{id}")
	@ResponseBody
	public List<BrandType> changeTypes(@PathVariable String id){
		return service.queryByBId(id);
	}
}
