package com.cocosh.nlf.model;

import java.io.Serializable;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * 会员课程卡券编号   nlf_user_ticket
 * @author bobo
 *
 */

public class UserTicket implements Serializable  {
	private static final long serialVersionUID = 1L;
	/**
	 * 对于购买的课程和卡券只有支付完成才会产生
	 */
	private String id;// 主键
	private Integer used;//0未使用，1已使用,2已作废
	private Integer type;//0课程，1卡券
 	private String user_id;//会员id
 	private String sn;//编号(支付完成才产生)
	private Integer create_sn;//序号（从1开始）
 	private String conn_id;//卡券id或课程id
 	private String rule_id;//规则id
 	private String norder_id;//订单id
 	private String used_id;//使用者id
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
 	private Date used_date;//使用时间
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date create_date;// 创建时间
 	private String line_sn;//线下卡号
	private String recharge_id;//充值id
	private Integer upgrade;//卡券是否升级（0默认未升级，1已升级,2升级后的卡）
 	private Integer status;//针对课程状态:，0未预约 ,1已预约，2预约取消，-3未训练，3训练中 ,4已结业  5,复训中 6已结束(针对复训)

 	/**非数据库字段**/
 	private String number;//会员编号
	private String name;//课程名称或卡券名称
	private String image;//课程或卡券图片
	private String catname;//课程所属分类
	private Integer lesson_type;//课程类型：0:集中训练+进阶训练；1集中训练
	private Double price;//课程价格
	private Appoint lastappoint;//最近的一次预约
	private Appoint lastclass;//最近的一次上课
	private Integer a_status;//判断课程的预约是否通过审核 0待审核 1已审核 2已取消 3训练中 4已结业 (只有待审核的才可以取消)
	private String user_name;//卡券所属使用人  wap端卡券升级里所用   
	private Integer ticket_type;//0卡，1券
	private String used_number;//使用者的编号
	private Integer left_review;//该课程的剩余复训次数
	private Integer cancomm=1;//默认不能评价
 	public UserTicket(){
 		super();
 	}
 	
 	public UserTicket(String id,Integer used, Integer type, String user_id, String sn,
			Integer create_sn, String conn_id, String rule_id,
			String norder_id, String used_id,String recharge_id,Integer status) {
		super();
		this.id=id;
		this.used = used;
		this.type = type;
		this.user_id = user_id;
		this.sn = sn;
		this.create_sn = create_sn;
		this.conn_id = conn_id;
		this.rule_id = rule_id;
		this.norder_id = norder_id;
		this.used_id = used_id;
		this.recharge_id=recharge_id;
		this.status=status;
		this.used_date = new Date();
	}
 	
 	
 	public Integer getLeft_review() {
		return left_review;
	}

	public void setLeft_review(Integer left_review) {
		this.left_review = left_review;
	}

 	public String getUser_name() {
		return user_name;
	}

	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}

	public String getImage() {
		return image;
 	}
	public Integer getA_status() {
		return a_status;
	}

	public void setA_status(Integer a_status) {
		this.a_status = a_status;
	}


	public void setImage(String image) {
		this.image = image;
	}

	public String getCatname() {
		return catname;
	}

	public void setCatname(String catname) {
		this.catname = catname;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getUsed() {
 		return used;
 	}
 	
 	public void setUsed(Integer used) {
 		this.used = used;
 	}
 	
 	public String getUser_id() {
 		return user_id;
 	}
 	
 	public void setUser_id(String user_id) {
 		this.user_id = user_id;
 	}
 	
 	public String getSn() {
 		return sn;
 	}
 	
 	public void setSn(String sn) {
 		this.sn = sn;
 	}
 	
 	public Integer getCreate_sn() {
 		return create_sn;
 	}
 	
 	public void setCreate_sn(Integer create_sn) {
 		this.create_sn = create_sn;
 	}
 	
 	public String getConn_id() {
 		return conn_id;
 	}
 	
 	public void setConn_id(String conn_id) {
 		this.conn_id = conn_id;
 	}
 	
 	public String getRule_id() {
 		return rule_id;
 	}
 	
 	public void setRule_id(String rule_id) {
 		this.rule_id = rule_id;
 	}
 	
 	public String getNorder_id() {
 		return norder_id;
 	}
 	
 	public void setNorder_id(String norder_id) {
 		this.norder_id = norder_id;
 	}
 	
 	public String getUsed_id() {
 		return used_id;
 	}
 	
 	public void setUsed_id(String used_id) {
 		this.used_id = used_id;
 	}
 	
 	public Date getUsed_date() {
 		return used_date;
 	}
 	
 	public void setUsed_date(Date used_date) {
 		this.used_date = used_date;
 	}
 	
 	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}
	public String getLine_sn() {
		return line_sn;
	}

	public void setLine_sn(String line_sn) {
		this.line_sn = line_sn;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Date getCreate_date() {
		return create_date;
	}

	public void setCreate_date(Date create_date) {
		this.create_date = create_date;
	}
	public String getRecharge_id() {
		return recharge_id;
	}

	public void setRecharge_id(String recharge_id) {
		this.recharge_id = recharge_id;
	}

 	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}
	public Appoint getLastappoint() {
		return lastappoint;
	}

	public void setLastappoint(Appoint lastappoint) {
		this.lastappoint = lastappoint;
	}

	public Appoint getLastclass() {
		return lastclass;
	}

	public void setLastclass(Appoint lastclass) {
		this.lastclass = lastclass;
	}
	public Integer getLesson_type() {
		return lesson_type;
	}

	public void setLesson_type(Integer lesson_type) {
		this.lesson_type = lesson_type;
	}
	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}
	public Integer getTicket_type() {
		return ticket_type;
	}

	public void setTicket_type(Integer ticket_type) {
		this.ticket_type = ticket_type;
	}
	
	public String getUsed_number() {
		return used_number;
	}

	public void setUsed_number(String used_number) {
		this.used_number = used_number;
	}
	public Integer getUpgrade() {
		return upgrade;
	}

	public void setUpgrade(Integer upgrade) {
		this.upgrade = upgrade;
	}
	public Integer getCancomm() {
		return cancomm;
	}

	public void setCancomm(Integer cancomm) {
		this.cancomm = cancomm;
	}




 	
}