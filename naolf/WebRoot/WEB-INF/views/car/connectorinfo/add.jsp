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
</head>

<body>
    <div class="row animated fadeInRight">
            <div class="col-sm-12">
                <div class="ibox float-e-margins">
                    <div class="ibox-content">
                        <form action="" method="post" class="form-horizontal">
                            <div class="form-group">
                                <label class="col-sm-2 control-label">接口编号</label>

                                <div class="col-sm-9">
                                    <input type="text" class="form-control" name="connectorID" placeholder="接口编号">
                                </div>
                            </div>
                            <div class="hr-line-dashed"></div>
                            <div class="form-group">
                                <label class="col-sm-2 control-label">接口名称</label>

                                <div class="col-sm-9">
                                    <input type="text" class="form-control" name="connectorName" placeholder="接口名称">
                                </div>
                            </div>
                            <div class="hr-line-dashed"></div>
                            <div class="form-group">
                                <label class="col-sm-2 control-label">接口类型</label>

                                <div class="col-sm-9">
                                    <select class="form-control m-b" name="connectorType">
                                        <option value="1">家用插座（模式2）</option>
                                        <option value="2">交流接口插座（模式3，连接方式B）</option>
                                        <option value="3">交流接口插头（带枪线，模式3，连接方式C）</option>
                                        <option value="4">直流接口枪头（带枪线，模式4）</option>
                                    </select>
                                </div>
                            </div>
                            <div class="hr-line-dashed"></div>
                            <div class="form-group">
                                <label class="col-sm-2 control-label">电压上线</label>

                                <div class="col-sm-9">
                                    <input type="text" class="form-control" name="voltageUpperLimits" placeholder="电压上线">
                                </div>
                            </div>
                            <div class="hr-line-dashed"></div>
                            <div class="form-group">
                                <label class="col-sm-2 control-label">电压下线</label>

                                <div class="col-sm-9">
                                    <input type="text" class="form-control" name="voltageLowerLimits" placeholder="电压下线">
                                </div>
                            </div>
                            <div class="hr-line-dashed"></div>
                            <div class="form-group">
                                <label class="col-sm-2 control-label">额定电流</label>

                                <div class="col-sm-9">
                                    <input type="text" class="form-control" name="current" placeholder="额定电流">
                                </div>
                            </div>
                            <div class="hr-line-dashed"></div>
                            <div class="form-group">
                                <label class="col-sm-2 control-label">额定功率</label>

                                <div class="col-sm-9">
                                    <input type="text" class="form-control" name="power" placeholder="额定功率">
                                </div>
                            </div>
                            <div class="hr-line-dashed"></div>
                            <div class="form-group">
                                <label class="col-sm-2 control-label">车位号</label>

                                <div class="col-sm-9">
                                    <input type="text" class="form-control" name="parkNo" placeholder="车位号">
                                </div>
                            </div>
                            <div class="hr-line-dashed"></div>
                            <div class="form-group">
                                <label class="col-sm-2 control-label">接口状态</label>
                                <div class="col-sm-9">
                                    <select class="form-control m-b" name="status">
                                        <option value="1">空闲</option>
                                        <option value="2">占用（未充电）</option>
                                        <option value="3">占用（充电中）</option>
                                        <option value="4">占用（预约锁定）</option>
                                        <option value="0">离网</option>
                                        <option value="255">故障</option>
                                    </select>
                                </div>
                            </div>
                            <div class="hr-line-dashed"></div>
                            <div class="form-group">
                                <label class="col-sm-2 control-label">所属充电站</label>
                                <div class="col-sm-9">
                                    <select data-placeholder="请选择充电站" class="chosen-select" tabindex="2" name="stationID">
                                    	<c:forEach items="${stations }" var="s">
                                    		<option value="${s.stationID }">【编号：${s.stationID }】${s.stationName }</option>
                                    	</c:forEach>
		                            </select>
                                </div>
                            </div>
                            <div class="hr-line-dashed"></div>
                            <div class="form-group">
                                <label class="col-sm-2 control-label">所属充电设备</label>
                                <div class="col-sm-9">
                                    <select data-placeholder="请选择充电设备" class="chosen-select" tabindex="2" name="equipmentID"></select>
                                </div>
                            </div>
                            <div class="hr-line-dashed"></div>
                            <div class="form-group">
                                <div class="col-sm-4 col-sm-offset-4" style="text-align: center;">
                                    <button class="btn btn-primary" type="button" onclick="add()">保存内容</button>
                                    <button class="btn btn-white" type="button" onclick="layer_close()">取消</button>
                                </div>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>
        
        <div id="modal-map" class="modal fade" aria-hidden="true">
	        <div class="modal-dialog">
	            <div class="modal-content">
	                <div class="modal-body">
	                    <div class="row">
	                        <div class="col-sm-12" id="allmap" style="height: 300px">
	                        	
	                        </div>
	                    </div>
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
    <script src="http://api.map.baidu.com/api?v=2.0&ak=WhXy2DrGL6B6hlzFhixKZTna6pGsYZzi"></script>
    
    <script>
    	$('.chosen-select').chosen();
    	getEquipments($("[name='stationID']").val());
    	$("[name='stationID']").change(function(){
    		getEquipments($(this).val());
		})
        function add(){
			var params={"connectorID":$("[name='connectorID']").val(),"connectorName":$("[name='connectorName']").val(),"connectorType":$("[name='connectorType']").val(),"voltageUpperLimits":$("[name='voltageUpperLimits']").val(),
			"voltageLowerLimits":$("[name='voltageLowerLimits']").val(),"current":$("[name='current']").val(),"power":$("[name='power']").val(),"parkNo":$("[name='parkNo']").val(),"status":$("[name='status']").val(),"stationID":$("[name='stationID']").val(),"equipmentID":$("[name='equipmentID']").val()};
        	$.post("${ctx}/manage/connectorinfo/add",params,function(data){
					if(data.code=="0"){
						opt_success("添加成功",0);
					}else{
						opt_error("添加失败");
					}
				},"json")
        }
		function getEquipments(sid){
			$("[name='equipmentID']").empty();
        	$(".chosen-select").trigger("chosen:updated");
			$.getJSON("${ctx}/manage/connectorinfo/getEquipments/"+sid,function(data){
				for(var i=0;i<data.length;i++){
        			$("[name='equipmentID']").append("<option value=\""+data[i].equipmentID+"\" hassubinfo=\"true\">"+data[i].equipmentID+"</option>");
        		}
        		$(".chosen-select").trigger("chosen:updated");
			})
		}
    </script>
</body>

</html>