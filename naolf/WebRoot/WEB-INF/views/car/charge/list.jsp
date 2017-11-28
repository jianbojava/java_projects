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
                            <h5>结算中心 > 充电订单</h5>

                            <div class="ibox-tools">
                                <a class="collapse-link">
                                    <i class="fa fa-chevron-up"></i>
                                </a>
                            </div>
                        </div>
                        <div class="ibox-content">
                        	<div class="ibox-tools" style="float:left;">
                        		<shiro:hasPermission name="manage:charge:checkAmount">
                        		<a class="btn btn-success btn-outline" onclick="checkAmountM()"><i class="fa fa-money"></i> 批量对账</a>
                        		</shiro:hasPermission>
                        		<shiro:hasPermission name="manage:charge:export">
                                <a class="btn btn-success btn-outline" onclick="javascript:location.href='${ctx}/manage/charge/export'"><i class="fa fa-file-excel-o"></i> 导出EXCEL</a>
                           		</shiro:hasPermission>
                            </div>
							<form action="${ctx }/manage/charge/list" method="post" id="searchForm"  class="pull-right" style="width: 645px">
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
		                            	<option value="-1">已取消</option>
		                            </select>
		                            <input type="text" class="form-control input-sm" style="width: 200px;float: right;" name="keywords" value="${vo.keywords}" placeholder="搜索订单号，手机号等">
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
                                    <th>开始时间</th>
                                    <th>结束时间</th>
                                    <th>充电量（度）</th>
                                    <th>电费</th>
                                    <th>服务费</th>
                                    <th>总额</th>
                                    <th>订单状态</th>
                                    <th>支付状态</th>
                                    <th>操作</th>
                                </tr>
                                </thead>
                                <tbody>
                                <c:forEach items="${page.result }" var="o">
                                <tr>
                                	<td><input type="checkbox" class="i-checks" name="input[]" <c:if test="${!(o.status==1 and o.pay_status==1)}">disabled="disabled"</c:if> value="${o.sn }"></td>
                                	<td>${o.sn }</td>
                                    <td>
                                    	<c:choose>
                                    		<c:when test="${empty(o.startTime) }">-</c:when>
                                    		<c:otherwise><fmt:formatDate value="${o.startTime}" pattern="yyyy-MM-dd HH:mm:ss"/></c:otherwise>
                                    	</c:choose>
                                    </td>
                                    <td>
                                    	<c:choose>
                                    		<c:when test="${empty(o.endTime) }">-</c:when>
                                    		<c:otherwise><fmt:formatDate value="${o.endTime}" pattern="yyyy-MM-dd HH:mm:ss"/></c:otherwise>
                                    	</c:choose>
                                    </td>
                                    <td>
                                    	<c:choose>
                                    		<c:when test="${empty(o.totalPower) }">-</c:when>
                                    		<c:otherwise>${o.totalPower }</c:otherwise>
                                    	</c:choose>
                                    </td>
                                    <td>
                                    	<c:choose>
                                    		<c:when test="${empty(o.totalElecMoney) }">-</c:when>
                                    		<c:otherwise>${o.totalElecMoney }</c:otherwise>
                                    	</c:choose>
                                    </td>
                                    <td>
                                    	<c:choose>
                                    		<c:when test="${empty(o.totalSeviceMoney) }">-</c:when>
                                    		<c:otherwise>${o.totalSeviceMoney }</c:otherwise>
                                    	</c:choose>
                                    </td>
                                    <td>
                                    	<c:choose>
                                    		<c:when test="${empty(o.totalMoney) }">-</c:when>
                                    		<c:otherwise>${o.totalMoney }</c:otherwise>
                                    	</c:choose>
                                    </td>
                                    <td>
                                    <c:choose>
                                    	<c:when test="${o.status!=-1 }"><span class="label label-primary">${o.order_status }</span></c:when>
                                    	<c:otherwise><span class="label label-default">${o.order_status }</span></c:otherwise>
                                    </c:choose>
                                    </td>
                                    <td>
                                    <c:choose>
                                    	<c:when test="${o.pay_status==0 }"><span class="label label-warning">待支付</span></c:when>
                                    	<c:otherwise><span class="label label-primary">已支付</span></c:otherwise>
                                    </c:choose>
                                    </td>
                                    <td>
                                    	<shiro:hasPermission name="manage:charge:view">
                                    	<a class="btn btn-xs btn-white" onclick="layer_show('','${ctx}/manage/charge/view/${o.sn }','','',true)"><i class="fa fa-eye"></i> 查看</a>
                                    	</shiro:hasPermission>
                                    	<shiro:hasPermission name="manage:charge:update">
                                    	<a class="btn btn-xs btn-white" onclick="layer_show('','${ctx}/manage/charge/update/${o.sn }','','',true)"><i class="fa fa-eye"></i> 修改</a>
                                    	</shiro:hasPermission>
                                    	<shiro:hasPermission name="manage:charge:checkAmount">
                                    	<a class="btn btn-xs btn-white" <c:choose><c:when test="${!(o.status==1 and o.pay_status==1)}">disabled="disabled"</c:when><c:otherwise>onclick="checkAmount('${o.sn}')"</c:otherwise></c:choose>><i class="fa fa-money"></i> 对账</a>
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
        function checkAmount(sn){
        	//prompt层
			layer.prompt({title: '填写备注，并确认', formType: 2}, function(text){
			    $.post("${ctx}/manage/charge/checkAmount",{"remark":text,"sn":sn},function(data){
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
				  //prompt层
				layer.prompt({title: '填写备注，并确认', formType: 2}, function(text){
				    $.post("${ctx}/manage/charge/checkAmount",{"remark":text,"sn":dels},function(data){
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