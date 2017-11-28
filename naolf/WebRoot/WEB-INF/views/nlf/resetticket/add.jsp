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
    <link href="${ctx}/statics/manage/css/plugins/awesome-bootstrap-checkbox/awesome-bootstrap-checkbox.css" rel="stylesheet">
    

</head>

<body>
    <div class="row animated fadeInRight">
            <div class="col-sm-12">
                <div class="ibox float-e-margins">
                    <div class="ibox-content">
                        <form action="" method="post" class="form-horizontal">
                            <div class="hr-line-dashed"></div>
                            <div class="form-group">
	                                <label class="col-sm-2 control-label">编号规则</label>
	                                <div class="col-sm-9">
	                                    <select data-placeholder="选择需要充值的会员..." class="chosen-select" tabindex="2" name="user_id">
	                                    	<option value="">选择需要充值的会员...</option>
		                                    	  <c:forEach items="${member}" var="m">
		                                    		  <option value="${m.id }">【编号:${m.number}】【名称:${m.name}】</option>
		                                    	  </c:forEach>
			                            </select>
	                                </div>
	                            </div>
	                        <div class="hr-line-dashed"></div>
	                        <div class="form-group">
                                <label class="col-sm-2 control-label">充值类型</label>
                                <div class="col-sm-9">
                                	<div class="radio radio-success radio-inline">
                                        <input type="radio" id="inlineRadio1" value="1" name="reason_type" checked="checked">
                                        <label for="inlineRadio1"> 自购</label>
                                    </div>
                                    <div class="radio radio-success radio-inline">
                                        <input type="radio" id="inlineRadio2" value="2" name="reason_type" >
                                        <label for="inlineRadio2"> 奖励</label>
                                    </div>
                                    <div class="radio radio-success radio-inline">
                                        <input type="radio" id="inlineRadio3" value="3" name="reason_type" >
                                        <label for="inlineRadio3"> 其他</label>
                                    </div>
                                </div>
                            </div>  
                            <div class="form-group" id = "d_refer">
                                <label class="col-sm-2 control-label">被推荐人</label>
                                <div class="col-sm-9">
                                	<select id="refer_id" data-placeholder="选择需要充值的会员..." class="chosen-select" tabindex="2" name="refer_id">
	                                    	<option value="">选择被推荐人...</option>
		                                   	<c:forEach items="${member}" var="m">
		                                    	<option value="${m.id }">【编号:${m.number}】【名称:${m.name}】</option>
		                                   	</c:forEach>
			                         </select>
                                </div>
                            </div>  
	                        <div class="hr-line-dashed"></div>
                           <div class="form-group">
                                <label class="col-sm-2 control-label">充值类别</label>
                                <div class="col-sm-9">
                                	<div class="radio radio-success radio-inline">
                                        <input type="radio" id="inlineRadio5" value="0" name="recharge_type" checked="">
                                        <label for="inlineRadio5"> 卡券</label>
                                    </div>
                                    <div class="radio radio-success radio-inline">
                                        <input type="radio" id="inlineRadio6" value="1" name="recharge_type" >
                                        <label for="inlineRadio6"> 积分</label>
                                    </div>
                                </div>
                            </div>    
                            <div class="hr-line-dashed"></div>
                           <div class="form-group" >
                                <label class="col-sm-2 control-label">选择卡券</label>
                                <label class="col-sm-3 control-label">卡券名称</label>
                                <label class="col-sm-3 control-label" style="text-align: center">卡券数量</label>
                            </div>
                            <div id="tdiv" >
	                            <c:forEach items="${ticket}" var="t">
	                            <div class="hr-line-dashed"></div>
	                                <div class="form-group">
		                                <label class="col-sm-2 control-label"><c:choose><c:when test="${t.type==0}">卡</c:when><c:otherwise>券</c:otherwise></c:choose></label>
		                                <label class="col-sm-3 control-label">${t.name}</label>
		                                <div class="col-sm-3">
		                                    <input type="text" class="form-control" placeholder="输入数量" value="" id="${t.id}" tag="${t.rule_id}">
		                                </div>
		                            </div>
		                             
	                            </c:forEach>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-2 control-label">充值理由</label>
                                <div class="col-sm-9">
                                    <input type="text" class="form-control" name="reason">
                                </div>
                            </div>
                            <!-- add by wjx 20170228 begin-->
                            <div class="hr-line-dashed"></div>
                           <div class="form-group" id="jf">
                                <label class="col-sm-2 control-label">结业积分</label>
                                <div class="col-sm-2">
                                	<input type="text" class="form-control" placeholder="输入积分"  name = 'KZscore'>
                                </div>
                                <label class="col-sm-2 control-label">可兑换积分</label>
                                <div class="col-sm-2">
                                	<input type="text" class="form-control" placeholder="输入积分"  name = 'KDscore'>
                                </div>
                            </div>
                            <!-- add by wjx 20170228 end-->
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
    <script src="${ctx}/statics/manage/js/plugins/chosen/chosen.jquery.js"></script>
    <script src="${ctx}/statics/manage/js/jquery.form.js"></script>
    <script src="${ctx}/statics/manage/js/common.js"></script>
    
    <script>
         $(document).ready(function(){$(".i-checks").iCheck({checkboxClass:"icheckbox_square-green",radioClass:"iradio_square-green"});
	         $("#jf").find("input[type='text']").attr("disabled",true);
	         $("#d_refer").hide();
	         
	         $(":radio[name='recharge_type']").click(function(){
	   			if($(this).val()==0){
	   				$("#tdiv").find("input[type='text']").attr("disabled",false);
	   				$("#jf").find("input[type='text']").attr("disabled",true);
	   					
	   			}else{
	   				$("#tdiv").find("input[type='text']").attr("disabled",true);
	   				$("#jf").find("input[type='text']").attr("disabled",false);
	   			}
  			 });
	         
	         $(":radio[name='reason_type']").click(function(){
		   			if($(this).val()==2){
		   				
		   				$("#d_refer").show();
		   					
		   			}else{
		   				$("#d_refer").hide();
		   			}
	  			 });
         });
         
        function add(){
            var tickets=[];
            $("#tdiv input").each(function(){
            	if($(this).val()!=null&&$(this).val()!=""){
              var number=$(this).val();
            	}
              if(number!=null){
                tickets.push({id:$(this).attr("id"),rule_id:$(this).attr("tag"),num:number})
              }
            })
            var user_id = $("[name='user_id']").val();
			if(user_id==null||user_id==""){
				opt_error("请选择会员");
				return;
			}
			
			var reason_type = $("[name='reason_type']:checked").val();
            var refer_id = $("[name='refer_id']").val();
			
			if(reason_type==2&&(refer_id==null||refer_id=="")){
				opt_error("奖励充值时，必须输入被推荐人");
				return;
			}
			
			var re = /^[1-9]+[0-9]*]*$/;
			var recharge_type = $("[name='recharge_type']:checked").val();
			if (recharge_type=='1'){
				var KZscore = $("[name='KZscore']").val();
				var KDscore = $("[name='KDscore']").val();
				if(KZscore=="" && KDscore==""){
					opt_error("请输入积分");
					return;
				}
				if (( KZscore!="" && !re.test(KZscore))||( KDscore!="" && !re.test(KDscore))){
					opt_error("积分必须为整数");
					return;
				}
				
				
			}
			
			$(".btn").attr("disabled",true);
        	$(".btn-primary").attr("disabled",true);//防止多次提交
			var params={
					"user_id":user_id,
					"reason_type":reason_type,
					"refer_id":refer_id,
					"reason":$("[name='reason']").val(),
					"tickets":tickets,
					"recharge_type":$("[name='recharge_type']:checked").val(),
					"KZscore":$("[name='KZscore']").val(),
					"KDscore":$("[name='KDscore']").val(),
						};
				$.ajax({
				url:"${ctx}/manage/recharge/add",
				type: "POST",
				data: JSON.stringify(params),
				success: function(data){
					if(data.code=="0"){
						opt_success("添加成功",0);
					}else{
						opt_error("添加失败");
        				$(".btn-primary").attr("disabled",true);//防止多次提交
					}
				},
				dataType: "json",
				contentType: "application/json"
			});
        }
        
         $('.chosen-select').chosen();
    </script>
</body>

</html>