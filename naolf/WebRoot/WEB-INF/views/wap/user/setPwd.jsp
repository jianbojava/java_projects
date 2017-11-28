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
<title>会员中心</title>
<link href="${ctx}/statics/wap/css/base.css" rel="stylesheet" type="text/css">
<link href="${ctx}/statics/wap/css/index.css" rel="stylesheet" type="text/css">
<script type="text/javascript" src="${ctx}/statics/wap/js/jquery-1.8.3.min.js"></script>
<script type="text/javascript" src="${ctx}/statics/wap/js/common.js"></script>
</head>
<body>
<div class="container">
	<div class="forgetPwd mBtm76">
	<input type="hidden" id="mobile" value="${user.mobile}">
    	<div class="row">
        	<label>密码：</label>
        	<input type="password" placeholder="请输入密码" id="password" class="row_input_a" />
        </div>
    	<div class="row">
        	<label>确认密码：</label>
        	<input type="password" placeholder="请再次输入密码" id="passwordtwo" class="row_input_a" />
        </div>
        <div class="submitBox">
        	<input type="button" value="设置" class="submitBtn" />
        </div>
    </div>
</div>
<script type="text/javascript" src="${ctx}/statics/wap/js/alerts.js"></script>
<script type="text/javascript" src="${ctx}/statics/wap/layui/layui.js"></script>
<script type="text/javascript">
layui.use(['layer', 'form'], function(){
  var layer = layui.layer
  ,form = layui.form();	
  
  $(".submitBtn").click(function(){
	  
	});
});

$(".submitBtn").click(function(){
		//密码校验
		if($("#password").val()!=$("#passwordtwo").val()){
			alert("两次密码输入不一致");
			return;
		}
		var pwd=$("#passwordtwo").val();
		var res =  /^.*?[\d]+.*$/;;
		var res1 = /^.*?[A-Za-z]/;
		var res2 = /^.*?[\u4e00-\u9fa5]+.*$/;
		var res3 = /^.{6,20}$/;
		if (!res.test(pwd)) {  
		    alertN("密码中必须包含数字 ");return;
		}
		if (!res1.test(pwd)) {  
		    alertN("密码中必须包含字母 ");return;
		}
		if (res2.test(pwd)) {  
		    alertN("密码中不能含有中文 ");return;
		}
		if (!res3.test(pwd)) {  
		    alertN("密码长度必须大于6位 ");return;
		}
    $.post("${ctx}/wapapi/register/registerPwd",{"password":$("#password").val(),"mobile":$("#mobile").val()},function(data){
      if(data.code=="0"){ 
    	  layer.open({
    		  type: 1, 
    		  content: data.msg, 
    		  end:function(){
    		  	javascript:location.href="${ctx}/wap/index";
    		  },
    		  title:false,
    		  closeBtn: 0,
    		  btn:'确定',
    		  btnAlign: 'c',
    		  shadeClose:true,
    		  skin: 'resetPwd_tip'
    		});
    		layer.config({
    		  skin: 'resetPwd_tip'
    		})
      }
   },"json"); 
})


</script> 
</body>
</html>