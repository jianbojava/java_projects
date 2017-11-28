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
    
</head>

<body>
    <div class="row animated fadeInRight">
            <div class="col-sm-12">
                <div class="ibox float-e-margins">
                    <div class="ibox-content">
                        <form action="" method="post" class="form-horizontal">
                            <div class="form-group">
                                <label class="col-sm-2 control-label">姓名</label>

                                <div class="col-sm-9">
                                    <input type="text" class="form-control" name="name" value="${student.name}">
                                </div>
                            </div>
                            <div class="hr-line-dashed"></div>
                            <div class="form-group">
                                <label class="col-sm-2 control-label">在读学校</label>
                                <div class="col-sm-9">
                                	<input type="text" class="form-control" name="school" value="${student.school }">
                                </div>
                            </div>
                             <div class="hr-line-dashed"></div>
                              <div class="form-group">
                                <label class="col-sm-2 control-label">年级</label>
                                <div class="col-sm-9">
                                	<input type="text" class="form-control" name="grade" value="${student.grade}">
                                </div>
                            </div>
                              <div class="hr-line-dashed"></div>
                                <div class="form-group">
                                <label class="col-sm-2 control-label">年龄</label>
                                <div class="col-sm-9">
                                	<input type="text" class="form-control" name="age" value="${student.age }">
                                </div>
                            </div>
                              <div class="hr-line-dashed"></div>
                               <div class="hr-line-dashed"></div>
                             <div class="form-group">
                                <label class="col-sm-2 control-label">性别</label>
                                <div class="col-sm-9">
                                	<div class="radio radio-success radio-inline">
                                        <input type="radio" id="inlineRadio1" value="0" name="gender" checked="">
                                        <label for="inlineRadio1"> 男 </label>
                                    </div>
                                    <div class="radio radio-success radio-inline">
                                        <input type="radio" id="inlineRadio2" value="1" name="gender" <c:if test="${student.gender==1 }">checked</c:if>>
                                        <label for="inlineRadio2"> 女 </label>
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
    <script src="${ctx}/statics/manage/js/content.min.js?v=1.0.0"></script>
    <script src="${ctx}/statics/manage/js/plugins/switchery/switchery.js"></script>
    <script src="${ctx}/statics/manage/js/plugins/iCheck/icheck.min.js"></script>
    <script src="${ctx}/statics/manage/plugins/layer/layer.js"></script>
    <script src="${ctx}/statics/manage/js/plugins/sweetalert/sweetalert.min.js"></script>
    <script src="${ctx}/statics/manage/plugins/uploadify/jquery.uploadify.js"></script>
    <script src="${ctx}/statics/manage/js/jquery.form.js"></script>
    <script src="${ctx}/statics/manage/js/common.js"></script>
    
    <script>
    var r = /^\+?[1-9][0-9]*$/;//正整数验证
         function update(){
          var name=$.trim($("[name='name']").val());
	       if(name==null||name==""){
	          opt_error("姓名不可为为空");
	          return;
	       }
	       var age=$.trim($("[name='age']").val());
	       if(age==null||age==""){
	         opt_error("年龄不可为空");
	         return;
	       }
	       if(!r.test(age)){
			   opt_error("年龄请输入正整数!");
			   return;
		   }
            $(".btn-primary").attr("disabled",true);//防止多次提交
			var params={"id":"${student.id}","name":$("[name='name']").val(),"school":$("[name='school']").val(),"age":$("[name='age']").val(),"grade":$("[name='grade']").val(),"gender":$("[name='gender']:checked").val()};
        	$.post("${ctx}/manage/student/update",params,function(data){
					if(data.code=="0"){
						opt_success("修改成功",0);
					}else{
						opt_error("修改失败");
						  $(".btn-primary").attr("disabled",false);//防止多次提交
					}
				},"json")
        }
    </script>
</body>

</html>