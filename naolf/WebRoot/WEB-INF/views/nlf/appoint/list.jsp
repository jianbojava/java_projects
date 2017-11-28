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
                            <h5>课程管理 > 课程预约</h5>
                            <div class="ibox-tools">
                                <a class="collapse-link">
                                    <i class="fa fa-chevron-up"></i>
                                </a>
                            </div>
                        </div>
                        <div class="ibox-content">
                        	<div class="ibox-tools" style="float:left;">
                                <shiro:hasPermission name="manage:appoint:update">
                                <div class="btn-group" >
	                                <a data-toggle="dropdown" class="btn btn-success btn-outline" onclick="allEnd()" ><i class="fa fa-cog" ></i> 批量结业</a>
	                            </div>
	                            </shiro:hasPermission>
                            </div>
							<form action="${ctx }/manage/appoint/list" method="post" id="searchForm" class="pull-right" target='_self' style="width: 545px">
		                        <div class="input-group">
		                             <select data-placeholder="选择预约状态..." style="width:200px;" class="chosen-select" tabindex="2" name="status">
		                            	<option value="">选择预约状态...</option>
		                            	<option value="0">待审核</option>
		                            	<option value="1">已审核</option>
		                            	<option value="2">已取消</option>
		                            	<option value="3">训练中</option>
		                            	<option value="4">已结业</option>
		                            </select>
		                            <input type="text" class="form-control input-sm" name="keywords" style="float: right;width: 200px" value="${vo.keywords}" placeholder="会员姓名,学生姓名">
		                            <input type="hidden" name="pageNo" value="${vo.pageNo }"/>
				      				<input type="hidden" name="pageSize" value="${vo.pageSize }">
				      				<input type="hidden" name="course_id" value="${vo.course_id}">
		                            <div class="input-group-btn">
		                                <button type="button" class="btn btn-sm btn-primary">搜索</button>
		                            </div>
		                        </div>
		                    </form>
                            <table class="footable table table-stripped toggle-arrow-tiny" data-sort="false" data-page-size="${vo.pageSize }">
                                <thead>
                                <tr>
                                	<th width="30"><input type="checkbox" class="i-checks" name="input"></th>
                                    <th data-toggle="true">学员姓名</th>
                                    <th>学员年龄</th>
                                    <th>学员性别</th>
                                    <th>预约状态</th>
                                    <th>申请日期</th>
                                    <th>上课日期</th>
                                    <th data-hide="all">家长</th>
                                    <th data-hide="all">推荐人</th>
                                    <th>操作</th>
                                </tr>
                                </thead>
                                <tbody>
                                <c:forEach items="${page.result }" var="appoint">
                                <tr>
                                	<th><input type="checkbox" <c:if test="${appoint.status!=3 }">disabled="disabled" </c:if>  class="i-checks" name="input[]" value="${appoint.id}"></th>
                                	<td>${appoint.student_name }</td>
                                	<td>${appoint.student_age }</td>
                                	<td><c:if test="${appoint.student_gender==0}">男</c:if>
                                	    <c:if test="${appoint.student_gender==1}">女</c:if>
                                	</td>
				                    <td>
				                        <c:choose>
				                           <c:when test="${appoint.status==0}"><span>待审核</span></c:when>
				                           <c:when test="${appoint.status==1}"><span>已审核</span></c:when>
				                           <c:when test="${appoint.status==2}"><span>已取消</span></c:when>
				                           <c:when test="${appoint.status==3}"><span>训练中</span></c:when>
				                           <c:when test="${appoint.status==4}"><span>已结业</span></c:when>
				                           <c:otherwise><span>已上过课</span></c:otherwise>
				                        </c:choose>
				                    </td>
				                    <td><fmt:formatDate value="${appoint.create_date }" pattern="yyyy-MM-dd hh:mm:ss"/></td>
				                    <td><fmt:formatDate value="${appoint.study_date }" pattern="yyyy-MM-dd hh:mm:ss"/></td>
				                    <td>姓名:${appoint.user_name }&nbsp;&nbsp;&nbsp;电话：${appoint.user_mobile }</td>
				                    <td>姓名:${appoint.ref_name }&nbsp;&nbsp;&nbsp;电话：${appoint.ref_mobile }</td>
				                    <td>
                                    	<shiro:hasPermission name="manage:appoint:update">
                                    	<c:if test="${appoint.status==0}">
			                                <a data-toggle="dropdown" onclick="statusUpdate(1,'${appoint.id}')" class="btn btn-xs btn-white dropdown-toggle"><i class="fa fa-cog"></i> 预约通过</span></a>
			                                <a data-toggle="dropdown" onclick="statusUpdate(2,'${appoint.id}')" class="btn btn-xs btn-white dropdown-toggle"><i class="fa fa-cog"></i> 预约取消</span></a>
			                            </c:if>
			                            <c:if test="${appoint.status==1}">
			                                <a data-toggle="dropdown" onclick="statusUpdate(2,'${appoint.id}')" class="btn btn-xs btn-white dropdown-toggle"><i class="fa fa-cog"></i> 预约取消</span></a>
			                            </c:if>
			                            <c:if test="${appoint.status==3}">
			                            	<a data-toggle="dropdown" onclick="oneEnd('${appoint.id}')" class="btn btn-xs btn-white dropdown-toggle"><i class="fa fa-cog"></i> 课程结业</span></a>
			                               	<a data-toggle="dropdown" onclick="onclassUpdate(2,'${appoint.id}')" class="btn btn-xs btn-white dropdown-toggle"><i class="fa fa-cog"></i> 课程取消</span></a>
			                           </c:if>
			                            </shiro:hasPermission>
			                            <shiro:hasPermission name="manage:tcomment:list">
				                            <c:if test="${(appoint.status==3||appoint.status==4)&&appoint.tcomment==1}">
				                               	<a class="btn btn-xs btn-white"  onclick="layer_show('查看评论','${ctx}/manage/tcomment/update/${appoint.id}','','','')"><i class="fa fa-eye"></i>查看评论</a>
				                           </c:if>
				                            <c:if test="${(appoint.status==3||appoint.status==4)&&appoint.tcomment==0}">
				                               	<a class="btn btn-xs btn-white"  onclick="layer_show('老师评论','${ctx}/manage/tcomment/update/${appoint.id}','','','')"><i class="fa fa-pencil"></i>老师评论</a>
				                          
				                           </c:if>
			                           </shiro:hasPermission>
			                         </td>
                                </tr>
                                </c:forEach>
                                </tbody>
                                <tfoot>
                                <tr>
                                    <td colspan="10" id="pagination"></td>
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
        $(document).ready(function(){$(".footable").footable();$("#pagination").html('${page.pageContent }');$(".i-checks").iCheck({checkboxClass:"icheckbox_square-green",radioClass:"iradio_square-green"});pageinit();});
    	$("[name='depart_id']").val('${vo.depart_id}');
    	$("[name='status']").val('${vo.status}');
        $(".chosen-select").trigger("chosen:updated");
         $('.chosen-select').chosen();
        });
        $(".footable-row-detail-cell").attr("colspan","8");
        //取消和审核通过
    	function statusUpdate(status,id){
        	layer.confirm('确定要操作吗？', function(index){
  			  layer.close(index);
  			  $.post("${ctx}/manage/appoint/update",{"id":id,"status":status},function(data){
  				if(data.code=="0"){
  					opt_success("操作成功");
  				}else{
  					opt_error("系统繁忙，请稍后再试");
  				}
  			  },"json")
  			});  
          }
          
          //已经在上课的取消（同上，只是确认信息不一样）
    	function onclassUpdate(status,id){
        	layer.confirm('该会员已经在上课了，确认取消？', function(index){
  			  layer.close(index);
  			  $.post("${ctx}/manage/appoint/update",{"id":id,"status":status},function(data){
  				if(data.code=="0"){
  					opt_success("操作成功");
  				}else{
  					opt_error("系统繁忙，请稍后再试");
  				}
  			  },"json")
  			});  
          }
          //批量结业
        function allEnd(){
	         if($("[name='input[]']").filter(':checked').length>0){
	              layer.confirm('确定要操作吗？', function(index){
	                  layer.close(index);
					  $("[name='input[]']").filter(':checked').each(function(){
						 dels+=","+$(this).attr("value");
					  })
					   $.post("${ctx}/manage/appoint/status/"+4+"/"+dels.substring(1)+"/${vo.type}",function(data){
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
       //单个结业
         function oneEnd(id){
	              layer.confirm('确定要操作吗？', function(index){
	                  layer.close(index);
					   $.post("${ctx}/manage/appoint/status/"+4+"/"+id+"/${vo.type}",function(data){
						if(data.code=="0"){
							opt_success("操作成功");
						}else{
							opt_error("系统繁忙，请稍后再试");
						}
					  },"json")
	           });
        }
    </script>
</body>

</html>