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

    <link rel="shortcut icon" href="favicon.ico"> <link href="${ctx}/statics/manage/css/bootstrap.min.css?v=3.3.5" rel="stylesheet">
    <link href="${ctx}/statics/manage/css/font-awesome.min.css?v=4.4.0" rel="stylesheet">
    <link href="${ctx}/statics/manage/css/plugins/footable/footable.core.css" rel="stylesheet">

    <link href="${ctx}/statics/manage/css/animate.min.css" rel="stylesheet">
    <link href="${ctx}/statics/manage/css/style.min.css?v=4.0.0" rel="stylesheet">
    <link href="${ctx}/statics/manage/css/plugins/iCheck/custom.css" rel="stylesheet">

</head>

<body class="gray-bg">
    <div class="wrapper wrapper-content animated fadeInRight">

            <div class="row">
                <div class="col-sm-12">
                    <div class="ibox float-e-margins">
                        <div class="ibox-title">
                            <h5>系统管理 > 日志管理</h5>

                            <div class="ibox-tools">
                                <a class="collapse-link">
                                    <i class="fa fa-chevron-up"></i>
                                </a>
                            </div>
                        </div>
                        <div class="ibox-content">
                        	<div class="ibox-tools" style="float:left;">
                        		<shiro:hasPermission name="manage:log:del">
                                <a class="btn btn-success btn-outline" onclick="delM()"><i class="fa fa-trash"></i> 批量删除</a>
                                </shiro:hasPermission>
                            </div>
							<form action="${ctx }/manage/log/list" method="post" id="searchForm" class="pull-right mail-search">
		                        <div class="input-group">
		                            <input type="text" class="form-control input-sm" name="keywords" value="${vo.keywords}" placeholder="搜索模块、方法等">
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
                                	<th  width="30px" style="display: none"><input type="checkbox" class="i-checks" name="input"></th>
                                    <th data-toggle="true">用户名</th>
                                    <th>模块</th>
				                    <th>方法</th>
				                    <th>异常</th>
				                    <th>IP</th>
				                    <th>时间</th>
				                    <th width="80" style="display: none">操作</th>
                                </tr>
                                </thead>
                                <tbody>
                                <c:forEach items="${page.result }" var="log">
                                <tr>
                                	<td style="display: none"><input type="checkbox" class="i-checks" name="input[]" value="${log.id }"></td>
                                	<td>${log.username }</td>
				                    <td>${log.module }</td>
				                    <td>${log.method }</td>
				                    <td><c:choose><c:when test='${empty(log.exception)}'>-</c:when><c:otherwise>${log.exception }</c:otherwise></c:choose></td>
				                    <td>${log.ip }</td>
				                    <td><fmt:formatDate value="${log.create_date}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
				                    <td style="display: none">
				                    	<shiro:hasPermission name="manage:log:del">
                                    	<a class="btn btn-xs btn-white" onclick="del('${log.id}')"><i class="fa fa-trash"></i> 删除</a>
                                    	</shiro:hasPermission>
                                    </td>
                                </tr>
                                </c:forEach>
                                </tbody>
                                <tfoot>
                                <tr>
                                    <td colspan="8" id="pagination"></td>
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
    <script src="${ctx}/statics/manage/js/plugins/iCheck/icheck.min.js"></script>
    <script src="${ctx}/statics/manage/plugins/layer/layer.js"></script>
    <script src="${ctx}/statics/manage/js/common.js?v=1.0.0"></script>
    <script>
        $(document).ready(function(){$(".footable").footable();$("#pagination").html('${page.pageContent }');$(".i-checks").iCheck({checkboxClass:"icheckbox_square-green",radioClass:"iradio_square-green"});pageinit();});
    	function del(id){
        	layer.confirm('确认要删除吗？', function(index){
			  layer.close(index);
			  $.post("${ctx}/manage/log/del/"+id,function(data){
				if(data.code=="0"){
					opt_success("已删除");
				}else{
					opt_error("删除失败");
				}
			  },"json")
			});  
        }
       function delM(){
        	if($("[name='input[]']").filter(':checked').length>0){
        		layer.confirm('确认要删除吗？', function(index){
					  layer.close(index);
					  $("[name='input[]']").filter(':checked').each(function(){
						 dels+=","+$(this).attr("value");
					  })
					  $.post("${ctx}/manage/log/del/"+dels.substring(1),function(data){
						if(data.code=="0"){
							opt_success("已删除");
						}else{
							opt_error("删除失败");
						}
					  },"json")
				});
        	}else{
        		layer.msg("请选择删除项", {
					icon : 2
				});
        	}
        }
    </script>
</body>

</html>