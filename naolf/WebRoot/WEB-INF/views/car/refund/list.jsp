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
    <link href="${ctx}/statics/manage/css/plugins/datapicker/datepicker3.css" rel="stylesheet">

</head>

<body class="gray-bg">
    <div class="wrapper wrapper-content animated fadeInRight">

            <div class="row">
                <div class="col-sm-12">
                    <div class="ibox float-e-margins">
                        <div class="ibox-title">
                            <h5>结算中心 > 申请记录（保证金）</h5>

                            <div class="ibox-tools">
                                <a class="collapse-link">
                                    <i class="fa fa-chevron-up"></i>
                                </a>
                            </div>
                        </div>
                        <div class="ibox-content">
                        	<div class="ibox-tools" style="float:left;">
                                <a class="btn btn-success btn-outline" onclick="solveM(1)"><i class="fa fa-check"></i> 通过</a>
                                <a class="btn btn-success btn-outline" onclick="solveM(2)"><i class="fa fa-close"></i> 拒绝</a>
                            </div>
							<form action="${ctx }/manage/refund/list" method="post" id="searchForm" class="pull-right" style="width: 246px">
		                        <div class="input-group">
		                            <input type="text" class="form-control input-sm" name="keywords" style="width: 200px" value="${vo.keywords}" placeholder="搜索申请人姓名、电话等">
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
                                    <th>申请人</th>
                                    <th>联系电话</th>
                                    <th>申请金额</th>
                                    <th>申请时间</th>
                                    <th>处理时间</th>
                                    <th>处理状态</th>
                                    <th data-hide="all">处理备注</th>
                                    <th>操作</th>
                                </tr>
                                </thead>
                                <tbody>
                                <c:forEach items="${page.result }" var="r">
                                <tr>
                                	<td><input type="checkbox" class="i-checks disabled" <c:if test="${r.is_solve!=0 }">disabled="disabled"</c:if>  name="input[]" value="${r.sn }"></td>
                                	<td>${r.sn }</td>
                                    <td>
                                    	<c:choose>
                                    		<c:when test="${empty(r.mem_name) }">-</c:when>
                                    		<c:otherwise>${r.mem_name }</c:otherwise>
                                    	</c:choose>
                                    </td>
                                    <td>${r.mem_mobile }</td>
                                    <td>${r.amount }</td>
                                    <td><fmt:formatDate value="${r.create_date}" pattern="yyyy-MM-dd HH:mm:ss" /></td>
                                    <td>
                                    	<c:choose>
                                    		<c:when test="${empty(r.modify_date) }">-</c:when>
                                    		<c:otherwise><fmt:formatDate value="${r.modify_date}" pattern="yyyy-MM-dd HH:mm:ss" /></c:otherwise>
                                    	</c:choose>
                                    </td>
                                    <td><c:choose><c:when test="${r.is_solve==0}">待处理</c:when><c:when test="${r.is_solve==1}">已通过</c:when><c:otherwise>已拒绝</c:otherwise></c:choose></td>
                                    <td>${r.remark}</td>
                                    <td>
                                    	<a class="btn btn-xs btn-white" <c:if test="${r.is_solve!=0 }">disabled="disabled"</c:if> <c:if test="${r.is_solve==0 }">onclick="solve('${r.sn }',1)"</c:if>><i class="fa fa-check"></i> 通过</a>
                                    	<a class="btn btn-xs btn-white" <c:if test="${r.is_solve!=0 }">disabled="disabled"</c:if> <c:if test="${r.is_solve==0 }">onclick="solve('${r.sn }',2)"</c:if>><i class="fa fa-close"></i> 拒绝</a>
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
    <script src="${ctx}/statics/manage/js/plugins/datapicker/bootstrap-datepicker.js"></script>
    <script src="${ctx}/statics/manage/js/common.js?v=1.0.0"></script>
    <script>
        $(document).ready(function(){$(".footable").footable();$("#pagination").html('${page.pageContent }');$(".i-checks").iCheck({checkboxClass:"icheckbox_square-green",radioClass:"iradio_square-green"});pageinit();});
        //违章处理
        function solve(sn,flg){
        	//prompt层
			layer.prompt({title: '填写备注，并确认', formType: 2}, function(text){
			    $.post("${ctx}/manage/refund/update",{"is_solve":flg,"remark":text,"sn":sn},function(data){
					if(data.code=="0"){
						opt_success("操作成功");
					}else{
						opt_error("系统繁忙，请稍后再试");
					}
				  },"json")
			});
        }
        
        function solveM(flg){
        	if($("[name='input[]']").filter(':checked').length>0){
        		$("[name='input[]']").filter(':checked').each(function(){
					 dels+=","+$(this).attr("value");
				  })
				  //prompt层
				layer.prompt({title: '填写备注，并确认', formType: 2}, function(text){
				    $.post("${ctx}/manage/refund/update",{"is_solve":flg,"remark":text,"sn":dels},function(data){
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