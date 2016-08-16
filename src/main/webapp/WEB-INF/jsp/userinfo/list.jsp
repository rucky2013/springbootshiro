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
		<a class="easyui-linkbutton" onclick="addTab('usr/add.jspx')"
			icons="easyui-icon-add" text="添加" ></a>
		<a class="easyui-linkbutton"
			onclick="deletebyIds('#tt','usr/delete.jspx')"
			iconCls="easyui-icon-cut" text="删除" ></a>
		<a class="easyui-linkbutton" onclick="edit('#tt','usr/edit')"
			iconCls="easyui-icon-edit" text="编辑" ></a>
		<span style="float: right;"><input class="easyui-searchbox"
			data-options="prompt:'请输入用户名',searcher:doSearch"
			style="width: 300px; height: 24px;" /></span> <span style="clear: both;"></span>
		<script>
			function doSearch(value) {
				$("#tt").datagrid("load", {
					queryString : value
				});
			}
		</script>
	</div>
</div>