<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
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
<title>消息通知</title>
<link href="${ctx}/statics/wap/css/base.css" rel="stylesheet" type="text/css">
<link href="${ctx}/statics/wap/css/index.css" rel="stylesheet" type="text/css">
<script type="text/javascript" src="${ctx}/statics/wap/js/common.js"></script>
</head>
<body style="background:#f0f2f5;">
<div class="container">
	<div class="message_con mBtm115">
	<c:forEach items="${list }" var="m">
    	<div class="message_block">
        	<div class="date"><fmt:formatDate value="${m.create_date }" pattern="yy/MM/dd HH:mm"/></div>
        	<div class="msg_content">
            	<div class="title">尊敬的会员：</div>
            	<div class="content">${m.content }</div>
            </div>
        </div>
    </c:forEach>
    </div>
    <div class="footer clearfix">
    	<div class="nav">
        	<a href="${ctx}/wap/shop/goodsList">
                <div class="navIcon">
                    <img src="${ctx}/statics/wap/images/home.png" alt="">
                </div>
                <div class="navName">商城</div>
            </a>
        </div>
        <div class="nav">
        	<a  onclick="tologin('${ctx}/wap/course/cardCouponSearch','${wapuser}')">
                <div class="navIcon">
                    <img src="${ctx}/statics/wap/images/course.png" alt="">
                </div>
                <div class="navName">课程</div>
            </a>
        </div>
        <div class="nav">
        	<a onclick="tologin('${ctx}/wap/message/message','${wapuser}')">
                <div class="navIcon">
                    <img src="${ctx}/statics/wap/images/msg_active.png" alt="">
                </div>
                <div class="navName color_745398">消息</div>
            </a>
        </div>
        <div class="nav">
        	<a onclick="tologin('${ctx}/wap/my/my','${wapuser}')">
                <div class="navIcon">
                    <img src="${ctx}/statics/wap/images/my.png" alt="">
                </div>
                <div class="navName">我的</div>
            </a>
        </div>
    </div>
</div>
<script type="text/javascript" src="${ctx}/statics/wap/layui/layui.js"></script>
<script type="text/javascript" src="${ctx}/statics/wap/js/alerts.js"></script>
</body>
</html>

