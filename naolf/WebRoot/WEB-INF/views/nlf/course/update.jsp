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
    <link href="${ctx}/statics/manage/css/animate.min.css" rel="stylesheet">
    <link href="${ctx}/statics/manage/css/style.min.css?v=4.0.0" rel="stylesheet">
    <link href="${ctx}/statics/manage/css/plugins/switchery/switchery.css" rel="stylesheet">
    <link href="${ctx}/statics/manage/css/plugins/iCheck/custom.css" rel="stylesheet">
    <link href="${ctx}/statics/manage/css/plugins/sweetalert/sweetalert.css" rel="stylesheet">
    <link href="${ctx}/statics/manage/plugins/uploadify/uploadify.css" rel="stylesheet">
    <link href="${ctx}/statics/manage/css/plugins/chosen/chosen.css" rel="stylesheet">
    <link href="${ctx}/statics/manage/css/plugins/datapicker/datepicker3.css" rel="stylesheet">
    <link href="${ctx}/statics/manage/plugins/bootstrap-datetimepicker/css/bootstrap-datetimepicker.css" rel="stylesheet">
    <link href="${ctx}/statics/manage/css/plugins/awesome-bootstrap-checkbox/awesome-bootstrap-checkbox.css" rel="stylesheet">

</head>

<body>
    <div class="row animated fadeInRight">
            <div class="col-sm-12">
                <div class="ibox float-e-margins">
                    <div class="ibox-content">
                        <form action="" method="post" class="form-horizontal">
                            <div class="form-group">
                                <label class="col-sm-2 control-label">课程</label>
                                <div class="col-sm-9">
                                    <select data-placeholder="课程选择..." class="chosen-select" tabindex="2" name="lesson_id" id="lesson_id">
                                    	<option value="">请选择课程</option>
                                    	<c:forEach items="${lessons }" var="l">
                                    		<option value="${l.id }" tag="${l.type }" hassubinfo="true">${l.name }</option>
                                    	</c:forEach>
		                            </select>
                                </div>
                            </div>
                            <div class="hr-line-dashed"></div>
                            <div id="typediv" style="display:none ">
                            	 <div class="form-group">
	                                <label class="col-sm-2 control-label">类型</label>
	                                <div class="col-sm-9">
	                                	<div class="radio radio-success radio-inline">
	                                        <input type="radio" id="inlineRadio1" value="0" name="type" <c:if test="${course.type==0}">checked</c:if>>
	                                        <label for="inlineRadio1"> 进阶</label>
	                                    </div>
	                                    <div class="radio radio-success radio-inline">
	                                        <input type="radio" id="inlineRadio2" value="1" name="type" <c:if test="${course.type==1}">checked</c:if>>
	                                        <label for="inlineRadio2">集中</label>
	                                    </div>
	                                </div>
	                            </div>
	                            <div class="hr-line-dashed"></div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-2 control-label">教师姓名</label>
                                <div class="col-sm-9">
                                    <input type="text" class="form-control" placeholder="教师姓名" name="teacher" value="${course.teacher}">
                                </div>
                            </div>
                              <div class="hr-line-dashed"></div>
                             <div class="form-group">
                                <label class="col-sm-2 control-label">发布日期</label>
                                <div class="col-sm-9">
		                                <input class="form-control" name="star_time" type="text" placeholder="发布日期" value="<fmt:formatDate value='${ course.start_time}' pattern='yyyy-MM-dd hh:mm'/>">
                                </div>
                            </div>
                            <div id="enddiv" <c:if test="${course.type==1}"> style="display: none"</c:if>>
	                            <div class="hr-line-dashed"></div>
	                            <div class="form-group">
	                                <label class="col-sm-2 control-label">结束日期</label>
	
	                                <div class="col-sm-9">
	                                    <div class="input-group input-daterange">
		                               			<input class="form-control input-sm" type="text" name="end_time" placeholder="结束日期" value="<fmt:formatDate value='${ course.end_time}' pattern='yyyy-MM-dd hh:mm'/>" style="width:300px">
			                            </div>
	                                </div>
	                            </div>
                            </div>
                             <div class="hr-line-dashed"></div>
                             <div class="form-group">
                                <label class="col-sm-2 control-label">服务部门</label>
                                <div class="col-sm-9">
                                    <select   data-placeholder="服务部门选择..." class="chosen-select" tabindex="2" name="depart_id">
                                    	<option value="">请选择服务部门</option>
                                    	<c:forEach items="${departs }" var="depart">
                                    		<option value="${depart.id }" <c:if test="${depart.id==d.id}">selected</c:if> tag="${depart.province_name}${depart.city_name}${depart.region_name}${depart.address}" hassubinfo="true">${depart.name }</option>
                                    	</c:forEach>
		                            </select>
                                </div>
                            </div>
                            <div class="hr-line-dashed"></div>
                            <div class="form-group">
                                <label class="col-sm-2 control-label">部门地址</label>
                                <div class="col-sm-9">
                                    <input type="text" class="form-control" id="address" readonly name="" value="${d.province_name}${d.city_name}${d.region_name}${d.address}">
                                </div>
                            </div>
                            <!--
	                            <div class="hr-line-dashed"></div>
                            <div class="form-group">
                                <label class="col-sm-2 control-label">部门名称</label>

                                  <div class="col-sm-9">
                                    <input type="text" class="form-control" readonly name="" value="${cdepart.name}">
                                </div>
                            </div>
                            <div class="hr-line-dashed"></div>
                            <div class="form-group">
                                <label class="col-sm-2 control-label">部门地址</label>

                                  <div class="col-sm-9">
                                    <input type="text" class="form-control" readonly name="" value="${cdepart.province_name}${cdepart.city_name}${cdepart.region_name}${cdepart.address}">
                                </div>
                            </div>
                            -->
                            <div class="hr-line-dashed"></div>
                            <div class="form-group">
                                <label class="col-sm-2 control-label">最大人数</label>
                                <div class="col-sm-9">
                                    <input type="text" class="form-control" name="num" value="${course.num}">
                                </div>
                            </div>
                           <div class="hr-line-dashed"></div>
                             <div class="form-group">
                                <label class="col-sm-2 control-label">备注</label>
                                <div class="col-sm-9">
                                    <script type="text/plain" id="description">${course.description}
    		   	  					</script>
                                </div>
                            </div>
                            <div class="hr-line-dashed"></div>
                            <div class="form-group">
                                <div class="col-sm-4 col-sm-offset-4" style="text-align: center;">
                                    <button class="btn btn-primary" <c:if test="${course.status>=1}">disabled="disabled"</c:if>< type="button" onclick="update()">保存内容</button>
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
    <script src="${ctx}/statics/manage/js/content.min.js?v=1.0.0"></script>
    <script src="${ctx}/statics/manage/js/plugins/switchery/switchery.js"></script>
    <script src="${ctx}/statics/manage/js/plugins/iCheck/icheck.min.js"></script>
    <script src="${ctx}/statics/manage/plugins/layer/layer.js"></script>
    <script src="${ctx}/statics/manage/js/plugins/sweetalert/sweetalert.min.js"></script>
    <script src="${ctx}/statics/manage/plugins/uploadify/jquery.uploadify.js"></script>
    <script src="${ctx}/statics/manage/js/plugins/chosen/chosen.jquery.js"></script>
    <script src="${ctx}/statics/manage/js/jquery.form.js"></script>
    <script src="${ctx}/statics/manage/js/common.js"></script>
    <script src="${ctx}/statics/manage/js/plugins/datapicker/bootstrap-datepicker.js"></script>
    <script src="${ctx}/statics/manage/plugins/bootstrap-datetimepicker/js/bootstrap-datetimepicker.js"></script>
    <script src="${ctx}/statics/manage/plugins/ueditor-1.4.3/ueditor.config.js"></script>
	<script src="${ctx}/statics/manage/plugins/ueditor-1.4.3/ueditor.all.js"></script>
    <script>
    
     var ue=UE.getEditor('description',{
		    //这里可以选择自己需要的工具按钮名称,此处仅选择如下五个
		    toolbars: [[
			   'fullscreen', 'source', '|', 'undo', 'redo', '|',
    	       'bold', 'italic', 'underline', 'fontborder', 'removeformat', 'formatmatch', 'pasteplain', '|', 'forecolor', 'backcolor', 'insertorderedlist', 'insertunorderedlist',
    	       'rowspacingtop', 'rowspacingbottom', 'lineheight', '|',
    	       'customstyle', 'paragraph', 'fontfamily', 'fontsize', '|',
    	       'indent',
    	       'justifyleft', 'justifycenter', 'justifyright', 'justifyjustify', '|',
    	       'link', 'unlink','simpleupload', 'insertimage'
			 ]],
		     //关闭字数统计
		     wordCount:false,
		     initialFrameHeight:200,
		     //关闭elementPath
		     elementPathEnabled:false
	    });
    /**有部门权限的话可以下拉**/
    <shiro:hasPermission name='manage:depart:list'>
      $("[name='depart_id']").attr("disabled",false);
     </shiro:hasPermission>
     $('.chosen-select').chosen();
     $("#lesson_id").val('${course.lesson_id}');
     $(".chosen-select").trigger("chosen:updated");
  	//时间控件								datepicker为直选日期无时分秒
    $("[name='star_time'],[name='end_time']").datetimepicker({
        language:  'zh-CN',
		format: "yyyy-mm-dd hh:ii",
		maxView:3,
		minuteStep:1,
   		autoclose: true,
   		todayBtn: true,
   		setDaysOfWeekDisabled:[0,6]
	});
  	$("[name='star_time']").datetimepicker().on("changeDate", function(ev){
  		var end_time=$("[name='end_time']").val();
  		if(end_time!=""){
  			if(new Date($(this).val())>=new Date(end_time)){
  				$(this).val("");
  				layer.msg('开始时间不能大于结束时间', function(){});
  			}else{
  				//calAmount();
  			}
  		}
	});
	$("[name='end_time']").datetimepicker().on("changeDate", function(ev){
	    var star_time=$("[name='star_time']").val();
  		if(star_time!=""){
  			if(new Date(star_time)>=new Date($(this).val())){
  				$(this).val("");
  				layer.msg('结束时间不能小于开始时间', function(){});
  			}
  		}
	});
	$(function(){
		resetadd($("[name='depart']").val());
		$("[name='depart']").change(function(){
// 			alert(resetadd($(this).val()));
			resetadd($(this).val());
		})
	})
    function resetadd(id){
    	$("[name='address']").html("<option value=\"\">请选择地址</option>");
    	$(".chosen-select").trigger("chosen:updated");
    	$.getJSON("${ctx}/manage/course/address/"+id,function(data){
    		for(var i=0;i<data.length;i++){
    			$("[name='address']").append("<option value=\""+data[i].id+"\" hassubinfo=\"true\">"+data[i].name+"</option>");
    		}
    		$(".chosen-select").trigger("chosen:updated");
		})
    }
         $("#imageUploadify").uploadify({
        	'buttonImage':'${ctx}/statics/manage/plugins/uploadify/uploadify-button.png',
	        'swf': '${ctx}/statics/manage/plugins/uploadify/uploadify.swf',
	        'uploader':'${ctx}/upload',
	        'onFallback' : function() {//检测FLASH失败调用 
	        	alert("您未安装FLASH控件，无法上传图片！请安装FLASH控件后再试。");  
	        },  
	        'onUploadSuccess':function(file, data, response){
                var jsonData = jQuery.parseJSON(data);
                $(".FildImg").css("display","block").find("img").attr("src",jsonData.msg);
        	}
	    });
        function update(){
	        var depart_id=$("[name='depart_id']").val();
	        var lesson_id=$("[name='lesson_id']").val();
	        var teacher=$.trim($("[name='teacher']").val());
	        var start_time=$("[name='star_time']").val();
	        var end_time=$("[name='end_time']").val();
	        var type=$("[name='type']:checked").val();
	        var num=$("[name='num']").val();
	        if(depart_id==null||depart_id==""){
	           opt_error("请选择部门");
	           return;
	        }
	        if(lesson_id==null||lesson_id==""){
	           opt_error("请选择课程");
	           return;
	        }
	        if(teacher==null||teacher==""){
	            opt_error("请输入教师姓名");
	           return;
	        }
	         if(start_time==null||start_time==""){
	            opt_error("请输入发布时间");
	           return;
	        }
	         if(num==null||num==""){
	            opt_error("请输入最大人数");
	           return;
	        }
	        if((type==0)&&(end_time==null||end_time=="")){
	           opt_error("请输入结束时间");
	           return;
	        }
	        $(".btn-primary").attr("disabled",true);//防止多次提交
			var params={
			            "depart_id":depart_id,
						"type":$("[name='type']:checked").val(),
						"teacher":$("[name='teacher']").val(),
						"lesson_id":$("[name='lesson_id']").val(),
				  		"start_time":$("[name='star_time']").val(),
				  		"end_time":$("[name='end_time']").val(),
						"num":$("[name='num']").val(),
						"id":'${course.id}',
						"description":ue.getContent()
						};
        	$.post("${ctx}/manage/course/update",params,function(data){
					if(data.code=="0"){
						opt_success("修改成功",0);
					}else{
						opt_error("修改失败");
						$(".btn-primary").attr("disabled",false);//防止多次提交
					}
				},"json")
        }
		$("[name='lesson_id']").change(function(){
			var type=$("[name='lesson_id'] option:selected").attr("tag");
			if(type==0){
				$("[name=type][value=0]").prop("checked",true);
				$("#typediv").css("display","block");
				$("#enddiv").css("display","block");
				$("[name=type]").attr("disabled",false);
			}else{
				$("[name=type][value=1]").prop("checked",'checked');
				$("#typediv").css("display","block");
				$("#enddiv").css("display","none");
				$("[name=type]").attr("disabled",'disabled');
			}
		})
		
        //radio
		$("[name='type']").change(function(){
			if($(this).attr("value")=="0"){
				$("#enddiv").css("display","block");
			}else{
				$("#enddiv").css("display","none");
			}
		})
		
		
		$("[name='depart_id']").change(function(){
			var address=$("[name='depart_id'] option:selected").attr("tag");
			$("#address").val(address);
		})
    </script>
</body>

</html>