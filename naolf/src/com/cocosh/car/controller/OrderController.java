package com.cocosh.car.controller;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

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
import com.cocosh.framework.excel.ExportExcel;
import com.cocosh.framework.mybatis.Page;
import com.cocosh.framework.util.DateUtil;
import com.cocosh.member.service.MemberService;
import com.cocosh.sys.service.DictService;
import com.cocosh.car.model.IllegalExport;
import com.cocosh.car.model.Order;
import com.cocosh.car.model.OrderExportVo;
import com.cocosh.car.model.TimeOrderExportVo;
import com.cocosh.car.service.ChargeOrderService;
import com.cocosh.car.service.DotService;
import com.cocosh.car.service.IllegalService;
import com.cocosh.car.service.OrderLogService;
import com.cocosh.car.service.OrderService;
import com.cocosh.car.model.Illegal;

@Controller
@RequestMapping("manage/order")
public class OrderController extends BaseController {
	@Autowired
	private OrderService service;
	@Autowired
	private DotService dotService;
	@Autowired
	private MemberService memberService;
	@Autowired
	private OrderLogService logService;
	@Autowired
	private DictService dictService;
	@Autowired
	private IllegalService illegalService;
	@Autowired
	private ChargeOrderService chargeService;

	/**
	 * 日租订单
	 * 
	 * @param vo
	 * @param model
	 * @return
	 */
	@RequiresPermissions("manage:order:list")
	@RequestMapping("list")
	public String list(BaseConditionVO vo, Model model) {
		dataAuth(vo);
		vo.setType("0");
		Page<Order> page = service.queryPage(vo);
		model.addAttribute("page", page);
		model.addAttribute("disps", service.queryDispFromOrder(vo));
		model.addAttribute("firms", service.queryFirmFromOrder(vo));
		model.addAttribute("vo", vo);
		return "car/order/list";
	}

	/**
	 * 时租订单
	 * 
	 * @param vo
	 * @param model
	 * @return
	 */
	@RequiresPermissions("manage:order:tlist")
	@RequestMapping("tlist")
	public String dlist(BaseConditionVO vo, Model model) {
		dataAuth(vo);
		vo.setType("1");
		Page<Order> page = service.queryPage(vo);
		model.addAttribute("page", page);
		model.addAttribute("firms", service.queryFirmFromOrder(vo));
		model.addAttribute("vo", vo);
		return "car/order/tlist";
	}

	@RequiresPermissions("manage:order:add")
	@RequestMapping(value = "add", method = RequestMethod.GET)
	public String add(Model model) {
		model.addAttribute("dots", dotService.queryByAll());
		model.addAttribute("members", memberService.queryAll());
		model.addAttribute("insurance", Double.parseDouble(dictService.queryByCode("INSURANCE_AMOUNT")));
		model.addAttribute("distribution_range", Double.parseDouble(dictService.queryByCode("DISTRIBUTION_RANGE")));
		model.addAttribute("distribution_amount", Double.parseDouble(dictService.queryByCode("DISTRIBUTION_AMOUNT")));
		return "car/order/add";
	}

	@RequiresPermissions("manage:order:add")
	@RequestMapping("add")
	@ResponseBody
	public AjaxResult add(Order po) {
		return service.add(po, null);
	}

	@RequiresPermissions(value={"manage:order:update","manage:order:tupdate"},logical=Logical.OR)
	@RequestMapping("update/{id}")
	public String update(@PathVariable String id, Model model) {
		Order order = service.queryById(id);
		model.addAttribute("order", order);
		if (order.getType() == 0) {
			model.addAttribute("getDot",dotService.queryById(order.getGet_dot_id()));
			model.addAttribute("retDot",dotService.queryById(order.getReturn_dot_id()));
		}
		model.addAttribute("member", memberService.queryById(order.getMem_id()));
		model.addAttribute("logs", logService.query(id));
		// 修改订单为已读
		service.updateIsRead(id);
		return order.getType() == 0 ? "car/order/update" : "car/order/tupdate";
	}

	@RequiresPermissions("manage:order:update")
	@RequestMapping("update")
	@ResponseBody
	public AjaxResult update(Order po) {
		return service.update(po);
	}

	@RequiresPermissions(value={"manage:order:view","manage:order:tview"},logical=Logical.OR)
	@RequestMapping("view/{id}")
	public String view(@PathVariable String id, Model model) {
		Order order = service.queryById(id);
		model.addAttribute("order", order);
		if (order.getType() == 0) {// 日租订单查询网点信息
			model.addAttribute("getDot",dotService.queryById(order.getGet_dot_id()));
			model.addAttribute("retDot",dotService.queryById(order.getReturn_dot_id()));
		}
		model.addAttribute("member", memberService.queryById(order.getMem_id()));
		model.addAttribute("logs", logService.query(id));
		// 修改订单为已读
		service.updateIsRead(id);
		return order.getType() == 0 ? "car/order/view" : "car/order/tview";
	}

	// 导出Excel
	@RequiresPermissions(value={"manage:order:export","manage:order:texport"},logical=Logical.OR)
	@RequestMapping("export")
	public void export(BaseConditionVO vo, HttpServletResponse response) {
		dataAuth(vo);
		try {
			if (vo.getType().equals("0")) {
				new ExportExcel(OrderExportVo.class).setData(service.export(vo)).write(response,"日租订单"+ DateUtil.formatDate(new Date(),"yyyyMMddHHmmss") + ".xls");
			} else {
				new ExportExcel(TimeOrderExportVo.class).setData(service.timeExport(vo)).write(response,"时租订单"+ DateUtil.formatDate(new Date(),"yyyyMMddHHmmss") + ".xls");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@RequestMapping("newOrderCount")
	@ResponseBody
	public Map<String,Integer> newOrderCount() {
		Map<String,Integer> map=new HashMap<String, Integer>();
		map.put("tcount", service.queryNoReadCount(1));
		map.put("dcount", service.queryNoReadCount(0));
		map.put("ccount", chargeService.queryNoReadCount());
		return map;
	}

	/**
	 * 假删除
	 * 
	 * @param flg
	 * @param id
	 * @return
	 */
	@RequiresPermissions("manage:order:del_flg")
	@RequestMapping("del_flg/{id}")
	@ResponseBody
	public AjaxResult del_flg(@PathVariable String id) {
		if (service.del(id)) {
			return new AjaxResult("0");
		}
		return new AjaxResult("1");
	}

	/**
	 * 违章查询
	 * @param vo
	 * @param model
	 * @return
	 */
	@RequiresPermissions("manage:order:illegal")
	@RequestMapping("illegal")
	public String Illegal(BaseConditionVO vo, Model model) {
		Page<Illegal> page = illegalService.queryPage(vo);
		model.addAttribute("page", page);
		model.addAttribute("vo", vo);
		return "car/illegal/list";
	}
	
	/**
	 * 违章处理
	 * @param vo
	 * @param model
	 * @return
	 */
	@RequiresPermissions("manage:order:illegalUpd")
	@RequestMapping("illegalUpd")
	@ResponseBody
	public AjaxResult illegalUpd(Illegal po) {
		if(illegalService.update(po)){
			return new AjaxResult("0");
		}
		return new AjaxResult("1");
	}
	
	/**
	 * 违章处理
	 * @param vo
	 * @param model
	 * @return
	 */
	@RequiresPermissions("manage:order:illegalExport")
	@RequestMapping("illegalExport")
	public void illegalExport(BaseConditionVO vo, HttpServletResponse response) {
		try {
			new ExportExcel(IllegalExport.class).setData(illegalService.queryExport(vo)).write(response,"违章"+ DateUtil.formatDate(new Date(),"yyyyMMddHHmmss") + ".xls");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 时租订单对账
	 */
	
	@RequiresPermissions("manage:order:checkAmount")
	@RequestMapping("checkAmount")
	@ResponseBody
	public AjaxResult checkAmount(Order po){
		if (service.checkAmount(po)) {
			return new AjaxResult("0");
		}
		return new AjaxResult("1");
	}
	
}
