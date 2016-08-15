<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Layout</title>
<link rel="stylesheet" type="text/css"
	href="../jquery-easyui-1.5/themes/default/easyui.css">
<link rel="stylesheet" type="text/css"
	href="../jquery-easyui-1.5/themes/icon.css">
<link rel="stylesheet" type="text/css"
	href="../jquery-easyui-1.5/demo/demo.css">
<script type="text/javascript" src="../jquery-easyui-1.5/jquery.min.js"></script>
<script type="text/javascript"
	src="../jquery-easyui-1.5/jquery.easyui.min.js"></script>
</head>
<body class="easyui-layout">
	<div data-options="region:'north'" style="height: 50px">
		<p>
			<a href="/logout">退出</a>
		</p>
	</div>
	<div data-options="region:'south',split:true" style="height: 30px;"></div>
	<div data-options="region:'east',split:true" title="East"
		style="width: 180px;">
		<ul class="easyui-tree"
			data-options="url:'../js/tree_data1.json',method:'get',animate:true,dnd:true"></ul>
	</div>
	<div data-options="region:'west',split:true" title="菜单"
		style="width: 180px;">
		<ul id="tree"></ul>
	</div>
	<div
		data-options="region:'center',title:'Main Title',iconCls:'icon-ok'">
		<div id="tt" class="easyui-tabs"
			data-options="fit:true,border:false,plain:true">
			<div title="Home" style="padding: 20px; display: none;">Home</div>
		</div>
	</div>
</body>
<script type="text/javascript">
	$(function() {
		// 实例化树菜单
		$("#tree").tree({
			lines : true,
			url:"../js/info.json",
			method:"GET",
			onClick : function(node) {
				addTab(node.text, node.attributes.url);
			}
		});
		
		function addTab(text, url) {
			if ($('#tt').tabs('exists', text)) {
				$('#tt').tabs('select', text);
			} else {
				var content = '<iframe scrolling="auto" frameborder="0"  src="'
						+ url + '" style="width:100%;height:100%;"></iframe>';
				$('#tt').tabs('add', {
					title : text,
					content : content,
					closable : true
				});
			}
		};
	})
</script>
</html>