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
    <link href="${ctx}/statics/manage/css/plugins/iCheck/custom.css" rel="stylesheet">
    <link href="${ctx}/statics/manage/css/plugins/datapicker/datepicker3.css" rel="stylesheet">

</head>

<body class="gray-bg">
    <div class="wrapper wrapper-content animated fadeInRight">

            <div class="row">
                <div class="col-sm-12">
                    <div class="ibox float-e-margins">
                        <div class="ibox-title">
                            <h5>结算中心 > 违章查询</h5>

                            <div class="ibox-tools">
                                <a class="collapse-link">
                                    <i class="fa fa-chevron-up"></i>
                                </a>
                            </div>
                        </div>
                        <div class="ibox-content">
                        	<div class="ibox-tools" style="float:left;">
                        		<shiro:hasPermission name="manage:order:illegalUpd">
                                <a class="btn btn-success btn-outline" onclick="solveM()"><i class="fa fa-check"></i> 违章处理</a>
                                </shiro:hasPermission>
                                <shiro:hasPermission name="manage:order:illegalExport">
                                <a class="btn btn-success btn-outline" onclick="javascript:location.href='${ctx}/manage/order/illegalExport'"><i class="fa fa-file-excel-o"></i> 导出EXCEL</a>
                            	</shiro:hasPermission>
                            </div>
							<form action="${ctx }/manage/order/illegal" method="post" id="searchForm" class="pull-right" style="width: 646px" onsubmit="return validParms();">
		                        <div class="input-group">
		                        	<input type="text" class="form-control input-sm" name="startDate2" style="width: 200px" value="<fmt:formatDate value='${vo.startDate2}' pattern='yyyy-MM-dd'/>" placeholder="开始时间">
		                        	<input type="text" class="form-control input-sm" name="endDate2" style="width: 200px" value="<fmt:formatDate value='${vo.endDate2}' pattern='yyyy-MM-dd'/>" placeholder="结束时间">
		                            <input type="text" class="form-control input-sm" name="keywords" style="width: 200px" value="${vo.keywords}" placeholder="搜索订单号">
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
                                    <th data-toggle="true">车架号</th>
                                    <th>违章时间</th>
                                    <th>代办服务费</th>
                                    <th>违法金额</th>
                                    <th>罚分</th>
                                    <th>订单编号</th>
                                    <th>支付方式</th>
                                    <th>支付状态</th>
                                    <th>状态</th>
                                    <th>处理状态</th>
                                    <th data-hide="all">凭证编号</th>
                                    <th data-hide="all">违法代码</th>
                                    <th data-hide="all">违法地点</th>
                                    <th data-hide="all">违法内容</th>
                                    <th data-hide="all">违章图片</th>
                                    <th data-hide="all">缴费凭证</th>
                                    <th data-hide="all">入库时间</th>
                                    <th data-hide="all">处理备注</th>
                                    <th>操作</th>
                                </tr>
                                </thead>
                                <tbody>
                                <c:forEach items="${page.result }" var="i">
                                <tr>
                                	<td><input type="checkbox" class="i-checks disabled" <c:if test="${i.is_solve==1 }">disabled="disabled"</c:if>  name="input[]" value="${i.illegalSeq }"></td>
                                	<td>${i.vin }</td>
                                    <td><fmt:formatDate value="${i.illegal_date}" pattern="yyyy-MM-dd HH:mm" /></td>
                                    <td>
                                    	<c:choose>
                                    		<c:when test="${empty(i.service_amount) }">-</c:when>
                                    		<c:otherwise>${i.service_amount }</c:otherwise>
                                    	</c:choose>
                                    </td>
                                    <td>${i.illegal_amount }</td>
                                    <td>${i.penalty_point }</td>
                                    <td>${i.orderSeq }</td>
                                    <td>
                                    	<c:choose>
                                    		<c:when test="${empty(i.payment) }">-</c:when>
                                    		<c:otherwise>
                                    			<c:choose><c:when test="${i.payment==0}">违章预授权</c:when><c:when test="${i.payment==1}">信用卡</c:when><c:otherwise>现金</c:otherwise></c:choose>
                                    		</c:otherwise>
                                    	</c:choose>
                                    </td>
                                    <td>
                                    	<c:choose>
                                    		<c:when test="${empty(i.payment_status) }">-</c:when>
                                    		<c:otherwise>
                                    			<c:choose><c:when test="${i.payment_status==0}">未支付</c:when><c:otherwise>已支付</c:otherwise></c:choose>
                                    		</c:otherwise>
                                    	</c:choose>
                                    </td>
                                    <td>
                                    	<c:choose>
                                    		<c:when test="${empty(i.status) }">-</c:when>
                                    		<c:otherwise>
                                    			<c:choose><c:when test="${i.status==0}">未处理</c:when><c:when test="${i.status==1}">处理中</c:when><c:otherwise>办理完毕</c:otherwise></c:choose>
                                    		</c:otherwise>
                                    	</c:choose>
                                    </td>
                                    <td><c:choose><c:when test="${i.is_solve==0}">未处理</c:when><c:otherwise>已处理</c:otherwise></c:choose></td>
                                    <td>${i.document_number }</td>
                                    <td>${i.illegal_code }</td>
                                    <td>${i.place }</td>
                                    <td>${i.illegal_content }</td>
                                    <td>
                                    	<c:if test="${!empty(i.illegal_imageUrl) }">
                                    	<a class="fancybox" href="${i.illegal_imageUrl}" title="违章图片">
                            				<img alt="image" src="${i.illegal_imageUrl}" width="30" height="30"/>
                        				</a>
                        				</c:if>
                        			</td>
                        			<td>
                                    	<c:if test="${!empty(i.certificate_image) }">
                                    	<a class="fancybox" href="${i.certificate_image }" title="缴费凭证">
                            				<img alt="image" src="${i.certificate_image }" width="30" height="30"/>
                        				</a>
                        				</c:if>
                        			</td>
                        			<td><fmt:formatDate value="${i.create_date}" pattern="yyyy-MM-dd" /></td>
                        			<td>${i.remark }</td>
                                    <td>
                                    	<shiro:hasPermission name="manage:order:illegalUpd">
                                    	<a class="btn btn-xs btn-white" <c:if test="${i.is_solve==1 }">disabled="disabled"</c:if> <c:if test="${i.is_solve==0 }">onclick="solve('${i.illegalSeq}')"</c:if>><i class="fa fa-check"></i> 违章处理</a>
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
        //更新
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
        
        //违章处理
        function solve(id){
        	//prompt层
			layer.prompt({title: '填写备注，并确认', formType: 2}, function(text){
			    $.post("${ctx}/manage/order/illegalUpd",{"remark":text,"id":id},function(data){
					if(data.code=="0"){
						opt_success("操作成功");
					}else{
						opt_error("系统繁忙，请稍后再试");
					}
				  },"json")
			});
        }
        
        function solveM(){
        	if($("[name='input[]']").filter(':checked').length>0){
        		$("[name='input[]']").filter(':checked').each(function(){
					 dels+=","+$(this).attr("value");
				  })
				  //prompt层
				layer.prompt({title: '填写备注，并确认', formType: 2}, function(text){
				    $.post("${ctx}/manage/order/illegalUpd",{"remark":text,"id":dels},function(data){
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
    </script>
</body>

</html>