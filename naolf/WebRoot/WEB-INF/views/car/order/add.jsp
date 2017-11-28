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
    <link href="${ctx}/statics/manage/css/plugins/chosen/chosen.css" rel="stylesheet">
    <link href="${ctx}/statics/manage/plugins/bootstrap-datetimepicker/css/bootstrap-datetimepicker.css" rel="stylesheet">
    <link href="${ctx}/statics/manage/plugins/bootstrap-3-input-spinner/css/spinner.css" rel="stylesheet">
    
</head>

<body>
    <div class="row animated fadeInRight" style="margin-bottom: 20px;">
            <div class="col-sm-12">
                <div class="wrapper wrapper-content">
                   
                   <div class="row">
                   	  <div class="col-sm-12" id="cluster_info">
                           <dl class="dl-horizontal">
							   <dt>选择会员：</dt>
                               <dd>
                               	  <select data-placeholder="请选择会员..." class="chosen-select" tabindex="2" name="members">
		                                <option value="">请选择会员...</option>
		                                <c:forEach items="${members }" var="m">
		                                <option value="${m.id }">【会员编号:${m.number }】【会员卡号:${m.card_number}】【手机号:${m.mobile }】${m.name }</option>
		                                </c:forEach>
		                           </select>
                               </dd>
                           </dl>
                       </div>
                       
                       <div class="col-sm-6">
                           <dl class="dl-horizontal">
							   <dt>取车网点：</dt>
                               <dd>
                               		<select data-placeholder="选择取车网点..." class="chosen-select" style="width:300px" tabindex="2" name="get_dot">
		                                <option value="">选择取车网点...</option>
		                                <c:forEach items="${dots }" var="d">
		                                <option value="${d.id },${d.longitude},${d.latitude}">【${d.number}】${d.name }</option>
		                                </c:forEach>
		                            </select>
		                       </dd>
		                       <dt>用车时间：</dt>
                               <dd>
	                               	<input class="form-control input-sm" type="text" name="rent_star_time" placeholder="用车时间" style="width:300px">
                               </dd>
                           </dl>
                       </div>
                       <div class="col-sm-6" id="cluster_info">
                           <dl class="dl-horizontal">
							   <dt>还车网点：</dt>
                               <dd>
                               	  <select data-placeholder="选择还车网点..." class="chosen-select" style="width:300px" tabindex="2" name="return_dot">
		                                <option value="">选择还车网点...</option>
		                                <c:forEach items="${dots }" var="d">
		                                <option value="${d.id },${d.longitude},${d.latitude}">【${d.number}】${d.name }</option>
		                                </c:forEach>
		                          </select>
                               </dd>
							   <dt>还车时间：</dt>
                               <dd>
	                               	<input class="form-control input-sm" type="text" name="rent_end_time" placeholder="用车时间" style="width:300px">
                               </dd>
                           </dl>
                       </div>
                       
                       <div class="col-sm-12" id="cluster_info">
                           <dl class="dl-horizontal">
							   <dt>选择车辆：</dt>
                               <dd>
                               	  <select data-placeholder="请选择车辆..." class="chosen-select" tabindex="2" name="cars">
		                                <option value="">请选择车辆...</option>
		                           </select>
		                           <a class="btn btn-white btn-bitbucket" onclick="refresh()"><i class="fa fa-refresh"></i> 刷新</a>
                               </dd>
                           </dl>
                       </div>
                       
                       <div class="col-sm-6">
                           <dl class="dl-horizontal">
                           	   <dt>租赁天数：</dt>
                               <dd>
	                               	<span id="rent_days">0</span>天
                               </dd>
							   <dt>送车上门：</dt>
                               <dd>
                               		<input type="checkbox" class="js-switch" name="get_type"/>
		                       </dd>
		                       <dt>送车地址：</dt>
                               <dd>
	                               <input type="text" class="form-control input-sm" style="width: 300px" name="get_address" placeholder="送车地址">
                               </dd>
                               <dt>送车坐标：</dt>
                               <dd>
	                               <input type="text" class="form-control input-sm" readonly="readonly"  style="width: 300px;cursor:pointer;" name="get_lnglat" placeholder="点击这里，在地图上选取经纬度" onclick="initMap('get')">
                               </dd>
                               <dt>送车费用：</dt>
                               <dd>
	                               	&yen;${distribution_amount }&nbsp;&nbsp;备注：${distribution_range }公里内${distribution_amount }元，超过${distribution_range }公里不予配送。
                               </dd>
                               <dt>保险费用：</dt>
                               <dd>
	                               	&yen;${insurance }
                               </dd>
                               <dt>销售折扣：</dt>
                               <dd>
	                               <input type="text" class="form-control input-sm" style="width: 300px" name="sale_amount" placeholder="销售折扣价" value="0.00">
                               </dd>
                               
                           </dl>
                       </div>
                       <div class="col-sm-6" id="cluster_info">
                           <dl class="dl-horizontal">
                           	   <dt>车辆租金：</dt>
                               <dd>
	                               	&yen;<span id="car_price">0</span>
                               </dd>
							   <dt>上门取车：</dt>
                               <dd>
                               		<input type="checkbox" class="js-switch" name="return_type"/>
		                       </dd>
		                       <dt>取车地址：</dt>
                               <dd>
	                               <input type="text" class="form-control input-sm" style="width: 300px" name="return_address" placeholder="取车地址">
                               </dd>
                               <dt>取车坐标：</dt>
                               <dd>
	                               <input type="text" class="form-control input-sm" readonly="readonly"  style="width: 300px;cursor:pointer;" name="return_lnglat" placeholder="点击这里，在地图上选取经纬度" onclick="initMap('ret')">
                               </dd>
                               <dt>取车费用：</dt>
                               <dd>
	                               	&yen;${distribution_amount }&nbsp;&nbsp;备注：${distribution_range }公里内${distribution_amount }元，超过${distribution_range }公里不予配送。
                               </dd>
                               <dt>保险份数：</dt>
                               <dd>
	                               <div class="input-group spinner">
								    <input type="text" class="form-control input-sm" value="0" name="ins_num" disabled="disabled">
								    <div class="input-group-btn-vertical">
								      <button class="btn btn-default" type="button" disabled="disabled"><i class="fa fa-caret-up"></i></button>
								      <button class="btn btn-default" type="button" disabled="disabled"><i class="fa fa-caret-down"></i></button>
								    </div>
								  </div>
                               </dd>
                               <dt style="font-size: 18px;">总&nbsp;&nbsp;&nbsp;计：</dt>
                               <dd style="font-size: 18px;">
	                               &yen;<span id="amount">0</span>
                               </dd>
                               
                           </dl>
                       </div>
                       
                   </div>
                   
                	<div class="well"><textarea class="form-control" name="remark" style="resize: none;" placeholder="下单备注"></textarea></div>
                	
                	<div class="form-group">
                         <div class="col-sm-4 col-sm-offset-4" style="text-align: center;">
                             <button class="btn btn-primary" type="button" onclick="add()">提交订单</button>
                             <button class="btn btn-white" type="button" onclick="layer_close()">取消</button>
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
    <script src="${ctx}/statics/manage/js/plugins/chosen/chosen.jquery_.js"></script>
    <script src="${ctx}/statics/manage/plugins/bootstrap-datetimepicker/js/bootstrap-datetimepicker.js"></script>
    <script src="${ctx}/statics/manage/plugins/bootstrap-datetimepicker/js/locales/bootstrap-datetimepicker.zh-CN.js"></script>
    <script src="${ctx}/statics/manage/js/jquery.form.js"></script>
    <script src="${ctx}/statics/manage/js/common.js"></script>
    <script src="http://api.map.baidu.com/api?v=2.0&ak=WhXy2DrGL6B6hlzFhixKZTna6pGsYZzi"></script>
    
    <script>
        $(document).ready(function(){$("#ibox-content").addClass("ibox-content");$("#vertical-timeline").removeClass("light-timeline");$("#vertical-timeline").addClass("dark-timeline");$(".fancybox").fancybox({openEffect:"none",closeEffect:"none"});$('.chosen-select').chosen();
        	$('.spinner .btn:first-of-type').on('click', function() {
				if(parseInt($('.spinner input').val())==10)return;
			    $('.spinner input').val( parseInt($('.spinner input').val()) + 1);
			    calAmount();
			});
			$('.spinner .btn:last-of-type').on('click', function() {
				if(parseInt($('.spinner input').val())==0)return;
			    $('.spinner input').val( parseInt($('.spinner input').val()) - 1);
			    calAmount();
			});
        });
    	$("[name='members']").css("width",$(".row").width()/2+280);
    	$("[name='cars']").css("width",$(".row").width()/2+280-68);
    	// 百度地图API功能
	    var map;
	 	function initMap(type){
	 		if($("[name='get_dot']").val()==""){layer.msg('请选择取/还网点', function(){});return;}
	 		$('#modal-map').modal();
	 		setTimeout("setMap("+$("[name='get_dot']").val().split(",")[1]+","+$("[name='get_dot']").val().split(",")[2]+",'"+type+"')",300);
	  	}
	 	function setMap(lng,lat,type){
		    map = new BMap.Map("allmap");
	 		var point = new BMap.Point(lng,lat);
	 		map.centerAndZoom(point,13); 
	 		var marker = new BMap.Marker(point);  // 创建标注
	 		map.addOverlay(marker);               // 将标注添加到地图中
	 		marker.setAnimation(BMAP_ANIMATION_BOUNCE); //跳动的动画
	 		map.addControl(new BMap.MapTypeControl());//添加地图类型控件
	 		map.enableScrollWheelZoom();   //启用滚轮放大缩小，默认禁用
	 		map.enableContinuousZoom();    //启用地图惯性拖拽，默认禁用
	 		var circle = new BMap.Circle(point,${distribution_range }*1000,{fillColor:"blue", strokeWeight: 1 ,fillOpacity: 0.3, strokeOpacity: 0.3});
    		map.addOverlay(circle);
	 		// 覆盖区域图层测试
			var stCtrl = new BMap.PanoramaControl(); //构造全景控件
			stCtrl.setOffset(new BMap.Size(20, 40));
			map.addControl(stCtrl);//添加全景控件
			//单击获取点击的经纬度
		 	 	map.addEventListener("click",function(e){
		 	 		var s=getDistance($("[name='get_dot']").val().split(",")[1],$("[name='get_dot']").val().split(",")[2],e.point.lng,e.point.lat);
		 	 		if(s>${distribution_range }){layer.msg('目标距离为'+s+'公里，超过最大配送距离，请重新选择', function(){});return;}
		 	 		if(type=="get"){
		 	 			$("[name='get_lnglat']").val(e.point.lng+","+e.point.lat);
		 	 		}else{
		 	 			$("[name='return_lnglat']").val(e.point.lng+","+e.point.lat);
		 	 		}
		 	 		$('#modal-map').modal('hide');
		 	 	});
	  	}
	  	//时间控件
        $("[name='rent_star_time'],[name='rent_end_time']").datetimepicker({
            language:  'zh-CN',
			format: "yyyy-mm-dd hh:ii",
			maxView:3,
			minuteStep:1,
       		autoclose: true,
       		todayBtn: true,
       		setDaysOfWeekDisabled:[0,6]
    	});
	  	//时间选择时间
	  	$("[name='rent_star_time']").datetimepicker().on("changeDate", function(ev){
	  		var end_time=$("[name='rent_end_time']").val();
	  		if(end_time!=""){
	  			if(new Date($(this).val())>=new Date(end_time)){
	  				$(this).val("");
	  				layer.msg('用车时间不能大于还车时间', function(){});
	  			}else{
	  				calAmount();
	  			}
	  		}
		});
		$("[name='rent_end_time']").datetimepicker().on("changeDate", function(ev){
		    var star_time=$("[name='rent_star_time']").val();
	  		if(star_time!=""){
	  			if(new Date(star_time)>=new Date($(this).val())){
	  				$(this).val("");
	  				layer.msg('还车时间不能小于用车时间', function(){});
	  			}else{
	  				calAmount();
	  			}
	  		}
		});
	  	//刷新车辆
	  	function refresh(){
	  		var dot_id=$("[name='get_dot']").val().split(",")[0];
	  		var star_time=$("[name='rent_star_time']").val();
	  		var end_time=$("[name='rent_end_time']").val();
	  		if(dot_id==""){layer.msg('请选择取/还网点', function(){});return;}
	  		if(star_time==""){layer.msg('请输入用车时间', function(){});return;}
	  		if(end_time==""){layer.msg('请输入还车时间', function(){});return;}
	  		var index = layer.load(1); //换了种风格
	  		$("[name='cars']").html("<option value=\"\">请选择车辆...</option>");
        	$(".chosen-select").trigger("chosen:updated");
	  		$.getJSON("${ctx}/manage/car/refresh?dot_id="+dot_id+"&startDate="+star_time+"&endDate="+end_time,function(data){
	  			layer.close(index);
	  			if(data.length==0){
	  				layer.msg('没有可租车辆了', {icon: 5});
	  				return false;
	  			}
	  			layer.msg('加载完成', {icon: 1,time: 1000});
	  			for(var i=0;i<data.length;i++){
        			$("[name='cars']").append("<option value=\""+data[i].id+","+data[i].price+"\">【编号:"+data[i].number+"】【车牌:"+data[i].license+"】"+data[i].name+(data[i].rent_status==1?'[已被预订]':'')+"</option>");
        		}
        		$(".chosen-select").trigger("chosen:updated");
			})
	  	}
	  	//网点联动
	  	$("[name='get_dot'],[name='return_dot']").change(function(){
	  		$("[name='get_dot'],[name='return_dot']").val($(this).val());
	  		$(".chosen-select").trigger("chosen:updated");
	  	})
	  	//车辆联动
	  	$("[name='cars']").change(function(){
	  		calAmount();
	  	})
	  	//取车/还车
	  	$("[name='get_type'],[name='return_type']").change(function(){
	  		calAmount();
	  	})
	  	//提交订单
	  	function add(){
	  		//表单验证
	  		var mem_id=$("[name='members']").val();
	  		var dot_id=$("[name='get_dot']").val().split(",")[0];
	  		var star_time=$("[name='rent_star_time']").val();
	  		var end_time=$("[name='rent_end_time']").val();
	  		var car_id=$("[name='cars']").val().split(",")[0];
	  		var get_address=$("[name='get_address']").val();
	  		var return_address=$("[name='return_address']").val();
	  		var get_lnglat=$("[name='get_lnglat']").val();
	  		var return_lnglat=$("[name='return_lnglat']").val();
	  		var remark=$("[name='remark']").val();
	  		var sale_amount=$("[name='sale_amount']").val();
	  		if(mem_id==""){layer.msg('请选择用车人', function(){});return;}
	  		if(dot_id==""){layer.msg('请选择取/还网点', function(){});return;}
	  		if(star_time==""){layer.msg('请输入用车时间', function(){});return;}
	  		if(end_time==""){layer.msg('请输入还车时间', function(){});return;}
	  		if(car_id==""){layer.msg('请选择车辆', function(){});return;}
	  		if(sale_amount==""){layer.msg('请输入销售折扣', function(){});return;}
	  		var params;
	  		if($("[name='get_type']").is(":checked")&&$("[name='return_type']").is(":checked")){
	  			if(get_address==""||return_address==""||get_lnglat==""||return_lnglat==""){layer.msg('请将取/还地址及经纬度填写完整', function(){});return;}
	  			params={"car_id":car_id,"mem_id":mem_id,"rent_star_time":star_time,"rent_end_time":end_time,"amount":calAmount(),"insurance":${insurance },"ins_num":$("[name='ins_num']").val(),
	  			"get_type":1,"return_type":1,"get_amount":${distribution_amount},"return_amount":${distribution_amount},"get_dot_id":dot_id,"return_dot_id":dot_id,"get_address":get_address,
	  			"get_lng":get_lnglat.split(",")[0],"get_lat":get_lnglat.split(",")[1],"return_address":return_address,"return_lng":return_lnglat.split(",")[0],"return_lat":return_lnglat.split(",")[1],"sale_amount":sale_amount,"remark":remark}
	  		}
	  		if(!$("[name='get_type']").is(":checked")&&$("[name='return_type']").is(":checked")){
	  			if(return_address==""||return_lnglat==""){layer.msg('请将还车地址及经纬度填写完整', function(){});return;}
	  			params={"car_id":car_id,"mem_id":mem_id,"rent_star_time":star_time,"rent_end_time":end_time,"amount":calAmount(),"insurance":${insurance },"ins_num":$("[name='ins_num']").val(),
	  			"get_type":0,"return_type":1,"return_amount":${distribution_amount},"get_dot_id":dot_id,"return_dot_id":dot_id,
	  			"return_address":return_address,"return_lng":return_lnglat.split(",")[0],"return_lat":return_lnglat.split(",")[1],"sale_amount":sale_amount,"remark":remark}
	  		}
	  		if($("[name='get_type']").is(":checked")&&!$("[name='return_type']").is(":checked")){
	  			if(get_address==""||get_lnglat==""){layer.msg('请将取车地址及经纬度填写完整', function(){});return;}
	  			params={"car_id":car_id,"mem_id":mem_id,"rent_star_time":star_time,"rent_end_time":end_time,"amount":calAmount(),"insurance":${insurance },"ins_num":$("[name='ins_num']").val(),
	  			"get_type":1,"return_type":0,"get_amount":${distribution_amount},"get_dot_id":dot_id,"return_dot_id":dot_id,"get_address":get_address,
	  			"get_lng":get_lnglat.split(",")[0],"get_lat":get_lnglat.split(",")[1],"sale_amount":sale_amount,"remark":remark}
	  		}
	  		if(!$("[name='get_type']").is(":checked")&&!$("[name='return_type']").is(":checked")){
	  			params={"car_id":car_id,"mem_id":mem_id,"rent_star_time":star_time,"rent_end_time":end_time,"amount":calAmount(),"insurance":${insurance },"ins_num":$("[name='ins_num']").val(),
	  			"get_type":0,"return_type":0,"get_dot_id":dot_id,"return_dot_id":dot_id,"sale_amount":sale_amount,"remark":remark}
	  		}
	  		//提交订单
	  		layer.confirm('确认要提交吗？', function(index){
			  layer.close(index);
			  $.post("${ctx}/manage/order/add",params,function(data){
				 if(data.code=="0"){
						opt_success("提交成功",0);
					}else{
						opt_error(data.msg);
					}
			  },"json")
		   });
	  	}
	  	//计算价格
	  	function calAmount(){
	  		var amount=0,rent_days=0,unit_price=0,get_amount=0,return_amount=0;
	  		if($("[name='rent_star_time']").val()!=""&&$("[name='rent_end_time']").val()!="")rent_days=getIntervalDays($("[name='rent_star_time']").val().split(" ")[0],$("[name='rent_end_time']").val().split(" ")[0]);
	  		if($("[name='cars']").val()!="")unit_price=$("[name='cars']").val().split(",")[1];
	  		if($("[name='get_type']").is(":checked"))get_amount=${distribution_amount};
	  		if($("[name='return_type']").is(":checked"))return_amount=${distribution_amount};
	  		$("#rent_days").html(rent_days);
	  		$("[name='ins_num']").val(rent_days)
	  		$("#car_price").html(rent_days*unit_price);
	  		amount=(rent_days*unit_price*100+get_amount*100+return_amount*100+${insurance }*$("[name='ins_num']").val()*100-$("[name='sale_amount']").val()*100)/100;
	  		$("#amount").html(amount);
	  		return amount;
	  	}
    </script>
</body>

</html>