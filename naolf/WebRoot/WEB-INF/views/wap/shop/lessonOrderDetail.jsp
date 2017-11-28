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
<title>订单详情</title>
<link href="${ctx}/statics/wap/css/base.css" rel="stylesheet" type="text/css">
<link href="${ctx}/statics/wap/css/index.css" rel="stylesheet" type="text/css">
<script type="text/javascript" src="${ctx}/statics/wap/js/common.js"></script>
</head>
<body style="background:#f0f2f5;">
<div class="container mBtm91">
	<div class="confirm_order">
    	<div class="acceptInfo">
            <div class="address">
                <div class="acceptNP clearfix">
                    <div class="acceptName fl f28">购买人：${user.name }</div>
                    <div class="acceptPhone fr f24">${user.mobile }</div>
                </div>
                <div class="acceptAdd f24">地址：${user.province_name }${user.city_name }${user.region_name }&nbsp;</div>
                <img src="${ctx}/statics/wap/images/address.png" alt="" class="add_icon" />
            </div>
            <div class="addImgBox">
            	<img src="${ctx}/statics/wap/images/address_bk.png" alt="" class="address_bk" />
            </div>
        </div>
        <div class="orderDetail">
        	<div class="orderName bold f28">订单编号：${norder.sn}</div>
        	<div class="orderList clearfix">
            	<div class="orderPic fl">
                	<img src="${lesson.image }" alt="" class="orderImg" />
                </div>
            	<div class="orderDe fl">
                	<div class="title bold f28">${lesson.name }</div>
                	<div class="age f24 color_a5a4a8">适用年龄：${lesson.userage }周岁</div>
                    <c:if test="${lesson.type==1}"><div class="course f24 color_a5a4a8">训练课时：${lesson.period }天集中训练</div></c:if>
                    <c:if test="${lesson_type==0}"><div class="course f24 color_a5a4a8">训练课时：${lesson.period }天集中训练+${lesson.train }课时进阶训练</div></c:if>
                	<div class="price color_745398">
                    	<span class="f24">&yen;</span>
                        <span class="f30 bold">${lesson.price }</span>
                    </div>
                </div>
            </div>
            <div class="orderInfo pTop30">
<!--             	<div class="row"> -->
<!--                     <label>卡券号：</label> -->
<!--                     <label class="row_input_a">2378991000909091872</label> -->
<!--                 </div> -->
                <div class="row">
                    <label>购买人：</label>
                	<label class="row_input_a">${user.name }</label>
                </div>
                <div class="row">
                    <label>手机号：</label>
                	<label class="row_input_a">${user.mobile }</label>
                </div>
                <c:if test="${norder.status==1||norder.status==-2 }">
                 <div class="row">
                    <label>课程编号：</label>
                	<label class="row_input_a">${norder.ticket_sn }</label>
                </div>
                </c:if>
                <div class="rowTotal">
                	<span class="total f24">总计：</span>
        			<span class="money f24 color_745398">&yen;</span>
    				<span class="price f30 bold color_745398">${norder.price }</span>
                </div>
            </div>
            <div class="orderInfo">
            	<div class="row">
                    <label>下单时间：</label>
				    <label><fmt:formatDate value="${norder.create_date }" pattern="yyyy-MM-dd hh:mm:ss"/></label>
                </div>
                <div class="row">
                    <label>订单状态：</label>
                	<c:if test="${norder.status == 0 }"><label class="row_input_a">未付款</label></c:if>
                	<c:if test="${norder.status == 1 }"><label class="row_input_a">已付款</label></c:if>
                	<c:if test="${norder.status == -1 }"><label class="row_input_a">订单已取消</label></c:if>
                	<c:if test="${norder.status == -2 }"><label class="row_input_a">订单已退款</label></c:if>
                </div>
            </div>
        </div>
    </div>
    <div class="orderDetailSubBox">
        <c:if test="${norder.status==0 }"><input type="button" value="去付款" class="submitBtn" onClick="paymentOrder('${norder.id}')" /></c:if>
        <c:if test="${norder.status==1 }"><input type="button" value="去上课" class="submitBtn" onClick="study()" /></c:if>
    </div>
</div>
<script type="text/javascript">

		function paymentOrder(id){
		    javascript:location.href="${ctx}/wap/shop/lessonListOrder?lid="+id;
		}
		
		function study(){
		    javascript:location.href="${ctx}/wap/course/courseSearch";;
		}

</script>
</body>
</html>