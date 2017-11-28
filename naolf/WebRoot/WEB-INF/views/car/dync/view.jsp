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
    <link href="${ctx}/statics/manage/css/animate.min.css" rel="stylesheet">
    <link href="${ctx}/statics/manage/css/style.min.css?v=4.0.0" rel="stylesheet">
    <link href="${ctx}/statics/manage/css/plugins/switchery/switchery.css" rel="stylesheet">
    <link href="${ctx}/statics/manage/css/plugins/iCheck/custom.css" rel="stylesheet">
    <link href="${ctx}/statics/manage/css/plugins/sweetalert/sweetalert.css" rel="stylesheet">
    <link href="${ctx}/statics/manage/plugins/uploadify/uploadify.css" rel="stylesheet">
    <link href="${ctx}/statics/manage/js/plugins/fancybox/jquery.fancybox.css" rel="stylesheet">
    
</head>

<body>
    	<div class="row animated fadeInRight">
            <div class="col-sm-12">
                <div class="ibox">
                    <div class="ibox-content">
                        <div class="row m-b-sm m-t-sm">
                            <div class="col-md-1">
                                <button type="button" id="loading-example-btn" class="btn btn-white btn-sm" onclick="javascript:window.location.reload();"><i class="fa fa-refresh"></i> 刷新</button>
                            </div>
                            <div class="col-md-11">
                            	<form action="${ctx }/manage/car/dyncDetail" method="post" id="searchForm">
                                <div class="input-group">
                                    <input type="text" name="keywords" value="${vo.keywords }" placeholder="订单编号" class="input-sm form-control">
                                    <input type="hidden" name="id" value="${vo.id }">
                                    <input type="hidden" name="pageNo" value="${vo.pageNo }"/>
				      				<input type="hidden" name="pageSize" value="${vo.pageSize }">
                                    <span class="input-group-btn"><button type="button" class="btn btn-sm btn-primary"> 搜索</button> </span>
                                </div>
                                </form>
                            </div>
                        </div>

                        <div class="project-list">

                            <table class="table table-hover">
                            	<thead>
                                <tr>
                                    <th>订单状态</th>
                                    <th>订单编号</th>
                                    <th>租赁用户</th>
                                    <th>租赁时间</th>
                                    <th>订单备注</th>
                                </tr>
                                </thead>
                                <tbody>
                                	<c:forEach items="${page.result }" var="o">
                                    <tr>
                                        <td>
                                        	<c:choose>
                                        		<c:when test="${o.status!=-1 }"><span class="label label-primary">${o.order_status }</span></c:when>
                                        		<c:otherwise><span class="label label-default">${o.order_status }</span></c:otherwise>
                                        	</c:choose>
                                        </td>
                                        <td>
                                            ${o.sn }
                                        </td>
                                        <td>
                                            ${o.mem_name }&nbsp;&nbsp;&nbsp;${o.mem_mobile }
                                            <br/>
                                            <small>创建于 <fmt:formatDate value="${o.create_date }" pattern="yyyy-MM-dd HH:mm:ss"/></small>
                                        </td>
                                        <td>
                                            <fmt:formatDate value="${o.rent_star_time }" pattern="yyyy-MM-dd HH:mm"/>--<fmt:formatDate value="${o.rent_end_time }" pattern="yyyy-MM-dd HH:mm"/>
                                        </td>
                                        <td>
                                        	<c:choose>
                                        		<c:when test="${empty(o.remark)}">-</c:when>
                                        		<c:otherwise>${o.remark }</c:otherwise>
                                        	</c:choose>
                                        </td>
                                    </tr>
                                    </c:forEach>
                                   </tbody>
                                </table>
                            </div>
                            <button class="btn btn-primary btn-block m" onclick="loadMore(this)"><i class="fa fa-arrow-down"></i> 显示更多</button>
                        </div>
                    </div>
            </div>
        </div>
        
        
        <div id="modal-map" class="modal fade" aria-hidden="true">
	        <div class="modal-dialog">
	            <div class="modal-content">
	                <div class="modal-body">
	                    <div class="row">
	                        <div class="col-sm-12" id="allmap" style="height: 400px">
	                        </div>
	                    </div>
	                </div>
	            </div>
	        </div>
	    </div>

    <script src="${ctx}/statics/manage/js/jquery.min.js?v=2.1.4"></script>
    <script src="${ctx}/statics/manage/js/bootstrap.min.js?v=3.3.6"></script>
    <script src="${ctx}/statics/manage/js/content.min.js?v=1.0.0"></script>
    <script src="${ctx}/statics/manage/js/plugins/switchery/switchery.js"></script>
    <script src="${ctx}/statics/manage/js/plugins/iCheck/icheck.min.js"></script>
    <script src="${ctx}/statics/manage/plugins/layer/layer.js"></script>
    <script src="${ctx}/statics/manage/js/plugins/sweetalert/sweetalert.min.js"></script>
    <script src="${ctx}/statics/manage/plugins/uploadify/jquery.uploadify.js"></script>
    <script src="${ctx}/statics/manage/js/plugins/fancybox/jquery.fancybox.js"></script>
    <script src="${ctx}/statics/manage/js/jquery.form.js"></script>
    
    <script>
       $(document).ready(function(){$("#loading-example-btn").click(function(){btn=$(this);simpleLoad(btn,true);simpleLoad(btn,false)})});function simpleLoad(btn,state){if(state){btn.children().addClass("fa-spin");btn.contents().last().replaceWith(" Loading")}else{setTimeout(function(){btn.children().removeClass("fa-spin");btn.contents().last().replaceWith(" Refresh")},2000)}};
        $(function(){
        	$(".input-group button").bind("click", function() {
				$("input[name='pageNo']").val(1);
				$("#searchForm").submit();
			})
        })
        function loadMore(obj){
        	$(obj).attr("disabled","disabled");
        	$("input[name='pageNo']").val(Number($("input[name='pageNo']").val())+1);
        	layer.msg('加载中', {icon: 16});
        	var options = {
				url : "${ctx }/manage/car/dyncAjax",
				type : "post",
				dataType: "json",
				success : function(data) {
					layer.closeAll();
					$(obj).removeAttr("disabled");
					if(data.length==0){
						layer.msg('没有更多数据了', {icon: 5});
					}
					var dyncs="";
					for(var i=0;i<data.length;i++){
						var o=data[i];
						dyncs+="<tr>"+
                                        "<td>";
                                        
                                        if(o.status!=-1){
                                        	dyncs+="<span class=\"label label-primary\">"+o.order_status+"</span>";
                                        }else{
                                        	dyncs+="<span class=\"label label-default\">"+o.order_status+"</span>";
                                        }
                                        
                                dyncs+="</td>"+
                                        "<td>"+o.sn+"</td>"+
                                        "<td>"+
                                              o.mem_name+"&nbsp;&nbsp;&nbsp;"+o.mem_mobile+
                                            "<br/>"+
                                            "<small>创建于 "+new Date(o.create_date).format('yyyy-MM-dd hh:mm')+"</small>"+
                                        "</td>"+
                                        "<td>"+
                                            new Date(o.rent_star_time).format('yyyy-MM-dd hh:mm')+"--"+new Date(o.rent_end_time).format('yyyy-MM-dd hh:mm')+
                                        "</td>"+
                                        "<td>";
                                        	if(o.remark!=null&&o.remark!=''){
                                        		dyncs+=o.remark;
                                        	}else{
                                        		dyncs+="-";
                                        	}
                                 dyncs+="</td>"+
                                    "</tr>";
					}
					$("tbody").append(dyncs);
				}
			};
			$("#searchForm").ajaxSubmit(options);
			return false;
        }
    </script>
</body>

</html>