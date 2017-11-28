<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html lang="en" class=" ">
<head>
<meta charset="utf-8" />
<title>微贝</title>
<meta name="description"
	content="app, web app, responsive, admin dashboard, admin, flat, flat ui, ui kit, off screen nav" />
<meta name="viewport"
	content="width=device-width, initial-scale=1, maximum-scale=1" />
<link type="text/css" rel="stylesheet"
	href="${ResStatic }/static/css/bootstrap.css" />
<link type="text/css" rel="stylesheet"
	href="${ResStatic }/static/css/app.css" />
<link type="text/css" rel="stylesheet"
	href="${ResStatic }/static/css/base.css">
<link type="text/css" rel="stylesheet"
	href="${ResStatic }/static/css/button.css">
<link type="text/css" rel="stylesheet"
	href="${ResStatic }/static/css/font-awesome.min.css">
<style type="text/css">
.Order-table tr td{ padding:2px 10px; border-right:1px solid #d9e4e6;}
.Order-table tr td.Order-td{background-color:#f7f7f7;}
.order-Ptitle{padding:6px 10px;}
</style>
</head>
<body>
	<section class="wrapper">
		<div class="m-b">
			<span class="h3 font-thin"><i class="i i-arrow-left3"></i>
			</span>
		</div>
		<section class="panel panel-default" style="">
			<div class="row wrapper">
				<div class="col-sm-12" style=" background:url(${ResStatic }/static/img/welcome.png) center no-repeat; background-size:100% 100%; height:520px; padding:0;">
					<div style=" font-size:24px; width:100%; text-align:center; margin-top:10px;">欢迎进入后台管理系统</div>
					<shiro:hasPermission name="manager:index:visit">
					<div style=" font-size:16px; width:100%; text-align:center; margin-top:5px;"><b>当前人数:${online }</b><a style="padding: 10px;" href="${ctx}/manager/index/visit">查看统计</a></div>
					</shiro:hasPermission>
				</div>
			</div>
		</section>
		<section style="">
				<div class="row wrapper">
					<div class="box_content" style="position:absolute;top:80px;width:97.5%;">
						<div class="col-sm-12">
						<c:if test="${type=='0' }">
							<div class="order-border Or-borderHed" style="background:#fff;margin:20px 12px 0;">
								<div class="order-Ptitle font16">商品订单信息</div>
								<table class="Order-table">
									<tr>
										<td class="Order-td">全部订单:</td>
										<td><label style="cursor:pointer;"><a href="${ctx }/manager/order/list/100">${order_all_num }</a></label>
										</td>
									</tr>
									<tr>
										<td class="Order-td">待确认订单:</td>
										<td><label style="cursor:pointer;"><a href="${ctx }/manager/order/list/1">${unconfirm_num }</a></label>
										</td>
									</tr>
									<tr>
										<td class="Order-td">待配货订单:</td>
										<td><label style="cursor:pointer;"><a href="${ctx }/manager/order/list/2">${unallocation_num }</a></label>
										</td>
									</tr>
									<tr>
										<td class="Order-td">待发货订单:</td>
										<td><label style="cursor:pointer;"><a href="${ctx }/manager/order/list/4">${unsend_num }</a></label>
										</td>
									</tr>
								</table>
							</div>
							</c:if>
							<div class="order-border Or-borderHed" style="background:#fff;margin:20px 12px 0;">
								<div class="order-Ptitle font16">服务预约信息</div>
								<table class="Order-table">
									<tr>
										<td class="Order-td">预约总数:</td>
										<td><label style="cursor:pointer;"><a href="${ctx }/manager/reserve/list/all">${reserve_all_num }</a></span>
										</label>
										</td>
									</tr>
									<tr>
										<td class="Order-td">待审预约数:</td>
										<td><label style="cursor:pointer;"><a href="${ctx }/manager/reserve/list/approve">${approve_num }</a></span>
										</label>
										</td>
									</tr>
								</table>
								<div class="order-Ptitle font16">服务购买信息</div>
								<table class="Order-table">
									<tr>
										<td class="Order-td">购买总数:</td>
										<td><label style="cursor:pointer;"><a href="${ctx }/manager/reserve/list/order">${buy_all_num }</a></span>
										</label>
										</td>
									</tr>
								</table>
							</div>
							<c:if test="${type=='0' }">
							<div class="order-border Or-borderHed" style="background:#fff;margin:20px 12px 0;">
								<div class="order-Ptitle font16">售后服务信息</div>
								<table class="Order-table">
									<tr>
										<td class="Order-td">退换货单数:</td>
										<td><label style="cursor:pointer;"><a href="${ctx }/manager/returned/list?state=0">${return_num }</a></span>
										</label>
										</td>
									</tr>
								</table>
							</div>
							<div class="order-border Or-borderHed" style="background:#fff;margin:20px 12px 0;">
								<div class="order-Ptitle font16">用户反馈信息</div>
								<table class="Order-table">
									<tr>
										<td class="Order-td">反馈信息数:</td>
										<td><label style="cursor:pointer;"><a href="${ctx }/manager/suggest/list">${suggest_num }</a></label>
										</td>
									</tr>
								</table>
							</div>
							</c:if>
						</div>
					</div>
				</div>
			</section>
	</section>
</body>
</html>