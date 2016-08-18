<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<%@include file="common.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
</head>
<body class="easyui-layout">
	<!-- 左边导航 -->
	<div data-options="region:'west',split:true" title="菜单"
		style="width: 180px;">
		<div class="easyui-accordion" data-options="fit:true,border:false">
			<div title="树" style="padding: 10px;">
				<ul id="tree"></ul>
			</div>
			<div title="Title2" data-options="selected:true">
				<div class="nav_item">
					<a><span onclick="addTab('用户管理','/userInfo/userList')">用户管理</span> </a>
				</div>
				<div class="nav_item">
					<a><span onclick="showDiag">权限管理</span> </a>
				</div>
			</div>
			<div title="Title3" style="padding: 10px">content3</div>
		</div>
	</div>
	<!-- 中间 -->
	<div
		data-options="region:'center',title:'首页',iconCls:'icon-ok',tools:'#tls'">
		<div id="tt" class="easyui-tabs"
			data-options="fit:true,border:false,plain:true">
			<div title="Home" style="padding: 20px; display: none;">Home</div>
		</div>
	</div>
	<div id="tls">
		<div>
			<a href="/logout" class="icon-exit" title="退出系统"></a>
			<span style="margin-right: 20px; color: #000000; font-weight: bold; cursor: pointer;"
			onclick="location.href='/logout'">退出系统</span>
		</div>
	</div>
	
	<!-- 上方 -->
	<div data-options="region:'north'" style="height: 50px"></div>
	<!-- 下方 -->
	<div data-options="region:'south',split:true" style="height: 30px;">
		<div style="text-align: center; color: gray;">
			<span style="color: #000000; padding-left: 10px;">Copyright© 2016</span> &nbsp; 版权所有
		</div>
	</div>
	<!-- 右边 -->
	<div data-options="region:'east',split:true" title="East"
		style="width: 180px;">
		<ul class="easyui-tree"
			data-options="url:'../js/tree_data1.json',method:'get',animate:true,dnd:true"></ul>
	</div>
</body>
<script type="text/javascript">
	$(function() {
		// 实例化树菜单
		$("#tree").tree({
			lines : true,
			url : "../js/info.json",
			method : "GET",
			onClick : function(node) {
				addTab(node.text, node.attributes.url);
			}
		});
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
	}
</script>
</html>