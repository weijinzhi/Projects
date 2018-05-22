<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!doctype html>
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport"
	content="initial-scale=1.0, user-scalable=no, width=device-width">
<title>基本地图展示</title>
<link rel="stylesheet"
	href="http://cache.amap.com/lbs/static/main1119.css" />
<script src="http://cache.amap.com/lbs/static/es5.min.js"></script>
<script
	src="http://webapi.amap.com/maps?v=1.4.5&key=19db227a9876393b5a95c74b3ffcb5d1"></script>
<script type="text/javascript"
	src="http://cache.amap.com/lbs/static/addToolbar.js"></script>
<script type="text/javascript" src="https://webapi.amap.com/demos/js/liteToolbar.js"></script>
<style type="text/css">
#panel {
	position: fixed;
	background-color: white;
	max-height: 90%;
	overflow-y: auto;
	top: 10px;
	right: 10px;
	width: 280px;
}
.marker {
    color: #ff6600;
    padding: 4px 10px;
    border: 1px solid #fff;
    white-space: nowrap;
    font-size: 12px;
    font-family: "";
    background-color: #0066ff;
}
</style>
</head>
<body>
	<div id="container"></div>
	<div id="myPageTop">
		<table>
			<tr>
				<td><label>按关键字搜索：</label></td>
				<td class="column2"><label>左击获取经纬度：</label></td>
			</tr>
			<tr>
				<td><input type="text" placeholder="请输入关键字进行搜索" id="tipinput">
				</td>
				<td class="column2"><input type="text" readonly="true"
					id="lnglat"></td>
			</tr>
		</table>
	</div>
	<div id="panel"></div>
	<div class="button-group">
		<input id="cityName" class="inputtext" placeholder="请输入城市的名称"
			type="text" /> <input id="query" class="button" value="到指定的城市"
			type="button" />
			<br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		<input id="add" class="button" value="添  加  路  线"
			type="button" />
	</div>
	
	<!-- 模态框（Modal） -->
<!-- 修改 -->
<div id="passDlg" class="modal fade"  tabindex="-1" role="dialog" data-backdrop="static" data-keyboard="false" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h4 class="modal-title" id="myModalLabel">添加路线</h4>
            </div>
            <div class="container">
			<form class="form-horizontal" id="myform"  method="post">
			<div class="form-group">
			<label class="col-md-2 control-label">起点：</label>
			<div class="col-md-3 ">
			<input type="password" id="oldPass" name="oldPass" class="form-control form-control-static"  placeholder="">
			<input  type="hidden" id="pass" value="" name="password">
			<input  type="hidden" id="uid" value="" name="id">
			</div>
			</div>
			
			<div class="form-group">
			<label class="col-md-2 control-label">终点：</label>
			<div class="col-md-3 ">
			<input type="text" id="newPass"  name="newPass" class="form-control form-control-static" placeholder="">
			</div>
			</div>
			
			<div class="form-group">
			<label class="col-md-2 control-label">权值：</label>
			<div class="col-md-3">
			<input type="text" id="againPass"  name="againPass" class="form-control form-control-static" placeholder="">
			</div>
			</div>
            <div class="modal-footer col-md-6">
            <!--用来清空表单数据-->
            <input type="reset" name="reset" style="display: none;" />
                <button type="button" class="btn btn-default" onclick="closeDlgs()">关闭</button>
               <button type="button" onclick="upPass()" class="btn btn-primary">添加</button>
            </div>
            </form>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal -->
</div>
	
	<script>
	    var markers = [];   
		var map = new AMap.Map('container', {
			resizeEnable : true,
			center : [ 120.35601472635308, 30.316113255236537 ],//地图中心点
			zoom : 20,
		});
		//设置城市
		AMap.event.addDomListener(document.getElementById('query'), 'click',
				function() {
					var cityName = document.getElementById('cityName').value;
					if (!cityName) {
						cityName = '北京市';
					}
					map.setCity(cityName);
				});
		//为地图注册click事件获取鼠标点击出的经纬度坐标
		var clickEventListener = map.on('click',function(e) {
			marker = new AMap.Marker({
	            icon: "http://webapi.amap.com/theme/v1.3/markers/n/mark_b.png",
	            position: [120.355846,30.316824]
	        });
	        marker.setMap(map);
			document.getElementById("lnglat").value = e.lnglat.getLng()+ ',' + e.lnglat.getLat();
		});
		$('#add').click(function(){
			$("#passDlg").modal('show');
		});
	</script>
</body>
</html>