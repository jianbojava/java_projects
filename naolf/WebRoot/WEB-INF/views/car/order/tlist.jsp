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
    <link href="${ctx}/statics/manage/css/plugins/chosen/chosen.css" rel="stylesheet">

</head>

<body class="gray-bg">
    <div class="wrapper wrapper-content animated fadeInRight">

            <div class="row">
                <div class="col-sm-12">
                    <div class="ibox float-e-margins">
                        <div class="ibox-title">
                            <h5>结算中心 > 时租订单</h5>

                            <div class="ibox-tools">
                                <a class="collapse-link">
                                    <i class="fa fa-chevron-up"></i>
                                </a>
                            </div>
                        </div>
                        <div class="ibox-content">
                        	<div class="ibox-tools" style="float:left;">
                        		<shiro:hasPermission name="manage:order:checkAmount">
                        		<a class="btn btn-success btn-outline" onclick="checkAmountM()"><i class="fa fa-money"></i> 批量对账</a>
                                </shiro:hasPermission>
                                <shiro:hasPermission name="manage:order:texport">
                                <a class="btn btn-success btn-outline" onclick="javascript:location.href='${ctx}/manage/order/export?type=${vo.type }'"><i class="fa fa-file-excel-o"></i> 导出EXCEL</a>
                           		</shiro:hasPermission>
                            </div>
							<form action="${ctx }/manage/order/tlist" method="post" id="searchForm"  class="pull-right" style="width: 645px">
		                        <div class="input-group">
		                            <select data-placeholder="选择企业..." style="width:200px;" class="chosen-select" tabindex="2" name="firm_id">
		                            	<option value="">选择企业...</option>
		                            	<c:forEach items="${firms }" var="f">
                                    	<option value="${f.id }">${f.name }</option>
                                    	</c:forEach>
		                            </select>
		                            <select data-placeholder="选择订单状态..." style="width:200px;" class="chosen-select" tabindex="2" name="status">
		                            	<option value="">选择订单状态...</option>
		                            	<option value="0">充电中</option>
		                            	<option value="1">已完成</option>
		                            	<option value="2">已对账</option>
		                            </select>
		                            <input type="text" class="form-control input-sm" style="width: 200px;float: right;" name="keywords" value="${vo.keywords}" placeholder="搜索订单号、手机号等">
		                            <input type="hidden" name="pageNo" value="${vo.pageNo }"/>
				      				<input type="hidden" name="pageSize" value="${vo.pageSize }">
				      				<input type="hidden" name="is_read" value="${vo.is_read }">
		                            <div class="input-group-btn">
		                                <button type="button" class="btn btn-sm btn-primary">搜索</button>
		                            </div>
		                        </div>
		                    </form>
                            <table class="footable table table-stripped toggle-arrow-tiny" data-sort="false" data-page-size="${vo.pageSize }">
                                <thead>
                                <tr>
                                	<th width="30"><input type="checkbox" class="i-checks" name="input"></th>
                                    <th>订单编号</th>
                                    <th>总额</th>
                                    <th>租车人</th>
                                    <th>联系电话</th>
                                    <th>所属企业</th>
                                    <th>订单状态</th>
                                    <th>支付状态</th>
                                    <th>操作</th>
                                </tr>
                                </thead>
                                <tbody>
                                <c:forEach items="${page.result }" var="o">
                                <tr>
                                	<td><input type="checkbox" class="i-checks" <c:if test="${!(o.status==3 and o.pay_status==1)}">disabled="disabled"</c:if> name="input[]" value="${o.id }"></td>
                                	<td>${o.sn }</td>
                                    <td>${o.amount }</td>
                                    <td>
                                    	<c:choose>
                                    		<c:when test="${empty(o.mem_name) }">-</c:when>
                                    		<c:otherwise>${o.mem_name }</c:otherwise>
                                    	</c:choose>
                                    </td>
                                    <td>
                                    	<c:choose>
                                    		<c:when test="${empty(o.mem_mobile) }">-</c:when>
                                    		<c:otherwise>${o.mem_mobile }</c:otherwise>
                                    	</c:choose>
                                    </td>
                                    <td>
                                    	<c:choose>
                                    		<c:when test="${empty(o.firm_name) }">-</c:when>
                                    		<c:otherwise>
                                    			<c:choose>
		                                    		<c:when test="${fn:length(o.firm_name)>8}">
		                                    			<span onclick="javascript:layer.tips('${o.firm_name}',this,{tips: [1, '#1ab394'],time: 3000});">${fn:substring(o.firm_name,0,8)}<i class="fa fa-info-circle"></i>
		                                    		</c:when>
		                                    		<c:otherwise>
		                                    			${o.firm_name }
		                                    		</c:otherwise>
		                                    	</c:choose>
                                    		</c:otherwise>
                                    	</c:choose>
                                    </td>
                                    <td>
                                    <c:choose>
                                    	<c:when test="${o.status==2 }"><span class="label label-warning">${o.order_status }</span></c:when>
                                    	<c:when test="${o.status==3 }"><span class="label label-info">${o.order_status }</span></c:when>
                                    	<c:otherwise><span class="label label-primary">${o.order_status }</span></c:otherwise>
                                    </c:choose>
                                    </td>
                                    <td>
                                    <c:choose>
                                    	<c:when test="${o.pay_status==0 }"><span class="label label-warning">待支付</span></c:when>
                                    	<c:otherwise><span class="label label-primary">已支付</span></c:otherwise>
                                    </c:choose>
                                    </td>
                                    <td>
                                   		<shiro:hasPermission name="manage:order:tview">
                                    	<a class="btn btn-xs btn-white" onclick="layer_show('','${ctx}/manage/order/view/${o.id }','','',true)"><i class="fa fa-eye"></i> 查看</a>
                                    	</shiro:hasPermission>
                                    	<shiro:hasPermission name="manage:order:tupdate">
                                    	<a class="btn btn-xs btn-white" onclick="layer_show('','${ctx}/manage/order/update/${o.id }','','',true)"><i class="fa fa-pencil"></i> 修改</a>
                                   		</shiro:hasPermission>
                                   		<shiro:hasPermission name="manage:order:checkAmount">
                                   		<a class="btn btn-xs btn-white" <c:choose><c:when test="${!(o.status==3 and o.pay_status==1)}">disabled="disabled"</c:when><c:otherwise>onclick="checkAmount('${o.id}')"</c:otherwise></c:choose>><i class="fa fa-money"></i> 对账</a>
                                    	</shiro:hasPermission>
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
    <script src="${ctx}/statics/manage/js/common.js?v=1.0.0"></script>
    <script>
        $(document).ready(function(){
        	$(".footable").footable();$("#pagination").html('${page.pageContent }');$(".i-checks").iCheck({checkboxClass:"icheckbox_square-green",radioClass:"iradio_square-green"});$('.chosen-select').chosen();pageinit();
        	//初始化select控件
        	$("[name='firm_id']").val('${vo.firm_id}');
        	$("[name='dispatch_id']").val('${vo.dispatch_id}');
        	$("[name='status']").val('${vo.status}');
        	$(".chosen-select").trigger("chosen:updated");
        });
        function checkAmount(id){
        	layer.confirm('确认完成对账吗？', function(index){
			  layer.close(index);
			  $.post("${ctx}/manage/order/checkAmount",{"id":id},function(data){
				if(data.code=="0"){
					opt_success("操作成功");
				}else{
					opt_error("系统繁忙，请稍后再试");
				}
			  },"json")
			});  
        }
        function checkAmountM(){
        	if($("[name='input[]']").filter(':checked').length>0){
        		$("[name='input[]']").filter(':checked').each(function(){
					 dels+=","+$(this).attr("value");
				  })
				layer.confirm('确认完成对账吗？', function(index){
				  layer.close(index);
				  $.post("${ctx}/manage/order/checkAmount",{"id":dels},function(data){
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