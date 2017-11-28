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
    <link href="${ctx}/statics/manage/plugins/bootstrap-datetimepicker/css/bootstrap-datetimepicker.css" rel="stylesheet">
    
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
                        			<div class="row" style="margin-bottom: 20px;">
			                        	<div class="col-sm-12">
					                        <button type="button" class="btn btn-outline btn-primary <c:if test='${order.status==1 }'>active disabled</c:if>" <c:if test='${order.status!=1 }'>onclick="updateStatus(1)"</c:if>>已准备</button>
					                        <button type="button" class="btn btn-outline btn-primary <c:if test='${order.status==2 }'>active disabled</c:if>" <c:if test='${order.status!=2 }'>onclick="updateStatus(2)"</c:if>>已交车</button>
					                        <button type="button" class="btn btn-outline btn-primary <c:if test='${order.status==3 }'>active disabled</c:if>" <c:if test='${order.status!=3 }'>onclick="updateStatus(3)"</c:if>>已还车</button>
					                        <button type="button" class="btn btn-outline btn-primary <c:if test='${order.status==4 }'>active disabled</c:if>" <c:if test='${order.status!=4 }'>onclick="updateStatus(4)"</c:if>>已核价</button>
					                        <button type="button" class="btn btn-outline btn-primary <c:if test='${order.status==6 }'>active disabled</c:if>" <c:if test='${order.status!=6 }'>onclick="updateStatus(6)"</c:if>>已结算</button>
					                        <button type="button" class="btn btn-outline btn-danger <c:if test='${order.status==-1 }'>active disabled</c:if>" <c:if test='${order.status!=-1 }'>onclick="updateStatus(-1)"</c:if>>已取消</button>
			                            </div>
			                        </div>
			                        
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
			
			                                    <dt>下单时间：</dt>
			                                    <dd><fmt:formatDate value="${order.create_date }" pattern="yyyy-MM-dd HH:mm"/></dd>
			                                    <dt>备车时间：</dt>
			                                    <dd>
			                                    	<div class="input-group date" style="width: 300px">
						                                <input class="form-control input-sm" type="text" name="ready_date" placeholder="备车时间" value="<fmt:formatDate value='${order.ready_date }' pattern='yyyy-MM-dd HH:mm'/>" style="width:180px">
						                                <a class="btn btn-white btn-bitbucket" onclick="updateOrder('ready_date',this)"><i class="fa fa-check"></i> 确定</a>
													</div>
			                                    </dd>
			                                    <dt>计划用车时间：</dt>
			                                    <dd>
			                                    	<div class="input-group date" style="width: 300px">
						                                <input class="form-control input-sm" type="text" name="rent_star_time" placeholder="计划用车时间" value="<fmt:formatDate value='${order.rent_star_time }' pattern='yyyy-MM-dd HH:mm'/>" style="width:180px">
						                                <a class="btn btn-white btn-bitbucket" onclick="updateOrder('rent_star_time',this)"><i class="fa fa-check"></i> 确定</a>
													</div>
			                                    </dd>
			                                    <dt>实际用车时间：</dt>
			                                    <dd>
			                                    	<div class="input-group date" style="width: 300px">
						                                <input class="form-control input-sm" type="text" name="rent_star_ttime" placeholder="实际用车时间" value="<fmt:formatDate value='${order.rent_star_ttime }' pattern='yyyy-MM-dd HH:mm'/>" style="width:180px">
						                                <a class="btn btn-white btn-bitbucket" onclick="updateOrder('rent_star_ttime',this)"><i class="fa fa-check"></i> 确定</a>
													</div>
			                                    </dd>
			                                    <dt>取车网点：</dt>
			                                    <dd>${getDot.name }&nbsp;&nbsp;<a data-toggle="modal" href="#modal-map" onclick="initMap('${getDot.longitude}','${getDot.latitude }')"><i class="fa fa-map-pin"></i> 查看</a></dd>
			                                    <dt>取车方式：</dt>
			                                    <dd><c:choose><c:when test="${order.get_type==0 }">自取</c:when><c:otherwise>送车上门</c:otherwise></c:choose></dd>
			                                    <dt>送车地址：</dt>
				                                <dd><c:choose><c:when test="${order.get_type==0 }">-</c:when><c:otherwise>${order.get_address }&nbsp;&nbsp;<a data-toggle="modal" href="#modal-map" onclick="initMap('${order.get_lng}','${order.get_lat }')"><i class="fa fa-map-pin"></i> 查看</a></c:otherwise></c:choose></dd>
			                                    <dt>所在车位：</dt>
			                                    <dd>${order.park_name }&nbsp;&nbsp;<a data-toggle="modal" href="#modal-map" onclick="initMap('${order.park_lng}','${order.park_lat }')"><i class="fa fa-map-pin"></i> 查看</a></dd>
			                                    <dt>租赁时长：</dt>
			                                    <dd>${order.rent_days }天</dd>
			                                    <dt>订单总额：</dt>
			                                    <dd>&yen;${order.amount }</dd>
			                                </dl>
			                            </div>
			                            <div class="col-sm-6" id="cluster_info">
			                                <dl class="dl-horizontal">
			
												<dt>完成时间：</dt>
			                                    <dd><c:choose><c:when test="${empty(order.complete_date) }">-</c:when><c:otherwise><fmt:formatDate value="${order.complete_date }" pattern="yyyy-MM-dd HH:mm"/></c:otherwise></c:choose></dd>
			                                    <dt>交车时间：</dt>
			                                    <dd>
			                                    	<div class="input-group date" style="width: 300px">
						                                <input class="form-control input-sm" type="text" name="delivery_date" placeholder="交车时间" value="<fmt:formatDate value='${order.delivery_date }' pattern='yyyy-MM-dd HH:mm'/>" style="width:180px">
						                                <a class="btn btn-white btn-bitbucket" onclick="updateOrder('delivery_date',this)"><i class="fa fa-check"></i> 确定</a>
													</div>
			                                    </dd>
			                                    <dt>计划还车时间：</dt>
			                                    <dd>
			                                    	<div class="input-group date" style="width: 300px">
						                                <input class="form-control input-sm" type="text" name="rent_end_time" placeholder="计划还车时间" value="<fmt:formatDate value='${order.rent_end_time }' pattern='yyyy-MM-dd HH:mm'/>" style="width:180px">
						                                <a class="btn btn-white btn-bitbucket" onclick="updateOrder('rent_end_time',this)"><i class="fa fa-check"></i> 确定</a>
													</div>
			                                    </dd>
			                                    <dt>实际还车时间：</dt>
			                                    <dd>
			                                    	<div class="input-group date" style="width: 300px">
						                                <input class="form-control input-sm" type="text" name="rent_end_ttime" placeholder="实际还车时间" value="<fmt:formatDate value='${order.rent_end_ttime }' pattern='yyyy-MM-dd HH:mm'/>" style="width:180px">
						                                <a class="btn btn-white btn-bitbucket" onclick="updateOrder('rent_end_ttime',this)"><i class="fa fa-check"></i> 确定</a>
													</div>
			                                    </dd>
			                                    <dt>还车网点：</dt>
			                                    <dd>${retDot.name }&nbsp;&nbsp;<a data-toggle="modal" href="#modal-map" onclick="initMap('${retDot.longitude}','${retDot.latitude }')"><i class="fa fa-map-pin"></i> 查看</a></dd>
			                                    <dt>还车方式：</dt>
			                                    <dd><c:choose><c:when test="${order.return_type==0 }">自还</c:when><c:otherwise>上门取车</c:otherwise></c:choose></dd>
			                                    <dt>取车地址：</dt>
				                                <dd><c:choose><c:when test="${order.return_type==0 }">-</c:when><c:otherwise>${order.return_address }&nbsp;&nbsp;<a data-toggle="modal" href="#modal-map" onclick="initMap('${order.return_lng}','${order.return_lat }')"><i class="fa fa-map-pin"></i> 查看</a></c:otherwise></c:choose></dd>
			                                    <dt>调度人员：</dt>
			                                    <dd>${order.dispatch_name }</dd>
			                                    <dt>调度电话：</dt>
			                                    <dd>${order.dispatch_mobile }</dd>
			                                    <dt>支付方式：</dt>
			                                    <dd>
			                                    	<c:choose><c:when test="${order.pay_type==0}">后付款</c:when><c:otherwise>预充值</c:otherwise></c:choose>
			                                    </dd>
			                                </dl>
			                            </div>
			                        </div>
			                        
			                        <div class="row">
			                        	<div class="col-sm-6">
			                                <dl class="dl-horizontal">
			                                    <dt>还车照片：</dt>
			                                    <dd style="margin-left: 68px">
			                                    	<input class="fileInput" id="returnUploadify" type="file" name="file" multiple="multiple" accept="image/*"/>
			                                    </dd>
			                                    <dt><span style="opacity:0;filter:alpha(opacity=0); -moz-opacity:0;">还车照片：</span></dt>
			                                    <dd>
			                                    	<div id="returnImgs">
			                                    		<c:if test="${!empty(order.return_gallerys) }">
			                                    		<c:forEach items="${fn:split(order.return_gallerys,',') }" var="g">
					                                    	<a class="fancybox" href="${g }" title="还车照片" data-fancybox-group="return_gallerys">
					                            				<img alt="image" src="${g }" width="30" height="30"/>
					                        				</a>
					                                    </c:forEach>
					                                    </c:if>
			                                    	</div>
			                        				<br>
			                        				<a class="btn btn-white btn-bitbucket" onclick="updateImg('return_gallerys','returnImgs')" style="margin-left: 65px"><i class="fa fa-check"></i> 确定</a>
			                                    </dd>
			                                </dl>
			                            </div>
			                            <div class="col-sm-6">
			                                <dl class="dl-horizontal">
			                                    <dt>车损照片：</dt>
			                                    <dd style="margin-left: 68px">
			                                    	<input class="fileInput" id="damageUploadify" type="file" name="file" multiple="multiple" accept="image/*"/>
			                                    </dd>
			                                    <dt><span style="opacity:0;filter:alpha(opacity=0); -moz-opacity:0;">车损照片：</span></dt>
			                                    <dd>
			                                    	<div id="damageImgs">
			                                    		<c:if test="${!empty(order.damage_gallerys) }">
					                                    <c:forEach items="${fn:split(order.damage_gallerys,',') }" var="g">
					                                    	<a class="fancybox" href="${g }" title="车损照片" data-fancybox-group="damage_gallerys">
					                            				<img alt="image" src="${g }" width="30" height="30"/>
					                        				</a>
					                                    </c:forEach>
					                                    </c:if>
			                                    	</div>
			                        				<br>
			                        				<a class="btn btn-white btn-bitbucket" onclick="updateImg('damage_gallerys','damageImgs')" style="margin-left: 65px"><i class="fa fa-check"></i> 确定</a>
			                                    </dd>
			                                </dl>
			                            </div>
			                        </div>
			                        
			                        <div class="table-responsive m-t well">
				                        <table class="table invoice-table">
				                            <thead>
				                                <tr>
				                                    <th>车辆编号</th>
				                                    <th>车辆名称</th>
				                                    <th>车牌号</th>
				                                    <th>租金</th>
				                                    <th>保险</th>
				                                    <th>送车费</th>
				                                    <th>取车费</th>
				                                    <th>车损</th>
				                                    <th>违章</th>
				                                    <th>其他</th>
				                                    <th>销售折扣</th>
				                                    <th>智券抵扣</th>
				                                    <th>积分抵扣</th>
				                                    <th>总计</th>
				                                </tr>
				                            </thead>
				                            <tbody>
				                                <tr>
				                                    <td>${order.car_number}</td>
				                                    <td>${order.car_name }</td>
				                                    <td>${order.car_license }</td>
				                                    <td>&yen;${order.car_price }</td>
				                                    <td>&yen;${order.insurance }×${order.ins_num }</td>
				                                    <td>&yen;${order.get_amount }</td>
				                                    <td>&yen;${order.return_amount }</td>
				                                    <td><input class="form-control input-sm" type="text" name="damage_amount" placeholder="车损" value="${order.damage_amount }" style="width:65px"><a class="btn btn-white btn-bitbucket" onclick="updateOrder('damage_amount',this)"><i class="fa fa-check"></i> 确定</a></td>
				                                    <td><input class="form-control input-sm" type="text" name="peccancy_amount" placeholder="违章" value="${order.peccancy_amount }" style="width:65px"><a class="btn btn-white btn-bitbucket" onclick="updateOrder('peccancy_amount',this)"><i class="fa fa-check"></i> 确定</a></td>
				                                    <td><input class="form-control input-sm" type="text" name="other_amount" placeholder="其他" value="${order.other_amount }" style="width:65px"><a class="btn btn-white btn-bitbucket" onclick="updateOrder('other_amount',this)"><i class="fa fa-check"></i> 确定</a></td>
				                                    <td>-&nbsp;&yen;${order.sale_amount }</td>
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
				                    
				                    <div class="well m-t"><strong>订单备注：</strong> ${order.remark }</div>
				                    <div class="well m-t"><textarea class="form-control" name="order_remark" style="resize: none" placeholder="还车备注">${order.order_remark }</textarea><a class="btn btn-white btn-bitbucket" onclick="updateOrder('order_remark',this)"><i class="fa fa-check"></i> 确定</a></div>
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
        
        
        <div id="modal-map" class="modal fade" aria-hidden="true">
	        <div class="modal-dialog">
	            <div class="modal-content">
	                <div class="modal-body">
	                    <div class="row">
	                        <div class="col-sm-12" id="allmap" style="height: 400px">
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
    <script src="${ctx}/statics/manage/plugins/bootstrap-datetimepicker/js/bootstrap-datetimepicker.js"></script>
    <script src="${ctx}/statics/manage/plugins/bootstrap-datetimepicker/js/locales/bootstrap-datetimepicker.zh-CN.js"></script>
    <script src="${ctx}/statics/manage/js/jquery.form.js"></script>
    <script src="${ctx}/statics/manage/js/common.js"></script>
    <script src="http://api.map.baidu.com/api?v=2.0&ak=WhXy2DrGL6B6hlzFhixKZTna6pGsYZzi"></script>
    
    <script>
        $(document).ready(function(){$("#ibox-content").addClass("ibox-content");$("#vertical-timeline").removeClass("light-timeline");$("#vertical-timeline").addClass("dark-timeline");$(".fancybox").fancybox({openEffect:"none",closeEffect:"none"});});
    	
    	// 百度地图API功能
	    var map;
	 	function initMap(lng,lat){
	 		setTimeout("setMap("+lng+","+lat+")",300);
	  	}
	 	function setMap(lng,lat){
		    map = new BMap.Map("allmap");
	 		var point = new BMap.Point(lng,lat);
	 		map.centerAndZoom(point,18); 
	 		var marker = new BMap.Marker(point);  // 创建标注
	 		map.addOverlay(marker);               // 将标注添加到地图中
	 		marker.setAnimation(BMAP_ANIMATION_BOUNCE); //跳动的动画
	 		map.addControl(new BMap.MapTypeControl());//添加地图类型控件
	 		map.enableScrollWheelZoom();   //启用滚轮放大缩小，默认禁用
	 		map.enableContinuousZoom();    //启用地图惯性拖拽，默认禁用
	 		
	 		// 覆盖区域图层测试
			var stCtrl = new BMap.PanoramaControl(); //构造全景控件
			stCtrl.setOffset(new BMap.Size(20, 40));
			map.addControl(stCtrl);//添加全景控件
	  	}
	  	
	  	$("#returnUploadify").uploadify({
        	'buttonImage':'${ctx}/statics/manage/plugins/uploadify/uploadify-button.png',
	        'swf': '${ctx}/statics/manage/plugins/uploadify/uploadify.swf',
	        'uploader':'${ctx}/upload',
	        'onFallback' : function() {//检测FLASH失败调用 
	        	alert("您未安装FLASH控件，无法上传图片！请安装FLASH控件后再试。");  
	        },  
	        'onUploadSuccess':function(file, data, response){
	            var jsonData = jQuery.parseJSON(data);
	            $("#returnImgs").append("<a class=\"fancybox\" href=\""+jsonData.msg+"\" title=\"还车照片\" data-fancybox-group=\"return_gallerys\">"+
			                            "<img alt=\"image\" src=\""+jsonData.msg+"\" width=\"30\" height=\"30\"/>"+
			                        	"</a>");
        	}
	    });
	    $("#damageUploadify").uploadify({
        	'buttonImage':'${ctx}/statics/manage/plugins/uploadify/uploadify-button.png',
	        'swf': '${ctx}/statics/manage/plugins/uploadify/uploadify.swf',
	        'uploader':'${ctx}/upload',
	        'onFallback' : function() {//检测FLASH失败调用 
	        	alert("您未安装FLASH控件，无法上传图片！请安装FLASH控件后再试。");  
	        },  
	        'onUploadSuccess':function(file, data, response){
	            var jsonData = jQuery.parseJSON(data);
                $("#damageImgs").append("<a class=\"fancybox\" href=\""+jsonData.msg+"\" title=\"车损照片\" data-fancybox-group=\"damage_gallerys\">"+
			                            "<img alt=\"image\" src=\""+jsonData.msg+"\" width=\"30\" height=\"30\"/>"+
			                        	"</a>");
        	}
	    });
	  	
        $("[name='delivery_date'],[name='ready_date'],[name='rent_star_time'],[name='rent_star_ttime'],[name='rent_end_time'],[name='rent_end_ttime']").datetimepicker({
            language:  'zh-CN',
			format: "yyyy-mm-dd hh:ii",
			maxView:3,
       		autoclose: true,
       		todayBtn: true,
       		setDaysOfWeekDisabled:[0,6]
    	});
	  	
	  	//修改状态
	  	function updateStatus(value){
	  		layer.confirm(value!=-1?'确认要操作吗？':'取消操作会退返订单金额且不允许再做任何操作，确定要操作吗？', function(index){
			  layer.close(index);
			  $.post("${ctx}/manage/order/update?id=${order.id}&status="+value,function(data){
				if(data.code=="0"){
					layer.msg("操作成功", {icon : 1});
					setTimeout("window.location.reload()", 1000)
				}else{
					opt_error(data.msg);
				}
			  },"json")
			});  
	  	}
	  	function updateOrder(field,o){
		  	var value=$(o).prev().val();
		  	if(value==""){
		  		layer.msg('内容不能为空', function(){});
		  		return false;
		  	}
	  		layer.confirm('确认要操作吗？', function(index){
			  layer.close(index);
			  $.post("${ctx}/manage/order/update?id=${order.id}&"+field+"="+value,function(data){
				if(data.code=="0"){
					layer.msg("操作成功", {icon : 1});
					setTimeout("window.location.reload()", 1000)
				}else{
					opt_error(data.msg);
				}
			  },"json")
			});  
	  	}
	  	function updateImg(field,c){
	  		var imgs=[];
	  		$("#"+c).find("img").each(function(){
	  			imgs.push($(this).attr("src"));
	  		})
	  		if(imgs.toString()==""){
		  		layer.msg('请先上传图片', function(){});
		  		return false;
		  	}
	  		layer.confirm('确认要操作吗？', function(index){
			  layer.close(index);
			  $.post("${ctx}/manage/order/update?id=${order.id}&"+field+"="+imgs.toString(),function(data){
				if(data.code=="0"){
					layer.msg("操作成功", {icon : 1});
					setTimeout("window.location.reload()", 1000)
				}else{
					opt_error(data.msg);
				}
			  },"json")
			});  
	  	}
    </script>
</body>

</html>