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
    <link href="${ctx}/statics/manage/css/plugins/clockpicker/clockpicker.css" rel="stylesheet">
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
                                    <input type="text" class="form-control" name="number" placeholder="编号" value="${dot.number }">
                                </div>
                            </div>
                            <div class="hr-line-dashed"></div>
                            <div class="form-group">
                                <label class="col-sm-2 control-label">名称</label>

                                <div class="col-sm-9">
                                    <input type="text" class="form-control" name="name" placeholder="网点名称" value="${dot.name }">
                                </div>
                            </div>
                            <div class="hr-line-dashed"></div>
                            <div class="form-group">
                                <label class="col-sm-2 control-label">地址</label>

                                <div class="col-sm-9">
                                    <div class="input-group">
                                    	<span class="input-group-addon">省</span>
		                                <select data-placeholder="选择省份..." class="chosen-select" tabindex="2" name="province">
		                                </select>
		                                <span class="input-group-addon">市</span>
		                                <select data-placeholder="选择城市..." class="chosen-select" tabindex="2" name="city">
		                                </select>
		                                <span class="input-group-addon">区</span>
		                                <select data-placeholder="选择区县..." class="chosen-select" tabindex="2" name="region">
		                                </select>
		                            </div>
	                                
                                </div>
                            </div>
                            <div class="hr-line-dashed"></div>
                            <div class="form-group">
                                <label class="col-sm-2 control-label">详细地址</label>

                                <div class="col-sm-9">
                                    <input type="text" class="form-control" name="address" placeholder="详细地址" value="${dot.address }">
                                </div>
                            </div>
                            <div class="hr-line-dashed"></div>
                            <div class="form-group">
                                <label class="col-sm-2 control-label">经纬度</label>

                                <div class="col-sm-9">
                                    <input type="text" class="form-control" name="lonlat" placeholder="经纬度" data-toggle="modal" href="#modal-map" onclick="initMap()" value="${dot.longitude },${dot.latitude}">
                                </div>
                            </div>
                            <div class="hr-line-dashed"></div>
                            <div class="form-group">
                                <label class="col-sm-2 control-label">服务热线</label>

                                <div class="col-sm-9">
                                    <input type="text" class="form-control" name="tel" placeholder="服务热线" value="${dot.tel }">
                                </div>
                            </div>
                            <div class="hr-line-dashed"></div>
                            <div class="form-group">
                                <label class="col-sm-2 control-label">营业时间</label>

                                <div class="col-sm-9">
                                	<div class="input-group input-daterange">
		                                <input class="form-control" name="start_time" type="text" placeholder="开始时间" value="<fmt:formatDate value='${dot.start_time }' pattern='HH:mm'/>">
		                                <span class="input-group-addon">到</span>
		                                <input class="form-control" name="end_time" type="text" placeholder="结束时间" value="<fmt:formatDate value='${dot.end_time }' pattern='HH:mm'/>">
		                            </div>
                                </div>
                            </div>
                            <div class="hr-line-dashed"></div>
                            <div class="form-group">
                                <label class="col-sm-2 control-label">网点图片</label>

                                <div class="col-sm-9">
                                    <input class="fileInput" id="galleryUploadify" type="file" name="file" multiple="multiple" accept="image/*"/>
                                    <div class="gallerysImg" <c:if test="${empty dot.gallerys}"> style="display:none"</c:if>  >   
							                 	<img src="${dot.gallerys }" style="width: 60px;height: 60px">
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
    <script src="${ctx}/statics/manage/js/plugins/clockpicker/clockpicker.js"></script>
    <script src="${ctx}/statics/manage/js/plugins/chosen/chosen.jquery.js"></script>
    <script src="${ctx}/statics/manage/js/jquery.form.js"></script>
    <script src="${ctx}/statics/manage/js/common.js"></script>
    <script src="http://api.map.baidu.com/api?v=2.0&ak=WhXy2DrGL6B6hlzFhixKZTna6pGsYZzi"></script>
    
    <script>
    	$(function(){
    		resetProvince();
    		$("[name='province']").change(function(){
    			resetCity($(this).val());
    		})
    		$("[name='city']").change(function(){
    			resetRegion($(this).val());
    		})
    	})
        function add(){
			var lonlat=$("[name='lonlat']").val();
			var params={"id":"${dot.id}","number":$("[name='number']").val(),"name":$("[name='name']").val(),"address":$("[name='address']").val(),"tel":$("[name='tel']").val(),"start_time":$("[name='start_time']").val(),"end_time":$("[name='end_time']").val(),
			"province_id":$("[name='province']").val(),"province_name":$("[name='province']").find("option:selected").text(),"city_id":$("[name='city']").val(),"city_name":$("[name='city']").find("option:selected").text(),"region_id":$("[name='region']").val(),"region_name":$("[name='region']").find("option:selected").text(),
			"longitude":lonlat.split(",")[0],"latitude":lonlat.split(",")[1],"gallerys":$(".gallerysImg img").attr("src")};
        	$.post("${ctx}/manage/dot/update",params,function(data){
					if(data.code=="0"){
						opt_success("修改成功",0);
					}else{
						opt_error("修改失败");
					}
				},"json")
        }
        function resetProvince(){
        	$("[name='province']").html("<option value=\"\">请选择省份</option>");
        	$.getJSON("${ctx}/manage/regions/1",function(data){
        		for(var i=0;i<data.length;i++){
        			$("[name='province']").append("<option value=\""+data[i].id+"\" hassubinfo=\"true\">"+data[i].name+"</option>");
        		}
        		$("[name='province']").val('${dot.province_id}');
        		$(".chosen-select").trigger("chosen:updated");
        		resetCity($("[name='province']").val());
    		})
        }
        function resetCity(province_id){
        	$("[name='city']").html("<option value=\"\">请选择城市</option>");
        	$(".chosen-select").trigger("chosen:updated");
        	if(province_id!=""){
        		$.getJSON("${ctx}/manage/regions/"+province_id,function(data){
	        		for(var i=0;i<data.length;i++){
	        			$("[name='city']").append("<option value=\""+data[i].id+"\" hassubinfo=\"true\">"+data[i].name+"</option>");
	        		}
	        		$("[name='city']").val('${dot.city_id}');
	        		$(".chosen-select").trigger("chosen:updated");
	        		resetRegion($("[name='city']").val());
	    		})
        	}else{
        		resetRegion($("[name='city']").val());
        	}
        }
        function resetRegion(city_id){
        	$("[name='region']").html("<option value=\"\">请选择区县</option>");
        	$(".chosen-select").trigger("chosen:updated");
       		$.getJSON("${ctx}/manage/regions/"+city_id,function(data){
        		for(var i=0;i<data.length;i++){
        			$("[name='region']").append("<option value=\""+data[i].id+"\" hassubinfo=\"true\">"+data[i].name+"</option>");
        		}
        		$("[name='region']").val('${dot.region_id}');
        		$(".chosen-select").trigger("chosen:updated");
    		})
        }
        $("#galleryUploadify").uploadify({
        	'buttonImage':'${ctx}/statics/manage/plugins/uploadify/uploadify-button.png',
	        'swf': '${ctx}/statics/manage/plugins/uploadify/uploadify.swf',
	        'uploader':'${ctx}/upload',
	        'onFallback' : function() {//检测FLASH失败调用 
	        	alert("您未安装FLASH控件，无法上传图片！请安装FLASH控件后再试。");  
	        },  
	        'onUploadSuccess':function(file, data, response){
	            var jsonData = jQuery.parseJSON(data);
                $(".gallerysImg").css("display","block").find("img").attr("src",jsonData.msg);
        	}
	    });
        $("[name='start_time']").clockpicker({
		    autoclose: true
		});
        $("[name='end_time']").clockpicker({
		    autoclose: true
		});
		$('.chosen-select').chosen();
		
		// 百度地图API功能
	    var map;
	 	function initMap(){
	 		setTimeout("setMap()",300);
	  	}
	 	
	 	function setMap(){
		    if($("[name='lonlat']").val()==""){
		    	var addr=$("[name='province']").find("option:selected").text()+$("[name='city']").find("option:selected").text()+$("[name='region']").find("option:selected").text()+$("[name='address']").val();
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
						layer.msg('网点地址定位失败，已切换至上海市');
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