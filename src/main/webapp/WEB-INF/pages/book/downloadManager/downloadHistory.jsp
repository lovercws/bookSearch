<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>下载记录(登录用户下载的书籍记录)</title>
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/css/themes/default/easyui.css">
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/css/themes/icon.css">
<script type="text/javascript"
	src="<%=request.getContextPath()%>/js/jquery.min.js"></script>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/js/jquery.easyui.min.js"></script>
<style type="text/css">
body{
    margin:0px;
    padding:0px;
    width: 100%;
    height: 100%;
    position: absolute;
}
</style>
<script type="text/javascript">
$(document).ready(function(){
	$('#downloadHistory').datagrid({
		toolbar: [{
			iconCls: 'icon-edit',
			handler: function(){alert('删除')}
		},'-',{
			iconCls: 'icon-help',
			handler: function(){alert('帮助按钮')}
		},'-',{
			iconCls: 'icon-edit',
			handler: function(){alert('导出')}
		}],
		onDblClickCell: function(index,field,value){
		}
	});
});
</script>
</head>
<body>
	<table id="downloadHistory" class="easyui-datagrid" data-options="iconCls:'icon-save',plain:true,fitColumns:true,rownumbers:true,singleSelect:true,pagePosition:'bottom',pageNumber:1,pageSize:10,title:'下载记录'" style="width: 100%;height: 96%;position: relative;">
		<thead>
			<tr>
				<th data-options="field:'isbn'" style="width: 10%">ISBN</th>
				<th data-options="field:'bookName'" style="width: 20%">书名</th>
				<th data-options="field:'bookAuthor'" style="width: 10%">作者</th>
				<th data-options="field:'bookPublisher'" style="width: 10%">出版社</th>
				<th data-options="field:'bookTitle'" style="width: 30%">主题</th>
				<th data-options="field:'bookCategory'" style="width: 10%">分类</th>
				<th data-options="field:'bookFileuploadDate'" style="width: 9%">下载时间</th>
			</tr>
		</thead>
		<tbody>
			<tr>
				<td>001</td>
				<td>name1</td>
				<td>2323</td>
				<td>2323</td>
				<td>2323</td>
				<td>2323</td>
				<td>2323</td>
			</tr>
			<tr>
				<td>001</td>
				<td>name1</td>
				<td>2323</td>
				<td>2323</td>
				<td>2323</td>
				<td>2323</td>
				<td>2323</td>
			</tr>
		</tbody>
	</table>
	<div id="pp" class="easyui-pagination" data-options="total:2000,pageSize:10" style="background:#efefef;border:1px solid #ccc;"></div> 
</body>
</html>