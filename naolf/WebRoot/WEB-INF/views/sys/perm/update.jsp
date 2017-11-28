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
                                    <input type="text" class="form-control" name="name" value="${perm.name}">
                                </div>
                            </div>
                            <div class="hr-line-dashed"></div>
                            <div class="form-group">
                                <label class="col-sm-2 control-label">描述</label>

                                <div class="col-sm-9">
                                    <input type="text" class="form-control" name="descript" value="${perm.description}">
                                </div>
                            </div>
                            <div class="hr-line-dashed"></div>
                            <div class="form-group">
                                <label class="col-sm-2 control-label">级别</label>
                                <div class="col-sm-9">
                                	<div class="radio radio-success radio-inline">
                                        <input type="radio" id="inlineRadio1" value="0" name="levelRadio" checked="" >
                                        <label for="inlineRadio1"> 权限 </label>
                                    </div>
                                    <div class="radio radio-success radio-inline">
                                        <input type="radio" id="inlineRadio2" value="1" name="levelRadio" <c:if test="${perm.parent_id=='0' }">  checked </c:if>>
                                        <label for="inlineRadio2"> 模块 </label>
                                    </div>
                                </div>
                            </div>
                            <div class="hr-line-dashed is-show" <c:if test="${perm.parent_id=='0' }">style="display: none"</c:if>   ></div>
                            <div class="form-group is-show" <c:if test="${perm.parent_id=='0' }"> style="display: none"</c:if> >
                                <label class="col-sm-2 control-label">模块</label>

                                <div class="col-sm-9">
                                    <select class="form-control m-b">
                                        <c:forEach items="${roots}" var="r">
					                    	<option value="${r.id}" <c:if  test="${perm.parent_id==r.id}">selected="selected"</c:if> >${r.name}</option>
					                    </c:forEach>
                                    </select>
                                </div>
                            </div>
                            <div class="hr-line-dashed is-show" <c:if test="${perm.parent_id=='0' }"> style="display: none"</c:if> ></div>
                            <div class="form-group is-show" <c:if test="${perm.parent_id=='0' }"> style="display: none"</c:if> >
                                <label class="col-sm-2 control-label">资源码</label>

                                <div class="col-sm-9">
                                    <input type="text" class="form-control" name="permission" value="${perm.permission}">
                                </div>
                            </div>
                            <div class="hr-line-dashed"></div>
                            <div class="form-group">
                                <label class="col-sm-2 control-label">启用</label>
                                <div class="col-sm-9">
                                	<input type="checkbox" class="js-switch" name="available" <c:if test="${perm.available==0}">checked</c:if> />
                                </div>
                            </div>
                            <div class="hr-line-dashed"></div>
                            <div class="form-group" >
                                <label class="col-sm-2 control-label">排序</label>

                                <div class="col-sm-9">
                                    <input type="text" class="form-control" name="sort" value="${perm.sort}">
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
    <script src="${ctx}/statics/manage/js/content.min.js?v=1.0.0"></script>
    <script src="${ctx}/statics/manage/js/plugins/switchery/switchery.js"></script>
    <script src="${ctx}/statics/manage/js/plugins/iCheck/icheck.min.js"></script>
    <script src="${ctx}/statics/manage/plugins/layer/layer.js"></script>
    <script src="${ctx}/statics/manage/js/plugins/sweetalert/sweetalert.min.js"></script>
    <script src="${ctx}/statics/manage/js/jquery.form.js"></script>
    <script src="${ctx}/statics/manage/js/common.js"></script>
    
    <script>
        $(document).ready(function(){$(".i-checks").iCheck({checkboxClass:"icheckbox_square-green",radioClass:"iradio_square-green"})});
        function update(){
           $(".btn-primary").attr("disabled",true);//防止多次提交
        	var available=1;
			if($("[name='available']").is(":checked"))available=0;
			var params={"id":'${perm.id}',"name":$("[name='name']").val(),"description":$("[name='descript']").val(),"permission":$("[name='permission']").val(),"available":available,"sort":$("[name='sort']").val(),"parent_id":$("select").val()};
			if($("[name='levelRadio']:checked").val()=="1"){
				params={"id":'${perm.id}',"name":$("[name='name']").val(),"description":$("[name='descript']").val(),"available":available,"sort":$("[name='sort']").val(),"parent_id":"0"}
			}
			$.post("${ctx}/manage/permission/update",params,function(data){
				if(data.code=="0"){
					opt_success("修改成功",0);
				}else{
					opt_error("修改失败");
					$(".btn-primary").attr("disabled",false);//防止多次提交
				}
			},"json")
        }
        //radio
		$(":radio[name='levelRadio']").change(function(){
			if($(this).attr("value")=="0"){
				$(".is-show").show();
			}else{
				$(".is-show").hide();
			}
		})
    </script>
</body>

</html>