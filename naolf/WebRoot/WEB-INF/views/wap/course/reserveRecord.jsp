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
<title><c:if test="${status==100}">预约</c:if><c:if test="${status==1000}">课程</c:if>记录</title>
<link href="${ctx}/statics/wap/css/base.css" rel="stylesheet" type="text/css">
<link href="${ctx}/statics/wap/css/index.css" rel="stylesheet" type="text/css">
<script type="text/javascript" src="${ctx}/statics/wap/js/common.js"></script>
<style type="text/css">
#haveclass table th,#haveclass table td{ width:25%; }
</style>
</head>
<body>
<div class="container">
	<div class="point">
        <div class="pointList" <c:if test="${status==1000 }"> id="haveclass"</c:if>>
        	<table>
	            	<thead>
	            	<c:if test="${status==100 }">
	                    <tr>
	                        <th>分中心地址</th>
	                        <th>课时</th>
	                        <th>上课时间</th>
	                    </tr>
	                 </c:if>
	                 <c:if test="${status==1000 }">
	                    <tr >
	                        <th>分中心地址</th>
	                        <th>课时</th>
	                        <th>上课时间</th>
	                        <th>教师评论</th>
	                    </tr>
	                 </c:if>
	                </thead>
                <tbody>
	        		<c:forEach items="${list }" var="l" varStatus="index">
	                	<tr>
	                    	<td>${l.depart_name }</td>
	                    	<td><c:if test="${l.course_type==1}">集中课时</c:if><c:if test="${l.course_type==0}">${index.index}课时</c:if></td>
	                    	<td>
	                    	   <c:if test="${status==100}">
			                        <div><fmt:formatDate value="${l.course_date}" pattern="yy/MM/dd"/></div>
			                        <div class="color_a5a4a8"><fmt:formatDate value="${l.course_date}" pattern="HH:mm"/></div>
	                           </c:if>
	                            <c:if test="${status==1000}">
			                        <div><fmt:formatDate value="${l.study_date}" pattern="yy/MM/dd"/></div>
			                        <div class="color_a5a4a8"><fmt:formatDate value="${l.study_date}" pattern="HH:mm"/></div>
	                           </c:if>
	                        </td>
	                        <c:if test="${status==1000 }"><td ><c:if test="${l.tcomment==1}"><span onclick="location.href='${ctx}/wap/course/tcomment/${l.id}'">查看</span></c:if></td></c:if>
	                    </tr>
	                </c:forEach>
                </tbody>
            </table>
        </div>
    </div>
</div>
</body>
</html>