package com.cocosh.car.model;

import java.util.Date;

import com.cocosh.framework.excel.ExcelField;
import com.cocosh.framework.util.DateUtil;

/**
 * 充电订单导出
 * 
 * @author jerry
 */
public class ChargeOrderExport {
	private String sn;
	private String connectorID;
	private String startTime;
	private String endTime;
	private Double totalPower;
	private Double totalElecMoney;
	private Double totalSeviceMoney;
	private Double totalMoney;
	private String stopReason;
	private String mem_name;
	private String mem_mobile;
	private String firm_name;
	private String status;// 订单状态 0：充电中 1：已完成 2：已对账 -1：取消
	private String pay_type;// 支付方式 0：后付款 1：预充值
	private String pay_status;// 支付状态 0：未支付 1：已支付
	private Double pay_amount;
	private String remark;

	@ExcelField(title="订单编号",sort=1)
	public String getSn() {
		return sn;
	}

	public void setSn(String sn) {
		this.sn = sn;
	}

	@ExcelField(title="设备接口编号",sort=2)
	public String getConnectorID() {
		return connectorID;
	}

	public void setConnectorID(String connectorID) {
		this.connectorID = connectorID;
	}

	@ExcelField(title="开始充电时间",sort=3)
	public String getStartTime() {
		return DateUtil.formatDate(DateUtil.stringToDate(startTime, "yyyy-MM-dd HH:mm:ss"),"yyyy-MM-dd HH:mm:ss");
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	@ExcelField(title="结束充电时间",sort=4)
	public String getEndTime() {
		return DateUtil.formatDate(DateUtil.stringToDate(endTime, "yyyy-MM-dd HH:mm:ss"),"yyyy-MM-dd HH:mm:ss");
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	@ExcelField(title="累计充电量",numType="Double",sort=5)
	public Double getTotalPower() {
		return totalPower;
	}

	public void setTotalPower(Double totalPower) {
		this.totalPower = totalPower;
	}

	@ExcelField(title="总电费",numType="Double",sort=6)
	public Double getTotalElecMoney() {
		return totalElecMoney;
	}

	public void setTotalElecMoney(Double totalElecMoney) {
		this.totalElecMoney = totalElecMoney;
	}

	@ExcelField(title="总服务费",numType="Double",sort=7)
	public Double getTotalSeviceMoney() {
		return totalSeviceMoney;
	}

	public void setTotalSeviceMoney(Double totalSeviceMoney) {
		this.totalSeviceMoney = totalSeviceMoney;
	}

	@ExcelField(title="累计总金额",numType="Double",sort=8)
	public Double getTotalMoney() {
		return totalMoney;
	}

	public void setTotalMoney(Double totalMoney) {
		this.totalMoney = totalMoney;
	}

	@ExcelField(title="充电结束原因",sort=9)
	public String getStopReason() {
		if (stopReason.equals("0")) {
			return "用户手动停止充电";
		}else if (stopReason.equals("1")) {
			return "客户归属地运营商平台停止充电";
		}else if (stopReason.equals("2")) {
			return "BMS停止充电";
		}else if (stopReason.equals("3")) {
			return "充电机设备故障";
		}else if (stopReason.equals("4")) {
			return "连接器断开";
		}else {
			return "其他";
		}
	}

	public void setStopReason(String stopReason) {
		this.stopReason = stopReason;
	}

	@ExcelField(title="会员姓名",sort=10)
	public String getMem_name() {
		return mem_name;
	}

	public void setMem_name(String mem_name) {
		this.mem_name = mem_name;
	}

	@ExcelField(title="联系电话",sort=11)
	public String getMem_mobile() {
		return mem_mobile;
	}

	public void setMem_mobile(String mem_mobile) {
		this.mem_mobile = mem_mobile;
	}

	@ExcelField(title="企业",sort=12)
	public String getFirm_name() {
		return firm_name;
	}

	public void setFirm_name(String firm_name) {
		this.firm_name = firm_name;
	}

	@ExcelField(title="订单状态",sort=13)
	public String getStatus() {
		if (status.equals("0")) {
			return "充电中";
		}else if(status.equals("1")){
			return "已完成";
		}else if(status.equals("2")){
			return "已对账";
		}else {
			return "已取消";
		}
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@ExcelField(title="支付方式",sort=14)
	public String getPay_type() {
		if (pay_type.equals("0")) {
			return "后付款";
		}
		return "预充值";
	}

	public void setPay_type(String pay_type) {
		this.pay_type = pay_type;
	}

	@ExcelField(title="支付状态",sort=15)
	public String getPay_status() {
		if (pay_status.equals("0")) {
			return "未支付";
		}else {
			return "已支付";
		}
	}

	public void setPay_status(String pay_status) {
		this.pay_status = pay_status;
	}

	@ExcelField(title="支付金额",sort=16)
	public Double getPay_amount() {
		return pay_amount;
	}

	public void setPay_amount(Double pay_amount) {
		this.pay_amount = pay_amount;
	}

	@ExcelField(title="备注",sort=17)
	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

}
