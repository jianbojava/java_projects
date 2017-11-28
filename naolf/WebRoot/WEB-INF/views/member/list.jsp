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
    <link href="${ctx}/statics/manage/js/plugins/fancybox/jquery.fancybox.css" rel="stylesheet">

</head>

<body class="gray-bg">
    <div class="wrapper wrapper-content animated fadeInRight">

            <div class="row">
                <div class="col-sm-12">
                    <div class="ibox float-e-margins">
                        <div class="ibox-title">
                            <h5>会员管理 > 普通会员</h5>

                            <div class="ibox-tools">
                            	<a class="collapse-link">
                                    <i class="fa fa-chevron-up"></i>
                                </a>
                            </div>
                        </div>
                        <div class="ibox-content">
                        	<div class="ibox-tools" style="float:left;">
                        		<shiro:hasPermission name="manage:member:add">
                                <a class="btn btn-success btn-outline" onclick="layer_show('新增会员','${ctx}/manage/member/add/${vo.type }','','','')"><i class="fa fa-plus"></i> 新增用户</a>
                                </shiro:hasPermission>
                                <shiro:hasPermission name="manage:member:export">
                                <a class="btn btn-success btn-outline" onclick="javascript:location.href='${ctx}/manage/member/export?type=${vo.type }'"><i class="fa fa-file-excel-o"></i> 导出EXCEL</a>
                            	</shiro:hasPermission>
                            </div>
							<form action="${ctx }/manage/member/list" method="post" id="searchForm" class="pull-right" style="width: 585px">
		                        <div class="input-group">
		                            <select data-placeholder="选择审核状态..." style="width:180px;" class="chosen-select" tabindex="2" name="review">
		                            	<option value="">选择审核状态...</option>
		                            	<option value="0">待审核</option>
		                            	<option value="1">已通过</option>
		                            	<option value="2">未通过</option>
		                            </select>
		                            <select data-placeholder="选择状态..." style="width:180px;" class="chosen-select" tabindex="2" name="enabled">
		                            	<option value="">选择状态...</option>
		                            	<option value="0">正常</option>
		                            	<option value="1">无效</option>
		                            </select>
		                            <input type="text" class="form-control input-sm" name="keywords" style="width:180px;float: right;" value="${vo.keywords}" placeholder="搜索手机号、姓名等">
		                            <input type="hidden" name="pageNo" value="${vo.pageNo }"/>
				      				<input type="hidden" name="pageSize" value="${vo.pageSize }">
		                            <div class="input-group-btn">
		                                <button type="button" class="btn btn-sm btn-primary">搜索</button>
		                            </div>
		                        </div>
		                    </form>
		                    <div class="ibox-tools" style="width: 100%;">
                                <shiro:hasPermission name="manage:member:review">
                                <div class="btn-group">
	                                <a data-toggle="dropdown" class="btn btn-success btn-outline dropdown-toggle"><i class="fa fa-unlock-alt"></i> 批量审核 <span class="caret"></span></a>
	                                <ul class="dropdown-menu">
	                                    <li><a onclick="reviewM(1)">通过</a></li>
	                                    <li><a onclick="reviewM(2)">拒绝</a></li>
	                                </ul>
	                            </div>
	                            </shiro:hasPermission>
	                            <shiro:hasPermission name="manage:member:del">
                                <div class="btn-group">
	                                <a data-toggle="dropdown" class="btn btn-success btn-outline dropdown-toggle"><i class="fa fa-cog"></i> 批量操作 <span class="caret"></span></a>
	                                <ul class="dropdown-menu">
	                                    <li><a onclick="delM(0)">正常</a></li>
	                                    <li><a onclick="delM(1)">禁用</a></li>
	                                </ul>
	                            </div>
	                            </shiro:hasPermission>
	                            <shiro:hasPermission name="manage:member:del_flg">
	                            <a class="btn btn-success btn-outline" onclick="del_M()"><i class="fa fa-trash"></i> 批量删除</a>
	                            </shiro:hasPermission>
	                            
	                            <shiro-ex:hasAnyPermissions name="manage:member:bind,manage:member:unbind,manage:member:check,manage:member:change">
	                            <div style="float: right;">
	                            	分时租赁：
	                            	<shiro:hasPermission name="manage:member:check">
		                            <a class="btn btn-success btn-outline" onclick="checkEvcard()"><i class="fa fa-refresh"></i> 检查</a>
		                            </shiro:hasPermission>
		                            <shiro:hasPermission name="manage:member:bind">
		                            <a class="btn btn-success btn-outline" onclick="syncEvcard()"><i class="fa fa-refresh"></i> 同步</a>
		                            </shiro:hasPermission>
		                            <shiro:hasPermission name="manage:member:change">
		                            <a class="btn btn-success btn-outline" onclick="changeEvcard()"><i class="fa fa-refresh"></i> 换卡</a>
		                            </shiro:hasPermission>
		                            <shiro:hasPermission name="manage:member:unbind">
		                            <a class="btn btn-success btn-outline" onclick="removeEvcard()"><i class="fa fa-refresh"></i> 解绑</a>
	                            	</shiro:hasPermission>
	                            </div>
	                            </shiro-ex:hasAnyPermissions>
                            </div>
                            <table class="footable table table-stripped toggle-arrow-tiny" data-sort="false" data-page-size="${vo.pageSize }">
                                <thead>
                                <tr>
                                	<th width="30"><input type="checkbox" class="i-checks" name="input"></th>
                                    <th data-toggle="true">编号</th>
                                    <th>手机</th>
                                    <th>姓名</th>
                                    <th>保证金</th>
                                    <th>余额</th>
                                    <th>积分</th>
                                    <th>销售员</th>
                                    <th>审核状态</th>
                                    <th>状态</th>
                                    <th>Evcard状态</th>
                                    <th data-hide="all">会员卡号</th>
                                    <th data-hide="all">身份证</th>
                                    <th data-hide="all">身份证照</th>
                                    <th data-hide="all">驾驶证</th>
                                    <th data-hide="all">驾驶证照</th>
                                    <th data-hide="all">收货地址</th>
                                    <th data-hide="all">注册时间</th>
                                    <th>操作</th>
                                </tr>
                                </thead>
                                <tbody>
                                <c:forEach items="${page.result }" var="m">
                                <tr>
                                	<td><input type="checkbox" class="i-checks" name="input[]" value="${m.id }"></td>
                                	<td>${m.number}</td>
                                	<td>${m.mobile }</td>
                                    <td>
                                    	<c:choose>
                                    		<c:when test="${empty(m.name) }">-</c:when>
                                    		<c:otherwise>${m.name }</c:otherwise>
                                    	</c:choose>
                                    </td>
                                    <td>${m.deposit }</td>
                                    <td>${m.account }</td>
                                    <td>${m.point }</td>
                                    <td>
                                    	<c:choose>
                                    		<c:when test="${empty(m.sale_id) }">-</c:when>
                                    		<c:otherwise>${m.sale_name }</c:otherwise>
                                    	</c:choose>
                                    </td>
                                    <td><c:choose><c:when test="${m.review==0}"><span class="label label-warning">等待审核</span></c:when><c:when test="${m.review==1}"><span class="label label-primary">已通过</span></c:when><c:otherwise><span class="label label-danger">未通过</span></c:otherwise></c:choose></td>
                                    <td><c:choose><c:when test="${m.enabled==0}"><span class="label label-primary">正常</span></c:when><c:otherwise><span class="label label-danger">禁用</span></c:otherwise></c:choose></td>
                                    <td>
                                    	<c:choose>
                                    		<c:when test="${m.evcard_status==0 }">未同步</c:when>
                                    		<c:when test="${m.evcard_status==1 }">已存在</c:when>
                                    		<c:when test="${m.evcard_status==2 }">待审核</c:when>
                                    		<c:when test="${m.evcard_status==3 }">审核不通过</c:when>
                                    		<c:otherwise>用户无效</c:otherwise>
                                    	</c:choose>
                                    </td>
                                    <td>${m.card_number }</td>
                                    <td>${m.card_no }</td>
                                    <td>
                                    	<c:if test="${!empty(m.card_front) }">
                                    	<a class="fancybox" href="${m.card_front }" title="身份证正面照" data-fancybox-group="gallery-${m.id }">
                            				<img alt="image" src="${m.card_front }" width="30" height="30"/>
                        				</a>
                        				</c:if>
                        				<c:if test="${!empty(m.card_back) }">
                        				<a class="fancybox" href="${m.card_back }" title="身份证背面照" data-fancybox-group="gallery-${m.id }">
                            				<img alt="image" src="${m.card_back }" width="30" height="30"/>
                        				</a>
                        				</c:if>
                        			</td>
                                    <td>${m.driver_no }</td>
                                    <td>
                                    	<c:if test="${!empty(m.driver_scan) }">
                                    	<a class="fancybox" href="${m.driver_scan }" title="驾驶证正面照" data-fancybox-group="gallery-${m.id }">
                            				<img alt="image" src="${m.driver_scan }" width="30" height="30"/>
                        				</a>
                        				</c:if>
                        				<c:if test="${!empty(m.driver_scan_back) }">
                                    	<a class="fancybox" href="${m.driver_scan_back }" title="驾驶证背面照" data-fancybox-group="gallery-${m.id }">
                            				<img alt="image" src="${m.driver_scan_back }" width="30" height="30"/>
                        				</a>
                        				</c:if>
                        			</td>
                                    <td>${m.ship_addr }</td>
                                    <td><fmt:formatDate value="${m.create_date}" pattern="yyyy-MM-dd　HH:mm:ss" /></td>
                                    <td>
                                    	<shiro:hasPermission name="manage:member:update">
                                    	<a class="btn btn-xs btn-white" onclick="layer_show('修改用户','${ctx}/manage/member/update/${m.id }','','','')"><i class="fa fa-pencil"></i> 修改</a>
                                    	</shiro:hasPermission>
                                    	<shiro:hasPermission name="manage:member:recharge">
                                    	<a class="btn btn-xs btn-white" onclick="layer_show('账户充值','${ctx}/manage/member/recharge/${m.id}','','','')"><i class="fa fa-money"></i> 充值</a>
                                    	</shiro:hasPermission>
                                    	<shiro:hasPermission name="manage:member:review">
                                    	<div class="btn-group">
			                                <a data-toggle="dropdown" class="btn btn-xs btn-white dropdown-toggle"><i class="fa fa-unlock-alt"></i> 审核 <span class="caret"></span></a>
			                                <ul class="dropdown-menu">
			                                    <li><a onclick="review(1,'${m.id}')" <c:if test="${m.review==1}">class="font-bold"</c:if>>通过</a></li>
			                                    <li><a onclick="review(2,'${m.id}')" <c:if test="${m.review==2}">class="font-bold"</c:if>>拒绝</a></li>
			                                </ul>
			                            </div>
			                            </shiro:hasPermission>
			                            <shiro:hasPermission name="manage:member:del">
                                    	<div class="btn-group">
			                                <a data-toggle="dropdown" class="btn btn-xs btn-white dropdown-toggle"><i class="fa fa-cog"></i> 操作 <span class="caret"></span></a>
			                                <ul class="dropdown-menu">
			                                    <li><a onclick="del(0,'${m.id}')" <c:if test="${m.enabled==0}">class="font-bold"</c:if>>正常</a></li>
			                                    <li><a onclick="del(1,'${m.id}')" <c:if test="${m.enabled==1}">class="font-bold"</c:if>>禁用</a></li>
			                                </ul>
			                            </div>
			                            </shiro:hasPermission>
			                            <shiro:hasPermission name="manage:member:del_flg">
			                            <a class="btn btn-xs btn-white" onclick="del_f('${m.id}')"><i class="fa fa-trash"></i> 删除</a>
                                    	</shiro:hasPermission>
                                    </td>
                                </tr>
                                </c:forEach>
                                </tbody>
                                <tfoot>
                                <tr>
                                    <td colspan="12" id="pagination"></td>
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
    <script src="${ctx}/statics/manage/js/jquery.form.js"></script>
    <script src="${ctx}/statics/manage/js/common.js"></script>
    <script>
        $(document).ready(function(){$(".footable").footable();$("#pagination").html('${page.pageContent }');$(".i-checks").iCheck({checkboxClass:"icheckbox_square-green",radioClass:"iradio_square-green"});$(".fancybox").fancybox({openEffect:"none",closeEffect:"none"});$('.chosen-select').chosen();pageinit();});
    	//初始化select控件
       	$("[name='review']").val('${vo.review}');
       	$("[name='enabled']").val('${vo.enabled}');
       	$(".chosen-select").trigger("chosen:updated");
    	//审核
    	function review(flg,id){
    		layer.confirm('确定要操作吗？', function(index){
			  layer.close(index);
			  $.post("${ctx}/manage/member/review/"+flg+"/"+id,function(data){
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
					  $.post("${ctx}/manage/member/review/"+flg+"/"+dels,function(data){
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
        	layer.confirm('确认要删除吗？', function(index){
			  layer.close(index);
			  $.post("${ctx}/manage/member/del/"+flg+"/"+id,function(data){
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
					  $.post("${ctx}/manage/member/del/"+flg+"/"+dels.substring(1),function(data){
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
        
        function del_f(id){
        	layer.confirm('确认要删除吗？', function(index){
			  layer.close(index);
			  $.post("${ctx}/manage/member/del_flg/"+id,function(data){
				if(data.code=="0"){
					opt_success("删除成功");
				}else{
					opt_error("系统繁忙，请稍后再试");
				}
			  },"json")
			});  
        }
        
        function del_M(){
        	if($("[name='input[]']").filter(':checked').length>0){
        		layer.confirm('确定要删除吗？', function(index){
					  layer.close(index);
					  $("[name='input[]']").filter(':checked').each(function(){
						 dels+=","+$(this).attr("value");
					  })
					  $.post("${ctx}/manage/member/del_flg/"+dels,function(data){
						if(data.code=="0"){
							opt_success("删除成功");
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
        
        function syncEvcard(){
        	dels="";
			if($("[name='input[]']").filter(':checked').length>0){
				layer.confirm('确认要同步吗？', function(index){
					layer.close(index);
					var index = layer.load(1); //换了种风格	
	        		$("[name='input[]']").filter(':checked').each(function(){
					 	dels+=","+$(this).attr("value");
				  	})
				  	$.post("${ctx}/manage/member/syncEvcard/"+dels.substring(1),function(data){
					  	layer.close(index);
						if(data.code=="0"){
							layer.open({
							  type: 1,
							  title:'同步结果',
							  skin: 'layui-layer-rim', //加上边框
							  area: ['420px', '240px'], //宽高
							  content: '<div style="padding:20px;">'+data.msg+'</div>'
							});
						}else{
							opt_error(data.msg);
						}
				  	},"json")
				});
        	}else{
        		layer.msg("请选择操作项", {
					icon : 2
				});
        	} 
        }
        
        function removeEvcard(){
        	dels="";
			if($("[name='input[]']").filter(':checked').length>0){
				layer.confirm('确认要解绑吗？', function(index){
					layer.close(index);
					var index = layer.load(1); //换了种风格	
	        		$("[name='input[]']").filter(':checked').each(function(){
					 	dels+=","+$(this).attr("value");
				  	})
				  	$.post("${ctx}/manage/member/removeEvcard/"+dels.substring(1),function(data){
					  	layer.close(index);
						if(data.code=="0"){
							layer.open({
							  type: 1,
							  title:'解绑结果',
							  skin: 'layui-layer-rim', //加上边框
							  area: ['420px', '240px'], //宽高
							  content: '<div style="padding:20px;">'+data.msg+'</div>'
							});
						}else{
							opt_error(data.msg);
						}
				  	},"json")
				});
        	}else{
        		layer.msg("请选择操作项", {
					icon : 2
				});
        	}  
        }
        
        function checkEvcard(){
        	dels="";
			if($("[name='input[]']").filter(':checked').length>0){
				var index = layer.load(1); //换了种风格	
        		$("[name='input[]']").filter(':checked').each(function(){
				 	dels+=","+$(this).attr("value");
			  	})
			  	$.post("${ctx}/manage/member/checkEvcard/"+dels.substring(1),function(data){
				  	layer.close(index);
					if(data.code=="0"){
						layer.open({
						  type: 1,
						  title:'检查结果',
						  skin: 'layui-layer-rim', //加上边框
						  area: ['420px', '240px'], //宽高
						  content: '<div style="padding:20px;">'+data.msg+'</div>'
						});
					}else{
						opt_error("系统繁忙，请稍后再试");
					}
			  	},"json")
        	}else{
        		layer.msg("请选择操作项", {
					icon : 2
				});
        	} 
        }
        
        function changeEvcard(){
        	dels="";
			if($("[name='input[]']").filter(':checked').length>0){
				layer.confirm('确认要换卡吗？', function(index){
					layer.close(index);
					var index = layer.load(1); //换了种风格	
	        		$("[name='input[]']").filter(':checked').each(function(){
					 	dels+=","+$(this).attr("value");
				  	})
				  	$.post("${ctx}/manage/member/changeEvcard/"+dels.substring(1),function(data){
					  	layer.close(index);
						if(data.code=="0"){
							layer.open({
							  type: 1,
							  title:'换卡结果',
							  skin: 'layui-layer-rim', //加上边框
							  area: ['420px', '240px'], //宽高
							  content: '<div style="padding:20px;">'+data.msg+'</div>'
							});
						}else{
							opt_error(data.msg);
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