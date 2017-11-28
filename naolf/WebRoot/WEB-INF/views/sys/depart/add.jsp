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
    <link href="${ctx}/statics/manage/css/plugins/chosen/chosen.css" rel="stylesheet">
    <link href="${ctx}/statics/manage/css/plugins/awesome-bootstrap-checkbox/awesome-bootstrap-checkbox.css" rel="stylesheet">
</head>

<body>
    <div class="row animated fadeInRight">
            <div class="col-sm-12">
                <div class="ibox float-e-margins">
                    <div class="ibox-content">
                        <form action="" method="post" class="form-horizontal">
                            <div class="form-group">
                                <label class="col-sm-2 control-label">名称</label>

                                <div class="col-sm-9">
                                    <input type="text" class="form-control" name="name" placeholder="部门名称">
                                </div>
                            </div>
                            <div class="hr-line-dashed"></div>
                           <div class="form-group">
                                <label class="col-sm-2 control-label">服务部门</label>
                                <div class="col-sm-9">
                                	<div class="radio radio-success radio-inline">
                                        <input type="radio" id="inlineRadio1" value="0" name="type" checked="">
                                        <label for="inlineRadio1"> 是</label>
                                    </div>
                                    <div class="radio radio-success radio-inline">
                                        <input type="radio" id="inlineRadio2" value="1" name="type">
                                        <label for="inlineRadio2"> 否</label>
                                    </div>
                                </div>
                            </div>
                             <div class="hr-line-dashed"></div>
                            <div class="form-group">
                                <label class="col-sm-2 control-label">描述</label>

                                <div class="col-sm-9">
                                <textarea class="form-control" id="description" name="description" placeholder="描述"></textarea>
                                </div>
                            </div>
                            <div class="hr-line-dashed"></div>
                            <div class="form-group">
                                <label class="col-sm-2 control-label">顺序</label>

                                <div class="col-sm-9">
                                    <input type="text" class="form-control" name="sort" placeholder="顺序">
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
                                    <input type="text" class="form-control" name="address" placeholder="详细地址">
                                </div>
                            </div>
                            <div id="lonlatdiv">
	                            <div class="hr-line-dashed"></div>
	                            <div class="form-group">
	                                <label class="col-sm-2 control-label">经纬度</label>
	
	                                <div class="col-sm-9">
	                                    <input type="text" class="form-control" name="lonlat" placeholder="经纬度" data-toggle="modal" href="#modal-map" onclick="initMap()">
	                                </div>
	                            </div>
                            </div>
                            <div class="hr-line-dashed"></div>
                            <div class="form-group">
                                <label class="col-sm-2 control-label">是否绩效部门</label>

                                <div class="col-sm-9">
                                	<div class="radio radio-success radio-inline">
                                        <input type="radio" id="inlineRadio3" value="1" name="performance_ind" checked="">
                                        <label for="inlineRadio3"> 是</label>
                                    </div>
                                    <div class="radio radio-success radio-inline">
                                        <input type="radio" id="inlineRadio4" value="0" name="performance_ind" checked="checked">
                                        <label for="inlineRadio4"> 否</label>
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
    <script src="${ctx}/statics/manage/js/plugins/chosen/chosen.jquery.js"></script>
    <script src="${ctx}/statics/manage/js/jquery.form.js"></script>
    <script src="${ctx}/statics/manage/js/common.js"></script>
    <script type="text/javascript" src="http://api.map.baidu.com/api?v=2.0&ak=k4FsZbodvrsSv89d0V3nch5kqxlTmZ5r"></script>
    
    <script>
     $('.chosen-select').chosen();
      //radio
		$("[name='type']").change(function(){
			if($(this).attr("value")=="0"){
				$("#lonlatdiv").css("display","block");
			}else{
				$("#lonlatdiv").css("display","none");
			}
		})
        function add(){
            var id=$("[name='province']").val();
            var name=$("[name='province'] option:selected").text();
            var type=$("[name='type']:checked").val();
            var lonlat=$("[name='lonlat']").val();
            if(type==0){
               if(lonlat==null||lonlat==""){
                 opt_error("请选择经纬度");
                 return;
               }
            }
             $(".btn-primary").attr("disabled",true);//防止多次提交
			var params={
			        "parent_id":"${parent_id}","name":$("[name='name']").val(),
			        "description":$("#description").val(), "sort":$("[name='sort']").val(),"grade":${grade},
			         "province_id":$("[name='province']").val(),"province_name":$("[name='province'] option:selected").text(),
			         "city_id":$("[name='city']").val(),"city_name":$("[name='city'] option:selected").text(),
			         "region_id":$("[name='region']").val(),"region_name":$("[name='region'] option:selected").text(),
			         "address":$("[name='address']").val(),"type":$("[name='type']:checked").val(),
			         "performance_ind":$("[name='performance_ind']:checked").val()
			        };
			//添加经纬度
			if(type==0){
			   params={
			        "parent_id":"${parent_id}","name":$("[name='name']").val(),
			        "description":$("#description").val(), "sort":$("[name='sort']").val(),"grade":${grade},
			         "province_id":$("[name='province']").val(),"province_name":$("[name='province'] option:selected").text(),
			         "city_id":$("[name='city']").val(),"city_name":$("[name='city'] option:selected").text(),
			         "region_id":$("[name='region']").val(),"region_name":$("[name='region'] option:selected").text(),
			         "address":$("[name='address']").val(),"type":$("[name='type']:checked").val(),"longitude":lonlat.split(",")[0],"latitude":lonlat.split(",")[1],
			         "performance_ind":$("[name='performance_ind']:checked").val()
			        };
			}
        	$.post("${ctx}/manage/depart/add",params,function(data){
					if(data.code=="0"){
						opt_success("添加成功",0);
					}else{
						opt_error("添加失败");
						$(".btn-primary").attr("disabled",false);//防止多次提交
					}
				},"json")
        }
        $(function(){
    		resetProvince();
    		$("[name='province']").change(function(){
    			resetCity($(this).val());
    		})
    		$("[name='city']").change(function(){
    			resetRegion($(this).val());
    		})
    	})
    	
    	 function resetProvince(){
        	$("[name='province']").html("<option value=\"\">请选择省份</option>");
        	$.getJSON("${ctx}/manage/regions/1",function(data){
        		for(var i=0;i<data.length;i++){
        			$("[name='province']").append("<option value=\""+data[i].id+"\" hassubinfo=\"true\">"+data[i].name+"</option>");
        		}
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
        		$(".chosen-select").trigger("chosen:updated");
    		})
        }
        
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
						layer.msg('地址定位失败，已切换至上海市');
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
		 		map.centerAndZoom(point,18); 
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