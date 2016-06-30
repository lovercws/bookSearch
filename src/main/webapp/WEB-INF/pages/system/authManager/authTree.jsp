<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>菜单树</title>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/themes/default/easyui.css">
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/themes/icon.css">
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery.easyui.min.js"></script>
<style type="text/css">
body{
   margin: 0px;
   padding: 0px;
   width: 100%;
   height: 100%;
   position: absolute;
}
</style>
<script type="text/javascript">
$(document).ready(function(){
	var menuComboTree="${requestScope.menuComboTree}";
	$('#menuTree').treegrid('append',{
		data: eval("("+menuComboTree+")")
	});
});
</script>
</head>
<body>
<table id="menuTree" class="easyui-treegrid" style="width:100%;height:100%;position: relative;"   
        data-options="idField:'id',title:'菜单权限展示树',treeField:'title',animate:true,rownumbers: true,method:'POST'">   
    <thead>
			<tr>
				<th data-options="field:'id',hidden:true" style="width: 0%">权限主键</th>
				<th data-options="field:'parentId',hidden:true" style="width: 0%">父权限主键</th>
				<th data-options="field:'title'" style="width: 10%">权限标题</th>
				<th data-options="field:'code'" style="width: 10%">权限代码</th>
				<th data-options="field:'parentCode'" style="width: 10%">菜单代码</th>
				<th data-options="field:'parentTitle'" style="width: 10%">菜单标题</th>
				<th data-options="field:'src'" style="width: 20%">链接</th>
				<th data-options="field:'order'" style="width: 5%">权限排序</th>
				<th data-options="field:'icon'" style="width: 10%">权限图标</th>
				<th data-options="field:'state'" style="width: 5%">权限状态</th>
				<th data-options="field:'autoShow'" style="width: 9%">自动显示</th>
				<th data-options="field:'createDate'" style="width: 10%">创建时间</th>
			</tr>
	</thead> 
</table> 
</body>
</html>