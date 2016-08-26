<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="../common.jsp"%>
<script type="text/javascript">
	$(function() {
		var fcolumn = [ [ {
			field : "id",
			checkbox : true
		} ] ];
		var columns = [ [
				{
					field : "description",
					title : "角色名称",
					width : 100,
					align : 'center',
					
				},
				{
					field : "role",
					title : "角色标识",
					width : 160,
					align : 'center'
				},
				{
					field : "available",
					title : "是否可用",
					width : 120,
					align : 'center'
				},
				{
					field : 'action',
					title : '操作',
					width : 130,
					align : 'center',
					formatter : function(value, row, index) {
						var str = '';
						<shiro:hasPermission name="role:edit">
						str += $
								.formatString(
										'<a href="javascript:void(0)"   onclick="editLayer(\'{0}\',\'{1}\',\'{2}\');" >编辑</a>',
										'/role/edit', row.id, '编辑角色');
						</shiro:hasPermission>
						<shiro:hasPermission name="role:del">
						str += '&nbsp;&nbsp;|&nbsp;&nbsp;';
						str += $
								.formatString(
										'<a href="javascript:void(0)"   onclick="deletebyId(\'{0}\',\'{1}\',\'{2}\');" >删除</a>',
										'#tt', row.id, '/role/del');
						</shiro:hasPermission>
						return str;
					}
				} ] ]
		datagrid("#tt", "", "/role/listRole", "#tb", "id", columns,
				fcolumn);
	});
</script>
<table id="tt">
</table>
<div id="tb" style="height: 28px; display: none;">
	<div style="margin: 2px;">
		<a href="javascript:void(0)" class="easyui-linkbutton"
			data-options="iconCls:'icon-add',plain:true"
			onclick="addPage('/role/add','添加角色')">添加</a> <a
			href="javascript:void(0)" class="easyui-linkbutton"
			data-options="iconCls:'icon-remove',plain:true"
			onclick="deletebyIds('#tt','/role/del')">删除</a> <span
			style="float: right;"><input class="easyui-searchbox"
			data-options="prompt:'请输入资源名称或描述',searcher:doSearch"
			style="width: 300px; height: 24px;" /></span> <span style="clear: both;"></span>
		<script type="text/javascript">
			function doSearch(value) {
				$("#tt").datagrid("load", {
					queryString : value
				});
			}
			function addPage(url, title) {
				returnValue = top.layer.open({
					type : 2,
					title : title,
					maxmin : false,
					area : [ '45%', '90%' ],
					content : url,
					scrollbar : true,
					end : function() {
						$("#tt").datagrid('reload');
					}
				});
				top.layer.restore(returnValue);
				return returnValue;
			}
			
		</script>
	</div>
</div>