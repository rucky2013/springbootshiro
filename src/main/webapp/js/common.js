function datagrid(id, title, dataUrl, toolbar, sortName, columns, fcolumn) {
	pageList = [ 15, 10, 20, 30, 50, 100, 150 ];
	pageSize = 15;
	dataGrid(id, title, dataUrl, toolbar, pageList, pageSize, sortName,
			columns, fcolumn);
}
function dataGrid(id, title, dataUrl, toolbar, pageList, pageSize, sortName,
		columns, fcolumn) {
	$(id).datagrid({
		title : title,
		columns : columns,
		url : dataUrl,
		iconCls : 'easyui-icon-ok',
		width : '100%',
		height : 'auto',
		fit : true,
		pageList : pageList,
		pageSize : pageSize,
		striped : true,
		loadMsg : '正在加载信息',
		sortName : sortName,
		sortOrder : 'asc',
		remoteSort : false,
		toolbar : toolbar,
		frozenColumns : fcolumn,
		pagination : true,
		rownumbers : true,
		fitColumns : true,
		checkOnSelect : false
	});
	$(id).datagrid('getPager').pagination({
	/*
	 * beforePageText : '第', afterPageText : '页 , 共{pages}页', displayMsg :
	 * '从{from}到{to}条记录，共{total}条记录', onBeforeRefresh : function(pageNumber,
	 * pageSize) { $(this).pagination('loading'); $(this).pagination('loaded'); },
	 */
	});
}