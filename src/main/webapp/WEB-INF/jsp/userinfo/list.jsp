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
					field : "username",
					title : "用户名",
					width : 160,
					align : 'center'
				},
				{
					field : "name",
					title : "姓名",
					width : 160,
					align : 'center'
				},
				{
					field : "roleList",
					title : "角色",
					width : 160,
					align : 'center'
				},
				{
					field : "state",
					title : "是否启用",
					width : 160,
					align : 'center',
					formatter:FState
				},
				{
					field : 'action',
					title : '操作',
					width : 130,
					align : 'center',
					formatter : function(value, row, index) {
						var str = '';
						<shiro:hasPermission name="userInfo:edit">
						str += $
								.formatString(
										'<a href="javascript:void(0)"   onclick="editLayer(\'{0}\',\'{1}\',\'{2}\');" >编辑</a>',
										'/userInfo/edit', row.uid, '编辑用户');
						</shiro:hasPermission>
						<shiro:hasPermission name="userInfo:del">
						str += '&nbsp;&nbsp;|&nbsp;&nbsp;';
						str += $
								.formatString(
										'<a href="javascript:void(0)"   onclick="deletebyId(\'{0}\',\'{1}\',\'{2}\');" >删除</a>',
										'#tt', row.uid, '/userInfo/del');
						</shiro:hasPermission>
						return str;
					}
				} ] ]
		datagrid("#tt", "", "/userInfo/listDetail", "#tb", "id", columns,
				fcolumn);
	});
</script>
<table id="tt">
</table>
<div id="tb" style="height: 28px; display: none;">
	<div style="margin: 2px;">
		<shiro:hasPermission name="userInfo:add">
			<a href="javascript:void(0)" class="easyui-linkbutton"
				data-options="iconCls:'icon-add',plain:true"
				onclick="addPage('/userInfo/add','添加用户')">添加</a>
		</shiro:hasPermission>
		<shiro:hasPermission name="userInfo:del">
			<a href="javascript:void(0)" class="easyui-linkbutton"
				data-options="iconCls:'icon-remove',plain:true"
				onclick="deletebyIds('#tt','/userInfo/del')">删除</a>
		</shiro:hasPermission>
		<span style="float: right;"><input class="easyui-searchbox"
			data-options="prompt:'请输入用户名',searcher:doSearch"
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
					area : [ '30%', '70%' ],
					content : url,
					scrollbar : true,
					end : function() {
						$("#tt").datagrid('reload');
					}
				});
				top.layer.restore(returnValue);
				return returnValue;
			}
			
			function FState(value, row, index) {
			    if (row.state) {
			        return "否";
			    } else {
			        return "是";
			    }
			}
			
		</script>
	</div>
</div>