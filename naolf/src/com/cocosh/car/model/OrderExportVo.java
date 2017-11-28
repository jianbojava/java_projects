package com.cocosh.car.model;

import com.cocosh.framework.excel.ExcelField;
import com.cocosh.framework.util.DateUtil;

/**
 * 订单导出
 * 
 * @author jerry
 */
public class OrderExportVo {
	private String sn;
	private String create_date;
	private String amount;
	private String car_price;
	private String insurance;
	private String ins_num;
	private String get_amount;
	private String return_amount;
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
	private String car_number;
	private String car_name;
	private String car_license;
	private String park_name;
	private String dispatch_name;
	private String dispatch_mobile;
	private String ready_date;
	private String delivery_date;
	private String rent_star_time;
	private String rent_end_time;
	private String rent_days;
	private String rent_star_ttime;
	private String rent_end_ttime;
	private String rent_ddays;
	private String get_dot_name;
	private String return_dot_name;
	private String get_type;
	private String return_type;
	private String get_address;
	private String return_address;
	private String remark;
	private String order_remark;// 平台订单备注

	@ExcelField(title = "订单编号", sort = 1,width=18)
	public String getSn() {
		return sn;
	}

	public void setSn(String sn) {
		this.sn = sn;
	}

	@ExcelField(title = "下单时间", sort = 2,width=18)
	public String getCreate_date() {
		return DateUtil.formatDate(DateUtil.stringToDate(create_date, "yyyy-MM-dd HH:mm:ss"),"yyyy-MM-dd HH:mm:ss");
	}

	public void setCreate_date(String create_date) {
		this.create_date = create_date;
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
	
	@ExcelField(title="保险费", sort = 5,numType="Double")
	public String getInsurance() {
		return insurance;
	}

	public void setInsurance(String insurance) {
		this.insurance = insurance;
	}

	@ExcelField(title="保险数", sort = 6,numType="Integer")
	public String getIns_num() {
		return ins_num;
	}

	public void setIns_num(String ins_num) {
		this.ins_num = ins_num;
	}
	
	@ExcelField(title="送车费用", sort = 7,numType="Double")
	public String getGet_amount() {
		return get_amount;
	}

	public void setGet_amount(String get_amount) {
		this.get_amount = get_amount;
	}

	@ExcelField(title="取车费用", sort = 8,numType="Double")
	public String getReturn_amount() {
		return return_amount;
	}

	public void setReturn_amount(String return_amount) {
		this.return_amount = return_amount;
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
		if (pay_type.equals("0")) {
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
		return getOrderStatus(status);
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

	@ExcelField(title = "车辆编号", sort = 22,width=12)
	public String getCar_number() {
		return car_number;
	}

	public void setCar_number(String car_number) {
		this.car_number = car_number;
	}

	@ExcelField(title = "车辆名称", sort = 23,width=15)
	public String getCar_name() {
		return car_name;
	}

	public void setCar_name(String car_name) {
		this.car_name = car_name;
	}

	@ExcelField(title = "车牌号", sort = 24,width=12)
	public String getCar_license() {
		return car_license;
	}

	public void setCar_license(String car_license) {
		this.car_license = car_license;
	}

	@ExcelField(title="所在车位", sort = 25)
	public String getPark_name() {
		return park_name;
	}

	public void setPark_name(String park_name) {
		this.park_name = park_name;
	}
	
	@ExcelField(title = "调度员", sort = 26)
	public String getDispatch_name() {
		return dispatch_name;
	}

	public void setDispatch_name(String dispatch_name) {
		this.dispatch_name = dispatch_name;
	}

	@ExcelField(title="调度电话", sort = 27)
	public String getDispatch_mobile() {
		return dispatch_mobile;
	}

	public void setDispatch_mobile(String dispatch_mobile) {
		this.dispatch_mobile = dispatch_mobile;
	}

	@ExcelField(title="备车时间", sort = 28,width=18)
	public String getReady_date() {
		return ready_date;
	}

	public void setReady_date(String ready_date) {
		this.ready_date = ready_date;
	}

	@ExcelField(title="交车时间", sort = 29,width=18)
	public String getDelivery_date() {
		return delivery_date;
	}

	public void setDelivery_date(String delivery_date) {
		this.delivery_date = delivery_date;
	}

	@ExcelField(title = "计划用车时间", sort = 30,width=18)
	public String getRent_star_time() {
		return DateUtil.formatDate(DateUtil.stringToDate(rent_star_time, "yyyy-MM-dd HH:mm:ss"),"yyyy-MM-dd HH:mm");
	}

	public void setRent_star_time(String rent_star_time) {
		this.rent_star_time = rent_star_time;
	}

	@ExcelField(title = "计划取车时间", sort = 31,width=18)
	public String getRent_end_time() {
		return DateUtil.formatDate(DateUtil.stringToDate(rent_end_time, "yyyy-MM-dd HH:mm:ss"),"yyyy-MM-dd HH:mm");
	}

	public void setRent_end_time(String rent_end_time) {
		this.rent_end_time = rent_end_time;
	}

	@ExcelField(title = "计划用车时长(天)", sort = 32,numType="Integer",width=12)
	public String getRent_days() {
		return String.valueOf(DateUtil.getIntervalDays(rent_star_time, rent_end_time));
	}

	public void setRent_days(String rent_days) {
		this.rent_days = rent_days;
	}
	
	@ExcelField(title="实际用车时间", sort = 33,width=18)
	public String getRent_star_ttime() {
		return rent_star_ttime;
	}

	public void setRent_star_ttime(String rent_star_ttime) {
		this.rent_star_ttime = rent_star_ttime;
	}

	@ExcelField(title="实际还车时间", sort = 34,width=18)
	public String getRent_end_ttime() {
		return rent_end_ttime;
	}

	public void setRent_end_ttime(String rent_end_ttime) {
		this.rent_end_ttime = rent_end_ttime;
	}
	
	@ExcelField(title="实际用车时长(天)", sort = 35,numType="Integer")
	public String getRent_ddays() {
		return String.valueOf(DateUtil.getIntervalDays(rent_star_ttime, rent_end_ttime));
	}

	public void setRent_ddays(String rent_ddays) {
		this.rent_ddays = rent_ddays;
	}

	@ExcelField(title="取车网点", sort = 36,width=18)
	public String getGet_dot_name() {
		return get_dot_name;
	}

	public void setGet_dot_name(String get_dot_name) {
		this.get_dot_name = get_dot_name;
	}

	@ExcelField(title="还车网点", sort = 37,width=18)
	public String getReturn_dot_name() {
		return return_dot_name;
	}

	public void setReturn_dot_name(String return_dot_name) {
		this.return_dot_name = return_dot_name;
	}

	@ExcelField(title="取车方式", sort = 38)
	public String getGet_type() {
		if (get_type.equals("0")) {
			return "自取";
		}
		return "送车上门";
	}

	public void setGet_type(String get_type) {
		this.get_type = get_type;
	}

	@ExcelField(title="还车方式", sort = 39)
	public String getReturn_type() {
		if (return_type.equals("0")) {
			return "自还";
		}
		return "上门取车";
	}

	public void setReturn_type(String return_type) {
		this.return_type = return_type;
	}

	@ExcelField(title="送车地址", sort = 40)
	public String getGet_address() {
		return get_address;
	}

	public void setGet_address(String get_address) {
		this.get_address = get_address;
	}

	@ExcelField(title="取车地址", sort = 41)
	public String getReturn_address() {
		return return_address;
	}

	public void setReturn_address(String return_address) {
		this.return_address = return_address;
	}
	
	@ExcelField(title = "下单备注", sort = 42)
	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}
	
	@ExcelField(title="还车备注" , sort = 43)
	public String getOrder_remark() {
		return order_remark;
	}

	public void setOrder_remark(String order_remark) {
		this.order_remark = order_remark;
	}

	public String getOrderStatus(Integer status) {
		switch (status) {
		case 0:
			order_status = "已下单";
			break;
		case 1:
			order_status = "已准备";
			break;
		case 2:
			order_status = "已交车";
			break;
		case 3:
			order_status = "已还车";
			break;
		case 4:
			order_status = "已核价";
			break;
		case 5:
			order_status = "已结算";
			break;
		case -1:
			order_status = "已取消";
			break;
		default:
			break;
		}
		return order_status;
	}
}
