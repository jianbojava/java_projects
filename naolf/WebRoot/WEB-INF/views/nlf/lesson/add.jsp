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
    <link href="${ctx}/statics/manage/css/plugins/switchery/switchery.css" rel="stylesheet">
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
                                <label class="col-sm-2 control-label">课程名称</label>
                                <div class="col-sm-9">
                                    <input type="text" class="form-control" placeholder="课程名称" name="name" value="${lesson.name}">
                                </div>
                            </div>
                           <div class="form-group" id="typeDiv" >
                            <div class="hr-line-dashed"></div>
                                <label class="col-sm-2 control-label">类型</label>
                                <div class="col-sm-9">
                                	<div class="radio radio-success radio-inline">
                                        <input type="radio" id="inlineRadio1" value="0" name="type" checked="">
                                        <label for="inlineRadio1"> 集中+进阶训练</label>
                                    </div>
                                    <div class="radio radio-success radio-inline">
                                        <input type="radio" id="inlineRadio2" value="1" name="type">
                                        <label for="inlineRadio2"> 集中训练</label>
                                    </div>
                                </div>
                            </div>
                            
                            <div class="form-group" id="typeDivs">
                            <div class="hr-line-dashed"></div>
                                <label class="col-sm-2 control-label">进阶课时</label>
                                <div class="col-sm-9">
                                    <input type="text" class="form-control" placeholder="进阶课时" name="train" value="${lesson.train}">
                                </div>
                            </div>
                            <div class="hr-line-dashed"></div>
                            <div class="form-group">
	                                <label class="col-sm-2 control-label">编号规则</label>
	
	                                <div class="col-sm-9">
	                                    <select data-placeholder="选择编号规则..." class="chosen-select" tabindex="2" name="rule">
	                                    	<option value="">请选择编号规则</option>
		                                    	  <c:forEach items="${rule}" var="r">
		                                    		  <option value="${r.id }">${r.sn_header}${r.sn_start}${r.sn_middle}${r.sn_end}</option>
		                                    	  </c:forEach>
			                            </select>
	                                </div>
	                            </div>
                            <div class="hr-line-dashed"></div>
                            <div class="form-group">
                                <label class="col-sm-2 control-label">课程分类</label>
                                <div class="col-sm-9">
                                    <input type="text" class="form-control" placeholder="课程分类" name="catname" value="${lesson.catname}">
                                </div>
                            </div>
                            <div class="hr-line-dashed"></div>
                            <div class="form-group">
                                <label class="col-sm-2 control-label">集中课时</label>
                                <div class="col-sm-9">
                                    <input type="text" class="form-control" placeholder="集中课时" name="period" value="${lesson.period}">
                                </div>
                            </div>
                            <div class="hr-line-dashed"></div>
                            <div class="form-group">
                                <label class="col-sm-2 control-label">适用年龄</label>
                                <div class="col-sm-9">
                                    <input type="text" class="form-control" placeholder="适用年龄" name="userage" value="${lesson.userage}">
                                </div>
                            </div>
                            <!-- 
	                            <div class="hr-line-dashed"></div>
	                            <div class="form-group">
	                                <label class="col-sm-2 control-label">课程特色</label>
	                                <div class="col-sm-9">
	                                    <textarea  class="form-control" rows="5" placeholder="课程特色" name="advantage" >${lesson.advantage}</textarea>
	                                </div>
	                            </div>
                             -->
                            <div class="hr-line-dashed"></div>
                            <div class="form-group">
                                <label class="col-sm-2 control-label">课程特点</label>
                                <div class="col-sm-9">
                                    <textarea class="form-control" id="special" name="special" placeholder="课程特点">${lesson.special}</textarea>
                                </div>
                            </div>
                            <div class="hr-line-dashed"></div>
                            <div class="form-group">
                                <label class="col-sm-2 control-label">费用</label>
                                <div class="col-sm-9">
                                    <input type="text" class="form-control" placeholder="费用" name="price" value="${lesson.price}">
                                </div>
                            </div>
                            <div class="hr-line-dashed"></div>
                            <div class="form-group">
                                <label class="col-sm-2 control-label">启用</label>
                                <div class="col-sm-9">
                                	<input type="checkbox" class="js-switch" name="available" checked="checked"/>
                                </div>
                            </div>
                            <div class="hr-line-dashed"></div>
                            <div class="form-group">
                                <label class="col-sm-2 control-label">图片</label>

                                <div class="col-sm-9">
                                    <input class="fileInput" id="imageUploadify" type="file" name="file" multiple="multiple" accept="image/*"/>
                                     <div class="FildImg" style="display:none;">   
							                 	<img src="" style="width: 60px;height: 60px">
							         </div>
                                </div>
                            </div>
                            <div class="hr-line-dashed"></div>
                            <div class="form-group">
                                <label class="col-sm-2 control-label">课程特色</label>
                                <div class="col-sm-9">
                                    <script type="text/plain" id="content">
    		   	  					</script>
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

    <script src="${ctx}/statics/manage/js/jquery.min.js?v=2.1.4"></script>
    <script src="${ctx}/statics/manage/js/bootstrap.min.js?v=3.3.6"></script>
    <script src="${ctx}/statics/manage/js/content.min.js?v=1.0.0"></script>
    <script src="${ctx}/statics/manage/js/plugins/switchery/switchery.js"></script>
    <script src="${ctx}/statics/manage/js/plugins/iCheck/icheck.min.js"></script>
    <script src="${ctx}/statics/manage/plugins/layer/layer.js"></script>
    <script src="${ctx}/statics/manage/js/plugins/sweetalert/sweetalert.min.js"></script>
    <script src="${ctx}/statics/manage/plugins/uploadify/jquery.uploadify.js"></script>
    <script src="${ctx}/statics/manage/js/jquery.form.js"></script>
    <script src="${ctx}/statics/manage/js/common.js"></script>
    <script src="${ctx}/statics/manage/js/plugins/chosen/chosen.jquery.js"></script>
    <script src="${ctx}/statics/manage/plugins/ueditor-1.4.3/ueditor.config.js"></script>
	<script src="${ctx}/statics/manage/plugins/ueditor-1.4.3/ueditor.all.js"></script>
    
    <script>
    var ue=UE.getEditor('content',{
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
        function add(){
			var available=1;
			if($("[name='available']").is(":checked"))available=0;
			var name=$.trim($("[name='name']").val());
			if(name==null||name==""){
			   opt_error("课程名称不可以为空!");
			   return;
			}
			var price=$.trim($("[name='price']").val());
			if(price==null||price==""){
			   opt_error("课程费用不可以为空!");
			   return;
			}
			var params={"name":name,
						"type":$("[name='type']:checked").val(),
						"rule_id":$("[name='rule']").val(),
						"catname":$("[name='catname']").val(),
						"train":$("[name='train']").val(),
						"userage":$("[name='userage']").val(),
						"period":$("[name='period']").val(),
						"price":price,
						"enabled":available,
						"image":$(".FildImg img").attr("src"),
						"advantage":$("[name='advantage']").val(),
						"special":$("[name='special']").val(),
						"content":ue.getContent()
						};
			$(".btn-primary").attr("disabled",true);//防止多次提交
        	$.post("${ctx}/manage/lesson/add",params,function(data){
					if(data.code=="0"){
						opt_success("添加成功",0);
					}else if(data.code=="2"){
					    opt_error("添加失败,名称已存在!");
					$(".btn-primary").attr("disabled",false);//防止多次提交
					}else{
						opt_error("添加失败");
					$(".btn-primary").attr("disabled",false);//防止多次提交
					}
				},"json")
        }

        $("[name='type']").change(function(){
			if($(this).attr("value")=="0"){
				$("#typeDivs").show();
			}else{
				$("#typeDivs").hide();
			}
        })
        $('.chosen-select').chosen();
    </script>
</body>

</html>