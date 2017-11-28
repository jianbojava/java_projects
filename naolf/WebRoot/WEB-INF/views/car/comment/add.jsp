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
                        	<c:choose>
                        		<c:when test="${type==0 }">
                        			<div class="form-group">
		                                <label class="col-sm-2 control-label">车辆</label>
		                                <div class="col-sm-9">
		                                    <select data-placeholder="请选择车辆..." class="chosen-select" tabindex="2" name="car">
		                                    	<option value="">请选择车辆...</option>
		                                    	 <c:forEach items="${cars}" var="c">
							                    	<option value="${c.id}">【${c.number}】${c.name}</option>
							                    </c:forEach>
				                            </select>
		                                </div>
		                            </div>
                        		</c:when>
                        		<c:otherwise>
                        			<div class="form-group">
		                                <label class="col-sm-2 control-label">充电站</label>
		                                <div class="col-sm-9">
		                                    <select data-placeholder="请选择充电站..." class="chosen-select" tabindex="2" name="station">
		                                    	<option value="">请选择充电站...</option>
		                                    	 <c:forEach items="${stations}" var="s">
							                    	<option value="${s.stationID}">【${s.stationID}】${s.stationName}</option>
							                    </c:forEach>
				                            </select>
		                                </div>
		                            </div>
                        		</c:otherwise>
                        	</c:choose>
                            <div class="hr-line-dashed"></div>
                            <div class="form-group">
                                <label class="col-sm-2 control-label">星级</label>
                                <div class="col-sm-9">
                                    <select data-placeholder="请选择星级..." class="chosen-select" tabindex="2" name="star">
		                            	<option value="">请选择星级...</option>
		                            	<option value="1">★</option>
		                            	<option value="2">★★</option>
		                            	<option value="3">★★★</option>
		                            	<option value="4">★★★★</option>
		                            	<option value="5">★★★★★</option>
		                            </select>
                                </div>
                            </div>
                            <div class="hr-line-dashed"></div>
                            <div class="form-group">
                                <label class="col-sm-2 control-label">手机号</label>

                                <div class="col-sm-9">
                                	<div class="input-group date">
		                                <input class="form-control" type="text" name="mem_name" placeholder="手机号">
		                                <span class="input-group-addon" style="cursor:pointer;" onclick="javascript:$(this).prev().val(create_mobile());"><i class="fa fa-refresh"></i> 随机生成</span>
									</div>
                                </div>
                            </div>
                            <div class="hr-line-dashed"></div>
                            <div class="form-group">
                                <label class="col-sm-2 control-label">内容</label>
                                <div class="col-sm-9">
                                    <textarea class="form-control" rows="5" name="content" style="resize: none"></textarea>
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
        var params;
        function add(){
        	if(${type}==0)params={"car_id":$("[name='car']").val(),"mem_name":$("[name='mem_name']").val(),"star":$("[name='star']").val(),"content":$("[name='content']").val()};
        	if(${type}==1)params={"station_id":$("[name='station']").val(),"mem_name":$("[name='mem_name']").val(),"star":$("[name='star']").val(),"content":$("[name='content']").val()};
        	$.post("${ctx}/manage/comment/add",params,function(data){
					if(data.code=="0"){
						opt_success("添加成功",0);
					}else{
						opt_error("系统繁忙，请稍后再试");
					}
				},"json")
        }
	    $('.chosen-select').chosen();
    </script>
</body>

</html>