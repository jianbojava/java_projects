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
<title>卡券查询</title>
<link href="${ctx}/statics/wap/css/base.css" rel="stylesheet" type="text/css">
<link href="${ctx}/statics/wap/css/index.css" rel="stylesheet" type="text/css">
<script type="text/javascript" src="${ctx}/statics/wap/js/jquery-1.8.3.min.js"></script>
<script type="text/javascript" src="${ctx}/statics/wap/js/common.js"></script>
<script type="text/javascript" src="${ctx}/statics/wap/js/base.js"></script>
</head>
<body>
<div class="container mBtm95">
	<div class="point">
        <div class="pointList cardList">
        	<table>
                <tbody>
	                <c:if test="${list.size()==0}"><td>暂无卡券</td></c:if>
                	<c:forEach items="${list}" var="l">
	                	<tr>
	                    	<td>${l.name }</td>
	                    	<td>${l.sn }</td>
	                    	<td>
	                    	<!-- 已经卖了---已激活 -->
	                    	   <c:if test="${(l.user_id!=l.used_id)&&(not empty l.used_id)}">已激活</c:if>
	                    	<!--没卖  -->
	                    	   <c:if test="${(l.user_id==l.used_id)||(empty l.used_id)}">
			                    	<c:if test="${l.used==0}">未激活</c:if>
			                    	<c:if test="${l.used==2}">已作废</c:if>
			                    	<c:if test="${l.used==1&&(l.upgrade==0||(l.upgrade==2))}">已激活</c:if>
			                    	<c:if test="${l.used==1&&l.upgrade==1}">已升级</c:if>
	                    	   </c:if>
	                    	</td>
	                        <td><button class="cardDetail" onClick="javascript:location.href='${ctx}/wap/my/myCardDetail?ut_id=${l.id }'">详情</button></td>
	                    </tr>
                    </c:forEach>
                </tbody>
            </table>
        </div>
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
        	<a href="${ctx}/wap/course/cardCouponSearch">
                <div class="navIcon">
                    <img src="${ctx}/statics/wap/images/course.png" alt="">
                </div>
                <div class="navName">课程</div>
            </a>
        </div>
        <div class="nav">
        	<a href="${ctx}/wap/message/message">
                <div class="navIcon">
                    <img src="${ctx}/statics/wap/images/msg.png" alt="">
                </div>
                <div class="navName">消息</div>
            </a>
        </div>
        <div class="nav">
        	<a href="${ctx}/wap/my/my">
                <div class="navIcon">
                    <img src="${ctx}/statics/wap/images/my_active.png" alt="">
                </div>
                <div class="navName color_745398">我的</div>
            </a>
        </div>
    </div>
</div>
</body>
</html>