package com.cocosh.nlf.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

import com.alibaba.fastjson.annotation.JSONField;
/**
 * 充值表  nlf_charge
 * @author bobo
 *
 */
public class Recharge implements Serializable {
 	
	private static final long serialVersionUID = 1L;
	private String id;
 	private String user_id;
 	private String refer_id;
 	

	private String reason;
 	private String operate_id;
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
 	private Date create_date;
	
	private Integer recharge_type;//充值类型（0卡券，1积分）
	private Integer reason_type;//充值类型（1，自购，2奖励，3其他）
	
	
	
	
	
	/**非数据库字段**/
	private List<Ticket> tickets;
	private String type;
	private String user_number;
	private String depart_name;//部门
	private String user_type;// 角色：员工，合伙人，学员
	
	
	private String refer_cname;//推荐人
	private String refer_number;//推荐人ID
	private String reason_type_desc;//充值类型：自购，奖励，其他
	private String charge_category;//充值类别：卡，券，积分
	private String ticket_no;//卡号/券号
	private BigDecimal KZscore ;
	private BigDecimal KDscore ;
	private BigDecimal BKZscore ;
	

	private String cName;

	public BigDecimal getBKZscore() {
		return BKZscore;
	}

	public void setBKZscore(BigDecimal bKZscore) {
		BKZscore = bKZscore;
	}
	
	public String getRefer_cname() {
		return refer_cname;
	}

	public void setRefer_cname(String refer_cname) {
		this.refer_cname = refer_cname;
	}

	public String getRefer_number() {
		return refer_number;
	}

	public void setRefer_number(String refer_number) {
		this.refer_number = refer_number;
	}

	public String getReason_type_desc() {
		return reason_type_desc;
	}

	public void setReason_type_desc(String reason_type_desc) {
		this.reason_type_desc = reason_type_desc;
	}

	public String getCharge_category() {
		return charge_category;
	}

	public void setCharge_category(String charge_category) {
		this.charge_category = charge_category;
	}

	public String getTicket_no() {
		return ticket_no;
	}

	public void setTicket_no(String ticket_no) {
		this.ticket_no = ticket_no;
	}

	public String getRefer_id() {
		return refer_id;
	}

	public void setRefer_id(String refer_id) {
		this.refer_id = refer_id;
	}

	public Integer getReason_type() {
		return reason_type;
	}

	public void setReason_type(Integer reason_type) {
		this.reason_type = reason_type;
	}
	
	public BigDecimal getKZscore() {
		return KZscore;
	}

	public void setKZscore(BigDecimal kZscore) {
		KZscore = kZscore;
	}

	public BigDecimal getKDscore() {
		return KDscore;
	}

	public void setKDscore(BigDecimal kDscore) {
		KDscore = kDscore;
	}

	
	public String getcName() {
		return cName;
	}

	public void setcName(String cName) {
		this.cName = cName;
	}


	public List<Ticket> getTickets() {
		return tickets;
	}
	
	

	public void setTickets(List<Ticket> tickets) {
		this.tickets = tickets;
	}

 	public String getId() {
 		return id;
 	}
 	
 	public void setId(String id) {
 		this.id = id;
 	}
 	
 	public String getUser_id() {
 		return user_id;
 	}
 	
 	public void setUser_id(String user_id) {
 		this.user_id = user_id;
 	}
 	
 	public String getReason() {
 		return reason;
 	}
 	
 	public void setReason(String reason) {
 		this.reason = reason;
 	}
 	
 	public String getOperate_id() {
 		return operate_id;
 	}
 	
 	public void setOperate_id(String operate_id) {
 		this.operate_id = operate_id;
 	}
 	
 	public Date getCreate_date() {
 		return create_date;
 	}
 	
 	public void setCreate_date(Date create_date) {
 		this.create_date = create_date;
 	}
 	
 	public Integer getRecharge_type() {
		return recharge_type;
	}

	public void setRecharge_type(Integer recharge_type) {
		this.recharge_type = recharge_type;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getDepart_name() {
		return depart_name;
	}

	public void setDepart_name(String depart_name) {
		this.depart_name = depart_name;
	}

	public String getUser_type() {
		return user_type;
	}

	public void setUser_type(String user_type) {
		this.user_type = user_type;
	}

	public String getUser_number() {
		return user_number;
	}

	public void setUser_number(String user_number) {
		this.user_number = user_number;
	}
}