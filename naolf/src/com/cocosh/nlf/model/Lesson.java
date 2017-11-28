package com.cocosh.nlf.model;


import com.cocosh.framework.base.BaseEntity;

public class Lesson extends BaseEntity {
 	private String catname;
 	private String name;
 	private String period;//课时
 	private String userage;
	private Double price;
 	private Integer enabled;
 	private String image;
 	private String advantage;
 	private String special;
 	private Integer type;//类型：0:集中训练+进阶训练；1集中训练
	private Integer train;//进阶训练课时(针对进阶训练)
 	private String rule_id;//规则id;
 	private String content;
	/**非数据库字段**/
 	private String rule_sn;//规则拼接
 	private String status;//userticket里面的上课状态
 	private String ut_id;//userticket里面的上课状态
	private String s_id;//上课学生id
 	
 	
 	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
	public String getS_id() {
		return s_id;
	}

	public void setS_id(String s_id) {
		this.s_id = s_id;
	}

	public String getUt_id() {
		return ut_id;
	}

	public void setUt_id(String ut_id) {
		this.ut_id = ut_id;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public Integer getTrain() {
		return train;
	}

	public void setTrain(Integer train) {
		this.train = train;
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
 	
 	public String getPeriod() {
 		return period;
 	}
 	
 	public void setPeriod(String period) {
 		this.period = period;
 	}
 	
 	public String getUserage() {
 		return userage;
 	}
 	
 	public void setUserage(String userage) {
 		this.userage = userage;
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
 	
 	public String getImage() {
 		return image;
 	}
 	
 	public void setImage(String image) {
 		this.image = image;
 	}
 	
 	public String getAdvantage() {
 		return advantage;
 	}
 	
 	public void setAdvantage(String advantage) {
 		this.advantage = advantage;
 	}
 	
 	public String getSpecial() {
 		return special;
 	}
 	
 	public void setSpecial(String special) {
 		this.special = special;
 	}
 	
 	public String getRule_id() {
		return rule_id;
	}

	public void setRule_id(String rule_id) {
		this.rule_id = rule_id;
	}

	public String getRule_sn() {
		return rule_sn;
	}

	public void setRule_sn(String rule_sn) {
		this.rule_sn = rule_sn;
	}
}