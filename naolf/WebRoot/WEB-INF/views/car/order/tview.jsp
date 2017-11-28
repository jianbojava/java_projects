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
				                                    	<c:when test="${order.status==2 }"><span class="label label-warning">${order.order_status }</span></c:when>
				                                    	<c:when test="${order.status==3 }"><span class="label label-info">${order.order_status }</span></c:when>
				                                    	<c:otherwise><span class="label label-primary">${order.order_status }</span></c:otherwise>
				                                    </c:choose>
			                                    </dd>
			                                </dl>
			                            </div>
			                        </div>
			                        
			                        <div class="row">
			                            <div class="col-sm-6">
			                                <dl class="dl-horizontal">
			
			                                    <dt>用车时间：</dt>
			                                    <dd>
			                                    	<c:choose>
			                                    		<c:when test="${empty(order.rent_star_time) }">-</c:when>
			                                    		<c:otherwise><fmt:formatDate value="${order.rent_star_time }" pattern="yyyy-MM-dd HH:mm"/></c:otherwise>
			                                    	</c:choose>
			                                    </dd>
			                                    <dt>取车网点：</dt>
			                                    <dd>
			                                    	<c:choose>
			                                    		<c:when test="${empty(order.get_dot_id) }">-</c:when>
			                                    		<c:otherwise>${order.get_dot_id }</c:otherwise>
			                                    	</c:choose>
			                                    </dd>
			                                    <dt>订单总额：</dt>
			                                    <dd>
			                                    	<c:choose>
			                                    		<c:when test="${order.status==2 }">-</c:when>
			                                    		<c:otherwise>&yen;${order.amount }</c:otherwise>
			                                    	</c:choose>
			                                    </dd>
			                                </dl>
			                            </div>
			                            <div class="col-sm-6" id="cluster_info">
			                                <dl class="dl-horizontal">
			
			                                    <dt>还车时间：</dt>
			                                    <dd>
			                                    	<c:choose>
			                                    		<c:when test="${empty(order.rent_end_time) }">-</c:when>
			                                    		<c:otherwise><fmt:formatDate value="${order.rent_end_time }" pattern="yyyy-MM-dd HH:mm"/></c:otherwise>
			                                    	</c:choose>
			                                    </dd>
			                                    <dt>还车网点：</dt>
			                                    <dd>
			                                    	<c:choose>
			                                    		<c:when test="${empty(order.return_dot_id) }">-</c:when>
			                                    		<c:otherwise>${order.return_dot_id }</c:otherwise>
			                                    	</c:choose>
			                                    </dd>
			                                    <dt>支付方式：</dt>
			                                    <dd>
			                                    	<c:choose><c:when test="${order.pay_type==0}">后付款</c:when><c:otherwise>预充值</c:otherwise></c:choose>
			                                    </dd>
			                                </dl>
			                            </div>
			                        </div>
			                        
			                        <div class="table-responsive m-t well">
				                        <table class="table invoice-table">
				                            <thead>
				                                <tr>
				                                    <th>车牌号</th>
				                                    <th>租金</th>
				                                    <th>车损</th>
				                                    <th>违章</th>
				                                    <th>其他</th>
				                                    <th>智券抵扣</th>
				                                    <th>积分抵扣</th>
				                                    <th>总计</th>
				                                </tr>
				                            </thead>
				                            <tbody>
				                                <tr>
				                                    <td>${order.car_id }</td>
				                                    <td>&yen;${order.car_price }</td>
				                                    <td>&yen;${order.damage_amount }</td>
				                                    <td>&yen;${order.peccancy_amount }</td>
				                                    <td>&yen;${order.other_amount }</td>
				                                    <td>-&nbsp;&yen;${order.coupon_amount }</td>
				                                    <td>-&nbsp;&yen;${order.use_point }</td>
				                                    <td>&yen;${order.amount }</td>
				                                </tr>
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
				                    
				                    <div class="well m-t"><strong>订单备注：</strong> ${order.order_remark }</div>
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