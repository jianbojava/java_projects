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
<title>超感创作力</title>
<link href="${ctx}/statics/wap/css/base.css" rel="stylesheet" type="text/css">
<link href="${ctx}/statics/wap/css/index.css" rel="stylesheet" type="text/css">
<script type="text/javascript" src="${ctx}/statics/wap/js/jquery-1.8.3.min.js"></script>
<script type="text/javascript" src="${ctx}/statics/wap/js/jquery.raty.min.js"></script>
<script type="text/javascript" src="${ctx}/statics/wap/js/common.js"></script>
<script type="text/javascript" src="${ctx}/statics/wap/js/base.js"></script>
</head>
<body>
<div class="container">
	<div class="comment mBtm96">
    	<div class="courseDetail">
    	<input type="hidden" id="ut_id" value="${appoint.user_ticket_id }"/>
    	<input type="hidden" id="lc_id" value="${lcomment.id }"/>
        	<div class="row clearfix">
            	<div class="icon fl">
                	<img src="${ctx}/statics/wap/images/time.png" class="commentIcon" alt="" />
                </div>
            	<div class="content f28">上课时间：<fmt:formatDate value="${course.start_time }" pattern="yy/MM/dd HH:mm"/></div>
            </div>
        	<div class="row clearfix">
            	<div class="icon fl">
                	<img src="${ctx}/statics/wap/images/person.png" class="commentIcon" alt="" />
                </div>
            	<div class="content f28">指导教师：${course.teacher }</div>
            </div>
        	<div class="row clearfix">
            	<div class="icon fl">
                	<img src="${ctx}/statics/wap/images/heart.png" class="commentIcon" alt="" />
                </div>
            	<div class="content f28">服务总结：通过${lesson.name }的培训，该学员已初见成效，达到培训验收标准。<c:if test="${course.type==0 }">后续脑立方免费为学员培训${lesson.train }个课时。</c:if></div>
            </div>
        </div>
		<c:if test="${lcomment.id==null }">
        <div class="satisfy">
        	<div class="evaluate">请对本次课程进行评价</div>
            <div class="row clearfix">
            	<div class="icon fl">服务态度</div>
            	<div class="content clearfix f24">
                	<div class="check fl serveice">
                    	<input type="radio" name="attitude" value="4"/>
                        <i class="active"></i>
                        <label>非常满意</label>
                    </div>
                    <div class="check fl serveice">
                    	<input type="radio" name="attitude" value="3"/>
                        <i class="noActive"></i>
                        <label>满意</label>
                    </div>
                    <div class="check fl serveice">
                    	<input type="radio" name="attitude" value="2"/>
                        <i class="noActive"></i>
                        <label>基本满意</label>
                    </div>
                    <div class="check fl serveice">
                    	<input type="radio" name="attitude" value="1"/>
                        <i class="noActive"></i>
                        <label>有待提高</label>
                    </div>
                </div>
            </div>
            <div class="row clearfix">
            	<div class="icon fl">专业水平</div>
            	<div class="content clearfix f24">
                	<div class="check fl profess">
                    	<input type="radio" name="level" value="4"/>
                        <i class="active"></i>
                        <label>非常满意</label>
                    </div>
                    <div class="check fl profess">
                    	<input type="radio" name="level" value="3"/>
                        <i class="noActive"></i>
                        <label>满意</label>
                    </div>
                    <div class="check fl profess">
                    	<input type="radio" name="level" value="2"/>
                        <i class="noActive"></i>
                        <label>基本满意</label>
                    </div>
                    <div class="check fl profess">
                    	<input type="radio" name="level" value="1"/>
                        <i class="noActive"></i>
                        <label>有待提高</label>
                    </div>
                </div>
            </div>
            <div class="row clearfix">
            	<div class="icon fl">满意度</div>
            	<div class="content clearfix f24">
                	<div class="check fl satify">
                    	<input type="radio" name="satisfy" value="4"/>
                        <i class="active"></i>
                        <label>非常满意</label>
                    </div>
                    <div class="check fl satify">
                    	<input type="radio" name="satisfy" value="3"/>
                        <i class="noActive"></i>
                        <label>满意</label>
                    </div>
                    <div class="check fl satify">
                    	<input type="radio" name="satisfy" value="2"/>
                        <i class="noActive"></i>
                        <label>基本满意</label>
                    </div>
                    <div class="check fl satify">
                    	<input type="radio" name="satisfy" value="1"/>
                        <i class="noActive"></i>
                        <label>有待提高</label>
                    </div>
                </div>
            </div>
            <div class="row clearfix">
            	<div class="icon fl">五星评价</div>
            	<div class="content clearfix f24 service">
            		<div id="star" data-score='5'></div>
                </div>
            </div>
        </div>
        <div class="otherAdvice">
        	<div class="advice">其他意见或建议</div>
            <textarea rows="5" placeholder="请输入不多于200字" id="content" class="adviceCon"></textarea>
        </div>
        <div class="submitBox">
        	<input type="button" id="btnservice" onclick="service()" value="提交" class="submitBtn" />
        </div>
        </c:if>
        
        <c:if test="${lcomment.id!=null }">
        <div class="satisfy">
        	<div class="evaluate">请对本次课程进行评价</div>
            <div class="row clearfix">
            	<div class="icon fl">服务态度</div>
            	<div class="content clearfix f24">
                	<div class="check fl serveice">
                    	<input type="radio" name="attitude1" value="4"/>
                        <i class="noActive"></i>
                        <label>非常满意</label>
                    </div>
                    <div class="check fl serveice">
                    	<input type="radio" name="attitude1" value="3"/>
                        <i class="noActive"></i>
                        <label>满意</label>
                    </div>
                    <div class="check fl serveice">
                    	<input type="radio" name="attitude1" value="2"/>
                        <i class="noActive"></i>
                        <label>基本满意</label>
                    </div>
                    <div class="check fl serveice">
                    	<input type="radio" name="attitude1" value="1"/>
                        <i class="noActive"></i>
                        <label>有待提高</label>
                    </div>
                </div>
            </div>
            <div class="row clearfix">
            	<div class="icon fl">专业水平</div>
            	<div class="content clearfix f24">
                	<div class="check fl profess">
                    	<input type="radio" name="level1" value="4"/>
                        <i class="noActive"></i>
                        <label>非常满意</label>
                    </div>
                    <div class="check fl profess">
                    	<input type="radio" name="level1" value="3"/>
                        <i class="noActive"></i>
                        <label>满意</label>
                    </div>
                    <div class="check fl profess">
                    	<input type="radio" name="level1" value="2"/>
                        <i class="noActive"></i>
                        <label>基本满意</label>
                    </div>
                    <div class="check fl profess">
                    	<input type="radio" name="level1" value="1"/>
                        <i class="noActive"></i>
                        <label>有待提高</label>
                    </div>
                </div>
            </div>
            <div class="row clearfix">
            	<div class="icon fl">满意度</div>
            	<div class="content clearfix f24">
                	<div class="check fl satify">
                    	<input type="radio" name="satisfy1" value="4"/>
                        <i class="noActive"></i>
                        <label>非常满意</label>
                    </div>
                    <div class="check fl satify">
                    	<input type="radio" name="satisfy1" value="3"/>
                        <i class="noActive"></i>
                        <label>满意</label>
                    </div>
                    <div class="check fl satify">
                    	<input type="radio" name="satisfy1" value="2"/>
                        <i class="noActive"></i>
                        <label>基本满意</label>
                    </div>
                    <div class="check fl satify">
                    	<input type="radio" name="satisfy1" value="1"/>
                        <i class="noActive"></i>
                        <label>有待提高</label>
                    </div>
                </div>
            </div>
            <div class="row clearfix">
            	<div class="icon fl">五星评价</div>
            	<div class="content clearfix f24 service">
            		<div id="star" data-score='5'></div>
                </div>
            </div>
        </div>
        <div class="otherAdvice">
        	<div class="advice">其他意见或建议</div>
            <textarea rows="5" placeholder="请输入不多于200字" id="content" class="adviceCon" disabled>${lcomment.content }</textarea>
        </div>
        <div class="submitBox">
        	<input type="button" onclick="toreturn()" value="返回" class="submitBtn" />
        </div>
        </c:if>
    </div>
</div>
<script type="text/javascript" src="${ctx}/statics/wap/js/alerts.js"></script>
<script type="text/javascript" src="${ctx}/statics/wap/layui/layui.js"></script>
<script type="text/javascript">
		if($("#lc_id").val()==null||$("#lc_id").val()==""){
			$('#star').raty({ score: 5 });
		}else{
		//已评价
			$('#star').raty({ score: '${lcomment.comm_num}' });
			$("[name=attitude1][value=${lcomment.serveice_num}]").attr("checked",'checked');
			$("[name=attitude1][value=${lcomment.serveice_num}]").siblings("i").attr("class","active");
			$("[name=level1][value=${lcomment.profess_num}]").attr("checked",'checked');
			$("[name=level1][value=${lcomment.profess_num}]").siblings("i").attr("class","active");
			$("[name=satisfy1][value=${lcomment.satify_num}]").attr("checked",'checked');
			$("[name=satisfy1][value=${lcomment.satify_num}]").siblings("i").attr("class","active");
			$(".service img").removeAttr("onmove");
		}
		var comm_num=5;
		var satify_num=4;
		var profess_num=4;
		var serveice_num=4;
		$(".service img").click(function(){
		    comm_num=$(this).attr("alt");
		})
		$(".satify i").click(function(){
		    satify_num=$(this).siblings("input").val();
		})
		$(".profess i").click(function(){
		    profess_num=$(this).siblings("input").val();
		})
		$(".serveice i").click(function(){
		   serveice_num=$(this).siblings("input").val();
		})
		function service(){
		$("#btnservice").attr("disabled",true);//防止多次提交
				$.post("${ctx}/courseApi/services",{"user_ticket_id":$("#ut_id").val(),"lesson_id":'${lesson.id}',"content":$("#content").val(),"serveice_num":serveice_num,"profess_num":profess_num,"satify_num":satify_num,"comm_num":comm_num},function(data){
					if(data.code=="0"){
						layui.use(['layer', 'form'], function(){
						  var layer = layui.layer
						  ,form = layui.form();	
							 layer.open({
							  type: 1, 
							  content: data.msg, 
							  end:function(){
			  				location.href="${ctx}/wap/course/courseDetail_noReserve?id="+$("#ut_id").val();
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
						alertN(data.msg)
					}
				},"json");
		}
		
		function toreturn(){
		   location.href="${ctx}/wap/course/courseDetail_noReserve?id="+$("#ut_id").val();
		}
		
		
</script>
</body>
</html>