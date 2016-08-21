/**
 * 
 * 增加formatString功能
 * 
 * 使用方法：$.formatString('字符串{0}字符串{1}字符串','第一个变量','第二个变量');
 * 
 * @returns 格式化后的字符串
 */
$.formatString = function(str) {
    for ( var i = 0; i < arguments.length - 1; i++) {
        str = str.replace("{" + i + "}", arguments[i + 1]);
    }
    return str;
}

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
		checkOnSelect : true
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
		maxmin : false
	});
	layer.restore(index);
}

//当前窗口
function editLayer(url, rowid,title) {
	var index = layer.open({
		title : title,
		type : 2,
		content : url+"?rowid="+rowid,
		area : [ '80%', '80%' ],
		maxmin : false,

	});
	layer.restore(index);
}

function closeLayer() {
	var index = parent.layer.getFrameIndex(window.name); // 先得到当前iframe层的索引
	parent.layer.close(index); // 执行关闭自身操作
}

function nLayer(url, title) {
	layer.open({
		title : title,
		type : 2,
		content : [ url, 'no' ]
	// 这里content是一个URL，如果你不想让iframe出现滚动条，你还可以content:
	// ['http://sentsin.com', 'no']
	});
}

function save(formId, saveUrl) {
	var roles = $(formId).serializeArray();
	$.post(saveUrl, roles, function(data) {
		if (data != null) {
			if (data.success) {
				parent.layer.msg(data.msg);
				parent.$("#tt").datagrid('reload');
				closeLayer();
			} else {
				parent.layer.msg(data.msg);
			}
		}
	}, "json");
}

/**
 * 删除信息
 * 
 * @returns
 */
function deletebyIds(id, delUrl) {
	var rows = $(id).datagrid("getSelections");// 获取多条记录
	if (rows.length <= 0) {
		layer.msg("没有选择要删除的记录",{
			time: 1000
		});
		layer.tips('请选择要删除的记录', id);
		return false;
	}
	layer.confirm('确定删除'+rows.length+'条记录？', {
		  btn: ['确定','取消'] //按钮
		}, function() {
		if (rows && rows.length > 0) {
			var idss = new Array();
			for ( var i = 0; i < rows.length; i++) {
				idss.push(rows[i].uid);
			}
			$.post(delUrl, {
				ids : idss.toString()
			}, function(data) {
				if (data.success) {
					$(id).datagrid('reload');
					layer.msg(data.msg);
				} else {
					layer.msg(data.msg);
				}
			}, "json");
		}
	});
}

function deletebyId(id,rowid, delUrl) {
	layer.confirm('确定删除？', {
		  btn: ['确定','取消'] //按钮
		}, function() {
			$.post(delUrl, {
				ids : rowid.toString()
			}, function(data) {
				if (data.success) {
					$(id).datagrid('reload');
					layer.msg(data.msg);
				} else {
					layer.msg(data.msg);
				}
			}, "json");
	});
}
