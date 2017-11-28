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
<title>卡券升级</title>
<link href="${ctx}/statics/wap/css/base.css" rel="stylesheet" type="text/css">
<link href="${ctx}/statics/wap/css/index.css" rel="stylesheet" type="text/css">
<script type="text/javascript" src="${ctx}/statics/wap/js/jquery-1.8.3.min.js"></script>
<script type="text/javascript" src="${ctx}/statics/wap/js/common.js"></script>
</head>
<body>
<div class="container mBtm95">
	<div class="forgetPwd">
    	<div class="info">选择学习券</div>
    	<div class="goodCont clearfix">
	    	<c:forEach items="${utlist}" var="ut">
	    	   <div class="goods_list">
	            	<a href="javascript:;" id="${ut.id}" onclick="upgrade('${ut.id}')">
	                    <img src="${ut.image}" class="goods_img" alt="" />
	                    <p class="title">${ut.sn}
	                       <c:if test="${ut.used==0}">未使用</c:if>
	                       <c:if test="${ut.used==1}">已使用</c:if>
	                    </p>
	                </a>
	            </div>
	    	</c:forEach>
    		
    	</div>
    </div>
</div>
<script type="text/javascript" src="${ctx}/statics/wap/layui/layui.js"></script>
<script type="text/javascript" src="${ctx}/statics/wap/js/alerts.js"></script>
<script type="text/javascript">
/**
layui.use(['layer', 'form'], function(){
  var layer = layui.layer
  ,form = layui.form();	
  
  $(".goods_list a").click(function(){
		var user_ticket_id2=$(this).attr("id");
		alert(user_ticket_id2);
		return;
		$.post("${ctx}/myapi/upgrade",{"user_ticket_id1":'${ut_id}',"user_ticket_id2":user_ticket_id2},function(data){
		  if(data.code=="0"){
		     layer.open({
			  type: 1, 
			  content: data.msg, 
			  title:false,
			  closeBtn: 0,
			  time:1500,
			  end:function(){
	    		javascript:location.href="${ctx}/wap/my/myCardList";
			  },
			  skin: 'active_tip'
			});
			layer.config({
			  skin: 'active_tip'
			})
		  }else{
		    alertN(data.msg);
		  }
		},"json");
	});
});
**/
function upgrade(ut_id2){
   layui.use(['layer', 'form'], function(){
			  var layer = layui.layer
			  ,form = layui.form();	
					layer.open({
					type: 1,  
					content:'确定立即升级',
					title:false,
					closeBtn: 0,
					btn:['取消','确定'],
			        btn2:function(index){
					   $.post("${ctx}/myapi/upgrade",{"user_ticket_id1":'${ut_id}',"user_ticket_id2":ut_id2},function(data){
					      if(data.code=="0"){
					        alertN("升级成功");
					        setTimeout("javascript:location.href='${ctx}/wap/my/myCardList'",2500);
					      }else{
					        alertN(data.msg);
					        setTimeout("javascript:location.reload();",2500);
					      }
					   },"json");
					},
					btnAlign: 'c',
					shadeClose:true,
					skin: 'resetPwd_tip'
					});
				});

}
</script> 
</body>
</html>