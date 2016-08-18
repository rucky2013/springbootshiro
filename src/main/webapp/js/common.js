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

// 弹出即全屏，父窗口
function fullPage(url, title) {
	returnValue = parent.layer.open({
		type : 2,
		title : title,
		maxmin : false,
		area : [ '80%', '80%' ],
		content : url,
		scrollbar : true
	});
	parent.layer.restore(returnValue);
	return returnValue;
}
// 当前窗口
function autoLayer(url, title) {
	var index = layer.open({
		title : title,
		type : 2,
		content : url,
		area : [ '80%', '80%' ],
		maxmin : true
	});
	layer.restore(index);
}
function closeLayer(){
	layer.closeAll('page');
}

function nLayer(url, title) {
	layer.open({
		title : title,
		type : 2,
		content : [ '/userInfo/userAdd', 'no' ]
	// 这里content是一个URL，如果你不想让iframe出现滚动条，你还可以content:
	// ['http://sentsin.com', 'no']
	});
}

function save(formId, saveUrl) {
	var roles = $(formId).serializeArray();
	$.post(saveUrl, roles, function(data) {
		if (data != null) {
			if (data.success) {
				
			}
			layer.msg(data.msg);
		}
	}, "json");
}
