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
    
    <title>智行家</title>
    <meta name="keywords" content="后台bootstrap框架,后台HTML,响应式后台">
    <meta name="description" content="基于Bootstrap3最新版本开发的扁平化主题，她采用了主流的左右两栏式布局，使用了Html5+CSS3等现代技术">

    <link rel="shortcut icon" href="favicon.ico">
	<link href="${ctx}/statics/manage/css/bootstrap.min.css?v=3.3.5" rel="stylesheet">
    <link href="${ctx}/statics/manage/css/font-awesome.min.css?v=4.4.0" rel="stylesheet">
    <link href="${ctx}/statics/manage/css/plugins/footable/footable.core.css" rel="stylesheet">
    <link href="${ctx}/statics/manage/css/animate.min.css" rel="stylesheet">
    <link href="${ctx}/statics/manage/css/style.min.css?v=4.0.0" rel="stylesheet">
    <link href="${ctx}/statics/manage/css/plugins/iCheck/custom.css" rel="stylesheet">
    <link href="${ctx}/statics/manage/plugins/bootstrap-datetimepicker/css/bootstrap-datetimepicker.css" rel="stylesheet">

</head>

<body class="gray-bg">
    <div class="wrapper wrapper-content animated fadeInRight">

            <div class="row">
                <div class="col-sm-12">
                    <div class="ibox float-e-margins">
                        <div class="ibox-title">
                            <h5>车辆管理 > 动态管理</h5>

                            <div class="ibox-tools">
                                <a class="collapse-link">
                                    <i class="fa fa-chevron-up"></i>
                                </a>
                            </div>
                        </div>
                        <div class="ibox-content">
							<form action="${ctx }/manage/car/dync" method="post" id="searchForm" class="pull-right" style="width: 646px" onsubmit="return validParms();">
		                        <div class="input-group">
		                        	<input type="text" class="form-control input-sm" name="startDate" style="width: 200px" value="<fmt:formatDate value='${vo.startDate}' pattern='yyyy-MM-dd HH:mm'/>" placeholder="计划用车时间">
		                        	<input type="text" class="form-control input-sm" name="endDate" style="width: 200px" value="<fmt:formatDate value='${vo.endDate}' pattern='yyyy-MM-dd HH:mm'/>" placeholder="计划还车时间">
		                            <input type="text" class="form-control input-sm" name="keywords" style="width: 200px" value="${vo.keywords}" placeholder="搜索车辆编号、名称等">
		                            <input type="hidden" name="pageNo" value="${vo.pageNo }"/>
				      				<input type="hidden" name="pageSize" value="${vo.pageSize }">
		                            <div class="input-group-btn">
		                                <button type="button" class="btn btn-sm btn-primary">搜索</button>
		                            </div>
		                        </div>
		                    </form>
                            <table class="footable table table-stripped toggle-arrow-tiny" data-sort="false" data-page-size="${vo.pageSize }">
                                <thead>
                                <tr>
                                    <th data-toggle="true">编号</th>
                                    <th>名称</th>
                                    <th>车牌号</th>
                                    <th>网点</th>
                                    <th>车位</th>
                                    <th>调度员</th>
                                    <th>调度电话</th>
                                    <th>审核状态</th>
                                    <th>状态</th>
                                    <th>在库状态</th>
                                    <th data-hide="all">品牌</th>
                                    <th data-hide="all">型号</th>
                                    <th>操作</th>
                                </tr>
                                </thead>
                                <tbody>
                                <c:forEach items="${page.result }" var="c">
                                <tr>
                                	<td>${c.number }</td>
                                    <td>${c.name }</td>
                                    <td>${c.license }</td>
                                    <td>
                                    	<c:choose>
                                    		<c:when test="${fn:length(c.dot_name)>6}">
                                    			<span onmouseover="javascript:layer.tips('${c.dot_name}',this,{tips: [1, '#1ab394'],time: 3000});">${fn:substring(c.dot_name,0,6)}<i class="fa fa-info-circle"></i>
                                    		</c:when>
                                    		<c:otherwise>
                                    			${c.dot_name }
                                    		</c:otherwise>
                                    	</c:choose>
                                    </td>
                                    <td>
                                    	<c:choose>
                                    		<c:when test="${fn:length(c.park_name)>6}">
                                    			<span onmouseover="javascript:layer.tips('${c.park_name}',this,{tips: [1, '#1ab394'],time: 3000});">${fn:substring(c.park_name,0,6)}<i class="fa fa-info-circle"></i>
                                    		</c:when>
                                    		<c:otherwise>
                                    			${c.park_name }
                                    		</c:otherwise>
                                    	</c:choose>
                                    </td>
                                    <td>
                                    	<c:choose>
                                    		<c:when test="${empty(c.dispatch_name) }">-</c:when>
                                    		<c:otherwise>${c.dispatch_name }</c:otherwise>
                                    	</c:choose>
                                    </td>
                                    <td>
                                    	<c:choose>
                                    		<c:when test="${empty(c.dispatch_mobile) }">-</c:when>
                                    		<c:otherwise>${c.dispatch_mobile }</c:otherwise>
                                    	</c:choose>
                                    </td>
                                    <td><c:choose><c:when test="${c.review==0}"><span class="label label-warning">等待审核</span></c:when><c:when test="${c.review==1}"><span class="label label-primary">已通过</span></c:when><c:otherwise><span class="label label-danger">未通过</span></c:otherwise></c:choose></td>
                                    <td><c:choose><c:when test="${c.enabled==0}"><span class="label label-primary">上线</span></c:when><c:when test="${c.enabled==1}"><span class="label label-danger">下线</span></c:when><c:otherwise><span class="label label-warning">维修</span></c:otherwise></c:choose></td>
                                    <td><c:choose><c:when test="${c.rent_status==0}"><span class="label label-primary">空闲</span></c:when><c:otherwise><span class="label label-warning">忙碌</span></c:otherwise></c:choose></td>
                                    <td>${c.brand_name }</td>
                                    <td>${c.model_name }</td>
                                    <td>
                                    	<shiro:hasPermission name="manage:car:dyncDetail">
                                    	<a class="btn btn-xs btn-white" onclick="layer_show('','${ctx}/manage/car/dyncDetail?id=${c.id }','','','true')"><i class="fa fa-eye"></i> 查看动态</a>
                                    	</shiro:hasPermission>
                                    </td>
                                </tr>
                                </c:forEach>
                                </tbody>
                                <tfoot>
                                <tr>
                                    <td colspan="12" id="pagination"></td>
                                </tr>
                                </tfoot>
                            </table>

                        </div>
                    </div>
                </div>
            </div>
            
        </div>
    <script src="${ctx}/statics/manage/js/jquery.min.js?v=2.1.4"></script>
    <script src="${ctx}/statics/manage/js/bootstrap.min.js?v=3.3.5"></script>
    <script src="${ctx}/statics/manage/js/content.min.js?v=1.0.0"></script>
    <script src="${ctx}/statics/manage/js/plugins/footable/footable.all.min.js"></script>
    <script src="${ctx}/statics/manage/js/plugins/iCheck/icheck.min.js"></script>
    <script src="${ctx}/statics/manage/plugins/layer/layer.js"></script>
    <script src="${ctx}/statics/manage/plugins/bootstrap-datetimepicker/js/bootstrap-datetimepicker.js"></script>
    <script src="${ctx}/statics/manage/plugins/bootstrap-datetimepicker/js/locales/bootstrap-datetimepicker.zh-CN.js"></script>
    <script src="${ctx}/statics/manage/js/common.js?v=1.0.0"></script>
    <script>
        $(document).ready(function(){$(".footable").footable();$("#pagination").html('${page.pageContent }');$(".i-checks").iCheck({checkboxClass:"icheckbox_square-green",radioClass:"iradio_square-green"});pageinit();});
    	$("[name='startDate'],[name='endDate']").datetimepicker({
            language: 'zh-CN',
			format: "yyyy-mm-dd hh:ii",
			maxView:3,
			minuteStep:1,
       		autoclose: true,
       		todayBtn: true
    	});
    	//审核
    	function review(flg,id){
    		layer.confirm('确定要操作吗？', function(index){
			  layer.close(index);
			  $.post("${ctx}/manage/car/review/"+flg+"/"+id,function(data){
					if(data.code=="0"){
						opt_success("操作成功");
					}else{
						opt_error("系统繁忙，请稍后再试");
					}
				},"json")
			}); 
    	}
    	function reviewM(flg){
    		if($("[name='input[]']").filter(':checked').length>0){
        		layer.confirm('确定要操作吗？', function(index){
					  layer.close(index);
					  $("[name='input[]']").filter(':checked').each(function(){
						 dels+=","+$(this).attr("value");
					  })
					  $.post("${ctx}/manage/car/review/"+flg+"/"+dels,function(data){
						if(data.code=="0"){
							opt_success("操作成功");
						}else{
							opt_error("系统繁忙，请稍后再试");
						}
					  },"json")
				});
        	}else{
        		layer.msg("请选择操作项", {
					icon : 2
				});
        	}
    	}
    	function del(flg,id){
        	layer.confirm('确定要操作吗？', function(index){
			  layer.close(index);
			  $.post("${ctx}/manage/car/del/"+flg+"/"+id,function(data){
				if(data.code=="0"){
					opt_success("操作成功");
				}else{
					opt_error("系统繁忙，请稍后再试");
				}
			  },"json")
			});  
        }
        function delM(flg){
        	if($("[name='input[]']").filter(':checked').length>0){
        		layer.confirm('确定要操作吗？', function(index){
					  layer.close(index);
					  $("[name='input[]']").filter(':checked').each(function(){
						 dels+=","+$(this).attr("value");
					  })
					  $.post("${ctx}/manage/car/del/"+flg+"/"+dels.substring(1),function(data){
						if(data.code=="0"){
							opt_success("操作成功");
						}else{
							opt_error("系统繁忙，请稍后再试");
						}
					  },"json")
				});
        	}else{
        		layer.msg("请选择操作项", {
					icon : 2
				});
        	}
        }
        
        function validParms(){
        	var star_date=$("[name='startDate']").val();
        	var end_date=$("[name='endDate']").val();
        	if($.trim(star_date)!=""){
        		if($.trim(end_date)==""){
        			layer.msg("还车时间不能为空", {
						icon : 0
					});
					return false;
        		}else{
        			if(new Date(star_date)>=new Date(end_date)){
        				layer.msg("用车时间不能大于还车时间", {
							icon : 0
						});
						return false;
        			}
        			return true;
        		}
        	}
        	if($.trim(end_date)!=""){
        		if($.trim(star_date)==""){
        			layer.msg("用车时间不能为空", {
						icon : 0
					});
					return false;
        		}else{
        			if(new Date(star_date)>=new Date(end_date)){
        				layer.msg("用车时间不能大于还车时间", {
							icon : 0
						});
						return false;
        			}
        			return true;
        		}
        	}
        }
    </script>
</body>

</html>