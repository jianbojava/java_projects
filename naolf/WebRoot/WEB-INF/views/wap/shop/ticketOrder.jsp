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
<title>确认订单</title>
<link href="${ctx}/statics/wap/css/base.css" rel="stylesheet" type="text/css">
<link href="${ctx}/statics/wap/css/index.css" rel="stylesheet" type="text/css">
<link rel="stylesheet" href="${ctx}/statics/wap/css/switchery.css">
<script type="text/javascript" src="${ctx}/statics/wap/js/jquery-1.8.3.min.js"></script>
<script type="text/javascript" src="${ctx}/statics/wap/js/common.js"></script>
<script src="${ctx}/statics/wap/js/switchery.js"></script>
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
        	<input type="hidden" value="${ticket.id }" id="id"/>
	        	<div class="orderName bold f28">${ticket.name }</div>
	        	<div class="orderList clearfix">
	            	<div class="orderPic fl">
	                	<img src="${ticket.image }" alt="" class="orderImg" />
	                </div>
	            	<div class="orderDe fl">
	                	<div class="title bold f28">${ticket.name }</div>
	                	<div class="age f24 color_a5a4a8">${ticket.use_rule }</div>
	                	<div class="course f24 color_a5a4a8">&nbsp</div>
	                	<div class="price color_745398">
	                    	<span class="f24">&yen;</span>
	                        <span class="f30 bold">${ticket.price }</span>
	                    </div>
	                </div>
	            </div>
            <div class="orderInfo pTop30">
            
            	<div class="row">
                    <label>推荐人：</label> 
                	<input type="text" <c:if test="${user.refer_number!=null && not empty user.refer_number }"> readonly </c:if> placeholder="请输入分享人编码" value="${user.refer_number}" class="row_input_a" id="refer_number" />
                </div>
                <div class="row">
                    <label>卡券号：</label>
                	<input type="text" placeholder="请输入卡券号" class="row_input_a" id="sn" />
                </div>
                <div class="row">
        			<label>手机号：</label>
        			<input type="text" placeholder="请输入正确的手机号码" class="row_input" id="mobile" />
            		<input type="button" value="获取验证码" class="row_btn row_btn1 fr" id="btn" onclick="rows()" />
        		</div>
                <div class="row">
                    <label>验证码：</label>
                	<input type="text" placeholder="请输入验证码" class="row_input_a" id="code" />
                </div>
                <div class="rowTotal">
                	<span class="total f24">总计：</span>
        			<span class="money f24 color_745398">&yen;</span>
    				<span class="price f30 bold color_745398" value="${ticket.price }" id="price">${ticket.price }</span>
                </div>
            </div>
            <div class="orderInfo">
            	<div class="row">
        			<label>可用积分</label>
        			<label class="js-field none"></label>
                    <div class="a a1 fr"><input type="checkbox" class="js-switch" /></div>
        		</div>
        		<div id="score" style="display: none">
	            	<div class="row">
	        			<label>可兑换积分：</label>
	        			<input type="text" placeholder="最多${kd_score }" class="row_input" id="kd_score" onchange="validkd()" />
	        		</div>
	            	<div class="row">
	        			<label>已结业积分：</label>
	        			<input type="text" placeholder="最多${kz_score }" class="row_input" id="kz_score" onchange="validkz()" />
	        		</div>
        		</div>
        		
            </div>
        </div>
    </div>
    <div class="footer footer1">
    	<span class="total">合计：</span>
        <span class="money">&yen;</span>
    	<span class="price" id="pricess">${ticket.price }</span>
    	<input type="button" value="提交订单" class="buyBtn" />
    </div>
</div>
<script type="text/javascript" src="${ctx}/statics/wap/js/alerts.js"></script>
<script type="text/javascript" src="${ctx}/statics/wap/layui/layui.js"></script>
<script type="text/javascript">
var r = /^\+?[1-9][0-9]*$/;//正整数验证
var percent=parseInt(${score_percent});
function validkd(){
	  var inp_kd=$("#kd_score").val();
	  if(r.test(inp_kd)){
	    if(inp_kd>${kd_score}){
	       alertN("输入积分大于已有积分");
	       $("#kd_score").val("");
	       return;
	    }
	    var inp_kz=$("#kz_score").val();
	    if(inp_kz=="") inp_kz=0;
	    var to_pay='${ticket.price}'-((parseFloat(inp_kd)+parseFloat(inp_kz))/percent);
	    if(to_pay<0){alertN("使用积分不能超过卡券金额");$("#kd_score").val("");return;}
	    $("#pricess").html(to_pay)
	  }else{
	      alertN("请输入正整数");
	      $("#kd_score").val("");
	      return;
	  }
}

function validkz(){
	  var inp_kz=$("#kz_score").val();
	  if(r.test(inp_kz)){
	    if(inp_kz>${kz_score}){
	       alertN("输入积分大于已有积分");
	       $("#kz_score").val("");
	       return;
	    }
	    var inp_kd=$("#kd_score").val();
	    if(inp_kd=="") inp_kd=0;
	    var to_pay='${ticket.price}'-((parseFloat(inp_kd)+parseFloat(inp_kz))/percent);
	    if(to_pay<0){alertN("使用积分不能超过卡券金额");$("#kd_score").val("");return;}
	    $("#pricess").html(to_pay)
	  }else{
	      alertN("请输入正整数");
	       $("#kz_score").val("");
	       return;
	  }
}

	function rows(){         
	   $.post("${ctx}/wapapi/sms/ticketCode",{"mobile":$("#mobile").val(),"sn":$("#sn").val(),"refer_number":$("#refer_number").val()},function(data){
	      if(data.code=="0"){ 
	    	  time();
	    	  alertN(data.msg+data.data.codes);
	    	  //alertN(data.msg);
	      }else{
	    	  alertN(data.msg);
	      }
	   },"json");
	}

	$(".buyBtn").click(function(){
	var kd_score=$("#kd_score").val();
	   var kz_score=$("#kz_score").val();
	   if(kd_score==""){ kd_score=0};
	   if(kz_score==""){ kz_score=0};
	   var total_score=parseFloat((parseFloat(kd_score)+parseFloat(kz_score)));
	    if(total_score>0&&(total_score!=(parseFloat(${ticket.price})*percent))){
	       alertN("积分金额必须等于订单总额");
	       return;
	   }
	   
	   if($("#mobile").val()==""){
	        alertN("手机号不可以为空");
	       return;
	   }
	    if($("#code").val()==""){
	        alertN("验证码不可以为空");
	       return;
	   }
	   $(".buyBtn").attr("disabled",true);
	   $.post("${ctx}/shopapi/ticketOrder",{"kd_score":kd_score,"kz_score":kz_score,"mobile":$("#mobile").val(),"ticket_sn":$("#sn").val(),"conn_id":$("#id").val(),"code":$("#code").val(),"type":1,"refer_number":$("#refer_number").val()},function(data){
	      if(data.code=="0"||data.code=="2"){ //0提交成功,2支付成功
	                alertN(data.msg);
				  	if(data.code=="0"){//提交成功
				  	   	setTimeout("javascript:location.href='${ctx}/pay/wxpay/topay?order_id="+data.data.id+"'",2000);
				  	}
				  	if(data.code=="2"){//支付成功
				  	  setTimeout("javascript:location.href='${ctx}/wap/shop/orderList'",2000);
				  	}
	      }else{
	      	alertN(data.msg);
	      	$(".buyBtn").attr("disabled",false);
	      }
	   },"json");
	})



var elem = document.querySelector('.js-switch');
var switchery = new Switchery(elem, { size: 'small', color: '#745398' });

elem.onchange = function() {
	var none=$(".js-field").hasClass("none");
 	if(none){
		$(".js-field").show();	
		$(".js-field").removeClass("none");
		$("#score").css("display","");
	}else{
		$(".js-field").hide();	
		$(".js-field").addClass("none");
		$("#score").css("display","none");
		$("#kd_score").val("");
	    $("#kz_score").val("");
	}
};


var wait=60;
document.getElementById("btn").disabled = false;   
function time() {
	var o=document.getElementById("btn");
	if (wait == 0) {
		o.removeAttribute("disabled");           
		o.value="重新发送";
		wait = 60;
	} else {
		o.setAttribute("disabled", true);
		o.value="发送中(" + wait + ")";
		wait--;
		var t = setTimeout(function() {
			time()
		},
		1000)
	}
}
</script>
</body>
</html>