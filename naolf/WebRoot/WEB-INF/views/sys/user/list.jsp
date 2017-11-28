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
                            <h5>系统管理 >
                                <c:if test="${vo.user_type==0}">员工管理</c:if> 
                                <c:if test="${vo.user_type==1}">合伙人管理</c:if> 
                                <c:if test="${vo.user_type==2}">会员管理</c:if> 
                            </h5>

                            <div class="ibox-tools">
                                <a class="collapse-link">
                                    <i class="fa fa-chevron-up"></i>
                                </a>
                            </div>
                        </div>
                        <div class="ibox-content">
                        	<div class="ibox-tools" style="float:left;">
                        		<shiro:hasPermission name="manage:user:add">
                                <a class="btn btn-success btn-outline" onclick="layer_show('新增用户','${ctx}/manage/user/add/${vo.user_type}','','',true)"><i class="fa fa-plus"></i> 新增用户</a>
                                </shiro:hasPermission>
                                <shiro:hasPermission name="manage:user:del">
                                <div class="btn-group">
                                   <!-- 
	                                <a data-toggle="dropdown" class="btn btn-success btn-outline dropdown-toggle"><i class="fa fa-cog"></i> 批量操作 <span class="caret"></span></a>
	                                <ul class="dropdown-menu">
	                                    <li><a onclick="delM(0)">正常</a></li>
	                                    <li><a onclick="delM(1)">锁定</a></li>
	                                </ul>
	                                 -->
	                                <a class="btn btn-success btn-outline" onclick="delM(1)"><i class="fa fa-lock"></i> 批量锁定</a>
	                            </div>
	                            </shiro:hasPermission>
                            </div>
							<form action="${ctx }/manage/user/list" method="post" id="searchForm" class="pull-right" target='_self' style="width: 785px">
		                        <div class="input-group">
		                         <select data-placeholder="选择状态..." style="width:150px;" class="chosen-select" tabindex="2" name="is_locked">
			                            	<option value="">选择状态...</option>
			                            	<option value="0">正常</option>
			                            	<option value="1">锁定</option>
			                      </select>
                                    <select  data-placeholder="选择部门..." style="width:200px;" class="chosen-select" tabindex="2" name="depart_id" >
                                    	<option value="">所有员工</option>
	                                    	<c:if test="${not empty depart}">
	                                    	  <option value="${depart.id }">${depart.name }</option>
		                                    	  <c:forEach items="${depart.child}" var="d">
		                                    		  <option value="${d.id }">&nbsp;&nbsp;&nbsp;${d.name }</option>
		                                    		  <c:forEach items="${d.child}" var="d">
		                                    		     <option value="${d.id }">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;${d.name }</option>
		                                    		     <c:forEach items="${d.child}" var="d">
		                                    		            <option value="${d.id }">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;${d.name }</option>
		                                    	                 <c:forEach items="${d.child}" var="d">
		                                    		                  <option value="${d.id }">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;${d.name }</option>
		                                    	                       <c:forEach items="${d.child}" var="d">
		                                    		                        <option value="${d.id }">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;${d.name }</option>
		                                    	                       </c:forEach>
		                                    	                </c:forEach>
		                                    	         </c:forEach>
		                                    	      </c:forEach>
		                                    	  </c:forEach>
	                                    	</c:if>
		                            </select>
		                            <c:if test="${vo.user_type<=1 }">
			                           <select data-placeholder="选择审核状态..." style="width:200px;" class="chosen-select" tabindex="2" name="enabled">
			                            	<option value="">选择审核状态...</option>
			                            	<option value="0">待审核</option>
			                            	<option value="1">已审核</option>
			                            	<option value="2">审核失败</option>
			                            </select>
		                            </c:if>
		                             <c:if test="${vo.user_type==2}">
			                           <select data-placeholder="选择激活状态..." style="width:200px;" class="chosen-select" tabindex="2" name="enabled">
			                            	<option value="">选择激活状态...</option>
			                            	<option value="0">未激活</option>
			                            	<option value="1">已激活</option>
			                            	<option value="2">激活失败</option>
			                            </select>
		                            </c:if>
		                            <input type="text" class="form-control input-sm" name="keywords" value="${vo.keywords}" style="width:180px;float: right;" placeholder="搜索用户名、姓名、ID、推荐人ID等">
		                            <input type="hidden" name="pageNo" value="${vo.pageNo }"/>
				      				<input type="hidden" name="pageSize" value="${vo.pageSize }">
				      				<input type="hidden" name="user_type" value="${vo.user_type}">
		                            <div class="input-group-btn">
		                                <button type="button" class="btn btn-sm btn-primary">搜索</button>
		                            </div>
		                        </div>
		                    </form>
                            <table class="footable table table-stripped toggle-arrow-tiny" data-sort="false" data-page-size="${vo.pageSize }">
                                <thead>
                                <tr>
                                	<th width="30"><input type="checkbox" class="i-checks" name="input"></th>
                                    <th data-toggle="true">姓名</th>
                                    <th>
                                      <c:if test="${vo.user_type==1 }">合伙人类型</c:if>
                                      <c:if test="${vo.user_type==0||vo.user_type==2 }">用户名</c:if>
                                    </th>
                                    <th>编号</th>
                                    <th>推荐人</th>
                                    <th>联系电话</th>
                                    <th>角色</th>
                                    <th>部门</th>
                                    <th>状态</th>
                                    <th><c:if test="${vo.user_type==0||vo.user_type==1}">审核</c:if><c:if test="${vo.user_type==2}">激活</c:if>状态</th>
                                    <th>操作</th>
                                </tr>
                                </thead>
                                <tbody>
                                <c:forEach items="${page.result }" var="user">
                                <tr>
                                	<td><input type="checkbox" class="i-checks" name="input[]" value="${user.id }"></td>
                                	<td>${user.name }</td>
                                	<td>
                                	  <c:if test="${vo.user_type==1 }">
                                	    <c:if test="${user.partner_type==1}">高级</c:if>
                                	    <c:if test="${user.partner_type==2}">事业</c:if>
                                	    <c:if test="${user.partner_type==3}">钻石</c:if>
                                	  </c:if>
                                      <c:if test="${vo.user_type==0||vo.user_type==2 }">${user.username }</c:if>
                                	</td>
                                    <td>${user.number}</td>
                                    <td>${user.refer_number}</td>
                                    <td><c:choose><c:when test="${empty(user.mobile) }">-</c:when><c:otherwise>${user.mobile }</c:otherwise></c:choose></td>
                                    <td>
	                                    <c:choose>	
	                                    	<c:when test="${empty(user.role_names) }">-</c:when>
	                                    	<c:otherwise>
	                                    		<c:forEach var="r" items="${user.role_names }">
			                    					${r}&nbsp;
			                    				</c:forEach>
	                                    	</c:otherwise>
	                                    </c:choose>
                    				</td>
                                    <td>${user.depart_name}</td>
                                    <td><c:choose><c:when test="${user.is_locked==0}"><span class="label label-primary">正常</span></c:when><c:otherwise><span class="label label-danger">锁定</span></c:otherwise></c:choose></td>
                                    <td>
                                        <c:choose>
                                            <c:when test="${user.enabled==0}"><span class="label label-danger"><c:if test="${vo.user_type==0||vo.user_type==1}">未审核</c:if><c:if test="${vo.user_type==2}">未激活</c:if></span></c:when>
                                            <c:when test="${user.enabled==1}"><span class="label label-primary"><c:if test="${vo.user_type==0||vo.user_type==1}">已审核</c:if><c:if test="${vo.user_type==2}">已激活</c:if></span></c:when>
                                            <c:otherwise><span class="label label-danger">审核失败</span></c:otherwise>
                                       </c:choose>
                                    </td>
                                    <td>
                                        <shiro:hasPermission name="manage:user:list">
                                    	<a class="btn btn-xs btn-white" onclick="layer_show('查看详情','${ctx}/manage/user/update/${user.id}/0','','',true)"><i class="fa fa-eye"></i> 查看</a>
                                    	</shiro:hasPermission>
                                    	<shiro:hasPermission name="manage:user:update">
                                    	<a class="btn btn-xs btn-white" onclick="layer_show('修改用户','${ctx}/manage/user/update/${user.id }/1','','',true)"><i class="fa fa-pencil"></i> 修改</a>
                                    	</shiro:hasPermission>
                                    	<shiro:hasPermission name="manage:user:del">
	                                    	 <c:if test="${user.is_locked==0 }">
	                                    	    <a class="btn btn-xs btn-white" onclick="del(1,'${user.id}')" ><i class="fa fa-lock"></i> 锁定</a>
	                                    	 </c:if>
                                    	<!-- 
                                    	<div class="btn-group">
			                                <a data-toggle="dropdown" class="btn btn-xs btn-white dropdown-toggle"><i class="fa fa-cog"></i> 操作 <span class="caret"></span></a>
			                                <ul class="dropdown-menu">
			                                    <li><a onclick="del(0,'${user.id}')" <c:if test="${user.is_locked==0}">class="font-bold"</c:if>>正常</a></li>
			                                    <li><a onclick="del(1,'${user.id}')" <c:if test="${user.is_locked==1}">class="font-bold"</c:if>>锁定</a></li>
			                                </ul>
			                            </div>
			                             -->
			                            </shiro:hasPermission>
			                            <c:if test="${vo.user_type==2||vo.user_type==1}">
				                            <shiro:hasPermission name="manage:student:list">
	                                    	<a class="btn btn-xs btn-white" onclick="location.href='${ctx}/manage/student/list?user_id=${user.id}'"><i class="fa fa-child"></i> 学生</a>
	                                    	</shiro:hasPermission>
                                    	</c:if>
                                    	
                                    	<shiro:hasPermission name="manage:user:del">
	                                    	<c:if test="${vo.user_type==0||vo.user_type==1 }">
	                                    	    <a class="btn btn-xs btn-white" onclick="layer_show('修改角色数据','${ctx}/manage/user/update/role/${user.id }','','',true)"><i class="fa fa-chain"></i> 角色</a>
	                                    	</c:if>
                                    	</shiro:hasPermission>
                                    	
                                    	<shiro:hasPermission name="manage:user:check">
	                                    	<c:if test="${(vo.user_type==0||vo.user_type==1)&&(user.enabled==0)}">
	                                    	<div class="btn-group">
				                                <a data-toggle="dropdown" class="btn btn-xs btn-white dropdown-toggle"><i class="fa fa-cog"></i> 审核 <span class="caret"></span></a>
				                                <ul class="dropdown-menu">
				                                    <li><a onclick="check(1,'${user.id}','${user.partner_type}')" >通过</a></li>
				                                    <li><a onclick="check(2,'${user.id}','${user.partner_type}')">拒绝</a></li>
				                                </ul>
				                            </div>
				                            </c:if>
				                            </shiro:hasPermission>
                                    </td>
                                </tr>
                                </c:forEach>
                                </tbody>
                                <tfoot>
                                <tr>
                                    <td colspan="11" id="pagination"></td>
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
   		$("[name='depart_id']").val('${vo.depart_id}');
   		$("[name='enabled']").val('${vo.enabled}');
   		$("[name='is_locked']").val('${vo.is_locked}');
   		$(".chosen-select").trigger("chosen:updated");
   		$('.chosen-select').chosen();
    	function del(flg,id){
        	layer.confirm('确定要操作吗？', function(index){
			  layer.close(index);
			  $.post("${ctx}/manage/user/del/"+flg+"/"+id,function(data){
				if(data.code=="0"){
					opt_success("操作成功");
				}else{
					opt_error("系统繁忙，请稍后再试");
				}
			  },"json")
			});  
        }
        
     function check(enabled,id,partner_type){
        var user_type=${vo.user_type};//合伙人审核通过必须是有合伙人类型
        if(enabled==1&&user_type==1&&(partner_type==null||partner_type=="")){
            opt_error("请为合伙人选择类型");
            return;
        }
        var conf_msg="确定审核通过吗？";
        if(enabled==2)  conf_msg="确定审核被拒绝，删除用户？";
        layer.confirm(conf_msg, function(index){
		   layer.close(index);
	          $.post("${ctx}/manage/user/check",{"id":id,"enabled":enabled},function(data){
	             if(data.code=="0"){
	                opt_success("操作成功");
	             }else{
	                opt_error(data.msg);
	             }
	          })
          });  
        }
        function delM(flg){
        	if($("[name='input[]']").filter(':checked').length>0){
	              layer.confirm('确定要操作吗？', function(index){
	                  layer.close(index);
					  $("[name='input[]']").filter(':checked').each(function(){
						 dels+=","+$(this).attr("value");
					  })
					   $.post("${ctx}/manage/user/del/"+flg+"/"+dels.substring(1),function(data){
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