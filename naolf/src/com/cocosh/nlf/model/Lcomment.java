package com.cocosh.nlf.model;

import java.util.Date;

import java.io.Serializable;

import com.cocosh.framework.base.BaseEntity;
/**
 * 课程评论   nlf_lcomment
 * @author bobo
 *
 */
public class Lcomment extends BaseEntity {
 	private String user_ticket_id;
 	private String user_id;
 	private String lesson_id;//课程id
 	private Integer serveice_num;//服务态度
 	private Integer profess_num;//专业水平
 	private Integer satify_num;//满意度
 	private Integer comm_num;//评价星级
 	private String content;//评论内容
 	
 	private String user_name;//评价人姓名
 	private String lesson_name;//课程名称
 	
 	
 	
 	public String getUser_name() {
		return user_name;
	}

	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}

	public String getLesson_name() {
		return lesson_name;
	}

	public void setLesson_name(String lesson_name) {
		this.lesson_name = lesson_name;
	}

	public String getUser_ticket_id() {
 		return user_ticket_id;
 	}
 	
 	public void setUser_ticket_id(String user_ticket_id) {
 		this.user_ticket_id = user_ticket_id;
 	}
 	
 	public String getUser_id() {
 		return user_id;
 	}
 	
 	public void setUser_id(String user_id) {
 		this.user_id = user_id;
 	}
 	
 	public String getLesson_id() {
 		return lesson_id;
 	}
 	
 	public void setLesson_id(String lesson_id) {
 		this.lesson_id = lesson_id;
 	}
 	
 	public Integer getServeice_num() {
 		return serveice_num;
 	}
 	
 	public void setServeice_num(Integer serveice_num) {
 		this.serveice_num = serveice_num;
 	}
 	
 	public Integer getProfess_num() {
 		return profess_num;
 	}
 	
 	public void setProfess_num(Integer profess_num) {
 		this.profess_num = profess_num;
 	}
 	
 	public Integer getSatify_num() {
 		return satify_num;
 	}
 	
 	public void setSatify_num(Integer satify_num) {
 		this.satify_num = satify_num;
 	}
 	
 	public Integer getComm_num() {
 		return comm_num;
 	}
 	
 	public void setComm_num(Integer comm_num) {
 		this.comm_num = comm_num;
 	}
 	
 	public String getContent() {
 		return content;
 	}
 	
 	public void setContent(String content) {
 		this.content = content;
 	}
}