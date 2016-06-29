<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>分类列表</title>
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
	$('#categoryTable').datagrid({
		toolbar: [{
			text:'添加',
			iconCls: 'icon-add',
			handler: function(){
				$('#detailCategoryWin').window('open');
			}
		},'-',{
			text:'删除',
			iconCls: 'icon-remove',
			handler: function(){
				
			}
		},'-',{
			text:'编辑',
			iconCls: 'icon-edit',
			handler: function(){
				
			}
		}],
		onDblClickCell: function(index,field,value){
		}
	});
	
	$('#detailCategoryWin').window('close');
});
//关闭分类详情弹框
function closeCategory(){
	$('#detailCategoryForm').form('reset');
	$('#detailCategoryWin').window('close');
}
//新添分类
function addCategory(){
	
}
</script>
</head>
<body>
	<table id="categoryTable" class="easyui-datagrid" data-options="iconCls:'icon-help',plain:true,fitColumns:true,rownumbers:true,singleSelect:true,pagePosition:'bottom',pageNumber:1,pageSize:10" style="width: 100%;height: 96%;position: relative;">
		<thead>
			<tr>
				<th data-options="field:'isbn'" style="width: 10%">分类ID</th>
				<th data-options="field:'bookName'" style="width: 20%">分类名称</th>
				<th data-options="field:'bookAuthor'" style="width: 10%">上级分类名称</th>
				<th data-options="field:'bookPublisher'" style="width: 10%">顺序</th>
				<th data-options="field:'bookTitle'" style="width: 30%">分类图标</th>
				<th data-options="field:'bookCategory'" style="width: 10%">分类</th>
				<th data-options="field:'bookFileuploadDate'" style="width: 9%">上传时间</th>
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
	
	<!-- 分类详情弹框 -->
	<div id="detailCategoryWin" class="easyui-window" title="分类详情" style="width:600px;height:400px"   
        data-options="iconCls:'icon-save',modal:true,collapsible:false,inline:true">   
	    <div class="easyui-layout" data-options="fit:true">   
	           <form id="detailCategoryForm" method="post" style="height: 400px">
		           <table align="center" style="margin-top: 50px">
		              <tr>
		                <td><label for="name">分类代码</label></td>
		                <td><input class="easyui-textbox" data-options="iconCls:'icon-search'" style="width:300px"></td>
		              </tr>
		              <tr>
		                <td><label for="name">分类名称</label></td>
		                <td><input class="easyui-textbox" data-options="iconCls:'icon-search'" style="width:300px"></td>
		              </tr>
		              <tr>
		                <td><label for="email">上级分类名称</label></td>
		                <td><select id="cc" class="easyui-combotree" style="width:300px;" data-options="url:'<%=request.getContextPath()%>/book/categoryManagerAction_getCategoryCombotree.do',required:false"></select> </td>
		              </tr>
		              <tr>
		                <td colspan="2" align="center">
		                	<button type="button" style="background-color: silver" onclick="addCategory()">保存</button>
		                	<button type="button" style="background-color: silver" onclick="closeCategory()">关闭</button>
		                </td>
		              </tr>
		           </table>   
			   </form>  
	    </div>   
	</div>  
	<div id="pp" class="easyui-pagination" data-options="total:2000,pageSize:10" style="background:#efefef;border:1px solid #ccc;"></div> 
</body>
</html>