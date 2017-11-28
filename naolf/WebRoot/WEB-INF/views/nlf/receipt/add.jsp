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
    

    <title>脑立方</title>
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
    <link href="${ctx}/statics/manage/css/plugins/awesome-bootstrap-checkbox/awesome-bootstrap-checkbox.css" rel="stylesheet">
    <link href="${ctx}/statics/manage/css/plugins/chosen/chosen.css" rel="stylesheet">
    <link href="${ctx}/statics/manage/css/plugins/datapicker/datepicker3.css" rel="stylesheet">

</head>

<body>
    <div class="row animated fadeInRight">
            <div class="col-sm-12">
                <div class="ibox float-e-margins">
                    <div class="ibox-content">
                        <form action="" method="post" class="form-horizontal">
                           <div class="hr-line-dashed"></div>
                                <div class="form-group">
                                <label class="col-sm-2 control-label">发票抬头</label>
                                <div class="col-sm-9">
                                	<input type="text" class="form-control" name="title">
                                </div>
                            </div>
                           <div class="hr-line-dashed"></div>
                                <div class="form-group">
                                <label class="col-sm-2 control-label">发票代码</label>
                                <div class="col-sm-9">
                                	<input type="text" class="form-control" name="code">
                                </div>
                            </div>
                           <div class="hr-line-dashed"></div>
                                <div class="form-group">
                                <label class="col-sm-2 control-label">发票编号</label>
                                <div class="col-sm-9">
                                	<input type="text" class="form-control" name="sn">
                                </div>
                            </div>
                            <div class="hr-line-dashed"></div>
                            <div class="form-group">
                                <label class="col-sm-2 control-label">发票金额</label>
                                <div class="col-sm-9">
                                	<input type="text" class="form-control" name="money" id="money">
                                </div>
                            </div>
                           <div class="hr-line-dashed"></div>
                           <div class="form-group">
                                <label class="col-sm-2 control-label">开票日期</label>
                                <div class="col-sm-9">
		                        	<input type="text" class="form-control input-sm" name="add_date"  placeholder="开票日期">
                                </div>
                            </div>
                            <div class="hr-line-dashed"></div>
                            <div class="form-group">
                                <label class="col-sm-2 control-label">发票类型</label>
                                <div class="col-sm-9">
                                	 <select data-placeholder="选择发票类型..." class="chosen-select" tabindex="2" name="type">
		                                    <option value="1">专票</option>
		                                    <option value="2">普票</option>
			                            </select>
                                </div>
                            </div>
                            <div class="hr-line-dashed"></div>
                            <div class="form-group">
                                <label class="col-sm-2 control-label">是否退票</label>
                                <div class="col-sm-9">
                                	 <select data-placeholder="选择是否退票..." class="chosen-select" tabindex="2" name="isreturn">
		                                    <option value="1">是</option>
		                                    <option value="2">否</option>
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

    <script src="${ctx}/statics/manage/js/jquery.min.js?v=2.1.4"></script>
    <script src="${ctx}/statics/manage/js/bootstrap.min.js?v=3.3.6"></script>
    <script src="${ctx}/statics/manage/js/content.min.js?v=1.0.0"></script>
    <script src="${ctx}/statics/manage/js/plugins/switchery/switchery.js"></script>
    <script src="${ctx}/statics/manage/js/plugins/iCheck/icheck.min.js"></script>
    <script src="${ctx}/statics/manage/plugins/layer/layer.js"></script>
    <script src="${ctx}/statics/manage/js/plugins/sweetalert/sweetalert.min.js"></script>
    <script src="${ctx}/statics/manage/plugins/uploadify/jquery.uploadify.js"></script>
    <script src="${ctx}/statics/manage/js/plugins/chosen/chosen.jquery.js"></script>
    <script src="${ctx}/statics/manage/js/plugins/datapicker/bootstrap-datepicker.js"></script>
    <script src="${ctx}/statics/manage/js/jquery.form.js"></script>
    <script src="${ctx}/statics/manage/js/common.js"></script>
    
    <script>
     $('.chosen-select').chosen();
     $("[name='add_date']").datepicker({
         	format: "yyyy-mm-dd",
         	todayHighlight:true,
        	autoclose: true,
        	todayBtn: true
         });
       function add(){
				var sn=$("[name='sn']").val();
				var reg= /^.{8}$/;
				if(!(reg.test(sn))){
				opt_error("发票编号必须为8位！");
				return false;
				}
				 if(isNaN($("#money").val())){
			         opt_error("金额请输入数字");
			         return;
			    }
	     		if(('${number}'<=0)&&($("#money").val()<=0)){
		     		opt_error("第一笔发票金额必须大于0")
		     		return;
	     		}
        	$(".btn-primary").attr("disabled",true);//防止多次提交
        	$.post("${ctx}/manage/receipt/add",{
        			"norder_id":"${norder_id}",
        			"title":$("[name='title']").val(),
        			"code":$("[name='code']").val(),
        			"sn":$("[name='sn']").val(),
        			"money":$("[name='money']").val(),
        			"type":$("[name='type']").val(),
        			"isreturn":$("[name='isreturn']").val(),
        			"content":$("[name='type']").find("option:selected").text(),
        			"add_date":$("[name='add_date']").val()
        			},function(data){
					if(data.code=="0"){
						opt_success("添加成功",0);
					}else{
						opt_error("添加失败");
        				$(".btn-primary").attr("disabled",false);//防止多次提交
					}
				},"json")
        }
    </script>
</body>

</html>