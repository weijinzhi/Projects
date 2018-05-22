<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%
	pageContext.setAttribute("path", request.getContextPath());
%>
<!DOCTYPE html>
<html>
<head>
<title>用户主页</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="stylesheet"
	href="${path }/bootstrap-3.3.7-dist/css/bootstrap.css">
<link href="${path }/bootstrap-3.3.7-dist/css/font-awesome.css"
	rel="stylesheet">
<link href="${path }/bootstrap-3.3.7-dist/css/animate.css"
	rel="stylesheet">
<link
	href="${path }/bootstrap-3.3.7-dist/css/bootstrap-datetimepicker.css"
	rel="stylesheet" />
<script src="${path }/bootstrap-3.3.7-dist/js/jquery-3.1.1.min.js"></script>
<script src="${path }/bootstrap-3.3.7-dist/js/bootstrap.js"></script>

<link href="${path }/bootstrap-3.3.7-dist/css/style.css"
	rel="stylesheet">

<link href="${path }/bootstrap-3.3.7-dist/css/bootstrap-table.min.css"
	rel="stylesheet">
</head>
<body class="gray-bg">
	<div class="panel-body">
		<div id="toolbar" class="btn-group">

			<button type="button" id="btn_add" class="btn btn-default"
				style="color: #093F4D">
				<span class="glyphicon glyphicon-plus" aria-hidden="true"></span>添加
			</button>

			<button type="button" id="btn_edit" class="btn btn-default"
				style="color: #093F4D">
				<span class="glyphicon glyphicon-pencil" aria-hidden="true"></span>修改
			</button>

			<button type="button" id="btn_delete" class="btn btn-default"
				style="color: #093F4D">
				<span class="glyphicon glyphicon-remove" aria-hidden="true"></span>删除
			</button>

		</div>
		<div class="row">
			<div class="col-lg-2">
				<div class="input-group">
					<span class="input-group-addon">用戶名 </span> <input type="text"
						name="username" class="" id="txt_search_username">
					<button id="btn_search" type="button" class="btn btn-default">
						<span class="glyphicon glyphicon-search" aria-hidden="true"></span>查询
					</button>
				</div>
			</div>
		</div>

		<table id="table_user"></table>

	</div>

	<!-- 新增和修改对话框 -->
	<div class="modal fade" id="modal_user_edit" role="dialog"
		aria-labelledby="modal_user_edit" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-body">
					<form id="form_user" method="post"
						action="${path }/visitor/reserveVisitor">
						<input type="hidden" name="id" id="id" value="" />
						<table
							style="border-collapse: separate; border-spacing: 0px 10px;">
							<tr>
								<td>用户名：</td>
								<td><input type="text" id="name" name="name"
									class="form-control" aria-required="true" required /></td>
								<td>&nbsp;&nbsp;</td>
								<td>密码：</td>
								<td><input type="password" id="password" name="password"
									class="form-control" aria-required="true" required /></td>
							</tr>
							<tr>
								<td>性别：</td>
								<td colspan="4"><select class="form-control" name="sex"
									id="sex" aria-required="true" required>
										<option value="">---请选择---</option>
										<option value="男">男</option>
										<option value="女">女</option>
								</select></td>
							</tr>
							<tr>
								<td>邮箱：</td>
								<td><input type="text" id="email" name="email"
									class="form-control" aria-required="true" required /></td>
								<td>&nbsp;&nbsp;</td>
								<td>电话：</td>
								<td><input type="text" id="phone" name="phone"
									class="form-control" aria-required="true" required /></td>
							</tr>
							<tr>
								<td>出生日期：</td>
								<td><input class="form_datetime form-control" id="birth"
									type="text" name="birth" value="请输入日期" size="8"></td>
							</tr>
						</table>

						<div class="modal-footer">
							<button type="button" class="btn btn-primary"
								id="submit_form_user_btn">保存</button>
							<button type="button" class="btn btn-default"
								data-dismiss="modal">关闭</button>
						</div>
					</form>
				</div>
			</div>
		</div>
	</div>


	<!--删除对话框 -->
	<div class="modal fade" id="modal_user_del" role="dialog"
		aria-labelledby="modal_user_del" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true">×</button>
					<h4 class="modal-title" id="modal_user_del_head">刪除</h4>
				</div>
				<div class="modal-body">删除所选记录？</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-danger" id="del_user_btn">刪除</button>
					<button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
				</div>
			</div>
		</div>
	</div>


	<div class="ui-jqdialog modal-content" id="alertmod_table_user_mod"
		dir="ltr" role="dialog" aria-labelledby="alerthd_table_user"
		aria-hidden="true"
		style="width: 200px; height: auto; z-index: 2222; overflow: hidden; top: 274px; left: 534px; display: none; position: absolute;">
		<div class="ui-jqdialog-titlebar modal-header" id="alerthd_table_user"
			style="cursor: move;">
			<span class="ui-jqdialog-title" style="float: left;">注意</span> <a
				id="alertmod_table_user_mod_a" class="ui-jqdialog-titlebar-close"
				style="right: 0.3em;"> <span
				class="glyphicon glyphicon-remove-circle"></span></a>
		</div>
		<div class="ui-jqdialog-content modal-body" id="alertcnt_table_user">
			<div id="select_message"></div>
			<span tabindex="0"> <span tabindex="-1" id="jqg_alrt"></span></span>
		</div>
		<div
			class="jqResize ui-resizable-handle ui-resizable-se glyphicon glyphicon-import"></div>
	</div>

	<!-- Peity-->
	<script src="${path }/bootstrap-3.3.7-dist/js/jquery.peity.min.js"></script>

	<!-- Bootstrap table-->
	<script src="${path }/bootstrap-3.3.7-dist/js/bootstrap-table.min.js"></script>
	<script
		src="${path }/bootstrap-3.3.7-dist/js/bootstrap-table-zh-CN.min.js"></script>

	<!-- jQuery Validation plugin javascript-->
	<script src="${path }/bootstrap-3.3.7-dist/js/jquery.validate.min.js"></script>
	<script src="${path }/bootstrap-3.3.7-dist/js/messages_zh.min.js"></script>

	<!-- jQuery form  -->
	<script src="${path }/bootstrap-3.3.7-dist/js/jquery.form.min.js"></script>
	<script src="${path }/bootstrap-3.3.7-dist/laydate/laydate.js"></script>

	<script
		src="${path }/bootstrap-3.3.7-dist/js/bootstrap-datetimepicker.js"></script>
	<script
		src="${path }/bootstrap-3.3.7-dist/js/bootstrap-datetimepicker.zh-CN.js"></script>
		
	<script type="text/javascript">
		$(".form_datetime").datetimepicker({
			formatDate : "yyyy-mm-dd",
			autoclose : true,
			todayBtn : true,
			todayHighlight : true,
			showMeridian : true,
			pickerPosition : "bottom-left",
			language : 'zh-CN',//中文，需要引用zh-CN.js包
			startView : 2,//月视图
			minView : 2
		//日期时间选择器所能够提供的最精确的时间选择视图
		});
		
	</script>

	<script type="text/javascript">
		$(function() {
			init();
			$("#btn_search").bind("click", function() {
				//先销毁表格  
				$('#table_user').bootstrapTable('destroy');
				init();
			});
			var validator = $("#form_user").validate({
				submitHandler : function(form) {
					$(form).ajaxSubmit({
						dataType : "json",
						success : function(data) {

							if (data.success && !data.errorMsg) {
								validator.resetForm();
								$('#modal_user_edit').modal('hide');
								$("#btn_search").click();
							} else {
								$("#select_message").text(data.errorMsg);
								$("#alertmod_table_user_mod").show();
							}
						}
					});
				}
			});
			$("#submit_form_user_btn").click(function() {
				$("#form_user").submit();
			});
		});

		var init = function() {
			//1.初始化Table
			var oTable = new TableInit();
			oTable.Init();
			//2.初始化Button的点击事件
			var oButtonInit = new ButtonInit();
			oButtonInit.Init();
		};

		var TableInit = function() {
			var oTableInit = new Object();
			//初始化Table
			oTableInit.Init = function() {
				$('#table_user').bootstrapTable({
					url : 'visitor/visitorList', //请求后台的URL（*）
					method : 'post', //请求方式（*）
					contentType : "application/x-www-form-urlencoded",
					toolbar : '#toolbar', //工具按钮用哪个容器
					striped : true, //是否显示行间隔色
					cache : false, //是否使用缓存，默认为true，所以一般情况下需要设置一下这个属性（*）
					pagination : true, //是否显示分页（*）
					sortable : true, //是否启用排序
					sortName : "id",
					sortOrder : "asc", //排序方式
					queryParams : oTableInit.queryParams,//传递参数（*）
					sidePagination : "server", //分页方式：client客户端分页，server服务端分页（*）
					pageNumber : 1, //初始化加载第一页，默认第一页
					pageSize : 10, //每页的记录行数（*）
					pageList : [ 10, 25, 50, 75, 100 ], //可供选择的每页的行数（*）
					search : false, //是否显示表格搜索，此搜索是客户端搜索，不会进服务端，所以，个人感觉意义不大
					strictSearch : true,
					showColumns : true, //是否显示所有的列
					showRefresh : false, //是否显示刷新按钮
					minimumCountColumns : 2, //最少允许的列数
					clickToSelect : true, //是否启用点击选中行
					// height: 500,                        //行高，如果没有设置height属性，表格自动根据记录条数觉得表格高度
					uniqueId : "id", //每一行的唯一标识，一般为主键列
					showToggle : true, //是否显示详细视图和列表视图的切换按钮
					cardView : false, //是否显示详细视图
					detailView : false, //是否显示父子表
					columns : [ {
						checkbox : true
					}, {
						field : 'id',
						title : '用户编号',
						sortable : true
					}, {
						field : 'name',
						title : '用户名',
						sortable : true
					}, {
						field : 'sex',
						title : '性别',
						sortable : true
					}, {
						field : 'birth',
						title : '出生日期',
						sortable : true
					}, {
						field : 'email',
						title : '电子邮箱',
						sortable : true
					}, {
						field : 'phone',
						title : '联系方式',
						sortable : true
					}, {
						field : 'password',
						title : '密码',
						sortable : true
					} ],
					onClickRow : function(row) {
						$("#alertmod_table_user_mod").hide();
					}
				});
			};

			//得到查询的参数
			oTableInit.queryParams = function(params) {
				var temp = {//这里的键的名字和控制器的变量名必须一致，这边改动，控制器也需要改成一样的
					limit : params.limit, //页面大小
					offset : params.offset, //页码
					username : $("#txt_search_username").val(),
					roleid : $("#txt_search_roleid").val(),
					usertype : $("#txt_search_usertype").val(),
					search : params.search,
					order : params.order,
					ordername : params.sort
				};
				return temp;
			};
			return oTableInit;
		};

		var ButtonInit = function() {
			var oInit = new Object();
			var postdata = {};

			oInit.Init = function() {
				//初始化页面上面的按钮事件
				$("#btn_add")
						.click(
								function() {
									$('#password').attr("readOnly", false).val(
											getSelection.password);
									document
											.getElementById("id").value = '';
									$('#modal_user_edit').modal({
										backdrop : 'static',
										keyboard : false
									});
									$('#modal_user_edit').modal('show');
								});

				$("#btn_edit").click(
						function() {
							var getSelections = $('#table_user')
									.bootstrapTable('getSelections');
							if (getSelections && getSelections.length == 1) {
								initEditUser(getSelections[0]);
								$('#modal_user_edit').modal({
									backdrop : 'static',
									keyboard : false
								});
								$('#modal_user_edit').modal('show');
							} else {
								$("#select_message").text("请选择其中一条数据");
								$("#alertmod_table_user_mod").show();
							}

						});

				$("#btn_delete").click(
						function() {
							var getSelections = $('#table_user')
									.bootstrapTable('getSelections');
							if (getSelections && getSelections.length > 0) {
								$('#modal_user_del').modal({
									backdrop : 'static',
									keyboard : false
								});
								$("#modal_user_del").show();
							} else {
								$("#select_message").text("请选择数据");
								$("#alertmod_table_user_mod").show();
							}
						});

			};

			return oInit;
		};

		$("#alertmod_table_user_mod_a").click(function() {
			$("#alertmod_table_user_mod").hide();
		});

		function initEditUser(getSelection) {
			$('#id').val(getSelection.id);
			$('#sex').val(getSelection.sex);
			$('#name').val(getSelection.name);
			$('#birth').val(getSelection.birth);
			$('#email').val(getSelection.email);
			$('#phone').val(getSelection.phone);
			$('#password').attr("readOnly", true).val(getSelection.password);
		}

		$("#del_user_btn").click(
				function() {
					var getSelections = $('#table_user').bootstrapTable(
							'getSelections');
					var idArr = new Array();
					var ids;
					getSelections.forEach(function(item) {
						idArr.push(item.id);
					});
					ids = idArr.join(",");
					$.ajax({
						url : "${path}/visitor/deleteVisitor",
						dataType : "json",
						data : {
							"ids" : ids
						},
						type : "post",
						success : function(res) {
							if (res.success) {
								$('#modal_user_del').modal('hide');
								$("#btn_search").click();
							} else {
								$("#select_message").text(res.errorMsg);
								$("#alertmod_table_user_mod").show();
							}
						}
					});
				});
	</script>
</body>
</html>