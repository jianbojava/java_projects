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
<title>商城介绍</title>
<link href="${ctx}/statics/wap/css/base.css" rel="stylesheet" type="text/css">
<link href="${ctx}/statics/wap/css/index.css" rel="stylesheet" type="text/css">
<script type="text/javascript" src="${ctx}/statics/wap/js/common.js"></script>
<script type="text/javascript" src="${ctx}/statics/wap/js/jquery-1.8.3.min.js"></script>
</head>
<body>
<div class="container mBtm95">
	<div class="forgetPwd">
    	<div class="info">课程</div>
    	<div class="goodCont clearfix">
	    	<c:forEach items="${listL}" var="l">
	    	<div class="goods_list">
	            <a href="${ctx}/wap/shop/goodsCourseDetail?id=${l.id}">
	                <img src="${l.image}" class="goods_img" alt="" />
	                <p class="title">${l.catname}：${l.name}</p>
	            </a>
	        </div>
	        </c:forEach>
    	</div>
    </div>
    <div class="forgetPwd">
    	<div class="info">卡券</div>
    	<div class="goodCont clearfix">
    		<c:forEach items="${listT}" var="t">
    		<div class="goods_list">
            	<a href="${ctx}/wap/shop/cardCouponDetail?id=${t.id}">
                    <img src="${t.image}" class="goods_img" alt="" />
                    <p class="title">${t.name}</p>
                </a>
            </div>
            </c:forEach>
    	</div>
    </div>
    <div class="footer clearfix">
    	<div class="nav">
        	<a href="${ctx}/wap/shop/goodsList">
                <div class="navIcon">
                    <img src="${ctx}/statics/wap/images/home_active.png" alt="">
                </div>
                <div class="navName color_745398">商城</div>
            </a>
        </div>
        <div class="nav">
        	<a onclick="tologin('${ctx}/wap/course/cardCouponSearch','${wapuser}')">
                <div class="navIcon">
                    <img src="${ctx}/statics/wap/images/course.png" alt="">
                </div>
                <div class="navName">课程</div>
            </a>
        </div>
        <div class="nav">
        	<a onclick="tologin('${ctx}/wap/message/message','${wapuser}')">
                <div class="navIcon">
                    <img src="${ctx}/statics/wap/images/msg.png" alt="">
                </div>
                <div class="navName">消息</div>
            </a>
        </div>
        <div class="nav">
        	<a  onclick="tologin('${ctx}/wap/my/my','${wapuser}')">
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