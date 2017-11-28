<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="customfn" uri="http://www.coco-sh.com/tags/custom-functions" %>
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

    <link rel="shortcut icon" href="favicon.ico"> <link href="${ctx}/statics/manage/css/bootstrap.min.css?v=3.3.5" rel="stylesheet">
    <link href="${ctx}/statics/manage/css/font-awesome.min.css?v=4.4.0" rel="stylesheet">
    <link href="${ctx}/statics/manage/css/plugins/footable/footable.core.css" rel="stylesheet">
    <link href="${ctx}/statics/manage/css/animate.min.css" rel="stylesheet">
    <link href="${ctx}/statics/manage/css/style.min.css?v=4.0.0" rel="stylesheet"><base target="_blank">

</head>

<body class="gray-bg">
    <div class="wrapper wrapper-content animated fadeInRight">

            <div class="row">
                <div class="col-sm-12">
                    <div class="ibox float-e-margins">
                        <div class="ibox-content">
							<div class="alert alert-warning">
								代表当前会话
                        	</div>
                            <table class="footable table table-stripped toggle-arrow-tiny" data-page-size="10">
                                <thead>
                                <tr>
                                    <th>会话ID</th>
                                    <th>用户名</th>
                                    <th>主机地址</th>
                                    <th>创建时间</th>
                                    <th>最后访问时间</th>
                                    <th>状态</th>
                                    <th data-sort-ignore="true">操作</th>
                                </tr>
                                </thead>
                                <tbody>
                                <c:forEach items="${sessions}" var="session">
							            <tr <c:if test="${session.id==cur }">class="warning"</c:if>>
							                <td>${session.id}</td>
							                <td>${customfn:principal(session)}</td>
							                <td>${session.host}</td>
							                <td><fmt:formatDate value="${session.startTimestamp}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
							                <td><fmt:formatDate value="${session.lastAccessTime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
							                <td>${customfn:isForceLogout(session) ? '<span class="label label-danger">退出</span>' : '<span class="label label-primary">正常</span>'}</td>
							                <td>
								                <shiro:hasPermission name="manage:sessions:forceLogout">
								                 <a class="btn btn-xs btn-white" <c:if test="${customfn:isForceLogout(session)}">disabled</c:if><c:if test="${not customfn:isForceLogout(session)}"> onclick="forceout('${session.id}')"</c:if>><i class="fa fa-sign-out"></i> 强行退出</a>
								                </shiro:hasPermission>
							                </td>
							            </tr>
							        </c:forEach>
                                </tbody>
                                <tfoot>
                                <tr>
                                    <td colspan="7">
                                    	<div class="pull-left pagination-detail"><span class="pagination-info">当前共 ${sessionCount } 条会话记录</span></span></div>
                                        <ul class="pull-right pagination"></ul>
                                    </td>
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
    <script src="${ctx}/statics/manage/js/plugins/footable/footable.all.min.js"></script>
    <script src="${ctx}/statics/manage/js/content.min.js?v=1.0.0"></script>
    <script src="${ctx}/statics/manage/plugins/layer/layer.js"></script>
    <script src="${ctx}/statics/manage/js/common.js?v=1.0.0"></script>
    <script>
        $(document).ready(function(){$(".footable").footable();$(".footable2").footable()});
        function forceout(id){
        	layer.confirm('确认要强制退出吗？', function(index){
				  layer.close(index);
				  $.getJSON("${ctx}/manage/sessions/forceLogout/"+id,function(data){
	        		if(data.code=="0"){
						layer.msg("已强行退出", {
							icon : 1
						});
						setTimeout("javascript:location.reload()",1000)
					}else{
						opt_error("退出失败");
					}
	        	})
			});
        }
    </script>
</body>

</html>