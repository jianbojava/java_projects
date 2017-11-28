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
<title>超感创作力</title>
<link href="${ctx}/statics/wap/css/base.css" rel="stylesheet" type="text/css">
<link href="${ctx}/statics/wap/css/index.css" rel="stylesheet" type="text/css">
<script type="text/javascript" src="${ctx}/statics/wap/js/common.js"></script>
</head>
<body>
<div class="container">
	<div class="forgetPwd mBtm106">
    	<div class="courseBanner">
            <img src="${ctx}/statics/wap/images/courseBanner.png" alt="" />
        </div>
        <div class="courseBlock">
        	<div class="title">
            	<div class="title1 f28">
                	<i></i>
                	<span>课程信息</span>
                	<i></i>
                </div>
            	<div class="title2 f24">THE COURSE INFORMATION</div>
            </div>
        	<div class="content">
            	<p>课程名：超感创作力</p>
            	<p>课程分类：潜力篇</p>
            	<p>适合年龄：10-18周岁</p>
            	<p>训练课时：7天集中训练+50课时进阶训练</p>
            </div>
        </div>
        <div class="courseBlock">
        	<div class="title">
            	<div class="title1 f28">
                	<span>课程特点</span>
                </div>
            	<div class="title2 f24">THE COURSE INFORMATION</div>
            </div>
        	<div class="content">
            	<p>个性化的高效科学训练方法再融合当代幸福学体系，将素质教育的德（德商）科学教育的智（智商）、全脑教育的慧（慧商）有机结合，构建一整套完美的心智教育体系并家校互动信息化，打造21世纪最前沿的特色教育产业平台。</p>
            </div>
        </div>
        <div class="courseStatus f24">
        	<span>课程状态：</span>
            <span class="status">训练中</span>
        </div>
        <div class="courseStatus f24 clearfix">
        	<span>课程记录：</span>
            <a href="javascript:;" class="status">查看详情</a>
            <div class="arrow_right fr">
            	<img src="${ctx}/statics/wap/images/arrow_right.png" alt="" />
            </div>
        </div>
        <div class="endBtm">
            <div class="courseEndImg endImg">
                <img src="${ctx}/statics/wap/images/courseDetailBtm.png" alt="" />
            </div>
        </div>
        <div class="submitBox">
            <input type="button" value="立即预约" class="submitBtn" />
        </div>
    </div>
</div>
</body>
</html>