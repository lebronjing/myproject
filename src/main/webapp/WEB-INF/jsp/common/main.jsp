<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>信息管理系统</title>
<link href="css/style.css" rel="stylesheet" type="text/css" />
<link href="css/bootstrap.min.css" rel="stylesheet" type="text/css" />
<link href="css/bootstrap-theme.min.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="js/jquery-1.12.2.min.js"></script>
<script type="text/javascript" src="js/bootstrap.min.js"></script>

<style type="text/css">
</style>
</head>
<body>

	<div class="place">
		<span>位置：</span>
		<ul class="placeul">
			<li><a href="#">首页</a></li>
		</ul>
	</div>

	<div id="myCarousel" class="carousel slide"  data-ride="carousel">
	   <!-- 轮播（Carousel）指标 -->
	   <ol class="carousel-indicators">
	      <li data-target="#myCarousel" data-slide-to="0" class="active"></li>
	      <li data-target="#myCarousel" data-slide-to="1"></li>
	      <li data-target="#myCarousel" data-slide-to="2"></li>
	   </ol>   
	   <!-- 轮播（Carousel）项目 -->
	   <div class="carousel-inner" role="listbox">
	      <div class="item active thumbnail">
	         <img src="images/dog.png"  class="img-responsive"  alt="dog" >
	      </div>
	      <div class="item thumbnail">
	         <img src="images/huge.png" alt="胡歌" >
	      </div>
	      <div class="item thumbnail">
	         <img src="images/tly.png" alt="佟丽娅" >
	      </div>
	   </div>
	   <!-- 轮播（Carousel）导航 -->
	   <a class="carousel-control left" href="#myCarousel" role="button" data-slide="prev"><span
	            class="glyphicon glyphicon-chevron-left">&lsaquo;</span></a>
	   <a class="carousel-control right" href="#myCarousel" role="button" data-slide="next"><span
	            class="glyphicon glyphicon-chevron-right">&rsaquo;</span></a>
	</div> 
</body>
</html>