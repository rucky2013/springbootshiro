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
					field : "id",
					title : "资源ID",
					width : 100,
					align : 'center',

				},
				{
					field : "name",
					title : "资源名称",
					width : 160,
					align : 'center'
				},
				{
					field : "parentId",
					title : "上级资源ID",
					width : 120,
					align : 'center'
				},
				{
					field : "permission",
					title : "权限",
					width : 200,
					align : 'center'
				},
				{
					field : "resourceType",
					title : "资源类型",
					width : 160,
					align : 'center'
				},
				{
					field : "url",
					title : "URL",
					width : 200,
					align : 'center'
				},
				{
					field : "available",
					title : "是否可用",
					width : 160,
					align : 'center'
				},
				{
					field : 'action',
					title : '操作',
					width : 130,
					align : 'center',
					formatter : function(value, row, index) {
						var str = '';
						<shiro:hasPermission name="permission:edit">
						str += $
								.formatString(
										'<a href="javascript:void(0)"   onclick="editLayer(\'{0}\',\'{1}\',\'{2}\');" >编辑</a>',
										'/sysper/edit', row.id, '编辑资源');
						</shiro:hasPermission>
						<shiro:hasPermission name="permission:del">
						str += '&nbsp;&nbsp;|&nbsp;&nbsp;';
						str += $
								.formatString(
										'<a href="javascript:void(0)"   onclick="deletebyId(\'{0}\',\'{1}\',\'{2}\');" >删除</a>',
										'#tt', row.id, '/sysper/del');
						</shiro:hasPermission>
						return str;
					}
				} ] ]
		datagrid("#tt", "", "/sysper/listPermission", "#tb", "id", columns,
				fcolumn);
	});
</script>
<table id="tt">
</table>
<div id="tb" style="height: 28px; display: none;">
	<div style="margin: 2px;">
		<shiro:hasPermission name="permission:add">
			<a href="javascript:void(0)" class="easyui-linkbutton"
				data-options="iconCls:'icon-add',plain:true"
				onclick="addPage('/sysper/add','添加用户')">添加</a>
		</shiro:hasPermission>
		<shiro:hasPermission name="permission:del">
			<a href="javascript:void(0)" class="easyui-linkbutton"
				data-options="iconCls:'icon-remove',plain:true"
				onclick="deletebyIds('#tt','/sysper/del')">删除</a>
		</shiro:hasPermission>
		<span style="float: right;"><input class="easyui-searchbox"
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
					area : [ '20%', '70%' ],
					content : url,
					scrollbar : true,
					end : function() {
						$("#tt").datagrid('reload');
					}
				});
				top.layer.restore(returnValue);
				return returnValue;
			}

			$('#tt').datagrid({
				rowStyler : function(index, row) {
					if (row.resourceType == "menu") {
						return 'background-color:#87CEEB'
					}
				}
			})
		</script>
	</div>
</div>