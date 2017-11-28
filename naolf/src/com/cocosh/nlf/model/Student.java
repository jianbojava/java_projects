package com.cocosh.nlf.model;



import com.cocosh.framework.base.BaseEntity;

public class Student extends BaseEntity {
 	private String name;
 	private Integer gender;//性别 0男1女
 	private Integer age;
	private String school;
 	private String grade;
 	private String user_id;
 	public Integer getGender() {
		return gender;
	}

	public void setGender(Integer gender) {
		this.gender = gender;
	}
 	public String getName() {
 		return name;
 	}
 	
 	public void setName(String name) {
 		this.name = name;
 	}
 	
 	public Integer getAge() {
 		return age;
 	}
 	
 	public void setAge(Integer age) {
 		this.age = age;
 	}
 	
 	public String getSchool() {
 		return school;
 	}
 	
 	public void setSchool(String school) {
 		this.school = school;
 	}
 	
 	public String getGrade() {
 		return grade;
 	}
 	
 	public void setGrade(String grade) {
 		this.grade = grade;
 	}
 	
 	
 	public String getUser_id() {
 		return user_id;
 	}
 	
 	public void setUser_id(String user_id) {
 		this.user_id = user_id;
 	}
}