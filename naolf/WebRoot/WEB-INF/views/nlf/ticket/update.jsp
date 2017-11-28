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
    <link href="${ctx}/statics/manage/css/plugins/awesome-bootstrap-checkbox/awesome-bootstrap-checkbox.css" rel="stylesheet">
    
    

</head>

<body>
    <div class="row animated fadeInRight">
            <div class="col-sm-12">
                <div class="ibox float-e-margins">
                    <div class="ibox-content">
                        <form action="" method="post" class="form-horizontal">
                            <div class="form-group">
                                <label class="col-sm-2 control-label">卡券名称</label>
                                <div class="col-sm-9">
                                    <input type="text" class="form-control" name="name" value="${ticket.name}">
                                </div>
                            </div>
                            <div class="hr-line-dashed"></div>
                           <div class="form-group">
                                <label class="col-sm-2 control-label">类型</label>
                                <div class="col-sm-9">
                                	<div class="radio radio-success radio-inline">
                                        <input type="radio" id="inlineRadio1" value="0" name="type" checked="">
                                        <label for="inlineRadio1"> 卡</label>
                                    </div>
                                    <div class="radio radio-success radio-inline">
                                        <input type="radio" id="inlineRadio2" value="1" name="type" <c:if test="${ticket.type==1}">checked</c:if>>
                                        <label for="inlineRadio2"> 券</label>
                                    </div>
                                </div>
                            </div>
                            <div class="hr-line-dashed"></div>
                            <div class="form-group">
	                                <label class="col-sm-2 control-label">编号规则</label>
	
	                                <div class="col-sm-9">
	                                    <select data-placeholder="选择编号规则..." class="chosen-select" tabindex="2" name="rule">
	                                    	<option value="">请选择编号规则</option>
		                                    	  <c:forEach items="${rules}" var="r">
		                                    		  <option value="${r.id }">${r.sn_header}${r.sn_start}${r.sn_middle}${r.sn_end}</option>
		                                    	  </c:forEach>
			                            </select>
	                                </div>
	                            </div>
                            <div class="hr-line-dashed"></div>
                            <div class="form-group">
                                <label class="col-sm-2 control-label">会员须知</label>
                                <div class="col-sm-9">
                                    <input type="text" class="form-control" name="notice" value="${ticket.notice}">
                                </div>
                            </div>
                            <div class="hr-line-dashed"></div>
                            <div class="form-group">
                                <label class="col-sm-2 control-label">使用规则</label>
                                <div class="col-sm-9">
                                    <input type="text" class="form-control" name="use_rule" value="${ticket.use_rule}">
                                </div>
                            </div>
                            <div class="hr-line-dashed"></div>
                            <div class="form-group">
                                <label class="col-sm-2 control-label">价格</label>
                                <div class="col-sm-9">
                                    <input type="text" class="form-control" name="price" value="${ticket.price}">
                                </div>
                            </div>
                            <div class="hr-line-dashed"></div>
                            <div class="form-group">
                                <label class="col-sm-2 control-label">图片</label>

                                <div class="col-sm-9">
                                    <input class="fileInput" id="imageUploadify" type="file" name="file" multiple="multiple" accept="image/*"/>
                                     <div class="FildImg" <c:if test="${empty ticket.image}"> style="display:none;"</c:if> >   
							                 	<img src="${ticket.image}" style="width: 60px;height: 60px">
							         </div>
                                </div>
                            </div>
                            <div class="hr-line-dashed"></div>
                            <div id="lidsdiv" >
                             <div class="form-group">
	                                <label class="col-sm-2 control-label">包含课程</label>
	
	                                <div class="col-sm-9">
	                                	<c:forEach items="${lessons }" var="lesson">
	                                		<label class="checkbox-inline i-checks"><input type="checkbox" value="${lesson.id }" name="lesson" <c:if test="${fn:indexOf(ticket.lesson_ids,lesson.id)>-1}">checked</c:if>>${lesson.name }</label>
	                                	</c:forEach>
	                                </div>
	                             </div>
	                          </div>
	                          <div id="liddiv" style="display: none"  >
		                             <div class="form-group">
	                                <label class="col-sm-2 control-label">关联课程</label>
	                                <div class="col-sm-9">
	                                   <c:forEach items="${lessons}" var="l" varStatus="index">
	                                	<div class="radio radio-success radio-inline">
	                                        <input type="radio" id="${l.id }" value="${l.id}" <c:if test="${l.id==ticket.lesson_ids}">checked</c:if>  name="mylesson">
	                                        <label for="${l.id }">${l.name}</label>
	                                    </div>
	                                     </c:forEach>
	                                </div>
	                            </div>
                             </div>
                               <div class="hr-line-dashed"></div>
                            <div class="form-group">
                                <div class="col-sm-4 col-sm-offset-4" style="text-align: center;">
                                    <button class="btn btn-primary" type="button" onclick="update()">保存内容</button>
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
    
    <script>
       $('.chosen-select').chosen();
       $("[name='rule']").val('${ticket.rule_id}');
       $(".chosen-select").trigger("chosen:updated");
         $(document).ready(function(){$(".i-checks").iCheck({checkboxClass:"icheckbox_square-green",radioClass:"iradio_square-green"})});
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
	     //radio  0卡，1券（单个课程）
		$("[name='type']").change(function(){
		  /**
			if($(this).attr("value")=="0"){//卡，多课程
				$("#lidsdiv").css("display","block");
				$("#liddiv").css("display","none");
			}else{//券，单课
				$("#lidsdiv").css("display","none");
				$("#liddiv").css("display","block");
			}
			**/
		})
        function update(){
             var lesson_ids;
            var lessons=[];
			$("[name='lesson']:checked").each(function(){
				lessons.push($(this).attr("value"));
			})
			var type=$("[name='type']:checked").val();
			//if(type==1)lesson_ids=$("[name='mylesson']:checked").val();
			lesson_ids=lessons.toString();
			if(lessons==""){
				opt_error("请选择关联的课程");
				return false;
			}
			var name=$.trim($("[name='name']").val());
			if(name==null||name==""){
			   opt_error("卡券名称不可以为空!");
			   return;
			}
			var price=$.trim($("[name='price']").val());
			if(price==null||price==""){
			   opt_error("卡券费用不可以为空!");
			   return;
			}
			 $(".btn-primary").attr("disabled",true);//防止多次提交
			var params={"id":"${ticket.id}",
						"type":type,
						"name":$("[name='name']").val(),
						"rule_id":$("[name='rule']").val(),
						"notice":$("[name='notice']").val(),
						"use_rule":$("[name='use_rule']").val(),
						"image":$(".FildImg img").attr("src"),
						"price":$("[name='price']").val(),
						"lesson_ids":lesson_ids
						};
        	$.post("${ctx}/manage/ticket/update",params,function(data){
					if(data.code=="0"){
						opt_success("更新成功",0);
					}else if(data.code=="2"){
					    opt_error("修改失败,名称已存在!");
					    $(".btn-primary").attr("disabled",false);//防止多次提交
					}else{
						opt_error("更新失败");
						$(".btn-primary").attr("disabled",false);//防止多次提交
					}
				},"json")
        }
    </script>
</body>

</html>