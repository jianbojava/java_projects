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
                            <h5>课程管理 > 规则管理</h5>
                            <div class="ibox-tools">
                                <a class="collapse-link">
                                    <i class="fa fa-chevron-up"></i>
                                </a>
                            </div>
                        </div>
                        <div class="ibox-content">
                        	<div class="ibox-tools" style="float:left;">
                        		<shiro:hasPermission name="manage:rule:add">
                                <a class="btn btn-success btn-outline" onclick="layer_show('新增规则','${ctx}/manage/rule/add','','','')"><i class="fa fa-plus"></i> 新增规则</a>
                                </shiro:hasPermission>
                                <shiro:hasPermission name="manage:rule:cdel">
                                <div class="btn-group" style="display: none">
	                                <a data-toggle="dropdown" class="btn btn-success btn-outline dropdown-toggle"><i class="fa fa-cog"></i> 批量操作 <span class="caret"></span></a>
	                                <ul class="dropdown-menu">
	                                    <li><a onclick="delM(0)" value="">正常</a></li>
	                                    <li><a onclick="delM(1)">无效</a></li>
	                                </ul>
	                            </div>
	                            </shiro:hasPermission>
                            </div>
							<form action="${ctx }/manage/rule/list" method="post" id="searchForm" class="pull-right mail-search" target='_self'>
		                        <div class="input-group"style="display: none">
		                            <input type="text" class="form-control input-sm" name="keywords" value="${vo.keywords}" placeholder="搜索规则名称">
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
                                    <th data-toggle="true">名称</th>
                                    <th>编码规则</th>
                                    <th>排序</th>
                                    <th>状态</th>
                                    <th width="130">操作</th>
                                </tr>
                                </thead>
                                <tbody>
                                <c:forEach items="${page.result }" var="r">
                                <tr>
                                	<th id="tdiv"><input type="checkbox" class="i-checks" name="input[]" value="${r.id }"></th>
                                	<td>${r.name }</td>
                                	<td>${r.sn_header}${r.sn_start}${r.sn_middle}${r.sn_end}</td>
                                	<td>${r.sort }</td>
                                	<td><c:choose><c:when test="${r.enabled==0}"><span class="label label-primary">正常</span></c:when><c:otherwise><span class="label label-danger">无效</span></c:otherwise></c:choose></td>
                                    <td>
                                    	<shiro:hasPermission name="manage:rule:update">
                                    	<a class="btn btn-xs btn-white" onclick="layer_show('修改规则','${ctx}/manage/rule/update/${r.id}','','','')"><i class="fa fa-pencil"></i> 修改</a>
                                    	</shiro:hasPermission>
                                    	<shiro:hasPermission name="manage:rule:del">
                                    	<div class="btn-group">
			                                <a data-toggle="dropdown" class="btn btn-xs btn-white dropdown-toggle"><i class="fa fa-cog"></i> 操作 <span class="caret"></span></a>
			                                <ul class="dropdown-menu">
			                                    <li><a onclick="del(0,'${r.id}')" <c:if test="${r.enabled==0}">class="font-bold"</c:if>>正常</a></li>
			                                    <li><a onclick="del(1,'${r.id}')" <c:if test="${r.enabled==1}">class="font-bold"</c:if>>无效</a></li>
			                                </ul>
			                            </div>
			                            </shiro:hasPermission>
                                    </td>
                                </tr>
                                </c:forEach>
                                </tbody>
                                <tfoot>
                                <tr>
                                    <td colspan="6" id="pagination"></td>
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
    <script src="${ctx}/statics/manage/js/common.js?v=1.0.0"></script>
    <script>
        $(document).ready(function(){$(".footable").footable();$("#pagination").html('${page.pageContent }');$(".i-checks").iCheck({checkboxClass:"icheckbox_square-green",radioClass:"iradio_square-green"});pageinit();});
    	function del(flg,id){
        	layer.confirm('确定要操作吗？', function(index){
			  layer.close(index);
			  $.post("${ctx}/manage/rule/status/"+flg+"/"+id,function(data){
				if(data.code=="0"){
					opt_success("操作成功");
				}else{
					opt_error("系统繁忙，请稍后再试");
				}
			  },"json")
			});  
        }
        function delM(flg){
            var tickets=[];
	         if($("[name='input[]']").filter(':checked').length>0){
	              layer.confirm('确定要操作吗？', function(index){
	                  layer.close(index);
					  $("#tdiv input").filter(':checked').each(function(){
						 dels+=","+$(this).attr("value");
					  });
					   $.post("${ctx}/manage/rule/del/"+flg+"/"+dels.substring(1),function(data){
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