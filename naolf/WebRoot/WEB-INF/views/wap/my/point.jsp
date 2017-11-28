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
<title>我的积分</title>
<link href="${ctx}/statics/wap/css/base.css" rel="stylesheet" type="text/css">
<link href="${ctx}/statics/wap/css/index.css" rel="stylesheet" type="text/css">
<script type="text/javascript" src="${ctx}/statics/wap/js/jquery-1.8.3.min.js"></script>
<script type="text/javascript" src="${ctx}/statics/wap/js/common.js"></script>
<script type="text/javascript" src="${ctx}/statics/wap/js/base.js"></script>
</head>
<body>
<div class="container mBtm95">
	<div class="point">
    	<div class="point_head">
        	<img src="${ctx}/statics/wap/images/point_bk.png" alt="" />
            <div class="pointTxtBlock">
            	<div class="pointType clearfix">
                	<select class="fl" name="point" onchange="">
                    	<option value="0" <c:if test="${empDisJournal.BKZ_SCORE!=null }">selected="selected"</c:if>>未结业积分</option>
                    	<option value="1" <c:if test="${empDisJournal.KZ_SCORE!=null  }">selected="selected"</c:if>>已结业积分</option>
                    	<option value="2" <c:if test="${empDisJournal.KD_SCORE!=null}">selected="selected"</c:if>>可提现积分</option>
                    	<option value="3" <c:if test="${empDisJournal.KD_SCORE==null and empDisJournal.KZ_SCORE==null and empDisJournal.BKZ_SCORE==null}">selected="selected"</c:if> onclick="jl()">已提现记录</option>
                    </select>
	                <c:if test="${empDisJournal.KD_SCORE!=null }">
                    	<input type="button" value="立即提现" class="moneyBtn fr" onClick="topayWithdrawals()"/>
	                </c:if>
                </div>
            	<div class="pointPrice">
            			<c:if test="${userScoreInfo.user_number==null&&(empty user)}">
            			<span>0</span>
            			</c:if>
                		<c:if test="${empDisJournal.BKZ_SCORE!=null }">
                		<span>${userScoreInfo.BKZ_SCORE }</span>
	                	</c:if>
	                	<c:if test="${empDisJournal.KZ_SCORE!=null }">
                		<span>${userScoreInfo.KZ_SCORE }</span>
	                	</c:if>
	                	<c:if test="${empDisJournal.KD_SCORE!=null }">
                		<span>${userScoreInfo.KD_SCORE }</span>
	                	</c:if>
	                <c:if test="${empDisJournal.KD_SCORE!=null}">
                    <input type="button" value="立即充值" class="moneyBtn fr"  onClick="topayScore()" />
                	</c:if>
                </div>
            </div>
        </div>
        <div class="pointList">
        	<table>
        		<c:if test="${userCash==null }">
            	<thead>
                    <tr>
                        <th>交易时间</th>
                        <th>交易类型</th>
                        <th>积分</th>
                    </tr>
                </thead>
                 </c:if>
                <tbody>
                	<c:forEach items="${list }" var="e">
	                	<c:if test="${empDisJournal.BKZ_SCORE!=null }">
	                	<tr onclick="point('${e.ORDER_NO}','${e.TRANSFER_name }',${e.BKZ_SCORE})">
	                    	<td><fmt:formatDate value="${e.create_date }" pattern="yy/MM/dd HH:mm"/></td>
	                    	<td>${e.TRANSFER_name }</td>
	                    	<td>${e.BKZ_SCORE}分</td>
	                    </tr>
	                	</c:if>
	                	<c:if test="${empDisJournal.KZ_SCORE!=null }">
	                	<tr onclick="point('${e.ORDER_NO}','${e.TRANSFER_name }',${e.KZ_SCORE})">
	                    	<td><fmt:formatDate value="${e.create_date }" pattern="yy/MM/dd HH:mm"/></td>
	                    	<td>${e.TRANSFER_name }</td>
	                    	<td>${e.KZ_SCORE}分</td>
	                    </tr>
	                	</c:if>
	                	<c:if test="${empDisJournal.KD_SCORE!=null }">
	                	<tr onclick="point('${e.ORDER_NO}','${e.TRANSFER_name }',${e.KD_SCORE})">
	                    	<td><fmt:formatDate value="${e.create_date }" pattern="yy/MM/dd HH:mm"/></td>
	                    	<td>${e.TRANSFER_name }</td>
	                    	<td>${e.KD_SCORE}分</td>
	                    </tr>
	                	</c:if>
                	</c:forEach>
                </tbody>
            </table>
        </div>
        <div class="pointList">
        	<table>
        		<c:if test="${userCash!=null }">
            	<thead>
                    <tr>
                        <th>提现时间</th>
                        <th>收款信息</th>
                        <th>提现金额</th>
                    </tr>
                </thead>
       		 	</c:if>
        		<c:forEach items="${userCash }" var="c">
                <tbody>
                	<tr>
                    	<c:if test="${c.status==0 }"><td>审核尚未通过</td></c:if>
                    	<c:if test="${c.pay_date!=null&&(c.status==1) }"><td><fmt:formatDate value="${c.pay_date}" pattern="yy/MM/dd HH:mm"/></td></c:if>
                    	<td>
                    	   <c:if test="${c.type==0 }">${user.bank_name }</c:if>
                    	   <c:if test="${c.type==1 }">${user.ali_no}</c:if>
                    	</td>
                    	<td>${c.kd_score/score_percent}元</td>
                    </tr>
                </tbody>
        		</c:forEach>
            </table>
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
	var score = parseFloat(${userScoreInfo.BKZ_SCORE })+parseFloat(${userScoreInfo.KZ_SCORE})+parseFloat(${userScoreInfo.KD_SCORE});
	$("#score").html(score);
	
		$(function(){
			$("select[name='point']").change(function(){
			if($(this).val()==0){window.location.href='${ctx}/wap/my/point?BKZ_SCORE=1'};
			if($(this).val()==1){window.location.href='${ctx}/wap/my/point?KZ_SCORE=1'};
			if($(this).val()==2){window.location.href='${ctx}/wap/my/point?KD_SCORE=1'};
			if($(this).val()==3){window.location.href='${ctx}/wap/my/point?record=1'};
			})
		})
	
	function point(ORDER_NO,TRANSFER_name,jf){
	   var type=$("[name=point]").val();
	   if(type==0){
	     location.href="${ctx}/wap/my/pointDetail?ORDER_NO="+ORDER_NO+"&BKZ_SCORE="+jf+"&type="+type;
	   }
	    if(type==1){
	     location.href="${ctx}/wap/my/pointDetail?ORDER_NO="+ORDER_NO+"&KZ_SCORE="+jf+"&type="+type;
	   }
	    if(type==2){
	     location.href="${ctx}/wap/my/pointDetail?ORDER_NO="+ORDER_NO+"&KD_SCORE="+jf+"&type="+type;
	   }
	}
	function  topayScore(){//去充值
	   location.href="${ctx}/pay/wxpay/topayscore";
	}
	function  topayWithdrawals(){//去提现
	   location.href="${ctx}/wap/my/topayWithdrawals";
	}
	

</script>
</body>
</html>