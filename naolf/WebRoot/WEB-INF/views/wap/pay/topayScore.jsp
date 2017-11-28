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
<title>充值</title>
<link href="${ctx}/statics/wap/css/base.css" rel="stylesheet" type="text/css">
<link href="${ctx}/statics/wap/css/index.css" rel="stylesheet" type="text/css">
<script type="text/javascript" src="${ctx}/statics/wap/js/jquery-1.8.3.min.js"></script>
<script type="text/javascript" src="${ctx}/statics/wap/js/jquery.form.js"></script>
<script type="text/javascript" src="${ctx}/statics/wap/js/common.js"></script>
<style>
.pay_input{ padding:0 0.2rem; }
.pay_input input{ width:100%; height:0.9rem; border:1px solid #ccc; border-radius:0.1rem; margin-top:0.2rem; padding:0 0.2rem; }
.pay_next{ padding:0 0.2rem; margin-top:2rem; }
.pay_next input{ width:100%; height:0.74rem; background:#745398; border-radius:0.1rem; color:#fff; }
</style>
</head>
<body>
<div class="container mBtm91">
	<div class="confirm_order">
        <div class="pay_input"><input type="text" id="amount" placeholder="输入充值金额" /></div>
        <div class="pay_next"><input type="button" value="下一步" onclick="payScore()"  /></div>
    </div>
</div>
<script type="text/javascript" src="${ctx}/statics/wap/layui/layui.js"></script>
<script type="text/javascript" src="${ctx}/statics/wap/js/alerts.js"></script>
<script type="text/javascript">
var r = /^\+?[1-9][0-9]*$/;//正整数验证
function payScore(){
var amount=$("#amount").val();
   if(r.test(amount)){
      location.href="${ctx}/pay/wxpay/payscore?amount="+amount;
   }else{
       alertN("请输入正整数");
      $("#amount").val("");
      return;
   }
}
</script>
</body>
</html>
