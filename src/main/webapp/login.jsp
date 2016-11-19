<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>登录</title>
<link href="css/style.css" rel="stylesheet" type="text/css" />
<script language="JavaScript" src="js/jquery.js"></script>
<script src="js/cloud.js" type="text/javascript"></script>

<script language="javascript">
	$(function(){
    $('.loginbox').css({'position':'absolute','left':($(window).width()-692)/2});
	$(window).resize(function(){  
    $('.loginbox').css({'position':'absolute','left':($(window).width()-692)/2});
    })  
});  
</script>
</head>
<body style="background-color: #1c77ac; background-image: url(images/light.png); 
	background-repeat: no-repeat; background-position: center top; ">

	<div id="mainBody">
		<div id="cloud1" class="cloud"></div>
		<div id="cloud2" class="cloud"></div>
	</div>

	<div class="logintop">
		<span>欢迎登录后台管理界面平台</span>
		<ul>
			<li><a href="#">回首页</a></li>
			<li><a href="#">帮助</a></li>
			<li><a href="#">关于</a></li>
		</ul>
	</div>
	<form name="from1" action="login.do" method="post">
		<div class="loginbody">
			<span class="systemlogo"></span>
			<div class="loginbox">
				<ul>
					<li><input name="username" type="text" class="loginuser" value="admin"
						onclick="JavaScript:this.value=''" /></li>
					<li><input name="password" type="text" class="loginpwd" value="密码"
						onclick="JavaScript:this.value=''" /><div>${user.username}</div></li>
					<li><input type="submit" name="submit" class="loginbtn" value="登录"/><label>
						<input name="" type="checkbox" value="" checked="checked" />记住密码</label><label>
						<a href="#">忘记密码？</a></label></li>
				</ul>
			</div>
		</div>
	</form>
	<div class="loginbm"><span>版权所有 2016  仅供学习交流，勿用于任何商业用途</span></div>
</body>
</html>