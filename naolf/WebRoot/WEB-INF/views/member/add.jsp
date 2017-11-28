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
    <link href="${ctx}/statics/manage/css/plugins/sweetalert/sweetalert.css" rel="stylesheet">
    <link href="${ctx}/statics/manage/plugins/uploadify/uploadify.css" rel="stylesheet">
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
                                    <input type="text" class="form-control" name="number">
                                </div>
                            </div>
                            <div class="hr-line-dashed"></div>
                            <div class="form-group">
                                <label class="col-sm-2 control-label">手机号</label>

                                <div class="col-sm-9">
                                    <input type="text" class="form-control" name="mobile">
                                </div>
                            </div>
                            <div class="hr-line-dashed"></div>
                            <div class="form-group">
                                <label class="col-sm-2 control-label">姓名</label>

                                <div class="col-sm-9">
                                    <input type="text" class="form-control" name="name">
                                </div>
                            </div>
                            <div class="hr-line-dashed"></div>
                            <div class="form-group">
                                <label class="col-sm-2 control-label">会员卡号</label>

                                <div class="col-sm-9">
                                    <input type="text" class="form-control" name="card_number">
                                </div>
                            </div>
                            <div class="hr-line-dashed"></div>
                            <div class="form-group">
                                <label class="col-sm-2 control-label">身份证号</label>

                                <div class="col-sm-9">
                                    <input type="text" class="form-control" name="card_no">
                                </div>
                            </div>
                            <div class="hr-line-dashed"></div>
                            <div class="form-group">
                                <label class="col-sm-2 control-label">驾驶证号</label>

                                <div class="col-sm-9">
                                    <input type="text" class="form-control" name="driver_no">
                                </div>
                            </div>
                            <div class="hr-line-dashed"></div>
                            <div class="form-group">
                                <label class="col-sm-2 control-label">收件地址</label>

                                <div class="col-sm-9">
                                    <input type="text" class="form-control" name="ship_addr">
                                </div>
                            </div>
                            <c:if test="${type==1}">
                            <div class="hr-line-dashed is-show"></div>
                            <div class="form-group is-show">
                                <label class="col-sm-2 control-label">所属企业</label>

                                <div class="col-sm-9">
                                    <select data-placeholder="选择企业..." class="chosen-select" tabindex="2" name="firm">
                                    	<option value="">请选择企业</option>
                                    	 <c:forEach items="${firm}" var="f">
					                    	<option value="${f.id}">${f.name}</option>
					                    </c:forEach>
		                            </select>
                                </div>
                            </div>
                            </c:if>
                            <c:if test="${type==0}">
                            <div class="hr-line-dashed is-show"></div>
                            <div class="form-group is-show">
                                <label class="col-sm-2 control-label">销售员</label>

                                <div class="col-sm-9">
                                    <select data-placeholder="选择销售..." class="chosen-select" tabindex="2" name="sale">
                                    	<option value="">请选择销售</option>
                                    	 <c:forEach items="${sales }" var="s">
                                    		<option value="${s.id }" hassubinfo="true">${s.username }</option>
                                    	 </c:forEach>
		                            </select>
                                </div>
                            </div>
                            </c:if>
                            <div class="hr-line-dashed"></div>
                            <div class="form-group">
                                <label class="col-sm-2 control-label">身份证正面照</label>

                                <div class="col-sm-9">
                                  	<input class="fileInput" id="cardFrontUploadify" type="file" name="file" multiple="multiple" accept="image/*"/>
                                    <div class="card_frontImg" style="display:none;">   
							                 	<img src="" style="width: 60px;height: 60px">
							         </div>
                                </div>
                            </div>
                            <div class="hr-line-dashed"></div>
                            <div class="form-group">
                                <label class="col-sm-2 control-label">身份证背面照</label>

                                <div class="col-sm-9">
                                    <input class="fileInput" id="cardBackUploadify" type="file" name="file" multiple="multiple" accept="image/*"/>
                                    <div class="card_backImg" style="display:none;">   
							                 	<img src="" style="width: 60px;height: 60px">
							         </div>
                                </div>
                            </div>
                            <div class="hr-line-dashed"></div>
                            <div class="form-group">
                                <label class="col-sm-2 control-label">驾驶证正面照</label>

                                <div class="col-sm-9">
                                    <input class="fileInput" id="driverUploadify" type="file" name="file" multiple="multiple" accept="image/*"/>
                                    <div class="driver_scanImg" style="display:none;">   
							                 	<img src="" style="width: 60px;height: 60px">
							         </div>
                                </div>
                            </div>
                            <div class="hr-line-dashed"></div>
                            <div class="form-group">
                                <label class="col-sm-2 control-label">驾驶证背面照</label>

                                <div class="col-sm-9">
                                    <input class="fileInput" id="driverBackUploadify" type="file" name="file" multiple="multiple" accept="image/*"/>
                                    <div class="driver_scan_backImg" style="display:none;">   
							                 	<img src="" style="width: 60px;height: 60px">
							         </div>
                                </div>
                            </div>
                            <div class="hr-line-dashed"></div>
                            <div class="form-group">
                                <label class="col-sm-2 control-label">备注</label>

                                <div class="col-sm-9">
                                    <textarea class="form-control" rows="3" name="remark" style="resize: none"></textarea>
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

    <script src="${ctx}/statics/manage/js/jquery.min.js?v=2.1.4"></script>
    <script src="${ctx}/statics/manage/js/bootstrap.min.js?v=3.3.6"></script>
    <script src="${ctx}/statics/manage/js/content.min.js?v=1.0.0"></script>
    <script src="${ctx}/statics/manage/js/plugins/switchery/switchery.js"></script>
    <script src="${ctx}/statics/manage/js/plugins/iCheck/icheck.min.js"></script>
    <script src="${ctx}/statics/manage/plugins/layer/layer.js"></script>
    <script src="${ctx}/statics/manage/js/plugins/sweetalert/sweetalert.min.js"></script>
    <script src="${ctx}/statics/manage/plugins/uploadify/jquery.uploadify.js"></script>
    <script src="${ctx}/statics/manage/js/plugins/chosen/chosen.jquery.js"></script>
    <script src="${ctx}/statics/manage/js/jquery.form.js"></script>
    <script src="${ctx}/statics/manage/js/common.js"></script>
    
    <script>
        $(document).ready(function(){$(".i-checks").iCheck({checkboxClass:"icheckbox_square-green",radioClass:"iradio_square-green"})});
        function add(){
			var type=${type};
			var params={"name":$("[name='name']").val(),"mobile":$("[name='mobile']").val(),"number":$("[name='number']").val(),"card_number":$("[name='card_number']").val(),"card_no":$("[name='card_no']").val(),"driver_no":$("[name='driver_no']").val(),"ship_addr":$("[name='ship_addr']").val(),"card_front":$(".card_frontImg img").attr("src"),"card_back":$(".card_backImg img").attr("src"),"driver_scan":$(".driver_scanImg img").attr("src"),"driver_scan_back":$(".driver_scan_backImg img").attr("src"),"remark":$("[name='remark']").val(),"sale_id":$("[name='sale']").val(),"sale_name":$("[name='sale'] option:selected").text(),"type":type};
			if(type==1){//企业
			   params={"name":$("[name='name']").val(),"mobile":$("[name='mobile']").val(),"number":$("[name='number']").val(),"card_number":$("[name='card_number']").val(),"card_no":$("[name='card_no']").val(),"driver_no":$("[name='driver_no']").val(),"ship_addr":$("[name='ship_addr']").val(),"card_front":$(".card_frontImg img").attr("src"),"card_back":$(".card_backImg img").attr("src"),"driver_scan":$(".driver_scanImg img").attr("src"),"driver_scan_back":$(".driver_scan_backImg img").attr("src"),"remark":$("[name='remark']").val(),"firm_id":$("[name='firm']").val(),"firm_name":$("[name='firm'] option:selected").text(),"type":type};
			}
        	$.post("${ctx}/manage/member/add",params,function(data){
					if(data.code=="0"){
						opt_success("添加成功",0);
					}else if(data.code=="1"){
						opt_error("添加失败");
					}else{
						opt_error("手机号已存在");
					}
				},"json")
        }
        $("#cardFrontUploadify").uploadify({
        	'buttonImage':'${ctx}/statics/manage/plugins/uploadify/uploadify-button.png',
	        'swf': '${ctx}/statics/manage/plugins/uploadify/uploadify.swf',
	        'uploader':'${ctx}/upload',
	        'onFallback' : function() {//检测FLASH失败调用 
	        	alert("您未安装FLASH控件，无法上传图片！请安装FLASH控件后再试。");  
	        },  
	        'onUploadSuccess':function(file, data, response){
	            var jsonData = jQuery.parseJSON(data);
                $(".card_frontImg").css("display","block").find("img").attr("src",jsonData.msg);
        	}
	    });
	    $("#cardBackUploadify").uploadify({
	    	'buttonImage':'${ctx}/statics/manage/plugins/uploadify/uploadify-button.png',
	        'swf': '${ctx}/statics/manage/plugins/uploadify/uploadify.swf',
	        'uploader':'${ctx}/upload',
	        'onFallback' : function() {//检测FLASH失败调用 
	        	alert("您未安装FLASH控件，无法上传图片！请安装FLASH控件后再试。");  
	        },  
	        'onUploadSuccess':function(file, data, response){
	           var jsonData = jQuery.parseJSON(data);
               $(".card_backImg").css("display","block").find("img").attr("src",jsonData.msg);
        	}
	    });
	    $("#driverUploadify").uploadify({
	    	'buttonImage':'${ctx}/statics/manage/plugins/uploadify/uploadify-button.png',
	        'swf': '${ctx}/statics/manage/plugins/uploadify/uploadify.swf',
	        'uploader':'${ctx}/upload',
	        'onFallback' : function() {//检测FLASH失败调用 
	        	alert("您未安装FLASH控件，无法上传图片！请安装FLASH控件后再试。");  
	        },  
	        'onUploadSuccess':function(file, data, response){
	            var jsonData = jQuery.parseJSON(data);
                $(".driver_scanImg").css("display","block").find("img").attr("src",jsonData.msg);
        	}
	    });
	    $("#driverBackUploadify").uploadify({
	    	'buttonImage':'${ctx}/statics/manage/plugins/uploadify/uploadify-button.png',
	        'swf': '${ctx}/statics/manage/plugins/uploadify/uploadify.swf',
	        'uploader':'${ctx}/upload',
	        'onFallback' : function() {//检测FLASH失败调用 
	        	alert("您未安装FLASH控件，无法上传图片！请安装FLASH控件后再试。");  
	        },  
	        'onUploadSuccess':function(file, data, response){
	            var jsonData = jQuery.parseJSON(data);
                $(".driver_scan_backImg").css("display","block").find("img").attr("src",jsonData.msg);
        	}
	    });
	    $('.chosen-select').chosen();
    </script>
</body>

</html>