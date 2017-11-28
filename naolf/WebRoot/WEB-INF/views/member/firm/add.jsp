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
    <link href="${ctx}/statics/manage/css/plugins/awesome-bootstrap-checkbox/awesome-bootstrap-checkbox.css" rel="stylesheet">

</head>

<body>
    <div class="row animated fadeInRight">
            <div class="col-sm-12">
                <div class="ibox float-e-margins">
                    <div class="ibox-content">
                        <form action="" method="post" class="form-horizontal">
                            <div class="form-group">
                                <label class="col-sm-2 control-label">企业名称</label>

                                <div class="col-sm-9">
                                    <input type="text" class="form-control" name="name">
                                </div>
                            </div>
                            <div class="hr-line-dashed"></div>
                            <div class="form-group">
                                <label class="col-sm-2 control-label">联系人</label>
                                <div class="col-sm-9">
                                    <input type="text" class="form-control" name="representer">
                                </div>
                            </div>
                            <div class="hr-line-dashed"></div>
                            <div class="form-group">
                                <label class="col-sm-2 control-label">联系电话</label>

                                <div class="col-sm-9">
                                    <input type="text" class="form-control" name="tel">
                                </div>
                            </div>
                            <div class="hr-line-dashed"></div>
                            <div class="form-group">
                                <label class="col-sm-2 control-label">邮箱</label>
                                <div class="col-sm-9">
                                    <input type="text" class="form-control" name="email">
                                </div>
                            </div>
                            <div class="hr-line-dashed"></div>
                            <div class="form-group">
                                <label class="col-sm-2 control-label">网址</label>
                                <div class="col-sm-9">
                                    <input type="text" class="form-control" name="url">
                                </div>
                            </div>
                            <div class="hr-line-dashed"></div>
                            <div class="form-group">
                                <label class="col-sm-2 control-label">地址</label>
                                <div class="col-sm-9">
                                    <input type="text" class="form-control" name="address">
                                </div>
                            </div>
                            <div class="hr-line-dashed"></div>
                            <div class="form-group">
                                <label class="col-sm-2 control-label">营业执照</label>

                                <div class="col-sm-9">
                                    <input class="fileInput" id="licenseUploadify" type="file" name="file" multiple="multiple" accept="image/*"/>
                                     <div class="licenseImg" style="display:none;">   
							                 	<img src="" style="width: 60px;height: 60px">
							         </div>
                                </div>
                            </div>
                            <div class="hr-line-dashed"></div>
                            <div class="form-group">
                                <label class="col-sm-2 control-label">销售员</label>
                                <div class="col-sm-9">
                                    <select data-placeholder="销售员..." class="chosen-select" tabindex="2" name="sales">
                                    	<option value="">请选择销售员</option>
                                    	<c:forEach items="${sales }" var="s">
                                    		<option value="${s.id }" hassubinfo="true">${s.username }</option>
                                    	</c:forEach>
		                            </select>
                                </div>
                            </div>
                            <div class="hr-line-dashed"></div>
                            <div class="form-group">
                                <label class="col-sm-2 control-label">支付方式</label>
                                <div class="col-sm-9">
                                    <div class="radio radio-success radio-inline">
                                        <input type="radio" id="inlineRadio1" value="0" name="pay_type" checked="">
                                        <label for="inlineRadio1"> 后付费 </label>
                                    </div>
                                    <div class="radio radio-success radio-inline">
                                        <input type="radio" id="inlineRadio2" value="1" name="pay_type">
                                        <label for="inlineRadio2"> 预充值 </label>
                                    </div>
                                </div>
                            </div>
                            <div class="hr-line-dashed"></div>
                            <div class="form-group">
                                <label class="col-sm-2 control-label">签约时间</label>

                                <div class="col-sm-9">
                                    <div class="input-group input-daterange">
		                                <input class="form-control" name="sign_date" type="text" placeholder="签约时间">
		                                <span class="input-group-addon">到</span>
		                                <input class="form-control" name="end_date" type="text" placeholder="终止时间">
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
        $(document).ready(function(){$(".i-checks").iCheck({checkboxClass:"icheckbox_square-green",radioClass:"iradio_square-green"})});
        function add(){
			var params={"name":$("[name='name']").val(),"representer":$("[name='representer']").val(),"tel":$("[name='tel']").val(),"email":$("[name='email']").val(),"url":$("[name='url']").val(),"address":$("[name='address']").val(),"bus_license":$(".licenseImg img").attr("src"),"sale_id":$("[name='sales']").val(),"sale_name":$("[name='sales']").find("option:selected").text(),"pay_type":$("[name='pay_type']:checked").val(),
					"sign_date":$("[name='sign_date']").val(),"end_date":$("[name='end_date']").val(),"remark":$("[name='remark']").val()};
			$.post("${ctx}/manage/firm/add",params,function(data){
				if(data.code=="0"){
					opt_success("添加成功",0);
				}else{
					opt_error("添加失败");
				}
			},"json")
        }
        
         $("#licenseUploadify").uploadify({
        	'buttonImage':'${ctx}/statics/manage/plugins/uploadify/uploadify-button.png',
	        'swf': '${ctx}/statics/manage/plugins/uploadify/uploadify.swf',
	        'uploader':'${ctx}/upload',
	        'onFallback' : function() {//检测FLASH失败调用 
	        	alert("您未安装FLASH控件，无法上传图片！请安装FLASH控件后再试。");  
	        },  
	        'onUploadSuccess':function(file, data, response){
                var jsonData = jQuery.parseJSON(data);
                $(".licenseImg").css("display","block").find("img").attr("src",jsonData.msg);
        	}
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