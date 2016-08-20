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
					field : 'action',
					title : '操作',
					width : 130,
					align : 'center',
					formatter : function(value, row, index) {
	                    var str = '';
                        <shiro:hasPermission name="userInfo:userUpdate">
                            str += $.formatString('<a href="javascript:void(0)" class="user-easyui-linkbutton-edit" data-options="plain:true,iconCls:\'icon-edit\'" onclick="editFun(\'{0}\');" >编辑</a>', row.id);
                        </shiro:hasPermission>
                        <shiro:hasPermission name="userInfo:delOne">
                            str += '&nbsp;&nbsp;|&nbsp;&nbsp;';
                            str += $.formatString('<a href="javascript:void(0)" class="user-easyui-linkbutton-del" data-options="plain:true,iconCls:\'icon-del\'" onclick="deletebyId(\'{0}\',\'{1}\',\'{2}\');" >删除</a>', '#tt',row.uid,'/userInfo/userDelOne');
                        </shiro:hasPermission>
                    return str;
				}
				}] ];
		datagrid("#tt", "", "/userInfo/listDetail", "#tb", "id", columns,
				fcolumn);
	});
</script>
<table id="tt">
</table>
<div id="tb" style="height: 28px; display: none;">
	<div style="margin: 2px;">
		<a href="javascript:void(0)" class="easyui-linkbutton"
			data-options="iconCls:'icon-add',plain:true"
			onclick="autoLayer('/userInfo/userAddPage','新增用户')">添加</a> 
		<a href="javascript:void(0)" class="easyui-linkbutton"
			data-options="iconCls:'icon-remove',plain:true"
			onclick="deletebyIds('#tt','/userInfo/userDelOne')">删除</a>
		<span style="float: right;"><input class="easyui-searchbox"
			data-options="prompt:'请输入用户名',searcher:doSearch"
			style="width: 300px; height: 24px;" /></span> <span style="clear: both;"></span>
		<script type="text/javascript">
			function doSearch(value) {
				$("#tt").datagrid("load", {
					queryString : value
				});
			}
		</script>
	</div>
</div>