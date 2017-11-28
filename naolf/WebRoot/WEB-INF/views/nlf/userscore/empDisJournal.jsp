<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
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

</head>

<body class="gray-bg">
	<div class="wrapper wrapper-content animated fadeInRight">

		<div class="row">
			<div class="col-sm-12">
				<div class="ibox float-e-margins">
					<div class="ibox-title">
						<h5>员工积分流水</h5>
						<div class="ibox-tools">
							<a class="collapse-link"> <i class="fa fa-chevron-up"></i> </a>
						</div>
					</div>
					<div class="ibox-content">
						<form action="${ctx }/manage/empdisjournal/list" method="post" id="searchForm" class="pull-right mail-search" target='_self'>
		                        <div class="input-group">
		                            <input type="text" class="form-control input-sm" name="keywords" value="${vo.keywords}" placeholder="搜索会员,会员ID或者订单号">
		                            <input type="hidden" name="pageNo" value="${vo.pageNo }"/>
				      				<input type="hidden" name="pageSize" value="${vo.pageSize }">
				      				<input type="hidden" name="user_id" value="${vo.user_id }">
		                            <div class="input-group-btn">
		                                <button type="button" class="btn btn-sm btn-primary">搜索</button>
		                            </div>
		                        </div>
		                </form>
						<table class="footable table table-stripped toggle-arrow-tiny"
							data-sort="false" data-page-size="${vo.pageSize }">
							<thead>
								<tr>
									<th>交易类型</th>
									<th>部门</th>
									<th>用户姓名</th>
									<th>用户ID</th>
									<th>未结业积分</th>
									<th>结业积分</th>
									<th>可兑换积分</th>
									<th>订单号</th>
									<th>订单金额</th>
									<th>购买人</th>
									<th>购买人ID</th>
									<th>推荐人ID</th>
									<th>等级</th>
									<th>关联推荐人1</th>
									<th>等级</th>
									<th>关联推荐人2</th>
									<th>等级</th>
									<th>关联推荐人3</th>
									<th>等级</th>
									<th>创建时间</th>
								</tr>
							</thead>
							<tbody>
								<c:forEach items="${page.result }" var="journal">
									<tr>
										<td>${journal.TRANSFER_name }</td>
										<td>${journal.depart_name }</td>
										<td>${journal.username }</td>
										<td>${journal.user_number }</td>
										<td>${journal.BKZ_SCORE }</td>
										<td>${journal.KZ_SCORE }</td>
										<td>${journal.KD_SCORE }</td>
										<td>${journal.ORDER_NO }</td>
										<td>${journal.price }</td>
										<td>${journal.buy_name }</td>
										<td>${journal.buy_num }</td>
										<td>${journal.grade1_refer }</td>
										<td>${journal.grade1 }</td>
										<td>${journal.grade2_refer }</td>
										<td>${journal.grade2 }</td>
										<td>${journal.grade3_refer }</td>
										<td>${journal.grade3 }</td>
										<td>${journal.grade4_refer }</td>
										<td>${journal.grade4 }</td>
										<td><fmt:formatDate value="${journal.create_date }" pattern="yyyy-MM-dd HH:mm:ss"/></td>
									</tr>
								</c:forEach>
							</tbody>
							<tfoot>
								<tr>
									<td colspan="19" id="pagination">
										</td>
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
	<script
		src="${ctx}/statics/manage/js/plugins/footable/footable.all.min.js"></script>
	<script src="${ctx}/statics/manage/js/plugins/iCheck/icheck.min.js"></script>
	<script src="${ctx}/statics/manage/plugins/layer/layer.js"></script>
	<script src="${ctx}/statics/manage/js/common.js?v=1.0.0"></script>
	<script>
		$(document).ready(function() {
			$(".footable").footable();
			$("#pagination").html('${page.pageContent }');
			$(".i-checks").iCheck({
				checkboxClass : "icheckbox_square-green",
				radioClass : "iradio_square-green"
			});
			pageinit();
		});
	</script>
</body>

</html>