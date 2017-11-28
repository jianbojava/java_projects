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
<title>卡券升级</title>
<link href="${ctx}/statics/wap/css/base.css" rel="stylesheet" type="text/css">
<link href="${ctx}/statics/wap/css/index.css" rel="stylesheet" type="text/css">
<script type="text/javascript" src="${ctx}/statics/wap/js/common.js"></script>
<script type="text/javascript" src="${ctx}/statics/wap/js/base.js"></script>
</head>
<body>
<div class="container">
	<div class="forgetPwd">
    	<div class="mInfoBlock">
            <div class="minBlock">
               <c:if test="${used==1}"><!-- 已使用的才能使用积分和金额升级 -->
	                <div class="row row_fff">
	                	<a href="${ctx}/wap/my/chooseOnlinePay?ut_id=${ut_id}" class="up_grade">
	                        <span class="f24">选择支付</span>
	                        <img src="${ctx}/statics/wap/images/arrow_right.png" alt="" class="arrowRt fr" />
	                    </a>
	                </div>
                </c:if>
                <div class="row row_fff">
                	<a href="${ctx}/wap/my/chooseStudentCard?ut_id=${ut_id}" class="up_grade">
                        <span class="f24">选择学习券</span>
                        <img src="${ctx}/statics/wap/images/arrow_right.png" alt="" class="arrowRt fr" />
                    </a>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>