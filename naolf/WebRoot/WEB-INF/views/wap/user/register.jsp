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
<link href="${ctx}/statics/wap/css/validate.css" rel="stylesheet" type="text/css" >
<script type="text/javascript" src="${ctx}/statics/wap/js/jquery-1.8.3.min.js"></script>
<script type="text/javascript" src="${ctx}/statics/wap/js/formValidate.js"></script>
<script type="text/javascript" src="${ctx}/statics/wap/js/common.js"></script>
<script type="text/javascript" src="${ctx}/statics/wap/js/distpicker.data.js"></script>
<script type="text/javascript" src="${ctx}/statics/wap/js/distpicker.js"></script>
<script type="text/javascript" src="${ctx}/statics/wap/js/main.js"></script>
<script type="text/javascript">
</script>
</head>
<body>
<div class="container">
	<div class="forgetPwd mBtm76">
	<form id="form1">
        <div class="block">
            <div class="info">用户信息</div>
            <div class="row">
                <label>姓名：</label>
                <input type="text" placeholder="请输入姓名" id="name" class="row_input_a" />
            </div>
	    	<div class="row">
	        	<label>手机号：</label>
	        	<input type="text" placeholder="请输手机号" id="mobile" class="row_input" />
	            <input type="button" value="获取激活码" id="btn" class="row_btn fr" />
	        </div>
            <div class="row">
                <label>验证码：</label>
                <input type="text" placeholder="请输入验证码" id="code" class="row_input_a" />
            </div>
            <div class="row">
                <label>密码：</label>
                <input type="password" placeholder="请输入密码" id="password" class="row_input_a" />
            </div>
            <div class="row">
                <label>确认密码：</label>
                <input type="password" placeholder="请再次输入密码" id="passwordtwo" class="row_input_a" />
            </div>
        </div>
        <div class="tipRow tip"></div>
        <div class="submitBox">
        	<input type="button" value="注册" class="submitBtn" />
        </div>
        </form>
    </div>
</div>
<script type="text/javascript" src="${ctx}/statics/wap/layui/layui.js"></script>
<script type="text/javascript" src="${ctx}/statics/wap/js/alerts.js"></script>
<script type="text/javascript">
	
	
$(".submitBtn").click(function(){
		//姓名校验
		var name=$("#name").val();
		var reg = /^[\u4e00-\u9fa5]{2,8}$/;  
		if(!reg.test(name)) {alertN("姓名为2-8位汉字");return flast;}
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
		//手机号校验
		var mobile=$("#mobile").val();         
			var reg=/^1[3|4|5|7|8][0-9]\d{8}$/ ;
				if(!(reg.test(mobile))){
				alertN("手机号格式有误！");
				return;
		}
	   $.post("${ctx}/wapapi/register/register",
			   {
			   "code":$("#code").val(),
			   "password":$("#password").val(),
			   "name":$("#name").val(),
			   "mobile":$("#mobile").val(),
			   "address":$("#address").val()
			   },
			   function(data){
			       if(data.code=="0"){ 
						  	layui.use(['layer', 'form'], function(){
						  var layer = layui.layer
						  ,form = layui.form();	
							  layer.open({
								  type: 1, 
								  content: data.msg, 
								  title:false,
								  closeBtn: 0,
								  time:1000,
								  end:function(){
						  	javascript:location.href="${ctx}/wap/index";
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
})




</script>
<script type="text/javascript">
layui.use(['layer', 'form'], function(){
  var layer = layui.layer
  ,form = layui.form();	
  

  $(".row_btn").click(function(){          
var mobile=$("#mobile").val();         
	var reg=/^1[3|4|5|7|8][0-9]\d{8}$/ ;
		if(!(reg.test(mobile))){
		alertN("手机号格式有误！");
		return;
		}
     $.post("${ctx}/wapapi/sms/register",{"mobile":$("#mobile").val()},function(data){
        if(data.code=="0"){ 
      	  time();
        	alertN(data.msg+data.data.codes);
        	//alertN(data.msg);
        }else{
      	  alertN(data.msg);
        }
     },"json");
  })
  
});

var wait=60;
document.getElementById("btn").disabled = false;   
function time() {
	var o=document.getElementById("btn");
	if (wait == 0) {
		o.removeAttribute("disabled");           
		o.value="重新发送";
		wait = 60;
	} else {
		o.setAttribute("disabled", true);
		o.value="发送中(" + wait + ")";
		wait--;
		var t = setTimeout(function() {
			time()
		},
		1000)
	}
}
								
</script>
<!--验证码时间是必须放到底部-->
</body>
</html>