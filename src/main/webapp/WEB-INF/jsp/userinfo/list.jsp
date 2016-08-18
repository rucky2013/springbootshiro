<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="../common.jsp"%>
<script type="text/javascript">
	$(function() {
		var fcolumn = [ [ {
			field : "id",
			checkbox : true
		} ] ];
		var columns = [ [ {
			field : "usrName",
			title : "用户名",
			width : 160,
			align : 'center'
		},{
			field : "part",
			title : "部  门",
			width : 200,
			align : 'center'
		}, {
			field : "createTimeStr",
			title : "创建时间",
			width : 200,
			align : 'center'
		}, {
			field : "statusStr",
			title : "状态",
			width : 200,
			align : 'center'
		}, {
			field : "remark",
			title : "备注",
			width : 300
		} ] ];
		datagrid("#tt", "", "usr/list.jspx", "#tb", "id", columns, fcolumn);
	});
</script>
<table id="tt">
</table>
<div id="tb" style="height: 28px; display: none;">
	<div style="margin: 2px;">
		<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-add',plain:true" onclick="openWin('/userInfo/userAdd','添加用户','addUser')">添加</a>
		<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-add',plain:true" onclick="openDiag()">对话框</a>
		<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-add',plain:true" onclick="autoLayer('/userInfo/userAddPage','对话框')">对话框2</a>
	</div>
</div>