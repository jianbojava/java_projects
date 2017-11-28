package com.cocosh.nlf.model;


import java.io.Serializable;

public class Uprule implements Serializable {
 	private String id;
 	private String ticket_id1;//原卡id
 	private String ticket_id2;//原券id
 	private String ticket_id3;//升级卡id
 	
 	public String getId() {
 		return id;
 	}
 	
 	public void setId(String id) {
 		this.id = id;
 	}
 	
 	public String getTicket_id1() {
 		return ticket_id1;
 	}
 	
 	public void setTicket_id1(String ticket_id1) {
 		this.ticket_id1 = ticket_id1;
 	}
 	
 	public String getTicket_id2() {
 		return ticket_id2;
 	}
 	
 	public void setTicket_id2(String ticket_id2) {
 		this.ticket_id2 = ticket_id2;
 	}
 	
 	public String getTicket_id3() {
 		return ticket_id3;
 	}
 	
 	public void setTicket_id3(String ticket_id3) {
 		this.ticket_id3 = ticket_id3;
 	}
}