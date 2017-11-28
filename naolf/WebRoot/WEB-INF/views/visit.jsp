<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
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
<script type="text/javascript" src="${ResStatic }/static/js/jquery-1.8.3.min.js"></script>
<script type="text/javascript" src="${ResStatic }/static/js/base.js"></script>
<script type="text/javascript" src="${ResStatic }/static/js/bootstrap.js"></script> 
<script type="text/javascript" src="${ResStatic }/static/js/app.js"></script> 
<script type="text/javascript" src="${ResStatic }/static/js/app.plugin.js"></script>
<script type="text/javascript" src="${ResStatic }/static/plugin/highcharts/js/highcharts.js"></script>
<script type="text/javascript" src="${ResStatic }/static/plugin/highcharts/js/exporting.js"></script>
<script type="text/javascript" src="${ResStatic }/static/js/Validform_v5.3.2_min.js"></script>
<script type="text/javascript" src="${ResStatic }/static/plugin/My97DatePicker/WdatePicker.js"></script>

</head>
<body class="container">
<section class="wrapper">
     <div class="m-b"> <span class="h3 font-thin"><i class="i i-arrow-left3"></i></span> </div>
     <section class="panel panel-default">
       <header class="panel-heading hed-font">在线人数统计</header>
       <div class="row wrapper">
              <div class="col-sm-7 m-b-xs"> </div>
              <div class="col-sm-5">
              	<form action="${ctx}/manager/index/visit" method="post" id="queryForm">
	                <div class="input-group">
			            <input type="text" class="Wdate_" style="width: 48%;" placeholder="开始时间" name="fDate" id="from_date" onClick="WdatePicker({maxDate:'#F{$dp.$D(\'to_date\')}',readOnly:true,dateFmt:'yyyy-MM-dd HH'})" value="${po.fDate}"> -
                        <input type="text" class="Wdate_" style="width: 48%;" placeholder="结束时间" name="eDate" id="to_date" onClick="WdatePicker({minDate:'#F{$dp.$D(\'from_date\')}',readOnly:true,dateFmt:'yyyy-MM-dd HH'})" value="${po.eDate}">
	                	<span class="input-group-btn">
	                  	<button class="btn btn-sm btn-default" type="button">搜索</button>
	                  	</span>
	                </div>
                </form>
              </div>
        </div>
       <div class="table-responsive">
         <div id="container" style="width:100%; height: 400px; margin: 0 auto; margin-top:0px; position:relative; float:left;"></div>
       </div>
     </section>
   </section>
<script type="text/javascript">
$(function () {
	  $('#container').highcharts({
	      chart: {
	          type: 'spline'
	      },
	      title: {
	          text: '在线人数统计'
	      },
	      xAxis: {
	    	  categories: [${x}]
	      },
	      yAxis: {
	          title: {
	              text: '在线人数(人)'
	          },
	          min:0,
	          allowDecimals:false,
	          plotBands: [{ // Light air
	              color: 'rgba(68, 170, 213, 0.1)',
	              label: {
	                  text: 'Light air',
	                  style: {
	                      color: '#606060'
	                  }
	              }
	          }]
	      },
	      tooltip: {
	          valueSuffix: '人'
	      },
	      plotOptions: {
	          spline: {
	              lineWidth: 4,
	              states: {
	                  hover: {
	                      lineWidth: 5
	                  }
	              },
	              marker: {
	                  enabled: false
	              }
	          }
	      },
	      series: [{
	          name: '在线人数',
	          data: [${y}]
	      }],
	      navigation: {
	          menuItemStyle: {
	              fontSize: '10px'
	          }
	      }
	  });
	});		
</script>
</body>
</html>