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
                                <label class="col-sm-2 control-label">认真专注</label>
                                <div class="col-sm-9">
                                	<div class="radio radio-success radio-inline">
                                        <input type="radio" id="inlineRadio1" value="0" name="isserious" checked="">
                                        <label for="inlineRadio1"> 是 </label>
                                    </div>
                                    <div class="radio radio-success radio-inline">
                                        <input type="radio" id="inlineRadio2" value="1" name="isserious" <c:if test="${tcomment.isserious==1 }">checked</c:if>>
                                        <label for="inlineRadio2"> 否 </label>
                                    </div>
                                </div>
                            </div>
                             <div class="hr-line-dashed"></div>
                             
                             <div class="form-group">
                                <label class="col-sm-2 control-label">小有进步</label>
                                <div class="col-sm-9">
                                	<div class="radio radio-success radio-inline">
                                        <input type="radio" id="inlineRadio3" value="0" name="isprogress" checked="">
                                        <label for="inlineRadio3"> 是 </label>
                                    </div>
                                    <div class="radio radio-success radio-inline">
                                        <input type="radio" id="inlineRadio4" value="1" name="isprogress" <c:if test="${tcomment.isprogress==1 }">checked</c:if>>
                                        <label for="inlineRadio4"> 否 </label>
                                    </div>
                                </div>
                            </div>
                             <div class="hr-line-dashed"></div>
                             
                             <div class="form-group">
                                <label class="col-sm-2 control-label">乐于助人</label>
                                <div class="col-sm-9">
                                	<div class="radio radio-success radio-inline">
                                        <input type="radio" id="inlineRadio5" value="0" name="ishelp" checked="">
                                        <label for="inlineRadio5"> 是 </label>
                                    </div>
                                    <div class="radio radio-success radio-inline">
                                        <input type="radio" id="inlineRadio6" value="1" name="ishelp" <c:if test="${tcomment.ishelp==1 }">checked</c:if>>
                                        <label for="inlineRadio6"> 否 </label>
                                    </div>
                                </div>
                            </div>
                             <div class="hr-line-dashed"></div>
                             
                             <div class="form-group">
                                <label class="col-sm-2 control-label">团队合作</label>
                                <div class="col-sm-9">
                                	<div class="radio radio-success radio-inline">
                                        <input type="radio" id="inlineRadio7" value="0" name="isteam" checked="">
                                        <label for="inlineRadio7"> 是 </label>
                                    </div>
                                    <div class="radio radio-success radio-inline">
                                        <input type="radio" id="inlineRadio8" value="1" name="isteam" <c:if test="${tcomment.isteam==1 }">checked</c:if>>
                                        <label for="inlineRadio8"> 否 </label>
                                    </div>
                                </div>
                            </div>
                             <div class="hr-line-dashed"></div>
                             
                             <div class="form-group">
                                <label class="col-sm-2 control-label">积极发言</label>
                                <div class="col-sm-9">
                                	<div class="radio radio-success radio-inline">
                                        <input type="radio" id="inlineRadio9" value="0" name="isspeak" checked="">
                                        <label for="inlineRadio9"> 是 </label>
                                    </div>
                                    <div class="radio radio-success radio-inline">
                                        <input type="radio" id="inlineRadio10" value="1" name="isspeak" <c:if test="${tcomment.isspeak==1 }">checked</c:if>>
                                        <label for="inlineRadio10"> 否 </label>
                                    </div>
                                </div>
                            </div>
                             <div class="hr-line-dashed"></div>
                             <div class="form-group">
                                <label class="col-sm-2 control-label">今日之星</label>
                                <div class="col-sm-9">
                                	<div class="radio radio-success radio-inline">
                                        <input type="radio" id="inlineRadio11" value="0" name="isstar" checked="">
                                        <label for="inlineRadio11"> 是 </label>
                                    </div>
                                    <div class="radio radio-success radio-inline">
                                        <input type="radio" id="inlineRadio12" value="1" name="isstar" <c:if test="${tcomment.isstar==1 }">checked</c:if>>
                                        <label for="inlineRadio12"> 否 </label>
                                    </div>
                                </div>
                            </div>
                           <div class="hr-line-dashed"></div>
                             <div class="form-group">
                                <label class="col-sm-2 control-label">老师寄语</label>
                                <div class="col-sm-9">
                                    <textarea class="form-control" id="comment" name="comment" placeholder="老师寄语">${tcomment.comment}</textarea>
                                </div>
                            </div>
                             <div class="hr-line-dashed"></div>
                              <div class="form-group">
                                <label class="col-sm-2 control-label">指导教师</label>
                                <div class="col-sm-9">
                                    <input type="text" class="form-control" placeholder="指导教师" name="teacher" <c:if test="${empty tcomment}"> value="${teacher}"</c:if> <c:if test="${not empty tcomment}"> value="${tcomment.teacher}"</c:if>>
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
         function update(){
           var comment=$.trim($("[name='comment']").val());
           if(comment==null||comment==""){
              opt_error("教师寄语不可以为空!");
              return;
           }
            $(".btn-primary").attr("disabled",true);//防止多次提交
			var params={"id":"${tcomment.id}","appoint_id":"${appoint_id}","comment":$("[name='comment']").val(),"teacher":$("[name='teacher']").val(),"isserious":$("[name='isserious']:checked").val(),"isprogress":$("[name='isprogress']:checked").val(),"ishelp":$("[name='ishelp']:checked").val(),"isteam":$("[name='isteam']:checked").val(),"isspeak":$("[name='ishelp']:checked").val(),"isstar":$("[name='isstar']:checked").val()};
        	$.post("${ctx}/manage/tcomment/add",params,function(data){
					if(data.code=="0"){
						opt_success("操作成功",0);
					}else{
						opt_error("操作失败");
						  $(".btn-primary").attr("disabled",false);//防止多次提交
					}
				},"json")
        }
    </script>
</body>

</html>