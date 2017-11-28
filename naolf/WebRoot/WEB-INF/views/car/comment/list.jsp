<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<%@ taglib prefix="shiro-ex" uri="http://www.coco-sh.com/tags/shiro" %>
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
    <link href="${ctx}/statics/manage/css/plugins/chosen/chosen.css" rel="stylesheet">

</head>

<body class="gray-bg">
    <div class="wrapper wrapper-content animated fadeInRight">

            <div class="row">
                <div class="col-sm-12">
                    <div class="ibox float-e-margins">
                        <div class="ibox-title">
                            <h5>车辆管理 > 评论管理</h5>

                            <div class="ibox-tools">
                                <a class="collapse-link">
                                    <i class="fa fa-chevron-up"></i>
                                </a>
                            </div>
                        </div>
                        <div class="ibox-content">
                        	<div class="ibox-tools" style="float:left;">
                        	   <shiro-ex:hasAnyPermissions name="manage:comment:add,manage:comment:sadd">
                        	   <a class="btn btn-success btn-outline" onclick="layer_show('新增评论','${ctx}/manage/comment/add/${type }','','','')"><i class="fa fa-plus"></i> 新增评论</a>
                               </shiro-ex:hasAnyPermissions>
                               <shiro-ex:hasAnyPermissions name="manage:comment:del,manage:comment:sdel">
                               <div class="btn-group">
	                                <a data-toggle="dropdown" class="btn btn-success btn-outline dropdown-toggle"><i class="fa fa-cog"></i> 批量操作 <span class="caret"></span></a>
	                                <ul class="dropdown-menu">
	                                    <li><a onclick="delM(0)">正常</a></li>
	                                    <li><a onclick="delM(1)">屏蔽</a></li>
	                                </ul>
	                            </div>
	                            </shiro-ex:hasAnyPermissions>
                            </div>
							<form action="${ctx }/manage/comment/<c:choose><c:when test='${type==0}'>list</c:when><c:otherwise>slist</c:otherwise></c:choose>" method="post" id="searchForm"  class="pull-right" style="width: 645px">
		                        <div class="input-group">
		                            <select data-placeholder="选择星级..." style="width:200px;" class="chosen-select" tabindex="2" name="star">
		                            	<option value="">选择星级...</option>
		                            	<option value="1">★</option>
		                            	<option value="2">★★</option>
		                            	<option value="3">★★★</option>
		                            	<option value="4">★★★★</option>
		                            	<option value="5">★★★★★</option>
		                            </select>
		                            <select data-placeholder="选择状态..." style="width:200px;" class="chosen-select" tabindex="2" name="enabled">
		                            	<option value="">选择状态...</option>
		                            	<option value="0">正常</option>
		                            	<option value="1">屏蔽</option>
		                            </select>
		                            <input type="text" class="form-control input-sm" style="width: 200px;float: right;" name="keywords" value="${vo.keywords}" placeholder="搜索内容、评论人、手机号等">
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
                                	<th width="30"><input type="checkbox" class="i-checks" name="input"></th>
                                    <c:choose>
                                    	<c:when test="${type==0}">
	                               			<th>车辆编号</th>
	                                    	<th>车辆名称</th>
	                               		</c:when>
	                               		<c:otherwise>
	                               			<th>充电站编号</th>
	                                    	<th>充电站名称</th>
	                               		</c:otherwise>
                                    </c:choose>
                                    <th>评论人</th>
                                    <th width="30%">内容</th>
                                    <th>星级</th>
                                    <th>时间</th>
                                    <th>状态</th>
                                    <th>操作</th>
                                </tr>
                                </thead>
                                <tbody>
                                <c:forEach items="${page.result }" var="c">
                                <tr>
                                	<td><input type="checkbox" class="i-checks" name="input[]" value="${c.id }"></td>
                                	<c:choose>
                                		<c:when test="${type==0}">
                                			<td>${c.car_number }</td>
                                    		<td>${c.car_name }</td>
                                		</c:when>
                                		<c:otherwise>
                                			<td>${c.station_id }</td>
                                    		<td>${c.station_name }</td>
                                		</c:otherwise>
                                	</c:choose>
                                    <td>
                                    	<c:choose>
                                    		<c:when test="${empty(c.mem_name) }">-</c:when>
                                    		<c:otherwise>${c.mem_name }</c:otherwise>
                                    	</c:choose>
                                    </td>
                                    <td>${c.content }</td>
                                    <td>
                                    	<c:choose>
                                    		<c:when test="${c.star==1 }">★</c:when>
                                    		<c:when test="${c.star==2 }">★★</c:when>
                                    		<c:when test="${c.star==3 }">★★★</c:when>
                                    		<c:when test="${c.star==4 }">★★★★</c:when>
                                    		<c:when test="${c.star==5 }">★★★★★</c:when>
                                    	</c:choose>
                                    </td>
                                    <td><fmt:formatDate value="${c.create_date }" pattern="yyyy-MM-dd HH:mm:ss"/></td>
                                    <td><c:choose><c:when test="${c.enabled==0}"><span class="label label-primary">正常</span></c:when><c:otherwise><span class="label label-danger">屏蔽</span></c:otherwise></c:choose></td>
                                    <td>
                                    	<shiro-ex:hasAnyPermissions name="manage:comment:del,manage:comment:sdel">
                                    	<div class="btn-group">
			                                <a data-toggle="dropdown" class="btn btn-xs btn-white dropdown-toggle"><i class="fa fa-cog"></i> 操作 <span class="caret"></span></a>
			                                <ul class="dropdown-menu">
			                                    <li><a onclick="del(0,'${c.id}')" <c:if test="${c.enabled==0}">class="font-bold"</c:if>>正常</a></li>
			                                    <li><a onclick="del(1,'${c.id}')" <c:if test="${c.enabled==1}">class="font-bold"</c:if>>屏蔽</a></li>
			                                </ul>
			                            </div>
			                            </shiro-ex:hasAnyPermissions>
                                    </td>
                                </tr>
                                </c:forEach>
                                </tbody>
                                <tfoot>
                                <tr>
                                    <td colspan="9" id="pagination"></td>
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
    <script src="${ctx}/statics/manage/js/plugins/chosen/chosen.jquery_.js"></script>
    <script src="${ctx}/statics/manage/js/common.js?v=1.0.0"></script>
    <script>
        $(document).ready(function(){
        	$(".footable").footable();$("#pagination").html('${page.pageContent }');$(".i-checks").iCheck({checkboxClass:"icheckbox_square-green",radioClass:"iradio_square-green"});$('.chosen-select').chosen();pageinit();
        	//初始化select控件
        	$("[name='star']").val('${vo.star}');
        	$("[name='enabled']").val('${vo.enabled}');
        	$(".chosen-select").trigger("chosen:updated");
        });
        function del(flg,id){
        	layer.confirm('确定要操作吗？', function(index){
			  layer.close(index);
			  $.post("${ctx}/manage/comment/del/"+flg+"/"+id,function(data){
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
					  $.post("${ctx}/manage/comment/del/"+flg+"/"+dels.substring(1),function(data){
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
    </script>
</body>

</html>