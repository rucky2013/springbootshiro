<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="../common.jsp"%>
<form id="ff">
	<div style="margin-bottom: 20px">
		<input class="easyui-textbox" name="username" style="width: 100%"
			data-options="label:'用户名:'" value="${user.username}">
	</div>
	<div style="margin-bottom: 20px">
		<input class="easyui-textbox" name="name" style="width: 100%"
			data-options="label:'姓名'" value="${user.name}">
	</div>
	<div style="margin-bottom: 20px">
		<select class="easyui-combobox" name="roleList"
			data-options="label:'角色'" style="width: 100%;">
			<option value="1">管理员</option>
			<option value="2">用户</option>
		</select>
	</div>
</form>
<div style="text-align: center; padding: 5px 0">
	<input type="button" class="d-button d-state-highlight" value="提交"
		onclick="saveUser()" style="width: 80px;" /><input type="button"
		class="d-button d-state-highlight" value="返回" onclick="closeLayer()"
		style="width: 80px;" />
</div>

<script type="text/javascript">
	function saveUser() {
		save('#ff', '/userInfo/save');
	}
</script>
