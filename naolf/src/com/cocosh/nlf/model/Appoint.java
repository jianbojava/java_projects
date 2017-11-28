package com.cocosh.nlf.model;

import java.io.Serializable;
import java.util.Date;

import com.cocosh.sys.model.Depart;

public class Appoint implements Serializable {
 	private String id;
 	private String user_id;
 	private String student_id;
 	private String norder_id;
 	private String course_id;
 	private String lesson_id;
 	private Integer status;//0待审核 1已审核 2已取消 3训练中 4已结业 (后台0,1的才可以取消,前端0可以取消)
 	private Date create_date;
 	private Date modify_date;
 	private String enabled;
	private Date about_date;//预约时间
	private Date study_date;//上课时间
	private Integer course_type;//课程类型： 0进阶训练，1集中训练
 	private String user_ticket_id;//userticket的id;
 	private Integer tcomment;//老师的评价(0未评价，1已评级)
 	//非数据库字段
 	private String student_ids;
 	private String student_name;
	private Integer student_age;
 	private Integer student_gender;
	private String norder_ids;
 	private String course_ids;
 	private String user_ids;
	private String user_name;
	private String user_mobile;
	private String ref_name;
	private String ref_mobile;
 	private String depart_name;//部门名称
 	private String lesson_name;//课程名称
	private Date course_date;//课程上课时间
 	private String lesson_period;  //课时
 	
 	public Appoint(){
 		super();
 	}
	public Appoint(String id, String user_id, String student_id,
			String norder_id, String course_id, String lesson_id,
			Integer status, String enabled, Date study_date, Integer course_type,String user_ticket_id) {
		super();
		this.id = id;
		this.user_id = user_id;
		this.student_id = student_id;
		this.norder_id = norder_id;
		this.course_id = course_id;
		this.lesson_id = lesson_id;
		this.status = status;
		this.enabled = enabled;
		this.study_date = study_date;
		this.course_type = course_type;
		this.user_ticket_id = user_ticket_id;
	}

 	
 	
 	public String getLesson_name() {
		return lesson_name;
	}

	public void setLesson_name(String lesson_name) {
		this.lesson_name = lesson_name;
	}





	public Date getCourse_date() {
		return course_date;
	}

	public void setCourse_date(Date course_date) {
		this.course_date = course_date;
	}

	public String getLesson_period() {
		return lesson_period;
	}

	public void setLesson_period(String lesson_period) {
		this.lesson_period = lesson_period;
	}

	public Date getAbout_date() {
		return about_date;
	}

	public void setAbout_date(Date about_date) {
		this.about_date = about_date;
	}

	public Date getStudy_date() {
		return study_date;
	}

	public void setStudy_date(Date study_date) {
		this.study_date = study_date;
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
 	
 	public String getStudent_id() {
 		return student_id;
 	}
 	
 	public void setStudent_id(String student_id) {
 		this.student_id = student_id;
 	}
 	
 	public String getNorder_id() {
 		return norder_id;
 	}
 	
 	public void setNorder_id(String norder_id) {
 		this.norder_id = norder_id;
 	}
 	
 	public String getCourse_id() {
 		return course_id;
 	}
 	
 	public void setCourse_id(String course_id) {
 		this.course_id = course_id;
 	}
 	
 	public String getLesson_id() {
 		return lesson_id;
 	}
 	
 	public void setLesson_id(String lesson_id) {
 		this.lesson_id = lesson_id;
 	}
 	
 	public Integer getStatus() {
 		return status;
 	}
 	
 	public void setStatus(Integer status) {
 		this.status = status;
 	}
 	
 	public Date getCreate_date() {
 		return create_date;
 	}
 	
 	public void setCreate_date(Date create_date) {
 		this.create_date = create_date;
 	}
 	
 	public Date getModify_date() {
 		return modify_date;
 	}
 	
 	public void setModify_date(Date modify_date) {
 		this.modify_date = modify_date;
 	}

 	
	public String getEnabled() {
		return enabled;
	}

	public void setEnabled(String enabled) {
		this.enabled = enabled;
	}

	public String getStudent_ids() {
		return student_ids;
	}

	public void setStudent_ids(String student_ids) {
		this.student_ids = student_ids;
	}

	public String getStudent_name() {
		return student_name;
	}

	public void setStudent_name(String student_name) {
		this.student_name = student_name;
	}


	public String getNorder_ids() {
		return norder_ids;
	}

	public void setNorder_ids(String norder_ids) {
		this.norder_ids = norder_ids;
	}

	public String getCourse_ids() {
		return course_ids;
	}

	public void setCourse_ids(String course_ids) {
		this.course_ids = course_ids;
	}

	public String getUser_ids() {
		return user_ids;
	}

	public void setUser_ids(String user_ids) {
		this.user_ids = user_ids;
	}

	public String getUser_name() {
		return user_name;
	}

	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}
	public String getUser_ticket_id() {
		return user_ticket_id;
	}

	public void setUser_ticket_id(String user_ticket_id) {
		this.user_ticket_id = user_ticket_id;
	}
	public Integer getCourse_type() {
		return course_type;
	}

	public void setCourse_type(Integer course_type) {
		this.course_type = course_type;
	}
	public String getDepart_name() {
		return depart_name;
	}

	public void setDepart_name(String depart_name) {
		this.depart_name = depart_name;
	}
	public Integer getTcomment() {
		return tcomment;
	}
	public void setTcomment(Integer tcomment) {
		this.tcomment = tcomment;
	}
	
	public Integer getStudent_age() {
		return student_age;
	}
	public void setStudent_age(Integer student_age) {
		this.student_age = student_age;
	}
	public Integer getStudent_gender() {
		return student_gender;
	}
	public void setStudent_gender(Integer student_gender) {
		this.student_gender = student_gender;
	}
	public String getUser_mobile() {
		return user_mobile;
	}
	public void setUser_mobile(String user_mobile) {
		this.user_mobile = user_mobile;
	}
	public String getRef_name() {
		return ref_name;
	}
	public void setRef_name(String ref_name) {
		this.ref_name = ref_name;
	}
	public String getRef_mobile() {
		return ref_mobile;
	}
	public void setRef_mobile(String ref_mobile) {
		this.ref_mobile = ref_mobile;
	}
 	
 	
}