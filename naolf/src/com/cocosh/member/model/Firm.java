package com.cocosh.member.model;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.cocosh.framework.base.BaseEntity;

/**
 * 企业会员对应表zxj_firm
 * 
 * @author bobo
 * 
 */
public class Firm extends BaseEntity {
	private String name;// 企业名
	private String tel;// 联系电话
	private String representer;// 企业联系人
	private String address;// 企业地址
	private String email;// 企业邮箱
	private String url;// 企业网址
	private String bus_license;// 营业执照
	private String sale_id;// 销售ID 对应user_id
	private String sale_name;// 销售姓名
	private Double deposit;//保证金
	private Double credit_amount;// 信用额度
	private Double used_amount;// 已用额度
	private Double left_amount;// 剩余额度
	private Integer pay_type;// 支付类型 0：后付款 1：预充值
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date sign_date;// 签约时间
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date end_date;// 终止时间
	private String remark;// 备注
	private Integer enabled;// 0可用，1禁用

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getRepresenter() {
		return representer;
	}

	public void setRepresenter(String representer) {
		this.representer = representer;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getBus_license() {
		return bus_license;
	}

	public void setBus_license(String bus_license) {
		this.bus_license = bus_license;
	}

	public String getSale_id() {
		return sale_id;
	}

	public void setSale_id(String sale_id) {
		this.sale_id = sale_id;
	}

	public String getSale_name() {
		return sale_name;
	}

	public void setSale_name(String sale_name) {
		this.sale_name = sale_name;
	}

	public Double getDeposit() {
		return deposit;
	}

	public void setDeposit(Double deposit) {
		this.deposit = deposit;
	}

	public Double getCredit_amount() {
		return credit_amount;
	}

	public void setCredit_amount(Double credit_amount) {
		this.credit_amount = credit_amount;
	}

	public Double getUsed_amount() {
		return used_amount;
	}

	public void setUsed_amount(Double used_amount) {
		this.used_amount = used_amount;
	}

	public Double getLeft_amount() {
		return left_amount;
	}

	public void setLeft_amount(Double left_amount) {
		this.left_amount = left_amount;
	}

	public Integer getPay_type() {
		return pay_type;
	}

	public void setPay_type(Integer pay_type) {
		this.pay_type = pay_type;
	}

	public Date getSign_date() {
		return sign_date;
	}

	public void setSign_date(Date sign_date) {
		this.sign_date = sign_date;
	}

	public Date getEnd_date() {
		return end_date;
	}

	public void setEnd_date(Date end_date) {
		this.end_date = end_date;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Integer getEnabled() {
		return enabled;
	}

	public void setEnabled(Integer enabled) {
		this.enabled = enabled;
	}

}