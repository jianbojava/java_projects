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
	                                <label class="col-sm-2 control-label">会员</label>
	                                <div class="col-sm-9">
	                                    <select data-placeholder="选择需要充值的会员..." class="chosen-select" tabindex="2" name="user_id" onchange="userchg()">
	                                    	<option value="">选择需要充值退款的会员...</option>
		                                    	  <c:forEach items="${member}" var="m">
		                                    		  <option value="${m.id }">【编号:${m.number}】【名称:${m.name}】</option>
		                                    	  </c:forEach>
			                            </select>
	                                </div>
	                            </div>
	                         
	                        <div class="hr-line-dashed"></div>
							<div class="form-group">
                                <label class="col-sm-2 control-label">充值流水号（可选）</label>
                                <div class="col-sm-9">
                                    <input type="text" class="form-control" name="journal_id" onblur="journal_blur(this)">
                                </div>
                            </div>	                        
	                            
	                        <div class="hr-line-dashed"></div>
	                        <div class="form-group">
	                        	<div class="col-sm-2"> </div>
                                <label class="col-sm-3 control-label">用户积分</label>
                                <div class="col-sm-1"> </div>
                                <label class="col-sm-4 control-label">输入需要退款积分</label>
                            </div>
                            <div class="form-group">
	                        	<label class="col-sm-2 control-label">未结业积分</label>
                                <div class="col-sm-3">
                                    <input type="text" class="form-control" disabled="true" name="BKZ_V">
                                </div>
                                <div class="col-sm-1"> </div>
                                <div class="col-sm-4">
                                    <input type="text" class="form-control" name="t_BKZ" >
                                </div>
                            </div>
                            <div class="form-group">
	                        	<label class="col-sm-2 control-label">结业积分</label>
                                <div class="col-sm-3">
                                    <input type="text" class="form-control" disabled="true" name="KZ_V">
                                </div>
                                <div class="col-sm-1"> </div>
                                <div class="col-sm-4">
                                    <input type="text" class="form-control" name="t_KZ">
                                </div>
                            </div>
                            <div class="form-group">
	                        	<label class="col-sm-2 control-label">可兑换积分</label>
                                <div class="col-sm-3">
                                    <input type="text" class="form-control" disabled="true" name="KD_V">
                                </div>
                                <div class="col-sm-1"> </div>
                                <div class="col-sm-4">
                                    <input type="text" class="form-control" name="t_KD">
                                </div>
                            </div>
                            
	                       
                            <div class="hr-line-dashed"></div>
                           	<div class="form-group">
                                <label class="col-sm-2 control-label">充值类型</label>
                                <div class="col-sm-9">
                                    <input type="text" class="form-control" name="reason_desc" disabled="true">
                                </div>
                            </div>
                           	<div class="form-group">
                                <label class="col-sm-2 control-label">被推荐人</label>
                                <div class="col-sm-9">
                                    <input type="text" class="form-control" name="refer" disabled="true">
                                    <input type="hidden" class="form-control" name="refer" disabled="true">
                                </div>
                            </div>
                           	<div class="form-group">
                                <label class="col-sm-2 control-label">充值备注</label>
                                <div class="col-sm-9">
                                    <input type="text" class="form-control" name="reason" disabled="true">
                                </div>
                            </div>
                           	<div class="form-group">
                                <label class="col-sm-2 control-label">充值日期</label>
                                <div class="col-sm-9">
                                    <input type="text" class="form-control" name="createdate" disabled="true">
                                </div>
                            </div>
                            
                           <div class="form-group" id="jf">
                                <label class="col-sm-2 control-label">结业积分</label>
                                <div class="col-sm-2">
                                	<input type="text" class="form-control"  name = 'KZscore'>
                                </div>
                                <label class="col-sm-2 control-label">可兑换积分</label>
                                <div class="col-sm-2">
                                	<input type="text" class="form-control"  name = 'KDscore'>
                                </div>
                            </div>
                            <!-- add by wjx 20170228 end-->
                            <div class="hr-line-dashed"></div>
                            <div class="form-group">
                                <div class="col-sm-4 col-sm-offset-4" style="text-align: center;">
                                    <button class="btn btn-primary" type="button" onclick="save_ret()">保存内容</button>
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
    $(document).ready(
    		function(){
    			$(".i-checks").iCheck({checkboxClass:"icheckbox_square-green",radioClass:"iradio_square-green"});
    			$("#jf").find("input[type='text']").attr("disabled",true);
    			
    		}
    );
    
    function userchg(){
    	var userid = $("[name='user_id']").val();
    	//清空输入
    	$(".ibox-content").find("input[type='text']").val("");
    	var params = {user_id:userid};
    	
    	//获取累计积分
    	if(userid!=null && userid!=""){
    		$.ajax({
				url:"${ctx}/manage/userscoreinfo/userscore",
				type: "POST",
				data: params,
				success: function(msg){
					console.log(msg);
					var p = JSON.parse(msg);
					
					if(p.code=="0"){
						$("[name='BKZ_V']").val(p.data.bKZ_SCORE);
						$("[name='KZ_V']").val(p.data.kZ_SCORE);
						$("[name='KD_V']").val(p.data.kD_SCORE);
					}else{
						opt_error("没有该会员积分记录");
					}
				},
				dataType: "text",
				contentType: "application/x-www-form-urlencoded"
			});
    	}
    }
    
    function journal_blur(){
    	var val = $("[name='journal_id']").val();
    	
    	if(val==null||val==""){
    		return ;
    	}
    	
    	var userid = $("[name='user_id']").val();
    	
    	if(userid==null|| userid==""){
    		$("[name='journal_id']").val("");
    		opt_error("先输入会员");    		
    		return ;
    	}
    	
    	var params = {id:$("[name='journal_id']").val(),user_id:userid};
    	$.ajax({
			url:"${ctx}/manage/recharge/querybyid",
			type: "POST",
			data: params,
			success: function(msg){
				
				var p = JSON.parse(msg);
				console.log(p);
				if(p.code=="0"){
					
					$("[name='reason_desc']").val(p.data.reason_type_desc);
					$("[name='refer']").val("["+p.data.refer_number+"]"+p.data.refer_cname);
					$("[name='refer_id']").val(p.data.refer_id);
					$("[name='reason']").val(p.data.reason);
					$("[name='createdate']").val(p.data.create_date);
					$("[name='KZscore']").val(p.data.kZscore);
					$("[name='KDscore']").val(p.data.kDscore);
				}else{
					opt_error(p.msg);
					$("[name='journal_id']").val("");
				}
			},
			dataType: "text",
			contentType: "application/x-www-form-urlencoded"
		});
    }
    
    $('.chosen-select').chosen();
    
    
    function save_ret(){
		var userid = $("[name='user_id']").val();
    	
    	if(userid==null|| userid==""){
    		opt_error("先输入会员");    		
    		return ;
    	}
		var BKZ_V = $("input[name='BKZ_V']").val();
		var KZ_V = $("input[name='KZ_V']").val();
		var KD_V = $("input[name='KD_V']").val();
		
		if(BKZ_V==null
			||KZ_V==null
			||KD_V==null){
			opt_error("没有累计积分，不能退款");    		
			return ;
		}
    	
    	var t_BKZ = $("input[name='t_BKZ']").val();
    	var t_KZ = $("input[name='t_KZ']").val();
    	var t_KD = $("input[name='t_KD']").val();
    	
		if((t_BKZ==null)
				&&(t_KZ==null)
				&&(t_KD==null)){
			opt_error("先输入退款积分");    		
    		return ;
		}
		
		var re = /^[1-9]+[0-9]*]*$/;
    	if(t_BKZ==null||t_BKZ==""){
    		t_BKZ= 0;
    	}else if (!re.test(t_BKZ)){
    		opt_error("退款积分必须是整数");    		
    		return ;
    	}
    	
    	if(t_KZ==null||t_KZ==""){
    		t_KZ= 0;
    	}else if (!re.test(t_KZ)){
    		opt_error("退款积分必须是整数");    		
    		return ;
    	}
    	
    	if(t_KD==null||t_KD==""){
    		t_KD= 0;
    	}else if (!re.test(t_KD)){
    		opt_error("退款积分必须是整数");    		
    		return ;
    	}
    	
		if(parseFloat(t_BKZ)>parseFloat(BKZ_V)
				||parseFloat(t_KZ)>parseFloat(KZ_V)
				||parseFloat(t_KD)>parseFloat(KD_V)){
			opt_error("退款积分不能大于累计积分");    		
    		return ;
		}
    	
		t_BKZ=-1*t_BKZ;
		t_KZ=-1*t_KZ;
		t_KD=-1*t_KD;
		
		var reason = '[充值流水号：'+$("[name='journal_id']").val()+"]"+$("[name='reason']").val();
    	
    	$(".btn").attr("disabled",true);
        	$(".btn-primary").attr("disabled",true);//防止多次提交
		var params={
				"user_id":userid,
				"reason_type":4,
				"refer_id":$("[name='refer_id']").val(),
				"reason": reason,
				"tickets":null,
				"recharge_type":1,
				"KZscore":t_KZ,
				"KDscore":t_KD,
				"BKZscore":t_BKZ,
					};
		$.ajax({
			url:"${ctx}/manage/recharge/returnsave",
			type: "POST",
			data: JSON.stringify(params),
			success: function(data){
				if(data.code=="0"){
					opt_success("添加成功",0);
				}else{
					opt_error("添加失败");
        			$(".btn-primary").attr("disabled",false);//防止多次提交
				}
			},
			dataType: "json",
			contentType: "application/json"
		});
    }
    </script>
</body>

</html>