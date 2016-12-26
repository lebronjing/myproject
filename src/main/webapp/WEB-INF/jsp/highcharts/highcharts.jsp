<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>highchars图表演示</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<script type="text/javascript" src="js/jquery-1.12.2.min.js"></script>
	<script type="text/javascript" src="js/highchartsjs/highcharts.js"></script>
	<script type="text/javascript">
		$(function () {
			$.ajax({
				url:"getHighcharsData.do",
				type:"get",
				success: function(data){
					var a = eval(data);
					console.log(a);
					GetData(a);
				},
				error: function () {
		            alert("请求超时，请重试！");
		        }
			});
		});
		
		
		function GetData(dataTmp){
			$('#container').highcharts({
		        chart: {
		            type: 'line'
		        },
		        title: {
		            text: 'Monthly Average Temperature'
		        },
		        subtitle: {
		            text: 'Source: WorldClimate.com'
		        },
		        xAxis: {
		            categories: ['Jan', 'Feb', 'Mar', 'Apr', 'May', 'Jun', 'Jul', 'Aug', 'Sep', 'Oct', 'Nov', 'Dec']
		        },
		        yAxis: {
		            title: {
		                text: 'Temperature (°C)'
		            }
		        },
		        exporting:{
                    enabled:false
                },
                credits: {
                    enabled: false
                },
		        plotOptions: {
		            line: {
		                dataLabels: {
		                    enabled: true
		                },
		                enableMouseTracking: false
		            }
		        },
		        series: eval("" + dataTmp + "")
		    });
		}
	</script>
  </head>
  
  <body>
    <div id="container" style="min-width: 310px; height: 400px; margin: 0 auto"></div>
  </body>
  
</html>
