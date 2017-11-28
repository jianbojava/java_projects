package com.cocosh.nlf.model;

import java.util.Date;

import java.io.Serializable;

public class Tcomment implements Serializable {
 	private String id;
 	private Integer isserious;//0是，1否
 	private Integer isprogress;
 	private Integer ishelp;
 	private Integer isteam;
 	private Integer isspeak;
 	private Integer isstar;
 	private String teacher;
 	private String comment;
 	private String appoint_id;
 	private Date create_date;
 	
 	public String getId() {
 		return id;
 	}
 	
 	public void setId(String id) {
 		this.id = id;
 	}
 	
 	public Integer getIsserious() {
 		return isserious;
 	}
 	
 	public void setIsserious(Integer isserious) {
 		this.isserious = isserious;
 	}
 	
 	public Integer getIsprogress() {
 		return isprogress;
 	}
 	
 	public void setIsprogress(Integer isprogress) {
 		this.isprogress = isprogress;
 	}
 	
 	public Integer getIshelp() {
 		return ishelp;
 	}
 	
 	public void setIshelp(Integer ishelp) {
 		this.ishelp = ishelp;
 	}
 	
 	public Integer getIsteam() {
 		return isteam;
 	}
 	
 	public void setIsteam(Integer isteam) {
 		this.isteam = isteam;
 	}
 	
 	public Integer getIsspeak() {
 		return isspeak;
 	}
 	
 	public void setIsspeak(Integer isspeak) {
 		this.isspeak = isspeak;
 	}
 	
 	public Integer getIsstar() {
 		return isstar;
 	}
 	
 	public void setIsstar(Integer isstar) {
 		this.isstar = isstar;
 	}
 	
 	public String getTeacher() {
 		return teacher;
 	}
 	
 	public void setTeacher(String teacher) {
 		this.teacher = teacher;
 	}
 	
 	public String getComment() {
 		return comment;
 	}
 	
 	public void setComment(String comment) {
 		this.comment = comment;
 	}
 	
 	public String getAppoint_id() {
 		return appoint_id;
 	}
 	
 	public void setAppoint_id(String appoint_id) {
 		this.appoint_id = appoint_id;
 	}
 	
 	public Date getCreate_date() {
 		return create_date;
 	}
 	
 	public void setCreate_date(Date create_date) {
 		this.create_date = create_date;
 	}
}