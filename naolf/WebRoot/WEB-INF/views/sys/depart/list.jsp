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
    <link href="${ctx}/statics/manage/plugins/jquery-treetable/jquery.treetable.theme.default.css" rel="stylesheet">
    <link href="${ctx}/statics/manage/plugins/jquery-treetable/jquery.treetable.css" rel="stylesheet">
    
</head>

<body class="gray-bg">
    <div class="wrapper wrapper-content animated fadeInRight">

            <div class="row">
                <div class="col-sm-12">
                    <div class="ibox float-e-margins">
                        <div class="ibox-title">
                            <h5>系统管理 > 部门管理</h5>

                            <div class="ibox-tools">
                                <a class="collapse-link">
                                    <i class="fa fa-chevron-up"></i>
                                </a>
                            </div>
                        </div>
                        <div class="ibox-content" >
                        	<div class="ibox-tools" style="float:left;display: none" >
                        		<shiro:hasPermission name="manage:depart:add">
                                <a class="btn btn-success btn-outline" onclick="layer_show('新增部门','${ctx}/manage/depart/add','','','')"><i class="fa fa-plus"></i> 新增部门</a>
                                </shiro:hasPermission>
                                <shiro:hasPermission name="manage:depart:del">
                                <div class="btn-group">
	                                <a data-toggle="dropdown" class="btn btn-success btn-outline dropdown-toggle"><i class="fa fa-cog"></i> 批量操作 <span class="caret"></span></a>
	                                <ul class="dropdown-menu">
	                                    <li><a onclick="delM(0)">正常</a></li>
	                                    <li><a onclick="delM(1)">无效</a></li>
	                                </ul>
	                            </div>
	                            </shiro:hasPermission>
                            </div>
							<form action="${ctx }/manage/depart/list" method="post" id="searchForm" class="pull-right mail-search" style="display: none">
		                        <div class="input-group">
		                            <input type="text" class="form-control input-sm" name="keywords" value="${vo.keywords}" placeholder="搜索部门名称等">
		                            <input type="hidden" name="pageNo" value="${vo.pageNo }"/>
				      				<input type="hidden" name="pageSize" value="${vo.pageSize }">
		                            <div class="input-group-btn">
		                                <button type="button" class="btn btn-sm btn-primary">搜索</button>
		                            </div>
		                        </div>
		                    </form>
                              <table id="sysDepartment_table" class="footable table table-stripped toggle-arrow-tiny" data-sort="false"  >
                                <thead>
                                <tr >
                                	<th data-toggle="true">&nbsp;&nbsp;名称</th>
                                    <th>&nbsp;&nbsp;描述</th>
                                    <th>&nbsp;&nbsp;排序</th>
                                    <th>&nbsp;&nbsp;服务部门</th>
                                    <th>&nbsp;&nbsp;状态</th>
                                    <th>&nbsp;&nbsp;操作</th>
                                </tr>
                                </thead>
                                <c:if test="${not empty depart}">
                               <!-- 1 -->
                                 <tr data-tt-id="${depart.id}" style="height: 44px;">
		                                	<td>${depart.name }</td>
		                                	<td>${depart.description }</td>
		                                    <td>${depart.sort}</td>
		                                    <td><c:choose><c:when test="${depart.type==0}">是</c:when><c:otherwise>否</c:otherwise></c:choose></td>
		                                    <td><c:choose><c:when test="${depart.enabled==0}"><span class="label label-primary">有效&nbsp;&nbsp;&nbsp;</span></c:when><c:otherwise><span class="label label-danger">无效&nbsp;&nbsp;&nbsp;</span></c:otherwise></c:choose></td>
		                                    <td>
		                                    	<shiro:hasPermission name="manage:depart:update">
		                                    	<a class="btn btn-xs btn-white" onclick="layer_show('修改部门','${ctx}/manage/depart/update/${depart.id }','','','')"><i class="fa fa-pencil"></i> 修改</a>
		                                    	</shiro:hasPermission>
		                                    	<shiro:hasPermission name="manage:depart:add">
					                             <a class="btn  btn-xs btn-white" onclick="layer_show('新增子部门','${ctx}/manage/depart/add/${depart.id}/${depart.grade+1}','','','')"><i class="fa fa-plus"></i> 新增子</a>
					                            </shiro:hasPermission>
		                                    </td>
		                                </tr>
		                                <!-- 2 -->
                                      <c:forEach items="${depart.child}" var="a">
		                                <tr data-tt-id="${a.id}" data-tt-parent-id="${a.parent_id}" style="height: 44px;">
		                                	<td>${a.name }</td>
		                                	<td>${a.description }</td>
		                                    <td>${a.sort }</td>
		                                    <td><c:choose><c:when test="${a.type==0}">是</c:when><c:otherwise>否</c:otherwise></c:choose></td>
		                                    <td><c:choose><c:when test="${a.enabled==0}"><span class="label label-primary">有效&nbsp;&nbsp;&nbsp;</span></c:when><c:otherwise><span class="label label-danger">无效&nbsp;&nbsp;&nbsp;</span></c:otherwise></c:choose></td>
		                                    <td>
		                                    	<shiro:hasPermission name="manage:depart:update">
		                                    	<a class="btn btn-xs btn-white" onclick="layer_show('修改部门','${ctx}/manage/depart/update/${a.id }','','','')"><i class="fa fa-pencil"></i> 修改</a>
		                                    	</shiro:hasPermission>
		                                    	<shiro:hasPermission name="manage:depart:del">
		                                    	<div class="btn-group" style="display: none">
					                                <a data-toggle="dropdown" class="btn btn-xs btn-white dropdown-toggle"><i class="fa fa-cog"></i> 操作 <i class="caret"></i></a>
					                                <ul class="dropdown-menu">
					                                    <li><a onclick="del(0,'${a.id}')" <c:if test="${a.enabled==0}">class="font-bold"</c:if>>正常</a></li>
					                                    <li><a onclick="del(1,'${a.id}')" <c:if test="${a.enabled==1}">class="font-bold"</c:if>>无效</a></li>
					                                </ul>
					                            </div>
					                            </shiro:hasPermission>
					                            <shiro:hasPermission name="manage:depart:add">
					                             <a class="btn  btn-xs btn-white" onclick="layer_show('新增子部门','${ctx}/manage/depart/add/${a.id}/${a.grade+1}','','','')"><i class="fa fa-plus"></i> 新增子</a>
					                            </shiro:hasPermission>
		                                    </td>
		                                </tr>
		                                <!-- 3-->
		                                 <c:forEach items="${a.child }" var="b">
				                                <tr data-tt-id="${b.id}" data-tt-parent-id="${b.parent_id}" style="height: 44px;">
				                                	<td>${b.name }</td>
				                                	<td>${b.description }</td>
				                                    <td>${b.sort }</td>
				                                    <td><c:choose><c:when test="${b.type==0}">是</c:when><c:otherwise>否</c:otherwise></c:choose></td>
				                                    <td><c:choose><c:when test="${b.enabled==0}"><span class="label label-primary">有效&nbsp;&nbsp;&nbsp;</span></c:when><c:otherwise><span class="label label-danger">无效&nbsp;&nbsp;&nbsp;</span></c:otherwise></c:choose></td>
				                                    <td>
				                                    	<shiro:hasPermission name="manage:depart:update">
				                                    	<a class="btn btn-xs btn-white" onclick="layer_show('修改部门','${ctx}/manage/depart/update/${b.id }','','','')"><i class="fa fa-pencil"></i> 修改</a>
				                                    	</shiro:hasPermission>
				                                    	 <shiro:hasPermission name="manage:depart:add">
							                             <a class="btn  btn-xs btn-white" onclick="layer_show('新增子部门','${ctx}/manage/depart/add/${b.id}/${b.grade+1}','','','')"><i class="fa fa-plus"></i> 新增子</a>
							                            </shiro:hasPermission>
				                                    </td>
				                                </tr>
				                                <!-- 4-->
				                                <c:forEach items="${b.child }" var="c">
					                                <tr data-tt-id="${c.id}" data-tt-parent-id="${c.parent_id}" style="height: 44px;">
					                                	<td>${c.name }</td>
					                                	<td>${c.description }</td>
					                                    <td>${c.sort }</td>
					                                    <td><c:choose><c:when test="${c.type==0}">是</c:when><c:otherwise>否</c:otherwise></c:choose></td>
					                                    <td><c:choose><c:when test="${c.enabled==0}"><span class="label label-primary">有效&nbsp;&nbsp;&nbsp;</span></c:when><c:otherwise><span class="label label-danger">无效&nbsp;&nbsp;&nbsp;</span></c:otherwise></c:choose></td>
					                                    <td>
					                                    	<shiro:hasPermission name="manage:depart:update">
					                                    	<a class="btn btn-xs btn-white" onclick="layer_show('修改部门','${ctx}/manage/depart/update/${c.id }','','','')"><i class="fa fa-pencil"></i> 修改</a>
					                                    	</shiro:hasPermission>
					                                    	 <shiro:hasPermission name="manage:depart:add">
								                             <a class="btn  btn-xs btn-white" onclick="layer_show('新增子部门','${ctx}/manage/depart/add/${c.id}/${c.grade+1}','','','')"><i class="fa fa-plus"></i> 新增子</a>
								                            </shiro:hasPermission>
					                                    </td>
					                                </tr>
					                                
					                                   <!-- 5 -->
					                                <c:forEach items="${c.child }" var="d">
						                                <tr data-tt-id="${d.id}" data-tt-parent-id="${d.parent_id}" style="height: 44px;">
						                                	<td>${d.name }</td>
						                                	<td>${d.description }</td>
						                                    <td>${d.sort }</td>
						                                    <td><c:choose><c:when test="${d.type==0}">是</c:when><c:otherwise>否</c:otherwise></c:choose></td>
						                                    <td><c:choose><c:when test="${d.enabled==0}"><span class="label label-primary">有效&nbsp;&nbsp;&nbsp;</span></c:when><c:otherwise><span class="label label-danger">无效&nbsp;&nbsp;&nbsp;</span></c:otherwise></c:choose></td>
						                                    <td>
						                                    	<shiro:hasPermission name="manage:depart:update">
						                                    	<a class="btn btn-xs btn-white" onclick="layer_show('修改部门','${ctx}/manage/depart/update/${d.id }','','','')"><i class="fa fa-pencil"></i> 修改</a>
						                                    	</shiro:hasPermission>
						                                    	 <shiro:hasPermission name="manage:depart:add">
									                             <a class="btn  btn-xs btn-white" onclick="layer_show('新增子部门','${ctx}/manage/depart/add/${d.id}/${d.grade+1}','','','')"><i class="fa fa-plus"></i> 新增子</a>
									                            </shiro:hasPermission>
						                                    </td>
						                                </tr>
						                                     <!-- 6 -->
						                                <c:forEach items="${d.child }" var="e">
							                                <tr data-tt-id="${e.id}" data-tt-parent-id="${e.parent_id}" style="height: 44px;">
							                                	<td>${e.name }</td>
							                                	<td>${e.description }</td>
							                                    <td>${e.sort }</td>
							                                    <td><c:choose><c:when test="${e.type==0}">是</c:when><c:otherwise>否</c:otherwise></c:choose></td>
							                                    <td><c:choose><c:when test="${e.enabled==0}"><span class="label label-primary">有效&nbsp;&nbsp;&nbsp;</span></c:when><c:otherwise><span class="label label-danger">无效&nbsp;&nbsp;&nbsp;</span></c:otherwise></c:choose></td>
							                                    <td>
							                                    	<shiro:hasPermission name="manage:depart:update">
							                                    	<a class="btn btn-xs btn-white" onclick="layer_show('修改部门','${ctx}/manage/depart/update/${e.id }','','','')"><i class="fa fa-pencil"></i> 修改</a>
							                                    	</shiro:hasPermission>
							                                    	<!-- 
								                                    	 <shiro:hasPermission name="manage:depart:add">
											                             <a class="btn  btn-xs btn-white" onclick="layer_show('新增子部门','${ctx}/manage/depart/add/${e.id}/${e.grade+1}','','','')"><i class="fa fa-plus"></i> 新增子</a>
											                            </shiro:hasPermission>
										                             -->
							                                    </td>
							                                </tr>
		                                               </c:forEach><!-- 6 -->
	                                               </c:forEach><!-- 5 -->
                                               </c:forEach><!-- 4 -->
                                       </c:forEach><!-- 3 -->
                                
                                </c:forEach><!-- 2 -->
                                </c:if>
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
    <script src="${ctx}/statics/manage/js/jquery-ui-1.10.4.min.js?v=2.1.4"></script>
    <script src="${ctx}/statics/manage/js/bootstrap.min.js?v=3.3.5"></script>
    <script src="${ctx}/statics/manage/js/content.min.js?v=1.0.0"></script>
    <script src="${ctx}/statics/manage/js/plugins/footable/footable.all.min.js"></script>
    <script src="${ctx}/statics/manage/js/plugins/iCheck/icheck.min.js"></script>
    <script src="${ctx}/statics/manage/plugins/layer/layer.js"></script>
    <script src="${ctx}/statics/manage/js/common.js?v=1.0.0"></script>
    <script src="${ctx}/statics/manage/plugins/jquery-treetable/jquery.treetable.js"></script>
    
    <script>
        $(document).ready(function(){$(".footable").footable();$("#pagination").html('${page.pageContent }');$(".i-checks").iCheck({checkboxClass:"icheckbox_square-green",radioClass:"iradio_square-green"});pageinit();});
	     	$(function(){
	 	      jQuery("#sysDepartment_table").treetable('expandAll');
	 	   })
	 	   $("#sysDepartment_table").treetable('destroy');
	 	   $("#sysDepartment_table").treetable({ expandable: true });
	 	  
			$("#sysDepartment_table tbody").on("mousedown", "tr", function() {
			    return;
			  	$(".selected").not(this).removeClass("selected");
			  	$(this).toggleClass("selected");
			  	clickId=$(this).data("id");
			});
		
		$("#sysDepartment_table .file2, #example-advanced .folder").draggable({
		  helper: "clone",
		  opacity: .75,
		  refreshPositions: true,
		  revert: "invalid",
		  revertDuration: 300,
		  scroll: true
		});
		$("#sysDepartment_table .file2").each(function() {
		
		  $(this).parents("#sysDepartment_table tr").droppable({
		    accept: ".file2",
		    drop: function(e, ui) {
		      var droppedEl = ui.draggable.parents("tr");
		      $("#sysDepartment_table").treetable("move", droppedEl.data("ttId"), $(this).data("ttId"));
		    },
		    hoverClass: "accept",
		    over: function(e, ui) {
		      var droppedEl = ui.draggable.parents("tr");
		      if(this != droppedEl[0] && !$(this).is(".expanded")) {
		        $("#sysDepartment_table").treetable("expandNode", $(this).data("ttId"));
		      }
		    }
		  });
		});
	 	
	 	
    	function del(flg,id){
        	layer.confirm('确定要操作吗？', function(index){
			  layer.close(index);
			  $.post("${ctx}/manage/depart/del/"+flg+"/"+id,function(data){
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
					  $.post("${ctx}/manage/depart/del/"+flg+"/"+dels.substring(1),function(data){
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