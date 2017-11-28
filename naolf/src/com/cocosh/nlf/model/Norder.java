package com.cocosh.nlf.model;


import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.cocosh.framework.base.BaseEntity;
/**
 * 课程或卡券订单  nlf_norder
 * @author bobo
 *
 */

public class Norder extends  BaseEntity {
 	private String user_id;
 	private String sn;//订单编号
 	private String refer_number;//推荐人编号
 	private String number;//会员编号
	private String ticket_sn;//卡券号或者课程号
 	private Integer status;//-1，已取消，0已提交，1已付款，-2已退款
 	private Integer type;//0,课程订单，1卡券
 	private Double price;//价格
 	private Double pay_amount;//产生积分金额(会产生积分price*0.94)(如果是历史未结业的,可能会有区别)
	private String depart_id;//部门
 	private String mobile;
 	private String conn_id;//课程id或卡券id
	private Integer pay_type;//0线下付款，1支付宝，2微信 3银行卡支付，4积分支付，5券支付(升级)
	private String name;//课程名称或卡券名称
	private Integer addtype;//0线上 微信添加，1手动录入 后台添加（未支付）2，已结业历史订单,3后台添加的未结业历史订单，4卡券升级产生
 	private Integer buy_type;//(0卡券从推荐人购买，1卡券从平台购买),课程订单也是1
 	private BigDecimal kd_score;//可兑换积分（可提现积分）
 	private BigDecimal kz_score;//可转积分（结业积分）
	private String  upnorder;//卡券升级相互关联的订单
 	private Integer uptype;//升级类型（默认是0，2w学习券升级就变成1）
 	private String uputicket;//升级后的钻卡卡号
 	//非数据库字段
 	private String depart_name;
	private String lesson_name;
 	private String lesson_price;
 	private String lesson_userage;
 	private String lesson_period;
 	private String lesson_image;
 	private String ticket_name;
 	private String ticket_price;
 	private String ticket_image;
	private Integer have_ut;//辅助字段，有无可展示的课程
 	private String student_id;
	List<UserTicket> utlist=new ArrayList<UserTicket>();//一个订单包含的课程
 	public List<UserTicket> getUtlist() {
		return utlist;
	}

	public void setUtlist(List<UserTicket> utlist) {
		this.utlist = utlist;
	}

	public String getLesson_price() {
		return lesson_price;
	}

	public void setLesson_price(String lesson_price) {
		this.lesson_price = lesson_price;
	}

	public BigDecimal getKd_score() {
		return kd_score;
	}

	public void setKd_score(BigDecimal kd_score) {
		this.kd_score = kd_score;
	}

	public BigDecimal getKz_score() {
		return kz_score;
	}

	public void setKz_score(BigDecimal kz_score) {
		this.kz_score = kz_score;
	}

	public String getTicket_price() {
		return ticket_price;
	}

	public void setTicket_price(String ticket_price) {
		this.ticket_price = ticket_price;
	}

	public String getLesson_name() {
		return lesson_name;
	}

	public void setLesson_name(String lesson_name) {
		this.lesson_name = lesson_name;
	}


	public String getLesson_userage() {
		return lesson_userage;
	}

	public void setLesson_userage(String lesson_userage) {
		this.lesson_userage = lesson_userage;
	}

	public String getLesson_period() {
		return lesson_period;
	}

	public void setLesson_period(String lesson_period) {
		this.lesson_period = lesson_period;
	}

	public String getLesson_image() {
		return lesson_image;
	}

	public void setLesson_image(String lesson_image) {
		this.lesson_image = lesson_image;
	}

	public String getTicket_name() {
		return ticket_name;
	}

	public void setTicket_name(String ticket_name) {
		this.ticket_name = ticket_name;
	}


	public String getTicket_image() {
		return ticket_image;
	}

	public void setTicket_image(String ticket_image) {
		this.ticket_image = ticket_image;
	}

	public String getUser_id() {
 		return user_id;
 	}
 	
 	public void setUser_id(String user_id) {
 		this.user_id = user_id;
 	}
 	public Integer getAddtype() {
		return addtype;
	}

	public void setAddtype(Integer addtype) {
		this.addtype = addtype;
	}

 	public String getSn() {
 		return sn;
 	}
 	
 	public void setSn(String sn) {
 		this.sn = sn;
 	}
 	
 	public String getRefer_number() {
 		return refer_number;
 	}
 	
 	public void setRefer_number(String refer_number) {
 		this.refer_number = refer_number;
 	}
 	
 	public String getTicket_sn() {
 		return ticket_sn;
 	}
 	
 	public void setTicket_sn(String ticket_sn) {
 		this.ticket_sn = ticket_sn;
 	}
 	
 	public Integer getStatus() {
 		return status;
 	}
 	
 	public void setStatus(Integer status) {
 		this.status = status;
 	}
 	
 	public Integer getType() {
 		return type;
 	}
 	
 	public void setType(Integer type) {
 		this.type = type;
 	}
 	
 	public Double getPrice() {
 		return price;
 	}
 	
 	public void setPrice(Double price) {
 		this.price = price;
 	}
 	
 	public Double getPay_amount() {
 		return pay_amount;
 	}
 	
 	public void setPay_amount(Double pay_amount) {
 		this.pay_amount = pay_amount;
 	}
 	
 	public String getDepart_id() {
 		return depart_id;
 	}
 	
 	public void setDepart_id(String depart_id) {
 		this.depart_id = depart_id;
 	}
 	
 	public String getMobile() {
 		return mobile;
 	}
 	
 	public void setMobile(String mobile) {
 		this.mobile = mobile;
 	}
 	
 	public String getConn_id() {
 		return conn_id;
 	}
 	
 	public void setConn_id(String conn_id) {
 		this.conn_id = conn_id;
 	}
 	
 	public Integer getPay_type() {
 		return pay_type;
 	}
 	
 	public void setPay_type(Integer pay_type) {
 		this.pay_type = pay_type;
 	}
 	
 	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	public Integer getBuy_type() {
		return buy_type;
	}

	public void setBuy_type(Integer buy_type) {
		this.buy_type = buy_type;
	}
	public Integer getHave_ut() {
		return have_ut;
	}

	public void setHave_ut(Integer have_ut) {
		this.have_ut = have_ut;
	}
	public String getDepart_name() {
		return depart_name;
	}

	public void setDepart_name(String depart_name) {
		this.depart_name = depart_name;
	}
	public String getStudent_id() {
		return student_id;
	}

	public void setStudent_id(String student_id) {
		this.student_id = student_id;
	}
	public String getUpnorder() {
		return upnorder;
	}

	public void setUpnorder(String upnorder) {
		this.upnorder = upnorder;
	}

	public Integer getUptype() {
		return uptype;
	}

	public void setUptype(Integer uptype) {
		this.uptype = uptype;
	}

	public String getUputicket() {
		return uputicket;
	}

	public void setUputicket(String uputicket) {
		this.uputicket = uputicket;
	}

}