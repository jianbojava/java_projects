package com.cocosh.car.model;

import java.util.Date;

import com.cocosh.framework.base.BaseEntity;
import com.cocosh.framework.util.DateUtil;
import com.cocosh.framework.util.StringUtil;

public class ChargeOrder extends BaseEntity {
	private String sn;
	private String connectorID;
	private Date startTime;
	private Date endTime;
	private Double totalPower;
	private Double totalElecMoney;
	private Double totalSeviceMoney;
	private Double totalMoney;
	private Integer stopReason;
	private Integer sumPeriod;
	private String mem_id;
	private String firm_id;
	private Integer status;// 订单状态 0：充电中 1：已完成 2：已对账 -1：取消
	private Integer pay_type;// 支付方式 0：后付款 1：预充值
	private Integer pay_status;// 支付状态 0：待支付 1：已支付
	private Double pay_amount;
	private String remark;
	private Integer is_read;// 0：未查看 1：已查看
	private String notify_json;// 订单json数据（特来电）

	/****** 非数据库字段 ******/
	private String charge_times;// 充电时长
	private String order_status;// 订单状态
	private String[] sns;// 操作记录集合

	public ChargeOrder() {
		super();
	}

	public ChargeOrder(String sn, String connectorID, String mem_id,
			String firm_id, Integer pay_type) {
		super();
		this.sn = sn;
		this.connectorID = connectorID;
		this.mem_id = mem_id;
		this.firm_id = firm_id;
		this.pay_type = pay_type;
	}

	public ChargeOrder(String notify_json) {
		super();
		this.sn = StringUtil.buildOrderSn();
		this.notify_json = notify_json;
	}

	public String getSn() {
		return sn;
	}

	public void setSn(String sn) {
		this.sn = sn;
	}

	public String getConnectorID() {
		return connectorID;
	}

	public void setConnectorID(String connectorID) {
		this.connectorID = connectorID;
	}

	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public Double getTotalPower() {
		return totalPower;
	}

	public void setTotalPower(Double totalPower) {
		this.totalPower = totalPower;
	}

	public Double getTotalElecMoney() {
		return totalElecMoney;
	}

	public void setTotalElecMoney(Double totalElecMoney) {
		this.totalElecMoney = totalElecMoney;
	}

	public Double getTotalSeviceMoney() {
		return totalSeviceMoney;
	}

	public void setTotalSeviceMoney(Double totalSeviceMoney) {
		this.totalSeviceMoney = totalSeviceMoney;
	}

	public Double getTotalMoney() {
		return totalMoney;
	}

	public void setTotalMoney(Double totalMoney) {
		this.totalMoney = totalMoney;
	}

	public Integer getStopReason() {
		return stopReason;
	}

	public void setStopReason(Integer stopReason) {
		this.stopReason = stopReason;
	}

	public Integer getSumPeriod() {
		return sumPeriod;
	}

	public void setSumPeriod(Integer sumPeriod) {
		this.sumPeriod = sumPeriod;
	}

	public String getMem_id() {
		return mem_id;
	}

	public void setMem_id(String mem_id) {
		this.mem_id = mem_id;
	}

	public String getFirm_id() {
		return firm_id;
	}

	public void setFirm_id(String firm_id) {
		this.firm_id = firm_id;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Integer getPay_type() {
		return pay_type;
	}

	public void setPay_type(Integer pay_type) {
		this.pay_type = pay_type;
	}

	public Integer getPay_status() {
		return pay_status;
	}

	public void setPay_status(Integer pay_status) {
		this.pay_status = pay_status;
	}

	public Double getPay_amount() {
		return pay_amount;
	}

	public void setPay_amount(Double pay_amount) {
		this.pay_amount = pay_amount;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Integer getIs_read() {
		return is_read;
	}

	public void setIs_read(Integer is_read) {
		this.is_read = is_read;
	}

	public String getNotify_json() {
		return notify_json;
	}

	public void setNotify_json(String notify_json) {
		this.notify_json = notify_json;
	}

	public String getCharge_times() {
		if (startTime==null||endTime==null) {
			return "-";
		}
		long diff=DateUtil.dateMinuteDiff(startTime,endTime);
		long h=diff/60;
		long m=diff%60;
		return (h<10?"0"+h:h)+"时"+(m<10?"0"+m:m)+"分";
	}

	public String getOrder_status() {
		return getOrderStatus(status);
	}

	public void setOrder_status(String order_status) {
		this.order_status = order_status;
	}

	public String getOrderStatus(Integer status) {
		switch (status) {
		case 0:
			order_status = "充电中";
			break;
		case 1:
			order_status = "充电结束";
			break;
		case 2:
			order_status = "已对账";
			break;
		case -1:
			order_status = "已取消";
			break;
		default:
			break;
		}
		return order_status;
	}

	public String[] getSns() {
		return sns;
	}

	public void setSns(String[] sns) {
		this.sns = sns;
	}
}