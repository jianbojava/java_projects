<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html lang="en" class=" ">
<head>
<meta charset="utf-8" />
<title></title>
<meta name="description" content="app, web app, responsive, admin dashboard, admin, flat, flat ui, ui kit, off screen nav" />
<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1" />
<link type="text/css" rel="stylesheet" href="${ResStatic }/static/css/bootstrap.css" />
<link type="text/css" rel="stylesheet" href="${ResStatic }/static/css/app.css" />
<link type="text/css" rel="stylesheet" href="${ResStatic }/static/css/base.css">
<link type="text/css" rel="stylesheet" href="${ResStatic }/static/css/button.css">
<link type="text/css" rel="stylesheet" href="${ResStatic }/static/css/font-awesome.min.css">
<script type="text/javascript" src="${ResStatic }/static/js/jquery-1.8.3.min.js"></script>
<script type="text/javascript" src="${ResStatic }/static/js/base.js"></script>
<script type="text/javascript" src="${ResStatic }/static/js/bootstrap.js"></script> 
<script type="text/javascript" src="${ResStatic }/static/js/app.js"></script> 
<script type="text/javascript" src="${ResStatic }/static/js/app.plugin.js"></script>
<script type="text/javascript" src="${ResStatic }/static/js/Validform_v5.3.2_min.js"></script>
<style type="text/css">
body {
	height: 100%;
	max-height: 100%;
	overflow: hidden;
}
</style>
</head>
<body>
<section class="wrapper">
  <div class="m-b"> <span class="h3 font-thin"><i class="i i-arrow-left3"></i></span> </div>
  <section class="panel panel-default">
    <header class="panel-heading hed-font">角色列表 </header>
    <div class="row wrapper">
      <div class="col-sm-9 m-b-xs">
        <shiro:hasPermission name="manager:role:add"><a href="javascript:;" onclick="jQuery('#modal-operation').modal('show');" class="button button-rounded"><img src="${ResStatic }/static/img/+_black.png" alt="添加" style="margin-top:-3px;"/>添加</a></shiro:hasPermission>
         <shiro:hasPermission name="manager:role:del"><a href="javascript:;" class="button button-rounded"><img src="${ResStatic }/static/img/x_black.png" alt="删除"  class="Mtop3""/>删除</a></shiro:hasPermission>
      </div>
      <div class="col-sm-3">
      	<form action="${ctx}/manager/role/list" method="post" id="queryForm">
         <div class="input-group">
           <input type="text" class="input-sm form-control" placeholder="角色名称" value="${role.name}" name="name">
		  <input type="hidden" name="pageNo" value="${role.pageNo}"/>
		  <input type="hidden" name="pageSize" value="${role.pageSize}">
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
            <th class="th-sortable">名称</th>
            <th>描述</th>
            <th>状态</th>
            <th>创建时间</th>
            <th width="160px;">操作</th>
          </tr>
        </thead>
        <tbody>
          <c:forEach items="${roles }" var="role">
          <tr>
            <td><label class="checkbox m-n i-checks">
                <input type="checkbox" name="post[]" value="${role.id }">
                <i></i></label></td>
            <td>${role.name }</td>
            <td>${role.description }</td>
            <td><c:choose><c:when test="${role.available==0}">启用</c:when><c:otherwise>禁用</c:otherwise></c:choose></td>
            <td><fmt:formatDate value="${role.create_date}" type="both" /></td>
            <td><shiro:hasPermission name="manager:role:update"><a href="javascript:;" onclick="jQuery('#modal-operation').modal('show');"><img src="${ResStatic }/static/img/table-gai.png" title="修改"></a></shiro:hasPermission><shiro:hasPermission name="manager:role:del"><a href="javascript:;" onclick="jQuery('#modal-del').modal('show', {backdrop: 'fade'});" ><img src="${ResStatic }/static/img/table-del.png" title="删除"></a></shiro:hasPermission></td>
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

<!--弹出权限管理页面-->

<div class="modal fade custom-width" id="modal-operation">
  <div class="modal-dialog PadddingBt50" style="width: 90%">
  <form id="fileForm" onsubmit="return false">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
        <h4 class="modal-title"></h4>
      </div>
      <div class="modal-body">
        <section class="panel panel-default">
          <div class="row wrapper">
              <div class="col-sm-12">
                <label class="col-sm-1 control-label"><h5>名称</h5></label>
                <div class="col-sm-8">
                  <input type="text" class="form-control" id="name" placeholder="角色名称" datatype="*1-50" nullmsg="请输入名称" errormsg="范围在1~50位之间！">
                  <div class="Validform_checktip"></div>
                </div>
              </div>
          </div>
          <div class="row wrapper">
              <div class="col-sm-12">
                <label class="col-sm-1 control-label"><h5>描述</h5></label>
                <div class="col-sm-8">
                  <input type="text" class="form-control" id="description" placeholder="角色描述" datatype="*1-50" nullmsg="请输入描述" errormsg="范围在1~50位之间！">
                  <div class="Validform_checktip"></div>
                </div>
              </div>
          </div>
          <div class="row wrapper">
          	   <div class="col-sm-12">
                <label class="col-sm-1 control-label"><h5>启用</h5></label>
                <div class="col-sm-8">
                  <label class="switch">
                    <input type="checkbox" id="availableCheckbox" checked="checked">
                    <span></span> </label>
                </div>
              </div>
          </div>
        </section>
        <c:forEach items="${perms}" var="perm">
        <c:if test="${fn:length(perm.childPers)>0}">
        <section class="panel panel-default">
          <header class="panel-heading">${perm.name}</header>
          <div class="row wrapper">
            <div class="col-sm-12">
              <div class="MesigPer">
                <ul>
                  <c:forEach items="${perm.childPers}" var="childPer">
                  <li class="col-sm-2">
                    <label class="checkbox m-n i-checks">
                      <input type="checkbox" name="subBox" value="${childPer.id}">
                      <i></i></label>
                      	${childPer.name}
                  <li>
                  </c:forEach>
                </ul>
              </div>
            </div>
          </div>
        </section>
        </c:if>
        </c:forEach>
      </div>
      <div class="modal-footer" style="text-align:center;">
        <div class="MOdalBottom">
          <label class="checkbox m-n i-checks">
            <input type="checkbox" id="all">
            <i></i></label>
          确定全选/取消</div>
        <button type="button" class="button button-rounded button-primary" style="margin-right:20px;">确定</button>
        <button type="button" class="button button-rounded" data-dismiss="modal">取消</button>
      </div>
    </div></form>
  </div>
</div>
<!--弹出权限管理页面--> 

<script type="text/javascript">
	$(function(){
		//删除
		$("#modal-del").find("button").eq(1).click(function(){
			$.post("${ctx}/manager/role/del/"+operationID,function(data){
				if(data.code=="0"){
					$(".input-group button").click();
				}
			},"json")
		})
		//添加按钮
		$(".m-b-xs").find("a").eq(0).click(function(){
			clearInput();
			operType="add";
			$(".modal-title").html("添加角色");
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
		//修改按钮
		$("tbody tr").each(function(){
			$(this).find("a:first").click(function(){
				clearInput();
				operType="update";
				$(".modal-title").html("修改角色");
				$.post("${ctx}/manager/role/update/"+operationID,function(data){
					if(data!=null){
						$("#name").val(data.name);
						$("#description").val(data.description);
						if(data.available==1){
							$("#availableCheckbox").attr("checked",false);
						}else{
							$("#availableCheckbox").attr("checked",true);
						}
						if(data.perms!=""){
							var perm_arr=data.perms.split(",");
							$(".modal-body input[name='subBox']").each(function(){
								for(var i=0;i<perm_arr.length;i++){
									if(perm_arr[i]==$(this).attr("value")){
										$(this).attr("checked",true);
									}
								}
							})
						}
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
			var perms=[];
			$(".modal-body input[name='subBox']:checked").each(function(){
				perms.push($(this).attr("value"));
			})
			if(perms==""){
				alert("请选择资源");
				return false;
			}
			var available=1;
			if($("#availableCheckbox").attr("checked"))available=0;
			if(operType=="add"){
				$.post("${ctx}/manager/role/add",{"name":$("#name").val(),"description":$("#description").val(),"available":available,"perms":perms.toString()},function(data){
					if(data.code=="0"){
						alert("添加成功");
						$(".input-group button").click();
					}
				},"json")
			}else{
				$.post("${ctx}/manager/role/update",{"id":operationID,"name":$("#name").val(),"description":$("#description").val(),"available":available,"perms":perms.toString()},function(data){
					if(data.code=="0"){
						alert("修改成功");
						$("#queryForm").submit();
					}
				},"json")
			}
		})
	})
	//清空表单
	function clearInput(){
		$("#name").val("");
		$("#description").val("");
		$("#availableCheckbox").attr("checked",true);
		$(".modal-body input[name='subBox']:checked").each(function(){
			$(this).attr("checked",false);
		});
		$(".Validform_checktip").html("");
		$(".Validform_error").attr("class","form-control"); 
	}
</script>
</body>
</html>