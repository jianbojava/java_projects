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
<title>会员中心</title>
<link href="${ctx}/statics/wap/css/base.css" rel="stylesheet" type="text/css">
<link href="${ctx}/statics/wap/css/index.css" rel="stylesheet" type="text/css">
<script type="text/javascript" src="${ctx}/statics/wap/js/jquery-1.8.3.min.js"></script>
<script type="text/javascript" src="${ctx}/statics/wap/js/common.js"></script>
<style type="text/css">
.row_type{ font-size:0.28rem; }
.mRt20{ margin-right:0.2rem; }
.row_type_input{ vertical-align:middle; margin-top:-0.06rem; }
</style>
</head>
<body>
<div class="container">
	<div class="forgetPwd">
    	<div class="info">获取信息</div>
    	<div class="row">
        	<label>激活码：</label>
        	<input type="text" placeholder="请联系人政获取" id="number" class="row_input_a" />
        </div>
        <div class="tipRow tip none">请先输入激活码</div>
        <div class="submitBox">
        	<input type="button" value="下一步" class="submitBtn" />
        </div>
    </div>
</div>
<script type="text/javascript" src="${ctx}/statics/wap/js/alerts.js"></script>
<script type="text/javascript" src="${ctx}/statics/wap/layui/layui.js"></script>
<script type="text/javascript">

$(function(){
	$(".submitBtn").click(function(){
		var valInput=$(".row_input_a").val();
		if(valInput=="" || valInput==null){
			$(".tipRow").show();	
		}
		else{
		   $.post("${ctx}/wapapi/register/partnerActivation",{"number":$("#number").val()},function(data){
		      if(data.code=="0"){//激活码未被使用 
	           		javascript:location.href="${ctx}/wap/partnerInfo?number="+$("#number").val();
		      }else if(data.code=="3"){//激活码已经被使用
		           	javascript:location.href="${ctx}/wap/partnerChecking?number="+$("#number").val();
		      }else{//激活码不存在
		          alertN(data.msg);
		      }
		   },"json");
		}
	})
});

</script>
</body>
</html>