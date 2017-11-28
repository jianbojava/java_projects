package com.cocosh.nlf.model;


import java.io.Serializable;
import java.util.Date;
/**
 * 
 * @author houzan
 * 激活码管理
 *
 */
public class Activation implements Serializable {
	private String id;
 	private String number;//8位随机数
 	private String member_id;
 	private Integer enabled;//0未使用，1已使用
 	private Date create_date;
 	/*非数据库字段*/
 	private String username ;
	private String name;//姓名
 	private String user_number;//会员number
 	private String depart_name;
 	
 	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getId() {
 		return id;
 	}
 	public Activation(){}
 	
 	public Activation(String id, String number, String member_id,
			Integer enabled) {
		super();
		this.id = id;
		this.number = number;
		this.member_id = member_id;
		this.enabled = enabled;
	}

	public void setId(String id) {
 		this.id = id;
 	}
 	
 	public String getNumber() {
 		return number;
 	}
 	
 	public void setNumber(String number) {
 		this.number = number;
 	}
 	
 	public String getMember_id() {
 		return member_id;
 	}
 	
 	public void setMember_id(String member_id) {
 		this.member_id = member_id;
 	}
 	
 	public Integer getEnabled() {
 		return enabled;
 	}
 	
 	public void setEnabled(Integer enabled) {
 		this.enabled = enabled;
 	}
 	
 	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getUser_number() {
		return user_number;
	}
	public void setUser_number(String user_number) {
		this.user_number = user_number;
	}
	public String getDepart_name() {
		return depart_name;
	}
	public void setDepart_name(String depart_name) {
		this.depart_name = depart_name;
	}
	public Date getCreate_date() {
		return create_date;
	}
	public void setCreate_date(Date create_date) {
		this.create_date = create_date;
	}

}