<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/include.inc.jsp"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>

<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="renderer" content="webkit">
    <meta http-equiv="Cache-Control" content="no-siteapp" />
    <title>脑立方管理系统</title>

    <meta name="keywords" content="后台bootstrap框架,后台HTML,响应式后台">
    <meta name="description" content="基于Bootstrap3最新版本开发的扁平化主题，她采用了主流的左右两栏式布局，使用了Html5+CSS3等现代技术">

    <!--[if lt IE 8]>
    <meta http-equiv="refresh" content="0;ie.html" />
    <![endif]-->

    <link href="${ctx}/statics/favicon/favicon.ico" rel="shortcut icon">
    <link href="${ctx}/statics/manage/css/bootstrap.min.css?v=3.3.5" rel="stylesheet">
    <link href="${ctx}/statics/manage/css/font-awesome.min.css?v=4.4.0" rel="stylesheet">
    <link href="${ctx}/statics/manage/css/animate.min.css" rel="stylesheet">
    <link href="${ctx}/statics/manage/css/style.min.css?v=4.0.0" rel="stylesheet">
</head>

<body class="fixed-sidebar full-height-layout gray-bg" style="overflow:hidden">
    <div id="wrapper">
        <!--左侧导航开始-->
        <nav class="navbar-default navbar-static-side" role="navigation">
            <div class="nav-close"><i class="fa fa-times-circle"></i>
            </div>
            <div class="sidebar-collapse">
                <ul class="nav" id="side-menu">
                    <li class="nav-header">
                        <div class="dropdown profile-element">
                            <span><img alt="image" class="img-circle" src=<c:choose><c:when test='${empty(user.head_img) }'>${ctx}/statics/manage/img/logo.png</c:when><c:otherwise>${user.head_img }</c:otherwise></c:choose> width="64" height="64"/></span>
                            <a data-toggle="dropdown" class="dropdown-toggle" href="#">
                                <span class="clear">
                               <span class="block m-t-xs"><strong class="font-bold">${user.username }</strong></span>
                                <span class="text-muted text-xs block">${user.number }<b class="caret"></b></span>
                                </span>
                            </a>
                            <ul class="dropdown-menu animated fadeInRight m-t-xs">
                                <li><a data-toggle="modal" href="#setUp">设置个人信息</a></li>
                                <li class="divider"></li>
                                <li><a href="${ctx}/manage/logout">安全退出</a></li>
                            </ul>
                        </div>
                        <div class="logo-element">脑立方
                        </div>
                    </li>
                    <li>
                        <a class="J_menuItem" href="${ctx}/manage/main" data-index="0">
                            <i class="fa fa-home"></i> <span class="nav-label">主页</span>
                        </a>
                    </li>
<!--                     <shiro-ex:hasAnyPermissions name="manage:brand:list,manage:car:list,manage:car:dync"> -->
<!--                     <li> -->
<!--                         <a href="javascript:;"><i class="fa fa-automobile"></i> <span class="nav-label">车辆管理</span> <span class="fa arrow"></span></a> -->
<!--                         <ul class="nav nav-second-level"> -->
<!--                         	<shiro:hasPermission name="manage:brand:list"> -->
<!--                             <li> -->
<!--                              	<a class="J_menuItem" href="${ctx}/manage/brand/list">品牌管理</a> -->
<!--                             </li> -->
<!--                             </shiro:hasPermission> -->
<!--                             <shiro:hasPermission name="manage:car:list"> -->
<!--                             <li> -->
<!--                             	<a class="J_menuItem" href="${ctx}/manage/car/list">车辆管理</a> -->
<!--                             </li> -->
<!--                             </shiro:hasPermission> -->
<!--                             <shiro:hasPermission name="manage:car:dync"> -->
<!--                             <li> -->
<!--                             	<a class="J_menuItem" href="${ctx}/manage/car/dync">动态管理</a> -->
<!--                             </li> -->
<!--                             </shiro:hasPermission> -->
<!--                         </ul> -->
<!--                     </li> -->
<!--                     </shiro-ex:hasAnyPermissions> -->
<!--                     <shiro-ex:hasAnyPermissions name="manage:dot:list"> -->
<!--                     <li> -->
<!--                         <a href="javascript:;"><i class="fa fa-object-group"></i> <span class="nav-label">网点管理 </span> <span class="fa arrow"></span></a> -->
<!--                         <ul class="nav nav-second-level"> -->
<!--                         	<shiro:hasPermission name="manage:dot:list"> -->
<!--                             <li> -->
<!--                              	<a class="J_menuItem" href="${ctx}/manage/dot/list">网点管理</a> -->
<!--                             </li> -->
<!--                             </shiro:hasPermission> -->
<!--                         </ul> -->
<!--                     </li> -->
<!-- 					</shiro-ex:hasAnyPermissions> -->
<!-- 					<shiro-ex:hasAnyPermissions name="manage:parking:list"> -->
<!--                     <li> -->
<!--                         <a href="javascript:;"><i class="fa fa-map"></i> <span class="nav-label">车位管理 </span> <span class="fa arrow"></span></a> -->
<!--                         <ul class="nav nav-second-level"> -->
<!--                         	<shiro:hasPermission name="manage:parking:list"> -->
<!--                             <li> -->
<!--                             	<a class="J_menuItem" href="${ctx }/manage/parking/list">车位管理</a> -->
<!--                             </li> -->
<!--                             </shiro:hasPermission> -->
<!--                         </ul> -->
<!--                     </li> -->
<!--                     </shiro-ex:hasAnyPermissions> -->
<!--                     <shiro-ex:hasAnyPermissions name="manage:operatorinfo:list,manage:stationinfo:list,manage:equipmentinfo:list,manage:connectorinfo:list"> -->
<!--                     <li> -->
<!--                         <a href="javascript:;"><i class="fa fa-battery-3"></i> <span class="nav-label">充电站管理 </span> <span class="fa arrow"></span></a> -->
<!--                         <ul class="nav nav-second-level"> -->
<!--                         	<shiro:hasPermission name="manage:operatorinfo:list"> -->
<!--                         	<li> -->
<!--                             	<a class="J_menuItem" href="${ctx }/manage/operatorinfo/list">运营商管理</a> -->
<!--                             </li> -->
<!--                             </shiro:hasPermission> -->
<!--                             <shiro:hasPermission name="manage:stationinfo:list"> -->
<!--                             <li> -->
<!--                             	<a class="J_menuItem" href="${ctx }/manage/stationinfo/list">充电站管理</a> -->
<!--                             </li> -->
<!--                             </shiro:hasPermission> -->
<!--                             <shiro:hasPermission name="manage:equipmentinfo:list"> -->
<!--                             <li> -->
<!--                             	<a class="J_menuItem" href="${ctx }/manage/equipmentinfo/list">充电设备管理</a> -->
<!--                             </li> -->
<!--                             </shiro:hasPermission> -->
<!--                             <shiro:hasPermission name="manage:connectorinfo:list"> -->
<!--                             <li> -->
<!--                             	<a class="J_menuItem" href="${ctx }/manage/connectorinfo/list">设备接口管理</a> -->
<!--                             </li> -->
<!--                             </shiro:hasPermission> -->
<!--                         </ul> -->
<!--                     </li> -->
<!--                     </shiro-ex:hasAnyPermissions> -->
<!--                     <shiro-ex:hasAnyPermissions name="manage:firm:list,manage:member:flist,manage:member:list,manage:level:list"> -->
<!--                     <li> -->
<!--                         <a href="javascript:;"><i class="fa fa-users"></i> <span class="nav-label">会员管理</span> <span class="fa arrow"></span></a> -->
<!--                         <ul class="nav nav-second-level"> -->
<!--                         	<shiro:hasPermission name="manage:firm:list"> -->
<!--                             <li> -->
<!--                                 <a class="J_menuItem" href="${ctx }/manage/firm/list">企业管理</a> -->
<!--                             </li> -->
<!--                             </shiro:hasPermission> -->
<!--                             <shiro:hasPermission name="manage:member:flist"> -->
<!--                             <li> -->
<!--                                 <a class="J_menuItem" href="${ctx }/manage/member/flist">企业会员</a> -->
<!--                             </li> -->
<!--                             </shiro:hasPermission> -->
<!--                             <shiro:hasPermission name="manage:member:list"> -->
<!--                             <li> -->
<!--                                 <a class="J_menuItem" href="${ctx}/manage/member/list">普通会员</a> -->
<!--                             </li> -->
<!--                             </shiro:hasPermission> -->
<!--                             <shiro:hasPermission name="manage:level:list"> -->
<!--                             <li> -->
<!--                                 <a class="J_menuItem" href="${ctx}/manage/level/list">积分等级</a> -->
<!--                             </li> -->
<!--                             </shiro:hasPermission> -->
<!--                         </ul> -->
<!--                     </li> -->
<!--                     </shiro-ex:hasAnyPermissions> -->
<!--                     <shiro-ex:hasAnyPermissions name="manage:order:tlist,manage:order:list,manage:charge:list,manage:order:illegal,manage:refund:list,manage:paymentLog:list"> -->
<!--                     <li> -->
<!--                         <a href="#"><i class="fa fa-balance-scale"></i> <span class="nav-label">结算中心</span><span class="fa arrow"></span></a> -->
<!--                         <ul class="nav nav-second-level"> -->
<!--                         	<shiro:hasPermission name="manage:order:tlist"> -->
<!--                         	<li> -->
<!--                             	<a class="J_menuItem" href="${ctx }/manage/order/tlist">时租订单</a> -->
<!--                             </li> -->
<!--                             </shiro:hasPermission> -->
<!--                             <shiro:hasPermission name="manage:order:list"> -->
<!--                             <li> -->
<!--                             	<a class="J_menuItem" href="${ctx }/manage/order/list">日租订单</a> -->
<!--                             </li> -->
<!--                             </shiro:hasPermission> -->
<!--                             <shiro:hasPermission name="manage:charge:list"> -->
<!--                             <li> -->
<!--                             	<a class="J_menuItem" href="${ctx }/manage/charge/list">充电订单</a> -->
<!--                             </li> -->
<!--                             </shiro:hasPermission> -->
<!--                             <shiro:hasPermission name="manage:order:illegal"> -->
<!--                             <li> -->
<!--                             	<a class="J_menuItem" href="${ctx }/manage/order/illegal">违章查询</a> -->
<!--                             </li> -->
<!--                             </shiro:hasPermission> -->
<!--                             <shiro:hasPermission name="manage:refund:list"> -->
<!--                             <li> -->
<!--                             	<a class="J_menuItem" href="${ctx }/manage/refund/list">退款申请（保证金）</a> -->
<!--                             </li> -->
<!--                             </shiro:hasPermission> -->
<!--                             <shiro:hasPermission name="manage:paymentLog:list"> -->
<!--                             <li> -->
<!--                             	<a class="J_menuItem" href="${ctx }/manage/paymentLog/list">交易流水</a> -->
<!--                             </li> -->
<!--                             </shiro:hasPermission> -->
<!--                         </ul> -->
<!--                     </li> -->
<!--                     </shiro-ex:hasAnyPermissions> -->
<!--                     <shiro-ex:hasAnyPermissions name="manage:comment:list,manage:comment:slist"> -->
<!--                     <li> -->
<!--                         <a href="javascript:;"><i class="fa fa-commenting-o"></i> <span class="nav-label">评论管理</span> <span class="fa arrow"></span></a> -->
<!--                         <ul class="nav nav-second-level"> -->
<!--                             <shiro:hasPermission name="manage:comment:list"> -->
<!--                             <li> -->
<!--                             	<a class="J_menuItem" href="${ctx}/manage/comment/list">车辆评论</a> -->
<!--                             </li> -->
<!--                             </shiro:hasPermission> -->
<!--                             <shiro:hasPermission name="manage:comment:slist"> -->
<!--                             <li> -->
<!--                             	<a class="J_menuItem" href="${ctx}/manage/comment/slist">充电站评论</a> -->
<!--                             </li> -->
<!--                             </shiro:hasPermission> -->
<!--                         </ul> -->
<!--                     </li> -->
<!--                     </shiro-ex:hasAnyPermissions> -->
<!-- 					<shiro-ex:hasAnyPermissions name="none:none">                     -->
<!--                     <li> -->
<!--                         <a href="#"><i class="fa fa fa-bar-chart-o"></i> <span class="nav-label">统计报表</span> <span class="fa arrow"></span></a> -->
<!--                         <ul class="nav nav-second-level"> -->
<!--                             <li> -->
<!--                                 <a class="J_menuItem" href="graph_echarts.html">热租车辆统计</a> -->
<!--                             </li> -->
<!--                             <li> -->
<!--                                 <a class="J_menuItem" href="graph_flot.html">营业额统计</a> -->
<!--                             </li> -->
<!--                         </ul> -->
<!--                     </li> -->
<!--                     </shiro-ex:hasAnyPermissions> -->
<!--                     <shiro-ex:hasAnyPermissions name="none:none"> -->
<!--                     <li> -->
<!--                         <a href="#"><i class="fa fa-flask"></i> <span class="nav-label">活动管理</span><span class="fa arrow"></span></a> -->
<!--                         <ul class="nav nav-second-level"> -->
<!--                             <li><a class="J_menuItem" href="typography.html">Banner管理</a> -->
<!--                             </li> -->
<!--                             <li><a class="J_menuItem" href="tabs_panels.html">活动规则管理</a> -->
<!--                             </li> -->
<!--                         </ul> -->
<!--                     </li> -->
<!--                     </shiro-ex:hasAnyPermissions> -->
                    <shiro-ex:hasAnyPermissions name="manage:role:list,manage:permission:list,manage:depart:list,manage:orderdisratesetting:list,manage:dict:list,manage:log:list,manage:sessions:list,manage:version:list,manage:push:list,manage:news:list,manage:news:joinUs">
                    <li>
                        <a href="#"><i class="fa fa-cogs"></i> <span class="nav-label">系统管理</span><span class="fa arrow"></span></a>
                        <ul class="nav nav-second-level">
                            <shiro:hasPermission name="manage:role:list">
                            <li>
                            	<a class="J_menuItem" href="${ctx }/manage/role/list">角色管理</a>
                            </li>
                            </shiro:hasPermission>
                            <shiro:hasPermission name="manage:permission:list">
                            <li>
                            	<a class="J_menuItem" href="${ctx }/manage/permission/list">权限管理</a>
                            </li>
                            </shiro:hasPermission>
                            <shiro:hasPermission name="manage:depart:list">
                            <li>
                            	<a class="J_menuItem" href="${ctx }/manage/depart/list">部门管理</a>
                            </li>
                            </shiro:hasPermission>
                            
                            <shiro:hasPermission name="manage:orderdisratesetting:list">
                            <li>
                            	<a class="J_menuItem" href="${ctx }/manage/orderdisratesetting/list">提成比例设置</a>
                            </li>
                            </shiro:hasPermission>
                            <shiro:hasPermission name="manage:log:list">
                              <li>
                            	<a class="J_menuItem" href="${ctx }/manage/log/list">日志管理</a> 
                             </li> 
                           </shiro:hasPermission>
<!--
<!--                             <shiro:hasPermission name="manage:dict:list"> -->
<!--                             <li> -->
<!--                             	<a class="J_menuItem" href="${ctx }/manage/dict/list">数据字典</a> -->
<!--                             </li> -->
<!--                             </shiro:hasPermission> -->
<!--                             <shiro:hasPermission name="manage:version:list"> -->
<!--                             <li> -->
<!--                             	<a class="J_menuItem" href="${ctx }/manage/version/list">版本管理</a> -->
<!--                             </li> -->
<!--                             </shiro:hasPermission> -->
<!--                             <shiro:hasPermission name="manage:push:list"> -->
<!--                             <li> -->
<!--                             	<a class="J_menuItem" href="table_bootstrap.html">推送管理</a> -->
<!--                             </li> -->
<!--                             </shiro:hasPermission> -->
<!--                             <shiro:hasPermission name="manage:log:list"> -->
<!--                             <li> -->
<!--                             	<a class="J_menuItem" href="${ctx }/manage/log/list">日志管理</a> -->
<!--                             </li> -->
<!--                             </shiro:hasPermission> -->
<!--                             <shiro:hasPermission name="manage:sessions:list"> -->
<!--                             <li> -->
<!--                             	<a class="J_menuItem" href="${ctx }/manage/sessions/list">会话管理</a> -->
<!--                             </li> -->
<!--                             </shiro:hasPermission> -->
<!--                             <shiro-ex:hasAnyPermissions name="manage:news:list,manage:news:joinUs"> -->
<!--                             <li> -->
<!--                                 <a href="javascript:;">官网设置 <span class="fa arrow"></span></a> -->
<!--                                 <ul class="nav nav-third-level"> -->
<!--                                 	<shiro:hasPermission name="manage:news:list"> -->
<!--                                     <li> -->
<!--                                     	<a class="J_menuItem" href="${ctx }/manage/news/list">新闻管理</a> -->
<!--                                     </li> -->
<!--                                     </shiro:hasPermission> -->
<!--                                     <shiro:hasPermission name="manage:news:joinUs"> -->
<!--                                     <li> -->
<!--                                     	<a class="J_menuItem" href="${ctx }/manage/news/joinUs">联系我们</a> -->
<!--                                     </li> -->
<!--                                     </shiro:hasPermission> -->
<!--                                 </ul> -->
<!--                             </li> -->
<!--                             </shiro-ex:hasAnyPermissions> -->
                        </ul>
                    </li>
                    </shiro-ex:hasAnyPermissions>
                     <shiro-ex:hasAnyPermissions name="manage:user:list,manage:activation:list">
                    <li>
                        <a href="#"><i class="fa fa-user"></i> <span class="nav-label">用户管理</span><span class="fa arrow"></span></a>
                        <ul class="nav nav-second-level">
                        	<shiro:hasPermission name="manage:user:list">
                            <li>
                            	<a class="J_menuItem" href="${ctx }/manage/user/list?user_type=2&is_locked=0">会员管理</a>
                            </li>
                            <li>
                            	<a class="J_menuItem" href="${ctx }/manage/user/list?user_type=1&is_locked=0">众创会员管理</a>
                            </li>
                            <li>
                            	<a class="J_menuItem" href="${ctx }/manage/user/list?user_type=0&is_locked=0">职员管理</a>
                            </li>
                            </shiro:hasPermission>
                             <shiro:hasPermission name="manage:activation:list">
                            <li>
                            	<a class="J_menuItem" href="${ctx }/manage/activation/list?enabled=0">激活码管理</a>
                            </li>
                            </shiro:hasPermission>
                        </ul>
                    </li>
                    </shiro-ex:hasAnyPermissions>
                     <shiro-ex:hasAnyPermissions name="manage:lesson:list,manage:ticket:list,manage:car:dync,manage:rule:list,manage:course:list,manage:appoint:list,manage:lcomment:list">
                    <li>
                        <a href="javascript:;"><i class="fa fa-book"></i> <span class="nav-label">课程管理</span> <span class="fa arrow"></span></a>
                        <ul class="nav nav-second-level">
                            <shiro:hasPermission name="manage:ticket:list">
                            <li>
                            	<a class="J_menuItem" href="${ctx}/manage/ticket/list">卡券类型管理</a>
                            </li>
                            </shiro:hasPermission>
                          	<shiro:hasPermission name="manage:rule:list">
                            <li>
                            	<a class="J_menuItem" href="${ctx}/manage/rule/list">规则管理</a>
                            </li>
                            </shiro:hasPermission>
                        	<shiro:hasPermission name="manage:lesson:list">
                            <li>
                             	<a class="J_menuItem" href="${ctx}/manage/lesson/list">课程管理</a>
                            </li>
                            </shiro:hasPermission>
                            <shiro:hasPermission name="manage:course:list">
                            <li>
                            	<a class="J_menuItem" href="${ctx}/manage/course/list?enabled=0">课程发布</a>
                            </li>
                            </shiro:hasPermission>
                            <shiro:hasPermission name="manage:appoint:list">
                            <li style="display: none">
                            	<a class="J_menuItem" href="${ctx}/manage/appoint/list">课程预约</a>
                            </li>
                            </shiro:hasPermission>
                            <shiro:hasPermission name="manage:lcomment:list">
                            <li>
                            	<a class="J_menuItem" href="${ctx}/manage/lcomment/list">查看评论</a>
                            </li>
                            </shiro:hasPermission>
                        </ul>
                    </li>
                    </shiro-ex:hasAnyPermissions>
                    <shiro-ex:hasAnyPermissions name="manage:norder:list">
                    <li>
                        <a href="javascript:;"><i class="fa fa-trademark"></i> <span class="nav-label">订单管理</span> <span class="fa arrow"></span></a>
                        <ul class="nav nav-second-level">
                        	<shiro:hasPermission name="manage:norder:list">
                            <li>
                             	<a class="J_menuItem" href="${ctx}/manage/norder/list?type=0">课程订单</a>
                            </li>
                            <li>
                             	<a class="J_menuItem" href="${ctx}/manage/norder/list?type=1">卡券订单</a>
                            </li>
                            </shiro:hasPermission>
                        </ul>
                    </li>
                    </shiro-ex:hasAnyPermissions>
                    
                    <shiro-ex:hasAnyPermissions name="manage:recharge:list,manage:usercash:list,manage:deptperformance:list">
                    <li>
                        <a href="javascript:;"><i class="fa fa-chain"></i> <span class="nav-label">交易管理</span> <span class="fa arrow"></span></a>
                        <ul class="nav nav-second-level">
                            <shiro:hasPermission name="manage:recharge:list">
                            <li>
                            	<a class="J_menuItem" href="${ctx}/manage/recharge/list">充值记录</a>
                            </li>
                            </shiro:hasPermission>
                            <shiro:hasPermission name="manage:usercash:list">
                            <li>
                            	<a class="J_menuItem" href="${ctx}/manage/usercash/list">积分提现</a>
                            </li>
                            </shiro:hasPermission>
                            <shiro:hasPermission name="manage:deptperformance:list">
                            <li>
                            	<a class="J_menuItem" href="${ctx}/manage/deptperformance/list">部门绩效</a>
                            </li>
                            </shiro:hasPermission>
                        </ul>
                    </li>
                    </shiro-ex:hasAnyPermissions>
                    
                    <shiro-ex:hasAnyPermissions name="manage:userscoreinfo:list,manage:userticket:list">
                    <li>
                        <a href="javascript:;"><i class="fa fa-gg-circle"></i> <span class="nav-label">账户管理</span> <span class="fa arrow"></span></a>
                        <ul class="nav nav-second-level">
                           <shiro:hasPermission name="manage:userticket:list">
                            <li>
                            	<a class="J_menuItem" href="${ctx}/manage/userticket/list">用户卡券管理</a>
                            </li>
                            </shiro:hasPermission>
                            <shiro:hasPermission name="manage:userscoreinfo:list">
                            <li>
                            	<a class="J_menuItem" href="${ctx}/manage/userscoreinfo/list">积分查看</a>
                            </li>
                            </shiro:hasPermission>
                        </ul>
                    </li>
                    </shiro-ex:hasAnyPermissions>
                </ul>
            </div>
        </nav>
        <!--左侧导航结束-->
        <!--右侧部分开始-->
        <div id="page-wrapper" class="gray-bg dashbard-1">
            <div class="row border-bottom">
                <nav class="navbar navbar-static-top" role="navigation" style="margin-bottom: 0">
                    <div class="navbar-header"><a class="navbar-minimalize minimalize-styl-2 btn btn-primary " href="javascript:;"><i class="fa fa-bars"></i> </a></div>
                    <ul class="nav navbar-top-links navbar-right">
                        <shiro-ex:hasAnyPermissions name="manage:order:tlist,manage:order:list">
                        <li class="dropdown" style="display: none">
                            <a class="dropdown-toggle count-info" data-toggle="dropdown" href="javascript:;">
                                <i class="fa fa-bell"></i>新增订单<span class="label label-primary" id="newTotalCount">-</span>
                            </a>
                            <ul class="dropdown-menu dropdown-alerts">
                            	<shiro:hasPermission name="manage:order:tlist">
                                <li>
                                    <a class="J_menuItem" href="${ctx }/manage/order/tlist?is_read=0">
                                        <div>
                                            <i class="fa fa-bell fa-fw"></i> 您有<span id="newTCount">-</span>个时租订单
                                        </div>
                                    </a>
                                </li>
                                </shiro:hasPermission>
                                <shiro:hasPermission name="manage:order:list">
                                <li class="divider"></li>
                                <li>
                                    <a class="J_menuItem" href="${ctx }/manage/order/list?is_read=0">
                                        <div>
                                            <i class="fa fa-bell fa-fw"></i> 您有<span id="newDCount">-</span>个日租订单
                                        </div>
                                    </a>
                                </li>
                                </shiro:hasPermission>
                                <li class="divider"></li>
                                <li>
                                    <a class="J_menuItem" href="${ctx }/manage/charge/list?is_read=0">
                                        <div>
                                            <i class="fa fa-bell fa-fw"></i> 您有<span id="newCCount">-</span>个充电订单
                                        </div>
                                    </a>
                                </li>
                            </ul>
                        </li>
                        </shiro-ex:hasAnyPermissions>
                        <li class="dropdown hidden-xs">
                            <a class="right-sidebar-toggle" aria-expanded="false">
                                <i class="fa fa-tasks"></i> 主题
                            </a>
                        </li>
                    </ul>
                </nav>
            </div>
            <div class="row content-tabs">
                <button class="roll-nav roll-left J_tabLeft"><i class="fa fa-backward"></i>
                </button>
                <nav class="page-tabs J_menuTabs">
                    <div class="page-tabs-content">
                        <a href="javascript:;" class="active J_menuTab" data-id="${ctx}/manage/main">主页</a>
                    </div>
                </nav>
                <button class="roll-nav roll-right J_tabRight"><i class="fa fa-forward"></i>
                </button>
                <div class="btn-group roll-nav roll-right">
                    <button class="dropdown J_tabClose" data-toggle="dropdown">关闭操作<span class="caret"></span>

                    </button>
                    <ul role="menu" class="dropdown-menu dropdown-menu-right">
                        <li class="J_tabShowActive"><a>定位当前选项卡</a>
                        </li>
                        <li class="divider"></li>
                        <li class="J_tabCloseAll"><a>关闭全部选项卡</a>
                        </li>
                        <li class="J_tabCloseOther"><a>关闭其他选项卡</a>
                        </li>
                    </ul>
                </div>
                <a href="${ctx}/manage/logout" class="roll-nav roll-right J_tabExit"><i class="fa fa fa-sign-out"></i> 退出</a>
            </div>
            <div class="row J_mainContent" id="content-main">
                <iframe class="J_iframe" name="iframe0" width="100%" height="100%" src="${ctx}/manage/main" frameborder="0" data-id="${ctx}/manage/main" seamless></iframe>
            </div>
            <div class="footer">
                <div class="pull-right">&copy; 2012-2016 椰果网络科技（上海）有限公司
                </div>
            </div>
        </div>
        <!--右侧部分结束-->
        <!--右侧边栏开始-->
        <div id="right-sidebar">
            <div class="sidebar-container">

                <div class="tab-content">
                    <div class="tab-pane active">
                        <div class="sidebar-title">
                            <h3> <i class="fa fa-comments-o"></i> 主题设置</h3>
                            <small><i class="fa fa-tim"></i> 你可以从这里选择和预览主题的布局和样式，这些设置会被保存在本地，下次打开的时候会直接应用这些设置。</small>
                        </div>
                        <div class="skin-setttings">
                            <div class="title">主题设置</div>
                            <div class="setings-item">
                                <span>收起左侧菜单</span>
                                <div class="switch">
                                    <div class="onoffswitch">
                                        <input type="checkbox" name="collapsemenu" class="onoffswitch-checkbox" id="collapsemenu">
                                        <label class="onoffswitch-label" for="collapsemenu">
                                            <span class="onoffswitch-inner"></span>
                                            <span class="onoffswitch-switch"></span>
                                        </label>
                                    </div>
                                </div>
                            </div>
                            <div class="setings-item">
                                <span>固定顶部</span>

                                <div class="switch">
                                    <div class="onoffswitch">
                                        <input type="checkbox" name="fixednavbar" class="onoffswitch-checkbox" id="fixednavbar">
                                        <label class="onoffswitch-label" for="fixednavbar">
                                            <span class="onoffswitch-inner"></span>
                                            <span class="onoffswitch-switch"></span>
                                        </label>
                                    </div>
                                </div>
                            </div>
                            <div class="setings-item">
                                <span>固定宽度</span>
                                <div class="switch">
                                    <div class="onoffswitch">
                                        <input type="checkbox" name="boxedlayout" class="onoffswitch-checkbox" id="boxedlayout">
                                        <label class="onoffswitch-label" for="boxedlayout">
                                            <span class="onoffswitch-inner"></span>
                                            <span class="onoffswitch-switch"></span>
                                        </label>
                                    </div>
                                </div>
                            </div>
                            <div class="title">皮肤选择</div>
                            <div class="setings-item default-skin nb">
                                <span class="skin-name ">
                         			<a href="javascript:;" class="s-skin-0">默认皮肤</a>
                    			</span>
                            </div>
                            <div class="setings-item blue-skin nb">
                                <span class="skin-name ">
                        			<a href="javascript:;" class="s-skin-1">蓝色主题</a>
                    			</span>
                            </div>
                            <div class="setings-item yellow-skin nb">
                                <span class="skin-name ">
                        			<a href="javascript:;" class="s-skin-3">黄色/紫色主题</a>
                    			</span>
                            </div>
                        </div>
                    </div>
                   
                </div>

            </div>
        </div>
        <!--右侧边栏结束-->
        
    </div>
    
   
    <div class="modal inmodal" id="setUp" tabindex="-1" role="dialog" aria-hidden="true">
        <div class="modal-dialog">
            <div class="modal-content animated bounceInRight">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">关闭</span></button>
                    <span><img alt="image" class="img-circle" src=<c:choose><c:when test='${empty(user.head_img) }'>${ctx}/statics/manage/img/logo.png</c:when><c:otherwise>${user.head_img }</c:otherwise></c:choose> width="80" height="80"/></span>
                	<h4 class="modal-title"><button type="button" onclick="javascript:$(this).parent().next().find('input').click();" class="btn btn-white btn-sm"><i class="fa fa-cloud-upload"></i> 更换头像</button></h4>
                	<form id="uploadForm" enctype="multipart/form-data" method="post" style="display: none;">
                    	<input type="file" name="file" onchange="uploadHead(this)"/>
                    </form>
                    <small class="font-bold">图片格式仅支持JPG，PNG，GIF
                </div>
                <div class="modal-body">
                            <form action="" method="post" class="form-horizontal">
                            <div class="form-group">
                                <label class="col-sm-2 control-label">原密码</label>

                                <div class="col-sm-9">
                                    <input type="password" class="form-control" name="oriPwd" placeholder="请输入原密码">
                                </div>
                            </div>
                            <div class="hr-line-dashed"></div>
                            <div class="form-group">
                                <label class="col-sm-2 control-label">新密码</label>

                                <div class="col-sm-9">
                                    <input type="password" class="form-control" name="newPwd" placeholder="请输入新密码">
                                </div>
                            </div>
                            <div class="hr-line-dashed"></div>
                            <div class="form-group">
                                <label class="col-sm-2 control-label">确认密码</label>

                                <div class="col-sm-9">
                                    <input type="password" class="form-control" name="rePwd" placeholder="请输入确认密码">
                                </div>
                            </div>
                        </form>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-white" data-dismiss="modal">关闭</button>
                    <button type="button" class="btn btn-primary" onclick="setUp()">保存</button>
                </div>
            </div>
         </div>
    </div>
    
    <script src="${ctx}/statics/manage/js/jquery.min.js?v=2.1.4"></script>
    <script src="${ctx}/statics/manage/js/bootstrap.min.js?v=3.3.5"></script>
    <script src="${ctx}/statics/manage/js/plugins/metisMenu/jquery.metisMenu.js"></script>
    <script src="${ctx}/statics/manage/js/plugins/slimscroll/jquery.slimscroll.min.js"></script>
    <script src="${ctx}/statics/manage/plugins/layer/layer.js"></script>
    <script src="${ctx}/statics/manage/js/hplus.min.js?v=4.0.0"></script>
    <script src="${ctx}/statics/manage/js/contabs.min.js"></script>
    <script src="${ctx}/statics/manage/js/plugins/pace/pace.min.js"></script>
    <script src="${ctx}/statics/manage/js/jquery.form.js"></script>
    <script src="${ctx}/statics/manage/js/common.js"></script>
    
    <script>
    /**
    $.getJSON("${ctx}/manage/order/newOrderCount",function(data){
		$("[id='newTotalCount']").html(data.tcount+data.dcount+data.ccount);
		if(data.tcount>0){$("[id='newTCount']").html(data.tcount);}else{$("[id='newTCount']").parent().parent().parent().hide();$("[id='newTCount']").parent().parent().parent().next().hide();};
		if(data.dcount>0){$("[id='newDCount']").html(data.dcount);}else{$("[id='newDCount']").parent().parent().parent().hide();$("[id='newDCount']").parent().parent().parent().next().hide();};
		if(data.ccount>0){$("[id='newCCount']").html(data.ccount);}else{$("[id='newCCount']").parent().parent().parent().hide();$("[id='newCCount']").parent().parent().parent().next().hide();};
	})
	**/
	function uploadHead(o){
    	var fileName=$(o).val();
    	var suffix=fileName.substring(fileName.lastIndexOf(".")+1);
		if(fileName==""){
			layer.msg('请选择上传的图片', function(){});
	  		return false;
		}
		if(!(suffix=="PNG"||suffix=="png"||suffix=="jpg"||suffix=="JPG"||suffix=="jpeg"||suffix=="JPEG"||suffix=="gif"||suffix=="GIF")){
			layer.msg('图片格式不支持', function(){});
	  		return false;
		}
		var index=layer.load(2);
		var options = {
			url : "${ctx}/upload",
			type : "post",
			dataType: "json",
			success : function(data) {
				layer.close(index); //关闭加载层
				if(data.code=="0"){
					$("#setUp .img-circle").attr("src",data.msg);
				}else{
					opt_error("上传失败");
				}
			}
		};
		$("#uploadForm").ajaxSubmit(options);
		return false;
	}
	
	function setUp(){
		var oriPwd=$("[name='oriPwd']").val();
		var newPwd=$("[name='newPwd']").val();
		var rePwd=$("[name='rePwd']").val();
		if(oriPwd==""){layer.msg('请输入原密码', function(){});return false;}
		if(newPwd==""){layer.msg('请输入新密码', function(){});return false;}
		if(rePwd==""){layer.msg('请输入确认密码', function(){});return false;}
		$.post("${ctx}/manage/member/setUp",{"headImg":$("#setUp .img-circle").attr("src"),"oriPwd":oriPwd,"newPwd":newPwd,"rePwd":rePwd},function(data){
			if(data.code=="0"){
				opt_success_reload(data.msg,"${ctx}/manage/logout");
			}else{
				opt_error(data.msg);
			}
		},"json")
	}
    </script>
</body>

</html>