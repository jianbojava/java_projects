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
    <link href="${ctx}/statics/manage/css/plugins/chosen/chosen.css" rel="stylesheet">

</head>

<body class="gray-bg">
    <div class="wrapper wrapper-content animated fadeInRight">

            <div class="row">
                <div class="col-sm-12">
                    <div class="ibox float-e-margins">
                        <div class="ibox-title">
                            <h5>系统管理 > 激活码管理</h5>
                            <div class="ibox-tools">
                                <a class="collapse-link">
                                    <i class="fa fa-chevron-up"></i>
                                </a>
                            </div>
                        </div>
                        <div class="ibox-content">
                        	<div class="ibox-tools" style="float:left;">
                        		<shiro:hasPermission name="manage:activation:add">
                                <a class="btn btn-success btn-outline" onclick="layer_show('添加激活码','${ctx}/manage/activation/add','','','')"><i class="fa fa-plus"></i> 新增激活码</a>
                                </shiro:hasPermission>
                            </div>
							<form action="${ctx }/manage/activation/list" method="post" id="searchForm" class="pull-right" target='_self'  style="width: 485px">
		                        <div class="input-group">
		                        <select data-placeholder="选择使用状态..." style="width:200px;float: right;" class="chosen-select" tabindex="2" name="enabled">
			                            	<option value="">选择状态...</option>
			                            	<option value="0">未使用</option>
			                            	<option value="1">已使用</option>
			                      </select>
		                            <input type="text" class="form-control input-sm" name="keywords" value="${vo.keywords}" style="width:200px;float: right;"  placeholder="搜索用户姓名,ID,激活码">
		                            <input type="hidden" name="pageNo" value="${vo.pageNo }"/>
				      				<input type="hidden" name="pageSize" value="${vo.pageSize }">
				      				<input type="hidden" name="user_id" value="${vo.user_id }">
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
                                    <th>状态</th>
                                    <th>姓名</th>
                                    <th>用户ID</th>
                                    <th>部门</th>
                                    <th>创建时间</th>
                                    <th width="130" style="display: none">操作</th>
                                </tr>
                                </thead>
                                <tbody>
                                <c:forEach items="${page.result }" var="activation">
                                <tr>
                                	<th><input type="checkbox" class="i-checks" name="input[]" value="${activation.id}"></th>
                                	<td>${activation.number }</td>
                                	<td>
                                	   <c:choose>
                                	     <c:when test="${activation.enabled==0}">未使用</c:when>
                                	     <c:otherwise>已使用</c:otherwise>
                                	  </c:choose>
                                	 </td>
                                	<td>${activation.name }</td>
                                	<td>${activation.user_number }</td>
                                	<td>${activation.depart_name }</td>
                                	<td><fmt:formatDate value="${activation.create_date }" pattern="yyyy-MM-dd hh:mm:ss"/></td>
                                    <td style="display: none">
                                    	<shiro:hasPermission name="manage:activation:update">
                                    	<a class="btn btn-xs btn-white" onclick="layer_show('修改学生','${ctx}/manage/activation/update/${activation.id}','','','')"><i class="fa fa-pencil"></i> 修改</a>
                                    	</shiro:hasPermission>
                                    	<shiro:hasPermission name="manage:activation:del">
                                		<a class="btn btn-xs btn-white" onclick="del('${activation.id}')"><i class="fa fa-trash"></i> 删除</a>
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
    <script src="${ctx}/statics/manage/js/plugins/chosen/chosen.jquery_.js"></script>
    <script src="${ctx}/statics/manage/plugins/layer/layer.js"></script>
    <script src="${ctx}/statics/manage/js/common.js?v=1.0.0"></script>
    <script>
        $(document).ready(function(){$(".footable").footable();$("#pagination").html('${page.pageContent }');$(".i-checks").iCheck({checkboxClass:"icheckbox_square-green",radioClass:"iradio_square-green"});pageinit();});
    	$("[name='enabled']").val('${vo.enabled}');
   		$(".chosen-select").trigger("chosen:updated");
   		$('.chosen-select').chosen();
    	function del(id){
        	layer.confirm('确定要操作吗？', function(index){
			  layer.close(index);
			  $.post("${ctx}/manage/activation/del/"+id,function(data){
				if(data.code=="0"){
					opt_success("操作成功");
				}else{
					opt_error("系统繁忙，请稍后再试");
				}
			  },"json")
			});  
			
        }
        function delM(){
	         if($("[name='input[]']").filter(':checked').length>0){
	              layer.confirm('确定要操作吗？', function(index){
	                  layer.close(index);
					  $("[name='input[]']").filter(':checked').each(function(){
						 dels+=","+$(this).attr("value");
					  })
					   $.post("${ctx}/manage/activation/del/"+dels.substring(1),function(data){
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