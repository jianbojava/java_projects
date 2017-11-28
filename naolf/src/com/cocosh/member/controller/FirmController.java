package com.cocosh.member.controller;

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
import com.cocosh.member.model.Firm;
import com.cocosh.member.service.FirmService;
import com.cocosh.sys.service.UserService;

@Controller
@RequestMapping("manage/firm")
public class FirmController extends BaseController {
	@Autowired
	private FirmService service;
	@Autowired
	private UserService userService;

	@RequiresPermissions("manage:firm:list")
	@RequestMapping("list")
	public String list(BaseConditionVO vo, Model model) {
		dataAuth(vo);
		Page<Firm> page=service.queryPage(vo);
		model.addAttribute("page", page);
		model.addAttribute("vo", vo);
		return "member/firm/list";
	}

	@RequiresPermissions("manage:firm:update")
	@RequestMapping("update/{id}")
	public String update(@PathVariable String id,Model model) {
		model.addAttribute("firm",service.queryById(id));
		model.addAttribute("sales",userService.queryByRole(CommonUtil.ROLE_SALE));
		return "member/firm/update";
	}

	@RequiresPermissions("manage:firm:update")
	@RequestMapping("update")
	@ResponseBody
	public AjaxResult update(Firm po) {
		if (service.update(po)) {
			return new AjaxResult("0");
		} else {
			return new AjaxResult("1");
		}
	}

	@RequiresPermissions("manage:firm:add")
	@RequestMapping(value="add",method=RequestMethod.GET)
	public String add(Model model) {
		model.addAttribute("sales",userService.queryByRole(CommonUtil.ROLE_SALE));
		return "member/firm/add";
	}

	@RequiresPermissions("manage:firm:add")
	@RequestMapping("add")
	@ResponseBody
	public AjaxResult add(Firm po) {
		if (service.add(po)) {
			return new AjaxResult("0");
		}
		return new AjaxResult("1");
	}

	@RequiresPermissions("manage:firm:del")
	@RequestMapping("del/{flg}/{id}")
	@ResponseBody
	public AjaxResult del(@PathVariable Integer flg,@PathVariable String id) {
		if (service.del(flg,id)) {
			return new AjaxResult("0");
		}
		return new AjaxResult("1");
	}
	
	/**
	 * 充值
	 * @param po
	 * @return
	 */
	@RequiresPermissions("manage:firm:recharge")
	@RequestMapping("recharge/{id}")
	public String recharge(@PathVariable String id,Model model) {
		model.addAttribute("firm",service.queryById(id));
		return "member/firm/recharge";
	}
	
	@RequiresPermissions("manage:firm:recharge")
	@RequestMapping("recharge")
	@ResponseBody
	public AjaxResult recharge(Firm po) {
		if (service.recharge(po)) {
			return new AjaxResult("0");
		}
		return new AjaxResult("1");
	}
	
}
