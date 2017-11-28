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
    <link href="${ctx}/statics/manage/css/plugins/chosen/chosen.css" rel="stylesheet">
    <link href="${ctx}/statics/manage/css/plugins/iCheck/custom.css" rel="stylesheet">
    <link href="${ctx}/statics/manage/js/plugins/fancybox/jquery.fancybox.css" rel="stylesheet">

</head>

<body class="gray-bg">
    <div class="wrapper wrapper-content animated fadeInRight">

            <div class="row">
                <div class="col-sm-12">
                    <div class="ibox float-e-margins">
                        <div class="ibox-title">
                            <h5>车辆管理 > 车辆管理</h5>

                            <div class="ibox-tools">
                                <a class="collapse-link">
                                    <i class="fa fa-chevron-up"></i>
                                </a>
                            </div>
                        </div>
                        <div class="ibox-content">
                        	<div class="ibox-tools" style="float:left;">
                        		<shiro:hasPermission name="manage:car:add">
                                <a class="btn btn-success btn-outline" onclick="layer_show('新增车辆','${ctx}/manage/car/add','','','')"><i class="fa fa-plus"></i> 新增车辆</a>
                                </shiro:hasPermission>
                                <shiro:hasPermission name="manage:car:review">
                                <div class="btn-group">
	                                <a data-toggle="dropdown" class="btn btn-success btn-outline dropdown-toggle"><i class="fa fa-unlock-alt"></i> 批量审核 <span class="caret"></span></a>
	                                <ul class="dropdown-menu">
	                                    <li><a onclick="reviewM(1)">通过</a></li>
	                                    <li><a onclick="reviewM(2)">拒绝</a></li>
	                                </ul>
	                            </div>
	                            </shiro:hasPermission>
	                            <shiro:hasPermission name="manage:car:del">
                                <div class="btn-group">
	                                <a data-toggle="dropdown" class="btn btn-success btn-outline dropdown-toggle"><i class="fa fa-cog"></i> 批量操作 <span class="caret"></span></a>
	                                <ul class="dropdown-menu">
	                                    <li><a onclick="delM(0)">上线</a></li>
	                                    <li><a onclick="delM(1)">下线</a></li>
	                                    <li><a onclick="delM(2)">维修</a></li>
	                                </ul>
	                            </div>
	                            </shiro:hasPermission>
                            </div>
							<form action="${ctx }/manage/car/list" method="post" id="searchForm" class="pull-right" style="width: 545px">
		                        <div class="input-group">
		                            <select data-placeholder="选择审核状态..." style="width:150px;" class="chosen-select" tabindex="2" name="review">
		                            	<option value="">选择审核状态...</option>
		                            	<option value="0">待审核</option>
		                            	<option value="1">已通过</option>
		                            	<option value="2">未通过</option>
		                            </select>
		                            <select data-placeholder="选择状态..." style="width:150px;" class="chosen-select" tabindex="2" name="enabled">
		                            	<option value="">选择状态...</option>
		                            	<option value="0">上线</option>
		                            	<option value="1">下线</option>
		                            	<option value="2">维修</option>
		                            </select>
		                            <input type="text" class="form-control input-sm" name="keywords" style="width:200px;float: right;" value="${vo.keywords}" placeholder="搜索车辆编号、名称等">
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
                                    <th data-toggle="true">编号</th>
                                    <th>名称</th>
                                    <th>车牌号</th>
                                    <th>价格</th>
                                    <th>原价</th>
                                    <th>押金</th>
                                    <th>网点</th>
                                    <th>车位</th>
                                    <th>审核状态</th>
                                    <th>状态</th>
                                    <th data-hide="all">品牌</th>
                                    <th data-hide="all">型号</th>
                                    <th data-hide="all">年份</th>
                                    <th data-hide="all">总里程</th>
                                    <th data-hide="all">续航能力</th>
                                    <th data-hide="all">充电时长</th>
                                    <th data-hide="all">最高车速</th>
                                    <th data-hide="all">长×宽×高(mm)</th>
                                    <th data-hide="all">车型(门/厢/人)</th>
                                    <th data-hide="all">车辆图片</th>
                                    <th data-hide="all">供应商</th>
                                    <th data-hide="all">供应商联系人</th>
                                    <th data-hide="all">供应商电话</th>
                                    <th data-hide="all">签约时间</th>
                                    <th>操作</th>
                                </tr>
                                </thead>
                                <tbody>
                                <c:forEach items="${page.result }" var="c">
                                <tr>
                                	<td><input type="checkbox" class="i-checks" name="input[]" value="${c.id }"></td>
                                	<td>${c.number }</td>
                                    <td>${c.name }</td>
                                    <td>
                                    	<c:choose>
                                    		<c:when test="${empty(c.license) }">-</c:when>
                                    		<c:otherwise>${c.license }</c:otherwise>
                                    	</c:choose>
                                    </td>
                                    <td>${c.price }</td>
                                    <td>${c.mkt_price }</td>
                                    <td>${c.deposit }</td>
                                    <td>
                                    	<c:choose>
                                    		<c:when test="${empty(c.dot_name)}">-</c:when>
                                    		<c:when test="${fn:length(c.dot_name)>5}">
                                    			<span onmouseover="javascript:layer.tips('${c.dot_name}',this,{tips: [1, '#1ab394'],time: 3000});">${fn:substring(c.dot_name,0,5)}<i class="fa fa-info-circle"></i>
                                    		</c:when>
                                    		<c:otherwise>
                                    			${c.dot_name }
                                    		</c:otherwise>
                                    	</c:choose>
                                    </td>
                                    <td>
                                    	<c:choose>
                                    		<c:when test="${empty(c.park_name) }">-</c:when>
                                    		<c:when test="${fn:length(c.park_name)>5}">
                                    			<span onmouseover="javascript:layer.tips('${c.park_name}',this,{tips: [1, '#1ab394'],time: 3000});">${fn:substring(c.park_name,0,5)}<i class="fa fa-info-circle"></i>
                                    		</c:when>
                                    		<c:otherwise>
                                    			${c.park_name }
                                    		</c:otherwise>
                                    	</c:choose>
                                    </td>
                                    <td><c:choose><c:when test="${c.review==0}"><span class="label label-warning">等待审核</span></c:when><c:when test="${c.review==1}"><span class="label label-primary">已通过</span></c:when><c:otherwise><span class="label label-danger">未通过</span></c:otherwise></c:choose></td>
                                    <td><c:choose><c:when test="${c.enabled==0}"><span class="label label-primary">上线</span></c:when><c:when test="${c.enabled==1}"><span class="label label-danger">下线</span></c:when><c:otherwise><span class="label label-warning">维修</span></c:otherwise></c:choose></td>
                                    <td>${c.brand_name }</td>
                                    <td>${c.model_name }</td>
                                    <td><fmt:formatDate value="${c.exfactory_date }" pattern="yyyy-MM"/></td>
                                    <td>${c.total_distance }</td>
                                    <td>${c.battery_distance }</td>
                                    <td>${c.charge_length }</td>
                                    <td>${c.top_speed }</td>
                                    <td>${c.body_size }</td>
                                    <td>${c.body_design }</td>
                                    <td>
                                    	<c:if test="${!empty(c.gallerys) }">
                                    	<a class="fancybox" href="${c.gallerys }" title="车辆照片" data-fancybox-group="gallery-${c.id }">
                            				<img alt="image" src="${c.gallerys }" width="30" height="30"/>
                        				</a>
                        				</c:if>
                                    </td>
                                    <td>${c.sup_company }</td>
                                    <td>${c.sup_person }</td>
                                    <td>${c.sup_mobile }</td>
                                    <td><fmt:formatDate value="${c.sign_date }" pattern="yyyy-MM-dd"/>-<fmt:formatDate value="${c.end_date }" pattern="yyyy-MM-dd"/></td>
                                    <td>
                                    	<shiro:hasPermission name="manage:car:update">
                                    	<a class="btn btn-xs btn-white" onclick="layer_show('修改车辆','${ctx}/manage/car/update/${c.id }','','','')"><i class="fa fa-pencil"></i> 修改</a>
                                    	</shiro:hasPermission>
                                    	<shiro:hasPermission name="manage:car:review">
                                    	<div class="btn-group">
			                                <a data-toggle="dropdown" class="btn btn-xs btn-white dropdown-toggle"><i class="fa fa-unlock-alt"></i> 审核 <span class="caret"></span></a>
			                                <ul class="dropdown-menu">
			                                    <li><a onclick="review(1,'${c.id}')" <c:if test="${c.review==1}">class="font-bold"</c:if>>通过</a></li>
			                                    <li><a onclick="review(2,'${c.id}')" <c:if test="${c.review==2}">class="font-bold"</c:if>>拒绝</a></li>
			                                </ul>
			                            </div>
			                            </shiro:hasPermission>
			                            <shiro:hasPermission name="manage:car:del">
                                    	<div class="btn-group">
			                                <a data-toggle="dropdown" class="btn btn-xs btn-white dropdown-toggle"><i class="fa fa-cog"></i> 操作 <span class="caret"></span></a>
			                                <ul class="dropdown-menu">
			                                    <li><a onclick="del(0,'${c.id}')" <c:if test="${c.enabled==0}">class="font-bold"</c:if>>上线</a></li>
			                                    <li><a onclick="del(1,'${c.id}')" <c:if test="${c.enabled==1}">class="font-bold"</c:if>>下线</a></li>
			                                    <li><a onclick="del(2,'${c.id}')" <c:if test="${c.enabled==2}">class="font-bold"</c:if>>维修</a></li>
			                                </ul>
			                            </div>
			                            </shiro:hasPermission>
			                            <shiro:hasPermission name="manage:car:fdel">
			                            <a class="btn btn-xs btn-white" onclick="del_f('${c.id}')"><i class="fa fa-trash"></i> 删除</a>
                                    	</shiro:hasPermission>
                                    </td>
                                </tr>
                                </c:forEach>
                                </tbody>
                                <tfoot>
                                <tr>
                                    <td colspan="14" id="pagination"></td>
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
    <script src="${ctx}/statics/manage/js/plugins/fancybox/jquery.fancybox.js"></script>
    <script src="${ctx}/statics/manage/js/common.js?v=1.0.0"></script>
    <script>
        $(document).ready(function(){$(".footable").footable();$("#pagination").html('${page.pageContent }');$(".i-checks").iCheck({checkboxClass:"icheckbox_square-green",radioClass:"iradio_square-green"});$('.chosen-select').chosen();pageinit();$(".fancybox").fancybox({openEffect:"none",closeEffect:"none"});});
    	//初始化select控件
       	$("[name='review']").val('${vo.review}');
       	$("[name='enabled']").val('${vo.enabled}');
       	$(".chosen-select").trigger("chosen:updated");
    	//审核
    	function review(flg,id){
    		layer.confirm('确定要操作吗？', function(index){
			  layer.close(index);
			  $.post("${ctx}/manage/car/review/"+flg+"/"+id,function(data){
					if(data.code=="0"){
						opt_success("操作成功");
					}else{
						opt_error("系统繁忙，请稍后再试");
					}
				},"json")
			}); 
    	}
    	function reviewM(flg){
    		if($("[name='input[]']").filter(':checked').length>0){
        		layer.confirm('确定要操作吗？', function(index){
					  layer.close(index);
					  $("[name='input[]']").filter(':checked').each(function(){
						 dels+=","+$(this).attr("value");
					  })
					  $.post("${ctx}/manage/car/review/"+flg+"/"+dels,function(data){
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
    	function del(flg,id){
        	layer.confirm('确定要操作吗？', function(index){
			  layer.close(index);
			  $.post("${ctx}/manage/car/del/"+flg+"/"+id,function(data){
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
					  $.post("${ctx}/manage/car/del/"+flg+"/"+dels.substring(1),function(data){
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
        //删除
        function del_f(id){
        	layer.confirm('确认要删除吗？', function(index){
			  layer.close(index);
			  $.post("${ctx}/manage/car/fdel/"+id,function(data){
				if(data.code=="0"){
					opt_success("删除成功");
				}else{
					opt_error("系统繁忙，请稍后再试");
				}
			  },"json")
			});  
        }
    </script>
</body>

</html>