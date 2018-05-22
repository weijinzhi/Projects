<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%
	pageContext.setAttribute("path", request.getContextPath());
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>简单web导航栏</title>
<style type="text/css">
	/*设置整体*/
	*{
		padding: 0;
		margin: 0;
		font-family: "微软雅黑";	
	}
	/*设置header的div区*/
	.header{
		height: 72px ;
    	background: #458fce ;
	}
	/*设置logo的div区*/
	.header .logo{
		color: #fff ;
		font-size: 30px ;
		font-weight:500 ;
    	line-height: 72px ;
    	margin-left: 20px ;
    	display:inline-block ;
    	float: left ;/*左浮动*/
	}
	/*设置li标签里*/
	.header ul li{
		float: left ;
		color: #fff ;
    	display: inline-block ;
    	width: 112px ;
    	height: 72px ; 
    	text-align: center ;
    	line-height:72px ;
    	cursor: pointer ;/*光标显示成小手型*/
	}
	/*细节调整*/
	ul li{
		list-style: none ;/*li的小圆点去掉*/
	}
	.header ul li.first{
		margin-left: 30px ;/*设置“首页”与“原创文字”间距*/
    	background:#74b0e2 ;/*“首页”设置默认背景*/
	}
	
	/*设置a链接标签*/
	a{
		color: #fff ;
    	text-decoration: none ;
	}
	/*设置导航添加hover事件*/
	.header ul li:hover{
		 background:#74b0e2 ;
	}
	/*设置登录、注册按钮*/
	.header .login{
		float: right ; 
    	color: #fff ;
    	line-height: 72px ;
    	margin-right: 20px ;
	}
</style>
</head>
<body>
	<div class="header">
		<div class='logo'>GPS定位管理系统</div>
		<ul>
			<li><a href="${path }/visitor/jingdian.jsp">景点信息</a></li>
			<li><a href="${path }/visitor/daoyou.jsp">导游信息</a></li>
			<li class="first"><a href="${path }/visitor/youke.jsp">游客论坛</a></li>
		</ul>
		<div class="login">
		<span><a href="${path }/login.jsp">登陆</a></span>  
    	<span>|</span> 
    	<span><a href="javascript:void(0)">注册</a></span>
		</div>
	</div>
	<div class="wrapper wrapper-content" id="content"></div>
</body>
</html>
