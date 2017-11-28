package com.cocosh.car.controller;

import java.io.IOException;
import java.util.Date;

import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.cocosh.framework.base.BaseConditionVO;
import com.cocosh.framework.base.BaseController;
import com.cocosh.framework.excel.ExportExcel;
import com.cocosh.framework.mybatis.Page;
import com.cocosh.framework.util.DateUtil;
import com.cocosh.car.model.PaymentLog;
import com.cocosh.car.model.PaymentLogExportVo;
import com.cocosh.car.service.PaymentLogService;

@Controller
@RequestMapping("manage/paymentLog")
public class PaymentLogController extends BaseController {
	@Autowired
	private PaymentLogService service;

	@RequiresPermissions("manage:paymentLog:list")
	@RequestMapping("list")
	public String list(BaseConditionVO vo, Model model) {
		Page<PaymentLog> page=service.queryPage(vo);
		model.addAttribute("page", page);
		model.addAttribute("firms", service.queryFirms());
		model.addAttribute("vo", vo);
		return "car/paymentlog/list";
	}
	
	// 导出Excel
	@RequiresPermissions("manage:paymentLog:export")
	@RequestMapping("export")
	public void export(BaseConditionVO vo, HttpServletResponse response) {
		try {
			new ExportExcel(PaymentLogExportVo.class).setData(service.queryExport(vo)).write(response,"交易流水"+ DateUtil.formatDate(new Date(),"yyyyMMddHHmmss") + ".xls");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
