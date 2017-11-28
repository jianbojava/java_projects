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
                            <h5>积分提现</h5>
                            <div class="ibox-tools">
                                <a class="collapse-link">
                                    <i class="fa fa-chevron-up"></i>
                                </a>
                            </div>
                        </div>
                        <div class="ibox-content">
                        	
                        	<!--  搜索，begin -->
							 <form action="${ctx}/manage/usercash/list" method="post" id="searchForm" class="pull-right" target='_self' style="width: 485px">
		                        <div class="input-group">
		                        	<select data-placeholder="选择状态..." style="width:200px;" class="chosen-select" tabindex="2" name="status">
		                            	<option value="">选择状态...</option>
		                            	<option value="0">待打款</option>
		                            	<option value="1">已打款</option>
		                            </select>
		                            <input type="text" class="form-control input-sm" name="keywords" value="${vo.keywords}" style="width:180px;float: right;" placeholder="搜索流水，姓名，ID">
		                            <input type="hidden" name="pageNo" value="${vo.pageNo }"/>
				      				<input type="hidden" name="pageSize" value="${vo.pageSize }">
		                            <div class="input-group-btn">
		                                <button type="button" class="btn btn-sm btn-primary">搜索</button>
		                            </div>
		                        </div>
		                    </form>
		                    <!--  搜索，end -->
                            <table class="footable table table-stripped toggle-arrow-tiny" data-sort="false" data-page-size="${vo.pageSize }">
                                <thead>
                                <tr>
                                	
                                    <th >流水</th>
                                    <th >姓名</th>
                                    <th>ID</th>
                                    <th>提现积分</th>
                                    <th>提现方式</th>
                                    <th>提现状态</th>
                                    <th>申请时间</th>
                                    <th>打款时间</th>
                                    <th width="230">操作</th>
                                </tr>
                                </thead>
                                <tbody>
                                <c:forEach items="${page.result }" var="u">
                                <tr>
                                	<td>${u.id }</td>
                                	<td>${u.username }</td>
                                	<td>${u.userno }</td>
				                    <td>${u.kd_score }</td>
				                    <td>${u.type_desc }</td>
				                    <td>${u.status_desc }</td>
				                    <td><fmt:formatDate value="${u.apply_date }" pattern="yyyy-MM-dd hh:mm:ss"/></td>
				                    <td><fmt:formatDate value="${u.pay_date }" pattern="yyyy-MM-dd hh:mm:ss"/></td>
				                    <td>
				                    	<shiro:hasPermission name="manage:usercash:list">
                                    	<a class="btn btn-xs btn-white" onclick="layer_show('账户信息','${ctx}/manage/user/account/${u.user_id }','','','')"><i class="fa fa-pencil"></i>查看账户</a>
                                    	</shiro:hasPermission>
                                    	<c:if test="${u.status==0}">
					                    	<shiro:hasPermission name="manage:usercash:pay">
	                                    	<a class="btn btn-xs btn-white" onclick="pay('${u.id }')"><i class="fa fa-pencil"></i>打款</a>
	                                    	</shiro:hasPermission>
                                    	</c:if>
				                    </td>
                                </tr>
                                </c:forEach>
                                </tbody>
                                <tfoot>
                                <tr>
                                    <td colspan="8" id="pagination"></td>
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
      $("[name='status']").val('${vo.status}');
      $(".chosen-select").trigger("chosen:updated");
 	  $('.chosen-select').chosen();
 	  function pay(id){
 		 layer.confirm('确定要操作吗？', function(index){
			  layer.close(index);
			  $.post("${ctx}/manage/usercash/pay/"+id,function(data){
				if(data.code=="0"){
					opt_success("操作成功");
				}else{
					opt_error("操作失败，"+data.msg);
				}
			  },"json")
			});  
 	  }
      </script>
</body>

</html>