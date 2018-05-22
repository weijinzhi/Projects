<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	pageContext.setAttribute("path", request.getContextPath());
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>后台管理系统</title>

<link rel="stylesheet"
	href="${path }/bootstrap-3.3.7-dist/css/bootstrap.css">
<link href="${path }/bootstrap-3.3.7-dist/css/font-awesome.css"
	rel="stylesheet">
<link href="${path }/bootstrap-3.3.7-dist/css/animate.css"
	rel="stylesheet">
<link href="${path }/bootstrap-3.3.7-dist/css/style.css"
	rel="stylesheet">

<!-- Mainly scripts -->
<script src="${path }/bootstrap-3.3.7-dist/js/jquery-3.1.1.min.js"></script>
<script src="${path }/bootstrap-3.3.7-dist/js/bootstrap.js"></script>
<script
	src="${path }/bootstrap-3.3.7-dist/js/plugins/metisMenu/jquery.metisMenu.js"></script>
<script
	src="${path }/bootstrap-3.3.7-dist/js/plugins/slimscroll/jquery.slimscroll.min.js"></script>

<!-- Custom and plugin javascript -->
<script src="${path }/bootstrap-3.3.7-dist/js/inspinia.js"></script>
<script src="${path }/bootstrap-3.3.7-dist/js/pace.min.js"></script>
<script type="text/javascript" src="${path }/bootstrap-3.3.7-dist/js/index.js"></script>

<script type="text/javascript"
	src="${path }/bootstrap-3.3.7-dist/js/bootstrap-table.js"></script>
<script type="text/javascript"
	src="${path }/bootstrap-3.3.7-dist/js/bootstrap-table-zh-CN.js"></script>
<script type="text/javascript"
	src="${path }/bootstrap-3.3.7-dist/js/bootstrapValidator.js"></script>
<script type="text/javascript"
	src="${path }/bootstrap-3.3.7-dist/js/zh_CN.js"></script>
<link href="${path }/bootstrap-3.3.7-dist/css/bootstrapValidator.css"
	rel="stylesheet" type="text/css">
</head>
<body class="">
	<div id="wrapper">
		<nav class="navbar-default navbar-static-side" role="navigation">
		<div class="sidebar-collapse">
			<ul class="nav metismenu" id="side-menu">
				<li class="nav-header">
					<div class="dropdown profile-element">
						<a data-toggle="dropdown" class="dropdown-toggle" href="#"> <span
							class="clear"> <span class="block m-t-xs"> <strong
									class="font-bold">${userName}</strong>
							</span> <span class="text-muted text-xs block">${NAME } <b
									class="caret"></b></span>
						</span>
						</a>
						<ul class="dropdown-menu animated fadeInRight m-t-xs">
							<!-- <li><a href="profile.html" id="">个人信息</a></li> -->
							<li><a onclick="openPassDlg()">修改密码</a></li>
							<li class="divider"></li>
							<li><a onclick="quit()">安全退出</a></li>
						</ul>
					</div>
				</li>

				<li><a onclick=""><i
						class="fa fa-cog"></i> <span class="nav-label">群组管理</span> <span
						class="fa arrow"></span> </a>
					<ul class="nav nav-second-level collapse">
						<li><a onclick="getPage('${path}/guide/goGuide','导游')">
								<i class="fa fa-user"></i> <span class="nav-label">导游</span>
						</a></li>
						<li><a onclick="getPage('${path}/visitor/goVisitor','游客')">
								<i class="fa fa-user"></i> <span class="nav-label">游客</span>
						</a></li>
					</ul></li>
					
				<li><a onclick=""><i
						class="fa fa-cog"></i> <span class="nav-label">地图管理</span> <span
						class="fa arrow"></span> </a>
					<ul class="nav nav-second-level collapse">
						<li><a onclick="getPage('${path}/admin/goMap','导游')">
								<i class="fa fa-user"></i> <span class="nav-label">我的位置</span>
						</a></li>
						<li><a onclick="getPage('${path}/visitor/goVisitor','游客')">
								<i class="fa fa-user"></i> <span class="nav-label">游客</span>
						</a></li>
					</ul></li>

				<!--下面两个菜单是固定死的  -->
				<li><a onclick="openPassDlg()"><i class="fa fa-key"></i> <span
						class="nav-label">修改密码</span> </a></li>
				<li><a onclick="quit()"><i class="fa fa-sign-out"></i> <span
						class="nav-label">退出登陆</span> </a></li>
			</ul>
		</div>
		</nav>
		<div id="page-wrapper" class="gray-bg">
			<div class="row border-bottom">
				<nav class="navbar navbar-static-top  " role="navigation"
					style="margin-bottom: 0">
				<div class="navbar-header">
					<a class="navbar-minimalize minimalize-styl-2 btn btn-primary "
						href="#"><i class="fa fa-bars"></i> </a>
				</div>
				</nav>
			</div>
			<div class="wrapper wrapper-content" id="content"></div>
		</div>
	</div>
<!-- 模态框（Modal） -->
<!-- 修改 -->
<div id="passDlg" class="modal fade"  tabindex="-1" role="dialog" data-backdrop="static" data-keyboard="false" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h4 class="modal-title" id="myModalLabel">修改密码</h4>
            </div>
            <div class="container">
			<form class="form-horizontal" id="myform"  method="post">
			<div class="form-group">
			<label class="col-md-2 control-label">旧密码：</label>
			<div class="col-md-3 ">
			<input type="password" id="oldPass" name="oldPass" class="form-control form-control-static"  placeholder="请输入原始密码">
			<input  type="hidden" id="pass" value="${password }" name="password">
			<input  type="hidden" id="uid" value="${id }" name="id">
			</div>
			</div>
			
			<div class="form-group">
			<label class="col-md-2 control-label">新密码：</label>
			<div class="col-md-3 ">
			<input type="password" id="newPass"  name="newPass" class="form-control form-control-static" placeholder="请输入新密码">
			</div>
			</div>
			
			<div class="form-group">
			<label class="col-md-2 control-label">确认密码：</label>
			<div class="col-md-3">
			<input type="password" id="againPass"  name="againPass" class="form-control form-control-static" placeholder="请输入新密码">
			</div>
			</div>
            <div class="modal-footer col-md-6">
            <!--用来清空表单数据-->
            <input type="reset" name="reset" style="display: none;" />
                <button type="button" class="btn btn-default" onclick="closeDlgs()">关闭</button>
               <button type="button" onclick="upPass()" class="btn btn-primary">修改</button>
            </div>
            </form>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal -->
</div>

<script type="text/javascript">
$('ul>li>a').click(function(){
    $(this).next('ul').toggle();

});
</script>

</body>
</html>
