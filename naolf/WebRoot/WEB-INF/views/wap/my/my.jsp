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
<title>我的</title>
<link href="${ctx}/statics/wap/css/base.css" rel="stylesheet" type="text/css">
<link href="${ctx}/statics/wap/css/index.css" rel="stylesheet" type="text/css">
<script type="text/javascript" src="${ctx}/statics/wap/js/jquery-1.8.3.min.js"></script>
<script src="${ctx}/statics/wap/js/jquery.form.js"></script>
<script type="text/javascript" src="${ctx}/statics/wap/js/common.js"></script>
</head>
<body style="background:#f0f2f5;">
<div class="container">
	<div class="my">
    	<div class="my_head">
        	<div class="head">
        	   <form id="uploadForm" enctype="multipart/form-data" method="post">
            	<input type="file" name="file" class="headFile" onchange="uploadHead(this)" />
          		<c:if test="${user.head_img == null and empty user.head_img}"><img class="headimg" src="${ctx}/statics/wap/images/my_head.png" alt="" /></c:if>
       			<c:if test="${user.head_img != null and not empty user.head_img}"><img class="headimg" src="${user.head_img}" alt="" /></c:if>
                </form>
                <div class="loginInfo f24">
          			<c:if test="${user.id == null and empty user.id}"><a href="${ctx}/wap/shop/orderList">登录/注册</a></c:if>
          			<c:if test="${user.id != null and not empty user.id}"><span style="color:#fff">${user.username },欢迎你</span><br><a href="${ctx}/wap/logout" style="color:#D2B48C">退出</a></c:if>
                </div>  
	               </div>
               </div>
	      <div>
        </div>
        <div class="my_body">
        	<div class="body_bk">
            	<img src="${ctx}/statics/wap/images/my_bk.png" alt="" />
            </div>
            <ul class="body_con clearfix">
            	<li class="body_icon">
                	<a href="${ctx}/wap/my/point?BKZ_SCORE=1">
                        <img src="${ctx}/statics/wap/images/my_1.png" alt="" />
                        <p>未结业积分</p>
                    </a>
                </li>
                <li class="body_icon">
                	<a href="${ctx}/wap/my/point?KZ_SCORE=1">
                        <img src="${ctx}/statics/wap/images/my_2.png" alt="" />
                        <p>已结业积分</p>
                    </a>
                </li>
                <li class="body_icon">
                	<a href="${ctx}/wap/my/point?KD_SCORE=1">
                        <img src="${ctx}/statics/wap/images/my_3.png" alt="" />
                        <p>可提现积分</p>
                    </a>
                </li>
                <li class="body_icon">
                	<a href="${ctx}/wap/my/point?record=1">
                        <img src="${ctx}/statics/wap/images/my_4.png" alt="" />
                        <p>已提现记录</p>
                    </a>
                </li>
                <li class="body_icon">
                	<a href="${ctx}/wap/shop/orderList">
                        <img src="${ctx}/statics/wap/images/my_5.png" alt="" />
                        <p>订单</p>
                    </a>
                </li>
                <li class="body_icon">
                	<a href="${ctx}/wap/shop/orderList_pay">
                        <img src="${ctx}/statics/wap/images/my_6.png" alt="" />
                        <p>已付款</p>
                    </a>
                </li>
                <li class="body_icon">
                	<a href="${ctx}/wap/shop/orderList_noPay">
                        <img src="${ctx}/statics/wap/images/my_7.png" alt="" />
                        <p>待付款</p>
                    </a>
                </li>
                <li class="body_icon">
                	<a href="${ctx}/wap/my/memberInfo">
                        <img src="${ctx}/statics/wap/images/my_8.png" alt="" />
                        <p>信息</p>
                    </a>
                </li>
                <li class="body_icon">
                	<a href="${ctx}/wap/my/myCardList">
                        <img src="${ctx}/statics/wap/images/my_9.png" alt="" />
                        <p>卡券</p>
                    </a>
                </li>
            </ul>
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
<script type="text/javascript" src="${ctx}/statics/wap/layui/layui.js"></script>
<script type="text/javascript" src="${ctx}/statics/wap/js/alerts.js"></script>
<script type="text/javascript">


$(".login_btn").click(function(){
   $.post("${ctx}/wapapi/login",{"number":$("#number").val(),"password":$("#password").val()},function(data){
      if(data.code=="0"){
           javascript:location.href='${ctx}/wap/shop/goodsList';
      }else{
    	alertN(data.msg);
		}
   },"json");
})

function uploadHead(o){
    	var fileName=$(o).val();
    	var suffix=fileName.substring(fileName.lastIndexOf(".")+1);
		if(fileName==""){
			alertN('请选择上传的图片');
	  		return false;
		}
		if(!(suffix=="PNG"||suffix=="png"||suffix=="jpg"||suffix=="JPG"||suffix=="jpeg"||suffix=="JPEG"||suffix=="gif"||suffix=="GIF")){
			alertN('图片格式不支持');
	  		return false;
		}
		var options = {
			url : "${ctx}/myapi/updatehead",
			type : "post",
			dataType: "json",
			success : function(data) {
				if(data.code=="0"){
					$(".headimg").attr("src",data.msg);
				}else{
					alertN("上传失败");
				}
			}
		};
		$("#uploadForm").ajaxSubmit(options);
		return false;
	}


</script>
</body>
</html>

