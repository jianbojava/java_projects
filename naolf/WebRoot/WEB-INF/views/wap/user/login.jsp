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
	<div class="login">
        <div class="header">
            <img src="${ctx}/statics/wap/images/logo.png" class="head_logo" alt="" />
        </div>
        <div class="main">
            <div class="login_input login_phone">
                <div class="icon_box">
                    <img src="${ctx}/statics/wap/images/login_phone.png" alt="" />
                </div>
                <input type="text" id="number" placeholder="请输入手机号/身份ID/用户名" />
            </div>
            <div class="login_input login_pwd">
                <div class="icon_box">
                    <img src="${ctx}/statics/wap/images/login_pwd.png" alt="" />
                </div>
                <input type="password" id="password" placeholder="请输入密码" />
            </div>
            <div>
                <input type="submit" value="登&nbsp;录" class="login_btn" />
            </div>
            <div class="forgetReg clearfix">
                <span class="forgetPwd fl" onClick="javascript:location.href='${ctx}/wap/forgetPwd'">忘记密码</span>
                <span class="regisAcc fr" onClick="javascript:location.href='${ctx}/wap/chooseIdentity'">注册账户</span>
            </div>
        </div>
    </div>
</div>
<script type="text/javascript" src="${ctx}/statics/wap/layui/layui.js"></script>
<script type="text/javascript" src="${ctx}/statics/wap/js/alerts.js"></script>
<script type="text/javascript">


$(".login_btn").click(function(){
   $.post("${ctx}/wapapi/login",{"number":$("#number").val(),"password":$("#password").val()},function(data){
      if(data.code=="0"){
           javascript:location.href='${ctx}/wap/shop/goodsList';
      }else{
    	alertN(data.msg);
		}
   },"json");
})
//点击回车登陆
$("body").keydown(function() {
             if (event.keyCode == "13") {//keyCode=13是回车键
                 $(".login_btn").click();
             }
});
</script>
</body>
</html>