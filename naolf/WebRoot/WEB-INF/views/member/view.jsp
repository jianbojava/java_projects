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

</head>

<body>
    <div class="row animated fadeInRight">
            <div class="col-sm-12">
                <div class="tabs-container">
                    <ul class="nav nav-tabs">
                        <li class="active"><a data-toggle="tab" href="#tab-1" aria-expanded="true">订单记录</a>
                        </li>
                        <li class=""><a data-toggle="tab" href="#tab-2" aria-expanded="false">消费记录</a>
                        </li>
                    </ul>
                    <div class="tab-content">
                        <div id="tab-1" class="tab-pane active">
                            <div class="panel-body">
                                <strong>HTML5 文档类型</strong>
                                <p>Bootstrap 使用到的某些 HTML 元素和 CSS 属性需要将页面设置为 HTML5 文档类型。在你项目中的每个页面都要参照下面的格式进行设置。</p>
                            </div>
                        </div>
                        <div id="tab-2" class="tab-pane">
                            <div class="panel-body">
                                <strong>移动设备优先</strong>
                                <p>在 Bootstrap 2 中，我们对框架中的某些关键部分增加了对移动设备友好的样式。而在 Bootstrap 3 中，我们重写了整个框架，使其一开始就是对移动设备友好的。这次不是简单的增加一些可选的针对移动设备的样式，而是直接融合进了框架的内核中。也就是说，Bootstrap 是移动设备优先的。针对移动设备的样式融合进了框架的每个角落，而不是增加一个额外的文件。</p>
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
    <script src="${ctx}/statics/manage/js/jquery.form.js"></script>
    <script src="${ctx}/statics/manage/js/common.js"></script>
    
    <script>
        $(document).ready(function(){$(".i-checks").iCheck({checkboxClass:"icheckbox_square-green",radioClass:"iradio_square-green"})});
        function add(){
        	var roles=[];
			$("[name='role']:checked").each(function(){
				roles.push($(this).attr("value"));
			})
			if(roles==""){
				alert("请选择角色");
				return false;
			}
			var is_reset=0;
			if($("[name='is_reset']").attr("checked"))is_reset=1;
			var is_locked=0;
			if($("[name='is_locked']").attr("checked"))is_locked=1;
        	$.post("${ctx}/manager/user/add",{"username":$("[name='username']").val(),"is_reset":is_reset,"nickname":$("[name='nickname']").val(),"is_locked":is_locked,"roles":roles.toString()},function(data){
					if(data.code=="0"){
						opt_success("添加成功",0);
					}else if(data.code=="1"){
						opt_error("添加失败");
					}else{
						opt_error("用户名已存在");
					}
				},"json")
        }
        $("#cardFontUploadify").uploadify({
			'buttonText':'选择文件',
			'fileObjName':'file',   //文件对象名称,用于后台获取文件对象时使用
	        'swf': '${ctx}/statics/manage/plugins/uploadify/uploadify.swf',
	        'uploader':'${ctx}/upload',
	        'onFallback' : function() {//检测FLASH失败调用 
	        	alert("您未安装FLASH控件，无法上传图片！请安装FLASH控件后再试。");  
	        },  
	        'onUploadSuccess':function(file, data, response){
	        	alert("上传成功");
        	}
	    });
	    $("#cardBackUploadify").uploadify({
			'buttonText':'选择文件',
			'fileObjName':'file',   //文件对象名称,用于后台获取文件对象时使用
	        'swf': '${ctx}/statics/manage/plugins/uploadify/uploadify.swf',
	        'uploader':'${ctx}/upload',
	        'onFallback' : function() {//检测FLASH失败调用 
	        	alert("您未安装FLASH控件，无法上传图片！请安装FLASH控件后再试。");  
	        },  
	        'onUploadSuccess':function(file, data, response){
	        	alert("上传成功");
        	}
	    });
	    $("#driverUploadify").uploadify({
			'buttonText':'选择文件',
			'fileObjName':'file',   //文件对象名称,用于后台获取文件对象时使用
	        'swf': '${ctx}/statics/manage/plugins/uploadify/uploadify.swf',
	        'uploader':'${ctx}/upload',
	        'onFallback' : function() {//检测FLASH失败调用 
	        	alert("您未安装FLASH控件，无法上传图片！请安装FLASH控件后再试。");  
	        },  
	        'onUploadSuccess':function(file, data, response){
	        	alert("上传成功");
        	}
	    });
    </script>
</body>

</html>