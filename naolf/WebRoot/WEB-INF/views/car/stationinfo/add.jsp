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
                                    <input type="text" class="form-control" name="stationID" placeholder="编号">
                                </div>
                            </div>
                            <div class="hr-line-dashed"></div>
                            <div class="form-group">
                                <label class="col-sm-2 control-label">运营商</label>

                                <div class="col-sm-9">
                                    <select data-placeholder="选择运营商..." class="chosen-select" tabindex="2" name="operatorID">
                                    	<c:forEach items="${operators }" var="o">
                                    		<option value="${o.operatorID }">${o.operatorName }</option>
                                    	</c:forEach>
		                            </select>
                                </div>
                            </div>
                            <div class="hr-line-dashed"></div>
                            <div class="form-group">
                                <label class="col-sm-2 control-label">名称</label>

                                <div class="col-sm-9">
                                    <input type="text" class="form-control" name="stationName" placeholder="名称" >
                                </div>
                            </div>
                            <div class="hr-line-dashed"></div>
                            <div class="form-group">
                                <label class="col-sm-2 control-label">地址</label>

                                <div class="col-sm-9">
                                    <input type="text" class="form-control" name="address" placeholder="地址" >
                                </div>
                            </div>
                            <div class="hr-line-dashed"></div>
                            <div class="form-group">
                                <label class="col-sm-2 control-label">经纬度</label>

                                <div class="col-sm-9">
                                    <input type="text" class="form-control" name="lonlat" placeholder="经纬度" data-toggle="modal" href="#modal-map" onclick="initMap()" >
                                </div>
                            </div>
                            <div class="hr-line-dashed"></div>
                            <div class="form-group">
                                <label class="col-sm-2 control-label">服务电话</label>

                                <div class="col-sm-9">
                                    <input type="text" class="form-control" name="serviceTel" placeholder="服务电话" >
                                </div>
                            </div>
                             <div class="hr-line-dashed"></div>
                            <div class="form-group">
                                <label class="col-sm-2 control-label">站点电话</label>

                                <div class="col-sm-9">
                                    <input type="text" class="form-control" name="stationTel" placeholder="站点电话" >
                                </div>
                            </div>
                            <div class="hr-line-dashed"></div>
                            <div class="form-group">
                                <label class="col-sm-2 control-label">站点类型</label>

                                <div class="col-sm-9">
                                    <select data-placeholder="站点类型..." class="chosen-select" tabindex="2" name="stationType">
                                    	<option value="1">公共</option>
                                    	<option value="100">公交（专用）</option>
                                    	<option value="101">环卫（专用）</option>
                                    	<option value="102">物流（专用）</option>
                                    	<option value="103">出租车（专用）</option>
                                    	<option value="255">其他</option>
		                            </select>
                                </div>
                            </div>
                            <div class="hr-line-dashed"></div>
                            <div class="form-group">
                                <label class="col-sm-2 control-label">建设场所</label>

                                <div class="col-sm-9">
                                    <select data-placeholder="建设场所..." class="chosen-select" tabindex="2" name="construction">
                                    	<option value="1">居民区</option>
                                    	<option value="2">公共机构</option>
                                    	<option value="3">企事业单位</option>
                                    	<option value="4">写字楼</option>
                                    	<option value="5">工业园区</option>
                                    	<option value="6">交通枢纽</option>
                                    	<option value="7">大型文体设施</option>
                                    	<option value="8">城市绿地</option>
                                    	<option value="9">大型建筑配建停车场</option>
                                    	<option value="10">路边停车位</option>
                                    	<option value="11">城际高速服务区</option>
                                    	<option value="255">其他</option>
		                            </select>
                                </div>
                            </div>
                            <div class="hr-line-dashed"></div>
                            <div class="form-group">
                                <label class="col-sm-2 control-label">状态</label>

                                <div class="col-sm-9">
                                    <select data-placeholder="站点状态..." class="chosen-select" tabindex="2" name="stationStatus">
                                    	<option value="50">正常使用</option>
                                    	<option value="1">建设中</option>
                                    	<option value="6">维护中</option>
                                    	<option value="5">关闭下线</option>
                                    	<option value="0">未知</option>
		                            </select>
                                </div>
                            </div>
                            <div class="hr-line-dashed"></div>
                            <div class="form-group">
                                <label class="col-sm-2 control-label">站点引导</label>

                                <div class="col-sm-9">
                                    <input type="text" class="form-control" name="siteGuide" placeholder="站点引导" >
                                </div>
                            </div>
                             <div class="hr-line-dashed"></div>
                            <div class="form-group">
                                <label class="col-sm-2 control-label">车位数量</label>

                                <div class="col-sm-9">
                                    <input type="text" class="form-control" name="parkNums" placeholder="车位数量"  >
                                </div>
                            </div>
                             <div class="hr-line-dashed"></div>
                            <div class="form-group">
                                <label class="col-sm-2 control-label">使用车型描述</label>

                                <div class="col-sm-9">
                                    <input type="text" class="form-control" name="matchCars" placeholder="使用车型描述"  >
                                </div>
                            </div>
                             <div class="hr-line-dashed"></div>
                            <div class="form-group">
                                <label class="col-sm-2 control-label">车位楼层及数量描述</label>

                                <div class="col-sm-9">
                                    <input type="text" class="form-control" name="parkInfo" placeholder="车位楼层及数量描述"  >
                                </div>
                            </div>
                            <div class="hr-line-dashed"></div>
                            <div class="form-group">
                                <label class="col-sm-2 control-label">充电费</label>

                                <div class="col-sm-9">
                                    <input type="text" class="form-control" name="elePrice" placeholder="充电费（元/度）"  >
                                </div>
                            </div>
                              <div class="hr-line-dashed"></div>
                            <div class="form-group">
                                <label class="col-sm-2 control-label">服务费</label>

                                <div class="col-sm-9">
                                    <input type="text" class="form-control" name="serPrice" placeholder="服务费（元/度）"  >
                                </div>
                            </div>
                            <div class="hr-line-dashed"></div>
                            <div class="form-group">
                                <label class="col-sm-2 control-label">充电费描述</label>

                                <div class="col-sm-9">
                                    <input type="text" class="form-control" name="electricityFee" placeholder="充电费描述"  >
                                </div>
                            </div>
                              <div class="hr-line-dashed"></div>
                            <div class="form-group">
                                <label class="col-sm-2 control-label">服务费率描述</label>

                                <div class="col-sm-9">
                                    <input type="text" class="form-control" name="serviceFee" placeholder="服务费率描述"  >
                                </div>
                            </div>
                              <div class="hr-line-dashed"></div>
                            <div class="form-group">
                                <label class="col-sm-2 control-label">停车费率描述</label>

                                <div class="col-sm-9">
                                    <input type="text" class="form-control" name="parkFee" placeholder="停车费率描述"  >
                                </div>
                            </div>
                              <div class="hr-line-dashed"></div>
                            <div class="form-group">
                                <label class="col-sm-2 control-label">是否支持预约</label>

                                <div class="col-sm-9">
                                    <select data-placeholder="是否支持预约..." class="chosen-select" tabindex="2" name="supportOrder">
                                    	<option value="0">不支持预约</option>
                                    	<option value="1">支持预约</option>
		                            </select>
                                </div>
                            </div>
                              <div class="hr-line-dashed"></div>
                            <div class="form-group">
                                <label class="col-sm-2 control-label">支付方式</label>

                                <div class="col-sm-9">
                                    <select data-placeholder="支付方式" class="chosen-select" tabindex="2" name="payment">
                                    	<option value="刷卡">刷卡（电子钱包类卡为刷卡）</option>
                                    	<option value="线上">线上（身份鉴权卡、微信/支付宝、APP为线上）</option>
                                    	<option value="现金">现金</option>
		                            </select>
                                </div>
                            </div>
                             <div class="hr-line-dashed"></div>
                            <div class="form-group">
                                <label class="col-sm-2 control-label">营业时间</label>

                                <div class="col-sm-9">
                                    <input type="text" class="form-control" name=busineHours placeholder="营业时间描述">
                                </div>
                            </div>
                            <div class="hr-line-dashed"></div>
                            <div class="form-group">
                                <label class="col-sm-2 control-label">站点照片</label>

                                <div class="col-sm-9">
                                    <input class="fileInput" id="galleryUploadify" type="file" name="file" multiple="multiple" accept="image/*"/>
                                    <div class="gallerysImg" style="display:none" >   
							                 	<img src="" style="width: 60px;height: 60px">
							         </div>
                                </div>
                            </div>
                            <div class="hr-line-dashed"></div>
                            <div class="form-group">
                                <label class="col-sm-2 control-label">备注</label>

                                <div class="col-sm-9">
                                    <textarea class="form-control" rows="5" name="remark" style="resize: none" placeholder="备注信息"></textarea>
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
        function add(){
			var lonlat=$("[name='lonlat']").val();
			var params={"stationID":$("[name='stationID']").val(),"operatorID":$("[name='operatorID']").val(),"stationName":$("[name='stationName']").val(),"address":$("[name='address']").val(),"serviceTel":$("[name='serviceTel']").val(),"stationTel":$("[name='stationTel']").val(),
			"stationType":$("[name='stationType']").val(),"stationStatus":$("[name='stationStatus']").val(),"construction":$("[name='construction']").val(),"supportOrder":$("[name='supportOrder']").val(),
			"parkNums":$("[name='parkNums']").val(),"siteGuide":$("[name='siteGuide']").val(),"matchCars":$("[name='matchCars']").val(),"parkInfo":$("[name='parkInfo']").val(),"busineHours":$("[name='busineHours']").val(),"elePrice":$("[name='elePrice']").val(),"serPrice":$("[name='serPrice']").val(),
			"electricityFee":$("[name='electricityFee']").val(),"serviceFee":$("[name='serviceFee']").val(),"parkFee":$("[name='parkFee']").val(),"payment":$("[name='payment']").val(),"remark":$("[name='remark']").val(),
			"stationLng":lonlat.split(",")[0],"stationLat":lonlat.split(",")[1],"pictures":$(".gallerysImg img").attr("src")};
        	$.post("${ctx}/manage/stationinfo/add",params,function(data){
					if(data.code=="0"){
						opt_success("添加成功",0);
					}else{
						opt_error("添加失败");
					}
				},"json")
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
        $(".date").datepicker({
        	minViewMode:1,
        	format: "yyyy-mm",
        	todayHighlight:true,
       		autoclose: true,
       		todayBtn: true
        });
        $(".input-daterange").datepicker({
        	format: "yyyy-mm-dd",
        	todayHighlight:true,
       		autoclose: true,
       		todayBtn: true
        });
        $('.chosen-select').chosen();
        
        // 百度地图API功能
	    // 百度地图API功能
	    var map;
	 	function initMap(){
	 		setTimeout("setMap()",300);
	  	}
	 	
	 	function setMap(){
		    if($("[name='lonlat']").val()==""){
		    	var addr=$("[name='address']").val();
		    	if(addr=="")addr="上海市外高桥";
		    	// 百度地图API功能
		    	map = new BMap.Map("allmap"); 
				// 创建地址解析器实例
				var myGeo = new BMap.Geocoder();
				// 将地址解析结果显示在地图上,并调整地图视野
				myGeo.getPoint(addr, function(point) {
					if (point) {
						map.centerAndZoom(point,15); 
				 		var marker = new BMap.Marker(point);  // 创建标注
				 		map.addOverlay(marker);               // 将标注添加到地图中
				 		marker.setAnimation(BMAP_ANIMATION_BOUNCE); //跳动的动画
						map.addControl(new BMap.MapTypeControl());//添加地图类型控件
						map.enableScrollWheelZoom();   //启用滚轮放大缩小，默认禁用
						map.enableContinuousZoom();    //启用地图惯性拖拽，默认禁用
					} else {
						layer.msg('车位地址定位失败，已切换至上海市');
						myGeo.getPoint("上海市外高桥", function(point) {
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