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
    <link href="${ctx}/statics/manage/css/plugins/datapicker/datepicker3.css" rel="stylesheet">

</head>

<body class="gray-bg">
    <div class="wrapper wrapper-content animated fadeInRight">

            <div class="row">
                <div class="col-sm-12">
                    <div class="ibox float-e-margins">
                        <div class="ibox-title">
                            <h5>结算中心 > 交易流水</h5>

                            <div class="ibox-tools">
                                <a class="collapse-link">
                                    <i class="fa fa-chevron-up"></i>
                                </a>
                            </div>
                        </div>
                        <div class="ibox-content">
                        	<div class="ibox-tools" style="float:left;">
                        		<shiro:hasPermission name="manage:paymentLog:export">
                                <a class="btn btn-success btn-outline" onclick="javascript:location.href='${ctx}/manage/paymentLog/export?type=${vo.type }&firm_id=${vo.firm_id }&keywords=${vo.keywords }'"><i class="fa fa-file-excel-o"></i> 导出EXCEL</a>
                            	</shiro:hasPermission>
                            </div>
							<form action="${ctx }/manage/paymentLog/list" method="post" id="searchForm" class="pull-right" style="width: 795px">
		                        <div class="input-group">
		                        	<select data-placeholder="选择企业..." style="width:250px;" class="chosen-select" tabindex="2" name="firm_id">
		                            	<option value="">选择企业...</option>
		                            	<c:forEach items="${firms }" var="f">
                                    	<option value="${f.id }">${f.name }</option>
                                    	</c:forEach>
		                            </select>
		                            <select data-placeholder="选择类型..." style="width:250px;" class="chosen-select" multiple tabindex="2" name="type">
		                            	<option value="">选择类型...</option>
		                            	<option value="0" hassubinfo="true">订单支付</option>
		                            	<option value="3" hassubinfo="true">订单退款</option>
		                            	<option value="2" hassubinfo="true">余额充值</option>
		                            	<option value="5" hassubinfo="true">余额退返</option>
		                            	<option value="1" hassubinfo="true">保证金充值</option>
		                            	<option value="4" hassubinfo="true">保证金退返</option>
		                            </select>
		                            <input type="text" class="form-control input-sm" name="keywords" style="width:250px;float: right;" value="${vo.keywords}" placeholder="搜索流水号、订单号、手机号等">
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
                                    <th data-toggle="true">流水号</th>
                                    <th>会员</th>
                                    <th>企业</th>
                                    <th>明细</th>
                                    <th>金额</th>
                                    <th>支付方式</th>
                                    <th>交易状态</th>
                                    <th>创建时间</th>
                                    <th>系统用户</th>
                                    <th>平台</th>
                                    <th data-hide="all">交易号</th>
                                    <th data-hide="all">订单号</th>
                                    <th data-hide="all">备注</th>
                                </tr>
                                </thead>
                                <tbody>
                                <c:forEach items="${page.result }" var="r">
                                <tr>
                                	<td>${r.sn }</td>
                                    <td>
                                    	<c:choose>
                                    		<c:when test="${empty(r.mem_name) }">-</c:when>
                                    		<c:otherwise>${r.mem_name }</c:otherwise>
                                    	</c:choose>
                                    </td>
                                    <td>
                                    	<c:choose>
                                    		<c:when test="${empty(r.firm_name) }">-</c:when>
                                    		<c:otherwise>${r.firm_name }</c:otherwise>
                                    	</c:choose>
                                    </td>
                                    <td>${r.descript }</td>
                                    <td>${r.amount }</td>
                                    <td><c:choose><c:when test="${r.pay_type==0}">余额</c:when><c:when test="${r.pay_type==1}">支付宝</c:when><c:when test="${r.pay_type==2}">微信</c:when><c:when test="${r.pay_type==3}">银联</c:when><c:otherwise>线下</c:otherwise></c:choose></td>
                                    <td><c:choose><c:when test="${r.pay_status==0}">待支付</c:when><c:otherwise>已支付</c:otherwise></c:choose></td>
                                    <td><fmt:formatDate value="${r.create_date}" pattern="yyyy-MM-dd HH:mm:ss" /></td>
                                    <td>
                                    	<c:choose>
                                    		<c:when test="${empty(r.user_name) }">-</c:when>
                                    		<c:otherwise>${r.user_name }</c:otherwise>
                                    	</c:choose>
                                    </td>
                                    <td><c:choose><c:when test="${r.channel==0}">系统</c:when><c:otherwise>APP</c:otherwise></c:choose></td>
                                	<td>${r.trade_no }</td>
                                	<td>${r.order_sn }</td>
                                	<td>${r.remark }</td>
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
    <script src="${ctx}/statics/manage/js/plugins/datapicker/bootstrap-datepicker.js"></script>
    <script src="${ctx}/statics/manage/js/common.js?v=1.0.0"></script>
    <script>
        $(document).ready(function(){$(".footable").footable();$("#pagination").html('${page.pageContent }');$(".i-checks").iCheck({checkboxClass:"icheckbox_square-green",radioClass:"iradio_square-green"});$('.chosen-select').chosen();pageinit();});
    	//初始化select控件
       	$("[name='firm_id']").val('${vo.firm_id}');
       	if('${vo.type}'!=''){
       		var ids='${vo.type}'.split(',');
       		for(var i=0;i<ids.length;i++){
       			$("[name='type'] option[value='" + ids[i] + "']").attr('selected', 'selected');
       		}
       	}
       	$(".chosen-select").trigger("chosen:updated");
    </script>
</body>

</html>