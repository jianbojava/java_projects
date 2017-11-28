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
    

    <title>脑立方---添加未结业历史卡券订单</title>
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
                    <div class="ibox-content">
                        <form action="" method="post" class="form-horizontal">
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
	                                    	<option value="" tag="">请选择会员</option>
		                                    	  <c:forEach items="${users}" var="u">
		                                    		  <option value="${u.id }" tag="${u.refer_number}" >【编号:${u.number }】【姓名:${u.name }】</option>
		                                    	  </c:forEach>
			                            </select>
	                                </div>
	                            </div>
	                             <div class="hr-line-dashed"></div>
	                              <div class="form-group">
	                                <label class="col-sm-2 control-label">购买卡券</label>
	
	                                <div class="col-sm-9">
	                                    <select data-placeholder="选择卡券..." class="chosen-select" tabindex="2" name="ticket" onchange="selectTicket()">
	                                    	<option value="">请选择卡券</option>
		                                    	  <c:forEach items="${tickets}" var="l">
		                                    		  <option value="${l.id }">【名称:${l.name}】【价格:￥${l.price}】</option>
		                                    	  </c:forEach>
			                            </select>
	                                </div>
	                            </div>
	                          <div class="hr-line-dashed"></div>
	                          
                            <!-- 如果是添加历史订单展示 -->
                            <div class="form-group">
                                <label class="col-sm-2 control-label">购买类型</label>
                                <div class="col-sm-9">
                                	<div class="radio radio-success radio-inline">
                                        <input type="radio" id="inlineRadio1" value="0" name="buy_type" checked="">
                                        <label for="inlineRadio1"> 自售 </label>
                                    </div>
                                    <div class="radio radio-success radio-inline">
                                        <input type="radio" id="inlineRadio2" value="1" name="buy_type">
                                        <label for="inlineRadio2"> 直售 </label>
                                    </div>
                                </div>
                            </div>
                            <div class="hr-line-dashed"></div>
                            <div class="form-group">
                                <label class="col-sm-2 control-label">未结业状态</label>
                                <div class="col-sm-9">
                                	<div class="radio radio-success radio-inline">
                                        <input type="radio" id="inlineRadio3" value="0" name="is_end" checked="">
                                        <label for="inlineRadio3"> 全部未结业 </label>
                                    </div>
                                    <div class="radio radio-success radio-inline">
                                        <input type="radio" id="inlineRadio4" value="1" name="is_end">
                                        <label for="inlineRadio4"> 部分未结业</label>
                                    </div>
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
                            <!-- 选择已结业的卡券订单 -->
                             <div id="enddiv" style="display: none;background-color: #f5f5f5">
	                             <div class="form-group">
		                                <label class="col-sm-2 control-label">选择孩子</label>
		
		                                <div class="col-sm-9">
		                                    <select data-placeholder="选择孩子..." class="chosen-select" tabindex="2" name="student">
					                          </select>
		                                </div>
		                            </div>
		                            <div class="hr-line-dashed"></div>
		                             <div class="form-group">
			                               <label class="col-sm-2 control-label ">选择已结业的课程</label>
			                               <div class="col-sm-3">课程名称</div>
			                               <div class="col-sm-3">复训总数</div>
			                               <div class="col-sm-3">剩余复训次数</div>
		                              </div>
		                              <!-- 课程信息 -->
		                            <div class="hr-line-dashed"></div>
		                            <div id="lessondiv"></div>
	                         </div>
	                         <!-- 选择未结业的卡券订单 -->
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
         $(document).ready(function(){$(".i-checks").iCheck({checkboxClass:"icheckbox_square-green",radioClass:"iradio_square-green"})});
	     $(function(){
	      $("[name='create_date']").datepicker({
	        	format: "yyyy-mm-dd 00:00:00",
	        	todayHighlight:true,
	       		autoclose: true,
	       		todayBtn: true
	        });
	    })
        function selectUser(){
          var refer_number=$("[name='user'] option:selected").attr("tag");
          $("[name='refer_number']").val(refer_number);
           selectStudent();
        }
         function selectStudent(){
         var user_id=$("[name='user']").val();//会员id
         $("[name='student']").html("<option value=\"\">请选择学生</option>"); 
		 $(".chosen-select").trigger("chosen:updated");
		 if(user_id!=""){//会员的孩子
		   $.post("${ctx}/manage/student/queryByUserId/"+user_id,function(data){
                for(var i=0;i<data.length;i++){
       			   $("[name='student']").append("<option value=\""+data[i].id+"\" hassubinfo=\"true\">"+data[i].name+"</option>");
       		    }
       		   $(".chosen-select").trigger("chosen:updated");
             })
		 }
      }
       $("[name='is_end']").change(function(){//0全部未结业，1部分未结业
			 selectTicket();
        })
        function selectTicket(){
           var ticket_id=$("[name='ticket']").val();
           var is_end=$("[name='is_end']:checked").val();//0全部未结业，1部分未结业
           var user_id=$("[name='user']").val();//会员id
           $("#enddiv").css("display","none");
           if(ticket_id!=""&&is_end==1){
	           $("#enddiv").css("display","");
	           $("#lessondiv").html("");
               $.post("${ctx}/manage/ticket/queryLessonById/"+ticket_id,function(data){
                     var html="";
	                 for(var i=0;i<data.length;i++){
	                   html+="<div class=\"form-group\">";
			           html+="<label class=\"col-sm-2 control-label\"></label>";
				       html+="<div class=\"col-sm-3\">";
					   html+="<label class=\"checkbox-inline i-checks\"><input type=\"checkbox\" value="+data[i].id+" name=\"lesson\">"+data[i].name+"</label>";
				       html+="</div>";
				       html+="<div class=\"col-sm-3\">";
				       html+="<input type=\"text\" class=\"form-control\" name=\"total_review\" value="+data[i].train+" readonly=\"readonly\">";
				       html+="</div>";
				       html+="<div class=\"col-sm-3\">";
				       if(data[i].type==1){
				         	html+="<input type=\"text\" class=\"form-control\" name=\"left_review\" placeholder=\"剩余复训次数\" value=\"0\"  readonly=\"readonly\">";
				       }else{
				          html+="<input type=\"text\" class=\"form-control\" name=\"left_review\" placeholder=\"剩余复训次数\" value=\"0\">";
				       }
				       html+="</div></div>";
	        		}
	        		 $("#lessondiv").html(html);
	        		 $(".i-checks").iCheck({checkboxClass:"icheckbox_square-green",radioClass:"iradio_square-green"});
               })
           }
        }
        function add(){
           if($("[name='refer_number']").val()==""){
               opt_error("操作失败您还没有推荐人");
               return;
            }
            var user_id=$("[name='user']").val();
            var conn_id=$("[name='ticket']").val();
            var is_end=$("[name='is_end']:checked").val();//0全部未结业，1部分为结业
		    if(conn_id=="") {
               opt_error("请选择课程");
               return;
            }
            if(user_id==""){
               opt_error("请选择会员");
               return;
            }
            if(is_end==1){//部分未结业
               var utlist=[];
               var errorReview=false;
				$("[name='lesson']:checked").each(function(){
				   var total_review=$(this).parents(".form-group").find("[name='total_review']").val();
				   var left_review=$(this).parents(".form-group").find("[name='left_review']").val();
				  var r = /^\+?[1-9][0-9]*$/;//正整数验证
			       if((left_review!=0&&(!r.test(left_review)))||(left_review>total_review)){
			        errorReview=true;
			        return;
			       }
					utlist.push({conn_id:$(this).attr("value"),left_review:left_review});
				})
				if(errorReview){
				     opt_error("剩余复训次数输入有误");
			         return;
				}
				var student_id=$("[name='student']").val();
				if(student_id==null||student_id==""){
				   opt_error("请选择孩子");
                    return;
				}
				if(utlist.length==0){
				   opt_error("请选择已结业的课程");
                    return;
				}
			    var params={"user_id":user_id,"conn_id":conn_id,"type":1,"buy_type":$("[name='buy_type']:checked").val(),"create_date":$("[name='create_date']").val(),"student_id":student_id,"utlist":utlist};
				$(".btn-primary").attr("disabled",true);//防止多次提交
				$.ajax({
						url:"${ctx}/manage/norder/addnofinish_part",
						type: "POST",
						data: JSON.stringify(params),
						success: function(data){
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
							}
						},
						dataType: "json",
						contentType: "application/json"
					});
            }
            if(is_end==0){
               $(".btn-primary").attr("disabled",true);//防止多次提交
               var post_url="${ctx}/manage/norder/addnofinish";
			    var params={"user_id":user_id,"conn_id":conn_id,"type":1,"buy_type":$("[name='buy_type']:checked").val(),"create_date":$("[name='create_date']").val()};
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
        }
        $('.chosen-select').chosen();
    </script>
</body>

</html>