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
<title>会员信息</title>
<link href="${ctx}/statics/wap/css/base.css" rel="stylesheet" type="text/css">
<link href="${ctx}/statics/wap/css/index.css" rel="stylesheet" type="text/css">
<script type="text/javascript" src="${ctx}/statics/wap/js/jquery-1.8.3.min.js"></script>
<script type="text/javascript" src="${ctx}/statics/wap/js/common.js"></script>
<script type="text/javascript" src="${ctx}/statics/wap/js/base.js"></script>
</head>
<body style="background:#f0f2f5;">
<div class="container mBtm95">
	<div class="forgetPwd">
    	<div class="mInfoBlock">
        	<div class="info">基本信息</div>
            <div class="minBlock mBtm10">
                <div class="row row_fff userName">
                    <label>用户名：</label>
                    <input type="text" placeholder="请输入用户名" class="row_input_padRt uName" value="${user.username }" disabled />
                    <img src="${ctx}/statics/wap/images/arrow_right.png" alt="" class="arrowRt fr" />
                </div>
                <div class="row row_fff" onClick="javascript:location.href='${ctx}/wap/my/memberUpdatePwd'">
                    <label>密码：</label>
                    <input type="password" placeholder="******" value="******" class="row_input_padRt" disabled />
                    <img src="${ctx}/statics/wap/images/arrow_right.png" alt="" class="arrowRt fr" />
                </div>
                <div class="row row_fff">
                    <label>身份ID：</label>
                    <input type="text" class="row_input_padRt" disabled value="${user.number }" />
                </div>
                <div class="row row_fff">
                    <label>身份类型：</label>
                    <c:if test="${user.user_type == 0 }"><input type="text" class="row_input_padRt" disabled value="员工" /></c:if>
                    <c:if test="${user.user_type == 1 }"><input type="text" class="row_input_padRt" disabled value="合伙人" /></c:if>
                    <c:if test="${user.user_type == 2 }"><input type="text" class="row_input_padRt" disabled value="会员" /></c:if>
                </div>
            </div>
            <div class="minBlock mBtm10">
                <div class="row row_fff">
                    <label>姓名：</label>
                    <input type="text" class="row_input_padRt" value="${user.name }" disabled />
                </div>
                <div class="row row_fff" onClick="javascript:location.href='${ctx}/wap/my/memberUpdatePhone'">
                <a href="${ctx}/wap/my/memberUpdatePhone">
                    <label>手机：</label>
                    <input type="text" placeholder="${user.mobile }" class="row_input_padRt"  />
                    <img src="${ctx}/statics/wap/images/arrow_right.png" alt="" class="arrowRt fr" />
				</a>
                </div>
                <div class="row row_fff mail">
                    <label>邮箱：</label>
                    <c:if test="${user.email == null || empty user.email  }"><input type="text" placeholder="设置常用邮箱"class="row_input_padRt mailBox" disabled />
                    <img src="${ctx}/statics/wap/images/arrow_right.png" alt="" class="arrowRt fr" /></c:if>
                    <c:if test="${user.email != null and not empty user.email  }"><input type="text" value="${user.email }" class="row_input_padRt mailBox" disabled value="" />
                    <img src="${ctx}/statics/wap/images/arrow_right.png" alt="" class="arrowRt fr" /></c:if>
                </div>
                <div class="row row_fff" onClick="javascript:location.href='${ctx}/wap/my/memberUpdateAddress'">
                    <label>地址：</label>
                    <input type="text" <c:if test="${user.province_name==null and user.city_name==null and user.region_name==null and user.address==null }">placeholder="设置常用地址"</c:if>placeholder="${user.province_name }&nbsp${user.city_name}&nbsp${user.region_name} ${user.address }" class="row_input_padRt" disabled />
                    <img src="${ctx}/statics/wap/images/arrow_right.png" alt="" class="arrowRt fr" />
                </div>
                <div class="row row_fff cards">
                    <label>身份证：</label>
                    <c:if test="${user.card == null || empty user.card  }"><input type="text" placeholder="设置身份证" class="row_input_padRt card " disabled value="" />
                    <img src="${ctx}/statics/wap/images/arrow_right.png" alt="" class="arrowRt fr" /></c:if>
                    <c:if test="${user.card != null and not empty user.card  }"><input type="text" value="${user.card }" class="row_input_padRt card" disabled value="" /></c:if>
                </div>
                <div class="row row_fff">
		                    <label>性别：</label>
		                    <select name="sex" class="sex">
		                    	<c:if test="${user.gender==null }" ><option value="0">请选择性别</option></c:if>
		                    	<option value="0">男</option>
		                    	<option value="1" <c:if test="${user.gender==1 }" >selected="selected"</c:if>>女</option>
		                    </select>
		               <img src="${ctx}/statics/wap/images/arrow_right.png" alt="" class="arrowRt fr" />
	            </div>
                <div class="row row_fff ages" >
                    <label>年龄：</label>
                    <input type="text" placeholder="设置年龄" class="row_input_padRt age" value="${user.age }" disabled />
                    <img src="${ctx}/statics/wap/images/arrow_right.png" alt="" class="arrowRt fr" />
                </div>
            </div>
            <div class="minBlock">
                <div class="row row_fff ali_nos">
                    <label>支付宝：</label>
                    <input type="text" placeholder="设置支付宝账号" class="row_input_padRt ali_no" value="${user.ali_no }" disabled />
                    <img src="${ctx}/statics/wap/images/arrow_right.png" alt="" class="arrowRt fr" />
                </div>
                <div class="row row_fff wx_nos">
                    <label>微信：</label>
                    <input type="text" placeholder="设置微信账号" class="row_input_padRt wx_no" value="${user.wx_no }" disabled />
                    <img src="${ctx}/statics/wap/images/arrow_right.png" alt="" class="arrowRt fr" />
                </div>
                <div class="row row_fff bankNo">
                    <label>银行卡号：</label>
                    <input type="text" placeholder="设置银行卡号" class="row_input_padRt bankN" disabled value="${user.bank_no }" />
                    <img src="${ctx}/statics/wap/images/arrow_right.png" alt="" class="arrowRt fr" />
                </div>
                <div class="row row_fff bankName">
                    <label>户名：</label>
                    <input type="text" placeholder="设置户名" class="row_input_padRt bankNa" value="${user.bank_user }" disabled />
                    <img src="${ctx}/statics/wap/images/arrow_right.png" alt="" class="arrowRt fr" />
                </div>
                <div class="row row_fff bankUser">
                    <label>开户行：</label>
                    <input type="text" placeholder="设置开户行" class="row_input_padRt bankU" value="${user.bank_name }" disabled />
                    <img src="${ctx}/statics/wap/images/arrow_right.png" alt="" class="arrowRt fr" />
                </div>
            </div>
        </div>
        <div class="mInfoBlock">
        	<div class="info clearfix">
            	<span class="fl">孩子信息</span>
                <span class="fr"><input type="button" value="添加" class="childAdd" onclick="javascript:location.href='${ctx}/wap/my/memberAddChild'"  /></span>
            </div>
        	<c:forEach items="${student }" var="s">
            <div class="minBlock mBtm10">
                <div class="row row_fff">
                    <label>姓名：</label>
                    <label class="row_input_padRt">${s.name }</label>
                </div>
                <div class="row row_fff">
                    <label>性别：</label>
                    <c:if test="${s.gender == 1 }"><label class="row_input_padRt">女</label></c:if>
                    <c:if test="${s.gender == 0 }"><label class="row_input_padRt">男</label></c:if>
                </div>
                <div class="row row_fff">
                    <label>年龄：</label>
                    <label class="row_input_padRt">${s.age }</label>
                </div>
                <div class="row row_fff">
                    <label>年级：</label>
                    <label class="row_input_padRt">${s.grade }</label>
                </div>
                <div class="row row_fff">
                    <label>在读学校：</label>
                    <label class="row_input_padRt">${s.school }</label>
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
<script type="text/javascript" src="${ctx}/statics/wap/js/alerts.js"></script>
<script type="text/javascript" src="${ctx}/statics/wap/layui/layui.js"></script>
<script type="text/javascript">

	layui.use(['layer', 'form'], function(){
  	var layer = layui.layer
  	,form = layui.form();	
  
		
	  $(".userName").click(function(){
		  layer.open({
			  type: 1,  
			  content:'<input type="text" placeholder="请输入用户名" class="inputCon" />',
			  title:"用户名修改",
			  closeBtn: 0,
			  btn:['取消','确定'],
			  btn2:function(index){
				var uName=$(".inputCon").val();
				if(uName==null||uName==""){
					alertN("用户名不能为空！");
					return false;
				}
				$.post("${ctx}/myapi/updateMember",{"username":uName},function(data){
				    if(data.code=="0"){
				     alertN(data.msg);
				     $(".uName").val(uName);
				    }else{
				      alertN(data.msg);
				    }
				},"json");
			},
			  btnAlign: 'c',
			  shadeClose:true,
			  skin: 'update_tip'
			});
			layer.config({
			  skin: 'update_tip'
			});
		});
	
		$(".cards").click(function(){
		  layer.open({
			  type: 1,  
			  content:'<input type="text" placeholder="请输入身份证" class="inputCon" />',
			  title:"身份证",
			  closeBtn: 0,
			  btn:['取消','确定'],
			  btn2:function(index){
				var card=$(".inputCon").val();
				var reg= /(^\d{15}$)|(^\d{17}([0-9]|X)$)/;
					if(!(reg.test(card))){
					alertN("身份证输入格式有误！");
					return false;
					}
				$.post("${ctx}/myapi/updateMember",{"card":card},function(data){
					if(data.code=="0"){
						layer.close(index);
						alertN(data.msg);
						$(".card").val(card);
					}else{
						alertN(data.msg);
					}
				},"json");
			},
			  btnAlign: 'c',
			  shadeClose:true,
			  skin: 'update_tip'
			});
			layer.config({
			  skin: 'update_tip'
			});
		});
		
		$(".bankUser").click(function(){
		  layer.open({
			  type: 1,  
			  content:'<input type="text" placeholder="请输入开户行" class="inputCon" />',
			  title:"开户行",
			  closeBtn: 0,
			  btn:['取消','确定'],
			  btn2:function(index){
				var bankU=$(".inputCon").val();
				var reg = /^[\u4e00-\u9fa5]{6,9}$/;  
    			if(!reg.test(bankU)) {alertN("请输入6-9位汉字");return flast;}
				$.post("${ctx}/myapi/updateMember",{"bank_name":bankU},function(data){
					if(data.code=="0"){
						layer.close(index);
						alertN(data.msg);
						$(".bankU").val(bankU);
					}else{
						alertN(data.msg);
					}
				},"json");
			},
			  btnAlign: 'c',
			  shadeClose:true,
			  skin: 'update_tip'
			});
			layer.config({
			  skin: 'update_tip'
			});
		});
	
		$(".bankName").click(function(){
		  layer.open({
			  type: 1,  
			  content:'<input type="text" placeholder="请输入户名" class="inputCon" />',
			  title:"户名",
			  closeBtn: 0,
			  btn:['取消','确定'],
			  btn2:function(index){
				var bankNa=$(".inputCon").val();
				var reg = /^[\u4e00-\u9fa5]{2,8}$/;  
    			if(!reg.test(bankNa)) {alertN("请输入2-8位汉字");return flast;}
				$.post("${ctx}/myapi/updateMember",{"bank_user":bankNa},function(data){
					if(data.code=="0"){
						layer.close(index);
						alertN(data.msg);
						$(".bankNa").val(bankNa);
					}else{
						alertN(data.msg);
					}
				},"json");
			},
			  btnAlign: 'c',
			  shadeClose:true,
			  skin: 'update_tip'
			});
			layer.config({
			  skin: 'update_tip'
			});
		});
		
		
		$(".bankNo").click(function(){
		  layer.open({
			  type: 1,  
			  content:'<input type="text" placeholder="请输入银行卡号" class="inputCon" />',
			  title:"银行卡号",
			  closeBtn: 0,
			  btn:['取消','确定'],
			  btn2:function(index){
				var bankN=$(".inputCon").val();
				var numPattern=/^[\d*]{16,19}$/; //数字的正则表达式  
			   if(!numPattern.test(bankN)){
			   alertN("请输入16或19位数字");
			   return flast; 
			   }  
				$.post("${ctx}/myapi/updateMember",{"bank_no":bankN},function(data){
					if(data.code=="0"){
						layer.close(index);
						alertN(data.msg);
						$(".bankN").val(bankN);
					}else{
						alertN(data.msg);
					}
				},"json");
			},
			  btnAlign: 'c',
			  shadeClose:true,
			  skin: 'update_tip'
			});
			layer.config({
			  skin: 'update_tip'
			});
		});
		
		
		$(".ages").click(function(){
		  layer.open({
			  type: 1,  
			  content:'<input type="text" placeholder="请输入年龄" class="inputCon" />',
			  title:"年龄",
			  closeBtn: 0,
			  btn:['取消','确定'],
			  btn2:function(index){
				var age=$(".inputCon").val();
				$.post("${ctx}/myapi/updateMember",{"age":age},function(data){
					if(data.code=="0"){
						layer.close(index);
						alertN(data.msg);
						$(".age").val(age);
					}else{
						alertN(data.msg);
					}
				},"json");
			},
			  btnAlign: 'c',
			  shadeClose:true,
			  skin: 'update_tip'
			});
			layer.config({
			  skin: 'update_tip'
			});
		});
		
		
		$(".mail").click(function(){
		  layer.open({
			  type: 1,  
			  content:'<input type="text" placeholder="请输入邮箱" class="inputCon" />',
			  title:"邮箱修改",
			  closeBtn: 0,
			  btn:['取消','确定'],
			  btn2:function(index){
				var mailBox=$(".inputCon").val();
				var reg=/\w+([-+.]\w+)*@\w+([-.]\w+)*\.\w+([-.]\w+)*/;  
		        if(!(reg.test(mailBox))){  
		                    alertN("邮箱输入格式有误！");  
		                    return false;  
              }  
				$.post("${ctx}/myapi/updateMember",{"email":mailBox},function(data){
					if(data.code=="0"){
						layer.close(index);
						alertN(data.msg);
						$(".mailBox").val(mailBox);
					}else{
						alertN(data.msg);
					}
				},"json");
			},
			  btnAlign: 'c',
			  shadeClose:true,
			  skin: 'update_tip'
			});
			layer.config({
			  skin: 'update_tip'
			});
		});
	});
	
	$(".wx_nos").click(function(){
		  layer.open({
			  type: 1,  
			  content:'<input type="text" placeholder="请输入微信号" class="inputCon" />',
			  title:"微信",
			  closeBtn: 0,
			  btn:['取消','确定'],
			  btn2:function(index){
				var wx_no=$(".inputCon").val();
				$.post("${ctx}/myapi/updateMember",{"wx_no":wx_no},function(data){
					if(data.code=="0"){
						layer.close(index);
						alertN(data.msg);
						$(".wx_no").val(wx_no);
					}else{
						alertN(data.msg);
					}
				},"json");
			},
			  btnAlign: 'c',
			  shadeClose:true,
			  skin: 'update_tip'
			});
			layer.config({
			  skin: 'update_tip'
			});
		});
		$(".ali_nos").click(function(){
		  layer.open({
			  type: 1,  
			  content:'<input type="text" placeholder="请输入支付宝号" class="inputCon" />',
			  title:"支付宝",
			  closeBtn: 0,
			  btn:['取消','确定'],
			  btn2:function(index){
				var ali_no=$(".inputCon").val();
				$.post("${ctx}/myapi/updateMember",{"ali_no":ali_no},function(data){
					if(data.code=="0"){
						layer.close(index);
						alertN(data.msg);
						$(".ali_no").val(ali_no);
					}else{
						alertN(data.msg);
					}
				},"json");
			},
			  btnAlign: 'c',
			  shadeClose:true,
			  skin: 'update_tip'
			});
			layer.config({
			  skin: 'update_tip'
			});
		});
	
	$(function(){
		$("select[name='sex']").change(function(){
				$.post("${ctx}/myapi/updateMember",{"gender":$(this).val()},function(data){
					if(data.code=="0"){
						location.reload();
					}else{
						alertN(data.msg);
					}
				},"json");
		});
	});
</script> 
</body>
</html>