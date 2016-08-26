<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="../common.jsp"%>
<script type="text/javascript">
	var resourceTree;
	$(function() {
		resourceTree = $('#resourceTree').tree({
			url : 'allTrees',
			parentField : 'pid',
			lines : true,
			checkbox : true,
			cascadeCheck : false
		});
	})
	function checkAll() {
		var nodes = resourceTree.tree('getChecked', 'unchecked');
		if (nodes && nodes.length > 0) {
			for (var i = 0; i < nodes.length; i++) {
				resourceTree.tree('check', nodes[i].target);
			}
		}
	}
	function uncheckAll() {
		var nodes = resourceTree.tree('getChecked');
		if (nodes && nodes.length > 0) {
			for (var i = 0; i < nodes.length; i++) {
				resourceTree.tree('uncheck', nodes[i].target);
			}
		}
	}
	function checkInverse() {
		var unchecknodes = resourceTree.tree('getChecked', 'unchecked');
		var checknodes = resourceTree.tree('getChecked');
		if (unchecknodes && unchecknodes.length > 0) {
			for (var i = 0; i < unchecknodes.length; i++) {
				resourceTree.tree('check', unchecknodes[i].target);
			}
		}
		if (checknodes && checknodes.length > 0) {
			for (var i = 0; i < checknodes.length; i++) {
				resourceTree.tree('uncheck', checknodes[i].target);
			}
		}
	}
</script>
<form id="ff" >
	<div style="width: 100%; height: 80%;">
		<div style="width: 60%; height: 80%; float: left; overflow: auto;">
			<table>
				<tr>
					<td>角色名称<span style="color: red;">*</span></td>
					<td><input type="text" class="x-form-text" name="role"
						id="role" value="${role.role}" /></td>
				</tr>
				<tr>
					<td>是否启用<span style="color: red;">*</span></td>
					<td><select class="easyui-combobox" name="available"
						style="width: 100%;">
							<option value="1">启用</option>
							<option value="0">禁用</option>
					</select></td>
				</tr>
				<tr>
					<td>角色描述</td>
					<td><input type="text" class="x-form-text" name="description"
						id="description" value="${role.description}" /></td>
				</tr>
			</table>
		</div>
		<div style="width: 40%; height: 80%; float: left; overflow: auto;">
			<div class="easyui-panel" title="栏目及功能信息" fit="true"
				data-options="tools:'#tls'">
				<ul id="resourceTree"></ul>
			</div>
		</div>
	</div>
	<div style="width: 100%; height: 10%;">
		<div style="text-align: center; ">
			<input type="button" class="d-button d-state-highlight" value="提交"
				onclick="savePermission()" style="width: 80px;" /><input
				type="button" class="d-button d-state-highlight" value="返回"
				onclick="closeLayer()" style="width: 80px;" />
		</div>
	</div>
</form>
<div id="tls">
	<div>
		<span
			style="margin-right: 5px; color: #000000; font-weight: bold; cursor: pointer;"
			onclick="checkAll();">全选</span> <span
			style="margin-right: 5px; color: #000000; font-weight: bold; cursor: pointer;"
			onclick="checkInverse();">反选</span> <span
			style="margin-right: 5px; color: #000000; font-weight: bold; cursor: pointer;"
			onclick="uncheckAll();">取消</span>
	</div>3
</div>
<script type="text/javascript">
	function savePermission() {
		save('#ff', '/role/save');
	}
</script>