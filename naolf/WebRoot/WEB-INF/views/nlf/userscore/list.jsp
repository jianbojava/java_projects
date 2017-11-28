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
                            <h5>员工积分</h5>
                            <div class="ibox-tools">
                                <a class="collapse-link">
                                    <i class="fa fa-chevron-up"></i>
                                </a>
                            </div>
                        </div>
                        <div class="ibox-content">
                        	<!-- 所有会员流水账 begin -->
                        	<div class="ibox-tools" style="float:left;">
                                <a class="btn btn-success btn-outline" onclick="layer_show('所有会员流水账','${ctx}/manage/empdisjournal/list','','',true)"><i class="fa fa-plus"></i>所有会员流水账</a>
                                
                            </div>
                            	<!-- 所有会员流水账 end -->
                        	<!--  搜索，begin -->
							 <form action="${ctx}/manage/userscoreinfo/list" method="post" id="searchForm" class="pull-right" target='_self' style="width: 485px">
		                        <div class="input-group">
		                        	<select data-placeholder="选择部门..." style="width:200px;" class="chosen-select" tabindex="2" name="depart_id">
		                            	<option value="">选择部门...</option>
		                            	<c:forEach items="${dept}" var="d">
						                   <option value="${d.id }">${d.name}</option>
						                 </c:forEach>
		                            </select>
		                            
		                            <input type="text" class="form-control input-sm" name="keywords" value="${vo.keywords}" style="width:180px;float: right;" placeholder="搜索员工，编码">
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
                                	
                                    <th data-toggle="true">员工</th>
                                    <th >员工编码</th>
                                    <th >部门</th>
                                    <th>未结业积分</th>
                                    <th>结业积分</th>
                                    <th>可兑换积分</th>
                                    <th>上次更新时间</th>
                                </tr>
                                </thead>
                                <tbody>
                                <c:forEach items="${page.result }" var="userpoint">
                                <tr>
                                	<td>${userpoint.user_name }</td>
                                	<td>${userpoint.user_number }</td>
                                	<td>${userpoint.depart_name }</td>
                                	<td>${userpoint.BKZ_SCORE }</td>
				                    <td>${userpoint.KZ_SCORE }</td>
				                    <td>${userpoint.KD_SCORE }</td>
				                    <td><fmt:formatDate value="${userpoint.last_update_date }" pattern="yyyy-MM-dd hh:mm:ss"/></td>
				                    
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
    <script src="${ctx}/statics/manage/plugins/layer/layer.js"></script>
    <script src="${ctx}/statics/manage/js/plugins/chosen/chosen.jquery_.js"></script>
    <script src="${ctx}/statics/manage/js/common.js?v=1.0.0"></script>
    <script>
      $(document).ready(function(){$(".footable").footable();$("#pagination").html('${page.pageContent }');$(".i-checks").iCheck({checkboxClass:"icheckbox_square-green",radioClass:"iradio_square-green"});pageinit();});
      $(".chosen-select").trigger("chosen:updated");
 	  $('.chosen-select').chosen();
    </script>
</body>

</html>