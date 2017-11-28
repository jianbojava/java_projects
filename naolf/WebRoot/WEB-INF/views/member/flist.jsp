<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<%@ taglib prefix="shiro-ex" uri="http://www.coco-sh.com/tags/shiro" %>
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
                            <h5>会员管理 > 企业会员</h5>

                            <div class="ibox-tools">
                            	<a href="javascript:;" onclick="javascript:location.href='${ctx}/statics/files/import_template.xls'" class="btn btn-primary btn-xs"><i class="fa fa-cloud-download"></i> 模板下载</a>
                            	<a class="collapse-link">
                                    <i class="fa fa-chevron-up"></i>
                                </a>
                            </div>
                        </div>
                        <div class="ibox-content">
                        	<div class="ibox-tools" style="float:left;">
                        		<shiro:hasPermission name="manage:member:fadd">
                                <a class="btn btn-success btn-outline" onclick="layer_show('新增会员','${ctx}/manage/member/add/${vo.type }','','','')"><i class="fa fa-plus"></i> 新增用户</a>
                                </shiro:hasPermission>
                                <shiro:hasPermission name="manage:member:import">
                                <a class="btn btn-success btn-outline" onclick="javascript:$(this).next().find('input').click();"><i class="fa fa-file-excel-o"></i> 导入EXCEL</a>
                                <form id="importForm" enctype="multipart/form-data" method="post" style="display: none;">
                                	<input type="file" name="file" onchange="uploadExcel(this)"/>
                                </form>
                                </shiro:hasPermission>
                            </div>
							<form action="${ctx }/manage/member/flist" method="post" id="searchForm" class="pull-right" style="width: 785px">
		                        <div class="input-group">
		                        	<select data-placeholder="选择企业..." style="width:200px;" class="chosen-select" tabindex="2" name="firm_id">
		                            	<option value="">选择企业...</option>
		                            	<c:forEach items="${firms }" var="f">
                                    	<option value="${f.id }">${f.name }</option>
                                    	</c:forEach>
		                            </select>
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
                                <shiro:hasPermission name="manage:member:freview">
                                <div class="btn-group">
	                                <a data-toggle="dropdown" class="btn btn-success btn-outline dropdown-toggle"><i class="fa fa-unlock-alt"></i> 批量审核 <span class="caret"></span></a>
	                                <ul class="dropdown-menu">
	                                    <li><a onclick="reviewM(1)">通过</a></li>
	                                    <li><a onclick="reviewM(2)">拒绝</a></li>
	                                </ul>
	                            </div>
	                            </shiro:hasPermission>
	                            <shiro:hasPermission name="manage:member:fdel">
                                <div class="btn-group">
	                                <a data-toggle="dropdown" class="btn btn-success btn-outline dropdown-toggle"><i class="fa fa-cog"></i> 批量操作 <span class="caret"></span></a>
	                                <ul class="dropdown-menu">
	                                    <li><a onclick="delM(0)">正常</a></li>
	                                    <li><a onclick="delM(1)">禁用</a></li>
	                                </ul>
	                            </div>
	                            </shiro:hasPermission>
	                            <shiro:hasPermission name="manage:member:fdel_flg">
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
                                    <th>积分</th>
                                    <th>所属企业</th>
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
                                    <td>${m.point }</td>
                                    <td>${m.firm_name }</td>
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
                                    	<shiro:hasPermission name="manage:member:fupdate">
                                    	<a class="btn btn-xs btn-white" onclick="layer_show('修改用户','${ctx}/manage/member/update/${m.id }','','','')"><i class="fa fa-pencil"></i> 修改</a>
                                    	</shiro:hasPermission>
                                    	<shiro:hasPermission name="manage:member:freview">
                                    	<div class="btn-group">
			                                <a data-toggle="dropdown" class="btn btn-xs btn-white dropdown-toggle"><i class="fa fa-unlock-alt"></i> 审核 <span class="caret"></span></a>
			                                <ul class="dropdown-menu">
			                                    <li><a onclick="review(1,'${m.id}')" <c:if test="${m.review==1}">class="font-bold"</c:if>>通过</a></li>
			                                    <li><a onclick="review(2,'${m.id}')" <c:if test="${m.review==2}">class="font-bold"</c:if>>拒绝</a></li>
			                                </ul>
			                            </div>
			                            </shiro:hasPermission>
			                            <shiro:hasPermission name="manage:member:fdel">
                                    	<div class="btn-group">
			                                <a data-toggle="dropdown" class="btn btn-xs btn-white dropdown-toggle"><i class="fa fa-cog"></i> 操作 <span class="caret"></span></a>
			                                <ul class="dropdown-menu">
			                                    <li><a onclick="del(0,'${m.id}')" <c:if test="${m.enabled==0}">class="font-bold"</c:if>>正常</a></li>
			                                    <li><a onclick="del(1,'${m.id}')" <c:if test="${m.enabled==1}">class="font-bold"</c:if>>禁用</a></li>
			                                </ul>
			                            </div>
			                            </shiro:hasPermission>
			                            <shiro:hasPermission name="manage:member:fdel_flg">
			                            <a class="btn btn-xs btn-white" onclick="del_f('${m.id}')"><i class="fa fa-trash"></i> 删除</a>
                                    	</shiro:hasPermission>
                                    </td>
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
    <script src="${ctx}/statics/manage/js/plugins/fancybox/jquery.fancybox.js"></script>
    <script src="${ctx}/statics/manage/js/jquery.form.js"></script>
    <script src="${ctx}/statics/manage/js/common.js"></script>
    <script>
        $(document).ready(function(){$(".footable").footable();$("#pagination").html('${page.pageContent }');$(".i-checks").iCheck({checkboxClass:"icheckbox_square-green",radioClass:"iradio_square-green"});$(".fancybox").fancybox({openEffect:"none",closeEffect:"none"});$('.chosen-select').chosen();pageinit();});
    	//初始化select控件
       	$("[name='firm_id']").val('${vo.firm_id}');
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
        	layer.confirm('确定要操作吗？', function(index){
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
        function uploadExcel(o){
        	var fileName=$(o).val();
        	var suffix=fileName.substring(fileName.lastIndexOf(".")+1);
			if(fileName==""||!(suffix=="xls"||suffix=="xlsx")){
				layer.msg('请选择需导入的Excel文件', function(){});
		  		return false;
			}
			var index=layer.load(2);
			var options = {
				url : "${ctx}/manage/member/import/"+suffix,
				type : "post",
				dataType: "json",
				success : function(data) {
					layer.close(index); //关闭加载层
					if(data.code=="0"){
						var c=data.msg;
						if(data.data.length>0){
							c=c+'【手机号重复】<br>失败记录如下：<br>';
							for(var i=0;i<data.data.length;i++){
								c=c+data.data[i].mobile+'<br>';
							}
						}
						layer.open({
						  type: 1,
						  title:'导入结果',
						  skin: 'layui-layer-rim', //加上边框
						  area: ['420px', '240px'], //宽高
						  content: '<div style="padding:20px;">'+c+'</div>'
						}); 
					}else{
						opt_error(data.msg);
					}
				}
			};
			$("#importForm").ajaxSubmit(options);
			return false;
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