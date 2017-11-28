<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
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
<title>卡券查询</title>
<link href="${ctx}/statics/wap/css/base.css" rel="stylesheet" type="text/css">
<link href="${ctx}/statics/wap/css/index.css" rel="stylesheet" type="text/css">
<script type="text/javascript" src="${ctx}/statics/wap/js/jquery-1.8.3.min.js"></script>
<script type="text/javascript" src="${ctx}/statics/wap/js/common.js"></script>
<style>
body .active_l{ width:5rem; line-height:0.5rem; border-radius:0.15rem; }
body .active_l .layui-layer-content{ padding:0.2rem; font-size:0.32rem; }
</style>
</head>
<body style="background:#f0f2f5;">
<div class="container">
	<div class="order_list_con confirm_order mBtm95">
    	<ul class="tabs tabs_two clearfix">
        	<li class="active"><a href="${ctx}/wap/course/cardCouponSearch">卡券</a></li>
        	<li><a href="${ctx}/wap/course/courseSearch">课程</a></li>
        </ul>
        <div class="orderListBox pTop90">
        <c:forEach items="${tlist }" var="t">
           <c:if test="${t.have_ut==1}">
	        	<div class="orderDetail">
	                <div class="orderName f28">
	                    <div class="clearfix">
	                        <div class="bold fl">${t.name }：${t.ticket_sn }</div>
	                    </div>
	                </div>
	                <div class="orderListBlock mBtm30 clearfix">
	                <c:forEach items="${t.utlist }" var="ut">
	                  <c:if test="${(t.status!=-2)||(t.status==-2&&ut.status>=4)}">
	                    <div class="fff pBtm20 bk_fff">
	                        <div class="orderList clearfix">
		                        <a href="${ctx}/wap/course/courseDetail_noReserve?id=${ut.id}">
		                            <div class="orderPic fl">
		                                <img src="${ut.image}" alt="" class="orderImg" />
		                            </div>
		                            <div class="orderDe fl">
		                                <div class="title bold f28">${ut.name }：${ut.sn }</div>
		                                <div class="age f24 color_a5a4a8">
			                                     <c:if test="${ut.status==0 }">课程状态：未预约</c:if>
			                                     <c:if test="${ut.status==1 }">课程状态：已预约</c:if>
			                                     <c:if test="${ut.status==2 }">课程状态：预约已取消</c:if>
			                                     <c:if test="${ut.status==-3}">课程状态：未训练</c:if>
			                                     <c:if test="${ut.status==3 }">课程状态：训练中</c:if>
			                                     <c:if test="${ut.status==4 }">课程状态：已结业</c:if>
			                                     <c:if test="${ut.status==5 }">课程状态：复训中</c:if>
			                                     <c:if test="${ut.status==6 }">课程状态：复训已结束</c:if>
			                             </div>
			                             <div class="course f24 color_a5a4a8">
		                                        <c:if test="${not empty ut.lastappoint}">
		                                                                                       预约记录：<fmt:formatDate value="${ut.lastappoint.create_date}" pattern="yy/MM/dd HH:mm"/>&nbsp;${ut.lastappoint.depart_name }
		                                         </c:if> &nbsp;
	                                     </div>
	                                     <div class="course f24 color_a5a4a8">
		                                    <c:if test="${not empty ut.lastclass}">
		                                	     学习记录：<fmt:formatDate value="${ut.lastclass.study_date}" pattern="yy/MM/dd HH:mm"/>&nbsp;${ut.lastclass.depart_name }
		                                     </c:if>&nbsp;
		                                  </div>
		                            </div>
		                         </a>
	                        </div>
	                        <div class="btnGroup fr">
	                           <c:if test="${ut.cancomm==0}">
	                             <input type="button" value="立即评价"  onclick="tocomment('${ut.id}')" class="btn btn_purple_b status" />
	                           </c:if> 
	                          <c:if test="${ut.status==0||ut.status==2 }">
		                         <input type="button" value="立即预约"  onclick="chooseChild('${ut.id}','${ut.norder_id }')" class="btn btn_purple_b status" />
		                      </c:if>
		                       <c:if test="${ut.lesson_type==0&&(ut.status==4||ut.status==5)}">
		                         <input type="button" value="预约复训"   onclick="toReview('${ut.id}')" class="btn btn_purple_b status" />
		                      </c:if>
		                      <input type="button" onclick="todetail('${ut.id}')" value="详情" class="btn btn_purple_b status" />
		                      
	                    	</div>
	                    </div>
	                    </c:if>
	                    </c:forEach>
	                </div>
	            </div>
	           </c:if>
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
        	<a onclick="tologin('${ctx}/wap/course/cardCouponSearch','${wapuser}')">
                <div class="navIcon">
                    <img src="${ctx}/statics/wap/images/course_active.png" alt="">
                </div>
                <div class="navName color_745398">课程</div>
            </a>
        </div>
        <div class="nav">
        	<a onclick="tologin('${ctx}/wap/message/message','${wapuser}')">
                <div class="navIcon">
                    <img src="${ctx}/statics/wap/images/msg.png" alt="">
                </div>
                <div class="navName">消息</div>
            </a>
        </div>
        <div class="nav">
        	<a onclick="tologin('${ctx}/wap/my/my','${wapuser}')">
                <div class="navIcon">
                    <img src="${ctx}/statics/wap/images/my.png" alt="">
                </div>
                <div class="navName">我的</div>
            </a>
        </div>
    </div>
</div>
<script type="text/javascript" src="${ctx}/statics/wap/js/alerts.js"></script>
<script type="text/javascript" src="${ctx}/statics/wap/layui/layui.js"></script>
<script>
    /*立即预约，选择孩子*/
     function chooseChild(utid,tid){
     	$.post("${ctx}/courseApi/studentVe",{"id":utid},function(data){
   			if(data.code=="0"){
       			location.href="${ctx}/wap/course/reserveChooseChild?id="+utid+"&n_id="+tid;
   			}else{
   			layui.use(['layer', 'form'], function(){
		  var layer = layui.layer
		  ,form = layui.form();	
			  layer.open({
				  type: 1, 
				  content: data.msg, 
				  title:false,
				  closeBtn: 0,
				  time:1000,
				  end:function(){
       						location.href="${ctx}/wap/my/memberInfo";
				  },
				  skin: 'active_tip'
				});
				layer.config({
				  skin: 'active_tip'
				})
		});
   			}
   		},"json");
     }   
     //复训预约
     function toReview(utid){
	   $.post("${ctx}/courseApi/validcomment/"+utid,function(data){
	           if(data>0){
	              location.href="${ctx}/wap/course/reviewDevelop/"+utid;
	           }else{
	               alertN("先对课程进行评价哦");
	              return;
	           }
	      },"json")
     } 
     //去课程详情
     function todetail(utid){
       location.href="${ctx}/wap/course/courseDetail_noReserve?id="+utid;
     }
     //课程去评价
     function tocomment(ut_id){
	  location.href="${ctx}/wap/course/comment?id="+ut_id;
	}
</script>
</body>
</html>
