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
<title>积分详情</title>
<link href="${ctx}/statics/wap/css/base.css" rel="stylesheet" type="text/css">
<link href="${ctx}/statics/wap/css/index.css" rel="stylesheet" type="text/css">
<script type="text/javascript" src="${ctx}/statics/wap/js/jquery-1.8.3.min.js"></script>
<script type="text/javascript" src="${ctx}/statics/wap/js/common.js"></script>
</head>
<body style="background:#e4e4e4;">
<div class="container">
	<div class="point">
        <div class="pointListDetail">
        	<table>
                <tbody>
                	<tr>
                    	<td>积分</td>
                    	<c:if test="${type==0 }"><td>${emp.BKZ_SCORE }</td></c:if>
                    	<c:if test="${type==1 }"><td>${emp.KZ_SCORE }</td></c:if>
                    	<c:if test="${type==2 }"><td>${emp.KD_SCORE }</td></c:if>
                    </tr>
                </tbody>
            </table>
            <table>
                <tbody>
                	<tr>
                    	<td>时间</td>
                    	<td><fmt:formatDate value="${emp.create_date }" pattern="yy/MM/dd HH:mm"/></td>
                    </tr>
                    <tr>
                    	<td>交易类型</td>
                    	<td>${emp.TRANSFER_name }</td>
                    </tr>
<!--                     <tr> -->
<!--                     	<td>积分转换类型</td> -->
<!--                     	<td>订单推荐</td> -->
<!--                     </tr> -->
                </tbody>
            </table>
            <c:if test="${emp.TRANSFER_name!='提现' }">
            <table>
                <tbody>
                	<tr>
                    	<td>订单号</td>
                    	<td>${emp.ORDER_NO }</td>
                    </tr>
                    <tr>
                    	<td>订单金额</td>
                    	<td>${emp.pay_amount }</td>
                    </tr>
                    <tr>
                    	<td>购买人</td>
                    	<td>${emp.username }</td>
                    </tr>
                    <tr>
                    	<td>购买人ID</td>
                    	<td>${emp.user_number }</td>
                    </tr>
                    <tr>
                    	<td>推荐人ID</td>
                    	<td>${emp.buy_num }</td>
                    </tr>
                    <c:if test="${emp.grade1_refer!=null }">
                    <tr>
                    	<td>关联推荐人1</td>
                    	<td>${emp.grade1_refer }</td>
                    </tr>
                    </c:if>
                    <c:if test="${emp.grade2_refer!=null }">
                    <tr>
                    	<td>关联推荐人2</td>
                    	<td>${emp.grade2_refer }</td>
                    </tr>
                    </c:if>
                    <c:if test="${emp.grade3_refer!=null }">
                    <tr>
                    	<td>关联推荐人3</td>
                    	<td>${emp.grade3_refer }</td>
                    </tr>
                    </c:if>
                    <c:if test="${emp.grade4_refer!=null }">
                    <tr>
                    	<td>关联推荐人4</td>
                    	<td>${emp.grade4_refer }</td>
                    </tr>
                    </c:if>
                </tbody>
            </table>
            </c:if>
        </div>
    </div>
</div>
</body>
</html>