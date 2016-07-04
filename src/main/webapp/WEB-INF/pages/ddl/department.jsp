<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>部门数据字典</title>
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
</head>
<body>
<table id="sexTable" class="easyui-datagrid"
		data-options="title:'部门列表',plain:true,fitColumns:true,rownumbers:true,singleSelect:true,halign:'center',align:'center'"
		style="width: 100%; height: 96%; position: relative;">
		<thead>
			<tr>
				<th data-options="field:'sexId',hidden:true" style="width: 0%">性别主键</th>
				<th data-options="field:'sexCode',editor:'text'" style="width: 20%">性别代码</th>
				<th data-options="field:'sexName',editor:'text'" style="width: 20%">性别名称</th>
				<th data-options="field:'sexActive',formatter: unitformatter,editor:{type:'text'}" style="width: 10%">是否可用</th>
				<th data-options="field:'sexRemark',editor:'text'" style="width: 48%">性别描述</th>
			</tr>
		</thead>
	</table>
</body>
</html>