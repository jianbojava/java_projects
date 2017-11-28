package com.cocosh.car.model;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.alibaba.fastjson.annotation.JSONField;
import com.cocosh.framework.base.BaseEntity;
import com.cocosh.framework.util.DateUtil;
import com.cocosh.framework.util.StringUtil;

public class Order extends BaseEntity {
	private String sn;
	private String mem_id;
	private String firm_id;
	private Integer status;
	private Integer pay_status;
	private String car_id;
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
	private Date ready_date;// 备车时间
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
	private Date delivery_date;// 交车时间
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
	private Date rent_star_time;// 计划用车时间
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
	private Date rent_star_ttime;// 实际用车时间
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
	private Date rent_end_time;// 计划还车时间
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
	private Date rent_end_ttime;// 实际还车时间
	private Double unit_price;// 单价
	private Double car_price;// 车价格
	private Double amount;// 总金额
	private Double pay_amount;// 支付金额
	private Double coupon_amount;// 优惠券抵扣金额
	private Integer pay_type;// 支付方式 0：后付款 1：预充值
	private Double insurance;// 保险费用
	private Integer ins_num;// 保险份数
	private String coupon_id;// 优惠券ID
	private Integer use_point;// 使用积分
	private String get_dot_id;// 取车网点
	private String return_dot_id;// 还车网点
	private Double get_amount;// 送车上门费用
	private Double return_amount;// 上门取车费用
	private Integer get_type;// 获取方式 0：自取 1：送车上门
	private Integer return_type;// 归还方式 0：自还 1：上门取车
	private String get_address;// 取车地址
	private String return_address;// 换车地址
	private Double get_lng;// 取车地址精度
	private Double get_lat;// 取车地址纬度
	private Double return_lng;// 还车地址精度
	private Double return_lat;// 还车地址纬度
	private String remark;// 用户下单备注

	private Double damage_amount;// 车损费
	private Double peccancy_amount;// 违章费
	private Double other_amount;// 其他费用
	private Double sale_amount;//销售折扣
	private String return_gallerys;// 还车单图片
	private String damage_gallerys;// 车损图片
	private String order_remark;// 平台订单备注

	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
	private Date complete_date;
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date settle_date;// 结算时间
	private Integer type;// 0:日租订单 1：分时租赁订单
	private Integer is_read;// 0：未查看 1：已查看
	private Integer del_flg;// 删除标识 0:正常 1：已删

	/**** 非数据库字段 *****/
	private String order_status;// 订单状态
	private String mem_name;// 租车人姓名
	private String mem_mobile;// 会员电话
	private String dispatch_name;// 调度员
	private String dispatch_mobile;// 调度员电话
	private String firm_name;// 企业名称
	private String car_number;// 车辆编号
	private String car_name;// 车辆名称
	private String car_license;// 车牌号
	private String dot_name;// 网点
	private String park_name;// 车位名
	private Double park_lng;// 车位精度
	private Double park_lat;// 车位纬度
	private Integer rent_days;// 租用时长（天）
	private long rent_minutes;// 租用时长（分）
	private String order_json;// 订单json数据 （Evcard）

	public String getSn() {
		return sn;
	}

	public void setSn(String sn) {
		this.sn = sn;
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

	public Integer getPay_status() {
		return pay_status;
	}

	public void setPay_status(Integer pay_status) {
		this.pay_status = pay_status;
	}

	public String getCar_id() {
		return car_id;
	}

	public void setCar_id(String car_id) {
		this.car_id = car_id;
	}

	public Date getReady_date() {
		return ready_date;
	}

	public void setReady_date(Date ready_date) {
		this.ready_date = ready_date;
	}

	public Date getRent_star_time() {
		return rent_star_time;
	}

	public void setRent_star_time(Date rent_star_time) {
		this.rent_star_time = rent_star_time;
	}

	public Date getRent_end_time() {
		return rent_end_time;
	}

	public void setRent_end_time(Date rent_end_time) {
		this.rent_end_time = rent_end_time;
	}

	public Double getUnit_price() {
		return unit_price;
	}

	public void setUnit_price(Double unit_price) {
		this.unit_price = unit_price;
	}

	public Double getCar_price() {
		return car_price;
	}

	public void setCar_price(Double car_price) {
		this.car_price = car_price;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public Double getPay_amount() {
		return pay_amount;
	}

	public void setPay_amount(Double pay_amount) {
		this.pay_amount = pay_amount;
	}

	public Double getCoupon_amount() {
		return coupon_amount;
	}

	public void setCoupon_amount(Double coupon_amount) {
		this.coupon_amount = coupon_amount;
	}

	public Integer getPay_type() {
		return pay_type;
	}

	public void setPay_type(Integer pay_type) {
		this.pay_type = pay_type;
	}

	public Double getInsurance() {
		return insurance;
	}

	public void setInsurance(Double insurance) {
		this.insurance = insurance;
	}

	public String getCoupon_id() {
		return coupon_id;
	}

	public void setCoupon_id(String coupon_id) {
		this.coupon_id = coupon_id;
	}

	public Integer getUse_point() {
		return use_point;
	}

	public void setUse_point(Integer use_point) {
		this.use_point = use_point;
	}

	public String getGet_dot_id() {
		return get_dot_id;
	}

	public void setGet_dot_id(String get_dot_id) {
		this.get_dot_id = get_dot_id;
	}

	public String getReturn_dot_id() {
		return return_dot_id;
	}

	public void setReturn_dot_id(String return_dot_id) {
		this.return_dot_id = return_dot_id;
	}

	public Double getGet_amount() {
		return get_amount;
	}

	public void setGet_amount(Double get_amount) {
		this.get_amount = get_amount;
	}

	public Double getReturn_amount() {
		return return_amount;
	}

	public void setReturn_amount(Double return_amount) {
		this.return_amount = return_amount;
	}

	public Integer getGet_type() {
		return get_type;
	}

	public void setGet_type(Integer get_type) {
		this.get_type = get_type;
	}

	public Integer getReturn_type() {
		return return_type;
	}

	public void setReturn_type(Integer return_type) {
		this.return_type = return_type;
	}

	public String getGet_address() {
		return get_address;
	}

	public void setGet_address(String get_address) {
		this.get_address = get_address;
	}

	public String getReturn_address() {
		return return_address;
	}

	public void setReturn_address(String return_address) {
		this.return_address = return_address;
	}

	public Double getGet_lng() {
		return get_lng;
	}

	public void setGet_lng(Double get_lng) {
		this.get_lng = get_lng;
	}

	public Double getGet_lat() {
		return get_lat;
	}

	public void setGet_lat(Double get_lat) {
		this.get_lat = get_lat;
	}

	public Double getReturn_lng() {
		return return_lng;
	}

	public void setReturn_lng(Double return_lng) {
		this.return_lng = return_lng;
	}

	public Double getReturn_lat() {
		return return_lat;
	}

	public void setReturn_lat(Double return_lat) {
		this.return_lat = return_lat;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Double getDamage_amount() {
		return damage_amount;
	}

	public void setDamage_amount(Double damage_amount) {
		this.damage_amount = damage_amount;
	}

	public Double getPeccancy_amount() {
		return peccancy_amount;
	}

	public void setPeccancy_amount(Double peccancy_amount) {
		this.peccancy_amount = peccancy_amount;
	}

	public Double getOther_amount() {
		return other_amount;
	}

	public void setOther_amount(Double other_amount) {
		this.other_amount = other_amount;
	}

	public Double getSale_amount() {
		return sale_amount;
	}

	public void setSale_amount(Double sale_amount) {
		this.sale_amount = sale_amount;
	}

	public String getReturn_gallerys() {
		return return_gallerys;
	}

	public void setReturn_gallerys(String return_gallerys) {
		this.return_gallerys = return_gallerys;
	}

	public String getDamage_gallerys() {
		return damage_gallerys;
	}

	public void setDamage_gallerys(String damage_gallerys) {
		this.damage_gallerys = damage_gallerys;
	}

	public String getOrder_remark() {
		return order_remark;
	}

	public void setOrder_remark(String order_remark) {
		this.order_remark = order_remark;
	}

	public Date getComplete_date() {
		return complete_date;
	}

	public void setComplete_date(Date complete_date) {
		this.complete_date = complete_date;
	}

	public Date getSettle_date() {
		return settle_date;
	}

	public void setSettle_date(Date settle_date) {
		this.settle_date = settle_date;
	}

	public void setOrder_status(String order_status) {
		this.order_status = order_status;
	}

	public String getOrder_status() {
		if (StringUtil.isEmpty(order_status)) {
			return getOrderStatus(status);
		}
		return order_status;
	}

	public String getMem_name() {
		return mem_name;
	}

	public void setMem_name(String mem_name) {
		this.mem_name = mem_name;
	}

	public String getMem_mobile() {
		return mem_mobile;
	}

	public void setMem_mobile(String mem_mobile) {
		this.mem_mobile = mem_mobile;
	}

	public String getDispatch_name() {
		return dispatch_name;
	}

	public void setDispatch_name(String dispatch_name) {
		this.dispatch_name = dispatch_name;
	}

	public String getDispatch_mobile() {
		return dispatch_mobile;
	}

	public void setDispatch_mobile(String dispatch_mobile) {
		this.dispatch_mobile = dispatch_mobile;
	}

	public String getFirm_name() {
		return firm_name;
	}

	public void setFirm_name(String firm_name) {
		this.firm_name = firm_name;
	}

	public String getCar_number() {
		return car_number;
	}

	public void setCar_number(String car_number) {
		this.car_number = car_number;
	}

	public String getCar_name() {
		return car_name;
	}

	public void setCar_name(String car_name) {
		this.car_name = car_name;
	}

	public String getCar_license() {
		return car_license;
	}

	public void setCar_license(String car_license) {
		this.car_license = car_license;
	}

	public String getDot_name() {
		return dot_name;
	}

	public void setDot_name(String dot_name) {
		this.dot_name = dot_name;
	}

	public String getPark_name() {
		return park_name;
	}

	public void setPark_name(String park_name) {
		this.park_name = park_name;
	}

	public Double getPark_lng() {
		return park_lng;
	}

	public void setPark_lng(Double park_lng) {
		this.park_lng = park_lng;
	}

	public Double getPark_lat() {
		return park_lat;
	}

	public void setPark_lat(Double park_lat) {
		this.park_lat = park_lat;
	}

	public Date getDelivery_date() {
		return delivery_date;
	}

	public void setDelivery_date(Date delivery_date) {
		this.delivery_date = delivery_date;
	}

	public Date getRent_star_ttime() {
		return rent_star_ttime;
	}

	public void setRent_star_ttime(Date rent_star_ttime) {
		this.rent_star_ttime = rent_star_ttime;
	}

	public Date getRent_end_ttime() {
		return rent_end_ttime;
	}

	public void setRent_end_ttime(Date rent_end_ttime) {
		this.rent_end_ttime = rent_end_ttime;
	}

	public Integer getIns_num() {
		return ins_num;
	}

	public void setIns_num(Integer ins_num) {
		this.ins_num = ins_num;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public Integer getRent_days() {
		if (this.getRent_end_time()==null) {
			return 0;
		}
		return DateUtil.getIntervalDays(this.getRent_star_time(),
				this.getRent_end_time());
	}

	public long getRent_minutes() {
		if (this.getRent_end_time() == null) {
			return 0;
		}
		return DateUtil.dateMinuteDiff(this.getRent_star_time(),
				this.getRent_end_time());
	}

	public Integer getIs_read() {
		return is_read;
	}

	public void setIs_read(Integer is_read) {
		this.is_read = is_read;
	}

	public Integer getDel_flg() {
		return del_flg;
	}

	public void setDel_flg(Integer del_flg) {
		this.del_flg = del_flg;
	}

	public String getOrder_json() {
		return order_json;
	}

	public void setOrder_json(String order_json) {
		this.order_json = order_json;
	}

	// 获取订单状态
	public String getOrderStatus(Integer status) {
		switch (status) {
		case 0:
			order_status = "已下单";
			break;
		case 1:
			order_status = "已备车";
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
			order_status = "已对账";
			break;
		case 6:
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