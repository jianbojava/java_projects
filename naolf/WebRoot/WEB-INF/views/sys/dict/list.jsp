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
    <link href="${ctx}/statics/manage/css/plugins/chosen/chosen.css" rel="stylesheet">
    <link href="${ctx}/statics/manage/css/plugins/iCheck/custom.css" rel="stylesheet">

</head>

<body class="gray-bg">
    <div class="wrapper wrapper-content">
        <div class="row animated fadeInRight">
            <div class="col-sm-3">
                <div class="ibox float-e-margins">
                    <div class="ibox-content mailbox-content">
                        <div class="file-manager">
                            <ul class="folder-list m-b-md" style="padding: 0">
                            	<c:forEach items="${list }" var="l">
                                <li>
                                    <a href="javascript:;" data-code="${l.code }" onclick="loadChilds('${l.code}','${l.remark }')"> <i class="fa fa-tag"></i> ${l.name }</a>
                                </li>
                                </c:forEach>
                            </ul>
                            <div class="clearfix"></div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="col-sm-9">
                <div class="mail-box">

                    <table class="table table-hover table-mail">
                        <thead>
                            <tr>
                                <th>名称</th>
                                <th>值</th>
                                <th>操作</th>
                                <th>最后修改时间</th>
                            </tr>
                        </thead>
                        <tbody>
                            
                        </tbody>
                    </table>
                    
                    <form method="post" class="form-horizontal" style="display:none;">
                       <div class="form-group">
                            <div class="col-sm-12">
                                <script type="text/plain" id="content"></script>
                                <div class="hr-line-dashed"></div>
                                <div class="col-sm-5 col-sm-offset-4" style="text-align: center;">
                                 <button class="btn btn-primary" type="button" onclick="updateEditor()">立即更新</button>
                             </div>
                            </div>
                        </div>
                    </form>
                    
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
    <script src="${ctx}/statics/manage/js/plugins/chosen/chosen.jquery_.js"></script>
    <script src="${ctx}/statics/manage/js/common.js"></script>
    <script src="${ctx}/statics/manage/plugins/ueditor-1.4.3/ueditor.config.js"></script>
	<script src="${ctx}/statics/manage/plugins/ueditor-1.4.3/ueditor.all.js"></script>
    <script>
        $(document).ready(function(){$(".i-checks").iCheck({checkboxClass:"icheckbox_square-green",radioClass:"iradio_square-green"});loadChilds($(".folder-list a:first").data("code"));});
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
		     initialFrameHeight:300,
		     //关闭elementPath
		     elementPathEnabled:false
	    })
    	
    	var dictType;
    	function loadChilds(code,remark){
    		dictType=remark;
    		if(remark=="注册协议"||remark=="退改规则"||remark=="充电接口说明"||remark=="终端状态说明"){
    			$("table").hide();
    			$("form").show();
    		}else{
    			$("form").hide();
    			$("table").show();
    		}
    		var index = layer.load(0); //换了种风格
    		$.getJSON("${ctx}/manage/dict/childs/"+code,function(data){
    			$("tbody").empty();
    			if(data!=null&&data.length>0){
    				if(remark=="注册协议"||remark=="退改规则"||remark=="充电接口说明"||remark=="终端状态说明"){
		    			ue.setContent(data[0].content);
		    		}else{
		    			var html="";
	    				for(var i=0;i<data.length;i++){
	    					html+="<tr class=\"read\">"+
	                                "<td class=\"mail-subject\"><a href=\"javascript:;\">"+data[i].name+"</a></td>"+
	                                "<td class=\"mail-subject\"><input type=\"text\" class=\"form-control input-sm\" placeholder=\""+data[i].remark+"\" value=\""+data[i].content+"\"></td>";
	                        if(${isPerm}==0){
	                        	html+="<td class=\"mail-subject\"><button class=\"btn btn-primary\" data-code=\""+data[i].code+"\" type=\"button\" onclick=\"updateDict(this)\"><i class=\"fa fa-check\"></i>&nbsp;提交</button></td>";
	                        }
	                        html+="<td class=\"mail-date\">"+new Date(data[i].modify_date).format('yyyy-MM-dd hh:mm:ss')+"</td></tr>";
	    				}
	    				$("tbody").html(html);
		    		}
    				layer.close(index);
    			}
    		})
    	}
    	function updateDict(o){
    		var code=$(o).data("code");
    		var content=$(o).parent().prev().find("input").val();
    		if($.trim(content)==""){
		  		layer.msg('内容不能为空', function(){});
		  		return false;
		  	}
		  	layer.confirm('确认要操作吗？', function(index){
			  layer.close(index);
			  $.post("${ctx}/manage/dict/update",{"code":code,"content":$.trim(content)},function(data){
			  		if(data.code="0"){
			  			layer.msg("修改成功", {
							icon : 1
						});
			  		}else{
			  			layer.msg("修改失败", {
							icon : 2
						});
			  		}
			  	},"json")
			});	
    	}
    	
    	function updateEditor(){
    		var code;
    		if(dictType=="注册协议")code="REGISTER_AGREEMENT";
    		if(dictType=="退改规则")code="BACK_CHANGE_RULE";
    		if(dictType=="充电接口说明")code="CONNECT_TYPE_DESCRIPT";
    		if(dictType=="终端状态说明")code="CONNECT_STATUS_DESCRIPT";
    		var content=ue.getContent();
    		if($.trim(content)==""){
		  		layer.msg('内容不能为空', function(){});
		  		return false;
		  	}
		  	layer.confirm('确认要操作吗？', function(index){
			  layer.close(index);
			  $.post("${ctx}/manage/dict/update",{"code":code,"content":content},function(data){
			  		if(data.code="0"){
			  			layer.msg("修改成功", {
							icon : 1
						});
			  		}else{
			  			layer.msg("修改失败", {
							icon : 2
						});
			  		}
			  	},"json")
			});	
    	}
    </script>
</body>

</html>