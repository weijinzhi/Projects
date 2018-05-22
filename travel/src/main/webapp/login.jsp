<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	pageContext.setAttribute("path", request.getContextPath());
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>登录</title>

<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link href="${path }/bootstrap-3.3.7-dist/css/login.css" rel="stylesheet">

<link rel="stylesheet" type="text/css" href="${path }/bootstrap-3.3.7-dist/css/bootstrap.min.css?v=3.3.6" />
<link rel="stylesheet" type="text/css" href="${path }/bootstrap-3.3.7-dist/css/font-awesome.css?v=4.4.0" />
<link rel="stylesheet" type="text/css" href="${path }/bootstrap-3.3.7-dist/css/animate.css" />
<script src="${path }/bootstrap-3.3.7-dist/js/jquery-3.1.1.min.js"></script>
<script src="${path }/bootstrap-3.3.7-dist/js/bootstrap.min.js"></script>

<script>
	if (window.top !== window.self) {
		window.top.location = window.location;
	}
</script>

<script type="text/javascript">
	function loadimage() {
		document.getElementById("randImage").src = "${path }/getRandImage.jsp?t=" + Math.random();
	}

</script>
<%
boolean auto = false;
Cookie[] cookies = request.getCookies();
if(cookies != null){
	for(int i=0; i<cookies.length; i++) {
   		Cookie cookie = cookies[i];
   		if("autoLogin".equals(cookie.getName())){
   			auto = true;
   			break;
   		}
	}
}
if(auto){
	response.sendRedirect("auto.htm");
}
%>

</head>
<body class="signin">
    <div class="signinpanel">
        <div class="row">
            <div class="col-sm-7">
                <div class="signin-info">
                	<div class="logopanel m-b">
                        <h1>基于旅游业的人员定位管理系统</h1>
                    </div>
                    <br><br><br>
                    <strong>还没有账号？ <a onclick="hi()">立即注册&raquo;</a>&nbsp;&nbsp;&nbsp;&nbsp;<a href="visitor/jingdian.jsp">游客进入&raquo;</a></strong>
                </div>
            </div>
            <div class="col-sm-5">
                <form id="form1" name="form1" action="${path }/admin/login" method="post">
                    <h4 class="no-margins"><font color="#76AB71">欢迎登录：</font></h4>
                    <input type="text" class="form-control uname" placeholder="用户名" name="userName" id="userName" required="required" value="${userName }"/>
                    <input type="password" class="form-control pword m-b" placeholder="密码" id="password" name="password" required="required" value="${password }"
						onkeydown="if(event.keyCode==13)form1.submit()" />
					<input type="text" class="form-control ucode" placeholder="验证码"
						required="required" value="${imageCode }" name="imageCode"
						id="imageCode" onkeydown="if(event.keyCode==13)form1.submit()" />
                   
                   <p class="text-muted text-center">
					<a href="login.htm"><small>忘记密码了？</small></a> | <input type="checkbox" name="auto" id="auto" >记住我 | <img onclick="javascript:loadimage();" title="换一张试试"
						name="randImage" id="randImage" src="${path }/getRandImage.jsp" height="20" border="1">
				</p>
                    <button type="submit" class="btn btn-success btn-block">登录</button>
                </form>
            </div>
        </div>
    </div>
</body>
<script type="text/javascript">
	alert(request.getContextPath());
</script>
</html>