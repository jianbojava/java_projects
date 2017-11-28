<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
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
    <link href="${ctx}/statics/manage/plugins/uploadify/uploadify.css" rel="stylesheet">
    <link href="${ctx}/statics/manage/css/plugins/datapicker/datepicker3.css" rel="stylesheet">
    <link href="${ctx}/statics/manage/css/plugins/chosen/chosen.css" rel="stylesheet">
</head>

<body>
    <div class="row animated fadeInRight">
            <div class="col-sm-12">
                <div class="ibox float-e-margins">
                    <div class="ibox-content">
                        <form action="" method="post" class="form-horizontal">
                            <div class="form-group">
                                <label class="col-sm-2 control-label">编号</label>

                                <div class="col-sm-9">
                                    <input type="text" class="form-control" name="number" placeholder="编号" value="${parking.number }">
                                </div>
                            </div>
                            <div class="hr-line-dashed"></div>
                            <div class="form-group">
                                <label class="col-sm-2 control-label">名称</label>

                                <div class="col-sm-9">
                                    <input type="text" class="form-control" name="name" placeholder="名称" value="${parking.name }">
                                </div>
                            </div>
                            <div class="hr-line-dashed"></div>
                            <div class="form-group">
                                <label class="col-sm-2 control-label">地址</label>

                                <div class="col-sm-9">
                                    <input type="text" class="form-control" name="address" placeholder="地址" value="${parking.address }">
                                </div>
                            </div>
                            <div class="hr-line-dashed"></div>
                            <div class="form-group">
                                <label class="col-sm-2 control-label">经纬度</label>

                                <div class="col-sm-9">
                                    <input type="text" class="form-control" name="lonlat" placeholder="经纬度" data-toggle="modal" href="#modal-map" onclick="initMap()" value="${parking.longitude },${parking.latitude}">
                                </div>
                            </div>
                            <div class="hr-line-dashed"></div>
                            <div class="form-group">
                                <label class="col-sm-2 control-label">网点</label>

                                <div class="col-sm-9">
                                    <select data-placeholder="选择网点..." class="chosen-select" tabindex="2" name="dot">
                                    	<option value="">请选择网点</option>
                                    	<c:forEach items="${dots }" var="d">
                                    	<option value="${d.id }">${d.name }</option>
                                    	</c:forEach>
		                            </select>
                                </div>
                            </div>
                            <div class="hr-line-dashed"></div>
                            <div class="form-group">
                                <label class="col-sm-2 control-label">调度员</label>
                                <div class="col-sm-9">
                                    <select data-placeholder="调度员..." class="chosen-select" tabindex="2" name="dispatch">
                                    	<option value="">请选择调度员</option>
                                    	<c:forEach items="${dispatchs }" var="d">
                                    		<option value="${d.id }" hassubinfo="true">${d.username }</option>
                                    	</c:forEach>
		                            </select>
                                </div>
                            </div>
                            <div class="hr-line-dashed"></div>
                            <div class="form-group">
                                <label class="col-sm-2 control-label">签约时间</label>

                                <div class="col-sm-9">
                                    <div class="input-group input-daterange">
		                                <input class="form-control" name="sign_date" type="text" placeholder="签约时间" value="<fmt:formatDate value='${parking.sign_date }' pattern='yyyy-MM-dd'/>">
		                                <span class="input-group-addon">到</span>
		                                <input class="form-control" name="end_date" type="text" placeholder="终止时间" value="<fmt:formatDate value='${parking.end_date }' pattern='yyyy-MM-dd'/>">
		                            </div>
                                </div>
                            </div>
                            <div class="hr-line-dashed"></div>
                            <div class="form-group">
                                <div class="col-sm-4 col-sm-offset-4" style="text-align: center;">
                                    <button class="btn btn-primary" type="button" onclick="add()">保存内容</button>
                                    <button class="btn btn-white" type="button" onclick="layer_close()">取消</button>
                                </div>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>
        
        <div id="modal-map" class="modal fade" aria-hidden="true">
	        <div class="modal-dialog">
	            <div class="modal-content">
	                <div class="modal-body">
	                    <div class="row">
	                        <div class="col-sm-12" id="allmap" style="height: 300px">
	                        	
	                        </div>
	                    </div>
	                </div>
	            </div>
	        </div>
	    </div>

    <script src="${ctx}/statics/manage/js/jquery.min.js?v=2.1.4"></script>
    <script src="${ctx}/statics/manage/js/bootstrap.min.js?v=3.3.6"></script>
    <script src="${ctx}/statics/manage/js/plugins/jasny/jasny-bootstrap.min.js"></script>
    <script src="${ctx}/statics/manage/js/content.min.js?v=1.0.0"></script>
    <script src="${ctx}/statics/manage/js/plugins/switchery/switchery.js"></script>
    <script src="${ctx}/statics/manage/js/plugins/iCheck/icheck.min.js"></script>
    <script src="${ctx}/statics/manage/plugins/layer/layer.js"></script>
    <script src="${ctx}/statics/manage/plugins/uploadify/jquery.uploadify.js"></script>
    <script src="${ctx}/statics/manage/js/plugins/datapicker/bootstrap-datepicker.js"></script>
    <script src="${ctx}/statics/manage/js/plugins/chosen/chosen.jquery.js"></script>
    <script src="${ctx}/statics/manage/js/jquery.form.js"></script>
    <script src="${ctx}/statics/manage/js/common.js"></script>
    <script src="http://api.map.baidu.com/api?v=2.0&ak=WhXy2DrGL6B6hlzFhixKZTna6pGsYZzi"></script>
    
    <script>
    $(document).ready(function(){
    	$("[name='dot']").val('${parking.dot_id}');
    	$("[name='dispatch']").val('${parking.dispatch_id}');
		$(".chosen-select").trigger("chosen:updated");
    });
        function add(){
			var lonlat=$("[name='lonlat']").val();
			var params={"id":"${parking.id}","number":$("[name='number']").val(),"name":$("[name='name']").val(),"address":$("[name='address']").val(),"sign_date":$("[name='sign_date']").val(),"end_date":$("[name='end_date']").val(),
			"dot_id":$("[name='dot']").val(),"dot_name":$("[name='dot'] option:selected").text(),"dispatch_id":$("[name='dispatch']").val(),"dispatch_name":$("[name='dispatch'] option:selected").text(),
			"longitude":lonlat.split(",")[0],"latitude":lonlat.split(",")[1]};
        	$.post("${ctx}/manage/parking/update",params,function(data){
					if(data.code=="0"){
						opt_success("修改成功",0);
					}else{
						opt_error("修改失败");
					}
				},"json")
        }
        $(".input-daterange").datepicker({
        	format: "yyyy-mm-dd",
        	todayHighlight:true,
       		autoclose: true,
       		todayBtn: true
        });
		$('.chosen-select').chosen();
		
		// 百度地图API功能
	    var map;
	 	function initMap(){
	 		setTimeout("setMap()",300);
	  	}
	 	
	 	function setMap(){
		    if($("[name='lonlat']").val()==""){
		    	var addr=$("[name='address']").val();
		    	if(addr=="")addr="上海市";
		    	// 百度地图API功能
		    	map = new BMap.Map("allmap"); 
				// 创建地址解析器实例
				var myGeo = new BMap.Geocoder();
				// 将地址解析结果显示在地图上,并调整地图视野
				myGeo.getPoint(addr, function(point) {
					if (point) {
						map.centerAndZoom(point,14); 
				 		var marker = new BMap.Marker(point);  // 创建标注
				 		map.addOverlay(marker);               // 将标注添加到地图中
				 		marker.setAnimation(BMAP_ANIMATION_BOUNCE); //跳动的动画
						map.addControl(new BMap.MapTypeControl());//添加地图类型控件
						map.enableScrollWheelZoom();   //启用滚轮放大缩小，默认禁用
						map.enableContinuousZoom();    //启用地图惯性拖拽，默认禁用
					} else {
						layer.msg('车位地址定位失败，已切换至上海市');
						myGeo.getPoint("上海市", function(point) {
							if (point) {
								map.centerAndZoom(point,14); 
						 		var marker = new BMap.Marker(point);  // 创建标注
						 		map.addOverlay(marker);               // 将标注添加到地图中
						 		marker.setAnimation(BMAP_ANIMATION_BOUNCE); //跳动的动画
								map.addControl(new BMap.MapTypeControl());//添加地图类型控件
								map.enableScrollWheelZoom();   //启用滚轮放大缩小，默认禁用
								map.enableContinuousZoom();    //启用地图惯性拖拽，默认禁用
							}
						});
					}
				});
		 	 	//单击获取点击的经纬度
		 	 	map.addEventListener("click",function(e){
		 	 		$("[name='lonlat']").val(e.point.lng+","+e.point.lat);
		 	 		$('#modal-map').modal('hide');
		 	 	});
		    }else{
		    	map = new BMap.Map("allmap");
		    	var lonlat=$("[name='lonlat']").val();
		 		var point = new BMap.Point(lonlat.split(",")[0],lonlat.split(",")[1]);
		 		map.centerAndZoom(point,14); 
		 		var marker = new BMap.Marker(point);  // 创建标注
		 		map.addOverlay(marker);               // 将标注添加到地图中
		 		marker.setAnimation(BMAP_ANIMATION_BOUNCE); //跳动的动画
		 		map.addControl(new BMap.MapTypeControl());//添加地图类型控件
		 		map.enableScrollWheelZoom();   //启用滚轮放大缩小，默认禁用
		 		map.enableContinuousZoom();    //启用地图惯性拖拽，默认禁用
		 	 	//单击获取点击的经纬度
		 	 	map.addEventListener("click",function(e){
		 	 		$("[name='lonlat']").val(e.point.lng+","+e.point.lat);
		 	 		$('#modal-map').modal('hide');
		 	 	});
		    }
	  	}
    </script>
</body>

</html>