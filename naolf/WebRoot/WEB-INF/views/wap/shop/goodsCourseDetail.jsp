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
<script type="text/javascript" src="${ctx}/statics/wap/js/jquery-1.8.3.min.js"></script>
<style type="text/css">
.courseCont img{width: 100%;}
</style>
</head>
<body>
<div class="container mBtm76">
	<div class="course_detail">
    <input type="hidden" value="${lesson.id }"  id="lid" />
    	<div class="title_new">${lesson.name}</div>
    	 <div class="courseCont" style="margin-top: 0.2rem">
           
            <div><img src="${lesson.image}"></div>
        </div>
        
        <div class="classinfo">
        	<ul>
            	<li><strong>适合年龄</strong>${lesson.userage}</li>
            	<li><strong>训练课时</strong>${lesson.period}</li>
            	<li><strong>课程特点</strong>${lesson.special }</li>
            </ul>
        </div>
        <div class="courseSpecial">
        	<div class="specLabel">课程特色</div>
            <p>${lesson.content }</p>
        </div>
        
        <div class="courseBtm" style="display: none">
        	<div class="courseDetail">
            	<div class="ageTitle">适用年龄</div>
            	<div class="ageVal">${lesson.userage}</div>
            </div>
            <div class="courseDetail">
            	<div class="ageTitle">训练课时</div>
            	<div class="ageVal">${lesson.period}</div>
            </div>
            <div class="endImg">
            	<img src="${ctx}/statics/wap/images/courseDetailBtm.png" alt="" />
            </div>
        </div>
    </div>
    <div class="footer footer1">
    	<span class="total">总计：</span>
        <span class="money">&yen;</span>
    	<span class="price">${lesson.price}</span>
    	<input type="button" value="购买" class="buyBtn" onclick="buy()" />
    </div>
</div>

<script type="text/javascript">

		function buy(){
	  		javascript:location.href="${ctx}/wap/shop/lessonOrder?lid="+$("#lid").val();
		}
</script> 
</body>
</html>