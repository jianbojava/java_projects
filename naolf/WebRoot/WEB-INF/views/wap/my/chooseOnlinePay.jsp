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
<title>收银台</title>
<link href="${ctx}/statics/wap/css/base.css" rel="stylesheet" type="text/css">
<link href="${ctx}/statics/wap/css/index.css" rel="stylesheet" type="text/css">
<link rel="stylesheet" href="${ctx}/statics/wap/css/switchery.css">
<script type="text/javascript" src="${ctx}/statics/wap/js/jquery-1.8.3.min.js"></script>
<script type="text/javascript" src="${ctx}/statics/wap/js/common.js"></script>
<script src="${ctx}/statics/wap/js/switchery.js"></script>
</head>
<body>
<div class="container mBtm91">
	<div class="confirm_order">
        <div class="orderDetail">
        	<div class="orderTotal borderBtm f28 clearfix">
            	<span class="f28  color_a5a4a8">在线支付</span>
                <div class="fr  color_745398">
                    <span class="f24">&yen;</span>
                    <span class="f30 bold">${price }</span>
                </div>
            </div>
            
            <div class="orderMethod borderBtm f28 clearfix" onclick="payjf()">
            	<a class="clearfix">
        			<label>使用积分</label>&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp
        			<div class="method_rt fr">
                        <img src="${ctx}/statics/wap/images/arrow_right.png" alt="" />
                    </div>
        		</a>
            </div>
            <div class="orderMethod f28" onclick="paywx()">
            	<a href="javascript:;" class="clearfix">
                    <div class="method_lf fl">
                        <img src="${ctx}/statics/wap/images/weichat.png" alt="" />
                    </div>
                    <div class="method_mid fl">
                        <div class="f28">微信支付</div>
                        <div class="f24 color_a5a4a8">微信安全支付</div>
                    </div>
                    <div class="method_rt fr">
                        <img src="${ctx}/statics/wap/images/arrow_right.png" alt="" />
                    </div>
                </a>
            </div>
            <div class="orderMethod f28" onclick="payali()">
            	<a href="javascript:;" class="clearfix">
                    <div class="method_lf fl">
                        <img src="${ctx}/statics/wap/images/alipay.png" alt="" />
                    </div>
                    <div class="method_mid fl">
                        <div class="f28">支付宝支付</div>
                        <div class="f24 color_a5a4a8">支付宝安全支付</div>
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
function payjf(){
  if(${canpay}>0){
     location.href="${ctx}/wap/my/upgradeByjf/${ut_id}";
  }else{
    alertN("您的可兑换积分不足");
    return;
  }
}
 var appid = "${appid}";
 var redirect_uri = "${redirect_uri}";
function paywx(){
  var ut_id='${ut_id}';
 location.href="https://open.weixin.qq.com/connect/oauth2/authorize?appid=" + appid + "&redirect_uri="+redirect_uri+"?ut_id="+ut_id + "&response_type=code&scope=snsapi_base&state=STATE#wechat_redirect";
 //location.href="${ctx}/pay/wxpay/paywxupgrade?ut_id=${ut_id}";
}
function payali(){
  $.post("${ctx}/myapi/upgradeValid/${ut_id}",function(data){//支付验证
            if(data.code=="0"){
               location.href="${ctx}/pay/alipay/topayupgrade/"+data.msg;
			}else{
			   alertN(data.msg);
			}
        },"json")
}
</script>
</body>
</html>
