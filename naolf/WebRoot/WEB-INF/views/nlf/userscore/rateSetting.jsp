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
    <link href="${ctx}/statics/manage/css/plugins/footable/footable.core.css" rel="stylesheet">
    <link href="${ctx}/statics/manage/css/animate.min.css" rel="stylesheet">
    <link href="${ctx}/statics/manage/css/style.min.css?v=4.0.0" rel="stylesheet">
    <link href="${ctx}/statics/manage/css/plugins/iCheck/custom.css" rel="stylesheet">
    <link href="${ctx}/statics/manage/css/plugins/chosen/chosen.css" rel="stylesheet">

</head>

<body class="gray-bg">
    <div class="wrapper wrapper-content animated fadeInRight">

            <div class="row">
                <div class="col-sm-12">
                    <div class="ibox float-e-margins">
                        <div class="ibox-title">
                            <h5>提成比例设置</h5>
                            <div class="ibox-tools">
                                <a class="collapse-link">
                                    <i class="fa fa-chevron-up"></i>
                                </a>
                            </div>
                        </div>
                        <div class="ibox-content">
                        	<!--  搜索，begin -->
							 <form type="hidden" action="${ctx}/manage/orderdisratesetting/list" method="post" id="searchForm" class="pull-right" target='_self' style="width: 485px">
		                        <div class="input-group">
		                            
		                            <input type="text" class="form-control input-sm" name="keywords" value="${vo.keywords}" style="width:180px;float: right;" placeholder="搜索员工编码">
		                            <input type="hidden" name="pageNo" value="${vo.pageNo }"/>
				      				<input type="hidden" name="pageSize" value="${vo.pageSize }">
		                            <div class="input-group-btn">
		                                <button type="button" class="btn btn-sm btn-primary">搜索</button>
		                            </div>
		                        </div>
		                    </form>
		                    <!--  搜索，end -->
                            <table class="footable table table-stripped toggle-arrow-tiny" data-sort="false" data-page-size="${vo.pageSize }">
                                <thead>
                                <tr>
                                	
                                    <th data-toggle="true">提成率编码</th>
                                    <th >编码说明</th>
                                    <th>比例（%）</th>
                                </tr>
                                </thead>
                                <tbody id="rate_table">
                                <c:forEach items="${page.result }" var="rateinfo">
                                <tr>
                                	<td>${rateinfo.rate_type }</td>
                                	<td>${rateinfo.description }</td>
                                	<td> <input class="form-control" type="text" value="${rateinfo.rate }" /> 
                                		 <input type="hidden" value="${rateinfo.rate }" />	
                                	</td>				                    
                                </tr>
                                </c:forEach>
                                </tbody>
                                <tfoot>
                                <tr>
                                    <td colspan="8" id="pagination"></td>
                                </tfoot>
                            </table>
                        </div>
                    </div>
                </div>
            </div>
            
            <shiro:hasPermission name="manage:orderdisratesetting:update">
            <div class="form-group">
                                <div class="col-sm-4 col-sm-offset-4" style="text-align: center;">
                                    <button class="btn btn-primary" type="button" onclick="update()">保存</button>
                                </div>
            </div>
            </shiro:hasPermission>
        </div>
    <script src="${ctx}/statics/manage/js/jquery.min.js?v=2.1.4"></script>
    <script src="${ctx}/statics/manage/js/bootstrap.min.js?v=3.3.5"></script>
    <script src="${ctx}/statics/manage/js/content.min.js?v=1.0.0"></script>
    <script src="${ctx}/statics/manage/js/plugins/footable/footable.all.min.js"></script>
    <script src="${ctx}/statics/manage/js/plugins/iCheck/icheck.min.js"></script>
    <script src="${ctx}/statics/manage/plugins/layer/layer.js"></script>
    <script src="${ctx}/statics/manage/js/plugins/chosen/chosen.jquery_.js"></script>
    <script src="${ctx}/statics/manage/js/common.js?v=1.0.0"></script>
    <script>
     $(document).ready(function(){
        $(document).ready(function(){$(".footable").footable();$("#pagination").html('${page.pageContent }');$(".i-checks").iCheck({checkboxClass:"icheckbox_square-green",radioClass:"iradio_square-green"});pageinit();});
    	
        });
        
     function update(){
     	var trList = $("#rate_table").children("tr");
     	
     	for (var i=0;i<trList.length;i++) {
     		var tdArr = trList.eq(i).find("td");
     		var rate =tdArr.eq(2).find("input[type='text']").val();
     		var rate_ori =tdArr.eq(2).find("input[type='hidden']").val();
     		var rate_desc =tdArr.eq(1).text();
     		var rate_type =tdArr.eq(0).text();
     		if (rate >100 ||rate <0){
     			alert("比例必须大于0且小于100");
     			return ;
     		} 
     		
     		if(rate!=rate_ori){
     			var  param = {"rate_type":rate_type,"description": rate_desc,"rate":rate};
     			$.post("${ctx}/manage/orderdisratesetting/update",param,function(data){
					if(data.code=="0"){
						//opt_success("修改成功",0);
					}else{
						opt_error(rate_type+"修改失败");
						return;
					}
				},"json");  
     		} 
     		
     	}	
     	opt_success("修改成功",0);
     	
     	//console.log(paramList);
     	
     }
        
    </script>
</body>

</html>