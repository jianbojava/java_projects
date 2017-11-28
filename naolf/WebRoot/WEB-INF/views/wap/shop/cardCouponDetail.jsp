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
<title>VIP钻卡</title>
<link href="${ctx}/statics/wap/css/base.css" rel="stylesheet" type="text/css">
<link href="${ctx}/statics/wap/css/index.css" rel="stylesheet" type="text/css">
<script type="text/javascript" src="${ctx}/statics/wap/js/common.js"></script>
<script type="text/javascript" src="${ctx}/statics/wap/js/jquery-1.8.3.min.js"></script>
</head>
<body>
<div class="container mBtm76">
	<div class="forgetPwd">
    	<div class="info">包含课程</div>
    	<div class="goodCont clearfix">
	    	<c:forEach items="${list}" var="l">
	    	<div class="goods_list">
	            <a>
	                <img src="${l.image}" class="goods_img" alt="" />
	                <p class="title">${l.catname}：${l.name}</p>
	            </a>
	        </div>
	        </c:forEach>
    	</div>
    </div>
    <input type="hidden" value="${ticket.id }"  id="id" />
    <div class="cardIntro">
    	<div class="detailBlock">
    		<div class="title">会员须知</div>
    		<div class="detail">${ticket.notice}</div>
    	</div>
        <div class="detailBlock mTop15">
    		<div class="title">使用须知</div>
    		<div class="detail">${ticket.use_rule}</div>
    	</div>
    </div>
    <div class="footer footer1">
    	<span class="total">总计：</span>
        <span class="money">&yen;</span>
    	<span class="price">${ticket.price}</span>
    	<input type="button" value="购买" class="buyBtn" onclick="buy()"/>
    </div>
</div>
<script type="text/javascript">
				function buy(){
    		  		javascript:location.href="${ctx}/wap/shop/ticketOrder?tid="+$("#id").val();
    		  	}

</script>
</body>
</html>