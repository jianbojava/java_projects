package com.cocosh.car.model;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;

import com.cocosh.framework.base.BaseEntity;
import com.cocosh.framework.util.StringUtil;
import com.cocosh.sys.model.User;

public class PaymentLog extends BaseEntity {
	private String sn;// 单号
	private String user_id;// 系统用户ID
	private String mem_id;// 会员ID
	private String firm_id;// 企业ID
	private String order_sn;// 订单相关显示订单号
	private Double amount;// 金额
	private String descript;// 日志描述
	private Integer channel;// 渠道 //0：pc 1：phone
	private Integer pay_type;// 支付方式 0:线下付款  1：支付宝 2：微信 3：银联 ；4积分支付(支付金额0),5券支付(支付金额0)
	private Integer type;// 类型 type 0:订单支付 1：充值保证金 2：充值账号金额 3:订单退款（取消订单等）4：保证金退返
							// 5:余额退返
	                        //naolf：0订单支付，1充值积分
	private String trade_no;// 交易流水号
	private Integer pay_status;// 0 未支付 1：已支付
	private String remark;// 备注

	/***** 非数据库字段 *****/
	private String user_name;
	private String mem_name;
	private String firm_name;

	public PaymentLog() {
		super();
	}

	public PaymentLog(String mem_id, String firm_id, Double amount,
			String descript, String order_sn, String trade_no,
			Integer pay_type, Integer pay_status, Integer type, String remark) {
		Subject sub = SecurityUtils.getSubject();
		Session session = sub.getSession();
		User user = (User) session.getAttribute("user");
		this.sn = StringUtil.buildOrderSn();
		this.user_id = user != null ? user.getId() : null;
		this.mem_id = mem_id;
		this.firm_id = firm_id;
		this.amount = amount;
		this.descript = descript;
		this.order_sn = order_sn;
		this.trade_no = trade_no;
		this.channel = user != null ? 0 : 1;
		this.pay_type = pay_type;
		this.type = type;
		this.pay_status = pay_status;
		this.remark = remark;
	}

	public String getSn() {
		return sn;
	}

	public void setSn(String sn) {
		this.sn = sn;
	}

	public String getUser_id() {
		return user_id;
	}

	public void setUser_id(String user_id) {
		this.user_id = user_id;
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

	public String getOrder_sn() {
		return order_sn;
	}

	public void setOrder_sn(String order_sn) {
		this.order_sn = order_sn;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public String getDescript() {
		return descript;
	}

	public void setDescript(String descript) {
		this.descript = descript;
	}

	public Integer getChannel() {
		return channel;
	}

	public void setChannel(Integer channel) {
		this.channel = channel;
	}

	public Integer getPay_type() {
		return pay_type;
	}

	public void setPay_type(Integer pay_type) {
		this.pay_type = pay_type;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public String getTrade_no() {
		return trade_no;
	}

	public void setTrade_no(String trade_no) {
		this.trade_no = trade_no;
	}

	public Integer getPay_status() {
		return pay_status;
	}

	public void setPay_status(Integer pay_status) {
		this.pay_status = pay_status;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getUser_name() {
		return user_name;
	}

	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}

	public String getMem_name() {
		return mem_name;
	}

	public void setMem_name(String mem_name) {
		this.mem_name = mem_name;
	}

	public String getFirm_name() {
		return firm_name;
	}

	public void setFirm_name(String firm_name) {
		this.firm_name = firm_name;
	}

}