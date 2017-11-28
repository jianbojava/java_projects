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
<title>选择训练对象</title>
<link href="${ctx}/statics/wap/css/base.css" rel="stylesheet" type="text/css">
<link href="${ctx}/statics/wap/css/index.css" rel="stylesheet" type="text/css">
<script type="text/javascript" src="${ctx}/statics/wap/js/jquery-1.8.3.min.js"></script>
<script type="text/javascript" src="${ctx}/statics/wap/js/common.js"></script>
<script type="text/javascript" src="${ctx}/statics/wap/js/base.js"></script>
</head>
<body>
<div class="container">
	<div class="forgetPwd reserveChild mBtm106">
    	<div class="title">选择训练对象</div>
    	<input type="hidden" name="ut_id" value="${user_ticket_id}"/>
    	<c:forEach items="${list }" var="s" varStatus="index">
        <div class="reserveCon" onclick="getid('${s.id }')">
    	<input type="hidden" id="s_id" value="${s.id }"/>
        	<div class="show">姓名：${s.name }</div>
            <div class="show">性别：${s.gender }</div>
            <div class="show">年龄：${s.age }</div>
            <div class="show">年级：${s.grade }</div>
            <div class="show">在读学校：${s.school }</div>
            <div class="chooseChild">
                <i <c:if test="${index.index==0 }">class="choiced" </c:if> ></i>
                
            </div>
        </div>
        </c:forEach>
        <div class="submitBox">
        	<input type="button" value="确定" onclick="reserveDevelop()" class="submitBtn" />
        </div>
    </div>
</div>

</body>
<script type="text/javascript">
		var s_id=$("#s_id").val();
		function getid(id){
			s_id = id;
		}
		function reserveDevelop(){
		  	javascript:location.href="${ctx}/wap/course/reserveDevelop?s_id="+s_id+"&ut_id="+$("[name='ut_id']").val();
		}
</script>
</html>