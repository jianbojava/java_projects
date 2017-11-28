package com.cocosh.nlf.controller;

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
import com.cocosh.nlf.model.Recharge;
import com.cocosh.nlf.service.RechargeService;
import com.cocosh.nlf.service.TicketService;
import com.cocosh.nlf.service.UserTicketService;
import com.cocosh.sys.service.UserService;

@Controller
@RequestMapping("manage/recharge")
public class RechargeController extends BaseController {
	@Autowired
	private RechargeService service;
	@Autowired
	private UserTicketService userTicketService;
	@Autowired
	private TicketService ticketservice;
	@Autowired
	private UserService userService;

	

	@RequiresPermissions("manage:recharge:list")
	@RequestMapping("sumlist")
	public String sumList(BaseConditionVO vo, Model model) {
		Page<Recharge> page=service.querySumPage(vo);
		model.addAttribute("page", page);
		model.addAttribute("vo", vo);
		return "nlf/resetticket/sumlist";
	}
	
	@RequiresPermissions("manage:recharge:list")
	@RequestMapping("list")
	public String list(BaseConditionVO vo, Model model) {
		Page<Recharge> page=service.queryPage(vo);
		model.addAttribute("page", page);
		model.addAttribute("vo", vo);
		return "nlf/resetticket/list";
	}
	
	@RequiresPermissions("manage:recharge:list")
	@RequestMapping("alllist")
	public String alllist(BaseConditionVO vo, Model model) {
		Page<Recharge> page=service.queryPage(vo);
		model.addAttribute("page", page);
		model.addAttribute("vo", vo);
		return "nlf/resetticket/alllist";
	}
	
	@RequiresPermissions("manage:recharge:add")
	@RequestMapping(value="add",method=RequestMethod.GET)
	public String add(BaseConditionVO vo,Model model) {
		model.addAttribute("member",userService.queryByUsertype(null));
		model.addAttribute("ticket",ticketservice.queryAllReset());
		return "nlf/resetticket/add";
	}
	@RequiresPermissions("manage:recharge:add")
	@RequestMapping(value="rcgreturn",method=RequestMethod.GET)
	public String rcgreturn(BaseConditionVO vo,Model model) {
		model.addAttribute("member",userService.queryByUsertype(null));
//		model.addAttribute("ticket",ticketservice.queryAllReset());
		return "nlf/resetticket/rcgreturn";
	}
	@RequiresPermissions("manage:recharge:add")
	@RequestMapping("add")
	@ResponseBody
	public AjaxResult add(@RequestBody Recharge po) {
		if (service.add(po)) {
			return new AjaxResult("0");
		}
		return new AjaxResult("1");
	}
	@RequiresPermissions("manage:recharge:add")
	@RequestMapping("returnsave")
	@ResponseBody
	public AjaxResult returnsave(@RequestBody Recharge po) {
		if (service.add(po)) {
			return new AjaxResult("0");
		}
		return new AjaxResult("1");
	}
	
	@RequiresPermissions("manage:recharge:add")
	@RequestMapping("querybyid")
	@ResponseBody
	public AjaxResult querybyid(BaseConditionVO vo) {
		Recharge r = service.queryById(vo.getId());
		if (r!=null){
			if(r.getRecharge_type()==0){
				 return new AjaxResult("1","请输入积分充值流水号");
			}
			
			if(!vo.getUser_id().equals(r.getUser_id())){
				return new AjaxResult("1","输入的会员跟充值流水号不匹配");
			}
			
			return new AjaxResult("0","Sucess",r);
		}
		return new AjaxResult("1","输入的充值流水号不存在");
	}

	@RequiresPermissions("manage:recharge:update")
	@RequestMapping("update/{id}")
	public String update(@PathVariable String id,Model model) {
		model.addAttribute("recharge",service.queryById(id));
		return "recharge/update";
	}
	
	
	

	@RequiresPermissions("manage:recharge:update")
	@RequestMapping("update")
	@ResponseBody
	public AjaxResult update(Recharge po) {
		if (service.update(po)) {
			return new AjaxResult("0");
		} else {
			return new AjaxResult("1");
		}
	}

	@RequiresPermissions("manage:recharge:del")
	@RequestMapping("del/{id}")
	@ResponseBody
	public AjaxResult del(@PathVariable String id) {
		if (service.del(id)) {
			return new AjaxResult("0");
		}
		return new AjaxResult("1");
	}
	
	
	@RequiresPermissions("manage:recharge:return")
	@RequestMapping("ret")
	@ResponseBody
	public AjaxResult ret(@PathVariable String id) {
		if (service.del(id)) {
			return new AjaxResult("0");
		}
		return new AjaxResult("1");
	}
}
