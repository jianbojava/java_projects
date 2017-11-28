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
    <link href="${ctx}/statics/manage/css/plugins/chosen/chosen.css" rel="stylesheet">
    <link href="${ctx}/statics/manage/css/plugins/datapicker/datepicker3.css" rel="stylesheet">
    <link href="${ctx}/statics/manage/css/plugins/awesome-bootstrap-checkbox/awesome-bootstrap-checkbox.css" rel="stylesheet">

</head>

<body>
    <div class="row animated fadeInRight">
            <div class="col-sm-12">
                <div class="ibox float-e-margins">
                    <div class="ibox-content">
                        <form action="" method="post" class="form-horizontal">
                               <div class="form-group">
	                                <label class="col-sm-2 control-label">订单总额</label>
	                                <div class="col-sm-4">
                                          <input type="text" class="form-control" value="${norder.price}"  readonly="readonly">
	                                </div>
	                                <label class="col-sm-2 control-label">可退积分</label>
	                                <div class="col-sm-4">
                                          <input type="text" class="form-control" value="${return_point}"  readonly="readonly">
	                                </div>
	                            </div>
	                            <div class="form-group">
	                                <label class="col-sm-2 control-label">单独课程总额</label>
	                                <div class="col-sm-4">
                                          <input type="text" class="form-control" value="${total_price}"  readonly="readonly">
	                                </div>
	                                <label class="col-sm-2 control-label">已结业课程总额</label>
	                                <div class="col-sm-4">
                                          <input type="text" class="form-control" value="${end_price}"  readonly="readonly">
	                                </div>
	                            </div>
	                             <div class="hr-line-dashed"></div>
	                             <div class="form-group">
	                                <label class="col-sm-2 control-label">课程</label>
	                                <div class="col-sm-9">
	                                    <c:forEach items="${ulist }" var="ut">
		                                    <c:if test="${ut.status<4}">
		                                         <input type="text" class="form-control" value="${ut.name}-------------金额：${ut.price}---------（状态：未结业）"  readonly="readonly">
		                                    </c:if>
	                                    </c:forEach>
	                                    
	                                    <c:forEach items="${ulist }" var="ut">
		                                    <c:if test="${ut.status>=4}">
		                                         <input type="text" class="form-control" value="${ut.name}-------------金额：${ut.price}--------（状态：已结业）"  readonly="readonly">
		                                    </c:if>
	                                    </c:forEach>
	                                    
	                                </div>
	                            </div>
	                             <div class="hr-line-dashed"></div>
                            <div class="form-group">
                                <label class="col-sm-2 control-label">退款比例%</label>

                                <div class="col-sm-3">
                                    <input type="text" class="form-control" placeholder="1-100" onchange="toreturn(1)" name="return_percent">
                                </div>
                                 <label class="col-sm-2 control-label">退款积分</label>

                                <div class="col-sm-3">
                                    <input type="text" class="form-control" onchange="toreturn(2)"  name="return_point">
                                </div>
                            </div>
                             <div class="hr-line-dashed"></div>
                            <div class="form-group">
                                <div class="col-sm-4 col-sm-offset-4" style="text-align: center;">
                                    <button class="btn btn-primary" type="button" onclick="addreturn()">确定退款</button>
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
    <script src="${ctx}/statics/manage/js/plugins/chosen/chosen.jquery.js"></script>
    <script src="${ctx}/statics/manage/js/plugins/datapicker/bootstrap-datepicker.js"></script>
    <script src="${ctx}/statics/manage/js/jquery.form.js"></script>
    <script src="${ctx}/statics/manage/js/common.js"></script>
    
    <script>
    function toreturn(flag){
      if(flag==1){//改变比例
        var return_percent=$("[name='return_percent']").val();
        var return_point=parseInt(${return_point}*return_percent/100);
         $("[name='return_point']").val(return_point);
      }
      if(flag==2){//改变积分
         var return_point=$("[name='return_point']").val();
        var return_percent=parseFloat(return_point*100/${return_point}).toFixed(2);
         $("[name='return_percent']").val(return_percent);
      }
    }
        function addreturn(){
           var return_percent=$("[name='return_percent']").val();
           var return_point=$("[name='return_point']").val();
           if ((return_percent==null || return_percent =="")&&(return_point==null || "" ==return_point)){
        	   opt_error("请输入退款额度");
        	   return ;
           }
			var params={"id":'${norder.id}',"return_percent":return_percent,
			             "return_point":return_point
			           }
        	$.post("${ctx}/manage/norder/nreturn",params,function(data){
					if(data.code=="0"){
						opt_success("退款成功",0);
					}else{
						opt_error(data.msg);
					}
				},"json")
        }
    </script>
</body>

</html>