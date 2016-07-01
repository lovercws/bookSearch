<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>性别数据字典</title>
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/css/themes/default/easyui.css">
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/css/themes/icon.css">
<script type="text/javascript"
	src="<%=request.getContextPath()%>/js/jquery.min.js"></script>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/js/jquery.easyui.min.js"></script>
<style type="text/css">
body {
	margin: 0px;
	padding: 0px;
	width: 100%;
	height: 100%;
	position: absolute;
}
</style>
<script type="text/javascript">
$(document).ready(function() {
	var sexTable=$('#sexTable');
	var sexList="${requestScope.sexList}";//获取性别列表数据
	
	sexTable.datagrid({
		data:eval('('+sexList+')'),
		toolbar : [ {
			iconCls : 'icon-add',
			text : '添加',
			hidden:true,
			handler : function() {
				//总行数
				var allRows=sexTable.datagrid('getRows');
				//添加行
				sexTable.datagrid('appendRow',{id:allRows.length});
				//选中最后一行
				sexTable.datagrid('selectRow',allRows.length-1);
				//编辑行
				editRow();
			}
		}, {
			iconCls : 'icon-edit',
			text : '编辑',
			handler : function() {
				var row = sexTable.datagrid('getSelected');
				if(row){
					editRow();
				}else{
					$.messager.alert('警告','请选择一行编辑');
				}
			}
		}, '-', {
			iconCls : 'icon-remove',
			text : '删除',
			handler : function() {
				var row = sexTable.datagrid('getSelected');
				if(row){
					$.messager.confirm('确认','您确认想要删除记录吗？',function(r){    
					    if (r){    
					    	$.ajax({
								url:'<%=request.getContextPath()%>/ddl/sexDDLManagerAction_delete.do',
								type: "POST",
								data:'sexId='+row.sexId,
								success: function(html){
								    window.location.reload();
								}
							});    
					    }    
					});  
				}else{
					$.messager.alert('警告','请选择一行删除');
				}
			}
		}, '-', {
			iconCls : 'icon-remove',
			text : '全部删除',
			handler : function() {
				var rows = sexTable.datagrid('getRows');
				if(rows&&rows.length>0){
					$.messager.confirm('确认','您确认想要删除记录吗？',function(r){    
					    if (r){    
					    	$.ajax({
								url:'<%=request.getContextPath()%>/ddl/sexDDLManagerAction_deleteAll.do',
								type: "POST",
								success: function(html){
								    window.location.reload();
								}
							});   
					    }    
					});  
				}else{
					$.messager.alert('警告','没有数据需要删除');
				}
			}
		}, '-', {
			iconCls : 'icon-save',
			text : '保存',
			handler : function() {
				var row = sexTable.datagrid('getSelected');
				if(row){
					$.ajax({
						url:'<%=request.getContextPath()%>/ddl/sexDDLManagerAction_save.do',
						type: "POST",
						data:'data='+JSON.stringify(row),
						success: function(html){
						    window.location.reload();
						},
						error:function (XMLHttpRequest, textStatus, errorThrown) {
							$.messager.alert('警告','服务端出现异常');
						}
					});
				}else{
					$.messager.alert('警告','请选择一行保存');
				}
				
			}
		} ],
		onClickRow:function(row){//当单击其他行 取消编辑
			 if (editingId != undefined&&row.sexId!=editingId){
		        	//取消编辑
		            sexTable.datagrid('endEdit', editingId);
		            editingId = undefined;
			 }
		},  
		onDblClickRow: function(row){
			editRow(); 
		}
	});
	
	//双击编辑行
	var editingId;//编辑的行id
	function editRow(){
        if (editingId != undefined){
        	sexTable.datagrid('endEdit', editingId);
        }
        var row = sexTable.datagrid('getSelected');
        var rowIndex = sexTable.datagrid('getRowIndex',row);
        if (row){
            editingId = rowIndex;
            sexTable.datagrid('beginEdit', editingId);
        }
    }
});	

//下拉框转换
function unitformatter(value, rowData, rowIndex) {
    if(value==false||value=='false'){
    	return '禁用';
    }else {
    	return '可用';
    }
}
</script>
</head>
<body>
	<table id="sexTable" class="easyui-datagrid"
		data-options="title:'性别列表',plain:true,fitColumns:true,rownumbers:true,singleSelect:true,halign:'center',align:'center'"
		style="width: 100%; height: 96%; position: relative;">
		<thead>
			<tr>
				<th data-options="field:'sexId',hidden:true" style="width: 0%">性别主键</th>
				<th data-options="field:'sexCode',editor:'text'" style="width: 20%">性别代码</th>
				<th data-options="field:'sexName',editor:'text'" style="width: 20%">性别名称</th>
				<th data-options="field:'sexActive',formatter: unitformatter,editor:{ type: 'combobox',options: { data: [{ 'value': 'true', 'text': '可用' },{ 'value': 'false', 'text': '禁用' }], valueField: 'value', textField: 'text' }}" style="width: 10%">是否可用</th>
				<th data-options="field:'sexRemark',editor:'text'" style="width: 48%">性别描述</th>
			</tr>
		</thead>
	</table>
</body>
</html>