<%
	/* *
	 *功能：支付宝手机网站支付接口调试入口页面
	 *版本：3.3
	 *日期：2012-08-17
	 *说明：
	 *以下代码只是为了方便商户测试而提供的样例代码，商户可以根据自己网站的需要，按照技术文档编写,并非一定要使用该代码。
	 */
%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<meta name="viewport"
	content="width=device-width, initial-scale=1.0, user-scalable=0, minimum-scale=1.0, maximum-scale=1.0">

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />

<script type="text/javascript" src="${ctx}/statics/wap/js/jquery-1.8.3.min.js"></script>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
	<head>
	<title>支付宝手机支付</title>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
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
<body text=#000000 bgColor="#ffffff" leftMargin=0 topMargin=4 >
<header class="am-header" >
        <h1>请点击右上角，选择在浏览器中打开完成支付</h1>
</header>
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
	 <button class="form_btn" style="background-color: #B4B4B4;border: 1px solid #B4B4B4">确认支付</button>
	</div>
<div id="main" style="display: none">
        <form name=alipayment action="${ctx}/pay/alipay/wxalipayapi" method=post >
            <div id="body" style="clear:left">
                <dl class="content">
					<dt>订单号：</dt>
					<dd>
						<input name="WIDout_trade_no" id="WIDout_trade_no" value="${sn}" />
					</dd>
                    			<hr class="one_line">
					<dt>订单名称：</dt>
					<dd>
						<input name="WIDsubject" id="WIDsubject" value="${sn }" />
					</dd>
                    			<hr class="one_line">
					<dt>付款金额：</dt>
					<dd>
						<input name="WIDtotal_fee" id="WIDtotal_fee" value="${total }" />
					</dd>
                    			<hr class="one_line">
					<dt>商品展示地址：</dt>
					<dd>
						<input name="WIDshow_url" id="WIDshow_url" />
					</dd>
                    			<hr class="one_line">
					<dt>订单描述：</dt>
					<dd>
						<input name="WIDbody" id="WIDbody" />
					</dd>
                    			<hr class="one_line">
					<dt>超时时间：</dt>
					<dd>
						<input name="WIDit_b_pay" id="WIDit_b_pay" />
					</dd>
                    			<hr class="one_line">
					<dt>钱包token：</dt>
					<dd>
						<input name="WIDextern_token" id="WIDextern_token" />
					</dd>
                    			<hr class="one_line">
                    <dt></dt>
                    <dd id="btn-dd">
                        <span class="new-btn-login-sp">
                            <button class="new-btn-login" type="submit" style="text-align:center;">确 认${is_login }</button>
                        </span>
                        <span class="note-help">如果您点击“确认”按钮，即表示您同意该次的执行操作。</span>
                    </dd>
                </dl>
            </div>
		</form>
        <div id="foot">
			<ul class="foot-ul">
				<li>
					支付宝版权所有 2015-2018 ALIPAY.COM 
				</li>
				
			</ul>
		</div>
	</div>
</body>
<script language="javascript">
var ua = navigator.userAgent.toLowerCase();
if(ua.match(/MicroMessenger/i)=="micromessenger") {//微信浏览器，不做任何操作

}else{//其他浏览器自动跳转至支付宝
   $(".new-btn-login").click();
}

</script>
</html>