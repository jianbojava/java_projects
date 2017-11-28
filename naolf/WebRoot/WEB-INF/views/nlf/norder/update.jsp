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
    <link href="${ctx}/statics/manage/js/plugins/fancybox/jquery.fancybox.css" rel="stylesheet">
    <link href="${ctx}/statics/manage/css/plugins/chosen/chosen.css" rel="stylesheet">
    
</head>

<body>
    <div class="row animated fadeInRight">
            <div class="col-sm-12">
                <div class="panel-options">
                    <ul class="nav nav-tabs">
                        <li class="active"><a data-toggle="tab" href="#tab-1" aria-expanded="true">订单详细</a></li>
                        <li class=""><a data-toggle="tab" href="#tab-2" aria-expanded="false">订单日志</a></li>
                    </ul>
                    <div class="tab-content">
                        <div id="tab-1" class="tab-pane active">
                        	<div class="wrapper wrapper-content">
			                        <div class="row">
			                        	<div class="col-sm-6">
			                                <dl class="dl-horizontal">
			                                    <dt>订单编号：</dt>
			                                    <dd><span class="label label-primary">${norder.sn }</span>
			                                    </dd>
			                                </dl>
			                            </div>
			                            <div class="col-sm-6">
			                                <dl class="dl-horizontal">
			                                    <dt>订单状态：</dt>
			                                    <dd>
			                                    	<c:choose>
				                                    	<c:when test="${norder.status==0 }"><span class="label label-warning">已提交</span></c:when>
				                                    	<c:when test="${norder.status==1 }"><span class="label label-info">已支付</span></c:when>
				                                    	<c:when test="${norder.status==-2}"><span class="label label-info">已退款</span></c:when>
				                                    	<c:when test="${norder.status==-1}"><span class="label label-info">已取消</span></c:when>
				                                    	<c:otherwise><span class="label label-primary">未知状态</span></c:otherwise>
				                                    </c:choose>
			                                    </dd>
			                                </dl>
			                            </div>
			                        </div>
			                        
			                        <div class="row">
			                            <div class="col-sm-6">
			                                <dl class="dl-horizontal">
			
			                                    <dt>下单时间：</dt>
			                                    <dd>
			                                    	<fmt:formatDate value="${norder.create_date }" pattern="yyyy-MM-dd HH:mm"/>
			                                    </dd>
			                                    <dt>订单总额：</dt>
			                                    <dd>&yen;${norder.price}</dd>
			                                    <dt><c:if  test="${norder.type==0}">课程</c:if><c:if  test="${norder.type==1}">卡券</c:if>编号：</dt>
			                                    <dd>${norder.ticket_sn}</dd>
			                                    <c:if test="${not empty norder.uputicket }">
				                                    <dt>升级卡编号：</dt>
				                                    <dd>
				                                    	${norder.uputicket}
				                                    </dd>
			                                    </c:if>
			                                    <dt>使用已结业积分：</dt>
			                                    <dd>
			                                    	${norder.kz_score}
			                                    </dd>
			                                    <dt>使用可兑换积分：</dt>
			                                    <dd>
			                                    	${norder.kd_score}
			                                    </dd>
			                                </dl>
			                            </div>
			                            <div class="col-sm-6" id="cluster_info">
			                                <dl class="dl-horizontal">
			
			                                    <dt>提交类型：</dt>
			                                    <dd>
			                                    	<c:if test="${norder.addtype==0 }">线上提交</c:if>
			                                    	<c:if test="${norder.addtype==1 }">手动录入</c:if>
			                                    	<c:if test="${norder.addtype==2 }">已结业历史订单</c:if>
			                                    	<c:if test="${norder.addtype==3 }">未结业历史订单</c:if>
			                                    	<c:if test="${norder.addtype==4 }">卡券升级产生</c:if>
			                                    </dd>
			                                    <dt>支付方式：</dt>
			                                    <dd>
			                                    	<c:if test="${norder.pay_type==0 }">线下支付</c:if>
			                                    	<c:if test="${norder.pay_type==1 }">支付宝</c:if>
			                                    	<c:if test="${norder.pay_type==2}">微信</c:if>
			                                    	<c:if test="${norder.pay_type==4 }">积分支付</c:if>
			                                    	<c:if test="${norder.pay_type==5 }">券支付</c:if>
			                                    </dd>
			                                    <dt>名称：</dt>
			                                    <dd>
			                                    	${norder.name}
			                                    </dd>
			                                </dl>
			                            </div>
			                        </div>
			                          
			                         <label class="control-label">课程信息：</label>
			                        <div class="table-responsive m-t well">
				                        <table class="table invoice-table">
				                            <thead>
				                                <tr>
				                                    <th>课程名称</th>
				                                    <th>课程编号</th>
				                                    <th>状态</th>
				                                    <c:if test="${norder.addtype==3}"><th>操作</th></c:if>
				                                </tr>
				                            </thead>
				                            <tbody>
				                                <!-- 未支付订单 -->
				                                <c:if test="${not empty lessons}">
					                               <c:forEach items="${lessons}" var="l">
					                                <tr>
					                                    <td>${l.name}</td>
					                                    <td>未产生</td>
					                                    <td>未产生</td>
					                                </tr>
					                                </c:forEach>
				                                </c:if>
				                                <!-- 已支付订单 -->
				                               <c:if test="${not empty ulessons}">
					                               <c:forEach items="${ulessons}" var="l">
					                                <tr>
					                                    <td>${l.name}</td>
					                                    <td>${l.sn}</td>
					                                    <td>
					                                      <c:if test="${l.status==0}">未预约</c:if>
					                                      <c:if test="${l.status==1}">已预约</c:if>
					                                      <c:if test="${l.status==2}">取消预约</c:if>
					                                      <c:if test="${l.status==-3}">未训练</c:if>
					                                      <c:if test="${l.status==3}">训练中</c:if>
					                                      <c:if test="${l.status==4}">已结业</c:if>
					                                      <c:if test="${l.status==5}">复训中</c:if>
					                                      <c:if test="${l.status==6}">复训结束</c:if>
					                                    </td>
					                                    <c:if test="${norder.addtype==3&&(l.status==0)}"><td><button class="btn btn-primary" type="button" onclick="tofinish('${l.id}','${l.name}')">设置结业</button></td></c:if>
					                                </tr>
					                                </c:forEach>
				                                </c:if>
				                            </tbody>
				                        </table>
				                    </div>
				                    
				                   <div id="studiv" style="display: none;height: 100px">
		                               <div class="form-group">
		                                    <label class="col-sm-2 control-label" id="finishname">结业课程</label>
			                                <label class="col-sm-1 control-label">选择孩子</label>
			
			                                <div class="col-sm-2">
			                                    <select data-placeholder="选择孩子..." class="chosen-select" tabindex="2" name="student">
						                          </select>
			                                </div>
			                                <div id="reviewdiv" style="display: none">
				                                <label class="col-sm-2 control-label" id="reviewlab">剩余复训次数<span id="lesson_train"></span></label>
				
				                                <div class="col-sm-2">
				                                    <input type="text" class="form-control" id="left_review" name="left_review" value="0" placeholder="剩余复训次数">
				                                </div>
			                                </div>
			                                <button class="btn btn-warning" id="finish" type="button" onclick="finish()">立即结业</button>
			                            </div>
	                              </div>
	                        
				                    <c:if test="${norder.type==1}">
				                      <label class="control-label">卡券信息：</label>
			                        <div class="table-responsive m-t well">
				                        <table class="table invoice-table">
				                            <thead>
				                                <tr>
				                                    <th>卡券名称</th>
				                                    <th>卡券编号</th>
				                                </tr>
				                            </thead>
				                            <tbody>
				                                <tr>
				                                    <td>${norder.name}</td>
				                                    <td>${norder.ticket_sn}</td>
				                                </tr>
				                            </tbody>
				                        </table>
				                    </div>
				                    </c:if>
				                     <label class="control-label">会员信息：</label>
				                    <div class="table-responsive m-t well">
				                        <table class="table invoice-table">
				                            <thead>
				                                <tr>
				                                	<th>会员编号</th>
				                                    <th>姓名</th>
				                                    <th>联系电话</th>
				                                    <th>推荐人编号</th>
				                                    <th>所属部门</th>
				                                </tr>
				                            </thead>
				                            <tbody>
				                                <tr>
				                                	<td><c:choose><c:when test="${empty(user.number)}">-</c:when><c:otherwise>${user.number }</c:otherwise></c:choose></td>
				                                    <td><c:choose><c:when test="${empty(user.name)}">-</c:when><c:otherwise>${user.name }</c:otherwise></c:choose></td>
				                                    <td><c:choose><c:when test="${empty(user.mobile)}">-</c:when><c:otherwise>${user.mobile }</c:otherwise></c:choose></td>
				                                   	<td><c:choose><c:when test="${empty(user.refer_number)}">-</c:when><c:otherwise>${user.refer_number }</c:otherwise></c:choose></td>
				                                    <td><c:choose><c:when test="${empty(user.depart_name)}">-</c:when><c:otherwise>${user.depart_name }</c:otherwise></c:choose></td>
				                                </tr>
				                            </tbody>
				                        </table>
				                    </div>
				                    
				                     <label class="control-label">发票信息：</label>
                              <div class="table-responsive m-t well">
				                        <table class="table invoice-table" id="mytable">
				                            <thead>
				                                <tr>
				                                	<th>发票编号</th>
				                                	<th>发票抬头</th>
				                                	<th>发票代码</th>
				                                    <th>发票金额</th>
				                                    <th>开具时间</th>
				                                    <th>发票类型</th>
				                                    <th>是否退票</th>
				                                </tr>
				                            </thead>
				                            <tbody>
				                              <c:forEach items="${receipt}" var="r">
				                                  <tr>
				                                  <td>${r.sn}</td>
				                                  <td>${r.title }</td>
				                                  <td>${r.code}</td>
				                                  <td>${r.money}</td>
			                                    <td><fmt:formatDate value="${r.add_date }" pattern="yyyy-MM-dd"/></td>
				                                  <td>${r.content}</td>
				                                  <td>
				                                     <c:if test="${r.isreturn==1}">是</c:if>
				                                      <c:if test="${r.isreturn==2}">否</c:if>
				                                  </td>
				                                </tr>
				                              </c:forEach>
				                            </tbody>
				                        </table>
				                    </div>
				                    
				                    <div class="well m-t" style="display: none"><strong>订单备注：</strong> ${order.order_remark }</div>
			            	</div>
                        </div>
                        
                        <div id="tab-2" class="tab-pane">
                            <div id="vertical-timeline" class="vertical-container light-timeline">
                            
                            		<c:forEach items="${logs }" var="log" varStatus="status">
                            			<div class="vertical-timeline-block">
	                                        <div class="vertical-timeline-icon <c:if test='${status.index==0 }'>navy-bg</c:if><c:if test='${status.index!=0 }'>gray-bg</c:if>">
	                                            <i class="fa fa-volume-down"></i>
	                                        </div>
	
	                                        <div class="vertical-timeline-content">
	                                            <h2>
	                                            	<c:choose>
	                                            		<c:when test="${empty(log.user_id) }">
	                                            			<c:choose>
	                                            				<c:when test="${empty(log.mem_name) }">${log.mem_mobile }</c:when>
	                                            				<c:otherwise>${log.mem_name}</c:otherwise>
	                                            			</c:choose>
	                                            		</c:when>
	                                            		<c:otherwise>${log.user_name }</c:otherwise>
	                                            	</c:choose>
	                                            </h2>
	                                            <p>
	                                            	${log.message }
	                                            </p>
	                                            <span class="vertical-date">
	                                        		<small><fmt:formatDate value="${log.create_date }" pattern="yyyy-MM-dd HH:mm:ss"/></small>
	                                    		</span>
	                                        </div>
	                                    </div>
                            		</c:forEach>

                                </div>
                        </div>
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
    <script src="${ctx}/statics/manage/js/plugins/fancybox/jquery.fancybox.js"></script>
    <script src="${ctx}/statics/manage/js/plugins/chosen/chosen.jquery.js"></script>
    <script src="${ctx}/statics/manage/js/jquery.form.js"></script>
    <script src="${ctx}/statics/manage/js/common.js"></script>
    
    <script>
        $(document).ready(function(){$("#ibox-content").addClass("ibox-content");$("#vertical-timeline").removeClass("light-timeline");$("#vertical-timeline").addClass("dark-timeline");$(".fancybox").fancybox({openEffect:"none",closeEffect:"none"});});
         $('.chosen-select').chosen();
       
       //设置结业
       var user_ticket_id="";
       var lesson_type=1;//类型：0:集中训练+进阶训练；1集中训练
       var lesson_train=0;
       function tofinish(ut_id,name){
          user_ticket_id=ut_id;
          $("#finishname").html("结业课程:"+name);
          $("#studiv").css("display","block"); 
          $("#left_review").val(0);
         $.post("${ctx}/manage/norder/tofinish/"+ut_id,function(data){
               //加载孩子
                var students=data.students;
                for(var i=0;i<students.length;i++){
       			   $("[name='student']").append("<option value=\""+students[i].id+"\" hassubinfo=\"true\">"+students[i].name+"</option>");
       		    }
       		   
       		    //如果有代表已经预约过了
       		    var student_id=data.student_id;
       		    if(student_id!=""){
       		       $("[name='student']").val(student_id);
       		       $("[name='student']").attr("disabled",true);
       		     }
       		    $(".chosen-select").trigger("chosen:updated");
       		    //查询课程
       		   var lesson=data.lesson;//0:集中训练+进阶训练；1集中训练
       		   lesson_type=lesson.type;
       		   if(lesson.type==0){
       		      $("#reviewdiv").css("display","block");
       		      $("#lesson_train").html("&nbsp;&nbsp;[最多"+lesson.train+"]");
       		      lesson_train=lesson.train;
       		   }else{
       		      $("#reviewdiv").css("display","none");
       		   }
             })
       }
       //立即结业
       function finish(){
          var student_id=$("[name='student']").val();
          if(student_id==null||student_id==""){
             opt_error("请选择学习的孩子");
             return;
          }
          var left_review=$("#left_review").val();
          if(lesson_type==0){//集中+进阶
             var r = /^\+?[1-9][0-9]*$/;//正整数验证
		     if(left_review!=0&&(!r.test(left_review))){
		         opt_error("剩余复训次数输入有误");
		         return;
		    }else{
		      if(left_review>lesson_train){
		          opt_error("剩余复训次数不能大于总复训数");
		         return;
		      } 
		    }
          }
          $("#finish").attr("disabled",true);//防止多次提交
          $.post("${ctx}/manage/norder/finish/"+user_ticket_id+"/"+student_id+"/"+left_review,function(data){
              if(data.code=="0"){
                opt_success_reload("结业成功",location.href);//结业成功，重新加载页面
              }else{
                 opt_error(data.msg);
                 setTimeout("javascript:location.href='"+location.href+"'",2000);
              }
          })
       }
   
    </script>
</body>

</html>