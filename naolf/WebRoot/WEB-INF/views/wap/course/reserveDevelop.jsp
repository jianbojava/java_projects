<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0" name="viewport">     
<meta content="yes" name="apple-mobile-web-app-capable">     
<meta content="black" name="apple-mobile-web-app-status-bar-style">     
<meta name="keywords" content="脑立方">
<meta name="description" content="脑立方">
<title>开发预约</title>
<link href="${ctx}/statics/wap/css/base.css" rel="stylesheet" type="text/css">
<link href="${ctx}/statics/wap/css/index.css" rel="stylesheet" type="text/css">
<script type="text/javascript" src="${ctx}/statics/wap/js/jquery-1.8.3.min.js"></script>
<script type="text/javascript" src="${ctx}/statics/wap/js/common.js"></script>
<style type="text/css">
  #distpicker2{ width:auto; }
  #search{
    border: 1px solid #745398;
    color: #745398;
    background-color: transparent;
    font-size:0.24rem;
    padding:0.05rem 0.1rem;
    border-radius:0.1rem;
    float:right;
    margin-top:0.06rem;
    margin-right: 20px;
  }
</style>

</head>
<body>
<div class="container">
	<div class="forgetPwd">
    	<div class="courseBanner mBtm20">
            <img src="${ctx}/statics/wap/images/courseBanner.png" alt="" />
        </div>
        <div class="reserveDevelop">
        	<table id="mytable">
        	<input type="hidden" value="${ut.id }" id="ut_id"/>
        	<input type="hidden" value="${s_id }" id="s_id"/>
        	<div class="row">
                <label>区&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;域：</label>
                <div id="distpicker2">
                    <select id="province" class="select" name="province" data-placeholder="选择省份..." ></select>&nbsp;&nbsp;&nbsp; 
                    <select id="city" class="select" name="city" data-placeholder="选择城市..." ></select>
                </div> 
            </div>
            <div class="row">
                <label>服务部门：</label>
                <div id="distpicker2">
                    <select id="depart" style="width: auto" class="select" name="depart" data-placeholder="选择省份..." ></select> 
                </div> 
                 <input id="search" type="button" onclick="searchCourse()" value="搜   索" class="btn btn_purple_b status" />
                
            </div>
	        		<c:forEach items="${list }" var="l">
	                <tr class="reserve">
	                    <td class="col col_fl">
	                    	<a href="${ctx}/wap/course/reserveDetail?id=${l.id}">
	                        <div class="reserveBtn f28"><fmt:formatDate value="${l.start_time}" pattern="yy/MM/dd"/></div>
	                        <div class="f24 color_a5a4a8"><fmt:formatDate value="${l.start_time}" pattern="HH:mm"/></div>
	                        </a>
	                    </td>
	                    <td class="col col_mid">
	                    	<a href="${ctx}/wap/course/reserveDetail?id=${l.id}">
	                        <div class="reserveBtn f28">${l.d_name }</div>
	                        <div class="f24 color_a5a4a8">${l.province_name }${l.city_name }${l.region_name }${l.address }</div>
	                        </a>
	                    </td>
	                    <c:if test="${l.students == 0 }">
		                    <td class="col col_fr"  onclick="appointAdd('${l.id}')">
	                    	<a href="javascript:;">
		                           <div class="reserveBtn f30 bold">预约</div>
		                           <div class="date">${l.have_appoint }/${l.num }&nbsp;人</div>
	                        </a>
		                    </td>
	                    </c:if>
	                    <c:if test="${l.students>=1 }">
		                    <td class="col col_fr col_red"  onclick="cancelappoint('${l.id}')">
	                    	<a href="javascript:;">
		                            <div class="reserveBtn f30 bold">已预约</div>
		                            <div class="date">${l.have_appoint }/${l.num }&nbsp;人</div>
	                        </a>
		                    </td>
	                    </c:if>
	                </tr>
	                <tr class="blank20"></tr>
                   </c:forEach>
            </table>
        </div>
    </div>
</div>
<script type="text/javascript" src="${ctx}/statics/wap/js/alerts.js"></script>
<script type="text/javascript" src="${ctx}/statics/wap/layui/layui.js"></script>
<script type="text/javascript">
//点击搜索按钮
function searchCourse(){
	var province_id=$("[name='province']").val();
	var city_id=$("[name='city']").val();
	var depart_id=$("[name='depart']").val();
     $.getJSON("${ctx}/courseApi/queryCourse/${ut.id}",{"province_id":province_id,"city_id":city_id,"depart_id":depart_id,"type":'${course_type}',"lesson_id":'${ut.conn_id}'},function(data){
		$("#mytable tbody").html("");
		var html="";
		for(var i=0;i<data.length;i++){
		    var course=data[i];
		    html+="<tr class=\"reserve\">";
		          html+="<td class=\"col col_fl\">";
	              html+="<a href=\"${ctx}/wap/course/reserveDetail?id="+course.id+"\">";
	              html+="<div class=\"reserveBtn f28\">"+(new Date(course.start_time)).format("yy/MM/dd")+"</div>";
	              html+="<div class=\"f24 color_a5a4a8\">"+(new Date(course.start_time)).format("hh:mm")+"</div>";
	              html+="</a></td>";      
	              html+="<td class=\"col col_mid\">";
	              html+="<a href=\"${ctx}/wap/course/reserveDetail?id="+course.id+"\">";
	              html+="<div class=\"reserveBtn f28\">"+course.d_name+"</div>";
	              html+="<div class=\"f24 color_a5a4a8\">"+course.province_name+course.city_name+course.region_name+course.address+"</div>";
	              html+="</a></td>";
	              if(course.students==0){
	                 html+="<td class=\"col col_fr\"  onclick=\"appointAdd('"+course.id+"')\">";
	                 html+="<a href=\"javascript:;\"><div class=\"reserveBtn f30 bold\">预约</div>";
		             html+="<div class=\"date\">"+course.have_appoint+"/"+course.num+"&nbsp;人</div>";
	                 html+="</a></td>";
	              }
		          if(course.students>=1){
		             html+="<td class=\"col col_fr col_red\"  onclick=\"cancelappoint('"+course.id+"')\">";
	                 html+="<a href=\"javascript:;\"><div class=\"reserveBtn f30 bold\">已预约</div>";
		             html+="<div class=\"date\">"+course.have_appoint+"/"+course.num+"&nbsp;人</div>";
	                 html+="</a></td>";
		          }         
	         html+="<tr class=\"blank20\"></tr>";
		}
		$("#mytable tbody").html(html);
	})
}
function appointAdd(id){
        	$.post("${ctx}/courseApi/addappoint",{"user_ticket_id":$("#ut_id").val(),"student_id":$("#s_id").val(),"course_id":id},function(data){
					if(data.code=="0"){
					layui.use(['layer', 'form'], function(){
					  var layer = layui.layer
					  ,form = layui.form();	
						 layer.open({
						  type: 1, 
						  content: data.msg, 
						  end:function(){
								location.reload();
			    		  },
						  title:false,
						  closeBtn: 0,
						  btn:'确定',
						  btnAlign: 'c',
						  shadeClose:true,
						  skin: 'resetPwd_tip'
						});
						layer.config({
						  skin: 'resetPwd_tip'
						})
					});
					}else{
						layui.use(['layer', 'form'], function(){
					  var layer = layui.layer
					  ,form = layui.form();	
						 layer.open({
						  type: 1, 
						  content: data.msg, 
						  end:function(){
								location.reload();
			    		  },
						  title:false,
						  closeBtn: 0,
						  btn:'确定',
						  btnAlign: 'c',
						  shadeClose:true,
						  skin: 'resetPwd_tip'
						});
						layer.config({
						  skin: 'resetPwd_tip'
						})
					});
					}
				},"json")
	};
	
function cancelappoint(id){
        	$.post("${ctx}/courseApi/cancelappoint",{"user_ticket_id":$("#ut_id").val(),"student_id":$("#s_id").val(),"course_id":id},function(data){
					if(data.code=="0"){
					layui.use(['layer', 'form'], function(){
					  var layer = layui.layer
					  ,form = layui.form();	
						 layer.open({
						  type: 1, 
						  content: data.msg, 
						  end:function(){
								location.reload();
			    		  },
						  title:false,
						  closeBtn: 0,
						  btn:'确定',
						  btnAlign: 'c',
						  shadeClose:true,
						  skin: 'resetPwd_tip'
						});
						layer.config({
						  skin: 'resetPwd_tip'
						})
					});
					}else{
						layui.use(['layer', 'form'], function(){
					  var layer = layui.layer
					  ,form = layui.form();	
						 layer.open({
						  type: 1, 
						  content: data.msg, 
						  end:function(){
								location.reload();
			    		  },
						  title:false,
						  closeBtn: 0,
						  btn:'确定',
						  btnAlign: 'c',
						  shadeClose:true,
						  skin: 'resetPwd_tip'
						});
						layer.config({
						  skin: 'resetPwd_tip'
						})
					});
					}
				},"json")
	};

$(function(){

	resetProvince();
	$("[name='province']").change(function(){
		resetCity($(this).val());
		resetDepart($(this).val(),null);
	})
	$("[name='city']").change(function(){
	   var province_id=$("[name='province']").val();
		resetDepart(province_id,$(this).val());
	})
})
	

function resetProvince(){
	$("[name='province']").html("<option value=\"\">选择省份</option>");
	$.getJSON("${ctx}/courseApi/departRegions/0",function(data){
		for(var i=0;i<data.length;i++){
			$("[name='province']").append("<option value=\""+data[i].id+"\" hassubinfo=\"true\">"+data[i].name+"</option>");
		}
		resetCity($("[name='province']").val());
		resetDepart($("[name='province']").val(),null);
	})
}
function resetCity(province_id){
	$("[name='city']").html("<option value=\"\">选择城市</option>");
	if(province_id!=""){
		$.getJSON("${ctx}/courseApi/departRegions/"+province_id,function(data){
    		for(var i=0;i<data.length;i++){
    			$("[name='city']").append("<option value=\""+data[i].id+"\" hassubinfo=\"true\">"+data[i].name+"</option>");
    		}
    		resetDepart(province_id,$("[name='city']").val());
		})
	}
}
function resetDepart(province_id,city_id){
	$("[name='depart']").html("<option value=\"\">选择服务部门</option>");
		$.getJSON("${ctx}/courseApi/queryDepart",{"province_id":province_id,"city_id":city_id},function(data){
		for(var i=0;i<data.length;i++){
			$("[name='depart']").append("<option value=\""+data[i].id+"\" hassubinfo=\"true\">"+data[i].name+"</option>");
		}
	})
}

</script> 
</body>
</html>