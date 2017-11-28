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
<title>会员中心</title>
<link href="${ctx}/statics/wap/css/base.css" rel="stylesheet" type="text/css">
<link href="${ctx}/statics/wap/css/index.css" rel="stylesheet" type="text/css">
<script type="text/javascript" src="${ctx}/statics/wap/js/jquery-1.8.3.min.js"></script>
<script type="text/javascript" src="${ctx}/statics/wap/js/common.js"></script>
<style type="text/css">
.row_type{ font-size:0.28rem; }
.mRt20{ margin-right:0.2rem; }
.row_type_input{ vertical-align:middle; margin-top:-0.06rem; }
</style>
</head>
<body>
<div class="container">
	<div class="forgetPwd mBtm76">
	<input type="hidden" id="numbers" value="${activation.number}">
    	<div class="info">获取信息</div>
    	<div class="row">
        	<label>姓名：</label>
        	<input type="text" placeholder="请输入姓名" id="name" class="row_input_a" />
        </div>
        <div class="row">
        	<label>类型：</label>
        	<span class="row_type mRt20"><input type="radio" name="type" checked="checked" value="0" class="row_type_input" />员工</span>
            <span class="row_type"> <input type="radio" name="type"  value="1" class="row_type_input" />合伙人</span>
        </div>
        <div class="row">
        	<label>手机：</label>
        	<input type="text" placeholder="请输入正确的手机号码" id="mobile" class="row_input" />
        	 <input type="button" value="获取验证码" id="btn" class="row_btn fr" />
        </div>
        <div class="row">
               <label>验证码：</label>
               <input type="text" placeholder="请输入验证码" id="code" class="row_input_a" />
        </div>
        <div class="row">
        	<label>密码：</label>
        	<input type="password" placeholder="请输入密码" id="password" class="row_input_a" />
        </div>
        <div class="row">
        	<label>确认密码：</label>
        	<input type="password" placeholder="请再次输入密码" id="passwordtwo" class="row_input_a" />
        </div>
        <div class="row">
        	<label>身份证：</label>
        	<input type="text" placeholder="请输入有效的身份证号" id="card" class="row_input_a" />
        </div>
        <div class="row">
        	<label>推荐人：</label>
        	<input type="text" placeholder="请输入推荐人编号"  onchange="validNumber()" id="refer_number" class="row_input_a" />
        </div>
        <div class="row">
        	<label>组织结构：</label>
            <label>${pdepart.name}</label>
        </div>
        <div class="row">
        	<label></label>
        	<select name="select1" id="select" class="select">
        	 <option value="">选择一级部门</option>
            </select>
        </div>
        <div class="row">
        	<label></label>
        	<select name="select2" id="select" class="select">
        	  <option value="">选择二级部门</option>
            </select>
        </div>
        <div class="row">
        	<label></label>
        	<select name="select3" id="select" class="select">
        	   <option value="">选择三级部门</option>
            </select>
        </div>
         <div class="row">
        	<label></label>
        	<select name="select4" id="select" class="select">
        	    <option value="">选择四级部门</option>
            </select>
        </div>
         <div class="row">
        	<label></label>
        	<select name="select5" id="select" class="select">
        	 <option value="">选择五级部门</option>
            </select>
        </div>
        <!--<div class="tipRow tip none">请先输入激活码</div>-->
        <div class="submitBox">
        	<input type="button" value="激活" class="submitBtn" />
        </div>
    </div>
</div>
<script type="text/javascript" src="${ctx}/statics/wap/js/alerts.js"></script>
<script type="text/javascript" src="${ctx}/statics/wap/layui/layui.js"></script>
<script type="text/javascript">
layui.use(['layer', 'form'], function(){
  var layer = layui.layer
  ,form = layui.form();	
  
  $(".submitBtn").click(function(){
  		//姓名校验
		var name=$("#name").val();
		var reg = /^[\u4e00-\u9fa5]{2,8}$/;  
  		if(!reg.test(name)) {alertN("请输入2-8位汉字");return;}
  		//手机号校验
		var mobile=$("#mobile").val();         
			var reg=/^1[3|4|5|7|8][0-9]\d{8}$/ ;
				if(!(reg.test(mobile))){
				alertN("手机号格式有误");
				return;
			}
	  	//密码校验
		if($("#password").val()!=$("#passwordtwo").val()){
			alertN("两次密码输入不一致");
			return;
		}
		var pwd=$("#passwordtwo").val();
		var res =  /^.*?[\d]+.*$/;;
		var res1 = /^.*?[A-Za-z]/;
		var res2 = /^.*?[\u4e00-\u9fa5]+.*$/;
		var res3 = /^.{6,20}$/;
		if (!res.test(pwd)) {  
		    alertN("密码中必须包含数字 ");return;
		}
		if (!res1.test(pwd)) {  
		    alertN("密码中必须包含字母 ");return;
		}
		if (res2.test(pwd)) {  
		    alertN("密码中不能含有中文 ");return;
		}
		if (!res3.test(pwd)) {  
		    alertN("密码长度必须大于6位 ");return;
		}
		//身份证校验
		var card=$("#card").val();
		var reg= /(^\d{15}$)|(^\d{17}([0-9]|X)$)/;
			if(!(reg.test(card))){
			alertN("身份证输入格式有误！");
			return false;
			}
		 var code=$("#code").val();
		 if(code==null||($.trim(code)=="")){
		   alert("验证码不可以为空!");
		   return;
		 }
		 if(verify==1&&$.trim($("#refer_number").val())!=""){
		   alertN("推荐人不存在");
		 }
    var depart_id="${pdepart.id}";
    var list=$(".select");
    for(var i=0;i<list.length;i++){
       if(list[i].value!=""){
         depart_id=list[i].value;
       }else{
         break;
       }
    }
    $(".submitBtn").attr("disabled",true);//先把按钮禁用，防止多次提交激活
     $.post("${ctx}/wapapi/register/partnerInfo",{
        "code":$("#code").val(),
     	"password":$("#password").val(),
         "user_type":$("[name='type']:checked").val(),
    	 "name":$("#name").val(),
    	 "mobile":$("#mobile").val(),
    	 "card":$("#card").val(),
    	 "refer_number":$("#refer_number").val(),
    	 "numbers":$("#numbers").val(),
    	 "depart_id":depart_id},function(data){
        if(data.code=="0"){ layer.open({
  		  type: 1, 
  		  content: '激活成功', 
  		  title:false,
  		  closeBtn: 0,
  		  time:1000,
  		  skin: 'active_tip'
  		});
  		layer.config({
  		  skin: 'active_tip'
  		})
        	javascript:location.href="${ctx}/wap/partnerChecking?mobile="+$("#mobile").val();
        }else{
        	alertN(data.msg);
        	$(".submitBtn").attr("disabled",false);
        }
     },"json");
  })
  
});



$(function(){
	resetSelect1("");
	$("[name='select1']").change(function(){
		resetselect($(this).val(),2,"");
	})
	$("[name='select2']").change(function(){
		resetselect($(this).val(),3,"");
	})
	$("[name='select3']").change(function(){
		resetselect($(this).val(),4,"");
	})
	$("[name='select4']").change(function(){
		resetselect($(this).val(),5,"");
	})
	$("[name='select5']").change(function(){
		resetselect($(this).val(),6,"");
	})
})

function resetSelect1(select_id){
     $("[name='select1']").html("<option value=\"\">选择一级部门</option>");
	$.getJSON("${ctx}/wap/depart/${pdepart.id}",function(data){
		for(var i=0;i<data.length;i++){
			$("[name='select1']").append("<option value=\""+data[i].id+"\" hassubinfo=\"true\">"+data[i].name+"</option>");
		}
		if(select_id!="") 	   $("[name='select1']").val(select_id);
		$(".chosen-select").trigger("chosen:updated");
		//resetselect($("[name='select1']").val(),2,"");
	})
}
function resetselect(pid,pint,select_id){
	$(".chosen-select").trigger("chosen:updated");
	var number=parseInt(pint);
	if(number<=5){
	      if(number==2) {
	          $("[name='select2']").html("<option value=\"\">选择二级部门</option>");
	          $("[name='select3']").html("<option value=\"\">选择三级部门</option>");
	          $("[name='select4']").html("<option value=\"\">选择四级部门</option>");
	          $("[name='select5']").html("<option value=\"\">选择五级部门</option>");
	      }
	       if(number==3) {
	          $("[name='select3']").html("<option value=\"\">选择三级部门</option>");
	          $("[name='select4']").html("<option value=\"\">选择四级部门</option>");
	          $("[name='select5']").html("<option value=\"\">选择五级部门</option>");
	      }
	       if(number==4) {
	          $("[name='select4']").html("<option value=\"\">选择四级部门</option>");
	          $("[name='select5']").html("<option value=\"\">选择五级部门</option>");
	      }
	       if(number==5) {
	          $("[name='select5']").html("<option value=\"\">选择五级部门</option>");
	      }
	   if(pid!=""){
			$.getJSON("${ctx}/wap/depart/"+pid,function(data){
	    		for(var i=0;i<data.length;i++){
	    			$("[name='select"+pint+"']").append("<option value=\""+data[i].id+"\" hassubinfo=\"true\">"+data[i].name+"</option>");
	    		}
	    		if(select_id!="") $("[name='select"+pint+"']").val(select_id);
	    		$(".chosen-select").trigger("chosen:updated");
	    		//var index=parseInt(parseInt(pint)+1);
	    		//resetselect($("[name='select"+pint+"']").val(),index);
			})
	   }
	}
}

    function validNumber(){
          var number=$("#refer_number").val();
           verify=0;
          if(number!=null&&number!=""){
             $.post("${ctx}/wap/queryByNumber/"+number,function(data){
                 if(data!=null&&data.user!=null){
	              $("[name='select2']").html("<option value=\"\">选择二级部门</option>");
		          $("[name='select3']").html("<option value=\"\">选择三级部门</option>");
		          $("[name='select4']").html("<option value=\"\">选择四级部门</option>");
		          $("[name='select5']").html("<option value=\"\">选择五级部门</option>");
		             if(data.grade2==null){
		                resetSelect1("");
		             }
	                 if(data.grade2!=null){
	                   resetSelect1(data.grade2);
	                 }
	                 if(data.grade3!=null) {
	                     resetselect(data.grade2,2,data.grade3);
	                 }
	                 if(data.grade4!=null) {
	                    resetselect(data.grade3,3,data.grade4);
	                 }
	                 if(data.grade5!=null) {
	                   resetselect(data.grade4,4,data.grade5);
	                 }
	                 if(data.grade6!=null){
	                   resetselect(data.grade5,5,data.grade6);
	                  }
                 }else{
                    alertN("推荐人不存在,请重新输入");
                    verify=1;
                 }
             })
          }
        }
</script> 
<script type="text/javascript">
layui.use(['layer', 'form'], function(){
  var layer = layui.layer
  ,form = layui.form();	
  

  $(".row_btn").click(function(){          
var mobile=$("#mobile").val();         
	var reg=/^1[3|4|5|7|8][0-9]\d{8}$/ ;
		if(!(reg.test(mobile))){
		alertN("手机号格式有误！");
		return;
		}
     $.post("${ctx}/wapapi/sms/register",{"mobile":$("#mobile").val()},function(data){
        if(data.code=="0"){ 
      	  time();
        	alertN(data.msg+data.data.codes);
        	//alertN(data.msg);
        }else{
      	  alertN(data.msg);
        }
     },"json");
  })
  
});

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
<!--验证码时间是必须放到底部-->
</body>
</html>