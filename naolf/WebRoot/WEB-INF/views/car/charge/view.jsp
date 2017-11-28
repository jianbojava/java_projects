<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>

<head>

    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    

    <title>智行家</title>
    <meta name="keywords" content="后台bootstrap框架,后台HTML,响应式后台">
    <meta name="description" content="基于Bootstrap3最新版本开发的扁平化主题，她采用了主流的左右两栏式布局，使用了Html5+CSS3等现代技术">

    <link rel="shortcut icon" href="favicon.ico"> 
    
    <link href="${ctx}/statics/manage/css/bootstrap.min.css?v=3.3.5" rel="stylesheet">
    <link href="${ctx}/statics/manage/css/font-awesome.min.css?v=4.4.0" rel="stylesheet">
    <link href="${ctx}/statics/manage/css/animate.min.css" rel="stylesheet">
    <link href="${ctx}/statics/manage/css/style.min.css?v=4.0.0" rel="stylesheet">
    <link href="${ctx}/statics/manage/css/plugins/switchery/switchery.css" rel="stylesheet">
    <link href="${ctx}/statics/manage/css/plugins/iCheck/custom.css" rel="stylesheet">
    <link href="${ctx}/statics/manage/css/plugins/sweetalert/sweetalert.css" rel="stylesheet">
    <link href="${ctx}/statics/manage/plugins/uploadify/uploadify.css" rel="stylesheet">
    <link href="${ctx}/statics/manage/js/plugins/fancybox/jquery.fancybox.css" rel="stylesheet">
    
</head>

<body>
    <div class="row animated fadeInRight">
            <div class="col-sm-12">
                <div class="panel-options">
                    <ul class="nav nav-tabs">
                        <li class="active"><a data-toggle="tab" href="#tab-1" aria-expanded="true">订单详细</a></li>
                        <li class=""><a data-toggle="tab" href="#tab-2" aria-expanded="false">订单日志</a></li>
                    </ul>
                    <div class="tab-content">
                        <div id="tab-1" class="tab-pane active">
                        	<div class="wrapper wrapper-content">
			                        <div class="row">
			                        	<div class="col-sm-6">
			                                <dl class="dl-horizontal">
			                                    <dt>订单编号：</dt>
			                                    <dd><span class="label label-primary">${order.sn }</span>
			                                    </dd>
			                                </dl>
			                            </div>
			                            <div class="col-sm-6">
			                                <dl class="dl-horizontal">
			                                    <dt>订单状态：</dt>
			                                    <dd>
			                                    	<c:choose>
				                                    	<c:when test="${order.status!=-1 }"><span class="label label-primary">${order.order_status }</span></c:when>
                                    					<c:otherwise><span class="label label-default">${order.order_status }</span></c:otherwise>
				                                    </c:choose>
			                                    </dd>
			                                </dl>
			                            </div>
			                        </div>
			                        
			                        <div class="row">
			                            <div class="col-sm-6">
			                                <dl class="dl-horizontal">
			
			                                    <dt>开始充电时间：</dt>
			                                    <dd>
			                                    	<c:choose>
			                                    		<c:when test="${empty(order.startTime) }">-</c:when>
			                                    		<c:otherwise><fmt:formatDate value="${order.startTime }" pattern="yyyy-MM-dd HH:mm:ss"/></c:otherwise>
			                                    	</c:choose>
			                                    </dd>
			                                    <dt>累计充电量（度）：</dt>
			                                    <dd>
			                                    	<c:choose>
			                                    		<c:when test="${empty(order.totalPower) }">-</c:when>
			                                    		<c:otherwise>${order.totalPower }</c:otherwise>
			                                    	</c:choose>
			                                    </dd>
			                                    <dt>总服务费：</dt>
			                                    <dd>
			                                    	<c:choose>
			                                    		<c:when test="${empty(order.totalSeviceMoney)}">-</c:when>
			                                    		<c:otherwise>&yen;${order.totalSeviceMoney }</c:otherwise>
			                                    	</c:choose>
			                                    </dd>
			                                    <dt>设备接口编码：</dt>
			                                    <dd>${order.connectorID }</dd>
			                                    <dt>充电结束原因：</dt>
			                                    <dd>
			                                    	<c:choose>
			                                    		<c:when test="${empty(order.stopReason) }">-</c:when>
			                                    		<c:otherwise>
			                                    			<c:choose>
			                                    				<c:when test="${order.stopReason==0 }">用户手动停止充电</c:when>
				                                    			<c:when test="${order.stopReason==1 }">客户归属地运营商平台停止充电</c:when>
				                                    			<c:when test="${order.stopReason==2 }">BMS停止充电</c:when>
				                                    			<c:when test="${order.stopReason==3 }">充电机设备故障</c:when>
				                                    			<c:when test="${order.stopReason==4 }">连接器断开</c:when>
				                                    			<c:otherwise>其他</c:otherwise>
			                                    			</c:choose>
			                                    		</c:otherwise>
			                                    	</c:choose>
			                                    </dd>
			                                </dl>
			                            </div>
			                            <div class="col-sm-6" id="cluster_info">
			                                <dl class="dl-horizontal">
			
			                                    <dt>结束充电时间：</dt>
			                                    <dd>
			                                    	<c:choose>
			                                    		<c:when test="${empty(order.endTime) }">-</c:when>
			                                    		<c:otherwise><fmt:formatDate value="${order.endTime }" pattern="yyyy-MM-dd HH:mm:ss"/></c:otherwise>
			                                    	</c:choose>
			                                    </dd>
			                                    <dt>总电费：</dt>
			                                    <dd>
			                                    	<c:choose>
			                                    		<c:when test="${empty(order.totalElecMoney) }">-</c:when>
			                                    		<c:otherwise>&yen;${order.totalElecMoney }</c:otherwise>
			                                    	</c:choose>
			                                    </dd>
			                                    <dt>累计总金额：</dt>
			                                    <dd>
			                                    	<c:choose>
			                                    		<c:when test="${empty(order.totalMoney)}">-</c:when>
			                                    		<c:otherwise>&yen;${order.totalMoney }</c:otherwise>
			                                    	</c:choose>
			                                    </dd>
			                                    <dt>支付方式：</dt>
			                                    <dd><c:choose><c:when test="${order.pay_type==0}">后付款</c:when><c:otherwise>预充值</c:otherwise></c:choose></dd>
			                                    <dt>时段数：</dt>
			                                    <dd>
			                                    	<c:choose>
			                                    		<c:when test="${empty(order.sumPeriod) }">-</c:when>
			                                    		<c:otherwise>${order.sumPeriod }</c:otherwise>
			                                    	</c:choose>
			                                    </dd>
			                                </dl>
			                            </div>
			                        </div>
			                        
			                        <div class="table-responsive m-t well">
				                        <table class="table invoice-table">
				                            <thead>
				                                <tr>
				                                	<th>开始时间</th>
				                                	<th>结束时间</th>
				                                    <th>时段电价</th>
				                                    <th>时段服务费价格</th>
				                                    <th>时段充电量（度）</th>
				                                    <th>时段电费</th>
				                                    <th>时段服务费</th>
				                                </tr>
				                            </thead>
				                            <tbody>
				                            	<c:choose>
				                            		<c:when test="${empty(details) }">
				                            			<tr>
				                            				<td colspan="7" style="text-align: center;"><i class="fa fa-hand-o-right"></i>&nbsp;无详细数据</td>
				                            			</tr>
				                            		</c:when>
				                            		<c:otherwise>
				                            			<c:forEach items="${details }" var="d">
						                                <tr>
						                                	<td><c:choose><c:when test="${empty(d.detailStartTime)}">-</c:when><c:otherwise><fmt:formatDate value="${d.detailStartTime }" pattern="yyyy-MM-dd HH:mm:ss"/></c:otherwise></c:choose></td>
						                                	<td><c:choose><c:when test="${empty(d.detailEndTime)}">-</c:when><c:otherwise><fmt:formatDate value="${d.detailEndTime }" pattern="yyyy-MM-dd HH:mm:ss"/></c:otherwise></c:choose></td>
						                                    <td><c:choose><c:when test="${empty(d.elecPrice)}">-</c:when><c:otherwise>&yen;${d.elecPrice }</c:otherwise></c:choose></td>
						                                    <td><c:choose><c:when test="${empty(d.sevicePrice)}">-</c:when><c:otherwise>&yen;${d.sevicePrice }</c:otherwise></c:choose></td>
						                                    <td><c:choose><c:when test="${empty(d.detailPower)}">-</c:when><c:otherwise>${d.detailPower }</c:otherwise></c:choose></td>
						                                    <td><c:choose><c:when test="${empty(d.detailElecMoney)}">-</c:when><c:otherwise>&yen;${d.detailElecMoney }</c:otherwise></c:choose></td>
						                                    <td><c:choose><c:when test="${empty(d.detailSeviceMoney)}">-</c:when><c:otherwise>&yen;${d.detailSeviceMoney }</c:otherwise></c:choose></td>
						                                </tr>
						                                </c:forEach>
				                            		</c:otherwise>
				                            	</c:choose>
				                            </tbody>
				                        </table>
				                    </div>
			                        
				                    <div class="table-responsive m-t well">
				                        <table class="table invoice-table">
				                            <thead>
				                                <tr>
				                                	<th>会员编号</th>
				                                	<th>会员卡号</th>
				                                    <th>姓名</th>
				                                    <th>联系电话</th>
				                                    <th>会员类型</th>
				                                    <th>所属企业</th>
				                                </tr>
				                            </thead>
				                            <tbody>
				                                <tr>
				                                	<td><c:choose><c:when test="${empty(member.number)}">-</c:when><c:otherwise>${member.number }</c:otherwise></c:choose></td>
				                                	<td><c:choose><c:when test="${empty(member.card_number)}">-</c:when><c:otherwise>${member.card_number }</c:otherwise></c:choose></td>
				                                    <td><c:choose><c:when test="${empty(member.name)}">-</c:when><c:otherwise>${member.name }</c:otherwise></c:choose></td>
				                                    <td><c:choose><c:when test="${empty(member.mobile)}">-</c:when><c:otherwise>${member.mobile }</c:otherwise></c:choose></td>
				                                    <td><c:choose><c:when test="${empty(member.type)}">-</c:when><c:otherwise><c:choose><c:when test="${member.type==0}">普通会员</c:when><c:otherwise>企业个人会员</c:otherwise></c:choose></c:otherwise></c:choose></td>
				                                    <td><c:choose><c:when test="${empty(member.firm_name)}">-</c:when><c:otherwise>${member.firm_name }</c:otherwise></c:choose></td>
				                                </tr>
				                            </tbody>
				                        </table>
				                    </div>
				                    
				                    <div class="well m-t"><strong>订单备注：</strong> ${order.remark }</div>
			            	</div>
                        </div>
                        
                        <div id="tab-2" class="tab-pane">
                            <div id="vertical-timeline" class="vertical-container light-timeline">
                            
                            		<c:forEach items="${logs }" var="log" varStatus="status">
                            			<div class="vertical-timeline-block">
	                                        <div class="vertical-timeline-icon <c:if test='${status.index==0 }'>navy-bg</c:if><c:if test='${status.index!=0 }'>gray-bg</c:if>">
	                                            <i class="fa fa-volume-down"></i>
	                                        </div>
	
	                                        <div class="vertical-timeline-content">
	                                            <h2>
	                                            	<c:choose>
	                                            		<c:when test="${empty(log.user_id) }">
	                                            			<c:choose>
	                                            				<c:when test="${empty(log.mem_name) }">${log.mem_mobile }</c:when>
	                                            				<c:otherwise>${log.mem_name}</c:otherwise>
	                                            			</c:choose>
	                                            		</c:when>
	                                            		<c:otherwise>${log.user_name }</c:otherwise>
	                                            	</c:choose>
	                                            </h2>
	                                            <p>
	                                            	${log.message }
	                                            </p>
	                                            <span class="vertical-date">
	                                        		<small><fmt:formatDate value="${log.create_date }" pattern="yyyy-MM-dd HH:mm:ss"/></small>
	                                    		</span>
	                                        </div>
	                                    </div>
                            		</c:forEach>

                                </div>
                        </div>
                    </div>


                </div>
            </div>
        </div>

    <script src="${ctx}/statics/manage/js/jquery.min.js?v=2.1.4"></script>
    <script src="${ctx}/statics/manage/js/bootstrap.min.js?v=3.3.6"></script>
    <script src="${ctx}/statics/manage/js/content.min.js?v=1.0.0"></script>
    <script src="${ctx}/statics/manage/js/plugins/switchery/switchery.js"></script>
    <script src="${ctx}/statics/manage/js/plugins/iCheck/icheck.min.js"></script>
    <script src="${ctx}/statics/manage/plugins/layer/layer.js"></script>
    <script src="${ctx}/statics/manage/js/plugins/sweetalert/sweetalert.min.js"></script>
    <script src="${ctx}/statics/manage/plugins/uploadify/jquery.uploadify.js"></script>
    <script src="${ctx}/statics/manage/js/plugins/fancybox/jquery.fancybox.js"></script>
    <script src="${ctx}/statics/manage/js/jquery.form.js"></script>
    <script src="${ctx}/statics/manage/js/common.js"></script>
    
    <script>
        $(document).ready(function(){$("#ibox-content").addClass("ibox-content");$("#vertical-timeline").removeClass("light-timeline");$("#vertical-timeline").addClass("dark-timeline");$(".fancybox").fancybox({openEffect:"none",closeEffect:"none"});});
    </script>
</body>

</html>