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
<title>订单列表</title>
<link href="${ctx}/statics/wap/css/base.css" rel="stylesheet" type="text/css">
<link href="${ctx}/statics/wap/css/index.css" rel="stylesheet" type="text/css">
<script type="text/javascript" src="${ctx}/statics/wap/js/common.js"></script>
<script type="text/javascript" src="${ctx}/statics/wap/js/jquery-1.8.3.min.js"></script>
<script type="text/javascript" src="${ctx}/statics/wap/js/alerts.js"></script>
<script type="text/javascript" src="${ctx}/statics/wap/layui/layui.js"></script>
</head>
<body>
<div class="container mBtm95">
	<div class="order_list_con confirm_order">
    	<ul class="tabs clearfix">
        	<li class="active"><a href="${ctx}/wap/shop/orderList">全部订单</a></li>
        	<li><a href="${ctx}/wap/shop/orderList_pay">已经付款</a></li>
        	<li><a href="${ctx}/wap/shop/orderList_noPay">等待付款</a></li>
        </ul>
        <div class="orderListBox pTop90">
        <c:forEach items="${listL}" var="l">
            <div class="orderDetail">
                <div class="orderName f28">
                    <div class="clearfix">
                        <div class="bold fl">订单编号：${l.sn }</div>
                        <c:if test="${l.status==0 }"><div class="color_a5a4a8 fr">等待买家付款</div></c:if>
                        <c:if test="${l.status==1 }"><div class="color_a5a4a8 fr">买家已付款</div></c:if>
                        <c:if test="${l.status==-1 }"><div class="color_a5a4a8 fr">买家已取消</div></c:if>
                        <c:if test="${l.status==-2 }"><div class="color_a5a4a8 fr">已退款</div></c:if>
                        
                    </div>
                </div>
                <div class="orderListBlock clearfix">
                    <div class="orderList clearfix">
                    	<a href="${ctx}/wap/shop/lessonOrderDetail/${l.id}">
                            <div class="orderPic fl">
                                <img src="${l.lesson_image }" alt="" class="orderImg" />
                            </div>
                            <div class="orderDe fl">
                                <div class="title bold f28">${l.lesson_name }</div>
                                <div class="age f24 color_a5a4a8">适用年龄：${l.lesson_userage }</div>
                                <div class="course f24 color_a5a4a8">训练课时：${l.lesson_period }</div>
                                <div class="price color_745398">
                                    <span class="f24">&yen;</span>
                                    <span class="f30 bold">${l.lesson_price }</span>
                                </div>
                            </div>
                        </a>
                    </div>
                    <div class="btnGroup fr">
                        <c:if test="${l.status==0 }"><input type="button" onclick="cancelL('${l.id}')" value="取消订单" class="btn btn_gray" /><input type="button" onclick="paymentL('${l.id}')" value="立即支付" class="btn btn_purple payment" /></c:if>
                        <c:if test="${l.status==1 }"><input type="button" value="去上课" onclick="study()" class="btn btn_purple" readonly/></c:if>
                    </div>
                </div>
            </div>
            </c:forEach>
        <c:forEach items="${listT}" var="t">
        <input type="hidden" value="${t.id }" id="ids"/>
            <div class="orderDetail">
                <div class="orderName f28">
                    <div class="clearfix">
                        <div class="bold fl">订单编号：${t.sn }</div>
                        <c:if test="${t.status==0 }"><div class="color_a5a4a8 fr">等待买家付款</div></c:if>
                        <c:if test="${t.status==1 }"><div class="color_a5a4a8 fr">买家已付款</div></c:if>
                        <c:if test="${t.status==-1 }"><div class="color_a5a4a8 fr">买家已取消</div></c:if>
                        <c:if test="${t.status==-2 }"><div class="color_a5a4a8 fr">已退款</div></c:if>
                    </div>
                </div>
                <div class="orderListBlock clearfix">
                    <div class="orderList clearfix">
                    	<a href="${ctx}/wap/shop/ticketOrderDetail/${t.id}">
                            <div class="orderPic fl">
                                <img src="${t.ticket_image }" alt="" class="orderImg" />
                            </div>
                            <div class="orderDe fl">
                                <div class="title bold f28">${t.ticket_name }</div>
                                <div class="price color_745398">
                                    <span class="f24">&yen;</span>
                                    <span class="f30 bold">${t.ticket_price }</span>
                                </div>
                            </div>
                        </a>
                    </div>
                    <div class="btnGroup fr">
                        <c:if test="${t.status==0 }"><input type="button" onclick="cancelT('${t.id}')" value="取消订单" class="btn btn_gray" /><input type="button" onclick="paymentT('${t.id}')" value="立即支付" class="btn btn_purple" /></c:if>
                        <c:if test="${t.status==1 }"><input type="button" value="去上课" onclick="studys()" class="btn btn_purple" readonly/></c:if>
                    </div>
                </div>
            </div>
            </c:forEach>
        </div>
    </div> 
    
    <div class="footer clearfix">
    	<div class="nav">
        	<a href="${ctx}/wap/shop/goodsList">
                <div class="navIcon">
                    <img src="${ctx}/statics/wap/images/home.png" alt="">
                </div>
                <div class="navName">商城</div>
            </a>
        </div>
        <div class="nav">
        	<a href="${ctx}/wap/course/cardCouponSearch">
                <div class="navIcon">
                    <img src="${ctx}/statics/wap/images/course.png" alt="">
                </div>
                <div class="navName">课程</div>
            </a>
        </div>
        <div class="nav">
        	<a href="${ctx}/wap/message/message">
                <div class="navIcon">
                    <img src="${ctx}/statics/wap/images/msg.png" alt="">
                </div>
                <div class="navName">消息</div>
            </a>
        </div>
        <div class="nav">
        	<a href="${ctx}/wap/my/my">
                <div class="navIcon">
                    <img src="${ctx}/statics/wap/images/my_active.png" alt="">
                </div>
                <div class="navName color_745398">我的</div>
            </a>
        </div>
    </div>
      
</div>
<script type="text/javascript">

		
		function studys(){
			javascript:location.href="${ctx}/wap/course/cardCouponSearch";
		}
		
		function study(){
			javascript:location.href="${ctx}/wap/course/courseSearch";
		}
		
		function paymentL(id){
			location.href="${ctx}/pay/wxpay/topay?order_id="+id;
		}

		function paymentT(id){
		   	location.href="${ctx}/pay/wxpay/topay?order_id="+id;
		}
		
		function cancelL(id){
					$.post("${ctx}/shopapi/cancelOrder",{"id":id},function(data){
				      if(data.code=="0"){ 
				     layui.use(['layer', 'form'], function(){
					  var layer = layui.layer
					  ,form = layui.form();	
						  layer.open({
							  type: 1, 
							  content: data.msg, 
							  title:false,
							  closeBtn: 0,
							  time:1500,
							  end:function(){
					    		javascript:location.href="${ctx}/wap/shop/orderList";
							  },
							  skin: 'active_tip'
							});
							layer.config({
							  skin: 'active_tip'
							})
					});
				      }else{
				    	  alertN(data.msg);
				      }
				   },"json");
			      	
		}
		
				function cancelT(id){
					$.post("${ctx}/shopapi/cancelOrder",{"id":id},function(data){
				      if(data.code=="0"){ 
				        layui.use(['layer', 'form'], function(){
					  var layer = layui.layer
					  ,form = layui.form();	
						  layer.open({
							  type: 1, 
							  content: data.msg, 
							  title:false,
							  closeBtn: 0,
							  time:1500,
							  end:function(){
					    		javascript:location.href="${ctx}/wap/shop/orderList";
							  },
							  skin: 'active_tip'
							});
							layer.config({
							  skin: 'active_tip'
							})
					});
				      }else{
				    	  alertN(data.msg);
				      }
				   },"json");
			      	
		}
</script>
</body>
</html>
