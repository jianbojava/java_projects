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
<script type="text/javascript" src="${ctx}/statics/wap/js/common.js"></script>
<script type="text/javascript" src="${ctx}/statics/wap/js/jquery-1.8.3.min.js"></script>
</head>
<body>
<div class="container">
	<div class="forgetPwd mBtm96">
    	<div class="row">
        	<label>手机号：</label>
        	<input type="text" placeholder="请输入正确的手机号码" id="mobile" class="row_input" />
            <input type="button" value="获取验证码" id="btn" class="row_btn fr" />
        </div>
    	<div class="row">
        	<label>验证码：</label>
        	<input type="text" placeholder="请输入验证码" id="code" class="row_input_a" />
        </div>
        <div class="tipRow tip">请先输入注册手机号码以验证账户</div>
        <div class="submitBox">
        	<input type="button" value="提交" class="submitBtn" />
        </div>
    </div>
</div>
<!--验证码时间是必须放到底部-->
<script type="text/javascript" src="${ctx}/statics/wap/layui/layui.js"></script>
<script type="text/javascript" src="${ctx}/statics/wap/js/alerts.js"></script>
<script type="text/javascript">
var code;
$(".row_btn").click(function(){          
var mobile=$("#mobile").val();         
	var reg=/^1[3|4|5|7|8][0-9]\d{8}$/ ;
		if(!(reg.test(mobile))){
		alertN("手机号格式有误！");
		return;
		}
   $.post("${ctx}/wapapi/sms/version",{"mobile":$("#mobile").val()},function(data){
      if(data.code=="0"){ 
    	  time();
        alertN(data.msg+data.data.codes);
       //alertN(data.msg);
        code = data.data.codes;
      }else{
    	  alertN(data.msg);
      }
   },"json");
})

$(".submitBtn").click(function(){
var mobile=$("#mobile").val();         
	var reg=/^1[3|4|5|7|8][0-9]\d{8}$/ ;
		if(!(reg.test(mobile))){
		alertN("手机号格式有误！");
		return;
		}
   $.post("${ctx}/wapapi/forgetPwd",{"mobile":$("#mobile").val(),"code":$("#code").val()},function(data){
      if(data.code=="0"){ 
           javascript:location.href="${ctx}/wap/resetPwd?mobile="+$("#mobile").val();
      }else{
      	alertN(data.msg);
      }
   },"json");
})

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