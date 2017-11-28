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
                                    <input type="text" class="form-control" name="number" placeholder="车辆编号" value="${car.number }">
                                </div>
                            </div>
                            <div class="hr-line-dashed"></div>
                            <div class="form-group">
                                <label class="col-sm-2 control-label">车牌</label>

                                <div class="col-sm-9">
                                    <input type="text" class="form-control" name="license" placeholder="车牌号" value="${car.license }">
                                </div>
                            </div>
                            <div class="hr-line-dashed"></div>
                            <div class="form-group">
                                <label class="col-sm-2 control-label">车名</label>

                                <div class="col-sm-9">
                                    <input type="text" class="form-control" name="name" placeholder="车名" value="${car.name }">
                                </div>
                            </div>
                            <div class="hr-line-dashed"></div>
                            <div class="form-group">
                                <label class="col-sm-2 control-label">品牌</label>

                                <div class="col-sm-9">
                                    <select data-placeholder="选择品牌..." class="chosen-select" tabindex="2" name="brand">
                                    	<option value="">请选择品牌</option>
                                    	<c:forEach items="${brands }" var="b">
                                    		<option value="${b.id }">${b.name }</option>
                                    	</c:forEach>
		                            </select>
                                </div>
                            </div>
                            <div class="hr-line-dashed"></div>
                            <div class="form-group">
                                <label class="col-sm-2 control-label">型号</label>

                                <div class="col-sm-9">
                                     <select data-placeholder="选择型号..." class="chosen-select" tabindex="2" name="model">
		                             </select>
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
                                <label class="col-sm-2 control-label">车位</label>

                                <div class="col-sm-9">
                                    <select data-placeholder="选择车位..." class="chosen-select" tabindex="2" name="park">
		                            </select>
                                </div>
                            </div>
                            <div class="hr-line-dashed"></div>
                            <div class="form-group">
                                <label class="col-sm-2 control-label">价格</label>

                                <div class="col-sm-9">
                                    <input type="text" class="form-control" name="price" placeholder="价格" value="${car.price }">
                                </div>
                            </div>
                            <div class="hr-line-dashed"></div>
                            <div class="form-group">
                                <label class="col-sm-2 control-label">原价</label>

                                <div class="col-sm-9">
                                    <input type="text" class="form-control" name="mkt_price" placeholder="原价" value="${car.mkt_price }">
                                </div>
                            </div>
                            <div class="hr-line-dashed"></div>
                            <div class="form-group">
                                <label class="col-sm-2 control-label">押金</label>

                                <div class="col-sm-9">
                                    <input type="text" class="form-control" name="deposit" placeholder="押金" value="${car.deposit }">
                                </div>
                            </div>
                            <div class="hr-line-dashed"></div>
                            <div class="form-group">
                                <label class="col-sm-2 control-label">年份</label>

                                <div class="col-sm-9">
                                	<div class="input-group date">
		                                <input class="form-control" type="text" name="exfactory_date" placeholder="年份" value="<fmt:formatDate value='${car.exfactory_date }' pattern='yyyy-MM'/>">
		                                <span class="input-group-addon"><i class="fa fa-calendar"></i></span>
									</div>
                                </div>
                            </div>
                            <div class="hr-line-dashed"></div>
                            <div class="form-group">
                                <label class="col-sm-2 control-label">总里程</label>

                                <div class="col-sm-9">
                                    <input type="text" class="form-control" name="total_distance" placeholder="总里程" value="${car.total_distance }">
                                </div>
                            </div>
                            <div class="hr-line-dashed"></div>
                            <div class="form-group">
                                <label class="col-sm-2 control-label">续航能力</label>

                                <div class="col-sm-9">
                                    <input type="text" class="form-control" name="battery_distance" placeholder="续航能力" value="${car.battery_distance }">
                                </div>
                            </div>
                            <div class="hr-line-dashed"></div>
                            <div class="form-group">
                                <label class="col-sm-2 control-label">充电时长</label>

                                <div class="col-sm-9">
                                    <input type="text" class="form-control" name="charge_length" placeholder="充电时长" value="${car.charge_length }">
                                </div>
                            </div>
                            <div class="hr-line-dashed"></div>
                            <div class="form-group">
                                <label class="col-sm-2 control-label">最高车速</label>

                                <div class="col-sm-9">
                                    <input type="text" class="form-control" name="top_speed" placeholder="最高车速" value="${car.top_speed }">
                                </div>
                            </div>
                            <div class="hr-line-dashed"></div>
                            <div class="form-group">
                                <label class="col-sm-2 control-label">长×宽×高(mm)</label>

                                <div class="col-sm-9">
                                    <input type="text" class="form-control" data-mask="9999×9999×9999"  name="body_size" placeholder="车体尺寸" value="${car.body_size }">
                                </div>
                            </div>
                            <div class="hr-line-dashed"></div>
                            <div class="form-group">
                                <label class="col-sm-2 control-label">车型(门/厢/人)</label>

                                <div class="col-sm-9">
                                    <input type="text" class="form-control" name="body_design" placeholder="两门/两厢/4人座" value="${car.body_design }">
                                </div>
                            </div>
                            <div class="hr-line-dashed"></div>
                            <div class="form-group">
                                <label class="col-sm-2 control-label">排序值</label>

                                <div class="col-sm-9">
                                    <input type="text" class="form-control" name="sort" placeholder="排序值" value="${car.sort }">
                                </div>
                            </div>
                            <div class="hr-line-dashed"></div>
                            <div class="form-group">
                                <label class="col-sm-2 control-label">车辆图片</label>

                                <div class="col-sm-9">
                                    <input class="fileInput" id="galleryUploadify" type="file" name="file" multiple="multiple" accept="image/*"/>
                                    <div class="gallerysImg" <c:if test="${empty car.gallerys}"> style="display:none"</c:if>  >   
							                 	<img src="${car.gallerys }" style="width: 60px;height: 60px">
							         </div>
                                </div>
                            </div>
                            <div class="hr-line-dashed"></div>
                            <div class="form-group">
                                <label class="col-sm-2 control-label">供应商</label>

                                <div class="col-sm-9">
                                    <input type="text" class="form-control" name="sup_company" placeholder="供应商" value="${car.sup_company }">
                                </div>
                            </div>
                            <div class="hr-line-dashed"></div>
                            <div class="form-group">
                                <label class="col-sm-2 control-label">供应商联系人</label>

                                <div class="col-sm-9">
                                    <input type="text" class="form-control" name="sup_person" placeholder="联系人" value="${car.sup_person }">
                                </div>
                            </div>
                            <div class="hr-line-dashed"></div>
                            <div class="form-group">
                                <label class="col-sm-2 control-label">供应商电话</label>

                                <div class="col-sm-9">
                                    <input type="text" class="form-control" name="sup_mobile" placeholder="联系电话" value="${car.sup_mobile }">
                                </div>
                            </div>
                            <div class="hr-line-dashed"></div>
                            <div class="form-group">
                                <label class="col-sm-2 control-label">签约时间</label>

                                <div class="col-sm-9">
                                    <div class="input-group input-daterange">
		                                <input class="form-control" name="sign_date" type="text" placeholder="签约时间" value="<fmt:formatDate value='${car.sign_date }' pattern='yyyy-MM-dd'/>">
		                                <span class="input-group-addon">到</span>
		                                <input class="form-control" name="end_date" type="text" placeholder="终止时间" value="<fmt:formatDate value='${car.end_date }' pattern='yyyy-MM-dd'/>">
		                            </div>
                                </div>
                            </div>
                            <div class="hr-line-dashed"></div>
                            <div class="form-group">
                                <div class="col-sm-4 col-sm-offset-4" style="text-align: center;">
                                    <button class="btn btn-primary" type="button" onclick="update()">保存内容</button>
                                    <button class="btn btn-white" type="button" onclick="layer_close()">取消</button>
                                </div>
                            </div>
                        </form>
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
    
    <script>
    	$(function(){
    		$("[name='brand']").val('${car.brand_id}');
    		$("[name='dot']").val('${car.dot_id}');
    		$(".chosen-select").trigger("chosen:updated");
    		resetModels('${car.brand_id}');
    		$("[name='brand']").change(function(){
    			resetModels($(this).val());
    		})
    		resetParks('${car.dot_id}');
    		$("[name='dot']").change(function(){
    			resetParks($(this).val());
    		})
    	})
        function update(){
			var params={"id":'${car.id}',"number":$("[name='number']").val(),"license":$("[name='license']").val(),"name":$("[name='name']").val(),"brand_id":$("[name='brand']").val(),"brand_name":$("[name='brand'] option:selected").text(),"model_id":$("[name='model']").val(),"model_name":$("[name='model'] option:selected").text(),"dot_id":$("[name='dot']").val(),"dot_name":$("[name='dot'] option:selected").text(),"park_id":$("[name='park']").val(),"park_name":$("[name='park'] option:selected").text(),
			"price":$("[name='price']").val(),"mkt_price":$("[name='mkt_price']").val(),"deposit":$("[name='deposit']").val(),"exfactory_date":$("[name='exfactory_date']").val(),"total_distance":$("[name='total_distance']").val(),"battery_distance":$("[name='battery_distance']").val(),"charge_length":$("[name='charge_length']").val(),"top_speed":$("[name='top_speed']").val(),"body_size":$("[name='body_size']").val(),
			"body_design":$("[name='body_design']").val(),"sort":$("[name='sort']").val(),"gallerys":$(".gallerysImg img").attr("src"),"sup_company":$("[name='sup_company']").val(),"sup_person":$("[name='sup_person']").val(),"sup_mobile":$("[name='sup_mobile']").val(),"sign_date":$("[name='sign_date']").val(),"end_date":$("[name='end_date']").val()};
        	$.post("${ctx}/manage/car/update",params,function(data){
					if(data.code=="0"){
						opt_success("修改成功",0);
					}else{
						opt_error("修改失败");
					}
				},"json")
        }
        function resetModels(id){
        	$("[name='model']").html("<option value=\"\">请选择型号</option>");
        	$(".chosen-select").trigger("chosen:updated");
        	$.getJSON("${ctx}/manage/brand/changeTypes/"+id,function(data){
        		for(var i=0;i<data.length;i++){
        			$("[name='model']").append("<option value=\""+data[i].id+"\" hassubinfo=\"true\">"+data[i].name+"</option>");
        		}
        		$("[name='model']").val('${car.model_id}');
        		$(".chosen-select").trigger("chosen:updated");
    		})
        }
        function resetParks(id){
        	$("[name='park']").html("<option value=\"\">请选择车位</option>");
        	$(".chosen-select").trigger("chosen:updated");
        	$.getJSON("${ctx}/manage/car/changeParking/"+id,function(data){
        		for(var i=0;i<data.length;i++){
        			$("[name='park']").append("<option value=\""+data[i].id+"\" hassubinfo=\"true\">"+data[i].name+"</option>");
        		}
        		$("[name='park']").val('${car.park_id}');
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
    </script>
</body>

</html>