<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
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
<title>微信支付</title>
<link href="${ctx}/statics/wap/css/base.css" rel="stylesheet" type="text/css">
<link href="${ctx}/statics/wap/css/index.css" rel="stylesheet" type="text/css">
<script type="text/javascript" src="${ctx}/statics/wap/js/common.js"></script>
<script type="text/javascript" src="${ctx}/statics/wap/js/jquery-1.8.3.min.js"></script>
<script type="text/javascript" src="${ctx}/statics/wap/js/jquery.form.js"></script>
<style>
.wePay{ width:100%; text-align:center; margin-top:2rem; position:fixed; bottom:0; }
.wePayBtn{ width:100%; color:#fff; background:#00EC40; border-radius:0; text-align:center; height:0.88rem; line-height:0.88rem;}
</style>
<style>
	body{font-size:16px; font-family: "Microsoft YaHei", "宋体", Verdana, Arial, Helvetica, sans-serif; }
    *{
        margin:0;
        padding:0;
    }
    ul,ol{
        list-style:none;
    }
    
    .hidden{
        display:none;
    }
    .new-btn-login-sp{
        padding: 1px;
        display: inline-block;
        width: 75%;
    }
    .new-btn-login {
        background-color: #02aaf1;
        color: #FFFFFF;
        font-weight: bold;
        border: none;
        width: 100%;
        height: 30px;
        border-radius: 5px;
        font-size: 16px;
    }
    #main{
        width:100%;
        margin:0 auto;
        font-size:14px;
    }
    .red-star{
        color:#f00;
        width:10px;
        display:inline-block;
    }
    .null-star{
        color:#fff;
    }
    .content{
        margin-top:5px;
    }
    .content dt{
        width:100px;
        display:inline-block;
        float: left;
        margin-left: 20px;
        color: #666;
        font-size: 15px;
        margin-top: 8px;
    }
    .content dd{
        margin-left:120px;
        margin-bottom:5px;
    }
    .content dd input {
        width: 85%;
        height: 28px;
        border: 0;
        -webkit-border-radius: 0;
        -webkit-appearance: none;
    }
    #foot{
        margin-top:10px;
        position: absolute;
        bottom: 15px;
        width: 100%;
    }
    .foot-ul{
        width: 100%;
    }
    .foot-ul li {
        width: 100%;
        text-align:center;
        color: #666;
    }
    .note-help {
        color: #999999;
        font-size: 12px;
        line-height: 130%;
        margin-top: 5px;
        width: 100%;
        display: block;
    }
    #btn-dd{
        margin: 20px;
        text-align: center;
    }
    .foot-ul{
        width: 100%;
    }
    .one_line{
        display: block;
        height: 1px;
        border: 0;
        border-top: 1px solid #eeeeee;
        width: 100%;
        margin-left: 20px;
    }
    .am-header {
        display: -webkit-box;
        display: -ms-flexbox;
        display: box;
        width: 100%;
        position: relative;
        padding: 7px 0;
        -webkit-box-sizing: border-box;
        -ms-box-sizing: border-box;
        box-sizing: border-box;
        background: #14941E;
        height: 50px;
        text-align: center;
        -webkit-box-pack: center;
        -ms-flex-pack: center;
        box-pack: center;
        -webkit-box-align: center;
        -ms-flex-align: center;
        box-align: center;
    }
    .am-header h1 {
        -webkit-box-flex: 1;
        -ms-flex: 1;
        box-flex: 1;
        line-height: 18px;
        text-align: center;
        font-size: 15px;
        font-weight: 300;
        color: #fff;
    }
    .main{
    	 padding-left:5%;
    	 line-height:30px;
    	 font-size:14px;
    }
    .form_btn{
    	width:90%;
    	box-sizing:border-box;
    	margin:0 5%;
    	padding:8px 5px;
    	background:#ddd; 
    	border-radius:5px;
    	color:#fff;
    	font-family: "Microsoft YaHei", "宋体", Verdana, Arial, Helvetica, sans-serif;
    }
</style>
</head>
<body>
<body style="background: #eeeeee;">
	<div id="loading"></div>
	<div class="main" style="height: 30%">
		<div class="payway_topcontent" style="border-bottom: none;margin-top: 40px">
			<div class="payway_topline">
				<span class="payway_span1">订单编号：</span><span class="payway_span2">${sn}</span>
			</div>
			<div class="payway_topline">
				<span class="payway_span1">订单总额：</span><span class="payway_span2">￥<fmt:formatNumber value="${total}" pattern="0.00" /></span>
			</div>
		</div>

	</div>
	 <button class="form_btn" onclick="callpay()" style="background-color: #00EC40;border: 1px solid #00EC40;margin-top: 2rem">确认支付</button>
         <form id="wxForm" method="post" target="_blank" style="display: none">
			<input type="hidden" id="out_trade_no" name="out_trade_no" value="${sn}"/>
			<input type="hidden" id="code" name="code" value="${code}"/>
         </form>
<script type="text/javascript" src="${ctx}/statics/wap/layui/layui.js"></script>
<script type="text/javascript" src="${ctx}/statics/wap/js/alerts.js"></script>
<script type="text/javascript">

	
 function callpay(){
  $.post("${ctx}/shopapi/payValid/${order_id}",function(data){//支付验证
    if(data.code=="0"){
  		var options = {
			url : "${ctx}/pay/wxpay/true_pay",
			type : "post",
			dataType: "json",
			success : function(data) {
				if(data.code=="0"){
					WeixinJSBridge.invoke('getBrandWCPayRequest',{
						"appId" : data.appId,
						"timeStamp" : data.timeStamp, 
						"nonceStr" : data.nonceStr, 
						"package" : data.packageName,
						"signType" : data.signType, 
						"paySign" : data.paySign
			          }, function(res) {
						WeixinJSBridge.log(res.err_msg);
						if(res.err_msg == "get_brand_wcpay_request:ok"){ 
							alertN("微信支付成功!");
			            	location.href="${ctx}/wap/shop/orderList";
			            }else if(res.err_msg == "get_brand_wcpay_request:cancel"){  
			                alertN("用户取消支付!");
			                setTimeout("javascript:location.href='${ctx}/pay/wxpay/topay?order_id=${order_id}'",2000);
			            }else{  
			    		   alertN("支付失败!错误信息:"+res.err_msg);
			    		   setTimeout("javascript:location.href='${ctx}/pay/wxpay/topay?order_id=${order_id}'",2000);
			            }  
			          });
				}else{
					 alertN("支付失败!");
					 setTimeout("javascript:location.href='${ctx}/pay/wxpay/topay?order_id=${order_id}'",2000);
				}
				//清空file内容
			}
		};
		$("#wxForm").ajaxSubmit(options);
	}else if(data.code=="-2"){
	   alertN("卡券已经被使用，订单已取消");
	}else if(data.code=="-3"){
	    alertN("直售订单，必须先设置推荐人的提成身份等级");
	}else if(data.code=="-4"){
	    alertN("该部门必须要有一个提成身份等级为1等员工");
	}else if(data.code=="-5"){
	    alertN("推荐人不能为空");
	}else if(data.code=="-6"){
	    alertN("推荐人或者推荐人关联人必须是绩效部门");
	}else if(data.code=="-7"){
	    alertN("会员积分不足");
	}else if(data.code=="1"){
	     alertN("登陆超时");
	}else{
	   alertN("系统繁忙，请稍后再试");
	}
	},"json")
}

</script>
</body>
</html>
