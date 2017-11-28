package com.cocosh.member.model;

import com.cocosh.framework.base.BaseEntity;

public class Member extends BaseEntity {
	private String number;// 会员编号
	private String card_number;// 会员卡号
	private String mobile;// 手机
	private String name;// 姓名
	private Double deposit;// 保证金
	private Double account;// 账号余额
	private Integer point;// 积分
	private String point_lv;// 会员等级
	private String card_no;// 身份证号
	private String card_front;// 身份证正面照
	private String card_back;// 身份证背面照
	private String driver_no;// 驾驶证号
	private String driver_scan;// 驾驶证扫面件
	private String driver_scan_back;//驾驶证扫面件副联
	private String ship_addr;// 收货地址
	private String head_img;// 会员头像
	private Integer type;// 会员类型 0普通会员 1企业个人会员
	private String remark;// 备注
	private String firm_id;// 企业id
	private String firm_name;// 企业名称
	private Integer review;// 状态 0待审核 1通过 2失败
	private String review_error;// 审核失败原因
	private String credential;// 证书
	private String pay_pwd;// 支付密码
	private Integer enabled;// 0可用，1禁用
	private String sale_id;// 销售ID
	private String sale_name;// 销售姓名
	private String old_card_number;//旧卡号（上次更新Evcard的卡号）
	private Integer evcard_status;//evcard同步状态
	private Integer del_flg;// 删除标识 0：正常 1：已删

	// 辅助字段
	private Double coupon_amount;// 智券金额
	private String headimg_base64;// 图片base64字符串
	private String cardfront_base64;// 身份证正面照base64字符串
	private String cardback_base64;// 身份证背面照base64字符串
	private String driverscan_base64;// 驾驶证正面照base64字符串
	private String driverscan_back_base64;//驾驶证背面base64字符串

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public String getCard_number() {
		return card_number;
	}

	public void setCard_number(String card_number) {
		this.card_number = card_number;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Double getDeposit() {
		return deposit;
	}

	public void setDeposit(Double deposit) {
		this.deposit = deposit;
	}

	public Double getAccount() {
		return account;
	}

	public void setAccount(Double account) {
		this.account = account;
	}

	public Integer getPoint() {
		return point;
	}

	public void setPoint(Integer point) {
		this.point = point;
	}

	public String getPoint_lv() {
		return point_lv;
	}

	public void setPoint_lv(String point_lv) {
		this.point_lv = point_lv;
	}

	public String getCard_no() {
		return card_no;
	}

	public void setCard_no(String card_no) {
		this.card_no = card_no;
	}

	public String getCard_front() {
		return card_front;
	}

	public void setCard_front(String card_front) {
		this.card_front = card_front;
	}

	public String getCard_back() {
		return card_back;
	}

	public void setCard_back(String card_back) {
		this.card_back = card_back;
	}

	public String getDriver_no() {
		return driver_no;
	}

	public void setDriver_no(String driver_no) {
		this.driver_no = driver_no;
	}

	public String getDriver_scan() {
		return driver_scan;
	}

	public void setDriver_scan(String driver_scan) {
		this.driver_scan = driver_scan;
	}

	public String getShip_addr() {
		return ship_addr;
	}

	public void setShip_addr(String ship_addr) {
		this.ship_addr = ship_addr;
	}

	public String getHead_img() {
		return head_img;
	}

	public void setHead_img(String head_img) {
		this.head_img = head_img;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getFirm_id() {
		return firm_id;
	}

	public void setFirm_id(String firm_id) {
		this.firm_id = firm_id;
	}

	public String getFirm_name() {
		return firm_name;
	}

	public void setFirm_name(String firm_name) {
		this.firm_name = firm_name;
	}

	public Integer getReview() {
		return review;
	}

	public void setReview(Integer review) {
		this.review = review;
	}

	public String getReview_error() {
		return review_error;
	}

	public void setReview_error(String review_error) {
		this.review_error = review_error;
	}

	public String getCredential() {
		return credential;
	}

	public void setCredential(String credential) {
		this.credential = credential;
	}

	public String getPay_pwd() {
		return pay_pwd;
	}

	public void setPay_pwd(String pay_pwd) {
		this.pay_pwd = pay_pwd;
	}

	public Integer getEnabled() {
		return enabled;
	}

	public void setEnabled(Integer enabled) {
		this.enabled = enabled;
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

	public Integer getDel_flg() {
		return del_flg;
	}

	public void setDel_flg(Integer del_flg) {
		this.del_flg = del_flg;
	}

	public Double getCoupon_amount() {
		return coupon_amount;
	}

	public void setCoupon_amount(Double coupon_amount) {
		this.coupon_amount = coupon_amount;
	}

	public String getHeadimg_base64() {
		return headimg_base64;
	}

	public void setHeadimg_base64(String headimg_base64) {
		this.headimg_base64 = headimg_base64;
	}

	public String getCardfront_base64() {
		return cardfront_base64;
	}

	public void setCardfront_base64(String cardfront_base64) {
		this.cardfront_base64 = cardfront_base64;
	}

	public String getCardback_base64() {
		return cardback_base64;
	}

	public void setCardback_base64(String cardback_base64) {
		this.cardback_base64 = cardback_base64;
	}

	public String getDriverscan_base64() {
		return driverscan_base64;
	}

	public void setDriverscan_base64(String driverscan_base64) {
		this.driverscan_base64 = driverscan_base64;
	}

	public String getDriver_scan_back() {
		return driver_scan_back;
	}

	public void setDriver_scan_back(String driver_scan_back) {
		this.driver_scan_back = driver_scan_back;
	}

	public String getOld_card_number() {
		return old_card_number;
	}

	public void setOld_card_number(String old_card_number) {
		this.old_card_number = old_card_number;
	}

	public Integer getEvcard_status() {
		return evcard_status;
	}

	public void setEvcard_status(Integer evcard_status) {
		this.evcard_status = evcard_status;
	}

	public String getDriverscan_back_base64() {
		return driverscan_back_base64;
	}

	public void setDriverscan_back_base64(String driverscan_back_base64) {
		this.driverscan_back_base64 = driverscan_back_base64;
	}

}