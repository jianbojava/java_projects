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
import com.cocosh.nlf.model.Receipt;
import com.cocosh.nlf.service.ReceiptService;

@Controller
@RequestMapping("manage/receipt")
public class ReceiptController extends BaseController {
	@Autowired
	private ReceiptService service;

	@RequiresPermissions("manage:receipt:list")
	@RequestMapping("list")
	public String list(BaseConditionVO vo, Model model) {
		Page<Receipt> page=service.queryPage(vo);
		model.addAttribute("page", page);
		model.addAttribute("vo", vo);
		return "nlf/receipt/list";
	}
	
	@RequiresPermissions("manage:receipt:add")
	@RequestMapping(value="add/{norder_id}",method=RequestMethod.GET)
	public String add(Model model,@PathVariable String norder_id) {
		model.addAttribute("norder_id", norder_id);
		return "nlf/receipt/add";
	}
	
	@RequiresPermissions("manage:receipt:add")
	@RequestMapping(value="addBack/{norder_id}",method=RequestMethod.GET)
	public String addBack(Model model,@PathVariable String norder_id) {
		model.addAttribute("number", service.queryByOrderId(norder_id).size());
		model.addAttribute("norder_id", norder_id);
		return "nlf/receipt/add";
	}

	@RequiresPermissions("manage:receipt:add")
	@RequestMapping("add")
	@ResponseBody
	public AjaxResult add(Receipt po) {
		if (service.add(po)) {
			return new AjaxResult("0");
		}
		return new AjaxResult("1");
	}

	@RequiresPermissions("manage:receipt:update")
	@RequestMapping("update/{id}")
	public String update(@PathVariable String id,Model model) {
		model.addAttribute("receipt",service.queryById(id));
		return "nlf/receipt/update";
	}

	@RequiresPermissions("manage:receipt:update")
	@RequestMapping("update")
	@ResponseBody
	public AjaxResult update(Receipt po) {
		if (service.update(po)) {
			return new AjaxResult("0");
		} else {
			return new AjaxResult("1");
		}
	}

	@RequiresPermissions("manage:receipt:del")
	@RequestMapping("del/{id}")
	@ResponseBody
	public AjaxResult del(@PathVariable String id) {
		if (service.del(id)) {
			return new AjaxResult("0");
		}
		return new AjaxResult("1");
	}
}
