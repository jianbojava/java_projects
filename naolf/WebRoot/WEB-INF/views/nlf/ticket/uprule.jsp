<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
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
	                                <label class="col-sm-2 control-label">原升级卡</label>
	                                <div class="col-sm-9">
	                                    <select data-placeholder="选择原升级卡..." class="chosen-select" tabindex="2" name="ticket_id1">
	                                    	<option value="">选择原升级卡</option>
		                                    	  <c:forEach items="${list1}" var="r">
		                                    		  <option value="${r.id }">${r.name}</option>
		                                    	  </c:forEach>
			                            </select>
	                                </div>
	                            </div>
                            <div class="hr-line-dashed"></div>
                            <div class="form-group">
	                                <label class="col-sm-2 control-label">原升级券</label>
	
	                                <div class="col-sm-9">
	                                    <select data-placeholder="选择升级券..." class="chosen-select" tabindex="2" name="ticket_id2">
	                                    	<option value="">请选择升级券</option>
		                                    	  <c:forEach items="${list2}" var="r">
		                                    		  <option value="${r.id }">${r.name}</option>
		                                    	  </c:forEach>
			                            </select>
	                                </div>
	                            </div>
                            <div class="hr-line-dashed"></div>
                            <div class="form-group">
	                                <label class="col-sm-2 control-label">升级后的卡</label>
	
	                                <div class="col-sm-9">
	                                    <select data-placeholder="选择升级后的卡..." class="chosen-select" tabindex="2" name="ticket_id3">
	                                    	<option value="">请选择升级后的卡</option>
		                                    	  <c:forEach items="${list1}" var="r">
		                                    		  <option value="${r.id }">${r.name}</option>
		                                    	  </c:forEach>
			                            </select>
	                                </div>
	                            </div>
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
    <script src="${ctx}/statics/manage/js/plugins/chosen/chosen.jquery.js"></script>
    <script src="${ctx}/statics/manage/js/jquery.form.js"></script>
    <script src="${ctx}/statics/manage/js/common.js"></script>
    
    <script>
       $('.chosen-select').chosen();
       $("[name='ticket_id1']").val('${uprule.ticket_id1}');
       $("[name='ticket_id2']").val('${uprule.ticket_id2}');
        $("[name='ticket_id3']").val('${uprule.ticket_id3}');
       $(".chosen-select").trigger("chosen:updated");
	     //radio  0卡，1券（单个课程）
        function update(){
          var ticket_id1=$("[name='ticket_id1']").val();
          var ticket_id2=$("[name='ticket_id2']").val();
          var ticket_id3=$("[name='ticket_id3']").val();
          if(ticket_id1==""||ticket_id2==""||ticket_id3==""){
             opt_error("请将信息填写完整!");
             return;
          }
          if(ticket_id1==ticket_id3){
             opt_error("原升级卡不能和升级后的卡一样!");
             return;
          }
          $(".btn-primary").attr("disabled",true);//防止多次提交
			var params={"id":"${uprule.id}",
						"ticket_id1":$("[name='ticket_id1']").val(),
						"ticket_id2":$("[name='ticket_id2']").val(),
						"ticket_id3":$("[name='ticket_id3']").val()
						};
        	$.post("${ctx}/manage/uprule/update",params,function(data){
					if(data.code=="0"){
						opt_success("更新成功",0);
					}else if(data.code=="2"){
						opt_error("更新失败，升级规则课程不匹配");
						$(".btn-primary").attr("disabled",false);//防止多次提交
					}else{
					  opt_error("更新失败");
						$(".btn-primary").attr("disabled",false);//防止多次提交
					}
				},"json")
        }
    </script>
</body>

</html>