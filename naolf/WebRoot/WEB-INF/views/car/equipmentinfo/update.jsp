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
                                <label class="col-sm-2 control-label">设备编号</label>

                                <div class="col-sm-9">
                                    <input type="text" class="form-control" name="equipmentID" disabled="disabled" placeholder="设备编号" value="${equipmentinfo.equipmentID }">
                                </div>
                            </div>
                            <div class="hr-line-dashed"></div>
                            <div class="form-group">
                                <label class="col-sm-2 control-label">组织机构代码</label>

                                <div class="col-sm-9">
                                    <input type="text" class="form-control" name="manufacturerID" placeholder="组织机构代码" value="${equipmentinfo.manufacturerID }">
                                </div>
                            </div>
                            <div class="hr-line-dashed"></div>
                            <div class="form-group">
                                <label class="col-sm-2 control-label">设备型号</label>

                                <div class="col-sm-9">
                                    <input type="text" class="form-control" name="equipmentModel" placeholder="设备型号" value="${equipmentinfo.equipmentModel }">
                                </div>
                            </div>
                            <div class="hr-line-dashed"></div>
                            <div class="form-group">
                                <label class="col-sm-2 control-label">生产日期</label>

                                <div class="col-sm-9">
                                    <input class="form-control" type="text" name="productionDate" placeholder="生产日期" value="${equipmentinfo.productionDate }">
                                </div>
                            </div>
                            <div class="hr-line-dashed"></div>
                            <div class="form-group">
                                <label class="col-sm-2 control-label">设备类型</label>
                                <div class="col-sm-9">
                                    <select class="form-control m-b" name="equipmentType">
                                        <option value="1" <c:if test="${equipmentinfo.equipmentType==1 }">selected</c:if>>直流设备</option>
                                        <option value="2" <c:if test="${equipmentinfo.equipmentType==2 }">selected</c:if>>交流设备</option>
                                        <option value="3" <c:if test="${equipmentinfo.equipmentType==3 }">selected</c:if>>交直流一体设备</option>
                                    </select>
                                </div>
                            </div>
                            <div class="hr-line-dashed"></div>
                            <div class="form-group">
                                <label class="col-sm-2 control-label">经纬度</label>

                                <div class="col-sm-9">
                                    <input type="text" class="form-control" name="lonlat" placeholder="经纬度" data-toggle="modal" href="#modal-map" onclick="initMap()" value=<c:if test="${!empty(equipmentinfo.equipmentLng) }">${equipmentinfo.equipmentLng },${equipmentinfo.equipmentLat}</c:if>>
                                </div>
                            </div>
                            <div class="hr-line-dashed"></div>
                            <div class="form-group">
                                <label class="col-sm-2 control-label">所属充电站</label>
                                <div class="col-sm-9">
                                    <select data-placeholder="请选择充电站" class="chosen-select" tabindex="2" name="stationID">
                                    	<c:forEach items="${stations }" var="s">
                                    		<option value="${s.stationID }">【编号：${s.stationID }】${s.stationName }</option>
                                    	</c:forEach>
		                            </select>
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
    	$("[name='stationID']").val('${equipmentinfo.stationID}');
    	$(".chosen-select").trigger("chosen:updated");
        function add(){
			var lonlat=$("[name='lonlat']").val();
			var params={"equipmentID":$("[name='equipmentID']").val(),"manufacturerID":$("[name='manufacturerID']").val(),"equipmentModel":$("[name='equipmentModel']").val(),"productionDate":$("[name='productionDate']").val(),
			"equipmentType":$("[name='equipmentType']").val(),"stationID":$("[name='stationID']").val(),"equipmentLng":lonlat.split(",")[0],"equipmentLat":lonlat.split(",")[1]};
        	$.post("${ctx}/manage/equipmentinfo/update",params,function(data){
					if(data.code=="0"){
						opt_success("修改成功",0);
					}else{
						opt_error("修改失败");
					}
				},"json")
        }
        $("[name='productionDate']").datepicker({
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
		    	// 百度地图API功能
		    	map = new BMap.Map("allmap"); 
				// 创建地址解析器实例
				var myGeo = new BMap.Geocoder();
				// 将地址解析结果显示在地图上,并调整地图视野
				myGeo.getPoint("上海市外高桥", function(point) {
					if (point) {
						map.centerAndZoom(point,14); 
				 		var marker = new BMap.Marker(point);  // 创建标注
				 		map.addOverlay(marker);               // 将标注添加到地图中
				 		marker.setAnimation(BMAP_ANIMATION_BOUNCE); //跳动的动画
						map.addControl(new BMap.MapTypeControl());//添加地图类型控件
						map.enableScrollWheelZoom();   //启用滚轮放大缩小，默认禁用
						map.enableContinuousZoom();    //启用地图惯性拖拽，默认禁用
					} else {
						layer.msg('网点地址定位失败，已切换至上海市');
						myGeo.getPoint("上海市", function(point) {
							if (point) {
								map.centerAndZoom(point,15); 
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
		 		map.centerAndZoom(point,15); 
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