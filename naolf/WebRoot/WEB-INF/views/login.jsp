<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/include.inc.jsp"%>
<!DOCTYPE html>
<html>

<head>

    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <title>登录</title>
    <meta name="keywords" content="后台bootstrap框架,后台HTML,响应式后台">
    <meta name="description" content="基于Bootstrap3最新版本开发的扁平化主题，她采用了主流的左右两栏式布局，使用了Html5+CSS3等现代技术">

    <link rel="shortcut icon" href="${ctx}/statics/favicon/favicon.ico"> 
    <link href="${ctx}/statics/manage/css/bootstrap.min.css?v=3.3.5" rel="stylesheet">
    <link href="${ctx}/statics/manage/css/font-awesome.min.css?v=4.4.0" rel="stylesheet">
    <link href="${ctx}/statics/manage/css/animate.min.css" rel="stylesheet">
    <link href="${ctx}/statics/manage/css/style.min.css?v=4.0.0" rel="stylesheet">
    <link href="${ctx}/statics/manage/css/plugins/toastr/toastr.min.css" rel="stylesheet">
    <link href="${ctx}/statics/manage/plugins/bootstrapvalidator-0.4.5/css/bootstrapValidator.css" rel="stylesheet">
    <!--[if lt IE 8]>
    <meta http-equiv="refresh" content="0;ie.html" />
    <![endif]-->
    <script>if(window.top !== window.self){ window.top.location = window.location;}</script>
    <style type="text/css">
    	.clearfix:after { content:"."; display: block; height:0; clear:both; visibility: hidden; }
		.clearfix { *zoom:1; }
		form div:nth-child(3) small {padding-right: 110px!important}
    </style>
</head>

<body class="gray-bg">

    <div class="middle-box text-center loginscreen  animated fadeInDown">
        <div>
            <div class="m-b-md">
                <img alt="image" class="img-circle circle-border" src="${ctx}/statics/manage/img/logo.png" width="140" height="140">
            </div>
            <h3>欢迎使用管理系统</h3>
            <form id="defaultForm" class="m-t" role="form" action="${ctx }/manage/login" method="post">
                <div class="form-group">
                    <input type="text" class="form-control" placeholder="用户名" required data-bv-notempty-message="用户名不能为空" name="username">
                </div>
                <div class="form-group">
                    <input type="password" class="form-control" placeholder="密码" required data-bv-notempty-message="密码不能为空" name="password">
                </div>
                <div class="form-group clearfix">
					<input type="text" class="form-control" placeholder="验证码" required data-bv-notempty-message="验证码不能为空" name="kaptcha" style="width: 60%;float: left;margin-bottom: 5px">
					<img src="${ctx }/validateCode" onclick="change(this)" style="width: 38%;float: right;margin-top: 2px">
				</div>
                <button type="submit" class="btn btn-primary block full-width m-b" style="margin-top: 15px">登 录</button>
            </form>
        </div>
    </div>
    <script src="${ctx}/statics/manage/js/jquery.min.js?v=2.1.4"></script>
    <script src="${ctx}/statics/manage/js/bootstrap.min.js?v=3.3.5"></script>
    <script src="${ctx}/statics/manage/js/plugins/toastr/toastr.min.js"></script>
    <script src="${ctx}/statics/manage/plugins/bootstrapvalidator-0.4.5/js/bootstrapValidator.js"></script>
    
    <script type="text/javascript">
	    $(document).ready(function() {
	        $('#defaultForm').bootstrapValidator();
	    });
		toastr.options = {
			"closeButton" : true,
			"debug" : true,
			"progressBar" : true,
			"positionClass" : "toast-top-center",
			"onclick" : null,
			"showDuration" : "400",
			"hideDuration" : "1000",
			"timeOut" : "3000",
			"extendedTimeOut" : "1000",
			"showEasing" : "swing",
			"hideEasing" : "linear",
			"showMethod" : "fadeIn",
			"hideMethod" : "fadeOut"
		}
		$(function() {
			if('${message}'!=null&&'${message}'!=""){
				toastr.error('${message}', '登录失败')
			}
		});
		function change(obj) {
			obj.src = "${ctx}/validateCode?" + Math.random();
		}
	</script>
</body>

</html>