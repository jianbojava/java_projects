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
	<div class="usercheck">
		<c:if test="${(not empty myuser)&&(myuser.enabled==0)}" >
		<div class="status">审核中</div>
		<div class="detail">您的账户还在审核中，请耐心等待!</div>
		</c:if>
		<c:if test="${(not empty myuser)&&(myuser.enabled==1)}" >
		<div class="status">审核成功</div>
		<div class="detail">您的账户审核成功，请使用手机号+密码进行登陆!</div>
		</c:if>
		<c:if test="${(empty myuser)||(myuser.enabled==2)}" >
		<div class="status">审核失败</div>
		<div class="detail">您的账户审核失败,请联系审核人员!</div>
		</c:if>
	</div>
</div>

</body>
</html>