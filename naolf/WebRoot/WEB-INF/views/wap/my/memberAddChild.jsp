<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0" name="viewport">     
<meta content="yes" name="apple-mobile-web-app-capable">     
<meta content="black" name="apple-mobile-web-app-status-bar-style">     
<meta name="keywords" content="脑立方">
<meta name="description" content="脑立方">
<title>会员信息</title>
<link href="${ctx}/statics/wap/css/base.css" rel="stylesheet" type="text/css">
<link href="${ctx}/statics/wap/css/index.css" rel="stylesheet" type="text/css">
<script type="text/javascript" src="${ctx}/statics/wap/js/common.js"></script>
<script type="text/javascript" src="${ctx}/statics/wap/js/jquery-1.8.3.min.js"></script>
<script type="text/javascript" src="${ctx}/statics/wap/layui/layui.js"></script>
<script type="text/javascript" src="${ctx}/statics/wap/js/alerts.js"></script>
</head>
<body>

<div class="container">
	<div class="forgetPwd mBtm76">
    	<div class="block">
            <div class="info">孩子信息</div>
            <div class="row">
                <label>姓名：</label>
                <input type="text" placeholder="请输入姓名" id="name" class="row_input_a" />
            </div>
            <div class="row row_fff">
                    <label>性别：</label>
                    <select name="sex" class="sex">
                    	<option value="0">男</option>
                    	<option value="1">女</option>
                    </select>
                    <img src="${ctx}/statics/wap/images/arrow_right.png" alt="" class="arrowRt fr" />
            </div>
            <div class="row">
                <label>年龄：</label>
                <input type="text" placeholder="请输入年龄" id="age" class="row_input_a" />
            </div>
            <div class="row">
                <label>年级：</label>
                <input type="text" placeholder="请输入年级" id="grade" class="row_input_a" />
            </div>
            <div class="row">
                <label>在读学校：</label>
                <input type="text" placeholder="请输入学校名称" id="school" class="row_input_a" />
            </div>
        </div>
        <div class="submitBox">
        	<input type="button" value="确认提交" onclick="submit()" class="submitBtn" />
        </div>
    </div>
</div>
<script type="text/javascript" src="${ctx}/statics/wap/layui/layui.js"></script>
<script type="text/javascript" src="${ctx}/statics/wap/js/alerts.js"></script>
<script type="text/javascript">
        var r = /^\+?[1-9][0-9]*$/;//正整数验证
		function submit(){
		var name=$.trim($("#name").val());
		if(name==""){
		   alertN("姓名不可以为空");
		   return;
		}
		var age=$.trim($("#age").val());
		if(age==""||age==null){
		  alertN("年龄不可以为空");
		   return;
		}
		if(!r.test(age)){
		   alertN("年龄请输入正整数!");
		   return;
		}
		
				  $.post("${ctx}/myapi/memberAddChild",{"name":$("#name").val(),"gender":$("[name='sex']").val(),"age":$("#age").val(),"grade":$("#grade").val(),"school":$("#school").val()},function(data){
				      if(data.code=="0"){ layui.use(['layer', 'form'], function(){
						  var layer = layui.layer
						  ,form = layui.form();	
							  layer.open({
								  type: 1, 
								  content: data.msg, 
								  title:false,
								  closeBtn: 0,
								  time:1500,
								  end:function(){
											javascript:location.href="${ctx}/wap/my/memberInfo";
								  },
								  skin: 'active_tip'
								});
								layer.config({
								  skin: 'active_tip'
								})
						});
				      }else{
				      	alertN(data.msg);
				      }
				   },"json");
		  
		   }
	   

</script>

</body>
</html>