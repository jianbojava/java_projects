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
    <link href="${ctx}/statics/manage/css/plugins/datapicker/datepicker3.css" rel="stylesheet">

</head>

<body class="gray-bg">
    <div class="wrapper wrapper-content animated fadeInRight">

            <div class="row">
                <div class="col-sm-12">
                    <div class="ibox float-e-margins">
                        <div class="ibox-title">
                            <h5>订单管理>订单管理
                            </h5>

                            <div class="ibox-tools">
                                <a class="collapse-link">
                                    <i class="fa fa-chevron-up"></i>
                                </a>
                            </div>
                        </div>
                        <div class="ibox-content">
                        	<div class="ibox-tools" style="float:left;">
                        		<shiro:hasPermission name="manage:norder:add">
                                <a class="btn btn-success btn-outline" onclick="layer_show('新增订单','${ctx}/manage/norder/add/${vo.type}','','','')"><i class="fa fa-plus"></i> 新增订单</a>
                                <a class="btn btn-success btn-outline" onclick="layer_show('新增历史已结业订单','${ctx}/manage/norder/addfinish/${vo.type}','','','')"><i class="fa fa-plus"></i>历史已结业订单</a>
                                <a class="btn btn-success btn-outline" onclick="layer_show('新增历史未结业订单','${ctx}/manage/norder/addnofinish/${vo.type}','','','true')"><i class="fa fa-plus"></i>历史未结业订单</a>
                                </shiro:hasPermission>
                                <shiro:hasPermission name="manage:norder:del">
                                <div class="btn-group" style="display: none">
	                                <a data-toggle="dropdown" class="btn btn-success btn-outline dropdown-toggle"><i class="fa fa-cog"></i> 批量操作 <span class="caret"></span></a>
	                                <ul class="dropdown-menu">
	                                    <li><a onclick="delM(0)">正常</a></li>
	                                    <li><a onclick="delM(1)">锁定</a></li>
	                                </ul>
	                            </div>
	                            </shiro:hasPermission>
                            </div>
							<form action="${ctx }/manage/norder/list" method="post" id="searchForm" class="pull-right" target='_self' style="width: 1030px" onsubmit="return validParms();">
		                        <div class="input-group">
		                          <select data-placeholder="选择订单状态..." style="width:200px;" class="chosen-select" tabindex="2" name="status">
		                            	<option value="">选择订单状态...</option>
		                            	<option value="0">已提交</option>
		                            	<option value="1">已付款</option>
		                            	<option value="-1">已取消</option>
		                            	<option value="-2">已退款</option>
		                            </select>
		                             <select  data-placeholder="选择部门..." style="width:200px;" class="chosen-select" tabindex="2" name="depart_id" >
                                    	<option value="">所有员工</option>
	                                    	<c:if test="${not empty depart}">
	                                    	  <option value="${depart.id }">${depart.name }</option>
		                                    	  <c:forEach items="${depart.child}" var="d">
		                                    		  <option value="${d.id }">&nbsp;&nbsp;&nbsp;${d.name }</option>
		                                    		  <c:forEach items="${d.child}" var="d">
		                                    		     <option value="${d.id }">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;${d.name }</option>
		                                    		     <c:forEach items="${d.child}" var="d">
		                                    		            <option value="${d.id }">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;${d.name }</option>
		                                    	                 <c:forEach items="${d.child}" var="d">
		                                    		                  <option value="${d.id }">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;${d.name }</option>
		                                    	                       <c:forEach items="${d.child}" var="d">
		                                    		                        <option value="${d.id }">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;${d.name }</option>
		                                    	                       </c:forEach>
		                                    	                </c:forEach>
		                                    	         </c:forEach>
		                                    	      </c:forEach>
		                                    	  </c:forEach>
	                                    	</c:if>
		                            </select>
		                            <input type="text" class="form-control input-sm" name="startDate2" style="width: 200px" value="<fmt:formatDate value='${vo.startDate2}' pattern='yyyy-MM-dd'/>" placeholder="开始时间">
		                        	<input type="text" class="form-control input-sm" name="endDate2" style="width: 200px" value="<fmt:formatDate value='${vo.endDate2}' pattern='yyyy-MM-dd'/>" placeholder="结束时间">
		                            <input type="text" class="form-control input-sm" name="keywords" value="${vo.keywords}" style="width:180px;float: right;" placeholder="编号,会员ID,推荐人ID,卡券号">
		                            <input type="hidden" name="pageNo" value="${vo.pageNo }"/>
				      				<input type="hidden" name="pageSize" value="${vo.pageSize }">
				      				<input type="hidden" name="type" value="${vo.type }">
		                            <div class="input-group-btn">
		                                <button type="button" class="btn btn-sm btn-primary">搜索</button>
		                            </div>
		                        </div>
		                    </form>
                            <table class="footable table table-stripped toggle-arrow-tiny" data-sort="false" data-page-size="${vo.pageSize }">
                                <thead>
                                <tr>
                                	<th width="30"><input type="checkbox" class="i-checks" name="input"></th>
                                    <th data-toggle="true">订单编号</th>
                                    <th>课程/卡券</th>
                                    <th>价格</th>
                                    <th>会员ID</th>
                                    <th>推荐人ID</th>
                                    <th>卡券号</th>
                                    <th>部门</th>
                                    <th>订单状态</th>
                                    <th>下单时间</th>
                                    <th>操作</th>
                                </tr>
                                </thead>
                                <tbody>
                                <c:forEach items="${page.result }" var="norder">
                                <tr>
                                	<td><input type="checkbox" class="i-checks" name="input[]" value="${norder.id }"></td>
                                	<td>${norder.sn}</td>
                                    <td>${norder.name }</td>
                                    <td>${norder.price }</td>
                                    <td>${norder.number}</td>
                                    <td>${norder.refer_number }</td>
                                    <td>${norder.ticket_sn}</td>
                                    <td>${norder.depart_name}</td>
                                    <td>
                                        <c:if test="${norder.status==0}">已提交</c:if>
                                        <c:if test="${norder.status==1}">已付款</c:if>
                                        <c:if test="${norder.status==-1}">已取消</c:if>
                                        <c:if test="${norder.status==-2}">已退款</c:if>
                                    </td>
                                    <td><fmt:formatDate value="${norder.create_date }" pattern="yyyy-MM-dd HH:mm:ss"/></td>
                                    <td>
                                    	<shiro:hasPermission name="manage:norder:list">
                                    	<a class="btn btn-xs btn-white" onclick="layer_show('订单详情','${ctx}/manage/norder/update/${norder.id }','','',true)"><i class="fa fa-eye"></i> 查看</a>
                                    	</shiro:hasPermission>
                                    	<shiro:hasPermission name="manage:norder:pay">
                                    	<c:if test="${norder.status==0}">
                                    	   <a class="btn btn-xs btn-white" onclick="pay('${norder.id}')"><i class="fa fa-cny"></i> 线下支付</a>
                                    	 </c:if>
                                    	</shiro:hasPermission>
                                    	<shiro:hasPermission name="manage:norder:update">
                                    	<c:if test="${norder.status==0}">
                                    	   <a class="btn btn-xs btn-white" onclick="cancel('${norder.id}')"><i class="fa fa-pencil"></i> 取消订单</a>
                                    	 </c:if>
                                    	</shiro:hasPermission>
                                    	<shiro:hasPermission name="manage:norder:nreturn">
                                    	<c:if test="${norder.status==1}">
                                    	   <a class="btn btn-xs btn-white" onclick="layer_show('退款','${ctx}/manage/norder/nreturn/${norder.id}','','','')"><i class="fa fa-google-wallet"></i> 退款</a>
                                    	 </c:if>
                                    	</shiro:hasPermission>
                                      <shiro:hasPermission name="manage:receipt:add">
                                    	<c:if test="${norder.status==1||norder.status==-2}">
                                    	  <a class="btn btn-xs btn-white" onclick="layer_show('添加发票','${ctx}/manage/receipt/addBack/${norder.id}','','','')"><i class="fa fa-th"></i> 添加发票</a>
                                    	 </c:if>
                                    	</shiro:hasPermission>
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
    <script src="${ctx}/statics/manage/js/plugins/chosen/chosen.jquery_.js"></script>
    <script src="${ctx}/statics/manage/plugins/layer/layer.js"></script>
    <script src="${ctx}/statics/manage/js/plugins/datapicker/bootstrap-datepicker.js"></script>
    <script src="${ctx}/statics/manage/js/common.js?v=1.0.0"></script>
    <script>
        $(document).ready(function(){$(".footable").footable();$("#pagination").html('${page.pageContent }');$(".i-checks").iCheck({checkboxClass:"icheckbox_square-green",radioClass:"iradio_square-green"});pageinit();});
   		 $("[name='startDate2'],[name='endDate2']").datepicker({
         	format: "yyyy-mm-dd",
         	todayHighlight:true,
        	autoclose: true,
        	todayBtn: true
         });
   		$("[name='depart_id']").val('${vo.depart_id}');
   		$("[name='status']").val('${vo.status}');
   		$(".chosen-select").trigger("chosen:updated");
   		$('.chosen-select').chosen();
    	function del(flg,id){
        	layer.confirm('确定要操作吗？', function(index){
			  layer.close(index);
			  $.post("${ctx}/manage/user/del/"+flg+"/"+id,function(data){
				if(data.code=="0"){
					opt_success("操作成功");
				}else{
					opt_error("系统繁忙，请稍后再试");
				}
			  },"json")
			});  
        }
        function cancel(id){
           layer.confirm('确定要取消订单吗？',function(index){
            layer.close(index);
              $.post("${ctx}/manage/norder/cancel",{"id":id},function(data){
                if(data.code=="0"){
					opt_success("取消成功");
				}else{
					opt_error("系统繁忙，请稍后再试");
				}
              },"json");
              
           })
        }
        function pay(id){
        	layer.confirm('确定已完成线下付款吗？', function(index){
			  layer.close(index);
			  $.post("${ctx}/manage/norder/pay/"+id,function(data){
				if(data.code=="0"){
					opt_success("付款成功");
				}else if(data.code=="-2"){
				    opt_error("卡券已经被使用，订单已取消");
				}else if(data.code=="-3"){
				    opt_error("直售订单，必须先设置推荐人的提成身份等级");
				}else if(data.code=="-4"){
				    opt_error("该部门必须要有一个提成身份等级为1等员工");
				}else if(data.code=="-5"){
				    opt_error("推荐人不能为空");
				}else if(data.code=="-6"){
				    opt_error("推荐人或者推荐人关联人必须是绩效部门");
				}else if(data.code=="-7"){
				    opt_error("会员积分不足");
				}
				else{
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
					   $.post("${ctx}/manage/user/del/"+flg+"/"+dels.substring(1),function(data){
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
        
        
         //表单时间验证
        function validParms(){
        	var star_date=$("[name='startDate2']").val();
        	var end_date=$("[name='endDate2']").val();
        	if($.trim(star_date)!=""){
        		if($.trim(end_date)!=""){
        			if(new Date(star_date)>=new Date(end_date)){
        				layer.msg("开始时间不能大于结束时间", {
							icon : 0
						});
						return false;
        			}
        			return true;
        		}
        	}
        	if($.trim(end_date)!=""){
        		if($.trim(star_date)!=""){
        			if(new Date(star_date)>=new Date(end_date)){
        				layer.msg("开始时间不能大于结束时间", {
							icon : 0
						});
						return false;
        			}
        			return true;
        		}
        	}
        }
    </script>
</body>

</html>