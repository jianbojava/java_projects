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
<title>会员信息</title>
<link href="${ctx}/statics/wap/css/base.css" rel="stylesheet" type="text/css">
<link href="${ctx}/statics/wap/css/index.css" rel="stylesheet" type="text/css">
<script type="text/javascript" src="${ctx}/statics/wap/js/jquery-1.8.3.min.js"></script>
<script type="text/javascript" src="${ctx}/statics/wap/js/formValidate.js"></script>
<script type="text/javascript" src="${ctx}/statics/wap/js/common.js"></script>
<script type="text/javascript" src="${ctx}/statics/wap/js/distpicker.data.js"></script>
<script type="text/javascript" src="${ctx}/statics/wap/js/distpicker.js"></script>
<script type="text/javascript" src="${ctx}/statics/wap/js/main.js"></script>
</head>
<body>
<div class="container">
	<div class="forgetPwd mBtm76">
    	<div class="info">地址修改</div>
        <div class="block">
            <div class="row">
                <label>地址：</label>
                <div id="distpicker2">
                    <select id="province" class="select" name="province" data-placeholder="选择省份..." ></select> 
                    <select id="city" class="select" name="city" data-placeholder="选择城市..." ></select>
                    <select id="district" class="select" name="region" data-placeholder="选择区县..." ></select>
                </div> 
            </div>
            <div class="row">
                <label>详细地址：</label>
                <input type="text" placeholder="街道、楼牌号等" id="address" class="row_input_a"  value="${user.address}"/>
            </div>
        </div>
        <!--<div class="tipRow tip">请先输入注册手机号码以验证账户</div>-->
        <div class="submitBox">
        	<input type="button" value="确认提交" class="submitBtn" />
        </div>
    </div>
</div>

<script type="text/javascript" src="${ctx}/statics/wap/layui/layui.js"></script>
<script type="text/javascript" src="${ctx}/statics/wap/js/alerts.js"></script>
<script type="text/javascript">
	
	
$(".submitBtn").click(function(){
	   $.post("${ctx}/myapi/updateMember",
			   { 
				"province_id":$("[name='province']").val(),
				"province_name":$("[name='province']").find("option:selected").text(),
				"city_id":$("[name='city']").val(),
				"city_name":$("[name='city']").find("option:selected").text(),
				"region_id":$("[name='region']").val(),
				"region_name":$("[name='region']").find("option:selected").text(),
			   "address":$("#address").val()
			   },
			   function(data){
			       if(data.code=="0"){ 
			      			alertS(data.msg);
						  	javascript:location.href="${ctx}/wap/my/memberInfo";
			      }else{
			      	alertN(data.msg);
			      }
	   },"json");
})

$(function(){

	resetProvince();
	$("[name='province']").change(function(){
		resetCity($(this).val());
	})
	$("[name='city']").change(function(){
		resetRegion($(this).val());
	})
})
	

function resetProvince(){
	$("[name='province']").html("<option value=\"\">选择省份</option>");
	$.getJSON("${ctx}/wap/regions/1",function(data){
		for(var i=0;i<data.length;i++){
			$("[name='province']").append("<option value=\""+data[i].id+"\" hassubinfo=\"true\">"+data[i].name+"</option>");
		}
		if($("[name='province']").val()=="")$("[name='province']").val('${user.province_id}');
		resetCity($("[name='province']").val());
	})
}
function resetCity(province_id){
	$("[name='city']").html("<option value=\"\">选择城市</option>");
	if(province_id!=""){
		$.getJSON("${ctx}/wap/regions/"+province_id,function(data){
    		for(var i=0;i<data.length;i++){
    			$("[name='city']").append("<option value=\""+data[i].id+"\" hassubinfo=\"true\">"+data[i].name+"</option>");
    		}
    		if($("[name='city']").val()=="")$("[name='city']").val('${user.city_id}');
    		resetRegion($("[name='city']").val());
		})
	}else{
		resetRegion($("[name='city']").val());
	}
}
function resetRegion(city_id){
	$("[name='region']").html("<option value=\"\">选择区县</option>");
		$.getJSON("${ctx}/wap/regions/"+city_id,function(data){
		for(var i=0;i<data.length;i++){
			$("[name='region']").append("<option value=\""+data[i].id+"\" hassubinfo=\"true\">"+data[i].name+"</option>");
		}
		if($("[name='region']").val()=="")$("[name='region']").val('${user.region_id}');
	})
}



</script>
</body>
</html>