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
    

    <title>智行家</title>
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
                                <label class="col-sm-2 control-label">用户名</label>

                                <div class="col-sm-9">
                                    <input type="text" class="form-control" name="username" onchange="checkUsername()">
                                </div>
                            </div>
                            <div class="hr-line-dashed"></div>
                             <!-- 
                            <div class="form-group">
                                <label class="col-sm-2 control-label">重置密码</label>
                                <div class="col-sm-9">
                                	<input type="checkbox" class="js-switch" name="is_reset"/>
                                	<span>密码重置为123456</span>
                                </div>
                            </div>
                            <div class="hr-line-dashed"></div>
                           
                            <div class="form-group">
                                <label class="col-sm-2 control-label">昵称</label>

                                <div class="col-sm-9">
                                    <input type="text" class="form-control" name="nickname">
                                </div>
                            </div>
                            <div class="hr-line-dashed"></div>
                             -->
                            <div class="form-group">
                                <label class="col-sm-2 control-label">联系电话</label>

                                <div class="col-sm-9">
                                    <input type="text" class="form-control" name="mobile" onchange="checkMobile()">
                                </div>
                            </div>
                            <div class="hr-line-dashed"></div>
                             <div class="form-group">
                                <label class="col-sm-2 control-label">出生日期</label>
                                <div class="col-sm-9">
		                                <input class="form-control" name="birthday" type="text" placeholder="出生日期" onchange="changebirth()">
                                </div>
                            </div>
                            <div class="hr-line-dashed"></div>
                             <div class="form-group">
                                <label class="col-sm-2 control-label">姓名</label>

                                <div class="col-sm-9">
                                    <input type="text" class="form-control" name="name">
                                </div>
                            </div>
                            <div class="hr-line-dashed"></div>
                             <div class="form-group">
                                <label class="col-sm-2 control-label">身份证</label>

                                <div class="col-sm-9">
                                    <input type="text" class="form-control" name="card">
                                </div>
                            </div>
                             <div class="hr-line-dashed"></div>
                             <div class="form-group">
                                <label class="col-sm-2 control-label">年龄</label>

                                <div class="col-sm-9">
                                    <input type="text" class="form-control" name="age" readonly="readonly">
                                </div>
                            </div>
                            <div class="hr-line-dashed"></div>
                            <div class="form-group">
                                <label class="col-sm-2 control-label">性别</label>
                                <div class="col-sm-9">
                                	<div class="radio radio-success radio-inline">
                                        <input type="radio" id="inlineRadio1" value="0" name="gender" checked="">
                                        <label for="inlineRadio1"> 男 </label>
                                    </div>
                                    <div class="radio radio-success radio-inline">
                                        <input type="radio" id="inlineRadio2" value="1" name="gender">
                                        <label for="inlineRadio2"> 女 </label>
                                    </div>
                                </div>
                            </div>
                            
                            <div class="hr-line-dashed"></div>
                            <div class="form-group">
                                <label class="col-sm-2 control-label">地址</label>

                                <div class="col-sm-9">
                                    <div class="input-group">
                                    	<span class="input-group-addon">省</span>
		                                <select data-placeholder="选择省份..." class="chosen-select" tabindex="2" name="province">
		                                </select>
		                                <span class="input-group-addon">市</span>
		                                <select data-placeholder="选择城市..." class="chosen-select" tabindex="2" name="city">
		                                </select>
		                                <span class="input-group-addon">区</span>
		                                <select data-placeholder="选择区县..." class="chosen-select" tabindex="2" name="region">
		                                </select>
		                            </div>
	                                
                                </div>
                            </div>
                             <div class="hr-line-dashed"></div>
                             <div class="form-group">
                                <label class="col-sm-2 control-label">详细地址</label>

                                <div class="col-sm-9">
                                    <input type="text" class="form-control" name="address">
                                </div>
                            </div>
                            <div class="hr-line-dashed"></div>
                             <div class="form-group">
                                <label class="col-sm-2 control-label">邮箱</label>

                                <div class="col-sm-9">
                                    <input type="text" class="form-control" name="email">
                                </div>
                            </div>
                            <div class="hr-line-dashed"></div>
                             <div class="form-group">
                                <label class="col-sm-2 control-label">银行名称</label>

                                <div class="col-sm-9">
                                    <input type="text" class="form-control" name="bank_name">
                                </div>
                            </div>
                            <div class="hr-line-dashed"></div>
                             <div class="form-group">
                                <label class="col-sm-2 control-label">银行卡号</label>

                                <div class="col-sm-9">
                                    <input type="text" class="form-control" name="bank_no">
                                </div>
                            </div>
                            <div class="hr-line-dashed"></div>
                             <div class="form-group">
                                <label class="col-sm-2 control-label">开户人</label>

                                <div class="col-sm-9">
                                    <input type="text" class="form-control" name="bank_user">
                                </div>
                            </div>
                            <div class="hr-line-dashed"></div>
                             <div class="form-group">
                                <label class="col-sm-2 control-label">微信号</label>

                                <div class="col-sm-9">
                                    <input type="text" class="form-control" name="wx_no">
                                </div>
                            </div>
                            <div class="hr-line-dashed"></div>
                             <div class="form-group">
                                <label class="col-sm-2 control-label">支付宝号</label>

                                <div class="col-sm-9">
                                    <input type="text" class="form-control" name="ali_no">
                                </div>
                            </div>
                            <div class="hr-line-dashed"></div>
                            
                            <c:if test="${user_type==1}">
	                              <div class="form-group">
	                                <label class="col-sm-2 control-label">合伙人类型</label>
	                                <div class="col-sm-9">
	                                	<div class="radio radio-success radio-inline">
	                                        <input type="radio" id="inlineRadio3" value="1" name="partner_type" checked="">
	                                        <label for="inlineRadio3"> 高级 </label>
	                                    </div>
	                                    <div class="radio radio-success radio-inline">
	                                        <input type="radio" id="inlineRadio4" value="2" name="partner_type">
	                                        <label for="inlineRadio4"> 事业 </label>
	                                    </div>
	                                    <div class="radio radio-success radio-inline">
	                                        <input type="radio" id="inlineRadio5" value="3" name="partner_type">
	                                        <label for="inlineRadio5"> 钻石 </label>
	                                    </div>
	                                </div>
	                            </div>
	                             <div class="hr-line-dashed"></div>
                             </c:if>
                              <div class="form-group">
                                <label class="col-sm-2 control-label">推荐人</label>

                                <div class="col-sm-9">
                                   <!--  <input type="text" class="form-control" name="refer_number" onchange="validNumber()">
                                   --> 
                                    <select data-placeholder="选择推荐人..." class="chosen-select" tabindex="2" name="refer_number" onchange="selectRefer()">
	                                    	<option value="">请选择推荐人</option>
		                                    	  <c:forEach items="${users}" var="u">
		                                    		  <option value="${u.number}" tag="${u.depart_id}" >【编号:${u.number }】【姓名:${u.name }】</option>
		                                    	  </c:forEach>
			                            </select>
                                </div>
                            </div>
                            <div class="hr-line-dashed"></div>
                            <div id="departdiv" <c:if test="${user_type==2 }"> style="display: none" </c:if> >  
	                              <div class="form-group">
	                                <label class="col-sm-2 control-label">所属部门</label>
	
	                                <div class="col-sm-9">
	                                    <select data-placeholder="选择部门..." class="chosen-select" tabindex="2" name="depart">
	                                    	<option value="" tage="">请选择部门</option>
	                                    	<c:if test="${not empty depart}">
	                                    	  <option value="${depart.id }" tag="${depart.performance_ind}">${depart.name }</option>
		                                    	  <c:forEach items="${depart.child}" var="d">
		                                    		  <option value="${d.id }" tag="${d.performance_ind}">&nbsp;&nbsp;&nbsp;${d.name }</option>
		                                    		  <c:forEach items="${d.child}" var="d">
		                                    		     <option value="${d.id }" tag="${d.performance_ind}">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;${d.name }</option>
		                                    		     <c:forEach items="${d.child}" var="d">
		                                    		            <option value="${d.id }" tag="${d.performance_ind}">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;${d.name }</option>
		                                    	                 <c:forEach items="${d.child}" var="d">
		                                    		                  <option value="${d.id }" tag="${d.performance_ind}">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;${d.name }</option>
		                                    	                       <c:forEach items="${d.child}" var="d">
		                                    		                        <option value="${d.id }" tag="${d.performance_ind}">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;${d.name }</option>
		                                    	                       </c:forEach>
		                                    	                </c:forEach>
		                                    	         </c:forEach>
		                                    	      </c:forEach>
		                                    	  </c:forEach>
	                                    	</c:if>
			                            </select>
	                                </div>
	                            </div>
	                             <div class="hr-line-dashed"></div>
                             </div>
                             <!-- add by wjx 2017-2-24 begin-->
                            <div class="form-group">
                            	
                                <label class="col-sm-2 control-label">提成身份等级</label>
                                <div class="col-sm-3">
                                	<select data-placeholder="选择提成身份等级..." class="chosen-select" tabindex="2" name="dispacth_grade" <c:if test="${user_type==1||(user_type==2)  }"> disabled="disabled"</c:if> >
                                		<option value="">请选择等级</option>
                                		<option value="1">1等级</option>
                                		<option value="2">2等级</option>
                                		<option value="3">3等级</option>
                                		<option value="4">4等级</option>
                                	</select>
                                </div>
                                
                            </div>
                            <div class="hr-line-dashed"></div>
                              <!-- add by wjx 2017-2-24 end-->
                            <div class="form-group">
                                <label class="col-sm-2 control-label">锁定</label>
                                <div class="col-sm-9">
                                	<input type="checkbox" class="js-switch" name="is_locked"/>
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
    <script src="${ctx}/statics/manage/js/plugins/chosen/chosen.jquery.js"></script>
    <script src="${ctx}/statics/manage/js/plugins/datapicker/bootstrap-datepicker.js"></script>
    <script src="${ctx}/statics/manage/js/jquery.form.js"></script>
    <script src="${ctx}/statics/manage/js/common.js"></script>
    
    <script>
        var validUsername=0;//验证用户名
        var validMobile=0;//验证手机号
        var validBirth=0;//验证出生日期
        $(document).ready(function(){$(".i-checks").iCheck({checkboxClass:"icheckbox_square-green",radioClass:"iradio_square-green"})});
        	//时间控件
         $("[name='birthday']").datepicker({
        	format: "yyyy-mm-dd",
        	todayHighlight:true,
       		autoclose: true,
       		todayBtn: true
        });
        if(${user_type}==2){//如果是会员的话默认是第四等级
           $("[name='dispacth_grade']").val(4);
          $(".chosen-select").trigger("chosen:updated");
        }
        if(${user_type}==1){//如果是合伙人的话默认是第1等级
           $("[name='dispacth_grade']").val(1);
          $(".chosen-select").trigger("chosen:updated");
        }
        $("[name='partner_type']").change(function(){//合伙人高级1级，事业2，钻石3
	        var partner_type=$(this).attr("value");
			 $("[name='dispacth_grade']").val(partner_type);
			  $(".chosen-select").trigger("chosen:updated");
				
        })
        function changebirth(){
	         var birthday=$("[name='birthday']").val();
	         if(birthday!=null&&birthday!=""){
	            var nyear=new Date().getFullYear();
	            var byear=new Date(birthday).getFullYear();
	            if(byear>nyear){
	              opt_error("出生日期输入有误");
	              validBirth=1;
	            }else{
	               $("[name='age']").val(nyear-byear);
	            }
	         }
        }
        //选择推荐人
       function selectRefer(){
              var user_type=${user_type};//0员工，1合伙人，2会员
              var number=$("[name='refer_number']").val();
	          if(number!=null&&number!=""){
	               $("#departdiv").css("display","block");
	               if(user_type==2)$("[name='depart']").attr("disabled","disabled");//会员的话禁用
	               var depart_id=$("[name='refer_number'] option:selected").attr("tag");
	               $("[name='depart']").val(depart_id);
	               $(".chosen-select").trigger("chosen:updated");
	        }
        }
        
        function checkUsername(){
           validUsername=0;
           var username=$.trim($("[name='username']").val());
           if(username!=null&&username!=""){
	           $.post("${ctx}/manage/user/queryByUsername/"+username,function(data){
	               if(data.user!=null){
	                  validUsername=1;
	                   opt_error("用户名已存在,请重新输入");
	               }
	           },"json")
           }
        }
        
        function checkMobile(){
           validMobile=0;
           var mobile=$.trim($("[name='mobile']").val());
           if(mobile!=null&&mobile!=""){
	           $.post("${ctx}/manage/user/queryByMobile/"+mobile,function(data){
	               if(data.user!=null){
	                  validMobile=1;
	                   opt_error("手机号已存在,请重新输入");
	               }
	           },"json")
           }
        }
        $(function(){
    		resetProvince();
    		$("[name='province']").change(function(){
    			resetCity($(this).val());
    		})
    		$("[name='city']").change(function(){
    			resetRegion($(this).val());
    		})
    	})
   	 function resetProvince(){
        $("[name='province']").html("<option value=\"\">请选择省份</option>");
       	$.getJSON("${ctx}/manage/regions/1",function(data){
       		for(var i=0;i<data.length;i++){
       			$("[name='province']").append("<option value=\""+data[i].id+"\" hassubinfo=\"true\">"+data[i].name+"</option>");
       		}
       		$(".chosen-select").trigger("chosen:updated");
       		resetCity($("[name='province']").val());
   		})
       }
       function resetCity(province_id){
        $("[name='city']").html("<option value=\"\">请选择城市</option>");
       	$(".chosen-select").trigger("chosen:updated");
       	if(province_id!=""){
       		$.getJSON("${ctx}/manage/regions/"+province_id,function(data){
	        		for(var i=0;i<data.length;i++){
	        			$("[name='city']").append("<option value=\""+data[i].id+"\" hassubinfo=\"true\">"+data[i].name+"</option>");
	        		}
	        		$(".chosen-select").trigger("chosen:updated");
	        		resetRegion($("[name='city']").val());
	    		})
       	}else{
       		resetRegion($("[name='city']").val());
       	}
       }
       function resetRegion(city_id){
        	$("[name='region']").html("<option value=\"\">请选择区县</option>");
       		$(".chosen-select").trigger("chosen:updated");
      		$.getJSON("${ctx}/manage/regions/"+city_id,function(data){
       		for(var i=0;i<data.length;i++){
       			$("[name='region']").append("<option value=\""+data[i].id+"\" hassubinfo=\"true\">"+data[i].name+"</option>");
       		}
       		$(".chosen-select").trigger("chosen:updated");
   		})
       }
        function add(){
            var username=$.trim($("[name='username']").val());
            var mobile=$.trim($("[name='mobile']").val());
            /**
           if(username==null||username=="") {
              opt_error("用户名不可以为空!"); 
                return;
           }
           **/
           if(validUsername==1){
              opt_error("用户名已存在!"); 
                return;
           }
           if(mobile==null||mobile=="") {
              opt_error("手机号不可以为空!"); 
                return;
           }
           if(validMobile==1){
              opt_error("手机号已存在!"); 
                return;
           }
           var depart_id=$("[name='depart']").val();
           if(depart_id==null||depart_id==""){
              opt_error("部门不可以为空!"); 
                return;
           }
           //部门是否是绩效部门（0否，1是）
           var performance_ind=$("[name='depart'] option:selected").attr("tag");
           var dispacth_grade=$("[name='dispacth_grade']").val();
           var user_type=${user_type};//user_type类型：0员工，1合伙人，2会员    
           var refer_number=$("[name='refer_number']").val();
           if((user_type==1||user_type==2)&&(refer_number==null||refer_number=="")){
             if(user_type==1&&dispacth_grade==1){
                //合伙人，并且是1等级(可以为空)
             }else{
               opt_error("推荐人不可以为空!"); 
                return;
             }
           }
           if(user_type==1&&performance_ind!=1){//合伙人必须是绩效部门
                opt_error("合伙人必须是绩效部门!"); 
                return;
           }
           if((user_type==0)&&(performance_ind==1)){//会员&&有绩效部门
               if(dispacth_grade==null||dispacth_grade==""){
                  opt_error("请设置提成身份等级！");
                  return;
               }else{
                  if((dispacth_grade!=1)&&(refer_number==null||refer_number=="")){
                         opt_error("推荐人不可以为空!"); 
                         return;
                     }
                  }
           }
          var  birthday= $("[name='birthday']").val();
          if(user_type==2){//会员要有出生日期
	          if(birthday==null||birthday==""){
	             opt_error("出生日期不可以为空");
	             return;
	          }
	          if(validBirth==1){
	             opt_error("出生日期输入有误");
	             return;
	          }
          }
          var  name= $.trim($("[name='name']").val());
          if(name==null||name==""){
             opt_error("姓名不可以为空");
             return;
          }
           var province_id=$("[name='province']").val();
		   var city_id=$("[name='city']").val();
		   var region_id=$("[name='region']").val();
		    var address=$.trim($("[name='address']").val());
		    if(user_type==2){//会员要地址
			    if(province_id==null||province_id==""||city_id==null||city_id==""||region_id==null||region_id==""||address==""){
			       opt_error("地址不可以为空");
			       return ;
			    }
		    }
			var is_reset=0;
			if($("[name='is_reset']").is(":checked"))is_reset=1;
			var is_locked=0;
			if($("[name='is_locked']").is(":checked"))is_locked=1;
            var params={  "username":username,"is_reset":is_reset,
			               "mobile":mobile,"nickname":$("[name='nickname']").val(),
			               "is_locked":is_locked,"depart_id":depart_id,
			               "birthday":$("[name='birthday']").val(),"name":name,"age":$("[name='age']").val(),"card":$("[name='card']").val(),
			               "gender":$("[name='gender']:checked").val(),
			               "email":$("[name='email']").val(),
			               "bank_name":$("[name='bank_name']").val(),"bank_no":$("[name='bank_no']").val(),"bank_user":$("[name='bank_user']").val(),
			               "wx_no":$("[name='wx_no']").val(),"ali_no":$("[name='ali_no']").val(),"refer_number":$("[name='refer_number']").val(),
			               "partner_type":$("[name='partner_type']:checked").val(),"user_type":${user_type},
			               "address":address,
			               "province_name":$("[name='province'] option:selected").text(),
			               "city_name":$("[name='city'] option:selected").text(),
			               "region_name":$("[name='region'] option:selected").text(),
			               "province_id":$("[name='province']").val(),
			               "city_id":$("[name='city']").val(),
			               "region_id":$("[name='region']").val(),
			               "dispacth_grade":$("[name='dispacth_grade']").val(),
			           }
			        
            	$(".btn-primary").attr("disabled",true);//防止多次提交
        	$.post("${ctx}/manage/user/add",params,function(data){
					if(data.code=="0"){
						opt_success("添加成功",0);
					}else if(data.code=="-1"){
						opt_error("该部门已经从存在提成身份等级为1等员工");
						$(".btn-primary").attr("disabled",false);//防止多次提交
					}else{
						opt_error("添加失败");
            	        $(".btn-primary").attr("disabled",false);//防止多次提交
					}
				},"json")
        }
        $('.chosen-select').chosen();
    </script>
</body>

</html>