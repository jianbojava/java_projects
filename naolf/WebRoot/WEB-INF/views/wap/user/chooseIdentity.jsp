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
</head>
<body>
<div class="container">
	<div class="chooseIdentity">
    	<div class="chooseTxt">请选择</div>
    	<div class="BtnGroup">
        	<input type="button" value="新用户" class="btn" onClick="javascript:location.href='${ctx}/wap/register'" />
        	<input type="button" value="学员"  class="btn" onClick="javascript:location.href='${ctx}/wap/studentsActivation'" />
        	<input type="button" value="员工/合伙人"  class="btn" onClick="javascript:location.href='${ctx}/wap/partnerActivation'" />
        </div>
    </div>
</div>
</body>
</html>