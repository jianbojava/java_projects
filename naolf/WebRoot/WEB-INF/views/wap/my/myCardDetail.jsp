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
<title>卡券查询</title>
<link href="${ctx}/statics/wap/css/base.css" rel="stylesheet" type="text/css">
<link href="${ctx}/statics/wap/css/index.css" rel="stylesheet" type="text/css">
<script type="text/javascript" src="${ctx}/statics/wap/js/common.js"></script>
</head>
<body>
<div class="container">
	<div class="confirm_order">
        <div class="orderDetail">
            <div class="cardRow f28 clearfix">
                <div class="cardImg fl">
                    <img src="${ctx}/statics/wap/images/card_person.png" alt="" />
                </div>
                <div class="method_mid fl">
                    <div class="f28">激活人</div>
                </div>
                <div class="color_a5a4a8 fr">${used_name}</div>
            </div>
            <div class="cardRow f28 clearfix">
                <div class="cardImg fl">
                    <img src="${ctx}/statics/wap/images/card_time.png" alt="" />
                </div>
                <div class="method_mid fl">
                    <div class="f28">激活时间</div>
                </div>
                <c:if test="${userTicket.used==1}"><div class="method_mid color_a5a4a8 fr"><fmt:formatDate value="${userTicket.used_date}" pattern="yy/MM/dd HH:mm"/></div></c:if>
                <c:if test="${userTicket.used==0}"><div class="method_mid color_a5a4a8 fr">尚未激活</div></c:if>
                <c:if test="${userTicket.used==2}"><div class="method_mid color_a5a4a8 fr">已作废</div></c:if>
            </div>
            <c:if test="${canup==1}">
            <div class="upGrade">
            	<input type="button" value="升级" onClick="toup()"/>
            </div>
            </c:if>
        </div>
    </div>
</div>
<script type="text/javascript" src="${ctx}/statics/wap/js/alerts.js"></script>
<script type="text/javascript" src="${ctx}/statics/wap/layui/layui.js"></script>
<script type="text/javascript">
function toup(){
  location.href="${ctx}/wap/my/cardUpGrade?ut_id=${userTicket.id}";
}

</script>
</body>
</html>
