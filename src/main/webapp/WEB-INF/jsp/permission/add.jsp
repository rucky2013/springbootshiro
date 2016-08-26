<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="../common.jsp"%>
<form id="ff">
	<div style="margin-bottom: 20px">
		<input class="easyui-textbox" name="name" style="width: 100%"
			data-options="label:'资源名称:'" value="${permission.name}">
	</div>
	<div style="margin-bottom: 20px">
		<input id="parentId" class="easyui-combobox" name="parentId" style="width: 100%"
			data-options="label:'上级节点:'">
	</div>
	<div style="margin-bottom: 20px">
		<input class="easyui-textbox" name="permission" style="width: 100%"
			data-options="label:'权限标志:'" value="${permission.permission}">
	</div>
	<div style="margin-bottom: 20px">
		<select class="easyui-combobox" name="resourceType"
			data-options="label:'资源类型:'" style="width: 100%;">
			<option value="menu">菜单</option>
			<option value="button">按钮</option>
		</select>
	</div>
	<div style="margin-bottom: 20px">
		<input class="easyui-textbox" name="url" style="width: 100%"
			data-options="label:'URL'" value="${permission.url}">
	</div>
	<div style="margin-bottom: 20px">
		<select class="easyui-combobox" name="available"
			data-options="label:'是否可用:'" style="width: 100%;">
			<option value="1">是</option>
			<option value="0">否</option>
		</select>
	</div>
</form>
<div style="text-align: center; padding: 5px 0">
	<input type="button" class="d-button d-state-highlight" value="提交"
		onclick="savePermission()" style="width: 80px;" /><input
		type="button" class="d-button d-state-highlight" value="返回"
		onclick="closeLayer()" style="width: 80px;" />
</div>

<script type="text/javascript">
	function savePermission() {
		save('#ff', '/sysper/save');
	}

	$('#parentId').combobox({
		url : 'getParent',
		valueField : 'id',
		textField : 'name',
		multiple : false,
		panelHeight : 'auto',
	});
</script>
