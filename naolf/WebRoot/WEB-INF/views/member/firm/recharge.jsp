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
    <link href="${ctx}/statics/manage/css/animate.min.css" rel="stylesheet">
    <link href="${ctx}/statics/manage/css/style.min.css?v=4.0.0" rel="stylesheet">
    <link href="${ctx}/statics/manage/css/plugins/switchery/switchery.css" rel="stylesheet">
    <link href="${ctx}/statics/manage/css/plugins/iCheck/custom.css" rel="stylesheet">
    <link href="${ctx}/statics/manage/plugins/uploadify/uploadify.css" rel="stylesheet">
    <link href="${ctx}/statics/manage/css/plugins/datapicker/datepicker3.css" rel="stylesheet">
    <link href="${ctx}/statics/manage/css/plugins/chosen/chosen.css" rel="stylesheet">    
    <link href="${ctx}/statics/manage/css/plugins/awesome-bootstrap-checkbox/awesome-bootstrap-checkbox.css" rel="stylesheet">

</head>

<body>
    <div class="row animated fadeInRight">
            <div class="col-sm-12">
                <div class="ibox float-e-margins">
                    <div class="ibox-content">
                        <form action="" method="post" class="form-horizontal">
                            <div class="form-group">
                                <label class="col-sm-2 control-label">保证金</label>
                                <div class="col-sm-9">
                                	当前保证金为：${firm.deposit }
                                	<br/>
                                    <input type="text" class="form-control" name="deposit" placeholder="请输入充值金额，充值输入正数，退款输入负数，不充值输入0">
                                </div>
                            </div>
                            <c:choose>
                            	<c:when test="${firm.pay_type==0 }">
                            		<div class="hr-line-dashed"></div>
		                            <div class="form-group">
		                                <label class="col-sm-2 control-label">信用额度</label>
		                                <div class="col-sm-9">
		                                	当前可用额度为：${firm.left_amount }
		                                	<br/>
		                                    <input type="text" class="form-control" name="left_amount" placeholder="请输入调整的额度，增加输入正数，减少输入负数，不调整输入0">
		                                </div>
		                            </div>
                            	</c:when>
                            	<c:otherwise>
                            		<div class="hr-line-dashed"></div>
		                            <div class="form-group">
		                                <label class="col-sm-2 control-label">账户余额</label>
		                                <div class="col-sm-9">
		                                	当前余额为：${firm.left_amount }
		                                	<br/>
		                                    <input type="text" class="form-control" name="left_amount" placeholder="请输入充值金额，充值输入正数，退款输入负数，不充值输入0">
		                                </div>
		                            </div>
                            	</c:otherwise>
                            </c:choose>
                            <div class="hr-line-dashed"></div>
                            <div class="form-group">
                                <label class="col-sm-2 control-label">备注</label>

                                <div class="col-sm-9">
                                    <textarea class="form-control" rows="3" name="remark" style="resize: none" placeholder="请填写充值备注~"></textarea>
                                </div>
                            </div>
                            <div class="hr-line-dashed"></div>
                            <div class="form-group">
                                <div class="col-sm-4 col-sm-offset-4" style="text-align: center;">
                                    <button class="btn btn-primary" type="button" onclick="recharge(this)">立即充值</button>
                                    <button class="btn btn-white" type="button" onclick="layer_close()">取消</button>
                                </div>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>

    <script src="${ctx}/statics/manage/js/jquery.min.js?v=2.1.4"></script>
    <script src="${ctx}/statics/manage/js/bootstrap.min.js?v=3.3.6"></script>
    <script src="${ctx}/statics/manage/js/plugins/jasny/jasny-bootstrap.min.js"></script>
    <script src="${ctx}/statics/manage/js/content.min.js?v=1.0.0"></script>
    <script src="${ctx}/statics/manage/js/plugins/switchery/switchery.js"></script>
    <script src="${ctx}/statics/manage/js/plugins/iCheck/icheck.min.js"></script>
    <script src="${ctx}/statics/manage/plugins/layer/layer.js"></script>
    <script src="${ctx}/statics/manage/plugins/uploadify/jquery.uploadify.js"></script>
    <script src="${ctx}/statics/manage/js/plugins/datapicker/bootstrap-datepicker.js"></script>
    <script src="${ctx}/statics/manage/js/plugins/chosen/chosen.jquery.js"></script>
    <script src="${ctx}/statics/manage/js/jquery.form.js"></script>
    <script src="${ctx}/statics/manage/js/common.js"></script>
    
    <script>
        function recharge(o){
        	var deposit=$("[name='deposit']").val();
        	var left_amount=$("[name='left_amount']").val();
        	var remark=$("[name='remark']").val();
        	if($.trim(deposit)==""){layer.msg('请填写充值金额（保证金）', function(){});return;}
        	if($.trim(left_amount)==""){layer.msg('请填写充值金额（余额）', function(){});return;}
        	if($.trim(remark)==""){layer.msg('请填写充值备注', function(){});return;}
        	$(o).attr("disabled","disabled");
			var params={"id":"${firm.id}","deposit":deposit,"left_amount":left_amount,"remark":remark};
			$.post("${ctx}/manage/firm/recharge",params,function(data){
				//$(o).removeAttr("disabled");
				if(data.code=="0"){
					opt_success("充值成功",0);
				}else{
					opt_error("系统繁忙，请稍后再试");
				}
			},"json")
        }
    </script>
</body>

</html>