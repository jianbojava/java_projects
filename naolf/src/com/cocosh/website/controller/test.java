package com.cocosh.website.controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import com.cocosh.sys.model.User;

public class test {
	public Connection getConn(){
	    Connection conn=null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/naolf?useUnicode=true&allowMultiQueries=true&characterEncoding=utf-8","root","root");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return conn;
	}
	
    public static void main(String[] args) throws Exception {
    	
    	List<String> list=new ArrayList<String>();
    	list.add("000");
    	list.add("111");
    	list.add("222");
    	list.add("000");
//    	for(int i=0;i<list.size();i++){
//    		if(list.get(i).equals("000")) list.remove(i);
//    	}
    	for(String s:list){
    		System.out.println(s+"----");
    	}
    	System.out.println(list.size());
    	Set<String> sets=new HashSet<String>();
    	sets.add("000");
    	sets.add("111");
    	Iterator it=sets.iterator();
    	while (it.hasNext()) {
			String s=(String) it.next();
			if(s.equals("000")) it.remove();
		}
    	/*
    	int a[]={54,23,12,45};

        int temp=0;

       for(int i=0;i<a.length;i++){
    	   for(int j=0;j<a.length-i-1;j++){
    		   if(a[j]>a[j+1]){
    			 temp=a[j+1];
    			 a[j+1]=a[j];
    			 a[j]=temp;		
    		   }
    	   }
       }

        for(int i=0;i<a.length;i++){

           System.out.println(a[i]);   

        }
    	
         test t=new test();
         List<User> list=new ArrayList<User>();
 		Connection conn=t.getConn();
 		PreparedStatement stmt=conn.prepareStatement("select * from sys_user where name='新测试2'");
 		 ResultSet rs=stmt.executeQuery();
 		while(rs.next()){
			User u=new User();
			u.setName(rs.getString("name"));
			System.out.println(u.getName());
			list.add(u);
		}
 		System.out.println(list.size());
 		t.close(rs, stmt, conn);
         */
    }
    
    public void close(ResultSet rs,Statement stmt,Connection conn){
		if(rs!=null){
			try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if(stmt!=null){
			try {
				stmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if(conn!=null){
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
	}
}
