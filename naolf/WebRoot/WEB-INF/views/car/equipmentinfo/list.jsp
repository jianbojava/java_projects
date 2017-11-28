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

</head>

<body class="gray-bg">
    <div class="wrapper wrapper-content animated fadeInRight">

            <div class="row">
                <div class="col-sm-12">
                    <div class="ibox float-e-margins">
                        <div class="ibox-title">
                            <h5>充电站管理 > 充电设备管理</h5>

                            <div class="ibox-tools">
                                <a class="collapse-link">
                                    <i class="fa fa-chevron-up"></i>
                                </a>
                            </div>
                        </div>
                        <div class="ibox-content">
                        	<div class="ibox-tools" style="float:left;">
                        		<shiro:hasPermission name="manage:equipmentinfo:add">
                                <a class="btn btn-success btn-outline" onclick="layer_show('新增充电设备','${ctx}/manage/equipmentinfo/add','','','')"><i class="fa fa-plus"></i> 新增充电设备</a>
                            	</shiro:hasPermission>
                            	<shiro:hasPermission name="manage:equipmentinfo:del">
                            	<div class="btn-group">
	                                <a data-toggle="dropdown" class="btn btn-success btn-outline dropdown-toggle"><i class="fa fa-cog"></i> 批量操作 <span class="caret"></span></a>
	                                <ul class="dropdown-menu">
	                                    <li><a onclick="delM(0)">正常</a></li>
	                                    <li><a onclick="delM(1)">无效</a></li>
	                                </ul>
	                            </div>
	                            </shiro:hasPermission>
	                            <shiro:hasPermission name="manage:equipmentinfo:delT">
	                            <a class="btn btn-success btn-outline" onclick="del_MT()"><i class="fa fa-trash"></i> 批量删除</a>
	                            </shiro:hasPermission>
                            </div>
							<form action="${ctx }/manage/equipmentinfo/list" method="post" id="searchForm" class="pull-right mail-search">
		                        <div class="input-group">
		                            <input type="text" class="form-control input-sm" name="keywords" value="${vo.keywords}" placeholder="搜索编号、名称等">
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
                                	<th data-toggle="true">编号</th>
                                	<th>组织机构代码</th>
                                    <th>设备型号</th>
                                    <th>设备类型</th>
                                    <th>生产日期</th>
                                    <th>所属充电站</th>
                                    <th>状态</th>
                                    <th>操作</th>
                                </tr>
                                </thead>
                                <tbody>
                                <c:forEach items="${page.result }" var="c">
                                <tr>
                                	<td><input type="checkbox" class="i-checks" name="input[]" value="${c.equipmentID }"></td>
                                	<td>${c.equipmentID}</td>
                                	<td>${c.manufacturerID }</td>
                                	<td>
                                		<c:choose>
                                			<c:when test="${empty(c.equipmentModel) }">-</c:when>
                                			<c:otherwise>${c.equipmentModel }</c:otherwise>
                                		</c:choose>
                                	</td>
                                    <td>
                                         <c:choose>
                                         	<c:when test="${c.equipmentType==1}">直流设备</c:when>
                                            <c:when test="${c.equipmentType==2}">交流设备</c:when>
                                            <c:when test="${c.equipmentType==3}">交直流一体设备</c:when>
                                         </c:choose>
                                    </td>
                                    <td>
                                    	<c:choose>
                                			<c:when test="${empty(c.productionDate) }">-</c:when>
                                			<c:otherwise>${c.productionDate }</c:otherwise>
                                		</c:choose>
                                   	</td>
                                    <td>${c.stationName }</td>
                                    <td>
                                    	<c:choose>
                                    		<c:when test="${c.enabled==0 }"><span class="label label-primary">正常</span></c:when>
                                    		<c:otherwise><span class="label label-danger">无效</span></c:otherwise>
                                    	</c:choose>
                                    </td>
                                    <td>
                                    	<shiro:hasPermission name="manage:equipmentinfo:update">
                                    	<a class="btn btn-xs btn-white" onclick="layer_show('修改充电设备','${ctx}/manage/equipmentinfo/update/${c.equipmentID }','','','')"><i class="fa fa-pencil"></i> 修改</a>
                                    	</shiro:hasPermission>
                                    	<shiro:hasPermission name="manage:equipmentinfo:del">
                                    	<div class="btn-group">
			                                <a data-toggle="dropdown" class="btn btn-xs btn-white dropdown-toggle"><i class="fa fa-cog"></i> 操作 <span class="caret"></span></a>
			                                <ul class="dropdown-menu">
			                                    <li><a onclick="del(0,'${c.equipmentID}')" <c:if test="${c.enabled==0}">class="font-bold"</c:if>>正常</a></li>
			                                    <li><a onclick="del(1,'${c.equipmentID}')" <c:if test="${c.enabled==1}">class="font-bold"</c:if>>无效</a></li>
			                                </ul>
			                            </div>
			                            </shiro:hasPermission>
			                            <shiro:hasPermission name="manage:equipmentinfo:delT">
			                            <a class="btn btn-xs btn-white" onclick="del_T('${c.equipmentID}')"><i class="fa fa-trash"></i> 删除</a>
                                    	</shiro:hasPermission>
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
    <script src="${ctx}/statics/manage/js/common.js?v=1.0.0"></script>
    <script>
        $(document).ready(function(){$(".footable").footable();$("#pagination").html('${page.pageContent }');$(".i-checks").iCheck({checkboxClass:"icheckbox_square-green",radioClass:"iradio_square-green"});pageinit();});
    	function del(flg,id){
        	layer.confirm('确定要操作吗？', function(index){
			  layer.close(index);
			  $.post("${ctx}/manage/equipmentinfo/del/"+flg+"/"+id,function(data){
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
					  $.post("${ctx}/manage/equipmentinfo/del/"+flg+"/"+dels.substring(1),function(data){
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
        
        function del_T(id){
        	layer.confirm('确认要删除吗？', function(index){
			  layer.close(index);
			  $.post("${ctx}/manage/equipmentinfo/delT/"+id,function(data){
				if(data.code=="0"){
					opt_success("删除成功");
				}else{
					opt_error("系统繁忙，请稍后再试");
				}
			  },"json")
			});  
        }
        
        function del_MT(){
        	if($("[name='input[]']").filter(':checked').length>0){
        		layer.confirm('确定要删除吗？', function(index){
					  layer.close(index);
					  $("[name='input[]']").filter(':checked').each(function(){
						 dels+=","+$(this).attr("value");
					  })
					  $.post("${ctx}/manage/equipmentinfo/delT/"+dels,function(data){
						if(data.code=="0"){
							opt_success("删除成功");
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