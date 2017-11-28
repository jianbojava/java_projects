<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!doctype html>
<!--[if lt IE 7]> <html class="ie6 oldie"> <![endif]-->
<!--[if IE 7]>    <html class="ie7 oldie"> <![endif]-->
<!--[if IE 8]>    <html class="ie8 oldie"> <![endif]-->
<!--[if gt IE 8]><!-->
<html class="">
<!--<![endif]-->
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0, user-scalable=0, minimum-scale=1.0, maximum-scale=1.0">
<title>对不起，您访问的页面不存在！</title>
<style type="text/css">
*{ box-sizing:border-box;}
body{ font-size:16px; font-family:"Microsoft Yahei"; color:#333;  width:100%; height:100%; margin:0; padding:0;}
.Yang_shop{ width:640px; margin:0 auto;}
.Yang_div{ width:100%; display:block; margin-top:40%; position:relative;}
dl{ margin:0; padding:0;}
.Yang_div dl dt{ font-size:1em; text-align:center; line-height:2em; color:#A7A7A7; margin-bottom:10px;}
.Yang_div dl dt img{ width:110px; height:110px;}
.Yang_div dl dd{ width:100%; text-align:center; margin:0; line-height:1.5em; color:#A7A7A7; margin-top:10px;}
.Yang_div dl dd a{ color:blue; line-height:1.5em; font-size:1em;color:#A7A7A7; text-decoration:none; }
@media(max-width:768px){
.Yang_shop{ width:96%; margin:10px 2%;}
	}
</style>
</head>

<body>
     <div class="Yang_shop">
          <div class="Yang_div">
               <dl>
                   <dt><img src="${ResStatic }/static/img/k.png"></dt>
                   <dd>页面加载失败，请稍后再试</dd>
                   <dd><a href="${ctx}/wap/index">返回首页</a></dd>
               </dl>
          </div>
     </div>
</body>
</html>