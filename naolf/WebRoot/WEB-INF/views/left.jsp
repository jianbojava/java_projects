<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<%@ taglib prefix="shiro-ex" uri="http://www.coco-sh.com/tags/shiro"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<script src="${ResStatic }/static/js/jquery-1.8.3.min.js" type="text/javascript"></script> 
<script type="text/javascript">
$(function() {
	$(".sub-menu").each(function() {
		$(this).find("li").each(function(){
			$(this).click(function(){
				removeClass();
				$(this).addClass("active");
				$(this).parent().parent().find("a").append("<span class=\"selected\"></span>");
				$(this).parent().parent().addClass("active");
			})
		})
	})
})
function removeClass(){
	$(".sub-menu").each(function() {
		$(this).parent().find(".selected").remove();
		$(this).parent().removeClass("active");
		$(this).find("li").each(function(){
			$(this).removeClass("active");
		})
	})
}
</script>
<!-- 左侧菜单导航开始 -->
<div class="page-sidebar-fixed">
    <div class="page-sidebar nav-collapse collapse">
      <div class="sidebar-toggler hidden-phone" style="z-index:1001; position:absolute; top:238px; right:-15px;"></div>
      <!--电脑页面的导航效果-->
      <div id="PageNav0" <c:if test="${user.type==1 }">style="display:none;"</c:if>>
        <ul class="page-sidebar-menu hidden-phone hidden-tablet">
          <li class="sidebar-search"></li>
          <shiro-ex:hasAnyPermissions name="manager:petkind:list">
          <li style="display: none" > <a href="javascript:;"> <img src="${ResStatic }/static/frame_left/image/allocationmanage.png" alt="" class="W18m"> <span class="title">宠物管理</span><span class="arrow"></span> </a>
            <ul class="sub-menu">
              <shiro:hasPermission name="manager:petkind:list">
             	 <li > <a href="${ctx }/manager/petkind/list" target="iframepage">宠物分类</a> </li>
              </shiro:hasPermission>
            </ul>
          </li>
          </shiro-ex:hasAnyPermissions>
          <shiro-ex:hasAnyPermissions name="manager:goods:list,manager:goods:add,manager:goods:list:pos">
          <li class="start"> <a href="javascript:;"> <img src="${ResStatic }/static/frame_left/image/commodity-management.png" alt="" class="W18m"> <span class="title WhilteColor">商品管理</span><span class="arrow"></span> </a>
            <ul class="sub-menu">
              <shiro:hasPermission name="manager:goods:add">
              <li> <a href="${ctx }/manager/goods/add" target="iframepage">商品入库</a> </li>
              </shiro:hasPermission>
              <shiro:hasPermission name="manager:goods:storage_list">
              <li> <a href="${ctx }/manager/goods/storage_list" target="iframepage">商品发布</a> </li>
              </shiro:hasPermission>
              <shiro:hasPermission name="manager:goods:list">
              <li> <a href="${ctx }/manager/goods/list" target="iframepage">商品管理</a> </li>
              </shiro:hasPermission>
            </ul>
          </li>
          </shiro-ex:hasAnyPermissions>
          <shiro-ex:hasAnyPermissions name="manager:brand:list,manager:spec:list,manager:goodType:list,manager:goodCat:list,manager:posGoodProp:list,manager:goods:imgcfg">
          <li > <a href="javascript:;"> <img src="${ResStatic }/static/frame_left/image/goodsset.png" alt="" class="W18m"> <span class="title">商品设置</span><span class="arrow"></span> </a>
            <ul class="sub-menu">
              <shiro:hasPermission name="manager:brand:list">
              	<li > <a href="${ctx }/manager/brand/list" target="iframepage">品牌管理</a> </li>
              </shiro:hasPermission>
              <shiro:hasPermission name="manager:spec:list">
              	<li > <a href="${ctx }/manager/spec/list" target="iframepage">规格管理</a> </li>
              </shiro:hasPermission>
              <shiro:hasPermission name="manager:goodType:list">
              	<li > <a href="${ctx }/manager/goodType/list" target="iframepage">类型管理</a> </li>
              </shiro:hasPermission>
              <shiro:hasPermission name="manager:goodCat:list">
              	<li > <a href="${ctx }/manager/goodCat/list" target="iframepage">分类管理</a> </li>
              </shiro:hasPermission>
              <shiro:hasPermission name="manager:goods:imgcfg">
              	<li> <a href="${ctx }/manager/goods/imgcfg" target="iframepage">图片规格</a> </li>
              </shiro:hasPermission>
            </ul>
          </li>
          </shiro-ex:hasAnyPermissions>
          <shiro-ex:hasAnyPermissions name="manager:order:list">
          <li> <a href="javascript:;"> <img src="${ResStatic }/static/frame_left/image/order-management.png" alt="" class="W18m"> <span class="title">订单管理</span><span class="arrow"></span> </a>
            <ul class="sub-menu">
              <li > <a href="${ctx }/manager/order/list/100" target="iframepage">全部订单</a> </li>
              <li > <a href="${ctx }/manager/order/list/1" target="iframepage">待确认订单</a> </li>
              <li > <a href="${ctx }/manager/order/list/2" target="iframepage">待配货订单</a> </li>
              <li > <a href="${ctx }/manager/order/list/4" target="iframepage">待发货订单</a> </li>
              <li style="display: none;"> <a href="${ctx }/manager/order/list/-1" target="iframepage">待退款订单</a> </li>
            </ul>
          </li>
          </shiro-ex:hasAnyPermissions>
          <shiro-ex:hasAnyPermissions name="manager:paylog:list,manager:refundlog:list,manager:deliverylog:list,manager:rtnOrderlog:list">
          <li> <a href="javascript:;"> <img src="${ResStatic }/static/frame_left/image/views-management.png" alt="" class="W18m"> <span class="title">单据管理</span><span class="arrow"></span> </a>
            <ul class="sub-menu">
              <shiro:hasPermission name="manager:paylog:list">
             	 <li> <a href="${ctx }/manager/paylog/list" target="iframepage">收款单据</a> </li>
              </shiro:hasPermission>
               <shiro:hasPermission name="manager:refundlog:list">
              	<li> <a href="${ctx }/manager/refundlog/list" target="iframepage">退款单据</a> </li>
              </shiro:hasPermission>
              <shiro:hasPermission name="manager:deliverylog:list">
              	<li> <a href="${ctx }/manager/deliverylog/list" target="iframepage">发货单据</a> </li>
              </shiro:hasPermission>
              <shiro:hasPermission name="manager:rtnOrderlog:list">
             	 <li> <a href="${ctx }/manager/rtnOrderlog/list" target="iframepage">退货单据</a> </li>
              </shiro:hasPermission>
            </ul>
          </li>
          </shiro-ex:hasAnyPermissions>
          <shiro-ex:hasAnyPermissions name="manager:allocation:list,manager:allocation:cmpl_list">
          <li > <a href="javascript:;"> <img src="${ResStatic }/static/frame_left/image/allocationmanage.png" alt="" class="W18m"> <span class="title">配货管理</span><span class="arrow"></span> </a>
            <ul class="sub-menu">
              <shiro:hasPermission name="manager:allocation:list">
             	 <li > <a href="${ctx }/manager/allocation/list" target="iframepage">待配货管理</a> </li>
              </shiro:hasPermission>
              <shiro:hasPermission name="manager:allocation:cmpl_list">
              	<li > <a href="${ctx }/manager/allocation/cmpl_list" target="iframepage">已配货管理</a> </li>
              </shiro:hasPermission>
            </ul>
          </li>
          </shiro-ex:hasAnyPermissions>
          <shiro-ex:hasAnyPermissions name="manager:stock:list,manager:stockin:list,manager:stockin:pick,manager:shortage:list">
         <li > <a href="javascript:;"><img src="${ResStatic }/static/frame_left/image/stockmanage.png" alt="" class="W18m"> <span class="title">库存管理</span><span class="arrow"></span></a>
          	<ul class="sub-menu">
          	<shiro:hasPermission name="manager:stock:list">
              <li > <a href="${ctx }/manager/stock/list/0" target="iframepage">库存设置</a> </li>
              <li > <a href="${ctx }/manager/stock/list/1" target="iframepage">预警库存</a> </li>
              </shiro:hasPermission>
              <shiro:hasPermission name="manager:stockin:list">
              <li > <a href="${ctx }/manager/stockin/list" target="iframepage">进货管理</a> </li>
              </shiro:hasPermission>
              <shiro:hasPermission name="manager:stockin:pick">
              <li > <a href="${ctx }/manager/stockin/pick" target="iframepage">拣货入库</a> </li>
              </shiro:hasPermission>
              <shiro:hasPermission name="manager:shortage:list">
              <li > <a href="${ctx }/manager/shortage/list" target="iframepage">缺货登记</a> </li>
              </shiro:hasPermission>
            </ul>
          </li>
          </shiro-ex:hasAnyPermissions>
          <shiro-ex:hasAnyPermissions name="manager:coupon:list">
          <li > <a href="javascript:;"> <img src="${ResStatic }/static/frame_left/image/goods-discount.png" alt="" class="W18m"> <span class="title">优惠券管理</span><span class="arrow"></span></a>
          	<ul class="sub-menu">
          	  <shiro:hasPermission name="manager:coupon:list">
              	<li > <a href="${ctx }/manager/coupon/list" target="iframepage">优惠券管理</a> </li>
              </shiro:hasPermission>
              <shiro:hasPermission name="manager:coupon:list">
              	<li > <a href="${ctx }/manager/coupon/list/register" target="iframepage">注册优惠券</a> </li>
              </shiro:hasPermission>
            </ul>
          </li>
          </shiro-ex:hasAnyPermissions>
		  <shiro-ex:hasAnyPermissions name="manager:memberbonus:list">
          <li > <a href="javascript:;"> <img src="${ResStatic }/static/frame_left/image/goods-discount.png" alt="" class="W18m"> <span class="title">红包管理</span><span class="arrow"></span></a>
          	<ul class="sub-menu">
          	  <shiro:hasPermission name="manager:actbonus:list">
              	<li > <a href="${ctx }/manager/actbonus/list?type=0" target="iframepage">红包管理</a> </li>
              	<li > <a href="${ctx }/manager/actbonus/list?type=1" target="iframepage">通用红包管理</a> </li>
              </shiro:hasPermission>
            </ul>
          </li>
          </shiro-ex:hasAnyPermissions>
          <shiro-ex:hasAnyPermissions name="manager:market:activity:list,manager:market:rule:list,manager:market:rule:add,manager:marketgoods:list">
          <li> <a href="javascript:;"> <img src="${ResStatic }/static/frame_left/image/commodity-management.png" alt="" class="W18m"> <span class="title">促销管理</span><span class="arrow"></span> </a>
            <ul class="sub-menu">
              <shiro:hasPermission name="manager:market:activity:list">
              <li > <a href="${ctx }/manager/market/activity/list" target="iframepage">促销活动</a> </li>
              </shiro:hasPermission>
              <shiro:hasPermission name="manager:market:rule:list">
              <li > <a href="${ctx }/manager/market/rule/list" target="iframepage">促销规则</a> </li>
              </shiro:hasPermission>
              <shiro:hasPermission name="manager:market:rule:add">
              <li > <a href="${ctx }/manager/market/rule/add" target="iframepage">添加规则</a> </li>
              </shiro:hasPermission>
              <shiro:hasPermission name="manager:marketgoods:list">
              <li > <a href="${ctx }/manager/marketgoods/list" target="iframepage">促销商品</a> </li>
              </shiro:hasPermission>
            </ul>
          </li>
          </shiro-ex:hasAnyPermissions>
          <shiro-ex:hasAnyPermissions name="manager:logistics:list,manager:logicompany:list,manager:regions:citylist">
          <li > <a href="javascript:;"> <img src="${ResStatic }/static/frame_left/image/logistics-distribution.png" alt="" class="W18m"> <span class="title">物流配送</span><span class="arrow"></span></a>
          	<ul class="sub-menu">
          	  <shiro:hasPermission name="manager:logistics:list">
              	<li > <a href="${ctx }/manager/logistics/list" target="iframepage">配送方式</a> </li>
              </shiro:hasPermission>
              <shiro:hasPermission name="manager:logicompany:list">
              	<li > <a href="${ctx }/manager/logicompany/list" target="iframepage">物流公司</a> </li>
              </shiro:hasPermission>
              <shiro:hasPermission name="manager:regions:citylist">
              	<li > <a href="${ctx }/manager/regions/citylist/logi" target="iframepage">运费管理</a> </li>
              </shiro:hasPermission>
              <shiro:hasPermission name="manager:freighttemplate:list">
              	<li > <a href="${ctx }/manager/freighttemplate/list" target="iframepage">运费模板</a> </li>
              </shiro:hasPermission>
            </ul>
          </li>
          </shiro-ex:hasAnyPermissions>
          <shiro-ex:hasAnyPermissions name="manager:paycfg:list,manager:paycfg:rate">
          <li > <a href="javascript:;"> <img src="${ResStatic }/static/frame_left/image/payment-settings.png" alt="" class="W18m"> <span class="title">支付设置</span><span class="arrow"></span></a>
          	<ul class="sub-menu">
          	  <shiro:hasPermission name="manager:paycfg:list">
              <li > <a href="${ctx }/manager/paycfg/list" target="iframepage">支付方式</a> </li>
              </shiro:hasPermission>
              <shiro:hasPermission name="manager:paycfg:rate">
              <li > <a href="${ctx }/manager/paycfg/rate" target="iframepage">利率设置</a> </li>
              </shiro:hasPermission>
            </ul>
          </li>
          </shiro-ex:hasAnyPermissions>
          <shiro-ex:hasAnyPermissions name="manager:store:list">
          <li > <a href="javascript:;"> <img src="${ResStatic }/static/frame_left/image/storemanage.png" alt="" class="W18m"> <span class="title">门店管理</span><span class="arrow"></span></a>
          	<ul class="sub-menu">
              <li > <a href="${ctx }/manager/store/list" target="iframepage">门店管理</a> </li>
            </ul>
          </li>
          </shiro-ex:hasAnyPermissions>
          <shiro-ex:hasAnyPermissions name="manager:sectiontype:list,manager:section:list">
          <li > <a href="javascript:;"> <img src="${ResStatic }/static/frame_left/image/log-management.png" alt="" class="W18m"> <span class="title">专栏管理</span><span class="arrow"></span></a>
          	<ul class="sub-menu">
              <shiro:hasPermission name="manager:section:list">
              <li > <a href="${ctx }/manager/section/list?differ=-1" target="iframepage">专栏管理</a> </li>
              </shiro:hasPermission>
              <shiro:hasPermission name="manager:sectiontype:list">
              <li > <a href="${ctx }/manager/sectiontype/list" target="iframepage">专栏类型</a> </li>
              </shiro:hasPermission>
            </ul>
          </li>
          </shiro-ex:hasAnyPermissions>
          <shiro-ex:hasAnyPermissions name="manager:section:list">
          <li > <a href="javascript:;"> <img src="${ResStatic }/static/frame_left/image/log-management.png" alt="" class="W18m"> <span class="title">商户专题</span><span class="arrow"></span></a>
          	<ul class="sub-menu">
              <shiro:hasPermission name="manager:section:list">
              <li > <a href="${ctx }/manager/section/list?differ=-2" target="iframepage">商户专题</a> </li>
              </shiro:hasPermission>
            </ul>
          </li>
          </shiro-ex:hasAnyPermissions>
          <shiro-ex:hasAnyPermissions name="manager:activity:list,manager:picture/list">
          <li > <a href="javascript:;"> <img src="${ResStatic }/static/frame_left/image/activitymanage.png" alt="" class="W18m"> <span class="title">活动管理</span><span class="arrow"></span></a>
          	<ul class="sub-menu">
              <li > <a href="${ctx }/manager/activity/list" target="iframepage">活动管理</a> </li>
              <li > <a href="${ctx }/manager/picture/list" target="iframepage">图文管理</a> </li>
            </ul>
          </li>
          </shiro-ex:hasAnyPermissions>
          <shiro-ex:hasAnyPermissions name="manager:returned:list">
          <li> <a href="javascript:;"> <img src="${ResStatic }/static/frame_left/image/activist.png" alt="" class="W18m"> <span class="title">售后服务</span><span class="arrow"></span></a>
          	<ul class="sub-menu">
              <li > <a href="${ctx }/manager/returned/list" target="iframepage">退换货申请</a> </li>
            </ul>
          </li>
          </shiro-ex:hasAnyPermissions> 
          <shiro-ex:hasAnyPermissions name="manager:comment:goodslist">
          <li> <a href="javascript:;"> <img src="${ResStatic }/static/frame_left/image/log-management.png" alt="" class="W18m"> <span class="title">商品评论</span><span class="arrow"></span></a>
          	<ul class="sub-menu">
              <li > <a href="${ctx }/manager/comment/goodslist" target="iframepage">商品评论</a> </li>
              <li > <a href="${ctx }/manager/comment/auditlist" target="iframepage">全部评论</a> </li>
            </ul>
          </li>
          </shiro-ex:hasAnyPermissions>
          <shiro-ex:hasAnyPermissions name="manager:plan:list">
          <li> <a href="javascript:;"> <img src="${ResStatic }/static/frame_left/image/activist.png" alt="" class="W18m"> <span class="title">定制代购</span><span class="arrow"></span></a>
          	<ul class="sub-menu">
              <li > <a href="${ctx }/manager/plan/list" target="iframepage">定制代购</a> </li>
            </ul>
          </li>
          </shiro-ex:hasAnyPermissions>
          <shiro-ex:hasAnyPermissions name="manager:order:statistics">
          <li class="last"> <a href="javascript:;"><img src="${ResStatic }/static/frame_left/image/stockmanage.png" alt="" class="W18m"> <span class="title">数据统计</span><span class="arrow"></span></a>
          	<ul class="sub-menu">
          	  <li> <a href="${ctx }/manager/goods/list/selllist" target="iframepage">商品销售量统计<!-- 畅销TOP10商品--></a> </li>
              <li> <a href="${ctx }/manager/order/statistics/1" target="iframepage">畅销TOP10商品<!-- 畅销TOP10商品--></a> </li>
              <li> <a href="${ctx }/manager/order/statistics/2" target="iframepage">滞销TOP10商品<!-- 滞销TOP10商品--></a> </li>
              <li> <a href="${ctx }/manager/order/statistics/3" target="iframepage">浏览TOP10商品<!-- 浏览TOP10商品--></a> </li>
              <li> <a href="${ctx }/manager/order/statistics/4" target="iframepage">订单成交总额<!-- 订单成交总额--></a> </li>
              <li> <a href="${ctx }/manager/order/statistics/5" target="iframepage">成交/购买用户数<!-- 成交/购买用户数--></a> </li>
              <li> <a href="${ctx }/manager/order/statistics/6" target="iframepage">订单支付率<!-- 订单支付率--></a> </li>
            </ul>
          </li>
          </shiro-ex:hasAnyPermissions>
        </ul>
      </div>
      <div id="PageNav1" style="display:none;">
        <ul class="page-sidebar-menu hidden-phone hidden-tablet">
          <li class="sidebar-search"></li>
          <shiro-ex:hasAnyPermissions name="manager:member:list,manager:pointrole:list,manager:level:list,manager:conusme:list">
          <li class="start"> <a href="javascript:;"> <img src="${ResStatic }/static/frame_left/image/fans-members.png" alt="" class="W18m"> <span class="title WhilteColor">会员管理</span><span class="arrow"></span> </a>
            <ul class="sub-menu">
              <shiro:hasPermission name="manager:member:list">
              <li> <a href="${ctx }/manager/member/list" target="iframepage">会员管理</a> </li>
              </shiro:hasPermission>
              <shiro:hasPermission name="manager:level:list">
              <li><a href="${ctx  }/manager/level/list" target="iframepage">会员等级</a></li>
              </shiro:hasPermission>
              <shiro:hasPermission name="manager:webabout:list:level">
              <li><a href="${ctx  }/manager/webabout/list/level" target="iframepage">等级介绍</a></li>
              </shiro:hasPermission>
              <shiro:hasPermission name="manager:pointrole:list">
              <li><a href="${ctx  }/manager/pointrole/list" target="iframepage">积分等级</a></li>
              </shiro:hasPermission>
              <shiro:hasPermission name="manager:webabout:list:point">
              <li><a href="${ctx  }/manager/webabout/list/point" target="iframepage">积分介绍</a></li>
              </shiro:hasPermission>
              <shiro:hasPermission name="manager:consume:list">
              <li><a href="${ctx  }/manager/consume/list" target="iframepage">会员消费记录</a></li>
              </shiro:hasPermission>
            </ul>
          </li>
          </shiro-ex:hasAnyPermissions>
           <shiro-ex:hasAnyPermissions name="manager:firm:list,manager:regcode:list">
          <li class="start"> <a href="javascript:;"> <img src="${ResStatic }/static/frame_left/image/enterprise-management.png" alt="" class="W18m"> <span class="title WhilteColor">企业会员管理</span><span class="arrow"></span> </a>
            <ul class="sub-menu">
              <shiro:hasPermission name="manager:firm:list">
              <li> <a href="${ctx }/manager/firm/list" target="iframepage">企业会员管理</a> </li>
              </shiro:hasPermission>
              <shiro:hasPermission name="manager:regcode:list">
              <li> <a href="${ctx }/manager/regcode/list" target="iframepage">企业注册码管理</a> </li>
              </shiro:hasPermission>
            </ul>
          </li>
          </shiro-ex:hasAnyPermissions>
          <shiro-ex:hasAnyPermissions name="manager:memberquestion:list">
          <li class="start"> <a href="javascript:;"> <img src="${ResStatic }/static/frame_left/image/views-management.png" alt="" class="W18m"> <span class="title WhilteColor">问答管理</span><span class="arrow"></span> </a>
            <ul class="sub-menu">
              <shiro:hasPermission name="manager:memberquestion:list">
              <li> <a href="${ctx }/manager/memberquestion/list/0" target="iframepage">问答管理</a> </li>
              </shiro:hasPermission>
            </ul>
          </li>
          </shiro-ex:hasAnyPermissions>
          <shiro-ex:hasAnyPermissions name="manager:memberquestion:list">
          <li class="start"> <a href="javascript:;"> <img src="${ResStatic }/static/frame_left/image/material.png" alt="" class="W18m"> <span class="title WhilteColor">萌宠秀管理</span><span class="arrow"></span> </a>
            <ul class="sub-menu">
              <shiro:hasPermission name="manager:memberquestion:list">
              <li> <a href="${ctx }/manager/memberquestion/list/1" target="iframepage">萌宠秀管理</a> </li>
              </shiro:hasPermission>
            </ul>
          </li>
          </shiro-ex:hasAnyPermissions>
          <shiro-ex:hasAnyPermissions name="manager:suggest:list">
          <li class="start"> <a href="javascript:;"> <img src="${ResStatic }/static/frame_left/image/enterprise-management.png" alt="" class="W18m"> <span class="title WhilteColor">用户反馈</span><span class="arrow"></span> </a>
            <ul class="sub-menu">
              <shiro:hasPermission name="manager:memberquestion:list">
              <li> <a href="${ctx }/manager/suggest/list" target="iframepage">用户反馈</a> </li>
              </shiro:hasPermission>
            </ul>
          </li>
          </shiro-ex:hasAnyPermissions>
          <shiro-ex:hasAnyPermissions name="weihuiyuan:none">
	          <li > <a href="javascript:;"> <img src="${ResStatic }/static/frame_left/image/micro-standing-member.png" alt="" class="W18m"> <span class="title">微站会员</span><span class="arrow"></span> </a>
	            <ul class="sub-menu">
	              <li > <a href="javascript:;">APP用户会员</a> </li>
	            </ul>
	          </li>
          </shiro-ex:hasAnyPermissions>
          <shiro-ex:hasAnyPermissions name="huiyuancard:none">
	          <li> <a href="javascript:;"> <img src="${ResStatic }/static/frame_left/image/membership-card-management.png" alt="" class="W18m"> <span class="title">会员卡管理</span><span class="arrow"></span> </a>
	            <ul class="sub-menu">
	              <li > <a href="javascript:;">商家设置</a> </li>
	              <li > <a href="javascript:;">会员卡设置</a> </li>
	              <li><a href="#">会员卡访问设置</a></li>
	            </ul>
	          </li>
          </shiro-ex:hasAnyPermissions>
          <shiro-ex:hasAnyPermissions name="jifen:none">
	          <li> <a href="javascript:;"> <img src="${ResStatic }/static/frame_left/image/points-for.png" alt="" class="W18m"> <span class="title">积分兑换</span><span class="arrow"></span> </a>
	            <ul class="sub-menu">
	              <li > <a href="javascript:;">折扣券</a></li>
	              <li > <a href="javascript:;">真实物品</a> </li>
	              <li><a href="#">代金券</a></li>
	            </ul>
	          </li>
          </shiro-ex:hasAnyPermissions>
          <shiro-ex:hasAnyPermissions name="message:none">
	          <li> <a href="javascript:;"> <img src="${ResStatic }/static/frame_left/image/alerts.png" alt="" class="W18m"> <span class="title">消息通知</span><span class="arrow"></span> </a>
	            <ul class="sub-menu">
	              <li > <a href="javascript:;">APP推送</a> </li>
	              <li > <a href="javascript:;">短信发送</a> </li>
	              <li > <a href="javascript:;">邮件推送</a> </li>
	              <li > <a href="javascript:;">微信推送</a> </li>
	              <li > <a href="javascript:;">接⼝设置</a> </li>
	            </ul>
	          </li>
          </shiro-ex:hasAnyPermissions>
          <shiro-ex:hasAnyPermissions name="defaulttag:none">
	          <li> <a href="javascript:;"> <img src="${ResStatic }/static/frame_left/image/custom-mark.png" alt="" class="W18m"> <span class="title">自定义会员标签分组</span><span class="arrow"></span></a>
	            <ul class="sub-menu">
	              <li><a href="javascript:;">最后购买时间</a> </li>
	              <li><a href="javascript:;">消费习惯</a></li>
	              <li><a href="javascript:;">购买件数</a></li>
	              <li><a href="javascript:;">购买金额</a></li>
	              <li><a href="javascript:;">平均购买折扣</a></li>
	              <li><a href="javascript:;">购买次数</a></li>
	              <li><a href="javascript:;">订单均价</a></li>
	              <li><a href="javascript:;">地区门店会员分布</a></li>
	            </ul>
	          </li>
          </shiro-ex:hasAnyPermissions>
          <shiro-ex:hasAnyPermissions name="pcmember:none">
          	<li> <a href="javascript:;"> <img src="${ResStatic }/static/frame_left/image/pc-management.png" alt="" class="W18m"> <span class="title">PC网站门户会员</span><span class="arrow"></span></a></li>
          </shiro-ex:hasAnyPermissions>
          <shiro-ex:hasAnyPermissions name="memberdata:none">
          	<li> <a href="javascript:;"> <img src="${ResStatic }/static/frame_left/image/database.png" alt="" class="W18m"> <span class="title">会员数据库</span><span class="arrow"></span></a></li>
          </shiro-ex:hasAnyPermissions>
          <shiro-ex:hasAnyPermissions name="member360:none">
          	<li class="last"> <a href="javascript:;"> <img src="${ResStatic }/static/frame_left/image/360vip.png" alt="" class="W18m"> <span class="title">会员360</span><span class="arrow"></span></a> </li>
           </shiro-ex:hasAnyPermissions>
        </ul>
      </div>
      <div id="PageNav2" style="display:none;">
        <ul class="page-sidebar-menu hidden-phone hidden-tablet">
          <li class="sidebar-search"></li>
          <shiro-ex:hasAnyPermissions name="yingxiao:none">
	          <li class="start"> <a href="javascript:;"> <img src="${ResStatic }/static/frame_left/image/marketing management.png" alt="" class="W18m"> <span class="title WhilteColor">营销管理</span><span class="arrow"></span> </a>
	            <ul class="sub-menu">
	              <li> <a href="javascript:;">销售促销</a> </li>
	            </ul>
	          </li>
          </shiro-ex:hasAnyPermissions>
          <shiro-ex:hasAnyPermissions name="manager:articlecate:list">
	          <li > <a href="javascript:;"> <img src="${ResStatic }/static/frame_left/image/article-classification.png" alt="" class="W18m"> <span class="title">文章分类</span><span class="arrow"></span></a>
	         	 <ul class="sub-menu">
	              <li> <a href="${ctx }/manager/articlecate/list" target="iframepage">文章分类</a> </li>
	             </ul>
	          </li>
          </shiro-ex:hasAnyPermissions>
          <shiro-ex:hasAnyPermissions name="manager:article:list">
	          <li > <a href="javascript:;"> <img src="${ResStatic }/static/frame_left/image/query.png" alt="" class="W18m"> <span class="title">文章查询</span><span class="arrow"></span></a>
	          	 <ul class="sub-menu">
	              <li> <a href="${ctx }/manager/article/list" target="iframepage">文章管理</a> </li>
	             </ul>
	          </li>
          </shiro-ex:hasAnyPermissions>
        </ul>
      </div>
      <shiro-ex:hasAnyPermissions name="none:none">
      <div id="PageNav3" style="display:none;">
        <ul class="page-sidebar-menu hidden-phone hidden-tablet">
          <li class="sidebar-search"></li>
          <shiro-ex:hasAnyPermissions name="none:none">
          <li class="start"> <a href="javascript:;"> <img src="${ResStatic }/static/frame_left/image/interactive activities.png" alt="" class="W18m"> <span class="title WhilteColor">互动活动</span><span class="arrow"></span> </a>
            <ul class="sub-menu">
              <li><a href="javascript:;">签到互动</a></li>
              <li><a href="javascript:;">微喜蛋</a></li>
              <li><a href="javascript:;">微喜蛋</a></li>
              <li><a href="javascript:;">2048游戏</a></li>
              <li><a href="javascript:;">大转盘</a></li>
              <li><a href="javascript:;">万能查询</a></li>
              <li><a href="javascript:;">微团购</a></li>
              <li><a href="javascript:;">摇一摇</a></li>
              <li><a href="javascript:;">活动报名</a></li>
              <li><a href="javascript:;">投票</a></li>
            </ul>
          </li>
          </shiro-ex:hasAnyPermissions>
          <shiro-ex:hasAnyPermissions name="none:none">
          <li > <a href="javascript:;"> <img src="${ResStatic }/static/frame_left/image/grouping.png" alt="" class="W18m"> <span class="title">分组群发</span><span class="arrow"></span> </a>
            <ul class="sub-menu">
              <li > <a href="javascript:;">消息管理</a> </li>
              <li > <a href="javascript:;">分组对话信息</a> </li>
              <li><a href="javascript:;">创建分组群发</a></li>
              <li><a href="javascript:;">筛选用户发送</a></li>
              <li><a href="javascript:;">标签分组发送</a></li>
            </ul>
          </li>
          </shiro-ex:hasAnyPermissions>
          <shiro-ex:hasAnyPermissions name="none:none">
          <li class="last"> <a href="javascript:;"> <img src="${ResStatic }/static/frame_left/image/qr-ode.png" alt="" class="W18m"> <span class="title">二维码</span><span class="arrow"></span> </a>
            <ul class="sub-menu">
              <li > <a href="javascript:;">二维码管理</a> </li>
              <li > <a href="javascript:;">生成二维码</a> </li>
              <li><a href="#">二维码统计</a></li>
              <li><a href="#">WIFI营销</a></li>
            </ul>
          </li>
          </shiro-ex:hasAnyPermissions>
        </ul>
      </div>
      </shiro-ex:hasAnyPermissions>
      <div id="PageNav4" style="display:none;">
        <ul class="page-sidebar-menu hidden-phone hidden-tablet">
          <li class="sidebar-search"></li>
          <shiro-ex:hasAnyPermissions name="none:none">
         	 <li class="start"> <a href="javascript:;"> <img src="${ResStatic }/static/frame_left/image/chat-statistics.png" alt="" class="W18m"> <span class="title WhilteColor">聊天统计</span><span class="arrow"></span></a></li>
          </shiro-ex:hasAnyPermissions>
          <shiro-ex:hasAnyPermissions name="none:none">
          	<li > <a href="javascript:;"> <img src="${ResStatic }/static/frame_left/image/use-frequency.png" alt="" class="W18m"> <span class="title">使用频率</span><span class="arrow"></span></a></li>
          </shiro-ex:hasAnyPermissions>
          <shiro-ex:hasAnyPermissions name="none:none">
          	<li > <a href="javascript:;"> <img src="${ResStatic }/static/frame_left/image/activity-statistics.png" alt="" class="W18m"> <span class="title">活动报名统计</span><span class="arrow"></span></a> </li>
          </shiro-ex:hasAnyPermissions>
          <shiro-ex:hasAnyPermissions name="none:none">
          	<li > <a href="javascript:;"> <img src="${ResStatic }/static/frame_left/image/basedonanalysis.png" alt="" class="W18m"> <span class="title">基础分析</span><span class="arrow"></span> </a>
            <ul class="sub-menu">
              <li > <a href="javascript:;">趋势数据</a> </li>
              <li > <a href="javascript:;">来源分析</a> </li>
              <li><a href="javascript:;">行为分析</a></li>
              <li><a href="javascript:;">用户分析</a></li>
            </ul>
          </li>
          </shiro-ex:hasAnyPermissions>
          <shiro-ex:hasAnyPermissions name="none:none">
          	<li class="last"> <a href="javascript:;"> <img src="${ResStatic }/static/frame_left/image/tatistical-analysis.png" alt="" class="W18m"> <span class="title">自定义标签统计分析</span><span class="arrow"></span> </a> </li>
          </shiro-ex:hasAnyPermissions>
        </ul>
      </div>
      <div id="PageNav5" style="display: none;">
        <ul class="page-sidebar-menu hidden-phone hidden-tablet">
          <li class="sidebar-search"></li>
          <shiro-ex:hasAnyPermissions name="manager:company:list">
          <li> <a href="javascript:;"> <img src="${ResStatic }/static/frame_left/image/fans-members.png" alt="" class="W18m"> <span class="title WhilteColor">分销会员</span><span class="arrow"></span> </a>
            <ul class="sub-menu">
             	 <li> <a href="${ctx }/manager/commember/list?level=0" target="iframepage">会员管理</a> </li>
            </ul>
          </li>
          </shiro-ex:hasAnyPermissions>
          <shiro-ex:hasAnyPermissions name="manager:comrate:list">
          <li> <a href="javascript:;"> <img src="${ResStatic }/static/frame_left/image/views-management.png" alt="" class="W18m"> <span class="title WhilteColor">佣金设置</span><span class="arrow"></span> </a>
            <ul class="sub-menu">
             	 <li> <a href="${ctx }/manager/comrate/list" target="iframepage">佣金设置</a> </li>
            </ul>
          </li>
          </shiro-ex:hasAnyPermissions>
        </ul>
      </div>
      <div id="PageNav6" style="display:none;">
        <ul class="page-sidebar-menu hidden-phone hidden-tablet">
          <li style="height:10px; margin-top:18px;"></li>
          <shiro-ex:hasAnyPermissions name="manager:menu:list">
          <li class="start ctive"> <a href="javascript:;"> <img src="${ResStatic }/static/frame_left/image/views-management.png" alt="" class="W18m"><span class="title WhilteColor">菜单管理</span><span class="arrow"></span></a>
            <ul class="sub-menu">
              <li> <a href="${ctx}/manager/menu/list" target="iframepage">菜单管理</a> </li>
             </ul>
          </li>
          </shiro-ex:hasAnyPermissions>
          <shiro:hasPermission name="manager:welcome:index">
          <li> <a href="javascript:;"> <img src="${ResStatic }/static/frame_left/image/enterprise-management.png" alt="" class="W18m"> <span class="title WhilteColor">回复设置</span><span class="arrow"></span> </a>
            <ul class="sub-menu">
             	 <li> <a href="${ctx }/manager/welcome/index" target="iframepage">回复设置</a> </li>
            </ul>
          </li>
          </shiro:hasPermission>
           <shiro-ex:hasAnyPermissions name="manager:material:list">
	          <li class="last"> <a href="javascript:;"> <img src="${ResStatic }/static/frame_left/image/material.png" alt="" class="W18m"> <span class="title">素材管理</span><span class="arrow"></span></a> 
	          	<ul class="sub-menu">
	          	  <shiro:hasPermission name="manager:material:list">
	              	<li> <a href="${ctx }/manager/material/list" target="iframepage">素材管理</a> </li>
	              </shiro:hasPermission>
	            </ul>
	          </li>
          </shiro-ex:hasAnyPermissions>
        </ul>
      </div>
      <div id="PageNav7" style="display: none;">
        <ul class="page-sidebar-menu hidden-phone hidden-tablet">
          <li class="sidebar-search"></li>
          <shiro-ex:hasAnyPermissions name="manager:company:list,manager:welcome:index">
          <li> <a href="javascript:;"> <img src="${ResStatic }/static/frame_left/image/enterprise-management.png" alt="" class="W18m"> <span class="title WhilteColor">企业管理</span><span class="arrow"></span> </a>
            <ul class="sub-menu">
              <shiro:hasPermission name="manager:company:list">
             	 <li> <a href="${ctx }/manager/company/list" target="iframepage">企业管理</a> </li>
              </shiro:hasPermission>
            </ul>
          </li>
          </shiro-ex:hasAnyPermissions>
          <shiro-ex:hasAnyPermissions name="manager:user:list,manager:user:changpwd">
          <li> <a href="javascript:;"> <img src="${ResStatic }/static/frame_left/image/user-management.png" alt="" class="W18m"> <span class="title WhilteColor">用户管理</span><span class="arrow"></span> </a>
            <ul class="sub-menu">
              <shiro:hasPermission name="manager:user:list">
              	<li> <a href="${ctx }/manager/user/list/0" target="iframepage">平台用户</a> </li>
              </shiro:hasPermission>
              <shiro:hasPermission name="manager:user:list">
              	<li> <a href="${ctx }/manager/user/list/1" target="iframepage">商家用户</a> </li>
              </shiro:hasPermission>
            </ul>
          </li>
          </shiro-ex:hasAnyPermissions>
          <shiro-ex:hasAnyPermissions name="manager:role:list,manager:permission:list,manager:dict:list">
          <li > <a href="javascript:;"> <img src="${ResStatic }/static/frame_left/image/role-management.png" alt="" class="W18m"> <span class="title">角色管理</span><span class="arrow"></span></a>
            <ul class="sub-menu">
              <shiro:hasPermission name="manager:permission:list">
              	<li style="display: none;"> <a href="${ctx }/manager/permission/list" target="iframepage">权限管理</a> </li>
              </shiro:hasPermission>
              <shiro:hasPermission name="manager:role:list">
              	<li > <a href="${ctx }/manager/role/list" target="iframepage">角色管理</a> </li>
              </shiro:hasPermission>
              <shiro:hasPermission name="manager:dict:list">
              	<li > <a href="${ctx }/manager/dict/list" target="iframepage">数据字典</a> </li>
              </shiro:hasPermission>
              
            </ul>
          </li>
          </shiro-ex:hasAnyPermissions>
          <shiro-ex:hasAnyPermissions name="manager:organization:list">
          <li style="display: none;"> <a href="javascript:;"> <img src="${ResStatic }/static/frame_left/image/orgmanage.png" alt="" class="W18m"> <span class="title">组织管理</span><span class="arrow"></span></a>
          	<ul class="sub-menu">
              <li> <a href="${ctx }/manager/organization/list" target="iframepage">组织架构管理</a> </li>
            </ul>
          </li>
          </shiro-ex:hasAnyPermissions>
          <shiro-ex:hasAnyPermissions name="manager:log:list">
          <li> <a href="javascript:;"> <img src="${ResStatic }/static/frame_left/image/log-management.png" alt="" class="W18m"> <span class="title">日志管理</span><span class="arrow"></span></a>
          	<ul class="sub-menu">
              <li> <a href="${ctx }/manager/log/list" target="iframepage">日志管理</a> </li>
            </ul>
          </li>
          </shiro-ex:hasAnyPermissions>
          <shiro-ex:hasAnyPermissions name="manager:version:list">
          <li> <a href="javascript:;"> <img src="${ResStatic }/static/frame_left/image/goodsset.png" alt="" class="W18m"> <span class="title">版本管理</span><span class="arrow"></span></a>
          	<ul class="sub-menu">
          	  <shiro:hasPermission name="manager:version:list">
              <li> <a href="${ctx }/manager/version/list" target="iframepage">版本管理</a> </li>
              </shiro:hasPermission>
            </ul>
          </li>
          </shiro-ex:hasAnyPermissions>
          <shiro-ex:hasAnyPermissions name="manager:announcement:list">
          <li> <a href="javascript:;"> <img src="${ResStatic }/static/frame_left/image/activitymanage.png" alt="" class="W18m"> <span class="title WhilteColor">公告管理</span><span class="arrow"></span> </a>
            <ul class="sub-menu">
              <shiro:hasPermission name="manager:announcement:list">
             	 <li> <a href="${ctx }/manager/announcement/list" target="iframepage">公告管理</a> </li>
              </shiro:hasPermission>
            </ul>
          </li>
          </shiro-ex:hasAnyPermissions>
          <shiro-ex:hasAnyPermissions name="manager:push:list">
          <li> <a href="javascript:;"> <img src="${ResStatic }/static/frame_left/image/views-management.png" alt="" class="W18m"> <span class="title WhilteColor">消息推送</span><span class="arrow"></span> </a>
            <ul class="sub-menu">
              <shiro:hasPermission name="manager:push:list">
             	 <li> <a href="${ctx}/manager/push/list" target="iframepage">消息推送</a> </li>
              </shiro:hasPermission>
            </ul>
          </li>
          </shiro-ex:hasAnyPermissions>
          <shiro-ex:hasAnyPermissions name="manager:qrcount:list">
          <li> <a href="javascript:;"> <img src="${ResStatic }/static/frame_left/image/stockmanage.png" alt="" class="W18m"> <span class="title WhilteColor">推广统计</span><span class="arrow"></span> </a>
            <ul class="sub-menu">
              <shiro:hasPermission name="manager:qrcount:list">
             	 <li> <a href="${ctx}/manager/qrcount/list" target="iframepage">推广统计</a> </li>
              </shiro:hasPermission>
            </ul>
          </li>
          </shiro-ex:hasAnyPermissions>
          <shiro-ex:hasAnyPermissions name="manager:website:show">
          <li> <a href="javascript:;"> <img src="${ResStatic }/static/frame_left/image/goodsset.png" alt="" class="W18m"> <span class="title WhilteColor">官网设置</span><span class="arrow"></span> </a>
            <ul class="sub-menu">
              <shiro:hasPermission name="manager:website:show">
             	 <li> <a href="${ctx}/manager/website/show" target="iframepage">官网设置</a> </li>
              </shiro:hasPermission>
            </ul>
          </li>
          </shiro-ex:hasAnyPermissions>
          
        </ul>
      </div>
      <div id="PageNav8" <c:if test="${user.type==0 }">style="display:none;"</c:if>>
        <ul class="page-sidebar-menu hidden-phone hidden-tablet">
          <li style="height:10px; margin-top:18px;"></li>
          <shiro-ex:hasAnyPermissions name="manager:merchant:list,manager:commissionrate:list,manager:regions:citylist">
          <li class="start ctive"> <a href="javascript:;"> <img src="${ResStatic }/static/frame_left/image/commodity-management.png" alt="" class="W18m"><span class="title WhilteColor">商户管理</span><span class="arrow"></span></a>
            <ul class="sub-menu">
              <shiro:hasPermission name="manager:merchant:list">
              <li> <a href="${ctx}/manager/merchant/list" target="iframepage">商户管理</a> </li>
              </shiro:hasPermission>
              <shiro:hasPermission name="manager:commissionrate:list">
              <li> <a href="${ctx}/manager/commissionrate/list" target="iframepage">佣金率设置</a> </li>
              </shiro:hasPermission>
              <shiro:hasPermission name="manager:regions:citylist">
              <li> <a href="${ctx}/manager/regions/citylist" target="iframepage">城市管理</a> </li>
              </shiro:hasPermission>
             </ul>
          </li>
          </shiro-ex:hasAnyPermissions>
          <shiro-ex:hasAnyPermissions name="manager:merchant:detail">
          <li class="start ctive"> <a href="javascript:;"> <img src="${ResStatic }/static/frame_left/image/menu management.png" alt="" class="W18m"><span class="title WhilteColor">商户资料</span><span class="arrow"></span></a>
            <ul class="sub-menu">
              <shiro:hasPermission name="manager:merchant:detail">
              <li> <a href="${ctx}/manager/merchant/detail" target="iframepage">商户资料</a> </li>
              </shiro:hasPermission>
             </ul>
          </li>
          </shiro-ex:hasAnyPermissions>
          <shiro-ex:hasAnyPermissions name="manager:serviceType:list">
          <li class="start ctive"> <a href="javascript:;"> <img src="${ResStatic }/static/frame_left/image/goods-discount.png" alt="" class="W18m"><span class="title WhilteColor">服务类型</span><span class="arrow"></span></a>
            <ul class="sub-menu">
              <li> <a href="${ctx}/manager/serviceType/list" target="iframepage">服务类型</a> </li>
             </ul>
          </li>
          </shiro-ex:hasAnyPermissions>
          
          <shiro-ex:hasAnyPermissions name="manager:service:add,manager:service:list,manager:service:approveList">
          <li class="start ctive"> <a href="javascript:;"> <img src="${ResStatic }/static/frame_left/image/menu management.png" alt="" class="W18m"><span class="title WhilteColor">服务管理</span><span class="arrow"></span></a>
            <ul class="sub-menu">
            	<c:if test="${user.type=='1' }">
            	<shiro-ex:hasAnyPermissions name="manager:service:add">
                <li> <a href="${ctx}/manager/service/add" target="iframepage">发布服务</a> </li>
                </shiro-ex:hasAnyPermissions>
                </c:if>
            	<shiro-ex:hasAnyPermissions name="manager:service:list">
                <li> <a href="${ctx}/manager/service/list" target="iframepage">服务管理</a> </li>
                </shiro-ex:hasAnyPermissions>
                <shiro-ex:hasAnyPermissions name="manager:service:merservice">
                <li> <a href="${ctx}/manager/service/merservice/all" target="iframepage">商户服务</a> </li>
                </shiro-ex:hasAnyPermissions>
                <shiro-ex:hasAnyPermissions name="manager:service:approveList">
                <li> <a href="${ctx}/manager/service/approveList" target="iframepage">服务审核</a> </li>
                </shiro-ex:hasAnyPermissions>
             </ul>
          </li>
          </shiro-ex:hasAnyPermissions>
          
          <shiro-ex:hasAnyPermissions name="manager:reserve:list,manager:reserve:valid">
          <li> <a href="javascript:;"> <img src="${ResStatic }/static/frame_left/image/log-management.png" alt="" class="W18m"><span class="title WhilteColor">预约管理</span><span class="arrow"></span></a>
            <ul class="sub-menu">
            	<shiro-ex:hasAnyPermissions name="manager:reserve:list">
                <li> <a href="${ctx}/manager/reserve/list/all" target="iframepage">全部预约</a> </li>
                </shiro-ex:hasAnyPermissions>
            	<shiro-ex:hasAnyPermissions name="manager:reserve:list">
                <li> <a href="${ctx}/manager/reserve/list/approve" target="iframepage">待审预约</a> </li>
                </shiro-ex:hasAnyPermissions>
                <shiro-ex:hasAnyPermissions name="manager:reserve:list">
                <li> <a href="${ctx}/manager/reserve/list/order" target="iframepage">全部购买</a> </li>
                </shiro-ex:hasAnyPermissions>
                <shiro-ex:hasAnyPermissions name="manager:reserve:valid">
                <li> <a href="${ctx}/manager/reserve/valid/init" target="iframepage">服务验证</a> </li>
                </shiro-ex:hasAnyPermissions>
             </ul>
          </li>
          </shiro-ex:hasAnyPermissions>
          <shiro-ex:hasAnyPermissions name="manager:merchantcomment:list">
          <li> <a href="javascript:;"> <img src="${ResStatic }/static/frame_left/image/views-management.png" alt="" class="W18m"><span class="title">商户评论</span><span class="arrow"></span></a>
          	<ul class="sub-menu">
              <li > <a href="${ctx }/manager/merchantcomment/list" target="iframepage">评论管理</a> </li>
            </ul>
          </li>
          </shiro-ex:hasAnyPermissions>
          <shiro-ex:hasAnyPermissions name="manager:statistic:list,manager:merchant:list,manager:user:salelist">
          <li> <a href="javascript:;"> <img src="${ResStatic }/static/frame_left/image/stockmanage.png" alt="" class="W18m"><span class="title">数据统计</span><span class="arrow"></span></a>
          	<ul class="sub-menu">
          	<c:if test="${user.type==0}">
              <li><a href="${ctx }/manager/statistic/list" target="iframepage">商户列表</a></li>
              <li><a href="${ctx }/manager/user/list/sale" target="iframepage">销售人员</a></li>
              </c:if>
              <c:if test="${user.type==1}">
              	<li><a href="${ctx }/manager/statistic/list/turn?tabs=1&type=1" target="iframepage">查看统计</a> </li>
              </c:if>
            </ul>
          </li>
          </shiro-ex:hasAnyPermissions>
        </ul>
      </div>
      <!--电脑页面上的导航效果--> 
      <!--平板，手机上显示的效果
      <ul class="page-sidebar-menu visible-phone visible-tablet">
        <li>
          <form class="sidebar-search">
          </form>
        </li>
        	<li class="start active"> <a href="javascript:;"> <i class="icon-cogs"></i> <span class="title WhilteColor">菜单管理</span><span class="arrow open"></span> </a>
          <ul class="sub-menu">
            	<li class="active"> <a href="javascript:;">菜单管理</a> </li>
            	<li > <a href="javascript:;">菜单内容展示</a> </li>
          </ul>
        </li>
        <li > <a href="javascript:;"> <i class="icon-th-list"></i> <span class="title">微站建设</span> <span class="arrow "></span> </a>
          <ul class="sub-menu">
            <li > <a href="javascript:;">菜单风格设置</a> </li>
            <li > <a href="javascript:;">微站导航设置</a> </li>
            <li > <a href="javascript:;">快捷菜单设置</a> </li>
          </ul>
        </li>
        <li > <a href="javascript:;"> <i class="icon-map-marker"></i> <span class="title">互动回复</span> <span class="arrow "></span> </a>
          <ul class="sub-menu">
            <li > <a href="javascript:;">自动回复</a> </li>
            <li > <a href="javascript:;">文字回复</a> </li>
            <li><a href="javascript:;">图文回复</a></li>
            <li><a href="javascript:;">音乐回复</a></li>
            <li><a href="javascript:;">自定义接口回复</a></li>
          </ul>
        </li>
        <li class="last"> <a href="javascript:;"> <i class="icon-user"></i> <span class="title">客服服务</span> </a> </li>
      </ul>-->
      <!--平板，手机上显示的效果--> 
    </div>
  </div>
<!-- 左侧菜单导航结束 -->