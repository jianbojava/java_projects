package com.cocosh.member.model;

import java.io.Serializable;
import java.util.Date;

import com.cocosh.framework.util.StringUtil;

public class Message implements Serializable{
	private String id;
 	private String mem_id;
 	private String title;
 	private String image;
 	private String content;
 	private Date create_date;
 	
 	public Message() {
	}

	public Message(String id, String mem_id) {
		this.id = id;
		this.mem_id = mem_id;
	}
	
	public Message(String mem_id, String title,String content) {
		this.id=StringUtil.getUuid();
		this.mem_id = mem_id;
		this.title=title;
		this.content = content;
	}

	public Message(String mem_id, String title, String image,String content) {
		this.id=StringUtil.getUuid();
		this.mem_id = mem_id;
		this.title = title;
		this.image = image;
		this.content = content;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

 	public String getMem_id() {
 		return mem_id;
 	}
 	
 	public void setMem_id(String mem_id) {
 		this.mem_id = mem_id;
 	}
 	
 	public String getTitle() {
 		return title;
 	}
 	
 	public void setTitle(String title) {
 		this.title = title;
 	}
 	
 	public String getImage() {
 		return image;
 	}
 	
 	public void setImage(String image) {
 		this.image = image;
 	}
 	
 	public String getContent() {
 		return content;
 	}
 	
 	public void setContent(String content) {
 		this.content = content;
 	}
 	
 	public Date getCreate_date() {
 		return create_date;
 	}
 	
 	public void setCreate_date(Date create_date) {
 		this.create_date = create_date;
 	}
 	
}