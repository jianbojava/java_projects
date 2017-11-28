/*
  Modification Log:
 -----------------------------------------------------------------------------------
  Version		Notes        		By                Date
  -------   	--------------------------   ---------------------------------------
   1.0			Create       		Techbirds         2012-12-13
 -----------------------------------------------------------------------------------
  */
 package com.cocosh.framework.freemarker;
 /**
  * 封装属性信息
  *
  */
 public class Attribute {
 	private String name;
 	private String type;
 
 	public Attribute() {
 	}
 
 	public Attribute(String name, String type) {
 		this.name = name;
 		this.type = type;
 	}
 
 	public String getName() {
 		return name;
 	}
 
 	public void setName(String name) {
 		this.name = name;
 	}
 
 	public String getType() {
 		return type;
 	}
 
 	public void setType(String type) {
 		this.type = type;
 	}
 }
 