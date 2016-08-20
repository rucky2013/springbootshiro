<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="../common.jsp"%>
<div class="easyui-layout" data-options="fit:true">
	<form id="funcform" style="padding: 20px;">
		<a>用户名<input type="text" class="x-form-text" id="username"
			name="username" value="${user.username}"></a> <input type="button"
			class="d-button d-state-highlight" value="提交" onclick="saveUser()"
			style="width: 80px;" /><input type="button"
			class="d-button d-state-highlight" value="返回" onclick="closeLayer()"
			style="width: 80px;" />
	</form>
	<script type="text/javascript">
		function saveUser() {
			save('#funcform', '/userInfo/userAdd');
		}
	</script>
</div>