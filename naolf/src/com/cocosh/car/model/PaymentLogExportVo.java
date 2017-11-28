package com.cocosh.car.model;

import com.cocosh.framework.excel.ExcelField;
import com.cocosh.framework.util.DateUtil;

public class PaymentLogExportVo {
	private String sn;// 单号
	private String user_name;
	private String mem_name;
	private String firm_name;
	private Double amount;// 金额
	private String descript;// 日志描述
	private Integer pay_type;// 支付方式 0:余额 1：支付宝 2：微信 3：银联 4：线下
	private Integer pay_status;// 0 未支付 1：已支付
	private String trade_no;// 交易流水号
	private Integer type;// 类型 type 0:订单支付 1：充值保证金 2：充值账号金额 3:订单退款（取消订单等）4：保证金退返  5:余额退返
	private String create_date;// 时间
	private Integer channel;// 渠道 //0：pc 1：phone
	private String order_sn;//订单号
	private String remark;//备注

	private String pay_type_str;
	private String pay_status_str;
	private String channel_str;

	@ExcelField(title="流水号",sort=1)
	public String getSn() {
		return sn;
	}

	public void setSn(String sn) {
		this.sn = sn;
	}

	@ExcelField(title="系统用户",sort=2)
	public String getUser_name() {
		return user_name;
	}

	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}

	@ExcelField(title="会员名",sort=3)
	public String getMem_name() {
		return mem_name;
	}

	public void setMem_name(String mem_name) {
		this.mem_name = mem_name;
	}

	@ExcelField(title="企业",sort=4)
	public String getFirm_name() {
		return firm_name;
	}

	public void setFirm_name(String firm_name) {
		this.firm_name = firm_name;
	}

	@ExcelField(title="明细",sort=5)
	public String getDescript() {
		return descript;
	}

	public void setDescript(String descript) {
		this.descript = descript;
	}
	
	@ExcelField(title="金额",sort=6,numType="Double")
	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
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

	@ExcelField(title="支付交易号",sort=7)
	public String getTrade_no() {
		return trade_no;
	}

	public void setTrade_no(String trade_no) {
		this.trade_no = trade_no;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public Integer getChannel() {
		return channel;
	}

	public void setChannel(Integer channel) {
		this.channel = channel;
	}

	@ExcelField(title="支付方式",sort=8)
	public String getPay_type_str() {
		//0:余额 1：支付宝 2：微信 3：银联 4：线下
		switch (pay_type) {
		case 0:
			return "余额";
		case 1:
			return "支付宝";
		case 2:
			return "微信";
		case 3:
			return "银联";
		case 4:
			return "线下";
		default:
			break;
		}
		return null;
	}

	public void setPay_type_str(String pay_type_str) {
		this.pay_type_str = pay_type_str;
	}

	@ExcelField(title="支付状态",sort=9)
	public String getPay_status_str() {
		switch (pay_status) {
		case 0:
			return "未支付";
		case 1:
			return "已支付";
		default:
			break;
		}
		return null;
	}

	public void setPay_status_str(String pay_status_str) {
		this.pay_status_str = pay_status_str;
	}
	
	
	@ExcelField(title="时间",sort=10)
	public String getCreate_date() {
		return DateUtil.formatDate(DateUtil.stringToDate(create_date, "yyyy-MM-dd HH:mm:ss"),"yyyy-MM-dd HH:mm:ss");
	}

	public void setCreate_date(String create_date) {
		this.create_date = create_date;
	}

	@ExcelField(title="平台",sort=11)
	public String getChannel_str() {
		switch (pay_status) {
		case 0:
			return "系统";
		case 1:
			return "APP";
		default:
			break;
		}
		return null;
	}

	public void setChannel_str(String channel_str) {
		this.channel_str = channel_str;
	}
	
	@ExcelField(title="订单编号",sort=12)
	public String getOrder_sn() {
		return order_sn;
	}

	public void setOrder_sn(String order_sn) {
		this.order_sn = order_sn;
	}

	@ExcelField(title="备注",sort=13)
	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}
}
