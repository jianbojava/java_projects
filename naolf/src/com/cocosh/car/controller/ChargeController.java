package com.cocosh.car.controller;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cocosh.framework.base.AjaxResult;
import com.cocosh.framework.base.BaseConditionVO;
import com.cocosh.framework.base.BaseController;
import com.cocosh.framework.excel.ExportExcel;
import com.cocosh.framework.mybatis.Page;
import com.cocosh.framework.util.DateUtil;
import com.cocosh.member.service.MemberService;
import com.cocosh.car.model.ChargeOrder;
import com.cocosh.car.model.ChargeOrderDetail;
import com.cocosh.car.model.ChargeOrderExport;
import com.cocosh.car.service.ChargeOrderService;
import com.cocosh.car.service.ChargeService;
import com.cocosh.car.service.OrderLogService;

@Controller
@RequestMapping("manage/charge")
public class ChargeController extends BaseController {
	@Autowired
	private ChargeService service;
	@Autowired
	private ChargeOrderService orderService;
	@Autowired
	private MemberService memberService;
	@Autowired
	private OrderLogService logService;

	/**
	 * 充电订单 
	 */
	@RequiresPermissions("manage:charge:list")
	@RequestMapping("list")
	public String order(BaseConditionVO vo, Model model) {
		Page<ChargeOrder> page=orderService.queryPage(vo);
		model.addAttribute("firms", orderService.queryFirmFromOrder());
		model.addAttribute("page", page);
		model.addAttribute("vo", vo);
		return "car/charge/list";
	}
	
	/**
	 * 充电单详情
	 */
	@RequiresPermissions("manage:charge:view")
	@RequestMapping("view/{sn}")
	public String order(@PathVariable String sn,Model model) {
		ChargeOrder order=orderService.queryById(sn);
		List<ChargeOrderDetail> details=orderService.queryOrderDetail(sn);
		model.addAttribute("order",order);
		model.addAttribute("details",details);
		model.addAttribute("member", memberService.queryById(order.getMem_id()));
		model.addAttribute("logs", logService.query(sn));
		// 修改订单为已读
		orderService.updateIsRead(sn);
		return "car/charge/view";
	}
	
	/**
	 * 订单修改
	 */
	@RequiresPermissions("manage:charge:update")
	@RequestMapping("update/{sn}")
	public String update(@PathVariable String sn, Model model) {
		ChargeOrder order=orderService.queryById(sn);
		List<ChargeOrderDetail> details=orderService.queryOrderDetail(sn);
		model.addAttribute("order",order);
		model.addAttribute("details",details);
		model.addAttribute("member", memberService.queryById(order.getMem_id()));
		model.addAttribute("logs", logService.query(sn));
		// 修改订单为已读
		orderService.updateIsRead(sn);
		return "car/charge/update";
	}

	@RequiresPermissions("manage:charge:update")
	@RequestMapping("update")
	@ResponseBody
	public AjaxResult update(ChargeOrder po) {
		if (orderService.update(po)) {
			return new AjaxResult("0");
		} else {
			return new AjaxResult("1");
		}
	}
	
	/**
	 * 订单对账
	 */
	@RequiresPermissions("manage:charge:checkAmount")
	@RequestMapping("checkAmount")
	@ResponseBody
	public AjaxResult checkAmount(ChargeOrder po){
		if (orderService.checkAmount(po)) {
			return new AjaxResult("0");
		}
		return new AjaxResult("1");
	}
	
	/**
	 * 导出Excel
	 */
	@RequiresPermissions("manage:charge:export")
	@RequestMapping("export")
	public void export(BaseConditionVO vo, HttpServletResponse response) {
		try {
			new ExportExcel(ChargeOrderExport.class).setData(orderService.queryExport(vo)).write(response,"充电订单"+ DateUtil.formatDate(new Date(),"yyyyMMddHHmmss") + ".xls");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 查询充电站信息
	 */
	@RequiresPermissions("manage:stations:sync")
	@RequestMapping("query_stations_info")
	@ResponseBody
	public AjaxResult query_stations_info() {
		return service.query_stations_info();
	}
	
	/*********以下接口非必须*************/
	
	/**
	 * 设备接口状态查询
	 */
	@RequestMapping("query_station_status")
	@ResponseBody
	public Object query_station_status(){
		return service.query_station_status();
	}
	
	/**
	 * 设备接口状态查询
	 */
	@RequestMapping("query_station_stats")
	@ResponseBody
	public Object query_station_stats(){
		return service.query_station_stats();
	}
	
	/**
	 * 查询充电状态
	 */
	@RequestMapping("query_equip_charge_status")
	@ResponseBody
	public Object query_equip_charge_status(){
		return service.query_equip_charge_status("");
	}
	
}
