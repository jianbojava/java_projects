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
    

    <title>脑立方--添加未结业历史课程订单</title>
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
    <link href="${ctx}/statics/manage/css/plugins/chosen/chosen.css" rel="stylesheet">
    <link href="${ctx}/statics/manage/css/plugins/datapicker/datepicker3.css" rel="stylesheet">
    <link href="${ctx}/statics/manage/css/plugins/awesome-bootstrap-checkbox/awesome-bootstrap-checkbox.css" rel="stylesheet">

</head>

<body>
    <div class="row animated fadeInRight">
            <div class="col-sm-12">
                <div class="ibox float-e-margins">
                    <div class="ibox-content" >
                        <form action="" method="post" class="form-horizontal" >
	                             <div class="form-group">
	                                <label class="col-sm-2 control-label">添加日期</label>
	                                <div class="col-sm-9">
			                                <input class="form-control" name="create_date" type="text" placeholder="添加日期">
	                                </div>
	                            </div>
                              <div class="hr-line-dashed"></div>
                            <div class="form-group">
	                                <label class="col-sm-2 control-label">会员</label>
	
	                                <div class="col-sm-9">
	                                    <select data-placeholder="选择会员..." class="chosen-select" tabindex="2" name="user" onchange="selectUser()">
	                                    	<option value="">请选择会员</option>
		                                    	  <c:forEach items="${users}" var="u">
		                                    		  <option value="${u.id }" tag="${u.refer_number}" >【编号:${u.number }】【姓名:${u.name }】</option>
		                                    	  </c:forEach>
			                            </select>
	                                </div>
	                            </div>
	                             <div class="hr-line-dashed"></div>
	                              <div class="form-group">
	                                <label class="col-sm-2 control-label">购买课程</label>
	
	                                <div class="col-sm-9">
	                                    <select data-placeholder="选择课程..." class="chosen-select" tabindex="2" name="lesson" onchange="selectLesson()">
	                                    	<option value="" tag="">请选择课程</option>
		                                    	  <c:forEach items="${lessons}" var="l">
		                                    		  <option value="${l.id }" tag="${l.type}">【名称:${l.name}】【价格:￥${l.price}】</option>
		                                    	  </c:forEach>
			                            </select>
	                                </div>
	                            </div>
	                        <div class="hr-line-dashed"></div>
                            <div class="form-group">
                                <label class="col-sm-2 control-label">推荐人编号</label>

                                <div class="col-sm-9">
                                    <input type="text" class="form-control" name="refer_number" readonly="readonly">
                                </div>
                            </div>
                            <div class="hr-line-dashed"></div>
                            <div class="form-group">
                                <label class="col-sm-2 control-label">是否结业</label>
                                <div class="col-sm-9">
                                	<div class="radio radio-success radio-inline">
                                        <input type="radio" id="inlineRadio1" value="0" name="is_end" checked="">
                                        <label for="inlineRadio1"> 未结业 </label>
                                    </div>
                                    <div class="radio radio-success radio-inline">
                                        <input type="radio" id="inlineRadio2" value="1" name="is_end">
                                        <label for="inlineRadio2"> 已结业</label>
                                    </div>
                                </div>
                            </div>
                              <div class="hr-line-dashed"></div>
                            <div id="studiv" style="display: none;background-color: #f5f5f5">
                               <div class="form-group">
	                                <label class="col-sm-2 control-label">孩子</label>
	
	                                <div class="col-sm-9">
	                                    <select data-placeholder="选择孩子..." class="chosen-select" tabindex="2" name="student">
				                          </select>
	                                </div>
	                            </div>
	                             <div class="hr-line-dashed"></div>
	                            <div class="form-group">
	                                <label class="col-sm-2 control-label">剩余复训次数</label>
	
	                                <div class="col-sm-9">
	                                    <input type="text" class="form-control" id="left_review" name="left_review" value="0" placeholder="剩余复训次数">
	                                </div>
	                            </div>
	                         <div class="hr-line-dashed"></div>
	                        </div>
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
    <script src="${ctx}/statics/manage/js/plugins/chosen/chosen.jquery.js"></script>
    <script src="${ctx}/statics/manage/js/plugins/datapicker/bootstrap-datepicker.js"></script>
    <script src="${ctx}/statics/manage/js/jquery.form.js"></script>
    <script src="${ctx}/statics/manage/js/common.js"></script>
    
    <script>
    $(function(){
      $("[name='create_date']").datepicker({
        	format: "yyyy-mm-dd 00:00:00",
        	todayHighlight:true,
       		autoclose: true,
       		todayBtn: true
        });
    })
    function selectStudent(){
         var is_end=$("[name='is_end']:checked").val();//0未结业，1已结业
         var user_id=$("[name='user']").val();//会员id
         var lesson_type=$("[name='lesson'] option:selected").attr("tag");//0:集中训练+进阶训练；1集中训练
         $("[name='student']").html("<option value=\"\">请选择学生</option>"); 
		 $(".chosen-select").trigger("chosen:updated");
		 $("#studiv").css("display","none");
		 $("#left_review").val("0");
		 if(is_end==1&&user_id!=""&&lesson_type=="0"){//已结业+会员+lesson_type0
		   $("#studiv").css("display","")
		   $.post("${ctx}/manage/student/queryByUserId/"+user_id,function(data){
                for(var i=0;i<data.length;i++){
       			   $("[name='student']").append("<option value=\""+data[i].id+"\" hassubinfo=\"true\">"+data[i].name+"</option>");
       		    }
       		$(".chosen-select").trigger("chosen:updated");
             })
		 }
         
    }
      $("[name='is_end']").change(function(){//0未结业，1已结业
			 selectStudent();
				
        })
        function selectUser(){
         var refer_number=$("[name='user'] option:selected").attr("tag");
          $("[name='refer_number']").val(refer_number);
          selectStudent();
        }
        function selectLesson(){
           selectStudent();
        }
        function add(){
            var user_id=$("[name='user']").val();
            var conn_id=$("[name='lesson']").val();
            var student=$("[name='student']").val();
            var left_review=$("#left_review").val();
            if($("[name='refer_number']").val()==""){
               opt_error("操作失败您还没有推荐人");
               return;
            }
            if(conn_id=="") {
               opt_error("请选择课程");
               return;
            }
            if(user_id==""){
               opt_error("请选择会员");
               return;
            }
            var is_end=$("[name='is_end']:checked").val();//0未结业，1已结业
            var lesson_type=$("[name='lesson'] option:selected").attr("tag");//0:集中训练+进阶训练；1集中训练
			if(is_end==1&&lesson_type=="0"){
			   if(student==""){
			      opt_error("请选择孩子");
			      return;
			   }
			   var r = /^\+?[1-9][0-9]*$/;//正整数验证
			   if(left_review!=0&&(!r.test(left_review))){
			      opt_error("剩余复训次数输入有误");
			     return;
			   }
			}
			var params={"user_id":user_id,"conn_id":conn_id,"type":0,"create_date":$("[name='create_date']").val()}
			var post_url="${ctx}/manage/norder/addnofinish?is_end="+is_end+"&student_id="+student+"&left_review="+left_review;//添加已结业历史订单
        	 $(".btn-primary").attr("disabled",true);//防止多次提交
        	$.post(post_url,params,function(data){
			    if(data.code=="1"){
					opt_success("操作成功",0);
				}else if(data.code=="-2"){
				    opt_error("卡券已经被使用，订单已取消");
        	 		$(".btn-primary").attr("disabled",false);//防止多次提交
				}else if(data.code=="-3"){
				    opt_error("直售订单，必须先设置推荐人的提成身份等级");
        	 		$(".btn-primary").attr("disabled",false);//防止多次提交
				}else if(data.code=="-4"){
				    opt_error("该部门必须要有一个提成身份等级为1等员工");
        	 		$(".btn-primary").attr("disabled",false);//防止多次提交
				}else if(data.code=="-5"){
				    opt_error("推荐人不能为空");
        	 		$(".btn-primary").attr("disabled",false);//防止多次提交
				}else if(data.code=="-6"){
				    opt_error("推荐人或者推荐人关联人必须是绩效部门");
        	 		$(".btn-primary").attr("disabled",false);//防止多次提交
				}else if(data.code=="-7"){
				    opt_error("会员积分不足");
        	 		$(".btn-primary").attr("disabled",false);//防止多次提交
				}
				else{
					opt_error("系统繁忙，请稍后再试");
					$(".btn-primary").attr("disabled",false);//防止多次提交
				}
				},"json")
        }
        $('.chosen-select').chosen();
    </script>
</body>

</html>