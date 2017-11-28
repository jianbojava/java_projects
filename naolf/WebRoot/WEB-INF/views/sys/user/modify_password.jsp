<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html lang="en" class=" ">
<head>
<meta charset="utf-8" />
<title>微贝</title>
<meta name="description" content="app, web app, responsive, admin dashboard, admin, flat, flat ui, ui kit, off screen nav" />
<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1" />
<link type="text/css" rel="stylesheet" href="${ResStatic }/static/css/bootstrap.css" />
<link type="text/css" rel="stylesheet" href="${ResStatic }/static/css/app.css" />
<link type="text/css" rel="stylesheet" href="${ResStatic }/static/css/base.css">
<link type="text/css" rel="stylesheet" href="${ResStatic }/static/css/button.css">
<link type="text/css" rel="stylesheet" href="${ResStatic }/static/css/font-awesome.min.css">
<link type="text/css" rel="stylesheet" href="${ResStatic }/static/plugin/ztree/zTreeStyle.css">
<script type="text/javascript" src="${ResStatic }/static/js/jquery-1.8.3.min.js"></script>
<script type="text/javascript" src="${ResStatic }/static/js/base.js"></script>
<script type="text/javascript" src="${ResStatic }/static/js/bootstrap.js"></script> 
<script type="text/javascript" src="${ResStatic }/static/js/app.js"></script> 
<script type="text/javascript" src="${ResStatic }/static/js/app.plugin.js"></script>
<script type="text/javascript" src="${ResStatic }/static/js/jquery.form.js"></script>
<script type="text/javascript" src="${ResStatic }/static/js/Validform_v5.3.2_min.js"></script>
</head>
<body>
        <section class="wrapper">
          <div class="m-b"> <span class="h3 font-thin"><i class="i i-arrow-left3"></i></span> </div>
          <section class="panel panel-default">
            <header class="panel-heading">修改个人信息</header>
            <div class="row wrapper Padd50">
              <div class="col-sm-12 m-b-xs"> 
                 <form id="fileForm"  method="post" enctypey="multipart/form-data"  onsubmit="return false">
                      <div class="row wrapper ">
                       <div class="col-sm-12 form-group Btm10">
                       <label class="col-sm-2 Wrap5">原密码:</label><div class="col-sm-5"><input type="password" class="form-control" id="oldpwd" name="password" placeholder="原密码" datatype="*1-32" nullmsg="输入不可以为空" errormsg="格式错误">
                           <div class="Validform_checktip"></div>
                         </div>
                       </div>
                       <div class="col-sm-12 form-group Btm10">
                       <label class="col-sm-2 Wrap5">新密码:</label><div class="col-sm-5"><input type="password" class="form-control" id="newpwd" name="newPassword" placeholder="新密码" datatype="*1-32" nullmsg="输入不可以为空" errormsg="格式错误">
                          <div class="Validform_checktip"></div>
                         </div>
                       </div>
                       <div class="col-sm-12 form-group Btm10">
                       <label class="col-sm-2 Wrap5">确认密码:</label><div class="col-sm-5"><input type="password" class="form-control" id="confpwd" placeholder="确认密码" datatype="*1-32"  recheck="newPassword" nullmsg="输入不可以为空" errormsg="两次密码不一致">
                           <div class="Validform_checktip"></div>
                         </div>
                       </div>
                       <div class="col-sm-12 form-group Btm10">
                       <label class="col-sm-2 Wrap5 line100">上传头像:</label>
                       <div class="col-sm-5">
                       
                       <!--<input type="file" id="fileElem"  multiple accept="image/*"  onchange="handleFiles(this)">-->
                       <div class="fileInputContainered title_l" id="hpty">
                       <input type="file"  name="file" class="fileInputed" id="doc" multiple  style="width:150px;" onchange="javascript:setImagePreviews();" accept="image/*" />
                        </div>
                        
                       <div id="dd" style=" width:100px;">
                       		<c:if test="${!empty user.head_img}"><img id="img" src="${user.head_img}"/></c:if>
                       		<c:if test="${empty user.head_img}"><img src="${ResStatic }/static/img/headdefault.png" /></c:if>
                       </div>
                       
                       </div>
                       </div>
                       <div class="col-sm-7 form-group Btm10 Change-foot Mtop50">
                        <button type="button" class="button button-rounded button-primary" id="surebtn" style="margin-right:20px;">确认</button>
                        <button type="button" class="button button-rounded" id="Re-wpt">取消</button>
                       </div>
                      </div></form>
                      
               </div>
            </div>
          </section>
        </section>
        
<!--表单页面列表-->



<!--表单页面列表-->

<!--预览-->
<div class="modal fade custom-width" id="molypt">
  <div class="modal-dialog" style="width: 30%">
    <div class="modal-content" style="background:none; border:none; box-shadow:none;">
      <div class="modal-body Padd0">
        <section>
          <div class="row wrapper">
              <div class="col-sm-12 form-group Btm10">
                 <div class="mobile-qT">
                   <div id="iframe-wrap" class="mobile-width-2">
                     <iframe id="iframe" src="Yang/index.html" frameborder="0" width="100%"></iframe>
                   </div>
                  </div>
              </div>
          </div>
        </section>
      </div>
      
    </div>
  </div>
</div>
<!--预览-->

<!--预览微信功能-->
<div class="modal fade custom-width" id="molywt">
  <div class="modal-dialog" style="width: 30%" >
    <div class="modal-content" style="background:none; border:none; box-shadow:none;">
      <div class="modal-body Padd0">
        <section>
          <div class="row wrapper">
              <div class="col-sm-12 form-group Btm10">
                 <div class="mobile-qT">
                   <div id="iframe-wrap" class="mobile-width-2">
                     <iframe id="iframe" src="weixin/1.html" frameborder="0" width="100%"></iframe>
                   </div>
                  </div>
              </div>
          </div>
        </section>
      </div>
      
    </div>
  </div>
</div>
<!--预览微信功能-->
<script type="text/javascript">
$(function(){
	 $("#surebtn").click(function() {
	    var err_len=$(".Validform_error").length;
				if(err_len>0){
				  return;
				}
	// 		if($("#updateForm input[type='file']").val()==""){
	// 			alert("先选择上传文件");
	// 			return;
	// 		}
			var options = {
				url : "${ctx}/manager/user/changpwd",
				type : "post",
				dataType: "json",
				success : function(data) {
					if(data.code=="0"){
						window.parent.location.href="${ctx }/manager/logout";
					}else{
						alert(data.message);
					}
					//清空file内容
					$("#fileForm input[type='file']").val()=="";
				}
			};
			$("#fileForm").ajaxSubmit(options);
	}); 
})
//提交
	function checkInput(){
		if($("#newpwd").val()!=$("#confpwd").val()){
			alert("两次密码输入不一致")
			return false;
		};
		if($("#confpwd").val().trim()==""){
		  alert("新密码不可以为空");
		  return  false;
		}
		return true;
	};
    //下面用于多图片上传预览功能
    function setImagePreviews(avalue) {
        var docObj = document.getElementById("doc");
        var dd = document.getElementById("dd");
        dd.innerHTML = "";
        var fileList = docObj.files;
        for (var i = 0; i < fileList.length; i++) {            

            dd.innerHTML += "<div style='float:left' > <img id='img" + i + "'  /> </div>";
            var imgObjPreview = document.getElementById("img"+i); 
            if (docObj.files && docObj.files[i]) {
                //火狐下，直接设img属性
                imgObjPreview.style.display = 'block';
                imgObjPreview.style.width = '100px';
                imgObjPreview.style.height = '100px';
                //imgObjPreview.src = docObj.files[0].getAsDataURL();
                //火狐7以上版本不能用上面的getAsDataURL()方式获取，需要一下方式
                imgObjPreview.src = window.URL.createObjectURL(docObj.files[i]);
            }
            else {
                //IE下，使用滤镜
                docObj.select();
                var imgSrc = document.selection.createRange().text;
                alert(imgSrc)
                var localImagId = document.getElementById("img" + i);
                //必须设置初始大小
                localImagId.style.width = "150px";
                localImagId.style.height = "180px";
                //图片异常的捕捉，防止用户修改后缀来伪造图片
                try {
                    localImagId.style.filter = "progid:DXImageTransform.Microsoft.AlphaImageLoader(sizingMethod=scale)";
                    localImagId.filters.item("DXImageTransform.Microsoft.AlphaImageLoader").src = imgSrc;
                }
                catch (e) {
                    alert("您上传的图片格式不正确，请重新选择!");
                    return false;
                }
                imgObjPreview.style.display = 'none';
                document.selection.empty();
            }
        }  

        return true;
    }
</script>
</body>
</html>