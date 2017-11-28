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
  * 封装类信息
  *
  */
 public class Class {
 	private String packagename;
 	private String classname;
 	private String[] imports;
 
 	public Class() {
 	}
 
 	public Class(String classname, String[] imports,String packagename) {
 		super();
 		this.classname = classname;
 		this.imports = imports;
 		this.packagename=packagename;
 	}
 
 	public String getClassname() {
 		return classname;
 	}
 
 	public void setClassname(String classname) {
 		this.classname = classname;
 	}
 
 	public String[] getImports() {
 		return imports;
 	}
 
 	public void setImports(String[] imports) {
 		this.imports = imports;
 	}
 
 	public String getPackagename() {
 		return packagename;
 	}
 
 	public void setPackagename(String packagename) {
 		this.packagename = packagename;
 	}
 }
 