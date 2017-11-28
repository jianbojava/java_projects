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
    <link href="${ctx}/statics/manage/css/plugins/datapicker/datepicker3.css" rel="stylesheet">

</head>

<body class="gray-bg">
    <div class="wrapper wrapper-content animated fadeInRight">

            <div class="row">
                <div class="col-sm-12">
                    <div class="ibox float-e-margins">
                        <div class="ibox-title">
                            <h5>课程管理 > 课程发布</h5>
                            <div class="ibox-tools">
                                <a class="collapse-link">
                                    <i class="fa fa-chevron-up"></i>
                                </a>
                            </div>
                        </div>
                        <div class="ibox-content">
                        	<div class="ibox-tools" style="float:left;">
                        	   <shiro:hasPermission name="manage:course:add">
                                <a class="btn btn-success btn-outline" onclick="layer_show('新增发布','${ctx}/manage/course/add/${vo.type }','','','')"><i class="fa fa-plus"></i> 新增发布</a>
                                </shiro:hasPermission>
                        	    <shiro:hasPermission name="manage:course:update">
	                                <div class="btn-group" >
		                                <a data-toggle="dropdown" class="btn btn-success btn-outline" onclick="allpublish()" ><i class="fa fa-cog" ></i> 批量发布</a>
		                            </div>
	                            </shiro:hasPermission>
                            </div>
							<form action="${ctx }/manage/course/list" method="post" id="searchForm" class="pull-right" target='_self' style="width: 880px" onsubmit="return validParms();">
		                        <div class="input-group">
		                         <select data-placeholder="选择状态..." style="width:100px;" class="chosen-select" tabindex="2" name="enabled">
			                            	<option value="">选择状态...</option>
			                            	<option value="0">正常</option>
			                            	<option value="1">取消</option>
			                      </select>
		                         <select data-placeholder="选择发布状态..." style="width:150px;" class="chosen-select" tabindex="2" name="status">
		                            	<option value="">选择发布状态...</option>
		                            	<option value="0">未发布</option>
		                            	<option value="1">已发布</option>
		                            	<option value="2">停止预约</option>
		                            	<option value="3">已结业</option>
		                            </select>
		                           <select data-placeholder="选择服务部门..." style="width:150px;" class="chosen-select" tabindex="2" name="depart_id">
		                            	<option value="">选择服务部门...</option>
		                            	<c:forEach items="${departs }" var="d">
                                    	<option value="${d.id }">${d.name}</option>
                                    	</c:forEach>
		                            </select>
		                            <input type="text" class="form-control input-sm" name="startDate2" style="width: 150px" value="<fmt:formatDate value='${vo.startDate2}' pattern='yyyy-MM-dd'/>" placeholder="开始时间">
		                        	<input type="text" class="form-control input-sm" name="endDate2" style="width: 150px" value="<fmt:formatDate value='${vo.endDate2}' pattern='yyyy-MM-dd'/>" placeholder="结束时间">
		                            <input type="text" class="form-control input-sm" name="keywords" value="${vo.keywords}"  style="float: right;width: 130px" placeholder="搜索课程名称">
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
                                    <th data-toggle="true">课程</th>
                                    <th>服务部门</th>
                                    <th>人数上限</th>
                                    <th>发布日期</th>
                                    <th>类型</th>
                                    <th>发布状态</th>
                                    <th>状态</th>
                                    <th>操作</th>
                                </tr>
                                </thead>
                                <tbody>
                                <c:forEach items="${page.result }" var="course">
                                <tr>
                                	<th><input type="checkbox" <c:if test="${course.status!=0 }">disabled="disabled" </c:if> class="i-checks" name="input[]" value="${course.id}"></th>
                                	<td>${course.l_name }</td>
                                	<td>${course.d_name }</td>
                                	<td>${course.have_appoint}/${course.num }</td>
                                    <td><fmt:formatDate value="${course.start_time }" pattern="yyyy-MM-dd HH:mm:ss"/></td>
				                    <td><c:choose><c:when test="${course.type==0}"><span>进阶训练</span></c:when><c:otherwise><span>集中训练</span></c:otherwise></c:choose></td>
				                    <td>
				                       <c:choose>
				                          <c:when test="${course.status==0}"><span class="label label-danger">未发布</span></c:when>
				                          <c:when test="${course.status==1}"><span class="label label-primary">已发布</span></c:when>
				                          <c:when test="${course.status==2}"><span class="label label-primary">停止预约</span></c:when>
				                          <c:when test="${course.status==3}"><span class="label label-primary">
				                            <c:if test="${course.type==0}">已结束</c:if>
			                                <c:if test="${course.type==1}">已结业</c:if>
				                          </span></c:when>
				                          </c:choose>
				                    </td>
				                    <td><c:choose><c:when test="${course.enabled==0}"><span class="label label-primary">正常</span></c:when><c:otherwise><span class="label label-danger">取消</span></c:otherwise></c:choose></td>
                                    <td>
                                    	<shiro:hasPermission name="manage:course:del">
                                    	<div class="btn-group">
			                                <a data-toggle="dropdown" class="btn btn-xs btn-white dropdown-toggle"><i class="fa fa-lock"></i> 状态<span class="caret"></span></a>
			                                <ul class="dropdown-menu">
			                                    <li><a onclick="del(0,'${course.id}',${course.status})" <c:if test="${course.enabled==0}">class="font-bold"</c:if>>正常</a></li>
			                                    <li><a onclick="del(1,'${course.id}',${course.status})" <c:if test="${course.enabled==1}">class="font-bold"</c:if>>取消</a></li>
			                                </ul>
			                            </div>
			                            <c:if test="${course.status<3}">
			                            <div class="btn-group">
			                                <a data-toggle="dropdown" class="btn btn-xs btn-white dropdown-toggle"><i class="fa fa-cog"></i> 操作<span class="caret"></span></a>
			                                <ul class="dropdown-menu">
			                                    <li><a onclick="statusUpdate(0,'${course.id}')" <c:if test="${course.status==0}">class="font-bold"</c:if>>未发布</a></li>
			                                    <li><a onclick="statusUpdate(1,'${course.id}')" <c:if test="${course.status==1}">class="font-bold"</c:if>>发布</a></li>
			                                    <li><a onclick="statusUpdate(2,'${course.id}')" <c:if test="${course.status==2}">class="font-bold"</c:if>>准备上课</a></li>
			                                    <li><a onclick="statusUpdate(3,'${course.id}')" <c:if test="${course.status==3}">class="font-bold"</c:if>>
			                                       <c:if test="${course.type==0}">结束</c:if>
			                                       <c:if test="${course.type==1}">结业</c:if>
			                                    </a></li>
			                                </ul>
			                            </div>
			                            </c:if>
			                            </shiro:hasPermission>
			                            <shiro:hasPermission name="manage:course:update">
                                    	<a class="btn btn-xs btn-white" onclick="layer_show('修改发布','${ctx}/manage/course/update/${course.id}/${vo.type }','','','')"><i class="fa fa-pencil"></i> 修改</a>
                                    	</shiro:hasPermission>
			                            <shiro:hasPermission name="manage:appoint:list">
				                            <c:if test="${course.status>0}">
	                                    	 <a class="btn btn-xs btn-white" onclick="layer_show('${course.l_name }>学员列表','${ctx}/manage/appoint/list?course_id=${course.id}&type=${course.type}','','','true')"><i class="fa fa-child"></i> 学员列表</a>
	                                    	</c:if>
                                    	</shiro:hasPermission>
	                                    	<c:if test="${course.status>=2 }">
	                                    	     <a class="btn btn-xs btn-white" onclick="layer_show('查看课程二维码','${ctx}/manage/course/qrcode/${course.id}','','','true')"><i class="fa fa-qrcode"></i> 二维码</a>
	                                    	</c:if>
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
    <script src="${ctx}/statics/manage/js/plugins/datapicker/bootstrap-datepicker.js"></script>
    <script src="${ctx}/statics/manage/js/common.js?v=1.0.0"></script>
    <script>
    $(document).ready(function(){$(".footable").footable();$("#pagination").html('${page.pageContent }');$(".i-checks").iCheck({checkboxClass:"icheckbox_square-green",radioClass:"iradio_square-green"});pageinit();});
    $("[name='startDate2'],[name='endDate2']").datepicker({
         	format: "yyyy-mm-dd",
         	todayHighlight:true,
        	autoclose: true,
        	todayBtn: true
         });
     $(document).ready(function(){
         $("[name='depart_id']").val('${vo.depart_id}');
    	$("[name='status']").val('${vo.status}');
    	$("[name='enabled']").val('${vo.enabled}');
        $(".chosen-select").trigger("chosen:updated");
         $('.chosen-select').chosen();
     });
    	function del(flg,id,status){//单个禁用，启用
    	   if(flg==1&&status==3){//已结业不能取消
    	      opt_error("已结业不能取消");
    	      return;
    	   }
        	layer.confirm('确定要操作吗？', function(index){
			  layer.close(index);
			  $.post("${ctx}/manage/course/del/"+flg+"/"+id,function(data){
				if(data.code=="0"){
					opt_success("操作成功");
				}else{
					opt_error("系统繁忙，请稍后再试");
				}
			  },"json")
			});  
        }
          function statusUpdate(flg,id){//单个修改发布状态
        	layer.confirm('确定要操作吗？', function(index){
  			  layer.close(index);
  			  $.post("${ctx}/manage/course/status/"+flg+"/"+id,function(data){
  				if(data.code=="0"){
  					opt_success("操作成功");
  				}else{
  					opt_error("系统繁忙，请稍后再试");
  				}
  			  },"json")
  			});  
          }
          
            //批量发布
        function allpublish(){
	         if($("[name='input[]']").filter(':checked').length>0){
	              layer.confirm('确定要操作吗？', function(index){
	                  layer.close(index);
					  $("[name='input[]']").filter(':checked').each(function(){
						 dels+=","+$(this).attr("value");
					  })
					   $.post("${ctx}/manage/course/allpublish/"+1+"/"+dels.substring(1),function(data){
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
        
         //表单时间验证
        function validParms(){
        	var star_date=$("[name='startDate2']").val();
        	var end_date=$("[name='endDate2']").val();
        	if($.trim(star_date)!=""){
        		if($.trim(end_date)!=""){
        			if(new Date(star_date)>=new Date(end_date)){
        				layer.msg("开始时间不能大于结束时间", {
							icon : 0
						});
						return false;
        			}
        			return true;
        		}
        	}
        	if($.trim(end_date)!=""){
        		if($.trim(star_date)!=""){
        			if(new Date(star_date)>=new Date(end_date)){
        				layer.msg("开始时间不能大于结束时间", {
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