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
<title>预约详情</title>
<link href="${ctx}/statics/wap/css/base.css" rel="stylesheet" type="text/css">
<link href="${ctx}/statics/wap/css/index.css" rel="stylesheet" type="text/css">
<script type="text/javascript" src="${ctx}/statics/wap/js/common.js"></script>
<style type="text/css">
.courseCont img{text-align: center;width: 100%}

</style>
</head>
<body style="background:#f0f2f5;">
<div class="container">
	<div class="reserve_detail">
    	<div class="reserve_con">
        	<div class="title f28">${course.l_name }</div>
            <div class="row">
        		<span class="row_left">上课时间</span>
            	<span><fmt:formatDate value="${course.start_time}" pattern="yy年MM月dd日"/></span>
        	</div>
            <div class="row">
        		<span class="row_left">上课地点</span>
            	<span>${course.d_name }</span>
                <span class="addDetail" onclick="tomap()">${course.province_name }${course.city_name }${course.region_name }${course.address }<img src="${ctx}/statics/wap/images/maps.png" style="width: 14px;height: 18px; vertical-align: middle;margin-left: 5px;"></span>
        	</div>
            <div class="row">
        		<span class="row_left">上课人数</span>
            	<span>${course.have_appoint }/${course.num }人</span>
        	</div>
            <div class="row">
        		<span class="row_left">指导教师</span>
            	<span>${course.teacher }</span>
        	</div>
        	<div class="row">
        		<span class="row_left">备注</span>
        		<div class="courseCont" style="margin-left: 1.8rem;margin-top: -0.43rem">${course.description}</div>
        	</div>
        </div>
    </div>
</div>
<script type="text/javascript">
  function tomap(){
   location.href="http://api.map.baidu.com/marker?location=${depart.latitude},${depart.longitude}&title=${depart.name}&content=${depart.address}&output=html"; 
	  //location.href="http://api.map.baidu.com/geocoder?location=${depart.latitude},${depart.longitude}&coord_type=gcj02&output=html";
			}
</script>
</body>
</html>