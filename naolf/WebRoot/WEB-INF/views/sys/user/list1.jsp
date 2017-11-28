<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html lang="en" class=" ">
<head>
<meta charset="utf-8" />
<title>微贝</title>
<meta name="description" content="app, web app, responsive, admin dashboard, admin, flat, flat ui, ui kit, off screen nav" />
<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1" />
<link type="text/css" rel="stylesheet" href="${ResStatic }/static/css/bootstrap.css" />
<link type="text/css" rel="stylesheet" href="${ResStatic }/static/css/app.css" />
<link type="text/css" rel="stylesheet" href="${ResStatic }/static/css/base.css">
<link type="text/css" rel="stylesheet" href="${ResStatic }/static/css/button.css">
<link type="text/css" rel="stylesheet" href="${ResStatic }/static/css/font-awesome.min.css">
<link type="text/css" rel="stylesheet" href="${ResStatic }/static/plugin/ztree/zTreeStyle.css">
<script type="text/javascript" src="${ResStatic }/static/js/jquery-1.8.3.min.js"></script>
<script type="text/javascript" src="${ResStatic }/static/js/base.js"></script>
<script type="text/javascript" src="${ResStatic }/static/js/bootstrap.js"></script> 
<script type="text/javascript" src="${ResStatic }/static/js/app.js"></script> 
<script type="text/javascript" src="${ResStatic }/static/js/app.plugin.js"></script>
<script type="text/javascript" src="${ResStatic }/static/plugin/ztree/jquery.ztree.core-3.5.js"></script>
<script type="text/javascript" src="${ResStatic }/static/js/Validform_v5.3.2_min.js"></script>
<SCRIPT type="text/javascript">
<%--var setting = {
		view: {
			showIcon: true
		},
		data: {
			simpleData: {
				enable: true
			}
		},
		callback: {
			onClick: zTreeOnClick
		}
	};

	$(document).ready(function(){
		var zNodes =${treeData};
		$.fn.zTree.init($("#ztree"), setting, zNodes);
		$.fn.zTree.getZTreeObj("ztree").expandAll(false);
		$("#parentOrgDiv input").focus(function(){
			$("#ztree").show();
		})
	});
	
	function zTreeOnClick(event, treeId, treeNode) {
		$("#parentOrgDiv input").val(treeNode.name);
		$("#parentOrgDiv input").attr("id",treeNode.id);
		$("#ztree").hide();
	};--%>
</SCRIPT>
</head>
<body class="container">
<section class="wrapper">
          <div class="m-b"> <span class="h3 font-thin"><i class="i i-arrow-left3"></i></span> </div>
          <section class="panel panel-default">
            <header class="panel-heading hed-font">用户列表 </header>
            <div class="row wrapper">
              <div class="col-sm-9 m-b-xs"> 
                <!--<select class="input-sm form-control input-s-sm inline v-middle">
                  <option value="0">Bulk action</option>
                  <option value="1">Delete selected</option>
                  <option value="2">Bulk edit</option>
                  <option value="3">Export</option>
                </select>
                <button class="btn btn-sm btn-default">Apply</button>--> 
                <c:if test="${user.type==0 }">
                <shiro:hasPermission name="manager:user:add"><a href="javascript:;" onclick="jQuery('#modal-operation').modal('show');" class="button button-rounded"><img src="${ResStatic }/static/img/+_black.png" alt="添加" style="margin-top:-3px;"/>添加</a></shiro:hasPermission>
                <shiro:hasPermission name="manager:user:del"><a href="javascript:;" class="button button-rounded"><img src="${ResStatic }/static/img/x_black.png" alt="删除"  class="Mtop3""/>删除</a></shiro:hasPermission>
                </c:if>
                </div>
              <div class="col-sm-3">
              	<form action="${ctx}/manager/user/list/${user.type}" method="post" id="queryForm">
	                <div class="input-group">
	                  <input type="text" class="input-sm form-control" placeholder="用户名,昵称" value="${user.username}" name="username">
				      <input type="hidden" name="pageNo" value="${user.pageNo}"/>
				      <input type="hidden" name="pageSize" value="${user.pageSize}">
	                  <span class="input-group-btn">
	                  	<button class="btn btn-sm btn-default" type="button">搜索</button>
	                  </span>
	                </div>
                </form>
              </div>
            </div>
            <div class="table-responsive">
              <table class="table table-striped b-t b-light">
                <thead>
                  <tr>
                    <th style="width:3.5%;"><label class="checkbox m-n i-checks">
                        <input type="checkbox">
                        <i></i></label></th>
                    <th class="th-sortable">用户名</th>
                    <th>昵称</th>
                    <c:if test="${user.type==0 || user.type==3}"><th>角色</th></c:if>
                    <th>登陆IP</th>
                    <th width="98px">状态</th>
                    <th><c:if test="${user.type=='0' }">企业名称</c:if><c:if test="${user.type=='1' }">商户名称</c:if></th>
                    <th width="98px;">操作</th>
                  </tr>
                </thead>
                <tbody>
                  <c:forEach items="${users }" var="user">
                  <tr>
                    <td><label class="checkbox m-n i-checks">
                        <input type="checkbox" name="post[]" value="${user.id }">
                        <i></i></label></td>
                    <td>${user.username }</td>
                    <td>${user.nickname }</td>
                    <c:if test="${user.type==0 || user.type==3}"><td>
                    	<c:forEach var="r" items="${user.role_names }">
                    		${r}&nbsp;
                    	</c:forEach>
                    </td></c:if>
                    <td>${user.login_ip }</td>
                    <td><c:choose><c:when test="${user.is_locked==0}">正常</c:when><c:otherwise>锁定</c:otherwise></c:choose></td>
                    <td>${user.companyName }</td>
                    <td><shiro:hasPermission name="manager:user:update"><a href="javascript:;" onclick="jQuery('#modal-operation').modal('show');"><img src="${ResStatic }/static/img/table-gai.png" title="修改"></a></shiro:hasPermission>
                    	
                    		<shiro:hasPermission name="manager:user:del"><a  <c:if test="${user.type==1}">style="display: none" </c:if> href="javascript:;" onclick="jQuery('#modal-del').modal('show', {backdrop: 'fade'});" ><img src="${ResStatic }/static/img/table-del.png" title="删除"></a></shiro:hasPermission>
                    	
                    </td>
                  </tr>
                  </c:forEach>
                </tbody>
              </table>
            </div>
            ${pageHtml }
          </section>
        </section>

<!-- 弹出层开始 -->
<div class="modal fade" id="modal-del" style="margin-top:10%;" >
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header" style="border:none;">
        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
        <h4 class="modal-title">你确定要删除此条信息吗?</h4>
      </div>
      <div class="modal-footer" style="text-align:center; border:none;">
	      <button type="button" class="button button-rounded button-primary" style="margin-right:20px;" data-dismiss="modal">确定</button>
	      <button type="button" class="button button-rounded" data-dismiss="modal">取消</button>
      </div>
    </div>
  </div>
</div>
<!-- 弹出层结束 -->

<!--表单页面列表-->

<div class="modal fade custom-width" id="modal-operation">
  <div class="modal-dialog" style="width: 60%">
    <form class="bs-example form-horizontal" id="fileForm" onsubmit="return false">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
        <h4 class="modal-title"></h4>
      </div>
      <div class="modal-body">
        <section>
          <div class="row wrapper">
              <div class="col-sm-12 form-group Btm10">
                <label class="col-sm-2 control-label">用户名</label>
                <div class="col-sm-10">
                  <input type="text" class="form-control" id="username" placeholder="用户名" datatype="*1-50" nullmsg="请输入用户名" errormsg="范围在1~50位之间！">
                  <div class="Validform_checktip"></div>
                </div>
              </div>
              <div class="col-sm-12 form-group Btm10" id="addPwd" style="display:none;">
                <label class="col-sm-2 control-label">初始密码</label>
                <div class="col-sm-2">
                  <label class="switch">
                    <input type="checkbox" checked="checked" disabled="disabled">
                    <span></span> </label>
                </div>
                <label class="col-sm-3 control-label">初始密码:123456</label>
              </div>
              <div class="col-sm-12 form-group Btm10" id="updatePwd" style="display:none;">
                <label class="col-sm-2 control-label">重置密码</label>
                <div class="col-sm-2">
                  <label class="switch">
                    <input type="checkbox" value="0">
                    <span></span> </label>
                </div>
                <label class="col-sm-3 control-label">重置密码:123456</label>
              </div>
              <div class="col-sm-12 form-group Btm10">
                <label class="col-sm-2 control-label">昵称</label>
                <div class="col-md-10">
                  <input type="text" class="form-control" id="nickname" placeholder="昵称" datatype="*1-50" nullmsg="请输入昵称" errormsg="范围在1~50位之间！">
                  <div class="Validform_checktip"></div>
                </div>
              </div>
              <div class="col-sm-12 form-group Btm10">
                <label class="col-sm-2 control-label">企业</label>
                <div class="col-sm-10">
                  <select id="companySelect" class="form-control m-b">
                  <c:forEach items="${companys }" var="comp">
                  <option value="${comp.id }">${comp.name }</option>
                  </c:forEach>
                  </select>
                </div>
              </div>
              <div class="col-sm-12 form-group Btm10" id="parentOrgDiv" style="display: none;">
                <label class="col-sm-2 control-label">组织</label>
                <div class="col-sm-10">
                  <input type="text" class="form-control" placeholder="组织架构(点击触发)" id="" value="" readonly="readonly">
                 	<ul id="ztree" class="ztree treeDemo form-control"></ul>
                </div>
              </div>
              <div class="col-sm-12 form-group Btm10">
                <label class="col-sm-2 control-label">锁定</label>
                <div class="col-sm-10">
                  <label class="switch">
                    <input type="checkbox" id="lockedCheckbox">
                    <span></span> </label>
                </div>
              </div>
              <div class="col-sm-12 form-group Btm10">
                <label class="col-sm-2 control-label">角色</label>
                <div class="col-sm-10">
                  <ul style="width: 100%;float: left;">
                <c:forEach items="${roles }" var="role" varStatus="index">
                  <li class="col-sm-4">
                    <label class="checkbox m-n i-checks">
                      <input type="checkbox" name="subBox" value=${role.id } <c:if test="${index.index==0}"> datatype="checkneed" nullmsg="请至少选择一项" errormsg="请至少选择一项" </c:if> >
                      <i></i></label>
                      ${role.name }
                  <li>
                  </c:forEach> 
                </ul><div id="mycheck" class="Validform_checktip"></div>
                </div>
              </div>
          </div>
        </section>
      </div>
      <div class="modal-footer" style="text-align:center;">
        <button type="button" class="button button-rounded button-primary" style="margin-right:20px;">确定</button>
        <button type="button" class="button button-rounded" data-dismiss="modal">取消</button>
      </div>
    </div></form>
  </div>
</div>

<!--表单页面列表-->

<script type="text/javascript">
	$(function(){
		//删除
		$("#modal-del").find("button").eq(1).click(function(){
			$.post("${ctx}/manager/user/del/"+operationID,function(data){
				if(data.code=="0"){
					$(".input-group button").click();
				}
			},"json")
		})
		$(".m-b-xs a").click(function(){
			clearInput();
			operType="add";
			$(".modal-title").html("添加用户");
			$("#addPwd").css("display","block");
		})
		//删除按钮
		$(".m-b-xs").find("a").eq(1).click(function(){
			operationID="";
			$("tbody input[type=checkbox]:checked").each(function(){
				operationID+=$(this).attr("value")+",";
			})
			if(operationID==""){
				alert("请选择操作项");
				return false;
			}
			$(".modal-title").html("你确定要删除此条信息吗?");
			jQuery('#modal-del').modal('show');
		})
		//初始化数据
		$("tbody tr").each(function(){
			$(this).find("td:last a:first").click(function(){
				clearInput();
				operType="update";
				//$("#username").attr("readonly",true);
				$(".modal-title").html("修改用户");
				$("#updatePwd").css("display","block");
				$.post("${ctx}/manager/user/update/"+operationID,function(data){
					if(data!=null){
						if(data.user!=null){
							$("#username").val(data.user.username);
							$("#password").val(data.user.password);
							$("#nickname").val(data.user.nickname);
							if(data.user.is_locked==0){
								$("#lockedCheckbox").attr("checked",false);
							}else{
								$("#lockedCheckbox").attr("checked",true);
							}
							$("#companySelect option").each(function (){
								if($(this).val()==data.user.company_id){
									$(this).attr("selected",true);
								}
						 	});
							if(data.user.roles!=""){
								var role_arr=data.user.roles.split(",");
								$(".modal-body input[name='subBox']").each(function(){
									for(var i=0;i<role_arr.length;i++){
										if(role_arr[i]==$(this).attr("value")){
											$(this).attr("checked",true);
										}
									}
								})
							}
						}
<%--						if(data.org!=null){
							$("#parentOrgDiv input").attr("id",data.org.id);
							$("#parentOrgDiv input").val(data.org.name);
						}--%>
					}
				},"json")
			})
			$(this).find("a:last").click(function(){
				$(".modal-title").html("你确定要删除此条信息吗?");
			})
		})
		//添加(修改)
		$("#modal-operation").find("button").eq(1).click(function(){
		    var err_len=$("#modal-operation").find(".Validform_error").length;
			if(err_len>0){
			  return;
			}
			var roles=[];
			$(".modal-body input[name='subBox']:checked").each(function(){
				roles.push($(this).attr("value"));
			})
			if(roles==""){
				alert("请选择角色");
				return false;
			}
			var lockstatus=0;
			if($("#lockedCheckbox").attr("checked"))lockstatus=1;
			var org_id=$("#parentOrgDiv input").attr("id");
			<%--if(org_id==""){
				alert("请选择组织节点");
				return;
			}--%>
			if(operType=="add"){
				$.post("${ctx}/manager/user/add",{"username":$("#username").val(),"password":"123456","nickname":$("#nickname").val(),"is_locked":lockstatus,"company_id":$("#companySelect").children("option:selected").val(),"roles":roles.toString(),"org_id":org_id},function(data){
					if(data.code=="0"){
						$(".input-group button").click();
						$("#modal-operation").find("button").eq(2).click();
					}else{
						alert(data.message);
					}
				},"json")
			}else{
				var params={"id":operationID,"username":$("#username").val(),"nickname":$("#nickname").val(),"is_locked":lockstatus,"company_id":$("#companySelect").children("option:selected").val(),"roles":roles.toString(),"org_id":org_id};
				if(typeof($("#updatePwd input[type='checkbox']").attr("checked"))!="undefined"){
					params={"id":operationID,"username":$("#username").val(),"password":"123456","nickname":$("#nickname").val(),"is_locked":lockstatus,"company_id":$("#companySelect").children("option:selected").val(),"roles":roles.toString(),"org_id":org_id};
				}
				$.post("${ctx}/manager/user/update",params,function(data){
					if(data.code=="0"){
						$("#queryForm").submit();
					}
				},"json")
			}
		})
	})
	//清空表单
	function clearInput(){
		$("#username").val("");
		$("#password").val("");
		$("#nickname").val("");
		$("#parentOrgDiv input").val("");
		$("#parentOrgDiv input").attr("id","");
		//$("#username").attr("readonly",false);
		$("#addPwd").css("display","none");
		$("#updatePwd").css("display","none");
		$("#updatePwd input[type='checkbox']").attr("checked",false);
		$("#lockedCheckbox").attr("checked",false);
		$(".modal-body input[name='subBox']:checked").each(function(){
			$(this).attr("checked",false);
		});
		$(".Validform_checktip").html("");
		$(".Validform_error").attr("class","form-control"); 
	}
</script>
</body>
</html>