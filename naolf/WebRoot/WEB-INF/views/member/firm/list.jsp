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

</head>

<body class="gray-bg">
    <div class="wrapper wrapper-content animated fadeInRight">

            <div class="row">
                <div class="col-sm-12">
                    <div class="ibox float-e-margins">
                        <div class="ibox-title">
                            <h5>会员管理 > 企业管理</h5>

                            <div class="ibox-tools">
                                <a class="collapse-link">
                                    <i class="fa fa-chevron-up"></i>
                                </a>
                            </div>
                        </div>
                        <div class="ibox-content">
                        	<div class="ibox-tools" style="float:left;">
                        		<shiro:hasPermission name="manage:firm:add">
                                <a class="btn btn-success btn-outline" onclick="layer_show('新增企业','${ctx}/manage/firm/add','','','')"><i class="fa fa-plus"></i> 新增企业</a>
                                </shiro:hasPermission>
                                <shiro:hasPermission name="manage:firm:del">
                                <div class="btn-group">
	                                <a data-toggle="dropdown" class="btn btn-success btn-outline dropdown-toggle"><i class="fa fa-cog"></i> 批量操作 <span class="caret"></span></a>
	                                <ul class="dropdown-menu">
	                                    <li><a onclick="delM(0)">正常</a></li>
	                                    <li><a onclick="delM(1)">禁用</a></li>
	                                </ul>
	                            </div>
	                            </shiro:hasPermission>
                            </div>
							<form action="${ctx }/manage/firm/list" method="post" id="searchForm" class="pull-right mail-search" target='_self'>
		                        <div class="input-group">
		                            <input type="text" class="form-control input-sm" name="keywords" value="${vo.keywords}" placeholder="搜索企业名称">
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
                                    <th data-toggle="true">企业名称</th>
                                    <th>联系人</th>
                                    <th>电话</th>
                                    <th>保证金</th>
                                    <th>余额</th>
                                    <th>支付类型</th>
                                    <th>销售员</th>
                                    <th>状态</th>
                                    <th data-hide="all">合同时间</th>
                                    <th data-hide="all">企业备注</th>
                                    <th>操作</th>
                                </tr>
                                </thead>
                                <tbody>
                                <c:forEach items="${page.result }" var="firm">
                                <tr>
                                	<th width="30"><input type="checkbox" class="i-checks" name="input[]" value="${firm.id}"></th>
                                	<td>${firm.name }</td>
                                	<td>
                                		<c:choose>
                                			<c:when test="${empty(firm.representer) }">-</c:when>
                                			<c:otherwise>${firm.representer }</c:otherwise>
                                		</c:choose>
                                	</td>
                                	<td>
                                		<c:choose>
                                			<c:when test="${empty(firm.tel)}">-</c:when>
                                			<c:otherwise>${firm.tel }</c:otherwise>
                                		</c:choose>
                                	</td>
                                	<td>${firm.deposit }</td>
                                	<td>${firm.left_amount }</td>
                                	<td><c:choose><c:when test="${firm.pay_type==0}">后付费</c:when><c:otherwise>预充值</c:otherwise></c:choose></td>
                                    <td>
                                    	<c:choose>
                                    		<c:when test="${empty(firm.sale_id) }">-</c:when>
                                    		<c:otherwise>${firm.sale_name }</c:otherwise>
                                    	</c:choose>
                                    </td>
				                    <td><c:choose><c:when test="${firm.enabled==0}"><span class="label label-primary">正常</span></c:when><c:otherwise><span class="label label-danger">禁用</span></c:otherwise></c:choose></td>
                                    <td>
                                    	<c:choose>
                                    		<c:when test="${empty(firm.sign_date) }">-</c:when>
                                    		<c:otherwise><fmt:formatDate value="${firm.sign_date }" pattern="yyyy-MM-dd"/>-<fmt:formatDate value="${firm.end_date }" pattern="yyyy-MM-dd"/></c:otherwise>
                                    	</c:choose>
                                    </td>
                                    <td>
                                    	<c:choose>
                                    		<c:when test="${empty(firm.remark) }">-</c:when>
                                    		<c:otherwise>${firm.remark }</c:otherwise>
                                    	</c:choose>
                                    </td>
                                    <td>
                                    	<shiro:hasPermission name="manage:firm:update">
                                    	<a class="btn btn-xs btn-white" onclick="layer_show('修改企业','${ctx}/manage/firm/update/${firm.id}','','','')"><i class="fa fa-pencil"></i> 修改</a>
                                    	</shiro:hasPermission>
                                    	<shiro:hasPermission name="manage:firm:recharge">
                                    	<a class="btn btn-xs btn-white" onclick="layer_show('账户充值','${ctx}/manage/firm/recharge/${firm.id}','','','')"><i class="fa fa-money"></i> 充值</a>
                                    	</shiro:hasPermission>
                                    	<shiro:hasPermission name="manage:firm:del">
                                    	<div class="btn-group">
			                                <a data-toggle="dropdown" class="btn btn-xs btn-white dropdown-toggle"><i class="fa fa-cog"></i> 操作 <span class="caret"></span></a>
			                                <ul class="dropdown-menu">
			                                    <li><a onclick="del(0,'${firm.id}')" <c:if test="${firm.enabled==0}">class="font-bold"</c:if>>正常</a></li>
			                                    <li><a onclick="del(1,'${firm.id}')" <c:if test="${firm.enabled==1}">class="font-bold"</c:if>>禁用</a></li>
			                                </ul>
			                            </div>
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
    <script src="${ctx}/statics/manage/js/common.js?v=1.0.0"></script>
    <script>
        $(document).ready(function(){$(".footable").footable();$("#pagination").html('${page.pageContent }');$(".i-checks").iCheck({checkboxClass:"icheckbox_square-green",radioClass:"iradio_square-green"});pageinit();});
    	function del(flg,id){
        	layer.confirm('确定要操作吗？', function(index){
			  layer.close(index);
			  $.post("${ctx}/manage/firm/del/"+flg+"/"+id,function(data){
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
					   $.post("${ctx}/manage/firm/del/"+flg+"/"+dels.substring(1),function(data){
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