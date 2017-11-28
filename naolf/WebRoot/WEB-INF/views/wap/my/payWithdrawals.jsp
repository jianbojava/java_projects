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
<title>提现</title>
<link href="${ctx}/statics/wap/css/base.css" rel="stylesheet" type="text/css">
<link href="${ctx}/statics/wap/css/index.css" rel="stylesheet" type="text/css">
<script type="text/javascript" src="${ctx}/statics/wap/js/jquery-1.8.3.min.js"></script>
<script type="text/javascript" src="${ctx}/statics/wap/js/jquery.raty.min.js"></script>
<script type="text/javascript" src="${ctx}/statics/wap/js/common.js"></script>
<script type="text/javascript" src="${ctx}/statics/wap/js/base.js"></script>
<body>
<div class="container mBtm91">
	<div class="confirm_order">
        <div class="orderDetail">
        	<div class="orderTotal borderBtm f28 clearfix">
            	<span class="f28  color_a5a4a8">提现积分</span>
                <div class="fr  color_745398">
                    <span class="f30 bold">${amount }分</span>
                    <input type="hidden" value="${amount }" id="amount"/>
                </div>
            </div>
<!--             <div class="orderMethod f28"> -->
<!--             	<a href="javascript:;" class="offlineMoney clearfix"> -->
<!--                     <div class="method_lf fl"> -->
<!--                         <img src="${ctx}/statics/wap/images/offlinePay.png" alt="" /> -->
<!--                     </div> -->
<!--                     <div class="method_mid fl"> -->
<!--                         <div class="f28">线下提现</div> -->
<!--                         <div class="f24 color_a5a4a8">线下安全支付</div> -->
<!--                     </div> -->
<!--                     <div class="method_rt fr"> -->
<!--                         <img src="${ctx}/statics/wap/images/arrow_right.png" alt="" /> -->
<!--                     </div> -->
<!--                 </a> -->
<!--             </div> -->
            <div class="orderMethod f28 zfbMoney">
            	<a class="clearfix " >
                    <div class="method_lf fl">
                        <img src="${ctx}/statics/wap/images/alipay.png" alt="" />
                    </div>
                    <div class="method_mid fl">
                        <div class="f28">提现至支付宝</div>
                        <div class="f24 color_a5a4a8">支付宝安全支付</div>
                    </div>
                    <div class="method_rt fr">
                        <img src="${ctx}/statics/wap/images/arrow_right.png" alt="" />
                    </div>
                </a>
            </div>
<!--             <div class="orderMethod f28"> -->
<!--             	<a href="javascript:;" class="clearfix"> -->
<!--                     <div class="method_lf fl"> -->
<!--                         <img src="${ctx}/statics/wap/images/weichat.png" alt="" /> -->
<!--                     </div> -->
<!--                     <div class="method_mid fl"> -->
<!--                         <div class="f28">微信支付</div> -->
<!--                         <div class="f24 color_a5a4a8">微信安全支付</div> -->
<!--                     </div> -->
<!--                     <div class="method_rt fr"> -->
<!--                         <img src="${ctx}/statics/wap/images/arrow_right.png" alt="" /> -->
<!--                     </div> -->
<!--                 </a> -->
<!--             </div> -->
            <div class="orderMethod f28 yhkMoney">
            	<a  class="clearfix " >
                    <div class="method_lf fl">
                        <img src="${ctx}/statics/wap/images/bankcard.png" alt="" />
                    </div>
                    <div class="method_mid fl">
                        <div class="f28">银行卡支付</div>
                        <div class="f24 color_a5a4a8">银行卡在线支付服务</div>
                    </div>
                    <div class="method_rt fr">
                        <img src="${ctx}/statics/wap/images/arrow_right.png" alt="" />
                    </div>
                </a>
            </div>
        </div>
    </div>
</div>
<script type="text/javascript" src="${ctx}/statics/wap/layui/layui.js"></script>
<script type="text/javascript" src="${ctx}/statics/wap/js/alerts.js"></script>
<script type="text/javascript">

  $(".yhkMoney").click(function(){
    $(".yhkMoney").css("pointer-events","none");//禁止div重复点击
				 $.post("${ctx}/myapi/Withdrawals",{type:0,amount:$("#amount").val()},function(data){
					if(data.code=="0"){
						layui.use(['layer', 'form'], function(){
						  var layer = layui.layer
						  ,form = layui.form();	
							 layer.open({
							  type: 1, 
							  content: data.msg, 
							  end:function(){
 								location.href="${ctx}/wap/my/my";
				    		  },
							  title:false,
							  closeBtn: 0,
							  btn:'确定',
							  btnAlign: 'c',
							  shadeClose:true,
							  skin: 'resetPwd_tip'
							});
							layer.config({
							  skin: 'resetPwd_tip'
							})
						});
					}else{
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
								  if(data.code=="-1") location.href="${ctx}/wap/my/my";
					    		  if(data.code=="1") location.href="${ctx}/wap/my/memberInfo";
							  },
							  skin: 'active_tip'
							});
							layer.config({
							  skin: 'active_tip'
							})
					});
					}
				},"json");
	});
		
			
			  $(".zfbMoney").click(function(){
			   $(".zfbMoney").css("pointer-events","none");//禁止div重复点击
				 $.post("${ctx}/myapi/Withdrawals",{type:1,amount:$("#amount").val()},function(data){
					if(data.code=="0"){
						layui.use(['layer', 'form'], function(){
						  var layer = layui.layer
						  ,form = layui.form();	
							 layer.open({
							  type: 1, 
							  content: data.msg, 
							  end:function(){
					    		javascript:location.href="${ctx}/wap/my/my";
				    		  },
							  title:false,
							  closeBtn: 0,
							  btn:'确定',
							  btnAlign: 'c',
							  shadeClose:true,
							  skin: 'resetPwd_tip'
							});
							layer.config({
							  skin: 'resetPwd_tip'
							})
						});
					}else{
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
					    		  if(data.code=="-1") location.href="${ctx}/wap/my/my";
					    		  if(data.code=="1") location.href="${ctx}/wap/my/memberInfo";
							  },
							  skin: 'active_tip'
							});
							layer.config({
							  skin: 'active_tip'
							})
					});
					}
				},"json");
	});
			 
		
</script>
</body>
</html>
