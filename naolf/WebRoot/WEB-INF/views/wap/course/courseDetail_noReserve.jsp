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
<title>${lesson.name}</title>
<link href="${ctx}/statics/wap/css/base.css" rel="stylesheet" type="text/css">
<link href="${ctx}/statics/wap/css/index.css" rel="stylesheet" type="text/css">
<script type="text/javascript" src="${ctx}/statics/wap/js/common.js"></script>
<script type="text/javascript" src="${ctx}/statics/wap/js/jquery-1.8.3.min.js"></script>
</head>
<body>
<div class="container">
	<div class="forgetPwd mBtm106">
    	<div class="courseBanner">
            <img src="${lesson.image}" alt="" />
        </div>
        <div class="courseBlock">
        	<div class="title">
            	<div class="title1 f28">
                	<i></i>
                	<span>课程信息</span>
                	<i></i>
                </div>
            	<div class="title2 f24">THE COURSE INFORMATION</div>
            </div>
        	<div class="content">
            	<p>课程名：${lesson.name }</p>
            	<p>课程分类：${lesson.catname }</p>
            	<p>适合年龄：${lesson.userage }</p>
            	<p>训练课时：${lesson.period }</p>
            </div>
        </div>
        <div class="courseBlock">
        	<div class="title">
            	<div class="title1 f28">
                	<span>课程特点</span>
                </div>
            	<div class="title2 f24">THE COURSE INFORMATION</div>
            </div>
        	<div class="content">
            	<p>${lesson.special }。</p>
            </div>
        </div>
        <div class="courseStatus f24">
           <c:if test="${ut.status==0 }"><span>课程状态：</span><span class="status">未预约</span></c:if>
           <c:if test="${ut.status==1 }"><span>课程状态：</span><span class="status">已预约</span></c:if>
           <c:if test="${ut.status==2 }"><span>课程状态：</span><span class="status">预约已取消</span></c:if>
           <c:if test="${ut.status==-3}"><span>课程状态：</span><span class="status">未训练</span></c:if>
           <c:if test="${ut.status==3 }"><span>课程状态：</span><span class="status">训练中</span></c:if>
           <c:if test="${ut.status==4 }"><span>课程状态：</span><span class="status">已结业</span></c:if>
           <c:if test="${ut.status==5 }"><span>课程状态：</span><span class="status">复训中</span></c:if>
           <c:if test="${ut.status==6 }"><span>课程状态：</span><span class="status">复训已结束</span></c:if><br><br>
           <c:if test="${ut.status>=3}">
           		<span>课程记录：</span>
           		<a href="${ctx}/wap/course/reserveRecord/${ut.id}/1000">
           			<span class="status">查看详情</span>
                	<img src="${ctx}/statics/wap/images/arrow_right.png" alt="" class="arrowFt fr" style="width:0.2rem!important"/>
                </a><br><br>
           </c:if>
           <c:if test="${(ut.status==-3)||(ut.status==4&&lesson.type==0)||(ut.status==5)}">
           		<span>预约记录：</span>
           		<a href="${ctx}/wap/course/reserveRecord/${ut.id}/100">
           			<span class="status">查看详情</span>
                	<img src="${ctx}/statics/wap/images/arrow_right.png" alt="" class="arrowFt fr" style="width:0.2rem!important"/>
                </a><br><br>
           </c:if>
          
        </div>
        <div class="submitBox">
            <!--0未预约 ,1已预约，2预约取消，-3未训练，3训练中 ,4已结业  5,复训中 6已结束  -->
            <!-- 0,2立即预约 -->
            <c:if test="${ut.status==0||ut.status==2}"><a ><input type="button" value="立即预约" name="status" id="1" onclick="chooseChild('${ut.id}','${ut.norder_id}')" class="submitBtn" /></a></c:if>
            <!-- 1查看详情即预约记录 -->
            <c:if test="${ut.status==1}"><a href="${ctx}/wap/course/reserveDevelop?s_id=${student_id}&ut_id=${ut.id}"><input type="button" value="查看详情" name="status" id="2" class="submitBtn status" /></a></c:if>
            <!-- 3立即训练 -->
             <c:if test="${ut.status==-3}"><input type="button" id="scanQRCode"  onclick="toqrcode('${ut.id}')"  value="立即训练" name="status"  class="submitBtn status" /></c:if>
            <!--集中的评价  -->
             <c:if test="${ut.status==4&&lesson.type==1&&comm==0}">
                <input type="button" onclick="tocomment('${ut.id}')"  value="立即评价"  class="submitBtn status" />
           </c:if>
           <c:if test="${ut.status==4&&lesson.type==1&&comm==1}">
                <input type="button" onclick="tocomment('${ut.id}')"  value="查看评价"  class="submitBtn status" />
           </c:if>
        </div>
        <c:if test="${ut.status>=4&&ut.status<=5&&lesson.type==0}">
        <div class="trainBox clearfix">
        	<div class="comment fl">
            	<a href="${ctx}/wap/course/comment?id=${ut.id}">
                    <img src="${ctx}/statics/wap/images/star.png" alt="" />
                    <label class="f28">评价</label>
                </a>
            </div>
            <input type="button" onclick="toreview('${ut.id}')" value="预约复训" class="trainBtn trainBtn1 fl" />
            <input type="button" value="复训" class="trainBtn trainBtn2 fl" id="scanQRCode" onclick="toqrcode('${ut.id}')" />
        </div>
        </c:if>
    </div>
</div>
<script type="text/javascript" src="${ctx}/statics/wap/layui/layui.js"></script>
<script type="text/javascript" src="${ctx}/statics/wap/js/alerts.js"></script>
<script type="text/javascript"  src="${ctx}/statics/wap/js/jweixin-1.0.0.js"></script>

<script type="text/javascript">
 /*立即预约，选择孩子href="${ctx}/wap/course/reserveChooseChild?id=${ut.id}&n_id=${n_id}"   */
     function chooseChild(utid,n_id){
     	$.post("${ctx}/courseApi/studentVe",{"id":utid},function(data){
   			if(data.code=="0"){
//        			location.href="${ctx}/wap/course/reserveChooseChild?id="+utid;
       			location.href="${ctx}/wap/course/reserveChooseChild?id="+utid+"&n_id="+n_id;
   			}else{
   			layui.use(['layer', 'form'], function(){
						  var layer = layui.layer
						  ,form = layui.form();	
							 layer.open({
							  type: 1, 
							  content: data.msg, 
							  end:function(){
       						location.href="${ctx}/wap/my/memberInfo";
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
   			}
   		},"json");
     }   
     
     
var timestamp,nonceStr,signature,appId,url=location.href;
$(document).ready(function() {
	$.ajax({
		type : 'GET',
		url : '${ctx}/wap/shareinit?url='+encodeURIComponent(url),
		dataType : 'json',
		success : function(json) {
			wx.config({
			      debug: false,
			      appId: json.appId,
			      timestamp: json.timestamp,
			      nonceStr: json.noncestr,
			      signature: json.signature,
			      jsApiList: [
			        'checkJsApi',
			        'scanQRCode'
			      ]
			  });  
		}
	}); 
});

function  toqrcode(ut_id){
/**
 var course_id ='${course_id}'; // 当needResult 为 1 时，扫码返回的结果  (result得到的是course的id)
	                      $.post("${ctx}/courseApi/review",{"user_ticket_id":ut_id,"course_id":course_id},function(data){
						       if(data.code=="0"){
						           alertN("扫码成功!")
						          	setTimeout("javascript:location.href='${ctx}/wap/course/coursesuccess/"+data.data+"'",2000);
						       }else{
						          alertN(data.msg);
						       }
					    },"json")
**/
  wx.scanQRCode({  
                needResult : 1, // 默认为0，扫描结果由微信处理，1则直接返回扫描结果，  
                scanType : [ "qrCode", "barCode" ], // 可以指定扫二维码还是一维码，默认二者都有  
                success : function(res) {  
	                    var course_id = res.resultStr; // 当needResult 为 1 时，扫码返回的结果  (result得到的是course的id)
	                      $.post("${ctx}/courseApi/review",{"user_ticket_id":ut_id,"course_id":course_id},function(data){
						       if(data.code=="0"){
						           alertN("扫码成功!")
						          	setTimeout("javascript:location.href='${ctx}/wap/course/coursesuccess/"+data.data+"'",1500);
						       }else{
						          alertN(data.msg);
						       }
					    },"json")
                }  
            });
} 
//复训预约
function toreview(ut_id){
  var comm=${comm};
  if(comm<=0){
     alertN("先对课程进行评价哦");
     return;
  }
   location.href="${ctx}/wap/course/reviewDevelop/"+ut_id;
}

//集中课程去评价
function tocomment(ut_id){
  location.href="${ctx}/wap/course/comment?id="+ut_id;
}

</script>
</body>
</html>