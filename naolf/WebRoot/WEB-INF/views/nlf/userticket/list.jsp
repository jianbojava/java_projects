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
    <link href="${ctx}/statics/manage/css/plugins/chosen/chosen.css" rel="stylesheet">

</head>

<body class="gray-bg">
    <div class="wrapper wrapper-content animated fadeInRight">

            <div class="row">
                <div class="col-sm-12">
                    <div class="ibox float-e-margins">
                        <div class="ibox-title">
                            <h5>课程管理 > 卡券管理</h5>
                            <div class="ibox-tools">
                                <a class="collapse-link">
                                    <i class="fa fa-chevron-up"></i>
                                </a>
                            </div>
                        </div>
                        <div class="ibox-content">
                        	<div class="ibox-tools" style="float:left;">
                        		<shiro:hasPermission name="manage:recharge:add">
                                <a class="btn btn-success btn-outline" onclick="layer_show('充值卡券','${ctx}/manage/recharge/add','','','')"> 充值卡券</a>
                                </shiro:hasPermission>
                        		<shiro:hasPermission name="manage:recharge:add">
                                <a class="btn btn-success btn-outline" onclick="layer_show('充值退款','${ctx}/manage/recharge/rcgreturn','','','')"> 充值退款</a>
                                </shiro:hasPermission>
                            </div>
							<form action="${ctx }/manage/userticket/list" method="post" id="searchForm" class="pull-right" target='_self' style="width: 580px">
		                        <div class="input-group">
		                         <select data-placeholder="选择类型..." style="width:150px;" class="chosen-select" tabindex="2" name="ticket_type">
			                            	<option value="">选择状态...</option>
			                            	<option value="0">卡</option>
			                            	<option value="1">券</option>
			                      </select>
			                       <select data-placeholder="使用状态..." style="width:150px;" class="chosen-select" tabindex="2" name="used">
			                            	<option value="">选择使用状态...</option>
			                            	<option value="0">未使用</option>
			                            	<option value="1">已使用</option>
			                            	<option value="2">已作废</option>
			                      </select>
		                            <input type="text" class="form-control input-sm" name="keywords" value="${vo.keywords}" style="float:right;width: 200px" placeholder="搜索编号，持有者ID，购买者ID">
		                            <input type="hidden" name="pageNo" value="${vo.pageNo }"/>
				      				<input type="hidden" name="pageSize" value="${vo.pageSize }">
				      				<input type="hidden" name="conn_id" value="${vo.conn_id }">
				      				<input type="hidden" name="recharge_id" value="${vo.recharge_id}">
		                            <div class="input-group-btn">
		                                <button type="button" class="btn btn-sm btn-primary">搜索</button>
		                            </div>
		                        </div>
		                    </form>
                            <table class="footable table table-stripped toggle-arrow-tiny" data-sort="false" data-page-size="${vo.pageSize }">
                                <thead>
                                <tr>
                                	<th width="30"><input type="hidden" class="i-checks" name="input"></th>
                                    <th data-toggle="true">编号</th>
                                    <th>名称</th>
                                    <th>类型</th>
                                    <th>持有者编号</th>
                                     <th>状态</th>
                                    <th>购买者姓名</th>
                                    <th>购买者编号</th>
                                    <th>使用时间</th>
                                    <th>线下卡号</th>
                                    <th>操作</th>
                                </tr>
                                </thead>
                                <tbody>
                                <c:forEach items="${page.result }" var="userticket">
                                <tr>
                                	<th><input type="hidden" class="i-checks" name="input[]" value="${userticket.id}"></th>
                                	<td>${userticket.sn }</td>
                                	<td>${userticket.name}</td>
                                	<th><c:if test="${userticket.ticket_type==0 }">卡</c:if><c:if test="${userticket.ticket_type==1 }">券</c:if></th>
                                	<td>${userticket.number }</td>
                                	<td>
                                	    <c:if test="${userticket.used==0 }">未使用</c:if>
                                	    <c:if test="${userticket.used==1 }">已使用</c:if>
                                	    <c:if test="${userticket.used==2 }">已作废</c:if>
                                	</td>
                                	<td>${userticket.user_name}</td>
                                	<td>${userticket.used_number}</td>
                                	<td><fmt:formatDate value="${userticket.used_date }" pattern="yyyy-MM-dd HH:mm:ss"/></td>
                                    <td>${userticket.line_sn }</td>
                                    <td>
                                    	<c:if test="${userticket.line_sn==null}">
	                                    	<shiro:hasPermission name="manage:userticket:update">
	                                    	<button class="btn btn-xs btn-white" onclick="updateSn('${userticket.id}')"><i class="fa fa-pencil"></i> 绑定线下号码</button>
	                                    	</shiro:hasPermission>
                                    	</c:if>
                                    	<c:if test="${userticket.upgrade==2 }">
	                                    	<shiro:hasPermission name="manage:upgrade:list">
	                                    	  <a class="btn btn-xs btn-white" onclick="layer_show('升级详情','${ctx}/manage/upgrade/list?id=${userticket.id}','','','')"><i class="fa fa-eye"></i> 升级详情</a>
                                            </shiro:hasPermission>
                                    	</c:if>
                                    	<c:if test="${userticket.upgrade==1 }">
	                                    	<shiro:hasPermission name="manage:upgrade:list">
	                                    	  <a class="btn btn-xs btn-white" onclick="layer_show('已升级详情','${ctx}/manage/upgrade/list?id=${userticket.id}','','','')"><i class="fa fa-eye"></i> 已升级详情</a>
                                            </shiro:hasPermission>
                                    	</c:if>
                                    	
                                    	<c:if test="${userticket.used==0}">
	                                    	<shiro:hasPermission name="manage:userticket:update">
	                                    	<button class="btn btn-xs btn-white" onclick="updateUsed('${userticket.id}')"><i class="fa fa-pencil"></i> 作废</button>
	                                    	</shiro:hasPermission>
                                    	</c:if>
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
        $(document).ready(function(){$(".footable").footable();$("#pagination").html('${page.pageContent }');$(".i-checks").iCheck({checkboxClass:"icheckbox_square-green",radioClass:"iradio_square-green"});pageinit();});
        $(document).ready(function(){
	    	$("[name='ticket_type']").val('${vo.ticket_type}');
	    	$("[name='used']").val('${vo.used}');
	        $(".chosen-select").trigger("chosen:updated");
	        $('.chosen-select').chosen();
       });

        function updateSn(sn){
        	//prompt层
			layer.prompt({title: '请输入线下卡券号码，并确认。', formType: 2}, function(text){
			    $.post("${ctx}/manage/userticket/update",{"line_sn":text,"id":sn},function(data){
					if(data.code=="0"){
						opt_success("操作成功");
					}else{
						opt_error("系统繁忙，请稍后再试");
					}
				  },"json")
			});
        }
       function updateUsed(id){//作废
        	layer.confirm('确定要作废吗？', function(index){
  			  layer.close(index);
  			  $.post("${ctx}/manage/userticket/update",{"id":id,"used":2},function(data){
  				if(data.code=="0"){
  					opt_success("操作成功");
  				}else{
  					opt_error("系统繁忙，请稍后再试");
  				}
  			  },"json")
  			});  
          }
    </script>
</body>

</html>