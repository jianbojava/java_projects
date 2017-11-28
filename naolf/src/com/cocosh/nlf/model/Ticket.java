package com.cocosh.nlf.model;


import com.cocosh.framework.base.BaseEntity;

public class Ticket extends BaseEntity {
 	private Integer type;//0卡，1券//券针对单个课程,卡针对多个课程
 	private String name;
	private String rule_id;//规则id
 	private String notice;
 	private String use_rule;//使用规则
 	private String lesson_ids;
	private String image;
 	private Double price;
 	private Integer enabled;
 	/**非数据库字段**/
 	private String rule_sn;//编号规则拼接
 	private Integer  num;//数量
 	public Integer getNum() {
		return num;
	}

	public void setNum(Integer num) {
		this.num = num;
	}
 	public Integer getType() {
 		return type;
 	}
 	
 	public void setType(Integer type) {
 		this.type = type;
 	}
 	
 	public String getName() {
 		return name;
 	}
 	
 	public void setName(String name) {
 		this.name = name;
 	}
 	
 	
 	public String getNotice() {
 		return notice;
 	}
 	
 	public void setNotice(String notice) {
 		this.notice = notice;
 	}
 	public String getLesson_ids() {
 		return lesson_ids;
 	}
 	
 	public void setLesson_ids(String lesson_ids) {
 		this.lesson_ids = lesson_ids;
 	}
 	
 	public String getImage() {
 		return image;
 	}
 	
 	public void setImage(String image) {
 		this.image = image;
 	}
 	
 	public Double getPrice() {
 		return price;
 	}
 	
 	public void setPrice(Double price) {
 		this.price = price;
 	}
 	

	public Integer getEnabled() {
		return enabled;
	}

	public void setEnabled(Integer enabled) {
		this.enabled = enabled;
	}
	
	public String getRule_sn() {
		return rule_sn;
	}

	public void setRule_sn(String rule_sn) {
		this.rule_sn = rule_sn;
	}

	public String getRule_id() {
		return rule_id;
	}

	public void setRule_id(String rule_id) {
		this.rule_id = rule_id;
	}

	public String getUse_rule() {
		return use_rule;
	}

	public void setUse_rule(String use_rule) {
		this.use_rule = use_rule;
	}

}