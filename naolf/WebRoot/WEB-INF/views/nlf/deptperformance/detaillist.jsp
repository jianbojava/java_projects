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


<body class="gray-bg">
    <div class="wrapper wrapper-content animated fadeInRight">

            <div class="row">
                <div class="col-sm-12">
                    <div class="ibox float-e-margins">
                        <div class="ibox-title">
                            <h5>部门绩效详情</h5>
                            <div class="ibox-tools">
                                <a class="collapse-link">
                                    <i class="fa fa-chevron-up"></i>
                                </a>
                            </div>
                        </div>
                        <div class="ibox-content">
                        	
                        	<!--  搜索，begin -->
							 <form action="${ctx}/manage/deptperformance/detaillist" method="post" id="searchForm" class="pull-right" target='_self' style="width: 485px">
		                        <div class="input-group">
		                            <%--<input type="text" class="form-control input-sm" name="keywords" value="${vo.keywords}" style="width:180px;float: right;" placeholder="搜索员工编码">
		                            --%>
		                            <div style="float:right">
		                            	<table>
		                            		<tr>
		                            			<td>年月：</td>
		                            			<td>
		                            				<input class="form-control form_datetime" name="timeStamp" type="text"  style="width:80px;" value="${vo.timeStamp }">
		                            			</td>
		                            			
		                            		</tr>
		                            	</table>
			                            
			                            
			                        </div>
			                        
		                            <input type="hidden" name="depart_id" value="${vo.depart_id }"/>
		                            <input type="hidden" name="pageNo" value="${vo.pageNo }"/>
				      				<input type="hidden" name="pageSize" value="${vo.pageSize }">
				      				<input type="hidden" name="datetemplat" value="${vo.timeStamp }">
		                            <div class="input-group-btn">
		                                <button type="button" class="btn btn-sm btn-primary">搜索</button>
		                            </div>
		                        </div>
		                    </form>
		                    <!--  搜索，end -->
                            <table class="footable table table-stripped toggle-arrow-tiny" data-sort="false" data-page-size="${vo.pageSize }">
                                <thead>
                                <tr>
                                	
                                    <th data-toggle="true">部门</th>
                                    <th >创建日期</th>
                                    <th>订单</th>
                                    <th>绩效（RMB）</th>
                                </tr>
                                </thead>
                                <tbody>
                                <c:forEach items="${page.result }" var="deptscore">
                                <tr>
                                	<td>${deptscore.depart_name }</td>
                                	<td><fmt:formatDate value="${deptscore.create_date}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
                                	<td>                                       
                                	   <a <shiro:hasPermission name="manage:norder:list"> onclick="layer_show('订单详情','${ctx}/manage/norder/queryBySn/${deptscore.order_number}','','',true)" </shiro:hasPermission> >
                                	    ${deptscore.order_number }
                                	  </a></td>
                                	<td><fmt:formatNumber type="number" value="${deptscore.score }" pattern="0.00" maxFractionDigits="2"/>  </td>
                                	
				                    
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
<script type="text/javascript" src="${ctx}/statics/manage/plugins/bootstrap-datetimepicker/js/bootstrap-datetimepicker.js"></script>
    <link rel="stylesheet" href="${ctx}/statics/manage/plugins/bootstrap-datetimepicker/css/bootstrap-datetimepicker.css" type="text/css"></link></head>
    
    <script>
      $(document).ready(function(){
    	  $(".footable").footable();$("#pagination").html('${page.pageContent }');
    	  $(".i-checks").iCheck({checkboxClass:"icheckbox_square-green",radioClass:"iradio_square-green"});
    	  pageinit();
    	 
    	  
    	  $('.form_datetime').datetimepicker({
    	        format: 'yyyymm',
    	        autoclose: true,
    	        todayBtn: true,
    	        startView: 'year',
    	        minView:'year',
    	        maxView:'decade',
    	        language:  'zh-CN',
    	    });
    	  
    	  
      });
      
     
    </script>
</body>

</html>