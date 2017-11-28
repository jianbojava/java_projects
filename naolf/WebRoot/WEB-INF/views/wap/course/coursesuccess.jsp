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
<title>扫码成功</title>
<link href="${ctx}/statics/wap/css/base.css" rel="stylesheet" type="text/css">
<link href="${ctx}/statics/wap/css/index.css" rel="stylesheet" type="text/css">
<script type="text/javascript" src="${ctx}/statics/wap/js/jquery-1.8.3.min.js"></script>
<script type="text/javascript" src="${ctx}/statics/wap/js/jquery.raty.min.js"></script>
<script type="text/javascript" src="${ctx}/statics/wap/js/common.js"></script>
<script type="text/javascript" src="${ctx}/statics/wap/js/base.js"></script>
<style type="text/css">
/*====2017.4.27-账单详情====*/
.payDetail_title{ height:1.5rem; line-height:1.5rem; text-align:center; font-size:0.4rem; background:#f0f2f5; color:#745398; font-weight:bold; }
.forgetPwd .row_un{ width:100%; height:0.68rem; padding:0.15rem 0.24rem; font-size:0.28rem; }
.forgetPwd .row_fr{ float:right; }
.forgetPwd .tinyBlock{ padding:0.2rem 0; border-bottom:1px solid #e4e4e4; }
.forgetPwd .payDetail_btn{ text-align:center; margin-top:1rem; }
.forgetPwd .payDetail_btn .payDetailBtn{ padding:0.1rem 0.3rem; background:#745398; color:#fff; font-size:0.34rem; border-radius:0.05rem; font-size:0.28rem; }
</style>
</head>
<body>
<div class="container">
	<div class="forgetPwd">
    	<div class="payDetail_title">请向老师展示后关闭</div>
        <div class="mInfoBlock">
            <div class="minBlock mBtm10">
  				<div class="tinyBlock">
                    <div class="row_un row_fff clearfix">
                        <label>课程名：</label>
                        <label class="row_fr">${lesson_name}&nbsp;&nbsp;&nbsp;&nbsp;<c:if test="${course_type==0}">进阶课程</c:if><c:if test="${course_type==1}">集中课程</c:if></label>
                    </div>
                    <div class="row_un row_fff clearfix">
                        <label>课程时间：</label>
                        <label class="row_fr">${start_time}</label>
                    </div>
                    <div class="row_un row_fff clearfix">
                        <label>课程号：</label>
                        <label class="row_fr">${userticket_sn}</label>
                    </div>
                    <c:if test="${ticket_sn!=''}">
                    <div class="row_un row_fff clearfix">
                        <label>卡号：</label>
                        <label class="row_fr">${ticket_sn}</label>
                    </div>
                    </c:if>
                </div>
                <div class="tinyBlock">
                    <div class="row_un row_fff clearfix">
                        <label>孩子姓名：</label>
                        <label class="row_fr">${stu_name}</label>
                    </div>
                    <div class="row_un row_fff clearfix">
                        <label>孩子年龄：</label>
                        <label class="row_fr">${stu_age}岁</label>
                    </div>
                </div>
            </div>
        </div>
        <div class="payDetail_btn">
        	<input type="button" value="确认" class="payDetailBtn" onclick="location.href='${ctx}/wap/course/cardCouponSearch'" />
        </div>
    </div>
</div> 
<script type="text/javascript" src="${ctx}/statics/wap/js/alerts.js"></script>
<script type="text/javascript" src="${ctx}/statics/wap/layui/layui.js"></script>
<script type="text/javascript">
</script>
</body>
</html>