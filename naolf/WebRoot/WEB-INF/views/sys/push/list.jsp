<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html lang="en" class=" ">
<head>
<meta charset="utf-8" />
<title>专栏分类</title>
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
<script type="text/javascript" src="${ResStatic }/static/js/jquery.form.js"></script>
<script type="text/javascript" src="${ctx }/static/plugin/ueditor1_4_3/ueditor.config.js"></script>
<script type="text/javascript" src="${ctx }/static/plugin/ueditor1_4_3/ueditor.all.js"></script>
<script type="text/javascript" src="${ResStatic }/static/js/Validform_v5.3.2_min.js"></script>
<style type="text/css">
.panel .table td{ /*padding: 8px 15px;*/
  border-top: 1px solid #eaeef1;word-break:keep-all; white-space: inherit; overflow:hidden; text-overflow:ellipsis;/* width:100%; */}
</style>
</head>
<body class="container">
<section class="wrapper">
          <div class="m-b"> <span class="h3 font-thin"><i class="i i-arrow-left3"></i></span> </div>
          <section class="panel panel-default">
            <header class="panel-heading">推送记录</header>
            <div class="row wrapper">
              <div class="col-sm-9 m-b-xs"> 
                <a href="javascript:;" onclick="javascript:clearInput();jQuery('#modal-operation').modal('show');" class="button button-rounded">
                    <img src="${ResStatic }/static/img/+_black.png" alt="添加" style="margin-top:-3px;"/>添加
                </a>
                <a href="javascript:;" class="button button-rounded">
                  <img src="${ResStatic }/static/img/x_black.png" alt="删除"  class="Mtop3""/>删除
                </a>
              </div>
              <div class="col-sm-3">
              	<form action="${ctx}/manager/push/list" method="post" id="queryForm">
	                <div class="input-group">
	                  <input type="text" class="input-sm form-control" placeholder="推送内容" value="${po.content }" name="content">
				      <input type="hidden" name="pageNo" value="${po.pageNo}"/>
				      <input type="hidden" name="pageSize" value="${po.pageSize}">
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
                    <th width="60%">推送内容</th>
                    <th width="20%">推送时间</th>
                    <th width="20%">推送用户</th>
                    <th width="98px;">操作</th>
                  </tr>
                </thead>
                
                <tbody>
                
                  <c:forEach items="${list}" var="list">
                  <tr>
                    <td><label class="checkbox m-n i-checks">
                        <input type="checkbox" name="post[]" value="${list.id}">
                        <i></i></label>
                    </td>
                    <td>${list.content}</td>
                    <td><fmt:formatDate value="${list.create_date }" type="both"/></td>
                    <td>
                    <c:if test="${list.application==0 }">客户端：</c:if>
                    <c:if test="${list.application==1 }">商户端：</c:if>
                    <c:if test="${list.platform==0 }">Android&iOS</c:if>
                    <c:if test="${list.platform==1 }">iOS</c:if>
                    <c:if test="${list.platform==2 }">Android</c:if>
                    <c:if test="${list.platform==3 }">${list.alias}</c:if>
                    </td>
                    <td>
                      <a href="javascript:;" onclick="jQuery('#modal-del').modal('show', {backdrop: 'fade'});" >
                          <img src="${ResStatic }/static/img/table-del.png" title="删除">
                      </a>
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
  <div class="modal-dialog" style="width: 70%">
  <form id="fileForm" onsubmit="return false">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
        <h4 class="modal-title">推送设置</h4>
      </div>
      <div class="modal-body">
        <section>
          <div class="row wrapper">
          	<div class="col-sm-12 form-group Btm10">
                <label class="col-sm-2 control-label" style="line-height: 40px;">推送应用</label>
                <div class="col-sm-9">
                  <div class="radio i-checks Mar10">
                    <label>
                      <input type="radio" name="application" value="0" checked="checked">
                      <i></i>客户端</label>
                  </div>
                  <div class="radio i-checks">
                    <label>
                      <input type="radio" name="application" value="1">
                      <i></i>商户端</label>
                  </div>
                </div>
              </div>
              <div class="col-sm-12 form-group Btm10">
                <label class="col-sm-2 control-label" style="line-height: 40px;">推送对象</label>
                <div class="col-sm-9">
                  <div class="radio i-checks Mar10">
                    <label>
                      <input type="radio" name="platform" value="0" checked="checked">
                      <i></i>Android&iOS</label>
                  </div>
                  <div class="radio i-checks">
                    <label>
                      <input type="radio" name="platform" value="1">
                      <i></i>iOS</label>
                  </div>
                  <div class="radio i-checks">
                    <label>
                      <input type="radio" name="platform" value="2">
                      <i></i>Android</label>
                  </div>
                  <div class="radio i-checks" onclick="initPerson();">
                    <label>
                      <input type="radio" name="platform" value="3">
                      <i></i>个人</label>
                  </div>
                </div>
              </div>
              <div class="col-sm-12 form-group Btm10">
						<label class="col-sm-2 control-label">推送内容</label></label>
						<div class="col-sm-7" style="width: 80%;">
							<textarea rows="6" style="width: 100%;resize:none" maxlength="255" id="content" placeholder="推送内容(最多255字)"></textarea>
						</div>
			</div>
          </div>
        </section>
      </div>
      <div class="modal-footer" style="text-align:center;">
        <button type="button" class="button button-rounded button-primary" style="margin-right:20px;" data-dismiss="modal">确定</button>
        <button type="button" class="button button-rounded" data-dismiss="modal">取消</button>
      </div>
    </div></form>
  </div>
</div>

<!--表单页面列表-->


<!--发放优惠券表单-->

<div class="modal fade custom-width" id="modal-person">
  <div class="modal-dialog" style="width: 80%">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
        <h4 class="modal-title">选择用户推送</h4>
      </div>
      <div class="modal-body">
        <section>
          <div class="row wrapper">
            <div class="col-sm-12 form-group Btm10">
              <div class="col-sm-5">
              	<input type="text" class="form-control" id="leftSInput" placeholder='输入手机号或名称搜索' style="margin-bottom: 10px;">
                <select multiple="multiple" id="select1" style="width:100%;height:300px;">
					
                </select>
                <div> </div>
              </div>
              <div class="col-sm-1 centent" style="margin-top: 100px;"> <span id="add" >></span> <span id="add_all" >>></span> <span id="remove"><</span> <span id="remove_all"><<</span> </div>
              <div class="col-sm-5">
                <input type="text" class="form-control" id="rightSInput" placeholder='输入手机号或名称搜索' style="margin-bottom: 10px;">
                <select multiple="multiple" id="select2" style="width: 100%;height:300px;">
                		
                </select>
                <div> </div>
              </div>
            </div>
          </div>
        </section>
      </div>
      <div class="modal-footer" style="text-align:center;">
        <button type="button" class="button button-rounded button-primary" style="margin-right:20px;">确定</button>
        <button type="button" class="button button-rounded" data-dismiss="modal">取消</button>
      </div>
    </div>
  </div>
</div>
<!--发放优惠券表单页面列表-->

<script type="text/javascript">
	$(function(){
		var mobile=[];
		//删除
		$("#modal-del").find("button").eq(1).click(function(){
			$.post("${ctx}/manager/push/del/"+operationID,function(data){
				if(data.code=="0"){
					$(".input-group button").click();
				}
			},"json")
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
			jQuery('#modal-del').modal('show');
		})
		//添加(修改)
		$("#modal-operation").find("button").eq(1).click(function(){
			var content=$("#content").val();
			var platform=$(":radio[name='platform']:checked").val();
			var application=$(":radio[name='application']:checked").val();
			if(platform=="3"&&mobile.length==0){
	 		   alert("请选择需要推送的用户");
	 		   return;
	 		}
			$.ajax({
				url:"${ctx}/manager/push/add",
				type: "POST",
				data: JSON.stringify({"content":content,"platform":platform,"application":application,"users":mobile}),
				success: function(data){
				    if(data.code=="0"){
				      alert("推送成功");
				      $(".input-group button").click();
					}
				},
				dataType: "json",
				contentType: "application/json"
			});
		})
		
		//选择用户推送
		$("#modal-person").find("button").eq(1).click(function(){
			mobile=[];
			$("#modal-person").find("#select2 option").each(function (){
				mobile.push($(this).val());
	 		});
	 		if(mobile.length==0){
	 		   alert("请选择需要推送的用户");
	 		   return;
	 		}
	 		$("#modal-person").find("button").eq(2).click();
		})
		
		//左侧搜索
		$("#leftSInput").keyup(function(){
			var keyword=$.trim($(this).val());
			if(keyword!=""){
				$("#select1 option").hide().filter(":contains('"+keyword+"')").show();  
			}else{
				$("#select1 option").show();
			}
 		});
		//左侧搜索
		$("#rightSInput").keyup(function(){
			var keyword=$.trim($(this).val());
			if(keyword!=""){
				$("#select2 option").hide().filter(":contains('"+keyword+"')").show();  
			}else{
				$("#select2 option").show();
			}
 		});
	})
	//清空表单
	function clearInput(){
		$("input[name='platform']").eq(0).attr("checked",true);
		$("input[name='application']").eq(0).attr("checked",true);
		$("#content").val("");
		$("#leftSInput").val("");
		$("#rightSInput").val("");
	}
	
	function initPerson(){
		var application=$(":radio[name='application']:checked").val();
		if(application=="0"){
			$.getJSON("${ctx}/manager/push/add/0",function(data){
				if(data!=null){
					initData(data);
				}
			})
		}else{
			$.getJSON("${ctx}/manager/push/add/1",function(data){
				if(data!=null){
					initData(data);
				}
			})
		}
		jQuery('#modal-person').modal('show');
	}
	
	function initData(list){
		$("#modal-person #select1").empty();
		$("#modal-person #select2").empty();
		if(list!=null){
			for(var i=0;i<list.length;i++){
				$("#modal-person #select1").append("<option value=\""+list[i]+"\">"+list[i]+"</option>");
			}
		}
	}
	
</script>
</body>
</html>