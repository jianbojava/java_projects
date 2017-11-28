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
<title>超感创作力</title>
<link href="${ctx}/statics/wap/css/base.css" rel="stylesheet" type="text/css">
<link href="${ctx}/statics/wap/css/index.css" rel="stylesheet" type="text/css">
<script type="text/javascript" src="${ctx}/statics/wap/js/jquery-1.8.3.min.js"></script>
<script type="text/javascript" src="${ctx}/statics/wap/js/jquery.raty.min.js"></script>
<script type="text/javascript" src="${ctx}/statics/wap/js/common.js"></script>
<script type="text/javascript" src="${ctx}/statics/wap/js/base.js"></script>
<style type="text/css">
.comment .courseDetail .row{ width:100%; padding:0;}
.comment .courseDetail .row .content{ padding-left:0.0rem; line-height:0.48rem; color:black; }
.clearfix i{ width:0.38rem;height:0.38rem; display:inline-block;}
.clearfix{font-size: 0.28rem;line-height: 0.4rem;}
.whstar{ background:url(${ctx}/statics/wap/images/star01.png) no-repeat; background-size:cover; }
.yestar{ background:url(${ctx}/statics/wap/images/star00.png) no-repeat; background-size:cover; }
.mycontent{padding-left: 0.5rem;}
.comment .satisfy .row .icon{ width:1.2rem; font-size:0.28rem; }
</style>
</head>
<body>
<div class="container">
	<div class="comment mBtm96">
    	<div class="courseDetail">
        	<div class="row clearfix">
            	<div class="content f28">这周的我</div>
            </div>
        </div>
		
        
        <div class="satisfy">
            <div class="row clearfix">
            	<div class="mycontent clearfix f24 fl">
            	  <div class="icon fl">认真专注</div>
            	  <c:if test="${tcomment.isserious==0}"><i class="yestar"></c:if><c:if test="${tcomment.isserious==1}"><i class="whstar"></c:if> </i>
                </div>
                <div class="mycontent clearfix f24 fl">
            	  <div class="icon fl">小有进步</div>
            	  <c:if test="${tcomment.isprogress==0}"><i class="yestar"></c:if><c:if test="${tcomment.isprogress==1}"><i class="whstar"></c:if> </i>
                </div>
                 <div class="mycontent clearfix f24 fl">
            	  <div class="icon fl">乐于助人</div>
            	  <c:if test="${tcomment.ishelp==0}"><i class="yestar"></c:if><c:if test="${tcomment.ishelp==1}"><i class="whstar"></c:if> </i>
                </div>
            </div>
            
             <div class="row clearfix">
            	<div class="mycontent clearfix f24 fl">
            	  <div class="icon fl">团队合作</div>
            	  <c:if test="${tcomment.isteam==0}"><i class="yestar"></c:if><c:if test="${tcomment.isteam==1}"><i class="whstar"></c:if> </i>
                </div>
                <div class="mycontent clearfix f24 fl">
            	  <div class="icon fl">积极发言</div>
            	  <c:if test="${tcomment.isspeak==0}"><i class="yestar"></c:if><c:if test="${tcomment.isspeak==1}"><i class="whstar"></c:if></i> 
                </div>
                 <div class="mycontent clearfix f24 fl">
            	  <div class="icon fl">今日之星</div>
            	  <c:if test="${tcomment.isstar==0}"><i class="yestar"></c:if><c:if test="${tcomment.isstar==1}"><i class="whstar"></c:if> </i>
                </div>
            </div>
            
           
        </div>
        <div class="otherAdvice">
        	<div class="advice" style="color: black">老师寄语</div>
        	<div style="border-top: 1px solid #ddd;border-bottom: 1px solid #ddd;">
            <textarea rows="5" style="color: #484746;line-height: 0.5rem" id="content" class="adviceCon" readonly="readonly">${tcomment.comment }</textarea>
            </div>
        </div>
        <div class="otherAdvice">
        	<div class="advice" style="float: right; margin-bottom: 0px">指导师：${tcomment.teacher }</div>
        </div>
        <div class="submitBox">
        	<input type="button" onclick="javascript:history.back(-1)" value="返回" class="submitBtn" />
        </div>
    </div>
</div>
</body>
</html>