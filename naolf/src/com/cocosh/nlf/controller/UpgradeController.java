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
import com.cocosh.nlf.model.Upgrade;
import com.cocosh.nlf.model.UserTicket;
import com.cocosh.nlf.service.UpgradeService;
import com.cocosh.nlf.service.UserTicketService;

@Controller
@RequestMapping("manage/upgrade")
public class UpgradeController extends BaseController {
	@Autowired
	private UpgradeService service;
	@Autowired
	private UserTicketService utService;

	@RequiresPermissions("manage:upgrade:list")
	@RequestMapping("list")
	public String list(BaseConditionVO vo, Model model) {
		Upgrade upgrade=service.queryByutid123(vo.getId());
		UserTicket ut3=utService.queryById(vo.getId());
		UserTicket ut1=utService.queryById(upgrade.getUser_ticket_id1());
		UserTicket ut2=utService.queryById(upgrade.getUser_ticket_id2());
		model.addAttribute("ut1", ut1);
		model.addAttribute("ut2", ut2);
		model.addAttribute("ut3", ut3);
		model.addAttribute("upgrade", upgrade);
		return "nlf/upgrade/list";
	}
	
	@RequiresPermissions("manage:upgrade:add")
	@RequestMapping(value="add",method=RequestMethod.GET)
	public String add(Model model) {
		return "upgrade/add";
	}

	@RequiresPermissions("manage:upgrade:add")
	@RequestMapping("add")
	@ResponseBody
	public AjaxResult add(Upgrade po) {
		if (service.add(po)) {
			return new AjaxResult("0");
		}
		return new AjaxResult("1");
	}

	@RequiresPermissions("manage:upgrade:update")
	@RequestMapping("update/{id}")
	public String update(@PathVariable String id,Model model) {
		model.addAttribute("upgrade",service.queryById(id));
		return "upgrade/update";
	}

	@RequiresPermissions("manage:upgrade:update")
	@RequestMapping("update")
	@ResponseBody
	public AjaxResult update(Upgrade po) {
		if (service.update(po)) {
			return new AjaxResult("0");
		} else {
			return new AjaxResult("1");
		}
	}

	@RequiresPermissions("manage:upgrade:del")
	@RequestMapping("del/{id}")
	@ResponseBody
	public AjaxResult del(@PathVariable String id) {
		if (service.del(id)) {
			return new AjaxResult("0");
		}
		return new AjaxResult("1");
	}
}
