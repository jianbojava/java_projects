package com.cocosh.car.model;

import com.cocosh.framework.excel.ExcelField;
import com.cocosh.framework.util.DateUtil;
import com.cocosh.framework.util.StringUtil;

/**
 * 订单导出
 * 
 * @author jerry
 */
public class TimeOrderExportVo {
	private String sn;
	private String amount;
	private String car_price;
	private String damage_amount;
	private String peccancy_amount;
	private String other_amount;
	private String coupon_amount;
	private String use_point;
	private String pay_type;
	private Integer status;
	private String order_status;
	private String mem_number;
	private String mem_card;
	private String mem_name;
	private String mem_mobile;
	private String mem_type;
	private String firm_name;
	private String car_id;
	private String rent_star_time;
	private String rent_end_time;
	private String order_remark;// 平台订单备注

	@ExcelField(title = "订单编号", sort = 1,width=18)
	public String getSn() {
		return sn;
	}

	public void setSn(String sn) {
		this.sn = sn;
	}

	@ExcelField(title = "订单总额", sort = 3, numType = "Double", isSum = true)
	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}

	@ExcelField(title="车辆租金", sort = 4,numType="Double")
	public String getCar_price() {
		return car_price;
	}

	public void setCar_price(String car_price) {
		this.car_price = car_price;
	}
	
	@ExcelField(title="车损费", sort = 9,numType="Double")
	public String getDamage_amount() {
		return damage_amount;
	}

	public void setDamage_amount(String damage_amount) {
		this.damage_amount = damage_amount;
	}

	@ExcelField(title="违章费", sort = 10,numType="Double")
	public String getPeccancy_amount() {
		return peccancy_amount;
	}

	public void setPeccancy_amount(String peccancy_amount) {
		this.peccancy_amount = peccancy_amount;
	}

	@ExcelField(title="其他费用", sort = 11,numType="Double")
	public String getOther_amount() {
		return other_amount;
	}
	
	public void setOther_amount(String other_amount) {
		this.other_amount = other_amount;
	}

	@ExcelField(title="智券抵扣", sort = 12,numType="Double")
	public String getCoupon_amount() {
		return coupon_amount;
	}

	public void setCoupon_amount(String coupon_amount) {
		this.coupon_amount = coupon_amount;
	}

	@ExcelField(title="积分抵扣", sort = 13,numType="Double")
	public String getUse_point() {
		return use_point;
	}

	public void setUse_point(String use_point) {
		this.use_point = use_point;
	}
	
	@ExcelField(title = "支付类型", sort = 14)
	public String getPay_type() {
		if (pay_type!=null&&pay_type.equals("0")) {
			return "后付款";
		}
		return "预充值";
	}

	public void setPay_type(String pay_type) {
		this.pay_type = pay_type;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	@ExcelField(title = "订单状态", sort = 15)
	public String getOrder_status() {
		if (status==2) {
			return "已交车";
		}else if(status==3){
			return "已还车";
		}
		return "已对账";
	}

	public void setOrder_status(String order_status) {
		this.order_status = order_status;
	}
	
	@ExcelField(title="会员编号", sort = 16)
	public String getMem_number() {
		return mem_number;
	}

	public void setMem_number(String mem_number) {
		this.mem_number = mem_number;
	}

	@ExcelField(title="会员卡号", sort = 17)
	public String getMem_card() {
		return mem_card;
	}

	public void setMem_card(String mem_card) {
		this.mem_card = mem_card;
	}

	@ExcelField(title = "租车人", sort = 18)
	public String getMem_name() {
		return mem_name;
	}

	public void setMem_name(String mem_name) {
		this.mem_name = mem_name;
	}

	@ExcelField(title = "联系电话", sort = 19,width=15)
	public String getMem_mobile() {
		return mem_mobile;
	}

	public void setMem_mobile(String mem_mobile) {
		this.mem_mobile = mem_mobile;
	}

	@ExcelField(title="会员类型", sort = 20)
	public String getMem_type() {
		if (mem_type==null) {
			return null;
		}
		if (mem_type.equals("0")) {
			return "普通会员";
		}
		return "企业个人会员";
	}

	public void setMem_type(String mem_type) {
		this.mem_type = mem_type;
	}

	@ExcelField(title = "所属企业", sort = 21,width=20)
	public String getFirm_name() {
		return firm_name;
	}

	public void setFirm_name(String firm_name) {
		this.firm_name = firm_name;
	}

	@ExcelField(title = "车牌号", sort = 24,width=12)
	public String getCar_id() {
		return car_id;
	}

	public void setCar_id(String car_id) {
		this.car_id = car_id;
	}

	@ExcelField(title = "用车时间", sort = 30,width=18)
	public String getRent_star_time() {
		if (StringUtil.isEmpty(rent_star_time)) {
			return "";
		}
		return DateUtil.formatDate(DateUtil.stringToDate(rent_star_time, "yyyy-MM-dd HH:mm:ss"),"yyyy-MM-dd HH:mm");
	}

	public void setRent_star_time(String rent_star_time) {
		this.rent_star_time = rent_star_time;
	}

	@ExcelField(title = "取车时间", sort = 31,width=18)
	public String getRent_end_time() {
		if (StringUtil.isEmpty(rent_end_time)) {
			return "";
		}
		return DateUtil.formatDate(DateUtil.stringToDate(rent_end_time, "yyyy-MM-dd HH:mm:ss"),"yyyy-MM-dd HH:mm");
	}

	public void setRent_end_time(String rent_end_time) {
		this.rent_end_time = rent_end_time;
	}
	
	@ExcelField(title="还车备注" , sort = 43)
	public String getOrder_remark() {
		return order_remark;
	}

	public void setOrder_remark(String order_remark) {
		this.order_remark = order_remark;
	}
	
}
