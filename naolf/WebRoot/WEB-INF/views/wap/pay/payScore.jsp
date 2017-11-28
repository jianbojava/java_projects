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
<title>充值</title>
<link href="${ctx}/statics/wap/css/base.css" rel="stylesheet" type="text/css">
<link href="${ctx}/statics/wap/css/index.css" rel="stylesheet" type="text/css">
<script type="text/javascript" src="${ctx}/statics/wap/js/jquery-1.8.3.min.js"></script>
<script type="text/javascript" src="${ctx}/statics/wap/js/jquery.form.js"></script>
<script type="text/javascript" src="${ctx}/statics/wap/js/common.js"></script>
</head>
<body>
<div class="container mBtm91">
	<div class="confirm_order">
        <div class="orderDetail">
          <div class="orderTotal borderBtm f28 clearfix">
            	<span class="f28  color_a5a4a8">订单金额</span>
                <div class="fr  color_745398">
                    <span class="f24">&yen;</span>
                    <span class="f30 bold">${total }</span>
                </div>
            </div>
            <div class="orderMethod f28" onclick="callalipay()">
            	<a href="javascript:;" class="clearfix">
                    <div class="method_lf fl">
                        <img src="${ctx}/statics/wap/images/alipay.png" alt="" />
                    </div>
                    <div class="method_mid fl">
                        <div class="f28">支付宝充值</div>
                        <div class="f24 color_a5a4a8">支付宝安全充值</div>
                    </div>
                    <div class="method_rt fr">
                        <img src="${ctx}/statics/wap/images/arrow_right.png" alt="" />
                    </div>
                </a>
            </div>
            <div class="orderMethod f28" onclick="callpay()">
            	<a href="javascript:;" class="clearfix">
                    <div class="method_lf fl">
                        <img src="${ctx}/statics/wap/images/weichat.png" alt="" />
                    </div>
                    <div class="method_mid fl">
                        <div class="f28">微信充值</div>
                        <div class="f24 color_a5a4a8">微信安全充值</div>
                    </div>
                    <div class="method_rt fr">
                        <img src="${ctx}/statics/wap/images/arrow_right.png" alt="" />
                    </div>
                </a>
            </div>
            <div class="orderMethod f28" style="display: none">
            	<a href="javascript:;" class="clearfix">
                    <div class="method_lf fl">
                        <img src="${ctx}/statics/wap/images/bankcard.png" alt="" />
                    </div>
                    <div class="method_mid fl">
                        <div class="f28">银行卡充值</div>
                        <div class="f24 color_a5a4a8">银行安全充值</div>
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
 var appid = "${appid}";
 var redirect_uri = "${redirect_uri}";
function callalipay(){
        location.href="${ctx}/pay/alipay/topayscore/"+'${sn}';
	}
	
 function callpay(){
  //location.href="${ctx}/pay/wxpay/paywxscore?sn=${sn}";
  location.href="https://open.weixin.qq.com/connect/oauth2/authorize?appid=" + appid + "&redirect_uri="+redirect_uri+"?sn="+'${sn}'+ "&response_type=code&scope=snsapi_base&state=STATE#wechat_redirect";
}

</script>
</body>
</html>
