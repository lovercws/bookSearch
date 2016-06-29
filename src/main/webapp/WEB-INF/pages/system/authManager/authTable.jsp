<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>权限列表</title>
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
	$('#authTable').datagrid({
		toolbar: [{
			text:'添加',
			iconCls: 'icon-add',
			handler: function(){
				$('#detailAuthWin').window('open');
			}
		},'-',{
			text:'删除',
			iconCls: 'icon-remove',
			handler: function(){
				var selections=$("#authTable").datagrid('getSelections');
				if(selections&&selections.length>0){
					$.messager.confirm('确认','您确认想要删除记录吗？',function(r){    
					    if (r){    
					    	var menuId=selections[0].id;
							window.location.href="<%=request.getContextPath()%>/system/authManagerAction_deleteAuth.do?id="+menuId;
					    }    
					}); 
				}else{
					$.messager.alert('提示','请选择一条记录');    
				}
			}
		},'-',{
			text:'编辑',
			iconCls: 'icon-edit',
			handler: function(){
				editAuthForm();
			}
		},'-',{
			text:'更新',
			iconCls: 'icon-edit',
			handler: function(){
				window.location.reload();
			}
		}],
		onDblClickCell: function(index,field,value){
			editAuthForm();
		}
	});
	//菜单 下拉树
	var data='${requestScope.authComboTree}';
	data=eval("("+data+")");
	$("#authComboTree").combotree({
		data:data
	});
	
	//编辑菜单表单
	function editAuthForm(){
		var selections=$("#authTable").datagrid('getSelections');
		if(selections&&selections.length>0){
			console.log(selections[0]);
			$('#detailAuthForm').form('load',selections[0]);
			$('#detailAuthWin').window('open');
		}else{
			$.messager.alert('提示','请选择一条记录');  
		}
	}
	
	//默认关闭 添加菜单window、导入菜单window
	$('#detailAuthWin').window('close');
	
});

//验证权限 表单
function validateAuthForm(){
	var validated=$('#detailAuthForm').form('validate');
	if(validated){
		$('#detailAuthForm').submit();
	}
}
//关闭权限详情弹框
function closeAuthForm(){
	$('#detailAuthForm').form('clear');
	$('#detailAuthWin').window('close');
}
</script>
</head>
<body>
<table id="authTable" class="easyui-datagrid" data-options="title:'权限列表',plain:true,fitColumns:true,rownumbers:true,singleSelect:true,halign:'center',align:'center'" style="width: 100%;height: 96%;position: relative;">
		<thead>
			<tr>
				<th data-options="field:'id',hidden:true" style="width: 0%">权限主键</th>
				<th data-options="field:'parentId',hidden:true" style="width: 0%">父权限主键</th>
				<th data-options="field:'code'" style="width: 10%">权限代码</th>
				<th data-options="field:'title'" style="width: 10%">权限标题</th>
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
		<tbody>
		    <s:iterator value="#request.authList" var="auth">
		      <tr>
				<td><s:property value="#auth.id"/></td>
				<td><s:property value="#auth.parentId"/></td>
				<td><s:property value="#auth.code"/></td>
				<td><s:property value="#auth.title"/></td>
				<td><s:property value="#auth.parentCode"/></td>
				<td><s:property value="#auth.parentTitle"/></td>
				<td><s:property value="#auth.src"/></td>
				<td><s:property value="#auth.order"/></td>
				<td><s:property value="#auth.icon"/></td>
				<td><s:property value="#auth.state"/></td>
				<td><s:property value="#auth.autoShow"/></td>
				<td><s:date name="#auth.createDate" format="yyyy-MM-dd HH:mm:ss"/>  </td>
		      </tr>
		    </s:iterator>
		</tbody>
	</table>
	<!-- 添加权限 -->
	<div id="detailAuthWin" class="easyui-window" title="添加权限" style="width:600px;height:400px"   
        data-options="iconCls:'icon-save',modal:true,collapsible:false,inline:true">   
	    <div class="easyui-layout" data-options="fit:true">   
	           <form id="detailAuthForm" method="post" style="height: 400px" action="<%=request.getContextPath()%>/system/authManagerAction_saveAuth.do">
		           <input type="hidden" name="id">
		           <table align="center" style="margin-top: 20px">
		              <tr>
		                <td><label>权限代码</label></td>
		                <td><input class="easyui-textbox" name="code" data-options="required:true" style="width:300px"></td>
		              </tr>
		              <tr></tr>
		              <tr>
		                <td><label>权限标题</label></td>
		                <td><input class="easyui-textbox" name="title" data-options="required:true" style="width:300px"></td>
		              </tr>
		              <tr></tr>
		              <tr>
		                <td><label>菜单标题</label></td>
		                <td><select id="authComboTree" name="parentId"  class="easyui-combotree" style="width:300px;" data-options="valueField:'id',textField: 'title'"></select> </td>
		              </tr>
		              <tr></tr>
		              <tr>
		                <td><label>权限链接</label></td>
		                <td><input class="easyui-textbox" name="src" style="width:300px"></td>
		              </tr>
		              <tr></tr>
		              <tr>
		                <td><label>权限排序</label></td>
		                <td><input class="easyui-numberspinner" name="order" value="1" data-options="min:1,editable:true" style="width:300px"></td>
		              </tr>
		              <tr></tr>
		              <tr>
		                <td><label>权限图标</label></td>
		                <td><input class="easyui-textbox" name="icon" style="width:300px"></td>
		              </tr>
		              <tr></tr>
		              <tr>
		                <td><label>权限状态</label></td>
		                <td><input class="easyui-combobox" name="state" data-options="valueField: 'label',textField: 'value',data: [{label: 'open',value: 'open'},{label: 'closed',value: 'closed'}],required:true"  style="width:300px"/></td>
		              </tr>
		              <tr></tr>
		              <tr>
		                <td><label>自动显示</label></td>
		                <td><input class="easyui-switchbutton" name="autoShow" data-options="onText:'true',offText:'false',value:'true'"></td>
		              </tr>
		              <tr></tr>
		              <tr>
		                <td colspan="2" align="center">
		                	<button type="button" style="background-color: silver" onclick="validateAuthForm()">保存</button>
		                	<button type="button" style="background-color: silver" onclick="closeAuthForm()">关闭</button>
		                </td>
		              </tr>
		           </table>   
			   </form>  
	    </div>   
	</div>  
	<div id="pp" class="easyui-pagination" data-options="total:2000,pageSize:10" style="background:#efefef;border:1px solid #ccc;"></div> 
</body>
</html>