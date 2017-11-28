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
                <div class="ibox float-e-margins">
                    <div class="ibox-content">
                        <form action="" method="post" class="form-horizontal">
                            <div class="form-group">
                                <label class="col-sm-2 control-label">名称</label>

                                <div class="col-sm-9">
                                    <input type="text" class="form-control" name="name" value="${brand.name}">
                                </div>
                            </div>
                            <div class="hr-line-dashed"></div>
                            <div class="form-group">
                                <label class="col-sm-2 control-label">排序</label>
                                <div class="col-sm-9">
                                	<input type="text" class="form-control" name="sort" value="${brand.sort }">
                                </div>
                            </div>
                            <c:if test="${type=='0' }">
                               <div id="typelist">
                                <c:forEach items="${brand.types}" var="t" varStatus="status">
                                 <div class="linetype">
		                               <div class="hr-line-dashed"></div>
		                               <div class="form-group">
			                                <label class="col-sm-2 control-label">型号</label>
			                                <div class="col-sm-7">
			                                    <input type="text" class="form-control" id="${t.id}" name="tname" value="${t.name}">
			                                </div>
			                                 <div class="col-sm-1">
			                                     <button class="btn btn-success btn-outline" type="button" onclick="addtype()">添加</button>
			                                </div>
			                                 <c:if test="${status.index>0}">
			                                 <div class="col-sm-1">
					                              <button class="btn btn-success btn-outline" type="button" onclick="deltype(this)">删除</button>
					                         </div>
					                         </c:if>
		                                </div>
                                 </div>
                                </c:forEach>
                            </div>
                            </c:if>
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
       
        function deltype(object){
            $(object).parents(".linetype").remove();
        }
        function addtype(){
           var html="<div class=\"linetype\"><div class=\"hr-line-dashed\"></div>"+
	                "<div class=\"form-group\"><label class=\"col-sm-2 control-label\">型号</label>"+
	                 "<div class=\"col-sm-7\"> <input type=\"text\" class=\"form-control\" name=\"tname\"></div>"+
	                 "<div class=\"col-sm-1\"><button class=\"btn btn-success btn-outline\" type=\"button\" onclick=\"addtype()\">添加</button></div>"+
	                  "<div class=\"col-sm-1\"><button class=\"btn btn-success btn-outline\" type=\"button\" onclick=\"deltype(this)\">删除</button></div></div></div>";
           $("#typelist").append(html);
        }
       
        function update(){
			var types=[];
			$("#typelist input[name='tname']").each(function(){
				types.push({id:$(this).attr("id"),name:$(this).val()});   
			})
			var params={"id":'${brand.id}',"name":$("[name='name']").val(),"sort":$("[name='sort']").val(),"types":types};
			$.ajax({
					url:"${ctx}/manage/brand/update",
					type: "POST",
					data: JSON.stringify(params),
					success: function(data){
						if(data.code=="0"){
							opt_success("修改成功",0);
						}else{
							opt_error("修改失败");
						}
					},
					dataType: "json",
					contentType: "application/json"
				});
        }
    </script>
</body>

</html>