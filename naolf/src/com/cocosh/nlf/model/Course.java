

package com.cocosh.nlf.model;

import java.util.Date;
import java.io.Serializable;

import org.springframework.format.annotation.DateTimeFormat;

import com.alibaba.fastjson.annotation.JSONField;
import com.cocosh.framework.base.BaseEntity;

public class Course extends BaseEntity {
 	private String depart_id;//部门id
 	private String lesson_id;//课程id
	@JSONField(format = "yyyy-MM-dd HH:mm")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
 	private Date start_time;
	@JSONField(format = "yyyy-MM-dd HH:mm")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
 	private Date end_time;//(集中训练没有结束时间)
	private Integer status;//0未发布，1已发布,2停止预约，3结业
 	private Integer num;//最大人数
 	private String teacher;//教师
	private Integer enabled;//状态
 	private Integer type;//类型：0进阶训练，1集中训练
 	private String description;
 	/**非数据库字段**/
 	private Integer have_appoint;//课程已预约的人数
 	private String city_name;//发布方地址
 	private String province_name;//发布方地址
 	private String region_name;//发布方地址
 	private String address;//发布方地址
 	private Integer students;//是否有学生预约课程  0未预约  1已预约
 	private String d_name;//发布方所属中心
 	private String l_name;//课程名称
 	private String province_id;
 	private String city_id;
 	
 	

	public String getProvince_id() {
		return province_id;
	}

	public void setProvince_id(String province_id) {
		this.province_id = province_id;
	}

	public String getCity_id() {
		return city_id;
	}

	public void setCity_id(String city_id) {
		this.city_id = city_id;
	}

	public String getL_name() {
		return l_name;
	}

	public void setL_name(String l_name) {
		this.l_name = l_name;
	}

	public String getD_name() {
		return d_name;
	}

	public void setD_name(String d_name) {
		this.d_name = d_name;
	}

	public String getCity_name() {
		return city_name;
	}

	public void setCity_name(String city_name) {
		this.city_name = city_name;
	}

	public String getProvince_name() {
		return province_name;
	}

	public void setProvince_name(String province_name) {
		this.province_name = province_name;
	}

	public String getRegion_name() {
		return region_name;
	}

	public void setRegion_name(String region_name) {
		this.region_name = region_name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Integer getStudents() {
		return students;
	}

	public void setStudents(Integer students) {
		this.students = students;
	}

	public Integer getHave_appoint() {
		return have_appoint;
	}

	public void setHave_appoint(Integer have_appoint) {
		this.have_appoint = have_appoint;
	}

	public String getDepart_id() {
 		return depart_id;
 	}
 	
 	public void setDepart_id(String depart_id) {
 		this.depart_id = depart_id;
 	}
 	
 	public String getLesson_id() {
 		return lesson_id;
 	}
 	
 	public void setLesson_id(String lesson_id) {
 		this.lesson_id = lesson_id;
 	}
 	
 	public Date getStart_time() {
 		return start_time;
 	}
 	
 	public void setStart_time(Date start_time) {
 		this.start_time = start_time;
 	}
 	
 	public Integer getStatus() {
 		return status;
 	}
 	
 	public void setStatus(Integer status) {
 		this.status = status;
 	}
 	
 	public Integer getNum() {
 		return num;
 	}
 	
 	public void setNum(Integer num) {
 		this.num = num;
 	}
 	
 	public String getTeacher() {
 		return teacher;
 	}
 	
 	public void setTeacher(String teacher) {
 		this.teacher = teacher;
 	}
 	
 	public Integer getEnabled() {
 		return enabled;
 	}
 	
 	public void setEnabled(Integer enabled) {
 		this.enabled = enabled;
 	}

 	public Date getEnd_time() {
		return end_time;
	}

	public void setEnd_time(Date end_time) {
		this.end_time = end_time;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

 	
 	
}