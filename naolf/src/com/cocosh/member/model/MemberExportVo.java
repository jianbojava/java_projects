package com.cocosh.member.model;

import com.cocosh.framework.excel.ExcelField;

public class MemberExportVo {
	private String number;// 会员编号
	private String card_number;// 会员卡号
	private String mobile;// 手机
	private String name;// 姓名
	private Double deposit;// 保证金
	private Double account;// 账号余额
	private String card_no;// 身份证号
	private String card_front;// 身份证正面照
	private String card_back;// 身份证背面照
	private String driver_no;// 驾驶证号
	private String driver_scan;// 驾驶证扫面件
	private String ship_addr;// 收货地址
	private String sale_name;// 销售姓名
	private String remark;// 备注

	@ExcelField(title="会员编号",sort=1)
	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	@ExcelField(title="会员卡号",sort=2)
	public String getCard_number() {
		return card_number;
	}

	public void setCard_number(String card_number) {
		this.card_number = card_number;
	}
	
	@ExcelField(title="姓名",sort=3)
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@ExcelField(title="联系电话",sort=4)
	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	@ExcelField(title="保证金",sort=5)
	public Double getDeposit() {
		return deposit;
	}

	public void setDeposit(Double deposit) {
		this.deposit = deposit;
	}

	@ExcelField(title="账号余额",sort=6)
	public Double getAccount() {
		return account;
	}

	public void setAccount(Double account) {
		this.account = account;
	}

	@ExcelField(title="身份证号",sort=7)
	public String getCard_no() {
		return card_no;
	}

	public void setCard_no(String card_no) {
		this.card_no = card_no;
	}

	@ExcelField(title="身份证正面照",sort=8)
	public String getCard_front() {
		return card_front;
	}

	public void setCard_front(String card_front) {
		this.card_front = card_front;
	}

	@ExcelField(title="身份证背面照",sort=9)
	public String getCard_back() {
		return card_back;
	}

	public void setCard_back(String card_back) {
		this.card_back = card_back;
	}

	@ExcelField(title="驾驶证号",sort=10)
	public String getDriver_no() {
		return driver_no;
	}

	public void setDriver_no(String driver_no) {
		this.driver_no = driver_no;
	}

	@ExcelField(title="驾驶证扫描件",sort=11)
	public String getDriver_scan() {
		return driver_scan;
	}

	public void setDriver_scan(String driver_scan) {
		this.driver_scan = driver_scan;
	}

	@ExcelField(title="收件地址",sort=12)
	public String getShip_addr() {
		return ship_addr;
	}

	public void setShip_addr(String ship_addr) {
		this.ship_addr = ship_addr;
	}

	@ExcelField(title="销售员",sort=13)
	public String getSale_name() {
		return sale_name;
	}

	public void setSale_name(String sale_name) {
		this.sale_name = sale_name;
	}

	@ExcelField(title="备注",sort=14)
	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}
}
